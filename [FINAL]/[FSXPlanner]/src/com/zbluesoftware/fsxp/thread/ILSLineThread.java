// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ILSLineThread.java

package com.zbluesoftware.fsxp.thread;

import com.zbluesoftware.fsxp.model.*;
import java.awt.geom.*;
import java.util.*;

// Referenced classes of package com.zbluesoftware.fsxp.thread:
//            ILSLineUpdates

public class ILSLineThread extends Thread
{

    public ILSLineThread(ILSLineUpdates ilsLineUpdates)
    {
        super("ILSLineThread");
        this.ilsLineUpdates = ilsLineUpdates;
        working = true;
    }

    public void setWorking(boolean working)
    {
        this.working = working;
    }

    public boolean isWorking()
    {
        return working;
    }

    public void run()
    {
        while(working) 
        {
            AirportModel airportModel = ilsLineUpdates.getNextAirportModel();
            ArrayList taxiwayAL = airportModel.getTaxiwayPathAL();
            ArrayList runwayAL = airportModel.getRunwayAL();
            HashMap taxiwayPointHM = airportModel.getTaxiwayPointHM();
            Iterator iterator = taxiwayPointHM.keySet().iterator();
            while(iterator.hasNext()) 
            {
                TaxiwayPointModel taxiwayPointModel = (TaxiwayPointModel)taxiwayPointHM.get(iterator.next());
                if(taxiwayPointModel.getType().equals("HOLD_SHORT"))
                {
                    ArrayList taxiwayModelAL = new ArrayList();
                    int index = taxiwayPointModel.getIndex();
                    for(int i = taxiwayAL.size() - 1; i >= 0; i--)
                        if(((TaxiwayPathModel)taxiwayAL.get(i)).getStart() == index || ((TaxiwayPathModel)taxiwayAL.get(i)).getEnd() == index)
                            taxiwayModelAL.add(taxiwayAL.get(i));

                    boolean found = false;
label0:
                    for(int i = taxiwayModelAL.size() - 1; i >= 0; i--)
                    {
                        int j = runwayAL.size() - 1;
                        do
                        {
                            if(j < 0)
                                continue label0;
                            if(((TaxiwayPathModel)taxiwayModelAL.get(i)).getModelPath() != null && ((RunwayModel)runwayAL.get(j)).getModelPath().intersects(((TaxiwayPathModel)taxiwayModelAL.get(i)).getModelPath().getBounds2D()))
                            {
                                createILSLine(taxiwayPointModel, (TaxiwayPathModel)taxiwayModelAL.get(i));
                                found = true;
                                break label0;
                            }
                            j--;
                        } while(true);
                    }

                    if(!found && taxiwayModelAL.size() > 0)
                        createILSLine(taxiwayPointModel, (TaxiwayPathModel)taxiwayModelAL.get(0));
                } else
                if(taxiwayPointModel.getType().equals("ILS_HOLD_SHORT"))
                {
                    ArrayList taxiwayModelAL = new ArrayList();
                    int index = taxiwayPointModel.getIndex();
                    for(int i = taxiwayAL.size() - 1; i >= 0; i--)
                        if(((TaxiwayPathModel)taxiwayAL.get(i)).getStart() == index || ((TaxiwayPathModel)taxiwayAL.get(i)).getEnd() == index)
                            taxiwayModelAL.add(taxiwayAL.get(i));

                    if(taxiwayModelAL.size() > 0)
                        createILSLine(taxiwayPointModel, (TaxiwayPathModel)taxiwayModelAL.get(0));
                }
            }
        }
    }

    private void createILSLine(TaxiwayPointModel taxiwayPointModel, TaxiwayPathModel taxiwayPathModel)
    {
        float taxiwayWidth = taxiwayPathModel.getWidth() / 2.0F;
        if(taxiwayPathModel.getWidthMeasure() == "M")
            taxiwayWidth *= 3.28F;
        taxiwayPointModel.setTaxiwayWidth(taxiwayWidth);
        float startCoords[] = {
            -1F, -1F, -1F, -1F, -1F, -1F
        };
        float endCoords[] = new float[6];
        GeneralPath modelPath = taxiwayPathModel.getModelPath();
        if(modelPath != null)
        {
            for(PathIterator pathIterator = modelPath.getPathIterator(new AffineTransform()); !pathIterator.isDone(); pathIterator.next())
                if(startCoords[2] == -1F)
                {
                    pathIterator.currentSegment(startCoords);
                    startCoords[2] = 0.0F;
                } else
                {
                    pathIterator.currentSegment(endCoords);
                }

            float x1 = startCoords[0];
            float y1 = startCoords[1];
            float x2 = endCoords[0];
            float y2 = endCoords[1];
            if(x2 > x1 && y2 < y1)
                taxiwayPointModel.setTaxiwayHeading((float)(90D - Math.toDegrees(Math.atan(Math.abs(y1 - y2) / Math.abs(x1 - x2)))));
            else
            if(x2 <= x1 && y2 < y1)
                taxiwayPointModel.setTaxiwayHeading((float)(270D + Math.toDegrees(Math.atan(Math.abs(y1 - y2) / Math.abs(x1 - x2)))));
            else
            if(x2 > x1 && y2 >= y1)
                taxiwayPointModel.setTaxiwayHeading((float)(90D + Math.toDegrees(Math.atan(Math.abs(y1 - y2) / Math.abs(x1 - x2)))));
            else
            if(x2 <= x1 && y2 >= y1)
                taxiwayPointModel.setTaxiwayHeading((float)(270D - Math.toDegrees(Math.atan(Math.abs(y1 - y2) / Math.abs(x1 - x2)))));
        }
    }

    private ILSLineUpdates ilsLineUpdates;
    private boolean working;
}
