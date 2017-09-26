package com.studyscala
import math.BigInt._
import scala.util.Random

/**
  * Created by GuanYu on 2017-4-14.
  */
object chapter1 {
  def main(args: Array[String]) {
    val a =  None
    println(a.getOrElse("null"))
//
//    val map1 = Map(1->'a',2->'b')
//    val map2 = Map(3->'c',2->'b')
//    println(map1.isDefinedAt(map2))
    val arr1 = Array("aa","bb")
    val arr2 = Array("cc","")
    val arr3 = Array(arr1,arr2)
    arr3.flatMap(x=>x).map(x=>x+"haha").foreach(println)
    val prime = probablePrime(100,Random)
    println(prime)
    println(BigInt(Random.nextInt).toString(36))
    println("hello"(0))
    println("hello".take(1))
    println("hello".reverse(0))
    println("hello".takeRight(1))
  }
}
