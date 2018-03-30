package com.huzhengkai.ecif.bean;

import java.io.Serializable;

/**
 * Created by root on 2018/3/26.
 */
public class T_WE_USER_INFO implements Serializable
{
    private String ID;
    private String OPEN_ID;
    private String WECHAT_TYPE;
    private String SUBSCRIBE;
    private String NICK_NAME;
    private String SEX;
    private String CITY;
    private String COUNTRY;
    private String PROVINCE;
    private String LANGUAGE;
    private String HEAD_IMG_URL;
    private String SUBSCRIBE_TIME;
    private String UNION_ID;
    private String REMARK;
    private String GROUP_ID;
    private String TICKET;
    private String RECOMMEND_NAME;
    private String RECOMMEND;
    private String TICKET_TIME;
    private String UN_SUBSCRIBE_TIME;
    private String UPDATE_TIME;

    public T_WE_USER_INFO()
    {
    }

    public T_WE_USER_INFO(String ID, String OPEN_ID, String WECHAT_TYPE, String SUBSCRIBE, String NICK_NAME, String SEX, String CITY, String COUNTRY, String PROVINCE, String LANGUAGE, String HEAD_IMG_URL, String SUBSCRIBE_TIME, String UNION_ID, String REMARK, String GROUP_ID, String TICKET, String RECOMMEND_NAME, String RECOMMEND, String TICKET_TIME, String UN_SUBSCRIBE_TIME, String UPDATE_TIME)
    {
        this.ID = ID;
        this.OPEN_ID = OPEN_ID;
        this.WECHAT_TYPE = WECHAT_TYPE;
        this.SUBSCRIBE = SUBSCRIBE;
        this.NICK_NAME = NICK_NAME;
        this.SEX = SEX;
        this.CITY = CITY;
        this.COUNTRY = COUNTRY;
        this.PROVINCE = PROVINCE;
        this.LANGUAGE = LANGUAGE;
        this.HEAD_IMG_URL = HEAD_IMG_URL;
        this.SUBSCRIBE_TIME = SUBSCRIBE_TIME;
        this.UNION_ID = UNION_ID;
        this.REMARK = REMARK;
        this.GROUP_ID = GROUP_ID;
        this.TICKET = TICKET;
        this.RECOMMEND_NAME = RECOMMEND_NAME;
        this.RECOMMEND = RECOMMEND;
        this.TICKET_TIME = TICKET_TIME;
        this.UN_SUBSCRIBE_TIME = UN_SUBSCRIBE_TIME;
        this.UPDATE_TIME = UPDATE_TIME;
    }

    public void convertNull()
    {
        if(this.getID().equals("null"))
        {
            this.setID(null);
        }
        if(this.getOPEN_ID().equals("null"))
        {
            this.setOPEN_ID(null);
        }
        if(this.getWECHAT_TYPE().equals("null"))
        {
            this.setWECHAT_TYPE(null);
        }
        if(this.getSUBSCRIBE().equals("null"))
        {
            this.setSUBSCRIBE(null);
        }
        if(this.getNICK_NAME().equals("null"))
        {
            this.setNICK_NAME(null);
        }
        if(this.getSEX().equals("null"))
        {
            this.setSEX(null);
        }
        if(this.getCITY().equals("null"))
        {
            this.setCITY(null);
        }
        if(this.getCOUNTRY().equals("null"))
        {
            this.setCOUNTRY(null);
        }
        if(this.getPROVINCE().equals("null"))
        {
            this.setPROVINCE(null);
        }
        if(this.getLANGUAGE().equals("null"))
        {
            this.setLANGUAGE(null);
        }
        if(this.getHEAD_IMG_URL().equals("null"))
        {
            this.setHEAD_IMG_URL(null);
        }
        if(this.getSUBSCRIBE_TIME().equals("null"))
        {
            this.setSUBSCRIBE_TIME(null);
        }
        if(this.getUNION_ID().equals("null"))
        {
            this.setUNION_ID(null);
        }
        if(this.getREMARK().equals("null"))
        {
            this.setREMARK(null);
        }
        if(this.getGROUP_ID().equals("null"))
        {
            this.setGROUP_ID(null);
        }
        if(this.getTICKET().equals("null"))
        {
            this.setTICKET(null);
        }
        if(this.getRECOMMEND_NAME().equals("null"))
        {
            this.setRECOMMEND_NAME(null);
        }
        if(this.getRECOMMEND().equals("null"))
        {
            this.setRECOMMEND(null);
        }
        if(this.getTICKET_TIME().equals("null"))
        {
            this.setTICKET_TIME(null);
        }
        if(this.getUN_SUBSCRIBE_TIME().equals("null"))
        {
            this.setUN_SUBSCRIBE_TIME(null);
        }
        if(this.getUPDATE_TIME().equals("null"))
        {
            this.setUPDATE_TIME(null);
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

    public String getOPEN_ID()
    {
        return OPEN_ID;
    }

    public void setOPEN_ID(String OPEN_ID)
    {
        this.OPEN_ID = OPEN_ID;
    }

    public String getWECHAT_TYPE()
    {
        return WECHAT_TYPE;
    }

    public void setWECHAT_TYPE(String WECHAT_TYPE)
    {
        this.WECHAT_TYPE = WECHAT_TYPE;
    }

    public String getSUBSCRIBE()
    {
        return SUBSCRIBE;
    }

    public void setSUBSCRIBE(String SUBSCRIBE)
    {
        this.SUBSCRIBE = SUBSCRIBE;
    }

    public String getNICK_NAME()
    {
        return NICK_NAME;
    }

    public void setNICK_NAME(String NICK_NAME)
    {
        this.NICK_NAME = NICK_NAME;
    }

    public String getSEX()
    {
        return SEX;
    }

    public void setSEX(String SEX)
    {
        this.SEX = SEX;
    }

    public String getCITY()
    {
        return CITY;
    }

    public void setCITY(String CITY)
    {
        this.CITY = CITY;
    }

    public String getCOUNTRY()
    {
        return COUNTRY;
    }

    public void setCOUNTRY(String COUNTRY)
    {
        this.COUNTRY = COUNTRY;
    }

    public String getPROVINCE()
    {
        return PROVINCE;
    }

    public void setPROVINCE(String PROVINCE)
    {
        this.PROVINCE = PROVINCE;
    }

    public String getLANGUAGE()
    {
        return LANGUAGE;
    }

    public void setLANGUAGE(String LANGUAGE)
    {
        this.LANGUAGE = LANGUAGE;
    }

    public String getHEAD_IMG_URL()
    {
        return HEAD_IMG_URL;
    }

    public void setHEAD_IMG_URL(String HEAD_IMG_URL)
    {
        this.HEAD_IMG_URL = HEAD_IMG_URL;
    }

    public String getSUBSCRIBE_TIME()
    {
        return SUBSCRIBE_TIME;
    }

    public void setSUBSCRIBE_TIME(String SUBSCRIBE_TIME)
    {
        this.SUBSCRIBE_TIME = SUBSCRIBE_TIME;
    }

    public String getUNION_ID()
    {
        return UNION_ID;
    }

    public void setUNION_ID(String UNION_ID)
    {
        this.UNION_ID = UNION_ID;
    }

    public String getREMARK()
    {
        return REMARK;
    }

    public void setREMARK(String REMARK)
    {
        this.REMARK = REMARK;
    }

    public String getGROUP_ID()
    {
        return GROUP_ID;
    }

    public void setGROUP_ID(String GROUP_ID)
    {
        this.GROUP_ID = GROUP_ID;
    }

    public String getTICKET()
    {
        return TICKET;
    }

    public void setTICKET(String TICKET)
    {
        this.TICKET = TICKET;
    }

    public String getRECOMMEND_NAME()
    {
        return RECOMMEND_NAME;
    }

    public void setRECOMMEND_NAME(String RECOMMEND_NAME)
    {
        this.RECOMMEND_NAME = RECOMMEND_NAME;
    }

    public String getRECOMMEND()
    {
        return RECOMMEND;
    }

    public void setRECOMMEND(String RECOMMEND)
    {
        this.RECOMMEND = RECOMMEND;
    }

    public String getTICKET_TIME()
    {
        return TICKET_TIME;
    }

    public void setTICKET_TIME(String TICKET_TIME)
    {
        this.TICKET_TIME = TICKET_TIME;
    }

    public String getUN_SUBSCRIBE_TIME()
    {
        return UN_SUBSCRIBE_TIME;
    }

    public void setUN_SUBSCRIBE_TIME(String UN_SUBSCRIBE_TIME)
    {
        this.UN_SUBSCRIBE_TIME = UN_SUBSCRIBE_TIME;
    }

    public String getUPDATE_TIME()
    {
        return UPDATE_TIME;
    }

    public void setUPDATE_TIME(String UPDATE_TIME)
    {
        this.UPDATE_TIME = UPDATE_TIME;
    }
}
