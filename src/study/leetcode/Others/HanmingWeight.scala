package study.leetcode.Others

/**
  * Created by root on 2018-8-6.
  *
  * 位1的个数
  *
  * 编写一个函数，输入是一个无符号整数，返回其二进制表达式中数字位数为 ‘1’ 的个数（也被称为汉明重量）。
  * *
  * 示例 :
  * *
  * 输入: 11
  * 输出: 3
  * 解释: 整数 11 的二进制表示为 00000000000000000000000000001011
  * *
  *
  * 示例 2:
  * *
  * 输入: 128
  * 输出: 1
  * 解释: 整数 128 的二进制表示为 00000000000000000000000010000000
  *
  *
  * https://www.baidu.com/s?wd=java%20%E5%A6%82%E4%BD%95%E4%BD%BF%E7%94%A8%E6%97%A0%E7%AC%A6%E5%8F%B7%E6%95%B4%E6%95%B0&rsv_spt=1&rsv_iqid=0xfdf88e74000852f9&issp=1&f=8&rsv_bp=1&rsv_idx=2&ie=utf-8&rqlang=cn&tn=baiduhome_pg&rsv_enter=1&oq=java%2520%25E5%25A6%2582%25E4%25BD%2595%25E4%25BD%25BF%25E7%2594%25A8%25E6%2597%25A0%25E7%25AC%25A6%25E5%258F%25B7&rsv_t=92d1O3R75JVKAUxRgFNQ37bPIV12dL1FrhJRS9Hn5u%2FlyeNFUyuzmUbBGw6ZPdx86bli&rsv_sug3=45&rsv_pq=c110ad3c000715c4&rsv_sug2=0&inputT=19345&rsv_sug4=20157
  * https://www.cnblogs.com/hglibin/p/8984777.html
  */
object HanmingWeight {
  // you need to treat n as an unsigned value
  /**
    *
    * 循环32次
    */
  def hammingWeight(n: Int): Int = {
    var result = 0
    var num = n & 0xffffffffl
    for (i <- 0 until 32) {
      result += (num % 2).toInt
      num /= 2
    }
    result
  }

  /**
    * 循环只与1的个数有关
    */

  def hammingWeight2(n: Int): Int = {
    var result = 0
    var num = n&0xffffffffl
    while(num!=0){
      num = num & (num-1)   //实质是抹掉最后边的1
      result+=1
    }
    result
  }


  def hammingWeight3(n: Int): Int = {
    var result = 0
    var num = n&0xffffffffl
    while(num!=0){
      num -= num & (~num+1)  //实质也是抹掉最后边的1
      result+=1
    }
    result
  }
  def main(args: Array[String]): Unit = {
    println(hammingWeight(2147483648l.toInt))
    println(hammingWeight2(2147483648l.toInt))
    println(hammingWeight3(2147483648l.toInt))


    val num = 2147483648l.toInt & 0xffffffffl
    println(num)

    val num1 = 2147483648l.toInt | 0x0l
    println(num1)


    println(Int.MaxValue)
    println(Int.MinValue)
  }

}
