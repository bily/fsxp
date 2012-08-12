// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BaseModel.java

package com.zbluesoftware.fsxp.model;

import com.zbluesoftware.fsxp.util.LatLonPoint;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.GeneralPath;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Vector;

// Referenced classes of package com.zbluesoftware.fsxp.model:
//            SelectedItem

public abstract class BaseModel
{

    public BaseModel()
    {
        listeners = new Vector();
        centerPoint = new LatLonPoint(0.0D, 0.0D);
        rotationAngle = 0;
        scale = 1.0F;
        currentlySelected = false;
        shouldNotify = true;
        modelName = "";
        parentModel = null;
    }

    public abstract void paint(Graphics2D graphics2d, boolean flag);

    public Shape getClip()
    {
        return null;
    }

    public boolean isWithinObject(int x, int y)
    {
        if(modelPath == null)
            return false;
        else
            return modelPath.contains(x, y);
    }

    public boolean moveTo(LatLonPoint latLonPoint, double centerX, double d)
    {
        return false;
    }

    public boolean isCopyable()
    {
        return false;
    }

    public void setCenterPoint(LatLonPoint centerPoint)
    {
        this.centerPoint = centerPoint;
    }

    public LatLonPoint getCenterPoint()
    {
        return centerPoint;
    }

    public void setRotationAngle(int rotationAngle)
    {
        this.rotationAngle = rotationAngle;
    }

    public String getModelName()
    {
        return modelName;
    }

    public void setShouldNotify(boolean shouldNotify)
    {
        this.shouldNotify = shouldNotify;
    }

    public void setCurrentlySelected(boolean currentlySelected)
    {
        if(currentlySelected)
            SelectedItem.getInstance().selectBaseModel(this);
        this.currentlySelected = currentlySelected;
    }

    public boolean isCurrentlySelected()
    {
        return currentlySelected;
    }

    public void setScale(float scale)
    {
        this.scale = scale;
    }

    public float getScale()
    {
        return scale;
    }

    public float getX()
    {
        return x;
    }

    public void setX(float x)
    {
        this.x = x;
    }

    public float getY()
    {
        return y;
    }

    public void setY(float y)
    {
        this.y = y;
    }

    public Object clone()
    {
        return null;
    }

    public BaseModel getParentModel()
    {
        return parentModel;
    }

    public void setParentModel(BaseModel parentModel)
    {
        this.parentModel = parentModel;
    }

    public Vector getListeners()
    {
        return listeners;
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

    public void firePropertyChange(String propertyName, Object oldValue, Object newValue)
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

    protected Vector listeners;
    protected GeneralPath modelPath;
    protected LatLonPoint centerPoint;
    protected String modelName;
    protected BaseModel parentModel;
    protected int rotationAngle;
    protected float scale;
    protected float x;
    protected float y;
    protected boolean currentlySelected;
    protected boolean shouldNotify;
}
