
def minmax(value:Array[Int])={
  (value.max,value.min)
}
def iteqgt(values:Array[Int],v:Int)={
  (values.count(_>v),values.count(_==v),values.count(_<v))
}
val maxminArr = Array(1,2,3,4,5,6,10)
minmax(maxminArr)
iteqgt(maxminArr,4)

import java.util.{HashMap => JavaHashMap}

import collection.mutable.HashMap
import scala.collection.mutable
val map = new JavaHashMap[String,String]()
map.put("1","a")
map.put("2","b")
val smap = new mutable.HashMap[String,String]()
for(key <- map.keySet().toArray()){
  smap += (key.toString -> map.get(key))
}
println(smap.mkString(" "))

val  a = 1
a.isInstanceOf[Int]
a.getClass == classOf[Int]
a match {
  case s :Int => println("aaaa"+a)
  case _ =>
}
abstract class shape{
  def centerPoint()
}
class Rectangle (x:Int,y:Int) extends shape{
  def centerPoint{}
}
var seq = collection.mutable.Set[String]()
seq += "haha"
//seq +: "GG"
seq
val map1 = Map("haha"->1,"heihei"->2)
println(map1)

val temp1 = Array("haha"->1,"haha"->1,"gaga"->1,"gaga"->2)
temp1.reduce((x,y)=>(x._1,x._2+y._2))