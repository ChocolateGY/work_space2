package com.talkingdata

import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by tend on 2017/1/9.
  * 速度还可以
*/
object TopTourist {
  def main(args: Array[String]): Unit = {
    val sc =new SparkContext(new SparkConf().setAppName("lable").setMaster("local[5]"))
    val ssc = new SQLContext(sc)
    val action = ssc.read.parquet("part.parquet").rdd.repartition(20)
    //本地人
    val LClocal = action.map { row =>
      (( row.getString(0), row.getString(8)), 1) //((tdid,district),1)
    }.reduceByKey(_+_).groupBy(_._1._1).map { x =>
      var t = List[((String, String), Int)]()
      for (temp <- x._2) {
        t = temp :: t
      }
      val r = t.sortWith((x, y) => x._2 > y._2).head
      (r._1._1, r._1._2)
    }.filter(_._2.toString().equals("栾川县")).coalesce(5)
    //栾川县人
    val tourist =action.filter{ row => row.getString(8).equals("栾川县")}.coalesce(5).map( x => ( x.getString(0),x )) /*.subtract(LClocal)*/

    val topTourist = tourist.subtractByKey(tourist).map( _._1).distinct().saveAsTextFile("C:\\Users\\tend\\Desktop\\test1613")/*.saveAsTextFile("out1")*/

    val topLocation = tourist.map { x => (x._2.getString(4),1) }
      .reduceByKey(_+_)/*(geoHash,count)*/
      .sortBy(_._2, false).coalesce(1).saveAsTextFile("C:\\Users\\tend\\Desktop\\testL1613")

  }
}
