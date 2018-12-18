package com.talkingdata

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by root on 2017-2-15.
  */
object HongQiaoShopTest {
  def main(args: Array[String]) {
    val sc = new SparkContext(new SparkConf().setAppName("test").setMaster("local"))
    val arrx = Array(31.1956057092,31.1957207092,31.1920067092,31.1918757092)
    val arry = Array(121.3140231861,121.3165381861,121.3167811861,121.3141851861)
//    println(isPointInPolygon(31.1966057092, 121.3140231861, arrx, arry))
    val rdd = sc.parallelize(Array(("a",2),("b",3)))
    val rdd2 = sc.parallelize(Array(("a",2),("b",3)))
    val  r = rdd.union(rdd2).reduceByKey((x,y)=>x+y).collect()

r.foreach(println)

  }

  /**
    * 是否有 横断
    * 参数为四个点的坐标
    */
  def isIntersect(px1: Double, py1: Double, px2: Double, py2: Double, px3: Double, py3: Double, px4: Double,
                  py4: Double) = {
    var flag = false
    val d = (px2 - px1) * (py4 - py3) - (py2 - py1) * (px4 - px3)
    if (d != 0) {
      val r = ((py1 - py3) * (px4 - px3) - (px1 - px3) * (py4 - py3)) / d
      val s = ((py1 - py3) * (px2 - px1) - (px1 - px3) * (py2 - py1)) / d
      if ((r >= 0) && (r <= 1) && (s >= 0) && (s <= 1)) {
        flag = true
      }
    }
    flag
  }

  /**
    * 目标点是否在目标边上边上
    * px0 目标点的经度坐标
    * py0 目标点的纬度坐标
    * px1 目标线的起点(终点)经度坐标
    * py1 目标线的起点(终点)纬度坐标
    * px2 目标线的终点(起点)经度坐标
    * py2 目标线的终点(起点)纬度坐标
    */
  def isPointOnLine(px0: Double, py0: Double, px1: Double, py1: Double, px2: Double, py2: Double) = {
    var flag = false
    val ESP = 1e-9 //无限小的正数
    if ((Math.abs(Multiply(px0, py0, px1, py1, px2, py2)) < ESP) && ((px0 - px1) * (px0 - px2) <= 0) && ((py0 - py1) * (py0 - py2) <= 0)) {
      flag = true
    }
    flag
  }

  def Multiply(px0: Double, py0: Double, px1: Double, py1: Double, px2: Double, py2: Double) = {
    (px1 - px0) * (py2 - py0) - (px2 - px0) * (py1 - py0)
  }

  /**
    * 判断目标点是否在多边形内(由多个点组成)<br/>
    *
    * px 目标点的经度坐标
    * py 目标点的纬度坐标
    * polygonXA 多边形的经度坐标集合
    * polygonYA 多边形的纬度坐标集合
    *
    */
  def isPointInPolygon(px: Double, py: Double, polygonXA: Array[Double], polygonYA: Array[Double]):Boolean = {
    var isInside = false
    val ESP = 1e-9
    var count = 0
    var linePoint1x = px
    var linePoint1y = py
    val linePoint2x = 180
    var linePoint2y = py

    linePoint1x = px
    linePoint1y = py
    linePoint2y = py

    for (i <- 0 until polygonXA.length -1) {
      val cx1 = polygonXA(i)
      val cy1 = polygonYA(i)
      val cx2 = polygonXA(i + 1)
      val cy2 = polygonYA(i + 1)
      //如果目标点在任何一条线上
      if (isPointOnLine(px, py, cx1, cy1, cx2, cy2)) {
        return true
      }
      //如果线段的长度无限小(趋于零)那么这两点实际是重合的，不足以构成一条线段
      if (Math.abs(cy2 - cy1) > ESP) {

        //第一个点是否在以目标点为基础衍生的平行纬度线
        if (isPointOnLine(cx1, cy1, linePoint1x, linePoint1y, linePoint2x, linePoint2y)) {
          //第二个点在第一个的下方,靠近赤道纬度为零(最小纬度)
          if (cy1 > cy2)
            count += 1
        }
        //第二个点是否在以目标点为基础衍生的平行纬度线
        else if (isPointOnLine(cx2, cy2, linePoint1x, linePoint1y, linePoint2x, linePoint2y)) {
          //第二个点在第一个的上方,靠近极点(南极或北极)纬度为90(最大纬度)
          if (cy2 > cy1)
            count += 1
        }
        //由两点组成的线段是否和以目标点为基础衍生的平行纬度线相交
        else if (isIntersect(cx1, cy1, cx2, cy2, linePoint1x, linePoint1y, linePoint2x, linePoint2y)) {
          count += 1
        }
      }
    }
    if (count % 2 == 1) {
      isInside = true
    }

    isInside
  }
}
