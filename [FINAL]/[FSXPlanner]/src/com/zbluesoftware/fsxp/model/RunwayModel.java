// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RunwayModel.java

package com.zbluesoftware.fsxp.model;

import com.zbluesoftware.fsxp.engine.*;
import com.zbluesoftware.fsxp.util.LatLonPoint;
import com.zbluesoftware.fsxp.util.Utilities;
import java.awt.*;
import java.awt.geom.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

// Referenced classes of package com.zbluesoftware.fsxp.model:
//            BaseModel, MarkingsModel, LightsModel, HistoryModel, 
//            ILSModel, RunwayDetailModel, ApproachLightsModel, VasiModel, 
//            TaxiwayPathModel, HistoryListModel

public class RunwayModel extends BaseModel
    implements PropertyChangeListener
{

    public RunwayModel()
    {
        modelName = "Runway";
        markingsModel = new MarkingsModel();
        lightsModel = new LightsModel();
        ilsAL = new ArrayList();
        markingAlpha = 198;
        primaryOffsetModel = null;
        secondaryOffsetModel = null;
        primaryBlastPadModel = null;
        secondaryBlastPadModel = null;
        primaryOverrunModel = null;
        secondaryOverrunModel = null;
        primaryApproachLightsModel = null;
        secondaryApproachLightsModel = null;
        primaryLeftVasiModel = null;
        primaryRightVasiModel = null;
        secondaryLeftVasiModel = null;
        secondaryRightVasiModel = null;
        altMeasure = "M";
        lengthMeasure = SettingsEngine.getInstance().getRunwayLengthMeasure();
        widthMeasure = SettingsEngine.getInstance().getRunwayWidthMeasure();
        patternAltitudeMeasure = "M";
        primaryMarkingBiasMeasure = "M";
        secondaryMarkingBiasMeasure = "M";
        surface = "ASPHALT";
        number = "36";
        designator = "";
        primaryDesignator = "";
        secondaryDesignator = "";
        primaryPattern = "LEFT";
        secondaryPattern = "LEFT";
        alt = 0.0F;
        length = SettingsEngine.getInstance().getRunwayLength();
        width = SettingsEngine.getInstance().getRunwayWidth();
        heading = 0.0F;
        primaryMarkingBias = 0.0D;
        secondaryMarkingBias = 0.0D;
        patternAltitude = 0.0F;
        primaryTakeoff = true;
        primaryLanding = true;
        secondaryTakeoff = true;
        secondaryLanding = true;
        latLon = new LatLonPoint();
        displayLights = SettingsEngine.getInstance().getDisplayLights();
        SettingsEngine.getInstance().addPropertyChangeListener(this);
    }

    public java.awt.geom.Point2D.Float getRunwayCP()
    {
        return runwayCP;
    }

    public void paint(Graphics2D g2, boolean recreate)
    {
        if(recreate || modelPath == null)
        {
            runwayCP = Utilities.getPixelsBetweenPoints(centerPoint.getLat(), centerPoint.getLon(), latLon.getLat(), latLon.getLon(), scale);
            int sizeWidth = widthMeasure.equals("M") ? (int)(width * 3.28F * scale) : (int)(width * scale);
            int sizeLength = lengthMeasure.equals("M") ? (int)(length * 3.28F * scale) : (int)(length * scale);
            java.awt.geom.Point2D.Float topLeft = new java.awt.geom.Point2D.Float((float)(runwayCP.getX() - (double)sizeWidth / 2D), (float)(runwayCP.getY() - (double)sizeLength / 2D));
            java.awt.geom.Point2D.Float topRight = new java.awt.geom.Point2D.Float((float)(runwayCP.getX() + (double)sizeWidth / 2D), (float)(runwayCP.getY() - (double)sizeLength / 2D));
            java.awt.geom.Point2D.Float bottomLeft = new java.awt.geom.Point2D.Float((float)(runwayCP.getX() - (double)sizeWidth / 2D), (float)(runwayCP.getY() + (double)sizeLength / 2D));
            java.awt.geom.Point2D.Float bottomRight = new java.awt.geom.Point2D.Float((float)(runwayCP.getX() + (double)sizeWidth / 2D), (float)(runwayCP.getY() + (double)sizeLength / 2D));
            if(primaryOffsetModel != null)
            {
                int pOffsetWidth = primaryOffsetModel.getWidthMeasure().equals("M") ? (int)(primaryOffsetModel.getWidth() * 3.28F) : (int)primaryOffsetModel.getWidth();
                int pOffsetLength = primaryOffsetModel.getLengthMeasure().equals("M") ? (int)(primaryOffsetModel.getLength() * 3.28F * scale) : (int)(primaryOffsetModel.getLength() * scale);
                java.awt.geom.Point2D.Float pOffsetTopLeft = new java.awt.geom.Point2D.Float((float)(runwayCP.getX() - (double)sizeWidth / 2D), (float)(bottomLeft.getY() - (double)pOffsetLength));
                java.awt.geom.Point2D.Float pOffsetTopRight = new java.awt.geom.Point2D.Float((float)(runwayCP.getX() + (double)sizeWidth / 2D), (float)(bottomRight.getY() - (double)pOffsetLength));
                pOffsetTopLeft = Utilities.rotatePoint(runwayCP, pOffsetTopLeft, heading);
                pOffsetTopRight = Utilities.rotatePoint(runwayCP, pOffsetTopRight, heading);
                pOffsetPath = new GeneralPath();
                pOffsetPath.moveTo(pOffsetTopLeft.x, pOffsetTopLeft.y);
                pOffsetPath.lineTo(pOffsetTopRight.x, pOffsetTopRight.y);
                for(float offset = 15F; !markingsModel.getNoThresholdEndArrows() && (double)offset < (double)pOffsetWidth / 2D; offset += 35F)
                {
                    java.awt.geom.Point2D.Float pArrowLeft = new java.awt.geom.Point2D.Float((float)(runwayCP.getX() - (double)((offset + 10F) * scale)), (float)((bottomRight.getY() - (double)pOffsetLength) + (double)(35F * scale)));
                    java.awt.geom.Point2D.Float pArrowCenter = new java.awt.geom.Point2D.Float((float)(runwayCP.getX() - (double)((offset + 5F) * scale)), (float)((bottomRight.getY() - (double)pOffsetLength) + (double)(10F * scale)));
                    java.awt.geom.Point2D.Float pArrowRight = new java.awt.geom.Point2D.Float((float)(runwayCP.getX() - (double)(offset * scale)), (float)((bottomLeft.getY() - (double)pOffsetLength) + (double)(35F * scale)));
                    pArrowLeft = Utilities.rotatePoint(runwayCP, pArrowLeft, heading);
                    pArrowCenter = Utilities.rotatePoint(runwayCP, pArrowCenter, heading);
                    pArrowRight = Utilities.rotatePoint(runwayCP, pArrowRight, heading);
                    pOffsetPath.moveTo(pArrowLeft.x, pArrowLeft.y);
                    pOffsetPath.lineTo(pArrowCenter.x, pArrowCenter.y);
                    pOffsetPath.lineTo(pArrowRight.x, pArrowRight.y);
                    pArrowLeft = new java.awt.geom.Point2D.Float((float)(runwayCP.getX() + (double)((offset + 10F) * scale)), (float)((bottomRight.getY() - (double)pOffsetLength) + (double)(35F * scale)));
                    pArrowCenter = new java.awt.geom.Point2D.Float((float)(runwayCP.getX() + (double)((offset + 5F) * scale)), (float)((bottomRight.getY() - (double)pOffsetLength) + (double)(10F * scale)));
                    pArrowRight = new java.awt.geom.Point2D.Float((float)(runwayCP.getX() + (double)(offset * scale)), (float)((bottomLeft.getY() - (double)pOffsetLength) + (double)(35F * scale)));
                    pArrowLeft = Utilities.rotatePoint(runwayCP, pArrowLeft, heading);
                    pArrowCenter = Utilities.rotatePoint(runwayCP, pArrowCenter, heading);
                    pArrowRight = Utilities.rotatePoint(runwayCP, pArrowRight, heading);
                    pOffsetPath.moveTo(pArrowLeft.x, pArrowLeft.y);
                    pOffsetPath.lineTo(pArrowCenter.x, pArrowCenter.y);
                    pOffsetPath.lineTo(pArrowRight.x, pArrowRight.y);
                }

                for(float offset = 75F * scale; offset < (float)pOffsetLength; offset += 175F * scale)
                {
                    java.awt.geom.Point2D.Float pArrowLeft = new java.awt.geom.Point2D.Float((float)(runwayCP.getX() - (double)(5F * scale)), (float)(((bottomRight.getY() + (double)offset) - (double)pOffsetLength) + (double)(35F * scale)));
                    java.awt.geom.Point2D.Float pArrowCenter = new java.awt.geom.Point2D.Float((float)runwayCP.getX(), (float)(((bottomRight.getY() + (double)offset) - (double)pOffsetLength) + (double)(10F * scale)));
                    java.awt.geom.Point2D.Float pArrowRight = new java.awt.geom.Point2D.Float((float)(runwayCP.getX() + (double)(5F * scale)), (float)(((bottomLeft.getY() + (double)offset) - (double)pOffsetLength) + (double)(35F * scale)));
                    pArrowLeft = Utilities.rotatePoint(runwayCP, pArrowLeft, heading);
                    pArrowCenter = Utilities.rotatePoint(runwayCP, pArrowCenter, heading);
                    pArrowRight = Utilities.rotatePoint(runwayCP, pArrowRight, heading);
                    pOffsetPath.moveTo(pArrowLeft.x, pArrowLeft.y);
                    pOffsetPath.lineTo(pArrowCenter.x, pArrowCenter.y);
                    pOffsetPath.lineTo(pArrowRight.x, pArrowRight.y);
                    pArrowLeft = new java.awt.geom.Point2D.Float((float)runwayCP.getX(), (float)(((bottomRight.getY() + (double)offset) - (double)pOffsetLength) + (double)(65F * scale)));
                    pArrowCenter = new java.awt.geom.Point2D.Float((float)runwayCP.getX(), (float)(((bottomRight.getY() + (double)offset) - (double)pOffsetLength) + (double)(35F * scale)));
                    pArrowLeft = Utilities.rotatePoint(runwayCP, pArrowLeft, heading);
                    pArrowCenter = Utilities.rotatePoint(runwayCP, pArrowCenter, heading);
                    pOffsetPath.moveTo(pArrowLeft.x, pArrowLeft.y);
                    pOffsetPath.lineTo(pArrowCenter.x, pArrowCenter.y);
                }

            }
            if(secondaryOffsetModel != null)
            {
                int sOffsetWidth = secondaryOffsetModel.getWidthMeasure().equals("M") ? (int)(secondaryOffsetModel.getWidth() * 3.28F) : (int)secondaryOffsetModel.getWidth();
                int sOffsetLength = secondaryOffsetModel.getLengthMeasure().equals("M") ? (int)(secondaryOffsetModel.getLength() * 3.28F * scale) : (int)(secondaryOffsetModel.getLength() * scale);
                java.awt.geom.Point2D.Float sOffsetTopLeft = new java.awt.geom.Point2D.Float((float)(runwayCP.getX() - (double)sizeWidth / 2D), (float)(topLeft.getY() + (double)sOffsetLength));
                java.awt.geom.Point2D.Float sOffsetTopRight = new java.awt.geom.Point2D.Float((float)(runwayCP.getX() + (double)sizeWidth / 2D), (float)(topRight.getY() + (double)sOffsetLength));
                sOffsetTopLeft = Utilities.rotatePoint(runwayCP, sOffsetTopLeft, heading);
                sOffsetTopRight = Utilities.rotatePoint(runwayCP, sOffsetTopRight, heading);
                sOffsetPath = new GeneralPath();
                sOffsetPath.moveTo(sOffsetTopLeft.x, sOffsetTopLeft.y);
                sOffsetPath.lineTo(sOffsetTopRight.x, sOffsetTopRight.y);
                for(float offset = 15F; (double)offset < (double)sOffsetWidth / 2D; offset += 35F)
                {
                    java.awt.geom.Point2D.Float sArrowLeft = new java.awt.geom.Point2D.Float((float)(runwayCP.getX() - (double)((offset + 10F) * scale)), (float)((topRight.getY() + (double)sOffsetLength) - (double)(35F * scale)));
                    java.awt.geom.Point2D.Float sArrowCenter = new java.awt.geom.Point2D.Float((float)(runwayCP.getX() - (double)((offset + 5F) * scale)), (float)((topRight.getY() + (double)sOffsetLength) - (double)(10F * scale)));
                    java.awt.geom.Point2D.Float sArrowRight = new java.awt.geom.Point2D.Float((float)(runwayCP.getX() - (double)(offset * scale)), (float)((topLeft.getY() + (double)sOffsetLength) - (double)(35F * scale)));
                    sArrowLeft = Utilities.rotatePoint(runwayCP, sArrowLeft, heading);
                    sArrowCenter = Utilities.rotatePoint(runwayCP, sArrowCenter, heading);
                    sArrowRight = Utilities.rotatePoint(runwayCP, sArrowRight, heading);
                    sOffsetPath.moveTo(sArrowLeft.x, sArrowLeft.y);
                    sOffsetPath.lineTo(sArrowCenter.x, sArrowCenter.y);
                    sOffsetPath.lineTo(sArrowRight.x, sArrowRight.y);
                    sArrowLeft = new java.awt.geom.Point2D.Float((float)(runwayCP.getX() + (double)((offset + 10F) * scale)), (float)((topRight.getY() + (double)sOffsetLength) - (double)(35F * scale)));
                    sArrowCenter = new java.awt.geom.Point2D.Float((float)(runwayCP.getX() + (double)((offset + 5F) * scale)), (float)((topRight.getY() + (double)sOffsetLength) - (double)(10F * scale)));
                    sArrowRight = new java.awt.geom.Point2D.Float((float)(runwayCP.getX() + (double)(offset * scale)), (float)((topLeft.getY() + (double)sOffsetLength) - (double)(35F * scale)));
                    sArrowLeft = Utilities.rotatePoint(runwayCP, sArrowLeft, heading);
                    sArrowCenter = Utilities.rotatePoint(runwayCP, sArrowCenter, heading);
                    sArrowRight = Utilities.rotatePoint(runwayCP, sArrowRight, heading);
                    sOffsetPath.moveTo(sArrowLeft.x, sArrowLeft.y);
                    sOffsetPath.lineTo(sArrowCenter.x, sArrowCenter.y);
                    sOffsetPath.lineTo(sArrowRight.x, sArrowRight.y);
                }

                for(float offset = 75F * scale; offset < (float)sOffsetLength; offset += 175F * scale)
                {
                    java.awt.geom.Point2D.Float sArrowLeft = new java.awt.geom.Point2D.Float((float)(runwayCP.getX() - (double)(5F * scale)), (float)(((topRight.getY() - (double)offset) + (double)sOffsetLength) - (double)(35F * scale)));
                    java.awt.geom.Point2D.Float sArrowCenter = new java.awt.geom.Point2D.Float((float)runwayCP.getX(), (float)(((topRight.getY() - (double)offset) + (double)sOffsetLength) - (double)(10F * scale)));
                    java.awt.geom.Point2D.Float sArrowRight = new java.awt.geom.Point2D.Float((float)(runwayCP.getX() + (double)(5F * scale)), (float)(((topLeft.getY() - (double)offset) + (double)sOffsetLength) - (double)(35F * scale)));
                    sArrowLeft = Utilities.rotatePoint(runwayCP, sArrowLeft, heading);
                    sArrowCenter = Utilities.rotatePoint(runwayCP, sArrowCenter, heading);
                    sArrowRight = Utilities.rotatePoint(runwayCP, sArrowRight, heading);
                    sOffsetPath.moveTo(sArrowLeft.x, sArrowLeft.y);
                    sOffsetPath.lineTo(sArrowCenter.x, sArrowCenter.y);
                    sOffsetPath.lineTo(sArrowRight.x, sArrowRight.y);
                    sArrowLeft = new java.awt.geom.Point2D.Float((float)runwayCP.getX(), (float)(((topRight.getY() - (double)offset) + (double)sOffsetLength) - (double)(65F * scale)));
                    sArrowCenter = new java.awt.geom.Point2D.Float((float)runwayCP.getX(), (float)(((topRight.getY() - (double)offset) + (double)sOffsetLength) - (double)(35F * scale)));
                    sArrowLeft = Utilities.rotatePoint(runwayCP, sArrowLeft, heading);
                    sArrowCenter = Utilities.rotatePoint(runwayCP, sArrowCenter, heading);
                    sOffsetPath.moveTo(sArrowLeft.x, sArrowLeft.y);
                    sOffsetPath.lineTo(sArrowCenter.x, sArrowCenter.y);
                }

            }
            if(primaryBlastPadModel != null)
            {
                int pBlastWidth = 0;
                if(primaryBlastPadModel.getWidthMeasure().equals("M"))
                    pBlastWidth = (int)(primaryBlastPadModel.getWidth() * 3.28F * scale);
                else
                    pBlastWidth = (int)(primaryBlastPadModel.getWidth() * scale);
                int pBlastLength = 0;
                if(primaryBlastPadModel.getLengthMeasure().equals("M"))
                    pBlastLength = (int)(primaryBlastPadModel.getLength() * 3.28F * scale);
                else
                    pBlastLength = (int)(primaryBlastPadModel.getLength() * scale);
                java.awt.geom.Point2D.Float pBlastTopLeft = new java.awt.geom.Point2D.Float((float)(runwayCP.getX() - (double)pBlastWidth / 2D), (float)bottomLeft.getY());
                java.awt.geom.Point2D.Float pBlastTopRight = new java.awt.geom.Point2D.Float((float)(runwayCP.getX() + (double)pBlastWidth / 2D), (float)bottomRight.getY());
                java.awt.geom.Point2D.Float pBlastBottomLeft = new java.awt.geom.Point2D.Float((float)(runwayCP.getX() - (double)pBlastWidth / 2D), (float)(bottomLeft.getY() + (double)pBlastLength));
                java.awt.geom.Point2D.Float pBlastBottomRight = new java.awt.geom.Point2D.Float((float)(runwayCP.getX() + (double)pBlastWidth / 2D), (float)(bottomRight.getY() + (double)pBlastLength));
                pBlastTopLeft = Utilities.rotatePoint(runwayCP, pBlastTopLeft, heading);
                pBlastTopRight = Utilities.rotatePoint(runwayCP, pBlastTopRight, heading);
                pBlastBottomLeft = Utilities.rotatePoint(runwayCP, pBlastBottomLeft, heading);
                pBlastBottomRight = Utilities.rotatePoint(runwayCP, pBlastBottomRight, heading);
                pBlastPath = new GeneralPath();
                pBlastPath.moveTo(pBlastTopLeft.x, pBlastTopLeft.y);
                pBlastPath.lineTo(pBlastTopRight.x, pBlastTopRight.y);
                pBlastPath.lineTo(pBlastBottomRight.x, pBlastBottomRight.y);
                pBlastPath.lineTo(pBlastBottomLeft.x, pBlastBottomLeft.y);
                pBlastPath.lineTo(pBlastTopLeft.x, pBlastTopLeft.y);
                float offset = -25F * scale;
                pBlastPadLines = new GeneralPath();
                for(; offset < (float)pBlastLength; offset += 100F * scale)
                {
                    java.awt.geom.Point2D.Float pBlastLeft = new java.awt.geom.Point2D.Float((float)(runwayCP.getX() - (double)pBlastWidth / 2D), (float)(bottomRight.getY() + (double)offset + (double)pBlastWidth / 2D));
                    java.awt.geom.Point2D.Float pBlastCenter = new java.awt.geom.Point2D.Float((float)runwayCP.getX(), (float)(bottomRight.getY() + (double)offset));
                    java.awt.geom.Point2D.Float pBlastRight = new java.awt.geom.Point2D.Float((float)(runwayCP.getX() + (double)pBlastWidth / 2D), (float)(bottomLeft.getY() + (double)offset + (double)pBlastWidth / 2D));
                    pBlastLeft = Utilities.rotatePoint(runwayCP, pBlastLeft, heading);
                    pBlastCenter = Utilities.rotatePoint(runwayCP, pBlastCenter, heading);
                    pBlastRight = Utilities.rotatePoint(runwayCP, pBlastRight, heading);
                    pBlastPadLines.moveTo(pBlastLeft.x, pBlastLeft.y);
                    pBlastPadLines.lineTo(pBlastCenter.x, pBlastCenter.y);
                    pBlastPadLines.lineTo(pBlastRight.x, pBlastRight.y);
                }

            }
            if(secondaryBlastPadModel != null)
            {
                int sBlastWidth = 0;
                if(secondaryBlastPadModel.getWidthMeasure().equals("M"))
                    sBlastWidth = (int)(secondaryBlastPadModel.getWidth() * 3.28F * scale);
                else
                    sBlastWidth = (int)(secondaryBlastPadModel.getWidth() * scale);
                int sBlastLength = 0;
                if(secondaryBlastPadModel.getLengthMeasure().equals("M"))
                    sBlastLength = (int)(secondaryBlastPadModel.getLength() * 3.28F * scale);
                else
                    sBlastLength = (int)(secondaryBlastPadModel.getLength() * scale);
                java.awt.geom.Point2D.Float sBlastTopLeft = new java.awt.geom.Point2D.Float((float)(runwayCP.getX() - (double)sBlastWidth / 2D), (float)topLeft.getY());
                java.awt.geom.Point2D.Float sBlastTopRight = new java.awt.geom.Point2D.Float((float)(runwayCP.getX() + (double)sBlastWidth / 2D), (float)topLeft.getY());
                java.awt.geom.Point2D.Float sBlastBottomLeft = new java.awt.geom.Point2D.Float((float)(runwayCP.getX() - (double)sBlastWidth / 2D), (float)(topLeft.getY() - (double)sBlastLength));
                java.awt.geom.Point2D.Float sBlastBottomRight = new java.awt.geom.Point2D.Float((float)(runwayCP.getX() + (double)sBlastWidth / 2D), (float)(topLeft.getY() - (double)sBlastLength));
                sBlastTopLeft = Utilities.rotatePoint(runwayCP, sBlastTopLeft, heading);
                sBlastTopRight = Utilities.rotatePoint(runwayCP, sBlastTopRight, heading);
                sBlastBottomLeft = Utilities.rotatePoint(runwayCP, sBlastBottomLeft, heading);
                sBlastBottomRight = Utilities.rotatePoint(runwayCP, sBlastBottomRight, heading);
                sBlastPath = new GeneralPath();
                sBlastPath.moveTo(sBlastTopLeft.x, sBlastTopLeft.y);
                sBlastPath.lineTo(sBlastTopRight.x, sBlastTopRight.y);
                sBlastPath.lineTo(sBlastBottomRight.x, sBlastBottomRight.y);
                sBlastPath.lineTo(sBlastBottomLeft.x, sBlastBottomLeft.y);
                sBlastPath.lineTo(sBlastTopLeft.x, sBlastTopLeft.y);
                float offset = -25F * scale;
                sBlastPadLines = new GeneralPath();
                for(; offset < (float)sBlastLength; offset += 100F * scale)
                {
                    java.awt.geom.Point2D.Float sBlastLeft = new java.awt.geom.Point2D.Float((float)(runwayCP.getX() - (double)sBlastWidth / 2D), (float)(topLeft.getY() - (double)offset - (double)sBlastWidth / 2D));
                    java.awt.geom.Point2D.Float sBlastCenter = new java.awt.geom.Point2D.Float((float)runwayCP.getX(), (float)(topLeft.getY() - (double)offset));
                    java.awt.geom.Point2D.Float sBlastRight = new java.awt.geom.Point2D.Float((float)(runwayCP.getX() + (double)sBlastWidth / 2D), (float)(topLeft.getY() - (double)offset - (double)sBlastWidth / 2D));
                    sBlastLeft = Utilities.rotatePoint(runwayCP, sBlastLeft, heading);
                    sBlastCenter = Utilities.rotatePoint(runwayCP, sBlastCenter, heading);
                    sBlastRight = Utilities.rotatePoint(runwayCP, sBlastRight, heading);
                    sBlastPadLines.moveTo(sBlastLeft.x, sBlastLeft.y);
                    sBlastPadLines.lineTo(sBlastCenter.x, sBlastCenter.y);
                    sBlastPadLines.lineTo(sBlastRight.x, sBlastRight.y);
                }

            }
            if(markingsModel.getThreshold())
                createThreshold(sizeWidth, runwayCP, topLeft, bottomLeft);
            if(markingsModel.getFixed())
                createFixedDistance(sizeWidth, runwayCP, topLeft, bottomLeft);
            if(markingsModel.getTouchdown())
                createTouchdownMarks(sizeWidth, runwayCP, topLeft, bottomLeft);
            if(markingsModel.getPrecision())
                createPrecisionMarks(sizeWidth, sizeLength, runwayCP, topLeft, bottomLeft);
            if(markingsModel.getDashes())
                createCenterLine(sizeWidth, runwayCP, topLeft, bottomLeft);
            if(markingsModel.getPrimaryClosed())
                createPrimaryClosed(runwayCP, bottomLeft);
            if(markingsModel.getSecondaryClosed())
                createSecondaryClosed(runwayCP, topLeft);
            if(!markingsModel.getPrimaryClosed() && markingsModel.getPrimaryStol())
                createPrimaryStol(runwayCP, bottomLeft);
            if(!markingsModel.getSecondaryClosed() && markingsModel.getSecondaryStol())
                createSecondaryStol(runwayCP, topLeft);
            if(markingsModel.getIdent())
            {
                createPrimaryDesignator(runwayCP, bottomLeft);
                if(!markingsModel.getSingleEnd())
                    createSecondaryDesignator(runwayCP, topLeft);
            }
            topLeft = Utilities.rotatePoint(runwayCP, topLeft, heading);
            bottomLeft = Utilities.rotatePoint(runwayCP, bottomLeft, heading);
            topRight = Utilities.rotatePoint(runwayCP, topRight, heading);
            bottomRight = Utilities.rotatePoint(runwayCP, bottomRight, heading);
            modelPath = new GeneralPath();
            modelPath.moveTo(topLeft.x, topLeft.y);
            modelPath.lineTo(topRight.x, topRight.y);
            modelPath.lineTo(bottomRight.x, bottomRight.y);
            modelPath.lineTo(bottomLeft.x, bottomLeft.y);
            modelPath.lineTo(topLeft.x, topLeft.y);
            if(markingsModel.getEdges())
            {
                float edgeDistance = 6F * scale;
                java.awt.geom.Point2D.Float edgeTopLeft = new java.awt.geom.Point2D.Float((float)(runwayCP.getX() - (double)((float)sizeWidth - edgeDistance) / 2D), (float)(runwayCP.getY() - (double)((float)sizeLength - edgeDistance) / 2D));
                java.awt.geom.Point2D.Float edgeTopRight = new java.awt.geom.Point2D.Float((float)(runwayCP.getX() + (double)((float)sizeWidth - edgeDistance) / 2D), (float)(runwayCP.getY() - (double)((float)sizeLength - edgeDistance) / 2D));
                java.awt.geom.Point2D.Float edgeBottomLeft = new java.awt.geom.Point2D.Float((float)(runwayCP.getX() - (double)((float)sizeWidth - edgeDistance) / 2D), (float)(runwayCP.getY() + (double)((float)sizeLength - edgeDistance) / 2D));
                java.awt.geom.Point2D.Float edgeBottomRight = new java.awt.geom.Point2D.Float((float)(runwayCP.getX() + (double)((float)sizeWidth - edgeDistance) / 2D), (float)(runwayCP.getY() + (double)((float)sizeLength - edgeDistance) / 2D));
                edgeTopLeft = Utilities.rotatePoint(runwayCP, edgeTopLeft, heading);
                edgeBottomLeft = Utilities.rotatePoint(runwayCP, edgeBottomLeft, heading);
                edgeTopRight = Utilities.rotatePoint(runwayCP, edgeTopRight, heading);
                edgeBottomRight = Utilities.rotatePoint(runwayCP, edgeBottomRight, heading);
                edgePath = new GeneralPath();
                edgePath.moveTo(topLeft.x, topLeft.y);
                edgePath.lineTo(edgeTopLeft.x, edgeTopLeft.y);
                edgePath.lineTo(edgeBottomLeft.x, edgeBottomLeft.y);
                edgePath.lineTo(bottomLeft.x, bottomLeft.y);
                edgePath.lineTo(topLeft.x, topLeft.y);
                edgePath.moveTo(topRight.x, topRight.y);
                edgePath.lineTo(edgeTopRight.x, edgeTopRight.y);
                edgePath.lineTo(edgeBottomRight.x, edgeBottomRight.y);
                edgePath.lineTo(bottomRight.x, bottomRight.y);
                edgePath.lineTo(topRight.x, topRight.y);
                edgePath.moveTo(topLeft.x, topLeft.y);
                edgePath.lineTo(topRight.x, topRight.y);
                edgePath.lineTo(edgeTopRight.x, edgeTopRight.y);
                edgePath.lineTo(edgeTopLeft.x, edgeTopLeft.y);
                edgePath.lineTo(topLeft.x, topLeft.y);
                edgePath.moveTo(bottomLeft.x, bottomLeft.y);
                edgePath.lineTo(bottomRight.x, bottomRight.y);
                edgePath.lineTo(edgeBottomRight.x, edgeBottomRight.y);
                edgePath.lineTo(edgeBottomLeft.x, edgeBottomLeft.y);
                edgePath.lineTo(bottomLeft.x, bottomLeft.y);
            }
        }
        if(primaryBlastPadModel != null && pBlastPath.intersects(g2.getClipBounds()))
        {
            g2.setPaint(ColorsEngine.getInstance().getSurfaceColor(primaryBlastPadModel.getSurface()));
            g2.fill(pBlastPath);
            java.awt.Shape clip = g2.getClip();
            g2.setPaint(new Color(255, 255, 0, markingAlpha));
            g2.setStroke(new BasicStroke(5F * scale));
            g2.clip(pBlastPath);
            g2.draw(pBlastPadLines);
            g2.setStroke(new BasicStroke());
            g2.setClip(clip);
        }
        if(secondaryBlastPadModel != null && sBlastPath.intersects(g2.getClipBounds()))
        {
            g2.setPaint(ColorsEngine.getInstance().getSurfaceColor(secondaryBlastPadModel.getSurface()));
            g2.fill(sBlastPath);
            java.awt.Shape clip = g2.getClip();
            g2.setPaint(new Color(255, 255, 0, markingAlpha));
            g2.setStroke(new BasicStroke(5F * scale));
            g2.clip(sBlastPath);
            g2.draw(sBlastPadLines);
            g2.setStroke(new BasicStroke());
            g2.setClip(clip);
        }
        if(!modelPath.intersects(g2.getClipBounds()))
            return;
        g2.setPaint(ColorsEngine.getInstance().getSurfaceColor(surface));
        g2.fill(modelPath);
        if(markingsModel.getEdges())
        {
            g2.setPaint(new Color(255, 255, 255, markingAlpha));
            g2.fill(edgePath);
        }
        if(primaryOffsetModel != null)
        {
            g2.setPaint(new Color(255, 255, 255, markingAlpha));
            g2.setStroke(new BasicStroke(3F * scale));
            g2.draw(pOffsetPath);
            g2.setStroke(new BasicStroke());
        }
        if(secondaryOffsetModel != null)
        {
            g2.setPaint(new Color(255, 255, 255, markingAlpha));
            g2.setStroke(new BasicStroke(3F * scale));
            g2.draw(sOffsetPath);
            g2.setStroke(new BasicStroke());
        }
        if(markingsModel.getThreshold())
        {
            g2.setPaint(new Color(255, 255, 255, markingAlpha));
            g2.setStroke(new BasicStroke(5F * scale));
            g2.draw(thresholdLines);
            g2.setStroke(new BasicStroke());
        }
        if(markingsModel.getFixed())
        {
            g2.setPaint(new Color(255, 255, 255, markingAlpha));
            g2.fill(fixedDistanceLines);
        }
        if(markingsModel.getTouchdown())
        {
            g2.setPaint(new Color(255, 255, 255, markingAlpha));
            g2.fill(touchdownLines);
        }
        if(markingsModel.getPrecision())
        {
            g2.setPaint(new Color(255, 255, 255, markingAlpha));
            g2.fill(precisionLines);
        }
        if(markingsModel.getDashes())
        {
            g2.setStroke(new BasicStroke(4F * scale, 0, 1, 0.0F, new float[] {
                130F * scale, 130F * scale
            }, 0.0F));
            g2.setPaint(new Color(255, 255, 255, markingAlpha));
            g2.draw(centerLine);
            g2.setStroke(new BasicStroke());
        }
        if(markingsModel.getPrimaryClosed())
        {
            g2.setStroke(new BasicStroke(10F * scale));
            g2.setPaint(new Color(212, 167, 49, markingAlpha));
            g2.draw(pClosedPath);
            g2.setStroke(new BasicStroke());
        }
        if(markingsModel.getSecondaryClosed())
        {
            g2.setStroke(new BasicStroke(10F * scale));
            g2.setPaint(new Color(212, 167, 49, markingAlpha));
            g2.draw(sClosedPath);
            g2.setStroke(new BasicStroke());
        }
        if(!markingsModel.getPrimaryClosed() && markingsModel.getPrimaryStol())
        {
            g2.setStroke(new BasicStroke(3F * scale));
            g2.setPaint(new Color(255, 255, 255, markingAlpha));
            g2.draw(pStolPath);
            g2.setStroke(new BasicStroke());
        }
        if(!markingsModel.getSecondaryClosed() && markingsModel.getSecondaryStol())
        {
            g2.setStroke(new BasicStroke(3F * scale));
            g2.setPaint(new Color(255, 255, 255, markingAlpha));
            g2.draw(sStolPath);
            g2.setStroke(new BasicStroke());
        }
        if(markingsModel.getIdent())
        {
            g2.setStroke(new BasicStroke(6F * scale));
            g2.setPaint(new Color(255, 255, 255, markingAlpha));
            g2.draw(pDesignatorPath);
            if(!markingsModel.getSingleEnd())
                g2.draw(sDesignatorPath);
            g2.setStroke(new BasicStroke());
        }
        if(primaryLeftVasiModel != null)
        {
            primaryLeftVasiModel.setRunwayCP(runwayCP);
            primaryLeftVasiModel.setScale(scale);
            primaryLeftVasiModel.setHeading(heading);
            primaryLeftVasiModel.paint(g2, recreate);
        }
        if(primaryRightVasiModel != null)
        {
            primaryRightVasiModel.setRunwayCP(runwayCP);
            primaryRightVasiModel.setScale(scale);
            primaryRightVasiModel.setHeading(heading);
            primaryRightVasiModel.paint(g2, recreate);
        }
        if(secondaryLeftVasiModel != null)
        {
            secondaryLeftVasiModel.setRunwayCP(runwayCP);
            secondaryLeftVasiModel.setScale(scale);
            secondaryLeftVasiModel.setHeading(heading);
            secondaryLeftVasiModel.paint(g2, recreate);
        }
        if(secondaryRightVasiModel != null)
        {
            secondaryRightVasiModel.setRunwayCP(runwayCP);
            secondaryRightVasiModel.setScale(scale);
            secondaryRightVasiModel.setHeading(heading);
            secondaryRightVasiModel.paint(g2, recreate);
        }
        if(currentlySelected)
            if(SettingsEngine.getInstance().isSelectedItemOutlined())
            {
                g2.setStroke(new BasicStroke(4F));
                g2.setPaint(ColorsEngine.getInstance().getSelectedColor());
                g2.draw(modelPath);
                if(pBlastPath != null)
                    g2.draw(pBlastPath);
                if(sBlastPath != null)
                    g2.draw(sBlastPath);
                g2.setStroke(new BasicStroke());
            } else
            {
                Color selectedColor = ColorsEngine.getInstance().getSelectedColor();
                g2.setPaint(new Color(selectedColor.getRed(), selectedColor.getGreen(), selectedColor.getBlue(), 196));
                g2.fill(modelPath);
                if(pBlastPath != null)
                    g2.fill(pBlastPath);
                if(sBlastPath != null)
                    g2.fill(sBlastPath);
            }
    }

    public void paintLights(Graphics2D g2)
    {
        int sizeWidth = widthMeasure.equals("M") ? (int)(width * 3.28F * scale) : (int)(width * scale);
        int sizeLength = lengthMeasure.equals("M") ? (int)(length * 3.28F * scale) : (int)(length * scale);
        int primaryOffset = 0;
        if(primaryOffsetModel != null)
        {
            primaryOffset = (int)(primaryOffsetModel.getLength() * scale);
            if(primaryOffsetModel.getLengthMeasure().equals("M"))
                primaryOffset = (int)((float)primaryOffset * 3.28F);
        }
        primaryOffset = (int)((float)primaryOffset + 50F * scale);
        int secondaryOffset = 0;
        if(secondaryOffsetModel != null)
        {
            secondaryOffset = (int)(secondaryOffsetModel.getLength() * scale);
            if(secondaryOffsetModel.getLengthMeasure().equals("M"))
                secondaryOffset = (int)((float)secondaryOffset * 3.28F);
        }
        secondaryOffset = (int)((float)secondaryOffset + 50F * scale);
        java.awt.geom.Point2D.Float topLeft = new java.awt.geom.Point2D.Float((float)(runwayCP.getX() - (double)sizeWidth / 2D), (float)((runwayCP.getY() - (double)sizeLength / 2D) + (double)secondaryOffset));
        java.awt.geom.Point2D.Float topRight = new java.awt.geom.Point2D.Float((float)(runwayCP.getX() + (double)sizeWidth / 2D), (float)((runwayCP.getY() - (double)sizeLength / 2D) + (double)secondaryOffset));
        java.awt.geom.Point2D.Float bottomLeft = new java.awt.geom.Point2D.Float((float)(runwayCP.getX() - (double)sizeWidth / 2D), (float)((runwayCP.getY() + (double)sizeLength / 2D) - (double)primaryOffset));
        java.awt.geom.Point2D.Float bottomRight = new java.awt.geom.Point2D.Float((float)(runwayCP.getX() + (double)sizeWidth / 2D), (float)((runwayCP.getY() + (double)sizeLength / 2D) - (double)primaryOffset));
        double lightSize = 5D * (double)scale;
        double haloSize = 7D * (double)scale;
        double precisionLength = 3000D * (double)scale;
        if(!lightsModel.getCenter().equals("NONE"))
        {
label0:
            for(int j = 1; j >= 0; j--)
            {
                int runwayLength = (int)((double)sizeLength / 2D - (double)(j != 1 ? secondaryOffset : primaryOffset));
                java.awt.geom.Point2D.Float startPoint = runwayCP;
                java.awt.geom.Point2D.Float endPoint = Utilities.rotatePoint(runwayCP, new java.awt.geom.Point2D.Float(runwayCP.x, j != 1 ? topLeft.y : bottomLeft.y), heading);
                boolean yLonger = false;
                double shortLen = endPoint.y - startPoint.y;
                double longLen = endPoint.x - startPoint.x;
                if(Math.abs(shortLen) > Math.abs(longLen))
                {
                    double swap = shortLen;
                    shortLen = longLen;
                    longLen = swap;
                    yLonger = true;
                }
                double lightSpacing = (longLen * (double)(50F * scale)) / (double)runwayLength;
                double divDiff = shortLen != 0.0D ? longLen / shortLen : longLen;
                double marker1 = (longLen * ((double)runwayLength - 2800D * (double)scale)) / (double)runwayLength;
                double marker2 = (longLen * ((double)runwayLength - 1000D * (double)scale)) / (double)runwayLength;
                boolean allWhite = (double)sizeLength / 2D - (double)primaryOffset < precisionLength || !lightsModel.getCenterRed();
                double i;
                if(yLonger)
                {
                    if(longLen > 0.0D)
                    {
                        i = 0.0D;
                        do
                        {
                            if(i >= longLen)
                                continue label0;
                            g2.setPaint(Utilities.HALO_COLOR);
                            g2.fill(new java.awt.geom.Ellipse2D.Double(((double)startPoint.x + i / divDiff) - haloSize / 2D, ((double)startPoint.y + i) - haloSize / 2D, haloSize, haloSize));
                            if(allWhite || i < marker1)
                                g2.setPaint(Color.white);
                            else
                            if(i < marker2)
                                g2.setPaint((int)(i % (lightSpacing * 2D)) != (int)lightSpacing ? ((java.awt.Paint) (Color.red)) : ((java.awt.Paint) (Color.white)));
                            else
                                g2.setPaint(Color.red);
                            g2.fill(new java.awt.geom.Ellipse2D.Double(((double)startPoint.x + i / divDiff) - lightSize / 2D, ((double)startPoint.y + i) - lightSize / 2D, lightSize, lightSize));
                            i += lightSpacing;
                        } while(true);
                    }
                    longLen = Math.floor(longLen / lightSpacing) * lightSpacing;
                    i = longLen;
                    do
                    {
                        if(i > 0.0D)
                            continue label0;
                        g2.setPaint(Utilities.HALO_COLOR);
                        g2.fill(new java.awt.geom.Ellipse2D.Double(((double)startPoint.x + i / divDiff) - haloSize / 2D, ((double)startPoint.y + i) - haloSize / 2D, haloSize, haloSize));
                        if(allWhite || i > marker1)
                            g2.setPaint(Color.white);
                        else
                        if(i > marker2)
                            g2.setPaint((int)(i % (lightSpacing * 2D)) != (int)lightSpacing ? ((java.awt.Paint) (Color.red)) : ((java.awt.Paint) (Color.white)));
                        else
                            g2.setPaint(Color.red);
                        g2.fill(new java.awt.geom.Ellipse2D.Double(((double)startPoint.x + i / divDiff) - lightSize / 2D, ((double)startPoint.y + i) - lightSize / 2D, lightSize, lightSize));
                        i -= lightSpacing;
                    } while(true);
                }
                if(longLen > 0.0D)
                {
                    i = 0.0D;
                    do
                    {
                        if(i >= longLen)
                            continue label0;
                        g2.setPaint(Utilities.HALO_COLOR);
                        g2.fill(new java.awt.geom.Ellipse2D.Double(((double)startPoint.x + i) - haloSize / 2D, ((double)startPoint.y + i / divDiff) - haloSize / 2D, haloSize, haloSize));
                        if(allWhite || i < marker1)
                            g2.setPaint(Color.white);
                        else
                        if(i < marker2)
                            g2.setPaint((int)(i % (lightSpacing * 2D)) != (int)lightSpacing ? ((java.awt.Paint) (Color.red)) : ((java.awt.Paint) (Color.white)));
                        else
                            g2.setPaint(Color.red);
                        g2.fill(new java.awt.geom.Ellipse2D.Double(((double)startPoint.x + i) - lightSize / 2D, ((double)startPoint.y + i / divDiff) - lightSize / 2D, lightSize, lightSize));
                        i += lightSpacing;
                    } while(true);
                }
                longLen = Math.floor(longLen / lightSpacing) * lightSpacing;
                i = longLen;
                do
                {
                    if(i > 0.0D)
                        continue label0;
                    g2.setPaint(Utilities.HALO_COLOR);
                    g2.fill(new java.awt.geom.Ellipse2D.Double(((double)startPoint.x + i) - haloSize / 2D, ((double)startPoint.y + i / divDiff) - haloSize / 2D, haloSize, haloSize));
                    if(allWhite || i > marker1)
                        g2.setPaint(Color.white);
                    else
                    if(i > marker2)
                        g2.setPaint((int)(i % (lightSpacing * 2D)) != (int)lightSpacing ? ((java.awt.Paint) (Color.red)) : ((java.awt.Paint) (Color.white)));
                    else
                        g2.setPaint(Color.red);
                    g2.fill(new java.awt.geom.Ellipse2D.Double(((double)startPoint.x + i) - lightSize / 2D, ((double)startPoint.y + i / divDiff) - lightSize / 2D, lightSize, lightSize));
                    i -= lightSpacing;
                } while(true);
            }

        }
        if(!lightsModel.getEdge().equals("NONE"))
        {
            for(int j = 1; j >= 0; j--)
            {
                java.awt.geom.Point2D.Float startPoint;
                java.awt.geom.Point2D.Float endPoint;
                if(j == 1)
                {
                    startPoint = Utilities.rotatePoint(runwayCP, new java.awt.geom.Point2D.Float(runwayCP.x - (float)sizeWidth / 2.0F, (bottomLeft.y + (float)primaryOffset) - 200F * scale), heading);
                    endPoint = Utilities.rotatePoint(runwayCP, new java.awt.geom.Point2D.Float(runwayCP.x - (float)sizeWidth / 2.0F, (topLeft.y - (float)secondaryOffset) + 200F * scale), heading);
                } else
                {
                    startPoint = Utilities.rotatePoint(runwayCP, new java.awt.geom.Point2D.Float(runwayCP.x + (float)sizeWidth / 2.0F, (bottomLeft.y + (float)primaryOffset) - 200F * scale), heading);
                    endPoint = Utilities.rotatePoint(runwayCP, new java.awt.geom.Point2D.Float(runwayCP.x + (float)sizeWidth / 2.0F, (topLeft.y - (float)secondaryOffset) + 200F * scale), heading);
                }
                boolean yLonger = false;
                double shortLen = endPoint.y - startPoint.y;
                double longLen = endPoint.x - startPoint.x;
                if(Math.abs(shortLen) > Math.abs(longLen))
                {
                    double swap = shortLen;
                    shortLen = longLen;
                    longLen = swap;
                    yLonger = true;
                }
                double lightSpacing = (longLen * (double)(200F * scale)) / (double)sizeLength;
                double divDiff = shortLen != 0.0D ? longLen / shortLen : longLen;
                paintNightLights(g2, startPoint, Color.white, yLonger, divDiff, longLen, lightSpacing, lightSize, haloSize);
            }

            for(float i = -1F; i < 3F; i++)
            {
                java.awt.geom.Point2D.Float greenBottomLeft = Utilities.rotatePoint(runwayCP, new java.awt.geom.Point2D.Float(bottomLeft.x + i * 15F * scale, bottomLeft.y + (float)primaryOffset), heading);
                java.awt.geom.Point2D.Float greenBottomRight = Utilities.rotatePoint(runwayCP, new java.awt.geom.Point2D.Float(bottomRight.x - i * 15F * scale, bottomRight.y + (float)primaryOffset), heading);
                java.awt.geom.Point2D.Float greenTopLeft = Utilities.rotatePoint(runwayCP, new java.awt.geom.Point2D.Float(topLeft.x + i * 15F * scale, topLeft.y - (float)secondaryOffset), heading);
                java.awt.geom.Point2D.Float greenTopRight = Utilities.rotatePoint(runwayCP, new java.awt.geom.Point2D.Float(topRight.x - i * 15F * scale, topRight.y - (float)secondaryOffset), heading);
                g2.setPaint(Utilities.HALO_COLOR);
                g2.fill(new java.awt.geom.Ellipse2D.Double((double)greenBottomLeft.x - haloSize / 2D, (double)greenBottomLeft.y - haloSize / 2D, haloSize, haloSize));
                g2.fill(new java.awt.geom.Ellipse2D.Double((double)greenBottomRight.x - haloSize / 2D, (double)greenBottomRight.y - haloSize / 2D, haloSize, haloSize));
                g2.fill(new java.awt.geom.Ellipse2D.Double((double)greenTopLeft.x - haloSize / 2D, (double)greenTopLeft.y - haloSize / 2D, haloSize, haloSize));
                g2.fill(new java.awt.geom.Ellipse2D.Double((double)greenTopRight.x - haloSize / 2D, (double)greenTopRight.y - haloSize / 2D, haloSize, haloSize));
                g2.setPaint(Color.red);
                g2.fill(new java.awt.geom.Ellipse2D.Double((double)greenBottomLeft.x - lightSize / 2D, (double)greenBottomLeft.y - lightSize / 2D, lightSize, lightSize));
                g2.fill(new java.awt.geom.Ellipse2D.Double((double)greenBottomRight.x - lightSize / 2D, (double)greenBottomRight.y - lightSize / 2D, lightSize, lightSize));
                g2.fill(new java.awt.geom.Ellipse2D.Double((double)greenTopLeft.x - lightSize / 2D, (double)greenTopLeft.y - lightSize / 2D, lightSize, lightSize));
                g2.fill(new java.awt.geom.Ellipse2D.Double((double)greenTopRight.x - lightSize / 2D, (double)greenTopRight.y - lightSize / 2D, lightSize, lightSize));
            }

            for(float i = 0.0F; i < 4F; i++)
            {
                java.awt.geom.Point2D.Float greenBottomLeft = Utilities.rotatePoint(runwayCP, new java.awt.geom.Point2D.Float(bottomLeft.x - i * 15F * scale, bottomLeft.y + 40F * scale), heading);
                java.awt.geom.Point2D.Float greenBottomRight = Utilities.rotatePoint(runwayCP, new java.awt.geom.Point2D.Float(bottomRight.x + i * 15F * scale, bottomRight.y + 40F * scale), heading);
                java.awt.geom.Point2D.Float greenTopLeft = Utilities.rotatePoint(runwayCP, new java.awt.geom.Point2D.Float(topLeft.x - i * 15F * scale, topLeft.y - 40F * scale), heading);
                java.awt.geom.Point2D.Float greenTopRight = Utilities.rotatePoint(runwayCP, new java.awt.geom.Point2D.Float(topRight.x + i * 15F * scale, topRight.y - 40F * scale), heading);
                g2.setPaint(Utilities.HALO_COLOR);
                g2.fill(new java.awt.geom.Ellipse2D.Double((double)greenBottomLeft.x - haloSize / 2D, (double)greenBottomLeft.y - haloSize / 2D, haloSize, haloSize));
                g2.fill(new java.awt.geom.Ellipse2D.Double((double)greenBottomRight.x - haloSize / 2D, (double)greenBottomRight.y - haloSize / 2D, haloSize, haloSize));
                g2.fill(new java.awt.geom.Ellipse2D.Double((double)greenTopLeft.x - haloSize / 2D, (double)greenTopLeft.y - haloSize / 2D, haloSize, haloSize));
                g2.fill(new java.awt.geom.Ellipse2D.Double((double)greenTopRight.x - haloSize / 2D, (double)greenTopRight.y - haloSize / 2D, haloSize, haloSize));
                g2.setPaint(Color.green);
                g2.fill(new java.awt.geom.Ellipse2D.Double((double)greenBottomLeft.x - lightSize / 2D, (double)greenBottomLeft.y - lightSize / 2D, lightSize, lightSize));
                g2.fill(new java.awt.geom.Ellipse2D.Double((double)greenBottomRight.x - lightSize / 2D, (double)greenBottomRight.y - lightSize / 2D, lightSize, lightSize));
                g2.fill(new java.awt.geom.Ellipse2D.Double((double)greenTopLeft.x - lightSize / 2D, (double)greenTopLeft.y - lightSize / 2D, lightSize, lightSize));
                g2.fill(new java.awt.geom.Ellipse2D.Double((double)greenTopRight.x - lightSize / 2D, (double)greenTopRight.y - lightSize / 2D, lightSize, lightSize));
            }

        }
        if(primaryApproachLightsModel != null)
        {
            if(primaryApproachLightsModel.getTouchdown())
            {
                for(float i = 20F; i <= 36F; i += 8F)
                {
                    for(int j = -1; j <= 1; j += 2)
                    {
                        float endPixel = Math.min(bottomLeft.y - runwayCP.y, 3000F * scale);
                        java.awt.geom.Point2D.Float startPoint = Utilities.rotatePoint(runwayCP, new java.awt.geom.Point2D.Float(runwayCP.x - (float)j * i * scale, bottomLeft.y - 50F * scale), heading);
                        java.awt.geom.Point2D.Float endPoint = Utilities.rotatePoint(runwayCP, new java.awt.geom.Point2D.Float(runwayCP.x - (float)j * i * scale, bottomLeft.y - endPixel), heading);
                        boolean yLonger = false;
                        double shortLen = endPoint.y - startPoint.y;
                        double longLen = endPoint.x - startPoint.x;
                        if(Math.abs(shortLen) > Math.abs(longLen))
                        {
                            double swap = shortLen;
                            shortLen = longLen;
                            longLen = swap;
                            yLonger = true;
                        }
                        double lineLength = Math.sqrt(Math.pow(endPoint.x - startPoint.x, 2D) + Math.pow(endPoint.y - startPoint.y, 2D));
                        double lightSpacing = (longLen * (double)(100F * scale)) / lineLength;
                        double divDiff = shortLen != 0.0D ? longLen / shortLen : longLen;
                        paintNightLights(g2, startPoint, Color.white, yLonger, divDiff, longLen, lightSpacing, lightSize, haloSize);
                    }

                }

            }
            if(primaryApproachLightsModel.getEndLights())
            {
                for(int j = 1; j >= 0; j--)
                {
                    java.awt.geom.Point2D.Float startPoint = Utilities.rotatePoint(runwayCP, new java.awt.geom.Point2D.Float(runwayCP.x - (float)sizeWidth / 2.0F, bottomLeft.y + 50F * scale), heading);
                    java.awt.geom.Point2D.Float endPoint = Utilities.rotatePoint(runwayCP, new java.awt.geom.Point2D.Float(runwayCP.x + (float)sizeWidth / 2.0F, bottomLeft.y + 50F * scale), heading);
                    boolean yLonger = false;
                    double shortLen = endPoint.y - startPoint.y;
                    double longLen = endPoint.x - startPoint.x;
                    if(Math.abs(shortLen) > Math.abs(longLen))
                    {
                        double swap = shortLen;
                        shortLen = longLen;
                        longLen = swap;
                        yLonger = true;
                    }
                    double lineLength = Math.sqrt(Math.pow(endPoint.x - startPoint.x, 2D) + Math.pow(endPoint.y - startPoint.y, 2D));
                    double lightSpacing = (longLen * (double)(8F * scale)) / lineLength;
                    double divDiff = shortLen != 0.0D ? longLen / shortLen : longLen;
                    paintNightLights(g2, startPoint, Color.green, yLonger, divDiff, longLen, lightSpacing, lightSize, haloSize);
                }

            }
            if(primaryApproachLightsModel.getReil())
            {
                java.awt.geom.Point2D.Float leftPoint = Utilities.rotatePoint(runwayCP, new java.awt.geom.Point2D.Float(bottomLeft.x - 15F * scale, bottomLeft.y + 50F * scale), heading);
                java.awt.geom.Point2D.Float rightPoint = Utilities.rotatePoint(runwayCP, new java.awt.geom.Point2D.Float(bottomRight.x + 15F * scale, bottomLeft.y + 50F * scale), heading);
                g2.setPaint(Utilities.HALO_COLOR);
                g2.fill(new java.awt.geom.Ellipse2D.Double((double)leftPoint.x - haloSize / 2D, (double)leftPoint.y - haloSize / 2D, haloSize, haloSize));
                g2.fill(new java.awt.geom.Ellipse2D.Double((double)rightPoint.x - haloSize / 2D, (double)rightPoint.y - haloSize / 2D, haloSize, haloSize));
                paintREIL(g2, leftPoint, (float)(lightSize / 2D));
                paintREIL(g2, rightPoint, (float)(lightSize / 2D));
            }
        }
        if(secondaryApproachLightsModel != null)
        {
            if(secondaryApproachLightsModel.getTouchdown())
            {
                for(float i = 20F; i <= 36F; i += 8F)
                {
                    for(int j = -1; j <= 1; j += 2)
                    {
                        float endPixel = Math.min(runwayCP.y - topLeft.y, 3000F * scale);
                        java.awt.geom.Point2D.Float startPoint = Utilities.rotatePoint(runwayCP, new java.awt.geom.Point2D.Float(runwayCP.x - (float)j * i * scale, topLeft.y + 50F * scale), heading);
                        java.awt.geom.Point2D.Float endPoint = Utilities.rotatePoint(runwayCP, new java.awt.geom.Point2D.Float(runwayCP.x - (float)j * i * scale, topLeft.y + endPixel), heading);
                        boolean yLonger = false;
                        double shortLen = endPoint.y - startPoint.y;
                        double longLen = endPoint.x - startPoint.x;
                        if(Math.abs(shortLen) > Math.abs(longLen))
                        {
                            double swap = shortLen;
                            shortLen = longLen;
                            longLen = swap;
                            yLonger = true;
                        }
                        double lineLength = Math.sqrt(Math.pow(endPoint.x - startPoint.x, 2D) + Math.pow(endPoint.y - startPoint.y, 2D));
                        double lightSpacing = (longLen * (double)(100F * scale)) / lineLength;
                        double divDiff = shortLen != 0.0D ? longLen / shortLen : longLen;
                        paintNightLights(g2, startPoint, Color.white, yLonger, divDiff, longLen, lightSpacing, lightSize, haloSize);
                    }

                }

            }
            if(secondaryApproachLightsModel.getEndLights())
            {
                for(int j = 1; j >= 0; j--)
                {
                    java.awt.geom.Point2D.Float startPoint = Utilities.rotatePoint(runwayCP, new java.awt.geom.Point2D.Float(runwayCP.x - (float)sizeWidth / 2.0F, topLeft.y - 50F * scale), heading);
                    java.awt.geom.Point2D.Float endPoint = Utilities.rotatePoint(runwayCP, new java.awt.geom.Point2D.Float(runwayCP.x + (float)sizeWidth / 2.0F, topLeft.y - 50F * scale), heading);
                    boolean yLonger = false;
                    double shortLen = endPoint.y - startPoint.y;
                    double longLen = endPoint.x - startPoint.x;
                    if(Math.abs(shortLen) > Math.abs(longLen))
                    {
                        double swap = shortLen;
                        shortLen = longLen;
                        longLen = swap;
                        yLonger = true;
                    }
                    double lineLength = Math.sqrt(Math.pow(endPoint.x - startPoint.x, 2D) + Math.pow(endPoint.y - startPoint.y, 2D));
                    double lightSpacing = (longLen * (double)(8F * scale)) / lineLength;
                    double divDiff = shortLen != 0.0D ? longLen / shortLen : longLen;
                    paintNightLights(g2, startPoint, Color.green, yLonger, divDiff, longLen, lightSpacing, lightSize, haloSize);
                }

            }
            if(secondaryApproachLightsModel.getReil())
            {
                java.awt.geom.Point2D.Float leftPoint = Utilities.rotatePoint(runwayCP, new java.awt.geom.Point2D.Float(topLeft.x - 15F * scale, topLeft.y - 50F * scale), heading);
                java.awt.geom.Point2D.Float rightPoint = Utilities.rotatePoint(runwayCP, new java.awt.geom.Point2D.Float(topRight.x + 15F * scale, topLeft.y - 50F * scale), heading);
                g2.setPaint(Utilities.HALO_COLOR);
                g2.fill(new java.awt.geom.Ellipse2D.Double((double)leftPoint.x - haloSize / 2D, (double)leftPoint.y - haloSize / 2D, haloSize, haloSize));
                g2.fill(new java.awt.geom.Ellipse2D.Double((double)rightPoint.x - haloSize / 2D, (double)rightPoint.y - haloSize / 2D, haloSize, haloSize));
                paintREIL(g2, leftPoint, (float)(lightSize / 2D));
                paintREIL(g2, rightPoint, (float)(lightSize / 2D));
            }
        }
    }

    private void paintREIL(Graphics2D g2, java.awt.geom.Point2D.Float point, float reilWidth)
    {
        g2.setPaint(Color.white);
        java.awt.geom.Point2D.Float drawPoint1 = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x - reilWidth, point.y), heading);
        java.awt.geom.Point2D.Float drawPoint2 = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x + reilWidth, point.y), heading);
        g2.draw(new java.awt.geom.Line2D.Float(drawPoint1, drawPoint2));
        drawPoint1 = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x, point.y - reilWidth), heading);
        drawPoint2 = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x, point.y + reilWidth), heading);
        g2.draw(new java.awt.geom.Line2D.Float(drawPoint1, drawPoint2));
        drawPoint1 = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x, point.y - reilWidth), heading + 45F);
        drawPoint2 = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x, point.y + reilWidth), heading + 45F);
        g2.draw(new java.awt.geom.Line2D.Float(drawPoint1, drawPoint2));
        drawPoint1 = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x, point.y - reilWidth), heading - 45F);
        drawPoint2 = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x, point.y + reilWidth), heading - 45F);
        g2.draw(new java.awt.geom.Line2D.Float(drawPoint1, drawPoint2));
    }

    private void paintNightLights(Graphics2D g2, java.awt.geom.Point2D.Float startPoint, Color lightColor, boolean yLonger, double divDiff, double longLen, double lightSpacing, double lightSize, double haloSize)
    {
        if(yLonger)
        {
            if(longLen > 0.0D)
            {
                for(double i = 0.0D; i < longLen; i += lightSpacing)
                {
                    g2.setPaint(Utilities.HALO_COLOR);
                    g2.fill(new java.awt.geom.Ellipse2D.Double(((double)startPoint.x + i / divDiff) - haloSize / 2D, ((double)startPoint.y + i) - haloSize / 2D, haloSize, haloSize));
                    g2.setPaint(lightColor);
                    g2.fill(new java.awt.geom.Ellipse2D.Double(((double)startPoint.x + i / divDiff) - lightSize / 2D, ((double)startPoint.y + i) - lightSize / 2D, lightSize, lightSize));
                }

            } else
            {
                longLen = Math.floor(longLen / lightSpacing) * lightSpacing;
                for(double i = longLen; i <= 0.0D; i -= lightSpacing)
                {
                    g2.setPaint(Utilities.HALO_COLOR);
                    g2.fill(new java.awt.geom.Ellipse2D.Double(((double)startPoint.x + i / divDiff) - haloSize / 2D, ((double)startPoint.y + i) - haloSize / 2D, haloSize, haloSize));
                    g2.setPaint(lightColor);
                    g2.fill(new java.awt.geom.Ellipse2D.Double(((double)startPoint.x + i / divDiff) - lightSize / 2D, ((double)startPoint.y + i) - lightSize / 2D, lightSize, lightSize));
                }

            }
        } else
        if(longLen > 0.0D)
        {
            for(double i = 0.0D; i < longLen; i += lightSpacing)
            {
                g2.setPaint(Utilities.HALO_COLOR);
                g2.fill(new java.awt.geom.Ellipse2D.Double(((double)startPoint.x + i) - haloSize / 2D, ((double)startPoint.y + i / divDiff) - haloSize / 2D, haloSize, haloSize));
                g2.setPaint(lightColor);
                g2.fill(new java.awt.geom.Ellipse2D.Double(((double)startPoint.x + i) - lightSize / 2D, ((double)startPoint.y + i / divDiff) - lightSize / 2D, lightSize, lightSize));
            }

        } else
        {
            longLen = Math.floor(longLen / lightSpacing) * lightSpacing;
            for(double i = longLen; i <= 0.0D; i -= lightSpacing)
            {
                g2.setPaint(Utilities.HALO_COLOR);
                g2.fill(new java.awt.geom.Ellipse2D.Double(((double)startPoint.x + i) - haloSize / 2D, ((double)startPoint.y + i / divDiff) - haloSize / 2D, haloSize, haloSize));
                g2.setPaint(lightColor);
                g2.fill(new java.awt.geom.Ellipse2D.Double(((double)startPoint.x + i) - lightSize / 2D, ((double)startPoint.y + i / divDiff) - lightSize / 2D, lightSize, lightSize));
            }

        }
    }

    public GeneralPath getModelPath()
    {
        return modelPath;
    }

    private void createThreshold(float sizeWidth, java.awt.geom.Point2D.Float point, java.awt.geom.Point2D.Float topLeft, java.awt.geom.Point2D.Float bottomLeft)
    {
        thresholdLines = new GeneralPath();
        float primaryOffset = 0.0F;
        if(primaryOffsetModel != null)
            if(primaryOffsetModel.getLengthMeasure().equals("M"))
                primaryOffset = (int)(primaryOffsetModel.getLength() * 3.28F * scale);
            else
                primaryOffset = (int)(primaryOffsetModel.getLength() * scale);
        float secondaryOffset = 0.0F;
        if(secondaryOffsetModel != null)
            if(secondaryOffsetModel.getLengthMeasure().equals("M"))
                secondaryOffset = (int)(secondaryOffsetModel.getLength() * 3.28F * scale);
            else
                secondaryOffset = (int)(secondaryOffsetModel.getLength() * scale);
        float offset = 10F * scale;
        if(markingsModel.getAlternateThreshold())
            offset = 32F * scale;
        for(; (double)offset < (double)sizeWidth / 2D; offset += 11F * scale)
        {
            for(int i = -1; i <= 1; i += 2)
            {
                if(!markingsModel.getPrimaryClosed() && !markingsModel.getPrimaryStol())
                {
                    java.awt.geom.Point2D.Float thresholdTop = new java.awt.geom.Point2D.Float((float)(point.getX() - (double)(offset * (float)i)), (float)(bottomLeft.getY() - (double)primaryOffset - (double)(150F * scale)));
                    java.awt.geom.Point2D.Float thresholdBottom = new java.awt.geom.Point2D.Float((float)(point.getX() - (double)(offset * (float)i)), (float)(bottomLeft.getY() - (double)primaryOffset - (double)(20F * scale)));
                    thresholdTop = Utilities.rotatePoint(point, thresholdTop, heading);
                    thresholdBottom = Utilities.rotatePoint(point, thresholdBottom, heading);
                    thresholdLines.moveTo(thresholdTop.x, thresholdTop.y);
                    thresholdLines.lineTo(thresholdBottom.x, thresholdBottom.y);
                }
                if(!markingsModel.getSecondaryClosed() && !markingsModel.getSecondaryStol() && !markingsModel.getSingleEnd())
                {
                    java.awt.geom.Point2D.Float thresholdTop = new java.awt.geom.Point2D.Float((float)(point.getX() - (double)(offset * (float)i)), (float)(topLeft.getY() + (double)secondaryOffset + (double)(150F * scale)));
                    java.awt.geom.Point2D.Float thresholdBottom = new java.awt.geom.Point2D.Float((float)(point.getX() - (double)(offset * (float)i)), (float)(topLeft.getY() + (double)secondaryOffset + (double)(20F * scale)));
                    thresholdTop = Utilities.rotatePoint(point, thresholdTop, heading);
                    thresholdBottom = Utilities.rotatePoint(point, thresholdBottom, heading);
                    thresholdLines.moveTo(thresholdTop.x, thresholdTop.y);
                    thresholdLines.lineTo(thresholdBottom.x, thresholdBottom.y);
                }
            }

        }

    }

    private void createFixedDistance(float sizeWidth, java.awt.geom.Point2D.Float point, java.awt.geom.Point2D.Float topLeft, java.awt.geom.Point2D.Float bottomLeft)
    {
        fixedDistanceLines = new GeneralPath();
        float primaryOffset = 0.0F;
        if(primaryOffsetModel != null)
            if(primaryOffsetModel.getLengthMeasure().equals("M"))
                primaryOffset = (int)(primaryOffsetModel.getLength() * 3.28F * scale);
            else
                primaryOffset = (int)(primaryOffsetModel.getLength() * scale);
        float secondaryOffset = 0.0F;
        if(secondaryOffsetModel != null)
            if(secondaryOffsetModel.getLengthMeasure().equals("M"))
                secondaryOffset = (int)(secondaryOffsetModel.getLength() * 3.28F * scale);
            else
                secondaryOffset = (int)(secondaryOffsetModel.getLength() * scale);
        if(markingsModel.getAlternateFixedDistance())
        {
            for(int i = -1; i <= 1; i += 2)
            {
                for(int j = 0; j < 3; j++)
                {
                    java.awt.geom.Point2D.Float fixedTopLeft = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float((float)(point.getX() - (double)i * ((double)sizeWidth / 2D - (double)((30F - 20F * (float)(j % 2)) * scale))), (float)(bottomLeft.getY() - (double)primaryOffset - (double)((1080F + 80F * (float)j) * scale))), heading);
                    java.awt.geom.Point2D.Float fixedTopRight = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float((float)(point.getX() - (double)i * ((double)sizeWidth / 2D - (double)((50F - 20F * (float)(j % 2)) * scale))), (float)(bottomLeft.getY() - (double)primaryOffset - (double)((1080F + 80F * (float)j) * scale))), heading);
                    java.awt.geom.Point2D.Float fixedBottomLeft = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float((float)(point.getX() - (double)i * ((double)sizeWidth / 2D - (double)((30F - 20F * (float)(j % 2)) * scale))), (float)(bottomLeft.getY() - (double)primaryOffset - (double)((1000F + 80F * (float)j) * scale))), heading);
                    java.awt.geom.Point2D.Float fixedBottomRight = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float((float)(point.getX() - (double)i * ((double)sizeWidth / 2D - (double)((50F - 20F * (float)(j % 2)) * scale))), (float)(bottomLeft.getY() - (double)primaryOffset - (double)((1000F + 80F * (float)j) * scale))), heading);
                    fixedDistanceLines.moveTo(fixedTopLeft.x, fixedTopLeft.y);
                    fixedDistanceLines.lineTo(fixedTopRight.x, fixedTopRight.y);
                    fixedDistanceLines.lineTo(fixedBottomRight.x, fixedBottomRight.y);
                    fixedDistanceLines.lineTo(fixedBottomLeft.x, fixedBottomLeft.y);
                    fixedDistanceLines.lineTo(fixedTopLeft.x, fixedTopLeft.y);
                    if(!markingsModel.getSingleEnd())
                    {
                        fixedTopLeft = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float((float)(point.getX() - (double)i * ((double)sizeWidth / 2D - (double)((30F - 20F * (float)(j % 2)) * scale))), (float)(topLeft.getY() + (double)secondaryOffset + (double)((1080F + 80F * (float)j) * scale))), heading);
                        fixedTopRight = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float((float)(point.getX() - (double)i * ((double)sizeWidth / 2D - (double)((50F - 20F * (float)(j % 2)) * scale))), (float)(topLeft.getY() + (double)secondaryOffset + (double)((1080F + 80F * (float)j) * scale))), heading);
                        fixedBottomLeft = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float((float)(point.getX() - (double)i * ((double)sizeWidth / 2D - (double)((30F - 20F * (float)(j % 2)) * scale))), (float)(topLeft.getY() + (double)secondaryOffset + (double)((1000F + 80F * (float)j) * scale))), heading);
                        fixedBottomRight = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float((float)(point.getX() - (double)i * ((double)sizeWidth / 2D - (double)((50F - 20F * (float)(j % 2)) * scale))), (float)(topLeft.getY() + (double)secondaryOffset + (double)((1000F + 80F * (float)j) * scale))), heading);
                        fixedDistanceLines.moveTo(fixedTopLeft.x, fixedTopLeft.y);
                        fixedDistanceLines.lineTo(fixedTopRight.x, fixedTopRight.y);
                        fixedDistanceLines.lineTo(fixedBottomRight.x, fixedBottomRight.y);
                        fixedDistanceLines.lineTo(fixedBottomLeft.x, fixedBottomLeft.y);
                        fixedDistanceLines.lineTo(fixedTopLeft.x, fixedTopLeft.y);
                    }
                }

            }

        } else
        {
            for(int i = -1; i <= 1; i += 2)
            {
                java.awt.geom.Point2D.Float fixedTopLeft = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float((float)(point.getX() - (double)i * ((double)sizeWidth / 2D - (double)(10F * scale))), (float)(bottomLeft.getY() - (double)primaryOffset - (double)(1250F * scale))), heading);
                java.awt.geom.Point2D.Float fixedTopRight = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float((float)(point.getX() - (double)i * ((double)sizeWidth / 2D - (double)(40F * scale))), (float)(bottomLeft.getY() - (double)primaryOffset - (double)(1250F * scale))), heading);
                java.awt.geom.Point2D.Float fixedBottomLeft = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float((float)(point.getX() - (double)i * ((double)sizeWidth / 2D - (double)(10F * scale))), (float)(bottomLeft.getY() - (double)primaryOffset - (double)(1000F * scale))), heading);
                java.awt.geom.Point2D.Float fixedBottomRight = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float((float)(point.getX() - (double)i * ((double)sizeWidth / 2D - (double)(40F * scale))), (float)(bottomLeft.getY() - (double)primaryOffset - (double)(1000F * scale))), heading);
                fixedDistanceLines.moveTo(fixedTopLeft.x, fixedTopLeft.y);
                fixedDistanceLines.lineTo(fixedTopRight.x, fixedTopRight.y);
                fixedDistanceLines.lineTo(fixedBottomRight.x, fixedBottomRight.y);
                fixedDistanceLines.lineTo(fixedBottomLeft.x, fixedBottomLeft.y);
                fixedDistanceLines.lineTo(fixedTopLeft.x, fixedTopLeft.y);
                if(!markingsModel.getSingleEnd())
                {
                    fixedTopLeft = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float((float)(point.getX() - (double)i * ((double)sizeWidth / 2D - (double)(10F * scale))), (float)(topLeft.getY() + (double)secondaryOffset + (double)(1250F * scale))), heading);
                    fixedTopRight = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float((float)(point.getX() - (double)i * ((double)sizeWidth / 2D - (double)(40F * scale))), (float)(topLeft.getY() + (double)secondaryOffset + (double)(1250F * scale))), heading);
                    fixedBottomLeft = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float((float)(point.getX() - (double)i * ((double)sizeWidth / 2D - (double)(10F * scale))), (float)(topLeft.getY() + (double)secondaryOffset + (double)(1000F * scale))), heading);
                    fixedBottomRight = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float((float)(point.getX() - (double)i * ((double)sizeWidth / 2D - (double)(40F * scale))), (float)(topLeft.getY() + (double)secondaryOffset + (double)(1000F * scale))), heading);
                    fixedDistanceLines.moveTo(fixedTopLeft.x, fixedTopLeft.y);
                    fixedDistanceLines.lineTo(fixedTopRight.x, fixedTopRight.y);
                    fixedDistanceLines.lineTo(fixedBottomRight.x, fixedBottomRight.y);
                    fixedDistanceLines.lineTo(fixedBottomLeft.x, fixedBottomLeft.y);
                    fixedDistanceLines.lineTo(fixedTopLeft.x, fixedTopLeft.y);
                }
            }

        }
    }

    private void createTouchdownMarks(float sizeWidth, java.awt.geom.Point2D.Float point, java.awt.geom.Point2D.Float topLeft, java.awt.geom.Point2D.Float bottomLeft)
    {
        touchdownLines = new GeneralPath();
        float primaryOffset = 0.0F;
        if(primaryOffsetModel != null)
            if(primaryOffsetModel.getLengthMeasure().equals("M"))
                primaryOffset = (int)(primaryOffsetModel.getLength() * 3.28F * scale);
            else
                primaryOffset = (int)(primaryOffsetModel.getLength() * scale);
        float secondaryOffset = 0.0F;
        if(secondaryOffsetModel != null)
            if(secondaryOffsetModel.getLengthMeasure().equals("M"))
                secondaryOffset = (int)(secondaryOffsetModel.getLength() * 3.28F * scale);
            else
                secondaryOffset = (int)(secondaryOffsetModel.getLength() * scale);
        for(int i = -1; i <= 1; i += 2)
        {
            java.awt.geom.Point2D.Float fixedTopLeft = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float((float)(point.getX() - (double)i * ((double)sizeWidth / 2D - (double)(30F * scale))), (float)(bottomLeft.getY() - (double)primaryOffset - (double)(650F * scale))), heading);
            java.awt.geom.Point2D.Float fixedTopRight = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float((float)(point.getX() - (double)i * ((double)sizeWidth / 2D - (double)(40F * scale))), (float)(bottomLeft.getY() - (double)primaryOffset - (double)(650F * scale))), heading);
            java.awt.geom.Point2D.Float fixedBottomLeft = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float((float)(point.getX() - (double)i * ((double)sizeWidth / 2D - (double)(30F * scale))), (float)(bottomLeft.getY() - (double)primaryOffset - (double)(500F * scale))), heading);
            java.awt.geom.Point2D.Float fixedBottomRight = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float((float)(point.getX() - (double)i * ((double)sizeWidth / 2D - (double)(40F * scale))), (float)(bottomLeft.getY() - (double)primaryOffset - (double)(500F * scale))), heading);
            touchdownLines.moveTo(fixedTopLeft.x, fixedTopLeft.y);
            touchdownLines.lineTo(fixedTopRight.x, fixedTopRight.y);
            touchdownLines.lineTo(fixedBottomRight.x, fixedBottomRight.y);
            touchdownLines.lineTo(fixedBottomLeft.x, fixedBottomLeft.y);
            touchdownLines.lineTo(fixedTopLeft.x, fixedTopLeft.y);
            if(!markingsModel.getSingleEnd())
            {
                fixedTopLeft = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float((float)(point.getX() - (double)i * ((double)sizeWidth / 2D - (double)(30F * scale))), (float)(topLeft.getY() + (double)secondaryOffset + (double)(650F * scale))), heading);
                fixedTopRight = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float((float)(point.getX() - (double)i * ((double)sizeWidth / 2D - (double)(40F * scale))), (float)(topLeft.getY() + (double)secondaryOffset + (double)(650F * scale))), heading);
                fixedBottomLeft = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float((float)(point.getX() - (double)i * ((double)sizeWidth / 2D - (double)(30F * scale))), (float)(topLeft.getY() + (double)secondaryOffset + (double)(500F * scale))), heading);
                fixedBottomRight = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float((float)(point.getX() - (double)i * ((double)sizeWidth / 2D - (double)(40F * scale))), (float)(topLeft.getY() + (double)secondaryOffset + (double)(500F * scale))), heading);
                touchdownLines.moveTo(fixedTopLeft.x, fixedTopLeft.y);
                touchdownLines.lineTo(fixedTopRight.x, fixedTopRight.y);
                touchdownLines.lineTo(fixedBottomRight.x, fixedBottomRight.y);
                touchdownLines.lineTo(fixedBottomLeft.x, fixedBottomLeft.y);
                touchdownLines.lineTo(fixedTopLeft.x, fixedTopLeft.y);
            }
        }

    }

    private void createPrecisionMarks(float sizeWidth, float sizeLength, java.awt.geom.Point2D.Float point, java.awt.geom.Point2D.Float topLeft, java.awt.geom.Point2D.Float bottomLeft)
    {
        precisionLines = new GeneralPath();
        float primaryOffset = 0.0F;
        if(primaryOffsetModel != null)
            if(primaryOffsetModel.getLengthMeasure().equals("M"))
                primaryOffset = (int)(primaryOffsetModel.getLength() * 3.28F * scale);
            else
                primaryOffset = (int)(primaryOffsetModel.getLength() * scale);
        float secondaryOffset = 0.0F;
        if(secondaryOffsetModel != null)
            if(secondaryOffsetModel.getLengthMeasure().equals("M"))
                secondaryOffset = (int)(secondaryOffsetModel.getLength() * 3.28F * scale);
            else
                secondaryOffset = (int)(secondaryOffsetModel.getLength() * scale);
        float stripeWidth = 20F;
        if(markingsModel.getAlternatePrecision())
            stripeWidth = 15F;
        for(int i = -1; i <= 1; i += 2)
        {
            for(float j = 1500F; j <= 2000F; j += 500F)
            {
                java.awt.geom.Point2D.Float fixedTopLeft = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float((float)(point.getX() - (double)i * ((double)sizeWidth / 2D - (double)(30F * scale))), (float)(bottomLeft.getY() - (double)primaryOffset - (double)((j + 150F) * scale))), heading);
                java.awt.geom.Point2D.Float fixedTopRight = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float((float)(point.getX() - (double)i * ((double)sizeWidth / 2D - (double)((30F + stripeWidth) * scale))), (float)(bottomLeft.getY() - (double)primaryOffset - (double)((j + 150F) * scale))), heading);
                java.awt.geom.Point2D.Float fixedBottomLeft = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float((float)(point.getX() - (double)i * ((double)sizeWidth / 2D - (double)(30F * scale))), (float)(bottomLeft.getY() - (double)primaryOffset - (double)(j * scale))), heading);
                java.awt.geom.Point2D.Float fixedBottomRight = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float((float)(point.getX() - (double)i * ((double)sizeWidth / 2D - (double)((30F + stripeWidth) * scale))), (float)(bottomLeft.getY() - (double)primaryOffset - (double)(j * scale))), heading);
                precisionLines.moveTo(fixedTopLeft.x, fixedTopLeft.y);
                precisionLines.lineTo(fixedTopRight.x, fixedTopRight.y);
                precisionLines.lineTo(fixedBottomRight.x, fixedBottomRight.y);
                precisionLines.lineTo(fixedBottomLeft.x, fixedBottomLeft.y);
                precisionLines.lineTo(fixedTopLeft.x, fixedTopLeft.y);
                if(!markingsModel.getSingleEnd())
                {
                    fixedTopLeft = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float((float)(point.getX() - (double)i * ((double)sizeWidth / 2D - (double)(30F * scale))), (float)(topLeft.getY() + (double)secondaryOffset + (double)((j + 150F) * scale))), heading);
                    fixedTopRight = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float((float)(point.getX() - (double)i * ((double)sizeWidth / 2D - (double)((30F + stripeWidth) * scale))), (float)(topLeft.getY() + (double)secondaryOffset + (double)((j + 150F) * scale))), heading);
                    fixedBottomLeft = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float((float)(point.getX() - (double)i * ((double)sizeWidth / 2D - (double)(30F * scale))), (float)(topLeft.getY() + (double)secondaryOffset + (double)(j * scale))), heading);
                    fixedBottomRight = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float((float)(point.getX() - (double)i * ((double)sizeWidth / 2D - (double)((30F + stripeWidth) * scale))), (float)(topLeft.getY() + (double)secondaryOffset + (double)(j * scale))), heading);
                    precisionLines.moveTo(fixedTopLeft.x, fixedTopLeft.y);
                    precisionLines.lineTo(fixedTopRight.x, fixedTopRight.y);
                    precisionLines.lineTo(fixedBottomRight.x, fixedBottomRight.y);
                    precisionLines.lineTo(fixedBottomLeft.x, fixedBottomLeft.y);
                    precisionLines.lineTo(fixedTopLeft.x, fixedTopLeft.y);
                }
            }

        }

        if(!markingsModel.getAlternatePrecision())
        {
            for(int i = -1; i <= 1; i += 2)
            {
                for(float j = 2500F; j <= 3000F; j += 500F)
                {
                    if(sizeLength / 2.0F - j * scale < 900F * scale)
                        continue;
                    java.awt.geom.Point2D.Float fixedTopLeft = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float((float)(point.getX() - (double)i * ((double)sizeWidth / 2D - (double)(30F * scale))), (float)(bottomLeft.getY() - (double)primaryOffset - (double)((j + 150F) * scale))), heading);
                    java.awt.geom.Point2D.Float fixedTopRight = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float((float)(point.getX() - (double)i * ((double)sizeWidth / 2D - (double)(40F * scale))), (float)(bottomLeft.getY() - (double)primaryOffset - (double)((j + 150F) * scale))), heading);
                    java.awt.geom.Point2D.Float fixedBottomLeft = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float((float)(point.getX() - (double)i * ((double)sizeWidth / 2D - (double)(30F * scale))), (float)(bottomLeft.getY() - (double)primaryOffset - (double)(j * scale))), heading);
                    java.awt.geom.Point2D.Float fixedBottomRight = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float((float)(point.getX() - (double)i * ((double)sizeWidth / 2D - (double)(40F * scale))), (float)(bottomLeft.getY() - (double)primaryOffset - (double)(j * scale))), heading);
                    precisionLines.moveTo(fixedTopLeft.x, fixedTopLeft.y);
                    precisionLines.lineTo(fixedTopRight.x, fixedTopRight.y);
                    precisionLines.lineTo(fixedBottomRight.x, fixedBottomRight.y);
                    precisionLines.lineTo(fixedBottomLeft.x, fixedBottomLeft.y);
                    precisionLines.lineTo(fixedTopLeft.x, fixedTopLeft.y);
                    if(!markingsModel.getSingleEnd())
                    {
                        fixedTopLeft = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float((float)(point.getX() - (double)i * ((double)sizeWidth / 2D - (double)(30F * scale))), (float)(topLeft.getY() + (double)secondaryOffset + (double)((j + 150F) * scale))), heading);
                        fixedTopRight = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float((float)(point.getX() - (double)i * ((double)sizeWidth / 2D - (double)(40F * scale))), (float)(topLeft.getY() + (double)secondaryOffset + (double)((j + 150F) * scale))), heading);
                        fixedBottomLeft = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float((float)(point.getX() - (double)i * ((double)sizeWidth / 2D - (double)(30F * scale))), (float)(topLeft.getY() + (double)secondaryOffset + (double)(j * scale))), heading);
                        fixedBottomRight = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float((float)(point.getX() - (double)i * ((double)sizeWidth / 2D - (double)(40F * scale))), (float)(topLeft.getY() + (double)secondaryOffset + (double)(j * scale))), heading);
                        precisionLines.moveTo(fixedTopLeft.x, fixedTopLeft.y);
                        precisionLines.lineTo(fixedTopRight.x, fixedTopRight.y);
                        precisionLines.lineTo(fixedBottomRight.x, fixedBottomRight.y);
                        precisionLines.lineTo(fixedBottomLeft.x, fixedBottomLeft.y);
                        precisionLines.lineTo(fixedTopLeft.x, fixedTopLeft.y);
                    }
                }

            }

        }
    }

    private void createCenterLine(float sizeWidth, java.awt.geom.Point2D.Float point, java.awt.geom.Point2D.Float topLeft, java.awt.geom.Point2D.Float bottomLeft)
    {
        centerLine = new GeneralPath();
        float primaryOffset = 0.0F;
        if(primaryOffsetModel != null)
            if(primaryOffsetModel.getLengthMeasure().equals("M"))
                primaryOffset = (int)(primaryOffsetModel.getLength() * 3.28F * scale);
            else
                primaryOffset = (int)(primaryOffsetModel.getLength() * scale);
        float secondaryOffset = 0.0F;
        if(secondaryOffsetModel != null)
            if(secondaryOffsetModel.getLengthMeasure().equals("M"))
                secondaryOffset = (int)(secondaryOffsetModel.getLength() * 3.28F * scale);
            else
                secondaryOffset = (int)(secondaryOffsetModel.getLength() * scale);
        primaryOffset += 40F * scale;
        secondaryOffset += 40F * scale;
        if(markingsModel.getIdent())
        {
            primaryOffset += 100F * scale;
            secondaryOffset += 100F * scale;
        }
        if(markingsModel.getPrimaryClosed())
            primaryOffset += 80F * scale;
        else
        if(markingsModel.getPrimaryStol())
            primaryOffset += 50F * scale;
        else
        if(markingsModel.getThreshold())
            primaryOffset += 150F * scale;
        if(markingsModel.getSecondaryClosed())
            secondaryOffset += 80F * scale;
        else
        if(markingsModel.getSecondaryStol())
            secondaryOffset += 50F * scale;
        else
        if(markingsModel.getThreshold())
            secondaryOffset += 150F * scale;
        java.awt.geom.Point2D.Float centerLineTop = new java.awt.geom.Point2D.Float((float)point.getX(), (float)(bottomLeft.getY() - (double)primaryOffset));
        java.awt.geom.Point2D.Float centerLineBottom = new java.awt.geom.Point2D.Float((float)point.getX(), (float)(topLeft.getY() + (double)secondaryOffset));
        centerLineTop = Utilities.rotatePoint(point, centerLineTop, heading);
        centerLineBottom = Utilities.rotatePoint(point, centerLineBottom, heading);
        centerLine.moveTo(centerLineTop.x, centerLineTop.y);
        centerLine.lineTo(centerLineBottom.x, centerLineBottom.y);
    }

    private void createPrimaryClosed(java.awt.geom.Point2D.Float point, java.awt.geom.Point2D.Float bottomLeft)
    {
        pClosedPath = new GeneralPath();
        float primaryOffset = 0.0F;
        if(primaryOffsetModel != null)
            if(primaryOffsetModel.getLengthMeasure().equals("M"))
                primaryOffset = (int)(primaryOffsetModel.getLength() * 3.28F * scale);
            else
                primaryOffset = (int)(primaryOffsetModel.getLength() * scale);
        java.awt.geom.Point2D.Float slashTop = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float((float)point.getX() - 25F * scale, (float)(bottomLeft.getY() - (double)primaryOffset - (double)(75F * scale))), heading);
        java.awt.geom.Point2D.Float slashBottom = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float((float)point.getX() + 25F * scale, (float)(bottomLeft.getY() - (double)primaryOffset - (double)(25F * scale))), heading);
        pClosedPath.moveTo(slashTop.x, slashTop.y);
        pClosedPath.lineTo(slashBottom.x, slashBottom.y);
        slashTop = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float((float)point.getX() - 25F * scale, (float)(bottomLeft.getY() - (double)primaryOffset - (double)(25F * scale))), heading);
        slashBottom = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float((float)point.getX() + 25F * scale, (float)(bottomLeft.getY() - (double)primaryOffset - (double)(75F * scale))), heading);
        pClosedPath.moveTo(slashTop.x, slashTop.y);
        pClosedPath.lineTo(slashBottom.x, slashBottom.y);
    }

    private void createSecondaryClosed(java.awt.geom.Point2D.Float point, java.awt.geom.Point2D.Float topLeft)
    {
        sClosedPath = new GeneralPath();
        float secondaryOffset = 0.0F;
        if(secondaryOffsetModel != null)
            if(secondaryOffsetModel.getLengthMeasure().equals("M"))
                secondaryOffset = (int)(secondaryOffsetModel.getLength() * 3.28F * scale);
            else
                secondaryOffset = (int)(secondaryOffsetModel.getLength() * scale);
        java.awt.geom.Point2D.Float slashTop = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float((float)point.getX() - 25F * scale, (float)(topLeft.getY() + (double)secondaryOffset + (double)(75F * scale))), heading);
        java.awt.geom.Point2D.Float slashBottom = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float((float)point.getX() + 25F * scale, (float)(topLeft.getY() + (double)secondaryOffset + (double)(25F * scale))), heading);
        sClosedPath.moveTo(slashTop.x, slashTop.y);
        sClosedPath.lineTo(slashBottom.x, slashBottom.y);
        slashTop = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float((float)point.getX() - 25F * scale, (float)(topLeft.getY() + (double)secondaryOffset + (double)(25F * scale))), heading);
        slashBottom = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float((float)point.getX() + 25F * scale, (float)(topLeft.getY() + (double)secondaryOffset + (double)(75F * scale))), heading);
        sClosedPath.moveTo(slashTop.x, slashTop.y);
        sClosedPath.lineTo(slashBottom.x, slashBottom.y);
    }

    private void createPrimaryStol(java.awt.geom.Point2D.Float point, java.awt.geom.Point2D.Float bottomLeft)
    {
        pStolPath = new GeneralPath();
        float primaryOffset = 0.0F;
        if(primaryOffsetModel != null)
            if(primaryOffsetModel.getLengthMeasure().equals("M"))
                primaryOffset = (int)(primaryOffsetModel.getLength() * 3.28F * scale);
            else
                primaryOffset = (int)(primaryOffsetModel.getLength() * scale);
        CharacterEngine.getInstance().drawCharacter('s', pStolPath, point, new java.awt.geom.Point2D.Float((float)(point.getX() - (double)(45F * scale)), (float)(bottomLeft.getY() - (double)primaryOffset - (double)(20F * scale))), heading, scale, false);
        CharacterEngine.getInstance().drawCharacter('t', pStolPath, point, new java.awt.geom.Point2D.Float((float)(point.getX() - (double)(20F * scale)), (float)(bottomLeft.getY() - (double)primaryOffset - (double)(20F * scale))), heading, scale, false);
        CharacterEngine.getInstance().drawCharacter('o', pStolPath, point, new java.awt.geom.Point2D.Float((float)(point.getX() + (double)(5F * scale)), (float)(bottomLeft.getY() - (double)primaryOffset - (double)(20F * scale))), heading, scale, false);
        CharacterEngine.getInstance().drawCharacter('l', pStolPath, point, new java.awt.geom.Point2D.Float((float)(point.getX() + (double)(30F * scale)), (float)(bottomLeft.getY() - (double)primaryOffset - (double)(20F * scale))), heading, scale, false);
    }

    private void createSecondaryStol(java.awt.geom.Point2D.Float point, java.awt.geom.Point2D.Float topLeft)
    {
        sStolPath = new GeneralPath();
        float secondaryOffset = 0.0F;
        if(secondaryOffsetModel != null)
            if(secondaryOffsetModel.getLengthMeasure().equals("M"))
                secondaryOffset = (int)(secondaryOffsetModel.getLength() * 3.28F * scale);
            else
                secondaryOffset = (int)(secondaryOffsetModel.getLength() * scale);
        CharacterEngine.getInstance().drawCharacter('s', sStolPath, point, new java.awt.geom.Point2D.Float((float)(point.getX() + (double)(45F * scale)), (float)(topLeft.getY() + (double)secondaryOffset + (double)(20F * scale))), heading, scale, true);
        CharacterEngine.getInstance().drawCharacter('t', sStolPath, point, new java.awt.geom.Point2D.Float((float)(point.getX() + (double)(20F * scale)), (float)(topLeft.getY() + (double)secondaryOffset + (double)(20F * scale))), heading, scale, true);
        CharacterEngine.getInstance().drawCharacter('o', sStolPath, point, new java.awt.geom.Point2D.Float((float)(point.getX() - (double)(5F * scale)), (float)(topLeft.getY() + (double)secondaryOffset + (double)(20F * scale))), heading, scale, true);
        CharacterEngine.getInstance().drawCharacter('l', sStolPath, point, new java.awt.geom.Point2D.Float((float)(point.getX() - (double)(30F * scale)), (float)(topLeft.getY() + (double)secondaryOffset + (double)(20F * scale))), heading, scale, true);
    }

    private void createPrimaryDesignator(java.awt.geom.Point2D.Float point, java.awt.geom.Point2D.Float bottomLeft)
    {
        pDesignatorPath = new GeneralPath();
        float primaryOffset = 0.0F;
        if(primaryOffsetModel != null)
            if(primaryOffsetModel.getLengthMeasure().equals("M"))
                primaryOffset = (int)(primaryOffsetModel.getLength() * 3.28F * scale);
            else
                primaryOffset = (int)(primaryOffsetModel.getLength() * scale);
        if(markingsModel.getPrimaryClosed())
            primaryOffset += 80F * scale;
        else
        if(markingsModel.getPrimaryStol())
            primaryOffset += 50F * scale;
        else
        if(markingsModel.getThreshold() && !markingsModel.getAlternateThreshold())
            primaryOffset += 150F * scale;
        if(designator.length() > 0 || primaryDesignator.length() > 0)
        {
            String desig = designator;
            if(desig.length() == 0)
                desig = primaryDesignator;
            desig = desig.substring(0, 1);
            if(desig.equals("L") || desig.equals("R") || desig.equals("C"))
            {
                CharacterEngine.getInstance().drawCharacter(desig.charAt(0), pDesignatorPath, point, new java.awt.geom.Point2D.Float((float)(point.getX() - (double)(10F * scale)), (float)(bottomLeft.getY() - (double)primaryOffset - (double)(32F * scale))), heading, scale, false);
                primaryOffset += 80F * scale;
            }
        }
        if(number.length() == 2)
            if(markingsModel.getLeadingZeroIdent() || number.charAt(0) != '0')
            {
                CharacterEngine.getInstance().drawCharacter(number.charAt(0), pDesignatorPath, point, new java.awt.geom.Point2D.Float((float)(point.getX() - (double)(25F * scale)), (float)(bottomLeft.getY() - (double)primaryOffset - (double)(32F * scale))), heading, scale, false);
                CharacterEngine.getInstance().drawCharacter(number.charAt(1), pDesignatorPath, point, new java.awt.geom.Point2D.Float((float)(point.getX() + (double)(10F * scale)), (float)(bottomLeft.getY() - (double)primaryOffset - (double)(32F * scale))), heading, scale, false);
            } else
            {
                CharacterEngine.getInstance().drawCharacter(number.charAt(1), pDesignatorPath, point, new java.awt.geom.Point2D.Float((float)(point.getX() - (double)(10F * scale)), (float)(bottomLeft.getY() - (double)primaryOffset - (double)(32F * scale))), heading, scale, false);
            }
    }

    private void createSecondaryDesignator(java.awt.geom.Point2D.Float point, java.awt.geom.Point2D.Float topLeft)
    {
        sDesignatorPath = new GeneralPath();
        float secondaryOffset = 0.0F;
        if(secondaryOffsetModel != null)
            if(secondaryOffsetModel.getLengthMeasure().equals("M"))
                secondaryOffset = (int)(secondaryOffsetModel.getLength() * 3.28F * scale);
            else
                secondaryOffset = (int)(secondaryOffsetModel.getLength() * scale);
        if(markingsModel.getSecondaryClosed())
            secondaryOffset += 80F * scale;
        else
        if(markingsModel.getSecondaryStol())
            secondaryOffset += 50F * scale;
        else
        if(markingsModel.getThreshold() && !markingsModel.getAlternateThreshold())
            secondaryOffset += 150F * scale;
        if(designator.length() > 0 || secondaryDesignator.length() > 0)
        {
            String desig = designator;
            if(desig.length() == 0)
                desig = secondaryDesignator;
            desig = desig.substring(0, 1);
            if(desig.equals("L") || desig.equals("R") || desig.equals("C"))
            {
                if(desig.equals("L"))
                    desig = "R";
                else
                if(desig.equals("R"))
                    desig = "L";
                CharacterEngine.getInstance().drawCharacter(desig.charAt(0), sDesignatorPath, point, new java.awt.geom.Point2D.Float((float)(point.getX() + (double)(10F * scale)), (float)(topLeft.getY() + (double)secondaryOffset + (double)(32F * scale))), heading, scale, true);
                secondaryOffset += 80F * scale;
            }
        }
        if(number.length() == 2)
        {
            int secondaryNumber = Integer.parseInt(number) + 18;
            if(secondaryNumber > 36)
                secondaryNumber -= 36;
            String secondaryDesig = (new StringBuilder()).append("").append(secondaryNumber).toString();
            if(secondaryDesig.length() == 1)
                secondaryDesig = (new StringBuilder()).append("0").append(secondaryDesig).toString();
            if(markingsModel.getLeadingZeroIdent() || secondaryDesig.charAt(0) != '0')
            {
                CharacterEngine.getInstance().drawCharacter(secondaryDesig.charAt(0), sDesignatorPath, point, new java.awt.geom.Point2D.Float((float)(point.getX() + (double)(30F * scale)), (float)(topLeft.getY() + (double)secondaryOffset + (double)(32F * scale))), heading, scale, true);
                CharacterEngine.getInstance().drawCharacter(secondaryDesig.charAt(1), sDesignatorPath, point, new java.awt.geom.Point2D.Float((float)(point.getX() - (double)(10F * scale)), (float)(topLeft.getY() + (double)secondaryOffset + (double)(32F * scale))), heading, scale, true);
            } else
            {
                CharacterEngine.getInstance().drawCharacter(secondaryDesig.charAt(1), sDesignatorPath, point, new java.awt.geom.Point2D.Float((float)(point.getX() + (double)(10F * scale)), (float)(topLeft.getY() + (double)secondaryOffset + (double)(32F * scale))), heading, scale, true);
            }
        }
    }

    public boolean moveTo(LatLonPoint latLonPoint, double oldX, double oldY)
    {
        LatLonPoint modelLatLonPoint = Utilities.getLatLonForPixel(centerPoint.getLat(), centerPoint.getLon(), oldX, oldY, scale);
        double latDifference = latLonPoint.getLat() - modelLatLonPoint.getLat();
        double lonDifference = latLonPoint.getLon() - modelLatLonPoint.getLon();
        LatLonPoint latLon2 = new LatLonPoint(latLon.getLat(), latLon.getLon());
        latLon2.adjustLat(latDifference);
        latLon2.adjustLon(lonDifference);
        setLatLon(latLon2);
        return true;
    }

    public boolean containsTaxiway(TaxiwayPathModel model)
    {
        java.awt.geom.Point2D.Float startPoint = Utilities.getPixelsBetweenPoints(model.getCenterPoint().getLat(), model.getCenterPoint().getLon(), model.getStartLat(), model.getStartLon(), scale);
        java.awt.geom.Point2D.Float endPoint = Utilities.getPixelsBetweenPoints(model.getCenterPoint().getLat(), model.getCenterPoint().getLon(), model.getEndLat(), model.getEndLon(), scale);
        return modelPath.contains(startPoint) && modelPath.contains(endPoint);
    }

    public boolean isWithinObject(int x, int y)
    {
        if(modelPath == null)
            return false;
        boolean within = modelPath.contains(x, y);
        if(!within)
        {
            if(pBlastPath != null && pBlastPath.contains(x, y))
                within = true;
            if(!within && sBlastPath != null && sBlastPath.contains(x, y))
                within = true;
        }
        return within;
    }

    public boolean isCopyable()
    {
        return true;
    }

    public String getModelName()
    {
        return (new StringBuilder()).append(modelName).append(" [").append(number).append("]").toString();
    }

    public MarkingsModel getMarkingsModel()
    {
        return markingsModel;
    }

    public void setMarkingsModel(MarkingsModel markingsModel)
    {
        this.markingsModel = markingsModel;
    }

    public LightsModel getLightsModel()
    {
        return lightsModel;
    }

    public void setLightsModel(LightsModel lightsModel)
    {
        this.lightsModel = lightsModel;
    }

    public RunwayDetailModel getPrimaryOffsetModel()
    {
        return primaryOffsetModel;
    }

    public void setPrimaryOffsetModel(RunwayDetailModel primaryOffsetModel)
    {
        this.primaryOffsetModel = primaryOffsetModel;
    }

    public RunwayDetailModel getSecondaryOffsetModel()
    {
        return secondaryOffsetModel;
    }

    public void setSecondaryOffsetModel(RunwayDetailModel secondaryOffsetModel)
    {
        this.secondaryOffsetModel = secondaryOffsetModel;
    }

    public RunwayDetailModel getPrimaryBlastPadModel()
    {
        return primaryBlastPadModel;
    }

    public void setPrimaryBlastPadModel(RunwayDetailModel primaryBlastPadModel)
    {
        this.primaryBlastPadModel = primaryBlastPadModel;
    }

    public RunwayDetailModel getSecondaryBlastPadModel()
    {
        return secondaryBlastPadModel;
    }

    public void setSecondaryBlastPadModel(RunwayDetailModel secondaryBlastPadModel)
    {
        this.secondaryBlastPadModel = secondaryBlastPadModel;
    }

    public RunwayDetailModel getPrimaryOverrunModel()
    {
        return primaryOverrunModel;
    }

    public void setPrimaryOverrunModel(RunwayDetailModel primaryOverrunModel)
    {
        this.primaryOverrunModel = primaryOverrunModel;
    }

    public RunwayDetailModel getSecondaryOverrunModel()
    {
        return secondaryOverrunModel;
    }

    public void setSecondaryOverrunModel(RunwayDetailModel secondaryOverrunModel)
    {
        this.secondaryOverrunModel = secondaryOverrunModel;
    }

    public ApproachLightsModel getPrimaryApproachLightsModel()
    {
        return primaryApproachLightsModel;
    }

    public void setPrimaryApproachLightsModel(ApproachLightsModel primaryApproachLightsModel)
    {
        this.primaryApproachLightsModel = primaryApproachLightsModel;
    }

    public ApproachLightsModel getSecondaryApproachLightsModel()
    {
        return secondaryApproachLightsModel;
    }

    public void setSecondaryApproachLightsModel(ApproachLightsModel secondaryApproachLightsModel)
    {
        this.secondaryApproachLightsModel = secondaryApproachLightsModel;
    }

    public VasiModel getPrimaryLeftVasiModel()
    {
        return primaryLeftVasiModel;
    }

    public void setPrimaryLeftVasiModel(VasiModel primaryLeftVasiModel)
    {
        this.primaryLeftVasiModel = primaryLeftVasiModel;
    }

    public VasiModel getPrimaryRightVasiModel()
    {
        return primaryRightVasiModel;
    }

    public void setPrimaryRightVasiModel(VasiModel primaryRightVasiModel)
    {
        this.primaryRightVasiModel = primaryRightVasiModel;
    }

    public VasiModel getSecondaryLeftVasiModel()
    {
        return secondaryLeftVasiModel;
    }

    public void setSecondaryLeftVasiModel(VasiModel secondaryLeftVasiModel)
    {
        this.secondaryLeftVasiModel = secondaryLeftVasiModel;
    }

    public VasiModel getSecondaryRightVasiModel()
    {
        return secondaryRightVasiModel;
    }

    public void setSecondaryRightVasiModel(VasiModel secondaryRightVasiModel)
    {
        this.secondaryRightVasiModel = secondaryRightVasiModel;
    }

    public ArrayList getILSAL()
    {
        return ilsAL;
    }

    public void addILSModel(ILSModel ilsModel)
    {
        if(!ilsAL.contains(ilsModel))
        {
            String runwayName = getNumber();
            if(getDesignator().length() > 0 && !getDesignator().equals("NONE"))
                runwayName = (new StringBuilder()).append(runwayName).append(" ").append(getDesignator()).toString();
            else
            if(getPrimaryDesignator().length() > 0 && !getPrimaryDesignator().equals("NONE"))
                runwayName = runwayName = (new StringBuilder()).append(" ").append(getPrimaryDesignator()).toString();
            ilsModel.setRunway(runwayName);
            ilsAL.add(ilsModel);
            ilsModel.addPropertyChangeListener(this);
        }
    }

    public void removeILSModel(ILSModel ilsModel)
    {
        ilsAL.remove(ilsModel);
        ilsModel.removePropertyChangeListener(this);
    }

    public float getAlt()
    {
        return alt;
    }

    public void setAlt(float alt)
    {
        if(shouldNotify && this.alt != alt)
            HistoryListModel.getInstance().addModel(new HistoryModel("setAlt", "Altitude", "", this, new Float(alt), new Float(this.alt)));
        firePropertyChange("alt", new Float(this.alt), new Float(alt));
        this.alt = alt;
    }

    public String getAltMeasure()
    {
        return altMeasure;
    }

    public void setAltMeasure(String altMeasure)
    {
        if(shouldNotify && !this.altMeasure.equals(altMeasure))
            HistoryListModel.getInstance().addModel(new HistoryModel("setAltMeasure", "Altitude Measure", "", this, altMeasure, this.altMeasure));
        firePropertyChange("altMeasure", this.altMeasure, altMeasure);
        this.altMeasure = altMeasure;
    }

    public String getSurface()
    {
        return surface;
    }

    public void setSurface(String surface)
    {
        if(shouldNotify && !this.surface.equals(surface))
            HistoryListModel.getInstance().addModel(new HistoryModel("setSurface", "Surface", "", this, surface, this.surface));
        firePropertyChange("surface", this.surface, surface);
        this.surface = surface;
    }

    public float getLength()
    {
        return length;
    }

    public void setLength(float length)
    {
        if(shouldNotify && this.length != length)
            HistoryListModel.getInstance().addModel(new HistoryModel("setLength", "Length", "", this, new Float(length), new Float(this.length)));
        firePropertyChange("length", new Float(this.length), new Float(length));
        this.length = length;
    }

    public String getLengthMeasure()
    {
        return lengthMeasure;
    }

    public void setLengthMeasure(String lengthMeasure)
    {
        if(shouldNotify && !this.lengthMeasure.equals(lengthMeasure))
            HistoryListModel.getInstance().addModel(new HistoryModel("setLengthMeasure", "Length Measure", "", this, lengthMeasure, this.lengthMeasure));
        firePropertyChange("lengthMeasure", this.lengthMeasure, lengthMeasure);
        this.lengthMeasure = lengthMeasure;
    }

    public float getWidth()
    {
        return width;
    }

    public void setWidth(float width)
    {
        if(shouldNotify && this.width != width)
            HistoryListModel.getInstance().addModel(new HistoryModel("setWidth", "Width", "", this, new Float(width), new Float(this.width)));
        firePropertyChange("width", new Float(this.width), new Float(width));
        this.width = width;
    }

    public String getWidthMeasure()
    {
        return widthMeasure;
    }

    public void setWidthMeasure(String widthMeasure)
    {
        if(shouldNotify && !this.widthMeasure.equals(widthMeasure))
            HistoryListModel.getInstance().addModel(new HistoryModel("setWidthMeasure", "Width Measure", "", this, widthMeasure, this.widthMeasure));
        firePropertyChange("widthMeasure", this.widthMeasure, widthMeasure);
        this.widthMeasure = widthMeasure;
    }

    public String getNumber()
    {
        return number;
    }

    public void setNumber(String number)
    {
        if(number.length() == 1)
            number = (new StringBuilder()).append("0").append(number).toString();
        if(shouldNotify && !this.number.equals(number))
            HistoryListModel.getInstance().addModel(new HistoryModel("setNumber", "Number", "", this, number, this.number));
        firePropertyChange("number", this.number, number);
        this.number = number;
        markingsModel.setNumber(number);
    }

    public String getDesignator()
    {
        return designator;
    }

    public void setDesignator(String designator)
    {
        if(shouldNotify && !this.designator.equals(designator))
            HistoryListModel.getInstance().addModel(new HistoryModel("setDesignator", "Designator", "", this, designator, this.designator));
        firePropertyChange("designator", this.designator, designator);
        this.designator = designator;
    }

    public String getPrimaryDesignator()
    {
        return primaryDesignator;
    }

    public void setPrimaryDesignator(String primaryDesignator)
    {
        if(shouldNotify && !this.primaryDesignator.equals(primaryDesignator))
            HistoryListModel.getInstance().addModel(new HistoryModel("setPrimaryDesignator", "Primary Designator", "", this, primaryDesignator, this.primaryDesignator));
        firePropertyChange("primaryDesignator", this.primaryDesignator, primaryDesignator);
        this.primaryDesignator = primaryDesignator;
    }

    public String getSecondaryDesignator()
    {
        return secondaryDesignator;
    }

    public void setSecondaryDesignator(String secondaryDesignator)
    {
        if(shouldNotify && !this.secondaryDesignator.equals(secondaryDesignator))
            HistoryListModel.getInstance().addModel(new HistoryModel("setSecondaryDesignator", "Secondary Designator", "", this, secondaryDesignator, this.secondaryDesignator));
        firePropertyChange("secondaryDesignator", this.secondaryDesignator, secondaryDesignator);
        this.secondaryDesignator = secondaryDesignator;
    }

    public float getPatternAltitude()
    {
        return patternAltitude;
    }

    public void setPatternAltitude(float patternAltitude)
    {
        if(shouldNotify && this.patternAltitude != patternAltitude)
            HistoryListModel.getInstance().addModel(new HistoryModel("setPatternAltitude", "Pattern Altitude", "", this, new Float(patternAltitude), new Float(this.patternAltitude)));
        firePropertyChange("patternAltitude", new Float(this.patternAltitude), new Float(patternAltitude));
        this.patternAltitude = patternAltitude;
    }

    public String getPatternAltitudeMeasure()
    {
        return patternAltitudeMeasure;
    }

    public void setPatternAltitudeMeasure(String patternAltitudeMeasure)
    {
        if(shouldNotify && !this.patternAltitudeMeasure.equals(patternAltitudeMeasure))
            HistoryListModel.getInstance().addModel(new HistoryModel("setPatternAltitudeMeasure", "Pattern Altitude Measure", "", this, patternAltitudeMeasure, this.patternAltitudeMeasure));
        firePropertyChange("patternAltitudeMeasure", this.patternAltitudeMeasure, patternAltitudeMeasure);
        this.patternAltitudeMeasure = patternAltitudeMeasure;
    }

    public String getPrimaryPattern()
    {
        return primaryPattern;
    }

    public void setPrimaryPattern(String primaryPattern)
    {
        if(shouldNotify && !this.primaryPattern.equals(primaryPattern))
            HistoryListModel.getInstance().addModel(new HistoryModel("setPrimaryPattern", "Primary Pattern", "", this, primaryPattern, this.primaryPattern));
        firePropertyChange("primaryPattern", this.primaryPattern, primaryPattern);
        this.primaryPattern = primaryPattern;
    }

    public String getSecondaryPattern()
    {
        return secondaryPattern;
    }

    public void setSecondaryPattern(String secondaryPattern)
    {
        if(shouldNotify && !this.secondaryPattern.equals(secondaryPattern))
            HistoryListModel.getInstance().addModel(new HistoryModel("setSecondaryPattern", "Secondary Pattern", "", this, secondaryPattern, this.secondaryPattern));
        firePropertyChange("secondaryPattern", this.secondaryPattern, secondaryPattern);
        this.secondaryPattern = secondaryPattern;
    }

    public double getPrimaryMarkingBias()
    {
        return primaryMarkingBias;
    }

    public void setPrimaryMarkingBias(double primaryMarkingBias)
    {
        if(shouldNotify && this.primaryMarkingBias != primaryMarkingBias)
            HistoryListModel.getInstance().addModel(new HistoryModel("setPrimaryMarkingBias", "Primary Marking Bias", "", this, new Double(primaryMarkingBias), new Double(this.primaryMarkingBias)));
        firePropertyChange("primaryMarkingBias", new Double(this.primaryMarkingBias), new Double(primaryMarkingBias));
        this.primaryMarkingBias = primaryMarkingBias;
    }

    public String getPrimaryMarkingBiasMeasure()
    {
        return primaryMarkingBiasMeasure;
    }

    public void setPrimaryMarkingBiasMeasure(String primaryMarkingBiasMeasure)
    {
        if(shouldNotify && !this.primaryMarkingBiasMeasure.equals(primaryMarkingBiasMeasure))
            HistoryListModel.getInstance().addModel(new HistoryModel("setPrimaryMarkingBiasMeasure", "Primary Marking Bias Measure", "", this, primaryMarkingBiasMeasure, this.primaryMarkingBiasMeasure));
        firePropertyChange("primaryMarkingBiasMeasure", this.primaryMarkingBiasMeasure, primaryMarkingBiasMeasure);
        this.primaryMarkingBiasMeasure = primaryMarkingBiasMeasure;
    }

    public double getSecondaryMarkingBias()
    {
        return secondaryMarkingBias;
    }

    public void setSecondaryMarkingBias(double secondaryMarkingBias)
    {
        if(shouldNotify && this.secondaryMarkingBias != secondaryMarkingBias)
            HistoryListModel.getInstance().addModel(new HistoryModel("setSecondaryMarkingBias", "Secondary Marking Bias", "", this, new Double(secondaryMarkingBias), new Double(this.secondaryMarkingBias)));
        firePropertyChange("secondaryMarkingBias", new Double(this.secondaryMarkingBias), new Double(secondaryMarkingBias));
        this.secondaryMarkingBias = secondaryMarkingBias;
    }

    public String getSecondaryMarkingBiasMeasure()
    {
        return secondaryMarkingBiasMeasure;
    }

    public void setSecondaryMarkingBiasMeasure(String secondaryMarkingBiasMeasure)
    {
        if(shouldNotify && !this.secondaryMarkingBiasMeasure.equals(secondaryMarkingBiasMeasure))
            HistoryListModel.getInstance().addModel(new HistoryModel("setSecondaryMarkingBiasMeasure", "Secondary Marking Bias Measure", "", this, secondaryMarkingBiasMeasure, this.secondaryMarkingBiasMeasure));
        firePropertyChange("secondaryMarkingBiasMeasure", this.secondaryMarkingBiasMeasure, secondaryMarkingBiasMeasure);
        this.secondaryMarkingBiasMeasure = secondaryMarkingBiasMeasure;
    }

    public LatLonPoint getLatLon()
    {
        return latLon;
    }

    public void setLatLon(LatLonPoint latLon)
    {
        if(shouldNotify && !this.latLon.equals(latLon))
            HistoryListModel.getInstance().addModel(new HistoryModel("setLatLon", "Lat/Lon", "", this, latLon, this.latLon));
        firePropertyChange("latLon", this.latLon, latLon);
        this.latLon = latLon;
    }

    public float getHeading()
    {
        return heading;
    }

    public void setHeading(float heading)
    {
        if(shouldNotify && this.heading != heading)
            HistoryListModel.getInstance().addModel(new HistoryModel("setHeading", "Heading", "", this, new Float(heading), new Float(this.heading)));
        firePropertyChange("heading", new Float(this.heading), new Float(heading));
        this.heading = heading;
    }

    public boolean isPrimaryTakeoff()
    {
        return primaryTakeoff;
    }

    public void setPrimaryTakeoff(boolean primaryTakeoff)
    {
        if(shouldNotify && this.primaryTakeoff != primaryTakeoff)
            HistoryListModel.getInstance().addModel(new HistoryModel("setPrimaryTakeoff", "Primary Takeoff", "", this, new Boolean(primaryTakeoff), new Boolean(this.primaryTakeoff)));
        firePropertyChange("primaryTakeoff", new Boolean(this.primaryTakeoff), new Boolean(primaryTakeoff));
        this.primaryTakeoff = primaryTakeoff;
    }

    public boolean isPrimaryLanding()
    {
        return primaryLanding;
    }

    public void setPrimaryLanding(boolean primaryLanding)
    {
        if(shouldNotify && this.primaryLanding != primaryLanding)
            HistoryListModel.getInstance().addModel(new HistoryModel("setPrimaryLanding", "Primary Landing", "", this, new Boolean(primaryLanding), new Boolean(this.primaryLanding)));
        firePropertyChange("primaryLanding", new Boolean(this.primaryLanding), new Boolean(primaryLanding));
        this.primaryLanding = primaryLanding;
    }

    public boolean isSecondaryTakeoff()
    {
        return secondaryTakeoff;
    }

    public void setSecondaryTakeoff(boolean secondaryTakeoff)
    {
        if(shouldNotify && this.secondaryTakeoff != secondaryTakeoff)
            HistoryListModel.getInstance().addModel(new HistoryModel("setSecondaryTakeoff", "Secondary Takeoff", "", this, new Boolean(secondaryTakeoff), new Boolean(this.secondaryTakeoff)));
        firePropertyChange("secondaryTakeoff", new Boolean(this.secondaryTakeoff), new Boolean(secondaryTakeoff));
        this.secondaryTakeoff = secondaryTakeoff;
    }

    public boolean isSecondaryLanding()
    {
        return secondaryLanding;
    }

    public void setSecondaryLanding(boolean secondaryLanding)
    {
        if(shouldNotify && this.secondaryLanding != secondaryLanding)
            HistoryListModel.getInstance().addModel(new HistoryModel("setSecondaryLanding", "Secondary Landing", "", this, new Boolean(secondaryLanding), new Boolean(this.secondaryLanding)));
        firePropertyChange("secondaryLanding", new Boolean(this.secondaryLanding), new Boolean(secondaryLanding));
        this.secondaryLanding = secondaryLanding;
    }

    public void propertyChange(PropertyChangeEvent event)
    {
        if(event.getSource() instanceof ILSModel)
        {
            if(event.getPropertyName().equals("runway"))
                firePropertyChange("ilsRunway", event.getSource(), event.getNewValue());
        } else
        if((event.getSource() instanceof SettingsEngine) && event.getPropertyName().equals("displayLights"))
            displayLights = ((Boolean)event.getNewValue()).booleanValue();
    }

    public Object clone()
    {
        RunwayModel model = new RunwayModel();
        model.setShouldNotify(false);
        model.setAltMeasure(getAltMeasure());
        model.setSurface(getSurface());
        model.setLengthMeasure(getLengthMeasure());
        model.setWidthMeasure(getWidthMeasure());
        model.setNumber(getNumber());
        model.setDesignator(getDesignator());
        model.setPrimaryDesignator(getPrimaryDesignator());
        model.setSecondaryDesignator(getSecondaryDesignator());
        model.setPatternAltitudeMeasure(getPatternAltitudeMeasure());
        model.setPrimaryPattern(getPrimaryPattern());
        model.setSecondaryPattern(getSecondaryPattern());
        model.setPrimaryMarkingBiasMeasure(getPrimaryMarkingBiasMeasure());
        model.setSecondaryMarkingBiasMeasure(getSecondaryMarkingBiasMeasure());
        model.setLatLon((LatLonPoint)getLatLon().clone());
        model.setHeading(getHeading());
        model.setLength(getLength());
        model.setWidth(getWidth());
        model.setPatternAltitude(getPatternAltitude());
        model.setAlt(getAlt());
        model.setPrimaryMarkingBias(getPrimaryMarkingBias());
        model.setSecondaryMarkingBias(getSecondaryMarkingBias());
        model.setPrimaryTakeoff(isPrimaryTakeoff());
        model.setPrimaryLanding(isPrimaryLanding());
        model.setSecondaryTakeoff(isSecondaryTakeoff());
        model.setSecondaryLanding(isSecondaryLanding());
        model.setMarkingsModel((MarkingsModel)getMarkingsModel().clone());
        model.setLightsModel((LightsModel)getLightsModel().clone());
        if(getPrimaryOffsetModel() != null)
            model.setPrimaryOffsetModel((RunwayDetailModel)getPrimaryOffsetModel().clone());
        if(getSecondaryOffsetModel() != null)
            model.setSecondaryOffsetModel((RunwayDetailModel)getSecondaryOffsetModel().clone());
        if(getPrimaryBlastPadModel() != null)
            model.setPrimaryBlastPadModel((RunwayDetailModel)getPrimaryBlastPadModel().clone());
        if(getSecondaryBlastPadModel() != null)
            model.setSecondaryBlastPadModel((RunwayDetailModel)getSecondaryBlastPadModel().clone());
        if(getPrimaryOverrunModel() != null)
            model.setPrimaryOverrunModel((RunwayDetailModel)getPrimaryOverrunModel().clone());
        if(getSecondaryOverrunModel() != null)
            model.setSecondaryOverrunModel((RunwayDetailModel)getSecondaryOverrunModel().clone());
        if(getPrimaryApproachLightsModel() != null)
            model.setPrimaryApproachLightsModel((ApproachLightsModel)getPrimaryApproachLightsModel().clone());
        if(getSecondaryApproachLightsModel() != null)
            model.setSecondaryApproachLightsModel((ApproachLightsModel)getSecondaryApproachLightsModel().clone());
        if(getPrimaryLeftVasiModel() != null)
            model.setPrimaryLeftVasiModel((VasiModel)getPrimaryLeftVasiModel().clone());
        if(getPrimaryRightVasiModel() != null)
            model.setPrimaryRightVasiModel((VasiModel)getPrimaryRightVasiModel().clone());
        if(getSecondaryLeftVasiModel() != null)
            model.setSecondaryLeftVasiModel((VasiModel)getSecondaryLeftVasiModel().clone());
        if(getSecondaryRightVasiModel() != null)
            model.setSecondaryRightVasiModel((VasiModel)getSecondaryRightVasiModel().clone());
        model.setShouldNotify(true);
        return model;
    }

    private GeneralPath pOffsetPath;
    private GeneralPath sOffsetPath;
    private GeneralPath pBlastPath;
    private GeneralPath pBlastPadLines;
    private GeneralPath sBlastPath;
    private GeneralPath sBlastPadLines;
    private GeneralPath thresholdLines;
    private GeneralPath fixedDistanceLines;
    private GeneralPath touchdownLines;
    private GeneralPath precisionLines;
    private GeneralPath edgePath;
    private GeneralPath centerLine;
    private GeneralPath pClosedPath;
    private GeneralPath sClosedPath;
    private GeneralPath pStolPath;
    private GeneralPath sStolPath;
    private GeneralPath pDesignatorPath;
    private GeneralPath sDesignatorPath;
    private java.awt.geom.Point2D.Float runwayCP;
    private MarkingsModel markingsModel;
    private LightsModel lightsModel;
    private RunwayDetailModel primaryOffsetModel;
    private RunwayDetailModel secondaryOffsetModel;
    private RunwayDetailModel primaryBlastPadModel;
    private RunwayDetailModel secondaryBlastPadModel;
    private RunwayDetailModel primaryOverrunModel;
    private RunwayDetailModel secondaryOverrunModel;
    private ApproachLightsModel primaryApproachLightsModel;
    private ApproachLightsModel secondaryApproachLightsModel;
    private VasiModel primaryLeftVasiModel;
    private VasiModel primaryRightVasiModel;
    private VasiModel secondaryLeftVasiModel;
    private VasiModel secondaryRightVasiModel;
    private ArrayList ilsAL;
    private String altMeasure;
    private String surface;
    private String lengthMeasure;
    private String widthMeasure;
    private String number;
    private String designator;
    private String primaryDesignator;
    private String secondaryDesignator;
    private String patternAltitudeMeasure;
    private String primaryPattern;
    private String secondaryPattern;
    private String primaryMarkingBiasMeasure;
    private String secondaryMarkingBiasMeasure;
    private LatLonPoint latLon;
    private int markingAlpha;
    private float heading;
    private float length;
    private float width;
    private float patternAltitude;
    private float alt;
    private double primaryMarkingBias;
    private double secondaryMarkingBias;
    private boolean primaryTakeoff;
    private boolean primaryLanding;
    private boolean secondaryTakeoff;
    private boolean secondaryLanding;
    private boolean displayLights;
}
