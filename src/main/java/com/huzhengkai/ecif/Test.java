package com.huzhengkai.ecif;

import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.VoidFunction;
import org.apache.spark.sql.SparkSession;

/**
 * Created by root on 2018/3/26.
 */
public class Test
{
    public static void main(String[] args)
    {
        String warehouseLocation = "D:\\IDEA\\spark_2_1_1\\spark-warehouse";
        SparkConf conf = new SparkConf().setAppName("Test").setMaster("local");
        conf.set("spark.testing.memory", "2147480000");
        conf.set("spark.sql.warehouse.dir", warehouseLocation);
        JavaSparkContext jsc = new JavaSparkContext(conf);
        JavaRDD<String> lines = jsc.textFile("hdfs://emr-header-1.cluster-55030:9000/user/hadoop/pcl.I_CUST_CONV/all/2018_03_26");
        lines.take(5);
        lines.foreach(new VoidFunction<String>()
        {
            @Override
            public void call(String s) throws Exception
            {
                System.out.println(s);
            }
        });


    }
}
