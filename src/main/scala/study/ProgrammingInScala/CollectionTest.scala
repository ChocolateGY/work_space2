package study.ProgrammingInScala

object CollectionTest {
  def flattenLeft[T](xss: List[List[T]]) = {
    (List[T]() /: xss) (_ ::: _)
  }
  def flattenRight[T](xss:List[List[T]])={
    (xss:\List[T]())(_:::_)
  }

  def main(args: Array[String]): Unit = {
    val list = List(List(1),List(2),List(3))
    flattenLeft(list)
    flattenRight(list)
  }
  List
}
