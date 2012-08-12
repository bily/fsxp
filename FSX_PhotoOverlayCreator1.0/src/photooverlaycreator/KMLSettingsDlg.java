// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   KMLSettingsDlg.java

package photooverlaycreator;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.text.DecimalFormat;
import javax.accessibility.AccessibleContext;
import javax.swing.*;
import org.jdesktop.layout.GroupLayout;

// Referenced classes of package photooverlaycreator:
//            PhotoOverlayData, KMLFOVDlg, KMLLatLonLookupDlg

public class KMLSettingsDlg extends JDialog
{

    public KMLSettingsDlg(Frame parent, boolean modal)
    {
        super(parent, modal);
        initComponents();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = getSize();
        if(frameSize.height > screenSize.height)
            frameSize.height = screenSize.height;
        if(frameSize.width > screenSize.width)
            frameSize.width = screenSize.width;
        setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
    }

    public void setImageDimension(int width, int height)
    {
        imageDimension = new Dimension(width, height);
        photoOverlayData.calculateDefaultFOV(width, height);
        altTextField.setText(df.format(photoOverlayData.alt));
    }

    public PhotoOverlayData getPhotoOverlayData()
    {
        return photoOverlayData;
    }

    public void setPhotoOverlayData(PhotoOverlayData data)
    {
        photoOverlayData = new PhotoOverlayData(data);
        jTextArea1.setText(photoOverlayData.description);
        shapeComboBox.setSelectedIndex(photoOverlayData.shape);
        latTextField.setText(df6.format(photoOverlayData.lat));
        lonTextField.setText(df6.format(photoOverlayData.lon));
        altTextField.setText(df.format(photoOverlayData.alt));
    }

    private void initComponents()
    {
        jLabel1 = new JLabel();
        jScrollPane1 = new JScrollPane();
        jTextArea1 = new JTextArea();
        jLabel2 = new JLabel();
        jLabel3 = new JLabel();
        jLabel4 = new JLabel();
        jLabel5 = new JLabel();
        lookupButton = new JButton();
        okButton = new JButton();
        cancelButton = new JButton();
        jLabel6 = new JLabel();
        lonTextField = new JFormattedTextField();
        latTextField = new JFormattedTextField();
        jLabel7 = new JLabel();
        shapeComboBox = new JComboBox();
        jLabel13 = new JLabel();
        altTextField = new JTextField();
        jLabel15 = new JLabel();
        setFOVButton = new JButton();
        setDefaultCloseOperation(2);
        setTitle("Photo Overlay Data");
        jLabel1.setText("Description");
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new Font("Tahoma", 0, 11));
        jTextArea1.setRows(5);
        jTextArea1.setText("Put description here");
        jScrollPane1.setViewportView(jTextArea1);
        jLabel2.setText("Longitude");
        jLabel3.setText("Latitude");
        jLabel4.setText("degrees");
        jLabel5.setText("degrees");
        lookupButton.setText("Lookup...");
        lookupButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt)
            {
                lookupButtonActionPerformed(evt);
            }
        }
);
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
        jLabel6.setText("<html>Enter the description that will appear under the PhotoOverlay title in the KML file and where to place the photo on the Earth. The 'Lookup' button contains some pre-defined places.");
        jLabel6.getAccessibleContext().setAccessibleName("<html><p>Enter the description that will appear under the PhotoOverlay title in the KML file and the location to place the photo.</p>");
        lonTextField.setColumns(8);
        lonTextField.setText("-0.134721");
        latTextField.setText("51.522");
        jLabel7.setText("Image Shape");
        shapeComboBox.setModel(new DefaultComboBoxModel(new String[] {
            "Rectangle", "Cylinder", "Panoramic Sphere"
        }));
        shapeComboBox.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt)
            {
                shapeComboBoxActionPerformed(evt);
            }

        }
);
        jLabel13.setText("Altitude");
        altTextField.setColumns(8);
        altTextField.setText("40");
        jLabel15.setText("metres");
        setFOVButton.setText("Set FOV...");
        setFOVButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt)
            {
                setFOVButtonActionPerformed(evt);
            }

        }
);
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(1).add(layout.createSequentialGroup().add(layout.createParallelGroup(1).add(layout.createSequentialGroup().add(10, 10, 10).add(layout.createParallelGroup(2).add(layout.createParallelGroup(1).add(layout.createSequentialGroup().add(20, 20, 20).add(layout.createParallelGroup(2).add(jLabel7).add(layout.createSequentialGroup().add(layout.createParallelGroup(1).add(jLabel13).add(jLabel3)).add(8, 8, 8)))).add(layout.createSequentialGroup().addPreferredGap(0).add(jLabel1))).add(jLabel2)).addPreferredGap(0).add(layout.createParallelGroup(1).add(shapeComboBox, -2, -1, -2).add(jScrollPane1, -1, 360, 32767).add(layout.createSequentialGroup().add(layout.createParallelGroup(1).add(altTextField, -2, -1, -2).add(latTextField, -2, -1, -2).add(lonTextField, -2, -1, -2)).add(11, 11, 11).add(layout.createParallelGroup(1).add(jLabel15).add(layout.createSequentialGroup().add(jLabel5).add(112, 112, 112).add(okButton).add(16, 16, 16).add(cancelButton)).add(layout.createSequentialGroup().add(jLabel4).add(12, 12, 12).add(layout.createParallelGroup(1).add(setFOVButton).add(lookupButton))))))).add(layout.createSequentialGroup().addContainerGap().add(jLabel6, -1, 447, 32767))).addContainerGap()));
        layout.linkSize(new Component[] {
            latTextField, lonTextField
        }, 1);
        layout.setVerticalGroup(layout.createParallelGroup(1).add(layout.createSequentialGroup().addContainerGap().add(jLabel6, -2, 49, -2).addPreferredGap(0).add(layout.createParallelGroup(1).add(jLabel1).add(jScrollPane1, -2, -1, -2)).add(18, 18, 18).add(layout.createParallelGroup(3).add(jLabel7).add(shapeComboBox, -2, -1, -2).add(setFOVButton)).add(layout.createParallelGroup(1).add(layout.createSequentialGroup().add(84, 84, 84).add(layout.createParallelGroup(3).add(okButton).add(cancelButton))).add(layout.createSequentialGroup().add(9, 9, 9).add(layout.createParallelGroup(3).add(altTextField, -2, -1, -2).add(jLabel13).add(jLabel15)).addPreferredGap(0).add(layout.createParallelGroup(3).add(jLabel4).add(lookupButton).add(latTextField, -2, -1, -2).add(jLabel3)).addPreferredGap(0).add(layout.createParallelGroup(3).add(jLabel5).add(lonTextField, -2, -1, -2).add(jLabel2)))).addContainerGap()));
        pack();
    }

    private void shapeComboBoxActionPerformed(ActionEvent evt)
    {
        int shape = shapeComboBox.getSelectedIndex();
        photoOverlayData.setShape(shape);
        if(imageDimension != null)
        {
            photoOverlayData.calculateDefaultFOV(imageDimension.width, imageDimension.height);
            altTextField.setText(df.format(photoOverlayData.alt));
        }
    }

    private void setFOVButtonActionPerformed(ActionEvent evt)
    {
        KMLFOVDlg fovDlg = new KMLFOVDlg(null, true);
        int shape = shapeComboBox.getSelectedIndex();
        photoOverlayData.setShape(shape);
        fovDlg.setPhotoOverlayData(photoOverlayData);
        fovDlg.setVisible(true);
        photoOverlayData = fovDlg.getPhotoOverlayData();
    }

    private void lookupButtonActionPerformed(ActionEvent evt)
    {
        KMLLatLonLookupDlg latlonDlg = new KMLLatLonLookupDlg(null, true);
        latlonDlg.setVisible(true);
        java.awt.geom.Point2D.Float place = latlonDlg.getSelectedPlace();
        latTextField.setText(java.lang.Float.toString(place.y));
        lonTextField.setText(java.lang.Float.toString(place.x));
    }

    private void cancelButtonActionPerformed(ActionEvent evt)
    {
        photoOverlayData = new PhotoOverlayData();
        setVisible(false);
        dispose();
    }

    private void okButtonActionPerformed(ActionEvent evt)
    {
        photoOverlayData.description = jTextArea1.getText();
        int shape = shapeComboBox.getSelectedIndex();
        photoOverlayData.setShape(shape);
        try
        {
            photoOverlayData.lat = java.lang.Float.parseFloat(latTextField.getText());
            photoOverlayData.lon = java.lang.Float.parseFloat(lonTextField.getText());
            photoOverlayData.alt = java.lang.Float.parseFloat(altTextField.getText());
        }
        catch(Exception ex) { }
        setVisible(false);
        dispose();
    }

    public static void main(String args[])
    {
        EventQueue.invokeLater(new Runnable() {

            public void run()
            {
                (new KMLSettingsDlg(new JFrame(), true)).setVisible(true);
            }

        }
);
    }

    private final DecimalFormat df = new DecimalFormat("0.00");
    private final DecimalFormat df6 = new DecimalFormat("0.000000");
    private Dimension imageDimension;
    private PhotoOverlayData photoOverlayData;
    private JTextField altTextField;
    private JButton cancelButton;
    private JLabel jLabel1;
    private JLabel jLabel13;
    private JLabel jLabel15;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel6;
    private JLabel jLabel7;
    private JScrollPane jScrollPane1;
    private JTextArea jTextArea1;
    private JFormattedTextField latTextField;
    private JFormattedTextField lonTextField;
    private JButton lookupButton;
    private JButton okButton;
    private JButton setFOVButton;
    private JComboBox shapeComboBox;





}
