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
import org.elasticsearch.hadoop.rest.EsHadoopInvalidRequest;
import org.elasticsearch.spark.rdd.api.java.JavaEsSpark;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by root on 2018/3/8.
 */
public class OracleToES
{
    public static void main(String[] args) throws IOException
    {
        SparkSession spark = SparkSession
                .builder()
                .appName("OracleToEs")
                .master("local[2]")
                .config("spark.testing.memory", "2147480000")
                .config("es.nodes", "es-cn-0pp0ebu1o000u7pog.kibana.elasticsearch.aliyuncs.com:9200")
                .config("es.index.auto.create", "true")
                .config("es.net.http.auth.user", "elastic")
                .config("es.net.http.auth.pass", "phkj@123")
                .config("es.batch.size.bytes", "10mb")
                .config("es.batch.size.entries", "30000")
                .config("es.input.json", "true")
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
                JSONObject obj1 = new JSONObject();
                obj1.put("data",obj);
                return obj1.toJSONString();
            }
        });
        JavaEsSpark.saveJsonToEs(rdd2,"ds_rpt_t_loan_wide/data");
    }
}
