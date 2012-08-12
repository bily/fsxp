// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ApronData.java

package com.zbluesoftware.fsxp.ui.data;

import com.zbluesoftware.fsxp.engine.*;
import com.zbluesoftware.fsxp.graphics.IconFactory;
import com.zbluesoftware.fsxp.model.*;
import com.zbluesoftware.fsxp.ui.WindowBorder;
import com.zbluesoftware.fsxp.util.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.PrintStream;
import java.util.Vector;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

// Referenced classes of package com.zbluesoftware.fsxp.ui.data:
//            ObjectData

public class ApronData extends ObjectData
    implements ActionListener, FocusListener, PropertyChangeListener
{

    public ApronData()
    {
        listeners = new Vector();
        setLayout(new BorderLayout(2, 2));
        lockingButton = new JButton(IconFactory.getInstance().getIcon("padlockUnlocked"));
        lockingButton.setBorder(new EmptyBorder(5, 5, 5, 5));
        lockingButton.setToolTipText("Lock/Unlock Aprons");
        lockingButton.addActionListener(this);
        if(LockingEngine.getInstance().getApronsLocked())
            lockingButton.setIcon(IconFactory.getInstance().getIcon("padlockLocked"));
        deleteButton = new JButton(IconFactory.getInstance().getIcon("delete"));
        deleteButton.setBorder(new EmptyBorder(5, 5, 5, 5));
        deleteButton.setToolTipText("Delete");
        deleteButton.addActionListener(this);
        JPanel buttonPanel = new JPanel(new FlowLayout(2));
        buttonPanel.add(lockingButton);
        buttonPanel.add(deleteButton);
        surfaceLabel = new JLabel("Surface");
        surfaceLabel.setFont(Utilities.LABEL_FONT);
        surfaceLabel.setForeground(Color.black);
        DefaultComboBoxModel surfaceModel = new DefaultComboBoxModel();
        surfaceModel.addElement("ASPHALT");
        surfaceModel.addElement("BITUMINOUS");
        surfaceModel.addElement("BRICK");
        surfaceModel.addElement("CLAY");
        surfaceModel.addElement("CEMENT");
        surfaceModel.addElement("CONCRETE");
        surfaceModel.addElement("CORAL");
        surfaceModel.addElement("DIRT");
        surfaceModel.addElement("GRASS");
        surfaceModel.addElement("GRAVEL");
        surfaceModel.addElement("ICE");
        surfaceModel.addElement("MACADAM");
        surfaceModel.addElement("OIL_TREATED, PLANKS");
        surfaceModel.addElement("SAND");
        surfaceModel.addElement("SHALE");
        surfaceModel.addElement("SNOW");
        surfaceModel.addElement("STEEL_MATS");
        surfaceModel.addElement("TARMAC");
        surfaceModel.addElement("UNKNOWN");
        surfaceModel.addElement("WATER");
        surfaceComboBox = new JComboBox(surfaceModel);
        surfaceComboBox.setFont(Utilities.COMBO_BOX_FONT);
        surfaceComboBox.setForeground(Color.black);
        surfaceComboBox.addActionListener(this);
        drawSurfaceLabel = new JLabel("Draw Surface");
        drawSurfaceLabel.setFont(Utilities.LABEL_FONT);
        drawSurfaceLabel.setForeground(Color.black);
        drawSurfaceComboBox = new JComboBox(getTrueFalseModel());
        drawSurfaceComboBox.setFont(Utilities.COMBO_BOX_FONT);
        drawSurfaceComboBox.setForeground(Color.black);
        drawSurfaceComboBox.addActionListener(this);
        drawDetailLabel = new JLabel("Draw Detail");
        drawDetailLabel.setFont(Utilities.LABEL_FONT);
        drawDetailLabel.setForeground(Color.black);
        drawDetailComboBox = new JComboBox(getTrueFalseModel());
        drawDetailComboBox.setFont(Utilities.COMBO_BOX_FONT);
        drawDetailComboBox.setForeground(Color.black);
        drawDetailComboBox.addActionListener(this);
        vertexLabel = new JLabel("Vertex Position");
        vertexLabel.setFont(Utilities.LABEL_FONT);
        vertexLabel.setForeground(Color.black);
        vertexLabel.setEnabled(false);
        indexLabel = new JLabel("Index");
        indexLabel.setFont(Utilities.LABEL_FONT);
        indexLabel.setForeground(Color.black);
        indexLabel.setEnabled(false);
        indexTF = new JTextField(10);
        indexTF.setFont(Utilities.TEXT_FIELD_FONT);
        indexTF.setForeground(Color.black);
        indexTF.setEditable(false);
        indexTF.setEnabled(false);
        latLabel = new JLabel("Latitude");
        latLabel.setFont(Utilities.LABEL_FONT);
        latLabel.setForeground(Color.black);
        latLabel.setEnabled(false);
        latTF = new PopupTextField(20);
        latTF.setFont(Utilities.TEXT_FIELD_FONT);
        latTF.setForeground(Color.black);
        latTF.addActionListener(this);
        latTF.addFocusListener(this);
        latTF.setEnabled(false);
        if(LockingEngine.getInstance().getApronsLocked())
            latTF.setBackground(Color.red);
        lonLabel = new JLabel("Longitude");
        lonLabel.setFont(Utilities.LABEL_FONT);
        lonLabel.setForeground(Color.black);
        lonLabel.setEnabled(false);
        lonTF = new PopupTextField(20);
        lonTF.setFont(Utilities.TEXT_FIELD_FONT);
        lonTF.setForeground(Color.black);
        lonTF.addActionListener(this);
        lonTF.addFocusListener(this);
        lonTF.setEnabled(false);
        if(LockingEngine.getInstance().getApronsLocked())
            lonTF.setBackground(Color.red);
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        Utilities.addComponent(mainPanel, buttonPanel, 0, 0, 2, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, surfaceLabel, 0, 1, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, surfaceComboBox, 1, 1, 1, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, drawSurfaceLabel, 0, 2, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, drawSurfaceComboBox, 1, 2, 1, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, drawDetailLabel, 0, 3, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, drawDetailComboBox, 1, 3, 1, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, vertexLabel, 1, 4, 1, 1, 0, 17, new Insets(10, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, indexLabel, 0, 5, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, indexTF, 1, 5, 1, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, latLabel, 0, 6, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, latTF, 1, 6, 1, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, lonLabel, 0, 7, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, lonTF, 1, 7, 1, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, Box.createGlue(), 0, 8, 2, 1, 3, 11, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.5D);
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        add(scrollPane, "Center");
        windowBorder = new WindowBorder("Apron Data");
        setBorder(windowBorder);
        LockingEngine.getInstance().addPropertyChangeListener(this);
    }

    private DefaultComboBoxModel getTrueFalseModel()
    {
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        model.addElement("TRUE");
        model.addElement("FALSE");
        return model;
    }

    public void updateDisplay(BaseModel baseModel)
    {
        ApronModel model = null;
        if(baseModel instanceof ApronModel)
            model = (ApronModel)baseModel;
        if(this.model != null)
            this.model.removePropertyChangeListener(this);
        updateModel();
        this.model = model;
        boolean status = true;
        surfaceComboBox.removeActionListener(this);
        drawSurfaceComboBox.removeActionListener(this);
        drawDetailComboBox.removeActionListener(this);
        if(model != null)
        {
            surfaceComboBox.setSelectedItem(model.getSurface());
            drawSurfaceComboBox.setSelectedIndex(model.getDrawSurface() ? 0 : 1);
            drawDetailComboBox.setSelectedIndex(model.getDrawDetail() ? 0 : 1);
            model.addPropertyChangeListener(this);
        } else
        {
            surfaceComboBox.setSelectedIndex(0);
            drawSurfaceComboBox.setSelectedIndex(0);
            drawDetailComboBox.setSelectedIndex(0);
            status = false;
        }
        displayVertexModel(null);
        surfaceComboBox.addActionListener(this);
        drawSurfaceComboBox.addActionListener(this);
        drawDetailComboBox.addActionListener(this);
        deleteButton.setEnabled(status);
        lockingButton.setEnabled(status);
        surfaceLabel.setEnabled(status);
        surfaceComboBox.setEnabled(status);
        drawSurfaceLabel.setEnabled(status);
        drawSurfaceComboBox.setEnabled(status);
        drawDetailLabel.setEnabled(status);
        drawDetailComboBox.setEnabled(status);
        vertexLabel.setEnabled(status);
        indexLabel.setEnabled(status);
        indexTF.setEnabled(status);
        latLabel.setEnabled(status);
        latTF.setEnabled(status);
        lonLabel.setEnabled(status);
        lonTF.setEnabled(status);
    }

    public void updateModel()
    {
        if(model != null)
        {
            model.setSurface((String)surfaceComboBox.getSelectedItem());
            model.setDrawSurface(drawSurfaceComboBox.getSelectedIndex() == 0);
            model.setDrawDetail(drawDetailComboBox.getSelectedIndex() == 0);
        }
    }

    public void displayVertexModel(VertexModel vertexModel)
    {
        if(this.vertexModel != null)
        {
            this.vertexModel.removePropertyChangeListener(this);
            this.vertexModel.setCurrentlySelected(false);
        }
        this.vertexModel = vertexModel;
        latTF.removeActionListener(this);
        latTF.removeFocusListener(this);
        lonTF.removeActionListener(this);
        lonTF.removeFocusListener(this);
        if(vertexModel != null)
        {
            indexTF.setText((new StringBuilder()).append("").append(model.getVertexModelIndex(vertexModel)).toString());
            latTF.setText(DisplayEngine.getInstance().formatObjectLatitude(vertexModel.getLatLon().getLat()));
            lonTF.setText(DisplayEngine.getInstance().formatObjectLongitude(vertexModel.getLatLon().getLon()));
            setVertexEnabled(true);
            vertexModel.addPropertyChangeListener(this);
            vertexModel.setCurrentlySelected(true);
        } else
        {
            indexTF.setText("");
            latTF.setText("");
            lonTF.setText("");
            setVertexEnabled(false);
        }
        latTF.addActionListener(this);
        latTF.addFocusListener(this);
        lonTF.addActionListener(this);
        lonTF.addFocusListener(this);
    }

    private void setVertexEnabled(boolean enabled)
    {
        vertexLabel.setEnabled(enabled);
        indexLabel.setEnabled(enabled);
        indexTF.setEnabled(enabled);
        latLabel.setEnabled(enabled);
        latTF.setEnabled(enabled);
        lonLabel.setEnabled(enabled);
        lonTF.setEnabled(enabled);
    }

    private void delete()
    {
        if(JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this Apron?", "Confirm Delete...", 0) != 0)
        {
            return;
        } else
        {
            firePropertyChange("delete", model, model);
            updateDisplay(null);
            return;
        }
    }

    private void updateLatitude()
    {
        if(vertexModel == null)
            return;
        if(latTF.isPopupDisplayed())
            return;
        double lat = ParseEngine.getInstance().parseLatitude(latTF.getText());
        if(lat == (1.0D / 0.0D))
            lat = vertexModel.getLatLon().getLat();
        latTF.setText(DisplayEngine.getInstance().formatObjectLatitude(lat));
        firePropertyChange("update-lat", new Double(vertexModel.getLatLon().getLat()), new Double(lat));
        vertexModel.setLatLon(new LatLonPoint(lat, vertexModel.getLatLon().getLon()));
    }

    private void updateLongitude()
    {
        if(vertexModel == null)
            return;
        if(lonTF.isPopupDisplayed())
            return;
        double lon = ParseEngine.getInstance().parseLongitude(lonTF.getText());
        if(lon == (1.0D / 0.0D))
            lon = vertexModel.getLatLon().getLon();
        lonTF.setText(DisplayEngine.getInstance().formatObjectLongitude(lon));
        firePropertyChange("update-lon", new Double(vertexModel.getLatLon().getLon()), new Double(lon));
        vertexModel.setLatLon(new LatLonPoint(vertexModel.getLatLon().getLat(), lon));
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

    public void actionPerformed(ActionEvent event)
    {
        if(event.getSource() == deleteButton)
            delete();
        else
        if(event.getSource() == lockingButton)
            LockingEngine.getInstance().setApronsLocked(!LockingEngine.getInstance().getApronsLocked());
        else
        if(event.getSource() == surfaceComboBox)
        {
            System.out.println("surface");
            firePropertyChange("update-surface", model.getSurface(), (String)surfaceComboBox.getSelectedItem());
            model.setSurface((String)surfaceComboBox.getSelectedItem());
        } else
        if(event.getSource() == drawSurfaceComboBox)
            model.setDrawSurface(drawSurfaceComboBox.getSelectedIndex() == 0);
        else
        if(event.getSource() == drawDetailComboBox)
            model.setDrawDetail(drawDetailComboBox.getSelectedIndex() == 0);
        else
        if(event.getSource() == latTF)
            updateLatitude();
        else
        if(event.getSource() == lonTF)
            updateLongitude();
    }

    public void focusGained(FocusEvent focusevent)
    {
    }

    public void focusLost(FocusEvent event)
    {
        if(event.getSource() == latTF)
            updateLatitude();
        else
        if(event.getSource() == lonTF)
            updateLongitude();
    }

    public void propertyChange(PropertyChangeEvent event)
    {
        if(event.getSource() == model)
        {
            if(event.getPropertyName().equals("surface"))
            {
                surfaceComboBox.removeActionListener(this);
                surfaceComboBox.setSelectedItem(event.getNewValue());
                surfaceComboBox.addActionListener(this);
            } else
            if(event.getPropertyName().equals("drawSurface"))
            {
                drawSurfaceComboBox.removeActionListener(this);
                drawSurfaceComboBox.setSelectedIndex(((Boolean)event.getNewValue()).booleanValue() ? 0 : 1);
                drawSurfaceComboBox.addActionListener(this);
            } else
            if(event.getPropertyName().equals("drawDetail"))
            {
                drawDetailComboBox.removeActionListener(this);
                drawDetailComboBox.setSelectedIndex(((Boolean)event.getNewValue()).booleanValue() ? 0 : 1);
                drawDetailComboBox.addActionListener(this);
            } else
            if(event.getPropertyName().equals("vertexModel"))
                displayVertexModel((VertexModel)event.getNewValue());
        } else
        if(event.getSource() instanceof VertexModel)
        {
            if(event.getPropertyName().equals("latLon"))
            {
                latTF.setText(DisplayEngine.getInstance().formatObjectLatitude(((LatLonPoint)event.getNewValue()).getLat()));
                lonTF.setText(DisplayEngine.getInstance().formatObjectLongitude(((LatLonPoint)event.getNewValue()).getLon()));
            }
        } else
        if((event.getSource() instanceof LockingEngine) && event.getPropertyName().equals("apronsLocked"))
        {
            latTF.setBackground(((Boolean)event.getNewValue()).booleanValue() ? Color.red : Color.white);
            lonTF.setBackground(((Boolean)event.getNewValue()).booleanValue() ? Color.red : Color.white);
            if(((Boolean)event.getNewValue()).booleanValue())
                lockingButton.setIcon(IconFactory.getInstance().getIcon("padlockLocked"));
            else
                lockingButton.setIcon(IconFactory.getInstance().getIcon("padlockUnlocked"));
        }
    }

    private ApronModel model;
    private VertexModel vertexModel;
    private Vector listeners;
    private JButton lockingButton;
    private JButton deleteButton;
    private JComboBox surfaceComboBox;
    private JComboBox drawSurfaceComboBox;
    private JComboBox drawDetailComboBox;
    private JLabel surfaceLabel;
    private JLabel drawSurfaceLabel;
    private JLabel drawDetailLabel;
    private JLabel vertexLabel;
    private JLabel indexLabel;
    private JTextField indexTF;
    private JLabel latLabel;
    private PopupTextField latTF;
    private JLabel lonLabel;
    private PopupTextField lonTF;
}
