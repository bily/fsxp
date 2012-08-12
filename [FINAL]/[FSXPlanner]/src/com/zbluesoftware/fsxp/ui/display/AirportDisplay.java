// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AirportDisplay.java

package com.zbluesoftware.fsxp.ui.display;

import com.zbluesoftware.fsxp.comparator.HeadingSort;
import com.zbluesoftware.fsxp.engine.*;
import com.zbluesoftware.fsxp.menu.CursorPopupMenu;
import com.zbluesoftware.fsxp.menu.FSXPMenuBar;
import com.zbluesoftware.fsxp.model.*;
import com.zbluesoftware.fsxp.ribbon.ZPopupGallery;
import com.zbluesoftware.fsxp.simconnect.FSXConnection;
import com.zbluesoftware.fsxp.thread.ILSLineUpdates;
import com.zbluesoftware.fsxp.util.LatLonPoint;
import com.zbluesoftware.fsxp.util.Utilities;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.NumberFormat;
import java.util.*;
import javax.swing.*;

public class AirportDisplay extends JComponent
    implements ComponentListener, MouseListener, MouseMotionListener, MouseWheelListener, PropertyChangeListener
{

    public AirportDisplay(AirportModel airportModel)
    {
        this.airportModel = airportModel;
        scale = 0.2F;
        airportModel.setScale(scale);
        centerPoint = new LatLonPoint(airportModel.getLatLon().getLat(), airportModel.getLatLon().getLon());
        mouseLatLon = new LatLonPoint(0.0D, 0.0D);
        centerSet = false;
        recreate = true;
        recreateBGImage = true;
        recreateBGAllImage = true;
        drawing = false;
        redrawScroll = true;
        secondsFormat = NumberFormat.getInstance();
        secondsFormat.setMinimumFractionDigits(0);
        secondsFormat.setMaximumFractionDigits(0);
        displayLights = SettingsEngine.getInstance().getDisplayLights();
        displayFPS = SettingsEngine.getInstance().getDisplayFPS();
        doubleBuffer = SettingsEngine.getInstance().getDoubleBuffer();
        fpsFormat = NumberFormat.getInstance();
        fpsFormat.setMaximumFractionDigits(1);
        fpsFormat.setMinimumFractionDigits(1);
        bgImagesBelow = SettingsEngine.getInstance().getBGImagesBelow();
        SettingsEngine.getInstance().addPropertyChangeListener(this);
        setCursor(CursorEngine.getInstance().getDefaultCursor());
        addMouseListener(this);
        addMouseWheelListener(this);
        addMouseMotionListener(this);
        addComponentListener(this);
    }

    public void setRecreate(boolean recreate)
    {
        this.recreate = recreate;
    }

    public void paint(Graphics g)
    {
        paint(g, null, false);
    }

    public void paint(Graphics g, Shape clip, boolean buffering)
    {
        long startTime = System.currentTimeMillis();
        drawing = true;
        if(!centerSet)
        {
            centerX = (double)getSize().width / 2D;
            centerY = (double)getSize().height / 2D;
            oldX = getSize().width / 2;
            oldY = getSize().height / 2;
            centerSet = true;
            recreateBGImage = true;
            displayImage = null;
        }
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if(buffering)
        {
            currentFrameBI = new BufferedImage(getSize().width, getSize().height, 6);
            g2 = currentFrameBI.createGraphics();
        }
        g2.translate((int)centerX, (int)centerY);
        double rotation = 0.0D;
        if((rotation = ((RotationModel)airportModel.getRotationAL().get(0)).getRotation()) > 0.0D)
        {
            g.setColor(ColorsEngine.getInstance().getBackgroundColor());
            g.fillRect((int)(-centerX), (int)(-centerY), getSize().width, getSize().height);
            g2.rotate(Math.toRadians(rotation));
        }
        if(clip != null)
            g2.setClip(clip);
        g2.setColor(ColorsEngine.getInstance().getBackgroundColor());
        g2.fillRect((int)(-centerX), (int)(-centerY), getSize().width, getSize().height);
        if(SettingsEngine.getInstance().getDisplayLatLon())
            paintLatLons(g2);
        drawAirport(g2);
        recreate = false;
        redrawScroll = true;
        if(rotation > 0.0D)
            g2.rotate(Math.toRadians(-rotation));
        if(displayFPS)
        {
            if(clip != null)
            {
                g2.setClip(new Rectangle((int)(-centerX), (int)(-centerY), 90, 15));
                g2.setColor(ColorsEngine.getInstance().getBackgroundColor());
                g2.fillRect((int)(-centerX), (int)(-centerY), getSize().width, getSize().height);
            }
            long endTime = System.currentTimeMillis();
            g2.setFont(new Font("Arial", 0, 10));
            g2.setColor(Color.red);
            g2.drawString((new StringBuilder()).append("fps: ").append(fpsFormat.format(1000F / (float)(endTime - startTime))).append(" [").append(endTime - startTime).append("ms]").toString(), (int)(10D - centerX), (int)(10D - centerY));
        }
        if(SettingsEngine.getInstance().getDisplayRotation())
        {
            for(int i = airportModel.getRotationAL().size() - 1; i >= 0; i--)
            {
                RotationModel rotationModel = (RotationModel)airportModel.getRotationAL().get(i);
                rotationModel.setOffset(new java.awt.geom.Point2D.Float((float)((double)getSize().width - centerX - 60D), (float)(10D - centerY)));
                rotationModel.paint(g2, recreate);
            }

        }
        if(buffering)
            g.drawImage(currentFrameBI, 0, 0, null);
        drawing = false;
    }

    public void scrollDisplay(Graphics g, float deltaX, float deltaY)
    {
        long startTime = System.currentTimeMillis();
        drawing = true;
        if(displayImage == null)
        {
            displayImage = new BufferedImage(getSize().width, getSize().height, 6);
            System.gc();
        }
        currentFrameBI = new BufferedImage(getSize().width, getSize().height, 6);
        Graphics2D g2 = currentFrameBI.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.translate((int)centerX, (int)centerY);
        double rotation = 0.0D;
        rotation = ((RotationModel)airportModel.getRotationAL().get(0)).getRotation();
        if(!redrawScroll)
        {
            g2.drawImage(lastFrameBI, (int)((double)deltaX - centerX), (int)((double)deltaY - centerY), null);
            if(deltaX != 0.0F)
            {
                GeneralPath clipPath = new GeneralPath();
                if(deltaX > 0.0F)
                {
                    clipPath.moveTo((int)(-centerX), (int)(-centerY));
                    clipPath.lineTo((int)((double)deltaX - centerX), (int)(-centerY));
                    clipPath.lineTo((int)((double)deltaX - centerX), (int)((double)getSize().height - centerY));
                    clipPath.lineTo((int)(-centerX), (int)((double)getSize().height - centerY));
                    clipPath.lineTo((int)(-centerX), (int)(-centerY));
                } else
                {
                    clipPath.moveTo((int)((double)getSize().width - centerX), (int)(-centerY));
                    clipPath.lineTo((int)((double)((float)getSize().width + deltaX) - centerX), (int)(-centerY));
                    clipPath.lineTo((int)((double)((float)getSize().width + deltaX) - centerX), (int)((double)getSize().height - centerY));
                    clipPath.lineTo((int)((double)getSize().width - centerX), (int)((double)getSize().height - centerY));
                    clipPath.lineTo((int)((double)getSize().width - centerX), (int)(-centerY));
                }
                g2.setClip(clipPath);
                if(rotation > 0.0D)
                    g2.rotate(Math.toRadians(rotation));
                drawAirport(g2);
            }
            if(deltaY != 0.0F)
            {
                GeneralPath clipPath = new GeneralPath();
                if(deltaY < 0.0F)
                {
                    clipPath.moveTo((float)(int)(-centerX) + Math.max(deltaX, 0.0F), (int)((double)getSize().height - centerY));
                    clipPath.lineTo((float)(int)(-centerX) + Math.max(deltaX, 0.0F), (int)((double)((float)getSize().height + deltaY) - centerY));
                    clipPath.lineTo((int)(((double)getSize().width - centerX) + (double)Math.min(deltaX, 0.0F)), (int)((double)((float)getSize().height + deltaY) - centerY));
                    clipPath.lineTo((int)(((double)getSize().width - centerX) + (double)Math.min(deltaX, 0.0F)), (int)((double)getSize().height - centerY));
                    clipPath.lineTo((float)(int)(-centerX) + Math.max(deltaX, 0.0F), (int)((double)getSize().height - centerY));
                } else
                {
                    clipPath.moveTo((float)(int)(-centerX) + Math.max(deltaX, 0.0F), (int)(-centerY));
                    clipPath.lineTo((int)(((double)getSize().width - centerX) + (double)Math.min(deltaX, 0.0F)), (int)(-centerY));
                    clipPath.lineTo((int)(((double)getSize().width - centerX) + (double)Math.min(deltaX, 0.0F)), (int)((double)deltaY - centerY));
                    clipPath.lineTo((float)(int)(-centerX) + Math.max(deltaX, 0.0F), (int)((double)deltaY - centerY));
                    clipPath.lineTo((float)(int)(-centerX) + Math.max(deltaX, 0.0F), (int)(-centerY));
                }
                if(rotation > 0.0D && deltaX != 0.0F)
                    g2.rotate(Math.toRadians(-rotation));
                g2.setClip(clipPath);
                if(rotation > 0.0D)
                    g2.rotate(Math.toRadians(rotation));
                drawAirport(g2);
            }
        } else
        {
            g2.setClip(new Rectangle((int)(-centerX), (int)(-centerY), getSize().width, getSize().height));
            if(rotation > 0.0D)
                g2.rotate(Math.toRadians(rotation));
            drawAirport(g2);
        }
        recreate = false;
        Graphics2D g3 = displayImage.createGraphics();
        g3.setColor(ColorsEngine.getInstance().getBackgroundColor());
        g3.fillRect(0, 0, getSize().width, getSize().height);
        if(SettingsEngine.getInstance().getDisplayLatLon())
        {
            g3.translate((int)centerX, (int)centerY);
            if(rotation > 0.0D)
                g3.rotate(Math.toRadians(rotation));
            paintLatLons(g3);
            if(rotation > 0.0D)
                g3.rotate(Math.toRadians(-rotation));
            g3.translate((int)(-centerX), (int)(-centerY));
        }
        g3.drawImage(currentFrameBI, 0, 0, null);
        g.drawImage(displayImage, 0, 0, null);
        lastFrameBI = currentFrameBI;
        if(displayFPS)
        {
            long endTime = System.currentTimeMillis();
            g.setFont(new Font("Arial", 0, 10));
            g.setColor(Color.red);
            g.drawString((new StringBuilder()).append("fps: ").append(fpsFormat.format(1000F / (float)(endTime - startTime))).append(" [").append(endTime - startTime).append("ms]").toString(), 10, 10);
        }
        if(SettingsEngine.getInstance().getDisplayRotation())
        {
            for(int i = airportModel.getRotationAL().size() - 1; i >= 0; i--)
            {
                RotationModel rotationModel = (RotationModel)airportModel.getRotationAL().get(i);
                rotationModel.setOffset(new java.awt.geom.Point2D.Float(getSize().width - 60, 10F));
                rotationModel.paint((Graphics2D)g, recreate);
            }

        }
        drawing = false;
        redrawScroll = false;
    }

    private void drawAirport(Graphics2D g2)
    {
        BaseModel baseModel = SelectedItem.getInstance().getBaseModel();
        if(bgImagesBelow && SettingsEngine.getInstance().getDisplayBGImage())
            drawBGImage(g2);
        airportModel.clearTaxiwayPathDisplayModels();
        if(SettingsEngine.getInstance().getDisplayApron())
            paintItems(g2, airportModel.getApronAL(), recreate || creatingApron || (baseModel instanceof ApronModel));
        paintTaxiwayPaths(g2, recreate || (baseModel instanceof TaxiwayPointModel) || (baseModel instanceof TaxiwayParkingModel), true);
        if(SettingsEngine.getInstance().getDisplayRunway())
            paintItems(g2, airportModel.getRunwayAL(), recreate || (baseModel instanceof RunwayModel));
        paintTaxiwayPaths(g2, recreate || (baseModel instanceof TaxiwayPointModel) || (baseModel instanceof TaxiwayParkingModel), false);
        if((double)scale >= 0.10000000000000001D && SettingsEngine.getInstance().getDisplayTWPoint())
            paintItems(g2, airportModel.getTaxiwayPointHM(), recreate || (baseModel instanceof TaxiwayPointModel));
        if(SettingsEngine.getInstance().getDisplayParking())
            paintItems(g2, airportModel.getTaxiwayParkingHM(), recreate || (baseModel instanceof TaxiwayParkingModel));
        if((double)scale >= 0.10000000000000001D)
            paintTaxiwayLines(g2);
        if(SettingsEngine.getInstance().getDisplayJetways())
            paintItems(g2, airportModel.getJetwayAL(), recreate || (baseModel instanceof JetwayModel));
        if(SettingsEngine.getInstance().getDisplayTWSign())
            paintItems(g2, airportModel.getTaxiwaySignAL(), recreate || (baseModel instanceof TaxiwaySignModel));
        if(SettingsEngine.getInstance().getDisplayTower())
            paintItems(g2, airportModel.getTowerAL(), recreate || (baseModel instanceof TowerModel));
        if(SettingsEngine.getInstance().getDisplayBoundFence())
            paintItems(g2, airportModel.getBoundaryFenceAL(), recreate || (baseModel instanceof BoundaryFenceModel));
        if(SettingsEngine.getInstance().getDisplayBlastFence())
            paintItems(g2, airportModel.getBlastFenceAL(), recreate || (baseModel instanceof BlastFenceModel));
        if(SettingsEngine.getInstance().getDisplayHelipad())
            paintItems(g2, airportModel.getHelipadAL(), recreate || (baseModel instanceof HelipadModel));
        if(SettingsEngine.getInstance().getDisplayStart())
            paintItems(g2, airportModel.getStartAL(), recreate || (baseModel instanceof StartModel));
        if(SettingsEngine.getInstance().getDisplayApronEL() && !modifyingApron)
            paintItems(g2, airportModel.getApronEdgeLightAL(), recreate || (baseModel instanceof ApronEdgeLightModel));
        if(SettingsEngine.getInstance().getDisplayILS())
            paintItems(g2, airportModel.getILSModels(), recreate || (baseModel instanceof ILSModel) || (baseModel instanceof DMEModel) || (baseModel instanceof GlideSlopeModel));
        if(SettingsEngine.getInstance().getDisplayMarkers())
            paintItems(g2, airportModel.getMarkerAL(), recreate || (baseModel instanceof MarkerModel));
        if(SettingsEngine.getInstance().getDisplayVORs())
            paintItems(g2, airportModel.getVORAL(), recreate || (baseModel instanceof VORModel) || (baseModel instanceof DMEModel));
        if(SettingsEngine.getInstance().getDisplayNDBs())
            paintItems(g2, airportModel.getNDBAL(), recreate || (baseModel instanceof NDBModel));
        if(SettingsEngine.getInstance().getDisplayWindsocks())
            paintItems(g2, airportModel.getWindsockAL(), recreate || (baseModel instanceof WindsockModel));
        if(SettingsEngine.getInstance().getDisplayScenery())
            paintItems(g2, airportModel.getSceneryAL(), recreate || (baseModel instanceof SceneryModel));
        if(SettingsEngine.getInstance().getDisplayExcludes())
            paintItems(g2, airportModel.getExclusionAL(), recreate || creatingExclude || (baseModel instanceof ExclusionModel));
        if(SettingsEngine.getInstance().getDisplayTriggers())
            paintItems(g2, airportModel.getTriggerAL(), recreate || creatingTrigger || (baseModel instanceof TriggerModel));
        if(FSXConnection.getInstance().getShowPlane())
            paintItems(g2, airportModel.getPlaneAL(), recreate || (baseModel instanceof PlaneModel));
        if(SettingsEngine.getInstance().getDisplayAirportCtr())
        {
            g2.setPaint(new Color(255, 0, 255));
            g2.fill(new java.awt.geom.Ellipse2D.Double(-8D, -8D, 16D, 16D));
            g2.draw(new java.awt.geom.Line2D.Double(0.0D, -15D, 0.0D, 15D));
            g2.draw(new java.awt.geom.Line2D.Double(-15D, 0.0D, 15D, 0.0D));
        }
        if(SettingsEngine.getInstance().getDisplayTestRadius())
            paintTestRadius(g2);
        if(!bgImagesBelow && SettingsEngine.getInstance().getDisplayBGImage())
            drawBGImage(g2);
        if(startPoint != null && endPoint != null)
        {
            g2.setPaint(Color.black);
            g2.draw(new java.awt.geom.Line2D.Double((double)startPoint.x - centerX, (double)startPoint.y - centerY, (double)endPoint.x - centerX, (double)endPoint.y - centerY));
        }
        if(apronModel != null)
        {
            apronModel.setAlpha(128);
            apronModel.paint(g2, recreate);
            apronModel.setAlpha(255);
        }
        if(displayLights)
        {
            Color nightColor = ColorsEngine.getInstance().getNightColor();
            g2.setPaint(new Color(nightColor.getRed(), nightColor.getGreen(), nightColor.getBlue(), 200));
            g2.fillRect((int)(-centerX), (int)(-centerY), getSize().width, getSize().height);
            ArrayList arrayList = airportModel.getTaxiwayPathDisplayAL();
            for(int i = arrayList.size() - 1; i >= 0; i--)
                ((TaxiwayPathDisplayModel)arrayList.get(i)).paintLights(g2, scale);

            ArrayList taxiwayPathAL = airportModel.getTaxiwayPathAL();
            for(int i = taxiwayPathAL.size() - 1; i >= 0; i--)
                ((TaxiwayPathModel)taxiwayPathAL.get(i)).paintLights(g2);

            ArrayList runwayAL = airportModel.getRunwayAL();
            for(int i = runwayAL.size() - 1; i >= 0; i--)
                ((RunwayModel)runwayAL.get(i)).paintLights(g2);

        }
    }

    private void drawBGImage(Graphics2D g2)
    {
        ArrayList bgImageAL = airportModel.getBGImageAL();
        for(int i = 0; i < bgImageAL.size(); i++)
        {
            BackgroundImageModel bgImageModel = (BackgroundImageModel)bgImageAL.get(i);
            bgImageModel.setRecreateBGImage(recreateBGImage);
            bgImageModel.setRecreateBGAllImage(recreateBGAllImage);
            bgImageModel.setDisplaySize(getSize());
            bgImageModel.setCenterPoint(centerPoint);
            bgImageModel.setScale(scale);
            bgImageModel.paint(g2, recreate);
        }

        recreateBGImage = false;
        recreateBGAllImage = false;
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
        g2.setFont(Utilities.LAT_LON_FONT);
        double secondIncrement = 0.00027777777777777778D * (double)SettingsEngine.getInstance().getSecondsIncrement();
        for(double i = leftLon; i < rightLon; i += secondIncrement)
        {
            java.awt.geom.Point2D.Float topPoint = Utilities.getPixelsBetweenPoints(centerPoint.getLat(), centerPoint.getLon(), topLat, i, scale);
            java.awt.geom.Point2D.Float bottomPoint = Utilities.getPixelsBetweenPoints(centerPoint.getLat(), centerPoint.getLon(), bottomLat, i, scale);
            g2.draw(new java.awt.geom.Line2D.Float(topPoint.x, topPoint.y, bottomPoint.x, bottomPoint.y));
            g2.drawString(DisplayEngine.getInstance().formatGridLongitude(i), bottomPoint.x + 2.0F, (float)((double)getSize().height - centerY));
        }

        for(double i = bottomLat; i < topLat; i += secondIncrement)
        {
            java.awt.geom.Point2D.Float leftPoint = Utilities.getPixelsBetweenPoints(centerPoint.getLat(), centerPoint.getLon(), i, leftLon, scale);
            java.awt.geom.Point2D.Float rightPoint = Utilities.getPixelsBetweenPoints(centerPoint.getLat(), centerPoint.getLon(), i, rightLon, scale);
            g2.draw(new java.awt.geom.Line2D.Float(leftPoint.x, leftPoint.y, rightPoint.x, rightPoint.y));
            g2.drawString(DisplayEngine.getInstance().formatGridLatitude(i), (float)(-centerX + 2D), leftPoint.y - 2.0F);
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

    private void paintItems(Graphics2D g2, ArrayList itemAL, boolean recreate)
    {
        for(int i = itemAL.size() - 1; i >= 0; i--)
        {
            BaseModel baseModel = (BaseModel)itemAL.get(i);
            baseModel.setCenterPoint(centerPoint);
            baseModel.setScale(scale);
            baseModel.paint(g2, recreate);
        }

    }

    private void paintItems(Graphics2D g2, HashMap itemHM, boolean recreate)
    {
        BaseModel baseModel;
        for(Iterator iterator = itemHM.keySet().iterator(); iterator.hasNext(); baseModel.paint(g2, recreate))
        {
            baseModel = (BaseModel)itemHM.get((Integer)iterator.next());
            baseModel.setCenterPoint(centerPoint);
            baseModel.setScale(scale);
        }

    }

    private void paintTaxiwayPaths(Graphics2D g2, boolean recreate, boolean first)
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
                taxiwayPathModel.setHighlightedTWPath(airportModel.getHighlightedTWPath());
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
            taxiwayPathModel.paint(g2, recreate);
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
        ArrayList arrayList = airportModel.getTaxiwayPathDisplayAL();
        for(int i = arrayList.size() - 1; i >= 0; i--)
            ((TaxiwayPathDisplayModel)arrayList.get(i)).paint(g2, scale);

        g2.setStroke(Utilities.DEFAULT_STROKE);
    }

    private void zoomFromWheel(Point point, int zoom)
    {
        float oldScale = scale;
        float zoomFactor = 0.01F;
        if((double)scale > 1.5D)
            zoomFactor = 0.5F;
        else
        if((double)scale > 0.75D)
            zoomFactor = 0.25F;
        else
        if((double)scale > 0.20000000000000001D)
            zoomFactor = 0.1F;
        scale -= (float)zoom * zoomFactor;
        if((double)scale < 0.0050000000000000001D)
            scale = 0.005F;
        else
        if(scale > 4F)
            scale = 4F;
        float adjust = scale / oldScale;
        if(point == null)
        {
            centerX = (centerX - (double)getSize().width / 2D) * (double)adjust + (double)getSize().width / 2D;
            centerY = (centerY - (double)getSize().height / 2D) * (double)adjust + (double)getSize().height / 2D;
        } else
        {
            centerX += (double)getSize().width / 2D - (double)point.x;
            centerX = (centerX - (double)getSize().width / 2D) * (double)adjust + (double)getSize().width / 2D;
            centerY += (double)getSize().height / 2D - (double)point.y;
            centerY = (centerY - (double)getSize().height / 2D) * (double)adjust + (double)getSize().height / 2D;
        }
        airportModel.setScale(scale);
        recreate = true;
        recreateBGImage = true;
        recreateBGAllImage = true;
        repaint();
        AlertModel.getInstance().firePropertyChange("scale", new Float(oldScale), new Float(scale));
    }

    private void rotateFromWheel(int delta)
    {
        double rotation = ((RotationModel)airportModel.getRotationAL().get(0)).getRotation() + (double)delta;
        if(rotation < 0.0D)
            rotation = 359D;
        else
        if(rotation > 359D)
            rotation = 0.0D;
        ((RotationModel)airportModel.getRotationAL().get(0)).setRotation(rotation);
        recreate = true;
        recreateBGImage = true;
        recreateBGAllImage = true;
        repaint();
    }

    public void recenter()
    {
        ((RotationModel)airportModel.getRotationAL().get(0)).setRotation(0.0D);
        scale = 0.2F;
        centerSet = false;
        recreate = true;
        repaint();
    }

    private synchronized void scrollAirport(int x, int y)
    {
        centerX += x - oldX;
        centerY += y - oldY;
        float deltaX = x - oldX;
        float deltaY = y - oldY;
        oldX = x;
        oldY = y;
        recreate = false;
        recreateBGImage = true;
        scrollDisplay(getGraphics(), deltaX, deltaY);
    }

    private boolean checkItems(Point point, ArrayList itemAL)
    {
        for(int i = itemAL.size() - 1; i >= 0; i--)
            if(((BaseModel)itemAL.get(i)).isWithinObject((int)((double)point.x - centerX), (int)((double)point.y - centerY)))
            {
                ((BaseModel)itemAL.get(i)).setCurrentlySelected(true);
                repaint();
                requestFocus();
                return true;
            }

        return false;
    }

    private boolean checkItems(Point point, HashMap itemHM)
    {
        for(Iterator iterator = itemHM.keySet().iterator(); iterator.hasNext();)
        {
            BaseModel baseModel = (BaseModel)itemHM.get((Integer)iterator.next());
            if(baseModel.isWithinObject((int)((double)point.x - centerX), (int)((double)point.y - centerY)))
            {
                baseModel.setCurrentlySelected(true);
                repaint();
                requestFocus();
                return true;
            }
        }

        return false;
    }

    public void movePlaneToCursor()
    {
        LatLonPoint latLonPoint = Utilities.getLatLonForPixel(centerPoint.getLat(), centerPoint.getLon(), mouseClickPoint.getX() - centerX, mouseClickPoint.getY() - centerY, scale);
        ((PlaneModel)airportModel.getPlaneAL().get(0)).moveTo(latLonPoint, (double)oldX - centerX, (double)oldY - centerY);
        ((PlaneModel)airportModel.getPlaneAL().get(0)).setCurrentlySelected(true);
        if(FSXConnection.getInstance().isConnectedToFSX())
        {
            float airportAltitude = (float)airportModel.getAlt();
            if(airportModel.getAltMeasure().equals("M"))
                airportAltitude *= 3.28F;
            FSXConnection.getInstance().movePlane(latLonPoint, ((PlaneModel)airportModel.getPlaneAL().get(0)).getHeading());
        }
        selectShowPlane();
        recreate = false;
        repaint();
    }

    public void movePlaneToFSX(boolean shouldSelect, boolean displayMessages)
    {
        LatLonPoint fsxLatLon = FSXConnection.getInstance().getPlaneLatLon();
        if(fsxLatLon == null)
        {
            if(displayMessages)
                JOptionPane.showMessageDialog(this, "Location of the plane in FSX is unknown.", "Unknown Location...", 0);
            return;
        }
        float testRadius = (float)airportModel.getAirportTestRadius();
        if(airportModel.getAirportTestRadiusMeasure().equals("M"))
            testRadius *= 3.28F;
        else
        if(airportModel.getAirportTestRadiusMeasure().equals("N"))
            testRadius *= 6074.56F;
        java.awt.geom.Point2D.Float distancePoint = Utilities.getPixelsBetweenPoints(airportModel.getLatLon().getLat(), airportModel.getLatLon().getLon(), fsxLatLon.getLat(), fsxLatLon.getLon(), 1.0F);
        float distance = (float)Math.sqrt(Math.pow(distancePoint.getX(), 2D) + Math.pow(distancePoint.getY(), 2D));
        if(distance < testRadius)
        {
            ((PlaneModel)airportModel.getPlaneAL().get(0)).moveTo((LatLonPoint)fsxLatLon.clone(), 0.0D, 0.0D);
            ((PlaneModel)airportModel.getPlaneAL().get(0)).setHeading((float)FSXConnection.getInstance().getPlaneHeading());
            if(shouldSelect)
                ((PlaneModel)airportModel.getPlaneAL().get(0)).setCurrentlySelected(true);
            java.awt.geom.Point2D.Float distanceOffset = Utilities.getPixelsBetweenPoints(centerPoint.getLat(), centerPoint.getLon(), fsxLatLon.getLat(), fsxLatLon.getLon(), scale);
            centerX = (double)(distanceOffset.x * -1F) + (double)getSize().width / 2D;
            centerY = (double)(distanceOffset.y * -1F) + (double)getSize().height / 2D;
            selectShowPlane();
            recreate = false;
            repaint();
        } else
        if(displayMessages)
            JOptionPane.showMessageDialog(this, "The plane's location is outside the airport test radius.", "Plane Too Far Away...", 0);
    }

    private void findItem(Point point, Point rotatedPoint)
    {
        if(FSXConnection.getInstance().getShowPlane() && checkItems(rotatedPoint, airportModel.getPlaneAL()))
            return;
        if(SettingsEngine.getInstance().getDisplayRotation() && checkItems(point, airportModel.getRotationAL()))
            return;
        if(SettingsEngine.getInstance().getDisplayExcludes() && checkItems(rotatedPoint, airportModel.getExclusionAL()))
        {
            vertexModel = ((ExclusionModel)SelectedItem.getInstance().getBaseModel()).isWithinVertex((int)((double)rotatedPoint.x - centerX), (int)((double)rotatedPoint.y - centerY));
            ((ExclusionModel)SelectedItem.getInstance().getBaseModel()).displayVertexModel(vertexModel);
            return;
        }
        if(SettingsEngine.getInstance().getDisplayTower() && checkItems(rotatedPoint, airportModel.getTowerAL()))
            return;
        if(SettingsEngine.getInstance().getDisplayStart() && checkItems(rotatedPoint, airportModel.getStartAL()))
            return;
        if(SettingsEngine.getInstance().getDisplayHelipad() && checkItems(rotatedPoint, airportModel.getHelipadAL()))
            return;
        if(SettingsEngine.getInstance().getDisplayTWSign() && checkItems(rotatedPoint, airportModel.getTaxiwaySignAL()))
            return;
        if(SettingsEngine.getInstance().getDisplayJetways() && checkItems(rotatedPoint, airportModel.getJetwayAL()))
            return;
        if(SettingsEngine.getInstance().getDisplayParking() && checkItems(rotatedPoint, airportModel.getTaxiwayParkingHM()))
            return;
        if(SettingsEngine.getInstance().getDisplayTriggers() && checkItems(rotatedPoint, airportModel.getTriggerAL()))
            return;
        if(SettingsEngine.getInstance().getDisplayTWPoint() && checkItems(rotatedPoint, airportModel.getTaxiwayPointHM()))
            return;
        if(checkItems(rotatedPoint, airportModel.getTaxiwayPathAL()))
            return;
        if(SettingsEngine.getInstance().getDisplayRunway() && checkItems(rotatedPoint, airportModel.getRunwayAL()))
            return;
        if(SettingsEngine.getInstance().getDisplayApronEL() && checkItems(rotatedPoint, airportModel.getApronEdgeLightAL()))
        {
            vertexModel = ((ApronEdgeLightModel)SelectedItem.getInstance().getBaseModel()).isWithinVertex((int)((double)rotatedPoint.x - centerX), (int)((double)rotatedPoint.y - centerY));
            ((ApronEdgeLightModel)SelectedItem.getInstance().getBaseModel()).displayVertexModel(vertexModel);
            return;
        }
        if(SettingsEngine.getInstance().getDisplayBoundFence() && checkItems(rotatedPoint, airportModel.getBoundaryFenceAL()))
        {
            vertexModel = ((BoundaryFenceModel)SelectedItem.getInstance().getBaseModel()).isWithinVertex((int)((double)rotatedPoint.x - centerX), (int)((double)rotatedPoint.y - centerY));
            ((BoundaryFenceModel)SelectedItem.getInstance().getBaseModel()).displayVertexModel(vertexModel);
            return;
        }
        if(SettingsEngine.getInstance().getDisplayBlastFence() && checkItems(rotatedPoint, airportModel.getBlastFenceAL()))
        {
            vertexModel = ((BlastFenceModel)SelectedItem.getInstance().getBaseModel()).isWithinVertex((int)((double)rotatedPoint.x - centerX), (int)((double)rotatedPoint.y - centerY));
            ((BlastFenceModel)SelectedItem.getInstance().getBaseModel()).displayVertexModel(vertexModel);
            return;
        }
        if(SettingsEngine.getInstance().getDisplayMarkers() && checkItems(rotatedPoint, airportModel.getMarkerAL()))
            return;
        if(SettingsEngine.getInstance().getDisplayVORs() && checkItems(rotatedPoint, airportModel.getVORAL()))
            return;
        if(SettingsEngine.getInstance().getDisplayVORs() && checkItems(rotatedPoint, airportModel.getVORdmeModels()))
            return;
        if(SettingsEngine.getInstance().getDisplayNDBs() && checkItems(rotatedPoint, airportModel.getNDBAL()))
            return;
        if(SettingsEngine.getInstance().getDisplayScenery() && checkItems(rotatedPoint, airportModel.getSceneryAL()))
            return;
        if(SettingsEngine.getInstance().getDisplayILS() && checkItems(rotatedPoint, airportModel.getILSModels()))
            return;
        if(SettingsEngine.getInstance().getDisplayILS() && checkItems(rotatedPoint, airportModel.getILSDMEModels()))
            return;
        if(SettingsEngine.getInstance().getDisplayILS() && checkItems(rotatedPoint, airportModel.getILSGSModels()))
            return;
        if(SettingsEngine.getInstance().getDisplayWindsocks() && checkItems(rotatedPoint, airportModel.getWindsockAL()))
            return;
        if(SettingsEngine.getInstance().getDisplayApron() && checkItems(rotatedPoint, airportModel.getApronAL()))
        {
            return;
        } else
        {
            SelectedItem.getInstance().selectBaseModel(null);
            repaint();
            return;
        }
    }

    private void dragItem(Point point)
    {
        if(SelectedItem.getInstance().getBaseModel() != null && SelectedItem.getInstance().isModelDraggable())
        {
            LatLonPoint latLonPoint = Utilities.getLatLonForPixel(centerPoint.getLat(), centerPoint.getLon(), point.getX() - centerX, point.getY() - centerY, scale);
            if(SelectedItem.getInstance().getBaseModel().moveTo(latLonPoint, (double)oldX - centerX, (double)oldY - centerY))
            {
                Rectangle clipR = null;
                if(doubleBuffer && SelectedItem.getInstance().getBaseModel().getClip() != null)
                {
                    clipR = SelectedItem.getInstance().getBaseModel().getClip().getBounds();
                    clipR.setLocation(clipR.x - 10, clipR.y - 10);
                    clipR.setSize(clipR.width + 20, clipR.height + 20);
                }
                recreate = false;
                if(clipR == null)
                    repaint();
                else
                if(!drawing)
                    paint(getGraphics(), clipR, true);
            }
            oldX = point.x;
            oldY = point.y;
            if((SelectedItem.getInstance().getBaseModel() instanceof ApronEdgeLightModel) && vertexModel != null)
                dragApronEdgeLight(point);
            else
            if((SelectedItem.getInstance().getBaseModel() instanceof BoundaryFenceModel) && vertexModel != null)
                dragFence(point);
            else
            if((SelectedItem.getInstance().getBaseModel() instanceof BlastFenceModel) && vertexModel != null)
                dragBlastFence(point);
            else
            if((SelectedItem.getInstance().getBaseModel() instanceof ExclusionModel) && vertexModel != null)
                dragExclude(point);
        }
    }

    private void displayLatLon(Point point)
    {
        mouseLatLon = Utilities.getLatLonForPixel(centerPoint.getLat(), centerPoint.getLon(), point.getX() - centerX, point.getY() - centerY, scale);
        AlertModel.getInstance().firePropertyChange("LatLon", mouseLatLon, mouseLatLon);
    }

    public LatLonPoint getMouseLatLon()
    {
        return mouseLatLon;
    }

    private void checkTaxiwayPoint(Point point)
    {
        if(SettingsEngine.getInstance().getDisplayParking() && checkItems(point, airportModel.getTaxiwayParkingHM()))
            return;
        if(SettingsEngine.getInstance().getDisplayTWPoint() && checkItems(point, airportModel.getTaxiwayPointHM()))
            return;
        if(SelectedItem.getInstance().getBaseModel() != null || startPoint != null)
        {
            SelectedItem.getInstance().selectBaseModel(null);
            repaint();
        }
    }

    private void selectDisplayMenu(int option)
    {
        JMenu menu = FSXPMenuBar.getInstance().getMenu("Display");
        for(int i = menu.getItemCount() - 1; i >= 0; i--)
            if(menu.getItem(i) != null && (menu.getItem(i) instanceof JCheckBoxMenuItem) && menu.getItem(i).getActionCommand().equals((new StringBuilder()).append("").append(option).toString()))
            {
                if(!((JCheckBoxMenuItem)menu.getItem(i)).getState())
                    menu.getItem(i).doClick(10);
                return;
            }

    }

    private void selectShowPlane()
    {
        JMenu menu = FSXPMenuBar.getInstance().getMenu("SimConnect");
        for(int i = menu.getItemCount() - 1; i >= 0; i--)
            if(menu.getItem(i) != null && (menu.getItem(i) instanceof JCheckBoxMenuItem) && menu.getItem(i).getActionCommand().equals("78"))
            {
                if(!((JCheckBoxMenuItem)menu.getItem(i)).getState())
                    menu.getItem(i).doClick(10);
                return;
            }

    }

    private void insertStartLocation(Point point)
    {
        LatLonPoint latLonPoint = Utilities.getLatLonForPixel(centerPoint.getLat(), centerPoint.getLon(), point.getX() - centerX, point.getY() - centerY, scale);
        StartModel model = new StartModel();
        model.setShouldNotify(false);
        model.setLatLon(latLonPoint);
        if(SettingsEngine.getInstance().getUseAirportAlt())
        {
            model.setAlt(airportModel.getAlt());
            model.setAltMeasure(airportModel.getAltMeasure());
        }
        model.setShouldNotify(true);
        airportModel.addStartModel(model);
        model.setCurrentlySelected(true);
        selectDisplayMenu(50);
        repaint();
    }

    private void insertTower(Point point)
    {
        LatLonPoint latLonPoint = Utilities.getLatLonForPixel(centerPoint.getLat(), centerPoint.getLon(), point.getX() - centerX, point.getY() - centerY, scale);
        TowerModel model = new TowerModel();
        model.setShouldNotify(false);
        model.setLatLon(latLonPoint);
        model.setSceneryLatLon((LatLonPoint)latLonPoint.clone());
        if(SettingsEngine.getInstance().getUseAirportAlt())
        {
            model.setAlt(airportModel.getAlt());
            model.setAltMeasure(airportModel.getAltMeasure());
            model.setSceneryAlt(0.0D);
            model.setSceneryAltMeasure(airportModel.getAltMeasure());
            model.setAltitudeIsAgl(true);
        }
        model.setShouldNotify(true);
        airportModel.addTowerModel(model);
        model.setCurrentlySelected(true);
        selectDisplayMenu(46);
        repaint();
    }

    private void insertParkingLocation(Point point)
    {
        LatLonPoint latLonPoint = Utilities.getLatLonForPixel(centerPoint.getLat(), centerPoint.getLon(), point.getX() - centerX, point.getY() - centerY, scale);
        TaxiwayParkingModel model = new TaxiwayParkingModel();
        model.setShouldNotify(false);
        model.setLatLon(latLonPoint);
        model.setIndex(airportModel.incrementTaxiIndex());
        model.setType(CursorEngine.getInstance().getParkingType());
        model.setShouldNotify(true);
        airportModel.addTaxiwayParkingModel(model);
        model.setCurrentlySelected(true);
        selectDisplayMenu(38);
        repaint();
    }

    private TaxiwayPointModel insertTaxiwayPoint(Point point, boolean shiftDown, String type)
    {
        LatLonPoint latLonPoint = Utilities.getLatLonForPixel(centerPoint.getLat(), centerPoint.getLon(), point.getX() - centerX, point.getY() - centerY, scale);
        TaxiwayPointModel model = new TaxiwayPointModel();
        model.setShouldNotify(false);
        model.setLatLon(latLonPoint);
        model.setIndex(airportModel.incrementTaxiIndex());
        model.setType(type);
        model.setShouldNotify(true);
        airportModel.addTaxiwayPointModel(model);
        model.setCurrentlySelected(true);
        if(shiftDown)
        {
            ArrayList itemAL = airportModel.getTaxiwayPathAL();
            int i = itemAL.size() - 1;
            do
            {
                if(i < 0)
                    break;
                if(((TaxiwayPathModel)itemAL.get(i)).isWithinObject((int)((double)point.x - centerX), (int)((double)point.y - centerY)))
                {
                    TaxiwayPathModel model2 = (TaxiwayPathModel)((TaxiwayPathModel)itemAL.get(i)).clone();
                    model2.setStart(model.getIndex());
                    airportModel.addTaxiwayPathModel(model2);
                    ((TaxiwayPathModel)itemAL.get(i)).setEnd(model.getIndex());
                    break;
                }
                i--;
            } while(true);
        }
        selectDisplayMenu(36);
        repaint();
        ILSLineUpdates.getInstance().addAirportModel(airportModel);
        return model;
    }

    private void insertTaxiwaySign(Point point)
    {
        LatLonPoint latLonPoint = Utilities.getLatLonForPixel(centerPoint.getLat(), centerPoint.getLon(), point.getX() - centerX, point.getY() - centerY, scale);
        TaxiwaySignModel model = new TaxiwaySignModel();
        model.setShouldNotify(false);
        model.setLatLon(latLonPoint);
        model.setShouldNotify(true);
        airportModel.addTaxiwaySignModel(model);
        model.setCurrentlySelected(true);
        selectDisplayMenu(37);
        repaint();
    }

    private void insertHelipad(Point point)
    {
        LatLonPoint latLonPoint = Utilities.getLatLonForPixel(centerPoint.getLat(), centerPoint.getLon(), point.getX() - centerX, point.getY() - centerY, scale);
        HelipadModel model = new HelipadModel();
        model.setShouldNotify(false);
        model.setLatLon(latLonPoint);
        if(SettingsEngine.getInstance().getUseAirportAlt())
        {
            model.setAlt(airportModel.getAlt());
            model.setAltMeasure(airportModel.getAltMeasure());
        }
        model.setShouldNotify(true);
        airportModel.addHelipadModel(model);
        model.setCurrentlySelected(true);
        selectDisplayMenu(49);
        repaint();
    }

    private void insertJetway(Point point)
    {
        LatLonPoint latLonPoint = Utilities.getLatLonForPixel(centerPoint.getLat(), centerPoint.getLon(), point.getX() - centerX, point.getY() - centerY, scale);
        JetwayModel model = new JetwayModel();
        model.setShouldNotify(false);
        model.setLatLon(latLonPoint);
        if(SettingsEngine.getInstance().getUseAirportAlt())
        {
            model.setAlt(0.0D);
            model.setAltMeasure(airportModel.getAltMeasure());
            model.setAltitudeIsAgl(true);
        }
        model.setShouldNotify(true);
        airportModel.addJetwayModel(model);
        model.setCurrentlySelected(true);
        selectDisplayMenu(39);
        repaint();
    }

    private void insertILSBeam(Point point)
    {
        LatLonPoint latLonPoint = Utilities.getLatLonForPixel(centerPoint.getLat(), centerPoint.getLon(), point.getX() - centerX, point.getY() - centerY, scale);
        ILSModel model = new ILSModel();
        model.setShouldNotify(false);
        model.setLatLon(latLonPoint);
        if(SettingsEngine.getInstance().getUseAirportAlt())
        {
            model.setAlt((float)airportModel.getAlt());
            model.setAltMeasure(airportModel.getAltMeasure());
        }
        airportModel.addILSModel(model);
        model.setCurrentlySelected(true);
        model.setShouldNotify(true);
        selectDisplayMenu(33);
        repaint();
    }

    private void insertMarker(Point point)
    {
        LatLonPoint latLonPoint = Utilities.getLatLonForPixel(centerPoint.getLat(), centerPoint.getLon(), point.getX() - centerX, point.getY() - centerY, scale);
        MarkerModel model = new MarkerModel();
        model.setShouldNotify(false);
        model.setLatLon(latLonPoint);
        if(SettingsEngine.getInstance().getUseAirportAlt())
        {
            model.setAlt((float)airportModel.getAlt());
            model.setAltMeasure(airportModel.getAltMeasure());
        }
        airportModel.addMarkerModel(model);
        model.setShouldNotify(true);
        model.setCurrentlySelected(true);
        selectDisplayMenu(56);
        repaint();
    }

    private void insertVOR(Point point)
    {
        LatLonPoint latLonPoint = Utilities.getLatLonForPixel(centerPoint.getLat(), centerPoint.getLon(), point.getX() - centerX, point.getY() - centerY, scale);
        VORModel model = new VORModel();
        model.setShouldNotify(false);
        model.setLatLon(latLonPoint);
        if(SettingsEngine.getInstance().getUseAirportAlt())
        {
            model.setAlt((float)airportModel.getAlt());
            model.setAltMeasure(airportModel.getAltMeasure());
        }
        airportModel.addVORModel(model);
        model.setShouldNotify(true);
        model.setCurrentlySelected(true);
        selectDisplayMenu(58);
        repaint();
    }

    private void insertNDB(Point point)
    {
        LatLonPoint latLonPoint = Utilities.getLatLonForPixel(centerPoint.getLat(), centerPoint.getLon(), point.getX() - centerX, point.getY() - centerY, scale);
        NDBModel model = new NDBModel();
        model.setShouldNotify(false);
        model.setLatLon(latLonPoint);
        if(SettingsEngine.getInstance().getUseAirportAlt())
        {
            model.setAlt((float)airportModel.getAlt());
            model.setAltMeasure(airportModel.getAltMeasure());
        }
        airportModel.addNDBModel(model);
        model.setShouldNotify(true);
        model.setCurrentlySelected(true);
        selectDisplayMenu(57);
        repaint();
    }

    private void insertWindsock(Point point)
    {
        LatLonPoint latLonPoint = Utilities.getLatLonForPixel(centerPoint.getLat(), centerPoint.getLon(), point.getX() - centerX, point.getY() - centerY, scale);
        WindsockModel model = new WindsockModel();
        model.setShouldNotify(false);
        model.setLatLon(latLonPoint);
        if(SettingsEngine.getInstance().getUseAirportAlt())
        {
            model.setAlt(0.0F);
            model.setAltMeasure(airportModel.getAltMeasure());
            model.setAltitudeIsAgl(true);
        }
        airportModel.addWindsockModel(model);
        model.setShouldNotify(true);
        model.setCurrentlySelected(true);
        selectDisplayMenu(59);
        repaint();
    }

    private void insertScenery(Point point)
    {
        LatLonPoint latLonPoint = Utilities.getLatLonForPixel(centerPoint.getLat(), centerPoint.getLon(), point.getX() - centerX, point.getY() - centerY, scale);
        SceneryModel model = new SceneryModel();
        model.setShouldNotify(false);
        model.setLatLon(latLonPoint);
        airportModel.addSceneryModel(model);
        model.setShouldNotify(true);
        model.setCurrentlySelected(true);
        selectDisplayMenu(61);
        repaint();
    }

    private void createTaxiway(Point point, boolean shiftDown)
    {
        BaseModel baseModel = SelectedItem.getInstance().getBaseModel();
        if(baseModel == null)
            baseModel = insertTaxiwayPoint(point, shiftDown, "NORMAL");
        taxiwayPathModel = new TaxiwayPathModel();
        taxiwayPathModel.setShouldNotify(false);
        if(baseModel instanceof TaxiwayParkingModel)
        {
            taxiwayPathModel.setEnd(((TaxiwayParkingModel)baseModel).getIndex());
            taxiwayPathModel.setType("PARKING");
        } else
        if(baseModel instanceof TaxiwayPointModel)
        {
            taxiwayPathModel.setStart(((TaxiwayPointModel)baseModel).getIndex());
            taxiwayPathModel.setType(CursorEngine.getInstance().getTaxiwayType());
        }
        taxiwayPathModel.setName(airportModel.getBlankTaxiNameIndex());
        if(SettingsEngine.getInstance().getTaxiwayProperties() && (baseModel instanceof TaxiwayPointModel))
        {
            ArrayList itemAL = airportModel.getTaxiwayPathAL();
            ArrayList arrayList = new ArrayList();
            for(int i = itemAL.size() - 1; i >= 0; i--)
                if(!((TaxiwayPathModel)itemAL.get(i)).getType().equals("PARKING") && (((TaxiwayPathModel)itemAL.get(i)).getStart() == taxiwayPathModel.getStart() || ((TaxiwayPathModel)itemAL.get(i)).getEnd() == taxiwayPathModel.getStart()))
                    arrayList.add(itemAL.get(i));

            if(arrayList.size() == 1)
            {
                TaxiwayPathModel model = (TaxiwayPathModel)arrayList.get(0);
                taxiwayPathModel.setType(model.getType());
                taxiwayPathModel.setSurface(model.getSurface());
                taxiwayPathModel.setLeftEdge(model.getLeftEdge());
                taxiwayPathModel.setRightEdge(model.getRightEdge());
                taxiwayPathModel.setNumber(model.getNumber());
                taxiwayPathModel.setDesignator(model.getDesignator());
                taxiwayPathModel.setWidthMeasure(model.getWidthMeasure());
                taxiwayPathModel.setName(model.getName());
                taxiwayPathModel.setWidth(model.getWidth());
                taxiwayPathModel.setWeightLimit(model.getWeightLimit());
                taxiwayPathModel.setDrawSurface(model.getDrawSurface());
                taxiwayPathModel.setDrawDetail(model.getDrawDetail());
                taxiwayPathModel.setCenterLine(model.getCenterLine());
                taxiwayPathModel.setCenterLineLighted(model.getCenterLineLighted());
                taxiwayPathModel.setLeftEdgeLighted(model.getLeftEdgeLighted());
                taxiwayPathModel.setRightEdgeLighted(model.getRightEdgeLighted());
            }
        }
    }

    private void finishTaxiway(Point point, boolean shiftDown)
    {
        BaseModel baseModel = SelectedItem.getInstance().getBaseModel();
        if(baseModel == null)
            baseModel = insertTaxiwayPoint(point, shiftDown, "NORMAL");
        if(baseModel instanceof TaxiwayParkingModel)
        {
            taxiwayPathModel.setEnd(((TaxiwayParkingModel)baseModel).getIndex());
            taxiwayPathModel.setType("PARKING");
        } else
        if(baseModel instanceof TaxiwayPointModel)
            if(taxiwayPathModel.getType().equals("PARKING"))
                taxiwayPathModel.setStart(((TaxiwayPointModel)baseModel).getIndex());
            else
                taxiwayPathModel.setEnd(((TaxiwayPointModel)baseModel).getIndex());
        if(taxiwayPathModel.getStart() != taxiwayPathModel.getEnd())
            airportModel.addTaxiwayPathModel(taxiwayPathModel);
        startPoint = null;
        endPoint = null;
        taxiwayPathModel.setShouldNotify(true);
        taxiwayPathModel.setCurrentlySelected(true);
        selectDisplayMenu(42);
        selectDisplayMenu(40);
        repaint();
        if(taxiwayPathModel.getType().equals("TAXI") && SettingsEngine.getInstance().getTaxiwayRunways())
        {
            Thread thread = new Thread() {

                public void run()
                {
                    while(taxiwayPathModel.getStartLat() == 0.0D && taxiwayPathModel.getEndLat() == 0.0D) 
                        try
                        {
                            Thread.sleep(100L);
                        }
                        catch(InterruptedException ignored) { }
                    ArrayList runwayAL = airportModel.getRunwayAL();
                    int i = runwayAL.size() - 1;
                    do
                    {
                        if(i < 0)
                            break;
                        RunwayModel runwayModel = (RunwayModel)runwayAL.get(i);
                        if(runwayModel.containsTaxiway(taxiwayPathModel))
                        {
                            taxiwayPathModel.setShouldNotify(false);
                            taxiwayPathModel.setType("RUNWAY");
                            taxiwayPathModel.setWidth(runwayModel.getWidth());
                            taxiwayPathModel.setWidthMeasure(runwayModel.getWidthMeasure());
                            taxiwayPathModel.setLeftEdge("SOLID");
                            taxiwayPathModel.setRightEdge("SOLID");
                            taxiwayPathModel.setSurface(runwayModel.getSurface());
                            taxiwayPathModel.setNumber(runwayModel.getNumber());
                            if(runwayModel.getPrimaryDesignator().length() == 0)
                                taxiwayPathModel.setDesignator(runwayModel.getDesignator());
                            else
                                taxiwayPathModel.setDesignator(runwayModel.getPrimaryDesignator());
                            taxiwayPathModel.setShouldNotify(true);
                            break;
                        }
                        i--;
                    } while(true);
                }

                final AirportDisplay this$0;

            
            {
                this$0 = AirportDisplay.this;
            }
            }
;
            thread.setPriority(4);
            thread.start();
        }
    }

    private void createExclude(Point point)
    {
        if(modifyingExclude && vertexModel == null && exclusionModel != null)
            vertexModel = exclusionModel.isWithinVertex((int)((double)point.x - centerX), (int)((double)point.y - centerY));
        if(vertexModel == null && SettingsEngine.getInstance().getDisplayExcludes() && checkItems(point, airportModel.getExclusionAL()))
        {
            exclusionModel = (ExclusionModel)SelectedItem.getInstance().getBaseModel();
            modifyingExclude = true;
        } else
        if(vertexModel == null)
        {
            exclusionModel = new ExclusionModel();
            exclusionModel.setShouldNotify(false);
            exclusionModel.getVertex1().setShouldNotify(false);
            exclusionModel.getVertex2().setShouldNotify(false);
            exclusionModel.getVertex1().setLatLon(Utilities.getLatLonForPixel(centerPoint.getLat(), centerPoint.getLon(), point.getX() - centerX, point.getY() - centerY, scale));
            exclusionModel.getVertex2().setLatLon(Utilities.getLatLonForPixel(centerPoint.getLat(), centerPoint.getLon(), point.getX() - centerX, point.getY() - centerY, scale));
            vertexModel = exclusionModel.getVertex2();
            airportModel.addExclusionModel(exclusionModel);
            SelectedItem.getInstance().selectBaseModel(exclusionModel);
            exclusionModel.setCurrentlySelected(true);
            creatingExclude = true;
        }
    }

    private void finishExclude(Point point)
    {
        exclusionModel.setShouldNotify(true);
        exclusionModel.getVertex1().setShouldNotify(true);
        exclusionModel.getVertex2().setShouldNotify(true);
        vertexModel = null;
        if(exclusionModel.getVertex1().getLatLon().equals(exclusionModel.getVertex2().getLatLon()))
            airportModel.removeExclusionModel(exclusionModel);
        repaint();
    }

    private void dragExclude(Point point)
    {
        if(vertexModel != null)
        {
            vertexModel.setLatLon(Utilities.getLatLonForPixel(centerPoint.getLat(), centerPoint.getLon(), point.getX() - centerX, point.getY() - centerY, scale));
            recreate = false;
            selectDisplayMenu(52);
            repaint();
        } else
        if(exclusionModel != null)
        {
            LatLonPoint latLonPoint = Utilities.getLatLonForPixel(centerPoint.getLat(), centerPoint.getLon(), point.getX() - centerX, point.getY() - centerY, scale);
            if(SelectedItem.getInstance().getBaseModel().moveTo(latLonPoint, (double)oldX - centerX, (double)oldY - centerY))
            {
                recreate = false;
                repaint();
            }
            oldX = point.x;
            oldY = point.y;
        }
    }

    private void createApron(Point point)
    {
        if(apronModel == null)
        {
            apronModel = new ApronModel();
            airportModel.addApronModel(apronModel);
            SelectedItem.getInstance().selectBaseModel(apronModel);
            apronModel.setCurrentlySelected(true);
            apronModel.setModifying(true);
            creatingApron = true;
            modifyingApron = true;
        }
        if(creatingApron)
        {
            startPoint = point;
            LatLonPoint latLonPoint = Utilities.getLatLonForPixel(centerPoint.getLat(), centerPoint.getLon(), point.getX() - centerX, point.getY() - centerY, scale);
            VertexModel model = new VertexModel();
            model.setShouldNotify(false);
            model.setLatLon(latLonPoint);
            model.setShouldNotify(true);
            apronModel.addVertexModel(model);
            selectDisplayMenu(34);
            repaint();
        }
    }

    private void startApronDrag(Point point)
    {
        if(modifyingApron && vertexModel == null && apronModel != null)
        {
            vertexModel = apronModel.isWithinVertex((int)((double)point.x - centerX), (int)((double)point.y - centerY));
            apronModel.displayVertexModel(vertexModel);
            repaint();
        }
        if(vertexModel == null && SettingsEngine.getInstance().getDisplayApron() && checkItems(point, airportModel.getApronAL()))
        {
            if(apronModel != null)
                apronModel.setModifying(false);
            apronModel = (ApronModel)SelectedItem.getInstance().getBaseModel();
            apronModel.setModifying(true);
            modifyingApron = true;
            repaint();
        } else
        if(vertexModel == null && !creatingApron && apronModel != null)
        {
            apronModel.setModifying(false);
            apronModel = null;
            modifyingApron = false;
            repaint();
        }
    }

    private void dragApron(Point point)
    {
        if(vertexModel != null && SelectedItem.getInstance().isModelDraggable())
        {
            vertexModel.setLatLon(Utilities.getLatLonForPixel(centerPoint.getLat(), centerPoint.getLon(), point.getX() - centerX, point.getY() - centerY, scale));
            recreate = false;
            repaint();
        } else
        if(apronModel != null && !creatingApron && SelectedItem.getInstance().isModelDraggable())
        {
            LatLonPoint latLonPoint = Utilities.getLatLonForPixel(centerPoint.getLat(), centerPoint.getLon(), point.getX() - centerX, point.getY() - centerY, scale);
            if(SelectedItem.getInstance().getBaseModel().moveTo(latLonPoint, (double)oldX - centerX, (double)oldY - centerY))
            {
                recreate = false;
                repaint();
            }
            oldX = point.x;
            oldY = point.y;
        }
    }

    private void finishApron(Point point)
    {
        startPoint = null;
        endPoint = null;
        creatingApron = false;
        repaint();
    }

    private void addApronVertex(Point point)
    {
        if(apronModel == null)
            return;
        int index = apronModel.isBetweenVertices((int)((double)point.x - centerX), (int)((double)point.y - centerY));
        if(index >= 0)
        {
            VertexModel model = new VertexModel();
            model.setShouldNotify(false);
            model.setLatLon(Utilities.getLatLonForPixel(centerPoint.getLat(), centerPoint.getLon(), point.getX() - centerX, point.getY() - centerY, scale));
            model.setShouldNotify(true);
            apronModel.insertVertexModel(model, index + 1);
            apronModel.displayVertexModel(model);
            repaint();
        }
    }

    private void deleteApronVertex(Point point)
    {
        if(apronModel == null)
            return;
        VertexModel model = apronModel.isWithinVertex((int)((double)point.x - centerX), (int)((double)point.y - centerY));
        if(model != null)
        {
            apronModel.removeVertexModel(model);
            apronModel.displayVertexModel(null);
            repaint();
        }
    }

    private void createTrigger(Point point)
    {
        if(triggerModel == null)
        {
            triggerModel = new TriggerModel();
            airportModel.addTriggerModel(triggerModel);
            SelectedItem.getInstance().selectBaseModel(triggerModel);
            triggerModel.setCurrentlySelected(true);
            triggerModel.setModifying(true);
            creatingTrigger = true;
            modifyingTrigger = true;
        }
        if(creatingTrigger)
        {
            startPoint = point;
            LatLonPoint latLonPoint = Utilities.getLatLonForPixel(centerPoint.getLat(), centerPoint.getLon(), point.getX() - centerX, point.getY() - centerY, scale);
            VertexModel model = new VertexModel();
            model.setShouldNotify(false);
            model.setLatLon(latLonPoint);
            model.setShouldNotify(true);
            triggerModel.addVertexModel(model);
            selectDisplayMenu(60);
            repaint();
        }
    }

    private void startTriggerDrag(Point point)
    {
        if(modifyingTrigger && vertexModel == null && triggerModel != null)
        {
            vertexModel = triggerModel.isWithinVertex((int)((double)point.x - centerX), (int)((double)point.y - centerY));
            triggerModel.displayVertexModel(vertexModel);
            repaint();
        }
        if(vertexModel == null && SettingsEngine.getInstance().getDisplayTriggers() && checkItems(point, airportModel.getTriggerAL()))
        {
            if(triggerModel != null)
                triggerModel.setModifying(false);
            triggerModel = (TriggerModel)SelectedItem.getInstance().getBaseModel();
            triggerModel.setModifying(true);
            modifyingTrigger = true;
            repaint();
        } else
        if(vertexModel == null && !creatingTrigger && triggerModel != null)
        {
            triggerModel.setModifying(false);
            triggerModel = null;
            modifyingTrigger = false;
            repaint();
        }
    }

    private void dragTrigger(Point point)
    {
        if(vertexModel != null && SelectedItem.getInstance().isModelDraggable())
        {
            vertexModel.setLatLon(Utilities.getLatLonForPixel(centerPoint.getLat(), centerPoint.getLon(), point.getX() - centerX, point.getY() - centerY, scale));
            recreate = false;
            repaint();
        } else
        if(triggerModel != null && !creatingTrigger && SelectedItem.getInstance().isModelDraggable())
        {
            LatLonPoint latLonPoint = Utilities.getLatLonForPixel(centerPoint.getLat(), centerPoint.getLon(), point.getX() - centerX, point.getY() - centerY, scale);
            if(SelectedItem.getInstance().getBaseModel().moveTo(latLonPoint, (double)oldX - centerX, (double)oldY - centerY))
            {
                recreate = false;
                repaint();
            }
            oldX = point.x;
            oldY = point.y;
        }
    }

    private void finishTrigger(Point point)
    {
        startPoint = null;
        endPoint = null;
        creatingTrigger = false;
        repaint();
    }

    private void addTriggerVertex(Point point)
    {
        if(triggerModel == null)
            return;
        int index = triggerModel.isBetweenVertices((int)((double)point.x - centerX), (int)((double)point.y - centerY));
        if(index >= 0)
        {
            VertexModel model = new VertexModel();
            model.setShouldNotify(false);
            model.setLatLon(Utilities.getLatLonForPixel(centerPoint.getLat(), centerPoint.getLon(), point.getX() - centerX, point.getY() - centerY, scale));
            model.setShouldNotify(true);
            triggerModel.insertVertexModel(model, index + 1);
            triggerModel.displayVertexModel(model);
            repaint();
        }
    }

    private void deleteTriggerVertex(Point point)
    {
        if(triggerModel == null)
            return;
        VertexModel model = triggerModel.isWithinVertex((int)((double)point.x - centerX), (int)((double)point.y - centerY));
        if(model != null)
        {
            triggerModel.removeVertexModel(model);
            triggerModel.displayVertexModel(null);
            repaint();
        }
    }

    private void insertRunway(Point point)
    {
        LatLonPoint latLonPoint = Utilities.getLatLonForPixel(centerPoint.getLat(), centerPoint.getLon(), point.getX() - centerX, point.getY() - centerY, scale);
        RunwayModel model = new RunwayModel();
        model.setShouldNotify(false);
        model.setLatLon(latLonPoint);
        model.setSurface(CursorEngine.getInstance().getRunwaySurface());
        if(SettingsEngine.getInstance().getUseAirportAlt())
        {
            model.setAlt((float)airportModel.getAlt());
            model.setAltMeasure(airportModel.getAltMeasure());
        }
        model.setShouldNotify(true);
        airportModel.addRunwayModel(model);
        model.setCurrentlySelected(true);
        selectDisplayMenu(32);
        repaint();
    }

    private void createApronEdgeLight(Point point)
    {
        if(apronEdgeLightModel == null)
        {
            apronEdgeLightModel = new ApronEdgeLightModel();
            airportModel.addApronEdgeLightModel(apronEdgeLightModel);
            SelectedItem.getInstance().selectBaseModel(apronEdgeLightModel);
            apronEdgeLightModel.setCurrentlySelected(true);
            creatingApronEdgeLight = true;
            modifyingApronEdgeLight = true;
        }
        if(creatingApronEdgeLight)
        {
            startPoint = point;
            LatLonPoint latLonPoint = Utilities.getLatLonForPixel(centerPoint.getLat(), centerPoint.getLon(), point.getX() - centerX, point.getY() - centerY, scale);
            VertexModel model = new VertexModel();
            model.setShouldNotify(false);
            model.setLatLon(latLonPoint);
            model.setShouldNotify(true);
            apronEdgeLightModel.addVertexModel(model);
            selectDisplayMenu(35);
            repaint();
        }
    }

    private void startApronEdgeLightDrag(Point point)
    {
        if(modifyingApronEdgeLight && vertexModel == null && apronEdgeLightModel != null)
        {
            vertexModel = apronEdgeLightModel.isWithinVertex((int)((double)point.x - centerX), (int)((double)point.y - centerY));
            apronEdgeLightModel.displayVertexModel(vertexModel);
            repaint();
        }
        if(vertexModel == null && SettingsEngine.getInstance().getDisplayApronEL() && checkItems(point, airportModel.getApronEdgeLightAL()))
        {
            if(apronEdgeLightModel != null)
                apronEdgeLightModel.setModifying(false);
            apronEdgeLightModel = (ApronEdgeLightModel)SelectedItem.getInstance().getBaseModel();
            apronEdgeLightModel.setModifying(true);
            modifyingApronEdgeLight = true;
            repaint();
            vertexModel = apronEdgeLightModel.isWithinVertex((int)((double)point.x - centerX), (int)((double)point.y - centerY));
            apronEdgeLightModel.displayVertexModel(vertexModel);
        } else
        if(vertexModel == null && !creatingApronEdgeLight && apronEdgeLightModel != null)
        {
            apronEdgeLightModel.setModifying(false);
            apronEdgeLightModel = null;
            modifyingApronEdgeLight = false;
            repaint();
        }
    }

    private void dragApronEdgeLight(Point point)
    {
        if(vertexModel != null && SelectedItem.getInstance().isModelDraggable())
        {
            vertexModel.setLatLon(Utilities.getLatLonForPixel(centerPoint.getLat(), centerPoint.getLon(), point.getX() - centerX, point.getY() - centerY, scale));
            recreate = false;
            repaint();
        }
    }

    private void finishApronEdgeLight(Point point, boolean close)
    {
        if(close)
            apronEdgeLightModel.addVertexModel((VertexModel)((VertexModel)apronEdgeLightModel.getVertexAL().get(0)).clone());
        apronEdgeLightModel.setModifying(false);
        startPoint = null;
        endPoint = null;
        creatingApronEdgeLight = false;
        repaint();
        repaint();
    }

    private void mergeApronEdgeLights(Point point, boolean shiftDown)
    {
        if(!shiftDown || vertexModel == null)
            return;
        if(!(SelectedItem.getInstance().getBaseModel() instanceof ApronEdgeLightModel))
            return;
        ArrayList vertexAL = ((ApronEdgeLightModel)SelectedItem.getInstance().getBaseModel()).getVertexAL();
        if(vertexAL.size() == 0)
            return;
        if(vertexAL.get(0) == vertexModel || vertexAL.get(vertexAL.size() - 1) == vertexModel)
        {
            ArrayList arrayList = airportModel.getApronEdgeLightAL();
            for(int i = arrayList.size() - 1; i >= 0; i--)
            {
                if(arrayList.get(i) == SelectedItem.getInstance().getBaseModel())
                    continue;
                VertexModel model1 = ((ApronEdgeLightModel)arrayList.get(i)).isWithinEndVertex((int)((double)point.x - centerX), (int)((double)point.y - centerY));
                if(model1 != null)
                {
                    airportModel.mergeApronEdgeLights((ApronEdgeLightModel)SelectedItem.getInstance().getBaseModel(), (ApronEdgeLightModel)arrayList.get(i), vertexModel, model1);
                    repaint();
                    return;
                }
            }

        }
    }

    private void addApronEdgeLightVertex(Point point)
    {
        if(apronEdgeLightModel == null)
            return;
        int index = apronEdgeLightModel.isBetweenVertices((int)((double)point.x - centerX), (int)((double)point.y - centerY));
        if(index >= 0)
        {
            VertexModel model = new VertexModel();
            model.setShouldNotify(false);
            model.setLatLon(Utilities.getLatLonForPixel(centerPoint.getLat(), centerPoint.getLon(), point.getX() - centerX, point.getY() - centerY, scale));
            model.setShouldNotify(true);
            apronEdgeLightModel.insertVertexModel(model, index + 1);
            apronEdgeLightModel.displayVertexModel(model);
            repaint();
        }
    }

    private void deleteApronEdgeLightVertex(Point point)
    {
        if(apronEdgeLightModel == null)
            return;
        VertexModel model = apronEdgeLightModel.isWithinVertex((int)((double)point.x - centerX), (int)((double)point.y - centerY));
        if(model != null)
        {
            apronEdgeLightModel.removeVertexModel(model);
            apronEdgeLightModel.displayVertexModel(null);
            repaint();
        }
    }

    private void createFence(Point point)
    {
        if(boundaryFenceModel == null)
        {
            boundaryFenceModel = new BoundaryFenceModel();
            boundaryFenceModel.setShouldNotify(false);
            boundaryFenceModel.setProfile(CursorEngine.getInstance().getFenceSize());
            boundaryFenceModel.setShouldNotify(true);
            airportModel.addBoundaryFenceModel(boundaryFenceModel);
            SelectedItem.getInstance().selectBaseModel(boundaryFenceModel);
            boundaryFenceModel.setCurrentlySelected(true);
            creatingFence = true;
            modifyingFence = true;
        }
        if(creatingFence)
        {
            startPoint = point;
            LatLonPoint latLonPoint = Utilities.getLatLonForPixel(centerPoint.getLat(), centerPoint.getLon(), point.getX() - centerX, point.getY() - centerY, scale);
            VertexModel model = new VertexModel();
            model.setShouldNotify(false);
            model.setLatLon(latLonPoint);
            model.setShouldNotify(true);
            boundaryFenceModel.addVertexModel(model);
            selectDisplayMenu(47);
            repaint();
        }
    }

    private void startFenceDrag(Point point)
    {
        if(modifyingFence && vertexModel == null && boundaryFenceModel != null)
        {
            vertexModel = boundaryFenceModel.isWithinVertex((int)((double)point.x - centerX), (int)((double)point.y - centerY));
            boundaryFenceModel.displayVertexModel(vertexModel);
            repaint();
        }
        if(vertexModel == null && SettingsEngine.getInstance().getDisplayBoundFence() && checkItems(point, airportModel.getBoundaryFenceAL()))
        {
            if(boundaryFenceModel != null)
                boundaryFenceModel.setModifying(false);
            boundaryFenceModel = (BoundaryFenceModel)SelectedItem.getInstance().getBaseModel();
            boundaryFenceModel.setModifying(true);
            modifyingFence = true;
            repaint();
        } else
        if(vertexModel == null && !creatingFence && boundaryFenceModel != null)
        {
            boundaryFenceModel.setModifying(false);
            boundaryFenceModel = null;
            modifyingFence = false;
            repaint();
        }
    }

    private void dragFence(Point point)
    {
        if(vertexModel != null && SelectedItem.getInstance().isModelDraggable())
        {
            vertexModel.setLatLon(Utilities.getLatLonForPixel(centerPoint.getLat(), centerPoint.getLon(), point.getX() - centerX, point.getY() - centerY, scale));
            recreate = false;
            repaint();
        }
    }

    private void finishFence(Point point, boolean close)
    {
        if(close)
            boundaryFenceModel.addVertexModel((VertexModel)((VertexModel)boundaryFenceModel.getVertexAL().get(0)).clone());
        boundaryFenceModel.setModifying(false);
        startPoint = null;
        endPoint = null;
        creatingFence = false;
        repaint();
    }

    private void mergeFence(Point point, boolean shiftDown)
    {
        if(!shiftDown || vertexModel == null)
            return;
        if(!(SelectedItem.getInstance().getBaseModel() instanceof BoundaryFenceModel))
            return;
        ArrayList vertexAL = ((BoundaryFenceModel)SelectedItem.getInstance().getBaseModel()).getVertexAL();
        if(vertexAL.size() == 0)
            return;
        if(vertexAL.get(0) == vertexModel || vertexAL.get(vertexAL.size() - 1) == vertexModel)
        {
            ArrayList arrayList = airportModel.getBoundaryFenceAL();
            for(int i = arrayList.size() - 1; i >= 0; i--)
            {
                if(arrayList.get(i) == SelectedItem.getInstance().getBaseModel())
                    continue;
                VertexModel model1 = ((BoundaryFenceModel)arrayList.get(i)).isWithinEndVertex((int)((double)point.x - centerX), (int)((double)point.y - centerY));
                if(model1 != null)
                {
                    airportModel.mergeBoundaryFences((BoundaryFenceModel)SelectedItem.getInstance().getBaseModel(), (BoundaryFenceModel)arrayList.get(i), vertexModel, model1);
                    repaint();
                    return;
                }
            }

        }
    }

    private void addFenceVertex(Point point)
    {
        if(boundaryFenceModel == null)
            return;
        int index = boundaryFenceModel.isBetweenVertices((int)((double)point.x - centerX), (int)((double)point.y - centerY));
        if(index >= 0)
        {
            VertexModel model = new VertexModel();
            model.setShouldNotify(false);
            model.setLatLon(Utilities.getLatLonForPixel(centerPoint.getLat(), centerPoint.getLon(), point.getX() - centerX, point.getY() - centerY, scale));
            model.setShouldNotify(true);
            boundaryFenceModel.insertVertexModel(model, index + 1);
            boundaryFenceModel.displayVertexModel(model);
            repaint();
        }
    }

    private void deleteFenceVertex(Point point)
    {
        if(boundaryFenceModel == null)
            return;
        VertexModel model = boundaryFenceModel.isWithinVertex((int)((double)point.x - centerX), (int)((double)point.y - centerY));
        if(model != null)
        {
            boundaryFenceModel.removeVertexModel(model);
            boundaryFenceModel.displayVertexModel(null);
            repaint();
        }
    }

    private void createBlastFence(Point point)
    {
        if(blastFenceModel == null)
        {
            blastFenceModel = new BlastFenceModel();
            blastFenceModel.setShouldNotify(false);
            blastFenceModel.setProfile(CursorEngine.getInstance().getBlastFenceSize());
            blastFenceModel.setShouldNotify(true);
            airportModel.addBlastFenceModel(blastFenceModel);
            SelectedItem.getInstance().selectBaseModel(blastFenceModel);
            blastFenceModel.setCurrentlySelected(true);
            creatingBlastFence = true;
            modifyingBlastFence = true;
        }
        if(creatingBlastFence)
        {
            startPoint = point;
            LatLonPoint latLonPoint = Utilities.getLatLonForPixel(centerPoint.getLat(), centerPoint.getLon(), point.getX() - centerX, point.getY() - centerY, scale);
            VertexModel model = new VertexModel();
            model.setShouldNotify(false);
            model.setLatLon(latLonPoint);
            model.setShouldNotify(true);
            blastFenceModel.addVertexModel(model);
            selectDisplayMenu(48);
            repaint();
        }
    }

    private void startBlastFenceDrag(Point point)
    {
        if(modifyingBlastFence && vertexModel == null && blastFenceModel != null)
        {
            vertexModel = blastFenceModel.isWithinVertex((int)((double)point.x - centerX), (int)((double)point.y - centerY));
            blastFenceModel.displayVertexModel(vertexModel);
            repaint();
        }
        if(vertexModel == null && SettingsEngine.getInstance().getDisplayBlastFence() && checkItems(point, airportModel.getBlastFenceAL()))
        {
            if(blastFenceModel != null)
                blastFenceModel.setModifying(false);
            blastFenceModel = (BlastFenceModel)SelectedItem.getInstance().getBaseModel();
            blastFenceModel.setModifying(true);
            modifyingBlastFence = true;
            repaint();
        } else
        if((vertexModel == null) & (!creatingBlastFence) && blastFenceModel != null)
        {
            blastFenceModel.setModifying(false);
            blastFenceModel = null;
            modifyingBlastFence = false;
            repaint();
        }
    }

    private void dragBlastFence(Point point)
    {
        if(vertexModel != null && SelectedItem.getInstance().isModelDraggable())
        {
            vertexModel.setLatLon(Utilities.getLatLonForPixel(centerPoint.getLat(), centerPoint.getLon(), point.getX() - centerX, point.getY() - centerY, scale));
            recreate = false;
            repaint();
        }
    }

    private void finishBlastFence(Point point)
    {
        blastFenceModel.setModifying(false);
        startPoint = null;
        endPoint = null;
        creatingBlastFence = false;
        repaint();
    }

    private void mergeBlastFence(Point point, boolean shiftDown)
    {
        if(!shiftDown || vertexModel == null)
            return;
        if(!(SelectedItem.getInstance().getBaseModel() instanceof BlastFenceModel))
            return;
        ArrayList vertexAL = ((BlastFenceModel)SelectedItem.getInstance().getBaseModel()).getVertexAL();
        if(vertexAL.size() == 0)
            return;
        if(vertexAL.get(0) == vertexModel || vertexAL.get(vertexAL.size() - 1) == vertexModel)
        {
            ArrayList arrayList = airportModel.getBlastFenceAL();
            for(int i = arrayList.size() - 1; i >= 0; i--)
            {
                if(arrayList.get(i) == SelectedItem.getInstance().getBaseModel())
                    continue;
                VertexModel model1 = ((BlastFenceModel)arrayList.get(i)).isWithinEndVertex((int)((double)point.x - centerX), (int)((double)point.y - centerY));
                if(model1 != null)
                {
                    airportModel.mergeBlastFences((BlastFenceModel)SelectedItem.getInstance().getBaseModel(), (BlastFenceModel)arrayList.get(i), vertexModel, model1);
                    repaint();
                    return;
                }
            }

        }
    }

    private void addBlastFenceVertex(Point point)
    {
        if(blastFenceModel == null)
            return;
        int index = blastFenceModel.isBetweenVertices((int)((double)point.x - centerX), (int)((double)point.y - centerY));
        if(index >= 0)
        {
            VertexModel model = new VertexModel();
            model.setShouldNotify(false);
            model.setLatLon(Utilities.getLatLonForPixel(centerPoint.getLat(), centerPoint.getLon(), point.getX() - centerX, point.getY() - centerY, scale));
            model.setShouldNotify(true);
            blastFenceModel.insertVertexModel(model, index + 1);
            blastFenceModel.displayVertexModel(model);
            repaint();
        }
    }

    private void deleteBlastFenceVertex(Point point)
    {
        if(blastFenceModel == null)
            return;
        VertexModel model = blastFenceModel.isWithinVertex((int)((double)point.x - centerX), (int)((double)point.y - centerY));
        if(model != null)
        {
            blastFenceModel.removeVertexModel(model);
            blastFenceModel.displayVertexModel(null);
            repaint();
        }
    }

    public void resetBGImages()
    {
        recreateBGImage = true;
        recreateBGAllImage = true;
        selectDisplayMenu(55);
        repaint();
    }

    public void resetFlags()
    {
        if(apronModel != null)
            apronModel.setModifying(false);
        apronModel = null;
        creatingApron = false;
        modifyingApron = false;
        if(triggerModel != null)
            triggerModel.setModifying(false);
        triggerModel = null;
        creatingTrigger = false;
        modifyingTrigger = false;
        if(apronEdgeLightModel != null)
            apronEdgeLightModel.setModifying(false);
        apronEdgeLightModel = null;
        creatingApronEdgeLight = false;
        modifyingApronEdgeLight = false;
        if(boundaryFenceModel != null)
            boundaryFenceModel.setModifying(false);
        boundaryFenceModel = null;
        creatingFence = false;
        modifyingFence = false;
        if(blastFenceModel != null)
            blastFenceModel.setModifying(false);
        blastFenceModel = null;
        creatingBlastFence = false;
        modifyingBlastFence = false;
        exclusionModel = null;
        creatingExclude = false;
        modifyingExclude = false;
        startPoint = null;
        endPoint = null;
        vertexModel = null;
        if(SelectedItem.getInstance().getBaseModel() instanceof BoundaryFenceModel)
        {
            boundaryFenceModel = (BoundaryFenceModel)SelectedItem.getInstance().getBaseModel();
            modifyingFence = true;
        } else
        if(SelectedItem.getInstance().getBaseModel() instanceof BlastFenceModel)
        {
            blastFenceModel = (BlastFenceModel)SelectedItem.getInstance().getBaseModel();
            modifyingBlastFence = true;
        } else
        if(SelectedItem.getInstance().getBaseModel() instanceof ApronEdgeLightModel)
        {
            apronEdgeLightModel = (ApronEdgeLightModel)SelectedItem.getInstance().getBaseModel();
            modifyingApronEdgeLight = true;
        } else
        if(SelectedItem.getInstance().getBaseModel() instanceof ExclusionModel)
        {
            exclusionModel = (ExclusionModel)SelectedItem.getInstance().getBaseModel();
            modifyingExclude = true;
        }
    }

    public void focusOn(BaseModel model)
    {
        scale = 0.5F;
        SelectedItem.getInstance().selectBaseModel(model);
        model.setCurrentlySelected(true);
        if(model instanceof JetwayModel)
        {
            java.awt.geom.Point2D.Float point = Utilities.getPixelsBetweenPoints(centerPoint.getLat(), centerPoint.getLon(), ((JetwayModel)model).getLatLon().getLat(), ((JetwayModel)model).getLatLon().getLon(), scale);
            centerX = (double)getSize().width / 2D - (double)point.x;
            centerY = (double)getSize().height / 2D - (double)point.y;
        } else
        if(model instanceof TaxiwayParkingModel)
        {
            java.awt.geom.Point2D.Float point = Utilities.getPixelsBetweenPoints(centerPoint.getLat(), centerPoint.getLon(), ((TaxiwayParkingModel)model).getLatLon().getLat(), ((TaxiwayParkingModel)model).getLatLon().getLon(), scale);
            centerX = (double)getSize().width / 2D - (double)point.x;
            centerY = (double)getSize().height / 2D - (double)point.y;
        } else
        if(model instanceof TaxiwayPointModel)
        {
            java.awt.geom.Point2D.Float point = Utilities.getPixelsBetweenPoints(centerPoint.getLat(), centerPoint.getLon(), ((TaxiwayPointModel)model).getLatLon().getLat(), ((TaxiwayPointModel)model).getLatLon().getLon(), scale);
            centerX = (double)getSize().width / 2D - (double)point.x;
            centerY = (double)getSize().height / 2D - (double)point.y;
        } else
        if(model instanceof TaxiwayPathModel)
        {
            java.awt.geom.Point2D.Float point = Utilities.getPixelsBetweenPoints(centerPoint.getLat(), centerPoint.getLon(), ((TaxiwayPathModel)model).getStartLat(), ((TaxiwayPathModel)model).getStartLon(), scale);
            centerX = (double)getSize().width / 2D - (double)point.x;
            centerY = (double)getSize().height / 2D - (double)point.y;
        } else
        if(model instanceof VORModel)
        {
            java.awt.geom.Point2D.Float point = Utilities.getPixelsBetweenPoints(centerPoint.getLat(), centerPoint.getLon(), ((VORModel)model).getLatLon().getLat(), ((VORModel)model).getLatLon().getLon(), scale);
            centerX = (double)getSize().width / 2D - (double)point.x;
            centerY = (double)getSize().height / 2D - (double)point.y;
        } else
        if(model instanceof NDBModel)
        {
            java.awt.geom.Point2D.Float point = Utilities.getPixelsBetweenPoints(centerPoint.getLat(), centerPoint.getLon(), ((NDBModel)model).getLatLon().getLat(), ((NDBModel)model).getLatLon().getLon(), scale);
            centerX = (double)getSize().width / 2D - (double)point.x;
            centerY = (double)getSize().height / 2D - (double)point.y;
        } else
        if(model instanceof ILSModel)
        {
            java.awt.geom.Point2D.Float point = Utilities.getPixelsBetweenPoints(centerPoint.getLat(), centerPoint.getLon(), ((ILSModel)model).getLatLon().getLat(), ((ILSModel)model).getLatLon().getLon(), scale);
            centerX = (double)getSize().width / 2D - (double)point.x;
            centerY = (double)getSize().height / 2D - (double)point.y;
        } else
        if(model instanceof RunwayModel)
        {
            java.awt.geom.Point2D.Float point = Utilities.getPixelsBetweenPoints(centerPoint.getLat(), centerPoint.getLon(), ((RunwayModel)model).getLatLon().getLat(), ((RunwayModel)model).getLatLon().getLon(), scale);
            centerX = (double)getSize().width / 2D - (double)point.x;
            centerY = (double)getSize().height / 2D - (double)point.y;
        }
        recreate = true;
        repaint();
    }

    public void resetCenterPoint(LatLonPoint centerPoint)
    {
        this.centerPoint = centerPoint;
        centerSet = false;
        recreate = true;
        repaint();
    }

    public void componentHidden(ComponentEvent componentevent)
    {
    }

    public void componentMoved(ComponentEvent componentevent)
    {
    }

    public void componentResized(ComponentEvent event)
    {
        if(event.getSource() == this)
        {
            centerSet = false;
            repaint();
        }
    }

    public void componentShown(ComponentEvent componentevent)
    {
    }

    public void mouseClicked(MouseEvent event)
    {
        if(event.getSource() == this)
        {
            java.awt.geom.Point2D.Float rotatedPointF = Utilities.rotatePoint(new java.awt.geom.Point2D.Float((float)centerX, (float)centerY), new java.awt.geom.Point2D.Float(event.getPoint().x, event.getPoint().y), (float)(360D - ((RotationModel)airportModel.getRotationAL().get(0)).getRotation()));
            Point rotatedPoint = new Point((int)rotatedPointF.getX(), (int)rotatedPointF.getY());
            if(event.getButton() == 1)
                if(getCursor() == CursorEngine.getInstance().getApronCursor())
                {
                    if(event.getClickCount() == 1)
                    {
                        if(event.isShiftDown())
                            addApronVertex(rotatedPoint);
                        else
                        if(event.isControlDown())
                            deleteApronVertex(rotatedPoint);
                        else
                            createApron(rotatedPoint);
                    } else
                    if(event.getClickCount() == 2)
                        finishApron(rotatedPoint);
                } else
                if(getCursor() == CursorEngine.getInstance().getFuelCursor())
                {
                    if(event.getClickCount() == 1)
                    {
                        if(event.isShiftDown())
                            addTriggerVertex(rotatedPoint);
                        else
                        if(event.isControlDown())
                            deleteTriggerVertex(rotatedPoint);
                        else
                            createTrigger(rotatedPoint);
                    } else
                    if(event.getClickCount() == 2)
                        finishTrigger(rotatedPoint);
                } else
                if(getCursor() == CursorEngine.getInstance().getApronEdgeLightCursor())
                {
                    if(event.getClickCount() == 1)
                    {
                        if(event.isShiftDown())
                            addApronEdgeLightVertex(rotatedPoint);
                        else
                        if(event.isControlDown())
                            deleteApronEdgeLightVertex(rotatedPoint);
                        else
                            createApronEdgeLight(rotatedPoint);
                    } else
                    if(event.getClickCount() == 2)
                        finishApronEdgeLight(rotatedPoint, event.isShiftDown());
                } else
                if(getCursor() == CursorEngine.getInstance().getFenceCursor())
                {
                    if(event.getClickCount() == 1)
                    {
                        if(event.isShiftDown())
                            addFenceVertex(rotatedPoint);
                        else
                        if(event.isControlDown())
                            deleteFenceVertex(rotatedPoint);
                        else
                            createFence(rotatedPoint);
                    } else
                    if(event.getClickCount() == 2)
                        finishFence(rotatedPoint, event.isShiftDown());
                } else
                if(getCursor() == CursorEngine.getInstance().getBlastFenceCursor())
                {
                    if(event.getClickCount() == 1)
                    {
                        if(event.isShiftDown())
                            addBlastFenceVertex(rotatedPoint);
                        else
                        if(event.isControlDown())
                            deleteBlastFenceVertex(rotatedPoint);
                        else
                            createBlastFence(rotatedPoint);
                    } else
                    if(event.getClickCount() == 2)
                        finishBlastFence(rotatedPoint);
                } else
                if(getCursor() == CursorEngine.getInstance().getZoomInCursor())
                    zoomFromWheel(rotatedPoint, -1);
                else
                if(getCursor() == CursorEngine.getInstance().getZoomOutCursor())
                    zoomFromWheel(rotatedPoint, 1);
        }
    }

    public void mousePressed(MouseEvent event)
    {
        if(event.getSource() == this)
        {
            oldX = event.getPoint().x;
            oldY = event.getPoint().y;
            java.awt.geom.Point2D.Float rotatedPointF = Utilities.rotatePoint(new java.awt.geom.Point2D.Float((float)centerX, (float)centerY), new java.awt.geom.Point2D.Float(oldX, oldY), (float)(360D - ((RotationModel)airportModel.getRotationAL().get(0)).getRotation()));
            Point rotatedPoint = new Point((int)rotatedPointF.getX(), (int)rotatedPointF.getY());
            if(event.getButton() == 2)
                setCursor(CursorEngine.getInstance().getScrollCursor());
            else
            if(event.getButton() == 1)
            {
                if(getCursor() == CursorEngine.getInstance().getStartCursor())
                    insertStartLocation(rotatedPoint);
                else
                if(getCursor() == CursorEngine.getInstance().getTowerCursor())
                    insertTower(rotatedPoint);
                else
                if(getCursor() == CursorEngine.getInstance().getParkingCursor())
                    insertParkingLocation(rotatedPoint);
                else
                if(getCursor() == CursorEngine.getInstance().getTaxiPointCursor())
                    insertTaxiwayPoint(rotatedPoint, event.isShiftDown(), "NORMAL");
                else
                if(getCursor() == CursorEngine.getInstance().getILSTaxiPointCursor())
                    insertTaxiwayPoint(rotatedPoint, event.isShiftDown(), "ILS_HOLD_SHORT");
                else
                if(getCursor() == CursorEngine.getInstance().getHSTaxiPointCursor())
                    insertTaxiwayPoint(rotatedPoint, event.isShiftDown(), "HOLD_SHORT");
                else
                if(getCursor() == CursorEngine.getInstance().getTaxiwaySignCursor())
                    insertTaxiwaySign(rotatedPoint);
                else
                if(getCursor() == CursorEngine.getInstance().getHelipadCursor())
                    insertHelipad(rotatedPoint);
                else
                if(getCursor() == CursorEngine.getInstance().getTaxiwayCursor())
                {
                    startPoint = rotatedPoint;
                    createTaxiway(rotatedPoint, event.isShiftDown());
                } else
                if(getCursor() == CursorEngine.getInstance().getExcludeCursor())
                    createExclude(rotatedPoint);
                else
                if(getCursor() == CursorEngine.getInstance().getRunwayCursor())
                    insertRunway(rotatedPoint);
                else
                if(getCursor() == CursorEngine.getInstance().getApronCursor())
                    startApronDrag(rotatedPoint);
                else
                if(getCursor() == CursorEngine.getInstance().getFuelCursor())
                    startTriggerDrag(rotatedPoint);
                else
                if(getCursor() == CursorEngine.getInstance().getApronEdgeLightCursor())
                    startApronEdgeLightDrag(rotatedPoint);
                else
                if(getCursor() == CursorEngine.getInstance().getFenceCursor())
                    startFenceDrag(rotatedPoint);
                else
                if(getCursor() == CursorEngine.getInstance().getBlastFenceCursor())
                    startBlastFenceDrag(rotatedPoint);
                else
                if(getCursor() == CursorEngine.getInstance().getJetwayCursor())
                    insertJetway(rotatedPoint);
                else
                if(getCursor() == CursorEngine.getInstance().getILSBeamCursor())
                    insertILSBeam(rotatedPoint);
                else
                if(getCursor() == CursorEngine.getInstance().getMarkerCursor())
                    insertMarker(rotatedPoint);
                else
                if(getCursor() == CursorEngine.getInstance().getVORCursor())
                    insertVOR(rotatedPoint);
                else
                if(getCursor() == CursorEngine.getInstance().getNDBCursor())
                    insertNDB(rotatedPoint);
                else
                if(getCursor() == CursorEngine.getInstance().getWindsockCursor())
                    insertWindsock(rotatedPoint);
                else
                if(getCursor() == CursorEngine.getInstance().getSceneryCursor())
                    insertScenery(rotatedPoint);
                else
                if(getCursor() == CursorEngine.getInstance().getDefaultCursor())
                    findItem(rotatedPoint, rotatedPoint);
            } else
            if(event.isPopupTrigger())
            {
                mouseClickPoint = event.getPoint();
                CursorPopupMenu.getInstance().updateMenu(vertexModel != null);
                CursorPopupMenu.getInstance().show(this, event.getPoint().x, event.getPoint().y);
            }
        }
    }

    public void mouseReleased(MouseEvent event)
    {
        if(event.getSource() == this)
        {
            java.awt.geom.Point2D.Float rotatedPointF = Utilities.rotatePoint(new java.awt.geom.Point2D.Float((float)centerX, (float)centerY), new java.awt.geom.Point2D.Float(event.getPoint().x, event.getPoint().y), (float)(360D - ((RotationModel)airportModel.getRotationAL().get(0)).getRotation()));
            Point rotatedPoint = new Point((int)rotatedPointF.getX(), (int)rotatedPointF.getY());
            if(event.getButton() == 2)
                setCursor(CursorEngine.getInstance().getCurrentCursor());
            else
            if(event.getButton() == 1)
            {
                if(getCursor() == CursorEngine.getInstance().getTaxiwayCursor())
                    finishTaxiway(rotatedPoint, event.isShiftDown());
                else
                if(getCursor() == CursorEngine.getInstance().getExcludeCursor())
                    finishExclude(rotatedPoint);
                else
                if(getCursor() == CursorEngine.getInstance().getApronCursor())
                    vertexModel = null;
                else
                if(getCursor() == CursorEngine.getInstance().getFuelCursor())
                    vertexModel = null;
                else
                if(getCursor() == CursorEngine.getInstance().getApronEdgeLightCursor())
                {
                    mergeApronEdgeLights(rotatedPoint, event.isShiftDown());
                    vertexModel = null;
                } else
                if(getCursor() == CursorEngine.getInstance().getFenceCursor())
                {
                    mergeFence(rotatedPoint, event.isShiftDown());
                    vertexModel = null;
                } else
                if(getCursor() == CursorEngine.getInstance().getBlastFenceCursor())
                {
                    mergeBlastFence(rotatedPoint, event.isShiftDown());
                    vertexModel = null;
                } else
                if(getCursor() == CursorEngine.getInstance().getDefaultCursor() && (SelectedItem.getInstance().getBaseModel() instanceof TaxiwayPointModel))
                    ILSLineUpdates.getInstance().addAirportModel(airportModel);
                else
                if(SelectedItem.getInstance().getBaseModel() instanceof BoundaryFenceModel)
                    mergeFence(rotatedPoint, event.isShiftDown());
                else
                if(SelectedItem.getInstance().getBaseModel() instanceof BlastFenceModel)
                    mergeBlastFence(rotatedPoint, event.isShiftDown());
                else
                if(SelectedItem.getInstance().getBaseModel() instanceof ApronEdgeLightModel)
                    mergeApronEdgeLights(rotatedPoint, event.isShiftDown());
                else
                if((SelectedItem.getInstance().getBaseModel() instanceof PlaneModel) && FSXConnection.getInstance().isConnectedToFSX() && FSXConnection.getInstance().getFSXAutoFollow())
                    FSXConnection.getInstance().movePlane(((PlaneModel)airportModel.getPlaneAL().get(0)).getLatLon(), ((PlaneModel)airportModel.getPlaneAL().get(0)).getHeading());
            } else
            if(event.isPopupTrigger())
            {
                mouseClickPoint = event.getPoint();
                CursorPopupMenu.getInstance().updateMenu(vertexModel != null);
                CursorPopupMenu.getInstance().show(this, event.getPoint().x, event.getPoint().y);
            }
        }
        ZPopupGallery.hidePopups(null);
    }

    public void mouseEntered(MouseEvent mouseevent)
    {
    }

    public void mouseExited(MouseEvent mouseevent)
    {
    }

    public void mouseDragged(MouseEvent event)
    {
        if(event.getSource() == this)
        {
            java.awt.geom.Point2D.Float rotatedPointF = Utilities.rotatePoint(new java.awt.geom.Point2D.Float((float)centerX, (float)centerY), new java.awt.geom.Point2D.Float(event.getPoint().x, event.getPoint().y), (float)(360D - ((RotationModel)airportModel.getRotationAL().get(0)).getRotation()));
            Point rotatedPoint = new Point((int)rotatedPointF.getX(), (int)rotatedPointF.getY());
            if(getCursor() == CursorEngine.getInstance().getScrollCursor())
                scrollAirport(event.getPoint().x, event.getPoint().y);
            else
            if(getCursor() == CursorEngine.getInstance().getTaxiwayCursor())
            {
                endPoint = rotatedPoint;
                checkTaxiwayPoint(rotatedPoint);
            } else
            if(getCursor() == CursorEngine.getInstance().getApronCursor())
                dragApron(rotatedPoint);
            else
            if(getCursor() == CursorEngine.getInstance().getFuelCursor())
                dragTrigger(rotatedPoint);
            else
            if(getCursor() == CursorEngine.getInstance().getApronEdgeLightCursor())
                dragApronEdgeLight(rotatedPoint);
            else
            if(getCursor() == CursorEngine.getInstance().getFenceCursor())
                dragFence(rotatedPoint);
            else
            if(getCursor() == CursorEngine.getInstance().getBlastFenceCursor())
                dragBlastFence(rotatedPoint);
            else
            if(getCursor() == CursorEngine.getInstance().getExcludeCursor())
                dragExclude(rotatedPoint);
            else
                dragItem(rotatedPoint);
            displayLatLon(rotatedPoint);
        }
    }

    public void mouseMoved(MouseEvent event)
    {
        if(event.getSource() == this)
        {
            java.awt.geom.Point2D.Float rotatedPointF = Utilities.rotatePoint(new java.awt.geom.Point2D.Float((float)centerX, (float)centerY), new java.awt.geom.Point2D.Float(event.getPoint().x, event.getPoint().y), (float)(360D - ((RotationModel)airportModel.getRotationAL().get(0)).getRotation()));
            Point rotatedPoint = new Point((int)rotatedPointF.getX(), (int)rotatedPointF.getY());
            displayLatLon(rotatedPoint);
            if(getCursor() == CursorEngine.getInstance().getTaxiwayCursor())
                checkTaxiwayPoint(rotatedPoint);
            else
            if(getCursor() == CursorEngine.getInstance().getApronCursor())
            {
                if(startPoint != null)
                {
                    endPoint = rotatedPoint;
                    repaint();
                }
            } else
            if(getCursor() == CursorEngine.getInstance().getFuelCursor())
            {
                if(startPoint != null)
                {
                    endPoint = rotatedPoint;
                    repaint();
                }
            } else
            if(getCursor() == CursorEngine.getInstance().getApronEdgeLightCursor())
            {
                if(startPoint != null)
                {
                    endPoint = rotatedPoint;
                    repaint();
                }
            } else
            if(getCursor() == CursorEngine.getInstance().getFenceCursor())
            {
                if(startPoint != null)
                {
                    endPoint = rotatedPoint;
                    repaint();
                }
            } else
            if(getCursor() == CursorEngine.getInstance().getBlastFenceCursor() && startPoint != null)
            {
                endPoint = rotatedPoint;
                repaint();
            }
        }
    }

    public void mouseWheelMoved(MouseWheelEvent event)
    {
        if(event.getSource() == this)
            if(event.isShiftDown())
                rotateFromWheel(event.getWheelRotation());
            else
                zoomFromWheel(null, event.getWheelRotation());
    }

    public void propertyChange(PropertyChangeEvent event)
    {
        if(event.getSource() instanceof SettingsEngine)
            if(event.getPropertyName().equals("bgImagesBelow"))
                bgImagesBelow = ((Boolean)event.getNewValue()).booleanValue();
            else
            if(event.getPropertyName().equals("displayLights"))
                displayLights = ((Boolean)event.getNewValue()).booleanValue();
            else
            if(event.getPropertyName().equals("displayFPS"))
                displayFPS = ((Boolean)event.getNewValue()).booleanValue();
            else
            if(event.getPropertyName().equals("doubleBuffer"))
                doubleBuffer = ((Boolean)event.getNewValue()).booleanValue();
    }

    private BufferedImage currentFrameBI;
    private BufferedImage lastFrameBI;
    private BufferedImage displayImage;
    private AirportModel airportModel;
    private LatLonPoint centerPoint;
    private LatLonPoint mouseLatLon;
    private NumberFormat secondsFormat;
    private TaxiwayPathModel taxiwayPathModel;
    private ApronModel apronModel;
    private TriggerModel triggerModel;
    private ApronEdgeLightModel apronEdgeLightModel;
    private BoundaryFenceModel boundaryFenceModel;
    private BlastFenceModel blastFenceModel;
    private VertexModel vertexModel;
    private ExclusionModel exclusionModel;
    private Point startPoint;
    private Point endPoint;
    private Point mouseClickPoint;
    private NumberFormat fpsFormat;
    private int oldX;
    private int oldY;
    private float scale;
    private double centerX;
    private double centerY;
    private boolean centerSet;
    private boolean recreate;
    private boolean recreateBGImage;
    private boolean recreateBGAllImage;
    private boolean creatingApron;
    private boolean modifyingApron;
    private boolean creatingTrigger;
    private boolean modifyingTrigger;
    private boolean creatingApronEdgeLight;
    private boolean modifyingApronEdgeLight;
    private boolean creatingFence;
    private boolean modifyingFence;
    private boolean creatingBlastFence;
    private boolean modifyingBlastFence;
    private boolean creatingExclude;
    private boolean modifyingExclude;
    private boolean bgImagesBelow;
    private boolean displayLights;
    private boolean drawing;
    private boolean redrawScroll;
    private boolean displayFPS;
    private boolean doubleBuffer;


}
