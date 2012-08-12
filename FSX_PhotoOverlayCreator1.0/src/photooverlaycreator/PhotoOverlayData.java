// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PhotoOverlayData.java

package photooverlaycreator;


public class PhotoOverlayData
{

    public PhotoOverlayData()
    {
        description = "Put description here";
        shape = 0;
        lat = 51.522F;
        lon = -0.134721F;
        alt = 40F;
        near = 20F;
        fovLeft = -60F;
        fovRight = 60F;
        fovBottom = -40F;
        fovTop = 40F;
    }

    public PhotoOverlayData(PhotoOverlayData data)
    {
        description = "Put description here";
        shape = 0;
        lat = 51.522F;
        lon = -0.134721F;
        alt = 40F;
        near = 20F;
        fovLeft = -60F;
        fovRight = 60F;
        fovBottom = -40F;
        fovTop = 40F;
        description = data.description;
        shape = data.shape;
        lat = data.lat;
        lon = data.lon;
        alt = data.alt;
        near = data.near;
        fovLeft = data.fovLeft;
        fovRight = data.fovRight;
        fovBottom = data.fovBottom;
        fovTop = data.fovTop;
    }

    public void calculateDefaultFOV(int width, int height)
    {
        switch(shape)
        {
        case 0: // '\0'
            near = 40F;
            calculateRectangleFOV(width, height);
            calculateAltitude();
            break;

        case 1: // '\001'
            near = 40F;
            calculateCylinderFOV(width, height);
            calculateAltitude();
            break;

        case 2: // '\002'
            near = 40F;
            fovLeft = -180F;
            fovRight = 180F;
            fovBottom = -90F;
            fovTop = 90F;
            calculateAltitude();
            break;
        }
    }

    public void calculateAltitude()
    {
        if(shape == 2)
        {
            alt = near + 1.0F;
        } else
        {
            float angle = (float)Math.toRadians(Math.abs(fovBottom));
            alt = (float)((double)near * Math.tan(angle));
        }
    }

    public void calculateRectangleFOV(int width, int height)
    {
        fovTop = (float)Math.toDegrees(Math.atan((double)((float)height / (float)width) * Math.tan(Math.toRadians(fovRight))));
        fovBottom = -fovTop;
    }

    public void calculateCylinderFOV(int width, int height)
    {
        fovTop = (float)Math.toDegrees(Math.atan((double)((float)height / (float)width) * 3.1415926535897931D));
        fovBottom = -fovTop;
    }

    public void checkFOVLimits()
    {
        switch(shape)
        {
        case 0: // '\0'
            if(fovLeft <= -90F)
                fovLeft = -89F;
            if(fovLeft >= 90F)
                fovLeft = 89F;
            if(fovRight >= 90F)
                fovRight = 89F;
            if(fovRight <= -90F)
                fovRight = -89F;
            if(fovBottom <= -90F)
                fovBottom = -89F;
            if(fovBottom >= 90F)
                fovBottom = 89F;
            if(fovTop >= 90F)
                fovTop = 89F;
            if(fovTop < -90F)
                fovTop = -89F;
            break;

        case 1: // '\001'
            if(fovLeft <= -180F)
                fovLeft = -179F;
            if(fovLeft >= 180F)
                fovLeft = 179F;
            if(fovRight >= 180F)
                fovRight = 179F;
            if(fovRight <= -180F)
                fovRight = -179F;
            if(fovBottom <= -90F)
                fovBottom = -89F;
            if(fovBottom >= 90F)
                fovBottom = 89F;
            if(fovTop >= 90F)
                fovTop = 89F;
            if(fovTop < -90F)
                fovTop = -89F;
            break;

        case 2: // '\002'
            if(fovLeft <= -180F)
                fovLeft = -179F;
            if(fovLeft >= 180F)
                fovLeft = 179F;
            if(fovRight >= 180F)
                fovRight = 179F;
            if(fovRight <= -180F)
                fovRight = -179F;
            if(fovBottom <= -90F)
                fovBottom = -89F;
            if(fovBottom >= 90F)
                fovBottom = 89F;
            if(fovTop >= 90F)
                fovTop = 89F;
            if(fovTop < -90F)
                fovTop = -89F;
            break;
        }
        if(fovLeft > fovRight)
        {
            float temp = fovLeft;
            fovLeft = fovRight;
            fovRight = temp;
        }
        if(fovBottom > fovTop)
        {
            float temp = fovBottom;
            fovBottom = fovTop;
            fovTop = temp;
        }
    }

    public void setShape(int shape)
    {
        if(this.shape == shape)
            return;
        this.shape = shape;
        switch(this.shape)
        {
        case 0: // '\0'
            fovLeft = -60F;
            fovRight = 60F;
            fovBottom = -40F;
            fovTop = 40F;
            break;

        case 1: // '\001'
            fovLeft = -180F;
            fovRight = 180F;
            fovBottom = -80F;
            fovTop = 80F;
            break;

        case 2: // '\002'
            fovLeft = -180F;
            fovRight = 180F;
            fovBottom = -90F;
            fovTop = 90F;
            break;
        }
    }

    public String getStringShapeType()
    {
        switch(shape)
        {
        case 0: // '\0'
            return "rectangle";

        case 1: // '\001'
            return "cylinder";

        case 2: // '\002'
            return "sphere";
        }
        return "unknown";
    }

    public static final int shapeRectangle = 0;
    public static final int shapeCylinder = 1;
    public static final int shapeSphere = 2;
    public String description;
    public int shape;
    public float lat;
    public float lon;
    public float alt;
    public float near;
    public float fovLeft;
    public float fovRight;
    public float fovBottom;
    public float fovTop;
}
