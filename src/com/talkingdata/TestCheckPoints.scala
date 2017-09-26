package com.talkingdata

import org.apache.hadoop.io.{BytesWritable, NullWritable}
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by GuanYu on 2017-7-6.
  */
object TestCheckPoints {
  def main(args: Array[String]): Unit = {
   val sc = new SparkContext(new SparkConf().setAppName("checkpoint").setMaster("local"))
//    sc.setCheckpointDir("D:\\TalkingData\\2017年7月6日\\checkpoint")
//    val a = sc.makeRDD(Array(1,2,3,4,5))
//    a.checkpoint()
//    a.reduce(_+_)
//  sc.textFile("D:\\TalkingData\\2017年7月6日\\checkpoint\\*\\*").foreach(println)
//a.foreach(println)
    sc.sequenceFile[NullWritable, BytesWritable]("D:\\TalkingData\\2017年7月6日\\checkpoint\\*\\*").map{
      x=>
        val body = new String(x._2.getBytes, "UTF-8")
    }.foreach(println)
  }

}
