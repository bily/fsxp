// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AirportModel.java

package com.zbluesoftware.fsxp.model;

import com.zbluesoftware.fsxp.engine.SettingsEngine;
import com.zbluesoftware.fsxp.util.LatLonPoint;
import com.zbluesoftware.fsxp.util.Utilities;
import java.awt.Graphics2D;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.PrintStream;
import java.util.*;

// Referenced classes of package com.zbluesoftware.fsxp.model:
//            BaseModel, DeleteModel, PlaneModel, RotationModel, 
//            BackgroundImageModel, RunwayModel, DMEModel, GlideSlopeModel, 
//            SceneryModel, ILSModel, TaxiwayPathModel, TaxiwayPointModel, 
//            TaxiwayParkingModel, ApronModel, BoundaryFenceModel, VertexModel, 
//            BlastFenceModel, ApronEdgeLightModel, VORModel, TriggerModel, 
//            HistoryModel, JetwayModel, NDBModel, TaxiwayPathDisplayModel, 
//            TaxiNameModel, HistoryListModel, TaxiwaySignModel, TowerModel, 
//            StartModel, HelipadModel, ComModel, ApproachModel, 
//            MarkerModel, WindsockModel, ExclusionModel

public class AirportModel extends BaseModel
    implements PropertyChangeListener
{

    public AirportModel(LatLonPoint latLon)
    {
        modelName = "Airport";
        runwayAL = new ArrayList();
        taxiwayPointHM = new HashMap();
        taxiwayParkingHM = new HashMap();
        taxiwaySignAL = new ArrayList();
        towerAL = new ArrayList();
        apronAL = new ArrayList();
        boundaryFenceAL = new ArrayList();
        blastFenceAL = new ArrayList();
        taxiwayPathAL = new ArrayList();
        startAL = new ArrayList();
        planeAL = new ArrayList();
        rotationAL = new ArrayList();
        helipadAL = new ArrayList();
        apronEdgeLightAL = new ArrayList();
        taxiNameAL = new ArrayList();
        jetwayAL = new ArrayList();
        comAL = new ArrayList();
        approachAL = new ArrayList();
        markerAL = new ArrayList();
        vorAL = new ArrayList();
        ndbAL = new ArrayList();
        unusedMarkerAL = new ArrayList();
        unusedVORAL = new ArrayList();
        unusedNDBAL = new ArrayList();
        taxiwayPathDisplayAL = new ArrayList();
        taxiwayPathDisplayHM = new HashMap();
        windsockAL = new ArrayList();
        unusedWindsockAL = new ArrayList();
        exclusionAL = new ArrayList();
        unusedExclusionAL = new ArrayList();
        triggerAL = new ArrayList();
        unusedTriggerAL = new ArrayList();
        sceneryAL = new ArrayList();
        unusedSceneryAL = new ArrayList();
        servicesAL = new ArrayList();
        deleteModel = new DeleteModel();
        fileName = "";
        region = "";
        country = "";
        state = "";
        city = "";
        name = "";
        ident = "";
        trafficScalar = 0.7F;
        altMeasure = "M";
        airportTestRadiusMeasure = "M";
        alt = 0.0D;
        magvar = 0.0F;
        airportTestRadius = 5000D;
        maxTaxiIndex = 0;
        maxTaxiNameIndex = 0;
        blankTaxiNameIndex = 0;
        highlightedTWPath = 0;
        bgImageAL = new ArrayList();
        this.latLon = (LatLonPoint)latLon.clone();
        PlaneModel planeModel = new PlaneModel();
        planeModel.setLatLon((LatLonPoint)latLon.clone());
        planeAL.add(planeModel);
        rotationAL.add(new RotationModel());
    }

    public void paint(Graphics2D graphics2d, boolean flag)
    {
    }

    public int getHighlightedTWPath()
    {
        return highlightedTWPath;
    }

    public void setHighlightedTWPath(int highlightedTWPath)
    {
        this.highlightedTWPath = highlightedTWPath;
    }

    public ArrayList getServicesAL()
    {
        return servicesAL;
    }

    public void setServicesAL(ArrayList servicesAL)
    {
        this.servicesAL = servicesAL;
    }

    public ArrayList getBGImageAL()
    {
        return bgImageAL;
    }

    public void setBGImageAL(ArrayList bgImageAL)
    {
        this.bgImageAL = bgImageAL;
    }

    public boolean addBackgroundImage(BufferedImage image, String path, boolean imageVisible, LatLonPoint topLeft, LatLonPoint bottomRight)
    {
        for(int i = bgImageAL.size() - 1; i >= 0; i--)
            if(((BackgroundImageModel)bgImageAL.get(i)).getPath().equals(path))
                return false;

        BackgroundImageModel backgroundImageModel = new BackgroundImageModel(image, path, topLeft, bottomRight);
        backgroundImageModel.setImageVisible(imageVisible);
        bgImageAL.add(backgroundImageModel);
        return true;
    }

    public ArrayList getRunwayAL()
    {
        return runwayAL;
    }

    public void addRunwayModel(RunwayModel runwayModel)
    {
        if(!runwayAL.contains(runwayModel))
        {
            runwayAL.add(runwayModel);
            runwayModel.addPropertyChangeListener(this);
            firePropertyChange("RunwayAdded", new Integer(runwayAL.size() - 1), new Integer(runwayAL.size()));
        }
    }

    public void removeRunwayModel(RunwayModel runwayModel)
    {
        runwayAL.remove(runwayModel);
        runwayModel.removePropertyChangeListener(this);
        firePropertyChange("RunwayRemoved", new Integer(runwayAL.size() + 1), new Integer(runwayAL.size()));
    }

    public ArrayList getILSModels()
    {
        ArrayList arrayList = new ArrayList();
        for(int i = runwayAL.size() - 1; i >= 0; i--)
            arrayList.addAll(((RunwayModel)runwayAL.get(i)).getILSAL());

        return arrayList;
    }

    public void removeILSModel(ILSModel ilsModel)
    {
        for(int i = runwayAL.size() - 1; i >= 0; i--)
        {
            ArrayList arrayList = ((RunwayModel)runwayAL.get(i)).getILSAL();
            if(arrayList.contains(ilsModel))
            {
                ((RunwayModel)runwayAL.get(i)).removeILSModel(ilsModel);
                return;
            }
        }

    }

    public void addILSModel(ILSModel ilsModel)
    {
        int i = runwayAL.size() - 1;
        if(i >= 0)
        {
            RunwayModel runwayModel = (RunwayModel)runwayAL.get(i);
            String runwayName = runwayModel.getNumber();
            if(runwayModel.getDesignator().length() > 0 && !runwayModel.getDesignator().equals("NONE"))
                runwayName = (new StringBuilder()).append(runwayName).append(" ").append(runwayModel.getDesignator()).toString();
            else
            if(runwayModel.getPrimaryDesignator().length() > 0 && !runwayModel.getPrimaryDesignator().equals("NONE"))
                runwayName = (new StringBuilder()).append(runwayName).append(" ").append(runwayModel.getPrimaryDesignator()).toString();
            ilsModel.setRunway(runwayName);
            runwayModel.addILSModel(ilsModel);
            return;
        } else
        {
            return;
        }
    }

    public void addILSModelToRunway(ILSModel ilsModel, RunwayModel runwayModel, boolean primaryEnd)
    {
        ilsModel.setShouldNotify(false);
        int sizeLength = 0;
        if(runwayModel.getLengthMeasure().equals("M"))
            sizeLength = (int)((runwayModel.getLength() * 3.28F + 2000F) * runwayModel.getScale());
        else
            sizeLength = (int)((runwayModel.getLength() + 2000F) * runwayModel.getScale());
        java.awt.geom.Point2D.Float runwayCP = Utilities.getPixelsBetweenPoints(runwayModel.getCenterPoint().getLat(), runwayModel.getCenterPoint().getLon(), runwayModel.getLatLon().getLat(), runwayModel.getLatLon().getLon(), runwayModel.getScale());
        java.awt.geom.Point2D.Float ilsPoint = new java.awt.geom.Point2D.Float((float)runwayCP.getX(), (float)(runwayCP.getY() - ((double)sizeLength / 2D) * (double)(primaryEnd ? 1 : -1)));
        ilsPoint = Utilities.rotatePoint(runwayCP, ilsPoint, runwayModel.getHeading());
        ilsModel.setLatLon(Utilities.getLatLonForPixel(runwayModel.getCenterPoint().getLat(), runwayModel.getCenterPoint().getLon(), ilsPoint.x, ilsPoint.y, runwayModel.getScale()));
        if(primaryEnd)
            ilsModel.setEnd("PRIMARY");
        else
            ilsModel.setEnd("SECONDARY");
        ilsModel.setAlt(runwayModel.getAlt());
        ilsModel.setAltMeasure(runwayModel.getAltMeasure());
        if(primaryEnd)
        {
            ilsModel.setHeading(runwayModel.getHeading());
        } else
        {
            float ilsHeading = runwayModel.getHeading() + 180F;
            if(ilsHeading >= 360F)
                ilsHeading -= 360F;
            ilsModel.setHeading(ilsHeading);
        }
        DMEModel dmeModel = new DMEModel();
        dmeModel.setShouldNotify(false);
        dmeModel.setLatLon((LatLonPoint)ilsModel.getLatLon().clone());
        dmeModel.setAlt(ilsModel.getAlt());
        dmeModel.setAltMeasure(ilsModel.getAltMeasure());
        dmeModel.setShouldNotify(true);
        ilsModel.setDMEModel(dmeModel);
        GlideSlopeModel glideSlopeModel = new GlideSlopeModel();
        glideSlopeModel.setShouldNotify(false);
        glideSlopeModel.setAlt(ilsModel.getAlt());
        glideSlopeModel.setAltMeasure(ilsModel.getAltMeasure());
        ilsModel.setGlideSlopeModel(glideSlopeModel);
        if(runwayModel.getLengthMeasure().equals("M"))
            sizeLength = (int)((runwayModel.getLength() * 3.28F - 2000F) * runwayModel.getScale());
        else
            sizeLength = (int)((runwayModel.getLength() - 2000F) * runwayModel.getScale());
        java.awt.geom.Point2D.Float glideSlopePoint = new java.awt.geom.Point2D.Float((float)runwayCP.getX() - 350F * runwayModel.getScale() * (float)(primaryEnd ? 1 : -1), (float)(runwayCP.getY() - ((double)sizeLength / 2D) * (double)(primaryEnd ? -1 : 1)));
        glideSlopePoint = Utilities.rotatePoint(runwayCP, glideSlopePoint, runwayModel.getHeading());
        glideSlopeModel.setLatLon(Utilities.getLatLonForPixel(runwayModel.getCenterPoint().getLat(), runwayModel.getCenterPoint().getLon(), glideSlopePoint.x, glideSlopePoint.y, runwayModel.getScale()));
        glideSlopeModel.setShouldNotify(true);
        String runwayName = runwayModel.getNumber();
        if(runwayModel.getDesignator().length() > 0 && !runwayModel.getDesignator().equals("NONE"))
            runwayName = (new StringBuilder()).append(runwayName).append(" ").append(runwayModel.getDesignator()).toString();
        else
        if(runwayModel.getPrimaryDesignator().length() > 0 && !runwayModel.getPrimaryDesignator().equals("NONE"))
            runwayName = (new StringBuilder()).append(runwayName).append(" ").append(runwayModel.getPrimaryDesignator()).toString();
        ilsModel.setRunway(runwayName);
        runwayModel.addILSModel(ilsModel);
        ilsModel.setShouldNotify(true);
    }

    private void addILSModelToRunway(ILSModel ilsModel, String runway)
    {
        for(int i = runwayAL.size() - 1; i >= 0; i--)
        {
            RunwayModel runwayModel = (RunwayModel)runwayAL.get(i);
            String runwayName = runwayModel.getNumber();
            if(runwayModel.getDesignator().length() > 0 && !runwayModel.getDesignator().equals("NONE"))
                runwayName = (new StringBuilder()).append(runwayName).append(" ").append(runwayModel.getDesignator()).toString();
            else
            if(runwayModel.getPrimaryDesignator().length() > 0 && !runwayModel.getPrimaryDesignator().equals("NONE"))
                runwayName = (new StringBuilder()).append(runwayName).append(" ").append(runwayModel.getPrimaryDesignator()).toString();
            if(runway.equals(runwayName))
            {
                runwayModel.addILSModel(ilsModel);
                return;
            }
        }

    }

    public void addLocalizerToILS(ILSModel model)
    {
        SceneryModel sceneryModel = new SceneryModel();
        sceneryModel.setShouldNotify(false);
        sceneryModel.setName("{fe978b1b-6b2f-4898-9e5d-a008e8675ed4}");
        sceneryModel.setHeading(model.getHeading());
        sceneryModel.setLatLon((LatLonPoint)model.getLatLon().clone());
        sceneryModel.setShouldNotify(true);
        addSceneryModel(sceneryModel);
    }

    public ArrayList getILSGSModels()
    {
        ArrayList arrayList = new ArrayList();
        for(int i = runwayAL.size() - 1; i >= 0; i--)
        {
            RunwayModel runwayModel = (RunwayModel)runwayAL.get(i);
            for(int j = runwayModel.getILSAL().size() - 1; j >= 0; j--)
                if(((ILSModel)runwayModel.getILSAL().get(j)).getGlideSlopeModel() != null)
                    arrayList.add(((ILSModel)runwayModel.getILSAL().get(j)).getGlideSlopeModel());

        }

        return arrayList;
    }

    public ArrayList getILSDMEModels()
    {
        ArrayList arrayList = new ArrayList();
        for(int i = runwayAL.size() - 1; i >= 0; i--)
        {
            RunwayModel runwayModel = (RunwayModel)runwayAL.get(i);
            for(int j = runwayModel.getILSAL().size() - 1; j >= 0; j--)
                if(((ILSModel)runwayModel.getILSAL().get(j)).getDMEModel() != null)
                    arrayList.add(((ILSModel)runwayModel.getILSAL().get(j)).getDMEModel());

        }

        return arrayList;
    }

    public HashMap getTaxiwayPointHM()
    {
        return taxiwayPointHM;
    }

    public void addTaxiwayPointModel(TaxiwayPointModel taxiwayPointModel)
    {
        taxiwayPointHM.put(new Integer(taxiwayPointModel.getIndex()), taxiwayPointModel);
    }

    public void removeTaxiwayPointModel(TaxiwayPointModel taxiwayPointModel)
    {
        taxiwayPointHM.remove(new Integer(taxiwayPointModel.getIndex()));
        for(int i = taxiwayPathAL.size() - 1; i >= 0; i--)
        {
            if(((TaxiwayPathModel)taxiwayPathAL.get(i)).getType().equals("PARKING") && ((TaxiwayPathModel)taxiwayPathAL.get(i)).getStart() == taxiwayPointModel.getIndex())
            {
                taxiwayPathAL.remove(i);
                continue;
            }
            if(!((TaxiwayPathModel)taxiwayPathAL.get(i)).getType().equals("PARKING") && (((TaxiwayPathModel)taxiwayPathAL.get(i)).getStart() == taxiwayPointModel.getIndex() || ((TaxiwayPathModel)taxiwayPathAL.get(i)).getEnd() == taxiwayPointModel.getIndex()))
                taxiwayPathAL.remove(i);
        }

    }

    public int cleanUpTaxiwayPoints()
    {
        ArrayList arrayList = new ArrayList();
        Iterator iterator = taxiwayPointHM.keySet().iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            int index = ((Integer)iterator.next()).intValue();
            boolean found = false;
            int i = taxiwayPathAL.size() - 1;
            do
            {
                if(i < 0)
                    break;
                TaxiwayPathModel taxiwayPathModel = (TaxiwayPathModel)taxiwayPathAL.get(i);
                if(taxiwayPathModel.getStart() == index || !taxiwayPathModel.getType().equals("PARKING") && taxiwayPathModel.getEnd() == index)
                {
                    found = true;
                    break;
                }
                i--;
            } while(true);
            if(!found)
                arrayList.add(taxiwayPointHM.get(new Integer(index)));
        } while(true);
        int totalDeleted = arrayList.size();
        for(int i = arrayList.size() - 1; i >= 0; i--)
            removeTaxiwayPointModel((TaxiwayPointModel)arrayList.get(i));

        return totalDeleted;
    }

    public HashMap getTaxiwayParkingHM()
    {
        return taxiwayParkingHM;
    }

    public void addTaxiwayParkingModel(TaxiwayParkingModel taxiwayParkingModel)
    {
        taxiwayParkingModel.addPropertyChangeListener(this);
        taxiwayParkingHM.put(new Integer(taxiwayParkingModel.getIndex()), taxiwayParkingModel);
    }

    public void removeTaxiwayParkingModel(TaxiwayParkingModel taxiwayParkingModel)
    {
        taxiwayParkingModel.removePropertyChangeListener(this);
        taxiwayParkingHM.remove(new Integer(taxiwayParkingModel.getIndex()));
        for(int i = taxiwayPathAL.size() - 1; i >= 0; i--)
            if(((TaxiwayPathModel)taxiwayPathAL.get(i)).getType().equals("PARKING") && ((TaxiwayPathModel)taxiwayPathAL.get(i)).getEnd() == taxiwayParkingModel.getIndex())
                taxiwayPathAL.remove(i);

    }

    public int cleanUpTaxiwayParking()
    {
        ArrayList arrayList = new ArrayList();
        Iterator iterator = taxiwayParkingHM.keySet().iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            int index = ((Integer)iterator.next()).intValue();
            boolean found = false;
            int i = taxiwayPathAL.size() - 1;
            do
            {
                if(i < 0)
                    break;
                TaxiwayPathModel taxiwayPathModel = (TaxiwayPathModel)taxiwayPathAL.get(i);
                if(taxiwayPathModel.getType().equals("PARKING") && taxiwayPathModel.getEnd() == index)
                {
                    found = true;
                    break;
                }
                i--;
            } while(true);
            if(!found)
                arrayList.add(taxiwayParkingHM.get(new Integer(index)));
        } while(true);
        int totalDeleted = arrayList.size();
        for(int i = arrayList.size() - 1; i >= 0; i--)
            removeTaxiwayParkingModel((TaxiwayParkingModel)arrayList.get(i));

        return totalDeleted;
    }

    public ArrayList getTaxiwaySignAL()
    {
        return taxiwaySignAL;
    }

    public void addTaxiwaySignModel(TaxiwaySignModel taxiwaySignModel)
    {
        if(!taxiwaySignAL.contains(taxiwaySignModel))
            taxiwaySignAL.add(taxiwaySignModel);
    }

    public void removeTaxiwaySignModel(TaxiwaySignModel taxiwaySignModel)
    {
        taxiwaySignAL.remove(taxiwaySignModel);
    }

    public ArrayList getTowerAL()
    {
        return towerAL;
    }

    public void addTowerModel(TowerModel towerModel)
    {
        if(!towerAL.contains(towerModel))
            towerAL.add(towerModel);
    }

    public void removeTowerModel(TowerModel towerModel)
    {
        towerAL.remove(towerModel);
    }

    public ArrayList getApronAL()
    {
        return apronAL;
    }

    public void addApronModel(ApronModel apronModel)
    {
        if(!apronAL.contains(apronModel))
            apronAL.add(apronModel);
    }

    public void removeApronModel(ApronModel apronModel)
    {
        apronAL.remove(apronModel);
    }

    public int cleanUpApronModels()
    {
        int totalDeleted = 0;
        for(int i = apronAL.size() - 1; i >= 0; i--)
            if(((ApronModel)apronAL.get(i)).getVertexAL().size() <= 2)
            {
                apronAL.remove(i);
                totalDeleted++;
            }

        return totalDeleted;
    }

    public ArrayList getBoundaryFenceAL()
    {
        return boundaryFenceAL;
    }

    public void addBoundaryFenceModel(BoundaryFenceModel boundaryFenceModel)
    {
        if(!boundaryFenceAL.contains(boundaryFenceModel))
            boundaryFenceAL.add(boundaryFenceModel);
    }

    public void removeBoundaryFenceModel(BoundaryFenceModel boundaryFenceModel)
    {
        boundaryFenceAL.remove(boundaryFenceModel);
    }

    public void cutBoundaryFenceModel(BoundaryFenceModel boundaryFenceModel, int vertex)
    {
        BoundaryFenceModel model2 = (BoundaryFenceModel)boundaryFenceModel.clone();
        for(int i = 0; i <= vertex; i++)
            model2.removeVertexModel((VertexModel)model2.getVertexAL().get(0));

        for(int i = boundaryFenceModel.getVertexAL().size() - 1; i > vertex; i--)
            boundaryFenceModel.removeVertexModel((VertexModel)boundaryFenceModel.getVertexAL().get(vertex + 1));

        addBoundaryFenceModel(model2);
    }

    public void mergeBoundaryFences(BoundaryFenceModel model1, BoundaryFenceModel model2, VertexModel vertexModel1, VertexModel vertexModel2)
    {
        boolean insert = model1.getVertexAL().get(0) == vertexModel1;
        ArrayList arrayList = model2.getVertexAL();
        if(vertexModel2 == arrayList.get(0))
        {
            for(int i = 1; i < arrayList.size(); i++)
                if(insert)
                    model1.insertVertexModel((VertexModel)((VertexModel)arrayList.get(i)).clone(), 0);
                else
                    model1.addVertexModel((VertexModel)((VertexModel)arrayList.get(i)).clone());

        } else
        {
            for(int i = arrayList.size() - 2; i >= 0; i--)
                if(insert)
                    model1.insertVertexModel((VertexModel)((VertexModel)arrayList.get(i)).clone(), 0);
                else
                    model1.addVertexModel((VertexModel)((VertexModel)arrayList.get(i)).clone());

        }
        removeBoundaryFenceModel(model2);
    }

    public int cleanUpBoundaryFenceModels()
    {
        int totalDeleted = 0;
        for(int i = boundaryFenceAL.size() - 1; i >= 0; i--)
            if(((BoundaryFenceModel)boundaryFenceAL.get(i)).getVertexAL().size() <= 1)
            {
                boundaryFenceAL.remove(i);
                totalDeleted++;
            }

        return totalDeleted;
    }

    public ArrayList getBlastFenceAL()
    {
        return blastFenceAL;
    }

    public void addBlastFenceModel(BlastFenceModel blastFenceModel)
    {
        if(!blastFenceAL.contains(blastFenceModel))
            blastFenceAL.add(blastFenceModel);
    }

    public void removeBlastFenceModel(BlastFenceModel blastFenceModel)
    {
        blastFenceAL.remove(blastFenceModel);
    }

    public void cutBlastFenceModel(BlastFenceModel blastFenceModel, int vertex)
    {
        BlastFenceModel model2 = (BlastFenceModel)blastFenceModel.clone();
        for(int i = 0; i <= vertex; i++)
            model2.removeVertexModel((VertexModel)model2.getVertexAL().get(0));

        for(int i = blastFenceModel.getVertexAL().size() - 1; i > vertex; i--)
            blastFenceModel.removeVertexModel((VertexModel)blastFenceModel.getVertexAL().get(vertex + 1));

        addBlastFenceModel(model2);
    }

    public void mergeBlastFences(BlastFenceModel model1, BlastFenceModel model2, VertexModel vertexModel1, VertexModel vertexModel2)
    {
        boolean insert = model1.getVertexAL().get(0) == vertexModel1;
        ArrayList arrayList = model2.getVertexAL();
        if(vertexModel2 == arrayList.get(0))
        {
            for(int i = 1; i < arrayList.size(); i++)
                if(insert)
                    model1.insertVertexModel((VertexModel)((VertexModel)arrayList.get(i)).clone(), 0);
                else
                    model1.addVertexModel((VertexModel)((VertexModel)arrayList.get(i)).clone());

        } else
        {
            for(int i = arrayList.size() - 2; i >= 0; i--)
                if(insert)
                    model1.insertVertexModel((VertexModel)((VertexModel)arrayList.get(i)).clone(), 0);
                else
                    model1.addVertexModel((VertexModel)((VertexModel)arrayList.get(i)).clone());

        }
        removeBlastFenceModel(model2);
    }

    public int cleanUpBlastFenceModels()
    {
        int totalDeleted = 0;
        for(int i = blastFenceAL.size() - 1; i >= 0; i--)
            if(((BlastFenceModel)blastFenceAL.get(i)).getVertexAL().size() <= 1)
            {
                blastFenceAL.remove(i);
                totalDeleted++;
            }

        return totalDeleted;
    }

    public ArrayList getTaxiwayPathAL()
    {
        return taxiwayPathAL;
    }

    public void addTaxiwayPathModel(TaxiwayPathModel taxiwayPathModel)
    {
        if(!taxiwayPathAL.contains(taxiwayPathModel))
        {
            taxiwayPathAL.add(taxiwayPathModel);
            taxiwayPathModel.setParentModel(this);
        }
    }

    public void removeTaxiwayPathModel(TaxiwayPathModel taxiwayPathModel)
    {
        taxiwayPathAL.remove(taxiwayPathModel);
        taxiwayPathModel.setParentModel(null);
    }

    public int cleanUpTaxiways()
    {
        int totalDeleted = 0;
        for(int i = taxiwayPathAL.size() - 1; i >= 0; i--)
            if(((TaxiwayPathModel)taxiwayPathAL.get(i)).getStart() == ((TaxiwayPathModel)taxiwayPathAL.get(i)).getEnd())
            {
                taxiwayPathAL.remove(i);
                totalDeleted++;
            }

        for(int i = taxiwayPathAL.size() - 1; i >= 0; i--)
        {
            TaxiwayPathModel model = (TaxiwayPathModel)taxiwayPathAL.get(i);
            if(model.getType().equals("PARKING"))
            {
                if(!taxiwayParkingHM.containsKey(new Integer(model.getStart())) && !taxiwayPointHM.containsKey(new Integer(model.getEnd())) && !taxiwayParkingHM.containsKey(new Integer(model.getEnd())) && !taxiwayPointHM.containsKey(new Integer(model.getStart())))
                {
                    taxiwayPathAL.remove(i);
                    totalDeleted++;
                }
                continue;
            }
            if(!taxiwayPointHM.containsKey(new Integer(model.getStart())))
            {
                taxiwayPathAL.remove(i);
                totalDeleted++;
            }
            if(!taxiwayPointHM.containsKey(new Integer(model.getEnd())))
            {
                taxiwayPathAL.remove(i);
                totalDeleted++;
            }
        }

        return totalDeleted;
    }

    public ArrayList getStartAL()
    {
        return startAL;
    }

    public void addStartModel(StartModel startModel)
    {
        if(!startAL.contains(startModel))
            startAL.add(startModel);
    }

    public void removeStartModel(StartModel startModel)
    {
        startAL.remove(startModel);
    }

    public ArrayList getPlaneAL()
    {
        return planeAL;
    }

    public ArrayList getRotationAL()
    {
        return rotationAL;
    }

    public ArrayList getHelipadAL()
    {
        return helipadAL;
    }

    public void addHelipadModel(HelipadModel helipadModel)
    {
        if(!helipadAL.contains(helipadModel))
            helipadAL.add(helipadModel);
    }

    public void removeHelipadModel(HelipadModel helipadModel)
    {
        helipadAL.remove(helipadModel);
    }

    public ArrayList getApronEdgeLightAL()
    {
        return apronEdgeLightAL;
    }

    public void addApronEdgeLightModel(ApronEdgeLightModel apronEdgeLightModel)
    {
        if(!apronEdgeLightAL.contains(apronEdgeLightModel))
            apronEdgeLightAL.add(apronEdgeLightModel);
    }

    public void removeApronEdgeLightModel(ApronEdgeLightModel apronEdgeLightModel)
    {
        apronEdgeLightAL.remove(apronEdgeLightModel);
    }

    public int cleanUpApronEdgeLightModels()
    {
        int totalDeleted = 0;
        for(int i = apronEdgeLightAL.size() - 1; i >= 0; i--)
            if(((ApronEdgeLightModel)apronEdgeLightAL.get(i)).getVertexAL().size() <= 1)
            {
                apronEdgeLightAL.remove(i);
                totalDeleted++;
            }

        return totalDeleted;
    }

    public void cutApronEdgeLightModel(ApronEdgeLightModel apronEdgeLightModel, int vertex)
    {
        ApronEdgeLightModel model2 = (ApronEdgeLightModel)apronEdgeLightModel.clone();
        for(int i = 0; i <= vertex; i++)
            model2.removeVertexModel((VertexModel)model2.getVertexAL().get(0));

        for(int i = apronEdgeLightModel.getVertexAL().size() - 1; i > vertex; i--)
            apronEdgeLightModel.removeVertexModel((VertexModel)apronEdgeLightModel.getVertexAL().get(vertex + 1));

        addApronEdgeLightModel(model2);
    }

    public void mergeApronEdgeLights(ApronEdgeLightModel model1, ApronEdgeLightModel model2, VertexModel vertexModel1, VertexModel vertexModel2)
    {
        boolean insert = model1.getVertexAL().get(0) == vertexModel1;
        ArrayList arrayList = model2.getVertexAL();
        if(vertexModel2 == arrayList.get(0))
        {
            for(int i = 1; i < arrayList.size(); i++)
                if(insert)
                    model1.insertVertexModel((VertexModel)((VertexModel)arrayList.get(i)).clone(), 0);
                else
                    model1.addVertexModel((VertexModel)((VertexModel)arrayList.get(i)).clone());

        } else
        {
            for(int i = arrayList.size() - 2; i >= 0; i--)
                if(insert)
                    model1.insertVertexModel((VertexModel)((VertexModel)arrayList.get(i)).clone(), 0);
                else
                    model1.addVertexModel((VertexModel)((VertexModel)arrayList.get(i)).clone());

        }
        removeApronEdgeLightModel(model2);
    }

    public ArrayList getJetwayAL()
    {
        return jetwayAL;
    }

    public void addJetwayModel(JetwayModel jetwayModel)
    {
        if(!jetwayAL.contains(jetwayModel))
            jetwayAL.add(jetwayModel);
    }

    public void removeJetwayModel(JetwayModel jetwayModel)
    {
        jetwayAL.remove(jetwayModel);
    }

    public ArrayList getComAL()
    {
        return comAL;
    }

    public void addComModel(ComModel comModel)
    {
        if(!comAL.contains(comModel))
            comAL.add(comModel);
    }

    public void removeComModel(ComModel comModel)
    {
        comAL.remove(comModel);
    }

    public ArrayList getApproachAL()
    {
        return approachAL;
    }

    public void addApproachModel(ApproachModel approachModel)
    {
        if(!approachAL.contains(approachModel))
            approachAL.add(approachModel);
    }

    public void removeApproachModel(ApproachModel approachModel)
    {
        approachAL.remove(approachModel);
    }

    public ArrayList getMarkerAL()
    {
        return markerAL;
    }

    public void addMarkerModel(MarkerModel markerModel)
    {
        if(!markerAL.contains(markerModel))
            markerAL.add(markerModel);
    }

    public void removeMarkerModel(MarkerModel markerModel)
    {
        markerAL.remove(markerModel);
    }

    public ArrayList getUnusedMarkerAL()
    {
        return unusedMarkerAL;
    }

    public void addUnusedMarkerModel(MarkerModel markerModel)
    {
        if(!unusedMarkerAL.contains(markerModel))
            unusedMarkerAL.add(markerModel);
    }

    public ArrayList getVORAL()
    {
        return vorAL;
    }

    public void addVORModel(VORModel vorModel)
    {
        if(!vorAL.contains(vorModel))
            vorAL.add(vorModel);
    }

    public void removeVORModel(VORModel vorModel)
    {
        vorAL.remove(vorModel);
    }

    public void addStationToVOR(VORModel model)
    {
        SceneryModel sceneryModel = new SceneryModel();
        sceneryModel.setShouldNotify(false);
        if(model.getDmeOnly())
            sceneryModel.setName("{ffbf01ac-2d33-4444-869a-0e1e48cdf2c3}");
        else
        if(model.getDme())
            sceneryModel.setName("{3a5affe1-5cb6-43e9-b5d3-00dfc3b86e78}");
        else
            sceneryModel.setName("{ffbf01ac-2d33-4444-869a-0e1e48cdf2c3}");
        sceneryModel.setLatLon((LatLonPoint)model.getLatLon().clone());
        sceneryModel.setShouldNotify(true);
        System.out.println("test");
        addSceneryModel(sceneryModel);
    }

    public ArrayList getUnusedVORAL()
    {
        return unusedVORAL;
    }

    public void addUnusedVORModel(VORModel vorModel)
    {
        if(!unusedVORAL.contains(vorModel))
            unusedVORAL.add(vorModel);
    }

    public ArrayList getNDBAL()
    {
        return ndbAL;
    }

    public void addNDBModel(NDBModel ndbModel)
    {
        if(!ndbAL.contains(ndbModel))
            ndbAL.add(ndbModel);
    }

    public void removeNDBModel(NDBModel ndbModel)
    {
        ndbAL.remove(ndbModel);
    }

    public void addAntennaToNDB(NDBModel model)
    {
        SceneryModel sceneryModel = new SceneryModel();
        sceneryModel.setShouldNotify(false);
        sceneryModel.setName("{5911c52a-dd21-4673-a3c7-c95c922e5aaf}");
        sceneryModel.setLatLon((LatLonPoint)model.getLatLon().clone());
        sceneryModel.setShouldNotify(true);
        addSceneryModel(sceneryModel);
    }

    public ArrayList getUnusedNDBAL()
    {
        return unusedNDBAL;
    }

    public void addUnusedNDBModel(NDBModel ndbModel)
    {
        if(!unusedNDBAL.contains(ndbModel))
            unusedNDBAL.add(ndbModel);
    }

    public ArrayList getVORdmeModels()
    {
        ArrayList arrayList = new ArrayList();
        for(int i = vorAL.size() - 1; i >= 0; i--)
        {
            VORModel vorModel = (VORModel)vorAL.get(i);
            if(vorModel.getDme())
                arrayList.add(vorModel.getDMEModel());
        }

        return arrayList;
    }

    public ArrayList getTaxiNameAL()
    {
        return taxiNameAL;
    }

    public void addTaxiNameModel(TaxiNameModel taxiNameModel)
    {
        if(!taxiNameAL.contains(taxiNameModel))
        {
            taxiNameAL.add(taxiNameModel);
            firePropertyChange("taxiNames", "", taxiNameAL);
        }
    }

    public void clearTaxiwayPathDisplayModels()
    {
        taxiwayPathDisplayHM.clear();
        taxiwayPathDisplayAL.clear();
    }

    public void addTaxiwayPathDisplayModel(TaxiwayPathDisplayModel taxiwayPathDisplayModel, boolean parking)
    {
        if(!taxiwayPathDisplayModel.isDisplayed())
            return;
        Integer start = new Integer(taxiwayPathDisplayModel.getStart());
        Integer end = new Integer(taxiwayPathDisplayModel.getEnd());
        if(!parking)
            if(taxiwayPathDisplayHM.containsKey(end))
            {
                ((ArrayList)taxiwayPathDisplayHM.get(end)).add(taxiwayPathDisplayModel);
            } else
            {
                ArrayList arrayList = new ArrayList();
                arrayList.add(taxiwayPathDisplayModel);
                taxiwayPathDisplayHM.put(end, arrayList);
            }
        if(taxiwayPathDisplayHM.containsKey(start))
        {
            ((ArrayList)taxiwayPathDisplayHM.get(start)).add(taxiwayPathDisplayModel);
        } else
        {
            ArrayList arrayList = new ArrayList();
            arrayList.add(taxiwayPathDisplayModel);
            taxiwayPathDisplayHM.put(start, arrayList);
        }
        taxiwayPathDisplayAL.add(taxiwayPathDisplayModel);
    }

    public ArrayList getTaxiwayPathDisplayAL()
    {
        return taxiwayPathDisplayAL;
    }

    public HashMap getTaxiwayPathDisplayHM()
    {
        return taxiwayPathDisplayHM;
    }

    public void removeTaxiNameModel(TaxiNameModel taxiNameModel)
    {
        taxiNameAL.remove(taxiNameModel);
        for(int i = taxiwayPathAL.size() - 1; i >= 0; i--)
            if(((TaxiwayPathModel)taxiwayPathAL.get(i)).getName() == taxiNameModel.getIndex())
                ((TaxiwayPathModel)taxiwayPathAL.get(i)).setName(0);

        firePropertyChange("taxiNames", "", taxiNameAL);
    }

    public ArrayList getWindsockAL()
    {
        return windsockAL;
    }

    public void addWindsockModel(WindsockModel windsockModel)
    {
        if(!windsockAL.contains(windsockModel))
            windsockAL.add(windsockModel);
    }

    public void removeWindsockModel(WindsockModel windsockModel)
    {
        windsockAL.remove(windsockModel);
    }

    public ArrayList getUnusedWindsockAL()
    {
        return unusedWindsockAL;
    }

    public void addUnusedWindsockModel(WindsockModel windsockModel)
    {
        if(!unusedWindsockAL.contains(windsockModel))
            unusedWindsockAL.add(windsockModel);
    }

    public ArrayList getExclusionAL()
    {
        return exclusionAL;
    }

    public void addExclusionModel(ExclusionModel exclusionModel)
    {
        if(!exclusionAL.contains(exclusionModel))
            exclusionAL.add(exclusionModel);
    }

    public void removeExclusionModel(ExclusionModel exclusionModel)
    {
        exclusionAL.remove(exclusionModel);
    }

    public ArrayList getUnusedExclusionAL()
    {
        return unusedExclusionAL;
    }

    public void addUnusedExclusionModel(ExclusionModel exclusionModel)
    {
        if(!unusedExclusionAL.contains(exclusionModel))
            unusedExclusionAL.add(exclusionModel);
    }

    public ArrayList getTriggerAL()
    {
        return triggerAL;
    }

    public void addTriggerModel(TriggerModel triggerModel)
    {
        if(!triggerAL.contains(triggerModel))
            triggerAL.add(triggerModel);
    }

    public void removeTriggerModel(TriggerModel triggerModel)
    {
        triggerAL.remove(triggerModel);
    }

    public void addTriggerToParking(TaxiwayParkingModel model)
    {
        Rectangle2D square = ((java.awt.geom.Ellipse2D.Float)model.getParkingCircle()).getBounds2D();
        square.setFrame(square.getX() - (double)(20F * scale), square.getY() - (double)(20F * scale), square.getWidth() + (double)(40F * scale), square.getHeight() + (double)(40F * scale));
        TriggerModel triggerModel = new TriggerModel();
        triggerModel.setShouldNotify(false);
        triggerModel.setType73("YES");
        triggerModel.setTypeJet("YES");
        triggerModel.setShouldNotify(true);
        java.awt.geom.Point2D.Float point = new java.awt.geom.Point2D.Float((float)(square.getX() + square.getWidth() / 2D), (float)(square.getY() + square.getHeight() / 2D));
        java.awt.geom.Point2D.Float vertexPoints[] = new java.awt.geom.Point2D.Float[4];
        vertexPoints[0] = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float((float)square.getX(), (float)square.getY()), model.getHeading());
        vertexPoints[1] = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float((float)square.getX(), (float)(square.getY() + square.getHeight())), model.getHeading());
        vertexPoints[2] = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float((float)(square.getX() + square.getWidth()), (float)(square.getY() + square.getHeight())), model.getHeading());
        vertexPoints[3] = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float((float)(square.getX() + square.getWidth()), (float)square.getY()), model.getHeading());
        for(int i = 0; i < 4; i++)
        {
            VertexModel vertexModel = new VertexModel();
            vertexModel.setShouldNotify(false);
            vertexModel.setLatLon(Utilities.getLatLonForPixel(model.getCenterPoint().getLat(), model.getCenterPoint().getLon(), vertexPoints[i].getX(), vertexPoints[i].getY(), scale));
            vertexModel.setShouldNotify(true);
            triggerModel.addVertexModel(vertexModel);
        }

        addTriggerModel(triggerModel);
    }

    public void addStationToParking(TaxiwayParkingModel model)
    {
        Rectangle2D square = ((java.awt.geom.Ellipse2D.Float)model.getParkingCircle()).getBounds2D();
        SceneryModel sceneryModel = new SceneryModel();
        sceneryModel.setShouldNotify(false);
        sceneryModel.setName("{0da416ee-c523-4d81-a562-9ce503f28468}");
        sceneryModel.setHeading(model.getHeading());
        java.awt.geom.Point2D.Float point = new java.awt.geom.Point2D.Float((float)(square.getX() + square.getWidth() / 2D), (float)(square.getY() + square.getHeight() / 2D));
        java.awt.geom.Point2D.Float sceneryPoint = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float((float)(square.getX() + square.getWidth() / 2D), (float)(square.getY() - 27.5D * (double)scale)), model.getHeading());
        sceneryModel.setLatLon(Utilities.getLatLonForPixel(model.getCenterPoint().getLat(), model.getCenterPoint().getLon(), sceneryPoint.getX(), sceneryPoint.getY(), scale));
        sceneryModel.setShouldNotify(true);
        addSceneryModel(sceneryModel);
    }

    public ArrayList getUnusedTriggerAL()
    {
        return unusedTriggerAL;
    }

    public void addUnusedTriggerModel(TriggerModel triggerModel)
    {
        if(!unusedTriggerAL.contains(triggerModel))
            unusedTriggerAL.add(triggerModel);
    }

    public ArrayList getSceneryAL()
    {
        return sceneryAL;
    }

    public void addSceneryModel(SceneryModel sceneryModel)
    {
        if(!sceneryAL.contains(sceneryModel))
            sceneryAL.add(sceneryModel);
    }

    public void removeSceneryModel(SceneryModel sceneryModel)
    {
        sceneryAL.remove(sceneryModel);
    }

    public ArrayList getUnusedSceneryAL()
    {
        return unusedSceneryAL;
    }

    public void addUnusedSceneryModel(SceneryModel sceneryModel)
    {
        if(!unusedSceneryAL.contains(sceneryModel))
            unusedSceneryAL.add(sceneryModel);
    }

    public DeleteModel getDeleteModel()
    {
        return deleteModel;
    }

    public void setDeleteModel(DeleteModel deleteModel)
    {
        this.deleteModel = deleteModel;
    }

    public String getFileName()
    {
        return fileName;
    }

    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }

    public String getRegion()
    {
        return region;
    }

    public void setRegion(String region)
    {
        if(shouldNotify && !this.region.equals(region))
            HistoryListModel.getInstance().addModel(new HistoryModel("setRegion", "Region", "", this, region, this.region));
        this.region = region;
    }

    public String getCountry()
    {
        return country;
    }

    public void setCountry(String country)
    {
        if(shouldNotify && !this.country.equals(country))
            HistoryListModel.getInstance().addModel(new HistoryModel("setCountry", "Country", "", this, country, this.country));
        this.country = country;
    }

    public String getState()
    {
        return state;
    }

    public void setState(String state)
    {
        if(shouldNotify && !this.state.equals(state))
            HistoryListModel.getInstance().addModel(new HistoryModel("setState", "State", "", this, state, this.state));
        this.state = state;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        if(shouldNotify && !this.city.equals(city))
            HistoryListModel.getInstance().addModel(new HistoryModel("setCity", "City", "", this, city, this.city));
        this.city = city;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        if(shouldNotify && !this.name.equals(name))
            HistoryListModel.getInstance().addModel(new HistoryModel("setName", "Name", "", this, name, this.name));
        this.name = name;
    }

    public double getAlt()
    {
        return alt;
    }

    public void setAlt(double alt)
    {
        if(shouldNotify && this.alt != alt)
            HistoryListModel.getInstance().addModel(new HistoryModel("setAlt", "Altitude", "", this, new Double(alt), new Double(this.alt)));
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
        this.altMeasure = altMeasure;
    }

    public String getIdent()
    {
        return ident;
    }

    public void setIdent(String ident)
    {
        if(shouldNotify && !this.ident.equals(ident))
            HistoryListModel.getInstance().addModel(new HistoryModel("setIdent", "Ident", "", this, ident, this.ident));
        firePropertyChange("ident", this.ident, ident);
        this.ident = ident;
    }

    public double getAirportTestRadius()
    {
        return airportTestRadius;
    }

    public void setAirportTestRadius(double airportTestRadius)
    {
        if(shouldNotify && this.airportTestRadius != airportTestRadius)
            HistoryListModel.getInstance().addModel(new HistoryModel("setAirportTestRadius", "Airport Test Radius", "", this, new Double(airportTestRadius), new Double(this.airportTestRadius)));
        this.airportTestRadius = airportTestRadius;
    }

    public String getAirportTestRadiusMeasure()
    {
        return airportTestRadiusMeasure;
    }

    public void setAirportTestRadiusMeasure(String airportTestRadiusMeasure)
    {
        if(shouldNotify && !this.airportTestRadiusMeasure.equals(airportTestRadiusMeasure))
            HistoryListModel.getInstance().addModel(new HistoryModel("setAirportTestRadiusMeasure", "Airport Test Radius Measure", "", this, airportTestRadiusMeasure, this.airportTestRadiusMeasure));
        this.airportTestRadiusMeasure = airportTestRadiusMeasure;
    }

    public float getTrafficScalar()
    {
        return trafficScalar;
    }

    public void setTrafficScalar(float trafficScalar)
    {
        if(shouldNotify && this.trafficScalar != trafficScalar)
            HistoryListModel.getInstance().addModel(new HistoryModel("setTrafficScalar", "Traffic Scalar", "", this, new Float(trafficScalar), new Float(this.trafficScalar)));
        this.trafficScalar = trafficScalar;
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

    public float getMagvar()
    {
        return magvar;
    }

    public void setMagvar(float magvar)
    {
        if(shouldNotify && this.magvar != magvar)
            HistoryListModel.getInstance().addModel(new HistoryModel("setMagvar", "Magvar", "", this, new Float(magvar), new Float(this.magvar)));
        this.magvar = magvar;
    }

    public int incrementTaxiIndex()
    {
        maxTaxiIndex++;
        return maxTaxiIndex;
    }

    public int getMaxTaxiIndex()
    {
        return maxTaxiIndex;
    }

    public void setMaxTaxiIndex(int maxTaxiIndex)
    {
        this.maxTaxiIndex = maxTaxiIndex;
    }

    public int incrementTaxiNameIndex()
    {
        maxTaxiNameIndex++;
        return maxTaxiNameIndex;
    }

    public int getMaxTaxiNameIndex()
    {
        return maxTaxiNameIndex;
    }

    public void setMaxTaxiNameIndex(int maxTaxiNameIndex)
    {
        this.maxTaxiNameIndex = maxTaxiNameIndex;
    }

    private void updateJetwayNames(String gate, String newName)
    {
        if(SettingsEngine.getInstance().getUpdateJetways())
        {
            for(int i = jetwayAL.size() - 1; i >= 0; i--)
                if(gate.equals((new StringBuilder()).append(((JetwayModel)jetwayAL.get(i)).getGateName()).append("-").append(((JetwayModel)jetwayAL.get(i)).getParkingNumber()).toString()))
                    ((JetwayModel)jetwayAL.get(i)).setGateName(newName);

        }
    }

    private void updateJetwayNumbers(String gate, int newNumber)
    {
        if(SettingsEngine.getInstance().getUpdateJetways())
        {
            for(int i = jetwayAL.size() - 1; i >= 0; i--)
                if(gate.equals((new StringBuilder()).append(((JetwayModel)jetwayAL.get(i)).getGateName()).append("-").append(((JetwayModel)jetwayAL.get(i)).getParkingNumber()).toString()))
                    ((JetwayModel)jetwayAL.get(i)).setParkingNumber(newNumber);

        }
    }

    public void setBlankTaxiNameIndex(int blankTaxiNameIndex)
    {
        this.blankTaxiNameIndex = blankTaxiNameIndex;
    }

    public int getBlankTaxiNameIndex()
    {
        return blankTaxiNameIndex;
    }

    public void propertyChange(PropertyChangeEvent event)
    {
        if(event.getSource() instanceof TaxiwayParkingModel)
        {
            if(event.getPropertyName().equals("name"))
                updateJetwayNames((String)event.getOldValue(), (String)event.getNewValue());
            else
            if(event.getPropertyName().equals("number"))
                updateJetwayNumbers((String)event.getOldValue(), ((Integer)event.getNewValue()).intValue());
        } else
        if((event.getSource() instanceof RunwayModel) && event.getPropertyName().equals("ilsRunway"))
        {
            removeILSModel((ILSModel)event.getOldValue());
            addILSModelToRunway((ILSModel)event.getOldValue(), (String)event.getNewValue());
        }
    }

    private String fileName;
    private ArrayList runwayAL;
    private HashMap taxiwayPointHM;
    private HashMap taxiwayParkingHM;
    private ArrayList taxiwaySignAL;
    private ArrayList towerAL;
    private ArrayList apronAL;
    private ArrayList boundaryFenceAL;
    private ArrayList blastFenceAL;
    private ArrayList taxiwayPathAL;
    private ArrayList startAL;
    private ArrayList planeAL;
    private ArrayList rotationAL;
    private ArrayList helipadAL;
    private ArrayList apronEdgeLightAL;
    private ArrayList taxiNameAL;
    private ArrayList jetwayAL;
    private ArrayList comAL;
    private ArrayList approachAL;
    private ArrayList markerAL;
    private ArrayList vorAL;
    private ArrayList ndbAL;
    private ArrayList unusedMarkerAL;
    private ArrayList unusedVORAL;
    private ArrayList unusedNDBAL;
    private ArrayList taxiwayPathDisplayAL;
    private ArrayList windsockAL;
    private ArrayList unusedWindsockAL;
    private ArrayList exclusionAL;
    private ArrayList unusedExclusionAL;
    private ArrayList triggerAL;
    private ArrayList unusedTriggerAL;
    private ArrayList sceneryAL;
    private ArrayList unusedSceneryAL;
    private ArrayList servicesAL;
    private HashMap taxiwayPathDisplayHM;
    private DeleteModel deleteModel;
    private String region;
    private String country;
    private String state;
    private String city;
    private String name;
    private String altMeasure;
    private String ident;
    private String airportTestRadiusMeasure;
    private LatLonPoint latLon;
    private ArrayList bgImageAL;
    private double alt;
    private double airportTestRadius;
    private float trafficScalar;
    private float magvar;
    private int maxTaxiIndex;
    private int maxTaxiNameIndex;
    private int blankTaxiNameIndex;
    private int highlightedTWPath;
}
