package com.huzhengkai.ecif.bean;

/**
 * Created by root on 2018/3/28.
 */
public class T_CASH_CUST_INFO
{
    private String REG_MTH;
    private String LOAN_NO;
    private String SA_NAME;
    private String SA_PHONE;
    private Integer ACTU_INST_NUM;
    private Integer INST_NUM;
    private String QQ_NUM;
    private String CERT_NO;
    private String CUST_NAME;
    private String CUST_PHONE;
    private String REG_PROV;
    private String REG_CITY;
    private String STR_NAME;
    private Double INIT_CREDIT_LIMIT;
    private Double CREDIT_LIMIT;
    private String LVL;
    private String STATE;
    private String BUSI_DT;
    private String LIVE_PROV;
    private String LIVE_CITY;
    private String LIVE_AREA;
    private String TYPE;
    private String INVALID_DT;
    private String IS_KX;
    private String BRAND;

    public T_CASH_CUST_INFO()
    {
    }

    public T_CASH_CUST_INFO(String REG_MTH, String LOAN_NO, String SA_NAME, String SA_PHONE, Integer ACTU_INST_NUM, Integer INST_NUM, String QQ_NUM, String CERT_NO, String CUST_NAME, String CUST_PHONE, String REG_PROV, String REG_CITY, String STR_NAME, Double INIT_CREDIT_LIMIT, Double CREDIT_LIMIT, String LVL, String STATE, String BUSI_DT, String LIVE_PROV, String LIVE_CITY, String LIVE_AREA, String TYPE, String INVALID_DT, String IS_KX, String BRAND)
    {
        this.REG_MTH = REG_MTH;
        this.LOAN_NO = LOAN_NO;
        this.SA_NAME = SA_NAME;
        this.SA_PHONE = SA_PHONE;
        this.ACTU_INST_NUM = ACTU_INST_NUM;
        this.INST_NUM = INST_NUM;
        this.QQ_NUM = QQ_NUM;
        this.CERT_NO = CERT_NO;
        this.CUST_NAME = CUST_NAME;
        this.CUST_PHONE = CUST_PHONE;
        this.REG_PROV = REG_PROV;
        this.REG_CITY = REG_CITY;
        this.STR_NAME = STR_NAME;
        this.INIT_CREDIT_LIMIT = INIT_CREDIT_LIMIT;
        this.CREDIT_LIMIT = CREDIT_LIMIT;
        this.LVL = LVL;
        this.STATE = STATE;
        this.BUSI_DT = BUSI_DT;
        this.LIVE_PROV = LIVE_PROV;
        this.LIVE_CITY = LIVE_CITY;
        this.LIVE_AREA = LIVE_AREA;
        this.TYPE = TYPE;
        this.INVALID_DT = INVALID_DT;
        this.IS_KX = IS_KX;
        this.BRAND = BRAND;
    }
    public void convertNull()
    {
        if(this.getREG_MTH().equals("null"))
        {
            this.setREG_MTH(null);
        }
        if(this.getLOAN_NO().equals("null"))
        {
            this.setLOAN_NO(null);
        }
        if(this.getSA_NAME().equals("null"))
        {
            this.setSA_NAME(null);
        }
        if(this.getSA_PHONE().equals("null"))
        {
            this.setSA_PHONE(null);
        }
        if(this.getACTU_INST_NUM().equals("null"))
        {
            this.setACTU_INST_NUM(null);
        }
        if(this.getINST_NUM().equals("null"))
        {
            this.setINST_NUM(null);
        }
        if(this.getQQ_NUM().equals("null"))
        {
            this.setQQ_NUM(null);
        }
        if(this.getCERT_NO().equals("null"))
        {
            this.setCERT_NO(null);
        }
        if(this.getCUST_NAME().equals("null"))
        {
            this.setCUST_NAME(null);
        }
        if(this.getCUST_PHONE().equals("null"))
        {
            this.setCUST_PHONE(null);
        }
        if(this.getREG_PROV().equals("null"))
        {
            this.setREG_PROV(null);
        }
        if(this.getREG_CITY().equals("null"))
        {
            this.setREG_CITY(null);
        }
        if(this.getSTR_NAME().equals("null"))
        {
            this.setSTR_NAME(null);
        }
        if(this.getINIT_CREDIT_LIMIT().equals("null"))
        {
            this.setINIT_CREDIT_LIMIT(null);
        }
        if(this.getCREDIT_LIMIT().equals("null"))
        {
            this.setCREDIT_LIMIT(null);
        }
        if(this.getLVL().equals("null"))
        {
            this.setLVL(null);
        }
        if(this.getSTATE().equals("null"))
        {
            this.setSTATE(null);
        }
        if(this.getBUSI_DT().equals("null"))
        {
            this.setBUSI_DT(null);
        }
        if(this.getLIVE_PROV().equals("null"))
        {
            this.setLIVE_PROV(null);
        }
        if(this.getLIVE_CITY().equals("null"))
        {
            this.setLIVE_CITY(null);
        }
        if(this.getLIVE_AREA().equals("null"))
        {
            this.setLIVE_AREA(null);
        }
        if(this.getTYPE().equals("null"))
        {
            this.setTYPE(null);
        }
        if(this.getINVALID_DT().equals("null"))
        {
            this.setINVALID_DT(null);
        }
        if(this.getIS_KX().equals("null"))
        {
            this.setIS_KX(null);
        }
        if(this.getBRAND().equals("null"))
        {
            this.setBRAND(null);
        }
    }
    public String getREG_MTH()
    {
        return REG_MTH;
    }

    public void setREG_MTH(String REG_MTH)
    {
        this.REG_MTH = REG_MTH;
    }

    public String getLOAN_NO()
    {
        return LOAN_NO;
    }

    public void setLOAN_NO(String LOAN_NO)
    {
        this.LOAN_NO = LOAN_NO;
    }

    public String getSA_NAME()
    {
        return SA_NAME;
    }

    public void setSA_NAME(String SA_NAME)
    {
        this.SA_NAME = SA_NAME;
    }

    public String getSA_PHONE()
    {
        return SA_PHONE;
    }

    public void setSA_PHONE(String SA_PHONE)
    {
        this.SA_PHONE = SA_PHONE;
    }

    public Integer getACTU_INST_NUM()
    {
        return ACTU_INST_NUM;
    }

    public void setACTU_INST_NUM(Integer ACTU_INST_NUM)
    {
        this.ACTU_INST_NUM = ACTU_INST_NUM;
    }

    public Integer getINST_NUM()
    {
        return INST_NUM;
    }

    public void setINST_NUM(Integer INST_NUM)
    {
        this.INST_NUM = INST_NUM;
    }

    public String getQQ_NUM()
    {
        return QQ_NUM;
    }

    public void setQQ_NUM(String QQ_NUM)
    {
        this.QQ_NUM = QQ_NUM;
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

    public String getCUST_PHONE()
    {
        return CUST_PHONE;
    }

    public void setCUST_PHONE(String CUST_PHONE)
    {
        this.CUST_PHONE = CUST_PHONE;
    }

    public String getREG_PROV()
    {
        return REG_PROV;
    }

    public void setREG_PROV(String REG_PROV)
    {
        this.REG_PROV = REG_PROV;
    }

    public String getREG_CITY()
    {
        return REG_CITY;
    }

    public void setREG_CITY(String REG_CITY)
    {
        this.REG_CITY = REG_CITY;
    }

    public String getSTR_NAME()
    {
        return STR_NAME;
    }

    public void setSTR_NAME(String STR_NAME)
    {
        this.STR_NAME = STR_NAME;
    }

    public Double getINIT_CREDIT_LIMIT()
    {
        return INIT_CREDIT_LIMIT;
    }

    public void setINIT_CREDIT_LIMIT(Double INIT_CREDIT_LIMIT)
    {
        this.INIT_CREDIT_LIMIT = INIT_CREDIT_LIMIT;
    }

    public Double getCREDIT_LIMIT()
    {
        return CREDIT_LIMIT;
    }

    public void setCREDIT_LIMIT(Double CREDIT_LIMIT)
    {
        this.CREDIT_LIMIT = CREDIT_LIMIT;
    }

    public String getLVL()
    {
        return LVL;
    }

    public void setLVL(String LVL)
    {
        this.LVL = LVL;
    }

    public String getSTATE()
    {
        return STATE;
    }

    public void setSTATE(String STATE)
    {
        this.STATE = STATE;
    }

    public String getBUSI_DT()
    {
        return BUSI_DT;
    }

    public void setBUSI_DT(String BUSI_DT)
    {
        this.BUSI_DT = BUSI_DT;
    }

    public String getLIVE_PROV()
    {
        return LIVE_PROV;
    }

    public void setLIVE_PROV(String LIVE_PROV)
    {
        this.LIVE_PROV = LIVE_PROV;
    }

    public String getLIVE_CITY()
    {
        return LIVE_CITY;
    }

    public void setLIVE_CITY(String LIVE_CITY)
    {
        this.LIVE_CITY = LIVE_CITY;
    }

    public String getLIVE_AREA()
    {
        return LIVE_AREA;
    }

    public void setLIVE_AREA(String LIVE_AREA)
    {
        this.LIVE_AREA = LIVE_AREA;
    }

    public String getTYPE()
    {
        return TYPE;
    }

    public void setTYPE(String TYPE)
    {
        this.TYPE = TYPE;
    }

    public String getINVALID_DT()
    {
        return INVALID_DT;
    }

    public void setINVALID_DT(String INVALID_DT)
    {
        this.INVALID_DT = INVALID_DT;
    }

    public String getIS_KX()
    {
        return IS_KX;
    }

    public void setIS_KX(String IS_KX)
    {
        this.IS_KX = IS_KX;
    }

    public String getBRAND()
    {
        return BRAND;
    }

    public void setBRAND(String BRAND)
    {
        this.BRAND = BRAND;
    }
}
