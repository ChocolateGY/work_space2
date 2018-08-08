package study.leetcode.Mathematics

/**
  * Created by root on 2018-8-6.
  *
  *  Fizz Buzz
  *
  * Write a program that outputs the string representation of numbers from 1 to n.
  * *
  * But for multiples of three it should output “Fizz” instead of the number and for the multiples of five output “Buzz”. For numbers which are multiples of both three and five output “FizzBuzz”.
  * Example:
  * *
  * n = 15,
  * *
  * Return:
  * [
  * "1",
  * "2",
  * "Fizz",
  * "4",
  * "Buzz",
  * "Fizz",
  * "7",
  * "8",
  * "Fizz",
  * "Buzz",
  * "11",
  * "Fizz",
  * "13",
  * "14",
  * "FizzBuzz"
  * ]
  *
  *
  * 写一个程序，输出从 1 到 n 数字的字符串表示。
  * *
  *1. 如果 n 是3的倍数，输出“Fizz”；
  * *
  *2. 如果 n 是5的倍数，输出“Buzz”；
  * *
  *3.如果 n 同时是3和5的倍数，输出 “FizzBuzz”。
  */
object FizzBuzz {
  def fizzBuzz(n: Int): List[String] = {
    (1 to 1).map {
      x =>
        if (x % 3 == 0 && x % 5 == 0)
          "FizzBuzz"
        else if (x % 3 == 0)
          "Fizz"
        else if (x % 5 == 0)
          "Buzz"
        else
          x.toString
    }.toList
  }

  def main(args: Array[String]): Unit = {
    println(fizzBuzz(15))
  }
}
