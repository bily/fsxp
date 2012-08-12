// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CreateDialog.java

package imagetoolscommon;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

// Referenced classes of package imagetoolscommon:
//            CreateDialog_startButton_actionAdapter, CreateDialog_cancelButton_actionAdapter, TileObserver, ImageTileCutterThread, 
//            Utils

public class CreateDialog extends JDialog
    implements TileObserver
{

    public CreateDialog(Frame owner, String title, boolean modal, ImageTileCutterThread createThread)
    {
        super(owner, title, modal);
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
            this.createThread = createThread;
            createThread.setTileObserver(this);
            setDefaultCloseOperation(2);
            jbInit();
            updateDescriptiveText();
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

    public void setWindowText(String text)
    {
        setTitle(text);
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
        String createDir = createThread.strCreateDir;
        int tileCount = createThread.totalTileCount;
        String text = createDir;
        if(text.length() > 30)
            text = text.substring(0, 30) + "...";
        jLabel1.setToolTipText(createDir);
        jLabel1.setText(tileCount + " files will be created in: " + text);
    }

    public void processingTile(String filename, int count, int totalCount)
    {
        jLabel1.setText("Tile: " + filename);
        if(count > 0)
        {
            int percent = (count * 100) / totalCount;
            jProgressBar1.setValue(percent);
            jProgressBar1.setString(Integer.toString(percent) + "%");
        }
    }

    public void finishedProcessingTiles(int totalCount)
    {
        jProgressBar1.setValue(100);
        jProgressBar1.setString("100%");
        jLabel1.setText("Successfully created " + totalCount + " tiles");
        cancelButton.setText("Finish");
    }

    public void doTileCreation()
    {
        cancelButton.setEnabled(true);
        startButton.setEnabled(false);
        try
        {
            createThread.start();
        }
        catch(Exception ex)
        {
            createThread.abortCreate();
            jLabel1.setText("Create aborted.");
            Utils.showErrorDialogBox(this, "Error Creating Tiles", null, ex);
        }
    }

    public void startButton_actionPerformed(ActionEvent e)
    {
        doTileCreation();
    }

    public void cancelButton_actionPerformed(ActionEvent e)
    {
        createThread.abortCreate();
        jLabel1.setText("Create aborted.");
        setVisible(false);
        dispose();
    }

    private ImageTileCutterThread createThread;
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
