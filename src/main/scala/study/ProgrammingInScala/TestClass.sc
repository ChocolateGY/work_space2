abstract class Expr

case class Var(name: String) extends Expr

case class Number(num: Double) extends Expr

case class UnOp(operator: String, arg: Expr) extends Expr

case class BinOp(operator: String, left: Expr, right: Expr) extends Expr


val v = Var("x")

val op = BinOp("+", Number(1), v)

v.name
op.left
println(op)
op.right == Var("x")

op.copy(operator = "-")

def smplifyTop(expr: Expr): Expr = expr match {
  case UnOp("-", UnOp("-", e)) => e
  case BinOp("+", e, Number(0)) => e
  case BinOp("*", e, Number(1)) => e
  case _ => expr
}
smplifyTop(UnOp("-", UnOp("-", Number(10))))

import java.io.{File, PrintWriter}

import math.{E, Pi}
import scala.collection.{SortedSet, mutable}

val pi = Pi
E match {
  case `pi` => "strange math"
  case _ => "ok"
}

def simplifyAll(expr: Expr): Expr = expr match {
  case UnOp("-", UnOp("-", e)) => simplifyAll(e)
  case BinOp("+", e, Number(0)) => simplifyAll(e)
  case BinOp("*", e, Number(1)) => simplifyAll(e)
  case UnOp(op, e) => UnOp(op, simplifyAll(e))
  case BinOp(op, l, r) => BinOp(op, simplifyAll(l), simplifyAll(r))
  case _ => expr
}

def myAssert(predict: => Boolean): Unit = {
  if (predict)
    println("true")
  else
    print("false")
}
myAssert(5 > 3)
def write(file:File)( op : PrintWriter=>Unit): Unit ={
  val w = new PrintWriter(file)
  try{
    op(w)
  }finally
    w.close()
}
write(new File("test.txt")){
  write=>
    write.println("hahha")
}