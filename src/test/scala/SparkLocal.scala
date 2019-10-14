import org.apache.spark.sql.SparkSession

object SparkLocal {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().appName("yu.guan_NewIdfa_Active")
      .config("spark.serializer", "org.apache.spark.serializer.KryoSerializer").master("local[1]")
      .getOrCreate()
    val sc = spark.sparkContext
    //    val imei_tdid = sc.textFile("D:\\TalkingData\\交付-teddy\\2019年5月6日_gov\\14imei_tdid").map(x => x.split("\t")(0) -> x.split("\t")(1)).collectAsMap()
    //    val loc = sc.textFile("D:\\TalkingData\\交付-teddy\\2019年5月6日_gov\\14imei_Location_res").map {
    //      x =>
    //        val arr = x.split("\t")
    //        arr(0) = imei_tdid.getOrElse(arr(0), "null")
    //        arr.mkString("\t")
    //    }.repartition(1).saveAsTextFile("D:\\TalkingData\\交付-teddy\\2019年5月6日_gov\\14imei_Location_res_imsi")

    //    val map = sc.textFile("D:\\TalkingData\\交付-teddy\\2019年5月6日_gov\\14imei_tdid.txt").map{
    //      x=>
    //        val arr = x.split("\t")
    //        arr(0)->arr(1)
    //    }.collectAsMap()
    //      sc.textFile("C:\\Users\\75102\\Desktop\\install&active\\14imei_tdid-active.txt").map{
    //        x=>
    //          val arr = x.split("\t",-1)
    //          map.getOrElse(arr(0),"")->Set(arr(3))
    //      }.reduceByKey(_++_).map(x=>x._1+"\t"+x._2.mkString(",")).repartition(1)
    //        .saveAsTextFile("D:\\TalkingData\\交付-teddy\\2019年5月6日_gov\\14imei_app_act")
    //
    //    sc.textFile("C:\\Users\\75102\\Desktop\\install&active\\14imei_tdid-install.txt").map{
    //      x=>
    //        val arr = x.split("\t")
    //        map.getOrElse(arr(0),"")->Set(arr(3))
    //    }.reduceByKey(_++_).map(x=>x._1+"\t"+x._2.mkString(",")).repartition(1)
    //      .saveAsTextFile("D:\\TalkingData\\交付-teddy\\2019年5月6日_gov\\14imei_app_ins")


    //    val imeiMap = sc.textFile("D:\\TalkingData\\交付-teddy\\2019年5月6日_gov\\area\\area_tdid_imei").map {
    //      x =>
    //        val arr = x.split("\t")
    //        arr(0) -> Set(arr(1))
    //    }.reduceByKey(_ ++ _).map(x => x._1 -> x._2.mkString(",")).collectAsMap
    //
    //    sc.textFile("D:\\TalkingData\\交付-teddy\\2019年5月6日_gov\\APP&标签明细\\tdidonly_label.txt").flatMap {
    //      x =>
    //        val arr = x.split("\t", -1)
    //        val t = imeiMap.getOrElse(arr(0), "")
    //        if (t.nonEmpty) {
    //          arr(0) = t
    //          Some(arr.mkString("\t"))
    //        }
    //        else
    //          None
    //    }.repartition(1).saveAsTextFile("D:\\TalkingData\\交付-teddy\\2019年5月6日_gov\\area\\area_imei_label")


    //    val imeiMap = sc.textFile("D:\\TalkingData\\交付-teddy\\2019年5月6日_gov\\14imei\\14imei_tdid").map {
    //      x =>
    //        val arr = x.split("\t")
    //        arr(0) -> Set(arr(1))
    //    }.reduceByKey(_ ++ _).map(x => x._1 -> x._2.mkString(",")).collectAsMap
    //
    //        sc.textFile("D:\\TalkingData\\交付-teddy\\2019年5月6日_gov\\APP&标签明细\\14imei_tdid_label.txt").flatMap {
    //      x =>
    //      val arr = x.split("\t", -1)
    //      val t = imeiMap.getOrElse(arr(0), "")
    //      if (t.nonEmpty) {
    //      arr(0) = t
    //      Some(arr.mkString("\t"))
    //      }
    //      else
    //      None
    //      }.repartition(1).saveAsTextFile("D:\\TalkingData\\交付-teddy\\2019年5月6日_gov\\14imei\\14imei_label")
    //    val rdd = sc.textFile("D:\\TalkingData\\交付-teddy\\2019年5月6日_gov\\APP&标签明细\\14imei_tdid_label.txt").map(x=>x->1)
    //    rdd.dependencies.foreach{
    //      d=>
    //        println("denp type: " + d.getClass)
    //        println("denp rdd: " + d.rdd)
    //        println("denp partitions: " + d.rdd.partitions)
    //        println("denp partitions size: " + d.rdd.partitions.length)
    //    }
    //    val a = sc.parallelize(1 to 9, 3).mapPartitions {
    //      x =>
    //        var res = List[(Int,Int)]()
    //        var pre = x.next()
    //        while (x.hasNext) {
    //          var cur = x.next()
    //          res ::= (pre, cur)
    //          pre = cur
    //        }
    //        res.iterator
    //    }
    //    a.collect().foreach(println)
//    val a = sc.makeRDD(1 to 10, 10).randomSplit(Array(1.0, 2.0, 3.0, 4.0))
//    println(a.length)
//    println("0:")
//    a(0).collect().foreach(println)
//    println("1:")
//    a(1).collect().foreach(println)
//    println("2:")
//    a(2).collect().foreach(println)
//    println("3:")
//    a(3).collect().foreach(println)
//
//    sc.makeRDD(1 to 10, 3).mapPartitionsWithIndex {
//      (x, iter) =>
//        var res = List[String]()
//        var i = 0
//        while (iter.hasNext)
//          i += iter.next()
//        Nil.::(x + "|" + i).toIterator
//    }.collect().foreach(println)

  }

}


