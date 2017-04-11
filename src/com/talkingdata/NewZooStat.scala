package com.talkingdata

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by GuanYu on 2017-3-29.
  */
object NewZooStat {
  def main(args: Array[String]) {
    val sc = new SparkContext(new SparkConf().setAppName("newzoo").setMaster("local[4]"))
    val a = sc.textFile("D:\\TalkingData\\2017-03-29\\201702").flatMap {
      str =>
        val arr = str.split("\t")
        arr match {
          case Array(original, types, brand, model, os, _, _, _, _, _, num) => {
            Some(os + "\t" + model + "\t" + brand + "\t" + types -> num.toInt)
          }
          case _ => None
        }
    }.reduceByKey(_ + _).map(x => x._1 + "\t" + x._2).repartition(1).saveAsTextFile("D:\\TalkingData\\2017-03-29\\201702Stat")
  }

}
