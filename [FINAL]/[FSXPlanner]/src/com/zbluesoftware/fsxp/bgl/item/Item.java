// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Item.java

package com.zbluesoftware.fsxp.bgl.item;


public abstract class Item
{

    public Item()
    {
        name = "";
        dataType = "";
        offset = 0;
        length = 0;
        hexData = null;
        decodedData = null;
        decodedType = 1;
    }

    public String getHexData()
    {
        return hexData;
    }

    public void setHexData(String hexData)
    {
        this.hexData = hexData;
        setDecodedData();
    }

    public Object getDecodedData()
    {
        return decodedData;
    }

    public void setDecodedData(Object decodedData)
    {
        this.decodedData = decodedData;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getOffset()
    {
        return offset;
    }

    public void setOffset(int offset)
    {
        this.offset = offset;
    }

    public int getLength()
    {
        return length;
    }

    public void setLength(int length)
    {
        this.length = length;
    }

    public String getDataType()
    {
        return dataType;
    }

    public void setDataType(String dataType)
    {
        this.dataType = dataType;
    }

    public void setDecodedData()
    {
        switch(decodedType)
        {
        case 1: // '\001'
            convertToInt();
            break;

        case 2: // '\002'
            convertToFloat();
            break;

        case 3: // '\003'
            convertToDouble();
            // fall through

        case 4: // '\004'
            convertToString();
            break;
        }
    }

    private void convertToInt()
    {
        if(dataType.equals("Word"))
        {
            String tempHex = hexData.replaceAll(" ", "");
            StringBuffer buffer = new StringBuffer();
            for(int i = tempHex.length() - 1; i >= 0; i -= 2)
                buffer.append(tempHex.charAt(i - 1)).append(tempHex.charAt(i));

            decodedData = new Integer(Long.valueOf(buffer.toString(), 16).intValue());
        } else
        if(dataType.equals("DWord"))
        {
            String tempHex = hexData.replaceAll(" ", "");
            StringBuffer buffer = new StringBuffer();
            for(int i = tempHex.length() - 1; i >= 0; i -= 2)
                buffer.append(tempHex.charAt(i - 1)).append(tempHex.charAt(i));

            decodedData = new Integer(Long.valueOf(buffer.toString(), 16).intValue());
        } else
        if(dataType.equals("Byte"))
            decodedData = new Integer(Long.valueOf(hexData, 16).intValue());
        else
        if(!dataType.equals("PackedByte") && !dataType.equals("Float"))
            if(!dataType.equals("String"));
    }

    private void convertToFloat()
    {
        if(!dataType.equals("Word"))
            if(dataType.equals("DWord"))
            {
                String tempHex = hexData.replaceAll(" ", "");
                StringBuffer buffer = new StringBuffer();
                for(int i = tempHex.length() - 1; i >= 0; i -= 2)
                    buffer.append(tempHex.charAt(i - 1)).append(tempHex.charAt(i));

                String tempData = (new StringBuilder()).append("").append(Long.valueOf(buffer.toString(), 16)).toString();
                Float whole = new Float(0.0F);
                if(tempData.length() > 3)
                    whole = Float.valueOf(tempData.substring(0, tempData.length() - 3));
                Float decimal = Float.valueOf(tempData.substring(tempData.length() - Math.min(3, tempData.length())));
                decodedData = new Float(whole.floatValue() + decimal.floatValue() / 1000F);
            } else
            if(!dataType.equals("Byte") && !dataType.equals("PackedByte"))
                if(dataType.equals("Float"))
                {
                    String tempHex = hexData.replaceAll(" ", "");
                    StringBuffer buffer = new StringBuffer();
                    for(int i = tempHex.length() - 1; i >= 0; i -= 2)
                        buffer.append(tempHex.charAt(i - 1)).append(tempHex.charAt(i));

                    int tempData = Long.valueOf(buffer.toString(), 16).intValue();
                    decodedData = new Float(Float.intBitsToFloat(tempData));
                } else
                if(!dataType.equals("String"));
    }

    private void convertToDouble()
    {
        if(!dataType.equals("Word"))
            if(dataType.equals("DWord"))
            {
                String tempHex = hexData.replaceAll(" ", "");
                StringBuffer buffer = new StringBuffer();
                for(int i = tempHex.length() - 1; i >= 0; i -= 2)
                    buffer.append(tempHex.charAt(i - 1)).append(tempHex.charAt(i));

                int tempData = Long.valueOf(buffer.toString(), 16).intValue();
                decodedData = new Float(Float.intBitsToFloat(tempData));
            } else
            if(!dataType.equals("Byte") && !dataType.equals("PackedByte") && !dataType.equals("Float"))
                if(!dataType.equals("String"));
    }

    private void convertToString()
    {
        if(!dataType.equals("Word"))
            if(dataType.equals("DWord"))
            {
                String tempHex = hexData.replaceAll(" ", "");
                StringBuffer buffer = new StringBuffer();
                for(int i = 0; i < tempHex.length(); i += 2)
                    buffer.append(Character.toString((char)Long.valueOf(tempHex.substring(i, i + 2), 16).intValue()));

                decodedData = buffer.toString();
            } else
            if(!dataType.equals("Byte") && !dataType.equals("PackedByte") && !dataType.equals("Float"))
                if(!dataType.equals("String"));
    }

    protected String name;
    protected String dataType;
    protected String hexData;
    protected Object decodedData;
    protected int decodedType;
    protected int offset;
    protected int length;
    public static final int INT = 1;
    public static final int SIGNED_FLOAT = 2;
    public static final int SIGNED_DOUBLE = 3;
    public static final int STRING = 4;
}
