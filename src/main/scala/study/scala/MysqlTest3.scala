package study.scala

import java.util.Properties

import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by GuanYu on 2017-3-22.
  * 本地测试mysql链接
  */
object MysqlTest3 {
  def main(args: Array[String]) {
    val sc = new SparkContext(new SparkConf().setAppName("mysqlTest2").setMaster("local"))
    val sqlContext = new SQLContext(sc)
    var prop = new Properties()
    prop.setProperty("user", "root")
    prop.setProperty("password", "root")
    prop.setProperty("driver", "com.mysql.jdbc.Driver")
    val studentInfosDF = sqlContext.read.jdbc("jdbc:mysql:///test",
      "device_carrier_standard", prop).registerTempTable("device_carrier_standard")
    val apphash = sqlContext.sql("select original_carrier,standard_carrier from device_carrier_standard").rdd.take(1).foreach(println)
  }
}
