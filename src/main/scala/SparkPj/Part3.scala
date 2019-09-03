package SparkPj

import org.apache.spark.{HashPartitioner, RangePartitioner}
import org.apache.spark.sql.SparkSession

object Part3 {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().master("local").appName("Part3Exercise").getOrCreate()
    val sc = spark.sparkContext

    //    val rdd1 = sc.makeRDD(Array(("a", 1), ("a", 2), ("a", 3), ("a", 4), ("b", 1), ("b", 2), ("c", 1)),3)
    ////    val partitioner = new RangePartitioner(7, rdd1)
    ////    val rdd2 = rdd1.partitionBy(partitioner)
    //    rdd1.combineByKey(
    //      (v: Int) => v + "_",
    //      (c: String, v: Int) => c + "@" + v,
    //      (c1: String, c2: String) => c1 + "$" + c2
    //    ).collect.foreach(println)
    //    rdd1.foreachPartition {
    //      x =>
    //        x.foreach(println)
    //        println("======")
    //    }

    var rdd1 = sc.makeRDD(1 to 10, 2)
    val res = rdd1.mapPartitionsWithIndex {
      (partIdx, iter) => {
        var part_map = collection.mutable.Map[String, List[Int]]()
        while (iter.hasNext) {
          var part_name = "part_" + partIdx
          var elem = iter.next()
          if (part_map.contains(part_name)) {
            var elems = part_map(part_name)
            elems ::= elem
            part_map(part_name) = elems
          } else
            part_map(part_name) = List[Int](elem)
        }
        part_map.iterator
      }
    }.collect()
    res.foreach(println)
    val res2 = rdd1.aggregate(1)(
      { (x: Int, y: Int) => x + y },
      { (a, b) => a + b }
    )
    println(res2)

  }

}
