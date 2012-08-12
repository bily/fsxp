// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BackgroundImageDialog.java

package com.zbluesoftware.fsxp.dialog;

import com.zbluesoftware.fsxp.engine.*;
import com.zbluesoftware.fsxp.filechooser.ImageFileFilter;
import com.zbluesoftware.fsxp.model.AirportModel;
import com.zbluesoftware.fsxp.model.BackgroundImageModel;
import com.zbluesoftware.fsxp.util.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;

public class BackgroundImageDialog extends JDialog
    implements ActionListener, DocumentListener, MouseListener
{

    private BackgroundImageDialog(Frame parent)
    {
        super(parent, "Background Images", true);
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(mainPanel, "Center");
        JTextArea instTA = new JTextArea(8, 75);
        instTA.setFont(Utilities.LABEL_FONT);
        instTA.setForeground(Color.black);
        instTA.setEditable(false);
        instTA.setOpaque(false);
        instTA.setLineWrap(true);
        instTA.setWrapStyleWord(true);
        mainPanel.add(instTA, "North");
        StringBuffer buffer = new StringBuffer();
        buffer.append("This dialog allows you to manage the background images associated with the airport.");
        buffer.append(" An airport may have several background images associated with it.  This allows you");
        buffer.append(" to use several highly detailed images, or one larger overall image.  Each background");
        buffer.append(" image must have latitude/longitude coordinates for the top left and bottom right");
        buffer.append(" hand corners.\n\n");
        buffer.append("[NOTE: 1) the the saving of background images is not done through the main airport xml");
        buffer.append(" file and therefore they will not show up when editing with other applications.  2) The");
        buffer.append(" use of background images requires more memory and more cpu time and may result in slower");
        buffer.append(" panning and zooming performance on some computers.]\n");
        instTA.setText(buffer.toString());
        browseButton = new JButton("Select Image");
        browseButton.setFont(Utilities.BUTTON_FONT);
        browseButton.setForeground(Color.black);
        browseButton.addActionListener(this);
        makeButton = new JButton("Create Image");
        makeButton.setFont(Utilities.BUTTON_FONT);
        makeButton.setForeground(Color.black);
        makeButton.addActionListener(this);
        JPanel imageButtonPanel = new JPanel();
        imageButtonPanel.add(makeButton);
        imageButtonPanel.add(browseButton);
        addButton = new JButton("Add to Airport");
        addButton.setFont(Utilities.BUTTON_FONT);
        addButton.setForeground(Color.black);
        addButton.setEnabled(false);
        addButton.addActionListener(this);
        JLabel previewLabel = new JLabel("Image preview");
        previewLabel.setFont(Utilities.LABEL_FONT);
        previewLabel.setForeground(Color.black);
        imageLabel = new JLabel();
        JPanel previewPanel = new JPanel(new BorderLayout());
        previewPanel.setMinimumSize(new Dimension(150, 150));
        previewPanel.setPreferredSize(previewPanel.getMinimumSize());
        previewPanel.setMaximumSize(previewPanel.getMinimumSize());
        previewPanel.setBorder(new EtchedBorder());
        previewPanel.add(imageLabel, "Center");
        visibleCB = new JCheckBox("Image is visible");
        visibleCB.setFont(Utilities.CHECK_BOX_FONT);
        visibleCB.setForeground(Color.black);
        visibleCB.addActionListener(this);
        JLabel placeLabel = new JLabel("Place By:");
        placeLabel.setFont(Utilities.LABEL_FONT);
        placeLabel.setForeground(Color.black);
        placeComboBox = new JComboBox(getPlacementOptions());
        placeComboBox.setFont(Utilities.COMBO_BOX_FONT);
        placeComboBox.setForeground(Color.black);
        placeComboBox.addActionListener(this);
        JLabel topLeftLatLabel = new JLabel("Top Left Latitude:");
        topLeftLatLabel.setFont(Utilities.LABEL_FONT);
        topLeftLatLabel.setForeground(Color.black);
        topLeftLatTF = new PopupTextField(20);
        topLeftLatTF.setFont(Utilities.TEXT_FIELD_FONT);
        topLeftLatTF.setForeground(Color.black);
        topLeftLatTF.getDocument().addDocumentListener(this);
        JLabel topLeftLonLabel = new JLabel("Top Left Longitude:");
        topLeftLonLabel.setFont(Utilities.LABEL_FONT);
        topLeftLonLabel.setForeground(Color.black);
        topLeftLonTF = new PopupTextField(20);
        topLeftLonTF.setFont(Utilities.TEXT_FIELD_FONT);
        topLeftLonTF.setForeground(Color.black);
        topLeftLonTF.getDocument().addDocumentListener(this);
        JLabel bottomRightLatLabel = new JLabel("Bottom Right Latitude:");
        bottomRightLatLabel.setFont(Utilities.LABEL_FONT);
        bottomRightLatLabel.setForeground(Color.black);
        bottomRightLatTF = new PopupTextField(20);
        bottomRightLatTF.setFont(Utilities.TEXT_FIELD_FONT);
        bottomRightLatTF.setForeground(Color.black);
        bottomRightLatTF.getDocument().addDocumentListener(this);
        JLabel bottomRightLonLabel = new JLabel("Bottom Right Longitude:");
        bottomRightLonLabel.setFont(Utilities.LABEL_FONT);
        bottomRightLonLabel.setForeground(Color.black);
        bottomRightLonTF = new PopupTextField(20);
        bottomRightLonTF.setFont(Utilities.TEXT_FIELD_FONT);
        bottomRightLonTF.setForeground(Color.black);
        bottomRightLonTF.getDocument().addDocumentListener(this);
        topBottomPanel = new JPanel(new GridBagLayout());
        Utilities.addComponent(topBottomPanel, topLeftLatLabel, 0, 0, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(topBottomPanel, topLeftLatTF, 1, 0, 1, 1, 0, 17, new Insets(1, 1, 1, 20), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(topBottomPanel, topLeftLonLabel, 0, 1, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(topBottomPanel, topLeftLonTF, 1, 1, 1, 1, 0, 17, new Insets(1, 1, 1, 20), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(topBottomPanel, bottomRightLatLabel, 0, 2, 1, 1, 0, 13, new Insets(10, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(topBottomPanel, bottomRightLatTF, 1, 2, 1, 1, 0, 17, new Insets(10, 1, 1, 20), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(topBottomPanel, bottomRightLonLabel, 0, 3, 1, 1, 0, 12, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(topBottomPanel, bottomRightLonTF, 1, 3, 1, 1, 0, 18, new Insets(1, 1, 1, 20), 0, 0, 0.0D, 0.0D);
        JLabel combinedLatLonLabel = new JLabel("Combined Lat/Lon:");
        combinedLatLonLabel.setFont(Utilities.LABEL_FONT);
        combinedLatLonLabel.setForeground(Color.black);
        combinedLatLonTF = new PopupTextField(20);
        combinedLatLonTF.setFont(Utilities.TEXT_FIELD_FONT);
        combinedLatLonTF.setForeground(Color.black);
        combinedLatLonTF.getDocument().addDocumentListener(this);
        combinedPanel = new JPanel(new GridBagLayout());
        combinedPanel.setVisible(false);
        Utilities.addComponent(combinedPanel, combinedLatLonLabel, 0, 0, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(combinedPanel, combinedLatLonTF, 1, 0, 1, 1, 0, 17, new Insets(1, 1, 1, 20), 0, 0, 0.0D, 0.0D);
        JLabel existingLabel = new JLabel("Existing Images:");
        existingLabel.setFont(Utilities.LABEL_FONT);
        existingLabel.setForeground(Color.black);
        previewLabel1 = new JLabel();
        previewLabel1.setCursor(new Cursor(12));
        previewLabel1.addMouseListener(this);
        previewLabel2 = new JLabel();
        previewLabel2.setCursor(new Cursor(12));
        previewLabel2.addMouseListener(this);
        previewLabel3 = new JLabel();
        previewLabel3.setCursor(new Cursor(12));
        previewLabel3.addMouseListener(this);
        previewLabel4 = new JLabel();
        previewLabel4.setCursor(new Cursor(12));
        previewLabel4.addMouseListener(this);
        previewPanel1 = new JPanel(new BorderLayout());
        previewPanel1.setMinimumSize(new Dimension(75, 75));
        previewPanel1.setPreferredSize(previewPanel1.getMinimumSize());
        previewPanel1.setMaximumSize(previewPanel1.getMinimumSize());
        previewPanel1.setBorder(new EtchedBorder());
        previewPanel1.add(previewLabel1, "Center");
        previewPanel2 = new JPanel(new BorderLayout());
        previewPanel2.setMinimumSize(new Dimension(75, 75));
        previewPanel2.setPreferredSize(previewPanel2.getMinimumSize());
        previewPanel2.setMaximumSize(previewPanel2.getMinimumSize());
        previewPanel2.setBorder(new EtchedBorder());
        previewPanel2.add(previewLabel2, "Center");
        previewPanel3 = new JPanel(new BorderLayout());
        previewPanel3.setMinimumSize(new Dimension(75, 75));
        previewPanel3.setPreferredSize(previewPanel3.getMinimumSize());
        previewPanel3.setMaximumSize(previewPanel3.getMinimumSize());
        previewPanel3.setBorder(new EtchedBorder());
        previewPanel3.add(previewLabel3, "Center");
        previewPanel4 = new JPanel(new BorderLayout());
        previewPanel4.setMinimumSize(new Dimension(75, 75));
        previewPanel4.setPreferredSize(previewPanel4.getMinimumSize());
        previewPanel4.setMaximumSize(previewPanel4.getMinimumSize());
        previewPanel4.setBorder(new EtchedBorder());
        previewPanel4.add(previewLabel4, "Center");
        JPanel existingPanel = new JPanel(new GridBagLayout());
        Utilities.addComponent(existingPanel, previewPanel1, 0, 0, 1, 1, 0, 10, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(existingPanel, previewPanel2, 1, 0, 1, 1, 0, 10, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(existingPanel, previewPanel3, 0, 1, 1, 1, 0, 10, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(existingPanel, previewPanel4, 1, 1, 1, 1, 0, 10, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        JPanel centerPanel = new JPanel(new GridBagLayout());
        Utilities.addComponent(centerPanel, new JSeparator(), 0, 0, 5, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(centerPanel, imageButtonPanel, 0, 2, 2, 1, 0, 10, new Insets(1, 1, 10, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(centerPanel, placeLabel, 0, 3, 1, 1, 0, 13, new Insets(1, 20, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(centerPanel, placeComboBox, 1, 3, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(centerPanel, topBottomPanel, 0, 4, 2, 1, 0, 13, new Insets(10, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(centerPanel, combinedPanel, 0, 4, 2, 1, 0, 12, new Insets(10, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(centerPanel, addButton, 0, 5, 2, 1, 0, 10, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(centerPanel, previewLabel, 3, 1, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(centerPanel, previewPanel, 3, 2, 1, 3, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(centerPanel, visibleCB, 3, 5, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(centerPanel, existingLabel, 4, 1, 1, 1, 0, 17, new Insets(1, 20, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(centerPanel, existingPanel, 4, 2, 1, 3, 0, 17, new Insets(1, 20, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(centerPanel, new JSeparator(), 0, 6, 5, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        mainPanel.add(centerPanel, "Center");
        closeButton = new JButton("Close");
        closeButton.setFont(Utilities.DIALOG_BUTTON_FONT);
        closeButton.setForeground(Color.black);
        closeButton.addActionListener(this);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(closeButton);
        mainPanel.add(buttonPanel, "South");
        pack();
        setLocationRelativeTo(parent);
    }

    public static void showDialog(Frame parent, AirportModel airportModel)
    {
        if(dialog == null)
            dialog = new BackgroundImageDialog(parent);
        dialog.updateDialog(airportModel);
        dialog.setVisible(true);
    }

    private void updateDialog(AirportModel airportModel)
    {
        this.airportModel = airportModel;
        updateImages();
    }

    private DefaultComboBoxModel getPlacementOptions()
    {
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        model.addElement("By lat/lon coordinates");
        model.addElement("By combined lat/lon");
        return model;
    }

    private void updateImages()
    {
        previewLabel1.setCursor(new Cursor(0));
        previewLabel2.setCursor(new Cursor(0));
        previewLabel3.setCursor(new Cursor(0));
        previewLabel4.setCursor(new Cursor(0));
        previewLabel1.setIcon(null);
        previewLabel2.setIcon(null);
        previewLabel3.setIcon(null);
        previewLabel4.setIcon(null);
        ArrayList arrayList = airportModel.getBGImageAL();
        for(int i = 0; i < arrayList.size() && i < 4; i++)
        {
            ImageIcon previewIcon = new ImageIcon(((BackgroundImageModel)arrayList.get(i)).getImage());
            int excessHeight = previewIcon.getIconHeight() - 75;
            int excessWidth = previewIcon.getIconWidth() - 75;
            if(excessHeight > 0 || excessWidth > 0)
                if(excessHeight > excessWidth)
                    previewIcon = new ImageIcon(previewIcon.getImage().getScaledInstance(Math.max(1, (int)((float)previewIcon.getIconWidth() / ((float)previewIcon.getIconHeight() / 75F))), 75, 1));
                else
                    previewIcon = new ImageIcon(previewIcon.getImage().getScaledInstance(75, Math.max(1, (int)((float)previewIcon.getIconHeight() / ((float)previewIcon.getIconWidth() / 75F))), 1));
            switch(i)
            {
            case 0: // '\0'
                previewLabel1.setIcon(previewIcon);
                previewLabel1.setCursor(new Cursor(12));
                break;

            case 1: // '\001'
                previewLabel2.setIcon(previewIcon);
                previewLabel2.setCursor(new Cursor(12));
                break;

            case 2: // '\002'
                previewLabel3.setIcon(previewIcon);
                previewLabel3.setCursor(new Cursor(12));
                break;

            case 3: // '\003'
                previewLabel4.setIcon(previewIcon);
                previewLabel4.setCursor(new Cursor(12));
                break;
            }
        }

    }

    private void setAddButtonEnabled()
    {
        boolean status = true;
        status &= previewIcon != null;
        if(placeComboBox.getSelectedIndex() == 0)
        {
            status &= topLeftLatTF.getText().trim().length() > 0;
            status &= topLeftLonTF.getText().trim().length() > 0;
            status &= bottomRightLatTF.getText().trim().length() > 0;
            status &= bottomRightLonTF.getText().trim().length() > 0;
        } else
        if(placeComboBox.getSelectedIndex() == 1)
            status &= combinedLatLonTF.getText().trim().length() > 0;
        addButton.setEnabled(status);
    }

    private void selectImage()
    {
        if(fileChooser == null)
        {
            fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new ImageFileFilter());
        }
        if(fileChooser.showOpenDialog(this) == 0)
        {
            long maxSize = 0x300000L;
            if(fileChooser.getSelectedFile().length() > maxSize)
            {
                JOptionPane.showMessageDialog(this, "The selected image is larger than the 3 MB limit.\n\nIt would be better to cut the image into several smaller ones.", "Image Too Large...", 0);
                return;
            }
            previewIcon = new ImageIcon(fileChooser.getSelectedFile().getAbsolutePath());
            int excessHeight = previewIcon.getIconHeight() - 150;
            int excessWidth = previewIcon.getIconWidth() - 150;
            if(excessHeight > 0 || excessWidth > 0)
            {
                if(excessHeight > excessWidth)
                    imageLabel.setIcon(new ImageIcon(previewIcon.getImage().getScaledInstance(Math.max(1, (int)((float)previewIcon.getIconWidth() / ((float)previewIcon.getIconHeight() / 150F))), 150, 1)));
                else
                    imageLabel.setIcon(new ImageIcon(previewIcon.getImage().getScaledInstance(150, Math.max(1, (int)((float)previewIcon.getIconHeight() / ((float)previewIcon.getIconWidth() / 150F))), 1)));
            } else
            {
                imageLabel.setIcon(previewIcon);
            }
            filePath = fileChooser.getSelectedFile().getAbsolutePath();
            setAddButtonEnabled();
            addButton.setText("Add to Airport");
        }
    }

    private void addBackgroundImage()
    {
        double topLeftLat = 0.0D;
        double topLeftLon = 0.0D;
        double bottomRightLat = 0.0D;
        double bottomRightLon = 0.0D;
        if(placeComboBox.getSelectedIndex() == 0)
        {
            topLeftLat = ParseEngine.getInstance().parseLatitude(topLeftLatTF.getText());
            if(topLeftLat == (1.0D / 0.0D))
            {
                topLeftLatTF.requestFocus();
                JOptionPane.showMessageDialog(this, "The Top Left Latitude cannot be parsed.", "Latitude Error...", 0);
                return;
            }
            topLeftLon = ParseEngine.getInstance().parseLongitude(topLeftLonTF.getText());
            if(topLeftLon == (1.0D / 0.0D))
            {
                topLeftLonTF.requestFocus();
                JOptionPane.showMessageDialog(this, "The Top Left Longitude cannot be parsed.", "Longitude Error...", 0);
                return;
            }
            bottomRightLat = ParseEngine.getInstance().parseLatitude(bottomRightLatTF.getText());
            if(bottomRightLat == (1.0D / 0.0D))
            {
                bottomRightLatTF.requestFocus();
                JOptionPane.showMessageDialog(this, "The Bottom Right Latitude cannot be parsed.", "Latitude Error...", 0);
                return;
            }
            bottomRightLon = ParseEngine.getInstance().parseLongitude(bottomRightLonTF.getText());
            if(bottomRightLon == (1.0D / 0.0D))
            {
                bottomRightLonTF.requestFocus();
                JOptionPane.showMessageDialog(this, "The Bottom Right Longitude cannot be parsed.", "Longitude Error...", 0);
                return;
            }
        } else
        {
            String coords[] = combinedLatLonTF.getText().trim().split(",");
            if(coords.length != 4)
            {
                StringBuffer buffer = new StringBuffer();
                buffer.append("The coordinates need to be formatted in the following way:\n");
                buffer.append("40.77716173848788,-73.88007402420044,40.77472435289935,-73.87578248977661");
                JOptionPane.showMessageDialog(this, buffer.toString(), "Formatting Error...", 0);
            }
            topLeftLat = ParseEngine.getInstance().parseLatitude(coords[0]);
            if(topLeftLat == (1.0D / 0.0D))
            {
                combinedLatLonTF.requestFocus();
                JOptionPane.showMessageDialog(this, "The Top Left Latitude cannot be parsed.", "Latitude Error...", 0);
                return;
            }
            topLeftLon = ParseEngine.getInstance().parseLongitude(coords[1]);
            if(topLeftLon == (1.0D / 0.0D))
            {
                combinedLatLonTF.requestFocus();
                JOptionPane.showMessageDialog(this, "The Top Left Longitude cannot be parsed.", "Longitude Error...", 0);
                return;
            }
            bottomRightLat = ParseEngine.getInstance().parseLatitude(coords[2]);
            if(bottomRightLat == (1.0D / 0.0D))
            {
                combinedLatLonTF.requestFocus();
                JOptionPane.showMessageDialog(this, "The Bottom Right Latitude cannot be parsed.", "Latitude Error...", 0);
                return;
            }
            bottomRightLon = ParseEngine.getInstance().parseLongitude(coords[3]);
            if(bottomRightLon == (1.0D / 0.0D))
            {
                combinedLatLonTF.requestFocus();
                JOptionPane.showMessageDialog(this, "The Bottom Right Longitude cannot be parsed.", "Longitude Error...", 0);
                return;
            }
        }
        if(topLeftLon >= bottomRightLon)
        {
            JOptionPane.showMessageDialog(this, "The top left longitude must be less than the bottom right longitude.", "Longitude Error...", 0);
            return;
        }
        if(topLeftLat <= bottomRightLat)
        {
            JOptionPane.showMessageDialog(this, "The top left latitude must be greater than the bottom right latitude.", "Latitude Error...", 0);
            return;
        }
        float testRadius = (float)airportModel.getAirportTestRadius();
        if(airportModel.getAirportTestRadiusMeasure().equals("M"))
            testRadius *= 3.28F;
        else
        if(airportModel.getAirportTestRadiusMeasure().equals("N"))
            testRadius *= 6074.56F;
        testRadius += 1000F;
        java.awt.geom.Point2D.Float tlDistancePoint = Utilities.getPixelsBetweenPoints(airportModel.getLatLon().getLat(), airportModel.getLatLon().getLon(), topLeftLat, topLeftLon, 1.0F);
        float topLeftDistance = (float)Math.sqrt(Math.pow(tlDistancePoint.getX(), 2D) + Math.pow(tlDistancePoint.getY(), 2D));
        java.awt.geom.Point2D.Float brDistancePoint = Utilities.getPixelsBetweenPoints(airportModel.getLatLon().getLat(), airportModel.getLatLon().getLon(), bottomRightLat, bottomRightLon, 1.0F);
        float bottomRightDistance = (float)Math.sqrt(Math.pow(brDistancePoint.getX(), 2D) + Math.pow(brDistancePoint.getY(), 2D));
        if(topLeftDistance > testRadius)
        {
            JOptionPane.showMessageDialog(this, "The top left corner of the image is more than 1,000 feet from the airport's test radius.\n\nThe image cannot be added", "Image Too Far Away...", 0);
            return;
        }
        if(bottomRightDistance > testRadius)
        {
            JOptionPane.showMessageDialog(this, "The bottom right corner of the image is more than 1,000 feet from the airport's test radius.\n\nThe image cannot be added.", "Image Too Far Away...", 0);
            return;
        }
        BufferedImage bufferedImage = new BufferedImage(previewIcon.getIconWidth(), previewIcon.getIconHeight(), 5);
        bufferedImage.getGraphics().drawImage(previewIcon.getImage(), 0, 0, null);
        if(airportModel.addBackgroundImage(bufferedImage, filePath, true, new LatLonPoint(topLeftLat, topLeftLon), new LatLonPoint(bottomRightLat, bottomRightLon)))
        {
            updateImages();
            JOptionPane.showMessageDialog(this, "The background image has been added to the airport.", "Image Added...", 1);
        } else
        {
            JOptionPane.showMessageDialog(this, "The background image is already associated with the airport.\n\nIt was not re-added.", "Image Exists...", 0);
        }
    }

    private void displayExistingImage(int imageIndex)
    {
        this.imageIndex = imageIndex;
        BackgroundImageModel model = (BackgroundImageModel)airportModel.getBGImageAL().get(imageIndex - 1);
        visibleCB.removeActionListener(this);
        visibleCB.setSelected(model.isImageVisible());
        visibleCB.addActionListener(this);
        previewIcon = new ImageIcon(model.getImage());
        int excessHeight = previewIcon.getIconHeight() - 150;
        int excessWidth = previewIcon.getIconWidth() - 150;
        if(excessHeight > 0 || excessWidth > 0)
            if(excessHeight > excessWidth)
                previewIcon = new ImageIcon(previewIcon.getImage().getScaledInstance(Math.max(1, (int)((float)previewIcon.getIconWidth() / ((float)previewIcon.getIconHeight() / 150F))), 150, 1));
            else
                previewIcon = new ImageIcon(previewIcon.getImage().getScaledInstance(150, Math.max(1, (int)((float)previewIcon.getIconHeight() / ((float)previewIcon.getIconWidth() / 150F))), 1));
        imageLabel.setIcon(previewIcon);
        topLeftLatTF.setText(DisplayEngine.getInstance().formatObjectLatitude(model.getTopLeft().getLat()));
        topLeftLonTF.setText(DisplayEngine.getInstance().formatObjectLongitude(model.getTopLeft().getLon()));
        bottomRightLatTF.setText(DisplayEngine.getInstance().formatObjectLatitude(model.getBottomRight().getLat()));
        bottomRightLonTF.setText(DisplayEngine.getInstance().formatObjectLongitude(model.getBottomRight().getLon()));
        StringBuffer buffer = new StringBuffer();
        buffer.append(DisplayEngine.getInstance().formatObjectLongitude(model.getTopLeft().getLat())).append(",");
        buffer.append(DisplayEngine.getInstance().formatObjectLongitude(model.getTopLeft().getLon())).append(",");
        buffer.append(DisplayEngine.getInstance().formatObjectLongitude(model.getBottomRight().getLat())).append(",");
        buffer.append(DisplayEngine.getInstance().formatObjectLongitude(model.getBottomRight().getLon()));
        combinedLatLonTF.setText(buffer.toString());
        combinedLatLonTF.setCaretPosition(0);
        setAddButtonEnabled();
        addButton.setText("Delete Image");
    }

    private void deleteBackgroundImage()
    {
        if(JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this background image?", "Confirm Delete...", 0) != 0)
        {
            return;
        } else
        {
            airportModel.getBGImageAL().remove(imageIndex - 1);
            previewIcon = null;
            imageLabel.setIcon(null);
            topLeftLatTF.setText("");
            topLeftLonTF.setText("");
            bottomRightLatTF.setText("");
            bottomRightLonTF.setText("");
            updateImages();
            setAddButtonEnabled();
            JOptionPane.showMessageDialog(this, "The background image has been deleted.", "Image Deleted...", 1);
            return;
        }
    }

    private void updatePlacementOption()
    {
        switch(placeComboBox.getSelectedIndex())
        {
        case 0: // '\0'
            topBottomPanel.setVisible(true);
            combinedPanel.setVisible(false);
            break;

        case 1: // '\001'
            topBottomPanel.setVisible(false);
            combinedPanel.setVisible(true);
            break;
        }
    }

    private void createImage()
    {
        StringBuffer urlBuffer = new StringBuffer("http://www.zbluesoftware.com/fsxplanner/maps.cfm");
        urlBuffer.append("?centerLat=").append(airportModel.getLatLon().getLat());
        urlBuffer.append("&centerLon=").append(airportModel.getLatLon().getLon());
        urlBuffer.append("&action=find");
        try
        {
            String cmd = (new StringBuilder()).append("rundll32 url.dll,FileProtocolHandler ").append(urlBuffer.toString()).toString();
            Process p = Runtime.getRuntime().exec(cmd);
        }
        catch(IOException ioe)
        {
            LogEngine.getInstance().log(ioe);
        }
    }

    private void updateImageVisibility()
    {
        if(imageIndex > 0 && imageIndex <= airportModel.getBGImageAL().size())
        {
            BackgroundImageModel model = (BackgroundImageModel)airportModel.getBGImageAL().get(imageIndex - 1);
            model.setImageVisible(visibleCB.isSelected());
        }
    }

    public void actionPerformed(ActionEvent event)
    {
        if(event.getSource() == closeButton)
            setVisible(false);
        else
        if(event.getSource() == browseButton)
            selectImage();
        else
        if(event.getSource() == addButton)
        {
            if(addButton.getText().equals("Add to Airport"))
                addBackgroundImage();
            else
            if(addButton.getText().equals("Delete Image"))
                deleteBackgroundImage();
        } else
        if(event.getSource() == placeComboBox)
        {
            updatePlacementOption();
            setAddButtonEnabled();
        } else
        if(event.getSource() == makeButton)
            createImage();
        else
        if(event.getSource() == visibleCB)
            updateImageVisibility();
    }

    public void changedUpdate(DocumentEvent documentevent)
    {
    }

    public void insertUpdate(DocumentEvent event)
    {
        if(event.getDocument() == topLeftLatTF.getDocument())
            setAddButtonEnabled();
        else
        if(event.getDocument() == topLeftLonTF.getDocument())
            setAddButtonEnabled();
        else
        if(event.getDocument() == bottomRightLatTF.getDocument())
            setAddButtonEnabled();
        else
        if(event.getDocument() == bottomRightLonTF.getDocument())
            setAddButtonEnabled();
        else
        if(event.getDocument() == combinedLatLonTF.getDocument())
            setAddButtonEnabled();
    }

    public void removeUpdate(DocumentEvent event)
    {
        if(event.getDocument() == topLeftLatTF.getDocument())
            setAddButtonEnabled();
        else
        if(event.getDocument() == topLeftLonTF.getDocument())
            setAddButtonEnabled();
        else
        if(event.getDocument() == bottomRightLatTF.getDocument())
            setAddButtonEnabled();
        else
        if(event.getDocument() == bottomRightLonTF.getDocument())
            setAddButtonEnabled();
    }

    public void mouseClicked(MouseEvent event)
    {
        if(event.getSource() == previewLabel1 && previewLabel1.getCursor().getType() == 12)
            displayExistingImage(1);
        else
        if(event.getSource() == previewLabel2 && previewLabel2.getCursor().getType() == 12)
            displayExistingImage(2);
        else
        if(event.getSource() == previewLabel3 && previewLabel3.getCursor().getType() == 12)
            displayExistingImage(3);
        else
        if(event.getSource() == previewLabel4 && previewLabel4.getCursor().getType() == 12)
            displayExistingImage(4);
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

    private AirportModel airportModel;
    private JCheckBox visibleCB;
    private JComboBox placeComboBox;
    private PopupTextField combinedLatLonTF;
    private JPanel topBottomPanel;
    private JPanel combinedPanel;
    private JLabel imageLabel;
    private JButton browseButton;
    private JButton makeButton;
    private JButton addButton;
    private PopupTextField topLeftLatTF;
    private PopupTextField topLeftLonTF;
    private PopupTextField bottomRightLatTF;
    private PopupTextField bottomRightLonTF;
    private JLabel previewLabel1;
    private JLabel previewLabel2;
    private JLabel previewLabel3;
    private JLabel previewLabel4;
    private JPanel previewPanel1;
    private JPanel previewPanel2;
    private JPanel previewPanel3;
    private JPanel previewPanel4;
    private JButton closeButton;
    private ImageIcon previewIcon;
    private String filePath;
    private JFileChooser fileChooser;
    private int imageIndex;
    private static final int IMAGE_PREVIEW_MAX = 150;
    private static final int EXISTING_PREVIEW_MAX = 75;
    private static BackgroundImageDialog dialog = null;

}
