package study.ProgrammingInScala

class Rational(numerator: Int, denominator: Int) {
  require(denominator != 0)
  private val g = gcd(numerator.abs, denominator.abs)
  val num = numerator / g
  val den = denominator / g


  def this(n: Int) = this(n, 1)

  def +(that: Rational) = {
    new Rational(num * that.den + den * that.num, den * that.den)
  }

  def +(n: Int) = {
    new Rational(num + n * den, den)
  }

  def -(that: Rational): Rational = {
    new Rational(num * that.den - den * that.num, den * that.den)
  }

  def -(n: Int) = {
    new Rational(num - n * den, den)
  }

  def *(that: Rational): Rational = {
    new Rational(num * that.num, den * that.den)
  }

  def *(n: Int) = {
    new Rational(num * n, den)
  }

  def /(that: Rational): Rational = {
    new Rational(num * that.den, den * that.num)
  }

  def /(n: Int) = {
    new Rational(num, den * n)
  }


  override def toString: String = s"Rational $num / $den"

  private def gcd(a: Int, b: Int): Int = {
    if (b == 0) a else gcd(b, a % b)
  }


}

object Rational {
  def main(args: Array[String]): Unit = {
    val a = new Rational(3, 4)
    val b = new Rational(5, 6)
    val c = 2
    println(a)
    println(b)
    println(a + b)
    println(a * b)
    println(a * c)
    println(a + 2)

    implicit def intToRation(i: Int): Rational = new Rational(i)

    println(2 + b)
    println(2 + new Rational(1,0))
  }
}
