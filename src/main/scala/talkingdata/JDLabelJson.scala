package talkingdata

import org.apache.spark.sql.SparkSession

object JDLabelJson {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().master("local").appName("JDLabel").getOrCreate()
    val sc = spark.sparkContext

    val sexL = Map("1"->"男","2"->	"女")
    val ageL = Map("-9999	"->"未知","1"->"15岁以下","2"->"16-25岁","3"->"26—35岁","4"->"36—45岁","5"->"46—55岁","6"->"56岁以上")



    val df = spark.read.json("D:\\TalkingData\\2018年12月24日\\response.json").rdd.map {
      x =>
        val id = x.getAs[String]("id")
        val name = x.getAs[String]("name")
        val weight = x.getAs[String]("weight")
        id -> Map(name -> weight)
    }.reduceByKey(_ ++ _).map {
      x =>
        val sex = x._2.getOrElse("性别预测", "null")
        val age = x._2.getOrElse("年龄预测", "null")
        x._1 + "\t" + sexL.getOrElse(sex,"null") + "\t" + ageL.getOrElse(age,"null")
      //        val map = x._2
      //        val sex = map.getOrElse("性别预测","null")
      //        val age = map.getOrElse("年龄预测" "null")
      //      x._1+"\t"+ sex+"\t"+age
    }.saveAsTextFile("D:\\TalkingData\\2018年12月24日\\haohaozhu2")
  }
}
