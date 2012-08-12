// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LockingEngine.java

package com.zbluesoftware.fsxp.engine;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Vector;
import java.util.prefs.Preferences;

public class LockingEngine
{

    private LockingEngine()
    {
        listeners = new Vector();
        runwaysLocked = false;
        taxiwaysLocked = false;
        taxiwaySignsLocked = false;
        jetwaysLocked = false;
        parkingLocked = false;
        startingLocked = false;
        helipadsLocked = false;
        towersLocked = false;
        apronsLocked = false;
        edgeLightsLocked = false;
        boundaryLocked = false;
        blastLocked = false;
        exclusionLocked = false;
        ilsLocked = false;
        markersLocked = false;
        vorLocked = false;
        ndbLocked = false;
        windsocksLocked = false;
        triggersLocked = false;
        sceneryLocked = false;
        readLocks();
    }

    public static LockingEngine getInstance()
    {
        if(engine == null)
            engine = new LockingEngine();
        return engine;
    }

    public boolean getRunwaysLocked()
    {
        return runwaysLocked;
    }

    public void setRunwaysLocked(boolean runwaysLocked)
    {
        if(this.runwaysLocked != runwaysLocked)
        {
            firePropertyChange("runwaysLocked", new Boolean(!runwaysLocked), new Boolean(runwaysLocked));
            this.runwaysLocked = runwaysLocked;
        }
    }

    public boolean getTaxiwaysLocked()
    {
        return taxiwaysLocked;
    }

    public void setTaxiwaysLocked(boolean taxiwaysLocked)
    {
        if(this.taxiwaysLocked != taxiwaysLocked)
        {
            firePropertyChange("taxiwaysLocked", new Boolean(!taxiwaysLocked), new Boolean(taxiwaysLocked));
            this.taxiwaysLocked = taxiwaysLocked;
        }
    }

    public boolean getTaxiwaySignsLocked()
    {
        return taxiwaySignsLocked;
    }

    public void setTaxiwaySignsLocked(boolean taxiwaySignsLocked)
    {
        if(this.taxiwaySignsLocked != taxiwaySignsLocked)
        {
            firePropertyChange("taxiwaySignsLocked", new Boolean(!taxiwaySignsLocked), new Boolean(taxiwaySignsLocked));
            this.taxiwaySignsLocked = taxiwaySignsLocked;
        }
    }

    public boolean getJetwaysLocked()
    {
        return jetwaysLocked;
    }

    public void setJetwaysLocked(boolean jetwaysLocked)
    {
        if(this.jetwaysLocked != jetwaysLocked)
        {
            firePropertyChange("jetwaysLocked", new Boolean(!jetwaysLocked), new Boolean(jetwaysLocked));
            this.jetwaysLocked = jetwaysLocked;
        }
    }

    public boolean getParkingLocked()
    {
        return parkingLocked;
    }

    public void setParkingLocked(boolean parkingLocked)
    {
        if(this.parkingLocked != parkingLocked)
        {
            firePropertyChange("parkingLocked", new Boolean(!parkingLocked), new Boolean(parkingLocked));
            this.parkingLocked = parkingLocked;
        }
    }

    public boolean getStartingLocked()
    {
        return startingLocked;
    }

    public void setStartingLocked(boolean startingLocked)
    {
        if(this.startingLocked != startingLocked)
        {
            firePropertyChange("startingLocked", new Boolean(!startingLocked), new Boolean(startingLocked));
            this.startingLocked = startingLocked;
        }
    }

    public boolean getHelipadsLocked()
    {
        return helipadsLocked;
    }

    public void setHelipadsLocked(boolean helipadsLocked)
    {
        if(this.helipadsLocked != helipadsLocked)
        {
            firePropertyChange("helipadsLocked", new Boolean(!helipadsLocked), new Boolean(helipadsLocked));
            this.helipadsLocked = helipadsLocked;
        }
    }

    public boolean getTowersLocked()
    {
        return towersLocked;
    }

    public void setTowersLocked(boolean towersLocked)
    {
        if(this.towersLocked != towersLocked)
        {
            firePropertyChange("towersLocked", new Boolean(!towersLocked), new Boolean(towersLocked));
            this.towersLocked = towersLocked;
        }
    }

    public boolean getApronsLocked()
    {
        return apronsLocked;
    }

    public void setApronsLocked(boolean apronsLocked)
    {
        if(this.apronsLocked != apronsLocked)
        {
            firePropertyChange("apronsLocked", new Boolean(!apronsLocked), new Boolean(apronsLocked));
            this.apronsLocked = apronsLocked;
        }
    }

    public boolean getEdgeLightsLocked()
    {
        return edgeLightsLocked;
    }

    public void setEdgeLightsLocked(boolean edgeLightsLocked)
    {
        if(this.edgeLightsLocked != edgeLightsLocked)
        {
            firePropertyChange("edgeLightsLocked", new Boolean(!edgeLightsLocked), new Boolean(edgeLightsLocked));
            this.edgeLightsLocked = edgeLightsLocked;
        }
    }

    public boolean getBoundaryLocked()
    {
        return boundaryLocked;
    }

    public void setBoundaryLocked(boolean boundaryLocked)
    {
        if(this.boundaryLocked != boundaryLocked)
        {
            firePropertyChange("boundaryLocked", new Boolean(!boundaryLocked), new Boolean(boundaryLocked));
            this.boundaryLocked = boundaryLocked;
        }
    }

    public boolean getBlastLocked()
    {
        return blastLocked;
    }

    public void setBlastLocked(boolean blastLocked)
    {
        if(this.blastLocked != blastLocked)
        {
            firePropertyChange("blastLocked", new Boolean(!blastLocked), new Boolean(blastLocked));
            this.blastLocked = blastLocked;
        }
    }

    public boolean getExclusionLocked()
    {
        return exclusionLocked;
    }

    public void setExclusionLocked(boolean exclusionLocked)
    {
        if(this.exclusionLocked != exclusionLocked)
        {
            firePropertyChange("exclusionLocked", new Boolean(!exclusionLocked), new Boolean(exclusionLocked));
            this.exclusionLocked = exclusionLocked;
        }
    }

    public boolean getILSLocked()
    {
        return ilsLocked;
    }

    public void setILSLocked(boolean ilsLocked)
    {
        if(this.ilsLocked != ilsLocked)
        {
            firePropertyChange("ilsLocked", new Boolean(!ilsLocked), new Boolean(ilsLocked));
            this.ilsLocked = ilsLocked;
        }
    }

    public boolean getMarkersLocked()
    {
        return markersLocked;
    }

    public void setMarkersLocked(boolean markersLocked)
    {
        if(this.markersLocked != markersLocked)
        {
            firePropertyChange("markersLocked", new Boolean(!markersLocked), new Boolean(markersLocked));
            this.markersLocked = markersLocked;
        }
    }

    public boolean getVORLocked()
    {
        return vorLocked;
    }

    public void setVORLocked(boolean vorLocked)
    {
        if(this.vorLocked != vorLocked)
        {
            firePropertyChange("vorLocked", new Boolean(!vorLocked), new Boolean(vorLocked));
            this.vorLocked = vorLocked;
        }
    }

    public boolean getNDBLocked()
    {
        return ndbLocked;
    }

    public void setNDBLocked(boolean ndbLocked)
    {
        if(this.ndbLocked != ndbLocked)
        {
            firePropertyChange("ndbLocked", new Boolean(!ndbLocked), new Boolean(ndbLocked));
            this.ndbLocked = ndbLocked;
        }
    }

    public boolean getWindsocksLocked()
    {
        return windsocksLocked;
    }

    public void setWindsocksLocked(boolean windsocksLocked)
    {
        if(this.windsocksLocked != windsocksLocked)
        {
            firePropertyChange("windsocksLocked", new Boolean(!windsocksLocked), new Boolean(windsocksLocked));
            this.windsocksLocked = windsocksLocked;
        }
    }

    public boolean getTriggersLocked()
    {
        return triggersLocked;
    }

    public void setTriggersLocked(boolean triggersLocked)
    {
        if(this.triggersLocked != triggersLocked)
        {
            firePropertyChange("triggersLocked", new Boolean(!triggersLocked), new Boolean(triggersLocked));
            this.triggersLocked = triggersLocked;
        }
    }

    public boolean getSceneryLocked()
    {
        return sceneryLocked;
    }

    public void setSceneryLocked(boolean sceneryLocked)
    {
        if(this.sceneryLocked != sceneryLocked)
        {
            firePropertyChange("sceneryLocked", new Boolean(!sceneryLocked), new Boolean(sceneryLocked));
            this.sceneryLocked = sceneryLocked;
        }
    }

    public void writeLocks()
    {
        Preferences userPrefs = Preferences.userNodeForPackage(getClass());
        userPrefs.putBoolean("runwaysLocked", runwaysLocked);
        userPrefs.putBoolean("taxiwaysLocked", taxiwaysLocked);
        userPrefs.putBoolean("taxiwaySignsLocked", taxiwaySignsLocked);
        userPrefs.putBoolean("jetwaysLocked", jetwaysLocked);
        userPrefs.putBoolean("parkingLocked", parkingLocked);
        userPrefs.putBoolean("startingLocked", startingLocked);
        userPrefs.putBoolean("helipadsLocked", helipadsLocked);
        userPrefs.putBoolean("towersLocked", towersLocked);
        userPrefs.putBoolean("apronsLocked", apronsLocked);
        userPrefs.putBoolean("edgeLightsLocked", edgeLightsLocked);
        userPrefs.putBoolean("boundaryLocked", boundaryLocked);
        userPrefs.putBoolean("blastLocked", blastLocked);
        userPrefs.putBoolean("exclusionLocked", exclusionLocked);
        userPrefs.putBoolean("ilsLocked", ilsLocked);
        userPrefs.putBoolean("markersLocked", markersLocked);
        userPrefs.putBoolean("vorLocked", vorLocked);
        userPrefs.putBoolean("ndbLocked", ndbLocked);
        userPrefs.putBoolean("windsocksLocked", windsocksLocked);
        userPrefs.putBoolean("triggersLocked", triggersLocked);
        userPrefs.putBoolean("sceneryLocked", sceneryLocked);
    }

    private void readLocks()
    {
        Preferences userPrefs = Preferences.userNodeForPackage(getClass());
        runwaysLocked = userPrefs.getBoolean("runwaysLocked", runwaysLocked);
        taxiwaysLocked = userPrefs.getBoolean("taxiwaysLocked", taxiwaysLocked);
        taxiwaySignsLocked = userPrefs.getBoolean("taxiwaySignsLocked", taxiwaySignsLocked);
        jetwaysLocked = userPrefs.getBoolean("jetwaysLocked", jetwaysLocked);
        parkingLocked = userPrefs.getBoolean("parkingLocked", parkingLocked);
        startingLocked = userPrefs.getBoolean("startingLocked", startingLocked);
        helipadsLocked = userPrefs.getBoolean("helipadsLocked", helipadsLocked);
        towersLocked = userPrefs.getBoolean("towersLocked", towersLocked);
        apronsLocked = userPrefs.getBoolean("apronsLocked", apronsLocked);
        edgeLightsLocked = userPrefs.getBoolean("edgeLightsLocked", edgeLightsLocked);
        boundaryLocked = userPrefs.getBoolean("boundaryLocked", boundaryLocked);
        blastLocked = userPrefs.getBoolean("blastLocked", blastLocked);
        exclusionLocked = userPrefs.getBoolean("exclusionLocked", exclusionLocked);
        ilsLocked = userPrefs.getBoolean("ilsLocked", ilsLocked);
        markersLocked = userPrefs.getBoolean("markersLocked", markersLocked);
        vorLocked = userPrefs.getBoolean("vorLocked", vorLocked);
        ndbLocked = userPrefs.getBoolean("ndbLocked", ndbLocked);
        windsocksLocked = userPrefs.getBoolean("windsocksLocked", windsocksLocked);
        triggersLocked = userPrefs.getBoolean("triggersLocked", triggersLocked);
        sceneryLocked = userPrefs.getBoolean("sceneryLocked", sceneryLocked);
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

    private Vector listeners;
    private boolean runwaysLocked;
    private boolean taxiwaysLocked;
    private boolean taxiwaySignsLocked;
    private boolean jetwaysLocked;
    private boolean parkingLocked;
    private boolean startingLocked;
    private boolean helipadsLocked;
    private boolean towersLocked;
    private boolean apronsLocked;
    private boolean edgeLightsLocked;
    private boolean boundaryLocked;
    private boolean blastLocked;
    private boolean exclusionLocked;
    private boolean ilsLocked;
    private boolean markersLocked;
    private boolean vorLocked;
    private boolean ndbLocked;
    private boolean windsocksLocked;
    private boolean triggersLocked;
    private boolean sceneryLocked;
    private static LockingEngine engine = null;

}
