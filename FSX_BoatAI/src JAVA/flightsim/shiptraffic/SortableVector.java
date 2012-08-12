// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TrafficSector.java

package flightsim.shiptraffic;

import java.util.Arrays;
import java.util.Vector;

class SortableVector extends Vector
{

    public SortableVector(int cross)
    {
        super(cross);
    }

    public SortableVector()
    {
    }

    public void sort()
    {
        Arrays.sort(elementData, 0, size());
    }

    private static final long serialVersionUID = 0x6fdd5c85c780bceeL;
}
