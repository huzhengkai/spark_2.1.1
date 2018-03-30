package com.huzhengkai.ecif.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by root on 2018/3/28.
 */
public class T_CUST_CREDIT_INFO implements Serializable
{
    private String CREDIT_ID;
    private String CUST_NO;
    private String CUST_NAME;
    private String CERT_NO;
    private String PHONE_NUM;
    private String CREDIT_LEVEL;
    private Double CREDIT_TOTAL_AMT;
    private Double CREDIT_USED_AMT;
    private Double CREDIT_REMAIN_AMT;
    private String STATE;
    private String INSERT_TIME;
    private String CONTACT_NAME;

    public T_CUST_CREDIT_INFO()
    {
    }

    public T_CUST_CREDIT_INFO(String CREDIT_ID, String CUST_NO, String CUST_NAME, String CERT_NO, String PHONE_NUM, String CREDIT_LEVEL, Double CREDIT_TOTAL_AMT, Double CREDIT_USED_AMT, Double CREDIT_REMAIN_AMT, String STATE, String INSERT_TIME, String CONTACT_NAME)
    {
        this.CREDIT_ID = CREDIT_ID;
        this.CUST_NO = CUST_NO;
        this.CUST_NAME = CUST_NAME;
        this.CERT_NO = CERT_NO;
        this.PHONE_NUM = PHONE_NUM;
        this.CREDIT_LEVEL = CREDIT_LEVEL;
        this.CREDIT_TOTAL_AMT = CREDIT_TOTAL_AMT;
        this.CREDIT_USED_AMT = CREDIT_USED_AMT;
        this.CREDIT_REMAIN_AMT = CREDIT_REMAIN_AMT;
        this.STATE = STATE;
        this.INSERT_TIME = INSERT_TIME;
        this.CONTACT_NAME = CONTACT_NAME;
    }
    public void convertNull()
    {
        if (this.getCREDIT_ID().equals("null"))
        {
            this.setCREDIT_ID(null);
        }
        if (this.getCUST_NO().equals("null"))
        {
            this.setCUST_NO(null);
        }
        if (this.getCUST_NAME().equals("null"))
        {
            this.setCUST_NAME(null);
        }
        if (this.getCERT_NO().equals("null"))
        {
            this.setCERT_NO(null);
        }
        if (this.getPHONE_NUM().equals("null"))
        {
            this.setPHONE_NUM(null);
        }
        if (this.getCREDIT_LEVEL().equals("null"))
        {
            this.setCREDIT_LEVEL(null);
        }
        if (this.getCREDIT_TOTAL_AMT().equals("null"))
        {
            this.setCREDIT_TOTAL_AMT(null);
        }
        if (this.getCREDIT_USED_AMT().equals("null"))
        {
            this.setCREDIT_USED_AMT(null);
        }
        if (this.getCREDIT_REMAIN_AMT().equals("null"))
        {
            this.setCREDIT_REMAIN_AMT(null);
        }
        if (this.getSTATE().equals("null"))
        {
            this.setSTATE(null);
        }
        if (this.getINSERT_TIME().equals("null"))
        {
            this.setINSERT_TIME(null);
        }
        if (this.getCONTACT_NAME().equals("null"))
        {
            this.setCONTACT_NAME(null);
        }
    }

    public String getCREDIT_ID()
    {
        return CREDIT_ID;
    }

    public void setCREDIT_ID(String CREDIT_ID)
    {
        this.CREDIT_ID = CREDIT_ID;
    }

    public String getCUST_NO()
    {
        return CUST_NO;
    }

    public void setCUST_NO(String CUST_NO)
    {
        this.CUST_NO = CUST_NO;
    }

    public String getCUST_NAME()
    {
        return CUST_NAME;
    }

    public void setCUST_NAME(String CUST_NAME)
    {
        this.CUST_NAME = CUST_NAME;
    }

    public String getCERT_NO()
    {
        return CERT_NO;
    }

    public void setCERT_NO(String CERT_NO)
    {
        this.CERT_NO = CERT_NO;
    }

    public String getPHONE_NUM()
    {
        return PHONE_NUM;
    }

    public void setPHONE_NUM(String PHONE_NUM)
    {
        this.PHONE_NUM = PHONE_NUM;
    }

    public String getCREDIT_LEVEL()
    {
        return CREDIT_LEVEL;
    }

    public void setCREDIT_LEVEL(String CREDIT_LEVEL)
    {
        this.CREDIT_LEVEL = CREDIT_LEVEL;
    }

    public Double getCREDIT_TOTAL_AMT()
    {
        return CREDIT_TOTAL_AMT;
    }

    public void setCREDIT_TOTAL_AMT(Double CREDIT_TOTAL_AMT)
    {
        this.CREDIT_TOTAL_AMT = CREDIT_TOTAL_AMT;
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

    public String getSTATE()
    {
        return STATE;
    }

    public void setSTATE(String STATE)
    {
        this.STATE = STATE;
    }

    public String getINSERT_TIME()
    {
        return INSERT_TIME;
    }

    public void setINSERT_TIME(String INSERT_TIME)
    {
        this.INSERT_TIME = INSERT_TIME;
    }

    public String getCONTACT_NAME()
    {
        return CONTACT_NAME;
    }

    public void setCONTACT_NAME(String CONTACT_NAME)
    {
        this.CONTACT_NAME = CONTACT_NAME;
    }
}
