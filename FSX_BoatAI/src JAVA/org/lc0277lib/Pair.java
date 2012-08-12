// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Pair.java

package org.lc0277lib;


public class Pair
{

    public Pair(Object tt1, Object tt2)
    {
        t1 = tt1;
        t2 = tt2;
    }

    public String toString()
    {
        return (new StringBuilder("(")).append(t1).append(",").append(t2).append(")").toString();
    }

    public Object get1()
    {
        return t1;
    }

    public Object get2()
    {
        return t2;
    }

    public int hashCode()
    {
        int hash = 0;
        if(t1 != null)
            hash ^= t1.hashCode();
        if(t2 != null)
            hash ^= t2.hashCode();
        return hash;
    }

    public boolean equals(Object obj)
    {
        if(obj instanceof Pair)
        {
            Pair tt = (Pair)obj;
            boolean b = true;
            if(t1 != null)
                b &= t1.equals(tt.t1);
            else
            if(t1 == null && tt.t1 != null)
                b = false;
            if(t2 != null)
                b &= t2.equals(tt.t2);
            else
            if(t2 == null && tt.t2 != null)
                b = false;
            return b;
        } else
        {
            return false;
        }
    }

    public Object t1;
    public Object t2;
}
