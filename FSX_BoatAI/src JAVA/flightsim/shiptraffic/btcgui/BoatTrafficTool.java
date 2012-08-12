// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BoatTrafficTool.java

package flightsim.shiptraffic.btcgui;

import flightsim.shiptraffic.BoatRecord;
import flightsim.shiptraffic.BoatRoute;
import flightsim.shiptraffic.PlanRecord;
import flightsim.shiptraffic.ProgressAdapter;
import flightsim.shiptraffic.TrafficBGLFile;
import flightsim.shiptraffic.TrafficProject;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.MessageFormat;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.prefs.PreferenceChangeEvent;
import java.util.prefs.PreferenceChangeListener;
import java.util.prefs.Preferences;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.ProgressMonitor;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;
import kml.KMLWriter;
import org.lc0277lib.gui.LookAndFeel;
import org.lc0277lib.gui.SwingWorker;

// Referenced classes of package flightsim.shiptraffic.btcgui:
//            I18N, DirListModel, ProgressDialog, ProgressAdapterRandomAccessFile

public class BoatTrafficTool
    implements ActionListener, PreferenceChangeListener, ListSelectionListener, MouseListener
{
    private class ProgressMonitorAdapter extends ProgressMonitor
        implements ProgressAdapter
    {

        public void increment()
        {
            setValue(++value);
        }

        public void setSection(String note, int nops)
        {
            setNote(note);
            setMinimum(0);
            setValue(0);
            setMaximum(nops + 1);
            try
            {
                Thread.sleep(20L);
            }
            catch(InterruptedException interruptedexception) { }
        }

        public void setValue(int nv)
        {
            value = nv;
            super.setProgress(nv);
        }

        private int value;
        final BoatTrafficTool this$0;

        public ProgressMonitorAdapter(Component parentComponent, Object message, String note, int min, int max)
        {
            super(parentComponent, message, note, min, max);
			this$0 = BoatTrafficTool.this;
            setMillisToDecideToPopup(0);
            setMillisToPopup(0);
            value = min;
        }
    }

    private class FileListCellRenderer extends DefaultListCellRenderer
    {

        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
        {
            Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if(value instanceof File)
            {
                File f = (File)value;
                setText(f.getName());
            }
            return c;
        }

        final BoatTrafficTool this$0;

        private FileListCellRenderer()
        {
            super();
            this$0 = BoatTrafficTool.this;
        }

        FileListCellRenderer(FileListCellRenderer filelistcellrenderer)
        {
            this();
        }
    }


    BoatTrafficTool()
    {
        frame = new JFrame("Boat Traffic Compiler");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(3);
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage(flightsim.shiptraffic.btcgui.BoatTrafficTool.class.getResource("tabicon_boats.gif")));
        JMenuBar menuBar = new JMenuBar();
        JMenu filesMenu = new JMenu(I18N._("BoatTrafficTool.FilesMenu"));
        filesMenu.setMnemonic(70);
        filesMenu.add(createItem(I18N._("BoatTrafficTool.SetSrcMenu"), "SetSrcFolder"));
        filesMenu.add(createItem(I18N._("BoatTrafficTool.SetDstMenu"), "SetDstFolder"));
        filesMenu.addSeparator();
        filesMenu.add(createItem(I18N._("BoatTrafficTool.QuitMenu"), "Quit"));
        menuBar.add(filesMenu);
        JMenu convertMenu = new JMenu(I18N._("BoatTrafficTool.Convert"));
        convertMenu.add(createItem(I18N._("BoatTrafficTool.KML2CSV"), "KML2CSV"));
        convertMenu.add(createItem(I18N._("BoatTrafficTool.CSV2KML"), "CSV2KML"));
        menuBar.add(convertMenu);
        JMenu helpMenu = new JMenu(I18N._("BoatTrafficTool.HelpMenu"));
        helpMenu.add(createItem(I18N._("BoatTrafficTool.AboutMenu"), "About"));
        menuBar.add(helpMenu);
        frame.setJMenuBar(menuBar);
        prefs = Preferences.userNodeForPackage(flightsim.shiptraffic.btcgui.BoatTrafficTool.class);
        prefs.addPreferenceChangeListener(this);
        if(prefs.get("srcDir", null) == null)
            prefs.put("srcDir", "C:\\");
        if(prefs.get("dstDir", null) == null)
            prefs.put("dstDir", "C:\\");
        javax.swing.ListCellRenderer lcl = new FileListCellRenderer(null);
        srcDirListModel = new DirListModel(prefs.get("srcDir", "C:\\"), new String[] {
            "kml", "kmz", "txt", "csv"
        });
        srcDirList = new JList(srcDirListModel);
        srcDirList.setCellRenderer(lcl);
        srcDirList.getSelectionModel().setSelectionMode(2);
        srcDirList.addListSelectionListener(this);
        srcDirList.addMouseListener(this);
        dstDirListModel = new DirListModel(prefs.get("dstDir", "C:\\"), "bgl");
        dstDirList = new JList(dstDirListModel);
        dstDirList.setCellRenderer(lcl);
        dstDirList.getSelectionModel().setSelectionMode(0);
        dstDirList.addListSelectionListener(this);
        dstDirList.addMouseListener(this);
        JPanel upPanel = new JPanel(new GridLayout(1, 3));
        srcDirLabel = new JLabel(srcDirListModel.getCurrentDir().getAbsolutePath(), 0);
        dstDirLabel = new JLabel(dstDirListModel.getCurrentDir().getAbsolutePath(), 0);
        upPanel.add(makeListPanel(I18N._("BoatTrafficTool.SourceFiles"), srcDirList, srcDirLabel));
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, 3));
        centerPanel.add(Box.createVerticalGlue());
        compileButton = new JButton(I18N._("BoatTrafficTool.Compile"));
        centerPanel.add(makeArrowButton("Compile", "fleche2.gif", compileButton));
        centerPanel.add(Box.createVerticalGlue());
        decompileButton = new JButton(I18N._("BoatTrafficTool.Decompile"));
        centerPanel.add(makeArrowButton("Decompile", "fleche.gif", decompileButton));
        centerPanel.add(Box.createVerticalGlue());
        upPanel.add(centerPanel);
        upPanel.add(makeListPanel(I18N._("BoatTrafficTool.TrafficFiles"), dstDirList, dstDirLabel));
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(upPanel, "Center");
        area = new JTextPane();
        area.setPreferredSize(new Dimension(0x7fffffff, 150));
        area.setContentType("text/html");
        area.setEditable(false);
        sdoc = area.getStyledDocument();
        initializeStyles(sdoc);
        mainPanel.add(new JScrollPane(area, 22, 30), "South");
        mainPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        frame.getContentPane().add(mainPanel);
        checkPath(srcDirListModel);
        checkPath(dstDirListModel);
    }

    private void checkPath(DirListModel dlm)
    {
        File path = dlm.getCurrentDir();
        if(path != null && !path.exists())
            error(null, (new StringBuilder("Path ")).append(path).append(" does not exists").toString());
    }

    private JPanel makeArrowButton(String cmdName, String iconName, JButton jb)
    {
        JPanel jp = new JPanel();
        jp.setLayout(new BoxLayout(jp, 2));
        jp.add(new JLabel(createImageIcon(iconName), 0));
        jb.setEnabled(false);
        jb.setActionCommand(cmdName);
        jb.addActionListener(this);
        jp.add(jb);
        jp.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        jp.add(new JLabel(createImageIcon(iconName), 0));
        return jp;
    }

    private JPanel makeListPanel(String headerText, JList jl, JLabel southLbl)
    {
        JPanel jp = new JPanel(new BorderLayout());
        JLabel northLbl = new JLabel(headerText, 0);
        jl.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        jp.add(northLbl, "North");
        jp.add(new JScrollPane(jl), "Center");
        jp.add(southLbl, "South");
        southLbl.setBorder(BorderFactory.createBevelBorder(1));
        jp.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        return jp;
    }

    private JMenuItem createItem(String name, String command)
    {
        JMenuItem item = new JMenuItem(name);
        item.setActionCommand(command);
        item.addActionListener(this);
        return item;
    }

    protected ImageIcon createImageIcon(String path)
    {
        java.net.URL imgURL = flightsim.shiptraffic.btcgui.BoatTrafficTool.class.getResource(path);
        if(imgURL != null)
        {
            return new ImageIcon(imgURL);
        } else
        {
            System.err.println((new StringBuilder("Couldn't find file: ")).append(path).toString());
            return null;
        }
    }

    public void actionPerformed(ActionEvent e)
    {
        String cmd = e.getActionCommand();
        if("Quit".equals(cmd))
            System.exit(0);
        else
        if("About".equals(cmd))
            JOptionPane.showMessageDialog(frame, (new StringBuilder("Boat Traffic Compiler 0.1\n")).append(I18N._("BoatTrafficTool.About0")).append("\n").append(I18N._("BoatTrafficTool.About1")).toString(), I18N._("BoatTrafficTool.About"), 1);
        else
        if("SetSrcFolder".equals(cmd))
            selectDir("srcDir", srcDirListModel);
        else
        if("SetDstFolder".equals(cmd))
            selectDir("dstDir", dstDirListModel);
        else
        if("Decompile".equals(cmd))
            doDecompile();
        else
        if("Compile".equals(cmd))
            doCompile();
        else
        if("CSV2KML".equals(cmd))
            doConvertCSV2KML();
        else
        if("KML2CSV".equals(cmd))
            doConvertKML2CSV();
    }

    private void doDecompile()
    {
        int dstSel = dstDirList.getSelectedIndex();
        if(dstSel == -1)
            return;
        final File f = dstDirListModel.getFileAt(dstSel);
        if(f == null)
        {
            return;
        } else
        {
            final ProgressMonitorAdapter pm = new ProgressMonitorAdapter(frame, I18N._("BoatTrafficTool.DecompileBGL"), I18N._("BoatTrafficTool.ReadingFile"), 0, (int)f.length());
            SwingWorker sw = new SwingWorker() {

                public Object construct()
                {
                    TrafficBGLFile tfbg = null;
                    try
                    {
                        ProgressAdapterRandomAccessFile para = new ProgressAdapterRandomAccessFile(f, "r", pm);
                        tfbg = new TrafficBGLFile(para, 82);
                        String suffix = f.getName();
                        if(suffix.toLowerCase().startsWith("traffic"))
                            suffix = suffix.substring(7);
                        int ip = suffix.lastIndexOf('.');
                        if(ip != -1)
                            suffix = suffix.substring(0, ip);
                        pm.setSection(I18N._("BoatTrafficTool.WritingTextFiles"), 3);
                        File routesOut = new File(srcDirListModel.getCurrentDir(), (new StringBuilder("Routes")).append(suffix).append(".kml").toString());
                        KMLWriter kmlWriter = new KMLWriter();
                        BoatRoute br;
                        for(Iterator iterator = tfbg.getBoatRoutes().iterator(); iterator.hasNext(); kmlWriter.addPath(br.getRoute(), (new StringBuilder(String.valueOf(br.getRouteId()))).toString()))
                            br = (BoatRoute)iterator.next();

                        kmlWriter.write(routesOut);
                        pm.increment();
                        File boatsOut = new File(srcDirListModel.getCurrentDir(), (new StringBuilder("Boats")).append(suffix).append(".txt").toString());
                        PrintStream ps = new PrintStream(new FileOutputStream(boatsOut));
                        BoatRecord br1;
                        for(Iterator iterator1 = tfbg.getBoatRecords().iterator(); iterator1.hasNext(); ps.println(br1.toTrafficString()))
                            br1 = (BoatRecord)iterator1.next();
                        ps.flush();
                        ps.close();
                        pm.increment();
                        File plansOut = new File(srcDirListModel.getCurrentDir(), (new StringBuilder("Plans")).append(suffix).append(".txt").toString());
                        ps = new PrintStream(new FileOutputStream(plansOut));
                        PlanRecord pl;
                        for(Iterator iterator2 = tfbg.getPlanRecords().iterator(); iterator2.hasNext(); ps.println(pl.toTrafficString()))
                            pl = (PlanRecord)iterator2.next();

                        ps.flush();
                        ps.close();
                        pm.increment();
                        pm.close();
                        srcDirListModel.resetCurrentDir();
                    }
                    catch(Exception e)
                    {
                        e.printStackTrace();
                        return e;
                    }
                    return null;
                }

                public void finished()
                {
                    pm.close();
                    Object o = get();
                    if(o != null && (o instanceof Exception))
                    {
                        Exception ex = (Exception)o;
                        error(null, (new StringBuilder("Cannot decompile traffic: ")).append(ex.getLocalizedMessage()).toString());
                        JOptionPane.showMessageDialog(frame, MessageFormat.format(I18N._("BoatTrafficTool.CannotDecompile"), new Object[] {
                            f.getAbsolutePath(), ex.getLocalizedMessage()
                        }), I18N._("BoatTrafficTool.Error"), 0);
                    } else
                    if(o == null)
                        info(null, "Decompiled traffic");
                }

                final BoatTrafficTool this$0;
            {
                this$0 = BoatTrafficTool.this;
            }
            };
            sw.start();
            return;
        }
    }

    private void doCompile()
    {
        final int selIdx[] = srcDirList.getSelectedIndices();
        if(selIdx == null || selIdx.length != 3)
        {
            return;
        } else
        {
            areaReset();
            final ProgressDialog pma = new ProgressDialog(frame, I18N._("BoatTrafficTool.Compiling"), I18N._("BoatTrafficTool.Compiling"));
            SwingWorker sw = new SwingWorker() {

                public Object construct()
                {
                    File acFile = null;
                    File fpFile = null;
                    File rtFile = null;
                    for(int i = 0; i < 3; i++)
                    {
                        File f = srcDirListModel.getFileAt(selIdx[i]);
                        String name = f.getName().toLowerCase();
                        if(name.startsWith("route"))
                            rtFile = f;
                        else
                        if(name.startsWith("plan"))
                            fpFile = f;
                        else
                        if(name.startsWith("boat"))
                            acFile = f;
                    }

                    if(acFile == null || fpFile == null || rtFile == null)
                    {
                        error(null, I18N._("BoatTrafficTool.CannotFindTextFIles"));
                        return null;
                    }
                    String suffix = acFile.getName().substring(5);
                    int ip = suffix.lastIndexOf('.');
                    if(ip != -1)
                        suffix = suffix.substring(0, ip);
                    try
                    {
                        TrafficProject project = new TrafficProject(acFile, rtFile, fpFile, pma);
                        File outFile = new File(dstDirListModel.getCurrentDir(), (new StringBuilder("traffic")).append(suffix).append(".bgl").toString());
                        project.compile(outFile, pma);
                        pma.setSection(I18N._("BoatTrafficTool.InsertingArrivalTimes"), 1);
                        project.inserArrivals(fpFile);
                        pma.increment();
                        return outFile;
                    }
                    catch(Exception e)
                    {
                        e.printStackTrace();
                        return e;
                    }
                }

                public void finished()
                {
                    pma.close();
                    Object o = getValue();
                    if(o instanceof Exception)
                    {
                        Exception ex = (Exception)o;
                        error(null, MessageFormat.format(I18N._("BoatTrafficTool.CannotReadTraffic"), new Object[] {
                            ex.getLocalizedMessage()
                        }));
                    }
                    if(o instanceof File)
                    {
                        dstDirListModel.resetCurrentDir();
                        info(null, (new StringBuilder(String.valueOf(I18N._("BoatTrafficTool.CompiledTrafficTo")))).append(o).toString());
                    }
                }

                final BoatTrafficTool this$0;           
            {
                this$0 = BoatTrafficTool.this;
            }
            };
            sw.start();
            return;
        }
    }

    private void selectDir(String prop, DirListModel listModel)
    {
        JFileChooser jfc = new JFileChooser();
        jfc.setFileSelectionMode(1);
        if(listModel.getCurrentDir() != null)
            jfc.setCurrentDirectory(listModel.getCurrentDir());
        int i = jfc.showOpenDialog(frame);
        if(i != 0)
        {
            return;
        } else
        {
            File f = jfc.getSelectedFile();
            prefs.put(prop, f.getAbsolutePath());
            return;
        }
    }

    private File askFile(final String ext, final String description)
    {
        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(0);
        fc.setFileFilter(new FileFilter() {

            public boolean accept(File f)
            {
                if(f.isDirectory())
                    return true;
                return f.getName().toLowerCase().endsWith((new StringBuilder(".")).append(ext).toString());
            }

            public String getDescription()
            {
                return description;
            }

            final BoatTrafficTool this$0;
            {
                this$0 = BoatTrafficTool.this;
            }
        }
);
        int res = fc.showOpenDialog(frame);
        if(res == 0)
            return fc.getSelectedFile();
        else
            return null;
    }

    private void doConvertKML2CSV()
    {
        final File kmlFile = askFile("kml", I18N._("BoatTrafficTool.GEData"));
        if(kmlFile == null)
        {
            return;
        } else
        {
            SwingWorker sw = new SwingWorker() {

                public Object construct()
                {
                    try
                    {
                        Map brtes = BoatRoute.readKMLBoatRoutes(kmlFile);
                        String csvFileName = kmlFile.getAbsolutePath();
                        int ip = csvFileName.lastIndexOf('.');
                        if(ip != -1)
                            csvFileName = csvFileName.substring(0, ip);
                        File csvFile = new File((new StringBuilder(String.valueOf(csvFileName))).append(".csv").toString());
                        PrintStream ps = new PrintStream(csvFile);
                        BoatRoute br;
                        for(Iterator iterator = brtes.values().iterator(); iterator.hasNext(); br.printCSV(ps))
                            br = (BoatRoute)iterator.next();

                        ps.flush();
                        ps.close();
                        return csvFile;
                    }
                    catch(Exception e)
                    {
                        return e;
                    }
                }

                public void finished()
                {
                    Object val = get();
                    if(val != null && (val instanceof Exception))
                    {
                        Exception e = (Exception)val;
                        JOptionPane.showMessageDialog(frame, MessageFormat.format(I18N._("BoatTrafficTool.CannotExport0"), new Object[] {
                            e.getMessage()
                        }), I18N._("BoatTrafficTool.Error"), 0);
                    }
                    if(val != null && (val instanceof File))
                    {
                        File f = (File)val;
                        info(null, (new StringBuilder("Exported to ")).append(f.getAbsolutePath()).toString());
                        JOptionPane.showMessageDialog(frame, MessageFormat.format(I18N._("BoatTrafficTool.Export0"), new Object[] {
                            f
                        }), I18N._("BoatTrafficTool.Export"), 1);
                    }
                }

                final BoatTrafficTool this$0;
            {
                this$0 = BoatTrafficTool.this;
            }
            };
            sw.start();
            return;
        }
    }

    private void doConvertCSV2KML()
    {
        final File csvFile = askFile("csv", I18N._("BoatTrafficTool.CSVData"));
        if(csvFile == null)
        {
            return;
        } else
        {
            SwingWorker sw = new SwingWorker() {

                public Object construct()
                {
                    try
                    {
                        Map brtes = BoatRoute.readFS10CSVBoatRoutes(csvFile);
                        String kmlFileName = csvFile.getAbsolutePath();
                        int ip = kmlFileName.lastIndexOf('.');
                        if(ip != -1)
                            kmlFileName = kmlFileName.substring(0, ip);
                        File kmlFile = new File((new StringBuilder(String.valueOf(kmlFileName))).append(".kml").toString());
                        KMLWriter wr = new KMLWriter();
                        BoatRoute br;
                        for(Iterator iterator = brtes.values().iterator(); iterator.hasNext(); wr.addPath(br.getRoute(), (new StringBuilder(String.valueOf(br.getRouteId()))).toString()))
                            br = (BoatRoute)iterator.next();

                        wr.write(kmlFile);
                        return kmlFile;
                    }
                    catch(Exception e)
                    {
                        return e;
                    }
                }

                public void finished()
                {
                    Object val = get();
                    if(val != null && (val instanceof Exception))
                    {
                        Exception e = (Exception)val;
                        JOptionPane.showMessageDialog(frame, MessageFormat.format(I18N._("BoatTrafficTool.CannotExport1"), new Object[] {
                            e.getMessage()
                        }), I18N._("BoatTrafficTool.Error"), 0);
                    }
                    if(val != null && (val instanceof File))
                    {
                        File f = (File)val;
                        info(null, (new StringBuilder("Exported to ")).append(f.getAbsolutePath()).toString());
                        JOptionPane.showMessageDialog(frame, MessageFormat.format(I18N._("BoatTrafficTool.Export1"), new Object[] {
                            f
                        }), I18N._("BoatTrafficTool.Export"), 1);
                    }
                }

                final BoatTrafficTool this$0;
            {
                this$0 = BoatTrafficTool.this;
            }
            };
            sw.start();
            return;
        }
    }

    public void preferenceChange(PreferenceChangeEvent evt)
    {
        String propName = evt.getKey();
        if(propName.equals("dstDir"))
        {
            dstDirListModel.setCurrentDir(evt.getNewValue());
            if(evt.getNewValue() != null)
                dstDirLabel.setText(evt.getNewValue());
        }
        if(propName.equals("srcDir"))
        {
            srcDirListModel.setCurrentDir(evt.getNewValue());
            if(evt.getNewValue() != null)
                srcDirLabel.setText(evt.getNewValue());
        }
    }

    public void show()
    {
        frame.setVisible(true);
    }

    private void initializeStyles(StyledDocument doc)
    {
        javax.swing.text.Style def = StyleContext.getDefaultStyleContext().getStyle("default");
        javax.swing.text.Style s = doc.addStyle("regular", def);
        s = doc.addStyle("red", def);
        StyleConstants.setForeground(s, Color.RED);
        s = doc.addStyle("redbold", def);
        StyleConstants.setForeground(s, Color.RED);
        StyleConstants.setBold(s, true);
        s = doc.addStyle("reditalic", def);
        StyleConstants.setForeground(s, Color.RED);
        StyleConstants.setItalic(s, true);
        s = doc.addStyle("bold", def);
        StyleConstants.setBold(s, true);
        s = doc.addStyle("italic", def);
        StyleConstants.setItalic(s, true);
    }

    private void areaAppend(String s, String style)
    {
        try
        {
            sdoc.insertString(sdoc.getLength(), s, sdoc.getStyle(style));
            area.setCaretPosition(sdoc.getLength());
        }
        catch(BadLocationException e)
        {
            e.printStackTrace();
        }
    }

    private void areaReset()
    {
        try
        {
            sdoc.remove(0, sdoc.getLength());
        }
        catch(BadLocationException e)
        {
            e.printStackTrace();
        }
    }

    public void error(Object o, String message)
    {
        areaAppend(I18N._("CheckWindow.error"), "redbold");
        areaAppend("   ", "redbold");
        if(o instanceof BoatRecord)
        {
            BoatRecord br = (BoatRecord)o;
            areaAppend((new StringBuilder("(")).append(I18N._("CheckWindow.boat")).append(" ").append(br.getIdentifier()).append(")").toString(), "reditalic");
        }
        if(o instanceof BoatRoute)
        {
            BoatRoute br = (BoatRoute)o;
            areaAppend((new StringBuilder("(")).append(I18N._("CheckWindow.route")).append(" ").append(br.getRouteId()).append(" ").append(br.getName()).append(")").toString(), "reditalic");
        }
        if(o instanceof PlanRecord)
        {
            PlanRecord plr = (PlanRecord)o;
            areaAppend((new StringBuilder("(")).append(I18N._("CheckWindow.plan")).append(" ").append(plr.getName()).append(")").toString(), "reditalic");
        }
        areaAppend(" ", "red");
        areaAppend(message, "red");
        areaAppend("\n", "red");
    }

    public void info(Object o, String message)
    {
        areaAppend(I18N._("CheckWindow.info"), "bold");
        areaAppend("   ", "bold");
        if(o instanceof BoatRecord)
        {
            BoatRecord br = (BoatRecord)o;
            areaAppend((new StringBuilder("(")).append(I18N._("CheckWindow.boat")).append(" ").append(br.getIdentifier()).append(")").toString(), "italic");
        }
        if(o instanceof BoatRoute)
        {
            BoatRoute br = (BoatRoute)o;
            areaAppend((new StringBuilder("(")).append(I18N._("CheckWindow.route")).append(" ").append(br.getRouteId()).append(" ").append(br.getName()).append(")").toString(), "italic");
        }
        if(o instanceof PlanRecord)
        {
            PlanRecord plr = (PlanRecord)o;
            areaAppend((new StringBuilder("(")).append(I18N._("CheckWindow.plan")).append(" ").append(plr.getName()).append(")").toString(), "italic");
        }
        areaAppend(" ", "regular");
        areaAppend(message, "regular");
        areaAppend("\n", "regular");
    }

    public void valueChanged(ListSelectionEvent e)
    {
        if(e.getValueIsAdjusting())
            return;
        if(e.getSource() == srcDirList)
        {
            int idxs[] = srcDirList.getSelectedIndices();
            if(idxs == null || idxs.length != 3)
                compileButton.setEnabled(false);
            else
                compileButton.setEnabled(true);
        }
        if(e.getSource() == dstDirList)
        {
            int idx = dstDirList.getSelectedIndex();
            if(idx == -1)
                decompileButton.setEnabled(false);
            else
                decompileButton.setEnabled(true);
        }
    }

    public void mouseClicked(MouseEvent e)
    {
        if(e.getSource() == srcDirList && e.getButton() == 1)
        {
            int idx = srcDirList.getSelectedIndex();
            File f = srcDirListModel.getFileAt(idx);
            if(f == null)
                return;
            int idxs[] = (int[])null;
            String fName = f.getName().toLowerCase();
            if(fName.startsWith("boats"))
            {
                String suffix = fName.substring(5);
                int ip = suffix.lastIndexOf('.');
                if(ip != -1)
                    suffix = suffix.substring(0, ip);
                int planIdx = srcDirListModel.getIndex((new StringBuilder("plans")).append(suffix).append(".txt").toString());
                int routesIdx = srcDirListModel.getIndex((new StringBuilder("routes")).append(suffix).append(".kml").toString());
                if(routesIdx == -1)
                    routesIdx = srcDirListModel.getIndex((new StringBuilder("routes")).append(suffix).append(".kmz").toString());
                if(routesIdx == -1)
                    routesIdx = srcDirListModel.getIndex((new StringBuilder("routes")).append(suffix).append(".csv").toString());
                if(planIdx != -1 && routesIdx != -1)
                    idxs = (new int[] {
                        idx, planIdx, routesIdx
                    });
            }
            if(fName.startsWith("routes"))
            {
                String suffix = fName.substring(6);
                int ip = suffix.lastIndexOf('.');
                if(ip != -1)
                    suffix = suffix.substring(0, ip);
                int planIdx = srcDirListModel.getIndex((new StringBuilder("plans")).append(suffix).append(".txt").toString());
                int boatsIdx = srcDirListModel.getIndex((new StringBuilder("boats")).append(suffix).append(".txt").toString());
                if(planIdx != -1 && boatsIdx != -1)
                    idxs = (new int[] {
                        idx, planIdx, boatsIdx
                    });
            }
            if(fName.startsWith("plans"))
            {
                String suffix = fName.substring(5);
                int ip = suffix.lastIndexOf('.');
                if(ip != -1)
                    suffix = suffix.substring(0, ip);
                int boatsIdx = srcDirListModel.getIndex((new StringBuilder("boats")).append(suffix).append(".txt").toString());
                int routesIdx = srcDirListModel.getIndex((new StringBuilder("routes")).append(suffix).append(".kml").toString());
                if(routesIdx == -1)
                    routesIdx = srcDirListModel.getIndex((new StringBuilder("routes")).append(suffix).append(".kmz").toString());
                if(routesIdx == -1)
                    routesIdx = srcDirListModel.getIndex((new StringBuilder("routes")).append(suffix).append(".csv").toString());
                if(boatsIdx != -1 && routesIdx != -1)
                    idxs = (new int[] {
                        idx, boatsIdx, routesIdx
                    });
            }
            if(idxs != null)
                srcDirList.setSelectedIndices(idxs);
            else
                srcDirList.setSelectedIndex(idx);
        }
    }

    public void mouseEntered(MouseEvent mouseevent)
    {
    }

    public void mouseExited(MouseEvent mouseevent)
    {
    }

    public void mousePressed(MouseEvent mouseevent)
    {
    }

    public void mouseReleased(MouseEvent mouseevent)
    {
    }

    public static void main(String args[])
    {
        LookAndFeel.setPlatformLookAndFeel();
        BoatTrafficTool bt = new BoatTrafficTool();
        bt.show();
    }

    private JFrame frame;
    private DirListModel srcDirListModel;
    private DirListModel dstDirListModel;
    private JList srcDirList;
    private JList dstDirList;
    private JLabel srcDirLabel;
    private JLabel dstDirLabel;
    private JButton compileButton;
    private JButton decompileButton;
    private JTextPane area;
    private StyledDocument sdoc;
    private Preferences prefs;
    public static final String ACTION_SET_SRC_DIR = "SetSrcFolder";
    public static final String ACTION_SET_DST_DIR = "SetDstFolder";
    public static final String ACTION_QUIT = "Quit";
    public static final String ACTION_ABOUT = "About";
    public static final String ACTION_COMPILE = "Compile";
    public static final String ACTION_DECOMPILE = "Decompile";
    public static final String ACTION_CSV2KML = "CSV2KML";
    public static final String ACTION_KML2CSV = "KML2CSV";
    public static final String PREF_SRCDIR = "srcDir";
    public static final String PREF_DSTDIR = "dstDir";
    public static final String PREF_SRCDIR_DEFAULT = "C:\\";
    public static final String PREF_DSTDIR_DEFAULT = "C:\\";
    public static final String TITLE = "Boat Traffic Compiler";
    public static final String VERSION = "0.1";



}
