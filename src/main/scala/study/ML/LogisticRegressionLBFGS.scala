package study.ML

import org.apache.spark.mllib.classification.{LogisticRegressionWithLBFGS, LogisticRegressionModel}
import org.apache.spark.mllib.regression.LabeledPoint
import org.apache.spark.mllib.linalg.{Vector, Vectors}
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.log4j.{Level, Logger}
import org.apache.spark.mllib.evaluation.MulticlassMetrics

/**
  * Created by root on 2018-8-18.
  */
object LogisticRegressionLBFGS {

  Logger.getLogger("org.apache.spark").setLevel(Level.ERROR)
  Logger.getLogger("org.apache.eclipse.jetty.server").setLevel(Level.OFF)

  val conf = new SparkConf().setMaster("local").setAppName("LogisticRegression3") //设置环境变变量
  val sc = new SparkContext(conf)

  def main(args: Array[String]): Unit = {
    val data = sc.textFile("D:\\TalkingData\\ML\\ML2.txt") //设置数据集
    println(data.count())
    val parseData = data.map {
      line =>
        val parts = line.split(',')
        LabeledPoint(parts(0).toDouble, Vectors.dense(parts(1).split(' ').map(_.toDouble)))
    }.cache()
    val splits = parseData.randomSplit(Array(0.6, 0.4), seed = 11L) //把测试集分为两部分一部分用来做训练集一部分做校验集

    val trainingData = splits(0)
    //训练集
    val testData = splits(1) //测试集
    println(trainingData.count, testData.count)
    testData.foreach(println)
    val model = new LogisticRegressionWithLBFGS().setNumClasses(2).run(trainingData) //创建训练模型
    val labelAndPreds = testData.map {
      point =>
        val prediction = model.predict(point.features)
        (point.label, prediction)
    }
    println(model.weights)
    labelAndPreds.foreach(println)
    val trainErr = labelAndPreds.filter(r => r._1 != r._2).count.toDouble / testData.count
    println("容错率为trainErr： " + trainErr)
    /*
    * 容错率为trainErr： 0.4
     * */

    val predictionAndLabels = testData.map { //计算测试值
      case LabeledPoint(label, features) =>
        val prediction = model.predict(features)
        (prediction, label) //存储测试值和预测值
    }
    val metrics = new MulticlassMetrics(predictionAndLabels) //创建验证类
    val precision = metrics.precision //计算验证值
    println("Precision= " + precision)

//    val patient = Vectors.dense(Array(70, 3, 180.0, 4, 3)) //计算患者可能性
    val patient = Vectors.dense(Array(58, 3, 128.0, 4, 3)) //计算患者可能性
    if (patient == 1) println("患者的胃癌有几率转移。 ")
    else println("患者的胃癌没有几率转移 。")
    /*
    *Precision= 0.6
    患者的胃癌没有几率转移 。
    * */
  }
}
