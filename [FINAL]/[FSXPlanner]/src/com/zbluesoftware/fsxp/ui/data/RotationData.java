// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RotationData.java

package com.zbluesoftware.fsxp.ui.data;

import com.zbluesoftware.fsxp.engine.LockingEngine;
import com.zbluesoftware.fsxp.model.BaseModel;
import com.zbluesoftware.fsxp.model.RotationModel;
import com.zbluesoftware.fsxp.ui.WindowBorder;
import com.zbluesoftware.fsxp.util.PopupTextField;
import com.zbluesoftware.fsxp.util.Utilities;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Vector;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

// Referenced classes of package com.zbluesoftware.fsxp.ui.data:
//            ObjectData

public class RotationData extends ObjectData
    implements ActionListener, ChangeListener, FocusListener, PropertyChangeListener
{

    public RotationData()
    {
        listeners = new Vector();
        setLayout(new BorderLayout(2, 2));
        rotationLabel = new JLabel("Rotation:");
        rotationLabel.setFont(Utilities.LABEL_FONT);
        rotationLabel.setForeground(Color.black);
        rotationTF = new PopupTextField(5);
        rotationTF.setFont(Utilities.TEXT_FIELD_FONT);
        rotationTF.setForeground(Color.black);
        rotationTF.addActionListener(this);
        rotationTF.addFocusListener(this);
        rotationSlider = new JSlider(0);
        rotationSlider.setModel(new DefaultBoundedRangeModel(0, 1, 0, 360));
        rotationSlider.setPreferredSize(new Dimension(100, rotationSlider.getPreferredSize().height));
        rotationSlider.addChangeListener(this);
        JPanel rotationPanel = new JPanel(new GridBagLayout());
        Utilities.addComponent(rotationPanel, rotationTF, 0, 0, 1, 1, 2, 13, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(rotationPanel, rotationSlider, 1, 0, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        Utilities.addComponent(mainPanel, rotationLabel, 0, 0, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, rotationPanel, 1, 0, 1, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, Box.createGlue(), 0, 1, 2, 1, 3, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.5D);
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        add(scrollPane, "Center");
        windowBorder = new WindowBorder("Rotation Data");
        setBorder(windowBorder);
        LockingEngine.getInstance().addPropertyChangeListener(this);
    }

    public void updateDisplay(BaseModel baseModel)
    {
        RotationModel model = null;
        if(baseModel instanceof RotationModel)
            model = (RotationModel)baseModel;
        if(this.model != null)
            this.model.removePropertyChangeListener(this);
        updateModel();
        this.model = model;
        boolean status = true;
        rotationTF.removeActionListener(this);
        rotationTF.removeFocusListener(this);
        rotationSlider.removeChangeListener(this);
        if(model != null)
        {
            rotationSlider.setValue((int)model.getRotation());
            rotationTF.setText((new StringBuilder()).append("").append(model.getRotation()).toString());
            model.addPropertyChangeListener(this);
        } else
        {
            rotationSlider.setValue(0);
            rotationTF.setText("");
            status = false;
        }
        rotationTF.addActionListener(this);
        rotationTF.addFocusListener(this);
        rotationSlider.addChangeListener(this);
        rotationLabel.setEnabled(status);
        rotationTF.setEnabled(status);
        rotationSlider.setEnabled(status);
    }

    public void updateModel()
    {
        if(model != null)
            updateRotation();
    }

    private void updateRotation()
    {
        if(rotationTF.isPopupDisplayed() || model == null)
            return;
        double rotation = model.getRotation();
        try
        {
            rotation = Float.parseFloat(rotationTF.getText());
        }
        catch(NumberFormatException nfe)
        {
            rotation = model.getRotation();
        }
        if(rotation < 0.0D)
            rotation = 0.0D;
        else
        if(rotation > 359D)
            rotation = 359D;
        rotationSlider.setValue((int)rotation);
        rotationTF.setText((new StringBuilder()).append("").append(rotation).toString());
        firePropertyChange("update-rotation", new Float(model.getRotation()), new Float(rotationSlider.getValue()));
        model.setRotation(rotation);
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
        if(event.getSource() == rotationTF)
            updateRotation();
    }

    public void stateChanged(ChangeEvent event)
    {
        if(event.getSource() == rotationSlider && model != null)
        {
            rotationTF.setText((new StringBuilder()).append("").append(rotationSlider.getValue()).toString());
            firePropertyChange("update-rotation", new Float(model.getRotation()), new Float(rotationSlider.getValue()));
            model.setRotation(rotationSlider.getValue());
        }
    }

    public void focusGained(FocusEvent focusevent)
    {
    }

    public void focusLost(FocusEvent event)
    {
        if(event.getSource() == rotationTF)
            updateRotation();
    }

    public void propertyChange(PropertyChangeEvent event)
    {
        if(event.getSource() == model && event.getPropertyName().equals("rotation"))
        {
            rotationTF.setText((new StringBuilder()).append("").append(((Double)event.getNewValue()).floatValue()).toString());
            rotationSlider.removeChangeListener(this);
            rotationSlider.setValue(((Double)event.getNewValue()).intValue());
            rotationSlider.addChangeListener(this);
        }
    }

    private RotationModel model;
    private Vector listeners;
    private JLabel rotationLabel;
    private PopupTextField rotationTF;
    private JSlider rotationSlider;
}
