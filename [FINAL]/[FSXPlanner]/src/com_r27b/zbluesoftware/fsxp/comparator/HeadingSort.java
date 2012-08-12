// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   HeadingSort.java

package com.zbluesoftware.fsxp.comparator;

import com.zbluesoftware.fsxp.model.TaxiwayPathDisplayModel;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.Comparator;

public class HeadingSort
    implements Comparator
{

    private HeadingSort()
    {
    }

    public static HeadingSort getInstance(int index)
    {
        if(headingSort == null)
            headingSort = new HeadingSort();
        headingSort.setIndex(index);
        return headingSort;
    }

    public void setIndex(int index)
    {
        this.index = index;
    }

    public int compare(Object o1, Object o2)
    {
        if((o1 instanceof TaxiwayPathDisplayModel) && (o2 instanceof TaxiwayPathDisplayModel))
        {
            double angle1 = getAngle((TaxiwayPathDisplayModel)o1);
            double angle2 = getAngle((TaxiwayPathDisplayModel)o2);
            return angle1 >= angle2 ? -1 : 1;
        } else
        {
            return 0;
        }
    }

    private double getAngle(TaxiwayPathDisplayModel model)
    {
        Line2D line = model.getLeftEdgeLine(index);
        Point2D startPoint;
        Point2D endPoint;
        if(model.isAtBeginning(index))
        {
            startPoint = line.getP1();
            endPoint = line.getP2();
        } else
        {
            startPoint = line.getP2();
            endPoint = line.getP1();
        }
        if(startPoint.getX() <= endPoint.getX() && startPoint.getY() <= endPoint.getY())
            return Math.toDegrees(Math.atan((startPoint.getY() - endPoint.getY()) / (startPoint.getX() - endPoint.getX()))) + 270D;
        if(startPoint.getX() > endPoint.getX() && startPoint.getY() <= endPoint.getY())
            return Math.toDegrees(Math.atan((startPoint.getY() - endPoint.getY()) / (startPoint.getX() - endPoint.getX()))) + 90D;
        if(startPoint.getX() > endPoint.getX() && startPoint.getY() > endPoint.getY())
            return Math.toDegrees(Math.atan((startPoint.getY() - endPoint.getY()) / (startPoint.getX() - endPoint.getX()))) + 90D;
        else
            return Math.toDegrees(Math.atan((startPoint.getY() - endPoint.getY()) / (startPoint.getX() - endPoint.getX()))) + 270D;
    }

    public boolean equals(Object object)
    {
        return super.equals(object);
    }

    private int index;
    private static HeadingSort headingSort = null;

}
