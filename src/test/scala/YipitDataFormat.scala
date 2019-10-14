import java.io.{File, FileWriter, PrintWriter}

import scala.io.Source
import scala.collection.mutable._

object YipitDataFormat {
  def main(args: Array[String]): Unit = {

    readFile3
  }

  def readFile1() = {
    val writer = new PrintWriter(new File("D:\\TalkingData\\交付-YipitData\\newAppCross\\YipitData_AppCross\\across00.txt"))
    val read = Source.fromFile("D:\\TalkingData\\交付-YipitData\\newAppCross\\YipitData_AppCross\\Pairs.csv")
    read.getLines().foreach {
      l =>
        val arr = l.split(",")
        if (!arr.last.startsWith("app")) {
          writer.println(arr(3) + "\t" + arr(4) + "\t0")
        }
    }
    writer.close()
    read.close()
  }

  def readFile2() = {
    val writer2 = new PrintWriter(new File("D:\\TalkingData\\交付-YipitData\\newAppCross\\YipitData_AppCross\\across01.txt"))
    val read2 = Source.fromFile("D:\\TalkingData\\交付-YipitData\\newAppCross\\YipitData_AppCross\\OverlapGroup.csv")
    val map = Map[String, ArrayBuffer[String]]()
    read2.getLines().foreach {
      l =>
        val arr = l.split(",")
        if (!arr.last.startsWith("app")) {
          map += (arr(0) -> (map.getOrElse(arr(0), ArrayBuffer[String]()) += arr.last))
        }
    }
    map.foreach {
      x =>
        writer2.println(x._2.mkString("\t") + "\t0")
    }
    writer2.close()
    read2.close()
  }

  def readFile3() = {
    val writer = new PrintWriter(new File("D:\\TalkingData\\交付-YipitData\\newAppCross\\YipitData_AppCross\\across1.txt"))
    val read = Source.fromFile("D:\\TalkingData\\交付-YipitData\\newAppCross\\YipitData_AppCross\\OverallGroup.csv")
    read.getLines().foreach {
      x =>
        println(x.replaceAll("[\\s\\[\\]\"]", ""))
        val arr = x.replaceAll("[\\s\\[\\]\"]", "").split(",")
        if (!arr.last.startsWith("ids"))
          writer.println(arr.tail.mkString("\t") + "\t1")
    }
    writer.close()
    read.close()
  }
}
