// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BatchCreateDialog.java

package imagetoolscommon;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import javax.swing.*;
import org.jdesktop.layout.GroupLayout;

// Referenced classes of package imagetoolscommon:
//            ImageTileCutterThread, ImageFileFilter, TileObserver, ImageData, 
//            Utils

public class BatchCreateDialog extends JDialog
    implements TileObserver
{

    public BatchCreateDialog(Frame parent, boolean modal)
    {
        super(parent, modal);
        chooser = new JFileChooser();
        fileList = new Vector();
        processedFileCount = 0;
        skippedFileCount = 0;
        logFilename = null;
    }

    public BatchCreateDialog(Frame parent, boolean modal, ImageTileCutterThread cutterThread)
    {
        super(parent, modal);
        chooser = new JFileChooser();
        fileList = new Vector();
        processedFileCount = 0;
        skippedFileCount = 0;
        logFilename = null;
        this.cutterThread = cutterThread;
        this.cutterThread.setTileObserver(this);
        initComponents();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = getSize();
        if(frameSize.height > screenSize.height)
            frameSize.height = screenSize.height;
        if(frameSize.width > screenSize.width)
            frameSize.width = screenSize.width;
        setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
    }

    public void processingTile(String filename, int count, int totalCount)
    {
        progressLabel.setText("Tile: " + filename);
        if(totalCount > 0)
        {
            int percent = (count * 100) / totalCount;
            jProgressBar1.setValue(percent);
            jProgressBar1.setString(Integer.toString(percent) + "%");
        }
    }

    public void finishedProcessingTiles(int totalCount)
    {
        processedFileCount++;
        logWriter.println("Successfully created " + totalCount + " tiles.");
        logWriter.flush();
        jProgressBar1.setValue(100);
        progressLabel.setText("Successfully created " + totalCount + " tiles");
        doProcessing();
    }

    public void doProcessing()
    {
        ImageData imageData;
        for(imageData = null; fileList.size() > 0 && imageData == null; todoList.setListData(fileList))
        {
            File file = (File)fileList.get(0);
            logWriter.println("Processing " + file.getAbsolutePath());
            logWriter.flush();
            try
            {
                imageData = ImageData.loadFile(file);
            }
            catch(Exception ex) { }
            if(imageData == null)
            {
                skippedFileCount++;
                logWriter.println("Error: file " + file.getAbsolutePath() + " could not be loaded - skipping image");
                logWriter.flush();
            }
            fileList.remove(0);
        }

        if(imageData == null)
        {
            String msg = "";
            if(processedFileCount == 1)
                msg = "1 file processed successfully, ";
            else
                msg = processedFileCount + " files processed successfully, ";
            if(skippedFileCount == 0)
                msg = msg + "no errors.";
            else
            if(skippedFileCount == 1)
                msg = msg + "1 file failed due to errors.";
            else
                msg = msg + skippedFileCount + " files failed due to errors.";
            logWriter.println(msg);
            logWriter.close();
            currentFileLabel.setText(msg);
            progressLabel.setText("Batch convert complete.");
            cancelButton.setText("Finish");
            viewLogButton.setEnabled(true);
            return;
        }
        String text = "Processing: " + imageData.filename;
        if(fileList.size() > 0)
            text = text + " (" + fileList.size() + " remaining)";
        currentFileLabel.setText(text);
        ImageTileCutterThread thread = null;
        try
        {
            thread = (ImageTileCutterThread)cutterThread.clone();
            ImageTileCutterThread _tmp = thread;
            int depth = ImageTileCutterThread.calculateMaxZoomDepth(imageData.image.getWidth(), imageData.image.getHeight());
            thread.setImageData(imageData);
            thread.start();
        }
        catch(Exception ex)
        {
            skippedFileCount++;
            thread.abortCreate();
            progressLabel.setText("Create aborted.");
            logWriter.println("Error processing " + imageData.filename + " - aborted");
            ex.printStackTrace(logWriter);
        }
    }

    private void initComponents()
    {
        jScrollPane1 = new JScrollPane();
        todoList = new JList();
        addButton = new JButton();
        removeButton = new JButton();
        cancelButton = new JButton();
        startButton = new JButton();
        jPanel1 = new JPanel();
        progressLabel = new JLabel();
        jProgressBar1 = new JProgressBar();
        currentFileLabel = new JLabel();
        viewLogButton = new JButton();
        setDefaultCloseOperation(2);
        setTitle("Batch Process Files");
        todoList.setModel(new AbstractListModel() {

            public int getSize()
            {
                return strings.length;
            }

            public Object getElementAt(int i)
            {
                return strings[i];
            }

            String strings[] = {
                "Add files to be processed.", "Use <SHIFT> or <CTRL> keys to select multiple files."
            };

        }
);
        jScrollPane1.setViewportView(todoList);
        addButton.setText("Add Files...");
        addButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt)
            {
                addButtonActionPerformed(evt);
            }

        }
);
        removeButton.setText("Remove");
        removeButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt)
            {
                removeButtonActionPerformed(evt);
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
        startButton.setText("Start");
        startButton.setEnabled(false);
        startButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt)
            {
                startButtonActionPerformed(evt);
            }
        }
);
        jPanel1.setBorder(BorderFactory.createTitledBorder("Progress"));
        progressLabel.setText("Waiting...");
        jProgressBar1.setStringPainted(true);
        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(1).add(jPanel1Layout.createSequentialGroup().addContainerGap().add(jPanel1Layout.createParallelGroup(1).add(jProgressBar1, -1, 348, 32767).add(progressLabel).add(currentFileLabel)).addContainerGap()));
        jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(1).add(2, jPanel1Layout.createSequentialGroup().add(currentFileLabel).addPreferredGap(0, -1, 32767).add(progressLabel).addPreferredGap(0).add(jProgressBar1, -2, -1, -2).addContainerGap()));
        viewLogButton.setText("View Log...");
        viewLogButton.setEnabled(false);
        viewLogButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt)
            {
                viewLogButtonActionPerformed(evt);
            }
        }
);
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(1).add(2, layout.createSequentialGroup().addContainerGap().add(layout.createParallelGroup(2).add(1, jPanel1, -1, -1, 32767).add(layout.createSequentialGroup().add(viewLogButton).addPreferredGap(0).add(startButton).addPreferredGap(0).add(cancelButton).addPreferredGap(0)).add(layout.createSequentialGroup().add(jScrollPane1, -1, 287, 32767).addPreferredGap(0).add(layout.createParallelGroup(1, false).add(removeButton, -1, -1, 32767).add(addButton, -1, -1, 32767)))).addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(1).add(layout.createSequentialGroup().addContainerGap().add(layout.createParallelGroup(2).add(1, layout.createSequentialGroup().add(addButton).addPreferredGap(0).add(removeButton)).add(1, jScrollPane1, -2, 164, -2)).addPreferredGap(0).add(jPanel1, -2, -1, -2).add(20, 20, 20).add(layout.createParallelGroup(3).add(startButton).add(cancelButton).add(viewLogButton)).add(22, 22, 22)));
        pack();
    }

   private void viewLogButtonActionPerformed(ActionEvent evt)
  {
    JTextArea textArea = new JTextArea(10, 80);
    textArea.setLineWrap(true);
    textArea.setWrapStyleWord(true);
    JScrollPane scrollPane = new JScrollPane(textArea);
    textArea.setText("");
    try
    {
      BufferedReader br = new BufferedReader(new FileReader(logFilename));
	  String line;
          while ((line = br.readLine()) != null)
			textArea.append(line + "\n");
	  br.close();
    }
    catch (Exception ex)
    {
      StringWriter sw = new StringWriter();
      PrintWriter pw = new PrintWriter(sw);
      ex.printStackTrace(pw);
      textArea.append("Exception:\n" + sw.getBuffer().toString());
    }
    textArea.setCaretPosition(0);
    JOptionPane.showMessageDialog(this, scrollPane, logFilename, 1);
  }

    private void removeButtonActionPerformed(ActionEvent evt)
    {
        int idx = todoList.getSelectedIndex();
        if(idx >= 0)
        {
            fileList.remove(idx);
            todoList.setListData(fileList);
        }
        if(fileList.size() <= 0)
            startButton.setEnabled(false);
    }

    private void addButtonActionPerformed(ActionEvent evt)
    {
        chooser.setAcceptAllFileFilterUsed(true);
        chooser.setFileFilter(new ImageFileFilter());
        chooser.setMultiSelectionEnabled(true);
        if(chooser.showOpenDialog(this) == 0)
        {
            File files[] = chooser.getSelectedFiles();
            for(int i = 0; i < files.length; i++)
                fileList.add(files[i]);

            todoList.setListData(fileList);
            startButton.setEnabled(true);
        }
    }

    private void cancelButtonActionPerformed(ActionEvent evt)
    {
        cutterThread.abortCreate();
        if(logWriter != null)
        {
            logWriter.write("Batch convert terminated at user request.");
            logWriter.close();
        }
        progressLabel.setText("Create aborted.");
        setVisible(false);
        dispose();
    }

    private void startButtonActionPerformed(ActionEvent evt)
    {
        startButton.setEnabled(false);
        cancelButton.setEnabled(true);
        addButton.setEnabled(false);
        removeButton.setEnabled(false);
        try
        {
            File firstFile = (File)fileList.get(0);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd_HHmmss");
            logFilename = firstFile.getParent() + File.separator + "batchprocess_" + formatter.format(new Date()) + ".log";
            logWriter = new PrintWriter(new FileWriter(logFilename));
            doProcessing();
        }
        catch(Exception ex)
        {
            Utils.showErrorDialogBox(this, "Error in batch export", "Error in batch export initialisation", ex);
        }
    }

    public static void main(String args[])
    {
        EventQueue.invokeLater(new Runnable() {

            public void run()
            {
                (new BatchCreateDialog(new JFrame(), true)).setVisible(true);
            }

        }
);
    }

    private ImageTileCutterThread cutterThread;
    private JFileChooser chooser;
    private Vector fileList;
    private int processedFileCount;
    private int skippedFileCount;
    private PrintWriter logWriter;
    private String logFilename;
    private JButton addButton;
    private JButton cancelButton;
    private JLabel currentFileLabel;
    private JPanel jPanel1;
    private JProgressBar jProgressBar1;
    private JScrollPane jScrollPane1;
    private JLabel progressLabel;
    private JButton removeButton;
    private JButton startButton;
    private JList todoList;
    private JButton viewLogButton;





}
