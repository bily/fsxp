// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MutableBoolean.java

package org.lc0277lib;


public class MutableBoolean
{

    public MutableBoolean(boolean initial)
    {
        value = initial;
    }

    public MutableBoolean()
    {
        this(false);
    }

    public boolean get()
    {
        return value;
    }

    public void set(boolean i)
    {
        value = i;
    }

    public String toString()
    {
        return Boolean.toString(value);
    }

    public int hashCode()
    {
        return value ? 1 : 0;
    }

    public boolean equals(Object obj)
    {
        if(obj instanceof MutableBoolean)
        {
            MutableBoolean mi = (MutableBoolean)obj;
            return mi.value == value;
        } else
        {
            return false;
        }
    }

    private boolean value;
}
