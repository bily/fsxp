// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   OpenAirportModel.java

package com.zbluesoftware.fsxp.model;

import com.zbluesoftware.fsxp.comparator.OpenAirportModelSort;
import java.util.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class OpenAirportModel
{

    private OpenAirportModel()
    {
        fileList = new ArrayList();
        listeners = new Vector();
        changeEvent = new ChangeEvent(this);
    }

    public static OpenAirportModel getInstance()
    {
        if(model == null)
            model = new OpenAirportModel();
        return model;
    }

    public void addAirport(String fileName, String ident)
    {
        for(int i = fileList.size() - 1; i >= 0; i--)
            if(((String)((HashMap)fileList.get(i)).get("fileName")).equals(fileName) && ((String)((HashMap)fileList.get(i)).get("ident")).equals(ident))
                return;

        HashMap hashMap = new HashMap();
        hashMap.put("fileName", fileName);
        hashMap.put("ident", ident);
        fileList.add(hashMap);
        reorderList();
        fireStateChanged();
    }

    public void updateAirportName(String oldFileName, String oldIdent, String newFileName, String newIdent)
    {
        for(int i = fileList.size() - 1; i >= 0; i--)
        {
            HashMap hashMap = (HashMap)fileList.get(i);
            if(((String)hashMap.get("fileName")).equals(oldFileName) && ((String)hashMap.get("ident")).equals(oldIdent))
            {
                hashMap.put("fileName", newFileName);
                hashMap.put("ident", newIdent);
                reorderList();
                fireStateChanged();
                return;
            }
        }

    }

    public void removeAirport(String fileName, String ident)
    {
        for(int i = fileList.size() - 1; i >= 0; i--)
        {
            HashMap hashMap = (HashMap)fileList.get(i);
            if(((String)hashMap.get("fileName")).equals(fileName) && ((String)hashMap.get("ident")).equals(ident))
            {
                fileList.remove(hashMap);
                reorderList();
                fireStateChanged();
                return;
            }
        }

    }

    public String[] getAirportNames()
    {
        String fileNames[] = new String[fileList.size()];
        for(int i = 0; i < fileList.size(); i++)
        {
            HashMap hashMap = (HashMap)fileList.get(i);
            fileNames[i] = (new StringBuilder()).append((String)hashMap.get("fileName")).append(" [").append((String)hashMap.get("ident")).append("]").toString();
        }

        return fileNames;
    }

    public String getAirportIdentAt(int index)
    {
        if(index >= 0 && index < fileList.size())
        {
            HashMap hashMap = (HashMap)fileList.get(index);
            return (String)hashMap.get("ident");
        } else
        {
            return "";
        }
    }

    private void reorderList()
    {
        Collections.sort(fileList, new OpenAirportModelSort());
    }

    public void addChangeListener(ChangeListener listener)
    {
        if(!listeners.contains(listener))
            listeners.addElement(listener);
    }

    public void removeChangeListener(ChangeListener listener)
    {
        listeners.remove(listener);
    }

    protected void fireStateChanged()
    {
        Vector list;
        synchronized(this)
        {
            list = (Vector)listeners.clone();
        }
        if(list.size() == 0)
            return;
        for(int i = list.size() - 1; i >= 0; i--)
            ((ChangeListener)list.elementAt(i)).stateChanged(changeEvent);

    }

    private ArrayList fileList;
    private Vector listeners;
    private ChangeEvent changeEvent;
    private static OpenAirportModel model = null;

}
