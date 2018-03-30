package com.huzhengkai.ecif.bean;

import java.io.Serializable;

/**
 * Created by root on 2018/3/27.
 */
public class ECIF_CUST_WECHAT_REG implements Serializable
{
    private String ID;
    private String ECIF_CUST_NO;
    private String WECHAT_OPENDID;
    private String PHONE_NO;
    private String CERT_NO;
    private String CUST_NAME;
    private String TYPE;
    private String STATUS;
    private String ATT_DATE;
    private String REG_DATE;
    private String AUTH_DATE;
    private String EXPIRE_DATE;

    public ECIF_CUST_WECHAT_REG()
    {
    }

    public ECIF_CUST_WECHAT_REG(String ID, String ECIF_CUST_NO, String WECHAT_OPENDID, String PHONE_NO, String CERT_NO, String CUST_NAME, String TYPE, String STATUS, String ATT_DATE, String REG_DATE, String AUTH_DATE, String EXPIRE_DATE)
    {
        this.ID = ID;
        this.ECIF_CUST_NO = ECIF_CUST_NO;
        this.WECHAT_OPENDID = WECHAT_OPENDID;
        this.PHONE_NO = PHONE_NO;
        this.CERT_NO = CERT_NO;
        this.CUST_NAME = CUST_NAME;
        this.TYPE = TYPE;
        this.STATUS = STATUS;
        this.ATT_DATE = ATT_DATE;
        this.REG_DATE = REG_DATE;
        this.AUTH_DATE = AUTH_DATE;
        this.EXPIRE_DATE = EXPIRE_DATE;
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

    public String getWECHAT_OPENDID()
    {
        return WECHAT_OPENDID;
    }

    public void setWECHAT_OPENDID(String WECHAT_OPENDID)
    {
        this.WECHAT_OPENDID = WECHAT_OPENDID;
    }

    public String getPHONE_NO()
    {
        return PHONE_NO;
    }

    public void setPHONE_NO(String PHONE_NO)
    {
        this.PHONE_NO = PHONE_NO;
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

    public String getTYPE()
    {
        return TYPE;
    }

    public void setTYPE(String TYPE)
    {
        this.TYPE = TYPE;
    }

    public String getSTATUS()
    {
        return STATUS;
    }

    public void setSTATUS(String STATUS)
    {
        this.STATUS = STATUS;
    }

    public String getATT_DATE()
    {
        return ATT_DATE;
    }

    public void setATT_DATE(String ATT_DATE)
    {
        this.ATT_DATE = ATT_DATE;
    }

    public String getREG_DATE()
    {
        return REG_DATE;
    }

    public void setREG_DATE(String REG_DATE)
    {
        this.REG_DATE = REG_DATE;
    }

    public String getAUTH_DATE()
    {
        return AUTH_DATE;
    }

    public void setAUTH_DATE(String AUTH_DATE)
    {
        this.AUTH_DATE = AUTH_DATE;
    }

    public String getEXPIRE_DATE()
    {
        return EXPIRE_DATE;
    }

    public void setEXPIRE_DATE(String EXPIRE_DATE)
    {
        this.EXPIRE_DATE = EXPIRE_DATE;
    }
}
