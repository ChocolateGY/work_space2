package study.ML

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by root on 2018-8-18.
  */
object FormatData {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local").setAppName("LogisticRegression3")
    val sc = new SparkContext(conf)
    val zheng = sc.textFile("D:\\TalkingData\\ML\\zheng.txt")
    val fu = sc.textFile("D:\\TalkingData\\ML\\fu.txt")
    val test = sc.textFile("D:\\TalkingData\\ML\\test.txt")
    val tdid = zheng.map(x => x.split("\t")(0)).intersection(fu.map(x => x.split("\t")(0))).collect().toSet
    /*val z = zheng.flatMap {
      x =>
        val arr = x.split("\t", -1)
        for (i <- arr.indices)
          if (arr(i).isEmpty)
            arr(i) = "0"
        if (!tdid.contains(arr(0)))
          Some("1," + arr.tail.mkString(" "))
        else
          None
    }
    val f = fu.flatMap {
      x =>
        val arr = x.split("\t", -1)
        for (i <- arr.indices)
          if (arr(i).isEmpty)
            arr(i) = "0"
        if (!tdid.contains(arr(0)))
          Some("0," + arr.tail.mkString(" "))
        else
          None
    }
    z.union(f).coalesce(1).saveAsTextFile("D:\\TalkingData\\ML\\demo")*/
    val z = zheng.flatMap {
      x =>
        val arr = x.split("\t", -1)
        for (i <- arr.indices)
          if (arr(i).isEmpty)
            arr(i) = "0"
        val temp = arr.zipWithIndex.map(x => x._2 + ":" + x._1)
        if (!tdid.contains(arr(0)) && arr.toSet.size > 20)
          Some("1 " + temp.tail.mkString(" "))
        else
          None
    }
    val f = fu.flatMap {
      x =>
        val arr = x.split("\t", -1)
        for (i <- arr.indices)
          if (arr(i).isEmpty)
            arr(i) = "0"
        val temp = arr.zipWithIndex.map(x => x._2 + ":" + x._1)
        if (!tdid.contains(arr(0)) && arr.toSet.size > 20)
          Some("0 " + temp.tail.mkString(" "))
        else
          None
    }
    z.union(f).coalesce(1).saveAsTextFile("D:\\TalkingData\\ML\\demo2_2")
  }

}
