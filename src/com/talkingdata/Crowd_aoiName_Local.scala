package com.talkingdata

import java.io.File

import com.util.ScanFile
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by GuanYu on 2017-4-6.
  * 本地聚合aoiName的tdid，原数据是按月分。超过10个月的保留
  */
object Crowd_aoiName_Local {
  def main(args: Array[String]) {
    val sc = new SparkContext(new SparkConf().setAppName("aoiName_local").setMaster("local[4]"))
    //    val input = ScanFile.subdirs(new File("D:\\TalkingData\\2017-04-06\\aoiName\\aoiName"))
    //      .filter(x=>x.toString.split("\\\\").length == 6).foreach{
    //      dir =>
    //        sc.textFile(dir.toString+"\\*\\").map(x=>x->1).reduceByKey(_+_).flatMap{
    //          x=>
    //            val tdid = x._1
    //            val num = x._2
    //            if(num >= 5)
    //              Some(tdid)
    //            else
    //              None
    //        }.repartition(1).saveAsTextFile(dir.toString+"\\stat5")
    //    }
    //抽样
    val input = ScanFile.subdirs(new File("D:\\TalkingData\\2017-04-06\\aoiNameStat\\aoiNameStat"))
      .foreach {
        dir =>
          val a = sc.textFile(dir.toString).takeSample(false,500000)
          sc.makeRDD(a,1).saveAsTextFile("D:\\TalkingData\\2017-04-06\\aoiNameStat50w\\"+dir.getName)

      }

  }

}
