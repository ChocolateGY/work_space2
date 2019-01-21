import scala.io.Source

def widthOfLength(s: String) = s.length.toString.length
val args = Array("D:\\Toolbox\\Scala\\scala-2.11.8\\bin\\scala")
if (args.length > 0) {
  val lines = Source.fromFile(args(0)).getLines().toList
  val longestLength = lines.reduceLeft((a, b) => if (a.length > b.length) a else b)
  val maxWidth = widthOfLength(longestLength)
  lines.foreach {
    l =>
      val numSpace = maxWidth - widthOfLength(l)
      val padding = " " * numSpace
      println(padding + l.length + "|" + l)
  }

}else
  Console.err.println("Please enter fileName")

val a : Int = 1
-2.0
2.0.unary_-
