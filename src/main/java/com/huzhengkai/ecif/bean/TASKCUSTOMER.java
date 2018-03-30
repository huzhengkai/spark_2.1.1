package com.huzhengkai.ecif.bean;

/**
 * Created by root on 2018/3/28.
 */
public class TASKCUSTOMER
{
    private String TASKID;
    private String GROUPID;
    private String STATUS;
    private String TASKNAME;
    private String CREATETIME;
    private String LOGINNO;

    public TASKCUSTOMER()
    {
    }

    public TASKCUSTOMER(String TASKID, String GROUPID, String STATUS, String TASKNAME, String CREATETIME, String LOGINNO)
    {
        this.TASKID = TASKID;
        this.GROUPID = GROUPID;
        this.STATUS = STATUS;
        this.TASKNAME = TASKNAME;
        this.CREATETIME = CREATETIME;
        this.LOGINNO = LOGINNO;
    }
    public void convertNull()
    {
        if(this.getTASKID().equals("null"))
        {
            this.setTASKID(null);
        }
        if(this.getGROUPID().equals("null"))
        {
            this.setGROUPID(null);
        }
        if(this.getSTATUS().equals("null"))
        {
            this.setSTATUS(null);
        }
        if(this.getTASKNAME().equals("null"))
        {
            this.setTASKNAME(null);
        }
        if(this.getCREATETIME().equals("null"))
        {
            this.setCREATETIME(null);
        }
        if(this.getLOGINNO().equals("null"))
        {
            this.setLOGINNO(null);
        }

    }
    public String getTASKID()
    {
        return TASKID;
    }

    public void setTASKID(String TASKID)
    {
        this.TASKID = TASKID;
    }

    public String getGROUPID()
    {
        return GROUPID;
    }

    public void setGROUPID(String GROUPID)
    {
        this.GROUPID = GROUPID;
    }

    public String getSTATUS()
    {
        return STATUS;
    }

    public void setSTATUS(String STATUS)
    {
        this.STATUS = STATUS;
    }

    public String getTASKNAME()
    {
        return TASKNAME;
    }

    public void setTASKNAME(String TASKNAME)
    {
        this.TASKNAME = TASKNAME;
    }

    public String getCREATETIME()
    {
        return CREATETIME;
    }

    public void setCREATETIME(String CREATETIME)
    {
        this.CREATETIME = CREATETIME;
    }

    public String getLOGINNO()
    {
        return LOGINNO;
    }

    public void setLOGINNO(String LOGINNO)
    {
        this.LOGINNO = LOGINNO;
    }
}
