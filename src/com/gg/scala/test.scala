package com.gg.scala

import org.apache.spark.{SparkConf, SparkContext}

import scala.util.control.Breaks

/**
  * Created by GuanYu on 2017/1/13.
  */
object test {
  def main(args: Array[String]): Unit = {
//    val sc = new SparkContext(new SparkConf().setAppName("test").setMaster("local"))
//    val rdd = sc.parallelize(List("aaa","bbb","ccc","aaa123","ccc1"))
//    rdd.sortBy( x => x ).foreach( println(_))
    val str ="00057b593aae"
    var s = new StringBuilder
    for(i <- 0 to str.length-2 ;if (i+1)%2 !=0){
      if(i!=str.length-2)
        s.append(str.substring(i,i+2)+":")
      else
        s.append(str.substring(i,i+2))
    }
val a = (10*100/3.toDouble).formatted("%.3f").toDouble
    print(a)

//    print(str.count(x=>x.equals('0')))
  }
}
