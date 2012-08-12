// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CreateDialog.java

package gmapimagecutter;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

// Referenced classes of package gmapimagecutter:
//            CreateDialog_startButton_actionAdapter, CreateDialog_cancelButton_actionAdapter, ImageTileCutterThread, TileObserver, 
//            GMapImgCutApp, Utils

public class CreateDialog extends JDialog
    implements TileObserver
{

    public CreateDialog(Frame owner, String title, boolean modal)
    {
        super(owner, title, modal);
        createDir = "";
        depth = 0;
        highQuality = true;
        tileCount = 0;
        progressPanel = new JPanel();
        jProgressBar1 = new JProgressBar();
        startButton = new JButton();
        cancelButton = new JButton();
        jLabel1 = new JLabel();
        borderLayout1 = new BorderLayout();
        textPanel = new JPanel();
        buttonPanel = new JPanel();
        borderLayout2 = new BorderLayout();
        flowLayout1 = new FlowLayout();
        try
        {
            setDefaultCloseOperation(2);
            jbInit();
            pack();
            setSize(320, 120);
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            Dimension frameSize = getSize();
            if(frameSize.height > screenSize.height)
                frameSize.height = screenSize.height;
            if(frameSize.width > screenSize.width)
                frameSize.width = screenSize.width;
            setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
    }

    public CreateDialog()
    {
        this(new Frame(), "CreateDialog", false);
    }

    public void setCreateDir(String createDir)
    {
        this.createDir = createDir;
        updateDescriptiveText();
    }

    public void setDepth(int depth)
    {
        this.depth = depth;
        tileCount = ImageTileCutterThread.calculateTotalTileCount(depth);
        updateDescriptiveText();
    }

    public void setHighQuality(boolean highQuality)
    {
        this.highQuality = highQuality;
        if(this.highQuality)
            setTitle("Create Image Tiles - High Quality (slow)");
        else
            setTitle("Create Image Tiles - Low Quality (fast)");
    }

    private void jbInit()
        throws Exception
    {
        startButton.setText("Start");
        startButton.addActionListener(new CreateDialog_startButton_actionAdapter(this));
        cancelButton.setEnabled(true);
        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new CreateDialog_cancelButton_actionAdapter(this));
        jLabel1.setText("jLabel1");
        getContentPane().setLayout(borderLayout1);
        progressPanel.setLayout(borderLayout2);
        jProgressBar1.setMinimumSize(new Dimension(128, 18));
        jProgressBar1.setStringPainted(true);
        setResizable(false);
        borderLayout1.setHgap(4);
        borderLayout1.setVgap(4);
        progressPanel.setMinimumSize(new Dimension(256, 18));
        buttonPanel.setMinimumSize(new Dimension(256, 33));
        textPanel.setMinimumSize(new Dimension(256, 25));
        textPanel.setLayout(flowLayout1);
        buttonPanel.add(startButton);
        buttonPanel.add(cancelButton);
        getContentPane().add(buttonPanel, "South");
        getContentPane().add(progressPanel, "Center");
        getContentPane().add(textPanel, "North");
        progressPanel.add(jProgressBar1, "South");
        textPanel.add(jLabel1, null);
    }

    private void updateDescriptiveText()
    {
        String text = createDir;
        if(text.length() > 30)
            text = (new StringBuilder()).append(text.substring(0, 30)).append("...").toString();
        jLabel1.setToolTipText(createDir);
        jLabel1.setText((new StringBuilder()).append(tileCount).append(" files will be created in: ").append(text).toString());
    }

    public void processingTile(String filename, int count, int totalCount)
    {
        jLabel1.setText((new StringBuilder()).append("Tile: ").append(filename).toString());
        if(tileCount > 0)
        {
            int percent = (count * 100) / totalCount;
            jProgressBar1.setValue(percent);
            jProgressBar1.setString((new StringBuilder()).append(Integer.toString(percent)).append("%").toString());
        }
    }

    public void finishedProcessingTiles(int totalCount)
    {
        jProgressBar1.setValue(100);
        jLabel1.setText((new StringBuilder()).append("Successfully created ").append(totalCount).append(" tiles").toString());
        cancelButton.setText("Finish");
    }

    public void startButton_actionPerformed(ActionEvent e)
    {
        cancelButton.setEnabled(true);
        startButton.setEnabled(false);
        try
        {
            GMapImgCutApp.imgTileCutThread = new ImageTileCutterThread();
            GMapImgCutApp.imgTileCutThread.setParams(this, GMapImgCutApp.image, GMapImgCutApp.createDir, GMapImgCutApp.filename, depth, highQuality);
            GMapImgCutApp.imgTileCutThread.start();
        }
        catch(Exception ex)
        {
            GMapImgCutApp.imgTileCutThread.abortCreate();
            jLabel1.setText("Create aborted.");
            Utils.showErrorDialogBox(this, "Error Creating Tiles", null, ex);
        }
    }

    public void cancelButton_actionPerformed(ActionEvent e)
    {
        GMapImgCutApp.imgTileCutThread.abortCreate();
        jLabel1.setText("Create aborted.");
        setVisible(false);
        dispose();
    }

    private static final String windowText = "Create Image Tiles";
    private static final String highQualityText = "High Quality (slow)";
    private static final String lowQualityText = "Low Quality (fast)";
    private String createDir;
    private int depth;
    private boolean highQuality;
    private int tileCount;
    JPanel progressPanel;
    JProgressBar jProgressBar1;
    JButton startButton;
    JButton cancelButton;
    JLabel jLabel1;
    BorderLayout borderLayout1;
    JPanel textPanel;
    JPanel buttonPanel;
    BorderLayout borderLayout2;
    FlowLayout flowLayout1;
}
