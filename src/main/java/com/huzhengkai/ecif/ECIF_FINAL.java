package com.huzhengkai.ecif;

import com.huzhengkai.ecif.bean.*;
import com.huzhengkai.sql.JavaSparkSQLExample;
import com.huzhengkai.sql.JavaUserDefinedUntypedAggregation;
import org.apache.log4j.Logger;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.MapFunction;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.sql.*;
import org.apache.spark.sql.api.java.UDF1;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructType;
import scala.Tuple2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.UUID;

/**
 * Created by root on 2018/3/29.
 */
public class ECIF_FINAL
{
    private static final Logger logger =   Logger.getLogger(ECIF_FINAL.class);
    private static HashMap<String,Boolean> hm = new HashMap<String, Boolean>();
    static
    {
        hm.put("T_MK_CUST_CERT_INFO",false);
        hm.put("CUST_CHAN_RELATION",false);
        hm.put("T_SMSPLTFORM_SNED_DATA",false);
        hm.put("T_CM_CUST_REGISTRY",false);
        hm.put("T_WE_USER_INFO",false);
        hm.put("T_MK_CUST_BURIED_POINT_INFO",false);
        hm.put("SMS_TELSALE_CUSTSALERESULT",false);
        hm.put("TASKCUSTOMER",false);
        hm.put("T_CASH_CUST_INFO",false);
        hm.put("T_CUST_CREDIT_INFO",false);
        hm.put("T_CUST_CREDIT_DETAIL",false);
        hm.put("I_CUST_CONV",false);
        hm.put("LATENT_CUST_BASE_INFO",false);
        hm.put("ECIF_CUST_CONTACT_INFO",false);
        hm.put("LATENT_CUST_CONTACT_INFO",false);
    }

    public static void main(String[] args)
    {
        SparkSession spark = SparkSession
                .builder()
                .appName("ECIF_HZK")
                .getOrCreate();

        spark.sparkContext().setLogLevel("ERROR");
//        exportECIF_CUST_MESSAGE(spark);
//        exportECIF_CUST_WECHAT_REG(spark);
//        exportECIF_CUST_WECHAT_POINT(spark);
//        exportECIF_CUST_MKTG_RESULT(spark);
//        exportECIF_CUST_WHITE_LIST_OLD(spark);
//        exportECIF_CUST_CREDIT_INFO_OLD(spark);
//        exportECIF_CUST_CREDIT_DETAIL_OLD(spark);

//        exportLATENT_ECIF_CUST_WECHAT_REG(spark);
//        exportLATENT_ECIF_CUST_WECHAT_POINT(spark);
        exportLATENT_ECIF_CUST_MESSAGE(spark);
//        exportLATENT_CUST_MKTG_RESULT(spark);

    }
    //客户短信记录
    public static void exportECIF_CUST_MESSAGE(SparkSession spark)
    {
        boolean t1 = hm.get("T_SMSPLTFORM_SNED_DATA");
        if(!t1)
        {
            loadT_SMSPLTFORM_SNED_DATA(spark);
        }
        boolean t2 = hm.get("ECIF_CUST_CONTACT_INFO");
        if(!t2)
        {
            loadECIF_CUST_CONTACT_INFO(spark);
        }
        spark.udf().register("CreateUUID1", new UDF1<String, String>()
        {
            @Override
            public String call(String s) throws Exception
            {
                return UUID.randomUUID().toString();
            }
        }, DataTypes.StringType);


        Dataset<Row> df1 = spark.sql("SELECT " +
                "CreateUUID1('') ID, " +
                "b.ecif_cust_no ECIF_CUST_NO, " +
                "a.mobile_phone PHONE_NO, " +
                "a.applic_code BUSI_CODE, " +
                "substr(a.busi_dt,0,10) SEND_DATE, " +
                "a.busi_dt SEND_TIME, " +
                "a.send_flag SEND_STATUS, " +
                "a.sql_collect SEND_COMMENT " +
                "FROM " +
                "T_SMSPLTFORM_SNED_DATA a " +
                    "JOIN ECIF_CUST_CONTACT_INFO b ON a.mobile_phone = b.CONTACT_NO");




        df1.write().mode(SaveMode.Overwrite)
                .format("org.apache.phoenix.spark")
                .option("table","ECIF_CUST_MESSAGE")
                .option("zkUrl","hb-bp1w9r77987gze6u8-001.hbase.rds.aliyuncs.com,hb-bp1w9r77987gze6u8-002.hbase.rds.aliyuncs.com,hb-bp1w9r77987gze6u8-003.hbase.rds.aliyuncs.com:2181")
                .save();

    }

    //客户微信公众号注册信息
    public static void exportECIF_CUST_WECHAT_REG(SparkSession spark)
    {
        boolean t1 = hm.get("T_MK_CUST_CERT_INFO");
        if(!t1)
        {
            loadT_MK_CUST_CERT_INFO(spark);
        }
        boolean t2 = hm.get("T_WE_USER_INFO");
        if(!t2)
        {
            loadT_WE_USER_INFO(spark);
        }
        boolean t3 = hm.get("CUST_CHAN_RELATION");
        if(!t3)
        {
            loadCUST_CHAN_RELATION(spark);
        }
        boolean t4 = hm.get("T_CM_CUST_REGISTRY");
        if(!t4)
        {
            loadT_CM_CUST_REGISTRY(spark);
        }
        //实名认证
        Dataset<Row> df1 = spark.sql("SELECT " +
                "g.id ID, "+
                "e.customer_id ECIF_CUST_NO, " +
                "f.opend_id WECHAT_OPENDID, " +
                "f.phone_num PHONE_NO, " +
                "d.cert_no CERT_NO, " +
                "f.name CUST_NAME, " +
                "'E1001003' TYPE, " +
                "f.status STATUS, " +
                "g.subscribe_time ATT_DATE, " +
                "f.insert_date REG_DATE, " +
                "d.insert_date AUTH_DATE, " +
                "d.valid_end_date EXPIRE_DATE " +
                "FROM " +
                "T_MK_CUST_CERT_INFO d " +
                "JOIN CUST_CHAN_RELATION e ON d.cid = e.chan_id " +
                "JOIN T_CM_CUST_REGISTRY f ON d.cert_no = f.id_card " +
                "JOIN T_WE_USER_INFO g ON f.opend_id = g.open_id " +
                "WHERE " +
                "e.chan_type = '01' " +
                "AND d.cert_type = '50100001' " +
                "AND g.subscribe = '67000001' " +
                "AND f.id_card IS NOT NULL");

        //注册
        Dataset<Row> df2 = spark.sql("SELECT " +
                "j.id ID, " +
                "i.customer_id ECIF_CUST_NO, " +
                "h.opend_id WECHAT_OPENDID, " +
                "h.phone_num PHONE_NO, " +
                "'' CERT_NO, " +
                "h.name CUST_NAME, " +
                "'E1001002' TYPE, " +
                "h.status STATUS, " +
                "j.subscribe_time ATT_DATE, " +
                "h.insert_date REG_DATE, " +
                "'' AUTH_DATE, " +
                "'' EXPIRE_DATE " +
                "FROM " +
                "T_CM_CUST_REGISTRY h " +
                "JOIN CUST_CHAN_RELATION i ON h.cid = i.chan_id " +
                "JOIN T_WE_USER_INFO j ON h.opend_id = j.open_id " +
                "WHERE " +
                "h.id_card IS NULL " +
                "AND i.chan_type = '01' " +
                "AND j.subscribe = '67000001'");
//
        //关注
        Dataset<Row> df3 = spark.sql("SELECT " +
                "k.id ID, " +
                "'' ECIF_CUST_NO, " +
                "k.open_id WECHAT_OPENDID, " +
                "'' PHONE_NO, " +
                "'' CERT_NO, " +
                "'' CUST_NAME, " +
                "'E1001001' TYPE, " +
                "'' STATUS, " +
                "k.subscribe_time ATT_DATE, " +
                "'' REG_DATE, " +
                "'' AUTH_DATE, " +
                "'' EXPIRE_DATE " +
                "FROM " +
                "T_WE_USER_INFO k " +
                "LEFT JOIN T_CM_CUST_REGISTRY l ON k.open_id = l.opend_id " +
                "WHERE " +
                "k.subscribe = '67000001' " +
                "and l.opend_id is null");


        Dataset<Row> dfFinal = df1.union(df2).union(df3);
        dfFinal.write().mode(SaveMode.Overwrite)
                .format("org.apache.phoenix.spark")
                .option("table","ECIF_CUST_WECHAT_REG")
                .option("zkUrl","hb-bp1w9r77987gze6u8-001.hbase.rds.aliyuncs.com,hb-bp1w9r77987gze6u8-002.hbase.rds.aliyuncs.com,hb-bp1w9r77987gze6u8-003.hbase.rds.aliyuncs.com:2181")
                .save();
    }
    //客户微信埋点信息
    public static void exportECIF_CUST_WECHAT_POINT(SparkSession spark)
    {
        boolean t1 = hm.get("T_MK_CUST_BURIED_POINT_INFO");
        if(!t1)
        {
            loadT_MK_CUST_BURIED_POINT_INFO(spark);
        }
        boolean t2 = hm.get("T_CM_CUST_REGISTRY");
        if(!t2)
        {
            loadT_CM_CUST_REGISTRY(spark);
        }
        boolean t3 = hm.get("CUST_CHAN_RELATION");
        if(!t3)
        {
            loadCUST_CHAN_RELATION(spark);
        }
        Dataset<Row> df = spark.sql("SELECT " +
                "o.id ID, " +
                "n.customer_id ECIF_CUST_NO, " +
                "m.channel_type CHANNEL_TYPE, " +
                "o.opend_id WECHAT_OPENID, " +
                "m.point_type POINT_TYPE, " +
                "m.page_code PAGE_CODE, " +
                "substr(m.insert_date,0,10) ENTER_DATE, " +
                "m.insert_date ENTER_TIME, " +
                "'' EXISTS_TIME, " +
                "0.0 STAY_TIMES " +
                "FROM " +
                "T_MK_CUST_BURIED_POINT_INFO m " +
                "JOIN CUST_CHAN_RELATION n ON m.cid = n.chan_id " +
                "JOIN T_CM_CUST_REGISTRY o ON o.cid = m.cid");


        df.write().mode(SaveMode.Overwrite)
                .format("org.apache.phoenix.spark")
                .option("table","ECIF_CUST_WECHAT_POINT")
                .option("zkUrl","hb-bp1w9r77987gze6u8-001.hbase.rds.aliyuncs.com,hb-bp1w9r77987gze6u8-002.hbase.rds.aliyuncs.com,hb-bp1w9r77987gze6u8-003.hbase.rds.aliyuncs.com:2181")
                .save();
    }

    //客户营销结果表
    public static void exportECIF_CUST_MKTG_RESULT(SparkSession spark)
    {
        boolean t1 = hm.get("SMS_TELSALE_CUSTSALERESULT");
        if(!t1)
        {
            loadSMS_TELSALE_CUSTSALERESULT(spark);
        }
        boolean t2 = hm.get("TASKCUSTOMER");
        if(!t2)
        {
            loadTASKCUSTOMER(spark);
        }
        boolean t3 = hm.get("ECIF_CUST_CONTACT_INFO");
        if(!t3)
        {
            loadECIF_CUST_CONTACT_INFO(spark);
        }
        //客户号存在时
        Dataset<Row> df = spark.sql("SELECT DISTINCT " +
                "p.ecif_cust_no ECIF_CUST_NO, " +
                "'001001' CERT_TYPE, " +
                "p.cert_no CERT_NO, " +
                "p.cust_name CUST_NAME, " +
                "p.tel_no PHONE_NO, " +
                "p.result_id MKTG_RESULT_ID, " +
                "q.taskname TASK_NAME, " +
                "p.operate_type OPER_TYPE, " +
                "p.result_state MKTG_RESULT, " +
                "p.remark MKTG_REMARK, " +
                "p.update_userid MKTG_STAFF_NO, " +
                "p.update_username MKTG_STAFF_NAME, " +
                "substr(p.update_time,0,10) MKTG_DATE, " +
                "p.update_time MKTG_TIME " +
                "FROM " +
                "SMS_TELSALE_CUSTSALERESULT p " +
                "JOIN TASKCUSTOMER q ON p.task_id = q.taskid " +
                "WHERE " +
                "p.ecif_cust_no IS NOT NULL");
        //客户号不存在时
        Dataset<Row> df1 = spark.sql("SELECT DISTINCT " +
                "s.ECIF_CUST_NO ECIF_CUST_NO, " +
                "'001001' CERT_TYPE, " +
                "r.cert_no CERT_NO, " +
                "r.cust_name CUST_NAME, " +
                "r.tel_no PHONE_NO, " +
                "r.result_id MKTG_RESULT_ID, " +
                "u.taskname TASK_NAME, " +
                "r.operate_type OPER_TYPE, " +
                "r.result_state MKTG_RESULT, " +
                "r.remark MKTG_REMARK, " +
                "r.update_userid MKTG_STAFF_NO, " +
                "r.update_username MKTG_STAFF_NAME, " +
                "substr(r.update_time,0,10) MKTG_DATE, " +
                "r.update_time MKTG_TIME " +
                "FROM " +
                "SMS_TELSALE_CUSTSALERESULT r " +
                "JOIN ECIF_CUST_CONTACT_INFO  s ON r.tel_no = s.CONTACT_NO " +
                "JOIN TASKCUSTOMER u ON u.taskid = r.task_id " +
                "WHERE " +
                "r.ecif_cust_no IS NULL");


        df.union(df1).write().mode(SaveMode.Overwrite)
                .format("org.apache.phoenix.spark")
                .option("table","ECIF_CUST_MKTG_RESULT")
                .option("zkUrl","hb-bp1w9r77987gze6u8-001.hbase.rds.aliyuncs.com,hb-bp1w9r77987gze6u8-002.hbase.rds.aliyuncs.com,hb-bp1w9r77987gze6u8-003.hbase.rds.aliyuncs.com:2181")
                .save();



    }

    //客户授信白名单信息(老版)
    public static void exportECIF_CUST_WHITE_LIST_OLD(SparkSession spark)
    {
        boolean t1 = hm.get("T_CASH_CUST_INFO");
        if(!t1)
        {
            loadT_CASH_CUST_INFO(spark);
        }
        boolean t2 = hm.get("I_CUST_CONV");
        if(!t2)
        {
            loadI_CUST_CONV(spark);
        }
        //Function可以使用UDF1到UDF22/21,所表达的意思就是几个参数，2代指两个入参，10代指10个入参
        spark.udf().register("CreateUUID", new UDF1<String, String>()
        {
            @Override
            public String call(String s) throws Exception
            {
                return UUID.randomUUID().toString();
            }
        }, DataTypes.StringType);

        Dataset<Row> df = spark.sql("SELECT " +
                "CreateUUID('') ID, " +
                "ECIF_CUST_NO, " +
                "CERT_TYPE, " +
                "CERT_NO, " +
                "CUST_NAME, " +
                "PHONE_NO, " +
                "LOAN_NO, " +
                "INIT_CREDIT_LIMIT, " +
                "CREDIT_LIMIT, " +
                "LEVEL, " +
                "IS_VALID, " +
                "CASH_LOAN_TYPE, " +
                "ENTER_DATE, " +
                "EXISTS_DATE, " +
                "IS_KX " +
                "FROM  " +
                "(SELECT  DISTINCT " +
                "    dd.ecif_cust_no ECIF_CUST_NO,  " +
                "    dd.cert_type CERT_TYPE,  " +
                "    cc.cert_no CERT_NO,  " +
                "    cc.cust_name CUST_NAME,  " +
                "    cc.cust_phone PHONE_NO,  " +
                "    cc.loan_no LOAN_NO,  " +
                "    cc.init_credit_limit INIT_CREDIT_LIMIT,  " +
                "    cc.credit_limit CREDIT_LIMIT,  " +
                "    cc.lvl LEVEL,  " +
                "    cc.state IS_VALID,  " +
                "    cc.type CASH_LOAN_TYPE,  " +
                "    cc.busi_dt ENTER_DATE,  " +
                "    cc.invalid_dt EXISTS_DATE,  " +
                "    cc.is_kx IS_KX  " +
                "    FROM  " +
                "    T_CASH_CUST_INFO cc  " +
                "    JOIN (  " +
                "    SELECT  " +
                "    max(aa.busi_dt) ee,  " +
                "    aa.loan_no  " +
                "    FROM  " +
                "    T_CASH_CUST_INFO aa  " +
                "    GROUP BY  " +
                "    aa.loan_no  " +
                "    ) bb ON cc.busi_dt = bb.ee  " +
                "    AND cc.loan_no = bb.loan_no  " +
                "    JOIN I_CUST_CONV dd ON dd.cert_no = cc.cert_no) aaa");


        //Overwrite代表，相同rowkey的数据会被覆盖
        df.write().mode(SaveMode.Overwrite)
                .format("org.apache.phoenix.spark")
                .option("table","ECIF_CUST_WHITE_LIST_OLD")
                .option("zkUrl","hb-bp1w9r77987gze6u8-001.hbase.rds.aliyuncs.com,hb-bp1w9r77987gze6u8-002.hbase.rds.aliyuncs.com,hb-bp1w9r77987gze6u8-003.hbase.rds.aliyuncs.com:2181")
                .save();
    }
    //客户授信信息(老版)
    public static void exportECIF_CUST_CREDIT_INFO_OLD(SparkSession spark)
    {
        boolean t1 = hm.get("T_CUST_CREDIT_INFO");
        if(!t1)
        {
            loadT_CUST_CREDIT_INFO(spark);
        }
        boolean t2 = hm.get("I_CUST_CONV");
        if(!t2)
        {
            loadI_CUST_CONV(spark);
        }

        Dataset<Row> df = spark.sql("SELECT " +
                "x.credit_id ID, " +
                "x.credit_id CREDIT_ID, " +
                "y.ecif_cust_no ECIF_CUST_NO, " +
                "y.cert_type CERT_TYPE, " +
                "y.cert_no CERT_NO, " +
                "x.cust_name CUST_NAME, " +
                "x.phone_num PHONE_NO, " +
                "x.CUST_NO CREDIT_PROD, " +
                "x.credit_level CREDIT_LEVEL, " +
                "x.credit_total_amt CREDIT_AMT, " +
                "x.credit_used_amt CREDIT_USED_AMT, " +
                "x.credit_remain_amt CREDIT_REMAIN_AMT, " +
                "x.state CREDIT_STATUS, " +
                "x.INSERT_TIME CREDIT_DATE " +
                "FROM " +
                "T_CUST_CREDIT_INFO x " +
                "JOIN I_CUST_CONV y ON x.cert_no = y.cert_no");

//        //这里还需要对多产品，进行拼接
//        JavaRDD<Row> rdd = df.toJavaRDD();
//        JavaPairRDD<String, ECIF_CUST_CREDIT_INFO_OLD> rdd1 = rdd.mapToPair(new PairFunction<Row, String, ECIF_CUST_CREDIT_INFO_OLD>()
//        {
//            @Override
//            public Tuple2<String, ECIF_CUST_CREDIT_INFO_OLD> call(Row row) throws Exception
//            {
//                ECIF_CUST_CREDIT_INFO_OLD ecif_cust_credit_info_old = new ECIF_CUST_CREDIT_INFO_OLD(row.getString(0),row.getString(1),row.getString(2),row.getString(3),row.getString(4),row.getString(5),row.getString(6),row.getString(7),row.getString(8)
//                        ,row.getDouble(9),row.getDouble(10),row.getDouble(11),row.getString(12),row.getString(13));
//                //用证件号作key
//                return new Tuple2<String, ECIF_CUST_CREDIT_INFO_OLD>(row.getString(4),ecif_cust_credit_info_old);
//            }
//        });
//        JavaPairRDD<String, ECIF_CUST_CREDIT_INFO_OLD> rdd2 = rdd1.reduceByKey(new Function2<ECIF_CUST_CREDIT_INFO_OLD, ECIF_CUST_CREDIT_INFO_OLD, ECIF_CUST_CREDIT_INFO_OLD>()
//        {
//            @Override
//            public ECIF_CUST_CREDIT_INFO_OLD call(ECIF_CUST_CREDIT_INFO_OLD v1, ECIF_CUST_CREDIT_INFO_OLD v2) throws Exception
//            {
//                //多产品逗号拼接
//                ECIF_CUST_CREDIT_INFO_OLD ec = new ECIF_CUST_CREDIT_INFO_OLD(v1.getID(),v1.getCREDIT_ID(),v1.getECIF_CUST_NO(),
//                        v1.getCERT_TYPE(),v1.getCERT_NO(),v1.getCUST_NAME(),v1.getPHONE_NO(),v1.getCREDIT_PROD()+","+v2.getCREDIT_PROD(),v1.getCREDIT_LEVEL(),
//                        v1.getCREDIT_AMT(),v1.getCREDIT_USED_AMT(),v1.getCREDIT_REMAIN_AMT(),v1.getCREDIT_STATUS(),v1.getCREDIT_DATE());
//                return ec;
//            }
//        });
//        JavaRDD<ECIF_CUST_CREDIT_INFO_OLD> rdd3 = rdd2.map(new Function<Tuple2<String,ECIF_CUST_CREDIT_INFO_OLD>, ECIF_CUST_CREDIT_INFO_OLD>()
//        {
//            @Override
//            public ECIF_CUST_CREDIT_INFO_OLD call(Tuple2<String, ECIF_CUST_CREDIT_INFO_OLD> v1) throws Exception
//            {
//                return v1._2;
//            }
//        });
//        Dataset<Row> ds = spark.createDataFrame(rdd3, ECIF_CUST_CREDIT_INFO_OLD.class);

        df.write().mode(SaveMode.Overwrite)
                .format("org.apache.phoenix.spark")
                .option("table","ECIF_CUST_CREDIT_INFO_OLD")
                .option("zkUrl","hb-bp1w9r77987gze6u8-001.hbase.rds.aliyuncs.com,hb-bp1w9r77987gze6u8-002.hbase.rds.aliyuncs.com,hb-bp1w9r77987gze6u8-003.hbase.rds.aliyuncs.com:2181")
                .save();
    }

    //客户授信明细信息(老版)
    public static void exportECIF_CUST_CREDIT_DETAIL_OLD(SparkSession spark)
    {
        boolean t1 = hm.get("T_CUST_CREDIT_DETAIL");
        if(!t1)
        {
            loadT_CUST_CREDIT_DETAIL(spark);
        }
        boolean t2 = hm.get("T_CUST_CREDIT_INFO");
        if(!t2)
        {
            loadT_CUST_CREDIT_INFO(spark);
        }
        boolean t3 = hm.get("I_CUST_CONV");
        if(!t3)
        {
            loadI_CUST_CONV(spark);
        }
        Dataset<Row> df = spark.sql("SELECT " +
                "ff.table_key ID, " +
                "ff.credit_id CREDIT_ID, " +
                "hh.ecif_cust_no ECIF_CUST_NO, " +
                "ff.prod_type CREDIT_PROD, " +
                "gg.credit_level CREDIT_LEVEL, " +
                "gg.credit_total_amt CREDIT_AMT, " +
                "gg.credit_used_amt CREDIT_USED_AMT, " +
                "gg.credit_remain_amt CREDIT_REMAIN_AMT, " +
                "ff.credit_beg_date CREDIT_START_DATE, " +
                "ff.credit_end_date CREDIT_END_DATE " +
                "FROM " +
                "T_CUST_CREDIT_DETAIL ff " +
                "JOIN T_CUST_CREDIT_INFO gg ON ff.credit_id = gg.credit_id " +
                "JOIN I_CUST_CONV hh ON hh.cert_no = gg.cert_no");

        df.write().mode(SaveMode.Overwrite)
                .format("org.apache.phoenix.spark")
                .option("table","ECIF_CUST_CREDIT_DETAIL_OLD")
                .option("zkUrl","hb-bp1w9r77987gze6u8-001.hbase.rds.aliyuncs.com,hb-bp1w9r77987gze6u8-002.hbase.rds.aliyuncs.com,hb-bp1w9r77987gze6u8-003.hbase.rds.aliyuncs.com:2181")
                .save();
    }
    //潜在客户微信公众号注册信息
    public static void exportLATENT_ECIF_CUST_WECHAT_REG(SparkSession spark)
    {
        boolean t1 = hm.get("T_MK_CUST_CERT_INFO");
        if(!t1)
        {
            loadT_MK_CUST_CERT_INFO(spark);
        }
        boolean t2 = hm.get("T_WE_USER_INFO");
        if(!t2)
        {
            loadT_WE_USER_INFO(spark);
        }
        boolean t3 = hm.get("CUST_CHAN_RELATION");
        if(!t3)
        {
            loadCUST_CHAN_RELATION(spark);
        }
        boolean t4 = hm.get("T_CM_CUST_REGISTRY");
        if(!t4)
        {
            loadT_CM_CUST_REGISTRY(spark);
        }
        boolean t5 = hm.get("LATENT_CUST_BASE_INFO");
        if(!t5)
        {
            loadLATENT_CUST_BASE_INFO(spark);
        }

        //实名认证
        Dataset<Row> df1 = spark.sql("SELECT " +
                "g.id ID, "+
                "xx.latent_cust_no LATENT_CUST_NO, " +
                "f.opend_id WECHAT_OPENDID, " +
                "f.phone_num PHONE_NO, " +
                "d.cert_no CERT_NO, " +
                "f.name CUST_NAME, " +
                "'E1001003' TYPE, " +
                "f.status STATUS, " +
                "g.subscribe_time ATT_DATE, " +
                "f.insert_date REG_DATE, " +
                "d.insert_date AUTH_DATE, " +
                "d.valid_end_date EXPIRE_DATE " +
                "FROM " +
                "T_MK_CUST_CERT_INFO d " +
                "JOIN CUST_CHAN_RELATION e ON d.cid = e.chan_id " +
                "JOIN T_CM_CUST_REGISTRY f ON d.cert_no = f.id_card " +
                "JOIN T_WE_USER_INFO g ON f.opend_id = g.open_id " +
                "JOIN LATENT_CUST_BASE_INFO xx ON xx.cert_no = d.cert_no "+
                "WHERE " +
                "e.chan_type = '01' " +
                "AND d.cert_type = '50100001' " +
                "AND g.subscribe = '67000001' " +
                "AND f.id_card IS NOT NULL "+
                "AND e.customer_id is null");

        //注册
        Dataset<Row> df2 = spark.sql("SELECT " +
                "j.id ID, " +
                "xx.latent_cust_no LATENT_CUST_NO, " +
                "h.opend_id WECHAT_OPENDID, " +
                "h.phone_num PHONE_NO, " +
                "'' CERT_NO, " +
                "h.name CUST_NAME, " +
                "'E1001002' TYPE, " +
                "h.status STATUS, " +
                "j.subscribe_time ATT_DATE, " +
                "h.insert_date REG_DATE, " +
                "'' AUTH_DATE, " +
                "'' EXPIRE_DATE " +
                "FROM " +
                "T_CM_CUST_REGISTRY h " +
                "JOIN CUST_CHAN_RELATION i ON h.cid = i.chan_id " +
                "JOIN T_WE_USER_INFO j ON h.opend_id = j.open_id " +
                "JOIN LATENT_CUST_BASE_INFO xx ON xx.cert_no = i.cust_cert_no "+
                "WHERE " +
                "h.id_card IS NULL " +
                "AND i.chan_type = '01' " +
                "AND j.subscribe = '67000001'"+
                "AND i.customer_id is null");

        //微信关注的潜在客户取不到，没有身份证号信息

        //得到最终所有的数据
        Dataset<Row> dfFinal = df1.union(df2);

        dfFinal.write().mode(SaveMode.Overwrite)
                .format("org.apache.phoenix.spark")
                .option("table","LATENT_ECIF_CUST_WECHAT_REG")
                .option("zkUrl","hb-bp1w9r77987gze6u8-001.hbase.rds.aliyuncs.com,hb-bp1w9r77987gze6u8-002.hbase.rds.aliyuncs.com,hb-bp1w9r77987gze6u8-003.hbase.rds.aliyuncs.com:2181")
                .save();
    }
    //潜在客户微信埋点信息
    public static void exportLATENT_ECIF_CUST_WECHAT_POINT(SparkSession spark)
    {
        boolean t1 = hm.get("T_MK_CUST_BURIED_POINT_INFO");
        if(!t1)
        {
            loadT_MK_CUST_BURIED_POINT_INFO(spark);
        }
        boolean t2 = hm.get("T_CM_CUST_REGISTRY");
        if(!t2)
        {
            loadT_CM_CUST_REGISTRY(spark);
        }
        boolean t3 = hm.get("CUST_CHAN_RELATION");
        if(!t3)
        {
            loadCUST_CHAN_RELATION(spark);
        }
        boolean t4 = hm.get("LATENT_CUST_BASE_INFO");
        if(!t4)
        {
            loadLATENT_CUST_BASE_INFO(spark);
        }
        Dataset<Row> df = spark.sql("SELECT " +
                "o.id ID, "+
                "xx.latent_cust_no LATENT_CUST_NO, " +
                "m.channel_type CHANNEL_TYPE, " +
                "o.opend_id WECHAT_OPENID, " +
                "m.point_type POINT_TYPE, " +
                "m.page_code PAGE_CODE, " +
                "substr(m.insert_date,0,10) ENTER_DATE, " +
                "m.insert_date ENTER_TIME, " +
                "'' EXISTS_TIME, " +
                "0.0 STAY_TIMES " +
                "FROM " +
                "T_MK_CUST_BURIED_POINT_INFO m " +
                "JOIN CUST_CHAN_RELATION n ON m.cid = n.chan_id " +
                "JOIN T_CM_CUST_REGISTRY o ON o.cid = m.cid " +
                "JOIN LATENT_CUST_BASE_INFO xx ON xx.cert_no = n.cust_cert_no "+
                "where n.customer_id is null");

        df.write().mode(SaveMode.Overwrite)
                .format("org.apache.phoenix.spark")
                .option("table","LATENT_ECIF_CUST_WECHAT_POINT")
                .option("zkUrl","hb-bp1w9r77987gze6u8-001.hbase.rds.aliyuncs.com,hb-bp1w9r77987gze6u8-002.hbase.rds.aliyuncs.com,hb-bp1w9r77987gze6u8-003.hbase.rds.aliyuncs.com:2181")
                .save();

    }
    //潜在客户短信记录
    public static void exportLATENT_ECIF_CUST_MESSAGE(SparkSession spark)
    {
        boolean t1 = hm.get("T_SMSPLTFORM_SNED_DATA");
        if(!t1)
        {
            loadT_SMSPLTFORM_SNED_DATA(spark);
        }
        boolean t2 = hm.get("LATENT_CUST_CONTACT_INFO");
        if(!t2)
        {
            loadLATENT_CUST_CONTACT_INFO(spark);
        }

        spark.udf().register("CreateUUID2", new UDF1<String, String>()
        {
            @Override
            public String call(String s) throws Exception
            {
                return UUID.randomUUID().toString();
            }
        }, DataTypes.StringType);


        Dataset<Row> df = spark.sql("" +
                "SELECT " +
                "CreateUUID2('') ID, " +
                "xx.LATENT_CUST_NO LATENT_CUST_NO, " +
                "a.mobile_phone PHONE_NO, " +
                "a.applic_code BUSI_CODE, " +
                "substr(a.busi_dt,0,10) SEND_DATE, " +
                "a.busi_dt SEND_TIME, " +
                "a.send_flag SEND_STATUS, " +
                "a.sql_collect SEND_COMMENT " +
                "FROM " +
                "T_SMSPLTFORM_SNED_DATA a " +
                "JOIN LATENT_CUST_CONTACT_INFO xx ON xx.contact_no = a.mobile_phone");
//        df.show();
        df.write().mode(SaveMode.Overwrite)
                .format("org.apache.phoenix.spark")
                .option("table","LATENT_ECIF_CUST_MESSAGE")
                .option("zkUrl","hb-bp1w9r77987gze6u8-001.hbase.rds.aliyuncs.com,hb-bp1w9r77987gze6u8-002.hbase.rds.aliyuncs.com,hb-bp1w9r77987gze6u8-003.hbase.rds.aliyuncs.com:2181")
                .save();


    }
    //潜在客户营销结果表
    public static void exportLATENT_CUST_MKTG_RESULT(SparkSession spark)
    {
        boolean t1 = hm.get("SMS_TELSALE_CUSTSALERESULT");
        if(!t1)
        {
            loadSMS_TELSALE_CUSTSALERESULT(spark);
        }
        boolean t2 = hm.get("TASKCUSTOMER");
        if(!t2)
        {
            loadTASKCUSTOMER(spark);
        }
        boolean t3 = hm.get("LATENT_CUST_CONTACT_INFO");
        if(!t3)
        {
            loadLATENT_CUST_CONTACT_INFO(spark);
        }
        boolean t4 = hm.get("LATENT_CUST_BASE_INFO");
        if(!t4)
        {
            loadLATENT_CUST_BASE_INFO(spark);
        }
        Dataset<Row> df = spark.sql("SELECT DISTINCT " +
                "p.result_id ID, " +
                "xx.LATENT_CUST_NO LATENT_CUST_NO, " +
                "yy.cert_type CERT_TYPE, " +
                "p.cert_no CERT_NO, " +
                "p.cust_name CUST_NAME, " +
                "p.tel_no PHONE_NO, " +
                "p.result_id MKTG_RESULT_ID, " +
                "q.taskname TASK_NAME, " +
                "p.operate_type OPER_TYPE, " +
                "p.result_state MKTG_RESULT, " +
                "p.remark MKTG_REMARK, " +
                "p.update_userid MKTG_STAFF_NO, " +
                "p.update_username MKTG_STAFF_NAME, " +
                "substr(p.update_time,0,10) MKTG_DATE, " +
                "p.update_time MKTG_TIME " +
                "FROM " +
                "SMS_TELSALE_CUSTSALERESULT p " +
                "LEFT JOIN TASKCUSTOMER q ON p.task_id = q.taskid " +
                "JOIN LATENT_CUST_CONTACT_INFO xx ON xx.contact_no = p.tel_no " +
                "JOIN LATENT_CUST_BASE_INFO yy ON yy.LATENT_CUST_NO = xx.LATENT_CUST_NO " +
                "WHERE " +
                "p.ecif_cust_no IS NULL");
//        df.show();
        df.write().mode(SaveMode.Overwrite)
                .format("org.apache.phoenix.spark")
                .option("table","LATENT_CUST_MKTG_RESULT")
                .option("zkUrl","hb-bp1w9r77987gze6u8-001.hbase.rds.aliyuncs.com,hb-bp1w9r77987gze6u8-002.hbase.rds.aliyuncs.com,hb-bp1w9r77987gze6u8-003.hbase.rds.aliyuncs.com:2181")
                .save();

    }

    public static void readCSV(SparkSession spark)
    {
         Dataset<Row> ds  = spark.read().option("header","true").csv();


    }

    //加载phoenix中ECIF_CUST_CONTACT_INFO
    public static void loadECIF_CUST_CONTACT_INFO(SparkSession spark)
    {
        Dataset<Row> ds = spark.read().format("org.apache.phoenix.spark").option("table","ECIF_CUST_CONTACT_INFO")
                .option("zkUrl","hb-bp1w9r77987gze6u8-001.hbase.rds.aliyuncs.com,hb-bp1w9r77987gze6u8-002.hbase.rds.aliyuncs.com,hb-bp1w9r77987gze6u8-003.hbase.rds.aliyuncs.com:2181").load();
        ds.createOrReplaceTempView("ECIF_CUST_CONTACT_INFO");
        ds.cache();
        hm.put("ECIF_CUST_CONTACT_INFO",true);
    }

    //加载潜在客户联系信息
    public static void loadLATENT_CUST_CONTACT_INFO(SparkSession spark)
    {
        logger.info("开始加载LATENT_CUST_CONTACT_INFO...");
        Encoder<LATENT_CUST_CONTACT_INFO> latent_cust_contact_infoEncoder = Encoders.javaSerialization(LATENT_CUST_CONTACT_INFO.class);
        Dataset<LATENT_CUST_CONTACT_INFO> LATENT_CUST_CONTACT_INFO = spark.read().textFile("hdfs://emr-header-1.cluster-55030:9000/user/hadoop/pcl.PCLODS.LATENT_CUST_CONTACT_INFO/all/2018_04_02")
                .map(new MapFunction<String, LATENT_CUST_CONTACT_INFO>()
                {
                    @Override
                    public LATENT_CUST_CONTACT_INFO call(String value) throws Exception
                    {
                        String [] s1 = value.split("\\@\\|\\^");
                        LATENT_CUST_CONTACT_INFO latent_cust_contact_info = new LATENT_CUST_CONTACT_INFO(s1[0],s1[1],s1[2],s1[3],
                                s1[4],s1[5]);
                        latent_cust_contact_info.convertNull();
                        return latent_cust_contact_info;
                    }
                },latent_cust_contact_infoEncoder);
        JavaRDD<LATENT_CUST_CONTACT_INFO> LATENT_CUST_CONTACT_INFO_RDD = LATENT_CUST_CONTACT_INFO.toJavaRDD();
        Dataset<Row> LATENT_CUST_CONTACT_INFO_DF = spark.createDataFrame(LATENT_CUST_CONTACT_INFO_RDD, LATENT_CUST_CONTACT_INFO.class);
        LATENT_CUST_CONTACT_INFO_DF.createOrReplaceTempView("LATENT_CUST_CONTACT_INFO");
        LATENT_CUST_CONTACT_INFO_DF.cache();

        hm.put("LATENT_CUST_CONTACT_INFO",true);

    }

    //加载潜在客户基本表
    public static void loadLATENT_CUST_BASE_INFO(SparkSession spark)
    {
        logger.info("开始加载LATENT_CUST_BASE_INFO...");
        Encoder<LATENT_CUST_BASE_INFO> latent_cust_base_infoEncoder = Encoders.javaSerialization(LATENT_CUST_BASE_INFO.class);
        Dataset<LATENT_CUST_BASE_INFO> LATENT_CUST_BASE_INFO = spark.read().textFile("hdfs://emr-header-1.cluster-55030:9000/user/hadoop/pcl.PCLODS.LATENT_CUST_BASE_INFO/all/2018_04_02")
                .map(new MapFunction<String, LATENT_CUST_BASE_INFO>()
                {
                    @Override
                    public LATENT_CUST_BASE_INFO call(String value) throws Exception
                    {
                        String [] s1 = value.split("\\@\\|\\^");
                        LATENT_CUST_BASE_INFO latent_cust_base_info = new LATENT_CUST_BASE_INFO(s1[0],s1[1],s1[2],s1[3],
                                s1[4],s1[5],s1[6]);
                        latent_cust_base_info.convertNull();
                        return latent_cust_base_info;
                    }
                },latent_cust_base_infoEncoder);
        JavaRDD<LATENT_CUST_BASE_INFO> LATENT_CUST_BASE_INFO_RDD = LATENT_CUST_BASE_INFO.toJavaRDD();
        Dataset<Row> LATENT_CUST_BASE_INFO_DF = spark.createDataFrame(LATENT_CUST_BASE_INFO_RDD, LATENT_CUST_BASE_INFO.class);
        LATENT_CUST_BASE_INFO_DF.createOrReplaceTempView("LATENT_CUST_BASE_INFO");
        LATENT_CUST_BASE_INFO_DF.cache();
        hm.put("LATENT_CUST_BASE_INFO",true);

    }

    public static void loadI_CUST_CONV(SparkSession spark)
    {
        logger.info("开始加载I_CUST_CONV...");
        Encoder<I_CUST_CONV> i_cust_convEncoder = Encoders.javaSerialization(I_CUST_CONV.class);
        Dataset<I_CUST_CONV> I_CUST_CONV = spark.read().textFile("hdfs://emr-header-1.cluster-55030:9000/user/hadoop/pcl.I_CUST_CONV/all/2018_04_02")
                .map(new MapFunction<String, I_CUST_CONV>()
                {
                    @Override
                    public I_CUST_CONV call(String value) throws Exception
                    {
                        String [] s1 = value.split("\\@\\|\\^");
                        I_CUST_CONV i_cust_conv = new I_CUST_CONV(s1[1],s1[2],s1[3],s1[4],s1[5],s1[0],s1[10],s1[6],s1[8],
                                s1[9],s1[7]);
                        i_cust_conv.convertNull();
                        return i_cust_conv;
                    }
                },i_cust_convEncoder);

        JavaRDD<I_CUST_CONV> I_CUST_CONV_RDD = I_CUST_CONV.toJavaRDD();
        Dataset<Row> I_CUST_CONV_DF = spark.createDataFrame(I_CUST_CONV_RDD, I_CUST_CONV.class);
        I_CUST_CONV_DF.createOrReplaceTempView("I_CUST_CONV");
        I_CUST_CONV_DF.cache();
        hm.put("I_CUST_CONV",true);
    }

    public static void loadT_MK_CUST_CERT_INFO(SparkSession spark)
    {
        logger.info("开始加载T_MK_CUST_CERT_INFO...");
        SparkContext sc = spark.sparkContext();
        JavaSparkContext jsc = new JavaSparkContext(sc);
        JavaRDD<T_MK_CUST_CERT_INFO> T_MK_CUST_CERT_INFO = jsc.
                textFile("hdfs://emr-header-1.cluster-55030:9000/user/hadoop/pcl.WCHAT.T_MK_CUST_CERT_INFO/add/2018_04_02")
                .map(new Function<String, T_MK_CUST_CERT_INFO>()
                {
                    @Override
                    public T_MK_CUST_CERT_INFO call(String v1) throws Exception
                    {
                        String [] s1 = v1.split("\\@\\|\\^");

                        String s = s1[18];
                        Integer i = s.indexOf(".");
                        if(i!= -1)
                        {
                            s = s.split("\\.")[0];
                        }

                        String ss = s1[9];
                        Integer ii = ss.indexOf(".");
                        if(ii!= -1)
                        {
                            ss = ss.split("\\.")[0];
                        }

                        T_MK_CUST_CERT_INFO t_mk_cust_cert_info = new T_MK_CUST_CERT_INFO(s1[0],s1[1],s1[2],s1[3],s1[4],s1[5],
                                s1[6],s1[7],s1[8],ss,s1[10],s1[11],s1[12],s1[13],s1[14],s1[15],s1[16],s1[17],s,s1[19],s1[20]);
                        t_mk_cust_cert_info.convertNull();
                        return t_mk_cust_cert_info;
                    }
                });
        Dataset<Row> T_MK_CUST_CERT_INFO_DF = spark.createDataFrame(T_MK_CUST_CERT_INFO, T_MK_CUST_CERT_INFO.class);
        T_MK_CUST_CERT_INFO_DF.createOrReplaceTempView("T_MK_CUST_CERT_INFO");
        T_MK_CUST_CERT_INFO_DF.cache();
        hm.put("T_MK_CUST_CERT_INFO",true);
    }
    public static void loadT_SMSPLTFORM_SNED_DATA(SparkSession spark)
    {
        logger.info("开始加载T_SMSPLTFORM_SNED_DATA...");

        Encoder<T_SMSPLTFORM_SNED_DATA> t_mk_cust_buried_point_infoEncoder = Encoders.javaSerialization(T_SMSPLTFORM_SNED_DATA.class);
        Dataset<T_SMSPLTFORM_SNED_DATA> T_SMSPLTFORM_SNED_DATA  = spark.read().textFile("hdfs://emr-header-1.cluster-55030:9000/user/hadoop/pcl.T_SMSPLTFORM_SNED_DATA/add/2018_03_28")
                .map(new MapFunction<String, T_SMSPLTFORM_SNED_DATA>()
                {
                    @Override
                    public T_SMSPLTFORM_SNED_DATA call(String value) throws Exception
                    {
                        String [] s1 = value.split("\\@\\|\\^");
                        String s = s1[6];
                        Integer i = s.indexOf(".");
                        if(i!= -1)
                        {
                            s = s.split("\\.")[0];
                        }
                        T_SMSPLTFORM_SNED_DATA t_smspltform_sned_data = new T_SMSPLTFORM_SNED_DATA(s1[0],s1[1],s1[2],s1[3],s1[4],s1[5],s,s1[7]);
                        t_smspltform_sned_data.convertNull();
                        return t_smspltform_sned_data;
                    }
                },t_mk_cust_buried_point_infoEncoder);
        JavaRDD<T_SMSPLTFORM_SNED_DATA> T_SMSPLTFORM_SNED_DATA_RDD = T_SMSPLTFORM_SNED_DATA.toJavaRDD();
        Dataset<Row> T_SMSPLTFORM_SNED_DATA_DF = spark.createDataFrame(T_SMSPLTFORM_SNED_DATA_RDD, T_SMSPLTFORM_SNED_DATA.class);
        T_SMSPLTFORM_SNED_DATA_DF.createOrReplaceTempView("T_SMSPLTFORM_SNED_DATA");

        T_SMSPLTFORM_SNED_DATA_DF.cache();
        hm.put("T_SMSPLTFORM_SNED_DATA",true);

    }
    public static void loadCUST_CHAN_RELATION(SparkSession spark)
    {
        logger.info("开始加载CUST_CHAN_RELATION...");
        SparkContext sc = spark.sparkContext();
        JavaSparkContext jsc = new JavaSparkContext(sc);
        JavaRDD<CUST_CHAN_RELATION> CUST_CHAN_RELATION = jsc.
                textFile("hdfs://emr-header-1.cluster-55030:9000/ECIF/PCL.CUST_CHAN_RELATION")
                .map(new Function<String, CUST_CHAN_RELATION>()
                {
                    @Override
                    public CUST_CHAN_RELATION call(String v1) throws Exception
                    {
                        String [] s1 = v1.split(",");
                        CUST_CHAN_RELATION cust_chan_relation = new CUST_CHAN_RELATION(s1[0],s1[1],s1[2],s1[3],s1[4]);
                        cust_chan_relation.convertNull();
                        return cust_chan_relation;
                    }
                });
        Dataset<Row> CUST_CHAN_RELATION_DF = spark.createDataFrame(CUST_CHAN_RELATION, CUST_CHAN_RELATION.class);
        CUST_CHAN_RELATION_DF.createOrReplaceTempView("CUST_CHAN_RELATION");
        CUST_CHAN_RELATION_DF.cache();
        hm.put("CUST_CHAN_RELATION",true);
    }
    public static void loadT_CM_CUST_REGISTRY(SparkSession spark)
    {
        logger.info("开始加载T_CM_CUST_REGISTRY...");
        SparkContext sc = spark.sparkContext();
        JavaSparkContext jsc = new JavaSparkContext(sc);
        JavaRDD<T_CM_CUST_REGISTRY> T_CM_CUST_REGISTRY = jsc.
                textFile("hdfs://emr-header-1.cluster-55030:9000/user/hadoop/pcl.WCHAT.T_CM_CUST_REGISTRY/add/2018_04_02")
                .map(new Function<String, T_CM_CUST_REGISTRY>()
                {
                    @Override
                    public T_CM_CUST_REGISTRY call(String v1) throws Exception
                    {
                        String [] s1 = v1.split("\\@\\|\\^");
                        T_CM_CUST_REGISTRY t_cm_cust_registry = new T_CM_CUST_REGISTRY(s1[0],s1[1],s1[2],s1[3],s1[4],s1[5],s1[6],s1[7],s1[8],s1[9],s1[10]);
                        t_cm_cust_registry.convertNull();
                        return t_cm_cust_registry;
                    }
                });
        Dataset<Row> T_CM_CUST_REGISTRY_DF = spark.createDataFrame(T_CM_CUST_REGISTRY, T_CM_CUST_REGISTRY.class);
        T_CM_CUST_REGISTRY_DF.createOrReplaceTempView("T_CM_CUST_REGISTRY");
        T_CM_CUST_REGISTRY_DF.cache();
        hm.put("T_CM_CUST_REGISTRY",true);
    }
    public static void loadT_WE_USER_INFO(SparkSession spark)
    {
        logger.info("开始加载T_WE_USER_INFO...");
        SparkContext sc = spark.sparkContext();
        JavaSparkContext jsc = new JavaSparkContext(sc);
        JavaRDD<T_WE_USER_INFO> T_WE_USER_INFO = jsc.
                textFile("hdfs://emr-header-1.cluster-55030:9000/user/hadoop/pcl.WCHAT.T_WE_USER_INFO/add/2018_04_02")
                .map(new Function<String, T_WE_USER_INFO>()
                {
                    @Override
                    public T_WE_USER_INFO call(String v1) throws Exception
                    {
                        String [] s1 = v1.split("\\@\\|\\^");
                        T_WE_USER_INFO t_we_user_info = new T_WE_USER_INFO(s1[0],s1[1],s1[2],s1[3],s1[4],s1[5],
                                s1[6],s1[7],s1[8],s1[9],s1[10],s1[11],s1[12],s1[13],s1[14],s1[15],s1[16],s1[17],s1[18],s1[19],s1[20]);
                        t_we_user_info.convertNull();
                        return t_we_user_info;
                    }
                });
        Dataset<Row> T_WE_USER_INFO_DF = spark.createDataFrame(T_WE_USER_INFO, T_WE_USER_INFO.class);
        T_WE_USER_INFO_DF.createOrReplaceTempView("T_WE_USER_INFO");
        T_WE_USER_INFO_DF.cache();
        hm.put("T_WE_USER_INFO",true);
    }
    public static void loadT_MK_CUST_BURIED_POINT_INFO(SparkSession spark)
    {
        logger.info("开始加载T_MK_CUST_BURIED_POINT_INFO...");
        Encoder<T_MK_CUST_BURIED_POINT_INFO> t_mk_cust_buried_point_infoEncoder = Encoders.javaSerialization(T_MK_CUST_BURIED_POINT_INFO.class);
        Dataset<T_MK_CUST_BURIED_POINT_INFO> T_MK_CUST_BURIED_POINT_INFO  = spark.read().textFile("hdfs://emr-header-1.cluster-55030:9000/user/hadoop/pcl.WCHAT.T_MK_CUST_BURIED_POINT_INFO/add/2018_04_02")
                .map(new MapFunction<String, T_MK_CUST_BURIED_POINT_INFO>()
                {
                    @Override
                    public T_MK_CUST_BURIED_POINT_INFO call(String value) throws Exception
                    {
                        String [] s1 = value.split("\\@\\|\\^");
                        String s = s1[7];
                        Integer i = s.indexOf(".");
                        if(i!= -1)
                        {
                            s = s.split("\\.")[0];
                        }
                        T_MK_CUST_BURIED_POINT_INFO t_mk_cust_buried_point_info = new T_MK_CUST_BURIED_POINT_INFO(s1[0],s1[1],s1[2],s1[3],s1[4]
                                ,s1[5],s1[6],s,s1[8]);
                        t_mk_cust_buried_point_info.convertNull();
                        return t_mk_cust_buried_point_info;
                    }
                },t_mk_cust_buried_point_infoEncoder);

        JavaRDD<T_MK_CUST_BURIED_POINT_INFO> T_MK_CUST_BURIED_POINT_INFO_RDD = T_MK_CUST_BURIED_POINT_INFO.toJavaRDD();
        Dataset<Row> T_MK_CUST_BURIED_POINT_INFO_DF = spark.createDataFrame(T_MK_CUST_BURIED_POINT_INFO_RDD, T_MK_CUST_BURIED_POINT_INFO.class);
        T_MK_CUST_BURIED_POINT_INFO_DF.createOrReplaceTempView("T_MK_CUST_BURIED_POINT_INFO");

        T_MK_CUST_BURIED_POINT_INFO_DF.cache();
        hm.put("T_MK_CUST_BURIED_POINT_INFO",true);
    }
    public static void loadSMS_TELSALE_CUSTSALERESULT(SparkSession spark)
    {
        logger.info("开始加载SMS_TELSALE_CUSTSALERESULT...");
        Encoder<SMS_TELSALE_CUSTSALERESULT> sms_telsale_custsaleresultEncoder = Encoders.javaSerialization(SMS_TELSALE_CUSTSALERESULT.class);
        Dataset<SMS_TELSALE_CUSTSALERESULT> SMS_TELSALE_CUSTSALERESULT = spark.read().textFile("hdfs://emr-header-1.cluster-55030:9000/user/hadoop/pcl.ODSSYNC.SMS_TELSALE_CUSTSALERESULT/add/2018_04_02")
                .map(new MapFunction<String, SMS_TELSALE_CUSTSALERESULT>()
                {
                    @Override
                    public SMS_TELSALE_CUSTSALERESULT call(String value) throws Exception
                    {
                        String [] s1 = value.split("\\@\\|\\^");
                        String s = s1[7];
                        Integer i = s.indexOf(".");
                        if(i!= -1)
                        {
                            s = s.split("\\.")[0];
                        }
                        SMS_TELSALE_CUSTSALERESULT sms_telsale_custsaleresult = new SMS_TELSALE_CUSTSALERESULT(s1[0],s1[1],s1[2],s1[3],s1[4]
                                ,s1[5],s1[6],s,s1[8],s1[9],s1[10],s1[11],s1[12]);
                        sms_telsale_custsaleresult.convertNull();
                        return sms_telsale_custsaleresult;
                    }
                },sms_telsale_custsaleresultEncoder);

        JavaRDD<SMS_TELSALE_CUSTSALERESULT> SMS_TELSALE_CUSTSALERESULT_RDD = SMS_TELSALE_CUSTSALERESULT.toJavaRDD();
        Dataset<Row> SMS_TELSALE_CUSTSALERESULT_DF = spark.createDataFrame(SMS_TELSALE_CUSTSALERESULT_RDD, SMS_TELSALE_CUSTSALERESULT.class);
        SMS_TELSALE_CUSTSALERESULT_DF.createOrReplaceTempView("SMS_TELSALE_CUSTSALERESULT");

        SMS_TELSALE_CUSTSALERESULT_DF.cache();
        hm.put("SMS_TELSALE_CUSTSALERESULT",true);
    }
    public static void loadTASKCUSTOMER(SparkSession spark)
    {
        logger.info("开始加载TASKCUSTOMER...");
        Encoder<TASKCUSTOMER> taskcustomerEncoder = Encoders.javaSerialization(TASKCUSTOMER.class);
        Dataset<TASKCUSTOMER> TASKCUSTOMER = spark.read().textFile("hdfs://emr-header-1.cluster-55030:9000/user/hadoop/pcl.ODSSYNC.TASKCUSTOMER/add/2018_04_02")
                .map(new MapFunction<String, TASKCUSTOMER>()
                {
                    @Override
                    public TASKCUSTOMER call(String value) throws Exception
                    {
                        String [] s1 = value.split("\\@\\|\\^");
                        TASKCUSTOMER taskcustomer = new TASKCUSTOMER(s1[0],s1[1],s1[2],s1[3],s1[4],s1[5]);
                        taskcustomer.convertNull();
                        return taskcustomer;
                    }
                },taskcustomerEncoder);
        JavaRDD<TASKCUSTOMER> TASKCUSTOMER_RDD = TASKCUSTOMER.toJavaRDD();
        Dataset<Row> TASKCUSTOMER_DF = spark.createDataFrame(TASKCUSTOMER_RDD, TASKCUSTOMER.class);
        TASKCUSTOMER_DF.createOrReplaceTempView("TASKCUSTOMER");

        TASKCUSTOMER_DF.cache();
        hm.put("TASKCUSTOMER",true);
    }
    public static void loadT_CASH_CUST_INFO(SparkSession spark)
    {
        logger.info("开始加载T_CASH_CUST_INFO...");
        Encoder<T_CASH_CUST_INFO> t_cash_cust_infoEncoder = Encoders.javaSerialization(T_CASH_CUST_INFO.class);
        Dataset<T_CASH_CUST_INFO> T_CASH_CUST_INFO = spark.read().textFile("hdfs://emr-header-1.cluster-55030:9000/user/hadoop/pcl.T_CASH_CUST_INFO/all/2018_04_02")
                .map(new MapFunction<String, T_CASH_CUST_INFO>()
                {
                    @Override
                    public T_CASH_CUST_INFO call(String value) throws Exception
                    {
                        String [] s1 = value.split("\\@\\|\\^");
                        T_CASH_CUST_INFO t_cash_cust_info = new T_CASH_CUST_INFO(s1[0],s1[1],s1[2],s1[3],
                                s1[4].equals("null")?0:Integer.parseInt(s1[4]),s1[5].equals("null")?0:Integer.parseInt(s1[5]),s1[6],s1[7],s1[8],s1[9]
                                ,s1[10],s1[11],s1[12],
                                s1[13].equals("null")?0.0:Double.parseDouble(s1[13]),
                                s1[14].equals("null")?0.0:Double.parseDouble(s1[14]),
                                s1[15],s1[16],s1[17],s1[18],s1[19],s1[20],s1[21],s1[22],s1[23],s1[24]);
                        t_cash_cust_info.convertNull();
                        return t_cash_cust_info;
                    }
                },t_cash_cust_infoEncoder);


        JavaRDD<T_CASH_CUST_INFO> T_CASH_CUST_INFO_RDD = T_CASH_CUST_INFO.toJavaRDD();
        Dataset<Row> T_CASH_CUST_INFO_DF = spark.createDataFrame(T_CASH_CUST_INFO_RDD, T_CASH_CUST_INFO.class);
        T_CASH_CUST_INFO_DF.createOrReplaceTempView("T_CASH_CUST_INFO");

        T_CASH_CUST_INFO_DF.cache();
        hm.put("T_CASH_CUST_INFO",true);
    }
    public static void loadT_CUST_CREDIT_INFO(SparkSession spark)
    {
        logger.info("开始加载T_CUST_CREDIT_INFO...");
        Encoder<T_CUST_CREDIT_INFO> t_cust_credit_infoEncoder = Encoders.javaSerialization(T_CUST_CREDIT_INFO.class);
        Dataset<T_CUST_CREDIT_INFO> T_CUST_CREDIT_INFO = spark.read().textFile("hdfs://emr-header-1.cluster-55030:9000/user/hadoop/pcl.T_CUST_CREDIT_INFO/all/2018_04_02")
                .map(new MapFunction<String, T_CUST_CREDIT_INFO>()
                {
                    @Override
                    public T_CUST_CREDIT_INFO call(String value) throws Exception
                    {
                        String [] s1 = value.split("\\@\\|\\^");
                        String s = s1[10];
                        Integer i = s.indexOf(".");
                        if(i!= -1)
                        {
                            s = s.split("\\.")[0];
                        }
                        T_CUST_CREDIT_INFO t_cust_credit_info = new T_CUST_CREDIT_INFO(s1[0],s1[1],s1[2],s1[3],s1[4],s1[5],
                                s1[6].equals("null")?0.0:Double.parseDouble(s1[6])
                                ,s1[7].equals("null")?0.0:Double.parseDouble(s1[7]),
                                s1[8].equals("null")?0.0:Double.parseDouble(s1[8]),s1[9],s,s1[11]);
                        t_cust_credit_info.convertNull();
                        return t_cust_credit_info;
                    }
                },t_cust_credit_infoEncoder);

        JavaRDD<T_CUST_CREDIT_INFO> T_CUST_CREDIT_INFO_RDD = T_CUST_CREDIT_INFO.toJavaRDD();
        Dataset<Row> T_CUST_CREDIT_INFO_DF = spark.createDataFrame(T_CUST_CREDIT_INFO_RDD, T_CUST_CREDIT_INFO.class);
        T_CUST_CREDIT_INFO_DF.createOrReplaceTempView("T_CUST_CREDIT_INFO");
        T_CUST_CREDIT_INFO_DF.cache();
        hm.put("T_CUST_CREDIT_INFO",true);
    }
    public static void loadT_CUST_CREDIT_DETAIL (SparkSession spark)
    {
        logger.info("开始加载T_CUST_CREDIT_DETAIL...");
        Encoder<T_CUST_CREDIT_DETAIL> t_cust_credit_detailEncoder = Encoders.javaSerialization(T_CUST_CREDIT_DETAIL.class);
        Dataset<T_CUST_CREDIT_DETAIL> T_CUST_CREDIT_DETAIL = spark.read().textFile("hdfs://emr-header-1.cluster-55030:9000/user/hadoop/pcl.T_CUST_CREDIT_DETAIL/all/2018_04_02")
                .map(new MapFunction<String, T_CUST_CREDIT_DETAIL>()
                {
                    @Override
                    public T_CUST_CREDIT_DETAIL call(String value) throws Exception
                    {
                        String [] s1 = value.split("\\@\\|\\^");
                        String s = s1[6];
                        Integer i = s.indexOf(".");
                        if(i!= -1)
                        {
                            s = s.split("\\.")[0];
                        }
                        String ss = s1[7];
                        Integer i1 = ss.indexOf(".");
                        if(i1!= -1)
                        {
                            ss = ss.split("\\.")[0];
                        }
                        T_CUST_CREDIT_DETAIL t_cust_credit_detail= new T_CUST_CREDIT_DETAIL(s1[0],s1[1],s1[2],
                                s1[3].equals("null")?0.0:Double.parseDouble(s1[3]),
                                s1[4].equals("null")?0.0:Double.parseDouble(s1[4]),
                                s1[5].equals("null")?0.0:Double.parseDouble(s1[5]),
                                s,ss,s1[8],s1[9],s1[10],s1[11],s1[12]);
                        t_cust_credit_detail.convertNull();
                        return t_cust_credit_detail;
                    }
                },t_cust_credit_detailEncoder);
        JavaRDD<T_CUST_CREDIT_DETAIL> T_CUST_CREDIT_DETAIL_RDD = T_CUST_CREDIT_DETAIL.toJavaRDD();
        Dataset<Row> T_CUST_CREDIT_DETAIL_DF = spark.createDataFrame(T_CUST_CREDIT_DETAIL_RDD, T_CUST_CREDIT_DETAIL.class);
        T_CUST_CREDIT_DETAIL_DF.createOrReplaceTempView("T_CUST_CREDIT_DETAIL");
        T_CUST_CREDIT_DETAIL_DF.cache();
        hm.put("T_CUST_CREDIT_DETAIL",true);
    }


}
