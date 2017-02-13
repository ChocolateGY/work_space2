package com.talkingdata

import java.io.File


/**
  * Created by root on 2017-2-12.
  */
object ScanFile {

  def main(args: Array[String]) {
    val a = new File("C:\\Users\\Chocolate\\Desktop\\公司\\LianHeTongShang")
    subdirs1(a).foreach(println(_))
//    walk(a)
  }


  def subdirs(dir: File): Iterator[File] = {
    val d = dir.listFiles.filter(_.isDirectory)
    val f = dir.listFiles.filter(!_.isFile).toIterator
    f ++ d.toIterator.flatMap(subdirs _)
  }
  def subdirs1(dir: File): Iterator[File] = {
    val d = dir.listFiles.filter(_.isDirectory)
    val f = dir.listFiles.filter(x=> !x.isFile && x.getName.contains("LinYi")).toIterator
    f ++ d.toIterator.flatMap(subdirs1 _)
  }

  def walk(file: File) {
    if (file.isFile()) println(file.getName()) else file.listFiles().foreach(walk)
  }


}
