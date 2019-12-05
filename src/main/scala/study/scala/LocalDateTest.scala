package study.scala

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import scala.collection.mutable.ArrayBuffer
import com.talkingdata.dmp.util.DateUtil

object LocalDateTest {
  def main(args: Array[String]): Unit = {
    //所有tdid的日期

    val df = DateTimeFormatter.ofPattern("yyyyMMdd")
    val a = Array(20191203, 20181203).flatMap {
      x =>
        val days = ArrayBuffer[String]()
        val date = LocalDate.parse(x.toString, df)
        for (i <- 1 to 90) {
          days += date.minusDays(i).format(df)
        }
        days
    }.toSet
    a.foreach(println)
  }
}
