package com.huzhengkai.streaming;

import org.apache.spark.SparkConf;
import org.apache.spark.sql.SparkSession;

/**
 * Created by root on 2018/1/3.
 */
public class JavaSparkSessionSingleton
{
    private static transient SparkSession instance = null;

    public static SparkSession getInstance(SparkConf sparkConf)
    {
        if (instance == null)
        {
            instance = SparkSession
                    .builder()
                    .config(sparkConf)
                    .getOrCreate();
        }
        return instance;
    }
}
