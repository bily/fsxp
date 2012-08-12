// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PrintEngine.java

package com.zbluesoftware.fsxp.engine;

import com.zbluesoftware.fsxp.comparator.HeadingSort;
import com.zbluesoftware.fsxp.model.*;
import com.zbluesoftware.fsxp.util.LatLonPoint;
import com.zbluesoftware.fsxp.util.Utilities;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.*;
import java.awt.print.*;
import java.text.DateFormat;
import java.util.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

// Referenced classes of package com.zbluesoftware.fsxp.engine:
//            PrintPrefsEngine, LogEngine, ExclusionEngine, ColorsEngine, 
//            SettingsEngine, DisplayEngine

public class PrintEngine extends JDialog
    implements ActionListener
{

    private PrintEngine(Frame frame)
    {
        super(frame, "Print Airport", true);
        JLabel printLabel = new JLabel("Print");
        printLabel.setFont(Utilities.DIALOG_LABEL_FONT);
        printLabel.setForeground(Color.black);
        airportRB = new JRadioButton("To edge of airport", PrintPrefsEngine.getInstance().getPrintAirport());
        airportRB.setFont(Utilities.LABEL_FONT);
        airportRB.setForeground(Color.black);
        radiusRB = new JRadioButton("To airport test radius", PrintPrefsEngine.getInstance().getPrintTestRadius());
        radiusRB.setFont(Utilities.LABEL_FONT);
        radiusRB.setForeground(Color.black);
        ButtonGroup printBG = new ButtonGroup();
        printBG.add(airportRB);
        printBG.add(radiusRB);
        JLabel optionsLabel = new JLabel("Options");
        optionsLabel.setFont(Utilities.DIALOG_LABEL_FONT);
        optionsLabel.setForeground(Color.black);
        backgroundCB = new JCheckBox("Print Background Color", PrintPrefsEngine.getInstance().getPrintBGColor());
        backgroundCB.setFont(Utilities.LABEL_FONT);
        backgroundCB.setForeground(Color.black);
        JLabel paperSizeLabel = new JLabel("Paper Size (inches)");
        paperSizeLabel.setFont(Utilities.DIALOG_LABEL_FONT);
        paperSizeLabel.setForeground(Color.black);
        JLabel widthLabel = new JLabel("Width");
        widthLabel.setFont(Utilities.LABEL_FONT);
        widthLabel.setForeground(Color.black);
        widthSpinner = new JSpinner(new SpinnerNumberModel(8.5D, 1.0D, 100D, 0.5D));
        widthSpinner.setEditor(new javax.swing.JSpinner.NumberEditor(widthSpinner, "0.00"));
        widthSpinner.setValue(new Double(PrintPrefsEngine.getInstance().getPaperWidth()));
        JLabel heightLabel = new JLabel("Height");
        heightLabel.setFont(Utilities.LABEL_FONT);
        heightLabel.setForeground(Color.black);
        heightSpinner = new JSpinner(new SpinnerNumberModel(11D, 1.0D, 100D, 0.5D));
        heightSpinner.setEditor(new javax.swing.JSpinner.NumberEditor(heightSpinner, "0.00"));
        heightSpinner.setValue(new Double(PrintPrefsEngine.getInstance().getPaperHeight()));
        marginLabel = new JLabel("Margins (inches):");
        marginLabel.setFont(Utilities.DIALOG_LABEL_FONT);
        marginLabel.setForeground(Color.black);
        JLabel topMarginLabel = new JLabel("T:");
        topMarginLabel.setFont(Utilities.LABEL_FONT);
        topMarginLabel.setForeground(Color.black);
        topMarginTF = new JTextField((new StringBuilder()).append("").append(PrintPrefsEngine.getInstance().getTopMargin()).toString(), 5);
        topMarginTF.setFont(Utilities.TEXT_FIELD_FONT);
        topMarginTF.setForeground(Color.black);
        JLabel bottomMarginLabel = new JLabel("B:");
        bottomMarginLabel.setFont(Utilities.LABEL_FONT);
        bottomMarginLabel.setForeground(Color.black);
        bottomMarginTF = new JTextField((new StringBuilder()).append("").append(PrintPrefsEngine.getInstance().getBottomMargin()).toString(), 5);
        bottomMarginTF.setFont(Utilities.TEXT_FIELD_FONT);
        bottomMarginTF.setForeground(Color.black);
        JLabel leftMarginLabel = new JLabel("L:");
        leftMarginLabel.setFont(Utilities.LABEL_FONT);
        leftMarginLabel.setForeground(Color.black);
        leftMarginTF = new JTextField((new StringBuilder()).append("").append(PrintPrefsEngine.getInstance().getLeftMargin()).toString(), 5);
        leftMarginTF.setFont(Utilities.TEXT_FIELD_FONT);
        leftMarginTF.setForeground(Color.black);
        JLabel rightMarginLabel = new JLabel("R:");
        rightMarginLabel.setFont(Utilities.LABEL_FONT);
        rightMarginLabel.setForeground(Color.black);
        rightMarginTF = new JTextField((new StringBuilder()).append("").append(PrintPrefsEngine.getInstance().getRightMargin()).toString(), 5);
        rightMarginTF.setFont(Utilities.TEXT_FIELD_FONT);
        rightMarginTF.setForeground(Color.black);
        JPanel marginPanel = new JPanel(new GridBagLayout());
        Utilities.addComponent(marginPanel, marginLabel, 0, 0, 4, 1, 0, 10, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(marginPanel, topMarginLabel, 0, 1, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(marginPanel, topMarginTF, 1, 1, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(marginPanel, bottomMarginLabel, 2, 1, 1, 1, 0, 13, new Insets(1, 10, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(marginPanel, bottomMarginTF, 3, 1, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(marginPanel, leftMarginLabel, 0, 2, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(marginPanel, leftMarginTF, 1, 2, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(marginPanel, rightMarginLabel, 2, 2, 1, 1, 0, 13, new Insets(1, 10, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(marginPanel, rightMarginTF, 3, 2, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        Utilities.addComponent(mainPanel, printLabel, 0, 0, 2, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, airportRB, 0, 1, 2, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, radiusRB, 0, 2, 2, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, optionsLabel, 2, 0, 1, 1, 0, 17, new Insets(1, 20, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, backgroundCB, 2, 1, 1, 1, 0, 17, new Insets(1, 20, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, paperSizeLabel, 0, 3, 2, 1, 0, 17, new Insets(10, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, widthLabel, 0, 4, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, widthSpinner, 1, 4, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, heightLabel, 0, 5, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, heightSpinner, 1, 5, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, marginPanel, 2, 3, 1, 3, 0, 18, new Insets(10, 20, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, new JSeparator(), 0, 6, 3, 1, 2, 10, new Insets(10, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        getContentPane().add(mainPanel, "Center");
        printButton = new JButton("Print");
        printButton.setFont(Utilities.DIALOG_BUTTON_FONT);
        printButton.setForeground(Color.black);
        printButton.addActionListener(this);
        cancelButton = new JButton("Cancel");
        cancelButton.setFont(Utilities.DIALOG_BUTTON_FONT);
        cancelButton.setForeground(Color.black);
        cancelButton.addActionListener(this);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(printButton);
        buttonPanel.add(cancelButton);
        getContentPane().add(buttonPanel, "South");
        pack();
        setLocationRelativeTo(frame);
    }

    public static void showDialog(Frame frame, AirportModel airportModel)
    {
        if(engine == null)
            engine = new PrintEngine(frame);
        engine.setAirportModel(airportModel);
        engine.setVisible(true);
    }

    public static PrintEngine getInstance()
    {
        return engine;
    }

    public void setAirportModel(AirportModel airportModel)
    {
        this.airportModel = airportModel;
    }

    public void printAirport()
    {
        float defaultMargin = 0.5F;
        float topMargin = defaultMargin;
        float bottomMargin = defaultMargin;
        float leftMargin = defaultMargin;
        float rightMargin = defaultMargin;
        try
        {
            topMargin = Float.parseFloat(topMarginTF.getText());
        }
        catch(NumberFormatException nfe)
        {
            topMargin = defaultMargin;
            topMarginTF.setText((new StringBuilder()).append("").append(defaultMargin).toString());
        }
        try
        {
            bottomMargin = Float.parseFloat(bottomMarginTF.getText());
        }
        catch(NumberFormatException nfe)
        {
            bottomMargin = defaultMargin;
            bottomMarginTF.setText((new StringBuilder()).append("").append(defaultMargin).toString());
        }
        try
        {
            leftMargin = Float.parseFloat(leftMarginTF.getText());
        }
        catch(NumberFormatException nfe)
        {
            leftMargin = defaultMargin;
            leftMarginTF.setText((new StringBuilder()).append("").append(defaultMargin).toString());
        }
        try
        {
            rightMargin = Float.parseFloat(rightMarginTF.getText());
        }
        catch(NumberFormatException nfe)
        {
            rightMargin = defaultMargin;
            rightMarginTF.setText((new StringBuilder()).append("").append(defaultMargin).toString());
        }
        if(topMargin < 0.0F || topMargin > defaultMargin * 8F)
        {
            topMargin = defaultMargin;
            topMarginTF.setText((new StringBuilder()).append("").append(defaultMargin).toString());
        }
        if(bottomMargin < 0.0F || bottomMargin > defaultMargin * 8F)
        {
            bottomMargin = defaultMargin;
            bottomMarginTF.setText((new StringBuilder()).append("").append(defaultMargin).toString());
        }
        if(leftMargin < 0.0F || leftMargin > defaultMargin * 8F)
        {
            leftMargin = defaultMargin;
            leftMarginTF.setText((new StringBuilder()).append("").append(defaultMargin).toString());
        }
        if(rightMargin < 0.0F || rightMargin > defaultMargin * 8F)
        {
            rightMargin = defaultMargin;
            rightMarginTF.setText((new StringBuilder()).append("").append(defaultMargin).toString());
        }
        PrintPrefsEngine.getInstance().setPrintAirport(airportRB.isSelected());
        PrintPrefsEngine.getInstance().setPrintTestRadius(radiusRB.isSelected());
        PrintPrefsEngine.getInstance().setPrintBGColor(backgroundCB.isSelected());
        PrintPrefsEngine.getInstance().setPaperWidth(((Double)widthSpinner.getValue()).floatValue());
        PrintPrefsEngine.getInstance().setPaperHeight(((Double)heightSpinner.getValue()).floatValue());
        PrintPrefsEngine.getInstance().setTopMargin(topMargin);
        PrintPrefsEngine.getInstance().setLeftMargin(leftMargin);
        PrintPrefsEngine.getInstance().setRightMargin(rightMargin);
        PrintPrefsEngine.getInstance().setBottomMargin(bottomMargin);
        PrintPrefsEngine.getInstance().writePreferences();
        int paperWidth = (int)((double)((Double)widthSpinner.getValue()).floatValue() * 72D);
        int paperHeight = (int)((double)((Double)heightSpinner.getValue()).floatValue() * 72D);
        Paper paper = new Paper();
        paper.setSize(paperWidth, paperHeight);
        paper.setImageableArea(72F * leftMargin, 72F * topMargin, (float)paperWidth - (72F * leftMargin + 72F * rightMargin), (float)paperHeight - (72F * topMargin + 72F * bottomMargin));
        PageFormat pageFormat = new PageFormat();
        pageFormat.setPaper(paper);
        PrinterJob printerJob = PrinterJob.getPrinterJob();
        printerJob.setJobName((new StringBuilder()).append("FSX Planner ").append(airportModel.getIdent()).toString());
        class AirportPrint
            implements Printable
        {

            public int print(Graphics g, PageFormat pf, int pageIndex)
            {
                if(!printing)
                {
                    printing = true;
                    return 0;
                }
                if(pageIndex > 0)
                    return 1;
                Graphics2D g2 = (Graphics2D)g;
                centerX = pf.getWidth() / 2D;
                centerY = (pf.getHeight() - (double)footerHeight) / 2D;
                imageableX = pf.getImageableX();
                imageableY = pf.getImageableY();
                imageableWidth = pf.getImageableWidth();
                imageableHeight = pf.getImageableHeight() - (double)footerHeight;
                if(airportRB.isSelected())
                {
                    LatLonPoint topLeftLat = (LatLonPoint)rectangleHM.get("topLeft");
                    LatLonPoint bottomRightLat = (LatLonPoint)rectangleHM.get("bottomRight");
                    centerPoint = new LatLonPoint((topLeftLat.getLat() + bottomRightLat.getLat()) / 2D, (topLeftLat.getLon() + bottomRightLat.getLon()) / 2D);
                    double widthDistance = Utilities.getDistanceBetweenLatLons(centerPoint.getLat(), topLeftLat.getLon(), centerPoint.getLat(), centerPoint.getLon());
                    double heightDistance = Utilities.getDistanceBetweenLatLons(topLeftLat.getLat(), centerPoint.getLon(), centerPoint.getLat(), centerPoint.getLon());
                    widthDistance *= 2D;
                    heightDistance *= 2D;
                    widthDistance = widthDistance * 1000D * 3.2799999713897705D;
                    heightDistance = heightDistance * 1000D * 3.2799999713897705D;
                    double widthScale = imageableWidth / widthDistance;
                    double heightScale = imageableHeight / heightDistance;
                    scale = (float)Math.min(widthScale, heightScale);
                } else
                {
                    centerPoint = new LatLonPoint(airportModel.getLatLon().getLat(), airportModel.getLatLon().getLon());
                    double radius = airportModel.getAirportTestRadius() * 2D;
                    if(airportModel.getAirportTestRadiusMeasure().equals("M"))
                        radius *= 3.2799999713897705D;
                    else
                    if(airportModel.getAirportTestRadiusMeasure().equals("N"))
                        radius *= 6074.56005859375D;
                    double widthScale = imageableWidth / radius;
                    double heightScale = imageableHeight / radius;
                    scale = (float)Math.min(widthScale, heightScale);
                }
                if((double)scale < 0.0050000000000000001D)
                    scale = 0.005F;
                if(backgroundCB.isSelected())
                    g2.setPaint(ColorsEngine.getInstance().getBackgroundColor());
                else
                    g2.setPaint(Color.white);
                g2.fillRect(0, 0, (int)pf.getWidth(), (int)pf.getHeight());
                g2.translate((int)centerX, (int)centerY);
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                airportModel.clearTaxiwayPathDisplayModels();
                if(SettingsEngine.getInstance().getDisplayLatLon())
                    paintLatLons(g2);
                if(SettingsEngine.getInstance().getDisplayApron())
                    paintItems(g2, airportModel.getApronAL());
                paintTaxiwayPaths(g2, true);
                if(SettingsEngine.getInstance().getDisplayRunway())
                    paintItems(g2, airportModel.getRunwayAL());
                paintTaxiwayPaths(g2, false);
                if(SettingsEngine.getInstance().getDisplayTWPoint())
                    paintItems(g2, airportModel.getTaxiwayPointHM());
                if(SettingsEngine.getInstance().getDisplayParking())
                    paintItems(g2, airportModel.getTaxiwayParkingHM());
                paintTaxiwayLines(g2);
                if(SettingsEngine.getInstance().getDisplayJetways())
                    paintItems(g2, airportModel.getJetwayAL());
                if(SettingsEngine.getInstance().getDisplayTWSign())
                    paintItems(g2, airportModel.getTaxiwaySignAL());
                if(SettingsEngine.getInstance().getDisplayTower())
                    paintItems(g2, airportModel.getTowerAL());
                if(SettingsEngine.getInstance().getDisplayBoundFence())
                    paintItems(g2, airportModel.getBoundaryFenceAL());
                if(SettingsEngine.getInstance().getDisplayBlastFence())
                    paintItems(g2, airportModel.getBlastFenceAL());
                if(SettingsEngine.getInstance().getDisplayHelipad())
                    paintItems(g2, airportModel.getHelipadAL());
                if(SettingsEngine.getInstance().getDisplayStart())
                    paintItems(g2, airportModel.getStartAL());
                if(SettingsEngine.getInstance().getDisplayApronEL())
                    paintItems(g2, airportModel.getApronEdgeLightAL());
                if(SettingsEngine.getInstance().getDisplayILS())
                    paintItems(g2, airportModel.getILSModels());
                if(SettingsEngine.getInstance().getDisplayMarkers())
                    paintItems(g2, airportModel.getMarkerAL());
                if(SettingsEngine.getInstance().getDisplayVORs())
                    paintItems(g2, airportModel.getVORAL());
                if(SettingsEngine.getInstance().getDisplayNDBs())
                    paintItems(g2, airportModel.getNDBAL());
                if(SettingsEngine.getInstance().getDisplayWindsocks())
                    paintItems(g2, airportModel.getWindsockAL());
                if(SettingsEngine.getInstance().getDisplayAirportCtr())
                {
                    java.awt.geom.Point2D.Float centerDist = Utilities.getPixelsBetweenPoints(centerPoint.getLat(), centerPoint.getLon(), airportModel.getLatLon().getLat(), airportModel.getLatLon().getLon(), scale);
                    g2.setPaint(new Color(255, 0, 255));
                    g2.fill(new java.awt.geom.Ellipse2D.Double(centerDist.x - 8F, centerDist.y - 8F, 16D, 16D));
                    g2.draw(new java.awt.geom.Line2D.Double(centerDist.x, centerDist.y - 15F, centerDist.x, centerDist.y + 15F));
                    g2.draw(new java.awt.geom.Line2D.Double(centerDist.x - 15F, centerDist.y, centerDist.x + 15F, centerDist.y));
                }
                if(radiusRB.isSelected())
                    paintTestRadius(g2);
                g2.setPaint(Color.black);
                g2.drawRect((int)(imageableX - centerX), (int)(imageableY - centerY), (int)imageableWidth, (int)imageableHeight + footerHeight);
                g2.setFont(new Font("Tahoma", 2, 10));
                g2.drawString("FSX Planner", (int)((imageableX - centerX) + 5D), (int)(((centerY - imageableY) + (double)footerHeight) - 3D));
                g2.drawString((new StringBuilder()).append(airportModel.getName()).append(" ").append(airportModel.getIdent()).toString(), -g2.getFontMetrics().stringWidth((new StringBuilder()).append(airportModel.getName()).append(" ").append(airportModel.getIdent()).toString()) / 2, (int)(((centerY - imageableY) + (double)footerHeight) - 3D));
                String theDate = DateFormat.getInstance().format(new Date());
                g2.drawString(theDate, (int)(imageableWidth / 2D - (double)g2.getFontMetrics().stringWidth(theDate) - 5D), (int)(((centerY - imageableY) + (double)footerHeight) - 3D));
                g2.drawLine((int)(imageableX - centerX), (int)(centerY - imageableY), (int)(imageableWidth / 2D), (int)(centerY - imageableY));
                printing = false;
                return 0;
            }

            private void paintLatLons(Graphics2D g2)
            {
                double rightLon = airportModel.getLatLon().getLon();
                int rightLonDegrees = (int)rightLon;
                int rightLonMinutes = (int)((rightLon - (double)rightLonDegrees) * 60D) + 20;
                float rightLonSeconds = 0.0F;
                rightLon = (double)rightLonDegrees + (double)rightLonMinutes / 60D + (double)rightLonSeconds / 3600D;
                double leftLon = airportModel.getLatLon().getLon();
                int leftLonDegrees = (int)leftLon;
                int leftLonMinutes = (int)((leftLon - (double)leftLonDegrees) * 60D) - 20;
                float leftLonSeconds = 0.0F;
                leftLon = (double)leftLonDegrees + (double)leftLonMinutes / 60D + (double)leftLonSeconds / 3600D;
                double topLat = airportModel.getLatLon().getLat();
                int topLatDegrees = (int)topLat;
                int topLatMinutes = (int)((topLat - (double)topLatDegrees) * 60D) + 20;
                float topLatSeconds = 0.0F;
                topLat = (double)topLatDegrees + (double)topLatMinutes / 60D + (double)topLatSeconds / 3600D;
                double bottomLat = airportModel.getLatLon().getLat();
                int bottomLatDegrees = (int)bottomLat;
                int bottomLatMinutes = (int)((bottomLat - (double)bottomLatDegrees) * 60D) - 20;
                float bottomLatSeconds = 0.0F;
                bottomLat = (double)bottomLatDegrees + (double)bottomLatMinutes / 60D + (double)bottomLatSeconds / 3600D;
                g2.setPaint(ColorsEngine.getInstance().getLatLonColor());
                g2.setFont(new Font("Tahoma", 0, 6));
                double secondIncrement = 0.00027777777777777778D * (double)SettingsEngine.getInstance().getSecondsIncrement();
                for(double i = leftLon; i < rightLon; i += secondIncrement)
                {
                    java.awt.geom.Point2D.Float topPoint = Utilities.getPixelsBetweenPoints(centerPoint.getLat(), centerPoint.getLon(), topLat, i, scale);
                    java.awt.geom.Point2D.Float bottomPoint = Utilities.getPixelsBetweenPoints(centerPoint.getLat(), centerPoint.getLon(), bottomLat, i, scale);
                    g2.draw(new java.awt.geom.Line2D.Float(topPoint.x, topPoint.y, bottomPoint.x, (float)(centerY - imageableY - 1.0D)));
                    g2.drawString(DisplayEngine.getInstance().formatGridLongitude(i), bottomPoint.x + 2.0F, (float)(centerY - imageableY - 3D));
                }

                for(double i = bottomLat; i < topLat; i += secondIncrement)
                {
                    java.awt.geom.Point2D.Float leftPoint = Utilities.getPixelsBetweenPoints(centerPoint.getLat(), centerPoint.getLon(), i, leftLon, scale);
                    java.awt.geom.Point2D.Float rightPoint = Utilities.getPixelsBetweenPoints(centerPoint.getLat(), centerPoint.getLon(), i, rightLon, scale);
                    g2.draw(new java.awt.geom.Line2D.Float(leftPoint.x, leftPoint.y, rightPoint.x, rightPoint.y));
                    g2.drawString(DisplayEngine.getInstance().formatGridLatitude(i), (float)(-centerX + imageableX + 2D), leftPoint.y - 2.0F);
                }

            }

            private void paintTestRadius(Graphics2D g2)
            {
                double radius = airportModel.getAirportTestRadius();
                if(airportModel.getAirportTestRadiusMeasure().equals("M"))
                    radius *= 3.2799999713897705D;
                else
                if(airportModel.getAirportTestRadiusMeasure().equals("N"))
                    radius *= 6074.56005859375D;
                radius *= scale;
                g2.setPaint(Color.red);
                g2.draw(new java.awt.geom.Ellipse2D.Double(-radius, -radius, radius * 2D, radius * 2D));
            }

            private void paintItems(Graphics2D g2, ArrayList itemAL)
            {
                for(int i = itemAL.size() - 1; i >= 0; i--)
                {
                    BaseModel baseModel = (BaseModel)itemAL.get(i);
                    baseModel.setCenterPoint(centerPoint);
                    baseModel.setScale(scale);
                    baseModel.paint(g2, true);
                }

            }

            private void paintItems(Graphics2D g2, HashMap itemHM)
            {
                BaseModel baseModel;
                for(Iterator iterator = itemHM.keySet().iterator(); iterator.hasNext(); baseModel.paint(g2, true))
                {
                    baseModel = (BaseModel)itemHM.get((Integer)iterator.next());
                    baseModel.setCenterPoint(centerPoint);
                    baseModel.setScale(scale);
                }

            }

            private void paintTaxiwayPaths(Graphics2D g2, boolean first)
            {
                ArrayList taxiwayPathAL = airportModel.getTaxiwayPathAL();
                HashMap taxiwayPointHM = airportModel.getTaxiwayPointHM();
                HashMap taxiwayParkingHM = airportModel.getTaxiwayParkingHM();
                for(int i = taxiwayPathAL.size() - 1; i >= 0; i--)
                {
                    TaxiwayPathModel taxiwayPathModel = (TaxiwayPathModel)taxiwayPathAL.get(i);
                    if(first)
                    {
                        taxiwayPathModel.setCenterPoint(centerPoint);
                        taxiwayPathModel.setScale(scale);
                        int start = taxiwayPathModel.getStart();
                        int end = taxiwayPathModel.getEnd();
                        if(taxiwayPathModel.getType().equals("PARKING"))
                        {
                            if(taxiwayPointHM.containsKey(new Integer(start)))
                            {
                                taxiwayPathModel.setStartLon(((TaxiwayPointModel)taxiwayPointHM.get(new Integer(start))).getLatLon().getLon());
                                taxiwayPathModel.setStartLat(((TaxiwayPointModel)taxiwayPointHM.get(new Integer(start))).getLatLon().getLat());
                            }
                            if(taxiwayParkingHM.containsKey(new Integer(end)))
                            {
                                taxiwayPathModel.setEndLon(((TaxiwayParkingModel)taxiwayParkingHM.get(new Integer(end))).getLatLon().getLon());
                                taxiwayPathModel.setEndLat(((TaxiwayParkingModel)taxiwayParkingHM.get(new Integer(end))).getLatLon().getLat());
                            }
                        } else
                        {
                            if(taxiwayPointHM.containsKey(new Integer(start)))
                            {
                                taxiwayPathModel.setStartLon(((TaxiwayPointModel)taxiwayPointHM.get(new Integer(start))).getLatLon().getLon());
                                taxiwayPathModel.setStartLat(((TaxiwayPointModel)taxiwayPointHM.get(new Integer(start))).getLatLon().getLat());
                            }
                            if(taxiwayPointHM.containsKey(new Integer(end)))
                            {
                                taxiwayPathModel.setEndLon(((TaxiwayPointModel)taxiwayPointHM.get(new Integer(end))).getLatLon().getLon());
                                taxiwayPathModel.setEndLat(((TaxiwayPointModel)taxiwayPointHM.get(new Integer(end))).getLatLon().getLat());
                            }
                        }
                    }
                    taxiwayPathModel.paint(g2, true);
                    if(!first || taxiwayPathModel.getLeftEdgeLine() == null)
                        continue;
                    TaxiwayPathDisplayModel taxiwayPathDisplayModel = new TaxiwayPathDisplayModel(taxiwayPathModel.getLeftEdgeLine(), taxiwayPathModel.getRightEdgeLine(), taxiwayPathModel.getLeftEdge(), taxiwayPathModel.getRightEdge(), taxiwayPathModel.getType(), taxiwayPathModel.getStart(), taxiwayPathModel.getEnd(), taxiwayPathModel.getWidth(), taxiwayPathModel.getLeftEdgeLighted(), taxiwayPathModel.getRightEdgeLighted(), taxiwayPathModel.getWidthMeasure());
                    if(taxiwayPathModel.getType().equals("PARKING"))
                        airportModel.addTaxiwayPathDisplayModel(taxiwayPathDisplayModel, true);
                    else
                        airportModel.addTaxiwayPathDisplayModel(taxiwayPathDisplayModel, false);
                }

            }

            private void paintTaxiwayLines(Graphics2D g2)
            {
                HashMap hashMap = airportModel.getTaxiwayPathDisplayHM();
                Iterator iterator = hashMap.keySet().iterator();
                do
                {
                    if(!iterator.hasNext())
                        break;
                    Integer index = (Integer)iterator.next();
                    ArrayList taxiwayPathDisplayModelAL = (ArrayList)hashMap.get(index);
                    Collections.sort(taxiwayPathDisplayModelAL, HeadingSort.getInstance(index.intValue()));
                    if(taxiwayPathDisplayModelAL.size() > 1)
                    {
                        int i = taxiwayPathDisplayModelAL.size() - 1;
                        while(i >= 0) 
                        {
                            TaxiwayPathDisplayModel model1 = (TaxiwayPathDisplayModel)taxiwayPathDisplayModelAL.get(i);
                            TaxiwayPathDisplayModel model2;
                            if(i > 0)
                                model2 = (TaxiwayPathDisplayModel)taxiwayPathDisplayModelAL.get(i - 1);
                            else
                                model2 = (TaxiwayPathDisplayModel)taxiwayPathDisplayModelAL.get(taxiwayPathDisplayModelAL.size() - 1);
                            Line2D line1 = model1.getLeftEdgeLine(index.intValue());
                            Line2D line2 = model2.getRightEdgeLine(index.intValue());
                            float x11 = (float)line1.getX1();
                            float y11 = (float)line1.getY1();
                            float x12 = (float)line1.getX2();
                            float y12 = (float)line1.getY2();
                            if(!model1.isAtBeginning(index.intValue()))
                            {
                                x11 = (float)line1.getX2();
                                y11 = (float)line1.getY2();
                                x12 = (float)line1.getX1();
                                y12 = (float)line1.getY1();
                            }
                            float x21 = (float)line2.getX1();
                            float y21 = (float)line2.getY1();
                            float x22 = (float)line2.getX2();
                            float y22 = (float)line2.getY2();
                            if(!model2.isAtBeginning(index.intValue()))
                            {
                                x21 = (float)line2.getX2();
                                y21 = (float)line2.getY2();
                                x22 = (float)line2.getX1();
                                y22 = (float)line2.getY1();
                            }
                            float b1 = (y11 - y12) / (x11 - x12);
                            float b2 = (y21 - y22) / (x21 - x22);
                            float a1 = y11 - b1 * x11;
                            float a2 = y21 - b2 * x21;
                            float xi;
                            if(b1 == (1.0F / 0.0F) || b1 == (-1.0F / 0.0F) || a1 == (1.0F / 0.0F) || a1 == (-1.0F / 0.0F))
                                xi = x11;
                            else
                            if(b2 == (1.0F / 0.0F) || b2 == (-1.0F / 0.0F) || a2 == (1.0F / 0.0F) || a2 == (-1.0F / 0.0F))
                                xi = x21;
                            else
                                xi = -(a1 - a2) / (b1 - b2);
                            float yi;
                            if(b1 == 0.0F || a1 == 0.0F)
                                yi = y11;
                            else
                            if(b2 == 0.0F || a2 == 0.0F)
                                yi = y21;
                            else
                            if(b1 == (1.0F / 0.0F) || b1 == (-1.0F / 0.0F))
                            {
                                if(a1 == (1.0F / 0.0F) || a1 == (-1.0F / 0.0F))
                                    yi = y22;
                                else
                                    yi = a1 + xi;
                            } else
                            {
                                yi = a1 + b1 * xi;
                            }
                            double length1 = Math.sqrt(Math.pow(x12 - x11, 2D) + Math.pow(y12 - y11, 2D));
                            double length2 = Math.sqrt(Math.pow(x22 - x21, 2D) + Math.pow(y22 - y21, 2D));
                            double length3;
                            if(model1.isAtBeginning(index.intValue()))
                                length3 = Math.sqrt(Math.pow((double)xi - line1.getX1(), 2D) + Math.pow((double)yi - line1.getY1(), 2D));
                            else
                                length3 = Math.sqrt(Math.pow(line1.getX2() - (double)xi, 2D) + Math.pow(line1.getY2() - (double)yi, 2D));
                            double length4;
                            if(model2.isAtBeginning(index.intValue()))
                                length4 = Math.sqrt(Math.pow((double)xi - line2.getX1(), 2D) + Math.pow((double)yi - line2.getY1(), 2D));
                            else
                                length4 = Math.sqrt(Math.pow(line2.getX2() - (double)xi, 2D) + Math.pow(line2.getY2() - (double)yi, 2D));
                            if(model1.isAtBeginning(index.intValue()))
                            {
                                if(length3 < length1 + length2 && length4 < length1 + length2)
                                    line1.setLine(line1.getX1(), line1.getY1(), xi, yi);
                            } else
                            if(length3 < length1 + length2 && length4 < length1 + length2)
                                line1.setLine(xi, yi, line1.getX2(), line1.getY2());
                            if(model2.isAtBeginning(index.intValue()))
                            {
                                if(length3 < length1 + length2 && length4 < length1 + length2)
                                    line2.setLine(line2.getX1(), line2.getY1(), xi, yi);
                            } else
                            if(length3 < length1 + length2 && length4 < length1 + length2)
                                line2.setLine(xi, yi, line2.getX2(), line2.getY2());
                            i--;
                        }
                    }
                } while(true);
                g2.setPaint(ColorsEngine.getInstance().getTaxiwayLineColor());
                ArrayList arrayList = airportModel.getTaxiwayPathDisplayAL();
                float dashSize = 24F * scale;
                for(int i = arrayList.size() - 1; i >= 0; i--)
                {
                    TaxiwayPathDisplayModel taxiwayPathDisplayModel = (TaxiwayPathDisplayModel)arrayList.get(i);
                    if(taxiwayPathDisplayModel.getLeftEdge().equals("SOLID"))
                        g2.draw(taxiwayPathDisplayModel.getLeftEdgeLine());
                    else
                    if(taxiwayPathDisplayModel.getLeftEdge().equals("DASHED"))
                    {
                        g2.setStroke(new BasicStroke(1.0F, 0, 1, 0.0F, new float[] {
                            dashSize, dashSize
                        }, 0.0F));
                        g2.draw(taxiwayPathDisplayModel.getLeftEdgeLine());
                        g2.setStroke(new BasicStroke());
                    }
                    if(taxiwayPathDisplayModel.getRightEdge().equals("SOLID"))
                    {
                        g2.draw(taxiwayPathDisplayModel.getRightEdgeLine());
                        continue;
                    }
                    if(taxiwayPathDisplayModel.getRightEdge().equals("DASHED"))
                    {
                        g2.setStroke(new BasicStroke(1.0F, 0, 1, 0.0F, new float[] {
                            dashSize, dashSize
                        }, 0.0F));
                        g2.draw(taxiwayPathDisplayModel.getRightEdgeLine());
                        g2.setStroke(new BasicStroke());
                    }
                }

            }

            private AirportModel airportModel;
            private LatLonPoint centerPoint;
            private HashMap rectangleHM;
            private boolean printing;
            private double centerX;
            private double centerY;
            private double imageableX;
            private double imageableY;
            private double imageableWidth;
            private double imageableHeight;
            private float scale;
            private int footerHeight;
            final PrintEngine this$0;

            public AirportPrint(AirportModel airportModel)
            {
                super();
                this$0 = PrintEngine.this;
                this.airportModel = airportModel;
                centerPoint = new LatLonPoint(airportModel.getLatLon().getLat(), airportModel.getLatLon().getLon());
                rectangleHM = ExclusionEngine.getInstance().getRectangle(airportModel);
                scale = 0.2F;
                footerHeight = 15;
            }
        }

        AirportPrint airportPrint = new AirportPrint(airportModel);
        printerJob.setPrintable(airportPrint, pageFormat);
        if(printerJob.printDialog())
            try
            {
                printerJob.print();
            }
            catch(PrinterException pe)
            {
                LogEngine.getInstance().log(pe);
            }
    }

    public void actionPerformed(ActionEvent event)
    {
        if(event.getSource() == printButton)
        {
            printAirport();
            setVisible(false);
        } else
        if(event.getSource() == cancelButton)
            setVisible(false);
    }

    private AirportModel airportModel;
    private JRadioButton airportRB;
    private JRadioButton radiusRB;
    private JCheckBox backgroundCB;
    private JSpinner widthSpinner;
    private JSpinner heightSpinner;
    private JLabel marginLabel;
    private JTextField topMarginTF;
    private JTextField bottomMarginTF;
    private JTextField leftMarginTF;
    private JTextField rightMarginTF;
    private String measurements;
    private JButton printButton;
    private JButton cancelButton;
    private static PrintEngine engine = null;
}