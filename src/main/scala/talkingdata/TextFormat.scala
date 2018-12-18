package com.talkingdata

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by GuanYu on 2017-6-1.
  */
object TextFormat {
  def main(args: Array[String]): Unit = {
    val sc = new SparkContext(new SparkConf().setMaster("local").setAppName("TextFormat"))
    val a = sc.textFile("D:\\TalkingData\\2017年5月26日\\5月17日mallList.txt").map(x=>x.split("\t")(0)->collection.mutable.Set(x.split("\t")(1))).reduceByKey{
      (x,y)=>
        x ++ y
    }.filter(x=>x._2.size==2).foreach(println)
  }
}
