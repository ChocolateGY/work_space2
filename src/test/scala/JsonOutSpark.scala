
import java.util.Calendar

import org.apache.spark.sql.SparkSession
import td.{DATA, TDID, TTD}
import com.talkingdata.dmp.util.Hdfs
import collection.JavaConversions._
object JsonOutSpark {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().appName("TTD_LableTest").master("local")
      .getOrCreate()
    val sc = spark.sparkContext
    val labelId = sc.textFile("D:\\TalkingData\\交付-TheTradeDesk\\labelId.txt").map {
      x =>
        val arr = x.split("\t")
        arr(0) -> arr(1)
    }.collectAsMap()

    val data = System.currentTimeMillis()
    val cal = Calendar.getInstance()
    val time = cal.getTimeInMillis
    cal.add(Calendar.DAY_OF_MONTH, 30)
    val time2 = cal.getTimeInMillis

    val rdd = sc.textFile("D:\\TalkingData\\交付-TheTradeDesk\\Test\\labelTest.txt").flatMap {
      x =>
        val arr = x.split("\t")
        val id = arr(0)

        val data = arr.last.split(",").flatMap {
          t =>
            val ar = t.split(":")
            val temp = labelId.getOrElse(ar(0), "")
            if (temp.nonEmpty)
//              Some(Data(time.toString, ar(0), time2.toString))
              Some(new DATA(time.toString, ar(0), time2.toString))
            else
              None
        }

        if (data.nonEmpty) {
//          val item = Item(id, data)
//          val res = new TTDSchema("talkingdata", Seq(item))
          val item = new TDID(id, data.toList)
          val res = new TTD("talkingdata", Seq(item))
          Some(res)
        }
        else
          None
    }
//    rdd.take(10).map(x=>x.Items(0).TDID).foreach(println)
//    spark.createDataFrame(rdd, Class.forName("TTD")).show()
    spark.createDataFrame(rdd, classOf[TTD]).show//.printSchema()
    //    spark.createDataFrame(rdd,Class.forName("TTDSchema")).write.json("D:\\TalkingData\\交付-TheTradeDesk\\Test\\toJson2")
  }
}
