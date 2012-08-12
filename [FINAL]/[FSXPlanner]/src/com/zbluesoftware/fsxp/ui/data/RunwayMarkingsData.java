// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RunwayMarkingsData.java

package com.zbluesoftware.fsxp.ui.data;

import com.zbluesoftware.fsxp.model.MarkingsModel;
import com.zbluesoftware.fsxp.model.RunwayModel;
import com.zbluesoftware.fsxp.util.Utilities;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Vector;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class RunwayMarkingsData extends JPanel
    implements ActionListener, PropertyChangeListener
{

    public RunwayMarkingsData()
    {
        listeners = new Vector();
        setLayout(new GridBagLayout());
        setBorder(new EmptyBorder(5, 5, 5, 5));
        altThresholdLabel = new JLabel("Alternate Threshold");
        altThresholdLabel.setFont(Utilities.LABEL_FONT);
        altThresholdLabel.setForeground(Color.black);
        altThresholdComboBox = new JComboBox(getTrueFalseModel());
        altThresholdComboBox.setFont(Utilities.COMBO_BOX_FONT);
        altThresholdComboBox.setForeground(Color.black);
        altThresholdComboBox.addActionListener(this);
        altTouchdownLabel = new JLabel("Alternate Touchdown");
        altTouchdownLabel.setFont(Utilities.LABEL_FONT);
        altTouchdownLabel.setForeground(Color.black);
        altTouchdownComboBox = new JComboBox(getTrueFalseModel());
        altTouchdownComboBox.setFont(Utilities.COMBO_BOX_FONT);
        altTouchdownComboBox.setForeground(Color.black);
        altTouchdownComboBox.addActionListener(this);
        altFixedDistLabel = new JLabel("Alternate Fixed Distance");
        altFixedDistLabel.setFont(Utilities.LABEL_FONT);
        altFixedDistLabel.setForeground(Color.black);
        altFixedDistComboBox = new JComboBox(getTrueFalseModel());
        altFixedDistComboBox.setFont(Utilities.COMBO_BOX_FONT);
        altFixedDistComboBox.setForeground(Color.black);
        altFixedDistComboBox.addActionListener(this);
        altPrecisionLabel = new JLabel("Alternate Precision");
        altPrecisionLabel.setFont(Utilities.LABEL_FONT);
        altPrecisionLabel.setForeground(Color.black);
        altPrecisionComboBox = new JComboBox(getTrueFalseModel());
        altPrecisionComboBox.setFont(Utilities.COMBO_BOX_FONT);
        altPrecisionComboBox.setForeground(Color.black);
        altPrecisionComboBox.addActionListener(this);
        leadingZeroLabel = new JLabel("Leading Zero Indent");
        leadingZeroLabel.setFont(Utilities.LABEL_FONT);
        leadingZeroLabel.setForeground(Color.black);
        leadingZeroComboBox = new JComboBox(getTrueFalseModel());
        leadingZeroComboBox.setFont(Utilities.COMBO_BOX_FONT);
        leadingZeroComboBox.setForeground(Color.black);
        leadingZeroComboBox.addActionListener(this);
        noEndArrowsLabel = new JLabel("No Threshold End Arrows");
        noEndArrowsLabel.setFont(Utilities.LABEL_FONT);
        noEndArrowsLabel.setForeground(Color.black);
        noEndArrowsComboBox = new JComboBox(getTrueFalseModel());
        noEndArrowsComboBox.setFont(Utilities.COMBO_BOX_FONT);
        noEndArrowsComboBox.setForeground(Color.black);
        noEndArrowsComboBox.addActionListener(this);
        edgesLabel = new JLabel("Edge Lines");
        edgesLabel.setFont(Utilities.LABEL_FONT);
        edgesLabel.setForeground(Color.black);
        edgesComboBox = new JComboBox(getTrueFalseModel());
        edgesComboBox.setFont(Utilities.COMBO_BOX_FONT);
        edgesComboBox.setForeground(Color.black);
        edgesComboBox.addActionListener(this);
        thresholdLabel = new JLabel("Threshold Marks");
        thresholdLabel.setFont(Utilities.LABEL_FONT);
        thresholdLabel.setForeground(Color.black);
        thresholdComboBox = new JComboBox(getTrueFalseModel());
        thresholdComboBox.setFont(Utilities.COMBO_BOX_FONT);
        thresholdComboBox.setForeground(Color.black);
        thresholdComboBox.addActionListener(this);
        fixedDistLabel = new JLabel("Fixed Distance Marks");
        fixedDistLabel.setFont(Utilities.LABEL_FONT);
        fixedDistLabel.setForeground(Color.black);
        fixedDistComboBox = new JComboBox(getTrueFalseModel());
        fixedDistComboBox.setFont(Utilities.COMBO_BOX_FONT);
        fixedDistComboBox.setForeground(Color.black);
        fixedDistComboBox.addActionListener(this);
        touchdownLabel = new JLabel("Touchdown Marks");
        touchdownLabel.setFont(Utilities.LABEL_FONT);
        touchdownLabel.setForeground(Color.black);
        touchdownComboBox = new JComboBox(getTrueFalseModel());
        touchdownComboBox.setFont(Utilities.COMBO_BOX_FONT);
        touchdownComboBox.setForeground(Color.black);
        touchdownComboBox.addActionListener(this);
        dashLabel = new JLabel("Dashed Center Line");
        dashLabel.setFont(Utilities.LABEL_FONT);
        dashLabel.setForeground(Color.black);
        dashComboBox = new JComboBox(getTrueFalseModel());
        dashComboBox.setFont(Utilities.COMBO_BOX_FONT);
        dashComboBox.setForeground(Color.black);
        dashComboBox.addActionListener(this);
        identLabel = new JLabel("Display Ident");
        identLabel.setFont(Utilities.LABEL_FONT);
        identLabel.setForeground(Color.black);
        identComboBox = new JComboBox(getTrueFalseModel());
        identComboBox.setFont(Utilities.COMBO_BOX_FONT);
        identComboBox.setForeground(Color.black);
        identComboBox.addActionListener(this);
        precisionLabel = new JLabel("Precision Markings");
        precisionLabel.setFont(Utilities.LABEL_FONT);
        precisionLabel.setForeground(Color.black);
        precisionComboBox = new JComboBox(getTrueFalseModel());
        precisionComboBox.setFont(Utilities.COMBO_BOX_FONT);
        precisionComboBox.setForeground(Color.black);
        precisionComboBox.addActionListener(this);
        edgePavementLabel = new JLabel("Pavement Edges");
        edgePavementLabel.setFont(Utilities.LABEL_FONT);
        edgePavementLabel.setForeground(Color.black);
        edgePavementComboBox = new JComboBox(getTrueFalseModel());
        edgePavementComboBox.setFont(Utilities.COMBO_BOX_FONT);
        edgePavementComboBox.setForeground(Color.black);
        edgePavementComboBox.addActionListener(this);
        singleEndedLabel = new JLabel("Single Ended");
        singleEndedLabel.setFont(Utilities.LABEL_FONT);
        singleEndedLabel.setForeground(Color.black);
        singleEndedComboBox = new JComboBox(getTrueFalseModel());
        singleEndedComboBox.setFont(Utilities.COMBO_BOX_FONT);
        singleEndedComboBox.setForeground(Color.black);
        singleEndedComboBox.addActionListener(this);
        primaryClosedLabel = new JLabel("Primary End Closed");
        primaryClosedLabel.setFont(Utilities.LABEL_FONT);
        primaryClosedLabel.setForeground(Color.black);
        primaryClosedComboBox = new JComboBox(getTrueFalseModel());
        primaryClosedComboBox.setFont(Utilities.COMBO_BOX_FONT);
        primaryClosedComboBox.setForeground(Color.black);
        primaryClosedComboBox.addActionListener(this);
        secondaryClosedLabel = new JLabel("Secondary End Closed");
        secondaryClosedLabel.setFont(Utilities.LABEL_FONT);
        secondaryClosedLabel.setForeground(Color.black);
        secondaryClosedComboBox = new JComboBox(getTrueFalseModel());
        secondaryClosedComboBox.setFont(Utilities.COMBO_BOX_FONT);
        secondaryClosedComboBox.setForeground(Color.black);
        secondaryClosedComboBox.addActionListener(this);
        primarySTOLLabel = new JLabel("Primary End STOL");
        primarySTOLLabel.setFont(Utilities.LABEL_FONT);
        primarySTOLLabel.setForeground(Color.black);
        primarySTOLComboBox = new JComboBox(getTrueFalseModel());
        primarySTOLComboBox.setFont(Utilities.COMBO_BOX_FONT);
        primarySTOLComboBox.setForeground(Color.black);
        primarySTOLComboBox.addActionListener(this);
        secondarySTOLLabel = new JLabel("Secondary End STOL");
        secondarySTOLLabel.setFont(Utilities.LABEL_FONT);
        secondarySTOLLabel.setForeground(Color.black);
        secondarySTOLComboBox = new JComboBox(getTrueFalseModel());
        secondarySTOLComboBox.setFont(Utilities.COMBO_BOX_FONT);
        secondarySTOLComboBox.setForeground(Color.black);
        secondarySTOLComboBox.addActionListener(this);
        Utilities.addComponent(this, thresholdLabel, 0, 0, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, thresholdComboBox, 1, 0, 1, 1, 2, 13, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(this, altThresholdLabel, 0, 1, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, altThresholdComboBox, 1, 1, 1, 1, 2, 13, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(this, noEndArrowsLabel, 0, 2, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, noEndArrowsComboBox, 1, 2, 1, 1, 2, 13, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(this, touchdownLabel, 0, 3, 1, 1, 0, 13, new Insets(10, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, touchdownComboBox, 1, 3, 1, 1, 2, 13, new Insets(10, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(this, altTouchdownLabel, 0, 4, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, altTouchdownComboBox, 1, 4, 1, 1, 2, 13, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(this, fixedDistLabel, 0, 5, 1, 1, 0, 13, new Insets(10, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, fixedDistComboBox, 1, 5, 1, 1, 2, 13, new Insets(10, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(this, altFixedDistLabel, 0, 6, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, altFixedDistComboBox, 1, 6, 1, 1, 2, 13, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(this, precisionLabel, 0, 7, 1, 1, 0, 13, new Insets(10, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, precisionComboBox, 1, 7, 1, 1, 2, 13, new Insets(10, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(this, altPrecisionLabel, 0, 8, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, altPrecisionComboBox, 1, 8, 1, 1, 2, 13, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(this, identLabel, 0, 9, 1, 1, 0, 13, new Insets(10, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, identComboBox, 1, 9, 1, 1, 2, 13, new Insets(10, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(this, leadingZeroLabel, 0, 10, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, leadingZeroComboBox, 1, 10, 1, 1, 2, 13, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(this, edgesLabel, 0, 11, 1, 1, 0, 13, new Insets(10, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, edgesComboBox, 1, 11, 1, 1, 2, 13, new Insets(10, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(this, edgePavementLabel, 0, 12, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, edgePavementComboBox, 1, 12, 1, 1, 2, 13, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(this, dashLabel, 0, 13, 1, 1, 0, 13, new Insets(10, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, dashComboBox, 1, 13, 1, 1, 2, 13, new Insets(10, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(this, singleEndedLabel, 0, 14, 1, 1, 0, 13, new Insets(10, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, singleEndedComboBox, 1, 14, 1, 1, 2, 13, new Insets(10, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(this, primaryClosedLabel, 0, 15, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, primaryClosedComboBox, 1, 15, 1, 1, 2, 13, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(this, secondaryClosedLabel, 0, 16, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, secondaryClosedComboBox, 1, 16, 1, 1, 2, 13, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(this, primarySTOLLabel, 0, 17, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, primarySTOLComboBox, 1, 17, 1, 1, 2, 13, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(this, secondarySTOLLabel, 0, 18, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, secondarySTOLComboBox, 1, 18, 1, 1, 2, 13, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(this, Box.createGlue(), 0, 19, 2, 1, 3, 11, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.5D);
        updateDisplay(null);
    }

    private DefaultComboBoxModel getTrueFalseModel()
    {
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        model.addElement("TRUE");
        model.addElement("FALSE");
        return model;
    }

    public void updateDisplay(RunwayModel model)
    {
        if(this.model != null)
            this.model.getMarkingsModel().removePropertyChangeListener(this);
        this.model = model;
        altThresholdComboBox.removeActionListener(this);
        altTouchdownComboBox.removeActionListener(this);
        altFixedDistComboBox.removeActionListener(this);
        altPrecisionComboBox.removeActionListener(this);
        leadingZeroComboBox.removeActionListener(this);
        noEndArrowsComboBox.removeActionListener(this);
        edgesComboBox.removeActionListener(this);
        thresholdComboBox.removeActionListener(this);
        fixedDistComboBox.removeActionListener(this);
        touchdownComboBox.removeActionListener(this);
        dashComboBox.removeActionListener(this);
        identComboBox.removeActionListener(this);
        precisionComboBox.removeActionListener(this);
        edgePavementComboBox.removeActionListener(this);
        singleEndedComboBox.removeActionListener(this);
        primaryClosedComboBox.removeActionListener(this);
        secondaryClosedComboBox.removeActionListener(this);
        primarySTOLComboBox.removeActionListener(this);
        secondarySTOLComboBox.removeActionListener(this);
        if(model != null)
        {
            altThresholdComboBox.setSelectedIndex(model.getMarkingsModel().getAlternateThreshold() ? 0 : 1);
            altTouchdownComboBox.setSelectedIndex(model.getMarkingsModel().getAlternateTouchdown() ? 0 : 1);
            altFixedDistComboBox.setSelectedIndex(model.getMarkingsModel().getAlternateFixedDistance() ? 0 : 1);
            altPrecisionComboBox.setSelectedIndex(model.getMarkingsModel().getAlternatePrecision() ? 0 : 1);
            leadingZeroComboBox.setSelectedIndex(model.getMarkingsModel().getLeadingZeroIdent() ? 0 : 1);
            noEndArrowsComboBox.setSelectedIndex(model.getMarkingsModel().getNoThresholdEndArrows() ? 0 : 1);
            edgesComboBox.setSelectedIndex(model.getMarkingsModel().getEdges() ? 0 : 1);
            thresholdComboBox.setSelectedIndex(model.getMarkingsModel().getThreshold() ? 0 : 1);
            fixedDistComboBox.setSelectedIndex(model.getMarkingsModel().getFixed() ? 0 : 1);
            touchdownComboBox.setSelectedIndex(model.getMarkingsModel().getTouchdown() ? 0 : 1);
            dashComboBox.setSelectedIndex(model.getMarkingsModel().getDashes() ? 0 : 1);
            identComboBox.setSelectedIndex(model.getMarkingsModel().getIdent() ? 0 : 1);
            precisionComboBox.setSelectedIndex(model.getMarkingsModel().getPrecision() ? 0 : 1);
            edgePavementComboBox.setSelectedIndex(model.getMarkingsModel().getEdgePavement() ? 0 : 1);
            singleEndedComboBox.setSelectedIndex(model.getMarkingsModel().getSingleEnd() ? 0 : 1);
            primaryClosedComboBox.setSelectedIndex(model.getMarkingsModel().getPrimaryClosed() ? 0 : 1);
            secondaryClosedComboBox.setSelectedIndex(model.getMarkingsModel().getSecondaryClosed() ? 0 : 1);
            primarySTOLComboBox.setSelectedIndex(model.getMarkingsModel().getPrimaryStol() ? 0 : 1);
            secondarySTOLComboBox.setSelectedIndex(model.getMarkingsModel().getSecondaryStol() ? 0 : 1);
            model.getMarkingsModel().addPropertyChangeListener(this);
        } else
        {
            altThresholdComboBox.setSelectedItem("FALSE");
            altTouchdownComboBox.setSelectedItem("FALSE");
            altFixedDistComboBox.setSelectedItem("FALSE");
            altPrecisionComboBox.setSelectedItem("FALSE");
            leadingZeroComboBox.setSelectedItem("FALSE");
            noEndArrowsComboBox.setSelectedItem("FALSE");
            edgesComboBox.setSelectedItem("TRUE");
            thresholdComboBox.setSelectedItem("TRUE");
            fixedDistComboBox.setSelectedItem("TRUE");
            touchdownComboBox.setSelectedItem("TRUE");
            dashComboBox.setSelectedItem("TRUE");
            identComboBox.setSelectedItem("TRUE");
            precisionComboBox.setSelectedItem("FALSE");
            edgePavementComboBox.setSelectedItem("FALSE");
            singleEndedComboBox.setSelectedItem("FALSE");
            primaryClosedComboBox.setSelectedItem("FALSE");
            secondaryClosedComboBox.setSelectedItem("FALSE");
            primarySTOLComboBox.setSelectedItem("FALSE");
            secondarySTOLComboBox.setSelectedItem("FALSE");
        }
        if(model != null)
        {
            altThresholdComboBox.addActionListener(this);
            altTouchdownComboBox.addActionListener(this);
            altFixedDistComboBox.addActionListener(this);
            altPrecisionComboBox.addActionListener(this);
            leadingZeroComboBox.addActionListener(this);
            noEndArrowsComboBox.addActionListener(this);
            edgesComboBox.addActionListener(this);
            thresholdComboBox.addActionListener(this);
            fixedDistComboBox.addActionListener(this);
            touchdownComboBox.addActionListener(this);
            dashComboBox.addActionListener(this);
            identComboBox.addActionListener(this);
            precisionComboBox.addActionListener(this);
            edgePavementComboBox.addActionListener(this);
            singleEndedComboBox.addActionListener(this);
            primaryClosedComboBox.addActionListener(this);
            secondaryClosedComboBox.addActionListener(this);
            primarySTOLComboBox.addActionListener(this);
            secondarySTOLComboBox.addActionListener(this);
        }
        boolean status = model != null;
        altThresholdLabel.setEnabled(status);
        altThresholdComboBox.setEnabled(status);
        altTouchdownLabel.setEnabled(status);
        altTouchdownComboBox.setEnabled(status);
        altFixedDistLabel.setEnabled(status);
        altFixedDistComboBox.setEnabled(status);
        altPrecisionLabel.setEnabled(status);
        altPrecisionComboBox.setEnabled(status);
        leadingZeroLabel.setEnabled(status);
        leadingZeroComboBox.setEnabled(status);
        noEndArrowsLabel.setEnabled(status);
        noEndArrowsComboBox.setEnabled(status);
        edgesLabel.setEnabled(status);
        edgesComboBox.setEnabled(status);
        thresholdLabel.setEnabled(status);
        thresholdComboBox.setEnabled(status);
        fixedDistLabel.setEnabled(status);
        fixedDistComboBox.setEnabled(status);
        touchdownLabel.setEnabled(status);
        touchdownComboBox.setEnabled(status);
        dashLabel.setEnabled(status);
        dashComboBox.setEnabled(status);
        identLabel.setEnabled(status);
        identComboBox.setEnabled(status);
        precisionLabel.setEnabled(status);
        precisionComboBox.setEnabled(status);
        edgePavementLabel.setEnabled(status);
        edgePavementComboBox.setEnabled(status);
        singleEndedLabel.setEnabled(status);
        singleEndedComboBox.setEnabled(status);
        primaryClosedLabel.setEnabled(status);
        primaryClosedComboBox.setEnabled(status);
        secondaryClosedLabel.setEnabled(status);
        secondaryClosedComboBox.setEnabled(status);
        primarySTOLLabel.setEnabled(status);
        primarySTOLComboBox.setEnabled(status);
        secondarySTOLLabel.setEnabled(status);
        secondarySTOLComboBox.setEnabled(status);
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
        if(event.getSource() == altThresholdComboBox)
        {
            firePropertyChange("update-altthreshold", new Boolean(model.getMarkingsModel().getAlternateThreshold()), new Boolean(altThresholdComboBox.getSelectedIndex() == 0));
            model.getMarkingsModel().setAlternateThreshold(altThresholdComboBox.getSelectedIndex() == 0);
        } else
        if(event.getSource() == altTouchdownComboBox)
        {
            firePropertyChange("update-alttouchdown", new Boolean(model.getMarkingsModel().getAlternateTouchdown()), new Boolean(altTouchdownComboBox.getSelectedIndex() == 0));
            model.getMarkingsModel().setAlternateTouchdown(altTouchdownComboBox.getSelectedIndex() == 0);
        } else
        if(event.getSource() == altFixedDistComboBox)
        {
            firePropertyChange("update-altfixeddistance", new Boolean(model.getMarkingsModel().getAlternateFixedDistance()), new Boolean(altFixedDistComboBox.getSelectedIndex() == 0));
            model.getMarkingsModel().setAlternateFixedDistance(altFixedDistComboBox.getSelectedIndex() == 0);
        } else
        if(event.getSource() == altPrecisionComboBox)
        {
            firePropertyChange("update-altpercision", new Boolean(model.getMarkingsModel().getAlternatePrecision()), new Boolean(altPrecisionComboBox.getSelectedIndex() == 0));
            model.getMarkingsModel().setAlternatePrecision(altPrecisionComboBox.getSelectedIndex() == 0);
        } else
        if(event.getSource() == leadingZeroComboBox)
        {
            firePropertyChange("update-leadingzeroident", new Boolean(model.getMarkingsModel().getLeadingZeroIdent()), new Boolean(leadingZeroComboBox.getSelectedIndex() == 0));
            model.getMarkingsModel().setLeadingZeroIdent(leadingZeroComboBox.getSelectedIndex() == 0);
        } else
        if(event.getSource() == noEndArrowsComboBox)
        {
            firePropertyChange("update-noendarrows", new Boolean(model.getMarkingsModel().getNoThresholdEndArrows()), new Boolean(noEndArrowsComboBox.getSelectedIndex() == 0));
            model.getMarkingsModel().setNoThresholdEndArrows(noEndArrowsComboBox.getSelectedIndex() == 0);
        } else
        if(event.getSource() == edgesComboBox)
        {
            firePropertyChange("update-edges", new Boolean(model.getMarkingsModel().getEdges()), new Boolean(edgesComboBox.getSelectedIndex() == 0));
            model.getMarkingsModel().setEdges(edgesComboBox.getSelectedIndex() == 0);
        } else
        if(event.getSource() == thresholdComboBox)
        {
            firePropertyChange("update-threshold", new Boolean(model.getMarkingsModel().getThreshold()), new Boolean(thresholdComboBox.getSelectedIndex() == 0));
            model.getMarkingsModel().setThreshold(thresholdComboBox.getSelectedIndex() == 0);
        } else
        if(event.getSource() == fixedDistComboBox)
        {
            firePropertyChange("update-fixed", new Boolean(model.getMarkingsModel().getFixed()), new Boolean(fixedDistComboBox.getSelectedIndex() == 0));
            model.getMarkingsModel().setFixed(fixedDistComboBox.getSelectedIndex() == 0);
        } else
        if(event.getSource() == touchdownComboBox)
        {
            firePropertyChange("update-touchdown", new Boolean(model.getMarkingsModel().getTouchdown()), new Boolean(touchdownComboBox.getSelectedIndex() == 0));
            model.getMarkingsModel().setTouchdown(touchdownComboBox.getSelectedIndex() == 0);
        } else
        if(event.getSource() == dashComboBox)
        {
            firePropertyChange("update-dashes", new Boolean(model.getMarkingsModel().getDashes()), new Boolean(dashComboBox.getSelectedIndex() == 0));
            model.getMarkingsModel().setDashes(dashComboBox.getSelectedIndex() == 0);
        } else
        if(event.getSource() == identComboBox)
        {
            firePropertyChange("update-ident", new Boolean(model.getMarkingsModel().getIdent()), new Boolean(identComboBox.getSelectedIndex() == 0));
            model.getMarkingsModel().setIdent(identComboBox.getSelectedIndex() == 0);
        } else
        if(event.getSource() == precisionComboBox)
        {
            firePropertyChange("update-precision", new Boolean(model.getMarkingsModel().getPrecision()), new Boolean(precisionComboBox.getSelectedIndex() == 0));
            model.getMarkingsModel().setPrecision(precisionComboBox.getSelectedIndex() == 0);
        } else
        if(event.getSource() == edgePavementComboBox)
        {
            firePropertyChange("update-edgepavement", new Boolean(model.getMarkingsModel().getEdgePavement()), new Boolean(edgePavementComboBox.getSelectedIndex() == 0));
            model.getMarkingsModel().setEdgePavement(edgePavementComboBox.getSelectedIndex() == 0);
        } else
        if(event.getSource() == singleEndedComboBox)
        {
            firePropertyChange("update-singleend", new Boolean(model.getMarkingsModel().getSingleEnd()), new Boolean(singleEndedComboBox.getSelectedIndex() == 0));
            model.getMarkingsModel().setSingleEnd(singleEndedComboBox.getSelectedIndex() == 0);
        } else
        if(event.getSource() == primaryClosedComboBox)
        {
            firePropertyChange("update-primaryclosed", new Boolean(model.getMarkingsModel().getPrimaryClosed()), new Boolean(primaryClosedComboBox.getSelectedIndex() == 0));
            model.getMarkingsModel().setPrimaryClosed(primaryClosedComboBox.getSelectedIndex() == 0);
        } else
        if(event.getSource() == secondaryClosedComboBox)
        {
            firePropertyChange("update-secondaryclosed", new Boolean(model.getMarkingsModel().getSecondaryClosed()), new Boolean(secondaryClosedComboBox.getSelectedIndex() == 0));
            model.getMarkingsModel().setSecondaryClosed(secondaryClosedComboBox.getSelectedIndex() == 0);
        } else
        if(event.getSource() == primarySTOLComboBox)
        {
            firePropertyChange("update-primarystol", new Boolean(model.getMarkingsModel().getPrimaryStol()), new Boolean(primarySTOLComboBox.getSelectedIndex() == 0));
            model.getMarkingsModel().setPrimaryStol(primarySTOLComboBox.getSelectedIndex() == 0);
        } else
        if(event.getSource() == secondarySTOLComboBox)
        {
            firePropertyChange("update-secondarystol", new Boolean(model.getMarkingsModel().getSecondaryStol()), new Boolean(secondarySTOLComboBox.getSelectedIndex() == 0));
            model.getMarkingsModel().setSecondaryStol(secondarySTOLComboBox.getSelectedIndex() == 0);
        }
    }

    public void propertyChange(PropertyChangeEvent event)
    {
        if(event.getSource() == model.getMarkingsModel())
            if(event.getPropertyName().equals("alternateThreshold"))
            {
                altThresholdComboBox.removeActionListener(this);
                altThresholdComboBox.setSelectedIndex(((Boolean)event.getNewValue()).booleanValue() ? 0 : 1);
                altThresholdComboBox.addActionListener(this);
            } else
            if(event.getPropertyName().equals("alternateTouchdown"))
            {
                altTouchdownComboBox.removeActionListener(this);
                altTouchdownComboBox.setSelectedIndex(((Boolean)event.getNewValue()).booleanValue() ? 0 : 1);
                altTouchdownComboBox.addActionListener(this);
            } else
            if(event.getPropertyName().equals("alternateFixedDistance"))
            {
                altFixedDistComboBox.removeActionListener(this);
                altFixedDistComboBox.setSelectedIndex(((Boolean)event.getNewValue()).booleanValue() ? 0 : 1);
                altFixedDistComboBox.addActionListener(this);
            } else
            if(event.getPropertyName().equals("alternatePrecision"))
            {
                altPrecisionComboBox.removeActionListener(this);
                altPrecisionComboBox.setSelectedIndex(((Boolean)event.getNewValue()).booleanValue() ? 0 : 1);
                altPrecisionComboBox.addActionListener(this);
            } else
            if(event.getPropertyName().equals("leadingZeroIdent"))
            {
                leadingZeroComboBox.removeActionListener(this);
                leadingZeroComboBox.setSelectedIndex(((Boolean)event.getNewValue()).booleanValue() ? 0 : 1);
                leadingZeroComboBox.addActionListener(this);
            } else
            if(event.getPropertyName().equals("noThresholdEndArrows"))
            {
                noEndArrowsComboBox.removeActionListener(this);
                noEndArrowsComboBox.setSelectedIndex(((Boolean)event.getNewValue()).booleanValue() ? 0 : 1);
                noEndArrowsComboBox.addActionListener(this);
            } else
            if(event.getPropertyName().equals("edges"))
            {
                edgesComboBox.removeActionListener(this);
                edgesComboBox.setSelectedIndex(((Boolean)event.getNewValue()).booleanValue() ? 0 : 1);
                edgesComboBox.addActionListener(this);
            } else
            if(event.getPropertyName().equals("threshold"))
            {
                thresholdComboBox.removeActionListener(this);
                thresholdComboBox.setSelectedIndex(((Boolean)event.getNewValue()).booleanValue() ? 0 : 1);
                thresholdComboBox.addActionListener(this);
            } else
            if(event.getPropertyName().equals("fixed"))
            {
                fixedDistComboBox.removeActionListener(this);
                fixedDistComboBox.setSelectedIndex(((Boolean)event.getNewValue()).booleanValue() ? 0 : 1);
                fixedDistComboBox.addActionListener(this);
            } else
            if(event.getPropertyName().equals("touchdown"))
            {
                touchdownComboBox.removeActionListener(this);
                touchdownComboBox.setSelectedIndex(((Boolean)event.getNewValue()).booleanValue() ? 0 : 1);
                touchdownComboBox.addActionListener(this);
            } else
            if(event.getPropertyName().equals("dashes"))
            {
                dashComboBox.removeActionListener(this);
                dashComboBox.setSelectedIndex(((Boolean)event.getNewValue()).booleanValue() ? 0 : 1);
                dashComboBox.addActionListener(this);
            } else
            if(event.getPropertyName().equals("ident"))
            {
                identComboBox.removeActionListener(this);
                identComboBox.setSelectedIndex(((Boolean)event.getNewValue()).booleanValue() ? 0 : 1);
                identComboBox.addActionListener(this);
            } else
            if(event.getPropertyName().equals("precision"))
            {
                precisionComboBox.removeActionListener(this);
                precisionComboBox.setSelectedIndex(((Boolean)event.getNewValue()).booleanValue() ? 0 : 1);
                precisionComboBox.addActionListener(this);
            } else
            if(event.getPropertyName().equals("edgePavement"))
            {
                edgePavementComboBox.removeActionListener(this);
                edgePavementComboBox.setSelectedIndex(((Boolean)event.getNewValue()).booleanValue() ? 0 : 1);
                edgePavementComboBox.addActionListener(this);
            } else
            if(event.getPropertyName().equals("singleEnd"))
            {
                singleEndedComboBox.removeActionListener(this);
                singleEndedComboBox.setSelectedIndex(((Boolean)event.getNewValue()).booleanValue() ? 0 : 1);
                singleEndedComboBox.addActionListener(this);
            } else
            if(event.getPropertyName().equals("primaryClosed"))
            {
                primaryClosedComboBox.removeActionListener(this);
                primaryClosedComboBox.setSelectedIndex(((Boolean)event.getNewValue()).booleanValue() ? 0 : 1);
                primaryClosedComboBox.addActionListener(this);
            } else
            if(event.getPropertyName().equals("secondaryClosed"))
            {
                secondaryClosedComboBox.removeActionListener(this);
                secondaryClosedComboBox.setSelectedIndex(((Boolean)event.getNewValue()).booleanValue() ? 0 : 1);
                secondaryClosedComboBox.addActionListener(this);
            } else
            if(event.getPropertyName().equals("primaryStol"))
            {
                primarySTOLComboBox.removeActionListener(this);
                primarySTOLComboBox.setSelectedIndex(((Boolean)event.getNewValue()).booleanValue() ? 0 : 1);
                primarySTOLComboBox.addActionListener(this);
            } else
            if(event.getPropertyName().equals("secondaryStol"))
            {
                secondarySTOLComboBox.removeActionListener(this);
                secondarySTOLComboBox.setSelectedIndex(((Boolean)event.getNewValue()).booleanValue() ? 0 : 1);
                secondarySTOLComboBox.addActionListener(this);
            }
    }

    private RunwayModel model;
    private Vector listeners;
    private JComboBox altThresholdComboBox;
    private JComboBox altTouchdownComboBox;
    private JComboBox altFixedDistComboBox;
    private JComboBox altPrecisionComboBox;
    private JComboBox leadingZeroComboBox;
    private JComboBox noEndArrowsComboBox;
    private JComboBox edgesComboBox;
    private JComboBox thresholdComboBox;
    private JComboBox fixedDistComboBox;
    private JComboBox touchdownComboBox;
    private JComboBox dashComboBox;
    private JComboBox identComboBox;
    private JComboBox precisionComboBox;
    private JComboBox edgePavementComboBox;
    private JComboBox singleEndedComboBox;
    private JComboBox primaryClosedComboBox;
    private JComboBox secondaryClosedComboBox;
    private JComboBox primarySTOLComboBox;
    private JComboBox secondarySTOLComboBox;
    private JLabel altThresholdLabel;
    private JLabel altTouchdownLabel;
    private JLabel altFixedDistLabel;
    private JLabel altPrecisionLabel;
    private JLabel leadingZeroLabel;
    private JLabel noEndArrowsLabel;
    private JLabel edgesLabel;
    private JLabel thresholdLabel;
    private JLabel fixedDistLabel;
    private JLabel touchdownLabel;
    private JLabel dashLabel;
    private JLabel identLabel;
    private JLabel precisionLabel;
    private JLabel edgePavementLabel;
    private JLabel singleEndedLabel;
    private JLabel primaryClosedLabel;
    private JLabel secondaryClosedLabel;
    private JLabel primarySTOLLabel;
    private JLabel secondarySTOLLabel;
}
