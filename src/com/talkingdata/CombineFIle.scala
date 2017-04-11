package com.talkingdata

import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by GuanYu on 2017-3-4.
  */
object CombineFIle {
  def main(args: Array[String]) {
    val sc = new SparkContext(new SparkConf().setAppName("combine").setMaster("local"))
    sc.textFile("D:\\TalkingData\\2017-03-17\\shidai_sample_result2017\\*\\*\\").coalesce(1).saveAsTextFile("D:\\TalkingData\\2017-03-17\\shidaiyunchou")
    val sqlContext = new SQLContext(sc)
    //    sc.newAPIHadoopFile()
    //    sc.newAPIHadoopFile("D:\\TalkingData\\2017-03-17\\shidai_sample_result2017\\*\\*\\",
    //      org.apache.hadoop.mapreduce.lib.input.CombineTextInputFormat,
    //      org.apache.hadoop.io.LongWritable,
    //      org.apache.hadoop.io.Text)
    //    null	32184b054742a6a4f13ca2b7f59fa1730	OPPO	Find 5
    //    null	34f9f1816cf38b40589398f7c2adc6b9f	OPPO	Find 5

    //GFK 换机 没有imsi的进行统计
    sc.textFile("").flatMap {
      x =>
        if (x.split("\t").length == 4 && x.split("\t")(3).nonEmpty)
          Some(x.split("\t")(3) -> 1)
        else
          None
    }.reduceByKey(_ + _).map(x => x._1 + "\t" + x._2).saveAsTextFile("")
    val id = sc.textFile("/datascience/datacloud/datacenter/delivery/temporary/2017-3-29/三星inP1-note3-92388.txt").collect().toSet
    val idB = sc.broadcast(id)
    sqlContext.read.parquet("/data/datacenter/rank/infos/2017/13/2017-03-27/deviceinfos")
      .select("deviceId", "model", "change_date", "first_date", "active_date").flatMap {
      row =>
        if (idB.value.contains(row.getString(0)))
          Some(row.getString(0) + "\t" + row.getString(1) + "\t" + row.getInt(2) + "\t" + row.getInt(3) + "\t" + row.getInt(4))
        else
          None
    }.repartition(1).saveAsTextFile("/datascience/datacloud/datagroup/hadoop/delivery/gfk/2017-3-29/sanxingtdid")
  }

}
