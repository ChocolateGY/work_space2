package study.leetcode.Mathematics

/**
  * Created by root on 2018-8-6.
  *
  *
  *  罗马数字转整数
  *
  * 罗马数字包含以下七种字符：I， V， X， L，C，D 和 M。
  * *
  * 字符          数值
  * I             1
  * V             5
  * X             10
  * L             50
  * C             100
  * D             500
  * M             1000
  * 例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。
  * *
  * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
  * *
  * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
  * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。
  * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
  * 给定一个罗马数字，将其转换成整数。输入确保在 1 到 3999 的范围内。
  * *
  * 示例 1:
  * *
  * 输入: "III"
  * 输出: 3
  * 示例 2:
  * *
  * 输入: "IV"
  * 输出: 4
  * 示例 3:
  * *
  * 输入: "IX"
  * 输出: 9
  * 示例 4:
  * *
  * 输入: "LVIII"
  * 输出: 58
  * 解释: C = 100, L = 50, XXX = 30, III = 3.
  * 示例 5:
  * *
  * 输入: "MCMXCIV"
  * 输出: 1994
  * 解释: M = 1000, CM = 900, XC = 90, IV = 4.
  *
  */
object RomanToInt {


  def romanToInt(s: String): Int = {
    var result = 0
    var pre = 0
    s.reverse.foreach {
      c =>
        c match {
          case 'I' =>
            if (1 < pre)
              result -= 1
            else
              result += 1
            pre = 1
          case 'V' =>
            result += 5
            pre = 5
          case 'X' =>
            if (10 < pre)
              result -= 10
            else
              result += 10
            pre = 10
          case 'L' =>
            result += 50
            pre = 50
          case 'C' =>
            if (100 < pre)
              result -= 100
            else
              result += 100
            pre = 100
          case 'D' =>
            result += 500
            pre = 500
          case 'M' =>
            result += 1000
            pre = 1000
        }
    }
    result
  }

  def main(args: Array[String]): Unit = {
//    println(intToRoma(3999))
    println(romanToInt("MCMXCIV"))
  }


  /**
    * IntToRoman
    *
    * 给定一个整数num，( 1<=num<=3999)，将整数转换成罗马数字。
    *
    * 枚举法
    *
    */
  def intToRoma(i: Int): String = {
    val digit = Array("", "I", "II", "III", "IV", "V", "VI", "VII",
      "VIII", "IX")
    val ten = Array("", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC")
    val hundred = Array("", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM")
    val thousand = Array("", "M", "MM", "MMM")
    thousand(i / 1000) + hundred(i % 1000 / 100) + ten(i % 100 / 10) + digit(i % 10)
  }
}
