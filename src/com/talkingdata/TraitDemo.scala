package com.talkingdata

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by root on 2017-11-22.
  */
trait TraitDemo {
      val a = 1
      val sc = new SparkContext(new SparkConf().setAppName("traitDemo").setMaster("local"))

}
object TraitDemoTest extends TraitDemo{
  def main(args: Array[String]): Unit = {
    println(a)
    sc.textFile("D:\\TalkingData\\2017年11月20日\\mac.txt").collect().foreach(println)
  }
}
