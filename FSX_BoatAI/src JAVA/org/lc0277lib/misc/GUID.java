// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GUID.java

package org.lc0277lib.misc;

import java.nio.ByteBuffer;

public class GUID
    implements Comparable
{

    public GUID()
    {
        data = new byte[16];
    }

    public GUID(byte data[])
    {
        this(data, 0);
    }

    public GUID(byte data[], int offset)
    {
        this.data = new byte[16];
        System.arraycopy(data, offset, this.data, 0, 16);
    }

    public GUID(int arg1, short arg2, short arg3, byte arg4[])
        throws IllegalArgumentException
    {
        data = decode(arg1, arg2, arg3, arg4);
    }

    public GUID(String s)
    {
        data = decodeRegistry(s);
    }

    public static byte[] decodeRegistry(String s)
    {
        s = s.trim().toLowerCase().substring(1, s.length() - 1);
        if(s.length() != 36)
            throw new IllegalArgumentException("Bad GUID length");
        String parts[] = s.split("-");
        if(parts.length != 5)
            throw new IllegalArgumentException("Bad GUID format");
        byte data[] = new byte[16];
        try
        {
            int a = (int)(Long.parseLong(parts[0], 16) & -1L);
            data[0] = (byte)(a & 0xff);
            data[1] = (byte)(a >> 8 & 0xff);
            data[2] = (byte)(a >> 16 & 0xff);
            data[3] = (byte)(a >> 24 & 0xff);
            a = Integer.parseInt(parts[1], 16);
            data[4] = (byte)(a & 0xff);
            data[5] = (byte)(a >> 8 & 0xff);
            a = Integer.parseInt(parts[2], 16);
            data[6] = (byte)(a & 0xff);
            data[7] = (byte)(a >> 8 & 0xff);
            a = Integer.parseInt(parts[3], 16);
            data[8] = (byte)(a >> 8 & 0xff);
            data[9] = (byte)(a & 0xff);
            int i = 0;
            for(int j = 10; i < parts[4].length(); j++)
            {
                data[j] = (byte)(Integer.parseInt(parts[4].substring(i, i + 2), 16) & 0xff);
                i += 2;
            }

        }
        catch(NumberFormatException nfe)
        {
            throw new IllegalArgumentException(nfe);
        }
        return data;
    }

    public static byte[] decodeOldRegistry(String s)
    {
        byte data[] = new byte[16];
        for(int i = 0; i < 4; i++)
        {
            int v = (int)(Long.parseLong(s.substring(8 * i, 8 * i + 8), 16) & -1L);
            data[4 * i + 3] = (byte)(v >> 24 & 0xff);
            data[4 * i + 2] = (byte)(v >> 16 & 0xff);
            data[4 * i + 1] = (byte)(v >> 8 & 0xff);
            data[4 * i] = (byte)(v & 0xff);
        }

        return data;
    }

    public static byte[] decode(int arg1, short arg2, short arg3, byte arg4[])
    {
        if(arg4 == null || arg4.length != 8)
        {
            throw new IllegalArgumentException("Bad array");
        } else
        {
            byte data[] = new byte[16];
            data[3] = (byte)(arg1 >> 24 & 0xff);
            data[2] = (byte)(arg1 >> 16 & 0xff);
            data[1] = (byte)(arg1 >> 8 & 0xff);
            data[0] = (byte)(arg1 & 0xff);
            data[5] = (byte)(arg2 >> 8 & 0xff);
            data[4] = (byte)(arg2 & 0xff);
            data[7] = (byte)(arg3 >> 8 & 0xff);
            data[6] = (byte)(arg3 & 0xff);
            System.arraycopy(arg4, 0, data, 8, 8);
            return data;
        }
    }

    public static GUID parseGUID(String s)
    {
        return new GUID(s);
    }

    public String toString()
    {
        StringBuffer sgb = new StringBuffer("");
        sgb.append('{');
        if((data[3] & 0xff) < 16)
            sgb.append('0');
        sgb.append(Integer.toHexString(data[3] & 0xff));
        if((data[2] & 0xff) < 16)
            sgb.append('0');
        sgb.append(Integer.toHexString(data[2] & 0xff));
        if((data[1] & 0xff) < 16)
            sgb.append('0');
        sgb.append(Integer.toHexString(data[1] & 0xff));
        if((data[0] & 0xff) < 16)
            sgb.append('0');
        sgb.append(Integer.toHexString(data[0] & 0xff));
        sgb.append('-');
        if((data[5] & 0xff) < 16)
            sgb.append('0');
        sgb.append(Integer.toHexString(data[5] & 0xff));
        if((data[4] & 0xff) < 16)
            sgb.append('0');
        sgb.append(Integer.toHexString(data[4] & 0xff));
        sgb.append('-');
        if((data[7] & 0xff) < 16)
            sgb.append('0');
        sgb.append(Integer.toHexString(data[7] & 0xff));
        if((data[6] & 0xff) < 16)
            sgb.append('0');
        sgb.append(Integer.toHexString(data[6] & 0xff));
        sgb.append('-');
        if((data[8] & 0xff) < 16)
            sgb.append('0');
        sgb.append(Integer.toHexString(data[8] & 0xff));
        if((data[9] & 0xff) < 16)
            sgb.append('0');
        sgb.append(Integer.toHexString(data[9] & 0xff));
        sgb.append('-');
        if((data[10] & 0xff) < 16)
            sgb.append('0');
        sgb.append(Integer.toHexString(data[10] & 0xff));
        if((data[11] & 0xff) < 16)
            sgb.append('0');
        sgb.append(Integer.toHexString(data[11] & 0xff));
        if((data[12] & 0xff) < 16)
            sgb.append('0');
        sgb.append(Integer.toHexString(data[12] & 0xff));
        if((data[13] & 0xff) < 16)
            sgb.append('0');
        sgb.append(Integer.toHexString(data[13] & 0xff));
        if((data[14] & 0xff) < 16)
            sgb.append('0');
        sgb.append(Integer.toHexString(data[14] & 0xff));
        if((data[15] & 0xff) < 16)
            sgb.append('0');
        sgb.append(Integer.toHexString(data[15] & 0xff));
        sgb.append('}');
        return sgb.toString().toUpperCase();
    }

    public int hashCode()
    {
        int h = 0;
        for(int i = 0; i < 16; i++)
            h = 31 * h + data[i];

        return h;
    }

    public boolean equals(Object obj)
    {
        if(obj instanceof GUID)
        {
            GUID g = (GUID)obj;
            for(int i = 0; i < 16; i++)
                if(data[i] != g.data[i])
                    return false;

            return true;
        } else
        {
            return false;
        }
    }

    public int compareTo(GUID g)
    {
        return toString().compareToIgnoreCase(g.toString());
    }

    public void read(ByteBuffer buffer)
    {
        buffer.get(data);
    }

    public void write(ByteBuffer buffer)
    {
        buffer.put(data);
    }

    public byte[] getData()
    {
        return data;
    }

    public int compareTo(Object obj)
    {
        return compareTo((GUID)obj);
    }

    protected final byte data[];
}
