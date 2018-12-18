package com.talkingdata

import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by tend on 2017/1/9.
  * 本地测试非常慢
*/
object TopTourist2 {
  def main(args: Array[String]): Unit = {
    val sc =new SparkContext(new SparkConf().setAppName("lable").setMaster("local[5]"))
    val ssc = new SQLContext(sc)
    val action = ssc.read.parquet("part.parquet").rdd.repartition(20)
    //本地人
    val LClocal = action.map { row =>
      (( row.getString(0), row.getString(8)),1) //((tdid,district),1)
    }.countByKey().groupBy(_._1._1).map { x =>
      val a = x._2.toList.sortWith(_._2>_._2).head
      (a._1._1, a._1._2)
    }.filter(_._2.equals("栾川县"))
    val localRDD = sc.parallelize(LClocal.toList)
    //栾川县人
    val tourist =action.filter{ row => row.getString(8).equals("栾川县")}.coalesce(5).map( x => ( x.getString(0),x )) /*.subtract(LClocal)*/

    val topTourist = tourist.subtractByKey(localRDD).map( _._1).distinct().saveAsTextFile("C:\\Users\\tend\\Desktop\\test1614")/*.saveAsTextFile("out1")*/

    val topLocation = tourist.map { x => (x._2.getString(4),1) }
      .countByKey().toList/*(geoHash,count)*/
      .sortWith(_._2 > _._2)
      sc.parallelize(topLocation).saveAsTextFile("C:\\Users\\tend\\Desktop\\testL1614")

  }
}
