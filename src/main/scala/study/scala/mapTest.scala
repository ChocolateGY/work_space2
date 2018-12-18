package study.scala

/**
  * Created by GuanYu on 2017-3-2.
  */
object mapTest {
  def main(args: Array[String]) {
    val map1=collection.mutable.Map((1,2),(3,4))
    val map2=collection.mutable.Map((1,2),(2,3))

    val c = map1 ++ map2
    c.map{case(k,v)=>k->(v+map1.getOrElse(k,0))}.foreach(println)
  }
}
