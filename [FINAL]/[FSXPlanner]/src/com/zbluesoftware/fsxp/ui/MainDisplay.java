// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MainDisplay.java

package com.zbluesoftware.fsxp.ui;

import com.zbluesoftware.fsxp.engine.CursorEngine;
import com.zbluesoftware.fsxp.engine.LogEngine;
import com.zbluesoftware.fsxp.model.*;
import com.zbluesoftware.fsxp.simconnect.FSXConnection;
import com.zbluesoftware.fsxp.simconnect.PlaneDimensions;
import com.zbluesoftware.fsxp.ui.display.AirportDisplay;
import java.awt.*;
import java.beans.*;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.*;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

// Referenced classes of package com.zbluesoftware.fsxp.ui:
//            AirportFrame

public class MainDisplay extends JPanel
    implements InternalFrameListener
{

    private MainDisplay()
    {
        init();
    }

    public static MainDisplay getInstance()
    {
        if(display == null)
            display = new MainDisplay();
        return display;
    }

    private void init()
    {
        listeners = new Vector();
        setLayout(new BorderLayout());
        desktop = new JDesktopPane();
        desktop.setBackground(Color.white);
        add(desktop, "Center");
    }

    public void addAirportFrame(String ident, String fileName)
    {
        AirportListModel.getInstance().setAirportFrame(ident, fileName, new AirportFrame(AirportListModel.getInstance().getAirportModel(ident, fileName)));
        showLayoutFrame(AirportListModel.getInstance().getAirportFrame(ident, fileName), true);
    }

    public void showAirportFrame(JInternalFrame frame)
    {
        showLayoutFrame(frame, true);
    }

    public void showAirportFrame(String ident)
    {
        JInternalFrame frames[] = desktop.getAllFrames();
        int i = frames.length - 1;
        do
        {
            if(i < 0)
                break;
            if((frames[i] instanceof AirportFrame) && frames[i].getTitle().endsWith((new StringBuilder()).append("[").append(ident).append("]").toString()))
            {
                showLayoutFrame(frames[i], true);
                break;
            }
            i--;
        } while(true);
    }

    private void showLayoutFrame(JInternalFrame frame, boolean maximized)
    {
        boolean found = false;
        JInternalFrame frames[] = desktop.getAllFrames();
        int i = frames.length - 1;
        do
        {
            if(i < 0)
                break;
            if(frames[i] == frame)
            {
                found = true;
                break;
            }
            i--;
        } while(true);
        if(!found)
        {
            frame.setVisible(true);
            frame.setLocation(0, 0);
            desktop.add(frame);
            frame.addInternalFrameListener(this);
            if(frame.getSize().width < frame.getMinimumSize().width)
                frame.setSize(frame.getMinimumSize().width, frame.getSize().height);
            if(frame.getSize().height < frame.getMinimumSize().height)
                frame.setSize(frame.getSize().width, frame.getMinimumSize().height);
            if(desktop.getSize().width < frame.getSize().width)
            {
                frame.setSize(Math.max(desktop.getSize().width, frame.getMinimumSize().width), frame.getSize().height);
                frame.setLocation(0, frame.getLocation().y);
            }
            if(desktop.getSize().height < frame.getSize().height)
            {
                frame.setSize(frame.getSize().width, Math.max(desktop.getSize().height, frame.getMinimumSize().height));
                frame.setLocation(frame.getLocation().x, 0);
            }
        }
        if(!frame.isVisible())
            frame.setVisible(true);
        desktop.setSelectedFrame(frame);
        frame.moveToFront();
        try
        {
            frame.setSelected(true);
            if(maximized)
                frame.setMaximum(true);
        }
        catch(PropertyVetoException pve)
        {
            LogEngine.getInstance().log(pve);
        }
    i = frames.length - 1;
    while (true)
    {
      if (i >= 0)
        break;
      if (frames[i] != frame)
        try
        {
          frames[i].setSelected(false);
        }
        catch (PropertyVetoException pve)
        {
          LogEngine.getInstance().log(pve);
        }
      --i;
    }
        AirportListModel.getInstance().setCurrentAirport(((AirportFrame)frame).getAirportModel());
        AlertModel.getInstance().firePropertyChange("scale", new Float(0.2F), new Float(((AirportFrame)frame).getAirportModel().getScale()));
    }

    public void setAirportDisplayCursor(Cursor cursor)
    {
        JInternalFrame frames[] = desktop.getAllFrames();
        for(int i = frames.length - 1; i >= 0; i--)
            if(frames[i] instanceof AirportFrame)
            {
                ((AirportFrame)frames[i]).getAirportDisplay().setCursor(cursor);
                ((AirportFrame)frames[i]).getAirportDisplay().resetFlags();
            }

        CursorEngine.getInstance().setCurrentCursor(cursor);
    }

    public void resetAirport()
    {
        JInternalFrame frames[] = desktop.getAllFrames();
        for(int i = frames.length - 1; i >= 0; i--)
            if(frames[i] instanceof AirportFrame)
            {
                ((AirportFrame)frames[i]).getAirportDisplay().recenter();
                return;
            }

    }

    public void repaintSelectedAirport()
    {
        JInternalFrame frame = desktop.getSelectedFrame();
        if(frame != null && (frame instanceof AirportFrame))
        {
            ((AirportFrame)frame).getAirportDisplay().setRecreate(true);
            ((AirportFrame)frame).getAirportDisplay().repaint();
        }
    }

    public AirportModel getCurrentAirportModel()
    {
        JInternalFrame frame = desktop.getSelectedFrame();
        if(frame != null && (frame instanceof AirportFrame))
            return ((AirportFrame)frame).getAirportModel();
        else
            return null;
    }

    public AirportFrame getCurrentAirportFrame()
    {
        JInternalFrame frame = desktop.getSelectedFrame();
        if(frame != null && (frame instanceof AirportFrame))
            return (AirportFrame)frame;
        else
            return null;
    }

    public void updateAirportDataDisplay(boolean displayed)
    {
        JInternalFrame frame = desktop.getSelectedFrame();
        if(frame != null && (frame instanceof AirportFrame))
            ((AirportFrame)frame).setAirportDataDisplay(displayed);
    }

    public void deleteSelectedItem()
    {
        JInternalFrame frame = desktop.getSelectedFrame();
        if(frame != null && (frame instanceof AirportFrame))
            ((AirportFrame)frame).deleteSelectedItem();
    }

    public void pasteItem(BaseModel baseModel)
    {
        JInternalFrame frame = desktop.getSelectedFrame();
        if(frame != null && (frame instanceof AirportFrame))
            ((AirportFrame)frame).pasteItem(baseModel);
    }

    public void updateObjectDataDisplay(boolean displayed)
    {
        JInternalFrame frame = desktop.getSelectedFrame();
        if(frame != null && (frame instanceof AirportFrame))
            ((AirportFrame)frame).setObjectDataDisplay(displayed);
    }

    public void updatePlaneMake(String planeMake)
    {
        FSXConnection.getInstance().setPlaneMake(planeMake);
        FSXConnection.getInstance().setPlaneWidth((float)PlaneDimensions.getInstance().getPlaneWidth(planeMake));
        FSXConnection.getInstance().setPlaneLength((float)PlaneDimensions.getInstance().getPlaneLength(planeMake));
        FSXConnection.getInstance().setPlaneType(PlaneDimensions.getInstance().getPlaneType(planeMake));
        FSXConnection.getInstance().writePreferences();
        if(getCurrentAirportModel() != null)
        {
            PlaneModel planeModel = (PlaneModel)getCurrentAirportModel().getPlaneAL().get(0);
            planeModel.setPlaneMake(planeMake);
            planeModel.setPlaneType(PlaneDimensions.getInstance().getPlaneType(planeMake));
            planeModel.setLength((float)PlaneDimensions.getInstance().getPlaneLength(planeMake));
            planeModel.setWidth((float)PlaneDimensions.getInstance().getPlaneWidth(planeMake));
        }
        repaintAirport();
    }

    public void focusOn(BaseModel model)
    {
        JInternalFrame frames[] = desktop.getAllFrames();
        for(int i = frames.length - 1; i >= 0; i--)
            if(frames[i] instanceof AirportFrame)
            {
                ((AirportFrame)frames[i]).getAirportDisplay().focusOn(model);
                return;
            }

    }

    public void setRecreate(boolean recreate)
    {
        JInternalFrame frames[] = desktop.getAllFrames();
        for(int i = frames.length - 1; i >= 0; i--)
            if(frames[i] instanceof AirportFrame)
                ((AirportFrame)frames[i]).getAirportDisplay().setRecreate(recreate);

    }

    public void removeCurrentAirport()
    {
        JInternalFrame frame = desktop.getSelectedFrame();
        if(frame != null && (frame instanceof AirportFrame))
        {
            ((AirportFrame)frame).clearDataDisplays();
            desktop.setSelectedFrame(null);
            desktop.remove(frame);
            frame.dispose();
            frame.removeInternalFrameListener(this);
            repaint();
        }
    }

    public void repaintAirport()
    {
        JInternalFrame frame = desktop.getSelectedFrame();
        if(frame != null && (frame instanceof AirportFrame))
        {
            ((AirportFrame)frame).getAirportDisplay().setRecreate(true);
            ((AirportFrame)frame).getAirportDisplay().repaint();
        }
    }

    private boolean checkBeforeClosing(JInternalFrame frame)
    {
        if(frame instanceof AirportFrame)
        {
            String ident = ((AirportFrame)frame).getAirportModel().getIdent();
            String fileName = ((AirportFrame)frame).getAirportModel().getFileName();
            int option = JOptionPane.showConfirmDialog(this, (new StringBuilder()).append("Would you like to save ").append(ident).append(" before closing?").toString(), "Save...", 1);
            if(option == 0)
            {
                firePropertyChange("removeFromView", fileName, ident);
                firePropertyChange("saveAndCloseAirport", fileName, ident);
                AirportListModel.getInstance().removeAirportFrame(ident, fileName);
                frame.removeInternalFrameListener(this);
                return true;
            }
            if(option == 1)
            {
                firePropertyChange("removeFromView", ident, ident);
                AirportListModel.getInstance().removeAirportModel(((AirportFrame)frame).getAirportModel());
                AirportListModel.getInstance().removeAirportFrame(ident, fileName);
                frame.removeInternalFrameListener(this);
                return true;
            } else
            {
                AirportListModel.getInstance().removeAirportFrame(ident, fileName);
                addAirportFrame(ident, fileName);
                return false;
            }
        } else
        {
            return false;
        }
    }

    public void checkAllAirports()
    {
        JInternalFrame frames[] = desktop.getAllFrames();
        for(int i = frames.length - 1; i >= 0; i--)
        {
            if(!(frames[i] instanceof AirportFrame))
                continue;
            frames[i].moveToFront();
            try
            {
                frames[i].setSelected(true);
                frames[i].setMaximum(true);
            }
            catch(PropertyVetoException pve)
            {
                LogEngine.getInstance().log(pve);
            }
            String ident = ((AirportFrame)frames[i]).getAirportModel().getIdent();
            String fileName = ((AirportFrame)frames[i]).getAirportModel().getFileName();
            int option = JOptionPane.showConfirmDialog(this, (new StringBuilder()).append("Would you like to save ").append(ident).append(" before closing?").toString(), "Save...", 0);
            if(option == 0)
            {
                firePropertyChange("removeFromView", fileName, ident);
                firePropertyChange("saveAndCloseAirport", fileName, ident);
                AirportListModel.getInstance().removeAirportFrame(ident, fileName);
                frames[i].removeInternalFrameListener(this);
                continue;
            }
            if(option == 1)
            {
                firePropertyChange("removeFromView", ident, ident);
                AirportListModel.getInstance().removeAirportModel(((AirportFrame)frames[i]).getAirportModel());
                AirportListModel.getInstance().removeAirportFrame(ident, fileName);
                frames[i].removeInternalFrameListener(this);
            }
        }

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
        super.firePropertyChange(propertyName, oldValue, newValue);
        if(listeners == null)
            return;
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

    public void internalFrameActivated(InternalFrameEvent event)
    {
        if(event.getInternalFrame() instanceof AirportFrame)
        {
            SelectedItem.getInstance().setPropertyChangeListener((AirportFrame)event.getInternalFrame());
            AirportListModel.getInstance().setCurrentAirport(((AirportFrame)event.getInternalFrame()).getAirportModel());
        }
        if(desktop.getSelectedFrame() != null)
            firePropertyChange("enableMenuOptions", "", "");
    }

    public void internalFrameClosed(InternalFrameEvent event)
    {
        if(event.getInternalFrame() instanceof AirportFrame)
        {
            SelectedItem.getInstance().removePropertyChangeListener((AirportFrame)event.getInternalFrame());
            AirportListModel.getInstance().setCurrentAirport(null);
        }
        if(desktop.getSelectedFrame() == null)
            firePropertyChange("disableMenuOptions", "", "");
    }

    public void internalFrameClosing(InternalFrameEvent event)
    {
        if(event.getInternalFrame() instanceof AirportFrame)
        {
            SelectedItem.getInstance().removePropertyChangeListener((AirportFrame)event.getInternalFrame());
            AirportListModel.getInstance().setCurrentAirport(null);
        }
        if(checkBeforeClosing(event.getInternalFrame()))
            firePropertyChange("disableMenuOptions", "", "");
    }

    public void internalFrameDeactivated(InternalFrameEvent internalframeevent)
    {
    }

    public void internalFrameDeiconified(InternalFrameEvent event)
    {
        if(event.getInternalFrame() instanceof AirportFrame)
        {
            SelectedItem.getInstance().setPropertyChangeListener((AirportFrame)event.getInternalFrame());
            AirportListModel.getInstance().setCurrentAirport(((AirportFrame)event.getInternalFrame()).getAirportModel());
        }
        if(desktop.getSelectedFrame() != null)
            firePropertyChange("enableMenuOptions", "", "");
    }

    public void internalFrameIconified(InternalFrameEvent event)
    {
        if(event.getInternalFrame() instanceof AirportFrame)
        {
            SelectedItem.getInstance().removePropertyChangeListener((AirportFrame)event.getInternalFrame());
            AirportListModel.getInstance().setCurrentAirport(null);
        }
        if(desktop.getSelectedFrame() == null)
            firePropertyChange("disableMenuOptions", "", "");
    }

    public void internalFrameOpened(InternalFrameEvent internalframeevent)
    {
    }

    private JDesktopPane desktop;
    private JLabel backgroundLabel;
    private Vector listeners;
    private static MainDisplay display = null;

}
