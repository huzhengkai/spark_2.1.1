package com.huzhengkai.ecif.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by root on 2018/3/28.
 */
public class I_CUST_CONV implements Serializable
{
    private String CUST_NO;
    private String SYS_ID;
    private String CERT_TYPE;
    private String CERT_NO;
    private String CUST_NAME;
    private String ECIF_CUST_NO;
    private String INSERT_TIME;
    private String VALID_FLAG;
    private String BUSI_DT;
    private String UPDATE_TIME;
    private String LOAN_NO;

    public I_CUST_CONV()
    {
    }

    public I_CUST_CONV(String CUST_NO, String SYS_ID, String CERT_TYPE, String CERT_NO, String CUST_NAME, String ECIF_CUST_NO, String INSERT_TIME, String VALID_FLAG, String BUSI_DT, String UPDATE_TIME, String LOAN_NO)
    {
        this.CUST_NO = CUST_NO;
        this.SYS_ID = SYS_ID;
        this.CERT_TYPE = CERT_TYPE;
        this.CERT_NO = CERT_NO;
        this.CUST_NAME = CUST_NAME;
        this.ECIF_CUST_NO = ECIF_CUST_NO;
        this.INSERT_TIME = INSERT_TIME;
        this.VALID_FLAG = VALID_FLAG;
        this.BUSI_DT = BUSI_DT;
        this.UPDATE_TIME = UPDATE_TIME;
        this.LOAN_NO = LOAN_NO;
    }

    public void convertNull()
    {
        if (this.getCUST_NO().equals("null"))
        {
            this.setCUST_NO(null);
        }
        if (this.getSYS_ID().equals("null"))
        {
            this.setSYS_ID(null);
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
        if (this.getECIF_CUST_NO().equals("null"))
        {
            this.setECIF_CUST_NO(null);
        }
        if (this.getINSERT_TIME().equals("null"))
        {
            this.setINSERT_TIME(null);
        }
        if (this.getVALID_FLAG().equals("null"))
        {
            this.setVALID_FLAG(null);
        }
        if (this.getBUSI_DT().equals("null"))
        {
            this.setBUSI_DT(null);
        }
        if (this.getUPDATE_TIME().equals("null"))
        {
            this.setUPDATE_TIME(null);
        }
        if (this.getLOAN_NO().equals("null"))
        {
            this.setLOAN_NO(null);
        }
    }
    public String getCUST_NO()
    {
        return CUST_NO;
    }

    public void setCUST_NO(String CUST_NO)
    {
        this.CUST_NO = CUST_NO;
    }

    public String getSYS_ID()
    {
        return SYS_ID;
    }

    public void setSYS_ID(String SYS_ID)
    {
        this.SYS_ID = SYS_ID;
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

    public String getECIF_CUST_NO()
    {
        return ECIF_CUST_NO;
    }

    public void setECIF_CUST_NO(String ECIF_CUST_NO)
    {
        this.ECIF_CUST_NO = ECIF_CUST_NO;
    }

    public String getINSERT_TIME()
    {
        return INSERT_TIME;
    }

    public void setINSERT_TIME(String INSERT_TIME)
    {
        this.INSERT_TIME = INSERT_TIME;
    }

    public String getVALID_FLAG()
    {
        return VALID_FLAG;
    }

    public void setVALID_FLAG(String VALID_FLAG)
    {
        this.VALID_FLAG = VALID_FLAG;
    }

    public String getBUSI_DT()
    {
        return BUSI_DT;
    }

    public void setBUSI_DT(String BUSI_DT)
    {
        this.BUSI_DT = BUSI_DT;
    }

    public String getUPDATE_TIME()
    {
        return UPDATE_TIME;
    }

    public void setUPDATE_TIME(String UPDATE_TIME)
    {
        this.UPDATE_TIME = UPDATE_TIME;
    }

    public String getLOAN_NO()
    {
        return LOAN_NO;
    }

    public void setLOAN_NO(String LOAN_NO)
    {
        this.LOAN_NO = LOAN_NO;
    }
}
