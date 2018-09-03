package study.ML

import org.apache.log4j.{Level, Logger}
import org.apache.spark.mllib.classification.{LogisticRegressionWithLBFGS, LogisticRegressionWithSGD}
import org.apache.spark.mllib.evaluation.MulticlassMetrics
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.regression.LabeledPoint
import org.apache.spark.mllib.util.MLUtils
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by root on 2018-8-18.
  */
object LR_Demo2 {
  Logger.getLogger("org.apache.spark").setLevel(Level.WARN)
  Logger.getLogger("org.apache.eclipse.jetty.server").setLevel(Level.OFF)

  val conf = new SparkConf().setMaster("local").setAppName("LogisticRegression3") //设置环境变变量
  val sc = new SparkContext(conf)

  def main(args: Array[String]): Unit = {
    val data = MLUtils.loadLibSVMFile(sc, "D:\\TalkingData\\ML\\demo2") //设置数据集
    //val data = MLUtils.loadLabeledPoints(sc,"E://wa.txt")
    /**
      * 首先介绍一下 libSVM的数据格式
      * Label 1:value 2:value ….
      * Label：是类别的标识
      * Value：就是要训练的数据，从分类的角度来说就是特征值，数据之间用空格隔开
      * 比如: -15 1:0.708 2:1056 3:-0.3333
      */
    val splits = data.randomSplit(Array(0.8, 0.2), seed = 11L) //对数据集切分成两部分，一部分训练模型，一部分校验模型
    //splits.foreach(println)
    val parsedData = splits(0)
    val parsedTest = splits(1)

    val numiteartor = 500
    val model = LogisticRegressionWithSGD.train(parsedData, numiteartor) //训练模型
    println(model.weights)

    val predictionAndLabels = parsedTest.map { //计算测试值
      case LabeledPoint(label, features) =>
        val prediction = model.predict(features)
        (prediction, label) //存储测试值和预测值
    }
//    predictionAndLabels.foreach(println)
    val trainErr = predictionAndLabels.filter(r => r._1 != r._2).count.toDouble / parsedTest.count
    println("容错率为trainErr： " + trainErr)
    /**
      * 容错率为trainErr： 0.3
      */

    val metrics = new MulticlassMetrics(predictionAndLabels) //创建验证类
    val precision = metrics.precision //计算验证值
    println("Precision= " + precision)

    //    val patient = Vectors.dense(Array(70, 3, 180.0, 4, 3)) //计算患者可能性
    //    if (patient == 1)
    //      println("患者的胃癌有几率转移。 ")
    //    else
    //      println("患者的胃癌没有几率转移 。")
    val test = sc.textFile("D:\\TalkingData\\ML\\test.txt").map {
      x =>
        val arr = x.split("\t", -1)
        val temp = arr.tail.map {
          x =>
            if (x.isEmpty)
              0
            else
              x.toDouble
        }
        val score = model.predict(Vectors.dense(temp))
        if (score == 1)
          "1\t" + x
        else
          "0\t" + x
    }.repartition(1).saveAsTextFile("D:\\TalkingData\\ML\\testResult_2_2")

    /**
      * Precision= 0.7
      * 患者的胃癌没有几率转移
      */
  }

}
