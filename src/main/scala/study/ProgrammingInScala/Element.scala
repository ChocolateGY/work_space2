package study.ProgrammingInScala

import Element.elem

abstract class Element {
  def contents: Array[String]

  def height = contents.length

  def width = if (height == 0) 0 else contents(0).length

  def above(that: Element): Element = {
    val this1 = this.widen(that.width)
    val that1 = that.widen(this.width)
    elem(this1.contents ++ that1.contents)
  }

  def beside(that: Element): Element = {

    //指令式风格
    //    val contents = new Array[String](this.contents.length)
    //    for (i <- 0 until this.contents.length)
    //      contents(i) = this.contents(i) + that.contents(i)
    //    new ArrayElement(contents)
    //表达式风格

    val this1 = this heighten that.height
    val that1 = that heighten this.height
    elem(
      for ((line1, line2) <- this1.contents zip that1.contents) yield line1 + line2
    )
  }

  def widen(w: Int): Element =
    if (w <= width) this
    else {
      val left = elem(' ', (w - width) / 2, height)
      val right = elem(' ', w - width - left.width, height)
      left beside this beside right
    }

  def heighten(h: Int): Element =
    if (h <= height) this
    else {
      val top = elem(' ', width, (h - height) / 2)
      val bot = elem(' ', width, h - height - top.height)
      top above this above bot
    }

  override def toString: String = contents.mkString("\n")
}


object Element {

  private class ArrayElement(
                              val contents: Array[String]
                            ) extends Element

  //版本1
  //class LineElement(s: String) extends ArrayElement(Array(s)) {
  //  override def width: Int = s.length
  //
  //  override def height: Int = 1
  //
  //}
  //版本二 使用直接继承Element，避免弱基类
  private class LineElement(s: String) extends Element {
    val contents = Array(s)

    override def width: Int = s.length

    override def height: Int = 1

  }

  private class UniformElement(
                                ch: Char,
                                override val width: Int,
                                override val height: Int
                              ) extends Element {
    private val line = ch.toString * width

    def contents = Array.fill(height)(line)
  }


  def elem(cont: Array[String]): Element =
    new ArrayElement(cont)

  def elem(s: String): Element =
    new LineElement(s)

  def elem(ch: Char, w: Int, h: Int): Element =
    new UniformElement(ch, w, h)
}