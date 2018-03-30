package com.huzhengkai.ecif.bean;

import java.util.Date;

/**
 * Created by root on 2018/3/28.
 */
public class ECIF_CUST_MKTG_RESULT
{
    private String ECIF_CUST_NO;
    private String CERT_TYPE;
    private String CERT_NO;
    private String CUST_NAME;
    private String PHONE_NO;
    private String MKTG_RESULT_ID;
    private String TASK_NAME;
    private String OPER_TYPE;
    private String MKTG_RESULT;
    private String MKTG_REMARK;
    private String MKTG_STAFF_NO;
    private String MKTG_STAFF_NAME;
    private String MKTG_DATE;
    private Date MKTG_TIME;

    public ECIF_CUST_MKTG_RESULT()
    {
    }

    public ECIF_CUST_MKTG_RESULT(String ECIF_CUST_NO, String CERT_TYPE, String CERT_NO, String CUST_NAME, String PHONE_NO, String MKTG_RESULT_ID, String TASK_NAME, String OPER_TYPE, String MKTG_RESULT, String MKTG_REMARK, String MKTG_STAFF_NO, String MKTG_STAFF_NAME, String MKTG_DATE, Date MKTG_TIME)
    {
        this.ECIF_CUST_NO = ECIF_CUST_NO;
        this.CERT_TYPE = CERT_TYPE;
        this.CERT_NO = CERT_NO;
        this.CUST_NAME = CUST_NAME;
        this.PHONE_NO = PHONE_NO;
        this.MKTG_RESULT_ID = MKTG_RESULT_ID;
        this.TASK_NAME = TASK_NAME;
        this.OPER_TYPE = OPER_TYPE;
        this.MKTG_RESULT = MKTG_RESULT;
        this.MKTG_REMARK = MKTG_REMARK;
        this.MKTG_STAFF_NO = MKTG_STAFF_NO;
        this.MKTG_STAFF_NAME = MKTG_STAFF_NAME;
        this.MKTG_DATE = MKTG_DATE;
        this.MKTG_TIME = MKTG_TIME;
    }
    public void convertNull()
    {
        if(this.getECIF_CUST_NO().equals("null"))
        {
            this.setECIF_CUST_NO(null);
        }
        if(this.getCERT_TYPE().equals("null"))
        {
            this.setCERT_TYPE(null);
        }
        if(this.getCERT_NO().equals("null"))
        {
            this.setCERT_NO(null);
        }
        if(this.getCUST_NAME().equals("null"))
        {
            this.setCUST_NAME(null);
        }
        if(this.getPHONE_NO().equals("null"))
        {
            this.setPHONE_NO(null);
        }
        if(this.getMKTG_RESULT_ID().equals("null"))
        {
            this.setMKTG_RESULT_ID(null);
        }
        if(this.getTASK_NAME().equals("null"))
        {
            this.setTASK_NAME(null);
        }
        if(this.getOPER_TYPE().equals("null"))
        {
            this.setOPER_TYPE(null);
        }
        if(this.getMKTG_RESULT().equals("null"))
        {
            this.setMKTG_RESULT(null);
        }
        if(this.getMKTG_REMARK().equals("null"))
        {
            this.setMKTG_REMARK(null);
        }
        if(this.getMKTG_STAFF_NO().equals("null"))
        {
            this.setMKTG_STAFF_NO(null);
        }
        if(this.getMKTG_STAFF_NAME().equals("null"))
        {
            this.setMKTG_STAFF_NAME(null);
        }
        if(this.getMKTG_DATE().equals("null"))
        {
            this.setMKTG_DATE(null);
        }
        if(this.getMKTG_TIME().equals("null"))
        {
            this.setMKTG_TIME(null);
        }

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

    public String getMKTG_RESULT_ID()
    {
        return MKTG_RESULT_ID;
    }

    public void setMKTG_RESULT_ID(String MKTG_RESULT_ID)
    {
        this.MKTG_RESULT_ID = MKTG_RESULT_ID;
    }

    public String getTASK_NAME()
    {
        return TASK_NAME;
    }

    public void setTASK_NAME(String TASK_NAME)
    {
        this.TASK_NAME = TASK_NAME;
    }

    public String getOPER_TYPE()
    {
        return OPER_TYPE;
    }

    public void setOPER_TYPE(String OPER_TYPE)
    {
        this.OPER_TYPE = OPER_TYPE;
    }

    public String getMKTG_RESULT()
    {
        return MKTG_RESULT;
    }

    public void setMKTG_RESULT(String MKTG_RESULT)
    {
        this.MKTG_RESULT = MKTG_RESULT;
    }

    public String getMKTG_REMARK()
    {
        return MKTG_REMARK;
    }

    public void setMKTG_REMARK(String MKTG_REMARK)
    {
        this.MKTG_REMARK = MKTG_REMARK;
    }

    public String getMKTG_STAFF_NO()
    {
        return MKTG_STAFF_NO;
    }

    public void setMKTG_STAFF_NO(String MKTG_STAFF_NO)
    {
        this.MKTG_STAFF_NO = MKTG_STAFF_NO;
    }

    public String getMKTG_STAFF_NAME()
    {
        return MKTG_STAFF_NAME;
    }

    public void setMKTG_STAFF_NAME(String MKTG_STAFF_NAME)
    {
        this.MKTG_STAFF_NAME = MKTG_STAFF_NAME;
    }

    public String getMKTG_DATE()
    {
        return MKTG_DATE;
    }

    public void setMKTG_DATE(String MKTG_DATE)
    {
        this.MKTG_DATE = MKTG_DATE;
    }

    public Date getMKTG_TIME()
    {
        return MKTG_TIME;
    }

    public void setMKTG_TIME(Date MKTG_TIME)
    {
        this.MKTG_TIME = MKTG_TIME;
    }
}
