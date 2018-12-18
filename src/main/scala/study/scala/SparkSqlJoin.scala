package study.scala

import org.apache.spark
import org.apache.spark.sql.catalyst.expressions.GenericRow
import org.apache.spark.sql.{Row, SQLContext}
import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by root on 2018-4-19.
  */

case class schema2(name: String, height: Int)

object SparkSqlJoin {
  def main(args: Array[String]): Unit = {
    val sc = new SparkContext(new SparkConf().setAppName("SparkSqlJoin").setMaster("local"))
    val sqlContext = new SQLContext(sc)

    val rdd = sc.parallelize(List((1, "Alice", 18), (2, "Andy", 19), (3, "Bob", 17), (4, "Justin", 21), (5, "Cindy", 20))).map {
      x => new GenericRow(Array(x._1, x._2, x._3)).asInstanceOf[Row]
    }

    val schema = StructType(
      Array(
        StructField("id", IntegerType, true),
        StructField("name", StringType, true),
        StructField("age", IntegerType, true)
      ))
    val df = sqlContext.createDataFrame(rdd, schema)
//    df.show()


    val rdd2 = sc.parallelize(Array(("Alice", 160), ("Andy", 159), ("Bob", 170), ("Cindy", 165), ("Rose", 160))).map {
      x => new GenericRow(Array(x._1, x._2)).asInstanceOf[Row]
    }
    val schema2 = StructType(
      List(
        StructField("name", StringType, true),
        StructField("height", IntegerType, true)
      ))
    val df2 = sqlContext.createDataFrame(rdd2, schema2)
//    df2.show()


    val rdd3 = sc.parallelize(Array((1, "Alice", 160), (2, "Andy", 159), (3, "Tom", 175), (4, "Justin", 171), (5, "Cindy", 165))).map {
      x => new GenericRow(Array(x._1, x._2, x._3)).asInstanceOf[Row]
    }
    val schema3 = StructType(Array(
      StructField("id", IntegerType, true),
      StructField("name", StringType, true),
      StructField("height", IntegerType, true)
    ))
    val df3 = sqlContext.createDataFrame(rdd3, schema3)
//    df3.show()


//    df.join(df2).show
    df.join(df2,"name").orderBy("id").show

  }
}
