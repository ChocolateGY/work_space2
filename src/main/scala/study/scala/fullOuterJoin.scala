package study.scala

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by GuanYu on 2017-3-27.
  */
object fullOuterJoin {
  def main(args: Array[String]) {
    val sc = new SparkContext(new SparkConf().setAppName("fullJoin").setMaster("local"))
    val a = sc.makeRDD(Array((1,"a"),(2,"b"),(3,"c")))
    val b = sc.makeRDD(Array((1,1),(1,11),(2,2),(3,3),(4,4)))

    val c = a.fullOuterJoin(b).foreach(println)

  }
}
