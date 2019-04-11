package study.ProgrammingInScala

object TypeParameters {

  class SlowAppendQueue[T](elems: List[T]) {
    def head = elems.head

    def tail = new SlowAppendQueue[T](elems.tail)

    def enqueue(x: T) = new SlowAppendQueue[T](elems ::: List(x))
  }

  class SlowHeadQueue[T](smele: List[T]) {
    def head = smele.last

    def tail = new SlowHeadQueue(smele.init)

    def enquue(x: T) = new SlowHeadQueue(x :: smele)
  }

  class Queue[T] private(
                          private val leading: List[T],
                          private val tailing: List[T]
                        ) {
    private def mirror = {
      if (leading.isEmpty)
        new Queue(tailing.reverse, Nil)
      else
        this
    }

    def head = mirror.leading.head

    def tail = {
      val q = mirror
      new Queue(q.leading.tail, q.tailing)
    }

    def enqueue(x: T) = new Queue(leading, x :: tailing)

  }

  object Queue {
    def apply[T](xs: T*) = new Queue[T](xs.toList, Nil)
  }

  def main(args: Array[String]): Unit = {
    //    val q1 = new Queue[Int](Nil,Nil)
//    val q1 = Queue[Int](1, 2, 3)
//    val q2 = q1.enqueue(1)
//    println(q2.head)
    val q1 = MyQueue(1,2,3)
    println(q1.head)
  }
}

trait MyQueue[T] {
  def head: T

  def tail: MyQueue[T]

  def enqueue(x: T): MyQueue[T]
}

object MyQueue {
  def apply[T](xs: T*): MyQueue[T] = new MyQueueImpl[T](xs.toList, Nil)

  private class MyQueueImpl[T](
                                private val leading: List[T],
                                private val tailing: List[T]
                              ) extends MyQueue[T] {
    def mirror =
      if (leading.isEmpty)
        new MyQueueImpl(tailing.reverse, Nil)
      else
        this

    override def head: T = mirror.leading.head

    override def tail: MyQueue[T] = new MyQueueImpl(leading.tail, tailing)

    override def enqueue(x: T): MyQueue[T] = new MyQueueImpl(leading, x :: tailing)
  }

}
class Cell[T](init:T){
  private [this] var current = init
  def get = current
  def set(x:T) = {current = x}
}