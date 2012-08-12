// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ApronEdgeLightData.java

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
import javax.swing.*;
import javax.swing.border.EmptyBorder;

// Referenced classes of package com.zbluesoftware.fsxp.ui.data:
//            ObjectData

public class ApronEdgeLightData extends ObjectData
    implements ActionListener, FocusListener, PropertyChangeListener
{

    public ApronEdgeLightData()
    {
        setLayout(new BorderLayout(2, 2));
        cutButton = new JButton(IconFactory.getInstance().getIcon("cutLine"));
        cutButton.setBorder(new EmptyBorder(5, 5, 5, 5));
        cutButton.setToolTipText("Cut String");
        cutButton.addActionListener(this);
        cutButton.setEnabled(false);
        lockingButton = new JButton(IconFactory.getInstance().getIcon("padlockUnlocked"));
        lockingButton.setBorder(new EmptyBorder(5, 5, 5, 5));
        lockingButton.setToolTipText("Lock/Unlock Apron Edge Lights");
        lockingButton.addActionListener(this);
        if(LockingEngine.getInstance().getEdgeLightsLocked())
            lockingButton.setIcon(IconFactory.getInstance().getIcon("padlockLocked"));
        deleteButton = new JButton(IconFactory.getInstance().getIcon("delete"));
        deleteButton.setBorder(new EmptyBorder(5, 5, 5, 5));
        deleteButton.setToolTipText("Delete");
        deleteButton.addActionListener(this);
        JPanel buttonPanel = new JPanel(new FlowLayout(2));
        buttonPanel.add(cutButton);
        buttonPanel.add(lockingButton);
        buttonPanel.add(deleteButton);
        indexLabel = new JLabel("Vertex Index");
        indexLabel.setFont(Utilities.LABEL_FONT);
        indexLabel.setForeground(Color.black);
        indexLabel.setEnabled(false);
        indexTF = new JTextField(10);
        indexTF.setFont(Utilities.TEXT_FIELD_FONT);
        indexTF.setForeground(Color.black);
        indexTF.setEditable(false);
        indexTF.setEnabled(false);
        latLabel = new JLabel("Vertex Latitude");
        latLabel.setFont(Utilities.LABEL_FONT);
        latLabel.setForeground(Color.black);
        latLabel.setEnabled(false);
        latTF = new PopupTextField(20);
        latTF.setFont(Utilities.TEXT_FIELD_FONT);
        latTF.setForeground(Color.black);
        latTF.addActionListener(this);
        latTF.addFocusListener(this);
        latTF.setEnabled(false);
        if(LockingEngine.getInstance().getEdgeLightsLocked())
            latTF.setBackground(Color.red);
        lonLabel = new JLabel("Vertex Longitude");
        lonLabel.setFont(Utilities.LABEL_FONT);
        lonLabel.setForeground(Color.black);
        lonLabel.setEnabled(false);
        lonTF = new PopupTextField(20);
        lonTF.setFont(Utilities.TEXT_FIELD_FONT);
        lonTF.setForeground(Color.black);
        lonTF.addActionListener(this);
        lonTF.addFocusListener(this);
        lonTF.setEnabled(false);
        if(LockingEngine.getInstance().getEdgeLightsLocked())
            lonTF.setBackground(Color.red);
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        Utilities.addComponent(mainPanel, buttonPanel, 0, 0, 3, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, indexLabel, 0, 1, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, indexTF, 1, 1, 1, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, latLabel, 0, 2, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, latTF, 1, 2, 1, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, lonLabel, 0, 3, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, lonTF, 1, 3, 1, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, Box.createGlue(), 0, 4, 3, 1, 3, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.5D);
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        add(scrollPane, "Center");
        windowBorder = new WindowBorder("Apron Edge Light Data");
        setBorder(windowBorder);
        LockingEngine.getInstance().addPropertyChangeListener(this);
    }

    public void updateDisplay(BaseModel baseModel)
    {
        ApronEdgeLightModel model = null;
        if(baseModel instanceof ApronEdgeLightModel)
            model = (ApronEdgeLightModel)baseModel;
        if(this.model != null)
            this.model.removePropertyChangeListener(this);
        this.model = model;
        boolean status = true;
        if(model != null)
            model.addPropertyChangeListener(this);
        else
            status = false;
        displayVertexModel(null);
        deleteButton.setEnabled(status);
        lockingButton.setEnabled(status);
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
        indexLabel.setEnabled(enabled);
        indexTF.setEnabled(enabled);
        latLabel.setEnabled(enabled);
        latTF.setEnabled(enabled);
        lonLabel.setEnabled(enabled);
        lonTF.setEnabled(enabled);
        cutButton.setEnabled(enabled);
    }

    private void delete()
    {
        if(JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this Apron Edge Light String?", "Confirm Delete...", 0) != 0)
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

    public void cutString()
    {
        if(JOptionPane.showConfirmDialog(this, (new StringBuilder()).append("Are you sure you want to cut this Apron Edge Light String after vertex ").append(indexTF.getText()).append("?").toString(), "Confirm Cut...", 0) != 0)
        {
            return;
        } else
        {
            firePropertyChange("cut", new Integer(indexTF.getText()), new Integer(indexTF.getText()));
            return;
        }
    }

    public ApronEdgeLightModel getApronEdgeLightModel()
    {
        return model;
    }

    public void actionPerformed(ActionEvent event)
    {
        if(event.getSource() == latTF)
            updateLatitude();
        else
        if(event.getSource() == lonTF)
            updateLongitude();
        else
        if(event.getSource() == deleteButton)
            delete();
        else
        if(event.getSource() == lockingButton)
            LockingEngine.getInstance().setEdgeLightsLocked(!LockingEngine.getInstance().getEdgeLightsLocked());
        else
        if(event.getSource() == cutButton)
            cutString();
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
        if((event.getSource() instanceof LockingEngine) && event.getPropertyName().equals("edgeLightsLocked"))
        {
            latTF.setBackground(((Boolean)event.getNewValue()).booleanValue() ? Color.red : Color.white);
            lonTF.setBackground(((Boolean)event.getNewValue()).booleanValue() ? Color.red : Color.white);
            if(((Boolean)event.getNewValue()).booleanValue())
                lockingButton.setIcon(IconFactory.getInstance().getIcon("padlockLocked"));
            else
                lockingButton.setIcon(IconFactory.getInstance().getIcon("padlockUnlocked"));
        }
    }

    private ApronEdgeLightModel model;
    private VertexModel vertexModel;
    private JButton lockingButton;
    private JButton deleteButton;
    private JButton cutButton;
    private JLabel indexLabel;
    private JTextField indexTF;
    private JLabel latLabel;
    private PopupTextField latTF;
    private JLabel lonLabel;
    private PopupTextField lonTF;
}
