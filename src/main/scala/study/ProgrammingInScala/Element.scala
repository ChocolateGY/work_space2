package study.ProgrammingInScala

abstract class Element {
  def contents: Array[String]

  def height = contents.length

  def width = if (height == 0) 0 else contents(0).length
}

class ArrayElement(conts: Array[String]) extends Element {
  def contents: Array[String] = conts
}

class LineElement(s: String) extends ArrayElement(Array(s)) {
  override def width: Int = s.length

  override def height: Int = 1
}

class UniformElement(
                      ch: Char,
                      override val width: Int,
                      override val height: Int
                    ) extends Element {
  private val line = ch.toString * width

  def contents = Array.fill(height)(line)
}

object Element {
  val ae = new ArrayElement(Array("a", "b"))
  ae.width
}