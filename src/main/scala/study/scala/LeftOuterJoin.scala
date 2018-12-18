package study.scala

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by root on 2017-2-8.
  */
object LeftOuterJoin {
  def main(args: Array[String]) {
    val sc = new SparkContext(new SparkConf().setAppName("Ljoin").setMaster("local"))
    val rdd1 = sc.parallelize(Array((1, "a"), (2, "b"), (3, "c")))
    val rdd2 = sc.parallelize(Array((1, "A"), (2, "B")))
//    val rdd3 = rdd1.leftOuterJoin(rdd2) /*.foreach(println)*/
//    val temp1 = rdd3.filter(x => x._2._2.isDefined).foreach(x=>println(x._2._2.head))
rdd1.leftOuterJoin(rdd2).foreach(println)
  }


}
