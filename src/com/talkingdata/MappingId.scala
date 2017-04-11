package com.talkingdata

import java.io.{File, PrintWriter}

import com.util.ScanFile

import scala.io.Source

/**
  * Created by root on 2017-2-16.
  * 内部需求，匹配标签id
  */
object MappingId {
  def main(args: Array[String]) {
    ScanFile.subdirs(new File("D:\\TalkingData\\2017-04-06\\aoiNameStat50wLabel\\aoiNameStat50wLabel")).foreach{
      dir =>
        val out = new PrintWriter(new File("D:\\TalkingData\\2017-04-06\\aoiNameStat50wLabelCN\\"+dir.getName))
        val schema = Source.fromFile("D:\\TalkingData\\2017-03-08\\label.txt").getLines()
          .map(x => x.split("\t")(0) -> x.split("\t")(1))
          .toArray

        //    out.println("\t" + schema.map(x => x._2).mkString("\t"))

        val test = Source.fromFile(dir.getPath+"\\part-00000").getLines()
        test.foreach {
          x =>
            var str = x.split("\t")(0)
            for (i <- schema.indices) {
              if (x.contains("\t" + schema(i)._1 + ":") || x.contains("," + schema(i)._1 + ":"))
                str += "\t" + schema(i)._2
              else
                str += "\t"
            }
            out.println(str)
        }
        out.close()
    }


  }
}
