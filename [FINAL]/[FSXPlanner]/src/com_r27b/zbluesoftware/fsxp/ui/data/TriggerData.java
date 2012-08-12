// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TriggerData.java

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
import java.util.Vector;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

// Referenced classes of package com.zbluesoftware.fsxp.ui.data:
//            ObjectData

public class TriggerData extends ObjectData
    implements ActionListener, FocusListener, PropertyChangeListener
{

    public TriggerData()
    {
        listeners = new Vector();
        setLayout(new BorderLayout(2, 2));
        lockingButton = new JButton(IconFactory.getInstance().getIcon("padlockUnlocked"));
        lockingButton.setBorder(new EmptyBorder(5, 5, 5, 5));
        lockingButton.setToolTipText("Lock/Unlock Fuel Triggers");
        lockingButton.addActionListener(this);
        if(LockingEngine.getInstance().getTriggersLocked())
            lockingButton.setIcon(IconFactory.getInstance().getIcon("padlockLocked"));
        deleteButton = new JButton(IconFactory.getInstance().getIcon("delete"));
        deleteButton.setBorder(new EmptyBorder(5, 5, 5, 5));
        deleteButton.setToolTipText("Delete");
        deleteButton.addActionListener(this);
        JPanel buttonPanel = new JPanel(new FlowLayout(2));
        buttonPanel.add(lockingButton);
        buttonPanel.add(deleteButton);
        type73Label = new JLabel("Fuel 73");
        type73Label.setFont(Utilities.LABEL_FONT);
        type73Label.setForeground(Color.black);
        type73ComboBox = new JComboBox(getFuelAvailability());
        type73ComboBox.setFont(Utilities.COMBO_BOX_FONT);
        type73ComboBox.setForeground(Color.black);
        type73ComboBox.addActionListener(this);
        type87Label = new JLabel("Fuel 87");
        type87Label.setFont(Utilities.LABEL_FONT);
        type87Label.setForeground(Color.black);
        type87ComboBox = new JComboBox(getFuelAvailability());
        type87ComboBox.setFont(Utilities.COMBO_BOX_FONT);
        type87ComboBox.setForeground(Color.black);
        type87ComboBox.addActionListener(this);
        type100Label = new JLabel("Fuel 100");
        type100Label.setFont(Utilities.LABEL_FONT);
        type100Label.setForeground(Color.black);
        type100ComboBox = new JComboBox(getFuelAvailability());
        type100ComboBox.setFont(Utilities.COMBO_BOX_FONT);
        type100ComboBox.setForeground(Color.black);
        type100ComboBox.addActionListener(this);
        type130Label = new JLabel("Fuel 130");
        type130Label.setFont(Utilities.LABEL_FONT);
        type130Label.setForeground(Color.black);
        type130ComboBox = new JComboBox(getFuelAvailability());
        type130ComboBox.setFont(Utilities.COMBO_BOX_FONT);
        type130ComboBox.setForeground(Color.black);
        type130ComboBox.addActionListener(this);
        type145Label = new JLabel("Fuel 145");
        type145Label.setFont(Utilities.LABEL_FONT);
        type145Label.setForeground(Color.black);
        type145ComboBox = new JComboBox(getFuelAvailability());
        type145ComboBox.setFont(Utilities.COMBO_BOX_FONT);
        type145ComboBox.setForeground(Color.black);
        type145ComboBox.addActionListener(this);
        mogasLabel = new JLabel("Fuel Mogas");
        mogasLabel.setFont(Utilities.LABEL_FONT);
        mogasLabel.setForeground(Color.black);
        mogasComboBox = new JComboBox(getFuelAvailability());
        mogasComboBox.setFont(Utilities.COMBO_BOX_FONT);
        mogasComboBox.setForeground(Color.black);
        mogasComboBox.addActionListener(this);
        jetLabel = new JLabel("Fuel Jet");
        jetLabel.setFont(Utilities.LABEL_FONT);
        jetLabel.setForeground(Color.black);
        jetComboBox = new JComboBox(getFuelAvailability());
        jetComboBox.setFont(Utilities.COMBO_BOX_FONT);
        jetComboBox.setForeground(Color.black);
        jetComboBox.addActionListener(this);
        jetaLabel = new JLabel("Fuel Jet A");
        jetaLabel.setFont(Utilities.LABEL_FONT);
        jetaLabel.setForeground(Color.black);
        jetaComboBox = new JComboBox(getFuelAvailability());
        jetaComboBox.setFont(Utilities.COMBO_BOX_FONT);
        jetaComboBox.setForeground(Color.black);
        jetaComboBox.addActionListener(this);
        jeta1Label = new JLabel("Fuel Jet A1");
        jeta1Label.setFont(Utilities.LABEL_FONT);
        jeta1Label.setForeground(Color.black);
        jeta1ComboBox = new JComboBox(getFuelAvailability());
        jeta1ComboBox.setFont(Utilities.COMBO_BOX_FONT);
        jeta1ComboBox.setForeground(Color.black);
        jeta1ComboBox.addActionListener(this);
        jetapLabel = new JLabel("Fuel Jet AP");
        jetapLabel.setFont(Utilities.LABEL_FONT);
        jetapLabel.setForeground(Color.black);
        jetapComboBox = new JComboBox(getFuelAvailability());
        jetapComboBox.setFont(Utilities.COMBO_BOX_FONT);
        jetapComboBox.setForeground(Color.black);
        jetapComboBox.addActionListener(this);
        jetbLabel = new JLabel("Fuel Jet B");
        jetbLabel.setFont(Utilities.LABEL_FONT);
        jetbLabel.setForeground(Color.black);
        jetbComboBox = new JComboBox(getFuelAvailability());
        jetbComboBox.setFont(Utilities.COMBO_BOX_FONT);
        jetbComboBox.setForeground(Color.black);
        jetbComboBox.addActionListener(this);
        jet4Label = new JLabel("Fuel Jet 4");
        jet4Label.setFont(Utilities.LABEL_FONT);
        jet4Label.setForeground(Color.black);
        jet4ComboBox = new JComboBox(getFuelAvailability());
        jet4ComboBox.setFont(Utilities.COMBO_BOX_FONT);
        jet4ComboBox.setForeground(Color.black);
        jet4ComboBox.addActionListener(this);
        jet5Label = new JLabel("Fuel Jet 5");
        jet5Label.setFont(Utilities.LABEL_FONT);
        jet5Label.setForeground(Color.black);
        jet5ComboBox = new JComboBox(getFuelAvailability());
        jet5ComboBox.setFont(Utilities.COMBO_BOX_FONT);
        jet5ComboBox.setForeground(Color.black);
        jet5ComboBox.addActionListener(this);
        unknownLabel = new JLabel("Fuel Unknown");
        unknownLabel.setFont(Utilities.LABEL_FONT);
        unknownLabel.setForeground(Color.black);
        unknownComboBox = new JComboBox(getFuelAvailability());
        unknownComboBox.setFont(Utilities.COMBO_BOX_FONT);
        unknownComboBox.setForeground(Color.black);
        unknownComboBox.addActionListener(this);
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
        if(LockingEngine.getInstance().getTriggersLocked())
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
        if(LockingEngine.getInstance().getTriggersLocked())
            lonTF.setBackground(Color.red);
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        Utilities.addComponent(mainPanel, buttonPanel, 0, 0, 2, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, type73Label, 0, 1, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, type73ComboBox, 1, 1, 1, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, type87Label, 0, 2, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, type87ComboBox, 1, 2, 1, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, type100Label, 0, 3, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, type100ComboBox, 1, 3, 1, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, type130Label, 0, 4, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, type130ComboBox, 1, 4, 1, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, type145Label, 0, 5, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, type145ComboBox, 1, 5, 1, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, jetLabel, 0, 6, 1, 1, 0, 13, new Insets(10, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, jetComboBox, 1, 6, 1, 1, 2, 17, new Insets(10, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, jetaLabel, 0, 7, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, jetaComboBox, 1, 7, 1, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, jeta1Label, 0, 8, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, jeta1ComboBox, 1, 8, 1, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, jetapLabel, 0, 9, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, jetapComboBox, 1, 9, 1, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, jetbLabel, 0, 10, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, jetbComboBox, 1, 10, 1, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, jet4Label, 0, 11, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, jet4ComboBox, 1, 11, 1, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, jet5Label, 0, 12, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, jet5ComboBox, 1, 12, 1, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, mogasLabel, 0, 13, 1, 1, 0, 13, new Insets(10, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, mogasComboBox, 1, 13, 1, 1, 2, 17, new Insets(10, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, unknownLabel, 0, 14, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, unknownComboBox, 1, 14, 1, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, vertexLabel, 1, 15, 1, 1, 0, 17, new Insets(10, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, indexLabel, 0, 16, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, indexTF, 1, 16, 1, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, latLabel, 0, 17, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, latTF, 1, 17, 1, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, lonLabel, 0, 18, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, lonTF, 1, 18, 1, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, Box.createGlue(), 0, 19, 2, 1, 3, 11, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.5D);
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        add(scrollPane, "Center");
        windowBorder = new WindowBorder("Fuel Trigger Data");
        setBorder(windowBorder);
        LockingEngine.getInstance().addPropertyChangeListener(this);
    }

    private DefaultComboBoxModel getFuelAvailability()
    {
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        model.addElement("NO");
        model.addElement("YES");
        model.addElement("UNKNOWN");
        model.addElement("PRIOR_REQUEST");
        return model;
    }

    public void updateDisplay(BaseModel baseModel)
    {
        TriggerModel model = null;
        if(baseModel instanceof TriggerModel)
            model = (TriggerModel)baseModel;
        if(this.model != null)
            this.model.removePropertyChangeListener(this);
        updateModel();
        this.model = model;
        boolean status = true;
        type73ComboBox.removeActionListener(this);
        type87ComboBox.removeActionListener(this);
        type100ComboBox.removeActionListener(this);
        type130ComboBox.removeActionListener(this);
        type145ComboBox.removeActionListener(this);
        mogasComboBox.removeActionListener(this);
        jetComboBox.removeActionListener(this);
        jetaComboBox.removeActionListener(this);
        jeta1ComboBox.removeActionListener(this);
        jetapComboBox.removeActionListener(this);
        jetbComboBox.removeActionListener(this);
        jet4ComboBox.removeActionListener(this);
        jet5ComboBox.removeActionListener(this);
        unknownComboBox.removeActionListener(this);
        if(model != null)
        {
            type73ComboBox.setSelectedItem(model.getType73());
            type87ComboBox.setSelectedItem(model.getType87());
            type100ComboBox.setSelectedItem(model.getType100());
            type130ComboBox.setSelectedItem(model.getType130());
            type145ComboBox.setSelectedItem(model.getType145());
            mogasComboBox.setSelectedItem(model.getTypeMogas());
            jetComboBox.setSelectedItem(model.getTypeJet());
            jetaComboBox.setSelectedItem(model.getTypeJetA());
            jeta1ComboBox.setSelectedItem(model.getTypeJetA1());
            jetapComboBox.setSelectedItem(model.getTypeJetAP());
            jetbComboBox.setSelectedItem(model.getTypeJetB());
            jet4ComboBox.setSelectedItem(model.getTypeJet4());
            jet5ComboBox.setSelectedItem(model.getTypeJet5());
            unknownComboBox.setSelectedItem(model.getTypeUnknown());
            model.addPropertyChangeListener(this);
        } else
        {
            type73ComboBox.setSelectedIndex(0);
            type87ComboBox.setSelectedIndex(0);
            type100ComboBox.setSelectedIndex(0);
            type130ComboBox.setSelectedIndex(0);
            type145ComboBox.setSelectedIndex(0);
            mogasComboBox.setSelectedIndex(0);
            jetComboBox.setSelectedIndex(0);
            jetaComboBox.setSelectedIndex(0);
            jeta1ComboBox.setSelectedIndex(0);
            jetapComboBox.setSelectedIndex(0);
            jetbComboBox.setSelectedIndex(0);
            jet4ComboBox.setSelectedIndex(0);
            jet5ComboBox.setSelectedIndex(0);
            unknownComboBox.setSelectedIndex(0);
            status = false;
        }
        displayVertexModel(null);
        type73ComboBox.addActionListener(this);
        type87ComboBox.addActionListener(this);
        type100ComboBox.addActionListener(this);
        type130ComboBox.addActionListener(this);
        type145ComboBox.addActionListener(this);
        mogasComboBox.addActionListener(this);
        jetComboBox.addActionListener(this);
        jetaComboBox.addActionListener(this);
        jeta1ComboBox.addActionListener(this);
        jetapComboBox.addActionListener(this);
        jetbComboBox.addActionListener(this);
        jet4ComboBox.addActionListener(this);
        jet5ComboBox.addActionListener(this);
        unknownComboBox.addActionListener(this);
        deleteButton.setEnabled(status);
        lockingButton.setEnabled(status);
        type73Label.setEnabled(status);
        type73ComboBox.setEnabled(status);
        type87Label.setEnabled(status);
        type87ComboBox.setEnabled(status);
        type100Label.setEnabled(status);
        type100ComboBox.setEnabled(status);
        type130Label.setEnabled(status);
        type130ComboBox.setEnabled(status);
        type145Label.setEnabled(status);
        type145ComboBox.setEnabled(status);
        mogasLabel.setEnabled(status);
        mogasComboBox.setEnabled(status);
        jetLabel.setEnabled(status);
        jetComboBox.setEnabled(status);
        jetaLabel.setEnabled(status);
        jetaComboBox.setEnabled(status);
        jeta1Label.setEnabled(status);
        jeta1ComboBox.setEnabled(status);
        jetapLabel.setEnabled(status);
        jetapComboBox.setEnabled(status);
        jetbLabel.setEnabled(status);
        jetbComboBox.setEnabled(status);
        jet4Label.setEnabled(status);
        jet4ComboBox.setEnabled(status);
        jet5Label.setEnabled(status);
        jet5ComboBox.setEnabled(status);
        unknownLabel.setEnabled(status);
        unknownComboBox.setEnabled(status);
    }

    public void updateModel()
    {
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
            LockingEngine.getInstance().setTriggersLocked(!LockingEngine.getInstance().getTriggersLocked());
        else
        if(event.getSource() == latTF)
            updateLatitude();
        else
        if(event.getSource() == lonTF)
            updateLongitude();
        else
        if(event.getSource() == type73ComboBox)
            model.setType73((String)type73ComboBox.getSelectedItem());
        else
        if(event.getSource() == type87ComboBox)
            model.setType87((String)type87ComboBox.getSelectedItem());
        else
        if(event.getSource() == type100ComboBox)
            model.setType100((String)type100ComboBox.getSelectedItem());
        else
        if(event.getSource() == type130ComboBox)
            model.setType130((String)type130ComboBox.getSelectedItem());
        else
        if(event.getSource() == type145ComboBox)
            model.setType145((String)type145ComboBox.getSelectedItem());
        else
        if(event.getSource() == mogasComboBox)
            model.setTypeMogas((String)mogasComboBox.getSelectedItem());
        else
        if(event.getSource() == jetComboBox)
            model.setTypeJet((String)jetComboBox.getSelectedItem());
        else
        if(event.getSource() == jetaComboBox)
            model.setTypeJetA((String)jetaComboBox.getSelectedItem());
        else
        if(event.getSource() == jeta1ComboBox)
            model.setTypeJetA1((String)jeta1ComboBox.getSelectedItem());
        else
        if(event.getSource() == jetapComboBox)
            model.setTypeJetAP((String)jetapComboBox.getSelectedItem());
        else
        if(event.getSource() == jetbComboBox)
            model.setTypeJetB((String)jetbComboBox.getSelectedItem());
        else
        if(event.getSource() == jet4ComboBox)
            model.setTypeJet4((String)jet4ComboBox.getSelectedItem());
        else
        if(event.getSource() == jet5ComboBox)
            model.setTypeJet5((String)jet5ComboBox.getSelectedItem());
        else
        if(event.getSource() == unknownComboBox)
            model.setTypeUnknown((String)unknownComboBox.getSelectedItem());
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
            else
            if(event.getPropertyName().equals("type73"))
            {
                type73ComboBox.removeActionListener(this);
                type73ComboBox.setSelectedItem((String)event.getNewValue());
                type73ComboBox.addActionListener(this);
            } else
            if(event.getPropertyName().equals("type87"))
            {
                type87ComboBox.removeActionListener(this);
                type87ComboBox.setSelectedItem((String)event.getNewValue());
                type87ComboBox.addActionListener(this);
            } else
            if(event.getPropertyName().equals("type100"))
            {
                type100ComboBox.removeActionListener(this);
                type100ComboBox.setSelectedItem((String)event.getNewValue());
                type100ComboBox.addActionListener(this);
            } else
            if(event.getPropertyName().equals("type130"))
            {
                type130ComboBox.removeActionListener(this);
                type130ComboBox.setSelectedItem((String)event.getNewValue());
                type130ComboBox.addActionListener(this);
            } else
            if(event.getPropertyName().equals("type145"))
            {
                type145ComboBox.removeActionListener(this);
                type145ComboBox.setSelectedItem((String)event.getNewValue());
                type145ComboBox.addActionListener(this);
            } else
            if(event.getPropertyName().equals("typeMogas"))
            {
                mogasComboBox.removeActionListener(this);
                mogasComboBox.setSelectedItem((String)event.getNewValue());
                mogasComboBox.addActionListener(this);
            } else
            if(event.getPropertyName().equals("typeJet"))
            {
                jetComboBox.removeActionListener(this);
                jetComboBox.setSelectedItem((String)event.getNewValue());
                jetComboBox.addActionListener(this);
            } else
            if(event.getPropertyName().equals("typeJetA"))
            {
                jetaComboBox.removeActionListener(this);
                jetaComboBox.setSelectedItem((String)event.getNewValue());
                jetaComboBox.addActionListener(this);
            } else
            if(event.getPropertyName().equals("typeJetA1"))
            {
                jeta1ComboBox.removeActionListener(this);
                jeta1ComboBox.setSelectedItem((String)event.getNewValue());
                jeta1ComboBox.addActionListener(this);
            } else
            if(event.getPropertyName().equals("typeJetAP"))
            {
                jetapComboBox.removeActionListener(this);
                jetapComboBox.setSelectedItem((String)event.getNewValue());
                jetapComboBox.addActionListener(this);
            } else
            if(event.getPropertyName().equals("typeJetB"))
            {
                jetbComboBox.removeActionListener(this);
                jetbComboBox.setSelectedItem((String)event.getNewValue());
                jetbComboBox.addActionListener(this);
            } else
            if(event.getPropertyName().equals("typeJet4"))
            {
                jet4ComboBox.removeActionListener(this);
                jet4ComboBox.setSelectedItem((String)event.getNewValue());
                jet4ComboBox.addActionListener(this);
            } else
            if(event.getPropertyName().equals("typeJet5"))
            {
                jet5ComboBox.removeActionListener(this);
                jet5ComboBox.setSelectedItem((String)event.getNewValue());
                jet5ComboBox.addActionListener(this);
            } else
            if(event.getPropertyName().equals("typeUnknown"))
            {
                unknownComboBox.removeActionListener(this);
                unknownComboBox.setSelectedItem((String)event.getNewValue());
                unknownComboBox.addActionListener(this);
            }
        } else
        if(event.getSource() instanceof VertexModel)
        {
            if(event.getPropertyName().equals("latLon"))
            {
                latTF.setText(DisplayEngine.getInstance().formatObjectLatitude(((LatLonPoint)event.getNewValue()).getLat()));
                lonTF.setText(DisplayEngine.getInstance().formatObjectLongitude(((LatLonPoint)event.getNewValue()).getLon()));
            }
        } else
        if((event.getSource() instanceof LockingEngine) && event.getPropertyName().equals("triggersLocked"))
        {
            latTF.setBackground(((Boolean)event.getNewValue()).booleanValue() ? Color.red : Color.white);
            lonTF.setBackground(((Boolean)event.getNewValue()).booleanValue() ? Color.red : Color.white);
            if(((Boolean)event.getNewValue()).booleanValue())
                lockingButton.setIcon(IconFactory.getInstance().getIcon("padlockLocked"));
            else
                lockingButton.setIcon(IconFactory.getInstance().getIcon("padlockUnlocked"));
        }
    }

    private TriggerModel model;
    private VertexModel vertexModel;
    private Vector listeners;
    private JButton lockingButton;
    private JButton deleteButton;
    private JComboBox type73ComboBox;
    private JComboBox type87ComboBox;
    private JComboBox type100ComboBox;
    private JComboBox type130ComboBox;
    private JComboBox type145ComboBox;
    private JComboBox mogasComboBox;
    private JComboBox jetComboBox;
    private JComboBox jetaComboBox;
    private JComboBox jeta1ComboBox;
    private JComboBox jetapComboBox;
    private JComboBox jetbComboBox;
    private JComboBox jet4ComboBox;
    private JComboBox jet5ComboBox;
    private JComboBox unknownComboBox;
    private JLabel vertexLabel;
    private JLabel indexLabel;
    private JTextField indexTF;
    private JLabel latLabel;
    private PopupTextField latTF;
    private JLabel lonLabel;
    private PopupTextField lonTF;
    private JLabel type73Label;
    private JLabel type87Label;
    private JLabel type100Label;
    private JLabel type130Label;
    private JLabel type145Label;
    private JLabel mogasLabel;
    private JLabel jetLabel;
    private JLabel jetaLabel;
    private JLabel jeta1Label;
    private JLabel jetapLabel;
    private JLabel jetbLabel;
    private JLabel jet4Label;
    private JLabel jet5Label;
    private JLabel unknownLabel;
}
