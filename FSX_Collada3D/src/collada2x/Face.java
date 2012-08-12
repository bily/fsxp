// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Face.java

package collada2x;


public class Face
{

    public Face(int p1, int p2, int p3)
    {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
    }

    public int getP1()
    {
        return p1;
    }

    public int getP2()
    {
        return p2;
    }

    public int getP3()
    {
        return p3;
    }

    private int p1;
    private int p2;
    private int p3;
}
