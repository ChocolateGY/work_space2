package com.studyscala

import java.io.File

import util.ImgErToFileUtil

import scala.io.Source

/**
  * Created by GuanYu on 2017-8-25.
  */
object ReadImage {
  def main(args: Array[String]): Unit = {
    val a = Source.fromFile("D:\\TalkingData\\WiFi来来\\photoString.txt").getLines().take(1).mkString
    print(a)
//    ImgErToFileUtil.saveToImgByStr(a,"D:\\TalkingData\\WiFi来来\\11","photo.jpg")
    ImgErToFileUtil.saveToImgByBytes(new File("D:\\TalkingData\\WiFi来来\\photoString.txt"),"D:\\TalkingData\\WiFi来来","1.jpg")
  }
}
