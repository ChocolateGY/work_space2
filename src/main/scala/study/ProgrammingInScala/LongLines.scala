package study.ProgrammingInScala

object LongLines {
  def processFile(name:String,width:Int)={
    val lines = scala.io.Source.fromFile(name).getLines()
    for(line<-lines)
      processLine(line)

    def processLine(line: String)={
      if(line.length>width)
        println(name+": " + line.trim)
    }
  }

  def main(args: Array[String]): Unit = {
//    processFile("D:\\IdeaProject\\work_space2\\src\\main\\scala\\study\\ProgrammingInScala\\ForExpression.scala",20)
val list =  List(1,2,3)

    var sum1 = 0
    list.foreach( sum1 += _ )


  }
}
