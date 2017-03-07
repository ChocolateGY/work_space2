package com.talkingdata

import java.io.PrintWriter

import scala.io.Source

/**
  * Created by root on 2017-2-14.
  *
  * 替换标签为中文。
  */
object ReplaceCode {
  def main(args: Array[String]) {
//    val sc = new SparkContext(new SparkConf().setAppName("ReplaceCode").setMaster("local"))
    val lable = scala.collection.mutable.Map[String, String]()
    Source.fromFile("Code2CN.txt", "UTF-8").getLines().foreach(x => lable += (x.split("\t")(1) -> x.split("\t")(0)))
        println(lable.size)
        println(lable.head)
        val out = new PrintWriter("D:\\TalkingData\\2017-03-01\\51信用卡全tagCNC.txt")
        Source.fromFile("D:\\TalkingData\\2017-03-01\\51信用卡全tagCN.txt"/*,"GBK"*/).getLines().foreach(x => {
          //字符串操作。。。
          var str = x
          lable.foreach(map => {
            str = str.replaceAll("\\t" + map._1 + ";", "\t" + map._2 + ";")
            str = str.replaceAll("\\|" + map._1 + ";", "|" + map._2 + ";")
          })
          out.println(str)
        })
        out.close()

//    sc.textFile("D:\\TalkingData\\2017-02-20\\酷派imei").map(x => {
//      var str = x
//      for (map <- lable) {
//        str = str.replaceAll("\\t" + map._1 + ";", "\t" + map._2 + ";")
//        str = str.replaceAll("\\|" + map._1 + ";", "|" + map._2 + ";")
//      }
//      str
//    }).coalesce(1).saveAsTextFile("D:\\TalkingData\\2017-02-20\\酷派imeiCN")
  }
}
