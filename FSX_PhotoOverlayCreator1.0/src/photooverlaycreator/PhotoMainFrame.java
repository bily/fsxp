// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PhotoMainFrame.java

package photooverlaycreator;

import imagetoolscommon.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DecimalFormat;
import javax.swing.*;
import org.jdesktop.layout.GroupLayout;

// Referenced classes of package photooverlaycreator:
//            KMLSettingsDlg, PhotoCutterThread, PhotoOverlayApp, PhotoOverlayData

public class PhotoMainFrame extends JFrame
{

    private void promptPhotoOverlaySettings()
    {
        KMLSettingsDlg photoDlg = new KMLSettingsDlg(this, true);
        photoDlg.setPhotoOverlayData(PhotoOverlayApp.photoOverlayData);
        if(PhotoOverlayApp.imageData != null)
            photoDlg.setImageDimension(PhotoOverlayApp.imageData.image.getWidth(), PhotoOverlayApp.imageData.image.getHeight());
        photoDlg.setVisible(true);
        PhotoOverlayApp.photoOverlayData = photoDlg.getPhotoOverlayData();
    }

    private void createButtonClick()
    {
        promptPhotoOverlaySettings();
        PhotoCutterThread cutterThread = new PhotoCutterThread();
        cutterThread.setParams(PhotoOverlayApp.imageData, PhotoOverlayApp.photoOverlayData);
        NBCreateDialog cd = new NBCreateDialog(this, "Create Image Tiles", true, cutterThread);
        cd.setWindowText("Create Image Tiles - KML PhotoOverlay");
        cd.setVisible(true);
    }

    public PhotoMainFrame()
    {
        df = new DecimalFormat("0.######");
        chooser = new JFileChooser();
        mainIcon = new ImageIcon((photooverlaycreator.PhotoMainFrame.class).getResource("images/camera16.gif"));
        initComponents();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = getSize();
        if(frameSize.height > screenSize.height)
            frameSize.height = screenSize.height;
        if(frameSize.width > screenSize.width)
            frameSize.width = screenSize.width;
        setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
    }

    private void initComponents()
    {
        rightPanel = new JPanel();
        jLabel1 = new JLabel();
        jSlider1 = new JSlider();
        jLabel2 = new JLabel();
        tileCountLabel = new JLabel();
        createButton = new JButton();
        statusBar = new JLabel();
        centrePanel = new ImagePanel();
        jMenuBar1 = new JMenuBar();
        jMenu1 = new JMenu();
        jMenuItem1 = new JMenuItem();
        jMenuItem3 = new JMenuItem();
        jSeparator2 = new JSeparator();
        jMenuItem2 = new JMenuItem();
        jMenu2 = new JMenu();
        kmlSettingsMenuItem = new JMenuItem();
        jSeparator1 = new JSeparator();
        createMenuItem = new JMenuItem();
        jMenu3 = new JMenu();
        helpAboutMenuItem = new JMenuItem();
        setDefaultCloseOperation(3);
        setTitle("Photo Overlay Creator");
        setIconImage(mainIcon.getImage());
        jLabel1.setText("Max Zoom Level");
        jLabel1.setEnabled(false);
        jSlider1.setMajorTickSpacing(5);
        jSlider1.setMaximum(17);
        jSlider1.setMinorTickSpacing(1);
        jSlider1.setOrientation(1);
        jSlider1.setPaintLabels(true);
        jSlider1.setPaintTicks(true);
        jSlider1.setEnabled(false);
        jLabel2.setText("Tile Count:");
        jLabel2.setEnabled(false);
        tileCountLabel.setText("0");
        tileCountLabel.setEnabled(false);
        createButton.setText("Create...");
        createButton.setEnabled(false);
        createButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt)
            {
                createButtonActionPerformed(evt);
            }

        }
);
        GroupLayout rightPanelLayout = new GroupLayout(rightPanel);
        rightPanel.setLayout(rightPanelLayout);
        rightPanelLayout.setHorizontalGroup(rightPanelLayout.createParallelGroup(1).add(rightPanelLayout.createSequentialGroup().addContainerGap().add(rightPanelLayout.createParallelGroup(1).add(jLabel1, -1, 92, 32767).add(createButton))).add(rightPanelLayout.createSequentialGroup().addContainerGap().add(jLabel2).addPreferredGap(0).add(tileCountLabel, -1, 14, 32767).add(20, 20, 20)).add(rightPanelLayout.createSequentialGroup().add(30, 30, 30).add(jSlider1, -2, 52, -2).addContainerGap(20, 32767)));
        rightPanelLayout.setVerticalGroup(rightPanelLayout.createParallelGroup(1).add(rightPanelLayout.createSequentialGroup().add(jLabel1).addPreferredGap(0).add(jSlider1, -1, 216, 32767).addPreferredGap(0).add(rightPanelLayout.createParallelGroup(3).add(jLabel2).add(tileCountLabel)).addPreferredGap(0).add(createButton).addContainerGap()));
        GroupLayout centrePanelLayout = new GroupLayout(centrePanel);
        centrePanel.setLayout(centrePanelLayout);
        centrePanelLayout.setHorizontalGroup(centrePanelLayout.createParallelGroup(1).add(0, 366, 32767));
        centrePanelLayout.setVerticalGroup(centrePanelLayout.createParallelGroup(1).add(0, 296, 32767));
        jMenu1.setText("File");
        jMenuItem1.setText("Open File...");
        jMenuItem1.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt)
            {
                jMenuItem1ActionPerformed(evt);
            }

        }
);
        jMenu1.add(jMenuItem1);
        jMenuItem3.setText("Batch Process...");
        jMenuItem3.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt)
            {
                jMenuItem3ActionPerformed(evt);
            }

        }
);
        jMenu1.add(jMenuItem3);
        jMenu1.add(jSeparator2);
        jMenuItem2.setText("Exit");
        jMenuItem2.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt)
            {
                jMenuItem2ActionPerformed(evt);
            }

        }
);
        jMenu1.add(jMenuItem2);
        jMenuBar1.add(jMenu1);
        jMenu2.setLabel("Processing");
        kmlSettingsMenuItem.setText("Photo Overlay Settings...");
        kmlSettingsMenuItem.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt)
            {
                kmlSettingsMenuItemActionPerformed(evt);
            }

        }
);
        jMenu2.add(kmlSettingsMenuItem);
        jMenu2.add(jSeparator1);
        createMenuItem.setText("Create...");
        createMenuItem.setEnabled(false);
        createMenuItem.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt)
            {
                createMenuItemActionPerformed(evt);
            }
        }
);
        jMenu2.add(createMenuItem);
        jMenuBar1.add(jMenu2);
        jMenu3.setText("Help");
        helpAboutMenuItem.setText("About");
        helpAboutMenuItem.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt)
            {
                helpAboutMenuItemActionPerformed(evt);
            }
        }
);
        jMenu3.add(helpAboutMenuItem);
        jMenuBar1.add(jMenu3);
        setJMenuBar(jMenuBar1);
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(1).add(2, layout.createSequentialGroup().add(centrePanel, -1, -1, 32767).addPreferredGap(0).add(rightPanel, -2, -1, -2)).add(statusBar, -1, 474, 32767));
        layout.setVerticalGroup(layout.createParallelGroup(1).add(layout.createSequentialGroup().add(layout.createParallelGroup(2).add(centrePanel, -1, -1, 32767).add(rightPanel, -1, -1, 32767)).addPreferredGap(0).add(statusBar)));
        pack();
    }

    private void kmlSettingsMenuItemActionPerformed(ActionEvent evt)
    {
        promptPhotoOverlaySettings();
    }

    private void jMenuItem3ActionPerformed(ActionEvent evt)
    {
        PhotoCutterThread cutterThread = new PhotoCutterThread();
        cutterThread.setPhotoOverlayData(PhotoOverlayApp.photoOverlayData);
        BatchCreateDialog bd = new BatchCreateDialog(this, true, cutterThread);
        bd.setVisible(true);
    }

    private void createMenuItemActionPerformed(ActionEvent evt)
    {
        createButtonClick();
    }

    private void createButtonActionPerformed(ActionEvent evt)
    {
        createButtonClick();
    }

    private void helpAboutMenuItemActionPerformed(ActionEvent evt)
    {
        ImageIcon image = new ImageIcon((photooverlaycreator.PhotoMainFrame.class).getResource("images/camera32.gif"));
        String title = "Photo Overlay Creator";
        String copyright = "Created by the Centre for Advanced Spatial Analysis at UCL";
        String version = "Version: 1.0";
        String comments = "The PhotoOverlay Creator takes an image file (gif, jpeg, png or tiff) and builds a KML file for Google Earth containing the image as a PhotoOverlay. An image pyramid is created from the original file, allowing large images to be displayed on the web by breaking them up into smaller tiles which are only downloaded as required. The maximum zoom level of the image is calculated so that it matches the resolution of the original image file. Each successive zoom level then has half the pixel resolution of the last until the image fits within a single 256 pixel square tile. Depending on how the image was captured, it can be mapped on to a rectangle, cylinder or sphere in Google Earth. This allows spherical and cylindrical panoramas to be displayed, while regular photographs can use the rectangular mapping option. The field of view angles for Google Earth are automatically calculated based on the image size and the projection type, so the default create options will produce a working PhotoOverlay KML file. See the readme file for more information.";
        AboutBox aboutBox = new AboutBox(this, true, image, "Photo Overlay Creator", "Version: 1.0", "Created by the Centre for Advanced Spatial Analysis at UCL", "The PhotoOverlay Creator takes an image file (gif, jpeg, png or tiff) and builds a KML file for Google Earth containing the image as a PhotoOverlay. An image pyramid is created from the original file, allowing large images to be displayed on the web by breaking them up into smaller tiles which are only downloaded as required. The maximum zoom level of the image is calculated so that it matches the resolution of the original image file. Each successive zoom level then has half the pixel resolution of the last until the image fits within a single 256 pixel square tile. Depending on how the image was captured, it can be mapped on to a rectangle, cylinder or sphere in Google Earth. This allows spherical and cylindrical panoramas to be displayed, while regular photographs can use the rectangular mapping option. The field of view angles for Google Earth are automatically calculated based on the image size and the projection type, so the default create options will produce a working PhotoOverlay KML file. See the readme file for more information.");
        aboutBox.setVisible(true);
    }

    private void jMenuItem2ActionPerformed(ActionEvent evt)
    {
        System.exit(0);
    }

    private void jMenuItem1ActionPerformed(ActionEvent evt)
    {
        chooser.setAcceptAllFileFilterUsed(true);
        chooser.setFileFilter(new ImageFileFilter());
        chooser.setMultiSelectionEnabled(false);
        if(chooser.showOpenDialog(this) == 0)
{
        try
        {
        PhotoOverlayApp.imageData = ImageData.loadFile(chooser.getSelectedFile());
        if(PhotoOverlayApp.imageData.image == null)
        {
            Utils.showErrorDialogBox(this, "Error Loading Image", "The file " + chooser.getSelectedFile().getName() + " could not be loaded for some reason. Please ensure" + " that this file type can be handled by ImageIO and" + " install Java Advanced Imaging or any required image" + " reader extensions. See the readme file for more" + " information.", null);
            return;
        }

            int width = PhotoOverlayApp.imageData.image.getWidth();
            int height = PhotoOverlayApp.imageData.image.getHeight();
            PhotoOverlayApp.photoOverlayData.calculateDefaultFOV(width, height);
            int maxDepth = PhotoCutterThread.calculateMaxZoomDepth(width, height);
            jSlider1.setValue(maxDepth);
            jSlider1.setToolTipText("PhotoOverlay image pyramid depth calculated as zoom level " + maxDepth);
            int tileCount = PhotoCutterThread.calculateTotalTileCount(maxDepth, width, height);
            tileCountLabel.setText(Integer.toString(tileCount));
            centrePanel.setImage(PhotoOverlayApp.imageData.image);
            statusBar.setText(chooser.getSelectedFile().getName() + " " + PhotoOverlayApp.imageData.image.getWidth() + " x " + PhotoOverlayApp.imageData.image.getHeight() + " pixels");
            jLabel2.setEnabled(true);
            createMenuItem.setEnabled(true);
            createButton.setEnabled(true);
            jLabel1.setEnabled(true);
            tileCountLabel.setEnabled(true);
            centrePanel.repaint();
        }
        catch(Exception ex)
        {
            Utils.showErrorDialogBox(this, "Error loading image file", "Error loading image file " + chooser.getSelectedFile().getAbsolutePath(), ex);
        }
	}
    }

    public static void main(String args[])
    {
        EventQueue.invokeLater(new Runnable() {

            public void run()
            {
                (new PhotoMainFrame()).setVisible(true);
            }

        }
);
    }

    private DecimalFormat df;
    private static final String windowText = "Create Image Tiles - KML PhotoOverlay";
    private JFileChooser chooser;
    private ImageIcon mainIcon;
    private ImagePanel centrePanel;
    private JButton createButton;
    private JMenuItem createMenuItem;
    private JMenuItem helpAboutMenuItem;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JMenu jMenu1;
    private JMenu jMenu2;
    private JMenu jMenu3;
    private JMenuBar jMenuBar1;
    private JMenuItem jMenuItem1;
    private JMenuItem jMenuItem2;
    private JMenuItem jMenuItem3;
    private JSeparator jSeparator1;
    private JSeparator jSeparator2;
    private JSlider jSlider1;
    private JMenuItem kmlSettingsMenuItem;
    private JPanel rightPanel;
    private JLabel statusBar;
    private JLabel tileCountLabel;







}
