package com.talkingdata

import java.io.{File, PrintWriter}

import org.apache.spark.{SparkConf, SparkContext}

import scala.tools.nsc.NewLinePrintWriter

/**
  * Created by tend on 2017/1/9.
  */
object test {
  def main(args: Array[String]) {
    val sc =new SparkContext(new SparkConf().setAppName("TopTourist").setMaster("local"))
    val action = sc.textFile("C:\\Users\\tend\\Desktop\\assembly_day - 副本.txt")
    val LClocal = action.map { str =>
      (str.split('\t')(0), str.split('\t')(8)) //((tdid,district),1)
    }.countByValue()/*((tdid,district),3)*/.groupBy(_._1._1).map( x =>{
      //      (tdid, ((tdid,district),count ) )
      //      var t = List[((String,String),Int)]()
      var r = x._2.toList.sortWith( (x , y) => x._2 > y._2).head
      //      for(temp <- x._2){
      //        t = temp :: t
      //      }
      //      var r = t.sortWith( (x , y) => x._2 > y._2).head
      (r._1._1,r._1._2) //(tdid,district)
    }
    ).filter(_._2.toString().equals("\"栾川县\"")).map(_._1).toList
      //    print(LClocal)
//      .foreach(print(_))
    val tourist =action.filter{ x => x.split('\t')(8).equals("\"栾川县\"" ) && !LClocal.contains(x.split('\t')(0))}
    //    .filter { x =>  LClocal.contains(x.split('\t')(0)) }
//    println("TopTourist")
    val write = new NewLinePrintWriter(new PrintWriter(new File("outT.txt")))
    write.println("TopTourist\r\n========================================================")
    val topTourist = tourist.map { x => x.split('\t')(0) }
      .countByValue()/*(tdid,count)*/
      .toList.sortWith((x,y)=>x._2>y._2).take(3)
    for (x <-topTourist){
      write.println(x+"")
    }
    write.println("\r\n\r\n\r\nTopLocation\r\n========================================================")
//    println("topLocation")
    val topLocation = tourist.map { x => (x.split('\t')(4),x) }
      .countByKey()/*(geoHash,count)*/
      .toList.sortWith( (x , y) => x._2 >y._2).take(3)
    for (x <-topLocation){
      write.println(x+"")
    }
    write.close()
  }

}
