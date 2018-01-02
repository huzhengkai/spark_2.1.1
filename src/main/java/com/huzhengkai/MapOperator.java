package com.huzhengkai;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.VoidFunction;

import java.util.Arrays;
import java.util.List;

public class MapOperator
{

	public static void main(String[] args) {
		SparkConf conf = new SparkConf().setAppName("MapOperator").setMaster("local");
		conf.set("spark.testing.memory", "2147480000");
		//集群模式不需要setMaster，使用spark-submit提交程序
		//local决定了我们的partition是1个
        JavaSparkContext sc = new JavaSparkContext(conf);
        
        List<Integer> numbers = Arrays.asList(1,2,3,4,5);
        JavaRDD<Integer> numberRDD = sc.parallelize(numbers);
        // map对每个元素进行操作
        JavaRDD<Integer> results = numberRDD.map(new Function<Integer, Integer>() {

			private static final long serialVersionUID = 1L;


			public Integer call(Integer number) throws Exception {
				return number * 10;
			}
		});
        //foreach是action操作
        results.foreach(new VoidFunction<Integer>() {
			
			private static final long serialVersionUID = 1L;


			public void call(Integer result) throws Exception {
				System.out.println(result);
			}
		});
        sc.close();

	}
}
