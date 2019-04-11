import scala.collection.immutable.TreeMap

val a = 1 :: 2 :: Nil

a.head

val b = List(1, 2)
b.head

//def insert(x: Int, xs: List[Int]): List[Int] = {
//  if (xs.isEmpty || x <= xs.head) x :: xs
//  else xs.head::insert(x, xs.tail)
//}
//
//def iSort(list: List[Int]): List[Int] = {
//  if (list.isEmpty) Nil
//  else insert(list.head, isSort(list.tail))
//}
//
//isSort(List(3,1,2))

def insert(x: Int, xs: List[Int]): List[Int] = xs match {
  case List() => List(x)
  case y :: ys => if (x <= y) x :: xs
  else y :: insert(x, ys)
}
def iSort(list: List[Int]): List[Int] = list match {
  case List() => List()
  case x :: xs => insert(x, iSort(xs))
}
iSort(List(3, 1, 2))

def append(xs: List[Int], ys: List[Int]): List[Int] = xs match {
  case List() => ys
  case x :: xsl => x :: append(xsl, ys)
}
append(List(1, 2, 3), List(4, 5, 6))


def sum(xs: List[Int]): Int = (0 /: xs) (_ + _)


sum(a)

def flattenLeft[T](xss: List[List[T]]) =
  (List[T]() /: xss) (_ ::: _)
def flattenRight[T](xss: List[List[T]]) =
  (xss :\ List[T]()) (_ ::: _)



def reverseLeft[T](xs: List[T]) =
  (List[T]() /: xs) { (ys, y) => y :: ys }

reverseLeft(List(1, 2, 3))
List.range(1, 10, 2)
List.range(10, 1, 3)


List.concat(List(1), List(2))


def countWord(s: String) = {
  val counts = collection.mutable.Map.empty[String, Int]
  for (rawWord <- s.split("[ ,!.]+")) {
    val word = rawWord.toLowerCase
    val oldCount = if (counts.contains(word)) counts(word)
    else 0
    counts += (word -> (oldCount + 1))
  }
  counts
}

countWord("See Spot run! Run, Spot. Run!")


val tm = TreeMap(1->'x',2->'x',3->'x')


List.fill(2,3,4)('a')


List(
  List(
    List(a, a, a, a),
    List(a, a, a, a),
    List(a, a, a, a)),
  List(
    List(a, a, a, a),
    List(a, a, a, a),
    List(a, a, a, a)))


