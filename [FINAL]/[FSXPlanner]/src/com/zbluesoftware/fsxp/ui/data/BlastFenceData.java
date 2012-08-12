// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BlastFenceData.java

package com.zbluesoftware.fsxp.ui.data;

import com.zbluesoftware.fsxp.engine.*;
import com.zbluesoftware.fsxp.graphics.IconFactory;
import com.zbluesoftware.fsxp.model.*;
import com.zbluesoftware.fsxp.renderer.IndexNameRenderer;
import com.zbluesoftware.fsxp.ui.WindowBorder;
import com.zbluesoftware.fsxp.util.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

// Referenced classes of package com.zbluesoftware.fsxp.ui.data:
//            ObjectData

public class BlastFenceData extends ObjectData
    implements ActionListener, FocusListener, PropertyChangeListener
{

    public BlastFenceData()
    {
        listeners = new Vector();
        setLayout(new BorderLayout(2, 2));
        cutButton = new JButton(IconFactory.getInstance().getIcon("cutLine"));
        cutButton.setBorder(new EmptyBorder(5, 5, 5, 5));
        cutButton.setToolTipText("Cut String");
        cutButton.addActionListener(this);
        cutButton.setEnabled(false);
        lockingButton = new JButton(IconFactory.getInstance().getIcon("padlockUnlocked"));
        lockingButton.setBorder(new EmptyBorder(5, 5, 5, 5));
        lockingButton.setToolTipText("Lock/Unlock Blast Fences");
        lockingButton.addActionListener(this);
        if(LockingEngine.getInstance().getBlastLocked())
            lockingButton.setIcon(IconFactory.getInstance().getIcon("padlockLocked"));
        deleteButton = new JButton(IconFactory.getInstance().getIcon("delete"));
        deleteButton.setBorder(new EmptyBorder(5, 5, 5, 5));
        deleteButton.setToolTipText("Delete");
        deleteButton.addActionListener(this);
        JPanel buttonPanel = new JPanel(new FlowLayout(2));
        buttonPanel.add(cutButton);
        buttonPanel.add(lockingButton);
        buttonPanel.add(deleteButton);
        sizeLabel = new JLabel("Size");
        sizeLabel.setFont(Utilities.LABEL_FONT);
        sizeLabel.setForeground(Color.black);
        sizeComboBox = new JComboBox(getFenceTypes());
        sizeComboBox.setFont(Utilities.COMBO_BOX_FONT);
        sizeComboBox.setForeground(Color.black);
        sizeComboBox.setRenderer(new IndexNameRenderer());
        sizeComboBox.addActionListener(this);
        profileLabel = new JLabel("Profile");
        profileLabel.setFont(Utilities.LABEL_FONT);
        profileLabel.setForeground(Color.black);
        profileTF = new PopupTextField(20);
        profileTF.setFont(Utilities.TEXT_FIELD_FONT);
        profileTF.setForeground(Color.black);
        profileTF.addActionListener(this);
        profileTF.addFocusListener(this);
        instanceLabel = new JLabel("Instance ID");
        instanceLabel.setFont(Utilities.LABEL_FONT);
        instanceLabel.setForeground(Color.black);
        instanceTF = new PopupTextField(20);
        instanceTF.setFont(Utilities.TEXT_FIELD_FONT);
        instanceTF.setForeground(Color.black);
        instanceTF.addActionListener(this);
        instanceTF.addFocusListener(this);
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
        if(LockingEngine.getInstance().getBlastLocked())
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
        if(LockingEngine.getInstance().getBlastLocked())
            lonTF.setBackground(Color.red);
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        Utilities.addComponent(mainPanel, buttonPanel, 0, 0, 2, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, sizeLabel, 0, 1, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, sizeComboBox, 1, 1, 1, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, profileLabel, 0, 2, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, profileTF, 1, 2, 1, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, instanceLabel, 0, 3, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, instanceTF, 1, 3, 1, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
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
        windowBorder = new WindowBorder("Blast Fence Data");
        setBorder(windowBorder);
        LockingEngine.getInstance().addPropertyChangeListener(this);
    }

    private DefaultComboBoxModel getFenceTypes()
    {
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        HashMap hashMap = new HashMap();
        hashMap.put("index", "");
        hashMap.put("name", "Custom Blast Fence");
        model.addElement(hashMap);
        hashMap = new HashMap();
        hashMap.put("index", "{91ce5d88-59b9-41e2-9776-b371490fc378}");
        hashMap.put("name", "Tiny Blast Fence");
        model.addElement(hashMap);
        hashMap = new HashMap();
        hashMap.put("index", "{28c633da-8926-48aa-b467-30799560462e}");
        hashMap.put("name", "Small Blast Fence");
        model.addElement(hashMap);
        hashMap = new HashMap();
        hashMap.put("index", "{a8beb882-f11f-4743-b09b-34f45f380802}");
        hashMap.put("name", "Medium Blast Fence");
        model.addElement(hashMap);
        hashMap = new HashMap();
        hashMap.put("index", "{5b05e5fc-915b-4c87-a31d-615bc9dbf1ab}");
        hashMap.put("name", "Large Blast Fence");
        model.addElement(hashMap);
        hashMap = new HashMap();
        hashMap.put("index", "{bafc77d3-fa52-4cc2-b713-7609560bb026}");
        hashMap.put("name", "Huge Blast Fence");
        model.addElement(hashMap);
        return model;
    }

    public void updateDisplay(BaseModel baseModel)
    {
        BlastFenceModel model = null;
        if(baseModel instanceof BlastFenceModel)
            model = (BlastFenceModel)baseModel;
        if(this.model != null)
            this.model.removePropertyChangeListener(this);
        updateModel();
        this.model = model;
        boolean status = true;
        profileTF.removeActionListener(this);
        instanceTF.removeActionListener(this);
        sizeComboBox.removeActionListener(this);
        if(model != null)
        {
            profileTF.setText(model.getProfile());
            instanceTF.setText(model.getInstanceID());
            profileTF.setCaretPosition(0);
            instanceTF.setCaretPosition(0);
            Utilities.setCodeDescComboBox(sizeComboBox, model.getProfile(), "");
            model.addPropertyChangeListener(this);
        } else
        {
            profileTF.setText("{5b05e5fc-915b-4c87-a31d-615bc9dbf1ab}");
            instanceTF.setText("");
            sizeComboBox.setSelectedIndex(3);
            status = false;
        }
        displayVertexModel(null);
        profileTF.addActionListener(this);
        instanceTF.addActionListener(this);
        sizeComboBox.addActionListener(this);
        deleteButton.setEnabled(status);
        lockingButton.setEnabled(status);
        profileLabel.setEnabled(status);
        profileTF.setEnabled(status);
        instanceLabel.setEnabled(status);
        instanceTF.setEnabled(status);
        sizeLabel.setEnabled(status);
        sizeComboBox.setEnabled(status);
        profileLabel.setEnabled(status && sizeComboBox.getSelectedIndex() == 0);
        profileTF.setEnabled(status && sizeComboBox.getSelectedIndex() == 0);
        instanceLabel.setEnabled(status && sizeComboBox.getSelectedIndex() == 0);
        instanceTF.setEnabled(status && sizeComboBox.getSelectedIndex() == 0);
    }

    public void updateModel()
    {
        if(model != null)
        {
            model.setProfile(profileTF.getText());
            model.setInstanceID(instanceTF.getText());
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
        cutButton.setEnabled(enabled);
    }

    private void delete()
    {
        if(JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this Blast Fence?", "Confirm Delete...", 0) != 0)
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
        if(JOptionPane.showConfirmDialog(this, (new StringBuilder()).append("Are you sure you want to cut this Blast Fence after vertex ").append(indexTF.getText()).append("?").toString(), "Confirm Cut...", 0) != 0)
        {
            return;
        } else
        {
            firePropertyChange("cut", new Integer(indexTF.getText()), new Integer(indexTF.getText()));
            return;
        }
    }

    private void setFenceSize()
    {
        profileLabel.setEnabled(sizeComboBox.getSelectedIndex() == 0);
        profileTF.setEnabled(sizeComboBox.getSelectedIndex() == 0);
        instanceLabel.setEnabled(sizeComboBox.getSelectedIndex() == 0);
        instanceTF.setEnabled(sizeComboBox.getSelectedIndex() == 0);
        profileTF.setText((String)((HashMap)sizeComboBox.getSelectedItem()).get("index"));
        profileTF.setCaretPosition(0);
        instanceTF.setText("");
        if(sizeComboBox.getSelectedIndex() == 0)
            profileTF.requestFocus();
    }

    public BlastFenceModel getBlastFenceModel()
    {
        return model;
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
            LockingEngine.getInstance().setBlastLocked(!LockingEngine.getInstance().getBlastLocked());
        else
        if(event.getSource() == profileTF)
            model.setProfile(profileTF.getText());
        else
        if(event.getSource() == instanceTF)
            model.setInstanceID(instanceTF.getText());
        else
        if(event.getSource() == latTF)
            updateLatitude();
        else
        if(event.getSource() == lonTF)
            updateLongitude();
        else
        if(event.getSource() == cutButton)
            cutString();
        else
        if(event.getSource() == sizeComboBox)
            setFenceSize();
    }

    public void focusGained(FocusEvent focusevent)
    {
    }

    public void focusLost(FocusEvent event)
    {
        if(event.getSource() == profileTF && !profileTF.isPopupDisplayed())
            model.setProfile(profileTF.getText());
        else
        if(event.getSource() == instanceTF && !instanceTF.isPopupDisplayed())
            model.setInstanceID(instanceTF.getText());
        else
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
            if(event.getPropertyName().equals("instanceID"))
                instanceTF.setText((String)event.getNewValue());
            else
            if(event.getPropertyName().equals("profile"))
                profileTF.setText((String)event.getNewValue());
            else
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
        if((event.getSource() instanceof LockingEngine) && event.getPropertyName().equals("blastLocked"))
        {
            latTF.setBackground(((Boolean)event.getNewValue()).booleanValue() ? Color.red : Color.white);
            lonTF.setBackground(((Boolean)event.getNewValue()).booleanValue() ? Color.red : Color.white);
            if(((Boolean)event.getNewValue()).booleanValue())
                lockingButton.setIcon(IconFactory.getInstance().getIcon("padlockLocked"));
            else
                lockingButton.setIcon(IconFactory.getInstance().getIcon("padlockUnlocked"));
        }
    }

    private BlastFenceModel model;
    private VertexModel vertexModel;
    private Vector listeners;
    private JButton lockingButton;
    private JButton deleteButton;
    private JButton cutButton;
    private JComboBox sizeComboBox;
    private PopupTextField profileTF;
    private PopupTextField instanceTF;
    private JLabel vertexLabel;
    private JLabel indexLabel;
    private JTextField indexTF;
    private JLabel latLabel;
    private PopupTextField latTF;
    private JLabel lonLabel;
    private PopupTextField lonTF;
    private JLabel sizeLabel;
    private JLabel profileLabel;
    private JLabel instanceLabel;
}
