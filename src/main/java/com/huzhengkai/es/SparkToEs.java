package com.huzhengkai.es;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.VoidFunction;
import org.elasticsearch.spark.rdd.api.java.JavaEsSpark;

import java.io.IOException;

/**
 * Created by root on 2018/1/11.
 */
public class SparkToEs
{
    public static void main(String[] args) throws IOException
    {
        SparkConf conf = new SparkConf().setAppName("SparkToEs").setMaster("local[2]");
        conf.set("spark.testing.memory", "2147480000");
        conf.set("es.nodes", "192.168.3.250:9200");
        conf.set("es.index.auto.create", "true");
        conf.set("es.input.json", "true");
        conf.set("es.net.http.auth.user", "elastic");
        conf.set("es.net.http.auth.pass", "phkj@123");
        conf.set("es.batch.size.bytes", "10mb");
        conf.set("es.batch.size.entries", "30000");
        JavaSparkContext sc = new JavaSparkContext(conf);
        JavaRDD<String> rdd =  sc.textFile("F:\\北京_30_车辆数据基本信息");
        JavaEsSpark.saveJsonToEs(rdd,"basic/type");
        JavaRDD<String> rdd1 =  sc.textFile("F:\\北京_30_驾驶过程历史表");
        JavaEsSpark.saveJsonToEs(rdd1,"history/type");
        JavaRDD<String> rdd2 =  sc.textFile("F:\\北京_30_驾驶行为表");
        JavaEsSpark.saveJsonToEs(rdd2,"behavior/type");
        JavaRDD<String> rdd3 =  sc.textFile("F:\\北京_30_信息历史表");
        JavaEsSpark.saveJsonToEs(rdd3,"information/type");
    }
}
