// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   HistoryModel.java

package com.zbluesoftware.fsxp.model;

import java.util.Date;

// Referenced classes of package com.zbluesoftware.fsxp.model:
//            BaseModel

public class HistoryModel
{

    public HistoryModel()
    {
        this("", "", "", null, null, null);
    }

    public HistoryModel(String setMethodName, String propertyDesc, String airportName, BaseModel baseModel, Object newValue, Object oldValue)
    {
        this(setMethodName, null, propertyDesc, airportName, baseModel, newValue, oldValue);
    }

    public HistoryModel(String setMethodName, String getMethodName, String propertyDesc, String airportName, BaseModel baseModel, Object newValue, Object oldValue)
    {
        this.setMethodName = setMethodName;
        this.propertyDesc = propertyDesc;
        this.airportName = airportName;
        this.baseModel = baseModel;
        this.newValue = newValue;
        this.oldValue = oldValue;
        date = new Date();
        if(getMethodName == null && setMethodName.length() > 1)
            this.getMethodName = (new StringBuilder()).append("g").append(setMethodName.substring(1)).toString();
        else
            this.getMethodName = getMethodName;
    }

    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    public String getAirportName()
    {
        return airportName;
    }

    public void setAirportName(String airportName)
    {
        this.airportName = airportName;
    }

    public BaseModel getBaseModel()
    {
        return baseModel;
    }

    public void setBaseModel(BaseModel baseModel)
    {
        this.baseModel = baseModel;
    }

    public String getPropertyDesc()
    {
        return propertyDesc;
    }

    public void setPropertyDesc(String propertyDesc)
    {
        this.propertyDesc = propertyDesc;
    }

    public String getSetMethodName()
    {
        return setMethodName;
    }

    public void setSetMethodName(String setMethodName)
    {
        this.setMethodName = setMethodName;
    }

    public String getGetMethodName()
    {
        return getMethodName;
    }

    public void setGetMethodName(String getMethodName)
    {
        this.getMethodName = getMethodName;
    }

    public Object getOldValue()
    {
        return oldValue;
    }

    public void setOldValue(Object oldValue)
    {
        this.oldValue = oldValue;
    }

    public Object getNewValue()
    {
        return newValue;
    }

    public void setNewValue(Object newValue)
    {
        this.newValue = newValue;
    }

    private BaseModel baseModel;
    private Object oldValue;
    private Object newValue;
    private String setMethodName;
    private String getMethodName;
    private String propertyDesc;
    private String airportName;
    private Date date;
}
