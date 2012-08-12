// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   KMLFOVDlg.java

package photooverlaycreator;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import javax.swing.*;

// Referenced classes of package photooverlaycreator:
//            PhotoOverlayData

public class KMLFOVDlg extends JDialog
{

    public KMLFOVDlg(Frame parent, boolean modal)
    {
        super(parent, modal);
        photoOverlayData = new PhotoOverlayData();
        initComponents();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = getSize();
        if(frameSize.height > screenSize.height)
            frameSize.height = screenSize.height;
        if(frameSize.width > screenSize.width)
            frameSize.width = screenSize.width;
        setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
    }

    public void setPhotoOverlayData(PhotoOverlayData data)
    {
        photoOverlayData = data;
        leftTextField.setText(df.format(photoOverlayData.fovLeft));
        rightTextField.setText(df.format(photoOverlayData.fovRight));
        bottomTextField.setText(df.format(photoOverlayData.fovBottom));
        topTextField.setText(df.format(photoOverlayData.fovTop));
        nearTextField.setText(df.format(photoOverlayData.near));
    }

    public PhotoOverlayData getPhotoOverlayData()
    {
        return photoOverlayData;
    }

    private void initComponents()
    {
        jLabel2 = new JLabel();
        topTextField = new JTextField();
        jLabel3 = new JLabel();
        leftTextField = new JTextField();
        jLabel4 = new JLabel();
        rightTextField = new JTextField();
        jLabel5 = new JLabel();
        bottomTextField = new JTextField();
        jLabel6 = new JLabel();
        nearTextField = new JTextField();
        jLabel7 = new JLabel();
        jLabel8 = new JLabel();
        jLabel9 = new JLabel();
        jLabel10 = new JLabel();
        jLabel11 = new JLabel();
        jButton1 = new JButton();
        jButton2 = new JButton();
        jLabel1 = new JLabel();
        setDefaultCloseOperation(2);
        setTitle("Field of View Information");
        jLabel2.setText("Top");
        topTextField.setColumns(5);
        topTextField.setText("jTextField1");
        jLabel3.setText("Left");
        leftTextField.setColumns(5);
        leftTextField.setText("jTextField2");
        jLabel4.setText("Right");
        rightTextField.setColumns(5);
        rightTextField.setText("jTextField3");
        jLabel5.setText("Bottom");
        bottomTextField.setColumns(5);
        bottomTextField.setText("jTextField4");
        jLabel6.setText("Near (or sphere radius)");
        nearTextField.setColumns(6);
        nearTextField.setText("jTextField5");
        jLabel7.setText("degrees");
        jLabel8.setText("degrees");
        jLabel9.setText("degrees");
        jLabel10.setText("degrees");
        jLabel11.setText("metres");
        jButton1.setText("Cancel");
        jButton1.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt)
            {
                jButton1ActionPerformed(evt);
            }

        }
);
        jButton2.setText("OK");
        jButton2.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt)
            {
                jButton2ActionPerformed(evt);
            }

        }
);
        jLabel1.setText("<html>Enter the field of view in degrees and the distance to the camera or the radius for a sphere or cylinder.");
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jLabel1, -1, 328, 32767).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING).addComponent(jLabel3).addComponent(jLabel4).addComponent(jLabel5).addComponent(jLabel2).addComponent(jLabel6)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(nearTextField, -2, -1, -2).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jLabel11)).addGroup(layout.createSequentialGroup().addComponent(topTextField, -2, -1, -2).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jLabel10)).addGroup(layout.createSequentialGroup().addComponent(bottomTextField, -2, -1, -2).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jLabel9)).addGroup(layout.createSequentialGroup().addComponent(rightTextField, -2, -1, -2).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jLabel8)).addGroup(layout.createSequentialGroup().addComponent(leftTextField, -2, -1, -2).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jLabel7)))).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addComponent(jButton2).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jButton1))).addContainerGap()));
        layout.linkSize(0, new Component[] {
            jButton1, jButton2
        });
        layout.linkSize(0, new Component[] {
            bottomTextField, leftTextField, rightTextField, topTextField
        });
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(jLabel1, -2, 42, -2).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(leftTextField, -2, -1, -2).addComponent(jLabel7).addComponent(jLabel3)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel4).addComponent(rightTextField, -2, -1, -2).addComponent(jLabel8)).addGap(8, 8, 8).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel5).addComponent(bottomTextField, -2, -1, -2).addComponent(jLabel9)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel2).addComponent(topTextField, -2, -1, -2).addComponent(jLabel10)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel6).addComponent(nearTextField, -2, -1, -2).addComponent(jLabel11)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, 32767).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jButton1).addComponent(jButton2)).addContainerGap()));
        pack();
    }

    private void jButton2ActionPerformed(ActionEvent evt)
    {
        try
        {
            photoOverlayData.fovLeft = Float.parseFloat(leftTextField.getText());
            photoOverlayData.fovRight = Float.parseFloat(rightTextField.getText());
            photoOverlayData.fovBottom = Float.parseFloat(bottomTextField.getText());
            photoOverlayData.fovTop = Float.parseFloat(topTextField.getText());
            photoOverlayData.near = Float.parseFloat(nearTextField.getText());
        }
        catch(Exception ex) { }
        setVisible(false);
        dispose();
    }

    private void jButton1ActionPerformed(ActionEvent evt)
    {
        setVisible(false);
        dispose();
    }

    public static void main(String args[])
    {
        EventQueue.invokeLater(new Runnable() {

            public void run()
            {
                (new KMLFOVDlg(new JFrame(), true)).setVisible(true);
            }

        }
);
    }

    private final DecimalFormat df = new DecimalFormat("0.00");
    private PhotoOverlayData photoOverlayData;
    private JTextField bottomTextField;
    private JButton jButton1;
    private JButton jButton2;
    private JLabel jLabel1;
    private JLabel jLabel10;
    private JLabel jLabel11;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel6;
    private JLabel jLabel7;
    private JLabel jLabel8;
    private JLabel jLabel9;
    private JTextField leftTextField;
    private JTextField nearTextField;
    private JTextField rightTextField;
    private JTextField topTextField;


}
