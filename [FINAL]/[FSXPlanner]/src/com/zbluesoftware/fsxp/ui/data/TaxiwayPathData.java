// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TaxiwayPathData.java

package com.zbluesoftware.fsxp.ui.data;

import com.zbluesoftware.fsxp.comparator.TaxiwayNameSort;
import com.zbluesoftware.fsxp.dialog.TaxiNameDialog;
import com.zbluesoftware.fsxp.engine.LogEngine;
import com.zbluesoftware.fsxp.engine.SettingsEngine;
import com.zbluesoftware.fsxp.graphics.IconFactory;
import com.zbluesoftware.fsxp.model.*;
import com.zbluesoftware.fsxp.renderer.IndexNameRenderer;
import com.zbluesoftware.fsxp.ui.WindowBorder;
import com.zbluesoftware.fsxp.util.PopupTextField;
import com.zbluesoftware.fsxp.util.Utilities;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.ParseException;
import java.util.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

// Referenced classes of package com.zbluesoftware.fsxp.ui.data:
//            ObjectData

public class TaxiwayPathData extends ObjectData
    implements ActionListener, ChangeListener, FocusListener, PropertyChangeListener
{

    public TaxiwayPathData()
    {
        listeners = new Vector();
        highlightedTWPath = 0;
        setLayout(new BorderLayout(2, 2));
        deleteButton = new JButton(IconFactory.getInstance().getIcon("delete"));
        deleteButton.setBorder(new EmptyBorder(5, 5, 5, 5));
        deleteButton.setToolTipText("Delete");
        deleteButton.addActionListener(this);
        highlightButton = new JButton(IconFactory.getInstance().getIcon("highlightedTWPath"));
        highlightButton.setBorder(new EmptyBorder(5, 5, 5, 5));
        highlightButton.setToolTipText("Highlight Taxiway Path");
        highlightButton.addActionListener(this);
        JPanel buttonPanel = new JPanel(new FlowLayout(2));
        buttonPanel.add(highlightButton);
        buttonPanel.add(deleteButton);
        typeLabel = new JLabel("Type");
        typeLabel.setFont(Utilities.LABEL_FONT);
        typeLabel.setForeground(Color.black);
        DefaultComboBoxModel typeModel = new DefaultComboBoxModel();
        typeModel.addElement("RUNWAY");
        typeModel.addElement("PARKING");
        typeModel.addElement("TAXI");
        typeModel.addElement("PATH");
        typeModel.addElement("CLOSED");
        typeModel.addElement("VEHICLE");
        typeComboBox = new JComboBox(typeModel);
        typeComboBox.setFont(Utilities.COMBO_BOX_FONT);
        typeComboBox.setForeground(Color.black);
        typeComboBox.addActionListener(this);
        startLabel = new JLabel("Start Index");
        startLabel.setFont(Utilities.LABEL_FONT);
        startLabel.setForeground(Color.black);
        startTF = new PopupTextField(10);
        startTF.setFont(Utilities.TEXT_FIELD_FONT);
        startTF.setForeground(Color.black);
        startTF.addActionListener(this);
        startTF.addFocusListener(this);
        endLabel = new JLabel("End Index");
        endLabel.setFont(Utilities.LABEL_FONT);
        endLabel.setForeground(Color.black);
        endTF = new PopupTextField(10);
        endTF.setFont(Utilities.TEXT_FIELD_FONT);
        endTF.setForeground(Color.black);
        endTF.addActionListener(this);
        endTF.addFocusListener(this);
        widthLabel = new JLabel("Width");
        widthLabel.setFont(Utilities.LABEL_FONT);
        widthLabel.setForeground(Color.black);
        widthSpinner = new JSpinner(new SpinnerNumberModel(0.0D, 0.0D, 10000D, 1.0D));
        widthSpinner.setEditor(new javax.swing.JSpinner.NumberEditor(widthSpinner, "0.000"));
        widthSpinner.addChangeListener(this);
        widthComboBox = new JComboBox(new String[] {
            "M", "F"
        });
        widthComboBox.setFont(Utilities.COMBO_BOX_FONT);
        widthComboBox.setForeground(Color.black);
        widthComboBox.addActionListener(this);
        if(SettingsEngine.getInstance().getLAF().equals("Windows"))
            widthComboBox.setPrototypeDisplayValue("--------");
        JPanel widthPanel = new JPanel(new GridBagLayout());
        Utilities.addComponent(widthPanel, widthSpinner, 0, 0, 1, 1, 2, 13, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(widthPanel, widthComboBox, 1, 0, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        weightLabel = new JLabel("Weight");
        weightLabel.setFont(Utilities.LABEL_FONT);
        weightLabel.setForeground(Color.black);
        weightSpinner = new JSpinner(new SpinnerNumberModel(0.0D, 0.0D, 10000000D, 10D));
        weightSpinner.setEditor(new javax.swing.JSpinner.NumberEditor(weightSpinner, "0.000"));
        weightSpinner.addChangeListener(this);
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
        surfaceModel.addElement("OIL_TREATED");
        surfaceModel.addElement("PLANKS");
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
        centerLineLabel = new JLabel("Center Line");
        centerLineLabel.setFont(Utilities.LABEL_FONT);
        centerLineLabel.setForeground(Color.black);
        centerLineComboBox = new JComboBox(getTrueFalseModel());
        centerLineComboBox.setFont(Utilities.COMBO_BOX_FONT);
        centerLineComboBox.setForeground(Color.black);
        centerLineComboBox.setSelectedItem("FALSE");
        centerLineComboBox.addActionListener(this);
        clLightedLabel = new JLabel("CL Lighted");
        clLightedLabel.setFont(Utilities.LABEL_FONT);
        clLightedLabel.setForeground(Color.black);
        clLightedComboBox = new JComboBox(getTrueFalseModel());
        clLightedComboBox.setFont(Utilities.COMBO_BOX_FONT);
        clLightedComboBox.setForeground(Color.black);
        clLightedComboBox.setSelectedItem("FALSE");
        clLightedComboBox.addActionListener(this);
        leftEdgeLabel = new JLabel("Left Edge");
        leftEdgeLabel.setFont(Utilities.LABEL_FONT);
        leftEdgeLabel.setForeground(Color.black);
        leftEdgeComboBox = new JComboBox(getEdgeModel());
        leftEdgeComboBox.setFont(Utilities.COMBO_BOX_FONT);
        leftEdgeComboBox.setForeground(Color.black);
        leftEdgeComboBox.addActionListener(this);
        leLightedLabel = new JLabel("LE Lighted");
        leLightedLabel.setFont(Utilities.LABEL_FONT);
        leLightedLabel.setForeground(Color.black);
        leLightedComboBox = new JComboBox(getTrueFalseModel());
        leLightedComboBox.setFont(Utilities.COMBO_BOX_FONT);
        leLightedComboBox.setForeground(Color.black);
        leLightedComboBox.setSelectedItem("FALSE");
        leLightedComboBox.addActionListener(this);
        rightEdgeLabel = new JLabel("Right Edge");
        rightEdgeLabel.setFont(Utilities.LABEL_FONT);
        rightEdgeLabel.setForeground(Color.black);
        rightEdgeComboBox = new JComboBox(getEdgeModel());
        rightEdgeComboBox.setFont(Utilities.COMBO_BOX_FONT);
        rightEdgeComboBox.setForeground(Color.black);
        rightEdgeComboBox.addActionListener(this);
        reLightedLabel = new JLabel("RE Lighted");
        reLightedLabel.setFont(Utilities.LABEL_FONT);
        reLightedLabel.setForeground(Color.black);
        reLightedComboBox = new JComboBox(getTrueFalseModel());
        reLightedComboBox.setFont(Utilities.COMBO_BOX_FONT);
        reLightedComboBox.setForeground(Color.black);
        reLightedComboBox.setSelectedItem("FALSE");
        reLightedComboBox.addActionListener(this);
        DefaultComboBoxModel numberModel = new DefaultComboBoxModel();
        numberModel.addElement("---------");
        for(int i = 0; i < 10; i++)
            numberModel.addElement((new StringBuilder()).append("0").append(i).toString());

        for(int i = 10; i < 37; i++)
            numberModel.addElement((new StringBuilder()).append("").append(i).toString());

        numberModel.addElement("EAST");
        numberModel.addElement("NORTH");
        numberModel.addElement("NORTHEAST");
        numberModel.addElement("NORTHWEST");
        numberModel.addElement("SOUTH");
        numberModel.addElement("SOUTHEAST");
        numberModel.addElement("SOUTHWEST");
        numberModel.addElement("WEST");
        numberLabel = new JLabel("Number");
        numberLabel.setFont(Utilities.LABEL_FONT);
        numberLabel.setForeground(Color.black);
        numberLabel.setEnabled(false);
        numberComboBox = new JComboBox(numberModel);
        numberComboBox.setFont(Utilities.COMBO_BOX_FONT);
        numberComboBox.setForeground(Color.black);
        numberComboBox.addActionListener(this);
        numberComboBox.setEnabled(false);
        DefaultComboBoxModel designatorModel = new DefaultComboBoxModel();
        designatorModel.addElement("NONE");
        designatorModel.addElement("C");
        designatorModel.addElement("CENTER");
        designatorModel.addElement("L");
        designatorModel.addElement("LEFT");
        designatorModel.addElement("R");
        designatorModel.addElement("RIGHT");
        designatorModel.addElement("W");
        designatorModel.addElement("WATER");
        designatorModel.addElement("A");
        designatorModel.addElement("B");
        designatorLabel = new JLabel("Designator");
        designatorLabel.setFont(Utilities.LABEL_FONT);
        designatorLabel.setForeground(Color.black);
        designatorLabel.setEnabled(false);
        designatorComboBox = new JComboBox(designatorModel);
        designatorComboBox.setFont(Utilities.COMBO_BOX_FONT);
        designatorComboBox.setForeground(Color.black);
        designatorComboBox.addActionListener(this);
        designatorComboBox.setEnabled(false);
        nameLabel = new JLabel("Name");
        nameLabel.setFont(Utilities.LABEL_FONT);
        nameLabel.setForeground(Color.black);
        nameComboBox = new JComboBox();
        nameComboBox.setFont(Utilities.COMBO_BOX_FONT);
        nameComboBox.setForeground(Color.black);
        nameComboBox.setRenderer(new IndexNameRenderer());
        nameComboBox.addActionListener(this);
        nameButton = new JButton("add names");
        nameButton.setFont(Utilities.BUTTON_FONT);
        nameButton.setForeground(Color.black);
        nameButton.addActionListener(this);
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        Utilities.addComponent(mainPanel, buttonPanel, 0, 0, 3, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, typeLabel, 0, 1, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, typeComboBox, 1, 1, 2, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, startLabel, 0, 2, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, startTF, 1, 2, 2, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, endLabel, 0, 3, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, endTF, 1, 3, 2, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, widthLabel, 0, 4, 1, 1, 0, 13, new Insets(10, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, widthPanel, 1, 4, 2, 1, 2, 17, new Insets(10, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, weightLabel, 0, 5, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, weightSpinner, 1, 5, 2, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, surfaceLabel, 0, 6, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, surfaceComboBox, 1, 6, 2, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, numberLabel, 0, 7, 1, 1, 0, 13, new Insets(10, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, numberComboBox, 1, 7, 2, 1, 2, 17, new Insets(10, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, designatorLabel, 0, 8, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, designatorComboBox, 1, 8, 2, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, nameLabel, 0, 9, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, nameComboBox, 1, 9, 1, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, nameButton, 2, 9, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, drawSurfaceLabel, 0, 10, 1, 1, 0, 13, new Insets(10, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, drawSurfaceComboBox, 1, 10, 2, 1, 2, 17, new Insets(10, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, drawDetailLabel, 0, 11, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, drawDetailComboBox, 1, 11, 2, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, centerLineLabel, 0, 12, 1, 1, 0, 13, new Insets(10, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, centerLineComboBox, 1, 12, 2, 1, 2, 17, new Insets(10, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, clLightedLabel, 0, 13, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, clLightedComboBox, 1, 13, 2, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, leftEdgeLabel, 0, 14, 1, 1, 0, 13, new Insets(10, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, leftEdgeComboBox, 1, 14, 2, 1, 2, 17, new Insets(10, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, leLightedLabel, 0, 15, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, leLightedComboBox, 1, 15, 2, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, rightEdgeLabel, 0, 16, 1, 1, 0, 13, new Insets(10, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, rightEdgeComboBox, 1, 16, 2, 1, 2, 17, new Insets(10, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, reLightedLabel, 0, 17, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, reLightedComboBox, 1, 17, 2, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, Box.createGlue(), 1, 18, 3, 1, 3, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.5D);
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        add(scrollPane, "Center");
        windowBorder = new WindowBorder("Taxiway Path Data");
        setBorder(windowBorder);
    }

    private DefaultComboBoxModel getTrueFalseModel()
    {
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        model.addElement("TRUE");
        model.addElement("FALSE");
        return model;
    }

    private DefaultComboBoxModel getEdgeModel()
    {
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        model.addElement("NONE");
        model.addElement("SOLID");
        model.addElement("DASHED");
        model.addElement("SOLID_DASHED");
        return model;
    }

    public void updateDisplay(BaseModel baseModel)
    {
        TaxiwayPathModel model = null;
        if(baseModel instanceof TaxiwayPathModel)
            model = (TaxiwayPathModel)baseModel;
        if(this.model != null)
            this.model.removePropertyChangeListener(this);
        updateModel();
        this.model = model;
        boolean status = true;
        typeComboBox.removeActionListener(this);
        widthComboBox.removeActionListener(this);
        widthSpinner.removeChangeListener(this);
        surfaceComboBox.removeActionListener(this);
        leftEdgeComboBox.removeActionListener(this);
        rightEdgeComboBox.removeActionListener(this);
        weightSpinner.removeChangeListener(this);
        numberComboBox.removeActionListener(this);
        designatorComboBox.removeActionListener(this);
        nameComboBox.removeActionListener(this);
        drawSurfaceComboBox.removeActionListener(this);
        drawDetailComboBox.removeActionListener(this);
        centerLineComboBox.removeActionListener(this);
        clLightedComboBox.removeActionListener(this);
        leLightedComboBox.removeActionListener(this);
        reLightedComboBox.removeActionListener(this);
        if(model != null)
        {
            typeComboBox.setSelectedItem(model.getType());
            startTF.setText((new StringBuilder()).append("").append(model.getStart()).toString());
            endTF.setText((new StringBuilder()).append("").append(model.getEnd()).toString());
            widthSpinner.setValue(new Double(model.getWidth()));
            widthComboBox.setSelectedItem(model.getWidthMeasure());
            weightSpinner.setValue(new Double(model.getWeightLimit()));
            surfaceComboBox.setSelectedItem(model.getSurface());
            drawSurfaceComboBox.setSelectedIndex(model.getDrawSurface() ? 0 : 1);
            drawDetailComboBox.setSelectedIndex(model.getDrawDetail() ? 0 : 1);
            centerLineComboBox.setSelectedIndex(model.getCenterLine() ? 0 : 1);
            clLightedComboBox.setSelectedIndex(model.getCenterLineLighted() ? 0 : 1);
            leftEdgeComboBox.setSelectedItem(model.getLeftEdge());
            leLightedComboBox.setSelectedIndex(model.getLeftEdgeLighted() ? 0 : 1);
            rightEdgeComboBox.setSelectedItem(model.getRightEdge());
            reLightedComboBox.setSelectedIndex(model.getRightEdgeLighted() ? 0 : 1);
            Utilities.setCodeDescComboBox(nameComboBox, (new StringBuilder()).append("").append(model.getName()).toString(), "-1");
            if(model.getNumber().length() == 0)
                numberComboBox.setSelectedIndex(0);
            else
                numberComboBox.setSelectedItem(model.getNumber());
            if(model.getDesignator().length() == 0)
                designatorComboBox.setSelectedIndex(0);
            else
                designatorComboBox.setSelectedItem(model.getDesignator());
            model.addPropertyChangeListener(this);
        } else
        {
            typeComboBox.setSelectedIndex(0);
            startTF.setText("");
            endTF.setText("");
            widthSpinner.setValue(new Double(0.0D));
            widthComboBox.setSelectedIndex(0);
            weightSpinner.setValue(new Double(0.0D));
            surfaceComboBox.setSelectedIndex(0);
            drawSurfaceComboBox.setSelectedIndex(0);
            drawDetailComboBox.setSelectedIndex(0);
            centerLineComboBox.setSelectedIndex(0);
            clLightedComboBox.setSelectedIndex(0);
            leftEdgeComboBox.setSelectedIndex(0);
            leLightedComboBox.setSelectedIndex(0);
            rightEdgeComboBox.setSelectedIndex(0);
            reLightedComboBox.setSelectedIndex(0);
            nameComboBox.setSelectedIndex(0);
            numberComboBox.setSelectedIndex(0);
            designatorComboBox.setSelectedIndex(0);
            status = false;
        }
        enableOptions();
        typeComboBox.addActionListener(this);
        widthComboBox.addActionListener(this);
        widthSpinner.addChangeListener(this);
        surfaceComboBox.addActionListener(this);
        leftEdgeComboBox.addActionListener(this);
        rightEdgeComboBox.addActionListener(this);
        weightSpinner.addChangeListener(this);
        numberComboBox.addActionListener(this);
        designatorComboBox.addActionListener(this);
        nameComboBox.addActionListener(this);
        drawSurfaceComboBox.addActionListener(this);
        drawDetailComboBox.addActionListener(this);
        centerLineComboBox.addActionListener(this);
        clLightedComboBox.addActionListener(this);
        leLightedComboBox.addActionListener(this);
        reLightedComboBox.addActionListener(this);
        deleteButton.setEnabled(status);
        typeLabel.setEnabled(status);
        typeComboBox.setEnabled(status);
        startLabel.setEnabled(status);
        startTF.setEnabled(status);
        endLabel.setEnabled(status);
        endTF.setEnabled(status);
        widthLabel.setEnabled(status);
        widthSpinner.setEnabled(status);
        widthComboBox.setEnabled(status);
        weightLabel.setEnabled(status);
        weightSpinner.setEnabled(status);
        surfaceLabel.setEnabled(status);
        surfaceComboBox.setEnabled(status);
        drawSurfaceLabel.setEnabled(status);
        drawSurfaceComboBox.setEnabled(status);
        drawDetailLabel.setEnabled(status);
        drawDetailComboBox.setEnabled(status);
        centerLineLabel.setEnabled(status);
        centerLineComboBox.setEnabled(status);
        clLightedLabel.setEnabled(status);
        clLightedComboBox.setEnabled(status);
        leftEdgeLabel.setEnabled(status);
        leftEdgeComboBox.setEnabled(status);
        leLightedLabel.setEnabled(status);
        leLightedComboBox.setEnabled(status);
        rightEdgeLabel.setEnabled(status);
        rightEdgeComboBox.setEnabled(status);
        reLightedLabel.setEnabled(status);
        reLightedComboBox.setEnabled(status);
        numberLabel.setEnabled(status);
        numberComboBox.setEnabled(status);
        designatorLabel.setEnabled(status);
        designatorComboBox.setEnabled(status);
        nameLabel.setEnabled(status);
        nameComboBox.setEnabled(status);
        nameButton.setEnabled(status);
    }

    public void updateModel()
    {
        if(model != null)
        {
            try
            {
                widthSpinner.commitEdit();
            }
            catch(ParseException pe)
            {
                LogEngine.getInstance().log(pe);
            }
            try
            {
                weightSpinner.commitEdit();
            }
            catch(ParseException pe)
            {
                LogEngine.getInstance().log(pe);
            }
            model.setWeightLimit(((Double)weightSpinner.getValue()).floatValue());
            model.setSurface((String)surfaceComboBox.getSelectedItem());
            model.setDrawSurface(drawSurfaceComboBox.getSelectedIndex() == 0);
            model.setDrawDetail(drawDetailComboBox.getSelectedIndex() == 0);
            model.setCenterLine(centerLineComboBox.getSelectedIndex() == 0);
            model.setCenterLineLighted(clLightedComboBox.getSelectedIndex() == 0);
            model.setLeftEdge((String)leftEdgeComboBox.getSelectedItem());
            model.setLeftEdgeLighted(leLightedComboBox.getSelectedIndex() == 0);
            model.setRightEdge((String)rightEdgeComboBox.getSelectedItem());
            model.setRightEdgeLighted(reLightedComboBox.getSelectedIndex() == 0);
            if(nameComboBox.getSelectedIndex() == 0)
                model.setName(-1);
            else
                model.setName(Integer.parseInt((String)((HashMap)nameComboBox.getSelectedItem()).get("index")));
            if(numberComboBox.getSelectedIndex() == 0)
                model.setNumber("");
            else
                model.setNumber((String)numberComboBox.getSelectedItem());
            if(designatorComboBox.getSelectedIndex() == 0)
                model.setDesignator("");
            else
                model.setDesignator((String)designatorComboBox.getSelectedItem());
        }
    }

    public void updateNames(ArrayList taxiNameAL)
    {
        ArrayList arrayList = new ArrayList();
        for(int i = taxiNameAL.size() - 1; i >= 0; i--)
        {
            HashMap h2 = new HashMap();
            String index = (new StringBuilder()).append("").append(((TaxiNameModel)taxiNameAL.get(i)).getIndex()).toString();
            String name = ((TaxiNameModel)taxiNameAL.get(i)).getName();
            if(name.length() == 0)
                name = "[none]";
            h2.put("index", index);
            h2.put("name", name);
            arrayList.add(h2);
        }

        Collections.sort(arrayList, new TaxiwayNameSort());
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        HashMap hashMap = new HashMap();
        hashMap.put("index", "-1");
        hashMap.put("name", "------");
        model.addElement(hashMap);
        for(int i = 0; i < arrayList.size(); i++)
            model.addElement(arrayList.get(i));

        nameComboBox.setModel(model);
    }

    private void updateStart()
    {
        int start = model.getStart();
        try
        {
            start = Integer.parseInt(startTF.getText());
        }
        catch(NumberFormatException nfe)
        {
            start = model.getStart();
        }
        if(start < 0)
            start = 0;
        else
        if(start > 3999)
            start = 3999;
        startTF.setText((new StringBuilder()).append("").append(start).toString());
        firePropertyChange("update-start", new Integer(model.getStart()), new Integer(start));
        model.setStart(start);
    }

    private void updateEnd()
    {
        int end = model.getEnd();
        try
        {
            end = Integer.parseInt(endTF.getText());
        }
        catch(NumberFormatException nfe)
        {
            end = model.getEnd();
        }
        if(end < 0)
            end = 0;
        else
        if(end > 3999)
            end = 3999;
        endTF.setText((new StringBuilder()).append("").append(end).toString());
        firePropertyChange("update-end", new Integer(model.getEnd()), new Integer(end));
        model.setEnd(end);
    }

    private void updateType()
    {
        firePropertyChange("update-type", model.getType(), (String)typeComboBox.getSelectedItem());
        model.setType((String)typeComboBox.getSelectedItem());
        enableOptions();
        if(model.getType().equals("RUNWAY"))
        {
            if(model.getNumber().length() == 0)
                numberComboBox.setSelectedItem("01");
            if(model.getDesignator().length() == 0)
                designatorComboBox.setSelectedItem("NONE");
            nameComboBox.setSelectedIndex(0);
        } else
        {
            if(model.getName() == 0)
                nameComboBox.setSelectedIndex(Math.min(1, nameComboBox.getItemCount() - 1));
            numberComboBox.setSelectedIndex(0);
            designatorComboBox.setSelectedIndex(0);
        }
    }

    private void enableOptions()
    {
        if(model != null && model.getType().equals("RUNWAY"))
        {
            numberLabel.setEnabled(true);
            numberComboBox.setEnabled(true);
            designatorLabel.setEnabled(true);
            designatorComboBox.setEnabled(true);
            nameLabel.setEnabled(false);
            nameComboBox.setEnabled(false);
        } else
        {
            numberLabel.setEnabled(false);
            numberComboBox.setEnabled(false);
            designatorLabel.setEnabled(false);
            designatorComboBox.setEnabled(false);
            nameLabel.setEnabled(true);
            nameComboBox.setEnabled(true);
        }
    }

    private void updateWidthMeasure()
    {
        if(((String)widthComboBox.getSelectedItem()).equals(model.getWidthMeasure()))
            return;
        if(SettingsEngine.getInstance().getAdjustMeasurements())
        {
            if(model.getWidthMeasure().equals("M"))
                model.setWidth(model.getWidth() * 3.28F);
            else
                model.setWidth(model.getWidth() / 3.28F);
            widthSpinner.setValue(new Double(model.getWidth()));
        }
        firePropertyChange("update-widthMeasure", model.getWidthMeasure(), (String)widthComboBox.getSelectedItem());
        model.setWidthMeasure((String)widthComboBox.getSelectedItem());
    }

    private void delete()
    {
        if(JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this Taxiway Path?", "Confirm Delete...", 0) != 0)
        {
            return;
        } else
        {
            firePropertyChange("delete", model, model);
            updateDisplay(null);
            return;
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

    public void actionPerformed(ActionEvent event)
    {
        if(event.getSource() == startTF)
            updateStart();
        else
        if(event.getSource() == endTF)
            updateEnd();
        else
        if(event.getSource() == typeComboBox && model != null)
            updateType();
        else
        if(event.getSource() == widthComboBox && model != null)
            updateWidthMeasure();
        else
        if(event.getSource() == deleteButton)
            delete();
        else
        if(event.getSource() == surfaceComboBox)
        {
            firePropertyChange("update-surface", model.getSurface(), (String)surfaceComboBox.getSelectedItem());
            model.setSurface((String)surfaceComboBox.getSelectedItem());
        } else
        if(event.getSource() == leftEdgeComboBox)
        {
            firePropertyChange("update-left edge", model.getLeftEdge(), (String)leftEdgeComboBox.getSelectedItem());
            model.setLeftEdge((String)leftEdgeComboBox.getSelectedItem());
        } else
        if(event.getSource() == rightEdgeComboBox)
        {
            firePropertyChange("update-right edge", model.getRightEdge(), (String)rightEdgeComboBox.getSelectedItem());
            model.setRightEdge((String)rightEdgeComboBox.getSelectedItem());
        } else
        if(event.getSource() == numberComboBox)
        {
            if(numberComboBox.getSelectedIndex() == 0)
                model.setNumber("");
            else
                model.setNumber((String)numberComboBox.getSelectedItem());
        } else
        if(event.getSource() == designatorComboBox)
        {
            if(designatorComboBox.getSelectedIndex() == 0)
                model.setDesignator("");
            else
                model.setDesignator((String)designatorComboBox.getSelectedItem());
        } else
        if(event.getSource() == nameComboBox)
        {
            if(nameComboBox.getSelectedIndex() == 0)
                model.setName(-1);
            else
                model.setName(Integer.parseInt((String)((HashMap)nameComboBox.getSelectedItem()).get("index")));
        } else
        if(event.getSource() == drawSurfaceComboBox)
            model.setDrawSurface(drawSurfaceComboBox.getSelectedIndex() == 0);
        else
        if(event.getSource() == drawDetailComboBox)
            model.setDrawDetail(drawDetailComboBox.getSelectedIndex() == 0);
        else
        if(event.getSource() == centerLineComboBox)
            model.setCenterLine(centerLineComboBox.getSelectedIndex() == 0);
        else
        if(event.getSource() == clLightedComboBox)
        {
            firePropertyChange("update-clLighted", new Boolean(model.getCenterLineLighted()), new Boolean(clLightedComboBox.getSelectedIndex() == 0));
            model.setCenterLineLighted(clLightedComboBox.getSelectedIndex() == 0);
        } else
        if(event.getSource() == leLightedComboBox)
        {
            firePropertyChange("update-leLighted", new Boolean(model.getLeftEdgeLighted()), new Boolean(leLightedComboBox.getSelectedIndex() == 0));
            model.setLeftEdgeLighted(leLightedComboBox.getSelectedIndex() == 0);
        } else
        if(event.getSource() == reLightedComboBox)
        {
            firePropertyChange("update-reLighted", new Boolean(model.getRightEdgeLighted()), new Boolean(reLightedComboBox.getSelectedIndex() == 0));
            model.setRightEdgeLighted(reLightedComboBox.getSelectedIndex() == 0);
        } else
        if(event.getSource() == nameButton)
            TaxiNameDialog.showDialog(Utilities.MAIN_FRAME, (AirportModel)model.getParentModel());
        else
        if(event.getSource() == highlightButton)
            if(highlightedTWPath != model.getName())
            {
                firePropertyChange("highlight", ((HashMap)nameComboBox.getSelectedItem()).get("index"), ((HashMap)nameComboBox.getSelectedItem()).get("index"));
                highlightedTWPath = model.getName();
            } else
            {
                firePropertyChange("highlight", "0", "0");
                highlightedTWPath = 0;
            }
    }

    public void stateChanged(ChangeEvent event)
    {
        if(event.getSource() == widthSpinner && model != null)
        {
            firePropertyChange("update-width", new Float(model.getWidth()), new Float(((Double)widthSpinner.getValue()).doubleValue()));
            model.setWidth((float)((Double)widthSpinner.getValue()).doubleValue());
        } else
        if(event.getSource() == weightSpinner && model != null)
            model.setWeightLimit(((Double)weightSpinner.getValue()).floatValue());
    }

    public void focusGained(FocusEvent focusevent)
    {
    }

    public void focusLost(FocusEvent event)
    {
        if(event.getSource() == startTF && !startTF.isPopupDisplayed())
            updateStart();
        else
        if(event.getSource() == endTF && !endTF.isPopupDisplayed())
            updateEnd();
    }

    public void propertyChange(PropertyChangeEvent event)
    {
        if(event.getSource() instanceof AirportModel)
        {
            if(event.getPropertyName().equals("taxiNames"))
                updateNames((ArrayList)event.getNewValue());
        } else
        if(event.getSource() == model)
            if(event.getPropertyName().equals("type"))
            {
                typeComboBox.removeActionListener(this);
                typeComboBox.setSelectedItem(event.getNewValue());
                typeComboBox.addActionListener(this);
            } else
            if(event.getPropertyName().equals("surface"))
            {
                surfaceComboBox.removeActionListener(this);
                surfaceComboBox.setSelectedItem(event.getNewValue());
                surfaceComboBox.addActionListener(this);
            } else
            if(event.getPropertyName().equals("leftEdge"))
            {
                leftEdgeComboBox.removeActionListener(this);
                leftEdgeComboBox.setSelectedItem(event.getNewValue());
                leftEdgeComboBox.addActionListener(this);
            } else
            if(event.getPropertyName().equals("rightEdge"))
            {
                rightEdgeComboBox.removeActionListener(this);
                rightEdgeComboBox.setSelectedItem(event.getNewValue());
                rightEdgeComboBox.addActionListener(this);
            } else
            if(event.getPropertyName().equals("number"))
            {
                numberComboBox.removeActionListener(this);
                if(((String)event.getNewValue()).equals(""))
                    numberComboBox.setSelectedIndex(0);
                else
                    numberComboBox.setSelectedItem(event.getNewValue());
                numberComboBox.addActionListener(this);
            } else
            if(event.getPropertyName().equals("designator"))
            {
                designatorComboBox.removeActionListener(this);
                designatorComboBox.setSelectedItem(event.getNewValue());
                designatorComboBox.addActionListener(this);
            } else
            if(event.getPropertyName().equals("widthMeasure"))
                widthComboBox.setSelectedItem(event.getNewValue());
            else
            if(event.getPropertyName().equals("name"))
            {
                nameComboBox.removeActionListener(this);
                Utilities.setCodeDescComboBox(nameComboBox, (new StringBuilder()).append("").append(event.getNewValue()).toString(), "-1");
                nameComboBox.addActionListener(this);
            } else
            if(event.getPropertyName().equals("start"))
                startTF.setText((new StringBuilder()).append("").append(((Integer)event.getNewValue()).intValue()).toString());
            else
            if(event.getPropertyName().equals("end"))
                endTF.setText((new StringBuilder()).append("").append(((Integer)event.getNewValue()).intValue()).toString());
            else
            if(event.getPropertyName().equals("width"))
            {
                widthSpinner.removeChangeListener(this);
                widthSpinner.setValue(new Double(((Float)event.getNewValue()).doubleValue()));
                widthSpinner.addChangeListener(this);
            } else
            if(event.getPropertyName().equals("weightLimit"))
            {
                weightSpinner.removeChangeListener(this);
                weightSpinner.setValue(new Double(((Float)event.getNewValue()).doubleValue()));
                weightSpinner.addChangeListener(this);
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
            if(event.getPropertyName().equals("centerLine"))
            {
                centerLineComboBox.removeActionListener(this);
                centerLineComboBox.setSelectedIndex(((Boolean)event.getNewValue()).booleanValue() ? 0 : 1);
                centerLineComboBox.addActionListener(this);
            } else
            if(event.getPropertyName().equals("centerLineLighted"))
            {
                clLightedComboBox.removeActionListener(this);
                clLightedComboBox.setSelectedIndex(((Boolean)event.getNewValue()).booleanValue() ? 0 : 1);
                clLightedComboBox.addActionListener(this);
            } else
            if(event.getPropertyName().equals("leftEdgeLighted"))
            {
                leLightedComboBox.removeActionListener(this);
                leLightedComboBox.setSelectedIndex(((Boolean)event.getNewValue()).booleanValue() ? 0 : 1);
                leLightedComboBox.addActionListener(this);
            } else
            if(event.getPropertyName().equals("rightEdgeLighted"))
            {
                reLightedComboBox.removeActionListener(this);
                reLightedComboBox.setSelectedIndex(((Boolean)event.getNewValue()).booleanValue() ? 0 : 1);
                reLightedComboBox.addActionListener(this);
            }
    }

    private TaxiwayPathModel model;
    private Vector listeners;
    private JButton deleteButton;
    private JButton highlightButton;
    private JComboBox typeComboBox;
    private PopupTextField startTF;
    private PopupTextField endTF;
    private JSpinner widthSpinner;
    private JComboBox widthComboBox;
    private JSpinner weightSpinner;
    private JComboBox surfaceComboBox;
    private JComboBox drawSurfaceComboBox;
    private JComboBox drawDetailComboBox;
    private JComboBox centerLineComboBox;
    private JComboBox clLightedComboBox;
    private JComboBox leftEdgeComboBox;
    private JComboBox leLightedComboBox;
    private JComboBox rightEdgeComboBox;
    private JComboBox reLightedComboBox;
    private JComboBox numberComboBox;
    private JComboBox designatorComboBox;
    private JComboBox nameComboBox;
    private JButton nameButton;
    private JLabel numberLabel;
    private JLabel designatorLabel;
    private JLabel nameLabel;
    private JLabel typeLabel;
    private JLabel startLabel;
    private JLabel endLabel;
    private JLabel widthLabel;
    private JLabel weightLabel;
    private JLabel surfaceLabel;
    private JLabel drawSurfaceLabel;
    private JLabel drawDetailLabel;
    private JLabel centerLineLabel;
    private JLabel clLightedLabel;
    private JLabel leftEdgeLabel;
    private JLabel leLightedLabel;
    private JLabel rightEdgeLabel;
    private JLabel reLightedLabel;
    private int highlightedTWPath;
}
