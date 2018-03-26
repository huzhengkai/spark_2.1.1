package com.huzhengkai.phoenix;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.VoidFunction;
import org.elasticsearch.spark.rdd.api.java.JavaEsSpark;
import scala.Tuple2;

import java.io.IOException;

/**
 * Created by root on 2018/3/9.
 */
public class SparkReadES
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
        JavaPairRDD<String, String> rdd = JavaEsSpark.esJsonRDD(sc, "rpt_t_loan_wide/type");
        rdd.foreach(new VoidFunction<Tuple2<String, String>>() {
            @Override
            public void call(Tuple2<String, String> value) throws Exception {
                System.out.println(value._1 + ": " + value._2);
            }
        });

    }
}
