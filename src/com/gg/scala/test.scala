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
//    val str ="00057b593aae"
//    var s = new StringBuilder
//    for(i <- 0 to str.length-2 ;if (i+1)%2 !=0){
//      if(i!=str.length-2)
//        s.append(str.substring(i,i+2)+":")
//      else
//        s.append(str.substring(i,i+2))
//    }
//val a = (10*100/3.toDouble).formatted("%.3f").toDouble
//    print(a)
//    val date = "2016-01-01""
//    val str = new StringBuilder("lala")
//    for(i <- 1 to 10){
//
//      str.append(i).append("\t"+i)
//    }
//print(str)
//    val ss = "s"
    val s = "365951d04368ae9643b4b05a3c1c2b1b7\t24\tSome(31.244430541992188,121.57539367675781)"
    println(s.split("\t")(2).substring(4,s.split("\t")(2).length-1)+s.split("\t")(2).indexOf('('))
//    val g = s"hello,function $ss"
//    val map  = Map("aa"->123)
//    map.foreach(x=>println(x._1))
//    print(str.count(x=>x.equals('0')))
//    sc.parallelize(Array(Some("aa"->Set(1,2)),Some("ab"->Set(1,2)))).flatMap(x => x).foreach(println)
  }
  def t1 : Boolean={
    if (2<2){
      println("1")
      return true
    }
    print("2")
      false
  }
}
