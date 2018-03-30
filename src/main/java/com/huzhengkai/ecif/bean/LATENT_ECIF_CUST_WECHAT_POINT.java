package com.huzhengkai.ecif.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by root on 2018/3/29.
 */
public class LATENT_ECIF_CUST_WECHAT_POINT implements Serializable
{
    private String ID;
    private String LATENT_CUST_NO;
    private String CHANNEL_TYPE;
    private String WECHAT_OPENID;
    private String POINT_TYPE;
    private String PAGE_CODE;
    private String ENTER_DATE;
    private Date ENTER_TIME;
    private Date EXISTS_TIME;
    private Double STAY_TIMES;

    public LATENT_ECIF_CUST_WECHAT_POINT()
    {
    }

    public LATENT_ECIF_CUST_WECHAT_POINT(String ID, String LATENT_CUST_NO, String CHANNEL_TYPE, String WECHAT_OPENID, String POINT_TYPE, String PAGE_CODE, String ENTER_DATE, Date ENTER_TIME, Date EXISTS_TIME, Double STAY_TIMES)
    {
        this.ID = ID;
        this.LATENT_CUST_NO = LATENT_CUST_NO;
        this.CHANNEL_TYPE = CHANNEL_TYPE;
        this.WECHAT_OPENID = WECHAT_OPENID;
        this.POINT_TYPE = POINT_TYPE;
        this.PAGE_CODE = PAGE_CODE;
        this.ENTER_DATE = ENTER_DATE;
        this.ENTER_TIME = ENTER_TIME;
        this.EXISTS_TIME = EXISTS_TIME;
        this.STAY_TIMES = STAY_TIMES;
    }

    public String getID()
    {
        return ID;
    }

    public void setID(String ID)
    {
        this.ID = ID;
    }

    public String getLATENT_CUST_NO()
    {
        return LATENT_CUST_NO;
    }

    public void setLATENT_CUST_NO(String LATENT_CUST_NO)
    {
        this.LATENT_CUST_NO = LATENT_CUST_NO;
    }

    public String getCHANNEL_TYPE()
    {
        return CHANNEL_TYPE;
    }

    public void setCHANNEL_TYPE(String CHANNEL_TYPE)
    {
        this.CHANNEL_TYPE = CHANNEL_TYPE;
    }

    public String getWECHAT_OPENID()
    {
        return WECHAT_OPENID;
    }

    public void setWECHAT_OPENID(String WECHAT_OPENID)
    {
        this.WECHAT_OPENID = WECHAT_OPENID;
    }

    public String getPOINT_TYPE()
    {
        return POINT_TYPE;
    }

    public void setPOINT_TYPE(String POINT_TYPE)
    {
        this.POINT_TYPE = POINT_TYPE;
    }

    public String getPAGE_CODE()
    {
        return PAGE_CODE;
    }

    public void setPAGE_CODE(String PAGE_CODE)
    {
        this.PAGE_CODE = PAGE_CODE;
    }

    public String getENTER_DATE()
    {
        return ENTER_DATE;
    }

    public void setENTER_DATE(String ENTER_DATE)
    {
        this.ENTER_DATE = ENTER_DATE;
    }

    public Date getENTER_TIME()
    {
        return ENTER_TIME;
    }

    public void setENTER_TIME(Date ENTER_TIME)
    {
        this.ENTER_TIME = ENTER_TIME;
    }

    public Date getEXISTS_TIME()
    {
        return EXISTS_TIME;
    }

    public void setEXISTS_TIME(Date EXISTS_TIME)
    {
        this.EXISTS_TIME = EXISTS_TIME;
    }

    public Double getSTAY_TIMES()
    {
        return STAY_TIMES;
    }

    public void setSTAY_TIMES(Double STAY_TIMES)
    {
        this.STAY_TIMES = STAY_TIMES;
    }
}
