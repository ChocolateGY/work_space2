package study.interview.byteDance.String

import scala.util.control.Breaks._
import scala.collection.mutable.ListBuffer

/**
  * 复原IP地址
  * 给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。
  *
  * 示例:
  *
  * 输入: "25525511135"
  * 输出: ["255.255.11.135", "255.255.111.35"]
  */
object RestoreIpAddresses {
  def main(args: Array[String]): Unit = {
    val str = "25525511135"
    val res = restoreIpAddresses(str)
    res.foreach(println)
    //    val list = ListBuffer[String]("abc")
    //    list + "1"
    //    list.foreach(println)
    val res2 = restoreIpAddresses2(str)
    res2.foreach(println)
  }


  /**
    * 暴力
    * 逻辑：判断每段范围 0~255 并且 转int时，去掉 01 -> 1 这种情况
    *
    * @param s
    * @return
    */
  def restoreIpAddresses(s: String): List[String] = {
    val res = scala.collection.mutable.ListBuffer[String]()
    if (s.nonEmpty)
      for (a <- 1 to 3) {
        for (b <- 1 to 3) {
          for (c <- 1 to 3) {
            for (d <- 1 to 3) {
              if (a + b + c + d == s.length) {
                val segment1 = s.substring(0, a)
                val segment2 = s.substring(a, a + b)
                val segment3 = s.substring(a + b, a + b + c)
                val segment4 = s.substring(a + b + c, a + b + c + d)
                if (check(segment1))
                  if (check(segment2))
                    if (check(segment3))
                      if (check(segment4))
                        res += (segment1, segment2, segment3, segment4).productIterator.mkString(".")
              }
            }
          }
        }
      }

    def check(str: String): Boolean = {
      //      ((str(0) == '0' && str.length == 1) || str(0) != '0') && str.toInt >= 0 && str.toInt <= 255
      String.valueOf(str.toInt).length == str.length && str.toInt >= 0 && str.toInt <= 255
    }

    res.toList
  }


  /**
    * 递归
    *
    * @param s
    * @return
    */
  def restoreIpAddresses2(s: String): List[String] = {
    import scala.collection.mutable.ListBuffer
    val res = ListBuffer[String]()
    if (s.nonEmpty && s.length <= 12)
      recursive(s, 0, "", res)

    //        def recursive(str: String, n: Int, out: String, res: ListBuffer[String]): Unit = {
    //          if (n == 4) {
    //            if (str.isEmpty) res += out
    //          }
    //
    //            for (k <- 1 to 3) {
    //              if (str.length >= k) {
    //                val value = str.substring(0, k).toInt
    //                if (String.valueOf(value).length == k && value <= 255) {
    //                  val o = if (n == 3)
    //                    str.substring(0, k)
    //                  else
    //                    str.substring(0, k) + "."
    //                  recursive(str.substring(k), n + 1, out + o, res)
    //                }
    //              }
    //            }
    //        }

    def recursive(str: String, n: Int, out: String, res: ListBuffer[String]): Unit = {
      if (n == 4) {
        if (str.isEmpty) res += out
      }
      //复习breakable
      breakable(
        for (k <- 1 to 3) {
          if (str.length < k) break
          val value = str.substring(0, k).toInt
          if (String.valueOf(value).length == k && value <= 255) {
            val o = if (n == 3)
              str.substring(0, k)
            else
              str.substring(0, k) + "."
            recursive(str.substring(k), n + 1, out + o, res)
          }
        }
      )
    }

    res.toList
  }

}
