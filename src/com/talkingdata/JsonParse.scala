package com.talkingdata

import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by root on 2017-11-1.
  */
object JsonParse {
  def main(args: Array[String]): Unit = {
    val sc = new SparkContext(new SparkConf().setAppName("JsonParse").setMaster("local"))
    val sqlContext = new SQLContext(sc)
    //    val rdd = sc.textFile("C:\\Users\\Chocolate\\Desktop\\2d8d29e3e3de81e39dcab6d6ba6acf051.json")
    val df = sqlContext.read.json("C:\\Users\\Chocolate\\Desktop\\2d8d29e3e3de81e39dcab6d6ba6acf051.json")/*.printSchema()*/
    df.map {
      row =>
        val tdid = row.getLong(0)
        val data = row.getStruct(1)
        val city = data.getAs[String]("city")
        val name = data.getAs[String]("name")
        val district = data.getAs[String]("district")
        tdid + "\t" + city + "\t" + name + "\t" + district
    }.collect().foreach(println)

  }
}
