// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ApronModel.java

package com.zbluesoftware.fsxp.model;

import com.zbluesoftware.fsxp.engine.ColorsEngine;
import com.zbluesoftware.fsxp.engine.SettingsEngine;
import com.zbluesoftware.fsxp.util.LatLonPoint;
import com.zbluesoftware.fsxp.util.Utilities;
import java.awt.*;
import java.awt.geom.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

// Referenced classes of package com.zbluesoftware.fsxp.model:
//            BaseModel, VertexModel, HistoryModel, HistoryListModel

public class ApronModel extends BaseModel
    implements PropertyChangeListener
{

    public ApronModel()
    {
        modelName = "Apron";
        vertexAL = new ArrayList();
        surface = "ASPHALT";
        drawSurface = true;
        drawDetail = true;
        modifying = false;
        alpha = 255;
        setVertexSize(SettingsEngine.getInstance().getVertexPointDiameter(), SettingsEngine.getInstance().getVertexPointMeasure());
        SettingsEngine.getInstance().addPropertyChangeListener(this);
    }

    public void paint(Graphics2D g2, boolean recreate)
    {
        if(vertexAL.size() <= 0)
            return;
        if(recreate || modelPath == null)
        {
            VertexModel vertexModel = (VertexModel)vertexAL.get(0);
            java.awt.geom.Point2D.Float currentPoint = Utilities.getPixelsBetweenPoints(centerPoint.getLat(), centerPoint.getLon(), vertexModel.getLatLon().getLat(), vertexModel.getLatLon().getLon(), scale);
            vertexModel.setCurrentPoint(currentPoint);
            modelPath = new GeneralPath();
            modelPath.moveTo(currentPoint.x, currentPoint.y);
            for(int i = 0; i < vertexAL.size(); i++)
            {
                VertexModel model1 = (VertexModel)vertexAL.get(i);
                VertexModel model2;
                if(i == vertexAL.size() - 1)
                    model2 = (VertexModel)vertexAL.get(0);
                else
                    model2 = (VertexModel)vertexAL.get(i + 1);
                java.awt.geom.Point2D.Float nextPoint = Utilities.getPixelsBetweenPoints(model1.getLatLon().getLat(), model1.getLatLon().getLon(), model2.getLatLon().getLat(), model2.getLatLon().getLon(), scale);
                currentPoint = new java.awt.geom.Point2D.Float(currentPoint.x + nextPoint.x, currentPoint.y + nextPoint.y);
                model2.setCurrentPoint(currentPoint);
                modelPath.lineTo(currentPoint.x, currentPoint.y);
            }

        }
        if(!modelPath.intersects(g2.getClipBounds()))
            return;
        Color color = ColorsEngine.getInstance().getSurfaceColor(surface);
        g2.setPaint(new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha));
        if(vertexAL.size() == 2)
            g2.draw(modelPath);
        else
            g2.fill(modelPath);
        if(currentlySelected && !modifying)
        {
            if(currentlySelected)
                if(SettingsEngine.getInstance().isSelectedItemOutlined())
                {
                    g2.setStroke(new BasicStroke(4F));
                    g2.setPaint(ColorsEngine.getInstance().getSelectedColor());
                    g2.draw(modelPath);
                    g2.setStroke(new BasicStroke());
                } else
                {
                    Color selectedColor = ColorsEngine.getInstance().getSelectedColor();
                    g2.setPaint(new Color(selectedColor.getRed(), selectedColor.getGreen(), selectedColor.getBlue(), 196));
                    g2.fill(modelPath);
                }
        } else
        if(modifying)
        {
            g2.setStroke(new BasicStroke(2.0F));
            Color selectedColor = ColorsEngine.getInstance().getSelectedColor();
            g2.setPaint(new Color(selectedColor.getRed(), selectedColor.getGreen(), selectedColor.getBlue(), 196));
            g2.draw(modelPath);
            int size = (int)(vertexSize * scale);
            int fontSize = Math.max(6, (int)(10F * scale));
            g2.setFont(new Font("Arial", 0, fontSize));
            for(int i = vertexAL.size() - 1; i >= 0; i--)
            {
                g2.setPaint(new Color(255, 0, 0));
                g2.fill(new java.awt.geom.Ellipse2D.Double(((VertexModel)vertexAL.get(i)).getCurrentPoint().x - (float)size / 2.0F, ((VertexModel)vertexAL.get(i)).getCurrentPoint().y - (float)size / 2.0F, size, size));
                if(((VertexModel)vertexAL.get(i)).isCurrentlySelected())
                {
                    g2.setPaint(ColorsEngine.getInstance().getSelectedColor());
                    g2.draw(new java.awt.geom.Ellipse2D.Double(((VertexModel)vertexAL.get(i)).getCurrentPoint().x - (float)size / 2.0F, ((VertexModel)vertexAL.get(i)).getCurrentPoint().y - (float)size / 2.0F, size, size));
                } else
                {
                    g2.setPaint(new Color(0, 0, 204));
                    g2.draw(new java.awt.geom.Ellipse2D.Double(((VertexModel)vertexAL.get(i)).getCurrentPoint().x - (float)size / 2.0F, ((VertexModel)vertexAL.get(i)).getCurrentPoint().y - (float)size / 2.0F, size, size));
                }
                g2.drawString((new StringBuilder()).append("").append(i).toString(), ((VertexModel)vertexAL.get(i)).getCurrentPoint().x, ((VertexModel)vertexAL.get(i)).getCurrentPoint().y + (float)fontSize);
            }

            g2.setStroke(new BasicStroke());
        }
    }

    public Shape getClip()
    {
        return modelPath;
    }

    public boolean moveTo(LatLonPoint latLonPoint, double oldX, double oldY)
    {
        LatLonPoint modelLatLonPoint = Utilities.getLatLonForPixel(centerPoint.getLat(), centerPoint.getLon(), oldX, oldY, scale);
        LatLonPoint apronCenter = getCenterLatLon();
        double latDifference = latLonPoint.getLat() - modelLatLonPoint.getLat();
        double lonDifference = latLonPoint.getLon() - modelLatLonPoint.getLon();
        for(int i = vertexAL.size() - 1; i >= 0; i--)
        {
            VertexModel vertexModel = (VertexModel)vertexAL.get(i);
            vertexModel.adjustLatLon(latDifference, lonDifference);
        }

        if(shouldNotify)
            HistoryListModel.getInstance().addModel(new HistoryModel("undoMoveTo", "getCenterLatLon", "Position", "", this, apronCenter, getCenterLatLon()));
        return true;
    }

    public void undoMoveTo(LatLonPoint oldLatLon)
    {
        LatLonPoint currentLatLon = getCenterLatLon();
        double latDifference = oldLatLon.getLat() - currentLatLon.getLat();
        double lonDifference = oldLatLon.getLon() - currentLatLon.getLon();
        for(int i = vertexAL.size() - 1; i >= 0; i--)
        {
            VertexModel vertexModel = (VertexModel)vertexAL.get(i);
            vertexModel.adjustLatLon(latDifference, lonDifference);
        }

    }

    public void moveCenterTo(LatLonPoint latLonPoint)
    {
        if(vertexAL.size() == 0)
            return;
        GeneralPath path = new GeneralPath();
        VertexModel vertexModel = (VertexModel)vertexAL.get(vertexAL.size() - 1);
        path.moveTo(vertexModel.getCurrentPoint().x, vertexModel.getCurrentPoint().y);
        for(int i = vertexAL.size() - 2; i >= 0; i--)
        {
            vertexModel = (VertexModel)vertexAL.get(i);
            path.lineTo(vertexModel.getCurrentPoint().x, vertexModel.getCurrentPoint().y);
        }

        Rectangle2D bounds = path.getBounds2D();
        double centerX = bounds.getX() + bounds.getWidth() / 2D;
        double centerY = bounds.getY() + bounds.getHeight() / 2D;
        LatLonPoint modelLatLonPoint = Utilities.getLatLonForPixel(centerPoint.getLat(), centerPoint.getLon(), centerX, centerY, scale);
        double latDifference = latLonPoint.getLat() - modelLatLonPoint.getLat();
        double lonDifference = latLonPoint.getLon() - modelLatLonPoint.getLon();
        for(int i = vertexAL.size() - 1; i >= 0; i--)
        {
            vertexModel = (VertexModel)vertexAL.get(i);
            vertexModel.adjustLatLon(latDifference, lonDifference);
        }

    }

    public LatLonPoint getCenterLatLon()
    {
        GeneralPath path = new GeneralPath();
        VertexModel vertexModel = (VertexModel)vertexAL.get(vertexAL.size() - 1);
        path.moveTo(vertexModel.getCurrentPoint().x, vertexModel.getCurrentPoint().y);
        for(int i = vertexAL.size() - 2; i >= 0; i--)
        {
            vertexModel = (VertexModel)vertexAL.get(i);
            path.lineTo(vertexModel.getCurrentPoint().x, vertexModel.getCurrentPoint().y);
        }

        Rectangle2D bounds = path.getBounds2D();
        double centerX = bounds.getX() + bounds.getWidth() / 2D;
        double centerY = bounds.getY() + bounds.getHeight() / 2D;
        return Utilities.getLatLonForPixel(centerPoint.getLat(), centerPoint.getLon(), centerX, centerY, scale);
    }

    public VertexModel isWithinVertex(int x, int y)
    {
        float size = (vertexSize / 2.0F) * scale;
        for(int i = vertexAL.size() - 1; i >= 0; i--)
        {
            VertexModel vertexModel = (VertexModel)vertexAL.get(i);
            if((float)x >= vertexModel.getCurrentPoint().x - size && (float)x <= vertexModel.getCurrentPoint().x + size && (float)y >= vertexModel.getCurrentPoint().y - size && (float)y <= vertexModel.getCurrentPoint().y + size)
                return vertexModel;
        }

        return null;
    }

    public boolean isCopyable()
    {
        return true;
    }

    public int isBetweenVertices(int x, int y)
    {
        if(vertexAL.size() <= 1)
            return -1;
        int totalVertices = vertexAL.size() - 1;
        double size = 3F * scale;
        for(int i = vertexAL.size() - 1; i >= 0; i--)
        {
            VertexModel model1 = (VertexModel)vertexAL.get(i);
            VertexModel model2;
            if(i == totalVertices)
                model2 = (VertexModel)vertexAL.get(0);
            else
                model2 = (VertexModel)vertexAL.get(i + 1);
            java.awt.geom.Line2D.Double lineSegment = new java.awt.geom.Line2D.Double(model1.getCurrentPoint().x, model1.getCurrentPoint().y, model2.getCurrentPoint().x, model2.getCurrentPoint().y);
            if(lineSegment.intersects((double)x - size, (double)y - size, size * 2D, size * 2D))
                return i;
        }

        return -1;
    }

    private void setVertexSize(float diameter, String measure)
    {
        vertexSize = diameter;
        if(measure.equals("M"))
            vertexSize *= 3.28F;
    }

    public void setAlpha(int alpha)
    {
        this.alpha = alpha;
    }

    public void setModifying(boolean modifying)
    {
        this.modifying = modifying;
    }

    public ArrayList getVertexAL()
    {
        return vertexAL;
    }

    public void addVertexModel(VertexModel vertexModel)
    {
        if(!vertexAL.contains(vertexModel))
            vertexAL.add(vertexModel);
    }

    public void insertVertexModel(VertexModel vertexModel, int index)
    {
        vertexAL.add(index, vertexModel);
    }

    public void removeVertexModel(VertexModel vertexModel)
    {
        vertexAL.remove(vertexModel);
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

    public void displayVertexModel(VertexModel vertexModel)
    {
        firePropertyChange("vertexModel", vertexModel, vertexModel);
    }

    public int getVertexModelIndex(VertexModel vertexModel)
    {
        for(int i = vertexAL.size() - 1; i >= 0; i--)
            if(vertexAL.get(i) == vertexModel)
                return i;

        return -1;
    }

    public Object clone()
    {
        ApronModel model = new ApronModel();
        model.setShouldNotify(false);
        model.setSurface(getSurface());
        model.setDrawSurface(getDrawSurface());
        model.setDrawDetail(getDrawDetail());
        model.setScale(scale);
        model.setCenterPoint((LatLonPoint)centerPoint.clone());
        model.setShouldNotify(true);
        int vertexCount = getVertexAL().size();
        for(int i = 0; i < vertexCount; i++)
            model.addVertexModel((VertexModel)((VertexModel)vertexAL.get(i)).clone());

        return model;
    }

    public void propertyChange(PropertyChangeEvent event)
    {
        if(event.getSource() instanceof SettingsEngine)
            if(event.getPropertyName().equals("vertexPointDiameter"))
                setVertexSize(((Float)event.getNewValue()).floatValue(), SettingsEngine.getInstance().getVertexPointMeasure());
            else
            if(event.getPropertyName().equals("vertexPointMeasure"))
                setVertexSize(SettingsEngine.getInstance().getVertexPointDiameter(), (String)event.getNewValue());
    }

    private ArrayList vertexAL;
    private String surface;
    private int alpha;
    private float vertexSize;
    private boolean drawSurface;
    private boolean drawDetail;
    private boolean modifying;
}
