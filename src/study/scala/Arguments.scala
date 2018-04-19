package study.scala

import java.text.MessageFormat

/**
  * Created by root on 2017-12-1.
  */
object Arguments {
  def main(args: Array[String]): Unit = {
 println(sum(1,2,3,4,5))
    val str = MessageFormat.format("hah{0}gaga{1}","123",123.asInstanceOf[AnyRef])
    println(str)
  }
  def sum(i:Int*)={
    var t :Int = 0
    for(a<-i){
      t = t+a
    }
    t
  }
}
