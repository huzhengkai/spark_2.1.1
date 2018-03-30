package com.huzhengkai.ecif.bean;

import java.util.Date;

/**
 * Created by root on 2018/3/29.
 */
public class LATENT_CUST_CONTACT_INFO
{
    private String LATENT_CUST_NO;
    private String CONTACT_TYPE;
    private String CONTACT_NO;
    private String SYS_SOURCE;
    private String INSERT_TIMR;
    private String UPDATE_TIME;

    public LATENT_CUST_CONTACT_INFO()
    {
    }

    public LATENT_CUST_CONTACT_INFO(String LATENT_CUST_NO, String CONTACT_TYPE, String CONTACT_NO, String SYS_SOURCE, String INSERT_TIMR, String UPDATE_TIME)
    {
        this.LATENT_CUST_NO = LATENT_CUST_NO;
        this.CONTACT_TYPE = CONTACT_TYPE;
        this.CONTACT_NO = CONTACT_NO;
        this.SYS_SOURCE = SYS_SOURCE;
        this.INSERT_TIMR = INSERT_TIMR;
        this.UPDATE_TIME = UPDATE_TIME;
    }

    public void convertNull()
    {
        if (this.getLATENT_CUST_NO().equals("null"))
        {
            this.setLATENT_CUST_NO(null);
        }
        if (this.getCONTACT_TYPE().equals("null"))
        {
            this.setCONTACT_TYPE(null);
        }
        if (this.getCONTACT_NO().equals("null"))
        {
            this.setCONTACT_NO(null);
        }
        if (this.getSYS_SOURCE().equals("null"))
        {
            this.setSYS_SOURCE(null);
        }
        if (this.getINSERT_TIMR().equals("null"))
        {
            this.setINSERT_TIMR(null);
        }
        if (this.getUPDATE_TIME().equals("null"))
        {
            this.setUPDATE_TIME(null);
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

    public String getCONTACT_TYPE()
    {
        return CONTACT_TYPE;
    }

    public void setCONTACT_TYPE(String CONTACT_TYPE)
    {
        this.CONTACT_TYPE = CONTACT_TYPE;
    }

    public String getCONTACT_NO()
    {
        return CONTACT_NO;
    }

    public void setCONTACT_NO(String CONTACT_NO)
    {
        this.CONTACT_NO = CONTACT_NO;
    }

    public String getSYS_SOURCE()
    {
        return SYS_SOURCE;
    }

    public void setSYS_SOURCE(String SYS_SOURCE)
    {
        this.SYS_SOURCE = SYS_SOURCE;
    }

    public String getINSERT_TIMR()
    {
        return INSERT_TIMR;
    }

    public void setINSERT_TIMR(String INSERT_TIMR)
    {
        this.INSERT_TIMR = INSERT_TIMR;
    }

    public String getUPDATE_TIME()
    {
        return UPDATE_TIME;
    }

    public void setUPDATE_TIME(String UPDATE_TIME)
    {
        this.UPDATE_TIME = UPDATE_TIME;
    }
}
