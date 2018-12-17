package com.gg.scala

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by GuanYu on 2017-3-31.
  */
object CheckPointTest {
  def main(args: Array[String]) {
    val conf = new SparkConf().setAppName("GroupByKey").setMaster("local")
    val sc = new SparkContext(conf)
    sc.setCheckpointDir("D:\\code_space\\work_space2\\checkpoint")

    val data = Array[(Int, Char)]((1, 'a'), (2, 'b'),
      (3, 'c'), (4, 'd'),
      (5, 'e'), (3, 'f'),
      (2, 'g'), (1, 'h')
    )
    val pairs = sc.parallelize(data, 3)

    pairs.checkpoint
    pairs.count

    val result = pairs.groupByKey(2)

//    result.foreachWith(i => i)((x, i) => println("[PartitionIndex " + i + "] " + x))

    println(result.toDebugString)
  }
}
