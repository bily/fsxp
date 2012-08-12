// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LERandomAccessFile.java

package org.lc0277lib.leio;

import java.io.*;

public class LERandomAccessFile
    implements DataInput, DataOutput
{

    public LERandomAccessFile(File file, String mode)
        throws FileNotFoundException
    {
        raf = new RandomAccessFile(file, mode);
        tmp = new byte[8];
    }

    public LERandomAccessFile(String file, String mode)
        throws FileNotFoundException
    {
        raf = new RandomAccessFile(file, mode);
        tmp = new byte[8];
    }

    public void close()
        throws IOException
    {
        raf.close();
    }

    public boolean equals(Object obj)
    {
        return raf.equals(obj);
    }

    public long getFilePointer()
        throws IOException
    {
        return raf.getFilePointer();
    }

    public int hashCode()
    {
        return raf.hashCode();
    }

    public long length()
        throws IOException
    {
        return raf.length();
    }

    public int read()
        throws IOException
    {
        return raf.read();
    }

    public int read(byte b[], int off, int len)
        throws IOException
    {
        return raf.read(b, off, len);
    }

    public int read(byte b[])
        throws IOException
    {
        return raf.read(b);
    }

    public boolean readBoolean()
        throws IOException
    {
        return raf.readBoolean();
    }

    public byte readByte()
        throws IOException
    {
        return raf.readByte();
    }

    public char readChar()
        throws IOException
    {
        raf.readFully(tmp, 0, 2);
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

    public void readFully(byte b[], int off, int len)
        throws IOException
    {
        raf.readFully(b, off, len);
    }

    public void readFully(byte b[])
        throws IOException
    {
        raf.readFully(b);
    }

    public int readInt()
        throws IOException
    {
        raf.readFully(tmp, 0, 4);
        return tmp[3] << 24 | (tmp[2] & 0xff) << 16 | (tmp[1] & 0xff) << 8 | tmp[0] & 0xff;
    }

    public String readLine()
        throws IOException
    {
        return raf.readLine();
    }

    public long readLong()
        throws IOException
    {
        raf.readFully(tmp, 0, 8);
        return (long)tmp[7] << 56 | (long)(tmp[6] & 0xff) << 48 | (long)(tmp[5] & 0xff) << 40 | (long)(tmp[4] & 0xff) << 32 | (long)(tmp[3] & 0xff) << 24 | (long)(tmp[2] & 0xff) << 16 | (long)(tmp[1] & 0xff) << 8 | (long)(tmp[0] & 0xff);
    }

    public short readShort()
        throws IOException
    {
        raf.readFully(tmp, 0, 2);
        return (short)((tmp[1] & 0xff) << 8 | tmp[0] & 0xff);
    }

    public int readUnsignedByte()
        throws IOException
    {
        return raf.readUnsignedByte();
    }

    public int readUnsignedShort()
        throws IOException
    {
        raf.readFully(tmp, 0, 2);
        return (tmp[1] & 0xff) << 8 | tmp[0] & 0xff;
    }

    public String readUTF()
        throws IOException
    {
        return raf.readUTF();
    }

    public void seek(long pos)
        throws IOException
    {
        raf.seek(pos);
    }

    public void setLength(long newLength)
        throws IOException
    {
        raf.setLength(newLength);
    }

    public int skipBytes(int n)
        throws IOException
    {
        return raf.skipBytes(n);
    }

    public String toString()
    {
        return raf.toString();
    }

    public void write(byte b[], int off, int len)
        throws IOException
    {
        raf.write(b, off, len);
    }

    public void write(byte b[])
        throws IOException
    {
        raf.write(b);
    }

    public void write(int b)
        throws IOException
    {
        raf.write(b);
    }

    public void writeBoolean(boolean v)
        throws IOException
    {
        raf.writeBoolean(v);
    }

    public void writeByte(int v)
        throws IOException
    {
        raf.writeByte(v);
    }

    public void writeBytes(String s)
        throws IOException
    {
        raf.writeBytes(s);
    }

    public void writeChar(int v)
        throws IOException
    {
        tmp[0] = (byte)v;
        tmp[1] = (byte)(v >> 8);
        raf.write(tmp, 0, 2);
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
        raf.write(tmp, 0, 4);
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
        raf.write(tmp, 0, 8);
    }

    public void writeShort(int v)
        throws IOException
    {
        tmp[0] = (byte)v;
        tmp[1] = (byte)(v >> 8);
        raf.write(tmp, 0, 2);
    }

    public void writeUTF(String str)
        throws IOException
    {
        raf.writeUTF(str);
    }

    private final RandomAccessFile raf;
    private final byte tmp[];
}
