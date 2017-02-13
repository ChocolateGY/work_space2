package com.talkingdata

import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkConf, SparkContext}

import scala.util.Random

/**
  * Created by tend on 2017/1/9.
  * 本地测试非常慢
  */
object ReadParquet {
  def main(args: Array[String]): Unit = {
    val sc = new SparkContext(new SparkConf().setAppName("D:\\1-13\\day.parquet").setMaster("local"))
    val ssc = new SQLContext(sc)
    val action = ssc.read.parquet("D:\\1-13\\day.parquet").select("info.idfa").rdd.filter(row => row.getSeq(0).length != 0).sample(false, 0.002)
    val seq = action.map {
      row =>
     row(0).asInstanceOf[Seq[String]]

    }
    val strRDD = seq.flatMap( seq => seq.map( x => x))
    val re = strRDD.filter( x=> x.length!=0 && !x.contains("00000000-0000-0000-0000-000000000000")).distinct().coalesce(1)
    re.saveAsTextFile("D:\\1-13\\129")

  }

}
