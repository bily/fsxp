// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AboutBox.java

package imagetoolscommon;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class AboutBox extends JDialog
{

    public AboutBox(Frame parent, boolean modal)
    {
        super(parent, modal);
        title = "Application Title";
        version = "Version Number";
        copyright = "Copyright text";
        comments = "Description of the application.";
    }

    public AboutBox(Frame parent, boolean modal, ImageIcon image, String title, String version, String copyright, String comments)
    {
        super(parent, modal);
        this.title = "Application Title";
        this.version = "Version Number";
        this.copyright = "Copyright text";
        this.comments = "Description of the application.";
        image1 = image;
        this.title = title;
        this.version = version;
        this.copyright = copyright;
        this.comments = comments;
        initComponents();
        jTextArea1.setCaretPosition(0);
        Dimension dlgSize = getSize();
        Dimension frmSize = Toolkit.getDefaultToolkit().getScreenSize();
        Point loc = getLocation();
        setLocation((frmSize.width - dlgSize.width) / 2 + loc.x, (frmSize.height - dlgSize.height) / 2 + loc.y);
    }

    private void initComponents()
    {
        jPanel1 = new JPanel();
        jLabel1 = new JLabel();
        jPanel2 = new JPanel();
        titleLabel = new JLabel();
        versionLabel = new JLabel();
        copyrightLabel = new JLabel();
        jScrollPane1 = new JScrollPane();
        jTextArea1 = new JTextArea();
        jPanel3 = new JPanel();
        jButton1 = new JButton();
        setDefaultCloseOperation(2);
        setTitle("About");
        jLabel1.setIcon(image1);
        jLabel1.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        jPanel1.add(jLabel1);
        getContentPane().add(jPanel1, "West");
        jPanel2.setLayout(new GridBagLayout());
        titleLabel.setFont(new Font("Tahoma", 1, 14));
        titleLabel.setText(title);
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = 17;
        gridBagConstraints.insets = new Insets(0, 0, 0, 16);
        jPanel2.add(titleLabel, gridBagConstraints);
        versionLabel.setFont(new Font("Tahoma", 0, 12));
        versionLabel.setText(version);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = 17;
        gridBagConstraints.insets = new Insets(0, 0, 0, 16);
        jPanel2.add(versionLabel, gridBagConstraints);
        copyrightLabel.setFont(new Font("Tahoma", 0, 12));
        copyrightLabel.setText(copyright);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = 17;
        gridBagConstraints.insets = new Insets(0, 0, 12, 16);
        jPanel2.add(copyrightLabel, gridBagConstraints);
        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(31);
        jScrollPane1.setAutoscrolls(true);
        jTextArea1.setBackground(SystemColor.control);
        jTextArea1.setColumns(20);
        jTextArea1.setEditable(false);
        jTextArea1.setFont(new Font("Tahoma", 0, 12));
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(12);
        jTextArea1.setText(comments);
        jTextArea1.setWrapStyleWord(true);
        jTextArea1.setBorder(null);
        jScrollPane1.setViewportView(jTextArea1);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0D;
        gridBagConstraints.weighty = 0.90000000000000002D;
        gridBagConstraints.insets = new Insets(0, 0, 0, 16);
        jPanel2.add(jScrollPane1, gridBagConstraints);
        getContentPane().add(jPanel2, "Center");
        jButton1.setText("OK");
        jButton1.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt)
            {
                jButton1ActionPerformed(evt);
            }
        }
);
        jPanel3.add(jButton1);
        getContentPane().add(jPanel3, "South");
        pack();
    }

    private void jButton1ActionPerformed(ActionEvent evt)
    {
        dispose();
    }

    public static void main(String args[])
    {
        EventQueue.invokeLater(new Runnable() {

            public void run()
            {
                (new AboutBox(new JFrame(), true)).setVisible(true);
            }

        }
);
    }

    protected ImageIcon image1;
    protected String title;
    protected String version;
    protected String copyright;
    protected String comments;
    private JLabel copyrightLabel;
    private JButton jButton1;
    private JLabel jLabel1;
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JPanel jPanel3;
    private JScrollPane jScrollPane1;
    private JTextArea jTextArea1;
    private JLabel titleLabel;
    private JLabel versionLabel;

}
