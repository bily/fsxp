// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   NBCreateDialog.java

package imagetoolscommon;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

// Referenced classes of package imagetoolscommon:
//            TileObserver, ImageTileCutterThread, Utils

public class NBCreateDialog extends JDialog
    implements TileObserver
{

    public NBCreateDialog(Frame parent, String title, boolean modal, ImageTileCutterThread createThread)
    {
        super(parent, title, modal);
        this.createThread = createThread;
        createThread.setTileObserver(this);
        initComponents();
        updateDescriptiveText();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = getSize();
        if(frameSize.height > screenSize.height)
            frameSize.height = screenSize.height;
        if(frameSize.width > screenSize.width)
            frameSize.width = screenSize.width;
        setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
    }

    public void setWindowText(String text)
    {
        setTitle(text);
    }

    private void updateDescriptiveText()
    {
        String createDir = createThread.strCreateDir;
        int tileCount = createThread.totalTileCount;
        String text = createDir;
        String message = tileCount + " files will be created in: ";
        FontMetrics fm = getFontMetrics(jLabel1.getFont());
        int msgWidth = fm.stringWidth(message);
        do
        {
            if(text.length() <= 0)
                break;
            NBCreateDialog _tmp = this;
            if(fm.stringWidth(text) >= 1 - msgWidth)
                break;
            text = text.substring(0, text.length() - 1);
        } while(true);
        if(text.length() < createDir.length())
            text = text + "...";
        jLabel1.setToolTipText(createDir);
        jLabel1.setText(message + text);
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

    private void initComponents()
    {
        textPanel = new JPanel();
        jLabel1 = new JLabel();
        progressPanel = new JPanel();
        jProgressBar1 = new JProgressBar();
        buttonPanel = new JPanel();
        startButton = new JButton();
        cancelButton = new JButton();
        getContentPane().setLayout(new BorderLayout(4, 4));
        setDefaultCloseOperation(2);
        setMinimumSize(new Dimension(320, 120));
        setResizable(false);
        jLabel1.setText("jLabel1");
        textPanel.add(jLabel1);
        getContentPane().add(textPanel, "North");
        progressPanel.setLayout(new BorderLayout());
        jProgressBar1.setStringPainted(true);
        progressPanel.add(jProgressBar1, "South");
        getContentPane().add(progressPanel, "Center");
        startButton.setText("Start");
        startButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt)
            {
                startButtonActionPerformed(evt);
            }
        }
);
        buttonPanel.add(startButton);
        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt)
            {
                cancelButtonActionPerformed(evt);
            }
        }
);
        buttonPanel.add(cancelButton);
        getContentPane().add(buttonPanel, "South");
        pack();
    }

    private void cancelButtonActionPerformed(ActionEvent evt)
    {
        createThread.abortCreate();
        jLabel1.setText("Create aborted.");
        setVisible(false);
        dispose();
    }

    private void startButtonActionPerformed(ActionEvent evt)
    {
        doTileCreation();
    }

    private ImageTileCutterThread createThread;
    private JPanel buttonPanel;
    private JButton cancelButton;
    private JLabel jLabel1;
    private JProgressBar jProgressBar1;
    private JPanel progressPanel;
    private JButton startButton;
    private JPanel textPanel;


}
