package com.gg.scala

/**
  * Created by root on 2017-2-8.
  */
object ArrayContains {
  def main(args: Array[String]) {
    //    val arr = Array("haha","heihei","hehe")
    //    var str = null
    //    print(arr.contains(str))
    //    val s = "2016/30/2016-07-31"
    //    print(s.split("/")(2))
    print(getDistance(31.1956057092,121.3140231861,31.1957207092,121.3165381861))

  }

  def getDistance(lat1: Double, lng1: Double, lat2: Double, lng2: Double) = {
    val radLat1 = rad(lat1)
    val radLat2 = rad(lat2)
    val a = rad(lat1) - rad(lat2)
    val b = rad(lng1) - rad(lng2)

    var s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) +
      Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)))
    s *= 6371000
    Math.round(s * 10000) / 10000
  }

  def rad(d: Double) = {
    d * Math.PI / 180.0
  }

}
