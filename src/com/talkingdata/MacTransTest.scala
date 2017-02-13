package com.talkingdata

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by GuanYu on 2017/2/9.
  */
object MacTransTest {
  def main(args: Array[String]): Unit = {
    val sc = new SparkContext(new SparkConf().setAppName("MacTrans").setMaster("local"))
    val mac = sc.textFile("C:\\Users\\Chocolate\\Desktop\\123.txt")
    val macA = sc.broadcast(mac.collect())
    val macB = sc.broadcast(mac.collect().toSet)
    val arr = sc.parallelize(Array("00:05:7b:59:3a:ae",
      "00:05:7b:59:27:56",
      "00:05:7b:50:eb:87",
      "00:25:86:e7:13:d9",
      "00:05:7b:50:c6:1d",
    "00:05:7b:59:3c:5a",
      "e0:a3:ac:73:80:45",
      "00:1d:0f:af:9a:55"
   ))
    val time1 = System.currentTimeMillis()
    arr.mapPartitions({ iter =>
      for {
        str <- iter
        if macB.value.contains(str)
      } yield str
    })
    val time2 = System.currentTimeMillis()
    println("2 time  "+(time2-time1))
    val time11 = System.currentTimeMillis()
    arr.filter(x => macB.value.contains(x))
    val time22 = System.currentTimeMillis()
    println("1 time  "+(time22-time11))
  }
}
