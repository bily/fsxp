// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MainFrame_AboutBox.java

package gmapimagecutter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

// Referenced classes of package gmapimagecutter:
//            MainFrame

public class MainFrame_AboutBox extends JDialog
    implements ActionListener
{

    public MainFrame_AboutBox(Frame parent)
    {
        super(parent);
        panel1 = new JPanel();
        panel2 = new JPanel();
        insetsPanel1 = new JPanel();
        insetsPanel2 = new JPanel();
        insetsPanel3 = new JPanel();
        button1 = new JButton();
        imageLabel = new JLabel();
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        image1 = new ImageIcon();
        borderLayout1 = new BorderLayout();
        borderLayout2 = new BorderLayout();
        flowLayout1 = new FlowLayout();
        copyright = "Created by the Centre for Advanced Spatial Analysis at UCL";
        comments = "The Google Maps Image Cutter takes an image file (gif, jpeg, png or tiff) and automatically creates a working Google Maps site. The initial zoom level on loading an image is calculated to match the image resolution, so the max zoom level slider will not normally need to be altered. The status bar at the bottom of the window shows the image name and dimensions together with the point in the image that the mouse is currently over. This point is in lat/lon coordinates to allow markers to be added to the  resulting Google Map by editing the html. See the readme file for more  information.";
        jTextArea1 = new JTextArea();
        gridBagLayout1 = new GridBagLayout();
        try
        {
            setDefaultCloseOperation(2);
            jbInit();
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
    }

    public MainFrame_AboutBox()
    {
        this(null);
    }

    private void jbInit()
        throws Exception
    {
        image1 = new ImageIcon(gmapimagecutter.MainFrame.class.getResource("images/mainicon32.gif"));
        imageLabel.setIcon(image1);
        setTitle("About");
        panel1.setLayout(borderLayout1);
        panel2.setLayout(borderLayout2);
        insetsPanel1.setLayout(flowLayout1);
        insetsPanel2.setLayout(flowLayout1);
        insetsPanel2.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        label1.setFont(new Font("Dialog", 1, 14));
        label1.setMaximumSize(new Dimension(240, 15));
        label1.setText("Google Maps Image Cutter");
        label2.setFont(new Font("Dialog", 1, 11));
        label2.setMaximumSize(new Dimension(240, 15));
        label2.setText("Version: 1.3");
        label3.setFont(new Font("Dialog", 1, 11));
        label3.setMaximumSize(new Dimension(240, 15));
        label3.setText(copyright);
        insetsPanel3.setLayout(gridBagLayout1);
        button1.setText("OK");
        button1.addActionListener(this);
        jTextArea1.setBackground(SystemColor.control);
        jTextArea1.setFont(new Font("SansSerif", 0, 11));
        jTextArea1.setEditable(false);
        jTextArea1.setText(comments);
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(0);
        jTextArea1.setWrapStyleWord(true);
        insetsPanel3.setAlignmentX(0.0F);
        insetsPanel3.setAlignmentY(0.0F);
        panel1.setPreferredSize(new Dimension(400, 220));
        insetsPanel2.add(imageLabel, null);
        panel2.add(insetsPanel2, "West");
        getContentPane().add(panel1, null);
        insetsPanel1.add(button1, null);
        panel1.add(insetsPanel1, "South");
        panel1.add(panel2, "North");
        panel2.add(insetsPanel3, "Center");
        insetsPanel3.add(label2, new GridBagConstraints(0, 1, 1, 1, 1.0D, 0.0D, 17, 2, new Insets(0, 0, 0, 0), 0, 0));
        insetsPanel3.add(label1, new GridBagConstraints(0, 0, 1, 1, 1.0D, 0.0D, 17, 2, new Insets(0, 0, 0, 0), 0, 0));
        insetsPanel3.add(label3, new GridBagConstraints(0, 2, 1, 1, 1.0D, 0.0D, 17, 2, new Insets(0, 0, 0, 0), 0, 0));
        insetsPanel3.add(jTextArea1, new GridBagConstraints(0, 3, 1, 1, 1.0D, 0.0D, 17, 2, new Insets(0, 0, 0, 0), 0, 0));
        setResizable(true);
    }

    public void actionPerformed(ActionEvent actionEvent)
    {
        if(actionEvent.getSource() == button1)
            dispose();
    }

    JPanel panel1;
    JPanel panel2;
    JPanel insetsPanel1;
    JPanel insetsPanel2;
    JPanel insetsPanel3;
    JButton button1;
    JLabel imageLabel;
    JLabel label1;
    JLabel label2;
    JLabel label3;
    ImageIcon image1;
    BorderLayout borderLayout1;
    BorderLayout borderLayout2;
    FlowLayout flowLayout1;
    String copyright;
    String comments;
    JTextArea jTextArea1;
    GridBagLayout gridBagLayout1;
}
