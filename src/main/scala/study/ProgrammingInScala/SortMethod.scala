package study.ProgrammingInScala

/**
  * scala 的排序方法
  */
object SortMethod {
  /**
    * 插入排序
    *
    * @param xs
    * @return
    */
  def isort(xs: List[Int]): List[Int] = xs match {
    case Nil => Nil
    case x :: xs1 => insert(x, isort(xs1))
  }

  def insert(x: Int, xs: List[Int]): List[Int] = xs match {
    case List() => List()
    case y :: ys => if (x < y) x :: xs else y :: insert(x, ys)
  }

  /**
    * 归并排序
    *
    * @param less
    * @param xs
    * @tparam T
    * @return
    */
  def msort[T](less: (T, T) => Boolean)(xs: List[T]): List[T] = {
    def merge(xs: List[T], ys: List[T]): List[T] = (xs, ys) match {
      case (Nil, _) => ys
      case (_, Nil) => xs
      case (x :: xs1, y :: ys1) => if (less(x, y)) x :: merge(xs1, ys)
      else y :: merge(xs, ys1)
    }

    val n = xs.length / 2
    if (n == 0)
      xs
    else {
      val (ys, zs) = xs splitAt n
      merge(msort(less)(ys), msort(less)(zs))
    }
  }
}
