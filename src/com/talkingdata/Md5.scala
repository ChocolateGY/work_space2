package com.talkingdata

import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkConf, SparkContext}

import scala.util.Random

/**
  * Created by GuanYu on 2017/1/13.
  */
object Md5 {
  def main(args: Array[String]) {
    val sc = new SparkContext(new SparkConf().setAppName("Md5").setMaster("local"))
    val ssc = new SQLContext(sc)
    val txt = sc.textFile("C:\\Users\\tend\\Desktop\\11\\phone393544e\\phone393544e.txt").map( x => x.split('\t')(3)).collect().toSet
    val md5 = sc.broadcast(txt)
    val action = ssc.read.parquet("123.parquet").select("deviceId","offset","imei","idfa").rdd
            .filter(row => md5.value.contains(row.getString(0)))//.coalesce(60)
            .sortBy(_.getString(0))
      .map(
        row =>
          row.getString(0)+"\t"+row.getLong(1)+"\t"+row.getList(2)+"\t"+row.getAs(3)
      )

     action./*sample(false,0.001,new Random().nextInt())*/saveAsTextFile("C:\\Users\\tend\\Desktop\\11\\126\\")
  }

}
