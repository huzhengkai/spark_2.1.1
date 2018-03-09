package com.huzhengkai.phoenix;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.VoidFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.elasticsearch.spark.rdd.api.java.JavaEsSpark;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by root on 2018/3/8.
 */
public class OracleToES
{
    private static final String ORACLE_USERNAME = "pcl";
    private static final String ORACLE_PWD = "Phpcl321";
    private static final String ORACLE_CONNECTION_URL = "jdbc:oracle:thin:@221.236.20.211:15213:orcl";
    private static final String ORACLE_DRIVER = "oracle.jdbc.driver.OracleDriver";
    public static void main(String[] args) throws IOException
    {
        SparkSession spark = SparkSession
                .builder()
                .appName("OracleToEs")
                .master("local[2]")
                .config("spark.testing.memory", "2147480000")
                .config("es.nodes", "192.168.3.250:9200")
                .config("es.index.auto.create", "true")
                .config("es.net.http.auth.user", "elastic")
                .config("es.net.http.auth.pass", "phkj@123")
                .config("es.batch.size.bytes", "10mb")
                .config("es.batch.size.entries", "30000")
                .getOrCreate();
        Dataset<Row> jdbcDF = spark.read()
                .format("jdbc")
                .option("url", "jdbc:oracle:thin:@221.236.20.211:15213:orcl")
                .option("dbtable", "PCLODS.RPT_T_LOAN_WIDE")
                .option("user", "pcl")
                .option("password", "Phpcl321")
                .option("driver", "oracle.jdbc.driver.OracleDriver")
                .load();

        Dataset<String> json = jdbcDF.toJSON();
        JavaRDD<String> rdd1 = json.toJavaRDD();
        JavaRDD<String> rdd2 = rdd1.map(new Function<String, String>()
        {
            @Override
            public String call(String s) throws Exception
            {
                JSONObject obj = JSON.parseObject(s);
                Set<String> keys = obj.keySet();
                Iterator<String> it = keys.iterator();
                while(it.hasNext())
                {
                    String key =  it.next();
                    String value = obj.getString(key);
                    obj.put(key,value);
                }
                return obj.toJSONString();
            }
        });
        JavaEsSpark.saveToEs(rdd2,"RPT_T_LOAN_WIDE/type");


    }
}
