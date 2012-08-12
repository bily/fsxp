// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Triple.java

package org.lc0277lib;


public class Triple
{

    public Triple(Object t1, Object t2, Object t3)
    {
        this.t1 = t1;
        this.t2 = t2;
        this.t3 = t3;
    }

    public String toString()
    {
        return (new StringBuilder("(")).append(t1).append(", ").append(t2).append(", ").append(t3).append(")").toString();
    }

    public int hashCode()
    {
        int hash = 0;
        if(t1 != null)
            hash ^= t1.hashCode();
        if(t2 != null)
            hash ^= t2.hashCode();
        if(t3 != null)
            hash ^= t3.hashCode();
        return hash;
    }

    public boolean equals(Object obj)
    {
        if(obj instanceof Triple)
        {
            Triple tt = (Triple)obj;
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
            if(t3 != null)
                b &= t3.equals(tt.t3);
            else
            if(t3 == null && tt.t3 != null)
                b = false;
            return b;
        } else
        {
            return false;
        }
    }

    public Object t1;
    public Object t2;
    public Object t3;
}
