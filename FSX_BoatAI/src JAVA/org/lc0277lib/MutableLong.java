// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MutableLong.java

package org.lc0277lib;


public class MutableLong
{

    public MutableLong(long initial)
    {
        value = initial;
    }

    public MutableLong()
    {
        this(0L);
    }

    public long inc()
    {
        return value++;
    }

    public long dec()
    {
        return value--;
    }

    public long get()
    {
        return value;
    }

    public void set(long i)
    {
        value = i;
    }

    public String toString()
    {
        return Long.toString(value);
    }

    public int hashCode()
    {
        return (int)(value ^ value >>> 32);
    }

    public boolean equals(Object obj)
    {
        if(obj instanceof MutableLong)
        {
            MutableLong mi = (MutableLong)obj;
            return mi.value == value;
        } else
        {
            return false;
        }
    }

    private long value;
}
