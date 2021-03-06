package com.java;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.SQLContext;

public class LoadSave {

	public static void main(String[] args) {
		SparkConf conf = new SparkConf().setAppName("dataframe").setMaster("local");
		JavaSparkContext sc = new JavaSparkContext(conf);
		SQLContext sqlContext = new SQLContext(sc);
		
		DataFrame usersDF = sqlContext.read().load("users.parquet");
		usersDF.printSchema();
		usersDF.show();
		
		usersDF.select("name","favorite_color").write().save("namesAndColors.parquet");
	}
}
