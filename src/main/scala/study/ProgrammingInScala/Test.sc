import java.io._
import java.util

import scala.collection.mutable.ArrayBuffer
import scala.io.Source
import java.nio.file._
import java.nio.file.attribute.BasicFileAttributes
import java.awt.geom.Ellipse2D
import java.beans.PropertyChangeSupport

import javax.swing.JComponent

import scala.util.Sorting

import java.time._
import scala.concurrent._
import ExecutionContext.Implicits.global

1 to 9 map("*" * _) foreach println
Array(1,2,3)
//val sum1 = (a: Int, b: Int) => a + b
//def sum(a: Int, b: Int) = a + b
//val f = sum _
//val f1 = sum1 _
//val p = sum(_:Int,1)
//val p1 = sum1(_:Int,1)
//p(1)
//p1(2)
//Future {
//  Thread.sleep(1000)
//  println(s"This is the future at ${LocalTime.now}")
//}
//println(s"This is the future at ${LocalTime.now}")
//Future{ for(i<- 1 to 100 ){print("A") ; Thread.sleep(10)}}
//Future{ for(i<- 1 to 100 ){print("B") ; Thread.sleep(10)}}

//def sum(list: List[Option[Int]]): Int = list.flatten.sum
//def sum2(list: List[Option[Int]]): Int = list.collect { case Some(x) => x }.sum
//
//sum2(List(Some(1), Some(2), None))
//
//
//def fun(a: Double => Option[Double], b: Double => Option[Double]): Double => Option[Double] = {
//  x =>
//    val t = b(x)
//    if (t.nonEmpty) {
//      val m = a(t.get)
//      if (m.nonEmpty)
//        m
//      else
//        None
//    }
//    else
//      None
//}
//def f(x: Double) = if (x >= 0) Some(math.sqrt(x)) else None
//def g(x: Double) = if (x != 1) Some(1 / (x - 1)) else None
//val h = fun(f, g)
//h(2)
//h(1)
//h(0)


//sealed abstract class BinaryTree
//
//case class Leaf(value: Int) extends BinaryTree

//case class Node(Left: BinaryTree, right: BinaryTree) extends BinaryTree
//
//def sum(tree: BinaryTree): Int = tree match {
//  case l:Leaf => l.value
//  case Node(left,right) =>sum(left) + sum(right)
//}
//val tree = Node(Node(Node(Leaf(1),Leaf(2)),Leaf(3)),Node(Leaf(4),Leaf(5)))
//
//sum(tree)

//case class Node(nodes: BinaryTree*) extends BinaryTree
//
//def sum(tree: BinaryTree): Int = tree match {
//  case l:Leaf => l.value
//  case Node(nodes @ _*) => nodes.map(sum).sum
//}
//val tree = Node(Node(Leaf(3),Leaf(8)),Leaf(2),Node(Leaf(5)))
//sum(tree)

//case class Node(op: Char, nodes: BinaryTree*) extends BinaryTree
//
//def operator(tree: BinaryTree): Int = tree match {
//  case l: Leaf => l.value
//  case Node(op, nodes@_*) => op match {
//    case '*' =>nodes.map(operator).product
//    case '+' => nodes.map(operator).sum
//    case '-' => 0 - nodes.map(operator).sum
//
//  }
//}
//val tree = Node('+',Node('*',Leaf(3), Leaf(8)), Leaf(2), Node('-',Leaf(5)))
//operator(tree)

//def op(o: Char): (Int, Int) => Int = o match {
//  case '+' => _ + _
//  case '*' => _ * _
//  case '/' => _ / _
//  case '-' => _ - _
//}


//def leafSum(list: Any): Int = list match {
//  case a :: Nil=> leafSum(a)
//  case a :: rest => leafSum(a) + leafSum(rest)
//  case num: Int => num
//}
//leafSum(List(List(3, 8), 2, List(5)))
//
//
//def leafSum2(tree: List[Any]): Int = {
//  var sum = 0
//  tree foreach {
//    _ match {
//      case n: Int => sum += n
//      case lst: List[Any] => sum += leafSum(lst)
//    }
//  }
//  sum
//}
//
//leafSum2(List(List(3, 8), 2, List(5)))


//val p: PartialFunction[Int, String] = {
//  case z if z % 2 == 0 => "Even"
//}
//val p2: PartialFunction[Int, String] = {
//  case x if x % 2 == 1 => "Odd"
//}
//val pt = p.orElse(p2)
//pt(2)
//pt.isDefinedAt(-1)
//def swap(pair: (Int, Int)): (Int, Int) = pair match {
//  case (x, y) => (y, x)
//}
//def swapArr(arr: Array[Int]): Array[Int] = {
//  arr match {
//    case a if arr.length >= 2 => {
//      val temp = a(0)
//      a(0) = a(1)
//      a(1) = temp
//      a
//    }
//    case b => b
//  }
//}
//swapArr(Array(1))

//def swapArr(arr: Array[Int]): Array[Int] = {
//  arr match {
//    case Array(a, b, rest@_*) => Array(b, a) ++ rest
//  }
//}


//val map = Map("a"->1,"a"->2)
//map.get("a").foreach(println)
//abstract class Item
//
//case class Article(des: String, price: Double) extends Item
//
//case class Bundle(des: String, discount: Double, items: Item*) extends Item
//
//case class Muliple(num: Double, item: Item) extends Item {
//  def price(): Double = {
//    def count(it: Item): Double =
//      it match {
//        case a: Article => a.price
//        case Bundle(_,_, arrs@_*) => arrs.map(count).sum
//      }
//
//    count(item) * num
//  }
//}
//
//
//Muliple(10, Article("book", 20)).price()


//
//val t = Bundle("aaaa", 20, Article("scala", 20), Bundle("bbbb", 10, Article("scalab", 10), Article("scalac", 20)))
//Muliple(2,t).price()
//
//t match {
//  case Bundle(_, _, Article(a, _), _*) => println(a)
//  case Bundle(_, _, art@Article(_, _), _*) => println(art.des)
//  case _ =>println("nothing")
//}
//val prices = List(5.0,20.0,9.95)
//val quantities = List(10,2,1)
//
//prices.zip(quantities).map(Function.tupled(_*_))
//
//def exercise8(arr:Array[Double],col:Int)={
//  arr.grouped(col).toArray
////  arr.groupBy(_>3)
//}
//exercise8(Array[Double](1,2,3,4,5,6),3)
//java.util.TimeZone.getAvailableIDs()
//java.util.TimeZone.getAvailableIDs().groupBy(_.split("/")(0)).maxBy(_._2.length)


//
//def exercise4(name:Array[String],map:Map[String,Int])={
//  map.flatMap{
//    m=>
//      if(name.contains(m._1))
//        Some(m._2)
//      else
//        None
//  }.toArray
//}
//val arr = Array("tom","fred","harry")
//val map = Map("tom"->3,"dick"->3,"harry"->5)
//exercise4(arr,map)
//
//def exercise5(arr:Array[String],sep:String) = {
//  arr.reduceLeft(_+sep+_)
//}
//exercise5(arr,",")
//
//(arr :\ List[String]())((a,b)=>b :+ a )
//(List[String]() /: arr)((a,b)=>b :: a)


//def zhengshu(list: util.LinkedList[Int]) = {
//  import scala.collection.JavaConversions.iterableAsScalaIterable
//
//  list.filter(_ != 0)
//}
//val list = new util.LinkedList[Int]()
//list.add(1)
//list.add(2)
//list.add(3)
//list.add(0)
//zhengshu(list)

//def str2Map(str:String)={
//  val couple = str.zipWithIndex
//  var map = collection.immutable.Map[Char,collection.mutable.SortedSet[Int]]()
////  couple.foreach{
////    c=>
////      map.get(c._1) match{
////        case Some(result) => map(c._1) = result + c._2
////        case None =>map += (c._1 -> collection.mutable.SortedSet(c._2))
////      }
////  }
//  couple.foreach{
//    c=>
//      map += (c._1 -> (map.getOrElse(c._1,collection.mutable.SortedSet[Int]()) + c._2))
//  }
//  map
//}
//str2Map("Mississippi")

//for(i<- (0 to 100 ).par) print(i+" ")
//"str".aggregate(Set[Char]())(_+_,_++_)
//def numsFrom(n: BigInt): Stream[BigInt] = n #:: numsFrom(n + 1)
//val tenOrMore = numsFrom(10)
//tenOrMore.tail
//tenOrMore.tail.tail
//tenOrMore.tail.tail.tail
//tenOrMore
//
//val squares = numsFrom(1).map(x => x * x)
//squares.take(5).force
//(0 to 1000).view.map(x => math.pow(10, _)).map(x => 1 / x).force


//val seq = Seq(1,2,3,4,5)
////seq.combinations(1).foreach(println)
//seq.permutations.foreach(println)
//"+3-4".collect{case '+' => 1 ;case '-' => -1}
//
//(0 /: List(1,3,5,7))(_+_)


//def unless(f: => Boolean)(f2: => Unit): Unit = {
//  if (!f) {
//    f2
//  }
//}
//unless(1!=1){
//  println("true")
//}

//val strArr = Array("1", "12", "123")
//val numArr = Array(1, 2, 3)
//
//strArr.corresponds(numArr)((x, y) => x.length == y)
//def corresponds2[A, B](a: Seq[A], b: Seq[B], f: (A, B) => Boolean) = {
//  //  (fun:(Any,Any) => Boolean) =>
//  a.zip(b).map(x => f(x._1, x._2)).forall(_ == true)
//}
//corresponds2(strArr, numArr, (x: String, y: Int) => x.length == y)
//def corresponds3[A, B](a: Seq[A], b: Seq[B]) = {
//  (fun: (A, B) => Boolean)   => a.zip(b).map(x => fun(x._1, x._2)).forall(_ == true)
//}
//val a = corresponds3(strArr, numArr)
//a(_.length == _)
//


//def largest(fun: (Int) => Int, inputs: Seq[Int]): Int = {
//  inputs.map(x => (x, fun(x))).maxBy(_._2)._1
//}
//largest(x => 10 * x - x * x, 1 to 10)
//def adjustToPair(fun: (Int, Int) => Int) = (tuple: (Int, Int)) => fun(tuple._1, tuple._2)
//adjustToPair(_ * _)((6, 7))
//
//val pairs = (1 to 10) zip (11 to 20)
//val y = pairs.map(adjustToPair(_ + _))
//y
//def values(fun: Int => Int, low: Int, high: Int): Seq[(Int, Int)] = {
//  val range = low to high
//  range.zip(range.map(fun))
//}
//values(x => x * x, -5, 5)
//def getMax(arr: Array[Int]) = {
//  arr.reduceLeft((x, y) => if (x > y) x; else y)
//}
//getMax(Array(4, 2, 5, 1, 6, 0))
//def factorial(num: Int) = {
//  (1 to num).reduceLeft(_*_)
//}
//
//def factorial2(num: Int) = {
//  (1 to num).foldLeft(1)(_*_)
//}
//
////factorial(-5)
//factorial2(-5)
//1 to -5


//import scala.math._
//
//val num = 3.14
//val fun = ceil _
//fun(num)
//val a = Array("Hello", "World")
//val b = Array("hello", "world")
//a.corresponds(b)(_.equalsIgnoreCase(_))
//def runInThread(block: () => Unit): Unit = {
//  new Thread {
//    override def run() {
//      block()
//    }
//  }.start()
//}
//def until(condition: => Boolean)(block: => Unit): Unit = {
//  if (!condition) {
//    block
//    until(condition)(block)
//  }
//}
//var x = 10
//until(x == 0) {
//  x -= 1
//  println(x)
//}
//def indexof(str: String, ch: Char): Int = {
//  var i = 0
//  until(i == str.length) {
//    if (str(i) == ch) return i
//    i += 1
//  }
//  return -1
//}
//indexof("12345",'4')
//object RichFile {


//  def unapply(str:String): Option[(String,String,String)] ={
//    val file = new File(str)
//    val path = file.getAbsolutePath
//    val parent = file.getParent
//    val name = file.getName
//    val last = name.split("\\.")(1)
//    Some((parent,name,last))
//  }
//  def unapplySeq(str: String): Option[Seq[String]] = {
//    if (str.nonEmpty)
//      Some(str.split("/"))
//    else
//      None
//  }
//
//}
//
//val RichFile(a, b, c) = "D:/IdeaProject/.txt"
//class Matrix(val row: Int, val col: Int) {
//  private val value = Array.ofDim[Int](row, col)
//
//  def update(x: Int, y: Int, v: Int) = value(x)(y) = v
//
//  def apply(x: Int, y: Int) = value(x)(y)
//
//  def +(that: Matrix) = {
//    require(that.row == row && that.col == col)
//    val res = new Matrix(row, col)
//    for (i <- value.indices; j <- value(0).indices)
//      res(i, j) = value(i)(j) + that.value(i)(j)
//    res
//  }
//
//  def *(that: Matrix) = {
//    require(col == that.row)
//    val res = new Matrix(row, that.col)
//    for (i <- 0 until row; j <- 0 until that.col) {
//      val arr = for (c <- 0 until col) yield value(i)(c) * that.value(c)(j)
//      res(i, j) = arr.sum
//    }
//    res
//  }
//
//  override def toString: String = {
//    value.map(
//      i =>
//        i.mkString(" ")
//    ).mkString("\n")
//  }
//}
//val m1 = new Matrix(2,3)
//val m2 = new Matrix(3,2)
//m1(0,0)=1
//m1(0,1)=2
//m1(0,2)=3
//m1(1,0)=4
//m1(1,1)=5
//m1(1,2)=6
//
//m1
//m2(0,0)=1
//m2(1,0)=2
//m2(2,0)=3
//m2(0,1)=4
//m2(1,1)=5
//m2(2,1)=6
//m2
//
//val m3 = new Matrix(2,3)
//m3(0,0)=1
//m3(0,1)=2
//m3(0,2)=3
//m3(1,0)=4
//m3(1,1)=5
//m3(1,2)=6
//m3
//println(m1 + m3)
//println(m1 * m2)

//class BigSequence(private var value: Long = 0) {
//  //  private var seq = value.toBinaryString
//
//  implicit def boolean2Int(b: Boolean) = if (b) 1 else 0
//
//  def apply(bit: Int): Int =
//    if ((value & 1L << 63 - bit % 64) > 0)
//      1
//    else
//      0
//
//  def update(bit: Int, state: Int) = {
//    if (state == 0)
//      value &= ~(1L << 63 - bit % 64)
//    else
//      value |= 1L << 63 - bit % 64
//  }
//
//  override def toString: String = "%64s".format(value.toBinaryString).replaceAll(" ", "0")
//}
//
//val a = new BigSequence()
//println(a)
//a(5) = 1
//a(0)
//a(63) = 1
//a(64) = 1
//println(a)
//a(64) = 0
//a(63) = 0
//println(a)

//object BigSequence {
//  def apply(num:Int) = BigSequence
//
//  def update(x: BigSequence): BigSequence = {
//
//  }
//}


//class Money (var yuan:Int,var fen:Int) {
//  private val fen2yuan:Int = fen / 100
//  yuan += fen2yuan
//  fen %= 100
//  def + (that:Money) = {
//    new Money(yuan + that.yuan,fen + that.fen)
//  }
//
//  override def toString: String =
//    yuan + " " + fen
//}
//
//val a = new Money(1,175)
//val b = new Money(1,30)
//a + b


//class Fraction(n: Int, d: Int) {
//  private val num: Int = if (d == 0)
//    1
//  else
//    n * sign(d) / gcd(n, d)
//  private val den: Int = if (d == 0)
//    0
//  else
//    d * sign(d) / gcd(n, d)
//
//  override def toString: String = num + "/" + den
//
//  def sign(a: Int) = if (a > 0) 1
//  else if (a < 0) -1
//  else
//    0
//
//  def gcd(a: Int, b: Int): Int = if (b == 0)
//    a.abs
//  else
//    gcd(b, a % b)
//
//  def +(that: Fraction) = {
//    new Fraction(num * that.den + that.num * den, den * that.den)
//  }
//}
//val a = new Fraction(1,2)
//val b = new Fraction(3,2)
//a + b

//object Name {
//  def apply(first: String, last: String) = first + " " + last
//
//  def unapply(name:String): Option[(String,String)] ={
//    val pos = name.split(" ")
//    if(pos.length == 2)
//      Some((pos(0),pos(1)))
//    else
//      None
//  }
//  def unapplySeq(name:String):Option[Seq[String]] = {
//    val pos = name.split("\\s+")
//    if(pos.nonEmpty)
//      Some(pos)
//    else
//      None
//  }
//}
//Name("guan","yu")
//val Name(first,last) = "guan yu"
//val Name(first1,last1) = "guanyu"
//val Name(a,b,c,d) = "a b c d"
//PropertyChangeSupport
//JComponent
//
//trait MySupport extends PropertyChangeSupport
//
//new java.awt.Point with MySupport
//
//trait MyBuffered extends BufferedInputStream{
//  override def read(): Int = super.read()
//}
//trait Logger {
//  def log(str: String, key: Int = 3): String
//}
//
//class CrypttoLogger extends Logger {
//  def log(str: String, key: Int): String = {
//    for (i <- str) yield if (key >= 0) (97 + (i - 97 + key) % 26).toChar
//    else (97 + ((i - 97 + 26 + key) % 26)).toChar
//  }
//}
//val str = "chenzhen"
//println(str)
//println(new CrypttoLogger().log(str))
//println(new CrypttoLogger().log(str,-3))
//trait RectangleLike {
//  this: Ellipse2D.Double =>
//  def translate(x: Double, y: Double): Unit = {
//    this.x = x
//    this.y = y
//  }
//}
//
//val egg = new Ellipse2D.Double(1, 2, 3, 4) with RectangleLike
//egg.translate(2, 3)
//
//import java.awt.Point
//
//class OrderedPoint(x: Int, y: Int) extends Point(x, y) with Ordered[Point] {
//  def compare(that: Point) = if (x <= that.x && y < that.y) -1
//  else if (x == that.x && y == that.y) 0
//  else 1
//
//  override def toString = "[%d %d]".format(x, y)
//}
//
//val p1 = new OrderedPoint(1, 1)
//
//val p2 = new OrderedPoint(1, 2)
//p1.compare(p2)
//println(p1 < p2)
//trait Logger {
//  def log(msg: String)
//}
//
//trait ConsoloLogger extends Logger {
//  def log(msg: String) = println(msg)
//}
//
//abstract class SavingsAccount extends Logger {
//  log("SavingsAccount")
//}
//
//new SavingsAccount with ConsoloLogger
//
//trait TimestampLogger extends ConsoloLogger {
//  override def log(msg: String): Unit = {
//    super.log(s"${java.time.Instant.now()} $msg")
//  }
//}
//
//trait ShortLogger extends ConsoloLogger {
//  val maxlength = 15
//
//  override def log(msg: String): Unit = {
//    super[ConsoloLogger].log {
//      if (msg.length <= maxlength) msg
//      else
//        msg.substring(0, 15 - 3) + "..."
//    }
//  }
//}
//val acc1 = new SavingsAccount with TimestampLogger with ShortLogger
//val acc2 = new SavingsAccount with ShortLogger with TimestampLogger


//val dir = new File(".")
//
//def subdir(dir:File):Iterator[File] = {
//  val chil = dir.listFiles().filter(_.getName.endsWith(".class"))
//  chil.toIterator ++ dir.listFiles().filter(_.isDirectory).toIterator.flatMap(subdir)
//}
//val n = subdir(dir).length
//val a = "([0-9]+) ([a-z]+)".r
//
//val a(num,str) = "99 haha"

//implicit def makeFileVisitor(f: (Path) => Unit) = new SimpleFileVisitor[Path] {
//  override def visitFile(p: Path, attrs: attribute.BasicFileAttributes): FileVisitResult = {
//    f(p)
//    FileVisitResult.CONTINUE
//  }
//
//}
//Files.walkFileTree(new Path("D:\\IdeaProject\\work_space2\\src\\", (f: Path) => println(f)))

//val print = new PrintWriter(new FileWriter("D:\\IdeaProject\\work_space2\\src\\main\\scala\\study\\ProgrammingInScala\\Testdocument"),true)
//print.printf("%6d %10.2f".format(123,1.234))
//for (i <- 0 to 100) print.println(i)

//val file = new File("D:\\IdeaProject\\work_space2\\src\\main\\scala\\study\\ProgrammingInScala\\Test.sc")
//val in = new FileInputStream(file)
//val bytes = new Array[Byte](file.length().toInt)
//in.read(bytes)
//bytes
//in.close()


//val source = Source.fromFile("D:\\IdeaProject\\work_space2\\src\\main\\scala\\study\\ProgrammingInScala\\Test.sc")
//
//for(i<-source.getLines()) print(i+" ")


//class Square(point: java.awt.Point, width: Int) extends java.awt.Rectangle(point.x, point.y, width, width) {
//
//}

//abstract class Shape{
//  def centerPoint
//}
//class Rectangle (x:Int,y:Int,w:Int,h:Int) extends Shape{
//  override def centerPoint: Unit = {
//
//  }
//}
//class Circlle(x:Int,y:Int,radius:Double) extends Shape{
//  override def centerPoint: Unit = {
//
//  }
//}

//class Point(val x: Double, val y: Double)
//
//class LabeledPoint(lable:String,x: Double, y: Double) extends Point(x, y)

//abstract class Item {
//  def price(): Double
//
//  def description(): String
//}
//
//class SimpleItem(val price: Double, val description: String) extends Item {
//}
//
//class Bundle extends Item {
//  val items = ArrayBuffer[Item]()
//
//  def additem(item: Item): Unit = {
//    items += item
//  }
//
//  override def price(): Double = {
//    items.reduce(_.price() + _.price())
//  }
//
//  override def description(): String = {
//    items.mkString(" ")
//  }
//}

//class BankAccount(initailBalance: Double) {
//  private var balance = initailBalance
//
//  def desposit(amount: Double) = {
//    balance += amount
//    balance
//  }
//
//  def withdraw(amount: Double) = {
//    balance -= amount
//    balance
//  }
//}

//class CheckingAccount(initailBalance: Double) extends BankAccount(initailBalance) {
//  private var balance = initailBalance
//
//  //  override def desposit(amount: Double): Double = {
//  //    balance += amount
//  //    balance -=1
//  //    balance
//  //  }
//  //
//  //  override def withdraw(amount: Double): Double = {
//  //    balance -= amount
//  //    balance -= 1
//  //    balance
//  //  }
//  override def desposit(amount: Double): Double = super.desposit(amount - 1)
//
//  override def withdraw(amount: Double): Double = super.withdraw(amount + 1)
//}
//class SavingAccount(initailBalance: Double) extends BankAccount(initailBalance) {
//  private var num: Int = _
//
//  def earnMonthlyInterest(): Unit = {
//    num = 3
//    super.desposit(1)
//  }
//
//  override def desposit(amount: Double): Double = {
//    num -= 1
//    if (num < 0) super.desposit(amount - 1)
//    else
//      super.desposit(amount)
//  }
//
//  override def withdraw(amount: Double): Double = {
//    num -= 1
//    if (num < 0) super.withdraw(amount + 1)
//    else
//      super.withdraw(amount)
//  }
//}

//object Account extends Enumeration{
//  type Account = Value
//  val Red,Yellow = Value
//
//}
//Account.Red
//Account.Yellow.id
//Account.values
//class Time(val hour:Int,val minutes:Int){
//  private val t = hour * 60 + minutes
//  def before(other:Time) ={
//    hour < other.hour || (hour == other.hour && minutes < other.minutes)
//  }
//
//  override def toString: String = {
//    hour+":"+minutes
//  }
//}
//val t1= new Time(1,1)
//val t2= new Time(2,2)
//t1
//t2
//t1.before(t2)
//def minmax(arr: Array[Int]) = {
//  arr.min ->arr.max
//
//}

//import scala.collection.JavaConversions.propertiesAsScalaMap
//val prop = System.getProperties.toMap
//val maxLength = prop.keySet.max.length
//prop.foreach{
//  x=>
//    println(x._1+" "*(maxLength - x._1.length)+"| "+x._2)
//}


//import scala.collection.mutable
//
//
//val map = mutable.LinkedHashMap[String, Int]()
//map += ("Monday" -> java.util.Calendar.MONDAY)


import scala.collection.mutable.ArrayBuffer
import scala.util.Random
//import scala.collection.JavaConversions.mapAsScalaMap
//val scan = new java.util.Scanner(new File("D:\\IdeaProject\\work_space2\\src\\main\\scala\\study\\ProgrammingInScala\\Test.sc"))
////var map = collection.immutable.SortedMap[String, Int]()
//val map = new util.TreeMap[String,Int]()
//while (scan.hasNext()) {
//  val str = scan.next()
//  str.split("\\s+").foreach {
//    x =>
//      val t = map.getOrElse(x, 0)
//      map += (x -> (t + 1))
//  }
//}
//map


//val a = Map("a"->10,"b"->20)
//a.mapValues(_*0.9)


//val a = Array(1,2,3,4)
//val b = Array("a","b","c")
//a.zipAll(b,0,"d")


//import java.awt.datatransfer._
//
//val flavors = SystemFlavorMap.getDefaultFlavorMap.asInstanceOf[SystemFlavorMap]
//flavors.getNativesForFlavor(DataFlavor.imageFlavor)

//java.util.TimeZone.getAvailableIDs()
//"Africa/Abidjan".substring(7)
//java.util.TimeZone.getAvailableIDs().flatMap{
//  x=>
//    if(x.startsWith("America/"))
//      Some(x.substring("America/".length))
//    else
//      None
//}.sorted.foreach(x=>print(x+","))

//def fun5(arr:Array[Double]) = arr.sum/arr.length
//fun5(Array(1.0,2.0,3.0))
//
//def fun6(arr:Array[Int])={
//  arr.sorted(Ordering.Int.reverse)
//}
//def fun6b(arr:ArrayBuffer[Int])={
//  arr.reverse
//}
//fun6(Array(1,2,3,4))
//def fun7(arr:Array[Int]) = arr.distinct
//fun7(Array(1,11,1,2,2,3))
//def fun4(arr: Array[Int]) = {
//  val indices1 = for (i <- arr.indices if arr(i) > 0) yield i
//  val indices0 = for (i <- arr.indices if arr(i) <= 0) yield i
//  for (i <- indices1 ++ indices0) yield arr(i)
//}
//def fun4b(arr:Array[Int]) ={
//  arr.filter(_>0) ++ arr.filter(_<=0)
//}
//fun4(Array(0,1,-1,2,-2,3,-3))
//fun4b(Array(0,1,-1,2,-2,3,-3))
//import scala.collection.mutable.ArrayBuffer
//
//val arr = ArrayBuffer[Int](1,2,3,4)
//arr.remove(0)
//arr.remove(0)
//
//val a = ArrayBuffer(1,2,3,4,5,-1,-2,-3)
//var first = true
//val indexes = for(i<- 0 until a.length if first || a(i) >= 0) yield  {
//  if( a(i) < 0) first = false
//  i
//}
//for( j <- 0 until indexes.length) a(j) = a(indexes(j))
//a.trimEnd(a.length - indexes.length)
//
//a
//import scala.util.Sorting
//val  pairs = Array(("a", 5, 2), ("c", 3, 1), ("b", 1, 3))
//Sorting.quickSort(pairs)(Ordering.by[(String, Int, Int),Int](_._2))
//def fun(n: Int): Array[Int] = {
//  //  val a = new Array[Int](n)
//  //  a.map(x=>Random.nextInt(n))
//  (for (i <- 0 until n) yield Random.nextInt(n)).toArray
//
//}
//fun(4)
//for(i<- 0 until(6,2)) print(i)
//def fun2(arr: Array[Int]) = {
//  for (i <- 0 until(arr.length-1, 2)) {
//    val temp = arr(i)
//    arr(i) = arr(i+1)
//    arr(i+1) = temp
//  }
//  arr
//}
//fun2(Array(1,2,3,4,5,6,7,8))
//def fun3(arr:Array[Int]) = {
//  for(i<- arr.indices) yield{
//    if(i % 2 ==0){
//      if(i+1 < arr.length)
//        arr(i+1)
//      else
//        arr(i)
//    }else
//      arr(i-1)
//  }
//}
//fun3(Array(1,2,3,4))