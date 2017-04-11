package com.gg.scala

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SQLContext

/**
  * Created by GuanYu on 2017-3-2.
*/
object ReduceByKeyTest {
  def main(args: Array[String]) {

    val arr = Array((1,"a")->1,(2,"b")->2,(3,"c")->3,(1,"a")->1,(2,"b")->2,(3,"c")->3)
    val arr1 = Array((1,"a")->1,(2,"b")->2,(3,"c")->3)


//    println(arr)

    val sc = new SparkContext(new SparkConf().setAppName("reduceByKeyTest").setMaster("local"))
    val sqlContext = new SQLContext(sc)

    sc.makeRDD(arr).reduceByKey(_+_).foreach(println)


    // System.out.println("cost time=" + (System.currentTimeMillis() - start) + "toal get times=" + times + ",right code times=" + total)

  }
}

