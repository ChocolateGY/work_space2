package com.talkingdata

import org.apache.spark.{SparkConf, SparkContext}

import scala.tools.cmd.gen.AnyVals

/**
  * Created by root on 2018-5-10.
  */
object LocalClass {
  def main(args: Array[String]): Unit = {
    val sc = new SparkContext(new SparkConf().setMaster("local").setAppName("abc"))
    sc.textFile("D:\\TalkingData\\资料\\town.txt").filter{
      x=>
        x.split(",")(5).split("\\.")(0) =="370783"

    }.repartition(1).saveAsTextFile("D:\\TalkingData\\2018年5月8日\\shouguang")
    val a :Any = AnyRef
//    val b : Any = AnyVals

  }
}
