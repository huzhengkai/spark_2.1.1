package com.huzhengkai.ecif.bean;

import java.io.Serializable;

/**
 * Created by root on 2018/3/26.
 */
public class CUST_CHAN_RELATION implements Serializable
{
    private String CUSTOMER_ID;
    private String CUST_TEL;
    private String CHAN_TYPE;
    private String CHAN_ID;
    private String CUST_CERT_NO;

    public CUST_CHAN_RELATION()
    {
    }

    public CUST_CHAN_RELATION(String CUSTOMER_ID, String CUST_TEL, String CHAN_TYPE, String CHAN_ID, String CUST_CERT_NO)
    {
        this.CUSTOMER_ID = CUSTOMER_ID;
        this.CUST_TEL = CUST_TEL;
        this.CHAN_TYPE = CHAN_TYPE;
        this.CHAN_ID = CHAN_ID;
        this.CUST_CERT_NO = CUST_CERT_NO;
    }
    public void convertNull()
    {
        if(this.getCUSTOMER_ID().equals(""))
        {
            this.setCUSTOMER_ID(null);
        }
        if(this.getCUST_TEL().equals(""))
        {
            this.setCUST_TEL(null);
        }
        if(this.getCHAN_TYPE().equals(""))
        {
            this.setCHAN_TYPE(null);
        }
        if(this.getCHAN_ID().equals(""))
        {
            this.setCHAN_ID(null);
        }
        if(this.getCUST_CERT_NO().equals(""))
        {
            this.setCUST_CERT_NO(null);
        }
    }
    public String getCUSTOMER_ID()
    {
        return CUSTOMER_ID;
    }

    public void setCUSTOMER_ID(String CUSTOMER_ID)
    {
        this.CUSTOMER_ID = CUSTOMER_ID;
    }

    public String getCUST_TEL()
    {
        return CUST_TEL;
    }

    public void setCUST_TEL(String CUST_TEL)
    {
        this.CUST_TEL = CUST_TEL;
    }

    public String getCHAN_TYPE()
    {
        return CHAN_TYPE;
    }

    public void setCHAN_TYPE(String CHAN_TYPE)
    {
        this.CHAN_TYPE = CHAN_TYPE;
    }

    public String getCHAN_ID()
    {
        return CHAN_ID;
    }

    public void setCHAN_ID(String CHAN_ID)
    {
        this.CHAN_ID = CHAN_ID;
    }

    public String getCUST_CERT_NO()
    {
        return CUST_CERT_NO;
    }

    public void setCUST_CERT_NO(String CUST_CERT_NO)
    {
        this.CUST_CERT_NO = CUST_CERT_NO;
    }
}
