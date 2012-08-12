// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TaxiwayPathModel.java

package com.zbluesoftware.fsxp.model;

import com.zbluesoftware.fsxp.engine.ColorsEngine;
import com.zbluesoftware.fsxp.engine.SettingsEngine;
import com.zbluesoftware.fsxp.util.LatLonPoint;
import com.zbluesoftware.fsxp.util.Utilities;
import java.awt.*;
import java.awt.geom.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

// Referenced classes of package com.zbluesoftware.fsxp.model:
//            BaseModel, HistoryModel, HistoryListModel

public class TaxiwayPathModel extends BaseModel
    implements PropertyChangeListener
{

    public TaxiwayPathModel()
    {
        modelName = "Taxiway Path";
        type = "TAXI";
        surface = "ASPHALT";
        leftEdge = "NONE";
        rightEdge = "NONE";
        number = "";
        designator = "NONE";
        widthMeasure = SettingsEngine.getInstance().getTaxiwayMeasure();
        name = 0;
        start = 0;
        end = 0;
        highlightedTWPath = 0;
        width = SettingsEngine.getInstance().getTaxiwayWidth();
        weightLimit = SettingsEngine.getInstance().getTaxiwayWeight();
        startLat = 0.0D;
        startLon = 0.0D;
        endLat = 0.0D;
        endLon = 0.0D;
        drawSurface = true;
        drawDetail = true;
        centerLine = false;
        centerLineLighted = false;
        leftEdgeLighted = false;
        rightEdgeLighted = false;
        paintCenterLine = false;
        drawTaxiwayPaths = SettingsEngine.getInstance().getDrawTaxiwayPaths();
        displayLights = SettingsEngine.getInstance().getDisplayLights();
        SettingsEngine.getInstance().addPropertyChangeListener(this);
    }

    public void paint(Graphics2D g2, boolean recreate)
    {
        if(startLat == 0.0D || startLon == 0.0D || endLat == 0.0D || endLon == 0.0D)
            return;
        if(type.equals("RUNWAY") && !SettingsEngine.getInstance().getShowRunwayPaths())
        {
            modelPath = null;
            leftEdgeLine = null;
            rightEdgeLine = null;
            return;
        }
        if(type.equals("PARKING") && !SettingsEngine.getInstance().getShowParkingPaths())
        {
            modelPath = null;
            leftEdgeLine = null;
            rightEdgeLine = null;
            return;
        }
        if(type.equals("TAXI") && !SettingsEngine.getInstance().getShowTaxiPaths())
        {
            modelPath = null;
            leftEdgeLine = null;
            rightEdgeLine = null;
            return;
        }
        if(type.equals("PATH") && !SettingsEngine.getInstance().getShowPathPaths())
        {
            modelPath = null;
            leftEdgeLine = null;
            rightEdgeLine = null;
            return;
        }
        if(type.equals("CLOSED") && !SettingsEngine.getInstance().getShowClosedPaths())
        {
            modelPath = null;
            leftEdgeLine = null;
            rightEdgeLine = null;
            return;
        }
        if(type.equals("VEHICLE") && !SettingsEngine.getInstance().getShowVehiclePaths())
        {
            modelPath = null;
            leftEdgeLine = null;
            rightEdgeLine = null;
            return;
        }
        if(!paintCenterLine && recreate || rightEdgeLine == null)
        {
            if(!recreate && taxiway != null && !taxiway.intersects(g2.getClipBounds()))
            {
                leftEdgeLine = null;
                rightEdgeLine = null;
                return;
            }
            startPoint = Utilities.getPixelsBetweenPoints(centerPoint.getLat(), centerPoint.getLon(), startLat, startLon, scale);
            endPoint = Utilities.getPixelsBetweenPoints(centerPoint.getLat(), centerPoint.getLon(), endLat, endLon, scale);
            modelPath = new GeneralPath();
            modelPath.moveTo(startPoint.x, startPoint.y);
            modelPath.lineTo(endPoint.x, endPoint.y);
            float taxiwayWidth = width / 2.0F;
            if(widthMeasure.equals("M"))
                taxiwayWidth *= 3.28F;
            if(endPoint.x > startPoint.x && endPoint.y < startPoint.y)
            {
                float angle = (float)Math.atan(Math.abs(startPoint.y - endPoint.y) / Math.abs(startPoint.x - endPoint.x));
                float yOffset = (float)((double)(taxiwayWidth * scale) * Math.cos(angle));
                float xOffset = (float)((double)(taxiwayWidth * scale) * Math.sin(angle));
                taxiway = new GeneralPath();
                taxiway.moveTo(startPoint.x + xOffset, startPoint.y + yOffset);
                taxiway.lineTo(endPoint.x + xOffset, endPoint.y + yOffset);
                taxiway.lineTo(endPoint.x - xOffset, endPoint.y - yOffset);
                taxiway.lineTo(startPoint.x - xOffset, startPoint.y - yOffset);
                taxiwayWidth -= 2.0F;
                yOffset = (float)((double)(taxiwayWidth * scale) * Math.cos(angle));
                xOffset = (float)((double)(taxiwayWidth * scale) * Math.sin(angle));
                rightEdgeLine = new java.awt.geom.Line2D.Float(startPoint.x + xOffset, startPoint.y + yOffset, endPoint.x + xOffset, endPoint.y + yOffset);
                leftEdgeLine = new java.awt.geom.Line2D.Float(startPoint.x - xOffset, startPoint.y - yOffset, endPoint.x - xOffset, endPoint.y - yOffset);
            } else
            if(endPoint.x <= startPoint.x && endPoint.y < startPoint.y)
            {
                float angle = (float)Math.atan(Math.abs(startPoint.y - endPoint.y) / Math.abs(startPoint.x - endPoint.x));
                float yOffset = (float)((double)(taxiwayWidth * scale) * Math.cos(angle)) * -1F;
                float xOffset = (float)((double)(taxiwayWidth * scale) * Math.sin(angle));
                taxiway = new GeneralPath();
                taxiway.moveTo(startPoint.x + xOffset, startPoint.y + yOffset);
                taxiway.lineTo(endPoint.x + xOffset, endPoint.y + yOffset);
                taxiway.lineTo(endPoint.x - xOffset, endPoint.y - yOffset);
                taxiway.lineTo(startPoint.x - xOffset, startPoint.y - yOffset);
                taxiwayWidth -= 2.0F;
                yOffset = (float)((double)(taxiwayWidth * scale) * Math.cos(angle)) * -1F;
                xOffset = (float)((double)(taxiwayWidth * scale) * Math.sin(angle));
                rightEdgeLine = new java.awt.geom.Line2D.Float(startPoint.x + xOffset, startPoint.y + yOffset, endPoint.x + xOffset, endPoint.y + yOffset);
                leftEdgeLine = new java.awt.geom.Line2D.Float(startPoint.x - xOffset, startPoint.y - yOffset, endPoint.x - xOffset, endPoint.y - yOffset);
            } else
            if(endPoint.x > startPoint.x && endPoint.y >= startPoint.y)
            {
                float angle = (float)Math.atan(Math.abs(startPoint.y - endPoint.y) / Math.abs(startPoint.x - endPoint.x));
                float yOffset = (float)((double)(taxiwayWidth * scale) * Math.cos(angle));
                float xOffset = (float)((double)(taxiwayWidth * scale) * Math.sin(angle)) * -1F;
                taxiway = new GeneralPath();
                taxiway.moveTo(startPoint.x + xOffset, startPoint.y + yOffset);
                taxiway.lineTo(endPoint.x + xOffset, endPoint.y + yOffset);
                taxiway.lineTo(endPoint.x - xOffset, endPoint.y - yOffset);
                taxiway.lineTo(startPoint.x - xOffset, startPoint.y - yOffset);
                taxiwayWidth -= 2.0F;
                yOffset = (float)((double)(taxiwayWidth * scale) * Math.cos(angle));
                xOffset = (float)((double)(taxiwayWidth * scale) * Math.sin(angle)) * -1F;
                rightEdgeLine = new java.awt.geom.Line2D.Float(startPoint.x + xOffset, startPoint.y + yOffset, endPoint.x + xOffset, endPoint.y + yOffset);
                leftEdgeLine = new java.awt.geom.Line2D.Float(startPoint.x - xOffset, startPoint.y - yOffset, endPoint.x - xOffset, endPoint.y - yOffset);
            } else
            if(endPoint.x <= startPoint.x && endPoint.y >= startPoint.y)
            {
                float angle = (float)Math.atan(Math.abs(startPoint.y - endPoint.y) / Math.abs(startPoint.x - endPoint.x));
                float yOffset = (float)((double)(taxiwayWidth * scale) * Math.cos(angle)) * -1F;
                float xOffset = (float)((double)(taxiwayWidth * scale) * Math.sin(angle)) * -1F;
                taxiway = new GeneralPath();
                taxiway.moveTo(startPoint.x + xOffset, startPoint.y + yOffset);
                taxiway.lineTo(endPoint.x + xOffset, endPoint.y + yOffset);
                taxiway.lineTo(endPoint.x - xOffset, endPoint.y - yOffset);
                taxiway.lineTo(startPoint.x - xOffset, startPoint.y - yOffset);
                taxiwayWidth -= 2.0F;
                yOffset = (float)((double)(taxiwayWidth * scale) * Math.cos(angle)) * -1F;
                xOffset = (float)((double)(taxiwayWidth * scale) * Math.sin(angle)) * -1F;
                rightEdgeLine = new java.awt.geom.Line2D.Float(startPoint.x + xOffset, startPoint.y + yOffset, endPoint.x + xOffset, endPoint.y + yOffset);
                leftEdgeLine = new java.awt.geom.Line2D.Float(startPoint.x - xOffset, startPoint.y - yOffset, endPoint.x - xOffset, endPoint.y - yOffset);
            }
        }
        if(!taxiway.intersects(g2.getClipBounds()))
        {
            leftEdgeLine = null;
            rightEdgeLine = null;
            return;
        }
        if(paintCenterLine)
        {
            if(currentlySelected)
                g2.setPaint(ColorsEngine.getInstance().getSelectedColor());
            else
            if(type.equals("RUNWAY"))
                g2.setPaint(Color.green);
            else
            if(type.equals("PARKING"))
                g2.setPaint(Color.yellow);
            else
            if(type.equals("TAXI"))
                g2.setPaint(new Color(153, 0, 0));
            else
            if(type.equals("PATH"))
                g2.setPaint(Color.lightGray);
            else
            if(type.equals("CLOSED"))
                g2.setPaint(Color.darkGray);
            else
            if(type.equals("VEHICLE"))
                g2.setPaint(Color.blue);
            else
                g2.setPaint(Color.pink);
            if(highlightedTWPath > 0 && highlightedTWPath == name)
            {
                if(!currentlySelected)
                    g2.setPaint(Color.red);
                g2.setStroke(new BasicStroke(2.0F));
                g2.draw(modelPath);
                g2.setStroke(new BasicStroke());
            } else
            {
                g2.draw(modelPath);
            }
        } else
        if(!type.equals("RUNWAY") && (!type.equals("PATH") || drawTaxiwayPaths))
        {
            g2.setColor(ColorsEngine.getInstance().getSurfaceColor(surface));
            g2.fill(taxiway);
            if(currentlySelected)
                if(SettingsEngine.getInstance().isSelectedItemOutlined())
                {
                    g2.setStroke(new BasicStroke(4F));
                    g2.setPaint(ColorsEngine.getInstance().getSelectedColor());
                    g2.draw(taxiway);
                    g2.setStroke(new BasicStroke());
                } else
                {
                    Color selectedColor = ColorsEngine.getInstance().getSelectedColor();
                    g2.setPaint(new Color(selectedColor.getRed(), selectedColor.getGreen(), selectedColor.getBlue(), 196));
                    g2.fill(taxiway);
                }
        }
        paintCenterLine = !paintCenterLine;
    }

    public void paintLights(Graphics2D g2)
    {
        if(displayLights && centerLineLighted && (double)scale >= 0.20000000000000001D)
        {
            double lightSize = 5D * (double)scale;
            double haloSize = 7D * (double)scale;
            g2.setPaint(Color.green);
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
            double lightSpacing;
            if(longLen < 0.0D)
                lightSpacing = -50D * (double)scale;
            else
                lightSpacing = 50D * (double)scale;
            double divDiff;
            if(shortLen == 0.0D)
                divDiff = longLen;
            else
                divDiff = longLen / shortLen;
            if(yLonger)
            {
                if(longLen > 0.0D)
                {
                    for(double i = 0.0D; i < longLen; i += lightSpacing)
                    {
                        g2.setPaint(Utilities.HALO_COLOR);
                        g2.fill(new java.awt.geom.Ellipse2D.Double(((double)startPoint.x + i / divDiff) - haloSize / 2D, ((double)startPoint.y + i) - haloSize / 2D, haloSize, haloSize));
                        g2.setPaint(Color.green);
                        g2.fill(new java.awt.geom.Ellipse2D.Double(((double)startPoint.x + i / divDiff) - lightSize / 2D, ((double)startPoint.y + i) - lightSize / 2D, lightSize, lightSize));
                    }

                } else
                {
                    for(double i = longLen; i <= 0.0D; i -= lightSpacing)
                    {
                        g2.setPaint(Utilities.HALO_COLOR);
                        g2.fill(new java.awt.geom.Ellipse2D.Double(((double)startPoint.x + i / divDiff) - haloSize / 2D, ((double)startPoint.y + i) - haloSize / 2D, haloSize, haloSize));
                        g2.setPaint(Color.green);
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
                    g2.setPaint(Color.green);
                    g2.fill(new java.awt.geom.Ellipse2D.Double(((double)startPoint.x + i) - lightSize / 2D, ((double)startPoint.y + i / divDiff) - lightSize / 2D, lightSize, lightSize));
                }

            } else
            {
                for(double i = longLen; i <= 0.0D; i -= lightSpacing)
                {
                    g2.setPaint(Utilities.HALO_COLOR);
                    g2.fill(new java.awt.geom.Ellipse2D.Double(((double)startPoint.x + i) - haloSize / 2D, ((double)startPoint.y + i / divDiff) - haloSize / 2D, haloSize, haloSize));
                    g2.setPaint(Color.green);
                    g2.fill(new java.awt.geom.Ellipse2D.Double(((double)startPoint.x + i) - lightSize / 2D, ((double)startPoint.y + i / divDiff) - lightSize / 2D, lightSize, lightSize));
                }

            }
        }
    }

    public void setHighlightedTWPath(int highlightedTWPath)
    {
        this.highlightedTWPath = highlightedTWPath;
    }

    public Line2D getLeftEdgeLine()
    {
        return leftEdgeLine;
    }

    public Line2D getRightEdgeLine()
    {
        return rightEdgeLine;
    }

    public GeneralPath getModelPath()
    {
        return modelPath;
    }

    public boolean isWithinObject(int x, int y)
    {
        if(modelPath == null)
            return false;
        if(type.equals("RUNWAY"))
            return modelPath.intersects(x - 3, y - 3, 6D, 6D);
        if(taxiway != null)
            return taxiway.contains(x, y);
        else
            return modelPath.intersects(x - 3, y - 3, 6D, 6D);
    }

    public String getModelName()
    {
        return (new StringBuilder()).append(modelName).append(" [").append(start).append(" - ").append(end).append("]").toString();
    }

    public double getStartLat()
    {
        return startLat;
    }

    public void setStartLat(double startLat)
    {
        this.startLat = startLat;
    }

    public double getStartLon()
    {
        return startLon;
    }

    public void setStartLon(double startLon)
    {
        this.startLon = startLon;
    }

    public double getEndLat()
    {
        return endLat;
    }

    public void setEndLat(double endLat)
    {
        this.endLat = endLat;
    }

    public double getEndLon()
    {
        return endLon;
    }

    public void setEndLon(double endLon)
    {
        this.endLon = endLon;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        if(shouldNotify && !this.type.equals(type))
            HistoryListModel.getInstance().addModel(new HistoryModel("setType", "Type", "", this, type, this.type));
        firePropertyChange("type", this.type, type);
        this.type = type;
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

    public String getLeftEdge()
    {
        return leftEdge;
    }

    public void setLeftEdge(String leftEdge)
    {
        if(shouldNotify && !this.leftEdge.equals(leftEdge))
            HistoryListModel.getInstance().addModel(new HistoryModel("setLeftEdge", "Left Edge", "", this, leftEdge, this.leftEdge));
        firePropertyChange("leftEdge", this.leftEdge, leftEdge);
        this.leftEdge = leftEdge;
    }

    public String getRightEdge()
    {
        return rightEdge;
    }

    public void setRightEdge(String rightEdge)
    {
        if(shouldNotify && !this.rightEdge.equals(rightEdge))
            HistoryListModel.getInstance().addModel(new HistoryModel("setRightEdge", "Right Edge", "", this, rightEdge, this.rightEdge));
        firePropertyChange("rightEdge", this.rightEdge, rightEdge);
        this.rightEdge = rightEdge;
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
    }

    public String getDesignator()
    {
        return designator;
    }

    public void setDesignator(String designator)
    {
        if(designator.length() == 0)
            designator = "NONE";
        if(shouldNotify && !this.designator.equals(designator))
            HistoryListModel.getInstance().addModel(new HistoryModel("setDesignator", "Designator", "", this, designator, this.designator));
        firePropertyChange("designator", this.designator, designator);
        this.designator = designator;
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

    public int getName()
    {
        return name;
    }

    public void setName(int name)
    {
        if(name < 0 || name > 255)
            return;
        if(shouldNotify && this.name != name)
            HistoryListModel.getInstance().addModel(new HistoryModel("setName", "Name", "", this, new Integer(name), new Integer(this.name)));
        firePropertyChange("name", new Integer(this.name), new Integer(name));
        this.name = name;
    }

    public int getStart()
    {
        return start;
    }

    public void setStart(int start)
    {
        if(shouldNotify && this.start != start)
            HistoryListModel.getInstance().addModel(new HistoryModel("setStart", "Start Index", "", this, new Integer(start), new Integer(this.start)));
        firePropertyChange("start", new Integer(this.start), new Integer(start));
        this.start = start;
    }

    public int getEnd()
    {
        return end;
    }

    public void setEnd(int end)
    {
        if(shouldNotify && this.end != end)
            HistoryListModel.getInstance().addModel(new HistoryModel("setEnd", "End Index", "", this, new Integer(end), new Integer(this.end)));
        firePropertyChange("end", new Integer(this.end), new Integer(end));
        this.end = end;
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

    public float getWeightLimit()
    {
        return weightLimit;
    }

    public void setWeightLimit(float weightLimit)
    {
        if(shouldNotify && this.weightLimit != weightLimit)
            HistoryListModel.getInstance().addModel(new HistoryModel("setWeightLimit", "Weight Limit", "", this, new Float(weightLimit), new Float(this.weightLimit)));
        firePropertyChange("weightLimit", new Float(this.weightLimit), new Float(weightLimit));
        this.weightLimit = weightLimit;
    }

    public boolean getDrawSurface()
    {
        return drawSurface;
    }

    public void setDrawSurface(boolean drawSurface)
    {
        if(shouldNotify && this.drawSurface != drawSurface)
            HistoryListModel.getInstance().addModel(new HistoryModel("setDrawSurface", "Draw Surface", "", this, new Boolean(drawSurface), new Boolean(this.drawSurface)));
        firePropertyChange("drawSurface", new Boolean(this.drawSurface), new Boolean(drawSurface));
        this.drawSurface = drawSurface;
    }

    public boolean getDrawDetail()
    {
        return drawDetail;
    }

    public void setDrawDetail(boolean drawDetail)
    {
        if(shouldNotify && this.drawDetail != drawDetail)
            HistoryListModel.getInstance().addModel(new HistoryModel("setDrawDetail", "Draw Detail", "", this, new Boolean(drawDetail), new Boolean(this.drawDetail)));
        firePropertyChange("drawDetail", new Boolean(this.drawDetail), new Boolean(drawDetail));
        this.drawDetail = drawDetail;
    }

    public boolean getCenterLine()
    {
        return centerLine;
    }

    public void setCenterLine(boolean centerLine)
    {
        if(shouldNotify && this.centerLine != centerLine)
            HistoryListModel.getInstance().addModel(new HistoryModel("setCenterLine", "Center Line", "", this, new Boolean(centerLine), new Boolean(this.centerLine)));
        firePropertyChange("centerLine", new Boolean(this.centerLine), new Boolean(centerLine));
        this.centerLine = centerLine;
    }

    public boolean getCenterLineLighted()
    {
        return centerLineLighted;
    }

    public void setCenterLineLighted(boolean centerLineLighted)
    {
        if(shouldNotify && this.centerLineLighted != centerLineLighted)
            HistoryListModel.getInstance().addModel(new HistoryModel("setCenterLineLighted", "Center Line Lighted", "", this, new Boolean(centerLineLighted), new Boolean(this.centerLineLighted)));
        firePropertyChange("centerLineLighted", new Boolean(this.centerLineLighted), new Boolean(centerLineLighted));
        this.centerLineLighted = centerLineLighted;
    }

    public boolean getLeftEdgeLighted()
    {
        return leftEdgeLighted;
    }

    public void setLeftEdgeLighted(boolean leftEdgeLighted)
    {
        if(shouldNotify && this.leftEdgeLighted != leftEdgeLighted)
            HistoryListModel.getInstance().addModel(new HistoryModel("setLeftEdgeLighted", "Left Edge Lighted", "", this, new Boolean(leftEdgeLighted), new Boolean(this.leftEdgeLighted)));
        firePropertyChange("leftEdgeLighted", new Boolean(this.leftEdgeLighted), new Boolean(leftEdgeLighted));
        this.leftEdgeLighted = leftEdgeLighted;
    }

    public boolean getRightEdgeLighted()
    {
        return rightEdgeLighted;
    }

    public void setRightEdgeLighted(boolean rightEdgeLighted)
    {
        if(shouldNotify && this.rightEdgeLighted != rightEdgeLighted)
            HistoryListModel.getInstance().addModel(new HistoryModel("setRightEdgeLighted", "Right Edge Lighted", "", this, new Boolean(rightEdgeLighted), new Boolean(this.rightEdgeLighted)));
        firePropertyChange("rightEdgeLighted", new Boolean(this.rightEdgeLighted), new Boolean(rightEdgeLighted));
        this.rightEdgeLighted = rightEdgeLighted;
    }

    public Object clone()
    {
        TaxiwayPathModel model = new TaxiwayPathModel();
        model.setType(getType());
        model.setSurface(getSurface());
        model.setLeftEdge(getLeftEdge());
        model.setRightEdge(getRightEdge());
        model.setNumber(getNumber());
        model.setDesignator(getDesignator());
        model.setWidthMeasure(getWidthMeasure());
        model.setName(getName());
        model.setStart(getStart());
        model.setEnd(getEnd());
        model.setWidth(getWidth());
        model.setWeightLimit(getWeightLimit());
        model.setDrawSurface(getDrawSurface());
        model.setDrawDetail(getDrawDetail());
        model.setCenterLine(getCenterLine());
        model.setCenterLineLighted(getCenterLineLighted());
        model.setLeftEdgeLighted(getLeftEdgeLighted());
        model.setRightEdgeLighted(getRightEdgeLighted());
        return model;
    }

    public void propertyChange(PropertyChangeEvent event)
    {
        if(event.getSource() instanceof SettingsEngine)
            if(event.getPropertyName().equals("drawTaxiwayPaths"))
                drawTaxiwayPaths = ((Boolean)event.getNewValue()).booleanValue();
            else
            if(event.getPropertyName().equals("displayLights"))
                displayLights = ((Boolean)event.getNewValue()).booleanValue();
    }

    private Line2D leftEdgeLine;
    private Line2D rightEdgeLine;
    private GeneralPath taxiway;
    private java.awt.geom.Point2D.Float startPoint;
    private java.awt.geom.Point2D.Float endPoint;
    private String type;
    private String surface;
    private String leftEdge;
    private String rightEdge;
    private String number;
    private String designator;
    private String widthMeasure;
    private int name;
    private int start;
    private int end;
    private int highlightedTWPath;
    private float width;
    private float weightLimit;
    private double startLat;
    private double startLon;
    private double endLat;
    private double endLon;
    private boolean drawSurface;
    private boolean drawDetail;
    private boolean centerLine;
    private boolean centerLineLighted;
    private boolean leftEdgeLighted;
    private boolean rightEdgeLighted;
    private boolean paintCenterLine;
    private boolean drawTaxiwayPaths;
    private boolean displayLights;
}
