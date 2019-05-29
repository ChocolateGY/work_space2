package com.talkingdata

import org.apache.spark.sql.SparkSession
import org.apache.spark.{SparkConf, SparkContext}

import scala.tools.cmd.gen.AnyVals

/**
  * Created by root on 2018-5-10.
  */
object LocalClass {
  def main(args: Array[String]): Unit = {
    //    val sc = new SparkContext(new SparkConf().setMaster("local").setAppName("abc"))
    //    sc.textFile("D:\\TalkingData\\资料\\town.txt").filter{
    //      x=>
    //        x.split(",")(5).split("\\.")(0) =="370783"
    //
    //    }.repartition(1).saveAsTextFile("D:\\TalkingData\\2018年5月8日\\shouguang")
    //    val a :Any = AnyRef
    //    val b : Any = AnyVals

    val spark = SparkSession.builder().appName("yu.guan_NewIdfa_Active")
      .config("spark.serializer", "org.apache.spark.serializer.KryoSerializer").master("local[1]")
      .getOrCreate()
    val sc = spark.sparkContext
    sc.textFile("D:\\TalkingData\\交付-teddy\\2019年5月10日_智数信通\\app.txt").take(10).foreach(println)
    //    val data = spark.read.format("CSV").options(Map("header"->"false","sep"->"\t")).load("D:\\TalkingData\\交付-teddy\\2019年5月10日_智数信通\\fin_tag.csv")
    //    data.printSchema()
    //    data.show(false)

    val data1 = spark.read
      //          推断数据类型
      //.option("inferSchema", "true")
      //		   可设置分隔符，默认，
      .option("delimiter", ",")
      //          设置空值
      .option("nullValue", "?")
      //          表示有表头，若没有则为false
      .option("header", true)
      //          文件路径
      .csv("D:\\TalkingData\\交付-teddy\\2019年5月10日_智数信通\\app.txt")
    //          打印数据格式
    data1.printSchema()
    //      显示数据,false参数为不要把数据截断
    data1.show(false)
    data1.rdd.map {
      x =>
        val hash = x.getAs[String]("hash")
        val pkg = x.getAs[String]("packagename")
        val appname = x.getAs[String]("appname")
        pkg + "\t" + hash
    }.repartition(1).saveAsTextFile("D:\\TalkingData\\交付-teddy\\2019年5月10日_智数信通\\app_pkg_hash")

    data1.rdd.map {
      x =>
        val hash = x.getAs[String]("hash")
        val pkg = x.getAs[String]("packagename")
        val appname = x.getAs[String]("appname")
        pkg + "\t" + appname
    }.repartition(1).saveAsTextFile("D:\\TalkingData\\交付-teddy\\2019年5月10日_智数信通\\app_pkg_name")

  }
}
