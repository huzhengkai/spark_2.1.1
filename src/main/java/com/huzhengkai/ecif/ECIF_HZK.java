package com.huzhengkai.ecif;

import com.huzhengkai.ecif.bean.*;
import com.huzhengkai.sql.JavaSparkSQLExample;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.MapFunction;
import org.apache.spark.sql.*;
import scala.Function1;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

/**
 * Created by root on 2018/3/26.
 */
public class ECIF_HZK
{
    private static final Logger logger =   Logger.getLogger(ECIF_HZK.class);
    private static boolean flag = false;
    public static void main(String[] args)
    {
        SparkSession spark = SparkSession
                .builder()
                .appName("ECIF_HZK")
                .getOrCreate();
        exportECIF_CUST_WECHAT_REG(spark);

    }
    //客户微信公众号注册信息
    public static void exportECIF_CUST_WECHAT_REG(SparkSession spark)
    {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd");
//        String date = sdf.format(new Date());
        String date = "2018_03_26";
        SparkContext sc = spark.sparkContext();
        JavaSparkContext jsc = new JavaSparkContext(sc);
        JavaRDD<T_MK_CUST_CERT_INFO> T_MK_CUST_CERT_INFO = jsc.
                textFile("hdfs://emr-header-1.cluster-55030:9000/user/hadoop/pcl.WCHAT.T_MK_CUST_CERT_INFO/add/2018_03_26")
                .map(new Function<String, T_MK_CUST_CERT_INFO>()
                {
                    @Override
                    public T_MK_CUST_CERT_INFO call(String v1) throws Exception
                    {
                        String [] s1 = v1.split("\\@\\|\\^");

                        T_MK_CUST_CERT_INFO t_mk_cust_cert_info = new T_MK_CUST_CERT_INFO(s1[0],s1[1],s1[2],s1[3],s1[4],s1[5],
                                s1[6],s1[7],s1[8],s1[9],s1[10],s1[11],s1[12],s1[13],s1[14],s1[15],s1[16],s1[17],s1[18],s1[19],s1[20]);
                        t_mk_cust_cert_info.convertNull();
                        return t_mk_cust_cert_info;
                    }
                });
        Dataset<Row> T_MK_CUST_CERT_INFO_DF = spark.createDataFrame(T_MK_CUST_CERT_INFO, T_MK_CUST_CERT_INFO.class);
        T_MK_CUST_CERT_INFO_DF.createOrReplaceTempView("T_MK_CUST_CERT_INFO");
        boolean b = false;
        if(!flag)
        {
            b = loadWCHAT_DATA(spark);

        }
        if(b==true)
        {
            flag = true;
        }

        JavaRDD<T_WE_USER_INFO> T_WE_USER_INFO = jsc.
                textFile("hdfs://emr-header-1.cluster-55030:9000/user/hadoop/pcl.WCHAT.T_WE_USER_INFO/add/2018_03_26")
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
        //实名认证
        Dataset<Row> df1 = spark.sql("SELECT " +
                "g.id, "+
                "e.customer_id, " +
                "f.opend_id, " +
                "f.phone_num, " +
                "d.cert_no, " +
                "f.name, " +
                "f.status, " +
                "g.subscribe_time, " +
                "f.insert_date, " +
                "d.insert_date, " +
                "d.valid_end_date " +
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
        Encoder<ECIF_CUST_WECHAT_REG> ecif_cust_wechat_regEncoder = Encoders.javaSerialization(ECIF_CUST_WECHAT_REG.class);
        Dataset<ECIF_CUST_WECHAT_REG> df2 = df1.map(new MapFunction<Row, ECIF_CUST_WECHAT_REG>()
        {
            @Override
            public ECIF_CUST_WECHAT_REG call(Row value) throws Exception
            {
                ECIF_CUST_WECHAT_REG ecif_cust_wechat_reg = new ECIF_CUST_WECHAT_REG(value.getString(0),value.getString(1),
                        value.getString(2),value.getString(3),value.getString(4),value.getString(5),
                        "E1001003",value.getString(6),value.getString(7),value.getString(8),value.getString(9),
                        value.getString(10));

                return ecif_cust_wechat_reg;
            }
        },ecif_cust_wechat_regEncoder);
        //注册
        Dataset<Row> df3 = spark.sql("SELECT " +
                "j.id, " +
                "i.customer_id, " +
                "h.opend_id, " +
                "h.phone_num, " +
                "h.name, " +
                "h.status, " +
                "j.subscribe_time, " +
                "h.insert_date " +
                "FROM " +
                "T_CM_CUST_REGISTRY h " +
                "JOIN CUST_CHAN_RELATION i ON h.cid = i.chan_id " +
                "JOIN T_WE_USER_INFO j ON h.opend_id = j.open_id " +
                "WHERE " +
                "h.id_card IS NULL " +
                "AND i.chan_type = '01' " +
                "AND j.subscribe = '67000001'");
        Dataset<ECIF_CUST_WECHAT_REG> df4 = df3.map(new MapFunction<Row, ECIF_CUST_WECHAT_REG>()
        {
            @Override
            public ECIF_CUST_WECHAT_REG call(Row value) throws Exception
            {
                ECIF_CUST_WECHAT_REG ecif_cust_wechat_reg = new ECIF_CUST_WECHAT_REG(value.getString(0),value.getString(1),
                        value.getString(2),value.getString(3),null,value.getString(4),"E1001002",value.getString(5),
                        value.getString(6),value.getString(7),null,null);
                return ecif_cust_wechat_reg;
            }
        },ecif_cust_wechat_regEncoder);
        //关注
        Dataset<Row> df5 = spark.sql("SELECT " +
                "k.id, "+
                "k.open_id, " +
                "k.subscribe_time " +
                "FROM " +
                "T_WE_USER_INFO k " +
                "JOIN T_CM_CUST_REGISTRY l ON k.open_id <> l.opend_id " +
                "WHERE " +
                "k.subscribe = '67000001'");
        Dataset<ECIF_CUST_WECHAT_REG> df6 = df5.map(new MapFunction<Row, ECIF_CUST_WECHAT_REG>()
        {
            @Override
            public ECIF_CUST_WECHAT_REG call(Row value) throws Exception
            {
                ECIF_CUST_WECHAT_REG ecif_cust_wechat_reg = new ECIF_CUST_WECHAT_REG(value.getString(0),null,value.getString(1),
                        null,null,null,null,null,value.getString(2),null,null,null);
                return null;
            }
        },ecif_cust_wechat_regEncoder);
        //得到最终所有的数据
        Dataset<ECIF_CUST_WECHAT_REG> dfFinal = df2.union(df4).union(df6);
        dfFinal.write().format("org.apache.phoenix.spark")
                .option("table","ECIF_CUST_WECHAT_REG")
                .option("zkUrl","hb-bp1w9r77987gze6u8-001.hbase.rds.aliyuncs.com,hb-bp1w9r77987gze6u8-002.hbase.rds.aliyuncs.com,hb-bp1w9r77987gze6u8-003.hbase.rds.aliyuncs.com:2181");
    }
    //客户微信埋点信息
    public static void exportECIF_CUST_WECHAT_POINT(SparkSession spark)
    {
        Encoder<T_MK_CUST_BURIED_POINT_INFO> t_mk_cust_buried_point_infoEncoder = Encoders.javaSerialization(T_MK_CUST_BURIED_POINT_INFO.class);
        Dataset<T_MK_CUST_BURIED_POINT_INFO> T_MK_CUST_BURIED_POINT_INFO  = spark.read().textFile("hdfs://emr-header-1.cluster-55030:9000/user/hadoop/pcl.WCHAT.T_MK_CUST_BURIED_POINT_INFO/add/2018_03_26")
        .map(new MapFunction<String, T_MK_CUST_BURIED_POINT_INFO>()
        {
            @Override
            public T_MK_CUST_BURIED_POINT_INFO call(String value) throws Exception
            {
                String [] s1 = value.split("\\@\\|\\^");
                T_MK_CUST_BURIED_POINT_INFO t_mk_cust_buried_point_info = new T_MK_CUST_BURIED_POINT_INFO(s1[0],s1[1],s1[2],s1[3],s1[4]
                ,s1[5],s1[6],s1[7],s1[8]);
                t_mk_cust_buried_point_info.convertNull();
                return t_mk_cust_buried_point_info;
            }
        },t_mk_cust_buried_point_infoEncoder);
        T_MK_CUST_BURIED_POINT_INFO.createOrReplaceTempView("T_MK_CUST_BURIED_POINT_INFO");
        //临时表的复用问题
        boolean b = false;
        if(!flag)
        {
            b = loadWCHAT_DATA(spark);

        }
        if(b==true)
        {
            flag = true;
        }

        Dataset<Row> df = spark.sql("SELECT " +
                "o.id, " +
                "n.customer_id, " +
                "m.channel_type, " +
                "o.opend_id, " +
                "m.point_type, " +
                "m.page_code, " +
                "to_char(m.insert_date, 'yyyy-mm-dd'), " +
                "m.insert_date " +
                "FROM " +
                "T_MK_CUST_BURIED_POINT_INFO m " +
                "JOIN cust_chan_relation n ON m.cid = n.chan_id " +
                "JOIN T_CM_CUST_REGISTRY o ON o.cid = m.cid");
        Encoder<ECIF_CUST_WECHAT_POINT> ecif_cust_wechat_pointEncoder = Encoders.javaSerialization(ECIF_CUST_WECHAT_POINT.class);
        Dataset<ECIF_CUST_WECHAT_POINT> df1 = df.map(new MapFunction<Row, ECIF_CUST_WECHAT_POINT>()
        {
            @Override
            public ECIF_CUST_WECHAT_POINT call(Row value) throws Exception
            {
                ECIF_CUST_WECHAT_POINT ecif_cust_wechat_point = new ECIF_CUST_WECHAT_POINT(value.getString(0),value.getString(1),value.getString(2),value.getString(3),value.getString(4)
                ,value.getString(5),value.getString(6),value.getDate(7),null,null);
                return ecif_cust_wechat_point;
            }
        },ecif_cust_wechat_pointEncoder);

        df1.write().format("org.apache.phoenix.spark")
                .option("table","ECIF_CUST_WECHAT_POINT")
                .option("zkUrl","hb-bp1w9r77987gze6u8-001.hbase.rds.aliyuncs.com,hb-bp1w9r77987gze6u8-002.hbase.rds.aliyuncs.com,hb-bp1w9r77987gze6u8-003.hbase.rds.aliyuncs.com:2181");


    }
    //客户营销结果表
    public static void exportECIF_CUST_MKTG_RESULT(SparkSession spark)
    {
        //加载SMS_TELSALE_CUSTSALERESULT表
        Encoder<SMS_TELSALE_CUSTSALERESULT> sms_telsale_custsaleresultEncoder = Encoders.javaSerialization(SMS_TELSALE_CUSTSALERESULT.class);
        Dataset<SMS_TELSALE_CUSTSALERESULT> SMS_TELSALE_CUSTSALERESULT = spark.read().textFile("hdfs://emr-header-1.cluster-55030:9000/user/hadoop/pcl.ODSSYNC.SMS_TELSALE_CUSTSALERESULT/add/2018_03_28")
                .map(new MapFunction<String, SMS_TELSALE_CUSTSALERESULT>()
                {
                    @Override
                    public SMS_TELSALE_CUSTSALERESULT call(String value) throws Exception
                    {
                        String [] s1 = value.split("\\@\\|\\^");
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                        SMS_TELSALE_CUSTSALERESULT sms_telsale_custsaleresult = new SMS_TELSALE_CUSTSALERESULT(s1[0],s1[1],s1[2],s1[3],s1[4]
                        ,s1[5],s1[6],sdf.parse(s1[7]),s1[8],s1[9],s1[10],s1[11],s1[12]);
                        sms_telsale_custsaleresult.convertNull();
                        return sms_telsale_custsaleresult;
                    }
                },sms_telsale_custsaleresultEncoder);
        SMS_TELSALE_CUSTSALERESULT.createOrReplaceTempView("SMS_TELSALE_CUSTSALERESULT");
        //加载TASKCUSTOMER表
        Encoder<TASKCUSTOMER> taskcustomerEncoder = Encoders.javaSerialization(TASKCUSTOMER.class);
        Dataset<TASKCUSTOMER> TASKCUSTOMER = spark.read().textFile("hdfs://emr-header-1.cluster-55030:9000/user/hadoop/pcl.ODSSYNC.TASKCUSTOMER/add/2018_03_28")
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
        TASKCUSTOMER.createOrReplaceTempView("TASKCUSTOMER");
        Dataset<Row> df = spark.sql("SELECT " +
                "p.ecif_cust_no, " +
                "p.cert_no, " +
                "p.cust_name, " +
                "p.tel_no, " +
                "p.result_id, " +
                "q.taskname, " +
                "p.operate_type, " +
                "p.result_state, " +
                "p.remark, " +
                "p.update_userid, " +
                "p.update_username, " +
                "to_char(p.update_time, 'yyyy-mm-dd'), " +
                "p.update_time " +
                "FROM " +
                "SMS_TELSALE_CUSTSALERESULT p " +
                "JOIN TASKCUSTOMER q ON p.task_id = q.taskid " +
                "WHERE " +
                "p.ecif_cust_no IS NOT NULL");
        Encoder<ECIF_CUST_MKTG_RESULT> ecif_cust_mktg_resultEncoder = Encoders.javaSerialization(ECIF_CUST_MKTG_RESULT.class);
        Dataset<ECIF_CUST_MKTG_RESULT> df1 = df.map(new MapFunction<Row, ECIF_CUST_MKTG_RESULT>()
        {
            @Override
            public ECIF_CUST_MKTG_RESULT call(Row value) throws Exception
            {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                ECIF_CUST_MKTG_RESULT ecif_cust_mktg_result = new ECIF_CUST_MKTG_RESULT(value.getString(0),"001001",value.getString(1),value.getString(2),value.getString(3),value.getString(4),value.getString(5),value.getString(6),value.getString(7)
                ,value.getString(8),value.getString(9),value.getString(10),value.getString(11),sdf.parse(value.getString(12)));
                return ecif_cust_mktg_result;
            }
        },ecif_cust_mktg_resultEncoder);
        df1.write().format("org.apache.phoenix.spark")
                .option("table","ECIF_CUST_MKTG_RESULT")
                .option("zkUrl","hb-bp1w9r77987gze6u8-001.hbase.rds.aliyuncs.com,hb-bp1w9r77987gze6u8-002.hbase.rds.aliyuncs.com,hb-bp1w9r77987gze6u8-003.hbase.rds.aliyuncs.com:2181");

        //该方法并没有写完，当客户号不存在时需要关联ECIF_CUST_CONTACT_INFO表，而该表并没有导入完成

    }
    //客户授信白名单信息(老版)
    public static void exportECIF_CUST_WHITE_LIST_OLD(SparkSession spark)
    {
        //加载T_CASH_CUST_INFO表
        Encoder<T_CASH_CUST_INFO> t_cash_cust_infoEncoder = Encoders.javaSerialization(T_CASH_CUST_INFO.class);
        Dataset<T_CASH_CUST_INFO> T_CASH_CUST_INFO = spark.read().textFile("hdfs://emr-header-1.cluster-55030:9000/user/hadoop/pcl.T_CASH_CUST_INFO/all/2018_03_28")
                .map(new MapFunction<String, T_CASH_CUST_INFO>()
                {
                    @Override
                    public T_CASH_CUST_INFO call(String value) throws Exception
                    {
                        String [] s1 = value.split("\\@\\|\\^");
                        T_CASH_CUST_INFO t_cash_cust_info = new T_CASH_CUST_INFO(s1[0],s1[1],s1[2],s1[3],
                                Integer.parseInt(s1[4]),Integer.parseInt(s1[5]),s1[6],s1[7],s1[8],s1[9]
                        ,s1[10],s1[11],s1[12], Double.parseDouble(s1[13]),Double.parseDouble(s1[14]),
                                s1[15],s1[16],s1[17],s1[18],s1[19],s1[20],s1[21],s1[22],s1[23],s1[24]);
                        t_cash_cust_info.convertNull();
                        return t_cash_cust_info;
                    }
                },t_cash_cust_infoEncoder);
        T_CASH_CUST_INFO.createOrReplaceTempView("T_CASH_CUST_INFO");

        //加载I_CUST_CONV表
        Encoder<I_CUST_CONV> i_cust_convEncoder = Encoders.javaSerialization(I_CUST_CONV.class);
        Dataset<I_CUST_CONV> I_CUST_CONV = spark.read().textFile("hdfs://emr-header-1.cluster-55030:9000/user/hadoop/pcl.I_CUST_CONV/all/2018_03_28")
                .map(new MapFunction<String, I_CUST_CONV>()
                {
                    @Override
                    public I_CUST_CONV call(String value) throws Exception
                    {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                        String [] s1 = value.split("\\@\\|\\^");
                        I_CUST_CONV i_cust_conv = new I_CUST_CONV(s1[0],s1[1],s1[2],s1[3],
                                s1[4],s1[5],sdf.parse(s1[6]),s1[7],s1[8],sdf.parse(s1[9]),s1[10]);
                        i_cust_conv.convertNull();
                        return i_cust_conv;
                    }
                },i_cust_convEncoder);
        I_CUST_CONV.createOrReplaceTempView("I_CUST_CONV");
        Dataset<Row> df = spark.sql("SELECT " +
                "dd.ecif_cust_no, " +
                "dd.cert_type, " +
                "cc.cert_no, " +
                "cc.cust_name, " +
                "cc.cust_phone, " +
                "cc.loan_no, " +
                "cc.init_credit_limit, " +
                "cc.credit_limit, " +
                "cc.lvl, " +
                "cc.state, " +
                "cc.type, " +
                "cc.busi_dt, " +
                "cc.invalid_dt, " +
                "cc.is_kx " +
                "FROM " +
                "T_CASH_CUST_INFO cc " +
                "JOIN ( " +
                "SELECT " +
                "max(aa.busi_dt) ee, " +
                "aa.loan_no " +
                "FROM " +
                "T_CASH_CUST_INFO aa " +
                "GROUP BY " +
                "aa.loan_no " +
                ") bb ON cc.busi_dt = bb.ee " +
                "AND cc.loan_no = bb.loan_no " +
                "JOIN I_CUST_CONV dd ON dd.cert_no = cc.cert_no");
        Encoder<ECIF_CUST_WHITE_LIST_OLD> ecif_cust_white_list_oldEncoder = Encoders.javaSerialization(ECIF_CUST_WHITE_LIST_OLD.class);
        Dataset<ECIF_CUST_WHITE_LIST_OLD> df1 = df.map(new MapFunction<Row, ECIF_CUST_WHITE_LIST_OLD>()
        {
            @Override
            public ECIF_CUST_WHITE_LIST_OLD call(Row value) throws Exception
            {
                ECIF_CUST_WHITE_LIST_OLD ecif_cust_white_list_old = new ECIF_CUST_WHITE_LIST_OLD(UUID.randomUUID().toString(),value.getString(0),value.getString(1),value.getString(2),value.getString(3),
                        value.getString(4),value.getString(5),Double.parseDouble(value.getString(6)),Double.parseDouble(value.getString(7)),value.getString(8),value.getString(9),
                        value.getString(10),value.getString(11),value.getString(12),value.getString(13));
                return ecif_cust_white_list_old;
            }
        },ecif_cust_white_list_oldEncoder);
        df1.write().format("org.apache.phoenix.spark")
                .option("table","ECIF_CUST_WHITE_LIST_OLD")
                .option("zkUrl","hb-bp1w9r77987gze6u8-001.hbase.rds.aliyuncs.com,hb-bp1w9r77987gze6u8-002.hbase.rds.aliyuncs.com,hb-bp1w9r77987gze6u8-003.hbase.rds.aliyuncs.com:2181");
    }

    //客户授信信息(老版)
    public static void exportECIF_CUST_CREDIT_INFO_OLD(SparkSession spark)
    {
        //加载I_CUST_CONV表
        Encoder<I_CUST_CONV> i_cust_convEncoder = Encoders.javaSerialization(I_CUST_CONV.class);
        Dataset<I_CUST_CONV> I_CUST_CONV = spark.read().textFile("hdfs://emr-header-1.cluster-55030:9000/user/hadoop/pcl.I_CUST_CONV/all/2018_03_28")
                .map(new MapFunction<String, I_CUST_CONV>()
                {
                    @Override
                    public I_CUST_CONV call(String value) throws Exception
                    {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                        String [] s1 = value.split("\\@\\|\\^");
                        I_CUST_CONV i_cust_conv = new I_CUST_CONV(s1[0],s1[1],s1[2],s1[3],
                                s1[4],s1[5],sdf.parse(s1[6]),s1[7],s1[8],sdf.parse(s1[9]),s1[10]);
                        i_cust_conv.convertNull();
                        return i_cust_conv;
                    }
                },i_cust_convEncoder);
        I_CUST_CONV.createOrReplaceTempView("I_CUST_CONV");

        //加载T_CUST_CREDIT_INFO表
        Encoder<T_CUST_CREDIT_INFO> t_cust_credit_infoEncoder = Encoders.javaSerialization(T_CUST_CREDIT_INFO.class);
        Dataset<T_CUST_CREDIT_INFO> T_CUST_CREDIT_INFO = spark.read().textFile("hdfs://emr-header-1.cluster-55030:9000/user/hadoop/pcl.T_CUST_CREDIT_INFO/all/2018_03_28")
                .map(new MapFunction<String, T_CUST_CREDIT_INFO>()
                {
                    @Override
                    public T_CUST_CREDIT_INFO call(String value) throws Exception
                    {
                        String [] s1 = value.split("\\@\\|\\^");
                        T_CUST_CREDIT_INFO t_cust_credit_info = new T_CUST_CREDIT_INFO(s1[0],s1[1],s1[2],s1[3],s1[4],s1[5],Double.parseDouble(s1[6])
                        ,Double.parseDouble(s1[7]),Double.parseDouble(s1[8]),s1[9],s1[10],s1[11]);
                        t_cust_credit_info.convertNull();
                        return t_cust_credit_info;
                    }
                },t_cust_credit_infoEncoder);
        T_CUST_CREDIT_INFO.createOrReplaceTempView("T_CUST_CREDIT_INFO");

        //加载T_CUST_CREDIT_DETAIL表
        Encoder<T_CUST_CREDIT_DETAIL> t_cust_credit_detailEncoder = Encoders.javaSerialization(T_CUST_CREDIT_DETAIL.class);
        Dataset<T_CUST_CREDIT_DETAIL> T_CUST_CREDIT_DETAIL = spark.read().textFile("hdfs://emr-header-1.cluster-55030:9000/user/hadoop/pcl.T_CUST_CREDIT_DETAIL/all/2018_03_28")
                .map(new MapFunction<String, T_CUST_CREDIT_DETAIL>()
                {
                    @Override
                    public T_CUST_CREDIT_DETAIL call(String value) throws Exception
                    {
                        String [] s1 = value.split("\\@\\|\\^");
                        T_CUST_CREDIT_DETAIL t_cust_credit_detail= new T_CUST_CREDIT_DETAIL();
                        t_cust_credit_detail.convertNull();
                        return t_cust_credit_detail;
                    }
                },t_cust_credit_detailEncoder);
        T_CUST_CREDIT_DETAIL.createOrReplaceTempView("T_CUST_CREDIT_DETAIL");


        Dataset<Row> df = spark.sql("SELECT " +
                "x.credit_id, " +
                "y.ecif_cust_no, " +
                "y.cert_type, " +
                "y.cert_no, " +
                "x.cust_name, " +
                "x.phone_num, " +
                "z.prod_type, " +
                "x.credit_level, " +
                "x.credit_total_amt, " +
                "x.credit_used_amt, " +
                "x.credit_remain_amt, " +
                "x.state, " +
                "z.credit_beg_date " +
                "FROM " +
                "T_CUST_CREDIT_INFO x " +
                "JOIN I_CUST_CONV y ON x.cert_no = y.cert_no " +
                "JOIN T_CUST_CREDIT_DETAIL z ON z.credit_id = x.credit_id");
        Encoder<ECIF_CUST_CREDIT_INFO_OLD> ecif_cust_credit_info_oldEncoder = Encoders.javaSerialization(ECIF_CUST_CREDIT_INFO_OLD.class);
        Dataset<ECIF_CUST_CREDIT_INFO_OLD> df1 = df.map(new MapFunction<Row, ECIF_CUST_CREDIT_INFO_OLD>()
        {
            @Override
            public ECIF_CUST_CREDIT_INFO_OLD call(Row value) throws Exception
            {
                ECIF_CUST_CREDIT_INFO_OLD ecif_cust_credit_info_old = new ECIF_CUST_CREDIT_INFO_OLD();
                return ecif_cust_credit_info_old;
            }
        },ecif_cust_credit_info_oldEncoder);
        df1.write().format("org.apache.phoenix.spark")
                .option("table","ECIF_CUST_CREDIT_INFO_OLD")
                .option("zkUrl","hb-bp1w9r77987gze6u8-001.hbase.rds.aliyuncs.com,hb-bp1w9r77987gze6u8-002.hbase.rds.aliyuncs.com,hb-bp1w9r77987gze6u8-003.hbase.rds.aliyuncs.com:2181");


    }


    public static boolean loadWCHAT_DATA(SparkSession spark)
    {
        boolean b = false;
        try
        {
            SparkContext sc = spark.sparkContext();
            JavaSparkContext jsc = new JavaSparkContext(sc);
            JavaRDD<CUST_CHAN_RELATION> CUST_CHAN_RELATION = jsc.
                    textFile("hdfs://emr-header-1.cluster-55030:9000/user/hadoop/pcl.CUST_CHAN_RELATION/all/2018_03_26")
                    .map(new Function<String, CUST_CHAN_RELATION>()
                    {
                        @Override
                        public CUST_CHAN_RELATION call(String v1) throws Exception
                        {
                            String [] s1 = v1.split("\\@\\|\\^");
                            CUST_CHAN_RELATION cust_chan_relation = new CUST_CHAN_RELATION(s1[0],s1[1],s1[2],s1[3],s1[4]);
                            cust_chan_relation.convertNull();
                            return cust_chan_relation;
                        }
                    });
            Dataset<Row> CUST_CHAN_RELATION_DF = spark.createDataFrame(CUST_CHAN_RELATION, CUST_CHAN_RELATION.class);
            CUST_CHAN_RELATION_DF.createOrReplaceTempView("CUST_CHAN_RELATION");
            CUST_CHAN_RELATION_DF.cache();

            JavaRDD<T_CM_CUST_REGISTRY> T_CM_CUST_REGISTRY = jsc.
                    textFile("hdfs://emr-header-1.cluster-55030:9000/user/hadoop/pcl.WCHAT.T_CM_CUST_REGISTRY/add/2018_03_27")
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
            b = true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return b;
    }


}
