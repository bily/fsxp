// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PreferencesDialog.java

package com.zbluesoftware.fsxp.dialog;

import com.zbluesoftware.fsxp.engine.*;
import com.zbluesoftware.fsxp.model.table.ColorPrefsTableModel;
import com.zbluesoftware.fsxp.renderer.ColorPrefsTableRenderer;
import com.zbluesoftware.fsxp.util.Utilities;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.*;

public class PreferencesDialog extends JDialog
    implements ActionListener, CaretListener, ChangeListener, ListSelectionListener, MouseListener
{

    private PreferencesDialog(Frame parent)
    {
        super(parent, "Preferences", true);
        tempFonts = new Font[8];
        tempFonts[0] = Utilities.MENU_FONT;
        tempFonts[1] = Utilities.LAT_LON_FONT;
        tempFonts[2] = Utilities.PARKING_FONT;
        tempFonts[3] = Utilities.JETWAY_FONT;
        tempFonts[4] = Utilities.ILS_FONT;
        tempFonts[5] = Utilities.SCENERY_FONT;
        tempFonts[6] = Utilities.VOR_FONT;
        tempFonts[7] = Utilities.NDB_FONT;
        JLabel latLonLabel = new JLabel("Latitude/Longitude pointer format:");
        latLonLabel.setFont(Utilities.LABEL_FONT);
        latLonLabel.setForeground(Color.black);
        DefaultComboBoxModel latLonModel = new DefaultComboBoxModel();
        latLonModel.addElement("40.7772444561124");
        latLonModel.addElement("40\260 46' 38.07\"N");
        latLonModel.addElement("40\260 46.6345'N");
        latLonModel.addElement("N40\260 46' 38.07\"");
        latLonModel.addElement("N40\260 46.6345'");
        latLonComboBox = new JComboBox(latLonModel);
        latLonComboBox.setFont(Utilities.COMBO_BOX_FONT);
        latLonComboBox.setForeground(Color.black);
        latLonComboBox.setSelectedIndex(SettingsEngine.getInstance().getLatLonDisplay());
        JLabel latLonGridLabel = new JLabel("Latitude/Longitude grid format:");
        latLonGridLabel.setFont(Utilities.LABEL_FONT);
        latLonGridLabel.setForeground(Color.black);
        DefaultComboBoxModel latLonGridModel = new DefaultComboBoxModel();
        latLonGridModel.addElement("40.777244");
        latLonGridModel.addElement("40\260 46' 38\"");
        latLonGridModel.addElement("40\260 46.6345'");
        latLonGridComboBox = new JComboBox(latLonGridModel);
        latLonGridComboBox.setFont(Utilities.COMBO_BOX_FONT);
        latLonGridComboBox.setForeground(Color.black);
        latLonGridComboBox.setSelectedIndex(SettingsEngine.getInstance().getLatLonGridDisplay());
        JLabel latLonObjectLabel = new JLabel("Latitude/Longitude object format:");
        latLonObjectLabel.setFont(Utilities.LABEL_FONT);
        latLonObjectLabel.setForeground(Color.black);
        DefaultComboBoxModel latLonObjectModel = new DefaultComboBoxModel();
        latLonObjectModel.addElement("40.7772444561124");
        latLonObjectModel.addElement("40 46 38.07N");
        latLonObjectModel.addElement("40 46.6345N");
        latLonObjectModel.addElement("N40 46 38.07");
        latLonObjectModel.addElement("N40 46.6345");
        latLonObjectComboBox = new JComboBox(latLonObjectModel);
        latLonObjectComboBox.setFont(Utilities.COMBO_BOX_FONT);
        latLonObjectComboBox.setForeground(Color.black);
        latLonObjectComboBox.setSelectedIndex(SettingsEngine.getInstance().getLatLonObjectDisplay());
        if(SettingsEngine.getInstance().getLAF().equals("Windows"))
            latLonObjectComboBox.setPrototypeDisplayValue("--------------------------------------");
        JLabel coordLabel = new JLabel("Display lat/lon lines every:");
        coordLabel.setFont(Utilities.LABEL_FONT);
        coordLabel.setForeground(Color.black);
        DefaultComboBoxModel coordModel = new DefaultComboBoxModel();
        for(int i = 5; i < 35; i += 5)
            coordModel.addElement((new StringBuilder()).append(i).append(" seconds").toString());

        coordComboBox = new JComboBox(coordModel);
        coordComboBox.setFont(Utilities.COMBO_BOX_FONT);
        coordComboBox.setForeground(Color.black);
        coordComboBox.setSelectedIndex(SettingsEngine.getInstance().getSecondsIncrement() / 5 - 1);
        JLabel historyLabel = new JLabel("Max items to store in history:");
        historyLabel.setFont(Utilities.LABEL_FONT);
        historyLabel.setForeground(Color.black);
        historySpinner = new JSpinner(new SpinnerNumberModel(30, 1, 10000, 1));
        historySpinner.setEditor(new javax.swing.JSpinner.NumberEditor(historySpinner, "0"));
        historySpinner.setValue(new Integer(SettingsEngine.getInstance().getMaxHistoryItems()));
        JLabel selectedLabel = new JLabel("Selected items should be:");
        selectedLabel.setFont(Utilities.LABEL_FONT);
        selectedLabel.setForeground(Color.black);
        selectedComboBox = new JComboBox(new String[] {
            "Outlined", "Highlighted"
        });
        selectedComboBox.setFont(Utilities.COMBO_BOX_FONT);
        selectedComboBox.setForeground(Color.black);
        selectedComboBox.setSelectedIndex(SettingsEngine.getInstance().isSelectedItemOutlined() ? 0 : 1);
        JLabel backgroundLabel = new JLabel("Display background images:");
        backgroundLabel.setFont(Utilities.LABEL_FONT);
        backgroundLabel.setForeground(Color.black);
        backgroundComboBox = new JComboBox(new String[] {
            "Below Airport", "Above Airport"
        });
        backgroundComboBox.setFont(Utilities.COMBO_BOX_FONT);
        backgroundComboBox.setForeground(Color.black);
        backgroundComboBox.setSelectedIndex(SettingsEngine.getInstance().getBGImagesBelow() ? 0 : 1);
        JLabel bgOpacityLabel = new JLabel("Background image opacity:");
        bgOpacityLabel.setFont(Utilities.LABEL_FONT);
        bgOpacityLabel.setForeground(Color.black);
        bgOpacitySpinner = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
        bgOpacitySpinner.setEditor(new javax.swing.JSpinner.NumberEditor(bgOpacitySpinner, "0"));
        bgOpacitySpinner.setValue(new Integer(SettingsEngine.getInstance().getBGImageOpacity()));
        JLabel lafLabel = new JLabel("Look & Feel:");
        lafLabel.setFont(Utilities.LABEL_FONT);
        lafLabel.setForeground(Color.black);
        DefaultComboBoxModel lafModel = new DefaultComboBoxModel();
        javax.swing.UIManager.LookAndFeelInfo lafs[] = UIManager.getInstalledLookAndFeels();
        for(int i = 0; i < lafs.length; i++)
            lafModel.addElement(lafs[i].getName());

        lafComboBox = new JComboBox(lafModel);
        lafComboBox.setFont(Utilities.COMBO_BOX_FONT);
        lafComboBox.setForeground(Color.black);
        lafComboBox.setSelectedItem(SettingsEngine.getInstance().getLAF());
        JLabel restartLabel = new JLabel("(requires restart)");
        restartLabel.setFont(Utilities.LABEL_FONT);
        restartLabel.setForeground(Color.black);
        JPanel displayPanel = new JPanel(new GridBagLayout());
        displayPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        Utilities.addComponent(displayPanel, latLonLabel, 0, 0, 1, 1, 0, 13, new Insets(5, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(displayPanel, latLonComboBox, 1, 0, 2, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(displayPanel, latLonGridLabel, 0, 1, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(displayPanel, latLonGridComboBox, 1, 1, 2, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(displayPanel, latLonObjectLabel, 0, 2, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(displayPanel, latLonObjectComboBox, 1, 2, 2, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(displayPanel, coordLabel, 0, 3, 1, 1, 0, 13, new Insets(10, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(displayPanel, coordComboBox, 1, 3, 1, 1, 2, 17, new Insets(10, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(displayPanel, historyLabel, 0, 4, 1, 1, 0, 13, new Insets(10, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(displayPanel, historySpinner, 1, 4, 1, 1, 2, 17, new Insets(10, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(displayPanel, selectedLabel, 0, 5, 1, 1, 0, 13, new Insets(10, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(displayPanel, selectedComboBox, 1, 5, 1, 1, 2, 17, new Insets(10, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(displayPanel, backgroundLabel, 0, 6, 1, 1, 0, 13, new Insets(10, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(displayPanel, backgroundComboBox, 1, 6, 1, 1, 2, 17, new Insets(10, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(displayPanel, bgOpacityLabel, 0, 7, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(displayPanel, bgOpacitySpinner, 1, 7, 1, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(displayPanel, lafLabel, 0, 8, 1, 1, 0, 13, new Insets(10, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(displayPanel, lafComboBox, 1, 8, 1, 1, 2, 17, new Insets(10, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(displayPanel, restartLabel, 2, 8, 1, 1, 0, 17, new Insets(10, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(displayPanel, Box.createGlue(), 1, 9, 3, 1, 1, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.5D);
        altitudeCB = new JCheckBox("Use airport altitude for new object altitudes", SettingsEngine.getInstance().getUseAirportAlt());
        altitudeCB.setFont(Utilities.LABEL_FONT);
        altitudeCB.setForeground(Color.black);
        adjustMeasureCB = new JCheckBox("Adjust measurements when switching between Feet and Meters", SettingsEngine.getInstance().getAdjustMeasurements());
        adjustMeasureCB.setFont(Utilities.LABEL_FONT);
        adjustMeasureCB.setForeground(Color.black);
        drawPathsCB = new JCheckBox("Draw taxiway type PATH with width", SettingsEngine.getInstance().getDrawTaxiwayPaths());
        drawPathsCB.setFont(Utilities.LABEL_FONT);
        drawPathsCB.setForeground(Color.black);
        taxiwayPropertiesCB = new JCheckBox("Set new taxiway properties based on existing ones.", SettingsEngine.getInstance().getTaxiwayProperties());
        taxiwayPropertiesCB.setFont(Utilities.LABEL_FONT);
        taxiwayPropertiesCB.setForeground(Color.black);
        taxiwayRunwayCB = new JCheckBox("Taxiways drawn on runways should inherit the Runway's properties.", SettingsEngine.getInstance().getTaxiwayRunways());
        taxiwayRunwayCB.setFont(Utilities.LABEL_FONT);
        taxiwayRunwayCB.setForeground(Color.black);
        jetwayCB = new JCheckBox("Update Jetways when Parking Gate information changes.", SettingsEngine.getInstance().getUpdateJetways());
        jetwayCB.setFont(Utilities.LABEL_FONT);
        jetwayCB.setForeground(Color.black);
        edgeLightCB = new JCheckBox("Display lines between Apron Edge Lights.", SettingsEngine.getInstance().getEdgeLightLines());
        edgeLightCB.setFont(Utilities.LABEL_FONT);
        edgeLightCB.setForeground(Color.black);
        JPanel optionsPanel = new JPanel(new GridBagLayout());
        optionsPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        Utilities.addComponent(optionsPanel, altitudeCB, 0, 0, 1, 1, 0, 17, new Insets(5, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(optionsPanel, adjustMeasureCB, 0, 1, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(optionsPanel, drawPathsCB, 0, 2, 1, 1, 0, 17, new Insets(10, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(optionsPanel, taxiwayPropertiesCB, 0, 3, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(optionsPanel, taxiwayRunwayCB, 0, 4, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(optionsPanel, jetwayCB, 0, 5, 1, 1, 0, 17, new Insets(10, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(optionsPanel, edgeLightCB, 0, 6, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(optionsPanel, Box.createGlue(), 1, 7, 1, 1, 1, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.5D);
        restoreWindowCB = new JCheckBox("Restore last window location when starting.", SettingsEngine.getInstance().getRestoreWindow());
        restoreWindowCB.setFont(Utilities.LABEL_FONT);
        restoreWindowCB.setForeground(Color.black);
        clearCompileCB = new JCheckBox("Clear Compiler Output when Compile Dialog is displayed.", SettingsEngine.getInstance().getClearCompile());
        clearCompileCB.setFont(Utilities.LABEL_FONT);
        clearCompileCB.setForeground(Color.black);
        xmlCB = new JCheckBox("Write each XML attribute on a separate line.", SettingsEngine.getInstance().getXMLPerLine());
        xmlCB.setFont(Utilities.LABEL_FONT);
        xmlCB.setForeground(Color.black);
        deletesCB = new JCheckBox("Read/write Deletes from/to airport XML files.", SettingsEngine.getInstance().getWriteDeletes());
        deletesCB.setFont(Utilities.LABEL_FONT);
        deletesCB.setForeground(Color.black);
        excludesCB = new JCheckBox("Read/write Exclusion Rectangles from/to airport XML files.", SettingsEngine.getInstance().getWriteExcludes());
        excludesCB.setFont(Utilities.LABEL_FONT);
        excludesCB.setForeground(Color.black);
        bgImageCB = new JCheckBox("Read/Write Background Images from/to airport XML files.", SettingsEngine.getInstance().getWriteBGImages());
        bgImageCB.setFont(Utilities.LABEL_FONT);
        bgImageCB.setForeground(Color.black);
        readNavCB = new JCheckBox("Read additional navigation information.", SettingsEngine.getInstance().getReadNav());
        readNavCB.setFont(Utilities.LABEL_FONT);
        readNavCB.setForeground(Color.black);
        readSceneryCB = new JCheckBox("Read additional scenery information.", SettingsEngine.getInstance().getReadScenery());
        readSceneryCB.setFont(Utilities.LABEL_FONT);
        readSceneryCB.setForeground(Color.black);
        memoryCB = new JCheckBox("Display memory window at startup", SettingsEngine.getInstance().getDisplayMemory());
        memoryCB.setFont(Utilities.LABEL_FONT);
        memoryCB.setForeground(Color.black);
        fpsCB = new JCheckBox("Display fps", SettingsEngine.getInstance().getDisplayFPS());
        fpsCB.setFont(Utilities.LABEL_FONT);
        fpsCB.setForeground(Color.black);
        bufferCB = new JCheckBox("Use double buffering", SettingsEngine.getInstance().getDoubleBuffer());
        bufferCB.setFont(Utilities.LABEL_FONT);
        bufferCB.setForeground(Color.black);
        ribbonCB = new JCheckBox("Use ribbon menus (requires restart)", SettingsEngine.getInstance().getRibbonMenus());
        ribbonCB.setFont(Utilities.LABEL_FONT);
        ribbonCB.setForeground(Color.black);
        JPanel appOptionsPanel = new JPanel(new GridBagLayout());
        appOptionsPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        Utilities.addComponent(appOptionsPanel, clearCompileCB, 0, 0, 1, 1, 0, 17, new Insets(5, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(appOptionsPanel, xmlCB, 0, 1, 1, 1, 0, 17, new Insets(10, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(appOptionsPanel, deletesCB, 0, 2, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(appOptionsPanel, excludesCB, 0, 3, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(appOptionsPanel, bgImageCB, 0, 4, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(appOptionsPanel, readNavCB, 0, 5, 1, 1, 0, 17, new Insets(10, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(appOptionsPanel, readSceneryCB, 0, 6, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(appOptionsPanel, restoreWindowCB, 0, 7, 1, 1, 0, 17, new Insets(10, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(appOptionsPanel, memoryCB, 0, 8, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(appOptionsPanel, fpsCB, 0, 9, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(appOptionsPanel, bufferCB, 0, 10, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(appOptionsPanel, ribbonCB, 0, 11, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(appOptionsPanel, Box.createGlue(), 1, 12, 1, 1, 1, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.5D);
        JLabel parkingLabel = new JLabel("Parking Spot Size:");
        parkingLabel.setFont(Utilities.LABEL_FONT);
        parkingLabel.setForeground(Color.black);
        parkingSpinner = new JSpinner(new SpinnerNumberModel(2D, 2D, 10000D, 1.0D));
        parkingSpinner.setEditor(new javax.swing.JSpinner.NumberEditor(parkingSpinner, "0.0"));
        parkingSpinner.setValue(new Double(SettingsEngine.getInstance().getParkingSize()));
        parkingComboBox = new JComboBox(new String[] {
            "M", "F"
        });
        parkingComboBox.setFont(Utilities.COMBO_BOX_FONT);
        parkingComboBox.setForeground(Color.black);
        parkingComboBox.setSelectedItem(SettingsEngine.getInstance().getParkingMeasure());
        if(SettingsEngine.getInstance().getLAF().equals("Windows"))
            parkingComboBox.setPrototypeDisplayValue("--------");
        JLabel tee1Label = new JLabel("Tee 1 Offset:");
        tee1Label.setFont(Utilities.LABEL_FONT);
        tee1Label.setForeground(Color.black);
        tee1Spinner = new JSpinner(new SpinnerNumberModel(0.0D, 0.0D, 50D, 0.01D));
        tee1Spinner.setEditor(new javax.swing.JSpinner.NumberEditor(tee1Spinner, "00.00000"));
        tee1Spinner.setValue(new Double(SettingsEngine.getInstance().getTee1Offset()));
        JLabel tee2Label = new JLabel("Tee 2 Offset:");
        tee2Label.setFont(Utilities.LABEL_FONT);
        tee2Label.setForeground(Color.black);
        tee2Spinner = new JSpinner(new SpinnerNumberModel(0.0D, 0.0D, 50D, 0.01D));
        tee2Spinner.setEditor(new javax.swing.JSpinner.NumberEditor(tee2Spinner, "00.00000"));
        tee2Spinner.setValue(new Double(SettingsEngine.getInstance().getTee2Offset()));
        JLabel tee3Label = new JLabel("Tee 3 Offset:");
        tee3Label.setFont(Utilities.LABEL_FONT);
        tee3Label.setForeground(Color.black);
        tee3Spinner = new JSpinner(new SpinnerNumberModel(0.0D, 0.0D, 50D, 0.01D));
        tee3Spinner.setEditor(new javax.swing.JSpinner.NumberEditor(tee3Spinner, "00.00000"));
        tee3Spinner.setValue(new Double(SettingsEngine.getInstance().getTee3Offset()));
        JLabel tee4Label = new JLabel("Tee 4 Offset:");
        tee4Label.setFont(Utilities.LABEL_FONT);
        tee4Label.setForeground(Color.black);
        tee4Spinner = new JSpinner(new SpinnerNumberModel(0.0D, 0.0D, 50D, 0.01D));
        tee4Spinner.setEditor(new javax.swing.JSpinner.NumberEditor(tee4Spinner, "00.00000"));
        tee4Spinner.setValue(new Double(SettingsEngine.getInstance().getTee4Offset()));
        JLabel taxiwayLabel = new JLabel("Taxiway Width:");
        taxiwayLabel.setFont(Utilities.LABEL_FONT);
        taxiwayLabel.setForeground(Color.black);
        taxiwaySpinner = new JSpinner(new SpinnerNumberModel(2D, 2D, 10000D, 1.0D));
        taxiwaySpinner.setEditor(new javax.swing.JSpinner.NumberEditor(taxiwaySpinner, "0.0"));
        taxiwaySpinner.setValue(new Double(SettingsEngine.getInstance().getTaxiwayWidth()));
        taxiwayComboBox = new JComboBox(new String[] {
            "M", "F"
        });
        taxiwayComboBox.setFont(Utilities.COMBO_BOX_FONT);
        taxiwayComboBox.setForeground(Color.black);
        taxiwayComboBox.setSelectedItem(SettingsEngine.getInstance().getTaxiwayMeasure());
        if(SettingsEngine.getInstance().getLAF().equals("Windows"))
            taxiwayComboBox.setPrototypeDisplayValue("--------");
        JLabel taxiWeightLabel = new JLabel("Taxiway Weight:");
        taxiWeightLabel.setFont(Utilities.LABEL_FONT);
        taxiWeightLabel.setForeground(Color.black);
        taxiWeightSpinner = new JSpinner(new SpinnerNumberModel(0.0D, 0.0D, 10000000D, 10D));
        taxiWeightSpinner.setEditor(new javax.swing.JSpinner.NumberEditor(taxiWeightSpinner, "0.0"));
        taxiWeightSpinner.setValue(new Double(SettingsEngine.getInstance().getTaxiwayWeight()));
        JLabel helipadLengthLabel = new JLabel("Helipad Length:");
        helipadLengthLabel.setFont(Utilities.LABEL_FONT);
        helipadLengthLabel.setForeground(Color.black);
        helipadLengthSpinner = new JSpinner(new SpinnerNumberModel(2D, 2D, 10000D, 1.0D));
        helipadLengthSpinner.setEditor(new javax.swing.JSpinner.NumberEditor(helipadLengthSpinner, "0.0"));
        helipadLengthSpinner.setValue(new Double(SettingsEngine.getInstance().getHelipadLength()));
        helipadLengthComboBox = new JComboBox(new String[] {
            "M", "F"
        });
        helipadLengthComboBox.setFont(Utilities.COMBO_BOX_FONT);
        helipadLengthComboBox.setForeground(Color.black);
        helipadLengthComboBox.setSelectedItem(SettingsEngine.getInstance().getHelipadLengthMeasure());
        if(SettingsEngine.getInstance().getLAF().equals("Windows"))
            helipadLengthComboBox.setPrototypeDisplayValue("--------");
        JLabel helipadWidthLabel = new JLabel("Helipad Width:");
        helipadWidthLabel.setFont(Utilities.LABEL_FONT);
        helipadWidthLabel.setForeground(Color.black);
        helipadWidthSpinner = new JSpinner(new SpinnerNumberModel(2D, 2D, 10000D, 1.0D));
        helipadWidthSpinner.setEditor(new javax.swing.JSpinner.NumberEditor(helipadWidthSpinner, "0.0"));
        helipadWidthSpinner.setValue(new Double(SettingsEngine.getInstance().getHelipadWidth()));
        helipadWidthComboBox = new JComboBox(new String[] {
            "M", "F"
        });
        helipadWidthComboBox.setFont(Utilities.COMBO_BOX_FONT);
        helipadWidthComboBox.setForeground(Color.black);
        helipadWidthComboBox.setSelectedItem(SettingsEngine.getInstance().getHelipadWidthMeasure());
        if(SettingsEngine.getInstance().getLAF().equals("Windows"))
            helipadWidthComboBox.setPrototypeDisplayValue("--------");
        JLabel taxiwayPointLabel = new JLabel("Taxiway Point Diameter:");
        taxiwayPointLabel.setFont(Utilities.LABEL_FONT);
        taxiwayPointLabel.setForeground(Color.black);
        taxiwayPointSpinner = new JSpinner(new SpinnerNumberModel(2D, 2D, 10000D, 1.0D));
        taxiwayPointSpinner.setEditor(new javax.swing.JSpinner.NumberEditor(taxiwayPointSpinner, "0.0"));
        taxiwayPointSpinner.setValue(new Double(SettingsEngine.getInstance().getTaxiwayPointDiameter()));
        taxiwayPointComboBox = new JComboBox(new String[] {
            "M", "F"
        });
        taxiwayPointComboBox.setFont(Utilities.COMBO_BOX_FONT);
        taxiwayPointComboBox.setForeground(Color.black);
        taxiwayPointComboBox.setSelectedItem(SettingsEngine.getInstance().getTaxiwayPointMeasure());
        if(SettingsEngine.getInstance().getLAF().equals("Windows"))
            taxiwayPointComboBox.setPrototypeDisplayValue("--------");
        JLabel vertexPointLabel = new JLabel("Vertex Point Diameter:");
        vertexPointLabel.setFont(Utilities.LABEL_FONT);
        vertexPointLabel.setForeground(Color.black);
        vertexPointSpinner = new JSpinner(new SpinnerNumberModel(2D, 2D, 10000D, 1.0D));
        vertexPointSpinner.setEditor(new javax.swing.JSpinner.NumberEditor(vertexPointSpinner, "0.0"));
        vertexPointSpinner.setValue(new Double(SettingsEngine.getInstance().getVertexPointDiameter()));
        vertexPointComboBox = new JComboBox(new String[] {
            "M", "F"
        });
        vertexPointComboBox.setFont(Utilities.COMBO_BOX_FONT);
        vertexPointComboBox.setForeground(Color.black);
        vertexPointComboBox.setSelectedItem(SettingsEngine.getInstance().getVertexPointMeasure());
        if(SettingsEngine.getInstance().getLAF().equals("Windows"))
            vertexPointComboBox.setPrototypeDisplayValue("--------");
        JPanel defaultPanel = new JPanel(new GridBagLayout());
        defaultPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        Utilities.addComponent(defaultPanel, parkingLabel, 0, 0, 1, 1, 0, 13, new Insets(5, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(defaultPanel, parkingSpinner, 1, 0, 1, 1, 0, 17, new Insets(5, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(defaultPanel, parkingComboBox, 2, 0, 1, 1, 0, 17, new Insets(5, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(defaultPanel, tee1Label, 0, 1, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(defaultPanel, tee1Spinner, 1, 1, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(defaultPanel, tee2Label, 0, 2, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(defaultPanel, tee2Spinner, 1, 2, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(defaultPanel, tee3Label, 0, 3, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(defaultPanel, tee3Spinner, 1, 3, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(defaultPanel, tee4Label, 0, 4, 1, 1, 0, 13, new Insets(1, 1, 10, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(defaultPanel, tee4Spinner, 1, 4, 1, 1, 0, 17, new Insets(1, 1, 10, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(defaultPanel, taxiwayLabel, 0, 5, 1, 1, 0, 13, new Insets(5, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(defaultPanel, taxiwaySpinner, 1, 5, 1, 1, 0, 17, new Insets(5, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(defaultPanel, taxiwayComboBox, 2, 5, 1, 1, 0, 17, new Insets(5, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(defaultPanel, taxiWeightLabel, 0, 6, 1, 1, 0, 13, new Insets(1, 1, 10, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(defaultPanel, taxiWeightSpinner, 1, 6, 2, 1, 0, 17, new Insets(1, 1, 10, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(defaultPanel, taxiwayPointLabel, 0, 7, 1, 1, 0, 13, new Insets(5, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(defaultPanel, taxiwayPointSpinner, 1, 7, 1, 1, 0, 17, new Insets(5, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(defaultPanel, taxiwayPointComboBox, 2, 7, 1, 1, 0, 17, new Insets(5, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(defaultPanel, vertexPointLabel, 0, 8, 1, 1, 0, 13, new Insets(1, 1, 10, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(defaultPanel, vertexPointSpinner, 1, 8, 1, 1, 0, 17, new Insets(1, 1, 10, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(defaultPanel, vertexPointComboBox, 2, 8, 1, 1, 0, 17, new Insets(1, 1, 10, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(defaultPanel, helipadLengthLabel, 0, 9, 1, 1, 0, 13, new Insets(5, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(defaultPanel, helipadLengthSpinner, 1, 9, 1, 1, 0, 17, new Insets(5, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(defaultPanel, helipadLengthComboBox, 2, 9, 1, 1, 0, 17, new Insets(5, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(defaultPanel, helipadWidthLabel, 0, 10, 1, 1, 0, 13, new Insets(1, 1, 10, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(defaultPanel, helipadWidthSpinner, 1, 10, 1, 1, 0, 17, new Insets(1, 1, 10, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(defaultPanel, helipadWidthComboBox, 2, 10, 1, 1, 0, 17, new Insets(1, 1, 10, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(defaultPanel, Box.createGlue(), 0, 11, 3, 1, 1, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.5D);
        JLabel runwayLengthLabel = new JLabel("Runway Length:");
        runwayLengthLabel.setFont(Utilities.LABEL_FONT);
        runwayLengthLabel.setForeground(Color.black);
        runwayLengthSpinner = new JSpinner(new SpinnerNumberModel(2D, 2D, 10000D, 1.0D));
        runwayLengthSpinner.setEditor(new javax.swing.JSpinner.NumberEditor(runwayLengthSpinner, "0.0"));
        runwayLengthSpinner.setValue(new Double(SettingsEngine.getInstance().getRunwayLength()));
        runwayLengthComboBox = new JComboBox(new String[] {
            "M", "F"
        });
        runwayLengthComboBox.setFont(Utilities.COMBO_BOX_FONT);
        runwayLengthComboBox.setForeground(Color.black);
        runwayLengthComboBox.setSelectedItem(SettingsEngine.getInstance().getRunwayLengthMeasure());
        if(SettingsEngine.getInstance().getLAF().equals("Windows"))
            runwayLengthComboBox.setPrototypeDisplayValue("--------");
        JLabel runwayWidthLabel = new JLabel("Runway Width:");
        runwayWidthLabel.setFont(Utilities.LABEL_FONT);
        runwayWidthLabel.setForeground(Color.black);
        runwayWidthSpinner = new JSpinner(new SpinnerNumberModel(2D, 2D, 10000D, 1.0D));
        runwayWidthSpinner.setEditor(new javax.swing.JSpinner.NumberEditor(runwayWidthSpinner, "0.0"));
        runwayWidthSpinner.setValue(new Double(SettingsEngine.getInstance().getRunwayWidth()));
        runwayWidthComboBox = new JComboBox(new String[] {
            "M", "F"
        });
        runwayWidthComboBox.setFont(Utilities.COMBO_BOX_FONT);
        runwayWidthComboBox.setForeground(Color.black);
        runwayWidthComboBox.setSelectedItem(SettingsEngine.getInstance().getRunwayWidthMeasure());
        if(SettingsEngine.getInstance().getLAF().equals("Windows"))
            runwayWidthComboBox.setPrototypeDisplayValue("--------");
        JLabel vasiXLabel = new JLabel("VASI Bias X:");
        vasiXLabel.setFont(Utilities.LABEL_FONT);
        vasiXLabel.setForeground(Color.black);
        vasiXSpinner = new JSpinner(new SpinnerNumberModel(0.0D, 0.0D, 100000D, 1.0D));
        vasiXSpinner.setEditor(new javax.swing.JSpinner.NumberEditor(vasiXSpinner, "0.000"));
        vasiXSpinner.setValue(new Double(SettingsEngine.getInstance().getVasiX()));
        vasiXComboBox = new JComboBox(new String[] {
            "M", "F"
        });
        vasiXComboBox.setFont(Utilities.COMBO_BOX_FONT);
        vasiXComboBox.setForeground(Color.black);
        vasiXComboBox.setSelectedItem(SettingsEngine.getInstance().getVasiXMeasure());
        if(SettingsEngine.getInstance().getLAF().equals("Windows"))
            vasiXComboBox.setPrototypeDisplayValue("--------");
        JLabel vasiZLabel = new JLabel("VASI Bias Z:");
        vasiZLabel.setFont(Utilities.LABEL_FONT);
        vasiZLabel.setForeground(Color.black);
        vasiZSpinner = new JSpinner(new SpinnerNumberModel(0.0D, 0.0D, 100000D, 1.0D));
        vasiZSpinner.setEditor(new javax.swing.JSpinner.NumberEditor(vasiZSpinner, "0.000"));
        vasiZSpinner.setValue(new Double(SettingsEngine.getInstance().getVasiZ()));
        vasiZComboBox = new JComboBox(new String[] {
            "M", "F"
        });
        vasiZComboBox.setFont(Utilities.COMBO_BOX_FONT);
        vasiZComboBox.setForeground(Color.black);
        vasiZComboBox.setSelectedItem(SettingsEngine.getInstance().getVasiZMeasure());
        if(SettingsEngine.getInstance().getLAF().equals("Windows"))
            vasiZComboBox.setPrototypeDisplayValue("--------");
        vasiZEndComboBox = new JComboBox(new String[] {
            "from center", "from end"
        });
        vasiZEndComboBox.setFont(Utilities.COMBO_BOX_FONT);
        vasiZEndComboBox.setForeground(Color.black);
        vasiZEndComboBox.setSelectedItem(SettingsEngine.getInstance().getVasiZEnd());
        if(SettingsEngine.getInstance().getLAF().equals("Windows"))
            vasiZEndComboBox.setPrototypeDisplayValue("------------------------");
        JLabel vasiSpacingLabel = new JLabel("VASI Spacing:");
        vasiSpacingLabel.setFont(Utilities.LABEL_FONT);
        vasiSpacingLabel.setForeground(Color.black);
        vasiSpacingSpinner = new JSpinner(new SpinnerNumberModel(0.0D, 0.0D, 100000D, 1.0D));
        vasiSpacingSpinner.setEditor(new javax.swing.JSpinner.NumberEditor(vasiSpacingSpinner, "0.000"));
        vasiSpacingSpinner.setValue(new Double(SettingsEngine.getInstance().getVasiSpacing()));
        vasiSpacingComboBox = new JComboBox(new String[] {
            "M", "F"
        });
        vasiSpacingComboBox.setFont(Utilities.COMBO_BOX_FONT);
        vasiSpacingComboBox.setForeground(Color.black);
        vasiSpacingComboBox.setSelectedItem(SettingsEngine.getInstance().getVasiSpacingMeasure());
        if(SettingsEngine.getInstance().getLAF().equals("Windows"))
            vasiSpacingComboBox.setPrototypeDisplayValue("--------");
        JLabel offsetLabel = new JLabel("Offset Threshold / Blast Pad / Overrun Defaults:");
        offsetLabel.setFont(Utilities.BOLD_LABEL_FONT);
        offsetLabel.setForeground(Color.black);
        JLabel offsetLengthLabel = new JLabel("Length");
        offsetLengthLabel.setFont(Utilities.LABEL_FONT);
        offsetLengthLabel.setForeground(Color.black);
        offsetLengthSpinner = new JSpinner(new SpinnerNumberModel(2D, 2D, 10000D, 1.0D));
        offsetLengthSpinner.setEditor(new javax.swing.JSpinner.NumberEditor(offsetLengthSpinner, "0.000"));
        offsetLengthSpinner.setValue(new Double(SettingsEngine.getInstance().getOffsetLength()));
        offsetLengthComboBox = new JComboBox(new String[] {
            "M", "F"
        });
        offsetLengthComboBox.setFont(Utilities.COMBO_BOX_FONT);
        offsetLengthComboBox.setForeground(Color.black);
        offsetLengthComboBox.setSelectedItem(SettingsEngine.getInstance().getOffsetLengthMeasure());
        if(SettingsEngine.getInstance().getLAF().equals("Windows"))
            offsetLengthComboBox.setPrototypeDisplayValue("--------");
        offsetWidthRunwayRB = new JRadioButton("Same Width As Runway", SettingsEngine.getInstance().getOffsetWidthRunway());
        offsetWidthRunwayRB.setFont(Utilities.LABEL_FONT);
        offsetWidthRunwayRB.setForeground(Color.black);
        offsetWidthRunwayRB.addActionListener(this);
        offsetWidthSpecificRB = new JRadioButton("Specific Width", !SettingsEngine.getInstance().getOffsetWidthRunway());
        offsetWidthSpecificRB.setFont(Utilities.LABEL_FONT);
        offsetWidthSpecificRB.setForeground(Color.black);
        offsetWidthSpecificRB.addActionListener(this);
        ButtonGroup widthBG = new ButtonGroup();
        widthBG.add(offsetWidthSpecificRB);
        widthBG.add(offsetWidthRunwayRB);
        offsetWidthSpinner = new JSpinner(new SpinnerNumberModel(2D, 2D, 10000D, 1.0D));
        offsetWidthSpinner.setEditor(new javax.swing.JSpinner.NumberEditor(offsetWidthSpinner, "0.000"));
        offsetWidthSpinner.setValue(new Double(SettingsEngine.getInstance().getOffsetWidth()));
        offsetWidthSpinner.setEnabled(!SettingsEngine.getInstance().getOffsetWidthRunway());
        offsetWidthComboBox = new JComboBox(new String[] {
            "M", "F"
        });
        offsetWidthComboBox.setFont(Utilities.COMBO_BOX_FONT);
        offsetWidthComboBox.setForeground(Color.black);
        offsetWidthComboBox.setEnabled(!SettingsEngine.getInstance().getOffsetWidthRunway());
        offsetWidthComboBox.setSelectedItem(SettingsEngine.getInstance().getOffsetWidthMeasure());
        if(SettingsEngine.getInstance().getLAF().equals("Windows"))
            offsetWidthComboBox.setPrototypeDisplayValue("--------");
        offsetSurfaceRunwayRB = new JRadioButton("Same Surface As Runway", SettingsEngine.getInstance().getOffsetSurfaceRunway());
        offsetSurfaceRunwayRB.setFont(Utilities.LABEL_FONT);
        offsetSurfaceRunwayRB.setForeground(Color.black);
        offsetSurfaceRunwayRB.addActionListener(this);
        offsetSurfaceSpecificRB = new JRadioButton("Specific Surface", !SettingsEngine.getInstance().getOffsetSurfaceRunway());
        offsetSurfaceSpecificRB.setFont(Utilities.LABEL_FONT);
        offsetSurfaceSpecificRB.setForeground(Color.black);
        offsetSurfaceSpecificRB.addActionListener(this);
        ButtonGroup surfaceBG = new ButtonGroup();
        surfaceBG.add(offsetSurfaceRunwayRB);
        surfaceBG.add(offsetSurfaceSpecificRB);
        offsetSurfaceComboBox = new JComboBox(getSurfaceModel());
        offsetSurfaceComboBox.setFont(Utilities.COMBO_BOX_FONT);
        offsetSurfaceComboBox.setForeground(Color.black);
        offsetSurfaceComboBox.setEnabled(!SettingsEngine.getInstance().getOffsetSurfaceRunway());
        offsetSurfaceComboBox.setSelectedItem(SettingsEngine.getInstance().getOffsetSurface());
        JPanel runwayPanel = new JPanel(new GridBagLayout());
        runwayPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        Utilities.addComponent(runwayPanel, runwayLengthLabel, 0, 0, 1, 1, 0, 13, new Insets(5, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(runwayPanel, runwayLengthSpinner, 1, 0, 1, 1, 2, 17, new Insets(5, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(runwayPanel, runwayLengthComboBox, 2, 0, 1, 1, 0, 17, new Insets(5, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(runwayPanel, runwayWidthLabel, 0, 1, 1, 1, 0, 13, new Insets(1, 1, 10, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(runwayPanel, runwayWidthSpinner, 1, 1, 1, 1, 2, 17, new Insets(1, 1, 10, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(runwayPanel, runwayWidthComboBox, 2, 1, 1, 1, 0, 17, new Insets(1, 1, 10, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(runwayPanel, vasiXLabel, 0, 2, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(runwayPanel, vasiXSpinner, 1, 2, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(runwayPanel, vasiXComboBox, 2, 2, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(runwayPanel, vasiZLabel, 0, 3, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(runwayPanel, vasiZSpinner, 1, 3, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(runwayPanel, vasiZComboBox, 2, 3, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(runwayPanel, vasiZEndComboBox, 3, 3, 1, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(runwayPanel, vasiSpacingLabel, 0, 4, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(runwayPanel, vasiSpacingSpinner, 1, 4, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(runwayPanel, vasiSpacingComboBox, 2, 4, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(runwayPanel, offsetLabel, 0, 5, 4, 1, 0, 17, new Insets(10, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(runwayPanel, offsetLengthLabel, 0, 6, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(runwayPanel, offsetLengthSpinner, 1, 6, 1, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(runwayPanel, offsetLengthComboBox, 2, 6, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(runwayPanel, offsetWidthSpecificRB, 0, 7, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(runwayPanel, offsetWidthSpinner, 1, 7, 1, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(runwayPanel, offsetWidthComboBox, 2, 7, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(runwayPanel, offsetWidthRunwayRB, 0, 8, 4, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(runwayPanel, offsetSurfaceSpecificRB, 0, 9, 1, 1, 0, 13, new Insets(5, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(runwayPanel, offsetSurfaceComboBox, 1, 9, 2, 1, 2, 17, new Insets(5, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(runwayPanel, offsetSurfaceRunwayRB, 0, 10, 4, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(runwayPanel, Box.createGlue(), 0, 11, 3, 1, 1, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.5D);
        colorTable = new JTable(new ColorPrefsTableModel());
        colorTable.setPreferredScrollableViewportSize(new Dimension(200, 200));
        colorTable.setDefaultRenderer(java.awt.Color.class, new ColorPrefsTableRenderer());
        colorTable.setRowHeight(30);
        colorTable.setIntercellSpacing(new Dimension(5, 5));
        colorTable.addMouseListener(this);
        JScrollPane colorSP = new JScrollPane(colorTable);
        JLabel fontLabel = new JLabel("Choose Font For:");
        fontLabel.setFont(Utilities.LABEL_FONT);
        fontLabel.setForeground(Color.black);
        fontList = new JList(new String[] {
            "Menus", "Map Lat/Lons", "Parking Spaces", "Jetways", "ILS", "Scenery", "VORs", "NDBs"
        });
        fontList.setFont(Utilities.DIALOG_LABEL_FONT);
        fontList.setForeground(Color.black);
        fontList.setVisibleRowCount(8);
        fontList.setSelectedIndex(0);
        fontList.setSelectionMode(0);
        fontList.addListSelectionListener(this);
        previousFontIndex = 0;
        JScrollPane fontSP = new JScrollPane(fontList);
        textSampleLabel = new JLabel("Font Sample", 0);
        textSampleLabel.setFont(Utilities.MENU_FONT);
        textSampleLabel.setForeground(Color.black);
        JLabel textFontLabel = new JLabel("Font:");
        textFontLabel.setFont(Utilities.LABEL_FONT);
        textFontLabel.setForeground(Color.black);
        String fontFamilies[] = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        Arrays.sort(fontFamilies);
        DefaultComboBoxModel textFontComboBoxModel = new DefaultComboBoxModel(fontFamilies);
        textFontComboBox = new JComboBox(textFontComboBoxModel);
        textFontComboBox.setSelectedItem(Utilities.MENU_FONT.getFamily());
        textFontComboBox.setFont(Utilities.COMBO_BOX_FONT);
        textFontComboBox.addActionListener(this);
        Box textFontBox = Box.createHorizontalBox();
        textFontBox.add(textFontLabel);
        textFontBox.add(Box.createHorizontalStrut(5));
        textFontBox.add(textFontComboBox);
        JLabel textSizeLabel = new JLabel("Size: ");
        textSizeLabel.setFont(Utilities.LABEL_FONT);
        textSizeLabel.setForeground(Color.black);
        textSizeTF = new JTextField((new StringBuilder()).append("").append(Utilities.MENU_FONT.getSize()).toString(), 5);
        textSizeTF.setFont(Utilities.TEXT_FIELD_FONT);
        textSizeTF.setForeground(Color.black);
        textSizeTF.setMaximumSize(textSizeTF.getPreferredSize());
        textSizeTF.addCaretListener(this);
        Box textSizeBox = Box.createHorizontalBox();
        textSizeBox.setAlignmentX(0.0F);
        textSizeBox.add(textSizeLabel);
        textSizeBox.add(textSizeTF);
        JLabel textStyleLabel = new JLabel("Style:");
        textStyleLabel.setFont(Utilities.LABEL_FONT);
        textStyleLabel.setForeground(Color.black);
        textBoldCB = new JCheckBox("Bold");
        textBoldCB.setFont(new Font(Utilities.LABEL_FONT.getName(), 1, 12));
        textBoldCB.setForeground(Color.black);
        textBoldCB.setSelected((Utilities.MENU_FONT.getStyle() & 1) != 0);
        textBoldCB.setActionCommand("1");
        textBoldCB.addChangeListener(this);
        textItalicsCB = new JCheckBox("Italics");
        textItalicsCB.setFont(new Font(Utilities.LABEL_FONT.getName(), 2, 12));
        textItalicsCB.setForeground(Color.black);
        textItalicsCB.setSelected((Utilities.MENU_FONT.getStyle() & 2) != 0);
        textItalicsCB.setActionCommand("2");
        textItalicsCB.addChangeListener(this);
        Box textStyleBox = Box.createHorizontalBox();
        textStyleBox.add(textStyleLabel);
        textStyleBox.add(Box.createHorizontalStrut(5));
        textStyleBox.add(textBoldCB);
        textStyleBox.add(textItalicsCB);
        JPanel fontPanel = new JPanel(new GridBagLayout());
        fontPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        Utilities.addComponent(fontPanel, fontLabel, 0, 0, 1, 1, 0, 17, new Insets(1, 1, 5, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(fontPanel, fontSP, 0, 1, 1, 4, 0, 11, new Insets(1, 1, 5, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(fontPanel, Box.createGlue(), 1, 0, 1, 5, 2, 17, new Insets(1, 1, 5, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(fontPanel, textFontBox, 2, 1, 1, 1, 0, 17, new Insets(1, 1, 5, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(fontPanel, textSizeBox, 2, 2, 1, 1, 0, 17, new Insets(1, 1, 5, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(fontPanel, textStyleBox, 2, 3, 1, 1, 0, 17, new Insets(1, 1, 5, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(fontPanel, textSampleLabel, 2, 4, 1, 1, 0, 11, new Insets(10, 1, 5, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(fontPanel, Box.createGlue(), 0, 5, 3, 1, 1, 10, new Insets(1, 1, 5, 1), 0, 0, 0.5D, 0.5D);
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.add(displayPanel, "Display");
        tabbedPane.add(optionsPanel, "CAD Options");
        tabbedPane.add(appOptionsPanel, "App Options");
        tabbedPane.add(defaultPanel, "Defaults");
        tabbedPane.add(runwayPanel, "Runway");
        tabbedPane.add(colorSP, "Colors");
        tabbedPane.add(fontPanel, "Fonts");
        getContentPane().add(tabbedPane, "Center");
        okButton = new JButton("OK");
        okButton.setFont(Utilities.DIALOG_BUTTON_FONT);
        okButton.setForeground(Color.black);
        okButton.addActionListener(this);
        cancelButton = new JButton("Cancel");
        cancelButton.setFont(Utilities.DIALOG_BUTTON_FONT);
        cancelButton.setForeground(Color.black);
        cancelButton.addActionListener(this);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);
        getContentPane().add(buttonPanel, "South");
        pack();
        setLocationRelativeTo(parent);
    }

    public static void showDialog(Frame parent)
    {
        if(dialog == null)
            dialog = new PreferencesDialog(parent);
        dialog.setVisible(true);
    }

    private void updatePreferences()
    {
        SettingsEngine.getInstance().setLatLonDisplay(latLonComboBox.getSelectedIndex());
        SettingsEngine.getInstance().setLatLonGridDisplay(latLonGridComboBox.getSelectedIndex());
        SettingsEngine.getInstance().setLatLonObjectDisplay(latLonObjectComboBox.getSelectedIndex());
        SettingsEngine.getInstance().setSecondsIncrement((coordComboBox.getSelectedIndex() + 1) * 5);
        SettingsEngine.getInstance().setLAF((String)lafComboBox.getSelectedItem());
        SettingsEngine.getInstance().setMaxHistoryItems(((Integer)historySpinner.getValue()).intValue());
        SettingsEngine.getInstance().setSelectedItemOutlined(selectedComboBox.getSelectedIndex() == 0);
        SettingsEngine.getInstance().setBGImagesBelow(backgroundComboBox.getSelectedIndex() == 0);
        SettingsEngine.getInstance().setBGImageOpacity(((Integer)bgOpacitySpinner.getValue()).intValue());
        SettingsEngine.getInstance().setUseAirportAlt(altitudeCB.isSelected());
        SettingsEngine.getInstance().setAdjustMeasurements(adjustMeasureCB.isSelected());
        SettingsEngine.getInstance().setDrawTaxiwayPaths(drawPathsCB.isSelected());
        SettingsEngine.getInstance().setUpdateJetways(jetwayCB.isSelected());
        SettingsEngine.getInstance().setTaxiwayProperties(taxiwayPropertiesCB.isSelected());
        SettingsEngine.getInstance().setTaxiwayRunways(taxiwayRunwayCB.isSelected());
        SettingsEngine.getInstance().setEdgeLightLines(edgeLightCB.isSelected());
        SettingsEngine.getInstance().setRestoreWindow(restoreWindowCB.isSelected());
        SettingsEngine.getInstance().setClearCompile(clearCompileCB.isSelected());
        SettingsEngine.getInstance().setXMLPerLine(xmlCB.isSelected());
        SettingsEngine.getInstance().setWriteDeletes(deletesCB.isSelected());
        SettingsEngine.getInstance().setWriteExcludes(excludesCB.isSelected());
        SettingsEngine.getInstance().setWriteBGImages(bgImageCB.isSelected());
        SettingsEngine.getInstance().setReadNav(readNavCB.isSelected());
        SettingsEngine.getInstance().setReadScenery(readSceneryCB.isSelected());
        SettingsEngine.getInstance().setDisplayMemory(memoryCB.isSelected());
        SettingsEngine.getInstance().setDisplayFPS(fpsCB.isSelected());
        SettingsEngine.getInstance().setDoubleBuffer(bufferCB.isSelected());
        SettingsEngine.getInstance().setRibbonMenus(ribbonCB.isSelected());
        SettingsEngine.getInstance().setParkingSize(((Double)parkingSpinner.getValue()).floatValue());
        SettingsEngine.getInstance().setParkingMeasure((String)parkingComboBox.getSelectedItem());
        SettingsEngine.getInstance().setTee1Offset(((Double)tee1Spinner.getValue()).floatValue());
        SettingsEngine.getInstance().setTee2Offset(((Double)tee2Spinner.getValue()).floatValue());
        SettingsEngine.getInstance().setTee3Offset(((Double)tee3Spinner.getValue()).floatValue());
        SettingsEngine.getInstance().setTee4Offset(((Double)tee4Spinner.getValue()).floatValue());
        SettingsEngine.getInstance().setTaxiwayWidth(((Double)taxiwaySpinner.getValue()).floatValue());
        SettingsEngine.getInstance().setTaxiwayMeasure((String)taxiwayComboBox.getSelectedItem());
        SettingsEngine.getInstance().setTaxiwayWeight(((Double)taxiWeightSpinner.getValue()).floatValue());
        SettingsEngine.getInstance().setHelipadLength(((Double)helipadLengthSpinner.getValue()).floatValue());
        SettingsEngine.getInstance().setHelipadLengthMeasure((String)helipadLengthComboBox.getSelectedItem());
        SettingsEngine.getInstance().setHelipadWidth(((Double)helipadWidthSpinner.getValue()).floatValue());
        SettingsEngine.getInstance().setHelipadWidthMeasure((String)helipadWidthComboBox.getSelectedItem());
        SettingsEngine.getInstance().setTaxiwayPointDiameter(((Double)taxiwayPointSpinner.getValue()).floatValue());
        SettingsEngine.getInstance().setTaxiwayPointMeasure((String)taxiwayPointComboBox.getSelectedItem());
        SettingsEngine.getInstance().setVertexPointDiameter(((Double)vertexPointSpinner.getValue()).floatValue());
        SettingsEngine.getInstance().setVertexPointMeasure((String)vertexPointComboBox.getSelectedItem());
        SettingsEngine.getInstance().setRunwayLength(((Double)runwayLengthSpinner.getValue()).floatValue());
        SettingsEngine.getInstance().setRunwayLengthMeasure((String)runwayLengthComboBox.getSelectedItem());
        SettingsEngine.getInstance().setRunwayWidth(((Double)runwayWidthSpinner.getValue()).floatValue());
        SettingsEngine.getInstance().setRunwayWidthMeasure((String)runwayWidthComboBox.getSelectedItem());
        SettingsEngine.getInstance().setVasiX(((Double)vasiXSpinner.getValue()).floatValue());
        SettingsEngine.getInstance().setVasiXMeasure((String)vasiXComboBox.getSelectedItem());
        SettingsEngine.getInstance().setVasiZ(((Double)vasiZSpinner.getValue()).floatValue());
        SettingsEngine.getInstance().setVasiZMeasure((String)vasiZComboBox.getSelectedItem());
        SettingsEngine.getInstance().setVasiZEnd((String)vasiZEndComboBox.getSelectedItem());
        SettingsEngine.getInstance().setVasiSpacing(((Double)vasiSpacingSpinner.getValue()).floatValue());
        SettingsEngine.getInstance().setVasiSpacingMeasure((String)vasiSpacingComboBox.getSelectedItem());
        SettingsEngine.getInstance().setOffsetLength(((Double)offsetLengthSpinner.getValue()).floatValue());
        SettingsEngine.getInstance().setOffsetLengthMeasure((String)offsetLengthComboBox.getSelectedItem());
        SettingsEngine.getInstance().setOffsetWidthRunway(offsetWidthRunwayRB.isSelected());
        SettingsEngine.getInstance().setOffsetWidth(((Double)offsetWidthSpinner.getValue()).floatValue());
        SettingsEngine.getInstance().setOffsetWidthMeasure((String)offsetWidthComboBox.getSelectedItem());
        SettingsEngine.getInstance().setOffsetSurfaceRunway(offsetSurfaceRunwayRB.isSelected());
        SettingsEngine.getInstance().setOffsetSurface((String)offsetSurfaceComboBox.getSelectedItem());
        SettingsEngine.getInstance().writePreferences();
        ColorsEngine.getInstance().setSurfaceColor("ASPHALT", (Color)colorTable.getValueAt(0, 1));
        ColorsEngine.getInstance().setSurfaceColor("BITUMINOUS", (Color)colorTable.getValueAt(1, 1));
        ColorsEngine.getInstance().setSurfaceColor("BRICK", (Color)colorTable.getValueAt(2, 1));
        ColorsEngine.getInstance().setSurfaceColor("CLAY", (Color)colorTable.getValueAt(3, 1));
        ColorsEngine.getInstance().setSurfaceColor("CEMENT", (Color)colorTable.getValueAt(4, 1));
        ColorsEngine.getInstance().setSurfaceColor("CONCRETE", (Color)colorTable.getValueAt(5, 1));
        ColorsEngine.getInstance().setSurfaceColor("CORAL", (Color)colorTable.getValueAt(6, 1));
        ColorsEngine.getInstance().setSurfaceColor("DIRT", (Color)colorTable.getValueAt(7, 1));
        ColorsEngine.getInstance().setSurfaceColor("GRASS", (Color)colorTable.getValueAt(8, 1));
        ColorsEngine.getInstance().setSurfaceColor("GRAVEL", (Color)colorTable.getValueAt(9, 1));
        ColorsEngine.getInstance().setSurfaceColor("ICE", (Color)colorTable.getValueAt(10, 1));
        ColorsEngine.getInstance().setSurfaceColor("MACADAM", (Color)colorTable.getValueAt(11, 1));
        ColorsEngine.getInstance().setSurfaceColor("OIL_TREATED, PLANKS", (Color)colorTable.getValueAt(12, 1));
        ColorsEngine.getInstance().setSurfaceColor("SAND", (Color)colorTable.getValueAt(13, 1));
        ColorsEngine.getInstance().setSurfaceColor("SHALE", (Color)colorTable.getValueAt(14, 1));
        ColorsEngine.getInstance().setSurfaceColor("SNOW", (Color)colorTable.getValueAt(15, 1));
        ColorsEngine.getInstance().setSurfaceColor("STEEL_MATS", (Color)colorTable.getValueAt(16, 1));
        ColorsEngine.getInstance().setSurfaceColor("TARMAC", (Color)colorTable.getValueAt(17, 1));
        ColorsEngine.getInstance().setSurfaceColor("UNKNOWN", (Color)colorTable.getValueAt(18, 1));
        ColorsEngine.getInstance().setSurfaceColor("WATER", (Color)colorTable.getValueAt(19, 1));
        ColorsEngine.getInstance().setLatLonColor((Color)colorTable.getValueAt(20, 1));
        ColorsEngine.getInstance().setBackgroundColor((Color)colorTable.getValueAt(21, 1));
        ColorsEngine.getInstance().setNightColor((Color)colorTable.getValueAt(22, 1));
        ColorsEngine.getInstance().setTaxiwayLineColor((Color)colorTable.getValueAt(23, 1));
        ColorsEngine.getInstance().setILSColor((Color)colorTable.getValueAt(24, 1));
        ColorsEngine.getInstance().setDMEColor((Color)colorTable.getValueAt(25, 1));
        ColorsEngine.getInstance().setGlideSlopeColor((Color)colorTable.getValueAt(26, 1));
        ColorsEngine.getInstance().setExclusionColor((Color)colorTable.getValueAt(27, 1));
        ColorsEngine.getInstance().setTriggerColor((Color)colorTable.getValueAt(28, 1));
        ColorsEngine.getInstance().setSceneryColor((Color)colorTable.getValueAt(29, 1));
        ColorsEngine.getInstance().setSelectedColor((Color)colorTable.getValueAt(30, 1));
        ColorsEngine.getInstance().writePreferences();
        Utilities.MENU_FONT = tempFonts[0];
        FontEngine.getInstance().setMenuFont(Utilities.MENU_FONT);
        Utilities.LAT_LON_FONT = tempFonts[1];
        FontEngine.getInstance().setLatLonFont(Utilities.LAT_LON_FONT);
        Utilities.PARKING_FONT = tempFonts[2];
        FontEngine.getInstance().setParkingFont(Utilities.PARKING_FONT);
        Utilities.JETWAY_FONT = tempFonts[3];
        FontEngine.getInstance().setJetwayFont(Utilities.JETWAY_FONT);
        Utilities.ILS_FONT = tempFonts[4];
        FontEngine.getInstance().setILSFont(Utilities.ILS_FONT);
        Utilities.SCENERY_FONT = tempFonts[5];
        FontEngine.getInstance().setSceneryFont(Utilities.SCENERY_FONT);
        Utilities.VOR_FONT = tempFonts[6];
        FontEngine.getInstance().setVORFont(Utilities.VOR_FONT);
        Utilities.NDB_FONT = tempFonts[7];
        FontEngine.getInstance().setNDBFont(Utilities.NDB_FONT);
        FontEngine.getInstance().writePreferences();
    }

    private DefaultComboBoxModel getSurfaceModel()
    {
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        model.addElement("ASPHALT");
        model.addElement("BITUMINOUS");
        model.addElement("BRICK");
        model.addElement("CLAY");
        model.addElement("CEMENT");
        model.addElement("CONCRETE");
        model.addElement("CORAL");
        model.addElement("DIRT");
        model.addElement("GRASS");
        model.addElement("GRAVEL");
        model.addElement("ICE");
        model.addElement("MACADAM");
        model.addElement("OIL_TREATED, PLANKS");
        model.addElement("SAND");
        model.addElement("SHALE");
        model.addElement("SNOW");
        model.addElement("STEEL_MATS");
        model.addElement("TARMAC");
        model.addElement("UNKNOWN");
        model.addElement("WATER");
        return model;
    }

    private void selectColor(Point point)
    {
        int row = colorTable.rowAtPoint(point);
        int column = colorTable.columnAtPoint(point);
        if(row == -1)
            return;
        if(column != 1)
            return;
        Color color = JColorChooser.showDialog(this, "Select Color:", (Color)colorTable.getValueAt(row, column));
        if(color != null)
            ((ColorPrefsTableModel)colorTable.getModel()).setColor((String)colorTable.getValueAt(row, 0), color);
    }

    private void updateEditingFont()
    {
        if(previousFontIndex >= 0)
            tempFonts[previousFontIndex] = textSampleLabel.getFont();
        Font font = null;
        if(fontList.getSelectedIndex() >= 0)
            font = tempFonts[fontList.getSelectedIndex()];
        if(font != null)
        {
            textFontComboBox.setSelectedItem(font.getFamily());
            textSizeTF.setText((new StringBuilder()).append("").append(font.getSize()).toString());
            textBoldCB.setSelected((font.getStyle() & 1) != 0);
            textItalicsCB.setSelected((font.getStyle() & 2) != 0);
        }
        previousFontIndex = fontList.getSelectedIndex();
    }

    private void updateTempFont()
    {
        if(fontList.getSelectedIndex() >= 0)
            tempFonts[fontList.getSelectedIndex()] = textSampleLabel.getFont();
    }

    public void actionPerformed(ActionEvent event)
    {
        if(event.getSource() == okButton)
        {
            updatePreferences();
            setVisible(false);
        } else
        if(event.getSource() == cancelButton)
            setVisible(false);
        else
        if(event.getSource() == offsetWidthRunwayRB)
        {
            offsetWidthSpinner.setEnabled(offsetWidthSpecificRB.isSelected());
            offsetWidthComboBox.setEnabled(offsetWidthSpecificRB.isSelected());
        } else
        if(event.getSource() == offsetWidthSpecificRB)
        {
            offsetWidthSpinner.setEnabled(offsetWidthSpecificRB.isSelected());
            offsetWidthComboBox.setEnabled(offsetWidthSpecificRB.isSelected());
        } else
        if(event.getSource() == offsetSurfaceRunwayRB)
            offsetSurfaceComboBox.setEnabled(offsetSurfaceSpecificRB.isSelected());
        else
        if(event.getSource() == offsetSurfaceSpecificRB)
            offsetSurfaceComboBox.setEnabled(offsetSurfaceSpecificRB.isSelected());
        else
        if(event.getSource() == textFontComboBox)
        {
            Font font = textSampleLabel.getFont();
            textSampleLabel.setFont(new Font((String)((JComboBox)event.getSource()).getSelectedItem(), font.getStyle(), font.getSize()));
            updateTempFont();
        }
    }

    public void caretUpdate(CaretEvent event)
    {
        if(event.getSource() == textSizeTF)
        {
            int size = -1;
            try
            {
                size = Integer.parseInt(textSizeTF.getText());
            }
            catch(NumberFormatException nfe)
            {
                size = -1;
            }
            Font font = textSampleLabel.getFont();
            if(size > 0 && size != font.getSize())
            {
                textSampleLabel.setFont(new Font(font.getName(), font.getStyle(), size));
                updateTempFont();
            }
        }
    }

    public void stateChanged(ChangeEvent event)
    {
        if(event.getSource() == textBoldCB || event.getSource() == textItalicsCB)
        {
            Font font = textSampleLabel.getFont();
            int selectedStyle = Integer.parseInt(((JCheckBox)event.getSource()).getActionCommand());
            int newStyle = font.getStyle();
            if(((JCheckBox)event.getSource()).isSelected())
                newStyle |= selectedStyle;
            else
                newStyle &= ~selectedStyle;
            textSampleLabel.setFont(new Font(font.getName(), newStyle, font.getSize()));
            updateTempFont();
        }
    }

    public void valueChanged(ListSelectionEvent event)
    {
        if((event.getSource() == fontList) & (!event.getValueIsAdjusting()))
            updateEditingFont();
    }

    public void mouseClicked(MouseEvent event)
    {
        if(event.getSource() == colorTable && event.getClickCount() == 2)
            selectColor(event.getPoint());
    }

    public void mousePressed(MouseEvent mouseevent)
    {
    }

    public void mouseReleased(MouseEvent mouseevent)
    {
    }

    public void mouseEntered(MouseEvent mouseevent)
    {
    }

    public void mouseExited(MouseEvent mouseevent)
    {
    }

    private JComboBox latLonComboBox;
    private JComboBox latLonGridComboBox;
    private JComboBox latLonObjectComboBox;
    private JComboBox coordComboBox;
    private JSpinner historySpinner;
    private JComboBox selectedComboBox;
    private JComboBox backgroundComboBox;
    private JSpinner bgOpacitySpinner;
    private JComboBox lafComboBox;
    private JCheckBox altitudeCB;
    private JCheckBox adjustMeasureCB;
    private JCheckBox drawPathsCB;
    private JCheckBox jetwayCB;
    private JCheckBox taxiwayPropertiesCB;
    private JCheckBox taxiwayRunwayCB;
    private JCheckBox restoreWindowCB;
    private JCheckBox clearCompileCB;
    private JCheckBox xmlCB;
    private JCheckBox deletesCB;
    private JCheckBox excludesCB;
    private JCheckBox bgImageCB;
    private JCheckBox readNavCB;
    private JCheckBox readSceneryCB;
    private JCheckBox edgeLightCB;
    private JCheckBox memoryCB;
    private JCheckBox fpsCB;
    private JCheckBox bufferCB;
    private JCheckBox ribbonCB;
    private JSpinner parkingSpinner;
    private JComboBox parkingComboBox;
    private JSpinner tee1Spinner;
    private JSpinner tee2Spinner;
    private JSpinner tee3Spinner;
    private JSpinner tee4Spinner;
    private JSpinner taxiwaySpinner;
    private JComboBox taxiwayComboBox;
    private JSpinner taxiWeightSpinner;
    private JSpinner helipadLengthSpinner;
    private JComboBox helipadLengthComboBox;
    private JSpinner helipadWidthSpinner;
    private JComboBox helipadWidthComboBox;
    private JSpinner runwayLengthSpinner;
    private JComboBox runwayLengthComboBox;
    private JSpinner runwayWidthSpinner;
    private JComboBox runwayWidthComboBox;
    private JSpinner taxiwayPointSpinner;
    private JComboBox taxiwayPointComboBox;
    private JSpinner vertexPointSpinner;
    private JComboBox vertexPointComboBox;
    private JSpinner vasiXSpinner;
    private JComboBox vasiXComboBox;
    private JSpinner vasiZSpinner;
    private JComboBox vasiZComboBox;
    private JComboBox vasiZEndComboBox;
    private JSpinner vasiSpacingSpinner;
    private JComboBox vasiSpacingComboBox;
    private JSpinner offsetLengthSpinner;
    private JComboBox offsetLengthComboBox;
    private JSpinner offsetWidthSpinner;
    private JComboBox offsetWidthComboBox;
    private JComboBox offsetSurfaceComboBox;
    private JRadioButton offsetWidthRunwayRB;
    private JRadioButton offsetWidthSpecificRB;
    private JRadioButton offsetSurfaceRunwayRB;
    private JRadioButton offsetSurfaceSpecificRB;
    private JTable colorTable;
    private JList fontList;
    private JTextField textSizeTF;
    private JComboBox textFontComboBox;
    private JCheckBox textBoldCB;
    private JCheckBox textItalicsCB;
    private JLabel textSampleLabel;
    private Font tempFonts[];
    private JButton okButton;
    private JButton cancelButton;
    private int previousFontIndex;
    private static PreferencesDialog dialog = null;

}
