package study.scala

/**
  * Created by GuanYu on 2017-3-28.
  */
object SeqTest {
  def main(args: Array[String]) {
    val seq1 = Seq[String]()
    val a = seq1.take(1)
    val arr = Array("a","b").flatMap{
      x=>
        val data = seq1.map{
          y=>
            y->x
        }
        data
    }
    println(a)
  }
}
