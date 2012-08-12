// Decompiled by DJ v3.9.9.91 Copyright 2005 Atanas Neshkov  Date: 3/2/2008 2:50:45 PM
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   UpdateEngine.java

package com.zbluesoftware.fsxp.engine;

import com.zbluesoftware.fsxp.model.table.UpdateTableModel;
import com.zbluesoftware.fsxp.util.Utilities;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;
import java.util.zip.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

// Referenced classes of package com.zbluesoftware.fsxp.engine:
//            SettingsEngine, LogEngine, DTDResolver

public class UpdateEngine extends JDialog
    implements ActionListener, TableModelListener
{

    public UpdateEngine(Frame frame)
    {
        super(frame, "Updates...", true);
        parent = frame;
        init(new ArrayList());
    }

    public UpdateEngine(Frame frame, ArrayList updateAL)
    {
        super(frame, "Updates...", true);
        parent = frame;
        init(updateAL);
    }

    public UpdateEngine(Dialog dialog)
    {
        super(dialog, "Updates...", true);
        parent = dialog;
        init(new ArrayList());
    }

    private void init(ArrayList updateAL)
    {
        installActive = false;
        setDefaultCloseOperation(0);
        JLabel availableLabel = new JLabel("Available Updates: (check the 'U' checkbox to select the update)");
        availableLabel.setFont(Utilities.LABEL_FONT);
        availableLabel.setForeground(Color.black);
        updateTableModel = new UpdateTableModel();
        updateTableModel.addTableModelListener(this);
        JTable availableTable = new JTable(updateTableModel);
        availableTable.setFont(Utilities.LABEL_FONT);
        availableTable.setForeground(Color.black);
        availableTable.setBackground(Color.white);
        availableTable.setPreferredScrollableViewportSize(new Dimension(500, 75));
        availableTable.getTableHeader().setReorderingAllowed(false);
        availableTable.setAutoResizeMode(0);
        availableTable.getColumnModel().getColumn(0).setMinWidth(25);
        availableTable.getColumnModel().getColumn(0).setPreferredWidth(25);
        availableTable.getColumnModel().getColumn(1).setPreferredWidth(100);
        availableTable.getColumnModel().getColumn(1).setMinWidth(100);
        availableTable.getColumnModel().getColumn(2).setPreferredWidth(225);
        availableTable.getColumnModel().getColumn(2).setMinWidth(225);
        availableTable.getColumnModel().getColumn(3).setPreferredWidth(50);
        availableTable.getColumnModel().getColumn(3).setMinWidth(50);
        availableTable.getColumnModel().getColumn(4).setPreferredWidth(100);
        availableTable.getColumnModel().getColumn(4).setMinWidth(100);
        JScrollPane availableSP = new JScrollPane(availableTable);
        availableSP.getViewport().setBackground(Color.white);
        checkingLabel = new JLabel("Checking for updates");
        checkingLabel.setFont(Utilities.LABEL_FONT);
        checkingLabel.setForeground(Color.black);
        checkingBar = new JProgressBar();
        checkingBar.setIndeterminate(true);
        cancelButton = new JButton("Cancel");
        cancelButton.setFont(Utilities.DIALOG_BUTTON_FONT);
        cancelButton.setForeground(Color.black);
        cancelButton.addActionListener(this);
        installButton = new JButton("Install");
        installButton.setFont(Utilities.DIALOG_BUTTON_FONT);
        installButton.setForeground(Color.black);
        installButton.addActionListener(this);
        installButton.setEnabled(false);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(cancelButton);
        buttonPanel.add(installButton);
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        Utilities.addComponent(mainPanel, availableLabel, 0, 0, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, availableSP, 0, 1, 1, 1, 0, 10, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, checkingLabel, 0, 2, 1, 1, 0, 10, new Insets(10, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, checkingBar, 0, 3, 1, 1, 0, 10, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, buttonPanel, 0, 4, 1, 1, 0, 10, new Insets(10, 1, 10, 1), 0, 0, 0.0D, 0.0D);
        getContentPane().add(mainPanel);
        pack();
        setLocationRelativeTo(parent);
        if(updateAL.size() == 0)
        {
            checkForUpdates(this);
        } else
        {
            for(int i = 0; i < updateAL.size(); i++)
            {
                updateTableModel.addRow((HashMap) updateAL.get(i));
                setNewUpdates();
            }

        }
    }

    private void checkForUpdates(final Component parent)
    {
        Thread thread = new Thread() {

            public void run()
            {
                String version;
                StringBuffer previousUpdates;
                OutputStream writer;
                version = SettingsEngine.getInstance().getVersion();
                previousUpdates = new StringBuffer();
                SettingsEngine.getInstance().setLastUpdate(new Date());
                SettingsEngine.getInstance().writePreferences();
                try
                {
                    Preferences prefs = Preferences.systemNodeForPackage(com.zbluesoftware.fsxp.engine.UpdateEngine.class);
                    Preferences updatePrefs = prefs.node("updatePrefs");
                    String keys[] = updatePrefs.keys();
                    for(int i = 0; i < keys.length; i++)
                        if(!keys[i].equals("lastRun"))
                        {
                            if(previousUpdates.length() > 0)
                                previousUpdates.append(",");
                            previousUpdates.append(keys[i]);
                        }

                }
                catch(BackingStoreException bse)
                {
                    LogEngine.getInstance().log(bse);
                }
                writer = null;
                NodeList nodeList;
                StringBuffer stringBuffer = new StringBuffer();
try {
                stringBuffer.append("version=").append(URLEncoder.encode(version, "UTF-8")).append("&");
                stringBuffer.append("previousUpdates=").append(URLEncoder.encode(previousUpdates.toString(), "UTF-8"));
                byte request[] = stringBuffer.toString().getBytes();
                URL url = new URL("http://www.zbluesoftware.com:8080/ZBS/fsxplanner/FSXPUpdates");
                URLConnection conn = url.openConnection();
                conn.setDoInput(true);
                conn.setDoOutput(true);
                conn.setUseCaches(false);
                conn.setAllowUserInteraction(false);
                conn.setRequestProperty("Content-Length", Integer.toString(request.length));
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                writer = conn.getOutputStream();
                writer.write(request);
                writer.flush();
                stringBuffer = new StringBuffer();
                BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                factory.setValidating(true);
                DocumentBuilder builder = factory.newDocumentBuilder();
                builder.setEntityResolver(DTDResolver.getInstance());
                Document xmlDoc = builder.parse(bis, "updates.dtd");
                nodeList = xmlDoc.getElementsByTagName("updates");
                Element updatesElement = null;
                for(int i = 0; i < nodeList.getLength(); i++)
                    updatesElement = (Element)nodeList.item(i);

                nodeList = updatesElement.getElementsByTagName("update-item");
		if(nodeList.getLength() == 0) {
                JOptionPane.showMessageDialog(parent, "There are no new updates.", "Updates...", 1);
                setNoNewUpdates();
                closeDisplay();
		} else {
                for(int i = 0; i < nodeList.getLength(); i++)
                {
                    Element updateItemElement = (Element)nodeList.item(i);
                    String updateID = UpdateEngine.getNodeValue(updateItemElement.getElementsByTagName("update-id"));
                    String updateType = UpdateEngine.getNodeValue(updateItemElement.getElementsByTagName("type"));
                    String updateDesc = UpdateEngine.getNodeValue(updateItemElement.getElementsByTagName("desc"));
                    String updateSize = UpdateEngine.getNodeValue(updateItemElement.getElementsByTagName("size"));
                    String updateDate = UpdateEngine.getNodeValue(updateItemElement.getElementsByTagName("date"));
                    NodeList dependencies = updateItemElement.getElementsByTagName("dependency");
                    ArrayList dependentAL = new ArrayList();
                    for(int j = 0; j < dependencies.getLength(); j++)
                    {
                        NodeList children = dependencies.item(j).getChildNodes();
                        for(int k = 0; k < children.getLength(); k++)
                        {
                            Node child = children.item(k);
                            if(child.getNodeType() == 3)
                                dependentAL.add(child.getNodeValue());
                        }

                    }
                    addUpdateItem(updateID, updateType, updateDesc, updateSize, updateDate, dependentAL);
                }
                setNewUpdates();
		}
	}
catch (IOException ioe){
                LogEngine.getInstance().log(ioe);
                JOptionPane.showMessageDialog(parent, "Unable to connect to FSX Planner server.\n\nPlease try again later.", "Connection Error...", 0);
                setUnableToConnect();
		}
catch (SAXException saxe) { LogEngine.getInstance().log(saxe); }
catch (ParserConfigurationException pce) { LogEngine.getInstance().log(pce); }
finally {
                try
                {
                    if(writer != null)
                        writer.close();
                }
                catch(IOException ignored) { }
		}
			}

            /*final Component val$parent;
            final UpdateEngine this$0;
            {
                super();
				this$0 = UpdateEngine.this;
                parent = component;
            }*/
        };
        thread.setPriority(Thread.currentThread().getPriority() - 1);
        thread.start();
    }

    public static ArrayList checkForUpdates()
    {
        OutputStream writer;
        String version;
        StringBuffer previousUpdates;
		ArrayList updateAL;
        updateAL = new ArrayList();
        writer = null;
        version = SettingsEngine.getInstance().getVersion();
        previousUpdates = new StringBuffer();
        try
        {
            Preferences prefs = Preferences.systemNodeForPackage(com.zbluesoftware.fsxp.engine.UpdateEngine.class);
            Preferences updatePrefs = prefs.node("updatePrefs");
            String keys[] = updatePrefs.keys();
            for(int i = 0; i < keys.length; i++)
                if(!keys[i].equals("lastRun"))
                {
                    if(previousUpdates.length() > 0)
                        previousUpdates.append(",");
                    previousUpdates.append(keys[i]);
                }

        }
        catch(BackingStoreException bse)
        {
            LogEngine.getInstance().log(bse);
        }
try {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("version=").append(URLEncoder.encode(version, "UTF-8")).append("&");
        stringBuffer.append("previousUpdates=").append(URLEncoder.encode(previousUpdates.toString(), "UTF-8"));
        byte request[] = stringBuffer.toString().getBytes();
        URL url = new URL("http://www.zbluesoftware.com:8080/ZBS/fsxplanner/FSXPUpdates");
        URLConnection conn = url.openConnection();
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setUseCaches(false);
        conn.setAllowUserInteraction(false);
        conn.setRequestProperty("Content-Length", Integer.toString(request.length));
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        writer = conn.getOutputStream();
        writer.write(request);
        writer.flush();
        stringBuffer = new StringBuffer();
        BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(true);
        DocumentBuilder builder = factory.newDocumentBuilder();
        builder.setEntityResolver(DTDResolver.getInstance());
        Document xmlDoc = builder.parse(bis, "updates.dtd");
        NodeList nodeList = xmlDoc.getElementsByTagName("updates");
        Element updatesElement = null;
        for(int i = 0; i < nodeList.getLength(); i++)
            updatesElement = (Element)nodeList.item(i);

        nodeList = updatesElement.getElementsByTagName("update-item");
        for(int i = 0; i < nodeList.getLength(); i++)
        {
            Element updateItemElement = (Element)nodeList.item(i);
            String updateID = getNodeValue(updateItemElement.getElementsByTagName("update-id"));
            String updateType = getNodeValue(updateItemElement.getElementsByTagName("type"));
            String updateDesc = getNodeValue(updateItemElement.getElementsByTagName("desc"));
            String updateSize = getNodeValue(updateItemElement.getElementsByTagName("size"));
            String updateDate = getNodeValue(updateItemElement.getElementsByTagName("date"));
            NodeList dependencies = updateItemElement.getElementsByTagName("dependency");
            ArrayList dependentAL = new ArrayList();
            for(int j = 0; j < dependencies.getLength(); j++)
            {
                NodeList children = dependencies.item(j).getChildNodes();
                for(int k = 0; k < children.getLength(); k++)
                {
                    Node child = children.item(k);
                    if(child.getNodeType() == 3)
                        dependentAL.add(child.getNodeValue());
                }

            }

            HashMap hashMap = new HashMap();
            hashMap.put("ID", updateID);
            hashMap.put("Type", updateType);
            hashMap.put("Desc", updateDesc);
            hashMap.put("Size", updateSize);
            hashMap.put("Date", updateDate);
            hashMap.put("Dependencies", dependentAL);
            updateAL.add(hashMap);
        }
}
catch (IOException ioe) { LogEngine.getInstance().log(ioe); }
catch (SAXException saxe) { LogEngine.getInstance().log(saxe); }
catch (ParserConfigurationException pce) { LogEngine.getInstance().log(pce); }
finally {
        try
        {
            if(writer != null)
                writer.close();
        }
        catch(IOException ignored) { }
		}
        return updateAL;
    }

    private void setNoNewUpdates()
    {
        cancelButton.setText("Close");
        checkingLabel.setText("No new updates.");
        checkingBar.setIndeterminate(false);
    }

    private void setNewUpdates()
    {
        cancelButton.setText("Close");
        checkingLabel.setText("Select updates then click INSTALL");
        installActive = true;
        checkingBar.setIndeterminate(false);
    }

    private void setUnableToConnect()
    {
        cancelButton.setText("Close");
        checkingLabel.setText("Unable to connect to the FSX Planner server");
        installActive = true;
        checkingBar.setIndeterminate(false);
    }

    private void addUpdateItem(String updateID, String updateType, String updateDesc, String updateSize, String updateDate, ArrayList dependentAL)
    {
        HashMap hashMap = new HashMap();
        hashMap.put("ID", updateID);
        hashMap.put("Type", updateType);
        hashMap.put("Desc", updateDesc);
        hashMap.put("Size", updateSize);
        hashMap.put("Date", updateDate);
        hashMap.put("Dependencies", dependentAL);
        updateTableModel.addRow(hashMap);
    }

    private static String getNodeValue(NodeList nodeList)
    {
        for(int i = 0; i < nodeList.getLength(); i++)
        {
            NodeList children = nodeList.item(i).getChildNodes();
            for(int j = 0; j < children.getLength(); j++)
            {
                Node child = children.item(j);
                if(child.getNodeType() == 3)
                    return child.getNodeValue();
            }

        }

        return "";
    }

    private void installUpdates()
    {
        Thread thread = new Thread() {

            public void run()
            {
                ArrayList updates;
                File updateDir;
                int success;
                int i;
                updates = updateTableModel.getSelectedUpdates();
                if(updates.size() == 0)
                {
                    JOptionPane.showMessageDialog(parent, "No updates have been selected", "No Updates...", 0);
                    return;
                }
                installButton.setEnabled(false);
                String os = System.getProperty("os.name");
                updateDir = new File((new StringBuilder()).append(System.getProperty("user.dir")).append(File.separator).append("updates").toString());
                if(os.equals("Windows Vista"))
                    updateDir = new File((new StringBuilder()).append(System.getProperty("user.home")).append("\\downloads\\FSXPlannerUpdates").toString());
                else
                if(os.equals("Windows XP"))
                    updateDir = new File((new StringBuilder()).append(System.getProperty("user.home")).append("\\Application Data\\zBlueSoftware\\FSXPlanner\\updates").toString());
                else
                    updateDir = new File((new StringBuilder()).append(System.getProperty("user.dir")).append(File.separator).append("updates").toString());
                updateDir.mkdirs();
                success = 1;

for (i=0;i<updates.size();i++) {

                OutputStream writer;
                BufferedOutputStream bos;
                writer = null;
                bos = null;
                HashMap hashMap = (HashMap) updates.get(i);
                checkingLabel.setText((new StringBuilder()).append("Downloading update: ").append(hashMap.get("Desc")).toString());
                pack();
                int size = 0;
                try
                {
                    size = Integer.parseInt((String)hashMap.get("Size"));
                }
                catch(NumberFormatException nfe)
                {
                    size = 0;
                }
                checkingBar.setMaximum(size);
                checkingBar.setValue(0);
		try {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("id=").append(URLEncoder.encode((String)hashMap.get("ID"), "UTF-8")).append("&");
                byte request[] = stringBuffer.toString().getBytes();
                URL url = new URL("http://www.zbluesoftware.com:8080/ZBS/fsxplanner/FSXPDownloads");
                URLConnection conn = url.openConnection();
                conn.setDoInput(true);
                conn.setDoOutput(true);
                conn.setUseCaches(false);
                conn.setAllowUserInteraction(false);
                conn.setRequestProperty("Content-Length", Integer.toString(request.length));
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                writer = conn.getOutputStream();
                writer.write(request);
                writer.flush();
                stringBuffer = new StringBuffer();
                BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
                File file = new File((new StringBuilder()).append(updateDir.getAbsolutePath()).append(File.separator).append("update").append((String)hashMap.get("ID")).append(".zip").toString());
                bos = new BufferedOutputStream(new FileOutputStream(file));
                byte b[] = new byte[1024];
                for(int bytesRead = -1; (bytesRead = bis.read(b)) != -1;)
                {
                    bos.write(b, 0, bytesRead);
                    checkingBar.setValue(checkingBar.getValue() + bytesRead);
                }
                bos.flush();
            }
	catch (IOException ioe) {
                LogEngine.getInstance().log(ioe);
                JOptionPane.showMessageDialog(parent, "Unable to connect to FSX Planner server.\n\nPlease try again later.", "Connection Error...", 0);
                success = 0;
			}
	finally {
                try
                {
                    if(writer != null)
                        writer.close();
                    if(bos != null)
                        bos.close();
                }
                catch(IOException ignored) { }
			}
}

for (i=0;i<updates.size();i++){
                ZipInputStream zis;
                BufferedOutputStream bos;
                File zipFile;
                 HashMap hashMap = (HashMap) updates.get(i);
                checkingLabel.setText((new StringBuilder()).append("Expanding update: ").append((String)hashMap.get("Desc")).toString());
                pack();
                zis = null;
                bos = null;
		try {
                zipFile = new File((new StringBuilder()).append(updateDir.getAbsolutePath()).append(File.separator).append("update").append((String)hashMap.get("ID")).append(".zip").toString());
                if(!zipFile.exists())
                {
                    JOptionPane.showMessageDialog(parent, (new StringBuilder()).append("Unable to find the downloaded file at:\n").append(zipFile.getAbsolutePath()).toString(), "File Not Found Error", 0);
                    success = 0;
                }
                ZipFile zFile = null;
                try
                {
                    zFile = new ZipFile(zipFile);
                }
                catch(ZipException ze)
                {
                    LogEngine.getInstance().log(ze);
                    JOptionPane.showMessageDialog(parent, (new StringBuilder()).append("Unable to extract update: ").append((String)hashMap.get("Desc")).toString(), "Zip Error...", 0);
                    success = 0;
                }
                catch(IOException ioe)
                {
                    LogEngine.getInstance().log(ioe);
                    JOptionPane.showMessageDialog(parent, (new StringBuilder()).append("Unable to extract update: ").append((String)hashMap.get("Desc")).toString(), "Zip Error...", 0);
                    success = 0;
                }
                int size = 0;
                for(Enumeration enumeration = zFile.entries(); enumeration.hasMoreElements();)
                {
                    ZipEntry ze = (ZipEntry)enumeration.nextElement();
                    size = (int)((long)size + ze.getCompressedSize());
                }

                checkingBar.setMaximum(size);
                checkingBar.setValue(0);
                zis = new ZipInputStream(new FileInputStream(zipFile));
                for(ZipEntry zipEntry = null; (zipEntry = zis.getNextEntry()) != null;)
                {
                    File outputFile = new File((new StringBuilder()).append(updateDir.getAbsolutePath()).append(File.separator).append(zipEntry.getName()).toString());
                    bos = new BufferedOutputStream(new FileOutputStream(outputFile));
                    byte b[] = new byte[1024];
                    for(int bytesRead = -1; (bytesRead = zis.read(b, 0, 1024)) != -1;)
                    {
                        bos.write(b, 0, bytesRead);
                        checkingBar.setValue(checkingBar.getValue() + bytesRead);
                    }

                    bos.flush();
                    bos.close();
                }
			}
catch (IOException ioe) {
                LogEngine.getInstance().log(ioe);
                success = 0;
			}
	finally {
                try
                {
                    if(zis != null)
                        zis.close();
                    if(bos != null)
                        bos.close();
                }
                catch(IOException ignored) { }
			}
}

for (i=0;i<updates.size();i++){
                HashMap hashMap = (HashMap) updates.get(i);
                checkingLabel.setText((new StringBuilder()).append("Installing update: ").append((String)hashMap.get("Desc")).toString());
                pack();
                File updateFile = new File((new StringBuilder()).append(updateDir).append(File.separator).append("update").append((String)hashMap.get("ID")).append(".xml").toString());
                if(!updateFile.exists())
                {
                    JOptionPane.showMessageDialog(parent, (new StringBuilder()).append("Unable to find the downloaded file at:\n").append(updateFile.getAbsolutePath()).toString(), "File Not Found Error", 0);
                    success = 0;
                    continue; /* Loop/switch isn't completed */
                }
                try
                {
                    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                    factory.setValidating(true);
                    DocumentBuilder builder = factory.newDocumentBuilder();
                    builder.setEntityResolver(DTDResolver.getInstance());
                    Document xmlDoc = builder.parse(updateFile);
                    NodeList nodeList = xmlDoc.getElementsByTagName("update-item");
                    for(int j = 0; j < nodeList.getLength(); j++)
                        success = installUpdate(updateDir, (Element)nodeList.item(j));
                }
                catch(ParserConfigurationException pce)
                {
                    LogEngine.getInstance().log(pce);
                    success = 0;
                }
                catch(SAXException saxe)
                {
                    LogEngine.getInstance().log(saxe);
                    success = 0;
                }
                catch(IOException ioe)
                {
                    LogEngine.getInstance().log(ioe);
                }
                success = 0;
	}
                if(success != 0)
                {
                    if(success == 1)
                        checkingLabel.setText("Installation complete.  You will need to restart FSX Planner for the changes to take effect.");
                    else
                        checkingLabel.setText("To complete the update, please copy the appropriate files, as previously noted.");
                    cancelButton.setText("Quit FSX Planner");
                    pack();
                    for(i = 0; i < updates.size(); i++)
                    {
                        HashMap hashMap = (HashMap) updates.get(i);
                        updateTableModel.removeUpdateItem((String)hashMap.get("ID"));
                    }

                    Preferences prefs = Preferences.systemNodeForPackage(com.zbluesoftware.fsxp.engine.UpdateEngine.class);
                    Preferences updatePrefs = prefs.node("updatePrefs");
                    for( i = 0; i < updates.size(); i++)
                    {
                        HashMap hashMap = (HashMap) updates.get(i);
                        updatePrefs.put((String)hashMap.get("ID"), (String)hashMap.get("ID"));
                    }

                    SettingsEngine.getInstance().setLastUpdate(new Date(0L));
                    SettingsEngine.getInstance().writePreferences();
                } else
                {
                    checkingLabel.setText("Update Failed.");
                    cancelButton.setText("Close");
                    pack();
                }
                return;
            }

            /*final UpdateEngine this$0;
            {
                super();
				this$0 = UpdateEngine.this;
            }*/
        };
        thread.setPriority(Thread.currentThread().getPriority() - 1);
        thread.start();
    }

    private int installUpdate(File updateDir, Element updateItemElement)
    {
        NodeList nodeList = updateItemElement.getElementsByTagName("update-action");
        int totalUpdates = nodeList.getLength();
        float incValue = 100F / (float)totalUpdates;
        float pBarValue = incValue;
        checkingBar.setMaximum(100);
        int success = 1;
        for(int i = 0; i < nodeList.getLength(); i++)
        {
            Element updateActionElement = (Element)nodeList.item(i);
            String type = updateActionElement.getAttribute("type");
            if(type.equals("updateApplication"))
                success = updateApplication(updateDir, updateActionElement);
            checkingBar.setValue(Math.round(pBarValue));
            pBarValue += incValue;
        }

        return success;
    }

    public int updateApplication(File updateDir, Element updateActionElement)
    {
        int success = 1;
        NodeList hashmapElements = updateActionElement.getElementsByTagName("hashmap-element");
        for(int i = 0; i < hashmapElements.getLength(); i++)
        {
            Element hashmapElement = (Element)hashmapElements.item(i);
            String key = hashmapElement.getAttribute("name");
            String value = hashmapElement.getAttribute("value");
            if(key.equals("jarFile"))
            {
                File origJar = new File((new StringBuilder()).append(updateDir).append(File.separator).append(value).toString());
                File destJar = new File((new StringBuilder()).append(System.getProperty("user.dir")).append(File.separator).append(value).toString());
                success = copyFileToDir(origJar, destJar);
                continue;
            }
            if(key.equals("confFile"))
            {
                File origConf = new File((new StringBuilder()).append(updateDir).append(File.separator).append(value).toString());
                File destConf = new File((new StringBuilder()).append(System.getProperty("user.dir")).append(File.separator).append("conf").append(File.separator).append(value).toString());
                success = copyFileToDir(origConf, destConf);
                continue;
            }
            if(key.equals("dtdFile"))
            {
                File origDTD = new File((new StringBuilder()).append(updateDir).append(File.separator).append(value).toString());
                File destDTD = new File((new StringBuilder()).append(System.getProperty("user.dir")).append(File.separator).append("conf").append(File.separator).append("dtd").append(File.separator).append(value).toString());
                success = copyFileToDir(origDTD, destDTD);
                continue;
            }
            if(key.equals("dataFile"))
            {
                String os = System.getProperty("os.name");
                File origDTD = new File((new StringBuilder()).append(updateDir).append(File.separator).append(value).toString());
                File destDTD;
                if(os.equals("Windows Vista"))
                    destDTD = new File((new StringBuilder()).append(System.getProperty("user.home")).append("\\AppData\\Local\\VirtualStore\\Program Files\\zBlueSoftware\\FSXPlanner\\").append(value).toString());
                else
                if(os.equals("Windows XP"))
                    destDTD = new File((new StringBuilder()).append(System.getProperty("user.home")).append("\\Application Data\\zBlueSoftware\\FSXPlanner\\").append(value).toString());
                else
                    destDTD = new File((new StringBuilder()).append(System.getProperty("user.dir")).append(File.separator).append("conf").append(File.separator).append(value).toString());
                success = copyFileToDir(origDTD, destDTD);
                continue;
            }
            if(key.equals("version"))
            {
                SettingsEngine.getInstance().setVersion(value);
                SettingsEngine.getInstance().writePreferences();
            }
        }

        return success;
    }

    private int copyFileToDir(File file, File dir)
    {
        BufferedInputStream bis;
        BufferedOutputStream bos;
        bis = null;
        bos = null;
        if(!file.exists())
        {
            JOptionPane.showMessageDialog(this, (new StringBuilder()).append("The following update file could not be found:\n").append(file.getAbsolutePath()).toString(), "File Not Found Error", 0);
            return 0;
        }
try {
        bis = new BufferedInputStream(new FileInputStream(file));
        bos = new BufferedOutputStream(new FileOutputStream(dir));
        byte b[] = new byte[1024];
        for(int bytesRead = -1; (bytesRead = bis.read(b, 0, 1024)) != -1;)
            bos.write(b, 0, bytesRead);

        bos.flush();
	}
catch (IOException ioe) {
        LogEngine.getInstance().log(ioe);
        StringBuffer buffer = new StringBuffer();
        buffer.append("The following update file could not be copied:\n");
        buffer.append(file.getAbsolutePath());
        int success = 0;
        if(System.getProperty("os.name").equals("Windows Vista"))
        {
            buffer.append("\n");
            buffer.append("This is most likely due to Vista's security settings.\n\n");
            buffer.append("In order to complete the update you will need to copy the above\n");
            buffer.append("file into the following folder:\n");
            buffer.append(dir.getParent());
            success = -1;
        }
        JOptionPane.showMessageDialog(this, buffer.toString(), "File Copy Error", 0);
		return success;
	}
finally {
        try
        {
            if(bis != null)
                bis.close();
            if(bos != null)
                bos.close();
        }
        catch(IOException ignored) { }
        return 1;
	}
}

    private void closeDisplay()
    {
        checkingBar.setIndeterminate(false);
        setVisible(false);
        dispose();
        if(cancelButton.getText().equals("Quit FSX Planner"))
            System.exit(0);
    }

    public void actionPerformed(ActionEvent event)
    {
        if(event.getSource() == cancelButton)
            closeDisplay();
        else
        if(event.getSource() == installButton)
            installUpdates();
    }

    public void tableChanged(TableModelEvent event)
    {
        if(event.getType() == 0 || event.getType() == -1)
            installButton.setEnabled(installActive && updateTableModel.isUpdateSelected());
    }

    private Component parent;
    private JProgressBar checkingBar;
    private JButton cancelButton;
    private JButton installButton;
    private JLabel checkingLabel;
    private UpdateTableModel updateTableModel;
    private boolean installActive;
    private static final String UPDATE_URL = "http://www.zbluesoftware.com:8080/ZBS/fsxplanner/FSXPUpdates";
    private static final String DOWNLOAD_URL = "http://www.zbluesoftware.com:8080/ZBS/fsxplanner/FSXPDownloads";
}