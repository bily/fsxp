// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MutableInteger.java

package org.lc0277lib;


public class MutableInteger
{

    public MutableInteger(int initial)
    {
        value = initial;
    }

    public MutableInteger()
    {
        this(0);
    }

    public int inc()
    {
        return value++;
    }

    public int dec()
    {
        return value--;
    }

    public int get()
    {
        return value;
    }

    public void set(int i)
    {
        value = i;
    }

    public String toString()
    {
        return Integer.toString(value);
    }

    public int hashCode()
    {
        return value;
    }

    public boolean equals(Object obj)
    {
        if(obj instanceof MutableInteger)
        {
            MutableInteger mi = (MutableInteger)obj;
            return mi.value == value;
        } else
        {
            return false;
        }
    }

    private int value;
}
