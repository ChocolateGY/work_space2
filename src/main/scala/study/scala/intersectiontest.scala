package study.scala

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by root on 2017-2-22.
  * 测试交集，差集
  */
object intersectionTest {
  def main(args: Array[String]) {
    val sc = new SparkContext(new SparkConf().setAppName("intersection").setMaster("local"))
    val a = sc.parallelize(Array(1,2,3,4,5))
    val b = sc.parallelize(Array(1,2))
    a.intersection(b).foreach(print)  //12
    a.subtract(b).foreach(print)  // 345
  }
}
