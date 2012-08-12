// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ApronEdgeLightModel.java

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
//            BaseModel, VertexModel

public class ApronEdgeLightModel extends BaseModel
    implements PropertyChangeListener
{

    public ApronEdgeLightModel()
    {
        vertexAL = new ArrayList();
        modifying = false;
        edgeLightLines = SettingsEngine.getInstance().getEdgeLightLines();
        modelName = "Apron Edge Lights";
        setVertexSize(SettingsEngine.getInstance().getVertexPointDiameter(), SettingsEngine.getInstance().getVertexPointMeasure());
        SettingsEngine.getInstance().addPropertyChangeListener(this);
    }

    public void paint(Graphics2D g2, boolean recreate)
    {
        if(recreate || modelPath == null)
        {
            int size = (int)(this.vertexSize * scale);
            modelPath = new GeneralPath();
            edgeLightPath = new GeneralPath();
            int vertexSize = vertexAL.size();
            for(int i = 0; i < vertexSize; i++)
            {
                VertexModel vertexModel = (VertexModel)vertexAL.get(i);
                java.awt.geom.Point2D.Float point = Utilities.getPixelsBetweenPoints(centerPoint.getLat(), centerPoint.getLon(), vertexModel.getLatLon().getLat(), vertexModel.getLatLon().getLon(), scale);
                vertexModel.setCurrentPoint(point);
                modelPath.append(new java.awt.geom.Ellipse2D.Float(point.x - (float)size / 2.0F, point.y - (float)size / 2.0F, size, size), false);
                if(i == 0)
                    edgeLightPath.moveTo(point.x, point.y);
                else
                    edgeLightPath.lineTo(point.x, point.y);
            }

        }
        if(!modelPath.intersects(g2.getClipBounds()))
            return;
        g2.setPaint(Color.red);
        g2.fill(modelPath);
        if(edgeLightLines && !currentlySelected && !modifying)
        {
            g2.setStroke(new BasicStroke(scale, 0, 2, 1.0F, new float[] {
                scale * 4F, scale * 4F
            }, scale * 2.0F));
            g2.draw(edgeLightPath);
            g2.setStroke(new BasicStroke());
        }
        if(currentlySelected || modifying)
        {
            g2.setStroke(new BasicStroke(2.0F));
            g2.setPaint(new Color(0, 0, 204));
            g2.draw(modelPath);
            g2.setStroke(new BasicStroke());
            g2.setPaint(new Color(255, 0, 0));
            int size = (int)(this.vertexSize * scale);
            int fontSize = Math.max(6, (int)(10F * scale));
            g2.setFont(new Font("Arial", 0, fontSize));
            for(int i = vertexAL.size() - 1; i >= 0; i--)
                if(((VertexModel)vertexAL.get(i)).isCurrentlySelected())
                {
                    g2.setPaint(ColorsEngine.getInstance().getSelectedColor());
                    g2.setStroke(new BasicStroke(2.0F));
                    g2.draw(new java.awt.geom.Ellipse2D.Double(((VertexModel)vertexAL.get(i)).getCurrentPoint().x - (float)size / 2.0F, ((VertexModel)vertexAL.get(i)).getCurrentPoint().y - (float)size / 2.0F, size, size));
                    g2.drawString((new StringBuilder()).append("").append(i).toString(), ((VertexModel)vertexAL.get(i)).getCurrentPoint().x, ((VertexModel)vertexAL.get(i)).getCurrentPoint().y + (float)fontSize);
                    g2.setStroke(new BasicStroke());
                    g2.setPaint(new Color(0, 0, 204));
                } else
                {
                    g2.drawString((new StringBuilder()).append("").append(i).toString(), ((VertexModel)vertexAL.get(i)).getCurrentPoint().x, ((VertexModel)vertexAL.get(i)).getCurrentPoint().y + (float)fontSize);
                }

            g2.setPaint(ColorsEngine.getInstance().getSelectedColor());
            g2.setStroke(new BasicStroke(3F, 0, 2, 1.0F, new float[] {
                4F, 4F
            }, 2.0F));
            g2.draw(edgeLightPath);
            g2.setStroke(new BasicStroke());
        }
    }

    public boolean isWithinObject(int x, int y)
    {
        if(modelPath == null)
            return false;
        float size = (int)(vertexSize * scale);
        for(int i = vertexAL.size() - 1; i >= 0; i--)
        {
            VertexModel vertexModel = (VertexModel)vertexAL.get(i);
            java.awt.geom.Ellipse2D.Float ellipse = new java.awt.geom.Ellipse2D.Float(vertexModel.getCurrentPoint().x - size / 2.0F, vertexModel.getCurrentPoint().y - size / 2.0F, size, size);
            if(ellipse.contains(x, y))
                return true;
        }

        if(edgeLightLines)
        {
            float coords[] = new float[6];
            Point lastPoint = null;
            for(PathIterator iterator = edgeLightPath.getPathIterator(new AffineTransform()); !iterator.isDone(); lastPoint = new Point((int)coords[0], (int)coords[1]))
            {
                iterator.currentSegment(coords);
                iterator.next();
                if(lastPoint == null)
                    continue;
                java.awt.geom.Line2D.Float line = new java.awt.geom.Line2D.Float(lastPoint.x, lastPoint.y, coords[0], coords[1]);
                if(line.intersects(x - 3, y - 3, 6D, 6D))
                    return true;
            }

        }
        return false;
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

    public VertexModel isWithinEndVertex(int x, int y)
    {
        float size = (vertexSize / 2.0F) * scale;
        if(vertexAL.size() == 0)
            return null;
        VertexModel vertexModel = (VertexModel)vertexAL.get(0);
        if((float)x >= vertexModel.getCurrentPoint().x - size && (float)x <= vertexModel.getCurrentPoint().x + size && (float)y >= vertexModel.getCurrentPoint().y - size && (float)y <= vertexModel.getCurrentPoint().y + size)
            return vertexModel;
        vertexModel = (VertexModel)vertexAL.get(vertexAL.size() - 1);
        if((float)x >= vertexModel.getCurrentPoint().x - size && (float)x <= vertexModel.getCurrentPoint().x + size && (float)y >= vertexModel.getCurrentPoint().y - size && (float)y <= vertexModel.getCurrentPoint().y + size)
            return vertexModel;
        else
            return null;
    }

    public int isBetweenVertices(int x, int y)
    {
        if(vertexAL.size() <= 1)
            return -1;
        for(int i = vertexAL.size() - 2; i >= 0; i--)
        {
            java.awt.geom.Line2D.Double lineSegment = new java.awt.geom.Line2D.Double(((VertexModel)vertexAL.get(i)).getCurrentPoint().x, ((VertexModel)vertexAL.get(i)).getCurrentPoint().y, ((VertexModel)vertexAL.get(i + 1)).getCurrentPoint().x, ((VertexModel)vertexAL.get(i + 1)).getCurrentPoint().y);
            if(lineSegment.intersects(x - 3, y - 3, 6D, 6D))
                return i;
        }

        return -1;
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

    private void setVertexSize(float diameter, String measure)
    {
        vertexSize = diameter;
        if(measure.equals("M"))
            vertexSize *= 3.28F;
    }

    public boolean isCopyable()
    {
        return true;
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
        ApronEdgeLightModel model = new ApronEdgeLightModel();
        model.setScale(scale);
        model.setCenterPoint((LatLonPoint)centerPoint.clone());
        int vertexCount = getVertexAL().size();
        for(int i = 0; i < vertexCount; i++)
            model.addVertexModel((VertexModel)((VertexModel)vertexAL.get(i)).clone());

        return model;
    }

    public void propertyChange(PropertyChangeEvent event)
    {
        if(event.getSource() instanceof SettingsEngine)
            if(event.getPropertyName().equals("edgeLightLines"))
                edgeLightLines = ((Boolean)event.getNewValue()).booleanValue();
            else
            if(event.getPropertyName().equals("vertexPointDiameter"))
                setVertexSize(((Float)event.getNewValue()).floatValue(), SettingsEngine.getInstance().getVertexPointMeasure());
            else
            if(event.getPropertyName().equals("vertexPointMeasure"))
                setVertexSize(SettingsEngine.getInstance().getVertexPointDiameter(), (String)event.getNewValue());
    }

    private GeneralPath edgeLightPath;
    private ArrayList vertexAL;
    private float vertexSize;
    private boolean modifying;
    private boolean edgeLightLines;
}
