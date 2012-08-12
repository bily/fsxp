// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TaxiwayParkingModel.java

package com.zbluesoftware.fsxp.model;

import com.zbluesoftware.fsxp.engine.*;
import com.zbluesoftware.fsxp.util.LatLonPoint;
import com.zbluesoftware.fsxp.util.Utilities;
import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.HashMap;

// Referenced classes of package com.zbluesoftware.fsxp.model:
//            BaseModel, HistoryModel, HistoryListModel

public class TaxiwayParkingModel extends BaseModel
{

    public TaxiwayParkingModel()
    {
        modelName = "Taxiway Parking";
        biasXMeasure = "M";
        biasZMeasure = "M";
        radiusMeasure = SettingsEngine.getInstance().getParkingMeasure();
        type = "RAMP_GA_MEDIUM";
        name = "PARKING";
        airlineCodes = "";
        pushBack = "BOTH";
        index = 0;
        number = 0;
        heading = 0.0F;
        radius = SettingsEngine.getInstance().getParkingSize();
        teeOffset1 = SettingsEngine.getInstance().getTee1Offset();
        teeOffset2 = SettingsEngine.getInstance().getTee2Offset();
        teeOffset3 = SettingsEngine.getInstance().getTee3Offset();
        teeOffset4 = SettingsEngine.getInstance().getTee4Offset();
        biasX = 0.0D;
        biasZ = 0.0D;
        latLon = new LatLonPoint();
        taxiwayLineAL = new ArrayList();
        parkingColor = new Color(153, 255, 153);
    }

    public void paint(Graphics2D g2, boolean recreate)
    {
        if(recreate || parkingCircle == null)
        {
            int size = 0;
            if(radiusMeasure.equals("M"))
                size = (int)((double)radius * 2D * 3.2799999713897705D * (double)scale);
            else
                size = (int)((double)radius * 2D * (double)scale);
            point = Utilities.getPixelsBetweenPoints(centerPoint.getLat(), centerPoint.getLon(), latLon.getLat(), latLon.getLon(), scale);
            parkingCircle = new java.awt.geom.Ellipse2D.Float(point.x - (float)size / 2.0F, point.y - (float)size / 2.0F, size, size);
            if(type.equals("VEHICLE"))
            {
                java.awt.geom.Point2D.Float vehicleTopLeft = new java.awt.geom.Point2D.Float(point.x - (float)size / 8F, point.y - (float)size / 4F);
                java.awt.geom.Point2D.Float vehicleTopRight = new java.awt.geom.Point2D.Float(point.x + (float)size / 8F, point.y - (float)size / 4F);
                java.awt.geom.Point2D.Float vehicleBottomLeft = new java.awt.geom.Point2D.Float(point.x - (float)size / 8F, point.y + (float)size / 4F);
                java.awt.geom.Point2D.Float vehicleBottomRight = new java.awt.geom.Point2D.Float(point.x + (float)size / 8F, point.y + (float)size / 4F);
                java.awt.geom.Point2D.Float bumperLeft = new java.awt.geom.Point2D.Float(point.x - (float)size / 8F, point.y - ((float)size * 3F) / 32F);
                java.awt.geom.Point2D.Float bumperRight = new java.awt.geom.Point2D.Float(point.x + (float)size / 8F, point.y - ((float)size * 3F) / 32F);
                java.awt.geom.Point2D.Float wheel1Top = new java.awt.geom.Point2D.Float(point.x - ((float)size * 5F) / 32F, point.y - ((float)size * 3F) / 16F);
                java.awt.geom.Point2D.Float wheel1Bottom = new java.awt.geom.Point2D.Float(point.x - ((float)size * 5F) / 32F, point.y - (float)size / 16F);
                java.awt.geom.Point2D.Float wheel2Top = new java.awt.geom.Point2D.Float(point.x + ((float)size * 5F) / 32F, point.y - ((float)size * 3F) / 16F);
                java.awt.geom.Point2D.Float wheel2Bottom = new java.awt.geom.Point2D.Float(point.x + ((float)size * 5F) / 32F, point.y - (float)size / 16F);
                java.awt.geom.Point2D.Float wheel3Top = new java.awt.geom.Point2D.Float(point.x - ((float)size * 5F) / 32F, point.y + ((float)size * 3F) / 16F);
                java.awt.geom.Point2D.Float wheel3Bottom = new java.awt.geom.Point2D.Float(point.x - ((float)size * 5F) / 32F, point.y + (float)size / 16F);
                java.awt.geom.Point2D.Float wheel4Top = new java.awt.geom.Point2D.Float(point.x + ((float)size * 5F) / 32F, point.y + ((float)size * 3F) / 16F);
                java.awt.geom.Point2D.Float wheel4Bottom = new java.awt.geom.Point2D.Float(point.x + ((float)size * 5F) / 32F, point.y + (float)size / 16F);
                vehicleTopLeft = Utilities.rotatePoint(point, vehicleTopLeft, heading);
                vehicleTopRight = Utilities.rotatePoint(point, vehicleTopRight, heading);
                vehicleBottomLeft = Utilities.rotatePoint(point, vehicleBottomLeft, heading);
                vehicleBottomRight = Utilities.rotatePoint(point, vehicleBottomRight, heading);
                bumperLeft = Utilities.rotatePoint(point, bumperLeft, heading);
                bumperRight = Utilities.rotatePoint(point, bumperRight, heading);
                wheel1Top = Utilities.rotatePoint(point, wheel1Top, heading);
                wheel1Bottom = Utilities.rotatePoint(point, wheel1Bottom, heading);
                wheel2Top = Utilities.rotatePoint(point, wheel2Top, heading);
                wheel2Bottom = Utilities.rotatePoint(point, wheel2Bottom, heading);
                wheel3Top = Utilities.rotatePoint(point, wheel3Top, heading);
                wheel3Bottom = Utilities.rotatePoint(point, wheel3Bottom, heading);
                wheel4Top = Utilities.rotatePoint(point, wheel4Top, heading);
                wheel4Bottom = Utilities.rotatePoint(point, wheel4Bottom, heading);
                parkingImage = new GeneralPath();
                parkingImage.moveTo(vehicleTopLeft.x, vehicleTopLeft.y);
                parkingImage.lineTo(vehicleTopRight.x, vehicleTopRight.y);
                parkingImage.lineTo(vehicleBottomRight.x, vehicleBottomRight.y);
                parkingImage.lineTo(vehicleBottomLeft.x, vehicleBottomLeft.y);
                parkingImage.lineTo(vehicleTopLeft.x, vehicleTopLeft.y);
                parkingImage.moveTo(bumperLeft.x, bumperLeft.y);
                parkingImage.lineTo(bumperRight.x, bumperRight.y);
                parkingImage.moveTo(wheel1Top.x, wheel1Top.y);
                parkingImage.lineTo(wheel1Bottom.x, wheel1Bottom.y);
                parkingImage.moveTo(wheel2Top.x, wheel2Top.y);
                parkingImage.lineTo(wheel2Bottom.x, wheel2Bottom.y);
                parkingImage.moveTo(wheel3Top.x, wheel3Top.y);
                parkingImage.lineTo(wheel3Bottom.x, wheel3Bottom.y);
                parkingImage.moveTo(wheel4Top.x, wheel4Top.y);
                parkingImage.lineTo(wheel4Bottom.x, wheel4Bottom.y);
            } else
            {
                java.awt.geom.Point2D.Float planeCenter = new java.awt.geom.Point2D.Float(point.x, point.y - ((float)size * 3F) / 16F);
                java.awt.geom.Point2D.Float planeTop = new java.awt.geom.Point2D.Float(point.x, point.y - ((float)size * 3F) / 8F);
                java.awt.geom.Point2D.Float planeBottom = new java.awt.geom.Point2D.Float(point.x, point.y + (float)size / 4F);
                java.awt.geom.Point2D.Float planeLeft = new java.awt.geom.Point2D.Float(point.x - (float)size / 4F, point.y + (float)size / 16F);
                java.awt.geom.Point2D.Float planeRight = new java.awt.geom.Point2D.Float(point.x + (float)size / 4F, point.y + (float)size / 16F);
                planeCenter = Utilities.rotatePoint(point, planeCenter, heading);
                planeTop = Utilities.rotatePoint(point, planeTop, heading);
                planeBottom = Utilities.rotatePoint(point, planeBottom, heading);
                planeLeft = Utilities.rotatePoint(point, planeLeft, heading);
                planeRight = Utilities.rotatePoint(point, planeRight, heading);
                parkingImage = new GeneralPath();
                parkingImage.moveTo(planeTop.x, planeTop.y);
                parkingImage.lineTo(planeBottom.x, planeBottom.y);
                parkingImage.moveTo(planeLeft.x, planeLeft.y);
                parkingImage.lineTo(planeCenter.x, planeCenter.y);
                parkingImage.lineTo(planeRight.x, planeRight.y);
                if(type.equals("FUEL"))
                {
                    java.awt.geom.Point2D.Float fuelTopLeft = new java.awt.geom.Point2D.Float(point.x - scale * 20F, point.y - (float)size / 2.0F - scale * 20F);
                    java.awt.geom.Point2D.Float fuelTopRight = new java.awt.geom.Point2D.Float(point.x + scale * 20F, point.y - (float)size / 2.0F - scale * 20F);
                    java.awt.geom.Point2D.Float fuelBottomLeft = new java.awt.geom.Point2D.Float(point.x - scale * 20F, point.y - (float)size / 2.0F - scale * 5F);
                    java.awt.geom.Point2D.Float fuelBottomRight = new java.awt.geom.Point2D.Float(point.x + scale * 20F, point.y - (float)size / 2.0F - scale * 5F);
                    fuelTopLeft = Utilities.rotatePoint(point, fuelTopLeft, heading);
                    fuelTopRight = Utilities.rotatePoint(point, fuelTopRight, heading);
                    fuelBottomLeft = Utilities.rotatePoint(point, fuelBottomLeft, heading);
                    fuelBottomRight = Utilities.rotatePoint(point, fuelBottomRight, heading);
                    parkingImage.moveTo(fuelTopLeft.x, fuelTopLeft.y);
                    parkingImage.lineTo(fuelTopRight.x, fuelTopRight.y);
                    parkingImage.lineTo(fuelBottomRight.x, fuelBottomRight.y);
                    parkingImage.lineTo(fuelBottomLeft.x, fuelBottomLeft.y);
                    parkingImage.lineTo(fuelTopLeft.x, fuelTopLeft.y);
                }
            }
        }
        if(!parkingCircle.intersects(g2.getClipBounds()))
            return;
        g2.setPaint(parkingColor);
        g2.fill(parkingCircle);
        g2.setPaint(Color.green);
        if((double)scale > 0.5D)
        {
            g2.setStroke(new BasicStroke(2.0F));
            g2.draw(parkingCircle);
            if((double)scale >= 0.10000000000000001D)
                g2.draw(parkingImage);
            g2.setStroke(new BasicStroke(1.0F));
        } else
        {
            g2.draw(parkingCircle);
            if((double)scale >= 0.10000000000000001D)
                g2.draw(parkingImage);
        }
        if(currentlySelected)
            if(SettingsEngine.getInstance().isSelectedItemOutlined())
            {
                g2.setStroke(new BasicStroke(4F));
                g2.setPaint(ColorsEngine.getInstance().getSelectedColor());
                g2.draw(parkingCircle);
                g2.setStroke(new BasicStroke());
            } else
            {
                Color selectedColor = ColorsEngine.getInstance().getSelectedColor();
                g2.setPaint(new Color(selectedColor.getRed(), selectedColor.getGreen(), selectedColor.getBlue(), 196));
                g2.fill(parkingCircle);
            }
        int fontSize = (int)((double)((float)Utilities.PARKING_FONT.getSize() * scale) * 0.5D);
        if(fontSize >= 6)
        {
            g2.setPaint(Color.black);
            g2.setFont(new Font(Utilities.PARKING_FONT.getFamily(), Utilities.PARKING_FONT.getStyle(), fontSize));
            int fontHeight = g2.getFontMetrics().getHeight();
            g2.drawString(type, point.x, point.y);
            g2.drawString((new StringBuilder()).append(name).append(" [").append(number).append("]").toString(), point.x, point.y + (float)fontHeight);
            g2.drawString(airlineCodes, point.x, point.y + (float)(fontHeight * 2));
        }
    }

    public Ellipse2D getParkingCircle()
    {
        return parkingCircle;
    }

    public boolean isCopyable()
    {
        return true;
    }

    public void addTaxiwayLines(HashMap hashMap)
    {
        int i = taxiwayLineAL.size() - 1;
        do
        {
            if(i < 0)
                break;
            if(((String)((HashMap)taxiwayLineAL.get(i)).get("name")).equals((String)hashMap.get("name")))
            {
                taxiwayLineAL.remove(i);
                break;
            }
            i--;
        } while(true);
        taxiwayLineAL.add(hashMap);
    }

    public void clearTaxiwayLines()
    {
        taxiwayLineAL.clear();
    }

    public boolean moveTo(LatLonPoint latLonPoint, double centerX, double centerY)
    {
        setLatLon(latLonPoint);
        return true;
    }

    public boolean isWithinObject(int x, int y)
    {
        if(parkingCircle == null)
            return false;
        else
            return parkingCircle.contains(x, y);
    }

    public String getModelName()
    {
        return (new StringBuilder()).append(modelName).append(" [").append(name).append(" ").append(number).append("]").toString();
    }

    public String getBiasXMeasure()
    {
        return biasXMeasure;
    }

    public void setBiasXMeasure(String biasXMeasure)
    {
        if(shouldNotify && !this.biasXMeasure.equals(biasXMeasure))
            HistoryListModel.getInstance().addModel(new HistoryModel("setBiasXMeasure", "Bias X Measure", "", this, biasXMeasure, this.biasXMeasure));
        this.biasXMeasure = biasXMeasure;
    }

    public String getBiasZMeasure()
    {
        return biasZMeasure;
    }

    public void setBiasZMeasure(String biasZMeasure)
    {
        if(shouldNotify && !this.biasZMeasure.equals(biasZMeasure))
            HistoryListModel.getInstance().addModel(new HistoryModel("setBiasZMeasure", "Bias Z Measure", "", this, biasZMeasure, this.biasZMeasure));
        this.biasZMeasure = biasZMeasure;
    }

    public String getRadiusMeasure()
    {
        return radiusMeasure;
    }

    public void setRadiusMeasure(String radiusMeasure)
    {
        if(shouldNotify && !this.radiusMeasure.equals(radiusMeasure))
            HistoryListModel.getInstance().addModel(new HistoryModel("setRadiusMeasure", "Radius Measure", "", this, radiusMeasure, this.radiusMeasure));
        firePropertyChange("radiusMeasure", this.radiusMeasure, radiusMeasure);
        this.radiusMeasure = radiusMeasure;
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

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        if(shouldNotify && !this.name.equals(name))
            HistoryListModel.getInstance().addModel(new HistoryModel("setName", "Name", "", this, name, this.name));
        firePropertyChange("name", (new StringBuilder()).append(this.name).append("-").append(number).toString(), name);
        this.name = name;
    }

    public String getAirlineCodes()
    {
        return airlineCodes;
    }

    public void setAirlineCodes(String airlineCodes)
    {
        if(shouldNotify && !this.airlineCodes.equals(airlineCodes))
            HistoryListModel.getInstance().addModel(new HistoryModel("setAirlineCodes", "Airline Codes", "", this, airlineCodes, this.airlineCodes));
        firePropertyChange("airlineCodes", this.airlineCodes, airlineCodes);
        this.airlineCodes = airlineCodes;
        determineCodeColor();
    }

    private void determineCodeColor()
    {
        if(airlineCodes.length() == 0)
            return;
        String codes[] = airlineCodes.split(",");
        int i = AirlineCodeEngine.getInstance().getAirlineAL().size() - 1;
        do
        {
            if(i < 0)
                break;
            if(((String)((HashMap)AirlineCodeEngine.getInstance().getAirlineAL().get(i)).get("C")).equalsIgnoreCase(codes[0]))
            {
                parkingColor = new Color(((Integer)((HashMap)AirlineCodeEngine.getInstance().getAirlineAL().get(i)).get("color")).intValue());
                break;
            }
            i--;
        } while(true);
    }

    public String getPushBack()
    {
        return pushBack;
    }

    public void setPushBack(String pushBack)
    {
        if(shouldNotify && !this.pushBack.equals(pushBack))
            HistoryListModel.getInstance().addModel(new HistoryModel("setPushBack", "Push Back", "", this, pushBack, this.pushBack));
        firePropertyChange("pushBack", this.pushBack, pushBack);
        this.pushBack = pushBack;
    }

    public int getIndex()
    {
        return index;
    }

    public void setIndex(int index)
    {
        if(shouldNotify && this.index != index)
            HistoryListModel.getInstance().addModel(new HistoryModel("setIndex", "Index", "", this, new Integer(index), new Integer(this.index)));
        this.index = index;
    }

    public int getNumber()
    {
        return number;
    }

    public void setNumber(int number)
    {
        if(shouldNotify && this.number != number)
            HistoryListModel.getInstance().addModel(new HistoryModel("setNumber", "Number", "", this, new Integer(number), new Integer(this.number)));
        firePropertyChange("number", (new StringBuilder()).append(name).append("-").append(this.number).toString(), new Integer(number));
        this.number = number;
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

    public float getRadius()
    {
        return radius;
    }

    public void setRadius(float radius)
    {
        if(shouldNotify && this.radius != radius)
            HistoryListModel.getInstance().addModel(new HistoryModel("setRadius", "Radius", "", this, new Float(radius), new Float(this.radius)));
        firePropertyChange("radius", new Float(this.radius), new Float(radius));
        this.radius = radius;
    }

    public float getTeeOffset1()
    {
        return teeOffset1;
    }

    public void setTeeOffset1(float teeOffset1)
    {
        if(shouldNotify && this.teeOffset1 != teeOffset1)
            HistoryListModel.getInstance().addModel(new HistoryModel("setTeeOffset1", "Tee Offset 1", "", this, new Float(teeOffset1), new Float(this.teeOffset1)));
        firePropertyChange("teeOffset1", new Float(this.teeOffset1), new Float(teeOffset1));
        this.teeOffset1 = teeOffset1;
    }

    public float getTeeOffset2()
    {
        return teeOffset2;
    }

    public void setTeeOffset2(float teeOffset2)
    {
        if(shouldNotify && this.teeOffset2 != teeOffset2)
            HistoryListModel.getInstance().addModel(new HistoryModel("setTeeOffset2", "Tee Offset 2", "", this, new Float(teeOffset2), new Float(this.teeOffset2)));
        firePropertyChange("teeOffset2", new Float(this.teeOffset2), new Float(teeOffset2));
        this.teeOffset2 = teeOffset2;
    }

    public float getTeeOffset3()
    {
        return teeOffset3;
    }

    public void setTeeOffset3(float teeOffset3)
    {
        if(shouldNotify && this.teeOffset3 != teeOffset3)
            HistoryListModel.getInstance().addModel(new HistoryModel("setTeeOffset3", "Tee Offset 3", "", this, new Float(teeOffset3), new Float(this.teeOffset3)));
        firePropertyChange("teeOffset3", new Float(this.teeOffset3), new Float(teeOffset3));
        this.teeOffset3 = teeOffset3;
    }

    public float getTeeOffset4()
    {
        return teeOffset4;
    }

    public void setTeeOffset4(float teeOffset4)
    {
        if(shouldNotify && this.teeOffset4 != teeOffset4)
            HistoryListModel.getInstance().addModel(new HistoryModel("setTeeOffset4", "Tee offset 4", "", this, new Float(teeOffset4), new Float(this.teeOffset4)));
        firePropertyChange("teeOffset4", new Float(this.teeOffset4), new Float(teeOffset4));
        this.teeOffset4 = teeOffset4;
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

    public double getBiasX()
    {
        return biasX;
    }

    public void setBiasX(double biasX)
    {
        if(shouldNotify && this.biasX != biasX)
            HistoryListModel.getInstance().addModel(new HistoryModel("setBiasX", "Bias X", "", this, new Double(biasX), new Double(this.biasX)));
        this.biasX = biasX;
    }

    public double getBiasZ()
    {
        return biasZ;
    }

    public void setBiasZ(double biasZ)
    {
        if(shouldNotify && this.biasZ != biasZ)
            HistoryListModel.getInstance().addModel(new HistoryModel("setBiasZ", "Bias Z", "", this, new Double(biasZ), new Double(this.biasZ)));
        this.biasZ = biasZ;
    }

    public Object clone()
    {
        TaxiwayParkingModel model = new TaxiwayParkingModel();
        model.setShouldNotify(false);
        model.setLatLon((LatLonPoint)getLatLon().clone());
        model.setBiasXMeasure(getBiasXMeasure());
        model.setBiasZMeasure(getBiasZMeasure());
        model.setRadiusMeasure(getRadiusMeasure());
        model.setType(getType());
        model.setName(getName());
        model.setAirlineCodes(getAirlineCodes());
        model.setPushBack(getPushBack());
        model.setIndex(getIndex());
        model.setNumber(getNumber());
        model.setHeading(getHeading());
        model.setRadius(getRadius());
        model.setTeeOffset1(getTeeOffset1());
        model.setTeeOffset2(getTeeOffset2());
        model.setTeeOffset3(getTeeOffset3());
        model.setTeeOffset4(getTeeOffset4());
        model.setBiasX(getBiasX());
        model.setBiasZ(getBiasZ());
        model.setShouldNotify(true);
        return model;
    }

    private ArrayList taxiwayLineAL;
    private Ellipse2D parkingCircle;
    private GeneralPath parkingImage;
    private java.awt.geom.Point2D.Float point;
    private LatLonPoint latLon;
    private String biasXMeasure;
    private String biasZMeasure;
    private String radiusMeasure;
    private String type;
    private String name;
    private String airlineCodes;
    private String pushBack;
    private Color parkingColor;
    private int index;
    private int number;
    private float heading;
    private float radius;
    private float teeOffset1;
    private float teeOffset2;
    private float teeOffset3;
    private float teeOffset4;
    private double biasX;
    private double biasZ;
}
