// Decompiled by DJ v3.9.9.91 Copyright 2005 Atanas Neshkov  Date: 3/2/2008 9:26:03 AM
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   MoveAirportDialog.java

package com.zbluesoftware.fsxp.dialog;

import com.zbluesoftware.fsxp.engine.DisplayEngine;
import com.zbluesoftware.fsxp.engine.ParseEngine;
import com.zbluesoftware.fsxp.model.*;
import com.zbluesoftware.fsxp.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class MoveAirportDialog extends JDialog
    implements ActionListener
{

    private MoveAirportDialog(Frame parent)
    {
        super(parent, "Move Airport", true);
        JTextArea infoTA = new JTextArea(5, 50);
        infoTA.setFont(Utilities.LABEL_FONT);
        infoTA.setForeground(Color.black);
        infoTA.setLineWrap(true);
        infoTA.setWrapStyleWord(true);
        infoTA.setOpaque(false);
        infoTA.setEditable(false);
        StringBuffer text = new StringBuffer();
        text.append("This dialog allows you to move the airport from its existing center point to a new one.");
        text.append(" All latitude/longitude references of the airport will be adjusted to maintain their");
        text.append(" current relationship to each other, but relative to the new airport center point.");
        infoTA.setText(text.toString());
        JLabel centerLabel = new JLabel("Current airport center point:");
        centerLabel.setFont(Utilities.LABEL_FONT);
        centerLabel.setForeground(Color.black);
        JLabel latLabel = new JLabel("Latitude:");
        latLabel.setFont(Utilities.LABEL_FONT);
        latLabel.setForeground(Color.black);
        latTF = new JTextField(15);
        latTF.setFont(Utilities.TEXT_FIELD_FONT);
        latTF.setForeground(Color.black);
        latTF.setEditable(false);
        JLabel lonLabel = new JLabel("Longitude:");
        lonLabel.setFont(Utilities.LABEL_FONT);
        lonLabel.setForeground(Color.black);
        lonTF = new JTextField(15);
        lonTF.setFont(Utilities.TEXT_FIELD_FONT);
        lonTF.setForeground(Color.black);
        lonTF.setEditable(false);
        JLabel newCenterLabel = new JLabel("New airport center point:");
        newCenterLabel.setFont(Utilities.LABEL_FONT);
        newCenterLabel.setForeground(Color.black);
        JLabel newLatLabel = new JLabel("Latitude:");
        newLatLabel.setFont(Utilities.LABEL_FONT);
        newLatLabel.setForeground(Color.black);
        newLatTF = new PopupTextField(15);
        newLatTF.setFont(Utilities.TEXT_FIELD_FONT);
        newLatTF.setForeground(Color.black);
        JLabel newLonLabel = new JLabel("Longitude:");
        newLonLabel.setFont(Utilities.LABEL_FONT);
        newLonLabel.setForeground(Color.black);
        newLonTF = new PopupTextField(15);
        newLonTF.setFont(Utilities.TEXT_FIELD_FONT);
        newLonTF.setForeground(Color.black);
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        Utilities.addComponent(mainPanel, infoTA, 0, 0, 4, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, centerLabel, 0, 1, 2, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, latLabel, 0, 2, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, latTF, 1, 2, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, lonLabel, 0, 3, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, lonTF, 1, 3, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, newCenterLabel, 2, 1, 2, 1, 0, 17, new Insets(1, 40, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, newLatLabel, 2, 2, 1, 1, 0, 17, new Insets(1, 40, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, newLatTF, 3, 2, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, newLonLabel, 2, 3, 1, 1, 0, 17, new Insets(1, 40, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, newLonTF, 3, 3, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, new JSeparator(), 0, 4, 4, 1, 2, 17, new Insets(10, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        getContentPane().add(mainPanel, "Center");
        moveButton = new JButton("Move Airport");
        moveButton.setFont(Utilities.DIALOG_BUTTON_FONT);
        moveButton.setForeground(Color.black);
        moveButton.addActionListener(this);
        cancelButton = new JButton("Cancel");
        cancelButton.setFont(Utilities.DIALOG_BUTTON_FONT);
        cancelButton.setForeground(Color.black);
        cancelButton.addActionListener(this);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(moveButton);
        buttonPanel.add(cancelButton);
        getContentPane().add(buttonPanel, "South");
        pack();
        setLocationRelativeTo(parent);
    }

    public static void showDialog(Frame parent, AirportModel airportModel)
    {
        if(dialog == null)
            dialog = new MoveAirportDialog(parent);
        if(airportModel != null)
        {
            dialog.setAirportModel(airportModel);
            dialog.setVisible(true);
        }
    }

    public void setAirportModel(AirportModel airportModel)
    {
        this.airportModel = airportModel;
        latTF.setText(DisplayEngine.getInstance().formatObjectLatitude(airportModel.getLatLon().getLat()));
        lonTF.setText(DisplayEngine.getInstance().formatObjectLatitude(airportModel.getLatLon().getLon()));
        newLatTF.setText(DisplayEngine.getInstance().formatObjectLatitude(airportModel.getLatLon().getLat()));
        newLonTF.setText(DisplayEngine.getInstance().formatObjectLatitude(airportModel.getLatLon().getLon()));
        setTitle((new StringBuilder()).append("Move Airport [").append(airportModel.getIdent()).append("]").toString());
    }

    private boolean moveAirport()
    {
        double lat = ParseEngine.getInstance().parseLatitude(newLatTF.getText());
        if(lat == (1.0D / 0.0D))
        {
            JOptionPane.showMessageDialog(this, "The latitude you have entered could not be parsed.", "Latitude Error...", 0);
            newLatTF.requestFocus();
            return false;
        }
        latTF.setText(DisplayEngine.getInstance().formatObjectLatitude(lat));
        double lon = ParseEngine.getInstance().parseLongitude(newLonTF.getText());
        if(lon == (1.0D / 0.0D))
        {
            JOptionPane.showMessageDialog(this, "The longitude you have entered could not be parsed.", "Longitude Error...", 0);
            newLonTF.requestFocus();
            return false;
        }
        lonTF.setText(DisplayEngine.getInstance().formatObjectLongitude(lon));
        double latDifference = airportModel.getLatLon().getLat() - lat;
        double lonDifference = airportModel.getLatLon().getLon() - lon;
        airportModel.setLatLon(new LatLonPoint(lat, lon));
        ArrayList arrayList = airportModel.getRunwayAL();
        for(int i = arrayList.size() - 1; i >= 0; i--)
        {
            RunwayModel model = (RunwayModel)arrayList.get(i);
            model.setLatLon(new LatLonPoint(model.getLatLon().getLat() - latDifference, model.getLatLon().getLon() - lonDifference));
        }
/*        HashMap hashMap = airportModel.getTaxiwayPointHM();
        TaxiwayPointModel model;
        for(Iterator iterator = hashMap.keySet().iterator(); iterator.hasNext(); model.setLatLon(new LatLonPoint(model.getLatLon().getLat() - latDifference, model.getLatLon().getLon() - lonDifference)))
            model = (TaxiwayPointModel)hashMap.get(iterator.next());

        HashMap hashMap = airportModel.getTaxiwayParkingHM();
        TaxiwayParkingModel model;
        for(Iterator iterator = hashMap.keySet().iterator(); iterator.hasNext(); model.setLatLon(new LatLonPoint(model.getLatLon().getLat() - latDifference, model.getLatLon().getLon() - lonDifference)))
            model = (TaxiwayParkingModel)hashMap.get(iterator.next());

        arrayList = airportModel.getTaxiwaySignAL();*/

		HashMap hashmap1 = airportModel.getTaxiwayPointHM();
		Set set = hashmap1.keySet();
		Iterator iter = set.iterator();
		while(iter.hasNext())
		{
			TaxiwayPointModel model1 = (TaxiwayPointModel) hashmap1.get(iter.next());
			model1.setLatLon(new LatLonPoint(model1.getLatLon().getLat() - latDifference, model1.getLatLon().getLon() - lonDifference));
		}
        
        HashMap hashmap2 = airportModel.getTaxiwayParkingHM();
		set = hashmap1.keySet();
		iter = set.iterator();
		while(iter.hasNext())
		{
			TaxiwayParkingModel model2 = (TaxiwayParkingModel) hashmap2.get(iter.next());
			model2.setLatLon(new LatLonPoint(model2.getLatLon().getLat() - latDifference, model2.getLatLon().getLon() - lonDifference));
		}
		
        for(int i = arrayList.size() - 1; i >= 0; i--)
        {
            TaxiwaySignModel model = (TaxiwaySignModel)arrayList.get(i);
            model.setLatLon(new LatLonPoint(model.getLatLon().getLat() - latDifference, model.getLatLon().getLon() - lonDifference));
        }

        arrayList = airportModel.getTowerAL();
        for(int i = arrayList.size() - 1; i >= 0; i--)
        {
            TowerModel model = (TowerModel)arrayList.get(i);
            model.setLatLon(new LatLonPoint(model.getLatLon().getLat() - latDifference, model.getLatLon().getLon() - lonDifference));
        }

        arrayList = airportModel.getApronAL();
        for(int i = arrayList.size() - 1; i >= 0; i--)
        {
            ArrayList vertexAL = ((ApronModel)arrayList.get(i)).getVertexAL();
            for(int j = vertexAL.size() - 1; j >= 0; j--)
            {
                VertexModel model = (VertexModel)vertexAL.get(j);
                model.setLatLon(new LatLonPoint(model.getLatLon().getLat() - latDifference, model.getLatLon().getLon() - lonDifference));
            }

        }

        arrayList = airportModel.getBoundaryFenceAL();
        for(int i = arrayList.size() - 1; i >= 0; i--)
        {
            ArrayList vertexAL = ((BoundaryFenceModel)arrayList.get(i)).getVertexAL();
            for(int j = vertexAL.size() - 1; j >= 0; j--)
            {
                VertexModel model = (VertexModel)vertexAL.get(j);
                model.setLatLon(new LatLonPoint(model.getLatLon().getLat() - latDifference, model.getLatLon().getLon() - lonDifference));
            }

        }

        arrayList = airportModel.getBlastFenceAL();
        for(int i = arrayList.size() - 1; i >= 0; i--)
        {
            ArrayList vertexAL = ((BlastFenceModel)arrayList.get(i)).getVertexAL();
            for(int j = vertexAL.size() - 1; j >= 0; j--)
            {
                VertexModel model = (VertexModel)vertexAL.get(j);
                model.setLatLon(new LatLonPoint(model.getLatLon().getLat() - latDifference, model.getLatLon().getLon() - lonDifference));
            }

        }

        arrayList = airportModel.getStartAL();
        for(int i = arrayList.size() - 1; i >= 0; i--)
        {
            StartModel model = (StartModel)arrayList.get(i);
            model.setLatLon(new LatLonPoint(model.getLatLon().getLat() - latDifference, model.getLatLon().getLon() - lonDifference));
        }

        arrayList = airportModel.getHelipadAL();
        for(int i = arrayList.size() - 1; i >= 0; i--)
        {
            HelipadModel model = (HelipadModel)arrayList.get(i);
            model.setLatLon(new LatLonPoint(model.getLatLon().getLat() - latDifference, model.getLatLon().getLon() - lonDifference));
        }

        arrayList = airportModel.getApronEdgeLightAL();
        for(int i = arrayList.size() - 1; i >= 0; i--)
        {
            ArrayList vertexAL = ((ApronEdgeLightModel)arrayList.get(i)).getVertexAL();
            for(int j = vertexAL.size() - 1; j >= 0; j--)
            {
                VertexModel model = (VertexModel)vertexAL.get(j);
                model.setLatLon(new LatLonPoint(model.getLatLon().getLat() - latDifference, model.getLatLon().getLon() - lonDifference));
            }

        }

        arrayList = airportModel.getJetwayAL();
        for(int i = arrayList.size() - 1; i >= 0; i--)
        {
            JetwayModel model = (JetwayModel)arrayList.get(i);
            model.setLatLon(new LatLonPoint(model.getLatLon().getLat() - latDifference, model.getLatLon().getLon() - lonDifference));
        }

        JOptionPane.showMessageDialog(this, "The airport has been moved.", "Airport Moved...", 1);
        return true;
    }

    public void actionPerformed(ActionEvent event)
    {
        if(event.getSource() == moveButton)
        {
            if(moveAirport())
                setVisible(false);
        } else
        if(event.getSource() == cancelButton)
            setVisible(false);
    }

    private AirportModel airportModel;
    private JTextField latTF;
    private JTextField lonTF;
    private PopupTextField newLatTF;
    private PopupTextField newLonTF;
    private JButton moveButton;
    private JButton cancelButton;
    private static MoveAirportDialog dialog = null;

}