package com.huzhengkai.ecif.bean;

import java.io.Serializable;

/**
 * Created by root on 2018/3/29.
 */
public class LATENT_CUST_BASE_INFO implements Serializable
{
    private String LATENT_CUST_NO;
    private String CERT_TYPE;
    private String CERT_NO;
    private String CUST_NAME;
    private String NATION;
    private String SEX;
    private String ENTER_DATE;

    public LATENT_CUST_BASE_INFO()
    {
    }

    public LATENT_CUST_BASE_INFO(String LATENT_CUST_NO, String CERT_TYPE, String CERT_NO, String CUST_NAME, String NATION, String SEX, String ENTER_DATE)
    {
        this.LATENT_CUST_NO = LATENT_CUST_NO;
        this.CERT_TYPE = CERT_TYPE;
        this.CERT_NO = CERT_NO;
        this.CUST_NAME = CUST_NAME;
        this.NATION = NATION;
        this.SEX = SEX;
        this.ENTER_DATE = ENTER_DATE;
    }
    public void convertNull()
    {
        if (this.getLATENT_CUST_NO().equals("null"))
        {
            this.setLATENT_CUST_NO(null);
        }
        if (this.getCERT_TYPE().equals("null"))
        {
            this.setCERT_TYPE(null);
        }
        if (this.getCERT_NO().equals("null"))
        {
            this.setCERT_NO(null);
        }
        if (this.getCUST_NAME().equals("null"))
        {
            this.setCUST_NAME(null);
        }
        if (this.getNATION().equals("null"))
        {
            this.setNATION(null);
        }
        if (this.getSEX().equals("null"))
        {
            this.setSEX(null);
        }
        if (this.getENTER_DATE().equals("null"))
        {
            this.setENTER_DATE(null);
        }
    }

    public String getLATENT_CUST_NO()
    {
        return LATENT_CUST_NO;
    }

    public void setLATENT_CUST_NO(String LATENT_CUST_NO)
    {
        this.LATENT_CUST_NO = LATENT_CUST_NO;
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

    public String getNATION()
    {
        return NATION;
    }

    public void setNATION(String NATION)
    {
        this.NATION = NATION;
    }

    public String getSEX()
    {
        return SEX;
    }

    public void setSEX(String SEX)
    {
        this.SEX = SEX;
    }

    public String getENTER_DATE()
    {
        return ENTER_DATE;
    }

    public void setENTER_DATE(String ENTER_DATE)
    {
        this.ENTER_DATE = ENTER_DATE;
    }
}
