package com.huzhengkai.ecif.bean;

import java.io.Serializable;

/**
 * Created by root on 2018/3/28.
 */
public class ECIF_CUST_WHITE_LIST_OLD implements Serializable
{
    private String ID;
    private String ECIF_CUST_NO;
    private String CERT_TYPE;
    private String CERT_NO;
    private String CUST_NAME;
    private String PHONE_NO;
    private String LOAN_NO;
    private Double INIT_CREDIT_LIMIT;
    private Double CREDIT_LIMIT;
    private String LEVEL;
    private String IS_VALID;
    private String CASH_LOAN_TYPE;
    private String ENTER_DATE;
    private String EXISTS_DATE;
    private String IS_KX;

    public ECIF_CUST_WHITE_LIST_OLD()
    {
    }

    public ECIF_CUST_WHITE_LIST_OLD(String ID, String ECIF_CUST_NO, String CERT_TYPE, String CERT_NO, String CUST_NAME, String PHONE_NO, String LOAN_NO, Double INIT_CREDIT_LIMIT, Double CREDIT_LIMIT, String LEVEL, String IS_VALID, String CASH_LOAN_TYPE, String ENTER_DATE, String EXISTS_DATE, String IS_KX)
    {
        this.ID = ID;
        this.ECIF_CUST_NO = ECIF_CUST_NO;
        this.CERT_TYPE = CERT_TYPE;
        this.CERT_NO = CERT_NO;
        this.CUST_NAME = CUST_NAME;
        this.PHONE_NO = PHONE_NO;
        this.LOAN_NO = LOAN_NO;
        this.INIT_CREDIT_LIMIT = INIT_CREDIT_LIMIT;
        this.CREDIT_LIMIT = CREDIT_LIMIT;
        this.LEVEL = LEVEL;
        this.IS_VALID = IS_VALID;
        this.CASH_LOAN_TYPE = CASH_LOAN_TYPE;
        this.ENTER_DATE = ENTER_DATE;
        this.EXISTS_DATE = EXISTS_DATE;
        this.IS_KX = IS_KX;
    }

    public String getID()
    {
        return ID;
    }

    public void setID(String ID)
    {
        this.ID = ID;
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

    public String getLOAN_NO()
    {
        return LOAN_NO;
    }

    public void setLOAN_NO(String LOAN_NO)
    {
        this.LOAN_NO = LOAN_NO;
    }

    public Double getINIT_CREDIT_LIMIT()
    {
        return INIT_CREDIT_LIMIT;
    }

    public void setINIT_CREDIT_LIMIT(Double INIT_CREDIT_LIMIT)
    {
        this.INIT_CREDIT_LIMIT = INIT_CREDIT_LIMIT;
    }

    public Double getCREDIT_LIMIT()
    {
        return CREDIT_LIMIT;
    }

    public void setCREDIT_LIMIT(Double CREDIT_LIMIT)
    {
        this.CREDIT_LIMIT = CREDIT_LIMIT;
    }

    public String getLEVEL()
    {
        return LEVEL;
    }

    public void setLEVEL(String LEVEL)
    {
        this.LEVEL = LEVEL;
    }

    public String getIS_VALID()
    {
        return IS_VALID;
    }

    public void setIS_VALID(String IS_VALID)
    {
        this.IS_VALID = IS_VALID;
    }

    public String getCASH_LOAN_TYPE()
    {
        return CASH_LOAN_TYPE;
    }

    public void setCASH_LOAN_TYPE(String CASH_LOAN_TYPE)
    {
        this.CASH_LOAN_TYPE = CASH_LOAN_TYPE;
    }

    public String getENTER_DATE()
    {
        return ENTER_DATE;
    }

    public void setENTER_DATE(String ENTER_DATE)
    {
        this.ENTER_DATE = ENTER_DATE;
    }

    public String getEXISTS_DATE()
    {
        return EXISTS_DATE;
    }

    public void setEXISTS_DATE(String EXISTS_DATE)
    {
        this.EXISTS_DATE = EXISTS_DATE;
    }

    public String getIS_KX()
    {
        return IS_KX;
    }

    public void setIS_KX(String IS_KX)
    {
        this.IS_KX = IS_KX;
    }
}
