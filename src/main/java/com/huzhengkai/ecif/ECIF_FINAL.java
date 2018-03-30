package com.huzhengkai.ecif;

import com.huzhengkai.ecif.bean.*;
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
import org.apache.spark.sql.catalyst.expressions.GenericRow;
import scala.Tuple2;

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
        hm.put("T_CUST_CREDIT_DETAIL ",false);
        hm.put("I_CUST_CONV",false);
        hm.put("LATENT_CUST_BASE_INFO",false);
        hm.put("LATENT_CUST_BASE_INFO",false);
    }

    public static void main(String[] args)
    {
        SparkSession spark = SparkSession
                .builder()
                .appName("ECIF_HZK")
                .getOrCreate();
        exportECIF_CUST_WECHAT_REG(spark);
        spark.sparkContext().setLogLevel("ERROR");
    }
    //客户短信记录
    //ECIF_CUST_CONTACT_INFO表未导入，暂时不能做
    public static void exportECIF_CUST_MESSAGE(SparkSession spark)
    {

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
        Dataset<Row> df3 = spark.sql("SELECT " +
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
        Dataset<Row> df5 = spark.sql("SELECT " +
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


        Dataset<Row> dfFinal = df1.union(df3).union(df5);
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
                "JOIN CUST_CHAN_RELATION n ON m.cid = n.chan_id " +
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
        boolean t3 = hm.get("T_CUST_CREDIT_DETAIL");
        if(!t3)
        {
            loadT_CUST_CREDIT_DETAIL(spark);
        }
        Dataset<Row> df = spark.sql("SELECT " +
                "z.table_key,"+
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
        //这里还需要对多产品，进行拼接
        JavaRDD<Row> rdd = df.toJavaRDD();
        JavaPairRDD<String, ECIF_CUST_CREDIT_INFO_OLD> rdd1 = rdd.mapToPair(new PairFunction<Row, String, ECIF_CUST_CREDIT_INFO_OLD>()
        {
            @Override
            public Tuple2<String, ECIF_CUST_CREDIT_INFO_OLD> call(Row row) throws Exception
            {
                ECIF_CUST_CREDIT_INFO_OLD ecif_cust_credit_info_old = new ECIF_CUST_CREDIT_INFO_OLD(row.getString(0),row.getString(1),row.getString(2),row.getString(3),row.getString(4),row.getString(5),row.getString(6),row.getString(7),row.getString(8)
                        ,Double.parseDouble(row.getString(9)),Double.parseDouble(row.getString(10)),Double.parseDouble(row.getString(11)),row.getString(12),row.getString(13));
                return new Tuple2<String, ECIF_CUST_CREDIT_INFO_OLD>(row.getString(4),ecif_cust_credit_info_old);
            }
        });
        JavaPairRDD<String, ECIF_CUST_CREDIT_INFO_OLD> rdd2 = rdd1.reduceByKey(new Function2<ECIF_CUST_CREDIT_INFO_OLD, ECIF_CUST_CREDIT_INFO_OLD, ECIF_CUST_CREDIT_INFO_OLD>()
        {
            @Override
            public ECIF_CUST_CREDIT_INFO_OLD call(ECIF_CUST_CREDIT_INFO_OLD v1, ECIF_CUST_CREDIT_INFO_OLD v2) throws Exception
            {
                //多产品逗号拼接
                ECIF_CUST_CREDIT_INFO_OLD ec = new ECIF_CUST_CREDIT_INFO_OLD(v1.getID(),v1.getCREDIT_ID(),v1.getECIF_CUST_NO(),
                        v1.getCERT_TYPE(),v1.getCERT_NO(),v1.getCUST_NAME(),v1.getPHONE_NO(),v1.getCREDIT_PROD()+","+v2.getCREDIT_PROD(),v1.getCREDIT_LEVEL(),
                        v1.getCREDIT_AMT(),v1.getCREDIT_USED_AMT(),v1.getCREDIT_REMAIN_AMT(),v1.getCREDIT_STATUS(),v1.getCREDIT_DATE());
                return ec;
            }
        });
        JavaRDD<ECIF_CUST_CREDIT_INFO_OLD> rdd3 = rdd2.map(new Function<Tuple2<String,ECIF_CUST_CREDIT_INFO_OLD>, ECIF_CUST_CREDIT_INFO_OLD>()
        {
            @Override
            public ECIF_CUST_CREDIT_INFO_OLD call(Tuple2<String, ECIF_CUST_CREDIT_INFO_OLD> v1) throws Exception
            {
                return v1._2;
            }
        });
        Dataset<Row> ds = spark.createDataFrame(rdd3, ECIF_CUST_CREDIT_INFO_OLD.class);

        ds.write().format("org.apache.phoenix.spark")
                .option("table","ECIF_CUST_CREDIT_INFO_OLD")
                .option("zkUrl","hb-bp1w9r77987gze6u8-001.hbase.rds.aliyuncs.com,hb-bp1w9r77987gze6u8-002.hbase.rds.aliyuncs.com,hb-bp1w9r77987gze6u8-003.hbase.rds.aliyuncs.com:2181");

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
                "ff.table_key, " +
                "ff.credit_id, " +
                "hh.ecif_cust_no, " +
                "ff.prod_type, " +
                "gg.credit_total_amt, " +
                "gg.credit_used_amt, " +
                "gg.credit_remain_amt, " +
                "gg.state, " +
                "ff.credit_beg_date, " +
                "ff.credit_end_date " +
                "FROM " +
                "PCL.T_CUST_CREDIT_DETAIL ff " +
                "JOIN PCL.T_CUST_CREDIT_INFO gg ON ff.credit_id = gg.credit_id " +
                "JOIN PCL.I_CUST_CONV hh ON hh.cert_no = gg.cert_no");
        Encoder<ECIF_CUST_CREDIT_DETAIL_OLD> ecif_cust_credit_detail_oldEncoder = Encoders.javaSerialization(ECIF_CUST_CREDIT_DETAIL_OLD.class);
        Dataset<ECIF_CUST_CREDIT_DETAIL_OLD> df1 = df.map(new MapFunction<Row, ECIF_CUST_CREDIT_DETAIL_OLD>()
        {
            @Override
            public ECIF_CUST_CREDIT_DETAIL_OLD call(Row value) throws Exception
            {
                ECIF_CUST_CREDIT_DETAIL_OLD ecif_cust_credit_detail_old = new ECIF_CUST_CREDIT_DETAIL_OLD(value.getString(0),value.getString(1),value.getString(2),value.getString(3),value.getString(4),
                        value.getDouble(5),value.getDouble(6),value.getDouble(7),value.getString(8),value.getString(9));
                return ecif_cust_credit_detail_old;
            }
        },ecif_cust_credit_detail_oldEncoder);
        df1.write().format("org.apache.phoenix.spark")
                .option("table","ECIF_CUST_CREDIT_DETAIL_OLD")
                .option("zkUrl","hb-bp1w9r77987gze6u8-001.hbase.rds.aliyuncs.com,hb-bp1w9r77987gze6u8-002.hbase.rds.aliyuncs.com,hb-bp1w9r77987gze6u8-003.hbase.rds.aliyuncs.com:2181");

    }
    //潜在客户微信公众号注册信息
    public static void exportLATENT_CUST_WECHAT_REG(SparkSession spark)
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
                "g.id, "+
                "xx.latent_cust_no, " +
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
                "JOIN LATENT_CUST_BASE_INFO xx ON xx.cert_no = d.cert_no "+
                "WHERE " +
                "e.chan_type = '01' " +
                "AND d.cert_type = '50100001' " +
                "AND g.subscribe = '67000001' " +
                "AND f.id_card IS NOT NULL "+
                "AND e.customer_id is null");
        Encoder<LATENT_ECIF_CUST_WECHAT_REG> latent_ecif_cust_wechat_regEncoder = Encoders.javaSerialization(LATENT_ECIF_CUST_WECHAT_REG.class);
        Dataset<LATENT_ECIF_CUST_WECHAT_REG> df2 = df1.map(new MapFunction<Row, LATENT_ECIF_CUST_WECHAT_REG>()
        {
            @Override
            public LATENT_ECIF_CUST_WECHAT_REG call(Row value) throws Exception
            {
                LATENT_ECIF_CUST_WECHAT_REG latent_ecif_cust_wechat_reg = new LATENT_ECIF_CUST_WECHAT_REG(value.getString(0),value.getString(1),
                        value.getString(2),value.getString(3),value.getString(4),value.getString(5),
                        "E1001003",value.getString(6),value.getString(7),value.getString(8),value.getString(9),
                        value.getString(10));

                return latent_ecif_cust_wechat_reg;
            }
        },latent_ecif_cust_wechat_regEncoder);
        //注册
        Dataset<Row> df3 = spark.sql("SELECT " +
                "j.id, " +
                "xx.latent_cust_no, " +
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
                "JOIN LATENT_CUST_BASE_INFO xx ON xx.cert_no = i.cust_cert_no "+
                "WHERE " +
                "h.id_card IS NULL " +
                "AND i.chan_type = '01' " +
                "AND j.subscribe = '67000001'"+
                "AND i.customer_id is null");
        Dataset<LATENT_ECIF_CUST_WECHAT_REG> df4 = df3.map(new MapFunction<Row, LATENT_ECIF_CUST_WECHAT_REG>()
        {
            @Override
            public LATENT_ECIF_CUST_WECHAT_REG call(Row value) throws Exception
            {
                LATENT_ECIF_CUST_WECHAT_REG latent_ecif_cust_wechat_reg = new LATENT_ECIF_CUST_WECHAT_REG(value.getString(0),value.getString(1),
                        value.getString(2),value.getString(3),null,value.getString(4),"E1001002",value.getString(5),
                        value.getString(6),value.getString(7),null,null);
                return latent_ecif_cust_wechat_reg;
            }
        },latent_ecif_cust_wechat_regEncoder);
        //微信关注的潜在客户取不到，没有身份证号信息

        //得到最终所有的数据
        Dataset<LATENT_ECIF_CUST_WECHAT_REG> dfFinal = df2.union(df4);
        dfFinal.write().format("org.apache.phoenix.spark")
                .option("table","LATENT_ECIF_CUST_WECHAT_REG")
                .option("zkUrl","hb-bp1w9r77987gze6u8-001.hbase.rds.aliyuncs.com,hb-bp1w9r77987gze6u8-002.hbase.rds.aliyuncs.com,hb-bp1w9r77987gze6u8-003.hbase.rds.aliyuncs.com:2181");

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
                "o.id, "+
                "xx.latent_cust_no, " +
                "m.channel_type, " +
                "o.opend_id, " +
                "m.point_type, " +
                "m.page_code, " +
                "to_char (m.insert_date, 'yyyy-mm-dd'), " +
                "m.insert_date " +
                "FROM " +
                "T_MK_CUST_BURIED_POINT_INFO m " +
                "JOIN CUST_CHAN_RELATION n ON m.cid = n.chan_id " +
                "JOIN T_CM_CUST_REGISTRY o ON o.cid = m.cid " +
                "JOIN LATENT_CUST_BASE_INFO xx ON xx.cert_no = n.cust_cert_no "+
                "where n.customer_id is null");
        Encoder<LATENT_ECIF_CUST_WECHAT_POINT> latent_ecif_cust_wechat_pointEncoder = Encoders.javaSerialization(LATENT_ECIF_CUST_WECHAT_POINT.class);
        Dataset<LATENT_ECIF_CUST_WECHAT_POINT> df1 = df.map(new MapFunction<Row, LATENT_ECIF_CUST_WECHAT_POINT>()
        {
            @Override
            public LATENT_ECIF_CUST_WECHAT_POINT call(Row value) throws Exception
            {
                LATENT_ECIF_CUST_WECHAT_POINT latent_ecif_cust_wechat_point = new LATENT_ECIF_CUST_WECHAT_POINT(value.getString(0),value.getString(1),value.getString(2),value.getString(3),value.getString(4)
                        ,value.getString(5),value.getString(6),value.getDate(7),null,null);
                return latent_ecif_cust_wechat_point;
            }
        },latent_ecif_cust_wechat_pointEncoder);

        df1.write().format("org.apache.phoenix.spark")
                .option("table","LATENT_ECIF_CUST_WECHAT_POINT")
                .option("zkUrl","hb-bp1w9r77987gze6u8-001.hbase.rds.aliyuncs.com,hb-bp1w9r77987gze6u8-002.hbase.rds.aliyuncs.com,hb-bp1w9r77987gze6u8-003.hbase.rds.aliyuncs.com:2181");

    }
    //潜在客户短信记录
    public static void exportLATENT_ECIF_CUST_MESSAGE(SparkSession spark)
    {

    }
    //潜在客户营销结果表
    public static void exportLATENT_ECIF_CUST_MKTG_RESULT(SparkSession spark)
    {

    }

    //加载潜在客户联系信息
    public static void loadLATENT_CUST_CONTACT_INFO(SparkSession spark)
    {
        logger.info("开始加载LATENT_CUST_CONTACT_INFO...");
        Encoder<LATENT_CUST_CONTACT_INFO> latent_cust_contact_infoEncoder = Encoders.javaSerialization(LATENT_CUST_CONTACT_INFO.class);
        Dataset<LATENT_CUST_CONTACT_INFO> LATENT_CUST_BASE_INFO = spark.read().textFile("hdfs://emr-header-1.cluster-55030:9000/user/hadoop/")
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
        LATENT_CUST_BASE_INFO.createOrReplaceTempView("LATENT_CUST_BASE_INFO");
        LATENT_CUST_BASE_INFO.cache();
        hm.put("LATENT_CUST_BASE_INFO",true);

    }

    //加载潜在客户基本表
    public static void loadLATENT_CUST_BASE_INFO(SparkSession spark)
    {
        logger.info("开始加载LATENT_CUST_BASE_INFO...");
        Encoder<LATENT_CUST_BASE_INFO> latent_cust_base_infoEncoder = Encoders.javaSerialization(LATENT_CUST_BASE_INFO.class);
        Dataset<LATENT_CUST_BASE_INFO> LATENT_CUST_BASE_INFO = spark.read().textFile("hdfs://emr-header-1.cluster-55030:9000/user/hadoop/")
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
        LATENT_CUST_BASE_INFO.createOrReplaceTempView("LATENT_CUST_BASE_INFO");
        LATENT_CUST_BASE_INFO.cache();
        hm.put("LATENT_CUST_BASE_INFO",true);

    }

    public static void loadI_CUST_CONV(SparkSession spark)
    {
        logger.info("开始加载I_CUST_CONV...");
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
        I_CUST_CONV.cache();
        hm.put("I_CUST_CONV",true);
    }

    public static void loadT_MK_CUST_CERT_INFO(SparkSession spark)
    {
        logger.info("开始加载T_MK_CUST_CERT_INFO...");
        SparkContext sc = spark.sparkContext();
        JavaSparkContext jsc = new JavaSparkContext(sc);
        JavaRDD<T_MK_CUST_CERT_INFO> T_MK_CUST_CERT_INFO = jsc.
                textFile("hdfs://emr-header-1.cluster-55030:9000/user/hadoop/pcl.WCHAT.T_MK_CUST_CERT_INFO/add/2018_03_29")
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
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                        String [] s1 = value.split("\\@\\|\\^");
                        T_SMSPLTFORM_SNED_DATA t_smspltform_sned_data = new T_SMSPLTFORM_SNED_DATA(s1[0],s1[1],s1[2],s1[3],s1[4],s1[5],sdf.parse(s1[6]),s1[7]);
                        t_smspltform_sned_data.convertNull();
                        return t_smspltform_sned_data;
                    }
                },t_mk_cust_buried_point_infoEncoder);
        T_SMSPLTFORM_SNED_DATA.createOrReplaceTempView("T_SMSPLTFORM_SNED_DATA");
        T_SMSPLTFORM_SNED_DATA.cache();
        hm.put("T_SMSPLTFORM_SNED_DATA",true);

    }
    public static void loadCUST_CHAN_RELATION(SparkSession spark)
    {
        logger.info("开始加载CUST_CHAN_RELATION...");
        SparkContext sc = spark.sparkContext();
        JavaSparkContext jsc = new JavaSparkContext(sc);
        JavaRDD<CUST_CHAN_RELATION> CUST_CHAN_RELATION = jsc.
                textFile("hdfs://emr-header-1.cluster-55030:9000/user/hadoop/pcl.CUST_CHAN_RELATION/all/2018_03_29")
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
        hm.put("CUST_CHAN_RELATION",true);
    }
    public static void loadT_CM_CUST_REGISTRY(SparkSession spark)
    {
        logger.info("开始加载T_CM_CUST_REGISTRY...");
        SparkContext sc = spark.sparkContext();
        JavaSparkContext jsc = new JavaSparkContext(sc);
        JavaRDD<T_CM_CUST_REGISTRY> T_CM_CUST_REGISTRY = jsc.
                textFile("hdfs://emr-header-1.cluster-55030:9000/user/hadoop/pcl.WCHAT.T_CM_CUST_REGISTRY/add/2018_03_29")
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
                textFile("hdfs://emr-header-1.cluster-55030:9000/user/hadoop/pcl.WCHAT.T_WE_USER_INFO/add/2018_03_29")
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
        T_MK_CUST_BURIED_POINT_INFO.cache();
        hm.put("T_MK_CUST_BURIED_POINT_INFO",true);
    }
    public static void loadSMS_TELSALE_CUSTSALERESULT(SparkSession spark)
    {
        logger.info("开始加载SMS_TELSALE_CUSTSALERESULT...");
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
        SMS_TELSALE_CUSTSALERESULT.cache();
        hm.put("SMS_TELSALE_CUSTSALERESULT",true);
    }
    public static void loadTASKCUSTOMER(SparkSession spark)
    {
        logger.info("开始加载TASKCUSTOMER...");
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
        TASKCUSTOMER.cache();
        hm.put("TASKCUSTOMER",true);
    }
    public static void loadT_CASH_CUST_INFO(SparkSession spark)
    {
        logger.info("开始加载T_CASH_CUST_INFO...");
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
        T_CASH_CUST_INFO.cache();
        hm.put("T_CASH_CUST_INFO",true);
    }
    public static void loadT_CUST_CREDIT_INFO(SparkSession spark)
    {
        logger.info("开始加载T_CUST_CREDIT_INFO...");
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
        T_CUST_CREDIT_INFO.cache();
        hm.put("T_CUST_CREDIT_INFO",true);
    }
    public static void loadT_CUST_CREDIT_DETAIL (SparkSession spark)
    {
        logger.info("开始加载T_CUST_CREDIT_DETAIL...");
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
        T_CUST_CREDIT_DETAIL.cache();
        hm.put("T_CUST_CREDIT_DETAIL",true);
    }


}
