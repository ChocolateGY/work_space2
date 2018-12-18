package study.ML

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by root on 2018-8-21.
  */
object TestData {

  def main(args: Array[String]) {
    val conf = new SparkConf().setAppName("PCAExample").setMaster("local[1]")
    val sc = new SparkContext(conf)
    val data = sc.textFile("D:\\TalkingData\\ML\\demo").filter(x => x.split(",")(1).split(" ").toSet.size != 1).saveAsTextFile("D:\\TalkingData\\ML\\demo_filter")
  }
}
