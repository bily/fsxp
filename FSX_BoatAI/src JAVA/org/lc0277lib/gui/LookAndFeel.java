package org.lc0277lib.gui;

import java.awt.Component;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class LookAndFeel {
	
	/** 
	 *  set a good-looking look and feel (gtk on linux, windows else)
	 */
	public static void setPlatformLookAndFeel() {
		// set up Windows or GTK look and feel depending
		// on the operating system
		try {
			String os = System.getProperty("os.name"); //$NON-NLS-1$
			if (os.toLowerCase().contains("linux")) UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");  //$NON-NLS-1$ //$NON-NLS-2$
			else UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");  //$NON-NLS-1$
		} catch (Exception e) {}
	}
	
	public static void showExceptionErrorDialog(Component parent, Throwable t) {
		StringWriter sw = new StringWriter();
		t.printStackTrace(new PrintWriter(sw));
		JOptionPane.showMessageDialog(parent, sw.toString(), "Error", JOptionPane.ERROR_MESSAGE);
		
	}
	
	public static void showExceptionErrorDialog(Throwable t) {
		showExceptionErrorDialog(null, t);
		
	}

}
