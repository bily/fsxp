// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ProgressAdapterRandomAccessFile.java

package flightsim.shiptraffic.btcgui;

import flightsim.shiptraffic.ProgressAdapter;
import java.io.*;
import org.lc0277lib.leio.LERandomAccessFile;

public class ProgressAdapterRandomAccessFile extends LERandomAccessFile
{

    public ProgressAdapterRandomAccessFile(File file, String mode, ProgressAdapter pa)
        throws FileNotFoundException
    {
        super(file, mode);
        this.pa = pa;
    }

    private void update()
        throws IOException
    {
        pos = Math.max(pos, getFilePointer());
        pa.setValue((int)pos);
    }

    public int read()
        throws IOException
    {
        update();
        return super.read();
    }

    public int read(byte b[], int off, int len)
        throws IOException
    {
        update();
        return super.read(b, off, len);
    }

    public int read(byte b[])
        throws IOException
    {
        update();
        return super.read(b);
    }

    public boolean readBoolean()
        throws IOException
    {
        update();
        return super.readBoolean();
    }

    public byte readByte()
        throws IOException
    {
        update();
        return super.readByte();
    }

    public char readChar()
        throws IOException
    {
        update();
        return super.readChar();
    }

    public double readDouble()
        throws IOException
    {
        update();
        return super.readDouble();
    }

    public float readFloat()
        throws IOException
    {
        update();
        return super.readFloat();
    }

    public void readFully(byte b[], int off, int len)
        throws IOException
    {
        update();
        super.readFully(b, off, len);
    }

    public void readFully(byte b[])
        throws IOException
    {
        update();
        super.readFully(b);
    }

    public int readInt()
        throws IOException
    {
        update();
        return super.readInt();
    }

    public String readLine()
        throws IOException
    {
        update();
        return super.readLine();
    }

    public long readLong()
        throws IOException
    {
        update();
        return super.readLong();
    }

    public short readShort()
        throws IOException
    {
        update();
        return super.readShort();
    }

    public int readUnsignedByte()
        throws IOException
    {
        update();
        return super.readUnsignedByte();
    }

    public int readUnsignedShort()
        throws IOException
    {
        update();
        return super.readUnsignedShort();
    }

    public String readUTF()
        throws IOException
    {
        update();
        return super.readUTF();
    }

    public int skipBytes(int n)
        throws IOException
    {
        update();
        return super.skipBytes(n);
    }

    public void write(byte b[], int off, int len)
        throws IOException
    {
        update();
        super.write(b, off, len);
    }

    public void write(byte b[])
        throws IOException
    {
        update();
        super.write(b);
    }

    public void write(int b)
        throws IOException
    {
        update();
        super.write(b);
    }

    public void writeBoolean(boolean v)
        throws IOException
    {
        update();
        super.writeBoolean(v);
    }

    public void writeByte(int v)
        throws IOException
    {
        update();
        super.writeByte(v);
    }

    public void writeBytes(String s)
        throws IOException
    {
        update();
        super.writeBytes(s);
    }

    public void writeChar(int v)
        throws IOException
    {
        update();
        super.writeChar(v);
    }

    public void writeChars(String s)
        throws IOException
    {
        update();
        super.writeChars(s);
    }

    public void writeDouble(double v)
        throws IOException
    {
        update();
        super.writeDouble(v);
    }

    public void writeFloat(float v)
        throws IOException
    {
        update();
        super.writeFloat(v);
    }

    public void writeInt(int v)
        throws IOException
    {
        update();
        super.writeInt(v);
    }

    public void writeLong(long v)
        throws IOException
    {
        update();
        super.writeLong(v);
    }

    public void writeShort(int v)
        throws IOException
    {
        update();
        super.writeShort(v);
    }

    public void writeUTF(String str)
        throws IOException
    {
        update();
        super.writeUTF(str);
    }

    private ProgressAdapter pa;
    private long pos;
}
