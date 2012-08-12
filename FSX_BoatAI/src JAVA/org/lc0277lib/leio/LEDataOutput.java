// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LEDataOutput.java

package org.lc0277lib.leio;

import java.io.*;

public class LEDataOutput
    implements DataOutput
{

    public LEDataOutput(OutputStream os)
    {
        dos = new DataOutputStream(os);
    }

    public void write(int b)
        throws IOException
    {
        dos.write(b);
    }

    public void write(byte b[])
        throws IOException
    {
        dos.write(b);
    }

    public void write(byte b[], int off, int len)
        throws IOException
    {
        dos.write(b, off, len);
    }

    public void writeBoolean(boolean v)
        throws IOException
    {
        dos.writeBoolean(v);
    }

    public void writeByte(int v)
        throws IOException
    {
        dos.write(v);
    }

    public void writeBytes(String s)
        throws IOException
    {
        dos.writeBytes(s);
    }

    public void writeChar(int v)
        throws IOException
    {
        tmp[0] = (byte)v;
        tmp[1] = (byte)(v >> 8);
        dos.write(tmp, 0, 2);
    }

    public void writeChars(String s)
        throws IOException
    {
        int len = s.length();
        for(int i = 0; i < len; i++)
            writeChar(s.charAt(i));

    }

    public void writeDouble(double v)
        throws IOException
    {
        writeLong(Double.doubleToLongBits(v));
    }

    public void writeFloat(float v)
        throws IOException
    {
        writeInt(Float.floatToIntBits(v));
    }

    public void writeInt(int v)
        throws IOException
    {
        tmp[0] = (byte)v;
        tmp[1] = (byte)(v >> 8);
        tmp[2] = (byte)(v >> 16);
        tmp[3] = (byte)(v >> 24);
        dos.write(tmp, 0, 4);
    }

    public void writeLong(long v)
        throws IOException
    {
        tmp[0] = (byte)(int)v;
        tmp[1] = (byte)(int)(v >> 8);
        tmp[2] = (byte)(int)(v >> 16);
        tmp[3] = (byte)(int)(v >> 24);
        tmp[4] = (byte)(int)(v >> 32);
        tmp[5] = (byte)(int)(v >> 40);
        tmp[6] = (byte)(int)(v >> 48);
        tmp[7] = (byte)(int)(v >> 56);
        dos.write(tmp, 0, 8);
    }

    public void writeShort(int v)
        throws IOException
    {
        tmp[0] = (byte)v;
        tmp[1] = (byte)(v >> 8);
        dos.write(tmp, 0, 2);
    }

    public void writeUTF(String str)
        throws IOException
    {
        dos.writeUTF(str);
    }

    private final DataOutputStream dos;
    private final byte tmp[] = new byte[8];
}
