package study.scala

/**
  * Created by GuanYu on 2017/1/13.
  */
object test {
  def main(args: Array[String]): Unit = {
//        val sc = new SparkContext(new SparkConf().setAppName("test").setMaster("local"))
//        val rdd = sc.textFile("D:\\TalkingData\\2017-04-11\\wifilailaiWuhan\\10\\*").coalesce(1).saveAsTextFile("D:\\TalkingData\\2017-04-11\\wifilailaiWuhanCombine")
    val a = Array(11)
    val b = Array(11)
    val c = a++b
      c.foreach(println)
    val str = "haha"
    println(str.init)

  }
}
