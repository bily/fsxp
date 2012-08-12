// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MutableFloat.java

package org.lc0277lib;


public class MutableFloat
{

    public MutableFloat(float initial)
    {
        value = initial;
    }

    public MutableFloat()
    {
        this(0.0F);
    }

    public float inc()
    {
        return value++;
    }

    public float dec()
    {
        return value--;
    }

    public float get()
    {
        return value;
    }

    public void set(float i)
    {
        value = i;
    }

    public String toString()
    {
        return Float.toString(value);
    }

    public int hashCode()
    {
        return Float.floatToIntBits(value);
    }

    public boolean equals(Object obj)
    {
        if(obj instanceof MutableFloat)
        {
            MutableFloat mi = (MutableFloat)obj;
            return mi.value == value;
        } else
        {
            return false;
        }
    }

    private float value;
}
