// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LEDataInput.java

package org.lc0277lib.leio;

import java.io.*;

public class LEDataInput
    implements DataInput
{

    public LEDataInput(InputStream is)
    {
        dis = new DataInputStream(is);
        tmp = new byte[8];
    }

    public LEDataInput(byte b[])
    {
        this(((InputStream) (new ByteArrayInputStream(b))));
    }

    public boolean readBoolean()
        throws IOException
    {
        return dis.readBoolean();
    }

    public byte readByte()
        throws IOException
    {
        return dis.readByte();
    }

    public char readChar()
        throws IOException
    {
        dis.readFully(tmp, 0, 2);
        return (char)((tmp[1] & 0xff) << 8 | tmp[0] & 0xff);
    }

    public double readDouble()
        throws IOException
    {
        return Double.longBitsToDouble(readLong());
    }

    public float readFloat()
        throws IOException
    {
        return Float.intBitsToFloat(readInt());
    }

    public void readFully(byte b[])
        throws IOException
    {
        dis.readFully(b);
    }

    public void readFully(byte b[], int off, int len)
        throws IOException
    {
        dis.readFully(b, off, len);
    }

    public int readInt()
        throws IOException
    {
        dis.readFully(tmp, 0, 4);
        return tmp[3] << 24 | (tmp[2] & 0xff) << 16 | (tmp[1] & 0xff) << 8 | tmp[0] & 0xff;
    }

    public String readLine()
        throws IOException
    {
        return dis.readLine();
    }

    public long readLong()
        throws IOException
    {
        dis.readFully(tmp, 0, 8);
        return (long)tmp[7] << 56 | (long)(tmp[6] & 0xff) << 48 | (long)(tmp[5] & 0xff) << 40 | (long)(tmp[4] & 0xff) << 32 | (long)(tmp[3] & 0xff) << 24 | (long)(tmp[2] & 0xff) << 16 | (long)(tmp[1] & 0xff) << 8 | (long)(tmp[0] & 0xff);
    }

    public short readShort()
        throws IOException
    {
        dis.readFully(tmp, 0, 2);
        return (short)((tmp[1] & 0xff) << 8 | tmp[0] & 0xff);
    }

    public String readUTF()
        throws IOException
    {
        return dis.readLine();
    }

    public int readUnsignedByte()
        throws IOException
    {
        return dis.readUnsignedByte();
    }

    public int readUnsignedShort()
        throws IOException
    {
        dis.readFully(tmp, 0, 2);
        return (short)((tmp[1] & 0xff) << 8 | tmp[0] & 0xff);
    }

    public int skipBytes(int n)
        throws IOException
    {
        return dis.skipBytes(n);
    }

    private final DataInputStream dis;
    private final byte tmp[];
}
