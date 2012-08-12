// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   KMLLatLonLookupDlg.java

package photooverlaycreator;

import imagetoolscommon.Utils;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.net.URL;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.jdesktop.layout.GroupLayout;
import org.w3c.dom.*;

public class KMLLatLonLookupDlg extends JDialog
{
    public class NodeData
    {

        public String toString()
        {
            return placeName;
        }

        public String placeName;
        public float lat;
        public float lon;

        NodeData(String placeName, float lat, float lon)
        {
            super();
            this.placeName = placeName;
            this.lat = lat;
            this.lon = lon;
        }
    }


    public KMLLatLonLookupDlg(Frame parent, boolean modal)
    {
        super(parent, modal);
        selectedLat = 51.522F;
        selectedLon = -0.134721F;
        initComponents();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = getSize();
        if(frameSize.height > screenSize.height)
            frameSize.height = screenSize.height;
        if(frameSize.width > screenSize.width)
            frameSize.width = screenSize.width;
        setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
    }

    public java.awt.geom.Point2D.Float getSelectedPlace()
    {
        return new java.awt.geom.Point2D.Float(selectedLon, selectedLat);
    }

    private DefaultMutableTreeNode populateTree()
    {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("places");
        try
        {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder parser = factory.newDocumentBuilder();
            Node document = parser.parse((photooverlaycreator.PhotoOverlayApp.class).getResource("templatefiles/places.xml").openStream());
            NodeList list = document.getFirstChild().getChildNodes();
            for(int i = 0; i < list.getLength(); i++)
            {
                Node node = list.item(i);
                String name = node.getNodeName();
                if(name != null && name.equals("continent"))
                {
                    NamedNodeMap attr = node.getAttributes();
                    String continentName = attr.getNamedItem("name").getNodeValue();
                    DefaultMutableTreeNode continentTree = new DefaultMutableTreeNode(continentName);
                    root.add(continentTree);
                    NodeList countries = node.getChildNodes();
                    for(int j = 0; j < countries.getLength(); j++)
                    {
                        Node countryNode = countries.item(j);
                        String countryNodeName = countryNode.getNodeName();
                        if(countryNodeName == null || !countryNodeName.equals("country"))
                            continue;
                        NamedNodeMap attrCountry = countryNode.getAttributes();
                        String countryName = attrCountry.getNamedItem("name").getNodeValue();
                        DefaultMutableTreeNode countryTree = new DefaultMutableTreeNode(countryName);
                        continentTree.add(countryTree);
                        NodeList places = countryNode.getChildNodes();
                        for(int k = 0; k < places.getLength(); k++)
                        {
                            Node placeNode = places.item(k);
                            String placeNodeName = placeNode.getNodeName();
                            if(placeNodeName != null && placeNodeName.equals("place"))
                            {
                                NamedNodeMap attrPlace = placeNode.getAttributes();
                                String placeName = attrPlace.getNamedItem("name").getNodeValue();
                                float lat = java.lang.Float.parseFloat(attrPlace.getNamedItem("lat").getNodeValue());
                                float lon = java.lang.Float.parseFloat(attrPlace.getNamedItem("lon").getNodeValue());
                                DefaultMutableTreeNode placeTree = new DefaultMutableTreeNode(new NodeData(placeName, lat, lon));
                                countryTree.add(placeTree);
                            }
                        }

                    }

                }
            }

        }
        catch(Exception ex)
        {
            Utils.showErrorDialogBox(null, "Error loading places file", "", ex);
        }
        return root;
    }

    private void initComponents()
    {
        jScrollPane1 = new JScrollPane();
        jTree1 = new JTree(populateTree());
        okButton = new JButton();
        cancelButton = new JButton();
        setDefaultCloseOperation(2);
        setTitle("Choose Photo Overlay Location");
        jScrollPane1.setViewportView(jTree1);
        okButton.setText("OK");
        okButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt)
            {
                okButtonActionPerformed(evt);
            }

        }
);
        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt)
            {
                cancelButtonActionPerformed(evt);
            }

        }
);
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(1).add(layout.createSequentialGroup().add(layout.createParallelGroup(1).add(2, layout.createSequentialGroup().addContainerGap().add(okButton).addPreferredGap(0).add(cancelButton)).add(layout.createSequentialGroup().add(19, 19, 19).add(jScrollPane1, -1, 371, 32767))).addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(1).add(layout.createSequentialGroup().addContainerGap().add(jScrollPane1, -1, 240, 32767).add(15, 15, 15).add(layout.createParallelGroup(3).add(cancelButton).add(okButton)).addContainerGap()));
        pack();
    }

    private void okButtonActionPerformed(ActionEvent evt)
    {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode)jTree1.getLastSelectedPathComponent();
        NodeData nodeData = (NodeData)node.getUserObject();
        selectedLat = nodeData.lat;
        selectedLon = nodeData.lon;
        setVisible(false);
        dispose();
    }

    private void cancelButtonActionPerformed(ActionEvent evt)
    {
        setVisible(false);
        dispose();
    }

    public static void main(String args[])
    {
        EventQueue.invokeLater(new Runnable() {

            public void run()
            {
                (new KMLLatLonLookupDlg(new JFrame(), true)).setVisible(true);
            }

        }
);
    }

    private static final String placesTag = "places";
    private static final String continentTag = "continent";
    private static final String countryTag = "country";
    private static final String placeTag = "place";
    private float selectedLat;
    private float selectedLon;
    private JButton cancelButton;
    private JScrollPane jScrollPane1;
    private JTree jTree1;
    private JButton okButton;


}
