package com.gg.scala

/**
  * Created by root on 2017-2-9.
  */
object canEquals {
  def main(args: Array[String]) {
    val l = Array(1,2,3)

     val l2=List(1,2,3)

     val l3=Seq(3)
print(l.canEqual(l2) + "   "+ l.canEqual(l3))


    /*I guess this returns true becuase canEqual check for type of the passed instance. Something like def canEqual(other: Any): Boolean = other.isInstanceOf[List] */



    //but if canEqual checks for type of passed instance then why this returns true when I call canEqual on List but pass a Set

    val s = Set(1,2,3)



    val s2=Set(2,3,4)



    print(l.canEqual(s)+"  "+l.canEqual(s)+"  "+l.canEqual(s2))
  }
}
