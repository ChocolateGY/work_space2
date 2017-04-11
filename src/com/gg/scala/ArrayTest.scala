package com.gg.scala

/**
  * Created by GuanYu on 2017-3-28.
  */
object ArrayTest {
  def main(args: Array[String]) {
    val arr = collection.mutable.ArrayBuffer(1.0,2.0,3.0)
    arr += 4.0
    println(arr.length)
  }

}
