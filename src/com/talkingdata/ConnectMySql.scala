package com.talkingdata

import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by GuanYu on 2017-3-1.
  */
object ConnectMySql {
  def main(args: Array[String]) {
    val sc = new SparkContext(new SparkConf().setAppName("mysql").setMaster("local"))
    val sqlContext = new SQLContext(sc)
//    val jdbcDF = sqlContext.read.format("jdbc").
//      options(
//      Map("url" -> "jdbc:mysql://172.21.64.84:3306/apptaginfo",
//        "dbtable" -> "appinfo",
//        "user" -> "ling.tang",
//        "password" -> "TangLing@016",
//        "driver"->"com.mysql.jdbc.Driver"))
    val jdbcDF = sqlContext.read.format("jdbc").
      options(
      Map("url" -> "jdbc:mysql://172.21.64.26:3306/location_poi",
        "dbtable" -> "w_poi",
        "user" -> "root",
        "password" -> "123456",
        "driver"->"com.mysql.jdbc.Driver")).load().registerTempTable("w_poi")
      sqlContext.sql("select * from w_poi where time like '%2017-02-27%'").show()
  }
}
