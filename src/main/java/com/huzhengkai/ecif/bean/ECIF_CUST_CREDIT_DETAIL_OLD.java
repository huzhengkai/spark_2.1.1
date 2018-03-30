package com.huzhengkai.ecif.bean;

import java.io.Serializable;

/**
 * Created by root on 2018/3/29.
 */
public class ECIF_CUST_CREDIT_DETAIL_OLD implements Serializable
{
    private String ID;
    private String CREDIT_ID;
    private String ECIF_CUST_NO;
    private String CREDIT_PROD;
    private String CREDIT_LEVEL;
    private Double CREDIT_AMT;
    private Double CREDIT_USED_AMT;
    private Double CREDIT_REMAIN_AMT;
    private String CREDIT_START_DATE;
    private String CREDIT_END_DATE;

    public ECIF_CUST_CREDIT_DETAIL_OLD()
    {
    }

    public ECIF_CUST_CREDIT_DETAIL_OLD(String ID, String CREDIT_ID, String ECIF_CUST_NO, String CREDIT_PROD, String CREDIT_LEVEL, Double CREDIT_AMT, Double CREDIT_USED_AMT, Double CREDIT_REMAIN_AMT, String CREDIT_START_DATE, String CREDIT_END_DATE)
    {
        this.ID = ID;
        this.CREDIT_ID = CREDIT_ID;
        this.ECIF_CUST_NO = ECIF_CUST_NO;
        this.CREDIT_PROD = CREDIT_PROD;
        this.CREDIT_LEVEL = CREDIT_LEVEL;
        this.CREDIT_AMT = CREDIT_AMT;
        this.CREDIT_USED_AMT = CREDIT_USED_AMT;
        this.CREDIT_REMAIN_AMT = CREDIT_REMAIN_AMT;
        this.CREDIT_START_DATE = CREDIT_START_DATE;
        this.CREDIT_END_DATE = CREDIT_END_DATE;
    }

    public String getID()
    {
        return ID;
    }

    public void setID(String ID)
    {
        this.ID = ID;
    }

    public String getCREDIT_ID()
    {
        return CREDIT_ID;
    }

    public void setCREDIT_ID(String CREDIT_ID)
    {
        this.CREDIT_ID = CREDIT_ID;
    }

    public String getECIF_CUST_NO()
    {
        return ECIF_CUST_NO;
    }

    public void setECIF_CUST_NO(String ECIF_CUST_NO)
    {
        this.ECIF_CUST_NO = ECIF_CUST_NO;
    }

    public String getCREDIT_PROD()
    {
        return CREDIT_PROD;
    }

    public void setCREDIT_PROD(String CREDIT_PROD)
    {
        this.CREDIT_PROD = CREDIT_PROD;
    }

    public String getCREDIT_LEVEL()
    {
        return CREDIT_LEVEL;
    }

    public void setCREDIT_LEVEL(String CREDIT_LEVEL)
    {
        this.CREDIT_LEVEL = CREDIT_LEVEL;
    }

    public Double getCREDIT_AMT()
    {
        return CREDIT_AMT;
    }

    public void setCREDIT_AMT(Double CREDIT_AMT)
    {
        this.CREDIT_AMT = CREDIT_AMT;
    }

    public Double getCREDIT_USED_AMT()
    {
        return CREDIT_USED_AMT;
    }

    public void setCREDIT_USED_AMT(Double CREDIT_USED_AMT)
    {
        this.CREDIT_USED_AMT = CREDIT_USED_AMT;
    }

    public Double getCREDIT_REMAIN_AMT()
    {
        return CREDIT_REMAIN_AMT;
    }

    public void setCREDIT_REMAIN_AMT(Double CREDIT_REMAIN_AMT)
    {
        this.CREDIT_REMAIN_AMT = CREDIT_REMAIN_AMT;
    }

    public String getCREDIT_START_DATE()
    {
        return CREDIT_START_DATE;
    }

    public void setCREDIT_START_DATE(String CREDIT_START_DATE)
    {
        this.CREDIT_START_DATE = CREDIT_START_DATE;
    }

    public String getCREDIT_END_DATE()
    {
        return CREDIT_END_DATE;
    }

    public void setCREDIT_END_DATE(String CREDIT_END_DATE)
    {
        this.CREDIT_END_DATE = CREDIT_END_DATE;
    }
}
