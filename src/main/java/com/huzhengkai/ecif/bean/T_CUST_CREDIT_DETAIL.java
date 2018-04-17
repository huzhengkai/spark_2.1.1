package com.huzhengkai.ecif.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by root on 2018/3/28.
 */
public class T_CUST_CREDIT_DETAIL implements Serializable
{
    private String TABLE_KEY;
    private String CREDIT_ID;
    private String CREDIT_TYPE;
    private Double CREDIT_AMT;
    private Double CREDIT_USED_AMT;
    private Double CREDIT_REMAIN_AMT;
    private String CREDIT_BEG_DATE;
    private String CREDIT_END_DATE;
    private String CREDIT_INSERT_DATE;
    private String STATE;
    private String OTHER_STATE;
    private String REMARK;
    private String PROD_TYPE;

    public T_CUST_CREDIT_DETAIL()
    {
    }

    public T_CUST_CREDIT_DETAIL(String TABLE_KEY, String CREDIT_ID, String CREDIT_TYPE, Double CREDIT_AMT, Double CREDIT_USED_AMT, Double CREDIT_REMAIN_AMT, String CREDIT_BEG_DATE, String CREDIT_END_DATE, String CREDIT_INSERT_DATE, String STATE, String OTHER_STATE, String REMARK, String PROD_TYPE)
    {
        this.TABLE_KEY = TABLE_KEY;
        this.CREDIT_ID = CREDIT_ID;
        this.CREDIT_TYPE = CREDIT_TYPE;
        this.CREDIT_AMT = CREDIT_AMT;
        this.CREDIT_USED_AMT = CREDIT_USED_AMT;
        this.CREDIT_REMAIN_AMT = CREDIT_REMAIN_AMT;
        this.CREDIT_BEG_DATE = CREDIT_BEG_DATE;
        this.CREDIT_END_DATE = CREDIT_END_DATE;
        this.CREDIT_INSERT_DATE = CREDIT_INSERT_DATE;
        this.STATE = STATE;
        this.OTHER_STATE = OTHER_STATE;
        this.REMARK = REMARK;
        this.PROD_TYPE = PROD_TYPE;
    }

    public void convertNull()
    {
        if (this.getTABLE_KEY().equals("null"))
        {
            this.setTABLE_KEY(null);
        }
        if (this.getCREDIT_ID().equals("null"))
        {
            this.setCREDIT_ID(null);
        }
        if (this.getCREDIT_TYPE().equals("null"))
        {
            this.setCREDIT_TYPE(null);
        }
        if (this.getCREDIT_AMT().equals("null"))
        {
            this.setCREDIT_AMT(null);
        }
        if (this.getCREDIT_USED_AMT().equals("null"))
        {
            this.setCREDIT_USED_AMT(null);
        }
        if (this.getCREDIT_REMAIN_AMT().equals("null"))
        {
            this.setCREDIT_REMAIN_AMT(null);
        }
        if (this.getCREDIT_BEG_DATE().equals("null"))
        {
            this.setCREDIT_BEG_DATE(null);
        }
        if (this.getCREDIT_END_DATE().equals("null"))
        {
            this.setCREDIT_END_DATE(null);
        }
        if (this.getCREDIT_INSERT_DATE().equals("null"))
        {
            this.setCREDIT_INSERT_DATE(null);
        }
        if (this.getSTATE().equals("null"))
        {
            this.setSTATE(null);
        }
        if (this.getOTHER_STATE().equals("null"))
        {
            this.setOTHER_STATE(null);
        }
        if (this.getREMARK().equals("null"))
        {
            this.setREMARK(null);
        }
        if (this.getPROD_TYPE().equals("null"))
        {
            this.setPROD_TYPE(null);
        }

    }
    public String getTABLE_KEY()
    {
        return TABLE_KEY;
    }

    public void setTABLE_KEY(String TABLE_KEY)
    {
        this.TABLE_KEY = TABLE_KEY;
    }

    public String getCREDIT_ID()
    {
        return CREDIT_ID;
    }

    public void setCREDIT_ID(String CREDIT_ID)
    {
        this.CREDIT_ID = CREDIT_ID;
    }

    public String getCREDIT_TYPE()
    {
        return CREDIT_TYPE;
    }

    public void setCREDIT_TYPE(String CREDIT_TYPE)
    {
        this.CREDIT_TYPE = CREDIT_TYPE;
    }

    public Double getCREDIT_AMT()
    {
        return CREDIT_AMT;
    }

    public void setCREDIT_AMT(Double CREDIT_AMT)
    {
        this.CREDIT_AMT = CREDIT_AMT;
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

    public String getCREDIT_BEG_DATE()
    {
        return CREDIT_BEG_DATE;
    }

    public void setCREDIT_BEG_DATE(String CREDIT_BEG_DATE)
    {
        this.CREDIT_BEG_DATE = CREDIT_BEG_DATE;
    }

    public String getCREDIT_END_DATE()
    {
        return CREDIT_END_DATE;
    }

    public void setCREDIT_END_DATE(String CREDIT_END_DATE)
    {
        this.CREDIT_END_DATE = CREDIT_END_DATE;
    }

    public String getCREDIT_INSERT_DATE()
    {
        return CREDIT_INSERT_DATE;
    }

    public void setCREDIT_INSERT_DATE(String CREDIT_INSERT_DATE)
    {
        this.CREDIT_INSERT_DATE = CREDIT_INSERT_DATE;
    }

    public String getSTATE()
    {
        return STATE;
    }

    public void setSTATE(String STATE)
    {
        this.STATE = STATE;
    }

    public String getOTHER_STATE()
    {
        return OTHER_STATE;
    }

    public void setOTHER_STATE(String OTHER_STATE)
    {
        this.OTHER_STATE = OTHER_STATE;
    }

    public String getREMARK()
    {
        return REMARK;
    }

    public void setREMARK(String REMARK)
    {
        this.REMARK = REMARK;
    }

    public String getPROD_TYPE()
    {
        return PROD_TYPE;
    }

    public void setPROD_TYPE(String PROD_TYPE)
    {
        this.PROD_TYPE = PROD_TYPE;
    }
}
