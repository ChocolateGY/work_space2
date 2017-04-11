package com.gg.scala

import org.apache.spark.{SparkConf, SparkContext}

import scala.util.control.Breaks

/**
  * Created by GuanYu on 2017/1/13.
  */
object test {
  def main(args: Array[String]): Unit = {
        val sc = new SparkContext(new SparkConf().setAppName("test").setMaster("local"))
        val rdd = sc.textFile("D:\\TalkingData\\2017-04-11\\wifilailaiWuhan\\10\\*").coalesce(1).saveAsTextFile("D:\\TalkingData\\2017-04-11\\wifilailaiWuhanCombine")
  }
}
