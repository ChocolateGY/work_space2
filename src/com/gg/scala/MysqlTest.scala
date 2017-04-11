package com.gg.scala

import java.sql.{ResultSet, DriverManager, Connection}

import org.apache.spark.{TaskContext, Partition, SparkConf, SparkContext}
import org.apache.spark.rdd.JdbcRDD

/**
  * Created by GuanYu on 2017-3-22.
  * 本地测试mysql链接
  */
object MysqlTest {
  def main(args: Array[String]) {
    val sc = new SparkContext(new SparkConf().setAppName("mysqlConnection").setMaster("local"))

    val data = new JdbcRDD(
      sc,
      createConnection,
      "select * from device_carrier_standard where ? = ?",
      1,
      1,
      10,
      extractCarrierValues).getNumPartitions
    println(data)
  }

  def createConnection() = {
    Class.forName("com.mysql.jdbc.Driver").newInstance()
    DriverManager.getConnection("jdbc:mysql:///test", "root", "root")//172.30.119.244:3306
  }

  def extractCarrierValues(r: ResultSet) = {
    (r.getString(1), r.getString(2))
  }
}
