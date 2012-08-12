// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FontEngine.java

package com.zbluesoftware.fsxp.engine;

import com.zbluesoftware.fsxp.util.Utilities;
import java.awt.Font;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Vector;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

// Referenced classes of package com.zbluesoftware.fsxp.engine:
//            LogEngine

public class FontEngine
{

    private FontEngine()
    {
        menuFont = Utilities.MENU_FONT;
        latLonFont = Utilities.LAT_LON_FONT;
        parkingFont = Utilities.PARKING_FONT;
        jetwayFont = Utilities.JETWAY_FONT;
        ilsFont = Utilities.ILS_FONT;
        sceneryFont = Utilities.SCENERY_FONT;
        vorFont = Utilities.VOR_FONT;
        ndbFont = Utilities.NDB_FONT;
        listeners = new Vector();
        readPreferences();
        Utilities.MENU_FONT = menuFont;
        Utilities.LAT_LON_FONT = latLonFont;
        Utilities.PARKING_FONT = parkingFont;
        Utilities.JETWAY_FONT = jetwayFont;
        Utilities.ILS_FONT = ilsFont;
        Utilities.SCENERY_FONT = sceneryFont;
        Utilities.VOR_FONT = vorFont;
        Utilities.NDB_FONT = ndbFont;
    }

    public static FontEngine getInstance()
    {
        if(engine == null)
            engine = new FontEngine();
        return engine;
    }

    public Font getMenuFont()
    {
        return menuFont;
    }

    public void setMenuFont(Font menuFont)
    {
        if(!this.menuFont.equals(menuFont))
            firePropertyChange("menuFont", this.menuFont, menuFont);
        this.menuFont = menuFont;
    }

    public Font getLatLonFont()
    {
        return latLonFont;
    }

    public void setLatLonFont(Font latLonFont)
    {
        this.latLonFont = latLonFont;
    }

    public Font getParkingFont()
    {
        return parkingFont;
    }

    public void setParkingFont(Font parkingFont)
    {
        this.parkingFont = parkingFont;
    }

    public Font getJetwayFont()
    {
        return jetwayFont;
    }

    public void setJetwayFont(Font jetwayFont)
    {
        this.jetwayFont = jetwayFont;
    }

    public Font getILSFont()
    {
        return ilsFont;
    }

    public void setILSFont(Font ilsFont)
    {
        this.ilsFont = ilsFont;
    }

    public Font getSceneryFont()
    {
        return sceneryFont;
    }

    public void setSceneryFont(Font sceneryFont)
    {
        this.sceneryFont = sceneryFont;
    }

    public Font getVORFont()
    {
        return vorFont;
    }

    public void setVORFont(Font vorFont)
    {
        this.vorFont = vorFont;
    }

    public Font getNDBFont()
    {
        return ndbFont;
    }

    public void setNDBFont(Font ndbFont)
    {
        this.ndbFont = ndbFont;
    }

    private void readPreferences()
    {
        Preferences prefs = Preferences.userNodeForPackage(getClass());
        try
        {
            if(prefs.nodeExists("fonts"))
            {
                Preferences fontNode = prefs.node("fonts");
                String fontFamily = fontNode.get("menuFont", menuFont.getFamily());
                int fontStyle = fontNode.getInt("menuStyle", menuFont.getStyle());
                int fontSize = fontNode.getInt("menuSize", menuFont.getSize());
                if(fontFamily.length() > 0)
                    menuFont = new Font(fontFamily, fontStyle, fontSize);
                fontFamily = fontNode.get("latLonFont", latLonFont.getFamily());
                fontStyle = fontNode.getInt("latLonStyle", latLonFont.getStyle());
                fontSize = fontNode.getInt("latLonSize", latLonFont.getSize());
                if(fontFamily.length() > 0)
                    latLonFont = new Font(fontFamily, fontStyle, fontSize);
                fontFamily = fontNode.get("parkingFont", parkingFont.getFamily());
                fontStyle = fontNode.getInt("parkingStyle", parkingFont.getStyle());
                fontSize = fontNode.getInt("parkingSize", parkingFont.getSize());
                if(fontFamily.length() > 0)
                    parkingFont = new Font(fontFamily, fontStyle, fontSize);
                fontFamily = fontNode.get("jetwayFont", jetwayFont.getFamily());
                fontStyle = fontNode.getInt("jetwayStyle", jetwayFont.getStyle());
                fontSize = fontNode.getInt("jetwaySize", jetwayFont.getSize());
                if(fontFamily.length() > 0)
                    jetwayFont = new Font(fontFamily, fontStyle, fontSize);
                fontFamily = fontNode.get("ilsFont", ilsFont.getFamily());
                fontStyle = fontNode.getInt("ilsStyle", ilsFont.getStyle());
                fontSize = fontNode.getInt("ilsSize", ilsFont.getSize());
                if(fontFamily.length() > 0)
                    ilsFont = new Font(fontFamily, fontStyle, fontSize);
                fontFamily = fontNode.get("sceneryFont", sceneryFont.getFamily());
                fontStyle = fontNode.getInt("sceneryStyle", sceneryFont.getStyle());
                fontSize = fontNode.getInt("scenerySize", sceneryFont.getSize());
                if(fontFamily.length() > 0)
                    sceneryFont = new Font(fontFamily, fontStyle, fontSize);
                fontFamily = fontNode.get("vorFont", vorFont.getFamily());
                fontStyle = fontNode.getInt("vorStyle", vorFont.getStyle());
                fontSize = fontNode.getInt("vorSize", vorFont.getSize());
                if(fontFamily.length() > 0)
                    vorFont = new Font(fontFamily, fontStyle, fontSize);
                fontFamily = fontNode.get("ndbFont", ndbFont.getFamily());
                fontStyle = fontNode.getInt("ndbStyle", ndbFont.getStyle());
                fontSize = fontNode.getInt("ndbSize", ndbFont.getSize());
                if(fontFamily.length() > 0)
                    ndbFont = new Font(fontFamily, fontStyle, fontSize);
            }
        }
        catch(BackingStoreException bse)
        {
            LogEngine.getInstance().log(1, bse);
        }
    }

    public void writePreferences()
    {
        Preferences prefs = Preferences.userNodeForPackage(getClass());
        Preferences fontNode = prefs.node("fonts");
        fontNode.put("menuFont", menuFont.getFamily());
        fontNode.putInt("menuStyle", menuFont.getStyle());
        fontNode.putInt("menuSize", menuFont.getSize());
        fontNode.put("latLonFont", latLonFont.getFamily());
        fontNode.putInt("latLonStyle", latLonFont.getStyle());
        fontNode.putInt("latLonSize", latLonFont.getSize());
        fontNode.put("parkingFont", parkingFont.getFamily());
        fontNode.putInt("parkingStyle", parkingFont.getStyle());
        fontNode.putInt("parkingSize", parkingFont.getSize());
        fontNode.put("jetwayFont", jetwayFont.getFamily());
        fontNode.putInt("jetwayStyle", jetwayFont.getStyle());
        fontNode.putInt("jetwaySize", jetwayFont.getSize());
        fontNode.put("ilsFont", ilsFont.getFamily());
        fontNode.putInt("ilsStyle", ilsFont.getStyle());
        fontNode.putInt("ilsSize", ilsFont.getSize());
        fontNode.put("sceneryFont", sceneryFont.getFamily());
        fontNode.putInt("sceneryStyle", sceneryFont.getStyle());
        fontNode.putInt("scenerySize", sceneryFont.getSize());
        fontNode.put("vorFont", vorFont.getFamily());
        fontNode.putInt("vorStyle", vorFont.getStyle());
        fontNode.putInt("vorSize", vorFont.getSize());
        fontNode.put("ndbFont", ndbFont.getFamily());
        fontNode.putInt("ndbStyle", ndbFont.getStyle());
        fontNode.putInt("ndbSize", ndbFont.getSize());
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

    private Vector listeners;
    private Font menuFont;
    private Font latLonFont;
    private Font parkingFont;
    private Font jetwayFont;
    private Font ilsFont;
    private Font sceneryFont;
    private Font vorFont;
    private Font ndbFont;
    private static FontEngine engine = null;

}
