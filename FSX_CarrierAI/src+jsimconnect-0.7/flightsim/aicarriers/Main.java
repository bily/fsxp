package flightsim.aicarriers;

import java.io.File;

import javax.swing.SwingUtilities;

import flightsim.simconnect.SimConnect;
import flightsim.simconnect.recv.ExceptionHandler;
import flightsim.simconnect.recv.RecvException;

public class Main {
	
	private static void createAndShowGUI(AICarriersManager aicm) {
		MiniLogGUI gui = new MiniLogGUI(aicm);
		gui.show();
	}
	
	private static String installDirFromClasspath() {
		String prop = System.getProperty("java.class.path");
		String[] cpElts = prop.split(File.pathSeparator);
		for (String cp : cpElts) {
			if (cp.toLowerCase().endsWith("aicarriers.jar")) {
				// found it
				File f = new File(cp);
				return f.getParent();
			}
		}
		return System.getProperty("user.dir");
	}

	
	public static void main(String[] args) throws Exception {
		final AICarriersManager aicm;
		
		try {
			aicm = new AICarriersManager(installDirFromClasspath()); 
		} catch (Exception e) {
			org.lc0277lib.gui.LookAndFeel.showExceptionErrorDialog(e);
			return;
		}
		
		
		boolean noGui = false;
		
		if (args.length != 0)
			if (args[0].equalsIgnoreCase("-nogui")) noGui = true;
		
		if (!noGui) {
			SwingUtilities.invokeAndWait(new Runnable(){
				public void run() {
					createAndShowGUI(aicm);
				}
			});
		} else {
			// someting more discreet
			aicm.getDispatcher().addExceptionHandler(new ExceptionHandler(){
				public void handleException(SimConnect sender, RecvException e) {
					System.out.println("Error: " + e.getException() + " packet " + e.getSendID() + " arg " +e.getIndex());
				}
			});
		}
		
		aicm.run();
	}
}
