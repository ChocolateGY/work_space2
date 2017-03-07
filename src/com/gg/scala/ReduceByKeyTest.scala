//package com.gg.scala
//
//import org.apache.spark.{SparkConf, SparkContext}
//import org.apache.spark.sql.SQLContext
//
///**
//  * Created by GuanYu on 2017-3-2.
//*/
//object ReduceByKeyTest {
//  def main(args: Array[String]) {
//
//    val arr = scala.collection.mutable.ArrayBuffer[Int]()
//    for (i <- 1 to 10000) {
//      arr += i
//    }
//
//
//    println(arr)
//
//    val sc = new SparkContext(new SparkConf())
//    val sqlContext = new SQLContext(sc)
//
//    sc.makeRDD(arr).map {
//      row =>
//        val start = System.currentTimeMillis();
//        var code = 0;
//        try {
//          code = Jsoup.connect("172.21.64.40:8080/user-loc-assemble-m4/v1?id=31072417c12cc4a4ae9cd95fbed29da00&type=tdid&month=201608").ignoreContentType(true).ignoreHttpErrors(true).timeout(3000).execute().statusCode();
//        } finally {
//
//        }
//        var cost = System.currentTimeMillis() - start
//        if (row % 500 == 0) {
//          println("i=" + row)
//        }
//        val map = scala.collection.mutable.ArrayBuffer[(String, Int)]()
//        if (cost < 200) {
//          map += "small than 200" -> 1
//        } else if (cost >= 200 && cost <= 500) {
//          map += "more than 200" -> 1
//        } else if (cost >= 500 && cost <= 1000) {
//          map += "more than 500" -> 1
//        } else if (cost >= 1000 && cost <= 2000) {
//          map += "more than 1000" -> 1
//        }
//        code ->(1, map)
//    }.reduceByKey {
//      (a, b) =>
//        val count = a._1 + b._1
//        val map = a._2 ++ b._2.toMap.map { case (k, v) => (k, v) ->(k, a._2.getOrElse(k, 0) + v) }
//        (count, map)
//    }.collect().foreach(println)
//
//    // System.out.println("cost time=" + (System.currentTimeMillis() - start) + "toal get times=" + times + ",right code times=" + total)
//
//  }
//}
//
