//import java.awt.event.{ActionEvent, ActionListener}
//
//import javax.swing.JButton
//
//val button = new JButton()
//button.addActionListener(
//  new ActionListener {
//    override def actionPerformed(e: ActionEvent): Unit =
//      println("pressed!")
//  }
//)
//implicit def fun2ActionListener(f: ActionEvent => Unit) =
//  new ActionListener {
//    override def actionPerformed(e: ActionEvent): Unit = f(e)
//
//  }
//button.addActionListener(
//  (_: ActionEvent) => println("pressed!")
//)
//"abc".reverse

//implicit def intToString(x: String) = x.toInt
//
//println("123")
//
//class myArrow[A](x: A) {
//  def --->[B](y: B): Tuple2[A, B] = Tuple2(x, y)
//}
//
//implicit def any2myArrow[A](x: A): myArrow[A] =
//  new myArrow(x)
//
//val a = 1 ---> 2
//
//case class Rectangle(width: Int, height: Int)
//
//implicit class RectangleMaker(width: Int) {
//  def x(height: Int) = Rectangle(width, height)
//}
//
//val myRectangle = 3 x 4

//
//class Prompt(val ref: String)
//class Drink(val ref: String)
//
//object Greeter {
//  def greet(name: String)(implicit prompt: Prompt,drink: Drink): Unit = {
//    println("hello," + name + ".the system is ready")
//    println("why not enjoy a cup of " + drink.ref)
//    println(prompt.ref)
//  }
//}
//
//object GuanPre {
//  implicit val prompt = new Prompt("Yse, master > ")
//  implicit val drink = new Drink("tea")
//}
//import GuanPre._
//Greeter.greet("guan")

//def maxListUpBound[T](elements: List[T])(ordering: Ordering[T]): T = elements match {
//  case Nil =>
//    throw new IllegalArgumentException("empty list")
//  case List(x) => x
//  case x :: rest =>
//    val maxRest = maxListUpBound(rest)(ordering)
//    if (ordering.gt(x, maxRest)) x
//    else maxRest
//}
//
//maxListUpBound(List(2,4,3,6))(Ordering.Int)