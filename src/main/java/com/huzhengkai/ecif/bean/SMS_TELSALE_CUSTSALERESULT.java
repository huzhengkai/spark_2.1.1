package com.huzhengkai.ecif.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by root on 2018/3/28.
 */
public class SMS_TELSALE_CUSTSALERESULT implements Serializable
{
    private String RESULT_ID;
    private String CERT_NO;
    private String ECIF_CUST_NO;
    private String TEL_NO;
    private String OPERATE_TYPE;
    private String RESULT_STATE;
    private String REMARK;
    private Date UPDATE_TIME;
    private String UPDATE_USERID;
    private String UPDATE_USERNAME;
    private String SERIAL_NO;
    private String TASK_ID;
    private String CUST_NAME;

    public SMS_TELSALE_CUSTSALERESULT()
    {
    }

    public SMS_TELSALE_CUSTSALERESULT(String RESULT_ID, String CERT_NO, String ECIF_CUST_NO, String TEL_NO, String OPERATE_TYPE, String RESULT_STATE, String REMARK, Date UPDATE_TIME, String UPDATE_USERID, String UPDATE_USERNAME, String SERIAL_NO, String TASK_ID, String CUST_NAME)
    {
        this.RESULT_ID = RESULT_ID;
        this.CERT_NO = CERT_NO;
        this.ECIF_CUST_NO = ECIF_CUST_NO;
        this.TEL_NO = TEL_NO;
        this.OPERATE_TYPE = OPERATE_TYPE;
        this.RESULT_STATE = RESULT_STATE;
        this.REMARK = REMARK;
        this.UPDATE_TIME = UPDATE_TIME;
        this.UPDATE_USERID = UPDATE_USERID;
        this.UPDATE_USERNAME = UPDATE_USERNAME;
        this.SERIAL_NO = SERIAL_NO;
        this.TASK_ID = TASK_ID;
        this.CUST_NAME = CUST_NAME;
    }
    public void convertNull()
    {
        if(this.getRESULT_ID().equals("null"))
        {
            this.setRESULT_ID(null);
        }
        if(this.getCERT_NO().equals("null"))
        {
            this.setCERT_NO(null);
        }
        if(this.getECIF_CUST_NO().equals("null"))
        {
            this.setECIF_CUST_NO(null);
        }
        if(this.getTEL_NO().equals("null"))
        {
            this.setTEL_NO(null);
        }
        if(this.getOPERATE_TYPE().equals("null"))
        {
            this.setOPERATE_TYPE(null);
        }
        if(this.getRESULT_STATE().equals("null"))
        {
            this.setRESULT_STATE(null);
        }
        if(this.getREMARK().equals("null"))
        {
            this.setREMARK(null);
        }
        if(this.getUPDATE_TIME().equals("null"))
        {
            this.setUPDATE_TIME(null);
        }
        if(this.getUPDATE_USERID().equals("null"))
        {
            this.setUPDATE_USERID(null);
        }
        if(this.getUPDATE_USERNAME().equals("null"))
        {
            this.setUPDATE_USERNAME(null);
        }
        if(this.getSERIAL_NO().equals("null"))
        {
            this.setSERIAL_NO(null);
        }
        if(this.getTASK_ID().equals("null"))
        {
            this.setTASK_ID(null);
        }
        if(this.getCUST_NAME().equals("null"))
        {
            this.setCUST_NAME(null);
        }
    }
    public String getRESULT_ID()
    {
        return RESULT_ID;
    }

    public void setRESULT_ID(String RESULT_ID)
    {
        this.RESULT_ID = RESULT_ID;
    }

    public String getCERT_NO()
    {
        return CERT_NO;
    }

    public void setCERT_NO(String CERT_NO)
    {
        this.CERT_NO = CERT_NO;
    }

    public String getECIF_CUST_NO()
    {
        return ECIF_CUST_NO;
    }

    public void setECIF_CUST_NO(String ECIF_CUST_NO)
    {
        this.ECIF_CUST_NO = ECIF_CUST_NO;
    }

    public String getTEL_NO()
    {
        return TEL_NO;
    }

    public void setTEL_NO(String TEL_NO)
    {
        this.TEL_NO = TEL_NO;
    }

    public String getOPERATE_TYPE()
    {
        return OPERATE_TYPE;
    }

    public void setOPERATE_TYPE(String OPERATE_TYPE)
    {
        this.OPERATE_TYPE = OPERATE_TYPE;
    }

    public String getRESULT_STATE()
    {
        return RESULT_STATE;
    }

    public void setRESULT_STATE(String RESULT_STATE)
    {
        this.RESULT_STATE = RESULT_STATE;
    }

    public String getREMARK()
    {
        return REMARK;
    }

    public void setREMARK(String REMARK)
    {
        this.REMARK = REMARK;
    }

    public Date getUPDATE_TIME()
    {
        return UPDATE_TIME;
    }

    public void setUPDATE_TIME(Date UPDATE_TIME)
    {
        this.UPDATE_TIME = UPDATE_TIME;
    }

    public String getUPDATE_USERID()
    {
        return UPDATE_USERID;
    }

    public void setUPDATE_USERID(String UPDATE_USERID)
    {
        this.UPDATE_USERID = UPDATE_USERID;
    }

    public String getUPDATE_USERNAME()
    {
        return UPDATE_USERNAME;
    }

    public void setUPDATE_USERNAME(String UPDATE_USERNAME)
    {
        this.UPDATE_USERNAME = UPDATE_USERNAME;
    }

    public String getSERIAL_NO()
    {
        return SERIAL_NO;
    }

    public void setSERIAL_NO(String SERIAL_NO)
    {
        this.SERIAL_NO = SERIAL_NO;
    }

    public String getTASK_ID()
    {
        return TASK_ID;
    }

    public void setTASK_ID(String TASK_ID)
    {
        this.TASK_ID = TASK_ID;
    }

    public String getCUST_NAME()
    {
        return CUST_NAME;
    }

    public void setCUST_NAME(String CUST_NAME)
    {
        this.CUST_NAME = CUST_NAME;
    }
}
