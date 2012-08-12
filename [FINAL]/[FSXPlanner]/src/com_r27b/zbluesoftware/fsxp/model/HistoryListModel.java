// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   HistoryListModel.java

package com.zbluesoftware.fsxp.model;

import com.zbluesoftware.fsxp.engine.LogEngine;
import com.zbluesoftware.fsxp.engine.SettingsEngine;
import com.zbluesoftware.fsxp.menu.MenuAction;
import com.zbluesoftware.fsxp.menu.MenuFactory;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Vector;

// Referenced classes of package com.zbluesoftware.fsxp.model:
//            HistoryModel, BaseModel

public class HistoryListModel
    implements PropertyChangeListener
{

    private HistoryListModel()
    {
        models = new ArrayList();
        redoModel = new HistoryModel();
        listeners = new Vector();
        maxHistoryItems = SettingsEngine.getInstance().getMaxHistoryItems();
        recordChanges = true;
        SettingsEngine.getInstance().addPropertyChangeListener(this);
    }

    public static HistoryListModel getInstance()
    {
        if(model == null)
            model = new HistoryListModel();
        return model;
    }

    public void setRecordChanges(boolean recordChanges)
    {
        this.recordChanges = recordChanges;
    }

    public ArrayList getModels()
    {
        return models;
    }

    public HistoryModel getModel(int index)
    {
        if(index >= 0 && index < models.size())
            return (HistoryModel)models.get(index);
        else
            return new HistoryModel();
    }

    public void addModel(HistoryModel historyModel)
    {
        if(recordChanges)
        {
            if(isContinuousChange(historyModel))
            {
                ((HistoryModel)models.get(0)).setNewValue(historyModel.getNewValue());
                ((HistoryModel)models.get(0)).setDate(historyModel.getDate());
            } else
            {
                models.add(0, historyModel);
                if(models.size() > maxHistoryItems)
                    models.remove(models.size() - 1);
                MenuFactory.getInstance().getMenuAction(new Integer(18)).setEnabled(true);
                MenuFactory.getInstance().getMenuAction(new Integer(18)).putValue("Name", (new StringBuilder()).append("Undo ").append(historyModel.getPropertyDesc()).append(" change").toString());
            }
            firePropertyChange("addModel", new Integer(0), new Integer(0));
        }
    }

    private boolean isContinuousChange(HistoryModel historyModel)
    {
        if(models.size() == 0)
            return false;
        HistoryModel lastModel = (HistoryModel)models.get(0);
        if(lastModel.getBaseModel() != historyModel.getBaseModel())
            return false;
        return lastModel.getPropertyDesc().equals(historyModel.getPropertyDesc());
    }

    public void removeModel(HistoryModel historyModel)
    {
        for(int i = models.size() - 1; i >= 0; i--)
            if(models.get(i) == historyModel)
            {
                models.remove(i);
                firePropertyChange("removeModel", new Integer(i), new Integer(i));
                if(models.size() == 0)
                {
                    MenuFactory.getInstance().getMenuAction(new Integer(18)).setEnabled(false);
                    MenuFactory.getInstance().getMenuAction(new Integer(18)).putValue("Name", "Undo");
                } else
                {
                    MenuFactory.getInstance().getMenuAction(new Integer(18)).setEnabled(true);
                    MenuFactory.getInstance().getMenuAction(new Integer(18)).putValue("Name", (new StringBuilder()).append("Undo ").append(((HistoryModel)models.get(0)).getPropertyDesc()).append(" change").toString());
                }
                return;
            }

    }

    public HistoryModel getRedoModel()
    {
        return redoModel;
    }

    public void setRedoModel(HistoryModel redoModel)
    {
        this.redoModel = redoModel;
        if(redoModel == null)
        {
            MenuFactory.getInstance().getMenuAction(new Integer(19)).setEnabled(false);
            MenuFactory.getInstance().getMenuAction(new Integer(19)).putValue("Name", "Redo");
        } else
        {
            MenuFactory.getInstance().getMenuAction(new Integer(19)).setEnabled(true);
            MenuFactory.getInstance().getMenuAction(new Integer(19)).putValue("Name", (new StringBuilder()).append("Redo ").append(redoModel.getPropertyDesc()).append(" change").toString());
        }
    }

    public void redoItem()
    {
        BaseModel baseModel = redoModel.getBaseModel();
        String setMethod = redoModel.getSetMethodName();
        Object methodValue = redoModel.getNewValue();
        Object objects[] = new Object[1];
        objects[0] = methodValue;
        try
        {
            Method methods[] = baseModel.getClass().getMethods();
            int i = methods.length - 1;
            do
            {
                if(i < 0)
                    break;
                if(methods[i].getName().equals(setMethod))
                {
                    baseModel.setShouldNotify(false);
                    methods[i].invoke(baseModel, objects);
                    baseModel.setShouldNotify(true);
                    break;
                }
                i--;
            } while(true);
        }
        catch(IllegalAccessException iae)
        {
            LogEngine.getInstance().log(iae);
        }
        catch(InvocationTargetException ite)
        {
            LogEngine.getInstance().log(ite);
        }
        addModel(redoModel);
        setRedoModel(null);
    }

    public void undoItem(HistoryModel historyModel)
    {
        BaseModel baseModel = historyModel.getBaseModel();
        String setMethod = historyModel.getSetMethodName();
        Object methodValue = historyModel.getOldValue();
        Object objects[] = new Object[1];
        objects[0] = methodValue;
        try
        {
            Method methods[] = baseModel.getClass().getMethods();
            int i = methods.length - 1;
            do
            {
                if(i < 0)
                    break;
                if(methods[i].getName().equals(setMethod))
                {
                    baseModel.setShouldNotify(false);
                    methods[i].invoke(baseModel, objects);
                    baseModel.setShouldNotify(true);
                    break;
                }
                i--;
            } while(true);
        }
        catch(IllegalAccessException iae)
        {
            LogEngine.getInstance().log(iae);
        }
        catch(InvocationTargetException ite)
        {
            LogEngine.getInstance().log(ite);
        }
        removeModel(historyModel);
        setRedoModel(historyModel);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener)
    {
        if(!listeners.contains(listener))
            listeners.add(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener)
    {
        listeners.remove(listener);
    }

    protected void firePropertyChange(String propertyName, Object oldValue, Object newValue)
    {
        Vector list;
        synchronized(this)
        {
            list = (Vector)listeners.clone();
        }
        if(list.size() == 0)
            return;
        PropertyChangeEvent event = new PropertyChangeEvent(this, propertyName, oldValue, newValue);
        for(int i = list.size() - 1; i >= 0; i--)
            ((PropertyChangeListener)list.elementAt(i)).propertyChange(event);

    }

    public void propertyChange(PropertyChangeEvent event)
    {
        if((event.getSource() instanceof SettingsEngine) && event.getPropertyName().equals("maxHistoryItems"))
            maxHistoryItems = ((Integer)event.getNewValue()).intValue();
    }

    private ArrayList models;
    private HistoryModel redoModel;
    private Vector listeners;
    private int maxHistoryItems;
    private boolean recordChanges;
    public static final int LOCAL_SOURCE = 0;
    public static final int REMOTE_SOURCE = 1;
    private static HistoryListModel model = null;

}
