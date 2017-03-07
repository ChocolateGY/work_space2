package com.talkingdata

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by GuanYu on 2017-3-4.
  */
object CombineFIle {
  def main(args: Array[String]) {
    val sc = new SparkContext(new SparkConf().setAppName("combine").setMaster("local"))
   sc.textFile("D:\\TalkingData\\2017-03-03\\resultCN").coalesce(1).saveAsTextFile("D:\\TalkingData\\2017-03-03\\resultCN1")
  }

}
