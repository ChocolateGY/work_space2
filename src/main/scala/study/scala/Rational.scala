package study.scala

/**
  * Created by root on 2017-12-23.
  */
class Rational(n: Int, d: Int) {
  require(d != 0)
  private val g = gcd(n.abs, d.abs)
  val num = n / g
  val denominator = d / g

  override def toString: String = num + "/" + denominator

  def this(n: Int) = this(n, 1)

  def add(that: Rational) = {
    new Rational(num * that.denominator + that.num * denominator, denominator * that.denominator)
  }

  def +(that: Rational) = {
    new Rational(num * that.denominator + that.num * denominator, denominator * that.denominator)
  }

  def +(i: Int) = new Rational(num + denominator * i, denominator)

  implicit def int2Rational(i: Int) = new Rational(i)

  def *(that: Rational) = new Rational(num * that.num, denominator * that.denominator)

  def gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)

}
