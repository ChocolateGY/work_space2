package study.ProgrammingInScala

import java.io.File

object ForExpression {
  def main(args: Array[String]): Unit = {

    //    val files = new File("D:\\IdeaProject\\work_space2\\src\\main\\scala\\study\\DataStructure").listFiles()
    //    def fileLines(file: java.io.File) = scala.io.Source.fromFile(file).getLines().toList
    //
    //    val lineLength =
    //      for{
    //        file<-files
    //        if file.getName.endsWith(".scala")
    //        line <- fileLines(file)
    //        trimmed = line.trim
    //        if trimmed.matches(".*for.*")
    //      } yield trimmed.length
    //  lineLength.foreach(println)
    println(multiTable())

  }

  def mkSeq(row: Int) =
    for (i <- 1 to 10) yield {
      val str = (row * i).toString
      val padding = " " * (4 - str.length)
      padding + str

    }

  def mkRow(row: Int) = mkSeq(row).mkString

  def multiTable() = {
    val tableSeq = for (i <- 1 to 10) yield mkRow(i)
    tableSeq.mkString("\n")

  }

}
