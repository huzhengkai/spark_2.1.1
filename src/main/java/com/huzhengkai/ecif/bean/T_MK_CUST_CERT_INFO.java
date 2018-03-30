package com.huzhengkai.ecif.bean;

import java.io.Serializable;

/**
 * Created by root on 2018/3/26.
 */
public class T_MK_CUST_CERT_INFO implements Serializable
{
    private String ID;
    private String CID;
    private String CERT_TYPE;
    private String CERT_NAME;
    private String CERT_NO;
    private String SEX;
    private String NATION;
    private String IS_PERMANENT;
    private String VALID_START_DATE;
    private String VALID_END_DATE;
    private String SIGN_ORG;
    private String PROV_NO;
    private String CITY_NO;
    private String AREA_NO;
    private String ADDR;
    private String IS_AUTH;
    private String AUTH_DATE;
    private String AUTH_INVALID_DATE;
    private String INSERT_DATE;
    private String MODIFY_DATE;
    private String CUSTOMER_TYPE;

    public T_MK_CUST_CERT_INFO()
    {
    }

    public T_MK_CUST_CERT_INFO(String ID, String CID, String CERT_TYPE, String CERT_NAME, String CERT_NO, String SEX, String NATION, String IS_PERMANENT, String VALID_START_DATE, String VALID_END_DATE, String SIGN_ORG, String PROV_NO, String CITY_NO, String AREA_NO, String ADDR, String IS_AUTH, String AUTH_DATE, String AUTH_INVALID_DATE, String INSERT_DATE, String MODIFY_DATE, String CUSTOMER_TYPE)
    {
        this.ID = ID;
        this.CID = CID;
        this.CERT_TYPE = CERT_TYPE;
        this.CERT_NAME = CERT_NAME;
        this.CERT_NO = CERT_NO;
        this.SEX = SEX;
        this.NATION = NATION;
        this.IS_PERMANENT = IS_PERMANENT;
        this.VALID_START_DATE = VALID_START_DATE;
        this.VALID_END_DATE = VALID_END_DATE;
        this.SIGN_ORG = SIGN_ORG;
        this.PROV_NO = PROV_NO;
        this.CITY_NO = CITY_NO;
        this.AREA_NO = AREA_NO;
        this.ADDR = ADDR;
        this.IS_AUTH = IS_AUTH;
        this.AUTH_DATE = AUTH_DATE;
        this.AUTH_INVALID_DATE = AUTH_INVALID_DATE;
        this.INSERT_DATE = INSERT_DATE;
        this.MODIFY_DATE = MODIFY_DATE;
        this.CUSTOMER_TYPE = CUSTOMER_TYPE;
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
        if(this.getCERT_TYPE().equals("null"))
        {
            this.setCERT_TYPE(null);
        }
        if(this.getCERT_NAME().equals("null"))
        {
            this.setCERT_NAME(null);
        }
        if(this.getCERT_NO().equals("null"))
        {
            this.setCERT_NO(null);
        }
        if(this.getSEX().equals("null"))
        {
            this.setSEX(null);
        }
        if(this.getNATION().equals("null"))
        {
            this.setNATION(null);
        }
        if(this.getIS_PERMANENT().equals("null"))
        {
            this.setIS_PERMANENT(null);
        }
        if(this.getVALID_START_DATE().equals("null"))
        {
            this.setVALID_START_DATE(null);
        }
        if(this.getVALID_END_DATE().equals("null"))
        {
            this.setVALID_END_DATE(null);
        }
        if(this.getSIGN_ORG().equals("null"))
        {
            this.setSIGN_ORG(null);
        }
        if(this.getPROV_NO().equals("null"))
        {
            this.setPROV_NO(null);
        }
        if(this.getCITY_NO().equals("null"))
        {
            this.setCITY_NO(null);
        }
        if(this.getAREA_NO().equals("null"))
        {
            this.setAREA_NO(null);
        }
        if(this.getADDR().equals("null"))
        {
            this.setADDR(null);
        }
        if(this.getIS_AUTH().equals("null"))
        {
            this.setIS_AUTH(null);
        }
        if(this.getAUTH_DATE().equals("null"))
        {
            this.setAUTH_DATE(null);
        }
        if(this.getAUTH_INVALID_DATE().equals("null"))
        {
            this.setAUTH_INVALID_DATE(null);
        }
        if(this.getINSERT_DATE().equals("null"))
        {
            this.setINSERT_DATE(null);
        }
        if(this.getMODIFY_DATE().equals("null"))
        {
            this.setMODIFY_DATE(null);
        }
        if(this.getCUSTOMER_TYPE().equals("null"))
        {
            this.setCUSTOMER_TYPE(null);
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

    public String getCERT_TYPE()
    {
        return CERT_TYPE;
    }

    public void setCERT_TYPE(String CERT_TYPE)
    {
        this.CERT_TYPE = CERT_TYPE;
    }

    public String getCERT_NAME()
    {
        return CERT_NAME;
    }

    public void setCERT_NAME(String CERT_NAME)
    {
        this.CERT_NAME = CERT_NAME;
    }

    public String getCERT_NO()
    {
        return CERT_NO;
    }

    public void setCERT_NO(String CERT_NO)
    {
        this.CERT_NO = CERT_NO;
    }

    public String getSEX()
    {
        return SEX;
    }

    public void setSEX(String SEX)
    {
        this.SEX = SEX;
    }

    public String getNATION()
    {
        return NATION;
    }

    public void setNATION(String NATION)
    {
        this.NATION = NATION;
    }

    public String getIS_PERMANENT()
    {
        return IS_PERMANENT;
    }

    public void setIS_PERMANENT(String IS_PERMANENT)
    {
        this.IS_PERMANENT = IS_PERMANENT;
    }

    public String getVALID_START_DATE()
    {
        return VALID_START_DATE;
    }

    public void setVALID_START_DATE(String VALID_START_DATE)
    {
        this.VALID_START_DATE = VALID_START_DATE;
    }

    public String getVALID_END_DATE()
    {
        return VALID_END_DATE;
    }

    public void setVALID_END_DATE(String VALID_END_DATE)
    {
        this.VALID_END_DATE = VALID_END_DATE;
    }

    public String getSIGN_ORG()
    {
        return SIGN_ORG;
    }

    public void setSIGN_ORG(String SIGN_ORG)
    {
        this.SIGN_ORG = SIGN_ORG;
    }

    public String getPROV_NO()
    {
        return PROV_NO;
    }

    public void setPROV_NO(String PROV_NO)
    {
        this.PROV_NO = PROV_NO;
    }

    public String getCITY_NO()
    {
        return CITY_NO;
    }

    public void setCITY_NO(String CITY_NO)
    {
        this.CITY_NO = CITY_NO;
    }

    public String getAREA_NO()
    {
        return AREA_NO;
    }

    public void setAREA_NO(String AREA_NO)
    {
        this.AREA_NO = AREA_NO;
    }

    public String getADDR()
    {
        return ADDR;
    }

    public void setADDR(String ADDR)
    {
        this.ADDR = ADDR;
    }

    public String getIS_AUTH()
    {
        return IS_AUTH;
    }

    public void setIS_AUTH(String IS_AUTH)
    {
        this.IS_AUTH = IS_AUTH;
    }

    public String getAUTH_DATE()
    {
        return AUTH_DATE;
    }

    public void setAUTH_DATE(String AUTH_DATE)
    {
        this.AUTH_DATE = AUTH_DATE;
    }

    public String getAUTH_INVALID_DATE()
    {
        return AUTH_INVALID_DATE;
    }

    public void setAUTH_INVALID_DATE(String AUTH_INVALID_DATE)
    {
        this.AUTH_INVALID_DATE = AUTH_INVALID_DATE;
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

    public String getCUSTOMER_TYPE()
    {
        return CUSTOMER_TYPE;
    }

    public void setCUSTOMER_TYPE(String CUSTOMER_TYPE)
    {
        this.CUSTOMER_TYPE = CUSTOMER_TYPE;
    }

    @Override
    public String toString()
    {
        return "T_MK_CUST_CERT_INFO{" +
                "ID='" + ID + '\'' +
                ", CID='" + CID + '\'' +
                ", CERT_TYPE='" + CERT_TYPE + '\'' +
                ", CERT_NAME='" + CERT_NAME + '\'' +
                ", CERT_NO='" + CERT_NO + '\'' +
                ", SEX='" + SEX + '\'' +
                ", NATION='" + NATION + '\'' +
                ", IS_PERMANENT='" + IS_PERMANENT + '\'' +
                ", VALID_START_DATE='" + VALID_START_DATE + '\'' +
                ", VALID_END_DATE='" + VALID_END_DATE + '\'' +
                ", SIGN_ORG='" + SIGN_ORG + '\'' +
                ", PROV_NO='" + PROV_NO + '\'' +
                ", CITY_NO='" + CITY_NO + '\'' +
                ", AREA_NO='" + AREA_NO + '\'' +
                ", ADDR='" + ADDR + '\'' +
                ", IS_AUTH='" + IS_AUTH + '\'' +
                ", AUTH_DATE='" + AUTH_DATE + '\'' +
                ", AUTH_INVALID_DATE='" + AUTH_INVALID_DATE + '\'' +
                ", INSERT_DATE='" + INSERT_DATE + '\'' +
                ", MODIFY_DATE='" + MODIFY_DATE + '\'' +
                ", CUSTOMER_TYPE='" + CUSTOMER_TYPE + '\'' +
                '}';
    }
}
