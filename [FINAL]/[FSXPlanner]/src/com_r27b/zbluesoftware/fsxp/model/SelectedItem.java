// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SelectedItem.java

package com.zbluesoftware.fsxp.model;

import com.zbluesoftware.fsxp.engine.LockingEngine;
import com.zbluesoftware.fsxp.menu.MenuAction;
import com.zbluesoftware.fsxp.menu.MenuFactory;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

// Referenced classes of package com.zbluesoftware.fsxp.model:
//            RunwayModel, TaxiwayPointModel, TaxiwaySignModel, JetwayModel, 
//            TaxiwayParkingModel, StartModel, HelipadModel, TowerModel, 
//            ApronModel, ApronEdgeLightModel, BoundaryFenceModel, BlastFenceModel, 
//            ExclusionModel, ILSModel, MarkerModel, VORModel, 
//            NDBModel, WindsockModel, TriggerModel, SceneryModel, 
//            PlaneModel, BaseModel

public class SelectedItem
{

    private SelectedItem()
    {
        baseModel = null;
        listener = null;
        modelDraggable = true;
    }

    public static SelectedItem getInstance()
    {
        if(item == null)
            item = new SelectedItem();
        return item;
    }

    public void selectBaseModel(BaseModel baseModel)
    {
        if(this.baseModel != null)
            this.baseModel.setCurrentlySelected(false);
        firePropertyChange(this.baseModel, baseModel);
        this.baseModel = baseModel;
        if(baseModel != null)
        {
            MenuFactory.getInstance().getMenuAction(new Integer(21)).setEnabled(true);
            MenuFactory.getInstance().getMenuAction(new Integer(22)).setEnabled(baseModel.isCopyable());
        } else
        {
            MenuFactory.getInstance().getMenuAction(new Integer(21)).setEnabled(false);
            MenuFactory.getInstance().getMenuAction(new Integer(22)).setEnabled(false);
        }
        if(baseModel instanceof RunwayModel)
            modelDraggable = !LockingEngine.getInstance().getRunwaysLocked();
        else
        if(baseModel instanceof TaxiwayPointModel)
            modelDraggable = !LockingEngine.getInstance().getTaxiwaysLocked();
        else
        if(baseModel instanceof TaxiwaySignModel)
            modelDraggable = !LockingEngine.getInstance().getTaxiwaySignsLocked();
        else
        if(baseModel instanceof JetwayModel)
            modelDraggable = !LockingEngine.getInstance().getJetwaysLocked();
        else
        if(baseModel instanceof TaxiwayParkingModel)
            modelDraggable = !LockingEngine.getInstance().getParkingLocked();
        else
        if(baseModel instanceof StartModel)
            modelDraggable = !LockingEngine.getInstance().getStartingLocked();
        else
        if(baseModel instanceof HelipadModel)
            modelDraggable = !LockingEngine.getInstance().getHelipadsLocked();
        else
        if(baseModel instanceof TowerModel)
            modelDraggable = !LockingEngine.getInstance().getTowersLocked();
        else
        if(baseModel instanceof ApronModel)
            modelDraggable = !LockingEngine.getInstance().getApronsLocked();
        else
        if(baseModel instanceof ApronEdgeLightModel)
            modelDraggable = !LockingEngine.getInstance().getEdgeLightsLocked();
        else
        if(baseModel instanceof BoundaryFenceModel)
            modelDraggable = !LockingEngine.getInstance().getBoundaryLocked();
        else
        if(baseModel instanceof BlastFenceModel)
            modelDraggable = !LockingEngine.getInstance().getBlastLocked();
        else
        if(baseModel instanceof ExclusionModel)
            modelDraggable = !LockingEngine.getInstance().getExclusionLocked();
        else
        if(baseModel instanceof ILSModel)
            modelDraggable = !LockingEngine.getInstance().getILSLocked();
        else
        if(baseModel instanceof MarkerModel)
            modelDraggable = !LockingEngine.getInstance().getMarkersLocked();
        else
        if(baseModel instanceof VORModel)
            modelDraggable = !LockingEngine.getInstance().getVORLocked();
        else
        if(baseModel instanceof NDBModel)
            modelDraggable = !LockingEngine.getInstance().getNDBLocked();
        else
        if(baseModel instanceof WindsockModel)
            modelDraggable = !LockingEngine.getInstance().getWindsocksLocked();
        else
        if(baseModel instanceof TriggerModel)
            modelDraggable = !LockingEngine.getInstance().getTriggersLocked();
        else
        if(baseModel instanceof SceneryModel)
            modelDraggable = !LockingEngine.getInstance().getSceneryLocked();
        else
        if(baseModel instanceof PlaneModel)
            modelDraggable = true;
    }

    public BaseModel getBaseModel()
    {
        return baseModel;
    }

    public boolean isModelDraggable()
    {
        return modelDraggable;
    }

    public synchronized void setPropertyChangeListener(PropertyChangeListener listener)
    {
        this.listener = listener;
    }

    public synchronized void removePropertyChangeListener(PropertyChangeListener listener)
    {
        if(this.listener == listener)
            this.listener = null;
    }

    protected void firePropertyChange(BaseModel oldValue, BaseModel newValue)
    {
        if(listener == null)
        {
            return;
        } else
        {
            PropertyChangeEvent event = new PropertyChangeEvent(this, "BaseModel", oldValue, newValue);
            listener.propertyChange(event);
            return;
        }
    }

    private PropertyChangeListener listener;
    private BaseModel baseModel;
    private boolean modelDraggable;
    private static SelectedItem item = null;

}
