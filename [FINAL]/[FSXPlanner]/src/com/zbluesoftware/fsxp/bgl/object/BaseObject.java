// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BaseObject.java

package com.zbluesoftware.fsxp.bgl.object;

import com.zbluesoftware.fsxp.bgl.item.Item;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class BaseObject
{

    public BaseObject()
    {
        itemAL = new ArrayList();
        objectAL = new ArrayList();
        name = "";
        offset = 0;
        length = 0;
    }

    public void addItem(Item item, boolean required)
    {
        HashMap hashMap = new HashMap();
        hashMap.put("item", item);
        hashMap.put("required", new Boolean(required));
        itemAL.add(hashMap);
    }

    public ArrayList getItemAL()
    {
        return itemAL;
    }

    public void setItemProperty(String itemName, int property, Object value)
    {
        for(int i = itemAL.size() - 1; i >= 0; i--)
        {
            Item item = (Item)((HashMap)itemAL.get(i)).get("item");
            if(item.getName().equals(itemName))
            {
                switch(property)
                {
                case 1: // '\001'
                    item.setName((String)value);
                    break;

                case 2: // '\002'
                    item.setDataType((String)value);
                    break;

                case 3: // '\003'
                    item.setOffset(((Integer)value).intValue());
                    break;

                case 4: // '\004'
                    item.setLength(((Integer)value).intValue());
                    break;

                case 5: // '\005'
                    item.setHexData((String)value);
                    break;

                case 6: // '\006'
                    item.setDecodedData(value);
                    break;
                }
                return;
            }
        }

    }

    public Item getItem(String itemName)
    {
        for(int i = itemAL.size() - 1; i >= 0; i--)
        {
            Item item = (Item)((HashMap)itemAL.get(i)).get("item");
            if(item.getName().equals(itemName))
                return item;
        }

        return null;
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

    public void addBaseObject(BaseObject baseObject)
    {
        objectAL.add(baseObject);
    }

    public ArrayList getObjectAL()
    {
        return objectAL;
    }

    protected ArrayList itemAL;
    protected ArrayList objectAL;
    protected String name;
    protected int offset;
    protected int length;
    public static final int NAME = 1;
    public static final int DATA_TYPE = 2;
    public static final int OFFSET = 3;
    public static final int LENGTH = 4;
    public static final int HEX_DATA = 5;
    public static final int DECODED_DATA = 6;
}
