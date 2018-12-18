package study.scala

import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by GuanYu on 2017-3-22.
  * 本地测试mysql链接
  */
object MysqlTest2 {
  def main(args: Array[String]) {
    val sc = new SparkContext(new SparkConf().setAppName("mysqlTest2").setMaster("local"))
    val sqlContext = new SQLContext(sc)
    val jdbcDF = sqlContext.read.format("jdbc").
      options(
        Map("url" -> "jdbc:mysql:///test",
          "dbtable" -> "device_carrier_standard",
          "user" -> "root",
          "password" -> "root",
          "driver" -> "com.mysql.jdbc.Driver")).load().registerTempTable("device_carrier_standard")
    val a = sqlContext.sql("select original_carrier,standard_carrier from device_carrier_standard").show(3)
//    println(a)
  }
}
