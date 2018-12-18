package com.talkingdata

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by GuanYu on 2017-3-29.
  * wifilailai聚合
  */
object wifilailai0329 {
  def main(args: Array[String]) {
    val sc = new SparkContext(new SparkConf().setAppName("newzoo").setMaster("local[4]"))

    //香江金融中心	351e2490428874dfab1716362cba2c1bf	2017-03-27 15
    //香江金融中心	39c27704559345145a965142d7874b3fa	2017-03-27 15
    val a = sc.textFile("/datascience/datacloud/datagroup/hadoop/delivery/wifilailai/20170329").flatMap {
      str =>
        val arr = str.split("\t")
        arr match {
          case Array(project, tdid,time) => {
            Some(project + "\t" + tdid + "\t" + time.split(" ")(0) -> (time.split(" ")(1),time.split(" ")(1)))
          }
          case _ => None
        }
    }.reduceByKey{
      (x,y)=>
        var start = x._1
        var end  = x._2
        if(y._1<start){
          start = y._1
        }
        if(y._2>end){
          end = y._2
        }
        (start,end)
    }.map(x => x._1 + " " + x._2._1 +" " + x._2._2).repartition(1).saveAsTextFile("/datascience/datacloud/datagroup/hadoop/delivery/wifilailai/20170329Stat")
  }

}
