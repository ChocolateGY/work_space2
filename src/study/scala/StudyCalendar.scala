package study.scala

import java.text.SimpleDateFormat
import java.util.{Calendar, Date}

/**
  * Created by root on 2018-8-14.
  */
object StudyCalendar {
  def main(args: Array[String]): Unit = {
//    val sdf = new SimpleDateFormat("yyyy-MM")
//    val calendar = Calendar.getInstance()
//    val date = new Date()
//    val time = calendar.getTime

//    range("2018-01-01", "2018-02-07").foreach(println)

    val sdf = new SimpleDateFormat("yyyy-MM-dd")
    val sdf2 = new SimpleDateFormat("yyyy/MM/dd")
    val cal = Calendar.getInstance()
//    cal.setTime(sdf.parse("2018-08-15"))//当前日期
    cal.set(5, 0)//上月最后一天
    val lastDayOfLastMonth = sdf.format(cal.getTime)
    cal.set(5, 0)//上上月最后一天
    val lastDayOfpreviousMonth = sdf2.format(cal.getTime)
    cal.add(5, +1)//上月第一天
    val firstDayOfLastMonth = sdf.format(cal.getTime)
    println(firstDayOfLastMonth)
    println(lastDayOfLastMonth)
    println(lastDayOfpreviousMonth)

  }


  def range(start: String, end: String) = {
    val sdf = new SimpleDateFormat("yyyy-MM-dd")
    val s = sdf.parse(start)
    val e = sdf.parse(end)

    val cal = Calendar.getInstance()
    cal.setTime(s)
    val arr = collection.mutable.ArrayBuffer[String]()
    while (cal.getTime.getTime <= e.getTime) {
      arr += sdf.format(cal.getTime)
      cal.add(5, 1)
    }
    arr
  }
}
