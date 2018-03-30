package com.huzhengkai.ecif.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by root on 2018/3/29.
 */
public class T_SMSPLTFORM_SNED_DATA implements Serializable
{
    private String ORG_ID;
    private String MOBILE_PHONE;
    private String SQL_COLLECT;
    private String APPLIC_CODE;
    private String SEND_FLAG;
    private String DATAID;
    private Date BUSI_DT;
    private String TABLE_KEY;

    public T_SMSPLTFORM_SNED_DATA()
    {
    }

    public T_SMSPLTFORM_SNED_DATA(String ORG_ID, String MOBILE_PHONE, String SQL_COLLECT, String APPLIC_CODE, String SEND_FLAG, String DATAID, Date BUSI_DT, String TABLE_KEY)
    {
        this.ORG_ID = ORG_ID;
        this.MOBILE_PHONE = MOBILE_PHONE;
        this.SQL_COLLECT = SQL_COLLECT;
        this.APPLIC_CODE = APPLIC_CODE;
        this.SEND_FLAG = SEND_FLAG;
        this.DATAID = DATAID;
        this.BUSI_DT = BUSI_DT;
        this.TABLE_KEY = TABLE_KEY;
    }
    public void convertNull()
    {
        if (this.getORG_ID().equals("null"))
        {
            this.setORG_ID(null);
        }
        if (this.getMOBILE_PHONE().equals("null"))
        {
            this.setMOBILE_PHONE(null);
        }
        if (this.getSQL_COLLECT().equals("null"))
        {
            this.setSQL_COLLECT(null);
        }
        if (this.getAPPLIC_CODE().equals("null"))
        {
            this.setAPPLIC_CODE(null);
        }
        if (this.getSEND_FLAG().equals("null"))
        {
            this.setSEND_FLAG(null);
        }
        if (this.getDATAID().equals("null"))
        {
            this.setDATAID(null);
        }
        if (this.getBUSI_DT().equals("null"))
        {
            this.setBUSI_DT(null);
        }
        if (this.getTABLE_KEY().equals("null"))
        {
            this.setTABLE_KEY(null);
        }
    }

    public String getORG_ID()
    {
        return ORG_ID;
    }

    public void setORG_ID(String ORG_ID)
    {
        this.ORG_ID = ORG_ID;
    }

    public String getMOBILE_PHONE()
    {
        return MOBILE_PHONE;
    }

    public void setMOBILE_PHONE(String MOBILE_PHONE)
    {
        this.MOBILE_PHONE = MOBILE_PHONE;
    }

    public String getSQL_COLLECT()
    {
        return SQL_COLLECT;
    }

    public void setSQL_COLLECT(String SQL_COLLECT)
    {
        this.SQL_COLLECT = SQL_COLLECT;
    }

    public String getAPPLIC_CODE()
    {
        return APPLIC_CODE;
    }

    public void setAPPLIC_CODE(String APPLIC_CODE)
    {
        this.APPLIC_CODE = APPLIC_CODE;
    }

    public String getSEND_FLAG()
    {
        return SEND_FLAG;
    }

    public void setSEND_FLAG(String SEND_FLAG)
    {
        this.SEND_FLAG = SEND_FLAG;
    }

    public String getDATAID()
    {
        return DATAID;
    }

    public void setDATAID(String DATAID)
    {
        this.DATAID = DATAID;
    }

    public Date getBUSI_DT()
    {
        return BUSI_DT;
    }

    public void setBUSI_DT(Date BUSI_DT)
    {
        this.BUSI_DT = BUSI_DT;
    }

    public String getTABLE_KEY()
    {
        return TABLE_KEY;
    }

    public void setTABLE_KEY(String TABLE_KEY)
    {
        this.TABLE_KEY = TABLE_KEY;
    }
}
