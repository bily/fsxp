// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RunwayObject.java

package com.zbluesoftware.fsxp.bgl.object;

import com.zbluesoftware.fsxp.bgl.item.*;

// Referenced classes of package com.zbluesoftware.fsxp.bgl.object:
//            BaseObject

public class RunwayObject extends BaseObject
{

    public RunwayObject()
    {
        this(0, 0);
    }

    public RunwayObject(int offset, int length)
    {
        name = "Runway";
        this.offset = offset;
        this.length = length;
        addItem(new IDItem(), true);
        addItem(new SizeItem(), true);
        addItem(new SurfaceItem(), true);
        addItem(new RunwayNumberItem("primaryRunwayNumber"), true);
        addItem(new RunwayDesigItem("primaryRunwayDesig"), true);
        addItem(new RunwayNumberItem("secondaryRunwayNumber"), true);
        addItem(new RunwayDesigItem("secondaryRunwayDesig"), true);
        addItem(new IcaoItem("primaryILSIcao", false), true);
        addItem(new IcaoItem("secondaryILSIcao", false), true);
        addItem(new LongitudeItem(), true);
        addItem(new LatitudeItem(), true);
        addItem(new AltitudeItem(), true);
        addItem(new LengthItem(), true);
        addItem(new WidthItem(), true);
        addItem(new HeadingItem(), true);
        addItem(new WidthItem("patternAltitude"), true);
        addItem(new RunwayMarkingItem("edges", 0), true);
        addItem(new RunwayMarkingItem("threshold", 1), true);
        addItem(new RunwayMarkingItem("fixedDistance", 2), true);
        addItem(new RunwayMarkingItem("touchdown", 3), true);
        addItem(new RunwayMarkingItem("dashes", 4), true);
        addItem(new RunwayMarkingItem("identDisplayed", 5), true);
        addItem(new RunwayMarkingItem("precision", 6), true);
        addItem(new RunwayMarkingItem("edgePavement", 7), true);
        addItem(new RunwayMarkingItem("singleEnd", 8), true);
        addItem(new RunwayMarkingItem("primaryClosed", 9), true);
        addItem(new RunwayMarkingItem("secondaryClosed", 10), true);
        addItem(new RunwayMarkingItem("primaryStol", 11), true);
        addItem(new RunwayMarkingItem("secondaryStol", 12), true);
        addItem(new RunwayMarkingItem("altThreshold", 13), true);
        addItem(new RunwayMarkingItem("altFixedDistance", 14), true);
        addItem(new RunwayMarkingItem("altTouchdown", 15), true);
        addItem(new RunwayLightItem("edgeLights", 0, 1), true);
        addItem(new RunwayLightItem("centerLights", 2, 3), true);
        addItem(new RunwayLightItem("centerRed", 4, -1), true);
        addItem(new RunwayLightItem("altPrecision", 5, -1), true);
        addItem(new RunwayLightItem("leadingZeroIdent", 6, -1), true);
        addItem(new RunwayLightItem("noThesholdEndArrows", 7, -1), true);
        addItem(new RunwayPatternItem("primaryTakeoff", 0, false), true);
        addItem(new RunwayPatternItem("primaryLanding", 1, false), true);
        addItem(new RunwayPatternItem("primaryPattern", 2, true), true);
        addItem(new RunwayPatternItem("secondaryTakeoff", 3, false), true);
        addItem(new RunwayPatternItem("secondaryLanding", 4, false), true);
        addItem(new RunwayPatternItem("secondaryPattern", 5, true), true);
    }

    public String toString()
    {
        return name;
    }

    public static final String ID1 = "04";
    public static final String ID2 = "00";
}
