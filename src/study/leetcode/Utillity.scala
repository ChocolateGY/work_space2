package study.leetcode

/**
  * Created by root on 2018-6-1.
  */
object Utillity {
  def time(f: => Unit): Long = {
    val start = System.currentTimeMillis()
    f
    val end = System.currentTimeMillis()
    end - start
  }
}
