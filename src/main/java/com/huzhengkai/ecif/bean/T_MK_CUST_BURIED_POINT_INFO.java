package com.huzhengkai.ecif.bean;

import java.io.Serializable;

/**
 * Created by root on 2018/3/27.
 */
public class T_MK_CUST_BURIED_POINT_INFO implements Serializable
{
    private String ID;
    private String CID;
    private String CHANNEL_TYPE;
    private String CHANNEL_NO;
    private String POINT_TYPE;
    private String PAGE_CODE;
    private String PAGE_URL;
    private String INSERT_DATE;
    private String MODIFY_DATE;

    public T_MK_CUST_BURIED_POINT_INFO()
    {
    }

    public T_MK_CUST_BURIED_POINT_INFO(String ID, String CID, String CHANNEL_TYPE, String CHANNEL_NO, String POINT_TYPE, String PAGE_CODE, String PAGE_URL, String INSERT_DATE, String MODIFY_DATE)
    {
        this.ID = ID;
        this.CID = CID;
        this.CHANNEL_TYPE = CHANNEL_TYPE;
        this.CHANNEL_NO = CHANNEL_NO;
        this.POINT_TYPE = POINT_TYPE;
        this.PAGE_CODE = PAGE_CODE;
        this.PAGE_URL = PAGE_URL;
        this.INSERT_DATE = INSERT_DATE;
        this.MODIFY_DATE = MODIFY_DATE;
    }

    public void convertNull()
    {
        if(this.getID().equals("null"))
        {
            this.setID(null);
        }
        if(this.getCID().equals("null"))
        {
            this.setCID(null);
        }
        if(this.getCHANNEL_TYPE().equals("null"))
        {
            this.setCHANNEL_TYPE(null);
        }
        if(this.getCHANNEL_NO().equals("null"))
        {
            this.setCHANNEL_NO(null);
        }
        if(this.getPOINT_TYPE().equals("null"))
        {
            this.setPOINT_TYPE(null);
        }
        if(this.getPAGE_CODE().equals("null"))
        {
            this.setPAGE_CODE(null);
        }
        if(this.getPAGE_URL().equals("null"))
        {
            this.setPAGE_URL(null);
        }
        if(this.getINSERT_DATE().equals("null"))
        {
            this.setINSERT_DATE(null);
        }
        if(this.getMODIFY_DATE().equals("null"))
        {
            this.setMODIFY_DATE(null);
        }
    }
    public String getID()
    {
        return ID;
    }

    public void setID(String ID)
    {
        this.ID = ID;
    }

    public String getCID()
    {
        return CID;
    }

    public void setCID(String CID)
    {
        this.CID = CID;
    }

    public String getCHANNEL_TYPE()
    {
        return CHANNEL_TYPE;
    }

    public void setCHANNEL_TYPE(String CHANNEL_TYPE)
    {
        this.CHANNEL_TYPE = CHANNEL_TYPE;
    }

    public String getCHANNEL_NO()
    {
        return CHANNEL_NO;
    }

    public void setCHANNEL_NO(String CHANNEL_NO)
    {
        this.CHANNEL_NO = CHANNEL_NO;
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

    public String getPAGE_URL()
    {
        return PAGE_URL;
    }

    public void setPAGE_URL(String PAGE_URL)
    {
        this.PAGE_URL = PAGE_URL;
    }

    public String getINSERT_DATE()
    {
        return INSERT_DATE;
    }

    public void setINSERT_DATE(String INSERT_DATE)
    {
        this.INSERT_DATE = INSERT_DATE;
    }

    public String getMODIFY_DATE()
    {
        return MODIFY_DATE;
    }

    public void setMODIFY_DATE(String MODIFY_DATE)
    {
        this.MODIFY_DATE = MODIFY_DATE;
    }
}
