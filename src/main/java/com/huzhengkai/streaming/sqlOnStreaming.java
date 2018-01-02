package com.huzhengkai.streaming;


import org.apache.spark.*;
import org.apache.spark.api.java.function.*;
import org.apache.spark.streaming.*;
import org.apache.spark.streaming.api.java.*;
import scala.Tuple2;

/**
 * Created by root on 2018/1/2.
 */
public class sqlOnStreaming
{
    SparkConf conf = new SparkConf().setMaster("local[2]").setAppName("NetworkWordCount");
    JavaStreamingContext jssc = new JavaStreamingContext(conf, Durations.seconds(1));

}
