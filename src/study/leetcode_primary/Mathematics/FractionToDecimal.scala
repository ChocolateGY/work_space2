package study.leetcode_primary.Mathematics

/**
  * 分数到小数
  * 给定两个整数，分别表示分数的分子 numerator 和分母 denominator，以字符串形式返回小数。
  *
  * 如果小数部分为循环小数，则将循环的部分括在括号内。
  *
  * 示例 1:
  *
  * 输入: numerator = 1, denominator = 2
  * 输出: "0.5"
  * 示例 2:
  *
  * 输入: numerator = 2, denominator = 1
  * 输出: "2"
  * 示例 3:
  *
  * 输入: numerator = 2, denominator = 3
  * 输出: "0.(6)"
  */
object FractionToDecimal {
  def fractionToDecimal(numerator: Int, denominator: Int): String = {

    if (numerator == 0 || denominator == 0)
      "0"
    else {
      var num = numerator.toLong.abs
      var den = denominator.toLong.abs
      val negative = (numerator < 0) ^ (denominator < 0)
      val integ = if (negative) "-" + num / den
      else num / den
      if (num % den != 0) {
        num %= den
        val map = collection.mutable.Map[Long, Int]()
        var pos = 0
        map += (num -> pos)
        val frac = new StringBuilder()

        while (num != 0) {
          num *= 10
          frac.append(num / den)
          num %= den
          val index = map.getOrElse(num, -1)
          if (index != -1) {
            val pre = frac.substring(0, index)
            val loop = frac.substring(index)
            return integ + s".$pre($loop)"
          }
          pos += 1
          map += (num -> pos)
        }
        integ + s".$frac"
      }
      integ.toString
    }
  }
}
