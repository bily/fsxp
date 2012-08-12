// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ProgressAdapter.java

package flightsim.shiptraffic;


public interface ProgressAdapter
{

    public abstract void setSection(String s, int i);

    public abstract void increment();

    public abstract void setValue(int i);

    public abstract void close();
}
