package com.talkingdata

import java.io.{File, PrintWriter}


import scala.io.Source

/**
  * Created by root on 2017-2-16.
  * 内部需求，匹配标签id
  */
object MappingId {
  def main(args: Array[String]) {
    /*ScanFile.subdirs(new File("D:\\TalkingData\\2017-04-06\\aoiNameStat50wLabel\\aoiNameStat50wLabel")).foreach{
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
    }*/
    // TD 标签Mapping
    val out = new PrintWriter("D:\\TalkingData\\2017年8月15日\\LongGMac2\\LongGMac2.txt")
    val schema = Source.fromFile("D:\\TalkingData\\2017-03\\2017-03-08\\label.txt").getLines()
      .map(x => x.split("\t")(0) -> x.split("\t")(1))
      .toArray

    //    out.println("\t" + schema.map(x => x._2).mkString("\t"))
    //    3799fe1827b3542d9d5df5b2bb9eb1046	2C:5B:B8:6D:03:99	OPPO	R7s		318:,303:,302:,320:100.00,305:81.00
    //不带权重
//        val test = Source.fromFile("D:\\TalkingData\\2017年6月27日\\xyy2\\xyy2\\part-00000").getLines()
//        test.foreach {
//          x =>
//            var str = x.split("\t").init.mkString("\t")
//            for (i <- schema.indices) {
//              if (x.contains("\t" + schema(i)._1 + ":") || x.contains("," + schema(i)._1 + ":"))
//                str += "\t" + schema(i)._2
//              else
//                str += "\t"
//            }
//            out.println(str)
//        }
//        out.close()
    //带权重
    val test1 = Source.fromFile("D:\\TalkingData\\2017年8月15日\\LongGMac2\\LongGMac2\\part-00000").getLines()
    test1.foreach {
      x =>
        var str = x.split("\t").init.mkString("\t")
        val arr = x.split("\t")
        val l = arr.tail.mkString.split(",").map {
          x =>
            if (x.split(":").length == 2)
              x.split(":")(0) -> x.split(":")(1)
            else
              x.split(":")(0) -> "0.3"
        }.toMap
        for (i <- schema.indices) {
          val t = l.getOrElse(schema(i)._1, "")
          if (t.nonEmpty)
            str += "\t" + schema(i)._2 + ":" + t
          else
            str += "\t"
        }
        out.println(str)
    }
    out.close()


    //
    //    val out = new PrintWriter("D:\\TalkingData\\2017年5月22日\\lilyImeiCode\\lily.txt")
    //    val schema = Source.fromFile("D:\\TalkingData\\2017年5月22日\\tagMappingDpi.txt").getLines()
    //      .map(x => x.split("\t")(0) -> x.split("\t")(1))
    //      .toArray
    //
    //    //    out.println("\t" + schema.map(x => x._2).mkString("\t"))
    //
    //    val test = Source.fromFile("D:\\TalkingData\\2017年5月22日\\lilyImeiCode\\lily\\part-00000").getLines()
    //    test.foreach {
    //      x =>
    //        var str = x.split("\t").init.mkString("\t")
    ////        var str = x
    //        for (i <- schema.indices) {
    //          if (x.contains("\t" + schema(i)._1 + ":") || x.contains("," + schema(i)._1 + ":"))
    //            str += "\t" + schema(i)._2
    //          else
    //            str += "\t"
    //        }
    //        out.println(str)
    //    }
    //    out.close()
    //

  }
}
