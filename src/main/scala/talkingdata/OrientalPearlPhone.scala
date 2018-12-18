package com.talkingdata

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by root on 2017-4-21.
  */
object OrientalPearlPhone {
  def main(args: Array[String]): Unit = {
    val sc = new SparkContext(new SparkConf().setAppName("OrientalPearlPhone").setMaster("local"))
//    sc.textFile("D:\\TalkingData\\2017-04-21\\orientalpearlphoneall\\orientalpearlphoneall")
//      .map {
//        x =>
//          var imei =""
//          if(x.split("\t")(0).split(",").size>1)
//            imei = x.split("\t")(0).split(",")(1)
//          val phone = x.split("\t")(1).init
//          imei + "\t" + phone
//      }.distinct(1).saveAsTextFile("D:\\TalkingData\\2017-04-21\\orientalpearlphoneallDis")
    val total = sc.textFile("D:\\TalkingData\\2017-04-21\\orientalpearlphoneallDis").map{
      x=>x.split("\t")(0)->x.split("\t")(1)
    }.collect
    val mapped = sc.textFile("D:\\TalkingData\\2017-04-21\\dongfangPhone").map{
        x=>
        x.split("\t")(0)
    }.collect().toSet
    val result = total.flatMap{
      x=>
        if(mapped.contains(x._1))
          None
        else
          Some(x._2+",,,")
    }
    sc.makeRDD(result,1).saveAsTextFile("D:\\TalkingData\\2017-04-21\\dongfangNone")
  }
}
