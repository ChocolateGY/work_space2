package com.talkingdata

import com.util.ScanFile
import java.io.File
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by GuanYu on 2017-3-30.
  */
object longhuFenceLocation {
  def main(args: Array[String]) {
    val sc = new SparkContext(new SparkConf().setAppName("longhu").setMaster("local[2]"))
    ScanFile.subdirs(new File("D:\\TalkingData\\2017-03-30\\longhu\\fence\\2016-11")).foreach {
      date11 =>

        ScanFile.subdirs(new File("D:\\TalkingData\\2017-03-30\\longhu\\fence\\2016-12")).foreach {
          data12 =>
            if (date11.toString.split("\\\\").last.equals(data12.toString.split("\\\\").last)) {
              val a11 = sc.textFile(date11.toString).map(x => x.split("\t")(0)).collect().toSet

              sc.textFile(data12.toString).flatMap {
                x =>
                  if (a11.contains(x.split("\t")(0)))
                    Some(x)
                  else
                    None
              }.coalesce(1).saveAsTextFile("D:\\TalkingData\\2017-03-30\\longhu\\fence\\result1\\" + data12.toString.split("\\\\").last)
            }
        }
    }
    //    val str = new File("D:\\TalkingData\\2017-03-30\\longhu\\fence\\2016-12")
    //    println(str.toString)
  }

}
