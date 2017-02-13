package com.talkingdata

import java.io.File


/**
  * Created by root on 2017-2-10.
  */
object POCLocal {
  def main(args: Array[String]) {
//    val sc = new SparkContext(new SparkConf().setAppName("PocLocal").setMaster("local"))
//    sc.textFile("")
//    val file = arr.flatMap(x => city.map(y => x + "\\" + y))
//    val rdd = new UnionRDD(sc,
//      file.map(date => sc.textFile("C:\\Users\\Chocolate\\Desktop\\公司\\LianHeTongShang\\" + date))
//    )
//    rdd.distinct().saveAsTextFile("C:\\Users\\Chocolate\\Desktop\\公司\\LianHeTongShangMapping")
    val a = new File("C:\\Users\\Chocolate\\Desktop\\公司\\LianHeTongShang")
    subdirs3(a).foreach(println(_))
  }
  def subdirs3(dir: File): Iterator[File] = {
    val d = dir.listFiles.filter(_.isDirectory)
    val f = dir.listFiles.filter(!_.isFile).toIterator
    f ++ d.toIterator.flatMap(subdirs3 _)
  }

}
