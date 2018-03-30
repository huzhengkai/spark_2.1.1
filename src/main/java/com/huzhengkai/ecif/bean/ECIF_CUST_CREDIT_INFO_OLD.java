package com.huzhengkai.ecif.bean;

import java.io.Serializable;

/**
 * Created by root on 2018/3/28.
 */
public class ECIF_CUST_CREDIT_INFO_OLD implements Serializable
{
    private String ID;
    private String CREDIT_ID;
    private String ECIF_CUST_NO;
    private String CERT_TYPE;
    private String CERT_NO;
    private String CUST_NAME;
    private String PHONE_NO;
    private String CREDIT_PROD;
    private String CREDIT_LEVEL;
    private Double CREDIT_AMT;
    private Double CREDIT_USED_AMT;
    private Double CREDIT_REMAIN_AMT;
    private String CREDIT_STATUS;
    private String CREDIT_DATE;

    public ECIF_CUST_CREDIT_INFO_OLD()
    {
    }

    public ECIF_CUST_CREDIT_INFO_OLD(String ID, String CREDIT_ID, String ECIF_CUST_NO, String CERT_TYPE, String CERT_NO, String CUST_NAME, String PHONE_NO, String CREDIT_PROD, String CREDIT_LEVEL, Double CREDIT_AMT, Double CREDIT_USED_AMT, Double CREDIT_REMAIN_AMT, String CREDIT_STATUS, String CREDIT_DATE)
    {
        this.ID = ID;
        this.CREDIT_ID = CREDIT_ID;
        this.ECIF_CUST_NO = ECIF_CUST_NO;
        this.CERT_TYPE = CERT_TYPE;
        this.CERT_NO = CERT_NO;
        this.CUST_NAME = CUST_NAME;
        this.PHONE_NO = PHONE_NO;
        this.CREDIT_PROD = CREDIT_PROD;
        this.CREDIT_LEVEL = CREDIT_LEVEL;
        this.CREDIT_AMT = CREDIT_AMT;
        this.CREDIT_USED_AMT = CREDIT_USED_AMT;
        this.CREDIT_REMAIN_AMT = CREDIT_REMAIN_AMT;
        this.CREDIT_STATUS = CREDIT_STATUS;
        this.CREDIT_DATE = CREDIT_DATE;
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

    public String getCERT_TYPE()
    {
        return CERT_TYPE;
    }

    public void setCERT_TYPE(String CERT_TYPE)
    {
        this.CERT_TYPE = CERT_TYPE;
    }

    public String getCERT_NO()
    {
        return CERT_NO;
    }

    public void setCERT_NO(String CERT_NO)
    {
        this.CERT_NO = CERT_NO;
    }

    public String getCUST_NAME()
    {
        return CUST_NAME;
    }

    public void setCUST_NAME(String CUST_NAME)
    {
        this.CUST_NAME = CUST_NAME;
    }

    public String getPHONE_NO()
    {
        return PHONE_NO;
    }

    public void setPHONE_NO(String PHONE_NO)
    {
        this.PHONE_NO = PHONE_NO;
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

    public String getCREDIT_STATUS()
    {
        return CREDIT_STATUS;
    }

    public void setCREDIT_STATUS(String CREDIT_STATUS)
    {
        this.CREDIT_STATUS = CREDIT_STATUS;
    }

    public String getCREDIT_DATE()
    {
        return CREDIT_DATE;
    }

    public void setCREDIT_DATE(String CREDIT_DATE)
    {
        this.CREDIT_DATE = CREDIT_DATE;
    }
}
