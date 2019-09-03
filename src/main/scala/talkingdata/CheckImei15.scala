package talkingdata

import org.apache.spark.sql.SparkSession

import scala.io.Source
import org.apache.hadoop.fs.ftp.FTPFileSystem
object CheckImei15 {
  def check(imei: String): Boolean = {
    var sum1, sum2, temp, total, lastNum = 0
    for (i <- 0 to 13) {
      if (i % 2 == 0) {
        sum1 += imei(i).toInt
      } else {
        temp = imei(i).toInt * 2
        if (temp < 10)
          sum2 += temp
        else
          sum2 += 1 + temp - 10
      }

    }
    total = sum1 + sum2
    if (total % 10 == 0)
      lastNum = 0
    else
      lastNum = 10 - (total % 10)

    if (lastNum == imei(14).toInt)
      true
    else
      false
  }


  def main(args: Array[String]): Unit = {
    val imeis = Source.fromFile("D:\\TalkingData\\交付-腾讯\\2019年6月14日_校验腾讯动漫\\imei1w.txt").getLines()
    val map = collection.mutable.Map[Int, Int]()
    imeis.foreach {
      x =>
        map += x.length -> (map.getOrElse(x.length, 0) + 1)
    }
    println("长度统计：")
    map.foreach(println)
    println()
    //
    //    val res = imeis.count {
    //      x =>
    //        x.length == 15 && check(x)
    //    }
    //    println("check imei 15 : " + res)
    //
    //    println("test : " + check("865265040076856"))
    val spark = SparkSession.builder().appName("local").master("local").getOrCreate()
    val sc = spark.sparkContext
    val imei1w = sc.textFile("D:\\TalkingData\\交付-腾讯\\2019年6月14日_校验腾讯动漫\\imei1w.txt").collect().toSet

    val imeiAll = sc.textFile("D:\\TalkingData\\交付-腾讯\\2019年6月14日_校验腾讯动漫\\TXDM0501-07_activeImei").map {
      x =>
        val arr = x.split("\t")
        arr(0) -> Set(arr(1))
    }.reduceByKey(_ ++ _)

    val rdd = imeiAll.filter {
      x =>
        x._2.intersect(imei1w).nonEmpty
    }.cache
    val count1 = rdd.count
    val count2 = rdd.filter {
      x =>
        x._2.size == 1
    }.count
    val count3 = rdd.filter {
      x =>
        if (x._2.size == 2) {
          val t = x._2.diff(imei1w)
          t.nonEmpty && t.head.size == 14
        }
        else
          false
    }.count

    val count4 = rdd.filter {
      x =>
        if (x._2.size == 2) {
          val t = x._2.diff(imei1w)
          t.nonEmpty && t.head.size == 15
        }
        else
          false
    }.count
    println(count1)
    println(count2)
    println(count3)
    println(count4)

  }
}
