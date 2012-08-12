// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TaxiwaySignWizardDialog.java

package com.zbluesoftware.fsxp.dialog;

import com.zbluesoftware.fsxp.engine.LogEngine;
import com.zbluesoftware.fsxp.ui.component.TaxiwaySignDisplay;
import com.zbluesoftware.fsxp.util.Utilities;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

public class TaxiwaySignWizardDialog extends JDialog
    implements ActionListener, DocumentListener, MouseListener
{

    private TaxiwaySignWizardDialog(Frame parent)
    {
        super(parent, "Taxiway Sign Wizard", true);
        JTextArea infoTA = new JTextArea(7, 5);
        infoTA.setFont(Utilities.LABEL_FONT);
        infoTA.setForeground(Color.black);
        infoTA.setLineWrap(true);
        infoTA.setWrapStyleWord(true);
        infoTA.setOpaque(false);
        infoTA.setEditable(false);
        StringBuffer text = new StringBuffer();
        text.append("This wizard is designed to help in the creation of taxiway signs.  Taxiway signs are composed of a string of");
        text.append(" characters which are parsed to create the sign.  You may use the capital letters A-Z and the numbers 0-9");
        text.append(" in your sign.  In addition there are several different types of taxiway sign as well as various special");
        text.append(" taxiway sign characters.  To select one of these click on the icon below.  Your taxiway sign may contain");
        text.append(" multiple types of taxi way signs.  For example you may have both a runway marker and a location marker");
        text.append(" in the same sign. Click the OK button to accept the new taxiway sign string.");
        infoTA.setText(text.toString());
        TitledBorder infoBorder = new TitledBorder(" Instructions ");
        infoBorder.setTitleFont(Utilities.TITLED_BORDER_FONT);
        infoBorder.setTitleColor(Color.black);
        JPanel infoPanel = new JPanel(new GridBagLayout());
        infoPanel.setBorder(new CompoundBorder(infoBorder, new EmptyBorder(5, 5, 5, 5)));
        Utilities.addComponent(infoPanel, infoTA, 0, 0, 1, 1, 1, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.5D);
        getContentPane().add(infoPanel, "North");
        JLabel locationLabel = new JLabel("Location");
        locationLabel.setFont(Utilities.LABEL_FONT);
        locationLabel.setForeground(Color.black);
        locationSign = new TaxiwaySignDisplay();
        locationSign.setLabelText("l[G]");
        locationSign.setAlign(1);
        locationSign.setCursor(new Cursor(12));
        locationSign.addMouseListener(this);
        JLabel directionLabel = new JLabel("Direction");
        directionLabel.setFont(Utilities.LABEL_FONT);
        directionLabel.setForeground(Color.black);
        directionSign = new TaxiwaySignDisplay();
        directionSign.setLabelText("d\\G");
        directionSign.setAlign(1);
        directionSign.setCursor(new Cursor(12));
        directionSign.addMouseListener(this);
        JLabel mandatoryLabel = new JLabel("Mandatory");
        mandatoryLabel.setFont(Utilities.LABEL_FONT);
        mandatoryLabel.setForeground(Color.black);
        mandatorySign = new TaxiwaySignDisplay();
        mandatorySign.setLabelText("m[G]");
        mandatorySign.setAlign(1);
        mandatorySign.setCursor(new Cursor(12));
        mandatorySign.addMouseListener(this);
        JLabel infoLabel = new JLabel("Information");
        infoLabel.setFont(Utilities.LABEL_FONT);
        infoLabel.setForeground(Color.black);
        infoSign = new TaxiwaySignDisplay();
        infoSign.setLabelText("iAB");
        infoSign.setAlign(1);
        infoSign.setCursor(new Cursor(12));
        infoSign.addMouseListener(this);
        JLabel runwayLabel = new JLabel("Runway");
        runwayLabel.setFont(Utilities.LABEL_FONT);
        runwayLabel.setForeground(Color.black);
        runwaySign = new TaxiwaySignDisplay();
        runwaySign.setLabelText("r28");
        runwaySign.setAlign(1);
        runwaySign.setCursor(new Cursor(12));
        runwaySign.addMouseListener(this);
        JLabel unknownLabel = new JLabel("Unknown");
        unknownLabel.setFont(Utilities.LABEL_FONT);
        unknownLabel.setForeground(Color.black);
        unknownSign = new TaxiwaySignDisplay();
        unknownSign.setLabelText("uG");
        unknownSign.setAlign(1);
        unknownSign.setCursor(new Cursor(12));
        unknownSign.addMouseListener(this);
        TitledBorder typeBorder = new TitledBorder(" Taxiway Sign Types ");
        typeBorder.setTitleFont(Utilities.TITLED_BORDER_FONT);
        typeBorder.setTitleColor(Color.black);
        JPanel typePanel = new JPanel(new GridBagLayout());
        typePanel.setBorder(new CompoundBorder(typeBorder, new EmptyBorder(5, 5, 5, 5)));
        Utilities.addComponent(typePanel, locationLabel, 0, 0, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(typePanel, locationSign, 0, 1, 1, 1, 1, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.5D);
        Utilities.addComponent(typePanel, runwayLabel, 0, 2, 1, 1, 0, 17, new Insets(5, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(typePanel, runwaySign, 0, 3, 1, 1, 1, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.5D);
        Utilities.addComponent(typePanel, mandatoryLabel, 0, 4, 1, 1, 0, 17, new Insets(5, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(typePanel, mandatorySign, 0, 5, 1, 1, 1, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.5D);
        Utilities.addComponent(typePanel, directionLabel, 1, 0, 1, 1, 0, 17, new Insets(1, 10, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(typePanel, directionSign, 1, 1, 1, 1, 1, 17, new Insets(1, 10, 1, 1), 0, 0, 0.5D, 0.5D);
        Utilities.addComponent(typePanel, infoLabel, 1, 2, 1, 1, 0, 17, new Insets(5, 10, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(typePanel, infoSign, 1, 3, 1, 1, 1, 17, new Insets(1, 10, 1, 1), 0, 0, 0.5D, 0.5D);
        Utilities.addComponent(typePanel, unknownLabel, 1, 4, 1, 1, 0, 17, new Insets(5, 10, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(typePanel, unknownSign, 1, 5, 1, 1, 1, 17, new Insets(1, 10, 1, 1), 0, 0, 0.5D, 0.5D);
        Utilities.addComponent(typePanel, Box.createVerticalStrut(175), 2, 0, 1, 6, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        JLabel rightArrowLabel = new JLabel("Right Arrow");
        rightArrowLabel.setFont(Utilities.LABEL_FONT);
        rightArrowLabel.setForeground(Color.black);
        rightArrowShape = new JLabel(convertShape(unknownSign.createRightArrow(), true));
        rightArrowShape.setFont(Utilities.LABEL_FONT);
        rightArrowShape.setForeground(Color.black);
        rightArrowShape.setCursor(new Cursor(12));
        rightArrowShape.addMouseListener(this);
        JLabel upArrowLabel = new JLabel("Up Arrow");
        upArrowLabel.setFont(Utilities.LABEL_FONT);
        upArrowLabel.setForeground(Color.black);
        upArrowShape = new JLabel(convertShape(unknownSign.createUpArrow(), true));
        upArrowShape.setFont(Utilities.LABEL_FONT);
        upArrowShape.setForeground(Color.black);
        upArrowShape.setCursor(new Cursor(12));
        upArrowShape.addMouseListener(this);
        JLabel upRightArrowLabel = new JLabel("Up-Right Arrow");
        upRightArrowLabel.setFont(Utilities.LABEL_FONT);
        upRightArrowLabel.setForeground(Color.black);
        upRightArrowShape = new JLabel(convertShape(unknownSign.createUpRightArrow(), true));
        upRightArrowShape.setFont(Utilities.LABEL_FONT);
        upRightArrowShape.setForeground(Color.black);
        upRightArrowShape.setCursor(new Cursor(12));
        upRightArrowShape.addMouseListener(this);
        JLabel leftArrowLabel = new JLabel("Left Arrow");
        leftArrowLabel.setFont(Utilities.LABEL_FONT);
        leftArrowLabel.setForeground(Color.black);
        leftArrowShape = new JLabel(convertShape(unknownSign.createLeftArrow(), true));
        leftArrowShape.setFont(Utilities.LABEL_FONT);
        leftArrowShape.setForeground(Color.black);
        leftArrowShape.setCursor(new Cursor(12));
        leftArrowShape.addMouseListener(this);
        JLabel backArrowLabel = new JLabel("Back Arrow");
        backArrowLabel.setFont(Utilities.LABEL_FONT);
        backArrowLabel.setForeground(Color.black);
        backArrowShape = new JLabel(convertShape(unknownSign.createBackArrow(), true));
        backArrowShape.setFont(Utilities.LABEL_FONT);
        backArrowShape.setForeground(Color.black);
        backArrowShape.setCursor(new Cursor(12));
        backArrowShape.addMouseListener(this);
        JLabel upLeftArrowLabel = new JLabel("Up-Left Arrow");
        upLeftArrowLabel.setFont(Utilities.LABEL_FONT);
        upLeftArrowLabel.setForeground(Color.black);
        upLeftArrowShape = new JLabel(convertShape(unknownSign.createUpLeftArrow(), true));
        upLeftArrowShape.setFont(Utilities.LABEL_FONT);
        upLeftArrowShape.setForeground(Color.black);
        upLeftArrowShape.setCursor(new Cursor(12));
        upLeftArrowShape.addMouseListener(this);
        JLabel backLeftArrowLabel = new JLabel("Back-Left Arrow");
        backLeftArrowLabel.setFont(Utilities.LABEL_FONT);
        backLeftArrowLabel.setForeground(Color.black);
        backLeftArrowShape = new JLabel(convertShape(unknownSign.createBackLeftArrow(), true));
        backLeftArrowShape.setFont(Utilities.LABEL_FONT);
        backLeftArrowShape.setForeground(Color.black);
        backLeftArrowShape.setCursor(new Cursor(12));
        backLeftArrowShape.addMouseListener(this);
        JLabel backRightArrowLabel = new JLabel("Back-Right Arrow");
        backRightArrowLabel.setFont(Utilities.LABEL_FONT);
        backRightArrowLabel.setForeground(Color.black);
        backRightArrowShape = new JLabel(convertShape(unknownSign.createBackRightArrow(), true));
        backRightArrowShape.setFont(Utilities.LABEL_FONT);
        backRightArrowShape.setForeground(Color.black);
        backRightArrowShape.setCursor(new Cursor(12));
        backRightArrowShape.addMouseListener(this);
        JLabel leftBorderLabel = new JLabel("Left Border");
        leftBorderLabel.setFont(Utilities.LABEL_FONT);
        leftBorderLabel.setForeground(Color.black);
        leftBorderShape = new JLabel(convertShape(unknownSign.createLeftBorder(), false));
        leftBorderShape.setFont(Utilities.LABEL_FONT);
        leftBorderShape.setForeground(Color.black);
        leftBorderShape.setCursor(new Cursor(12));
        leftBorderShape.addMouseListener(this);
        JLabel rightBorderLabel = new JLabel("Right Border");
        rightBorderLabel.setFont(Utilities.LABEL_FONT);
        rightBorderLabel.setForeground(Color.black);
        rightBorderShape = new JLabel(convertShape(unknownSign.createRightBorder(), false));
        rightBorderShape.setFont(Utilities.LABEL_FONT);
        rightBorderShape.setForeground(Color.black);
        rightBorderShape.setCursor(new Cursor(12));
        rightBorderShape.addMouseListener(this);
        JLabel noEnterLabel = new JLabel("Do Not Enter");
        noEnterLabel.setFont(Utilities.LABEL_FONT);
        noEnterLabel.setForeground(Color.black);
        noEnterShape = new JLabel(convertShape(unknownSign.createDoNotEnter(), false));
        noEnterShape.setFont(Utilities.LABEL_FONT);
        noEnterShape.setForeground(Color.black);
        noEnterShape.setCursor(new Cursor(12));
        noEnterShape.addMouseListener(this);
        JLabel ilsLabel = new JLabel("ILS Boundary");
        ilsLabel.setFont(Utilities.LABEL_FONT);
        ilsLabel.setForeground(Color.black);
        ilsShape = new JLabel(convertShape(unknownSign.createILSSign(), false));
        ilsShape.setFont(Utilities.LABEL_FONT);
        ilsShape.setForeground(Color.black);
        ilsShape.setCursor(new Cursor(12));
        ilsShape.addMouseListener(this);
        JLabel runwayBoundLabel = new JLabel("Runway Boundary");
        runwayBoundLabel.setFont(Utilities.LABEL_FONT);
        runwayBoundLabel.setForeground(Color.black);
        runwayBoundShape = new JLabel(convertShape(unknownSign.createRunway(), false));
        runwayBoundShape.setFont(Utilities.LABEL_FONT);
        runwayBoundShape.setForeground(Color.black);
        runwayBoundShape.setCursor(new Cursor(12));
        runwayBoundShape.addMouseListener(this);
        JLabel dotLabel = new JLabel("Dot");
        dotLabel.setFont(Utilities.LABEL_FONT);
        dotLabel.setForeground(Color.black);
        dotShape = new JLabel(convertShape(unknownSign.createDot(), true));
        dotShape.setFont(Utilities.LABEL_FONT);
        dotShape.setForeground(Color.black);
        dotShape.setCursor(new Cursor(12));
        dotShape.addMouseListener(this);
        JLabel verticalLabel = new JLabel("Vertical Line");
        verticalLabel.setFont(Utilities.LABEL_FONT);
        verticalLabel.setForeground(Color.black);
        verticalShape = new JLabel(convertShape(unknownSign.createVerticalLine(), false));
        verticalShape.setFont(Utilities.LABEL_FONT);
        verticalShape.setForeground(Color.black);
        verticalShape.setCursor(new Cursor(12));
        verticalShape.addMouseListener(this);
        TitledBorder specialBorder = new TitledBorder(" Taxiway Sign Special Characters ");
        specialBorder.setTitleFont(Utilities.TITLED_BORDER_FONT);
        specialBorder.setTitleColor(Color.black);
        JPanel specialPanel = new JPanel(new GridBagLayout());
        specialPanel.setBorder(new CompoundBorder(specialBorder, new EmptyBorder(5, 5, 5, 5)));
        Utilities.addComponent(specialPanel, upLeftArrowLabel, 0, 0, 1, 1, 0, 10, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(specialPanel, upLeftArrowShape, 0, 1, 1, 1, 0, 10, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(specialPanel, leftArrowLabel, 0, 2, 1, 1, 0, 10, new Insets(5, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(specialPanel, leftArrowShape, 0, 3, 1, 1, 0, 10, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(specialPanel, backLeftArrowLabel, 0, 4, 1, 1, 0, 10, new Insets(5, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(specialPanel, backLeftArrowShape, 0, 5, 1, 1, 0, 10, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(specialPanel, upArrowLabel, 1, 0, 1, 1, 0, 10, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(specialPanel, upArrowShape, 1, 1, 1, 1, 0, 10, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(specialPanel, noEnterLabel, 1, 2, 1, 1, 0, 10, new Insets(5, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(specialPanel, noEnterShape, 1, 3, 1, 1, 0, 10, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(specialPanel, backArrowLabel, 1, 4, 1, 1, 0, 10, new Insets(5, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(specialPanel, backArrowShape, 1, 5, 1, 1, 0, 10, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(specialPanel, upRightArrowLabel, 2, 0, 1, 1, 0, 10, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(specialPanel, upRightArrowShape, 2, 1, 1, 1, 0, 10, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(specialPanel, rightArrowLabel, 2, 2, 1, 1, 0, 10, new Insets(5, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(specialPanel, rightArrowShape, 2, 3, 1, 1, 0, 10, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(specialPanel, backRightArrowLabel, 2, 4, 1, 1, 0, 10, new Insets(5, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(specialPanel, backRightArrowShape, 2, 5, 1, 1, 0, 10, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(specialPanel, leftBorderLabel, 3, 0, 1, 1, 0, 10, new Insets(1, 10, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(specialPanel, leftBorderShape, 3, 1, 1, 1, 0, 10, new Insets(1, 10, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(specialPanel, runwayBoundLabel, 3, 2, 1, 1, 0, 10, new Insets(5, 10, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(specialPanel, runwayBoundShape, 3, 3, 1, 1, 0, 10, new Insets(1, 10, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(specialPanel, ilsLabel, 3, 4, 1, 1, 0, 10, new Insets(5, 10, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(specialPanel, ilsShape, 3, 5, 1, 1, 0, 10, new Insets(1, 10, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(specialPanel, rightBorderLabel, 4, 0, 1, 1, 0, 10, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(specialPanel, rightBorderShape, 4, 1, 1, 1, 0, 10, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(specialPanel, dotLabel, 4, 2, 1, 1, 0, 10, new Insets(5, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(specialPanel, dotShape, 4, 3, 1, 1, 0, 10, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(specialPanel, verticalLabel, 4, 4, 1, 1, 0, 10, new Insets(5, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(specialPanel, verticalShape, 4, 5, 1, 1, 0, 10, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        labelTF = new JTextField(30);
        labelTF.setFont(Utilities.TEXT_FIELD_FONT);
        labelTF.setForeground(Color.black);
        labelTF.getDocument().addDocumentListener(this);
        taxiwaySign = new TaxiwaySignDisplay();
        TitledBorder taxiwayBorder = new TitledBorder(" Taxiway Sign label and display ");
        taxiwayBorder.setTitleFont(Utilities.TITLED_BORDER_FONT);
        taxiwayBorder.setTitleColor(Color.black);
        JPanel taxiwayPanel = new JPanel(new GridBagLayout());
        taxiwayPanel.setBorder(new CompoundBorder(taxiwayBorder, new EmptyBorder(5, 5, 5, 5)));
        Utilities.addComponent(taxiwayPanel, labelTF, 0, 0, 1, 1, 0, 10, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(taxiwayPanel, taxiwaySign, 0, 1, 1, 1, 1, 10, new Insets(10, 1, 1, 1), 0, 0, 0.5D, 0.5D);
        Utilities.addComponent(taxiwayPanel, Box.createVerticalStrut(70), 1, 0, 1, 2, 0, 10, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        JPanel mainPanel = new JPanel(new GridBagLayout());
        Utilities.addComponent(mainPanel, typePanel, 0, 0, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, specialPanel, 1, 0, 1, 1, 3, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.5D);
        Utilities.addComponent(mainPanel, taxiwayPanel, 0, 1, 2, 1, 1, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.5D);
        getContentPane().add(mainPanel, "Center");
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

    public static String showDialog(Frame parent, String labelText)
    {
        if(dialog == null)
            dialog = new TaxiwaySignWizardDialog(parent);
        dialog.setLabelText(labelText);
        dialog.setVisible(true);
        return dialog.getLabelText();
    }

    private ImageIcon convertShape(Shape shape, boolean fillShape)
    {
        BufferedImage bufferedImage = new BufferedImage(30, 30, 5);
        Graphics2D g2 = bufferedImage.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setPaint(Color.white);
        g2.fill(new java.awt.geom.Rectangle2D.Float(0.0F, 0.0F, 30F, 30F));
        g2.setPaint(Color.black);
        g2.draw(new java.awt.geom.Rectangle2D.Float(0.0F, 0.0F, 29F, 29F));
        g2.translate((30 - shape.getBounds().width) / 2, 0);
        g2.setPaint(Color.black);
        if(fillShape)
            g2.fill(shape);
        else
            g2.draw(shape);
        return new ImageIcon(bufferedImage);
    }

    public void setLabelText(String labelText)
    {
        this.labelText = labelText;
        labelTF.setText(labelText);
        taxiwaySign.setLabelText(labelText);
    }

    public String getLabelText()
    {
        return labelText;
    }

    public void actionPerformed(ActionEvent event)
    {
        if(event.getSource() == okButton)
        {
            labelText = labelTF.getText();
            setVisible(false);
        } else
        if(event.getSource() == cancelButton)
        {
            labelText = null;
            setVisible(false);
        }
    }

    public void changedUpdate(DocumentEvent documentevent)
    {
    }

    public void insertUpdate(DocumentEvent event)
    {
        if(event.getDocument() == labelTF.getDocument())
            taxiwaySign.setLabelText(labelTF.getText());
    }

    public void removeUpdate(DocumentEvent event)
    {
        if(event.getDocument() == labelTF.getDocument())
            taxiwaySign.setLabelText(labelTF.getText());
    }

    public void mouseEntered(MouseEvent mouseevent)
    {
    }

    public void mouseExited(MouseEvent mouseevent)
    {
    }

    public void mouseClicked(MouseEvent event)
    {
        if(event.getSource() == locationSign)
            try
            {
                labelTF.getDocument().insertString(labelTF.getCaretPosition(), "l", null);
            }
            catch(BadLocationException ble)
            {
                LogEngine.getInstance().log(ble);
            }
        else
        if(event.getSource() == directionSign)
            try
            {
                labelTF.getDocument().insertString(labelTF.getCaretPosition(), "d", null);
            }
            catch(BadLocationException ble)
            {
                LogEngine.getInstance().log(ble);
            }
        else
        if(event.getSource() == mandatorySign)
            try
            {
                labelTF.getDocument().insertString(labelTF.getCaretPosition(), "m", null);
            }
            catch(BadLocationException ble)
            {
                LogEngine.getInstance().log(ble);
            }
        else
        if(event.getSource() == infoSign)
            try
            {
                labelTF.getDocument().insertString(labelTF.getCaretPosition(), "i", null);
            }
            catch(BadLocationException ble)
            {
                LogEngine.getInstance().log(ble);
            }
        else
        if(event.getSource() == runwaySign)
            try
            {
                labelTF.getDocument().insertString(labelTF.getCaretPosition(), "r", null);
            }
            catch(BadLocationException ble)
            {
                LogEngine.getInstance().log(ble);
            }
        else
        if(event.getSource() == unknownSign)
            try
            {
                labelTF.getDocument().insertString(labelTF.getCaretPosition(), "u", null);
            }
            catch(BadLocationException ble)
            {
                LogEngine.getInstance().log(ble);
            }
        else
        if(event.getSource() == rightArrowShape)
            try
            {
                labelTF.getDocument().insertString(labelTF.getCaretPosition(), ">", null);
            }
            catch(BadLocationException ble)
            {
                LogEngine.getInstance().log(ble);
            }
        else
        if(event.getSource() == upArrowShape)
            try
            {
                labelTF.getDocument().insertString(labelTF.getCaretPosition(), "^", null);
            }
            catch(BadLocationException ble)
            {
                LogEngine.getInstance().log(ble);
            }
        else
        if(event.getSource() == upRightArrowShape)
            try
            {
                labelTF.getDocument().insertString(labelTF.getCaretPosition(), "'", null);
            }
            catch(BadLocationException ble)
            {
                LogEngine.getInstance().log(ble);
            }
        else
        if(event.getSource() == leftArrowShape)
            try
            {
                labelTF.getDocument().insertString(labelTF.getCaretPosition(), "<", null);
            }
            catch(BadLocationException ble)
            {
                LogEngine.getInstance().log(ble);
            }
        else
        if(event.getSource() == backArrowShape)
            try
            {
                labelTF.getDocument().insertString(labelTF.getCaretPosition(), "v", null);
            }
            catch(BadLocationException ble)
            {
                LogEngine.getInstance().log(ble);
            }
        else
        if(event.getSource() == upLeftArrowShape)
            try
            {
                labelTF.getDocument().insertString(labelTF.getCaretPosition(), "`", null);
            }
            catch(BadLocationException ble)
            {
                LogEngine.getInstance().log(ble);
            }
        else
        if(event.getSource() == backLeftArrowShape)
            try
            {
                labelTF.getDocument().insertString(labelTF.getCaretPosition(), "/", null);
            }
            catch(BadLocationException ble)
            {
                LogEngine.getInstance().log(ble);
            }
        else
        if(event.getSource() == backRightArrowShape)
            try
            {
                labelTF.getDocument().insertString(labelTF.getCaretPosition(), "\\", null);
            }
            catch(BadLocationException ble)
            {
                LogEngine.getInstance().log(ble);
            }
        else
        if(event.getSource() == leftBorderShape)
            try
            {
                labelTF.getDocument().insertString(labelTF.getCaretPosition(), "[", null);
            }
            catch(BadLocationException ble)
            {
                LogEngine.getInstance().log(ble);
            }
        else
        if(event.getSource() == rightBorderShape)
            try
            {
                labelTF.getDocument().insertString(labelTF.getCaretPosition(), "]", null);
            }
            catch(BadLocationException ble)
            {
                LogEngine.getInstance().log(ble);
            }
        else
        if(event.getSource() == noEnterShape)
            try
            {
                labelTF.getDocument().insertString(labelTF.getCaretPosition(), "x", null);
            }
            catch(BadLocationException ble)
            {
                LogEngine.getInstance().log(ble);
            }
        else
        if(event.getSource() == ilsShape)
            try
            {
                labelTF.getDocument().insertString(labelTF.getCaretPosition(), "#", null);
            }
            catch(BadLocationException ble)
            {
                LogEngine.getInstance().log(ble);
            }
        else
        if(event.getSource() == runwayBoundShape)
            try
            {
                labelTF.getDocument().insertString(labelTF.getCaretPosition(), "=", null);
            }
            catch(BadLocationException ble)
            {
                LogEngine.getInstance().log(ble);
            }
        else
        if(event.getSource() == dotShape)
            try
            {
                labelTF.getDocument().insertString(labelTF.getCaretPosition(), ".", null);
            }
            catch(BadLocationException ble)
            {
                LogEngine.getInstance().log(ble);
            }
        else
        if(event.getSource() == verticalShape)
            try
            {
                labelTF.getDocument().insertString(labelTF.getCaretPosition(), "|", null);
            }
            catch(BadLocationException ble)
            {
                LogEngine.getInstance().log(ble);
            }
    }

    public void mousePressed(MouseEvent mouseevent)
    {
    }

    public void mouseReleased(MouseEvent mouseevent)
    {
    }

    private String labelText;
    private JTextField labelTF;
    private TaxiwaySignDisplay locationSign;
    private TaxiwaySignDisplay directionSign;
    private TaxiwaySignDisplay mandatorySign;
    private TaxiwaySignDisplay infoSign;
    private TaxiwaySignDisplay runwaySign;
    private TaxiwaySignDisplay unknownSign;
    private TaxiwaySignDisplay taxiwaySign;
    private JLabel rightArrowShape;
    private JLabel upArrowShape;
    private JLabel upRightArrowShape;
    private JLabel leftArrowShape;
    private JLabel backArrowShape;
    private JLabel upLeftArrowShape;
    private JLabel backLeftArrowShape;
    private JLabel backRightArrowShape;
    private JLabel leftBorderShape;
    private JLabel rightBorderShape;
    private JLabel noEnterShape;
    private JLabel ilsShape;
    private JLabel runwayBoundShape;
    private JLabel dotShape;
    private JLabel verticalShape;
    private JButton okButton;
    private JButton cancelButton;
    private static TaxiwaySignWizardDialog dialog = null;

}
