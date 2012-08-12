// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CompileDialog.java

package com.zbluesoftware.fsxp.dialog;

import com.zbluesoftware.fsxp.engine.*;
import com.zbluesoftware.fsxp.model.AirportModel;
import com.zbluesoftware.fsxp.util.Utilities;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.*;
import javax.swing.border.*;

// Referenced classes of package com.zbluesoftware.fsxp.dialog:
//            PrepareDialog

public class CompileDialog extends JDialog
    implements ActionListener
{

    private CompileDialog(Frame parent)
    {
        super(parent, "Compile", true);
        JLabel bglLabel = new JLabel("BglComp.exe Folder:");
        bglLabel.setFont(Utilities.LABEL_FONT);
        bglLabel.setForeground(Color.black);
        bglTF = new JTextField(SettingsEngine.getInstance().getBglCompFolder(), 40);
        bglTF.setFont(Utilities.TEXT_FIELD_FONT);
        bglTF.setForeground(Color.black);
        bglTF.setCaretPosition(0);
        bglButton = new JButton("Browse");
        bglButton.setFont(Utilities.BUTTON_FONT);
        bglButton.setForeground(Color.black);
        bglButton.addActionListener(this);
        JLabel destLabel = new JLabel("Destination Folder:");
        destLabel.setFont(Utilities.LABEL_FONT);
        destLabel.setForeground(Color.black);
        destTF = new JTextField(SettingsEngine.getInstance().getCompDestFolder(), 40);
        destTF.setFont(Utilities.TEXT_FIELD_FONT);
        destTF.setForeground(Color.black);
        destTF.setCaretPosition(0);
        browseButton = new JButton("Browse");
        browseButton.setFont(Utilities.BUTTON_FONT);
        browseButton.setForeground(Color.black);
        browseButton.addActionListener(this);
        sceneryCB = new JCheckBox("Use scenery & texture sub-folders", SettingsEngine.getInstance().getUseSceneryTexture());
        sceneryCB.setFont(Utilities.LABEL_FONT);
        sceneryCB.setForeground(Color.black);
        JLabel bglFileLabel = new JLabel("BGL File Name:");
        bglFileLabel.setFont(Utilities.LABEL_FONT);
        bglFileLabel.setForeground(Color.black);
        bglFileTF = new JTextField(20);
        bglFileTF.setFont(Utilities.TEXT_FIELD_FONT);
        bglFileTF.setForeground(Color.black);
        rememberCB = new JCheckBox("Remember this name", true);
        rememberCB.setFont(Utilities.LABEL_FONT);
        rememberCB.setForeground(Color.black);
        TitledBorder dirBorder = new TitledBorder(" Directories and Files ");
        dirBorder.setTitleFont(Utilities.TITLED_BORDER_FONT);
        dirBorder.setTitleColor(Color.black);
        JPanel dirPanel = new JPanel(new GridBagLayout());
        dirPanel.setBorder(new CompoundBorder(dirBorder, new EmptyBorder(5, 5, 5, 5)));
        Utilities.addComponent(dirPanel, bglLabel, 0, 0, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(dirPanel, bglTF, 1, 0, 1, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(dirPanel, bglButton, 2, 0, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(dirPanel, destLabel, 0, 1, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(dirPanel, destTF, 1, 1, 1, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(dirPanel, browseButton, 2, 1, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(dirPanel, sceneryCB, 1, 2, 2, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(dirPanel, bglFileLabel, 0, 3, 1, 1, 0, 13, new Insets(10, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(dirPanel, bglFileTF, 1, 3, 1, 1, 2, 17, new Insets(10, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(dirPanel, rememberCB, 2, 3, 1, 1, 0, 17, new Insets(10, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        JLabel optionsLabel = new JLabel("You can specify what is compiled into the BGL file by using the Prepare Options dialog.");
        optionsLabel.setFont(Utilities.LABEL_FONT);
        optionsLabel.setForeground(Color.black);
        prepareButton = new JButton("Set Prepare Options");
        prepareButton.setFont(Utilities.BUTTON_FONT);
        prepareButton.setForeground(Color.black);
        prepareButton.addActionListener(this);
        elevationCB = new JCheckBox("Create Airport Elevation BGL (Required if you change the airport elevation)");
        elevationCB.setFont(Utilities.LABEL_FONT);
        elevationCB.setForeground(Color.black);
        JLabel elevationLabel = new JLabel("Place elevation BGL in:");
        elevationLabel.setFont(Utilities.LABEL_FONT);
        elevationLabel.setForeground(Color.black);
        elevationTF = new JTextField(SettingsEngine.getInstance().getElevationFolder(), 20);
        elevationTF.setFont(Utilities.TEXT_FIELD_FONT);
        elevationTF.setForeground(Color.black);
        elevationTF.setCaretPosition(0);
        elevationButton = new JButton("Browse");
        elevationButton.setFont(Utilities.BUTTON_FONT);
        elevationButton.setForeground(Color.black);
        elevationButton.addActionListener(this);
        TitledBorder bglBorder = new TitledBorder(" BGL Contents ");
        bglBorder.setTitleFont(Utilities.TITLED_BORDER_FONT);
        bglBorder.setTitleColor(Color.black);
        JPanel bglPanel = new JPanel(new GridBagLayout());
        bglPanel.setBorder(new CompoundBorder(bglBorder, new EmptyBorder(5, 5, 5, 5)));
        Utilities.addComponent(bglPanel, optionsLabel, 0, 0, 3, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(bglPanel, prepareButton, 0, 1, 3, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(bglPanel, elevationCB, 0, 2, 3, 1, 0, 17, new Insets(15, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(bglPanel, elevationLabel, 0, 3, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(bglPanel, elevationTF, 1, 3, 1, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(bglPanel, elevationButton, 2, 3, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        JLabel outputLabel = new JLabel("Compiler Output:");
        outputLabel.setFont(Utilities.LABEL_FONT);
        outputLabel.setForeground(Color.black);
        resultsLabel = new JLabel("-----");
        resultsLabel.setFont(Utilities.BOLD_LABEL_FONT);
        resultsLabel.setForeground(new Color(0, 102, 0));
        outputTA = new JTextArea(13, 8);
        outputTA.setFont(Utilities.TEXT_FIELD_FONT);
        outputTA.setForeground(new Color(0, 102, 0));
        JScrollPane outputSP = new JScrollPane(outputTA);
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        Utilities.addComponent(mainPanel, dirPanel, 0, 0, 2, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, bglPanel, 0, 1, 2, 1, 2, 17, new Insets(10, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, outputLabel, 0, 2, 1, 1, 0, 17, new Insets(20, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, resultsLabel, 1, 2, 1, 1, 0, 17, new Insets(20, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, outputSP, 0, 3, 2, 1, 1, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.5D);
        getContentPane().add(mainPanel, "Center");
        compileButton = new JButton("Compile");
        compileButton.setFont(Utilities.DIALOG_BUTTON_FONT);
        compileButton.setForeground(Color.black);
        compileButton.addActionListener(this);
        closeButton = new JButton("Close");
        closeButton.setFont(Utilities.DIALOG_BUTTON_FONT);
        closeButton.setForeground(Color.black);
        closeButton.addActionListener(this);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(compileButton);
        buttonPanel.add(closeButton);
        getContentPane().add(buttonPanel, "South");
        pack();
        setLocationRelativeTo(parent);
    }

    public static void showDialog(Frame parent, AirportModel airportModel)
    {
        if(dialog == null)
            dialog = new CompileDialog(parent);
        dialog.updateDialog(airportModel);
        dialog.pack();
        dialog.setVisible(true);
    }

    private void updateDialog(AirportModel airportModel)
    {
        if(this.airportModel != airportModel)
            bglFileTF.setText((new StringBuilder()).append(airportModel.getName()).append(" ").append(airportModel.getIdent()).toString());
        else
        if(!rememberCB.isSelected())
            bglFileTF.setText((new StringBuilder()).append(airportModel.getName()).append(" ").append(airportModel.getIdent()).toString());
        setTitle((new StringBuilder()).append("Compile [").append(airportModel.getIdent()).append("]").toString());
        this.airportModel = airportModel;
        if(SettingsEngine.getInstance().getClearCompile())
            outputTA.setText("");
    }

    private void selectDestFolder()
    {
        JFileChooser fileChooser = new JFileChooser(destTF.getText());
        fileChooser.setFileSelectionMode(1);
        fileChooser.setDialogTitle("Select Destination Folder");
        if(fileChooser.showOpenDialog(this) == 0)
        {
            destTF.setText(fileChooser.getSelectedFile().getAbsolutePath());
            SettingsEngine.getInstance().setCompDestFolder(destTF.getText());
            SettingsEngine.getInstance().writePreferences();
        }
    }

    private void selectBGLFolder()
    {
        JFileChooser fileChooser = new JFileChooser(bglTF.getText());
        fileChooser.setFileSelectionMode(1);
        fileChooser.setDialogTitle("Select BglComp.exe Folder");
        if(fileChooser.showDialog(this, "Select") == 0)
        {
            bglTF.setText(fileChooser.getSelectedFile().getAbsolutePath());
            SettingsEngine.getInstance().setBglCompFolder(bglTF.getText());
            SettingsEngine.getInstance().writePreferences();
        }
    }

    private void selectElevationFolder()
    {
        JFileChooser fileChooser = new JFileChooser(elevationTF.getText());
        fileChooser.setFileSelectionMode(1);
        fileChooser.setDialogTitle("Select Elevation BGL Folder");
        if(fileChooser.showOpenDialog(this) == 0)
        {
            elevationTF.setText(fileChooser.getSelectedFile().getAbsolutePath());
            SettingsEngine.getInstance().setElevationFolder(elevationTF.getText());
            SettingsEngine.getInstance().writePreferences();
        }
    }

    private void compile()
    {
        outputTA.setText("");
        resultsLabel.setText("-----");
        resultsLabel.setForeground(new Color(0, 102, 0));
        SettingsEngine.getInstance().setCompDestFolder(destTF.getText().trim());
        SettingsEngine.getInstance().setUseSceneryTexture(sceneryCB.isSelected());
        SettingsEngine.getInstance().setBglCompFolder(bglTF.getText().trim());
        SettingsEngine.getInstance().setElevationFolder(elevationTF.getText().trim());
        SettingsEngine.getInstance().writePreferences();
        if(CompileEngine.getInstance().compile(outputTA, airportModel, bglFileTF.getText().trim(), elevationCB.isSelected()))
        {
            resultsLabel.setText("COMPILATION SUCCESSFUL");
            resultsLabel.setForeground(new Color(0, 102, 0));
            StringBuffer buffer;
            if(elevationCB.isSelected())
            {
                buffer = new StringBuffer();
                for(int i = 96 + bglFileTF.getText().trim().length(); i >= 0; i--)
                    buffer.append("-");

                buffer.append("\n");
                buffer.append(" NOTE: An additional BGL file called '");
                buffer.append(bglFileTF.getText().trim()).append("_ARP.bgl'");
                buffer.append(" was created for modifying the airport elevation.\n");
                for(int i = 96 + bglFileTF.getText().trim().length(); i >= 0; i--)
                    buffer.append("-");

                buffer.append("\n\n");
                outputTA.insert(buffer.toString(), outputTA.getText().length());
            }
            buffer = new StringBuffer();
            buffer.append("The following elements were written to the BGL file:\n");
            if(PrepareEngine.getInstance().getWriteAprons())
                buffer.append("Aprons\n");
            if(PrepareEngine.getInstance().getWriteBlastFences())
                buffer.append("Blast Fences\n");
            if(PrepareEngine.getInstance().getWriteBoundaryFences())
                buffer.append("Boundary Fences\n");
            if(PrepareEngine.getInstance().getWriteHelipads())
                buffer.append("Helipads\n");
            if(PrepareEngine.getInstance().getWriteJetways())
                buffer.append("Jetways\n");
            if(PrepareEngine.getInstance().getWriteRunways())
                buffer.append("Runways\n");
            if(PrepareEngine.getInstance().getWriteScenery())
                buffer.append("Scenery\n");
            if(PrepareEngine.getInstance().getWriteStarts())
                buffer.append("Start Locations\n");
            if(PrepareEngine.getInstance().getWriteTaxiways())
                buffer.append("Taxiways\n");
            if(PrepareEngine.getInstance().getWriteTaxiwaySigns())
                buffer.append("Taxiway Signs\n");
            if(PrepareEngine.getInstance().getWriteTriggers())
                buffer.append("Triggers\n");
            if(PrepareEngine.getInstance().getWriteTowers())
                buffer.append("Towers\n");
            buffer.append("\nThe following navigation information was written to the BGL file:\n");
            if(PrepareEngine.getInstance().getWriteApproaches())
                buffer.append("Approaches\n");
            if(PrepareEngine.getInstance().getWriteCOMs())
                buffer.append("COMs\n");
            if(PrepareEngine.getInstance().getWriteDMEs())
                buffer.append("DMEs\n");
            if(PrepareEngine.getInstance().getWriteMarkers())
                buffer.append("Markers\n");
            if(PrepareEngine.getInstance().getWriteNDBs())
                buffer.append("NDBs\n");
            if(PrepareEngine.getInstance().getWriteVORs())
                buffer.append("VORs\n");
            outputTA.insert(buffer.toString(), outputTA.getText().length());
            buffer = new StringBuffer();
            buffer.append("\nThe following delete flags were included in the BGL file:\n");
            if(PrepareEngine.getInstance().getDeleteApproaches())
                buffer.append("Approaches\n");
            if(PrepareEngine.getInstance().getDeleteApronLights())
                buffer.append("Apron Lights\n");
            if(PrepareEngine.getInstance().getDeleteAprons())
                buffer.append("Aprons\n");
            if(PrepareEngine.getInstance().getDeleteBlastFences())
                buffer.append("Blast Fences\n");
            if(PrepareEngine.getInstance().getDeleteBoundaryFences())
                buffer.append("Boundary Fences\n");
            if(PrepareEngine.getInstance().getDeleteFrequencies())
                buffer.append("Communication Frequencies\n");
            if(PrepareEngine.getInstance().getDeleteHelipads())
                buffer.append("Helipads\n");
            if(PrepareEngine.getInstance().getDeleteJetways())
                buffer.append("Jetways\n");
            if(PrepareEngine.getInstance().getDeleteRunways())
                buffer.append("Runways\n");
            if(PrepareEngine.getInstance().getDeleteStarts())
                buffer.append("Start Locations\n");
            if(PrepareEngine.getInstance().getDeleteTaxiways())
                buffer.append("Taxiways\n");
            if(PrepareEngine.getInstance().getDeleteTowers())
                buffer.append("Towers\n");
            outputTA.insert(buffer.toString(), outputTA.getText().length());
        } else
        {
            resultsLabel.setText("COMPILATION FAILED");
            resultsLabel.setForeground(Color.red);
        }
        JOptionPane.showMessageDialog(this, "Compilation Finished.\n\nPlease see the text area for details.", "Finished...", 1);
    }

    public void actionPerformed(ActionEvent event)
    {
        if(event.getSource() == browseButton)
            selectDestFolder();
        else
        if(event.getSource() == bglButton)
            selectBGLFolder();
        else
        if(event.getSource() == elevationButton)
            selectElevationFolder();
        else
        if(event.getSource() == prepareButton)
            PrepareDialog.showDialog(Utilities.MAIN_FRAME, airportModel);
        else
        if(event.getSource() == compileButton)
            compile();
        else
        if(event.getSource() == closeButton)
            setVisible(false);
    }

    private AirportModel airportModel;
    private JTextField destTF;
    private JCheckBox sceneryCB;
    private JTextField bglFileTF;
    private JTextField bglTF;
    private JTextArea outputTA;
    private JCheckBox rememberCB;
    private JButton browseButton;
    private JButton bglButton;
    private JButton prepareButton;
    private JButton compileButton;
    private JButton closeButton;
    private JLabel resultsLabel;
    private JCheckBox elevationCB;
    private JTextField elevationTF;
    private JButton elevationButton;
    private static CompileDialog dialog = null;

}
