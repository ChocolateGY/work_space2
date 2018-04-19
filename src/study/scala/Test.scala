package study.scala

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SQLContext

/**
  * Created by root on 2017-12-5.
  */
object Test {
  def main(args: Array[String]): Unit = {
    val sc = new SparkContext(new SparkConf().setAppName("yu.guan_MappingLocation")
      .setMaster("local"))
    val sqlContext = new SQLContext(sc)
    //    val json = sqlContext.read.json("")
    //    val schema = json.schema
    //
    //    val rdd = sc.makeRDD(json.take(1))
    //    sqlContext.createDataFrame(rdd,schema)

    import sqlContext.implicits._
    //sqlContext.read.json("D:\\TalkingData\\资料\\jinjingji1.json").printSchema()
    val url = "jdbc:mysql://localhost:3306/test"

    val property = new java.util.Properties
    property.setProperty("user", "root")
    property.setProperty("password", "root")
    property.setProperty("driver", "com.mysql.jdbc.Driver")
    val df = sqlContext.read.jdbc(url, "device_carrier_standard", property)
    df.show
  }
}
