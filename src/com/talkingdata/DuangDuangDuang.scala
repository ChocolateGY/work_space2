package com.talkingdata

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by GuanYu on 2017-5-9.
  */
object DuangDuangDuang extends  App{
//    val sc = new SparkContext(new SparkConf().setAppName("duangduang").setMaster("local"))
//    sc.textFile("D:\\TalkingData\\2017-05-09\\MOBA_MAC20170509_mac.txt").map(x=>x.toLowerCase).repartition(1)
//      .saveAsTextFile("D:\\TalkingData\\2017-05-09\\MOBA_MAC20170509_macL")
    val arr1 = Array[Int](1,2,3)
    val arr2 = Array[Int]()
    println(arr1.intersect(arr2).mkString(" "))
}
