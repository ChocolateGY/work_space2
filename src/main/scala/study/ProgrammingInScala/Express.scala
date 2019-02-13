package study.ProgrammingInScala

object Express extends App {
//  val f = new ExprFormatter
//
//  val e1 = BinOp("*", BinOp("/", Number(1), Number(2)),
//    BinOp("+", Var("x"), Number(1)))
//
//  val e2 = BinOp("+", BinOp("/", Var("x"), Number(2)),
//    BinOp("/", Number(1.5), Var("x")))
//  val e3 = BinOp("/", e1, e2)
//
//  def show(e: Expr) = println(f.format(e) + "\n\n")
//
//  for (e <- Array(e1, e2, e3)) show(e)

  def sort(list: List[Int]): List[Int] = {
    if (list.isEmpty) Nil
    else insert(list.head, sort(list.tail))
  }

  def insert(i: Int, ints: List[Int]): List[Int] = {
    if (ints.isEmpty || i <= ints.head) i :: ints
    else ints.head :: insert(i,ints.tail)
  }

  sort(List(3,2,4,6,5,1)).foreach(println)

}
