package talkingdata

import org.apache.spark.sql.SparkSession

/**
  * Created by root on 2018-8-13.
  */
object FormatStandardization {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().appName("yu.guan_FormatStandardization").master("local")
//      .config("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
      .getOrCreate()
    val sc = spark.sparkContext
    //4.5-3.0英寸 \t 3.0-4.5 Inch
    val screen_table = sc.textFile("D:\\TalkingData\\交付-newzoo\\第二版更新\\screen_table.txt").map {
      x =>
        val arr = x.split("\t")
        arr(0) -> arr(1)
    }.collectAsMap()

    //iPhone 6 \t iPhone 6
    //荣耀畅玩6X \t Honer Playing 6X
    val model_cn_en = sc.textFile("D:\\TalkingData\\交付-newzoo\\第二版更新\\model_cn_en.txt").map {
      x =>
        val arr = x.split("\t")
        //将原机型（中文）去空格并大写，因为会有个别中间夹杂空格
        arr(0).replaceAll(" ", "").toUpperCase() -> arr(1)
    }.collectAsMap()

    val deviceTypeMap = Map("others" -> "others", "手机" -> "Phone", "平板电脑" -> "Tablet")
    val priceMap = Map("4000及以上" -> "4000 and above")
    //输入文件
    //    standard_brand, brand_cn, standard_model, device_model, price, device_type, screen, platform, nums
    //    Samsung	三星	Galaxy S4 Mini	三星GALAXY S4 Mini（I9195/单卡/LTE版）	2000-3999	手机	4.5-3.0英寸	android	11159
    //    Samsung\t三星\tGalaxy S4 Mini\t三星GALAXY S4 Mini（I9195/单卡/LTE版）\t2000-3999\t手机\t4.5-3.0英寸\tandroid\t11159
    //输出文件
    //    Country	Region	Model	Brand	Type	Count（devices）	System	Price	Screensize
    //    China	Asia	others	OTHERS	others	66519762	Android	others	others
    //    China\tAsia\tiPhone 6\tAPPLE\tPhone\t17917423\tIOS\t2000-3999\t4.5-5.0 inch

    val dir = "D:\\TalkingData\\交付-newzoo\\第二版更新\\ModelCount\\newzoo201904"
    //    val files = new File(dir).listFiles()
    val out = "D:\\TalkingData\\交付-newzoo\\第二版更新\\ModelCount\\result\\newzoo2019-04"
    //    files.foreach {
    //      file =>
    //        "D:\\TalkingData\\交付-newzoo\\第二版更新\\ModelCount\\newzoo201807_2\\part-00000"
    //        val result = sc.textFile(file.getAbsolutePath).flatMap {
    val result = sc.textFile(dir).flatMap {
      x =>
        val arr = x.split("\t")
        if (arr.length == 9) {
          var Array(standard_brand, brand_cn, standard_model, device_model, price, device_type, screen, platform, nums) = arr


          standard_model = standard_model.replaceAll(" ", "").toUpperCase()
          standard_model = model_cn_en.getOrElse(standard_model, standard_model)
          standard_brand = standard_brand.toUpperCase
          device_type = deviceTypeMap.getOrElse(device_type, "")
          screen = screen_table.getOrElse(screen, screen)
          price = priceMap.getOrElse(price, price)
          val key = if (standard_model != "others") (standard_brand + standard_model).replaceAll(" ", "").toUpperCase()
          else
            x
          //这里按照brand和英文的model作为key
          if (device_type.nonEmpty)
            Some((key, Array(("China\tAsia\t" + standard_model + "\t" + standard_brand + "\t" + device_type + "\t" + platform + "\t" + price + "\t" + screen, nums.toInt))))
          else
            None
        } else
          None
    }.reduceByKey(_ ++ _).map {
      x =>
        val arr = x._2
        val sum = arr.map(_._2).sum
        val str = arr.reduce {
          (x, y) =>
            if (x._2 > y._2)
              x
            else
              y
        }._1
        str + "\t" + sum
    }.coalesce(1).sortBy(x => x.split("\t").last.toInt, false)
    //"D:\\TalkingData\\交付-newzoo\\第二版更新\\ModelCount\\result\\newzoo2018-07"
    sc.makeRDD(Array("Country\tRegion\tModel\tBrand\tType\tSystem\tPrice\tScreensize\tCount（devices）"), 1).union(result).coalesce(1)
      .saveAsTextFile(out)
    //    }

  }

}
