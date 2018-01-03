package com.huzhengkai.streaming;


import java.util.Arrays;
import java.util.Iterator;
import java.util.regex.Pattern;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.VoidFunction2;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.api.java.StorageLevels;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.Time;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaReceiverInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;

import java.util.Arrays;
import java.util.Iterator;

/**
 * Created by root on 2018/1/2.
 */
public class sqlOnStreaming
{
    public static void main(String[] args) throws InterruptedException
    {
        SparkConf conf = new SparkConf().setMaster("local[2]").setAppName("NetworkWordCount");
        conf.set("spark.testing.memory", "2147480000");
        JavaStreamingContext jssc = new JavaStreamingContext(conf, Durations.seconds(2));
        JavaReceiverInputDStream<String> lines = jssc.socketTextStream("Main", 9999);
        JavaDStream<String> words = lines.flatMap(
                new FlatMapFunction<String, String>()
                {

                    public Iterator<String> call(String x)
                    {
                        return Arrays.asList(x.split(" ")).iterator();
                    }
                });

        // Convert RDDs of the words DStream to DataFrame and run SQL query
        words.foreachRDD(new VoidFunction2<JavaRDD<String>, Time>()
        {

            public void call(JavaRDD<String> rdd, Time time)
            {
                SparkSession spark = JavaSparkSessionSingleton.getInstance(rdd.context().getConf());

                // Convert JavaRDD[String] to JavaRDD[bean class] to DataFrame
                JavaRDD<JavaRecord> rowRDD = rdd.map(new Function<String, JavaRecord>()
                {

                    public JavaRecord call(String word)
                    {
                        JavaRecord record = new JavaRecord();
                        record.setWord(word);
                        return record;
                    }
                });
                Dataset<Row> wordsDataFrame = spark.createDataFrame(rowRDD, JavaRecord.class);

                // Creates a temporary view using the DataFrame
                wordsDataFrame.createOrReplaceTempView("words");

                // Do word count on table using SQL and print it
                Dataset<Row> wordCountsDataFrame =
                        spark.sql("select word, count(*) as total from words group by word");
                System.out.println("========= " + time + "=========");
                wordCountsDataFrame.show();
            }
        });


        jssc.start();              // Start the computation
        jssc.awaitTermination();   // Wait for the computation to terminate

    }
}
