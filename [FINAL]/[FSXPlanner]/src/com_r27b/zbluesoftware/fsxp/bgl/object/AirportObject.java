// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AirportObject.java

package com.zbluesoftware.fsxp.bgl.object;

import com.zbluesoftware.fsxp.bgl.item.*;

// Referenced classes of package com.zbluesoftware.fsxp.bgl.object:
//            BaseObject

public class AirportObject extends BaseObject
{

    public AirportObject()
    {
        this(0, 0);
    }

    public AirportObject(int offset, int length)
    {
        name = "Airport";
        this.offset = offset;
        this.length = length;
        addItem(new IDItem(), true);
        addItem(new SizeItem(), true);
        addItem(new RunwayCountItem(), true);
        addItem(new ComCountItem(), true);
        addItem(new StartCountItem(), true);
        addItem(new ApproachCountItem(), true);
        addItem(new ApronCountItem(), true);
        addItem(new HelipadCountItem(), true);
        addItem(new OptionItem("deleteFlag", 7, "Byte", 1), true);
        addItem(new LongitudeItem(), true);
        addItem(new LatitudeItem(), true);
        addItem(new AltitudeItem(), true);
        addItem(new TowerLongitudeItem(), true);
        addItem(new TowerLatitudeItem(), true);
        addItem(new TowerAltitudeItem(), true);
        addItem(new MagVarItem(), true);
        addItem(new IcaoItem(), true);
        addItem(new UnknownItem(), true);
        addItem(new ServicesItem(), true);
        addItem(new ScalarItem(), true);
    }

    public String toString()
    {
        return name;
    }

    public String getRegion()
    {
        return region;
    }

    public void setRegion(String region)
    {
        this.region = region;
    }

    public String getCountry()
    {
        return country;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getState()
    {
        return state;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    private String region;
    private String country;
    private String city;
    private String state;
    public static final String ID1 = "3C";
    public static final String ID2 = "00";
}
