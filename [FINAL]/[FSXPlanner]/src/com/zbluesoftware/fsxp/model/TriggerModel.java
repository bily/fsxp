// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TriggerModel.java

package com.zbluesoftware.fsxp.model;

import com.zbluesoftware.fsxp.engine.ColorsEngine;
import com.zbluesoftware.fsxp.engine.SettingsEngine;
import com.zbluesoftware.fsxp.util.LatLonPoint;
import com.zbluesoftware.fsxp.util.Utilities;
import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;

// Referenced classes of package com.zbluesoftware.fsxp.model:
//            BaseModel, VertexModel, HistoryModel, HistoryListModel

public class TriggerModel extends BaseModel
{

    public TriggerModel()
    {
        modelName = "Fuel Trigger";
        vertexAL = new ArrayList();
        modifying = false;
        alpha = 255;
        type73 = "NO";
        type87 = "NO";
        type100 = "NO";
        type130 = "NO";
        type145 = "NO";
        typeMogas = "NO";
        typeJet = "NO";
        typeJetA = "NO";
        typeJetA1 = "NO";
        typeJetAP = "NO";
        typeJetB = "NO";
        typeJet4 = "NO";
        typeJet5 = "NO";
        typeUnknown = "NO";
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
        if(vertexAL.size() <= 2)
        {
            g2.setPaint(ColorsEngine.getInstance().getTriggerColor());
            g2.draw(modelPath);
        } else
        {
            Color triggerColor = ColorsEngine.getInstance().getTriggerColor();
            g2.setPaint(new Color(triggerColor.getRed(), triggerColor.getGreen(), triggerColor.getBlue(), 128));
            g2.fill(modelPath);
            g2.setPaint(ColorsEngine.getInstance().getTriggerColor());
            g2.draw(modelPath);
        }
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
            int size = (int)(5F * scale);
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
        LatLonPoint triggerCenter = getCenterLatLon();
        double latDifference = latLonPoint.getLat() - modelLatLonPoint.getLat();
        double lonDifference = latLonPoint.getLon() - modelLatLonPoint.getLon();
        for(int i = vertexAL.size() - 1; i >= 0; i--)
        {
            VertexModel vertexModel = (VertexModel)vertexAL.get(i);
            vertexModel.adjustLatLon(latDifference, lonDifference);
        }

        if(shouldNotify)
            HistoryListModel.getInstance().addModel(new HistoryModel("undoMoveTo", "getCenterLatLon", "Position", "", this, triggerCenter, getCenterLatLon()));
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
        float size = 2.5F * scale;
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
        for(int i = vertexAL.size() - 1; i >= 0; i--)
        {
            VertexModel model1 = (VertexModel)vertexAL.get(i);
            VertexModel model2;
            if(i == totalVertices)
                model2 = (VertexModel)vertexAL.get(0);
            else
                model2 = (VertexModel)vertexAL.get(i + 1);
            java.awt.geom.Line2D.Double lineSegment = new java.awt.geom.Line2D.Double(model1.getCurrentPoint().x, model1.getCurrentPoint().y, model2.getCurrentPoint().x, model2.getCurrentPoint().y);
            if(lineSegment.intersects(x - 3, y - 3, 6D, 6D))
                return i;
        }

        return -1;
    }

    public String getType73()
    {
        return type73;
    }

    public void setType73(String type73)
    {
        if(shouldNotify && !this.type73.equals(type73))
            HistoryListModel.getInstance().addModel(new HistoryModel("setType73", "Type 73", "", this, type73, this.type73));
        firePropertyChange("type73", this.type73, type73);
        this.type73 = type73;
    }

    public String getType87()
    {
        return type87;
    }

    public void setType87(String type87)
    {
        if(shouldNotify && !this.type87.equals(type87))
            HistoryListModel.getInstance().addModel(new HistoryModel("setType87", "Type 87", "", this, type87, this.type87));
        firePropertyChange("type87", this.type87, type87);
        this.type87 = type87;
    }

    public String getType100()
    {
        return type100;
    }

    public void setType100(String type100)
    {
        if(shouldNotify && !this.type100.equals(type100))
            HistoryListModel.getInstance().addModel(new HistoryModel("setType100", "Type 100", "", this, type100, this.type100));
        firePropertyChange("type100", this.type100, type100);
        this.type100 = type100;
    }

    public String getType130()
    {
        return type130;
    }

    public void setType130(String type130)
    {
        if(shouldNotify && !this.type130.equals(type130))
            HistoryListModel.getInstance().addModel(new HistoryModel("setType130", "Type 130", "", this, type130, this.type130));
        firePropertyChange("type130", this.type130, type130);
        this.type130 = type130;
    }

    public String getType145()
    {
        return type145;
    }

    public void setType145(String type145)
    {
        if(shouldNotify && !this.type145.equals(type145))
            HistoryListModel.getInstance().addModel(new HistoryModel("setType145", "Type 145", "", this, type145, this.type145));
        firePropertyChange("type145", this.type145, type145);
        this.type145 = type145;
    }

    public String getTypeMogas()
    {
        return typeMogas;
    }

    public void setTypeMogas(String typeMogas)
    {
        if(shouldNotify && !this.typeMogas.equals(typeMogas))
            HistoryListModel.getInstance().addModel(new HistoryModel("setTypeMogas", "Type Mogas", "", this, typeMogas, this.typeMogas));
        firePropertyChange("typeMogas", this.typeMogas, typeMogas);
        this.typeMogas = typeMogas;
    }

    public String getTypeJet()
    {
        return typeJet;
    }

    public void setTypeJet(String typeJet)
    {
        if(shouldNotify && !this.typeJet.equals(typeJet))
            HistoryListModel.getInstance().addModel(new HistoryModel("setTypeJet", "Type Jet", "", this, typeJet, this.typeJet));
        firePropertyChange("typeJet", this.typeJet, typeJet);
        this.typeJet = typeJet;
    }

    public String getTypeJetA()
    {
        return typeJetA;
    }

    public void setTypeJetA(String typeJetA)
    {
        if(shouldNotify && !this.typeJetA.equals(typeJetA))
            HistoryListModel.getInstance().addModel(new HistoryModel("setTypeJetA", "Type Jet A", "", this, typeJetA, this.typeJetA));
        firePropertyChange("typeJetA", this.typeJetA, typeJetA);
        this.typeJetA = typeJetA;
    }

    public String getTypeJetA1()
    {
        return typeJetA1;
    }

    public void setTypeJetA1(String typeJetA1)
    {
        if(shouldNotify && !this.typeJetA1.equals(typeJetA1))
            HistoryListModel.getInstance().addModel(new HistoryModel("setTypeJetA1", "Type Jet A1", "", this, typeJetA1, this.typeJetA1));
        firePropertyChange("typeJetA1", this.typeJetA1, typeJetA1);
        this.typeJetA1 = typeJetA1;
    }

    public String getTypeJetAP()
    {
        return typeJetAP;
    }

    public void setTypeJetAP(String typeJetAP)
    {
        if(shouldNotify && !this.typeJetAP.equals(typeJetAP))
            HistoryListModel.getInstance().addModel(new HistoryModel("setTypeJetAP", "Type Jet AP", "", this, typeJetAP, this.typeJetAP));
        firePropertyChange("typeJetAP", this.typeJetAP, typeJetAP);
        this.typeJetAP = typeJetAP;
    }

    public String getTypeJetB()
    {
        return typeJetB;
    }

    public void setTypeJetB(String typeJetB)
    {
        if(shouldNotify && !this.typeJetB.equals(typeJetB))
            HistoryListModel.getInstance().addModel(new HistoryModel("setTypeJetB", "Type Jet B", "", this, typeJetB, this.typeJetB));
        firePropertyChange("typeJetB", this.typeJetB, typeJetB);
        this.typeJetB = typeJetB;
    }

    public String getTypeJet4()
    {
        return typeJet4;
    }

    public void setTypeJet4(String typeJet4)
    {
        if(shouldNotify && !this.typeJet4.equals(typeJet4))
            HistoryListModel.getInstance().addModel(new HistoryModel("setTypeJet4", "Type Jet 4", "", this, typeJet4, this.typeJet4));
        firePropertyChange("typeJet4", this.typeJet4, typeJet4);
        this.typeJet4 = typeJet4;
    }

    public String getTypeJet5()
    {
        return typeJet5;
    }

    public void setTypeJet5(String typeJet5)
    {
        if(shouldNotify && !this.typeJet5.equals(typeJet5))
            HistoryListModel.getInstance().addModel(new HistoryModel("setTypeJet5", "Type Jet 5", "", this, typeJet5, this.typeJet5));
        firePropertyChange("typeJet5", this.typeJet5, typeJet5);
        this.typeJet5 = typeJet5;
    }

    public String getTypeUnknown()
    {
        return typeUnknown;
    }

    public void setTypeUnknown(String typeUnknown)
    {
        if(shouldNotify && !this.typeUnknown.equals(typeUnknown))
            HistoryListModel.getInstance().addModel(new HistoryModel("setTypeUnknown", "Type Unknown", "", this, typeUnknown, this.typeUnknown));
        firePropertyChange("typeUnknown", this.typeUnknown, typeUnknown);
        this.typeUnknown = typeUnknown;
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
        TriggerModel model = new TriggerModel();
        model.setShouldNotify(false);
        model.setScale(scale);
        model.setCenterPoint((LatLonPoint)centerPoint.clone());
        model.setType73(getType73());
        model.setType87(getType87());
        model.setType100(getType100());
        model.setType130(getType130());
        model.setType145(getType145());
        model.setTypeMogas(getTypeMogas());
        model.setTypeJet(getTypeJet());
        model.setTypeJetA(getTypeJetA());
        model.setTypeJetA1(getTypeJetA1());
        model.setTypeJetAP(getTypeJetAP());
        model.setTypeJetB(getTypeJetB());
        model.setTypeJet4(getTypeJet4());
        model.setTypeJet5(getTypeJet5());
        model.setTypeUnknown(getTypeUnknown());
        model.setShouldNotify(true);
        int vertexCount = getVertexAL().size();
        for(int i = 0; i < vertexCount; i++)
            model.addVertexModel((VertexModel)((VertexModel)vertexAL.get(i)).clone());

        return model;
    }

    private ArrayList vertexAL;
    private String type73;
    private String type87;
    private String type100;
    private String type130;
    private String type145;
    private String typeMogas;
    private String typeJet;
    private String typeJetA;
    private String typeJetA1;
    private String typeJetAP;
    private String typeJetB;
    private String typeJet4;
    private String typeJet5;
    private String typeUnknown;
    private int alpha;
    private boolean modifying;
}
