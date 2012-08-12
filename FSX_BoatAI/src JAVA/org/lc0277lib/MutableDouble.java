// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MutableDouble.java

package org.lc0277lib;


public class MutableDouble
{

    public MutableDouble(double initial)
    {
        value = initial;
    }

    public MutableDouble()
    {
        this(0.0D);
    }

    public double inc()
    {
        return value++;
    }

    public double dec()
    {
        return value--;
    }

    public double get()
    {
        return value;
    }

    public void set(double i)
    {
        value = i;
    }

    public String toString()
    {
        return Double.toString(value);
    }

    public int hashCode()
    {
        long bits = Double.doubleToLongBits(value);
        return (int)(bits ^ bits >>> 32);
    }

    public boolean equals(Object obj)
    {
        if(obj instanceof MutableDouble)
        {
            MutableDouble mi = (MutableDouble)obj;
            return mi.value == value;
        } else
        {
            return false;
        }
    }

    private double value;
}
