package com.talkingdata

import java.io.File

import org.apache.spark.{SparkConf, SparkContext}


/**
  * Created by root on 2017-2-10.
  */
object POCLocal_2 {
  def main(args: Array[String]) {
    val sc = new SparkContext(new SparkConf().setAppName("PocLocal").setMaster("local"))
    sc.textFile("C:\\Users\\Chocolate\\Desktop\\公司\\LianHeTongShangMapping").coalesce(1)
      .saveAsTextFile("C:\\Users\\Chocolate\\Desktop\\公司\\LianHeTongShangMapping_1")
  }

}
