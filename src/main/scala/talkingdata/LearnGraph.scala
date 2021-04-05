package talkingdata

import org.apache.log4j.{Level, Logger}
import org.apache.spark.graphx.{Edge, EdgeDirection, EdgeTriplet, Graph, VertexId}
import org.apache.spark.sql.SparkSession

import scala.collection.mutable.{ListBuffer,Map}

/**
  * 连通图
  */
object LearnGraph {
  //这里使用位图的方法可以减少标签的存储空间
  private val SHIFT = 0x5 //位移
  private val MASK = 0x1F

  def main(args: Array[String]) {

//    Logger.getLogger("org").setLevel(Level.ERROR)
    val spark = SparkSession
      .builder()
      .appName("SparkSQLTest")
      .config("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
      .master("local")
      .getOrCreate()
    val sc = spark.sparkContext

    val dataSource = List("1 1", "2 1", "2 2", "3 2",
      "4 3", "5 3", "5 4", "6 4", "6 5", "7 5",
      "8 7")

    val rdd = sc.parallelize(dataSource).map { x => {
      val data = x.split(" ")
      (data(0).toLong, data(1).toInt)
    }
    }.cache()

    //提取顶点
    val vertexRdd = rdd.groupBy(_._1).map(x => {
      val vlabel = Map[Int, Int]() //这里其实存储的就是一个int  只是用map的位图法来存
      x._2.foreach(a => add2Map(a._2, vlabel))

      (x._1, vlabel)
    })

    //提取边
    val edgeRdd = rdd.groupBy(_._2).flatMap { x =>

      val vertexList = x._2.toList.unzip._1
      val ret = ListBuffer[Edge[Option[Int]]]()
      for (i <- 0 until vertexList.size;
           j <- i + 1 until vertexList.size;
           if j < vertexList.size) {
        ret.append(Edge(vertexList(i), vertexList(j), None))
      }

      ret
    }

    //构成图
    val graph = Graph(vertexRdd, edgeRdd)
    println("init graph")
    graph.triplets.collect().foreach(println(_))

    //进行pregel计算
    val newG = graph.pregel(Map[Int, Int](), 1000, EdgeDirection.Out)(vprog, sendMsg, mergeMsg)
    println("after pregel")
    newG.triplets.collect().foreach(println(_))


    println("connect component")
    newG.vertices.groupBy(_._2.hashCode()).map(_._2.unzip._1).collect().foreach(println(_))
    //这里暂时用hashCode分组吧-_-  实际情况下大数据量很容易存在hash冲突
  }

  /**
    * 节点数据的更新 就是集合的union
    */
  def vprog(vid: VertexId, vdata: Map[Int, Int], message: Map[Int, Int]): Map[Int, Int] = {
    val ret = vdata

    for ((k, v) <- message) {
      if (!ret.contains(k))
        ret.put(k, v)
      else
        ret.put(k, ret(k) | message(k))
    }

    ret
  }

  /**
    * 发送消息
    */
  def sendMsg(e: EdgeTriplet[Map[Int, Int], Option[Int]]) = {
    if (equalMap(e.srcAttr, e.dstAttr))
      Iterator.empty //迭代停止
    else {
      //哎，EdgeDirection.Either好像根本没效果，只能在这里发送双向来模拟无向图
      Iterator((e.dstId, e.srcAttr.clone()),
        (e.srcId, e.dstAttr.clone())) //将自己发送给邻接顶点
    }
  }

  /**
    * 合并消息
    */
  def mergeMsg(map1: Map[Int, Int], map2: Map[Int, Int]): Map[Int, Int] = {
    for ((k, v) <- map2) {
      if (!map1.contains(k))
        map1.put(k, v)
      else
        map1.put(k, map1(k) | map2(k))
    }

    map1
  }

  def add2Map(v: Int, map: Map[Int, Int]) {
    val key = v >> SHIFT //取索引位置
    val value = 1 << (v & MASK) //取32的模
    if (!map.contains(key))
      map.put(key, value)
    else
      map.put(key, map(key) | value) //使用位图法进行存储

  }

  /**
    * 比较两个map是否相等
    */
  def equalMap(map1: Map[Int, Int], map2: Map[Int, Int]): Boolean = {
    var ret = true
    if (map1.size == map2.size) {
      for ((k, v) <- map1; if ret)
        ret = map2.contains(k) && map1(k) == map2(k)
    } else {
      ret = false
    }

    ret
  }
}
