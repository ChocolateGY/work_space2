package study.ML

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.mllib.feature.PCA
import org.apache.spark.mllib.linalg.{Vector, Vectors}
import org.apache.spark.rdd.RDD

/**
  * Created by root on 2018-8-21.
  */
object PCA_Demo {
  def main(args: Array[String]) {
    val conf = new SparkConf().setAppName("PCAExample").setMaster("local[1]")
    val sc = new SparkContext(conf)

    // 设置日志等级
    sc.setLogLevel("WARN")

    // 原始数据
    //    val arr = Array(
    //      Vectors.dense(4.0, 1.0, 4.0, 5.0),
    //      Vectors.dense(2.0, 3.0, 4.0, 5.0),
    //      Vectors.dense(4.0, 0.0, 6.0, 7.0))
    //    val data: RDD[Vector] = sc.parallelize(arr)

//    sc.textFile("D:\\TalkingData\\ML\\demo_filter").flatMap {
//      x =>
//        val arr = x.split(",")
//        val temp = arr(1).split(" ", -1).slice(4,11)
//        if (temp.toSet.size > 1)
//          Some(arr(0) + "," + temp.mkString(" "))
//        else
//          None
//    }.saveAsTextFile("D:\\TalkingData\\ML\\jd\\demo_filter_11")
//    val label1 = sc.textFile("D:\\TalkingData\\ML\\jd\\demo_filter_11").map {
//      x =>
//        val arr = x.split(",")
//        arr(0)
//    }
//    val data1 = sc.textFile("D:\\TalkingData\\ML\\jd\\demo_filter_11").map {
//      x =>
//        val arr = x.split(",")
//        Vectors.dense(arr(1).split(" ").map(_.toDouble))
//    }
//    // PCA降维
//    val pca1: RDD[Vector] = new PCA(3).fit(data1).transform(data1)
//
//
//    label1.zip(pca1).map(x => x._1 + "," + x._2.toArray.mkString(" ")).saveAsTextFile("D:\\TalkingData\\ML\\jd\\PCA_data11_3")


//    val rdd = sc.textFile("D:\\TalkingData\\ML\\test.txt").flatMap {
//      x =>
//        val arr = x.split("\t",-1)
//        val temp = arr.slice(4,11)
//        if (temp.toSet.size > 1)
//          Some(arr(0) + "\t" + temp.mkString("\t"))
//        else
//          None
//    }.saveAsTextFile("D:\\TalkingData\\ML\\jd\\test_filter_11")
//    val label = rdd
//      .map {
//        x =>
//          val arr = x.split("\t", -1)
//          arr(0)
//      }
//    val data = rdd.map {
//      x =>
//        val arr = x.split("\t", -1)
//        val temp = arr.tail.map {
//          x =>
//            if (x.isEmpty)
//              0
//            else
//              x.toDouble
//        }
//        Vectors.dense(temp)
//    }
//    // PCA降维
//    val pca: RDD[Vector] = new PCA(3).fit(data).transform(data)
//
//
//    label.zip(pca).map(x => x._1 + "\t" + x._2.toArray.mkString("\t")).saveAsTextFile("D:\\TalkingData\\ML\\jd\\PCA_test_3")
  }
}
