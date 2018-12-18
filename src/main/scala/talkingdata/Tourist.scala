package com.talkingdata

import org.apache.spark.rdd.UnionRDD
import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by GuanYu on 2017/1/9.
  */
object Tourist {
  def main(args: Array[String]) {
//    val Array(in, month, out1, district, out2) = args
    val sc = new SparkContext(new SparkConf().setAppName("TopTourist").setMaster("local"))
//    val ssc = new SQLContext(sc)

//    val inputs = DateUtil.getMonthDays(month,"weekday")

//    val rdd = new UnionRDD(sc, inputs.map( x => in+x+"/").filter(Hdfs.exists).map( x => ssc.read.parquet(x).rdd))
//    val action = rdd.map {
//      row =>
//        val tdid = row.getString(0)
//        val geo = row.getString(4)
//        val district = row.getString(8)
//        new String(tdid + "\t" + geo + "\t" + district)
//    }
    val action = sc.textFile("C:\\Users\\tend\\Desktop\\assembly_day - 副本.txt").map{ x =>
     var tdid = x.split("\t")(0)
     val geo = x.split("\t")(4)
  val district = x.split("\t")(8)

    new String(tdid.substring(1,tdid.length-2)+"\t"+geo.substring(1,geo.length-2)+"\t"+district.substring(1,district.length-2))
  }
    val LClocal = action.map { str =>
      (str.split('\t')(0), str.split('\t')(2))
    }.countByValue().groupBy(_._1._1).map(x => {
      var r = x._2.toList.sortWith((x, y) => x._2 > y._2).head
      (r._1._1, r._1._2)
    }
    ).filter(_._2.toString().equals("栾川县")).map(_._1).toList
    //游客
    val tourist = action.filter { x => x.split('\t')(2).equals("栾川县") && !LClocal.contains(x.split('\t')(0)) }
    //top游客
    var topTourist = tourist.map { x => x.split('\t')(0) }
      .countByValue()
      .toList.sortWith((x, y) => x._2 > y._2).map(_._1)
    //top经纬度
    var topLocation = tourist.map { x => x.split('\t')(1) }
      .countByValue() /*(geoHash,count)*/
      .toList.sortWith( (x,y) => x._2 > y._2 )
    var RL = topLocation.map(_._1)
    var result =sc.parallelize(topTourist).saveAsTextFile("out1")
    var result2 = sc.parallelize(RL).saveAsTextFile("out2")

  }

}
