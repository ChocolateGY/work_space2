import org.apache.spark.sql.Row
import scala.util.matching.Regex
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Calendar
import scala.collection.mutable.ArrayBuffer
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.hadoop.fs.{FSDataInputStream, FSDataOutputStream, FileUtil, Path}

/**
  * Created by Administrator on 2019/5/20.
  */
object MI_extract {
  def date_range(start: String, end: String) = {
    val SDF = new SimpleDateFormat("yyyy/MM/dd")
    val startData: Date = SDF.parse(start); //定义起始日期
    val endData: Date = SDF.parse(end); //定义结束日期

    var buffer = new ArrayBuffer[String]

    val tempStart = Calendar.getInstance()
    tempStart.setFirstDayOfWeek(Calendar.MONDAY);
    tempStart.setTime(startData)

    val tempEnd = Calendar.getInstance()
    tempEnd.setFirstDayOfWeek(Calendar.MONDAY);
    tempEnd.setTime(endData)
    while (tempStart.before(tempEnd)) {
      //val year=tempStart.get(Calendar.YEAR).toString
      //val week=tempStart.get(Calendar.WEEK_OF_YEAR).toString
      buffer += SDF.format(tempStart.getTime())
      tempStart.add(Calendar.DAY_OF_YEAR, 1)
    }
    val year = tempEnd.get(Calendar.YEAR).toString
    val week = tempEnd.get(Calendar.WEEK_OF_YEAR).toString
    buffer += SDF.format(tempEnd.getTime())
    buffer.toList

  }

  def main(args: Array[String]): Unit = {
    val Array(start, end, out) = args
    val fs = org.apache.hadoop.fs.FileSystem.get(new org.apache.hadoop.conf.Configuration)

    def exists(path: String) = fs.exists(new Path(path)) && !fs.exists(new Path(path, "_temporary"))

    def delete(path: String) = fs.deleteOnExit(new Path(path))

    val sc = new SparkContext(new SparkConf().setAppName("cheng_mi"))

    val df = new org.apache.spark.sql.SQLContext(sc)
    val pattern = new Regex("[vV]4.0.2[1-9]")
    val apps = sc.textFile("/datascience/service/datadeliver/zheng.cheng/five_apps_package_info.csv").map { l =>
      val tmp = l.split(",")
      tmp(1).toLong
    }.collect().toSet
    val bc = sc.broadcast(apps)

    date_range(start, end).foreach { date =>
      val path = "/datascience/etl2/aggregate/non_business/" + date + "/*"
      //      "/datamodeling/user/zheng.cheng/MI_sdk/"
      val dayOutput = out + date.replaceAll("/", "-")

      val output_exist = exists(dayOutput)
      if (!output_exist) {
        //如果存在删除无效文件
        if (exists(dayOutput)) {
          delete(dayOutput)
        }

        df.read.parquet(path).select("deviceId", "seq").rdd.flatMap { row =>
          val seq = row.getAs[Seq[Row]]("seq")
          val tdid = row.getAs[String]("deviceId")
          val bc_apps = bc.value
          seq.flatMap { l =>
            val sdk = l.getAs[String]("sdk")
            val valid = (pattern findAllIn sdk).mkString(",")

            if (sdk.contains("Android") & (valid != "")) {
              val apps = l.getAs[Row]("apps")


              val install = apps.getAs[Map[Long, Int]]("install").keySet
              val run = apps.getAs[Map[Long, Int]]("run").keySet
              val open = apps.getAs[Map[Long, Int]]("open").keySet

              val act = run ++ open

              if ((install.isEmpty == false) | (act.isEmpty == false)) {
                bc_apps.flatMap { app =>
                  if (install.contains(app)) {
                    Some(sdk + "\t" + tdid + "\t" + app + "\tinstall")
                  } else if (act.contains(app)) {
                    Some(sdk + "\t" + tdid + "\t" + app + "\tactive")
                  } else None
                }

              } else None


            } else None

          }
        }.repartition(10).saveAsTextFile(dayOutput)


      }

    }

  }
}
