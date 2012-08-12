// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BoundaryFenceModel.java

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

public class BoundaryFenceModel extends BaseModel
    implements PropertyChangeListener
{

    public BoundaryFenceModel()
    {
        modelName = "Boundary Fence";
        vertexAL = new ArrayList();
        instanceID = "";
        profile = "{a3a491b1-ef49-47db-9c2b-080f48a5ea5d}";
        modifying = false;
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
            int vertexSize = vertexAL.size() - 1;
            for(int i = 0; i < vertexSize; i++)
            {
                VertexModel model1 = (VertexModel)vertexAL.get(i);
                VertexModel model2 = (VertexModel)vertexAL.get(i + 1);
                java.awt.geom.Point2D.Float nextPoint = Utilities.getPixelsBetweenPoints(model1.getLatLon().getLat(), model1.getLatLon().getLon(), model2.getLatLon().getLat(), model2.getLatLon().getLon(), scale);
                currentPoint = new java.awt.geom.Point2D.Float(currentPoint.x + nextPoint.x, currentPoint.y + nextPoint.y);
                model2.setCurrentPoint(currentPoint);
                modelPath.lineTo(currentPoint.x, currentPoint.y);
            }

        }
        if(!modelPath.intersects(g2.getClipBounds()))
            return;
        if(!currentlySelected && !modifying)
        {
            g2.setPaint(Color.black);
            g2.draw(modelPath);
        }
        if(currentlySelected || modifying)
        {
            g2.setStroke(new BasicStroke(2.0F));
            g2.setPaint(ColorsEngine.getInstance().getSelectedColor());
            g2.draw(modelPath);
            int size = (int)(this.vertexSize * scale);
            int fontSize = Math.max(6, (int)(10F * scale));
            g2.setFont(new Font("Arial", 0, fontSize));
            for(int i = vertexAL.size() - 1; i >= 0; i--)
            {
                g2.setPaint(new Color(255, 0, 0));
                g2.fill(new java.awt.geom.Ellipse2D.Double(((VertexModel)vertexAL.get(i)).getCurrentPoint().x - (float)size / 2.0F, ((VertexModel)vertexAL.get(i)).getCurrentPoint().y - (float)size / 2.0F, size, size));
                if(((VertexModel)vertexAL.get(i)).isCurrentlySelected())
                    g2.setPaint(ColorsEngine.getInstance().getSelectedColor());
                else
                    g2.setPaint(new Color(0, 0, 204));
                g2.draw(new java.awt.geom.Ellipse2D.Double(((VertexModel)vertexAL.get(i)).getCurrentPoint().x - (float)size / 2.0F, ((VertexModel)vertexAL.get(i)).getCurrentPoint().y - (float)size / 2.0F, size, size));
                g2.drawString((new StringBuilder()).append("").append(i).toString(), ((VertexModel)vertexAL.get(i)).getCurrentPoint().x, ((VertexModel)vertexAL.get(i)).getCurrentPoint().y + (float)fontSize);
            }

            g2.setStroke(new BasicStroke());
        }
    }

    public boolean isWithinObject(int x, int y)
    {
        if(modelPath == null)
            return false;
        float coords[] = new float[6];
        Point lastPoint = null;
        for(PathIterator iterator = modelPath.getPathIterator(new AffineTransform()); !iterator.isDone(); lastPoint = new Point((int)coords[0], (int)coords[1]))
        {
            iterator.currentSegment(coords);
            iterator.next();
            if(lastPoint == null)
                continue;
            java.awt.geom.Line2D.Float line = new java.awt.geom.Line2D.Float(lastPoint.x, lastPoint.y, coords[0], coords[1]);
            if(line.intersects(x - 3, y - 3, 6D, 6D))
                return true;
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

    public String getInstanceID()
    {
        return instanceID;
    }

    public void setInstanceID(String instanceID)
    {
        if(shouldNotify && !this.instanceID.equals(instanceID))
            HistoryListModel.getInstance().addModel(new HistoryModel("setInstanceID", "Instance ID", "", this, instanceID, this.instanceID));
        firePropertyChange("instanceID", this.instanceID, instanceID);
        this.instanceID = instanceID;
    }

    public String getProfile()
    {
        return profile;
    }

    public void setProfile(String profile)
    {
        if(shouldNotify && !this.profile.equals(profile))
            HistoryListModel.getInstance().addModel(new HistoryModel("setProfile", "Profile", "", this, profile, this.profile));
        firePropertyChange("profile", this.profile, profile);
        this.profile = profile;
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
        BoundaryFenceModel model = new BoundaryFenceModel();
        model.setShouldNotify(false);
        model.setInstanceID(getInstanceID());
        model.setProfile(getProfile());
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
    private String instanceID;
    private String profile;
    private float vertexSize;
    private boolean modifying;
}
