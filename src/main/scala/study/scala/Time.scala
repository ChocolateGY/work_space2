package study.scala

import java.text.SimpleDateFormat

/**
  * Created by root on 2017-2-13.
  */
object Time {
  def main(args: Array[String]) {
    val time = 1486872199043l
    val sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    print(sdf.format(time))
  }
}
