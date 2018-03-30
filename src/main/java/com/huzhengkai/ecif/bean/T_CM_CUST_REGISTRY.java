package com.huzhengkai.ecif.bean;

/**
 * Created by root on 2018/3/26.
 */
public class T_CM_CUST_REGISTRY
{
    private String ID;
    private String OPEND_ID;
    private String PHONE_NUM;
    private String APP_ID;
    private String INSERT_DATE;
    private String UPDT_DATE;
    private String CID;
    private String NAME;
    private String ID_CARD;
    private String STATUS;
    private String UPDATE_STATUS_DATE;

    public T_CM_CUST_REGISTRY()
    {
    }

    public T_CM_CUST_REGISTRY(String ID, String OPEND_ID, String PHONE_NUM, String APP_ID, String INSERT_DATE, String UPDT_DATE, String CID, String NAME, String ID_CARD, String STATUS, String UPDATE_STATUS_DATE)
    {
        this.ID = ID;
        this.OPEND_ID = OPEND_ID;
        this.PHONE_NUM = PHONE_NUM;
        this.APP_ID = APP_ID;
        this.INSERT_DATE = INSERT_DATE;
        this.UPDT_DATE = UPDT_DATE;
        this.CID = CID;
        this.NAME = NAME;
        this.ID_CARD = ID_CARD;
        this.STATUS = STATUS;
        this.UPDATE_STATUS_DATE = UPDATE_STATUS_DATE;
    }

    public void convertNull()
    {
        if(this.getID().equals("null"))
        {
            this.setID(null);
        }
        if(this.getOPEND_ID().equals("null"))
        {
            this.setOPEND_ID(null);
        }
        if(this.getPHONE_NUM().equals("null"))
        {
            this.setPHONE_NUM(null);
        }
        if(this.getAPP_ID().equals("null"))
        {
            this.setAPP_ID(null);
        }
        if(this.getINSERT_DATE().equals("null"))
        {
            this.setINSERT_DATE(null);
        }
        if(this.getUPDT_DATE().equals("null"))
        {
            this.setUPDT_DATE(null);
        }
        if(this.getCID().equals("null"))
        {
            this.setCID(null);
        }
        if(this.getNAME().equals("null"))
        {
            this.setNAME(null);
        }
        if(this.getID_CARD().equals("null"))
        {
            this.setID_CARD(null);
        }
        if(this.getSTATUS().equals("null"))
        {
            this.setSTATUS(null);
        }
        if(this.getUPDATE_STATUS_DATE().equals("null"))
        {
            this.setUPDATE_STATUS_DATE(null);
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

    public String getOPEND_ID()
    {
        return OPEND_ID;
    }

    public void setOPEND_ID(String OPEND_ID)
    {
        this.OPEND_ID = OPEND_ID;
    }

    public String getPHONE_NUM()
    {
        return PHONE_NUM;
    }

    public void setPHONE_NUM(String PHONE_NUM)
    {
        this.PHONE_NUM = PHONE_NUM;
    }

    public String getAPP_ID()
    {
        return APP_ID;
    }

    public void setAPP_ID(String APP_ID)
    {
        this.APP_ID = APP_ID;
    }

    public String getINSERT_DATE()
    {
        return INSERT_DATE;
    }

    public void setINSERT_DATE(String INSERT_DATE)
    {
        this.INSERT_DATE = INSERT_DATE;
    }

    public String getUPDT_DATE()
    {
        return UPDT_DATE;
    }

    public void setUPDT_DATE(String UPDT_DATE)
    {
        this.UPDT_DATE = UPDT_DATE;
    }

    public String getCID()
    {
        return CID;
    }

    public void setCID(String CID)
    {
        this.CID = CID;
    }

    public String getNAME()
    {
        return NAME;
    }

    public void setNAME(String NAME)
    {
        this.NAME = NAME;
    }

    public String getID_CARD()
    {
        return ID_CARD;
    }

    public void setID_CARD(String ID_CARD)
    {
        this.ID_CARD = ID_CARD;
    }

    public String getSTATUS()
    {
        return STATUS;
    }

    public void setSTATUS(String STATUS)
    {
        this.STATUS = STATUS;
    }

    public String getUPDATE_STATUS_DATE()
    {
        return UPDATE_STATUS_DATE;
    }

    public void setUPDATE_STATUS_DATE(String UPDATE_STATUS_DATE)
    {
        this.UPDATE_STATUS_DATE = UPDATE_STATUS_DATE;
    }
}
