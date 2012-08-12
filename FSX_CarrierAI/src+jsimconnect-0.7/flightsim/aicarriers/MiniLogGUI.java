package flightsim.aicarriers;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import flightsim.simconnect.SimConnect;
import flightsim.simconnect.recv.ExceptionHandler;
import flightsim.simconnect.recv.OpenHandler;
import flightsim.simconnect.recv.RecvException;
import flightsim.simconnect.recv.RecvOpen;

public class MiniLogGUI {

	private JFrame mainFrame;
	private JTextArea logArea;
	private JLabel topLabel;

	
	public MiniLogGUI(AICarriersManager aicm) {
		mainFrame = new JFrame("AI Carriers r2");
		mainFrame.setSize(400, 200);
		mainFrame.setLocationRelativeTo(null);		// center on screen
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	// exit
		
		// main panel
		JPanel mainPanel = new JPanel(new BorderLayout());
		mainFrame.setContentPane(mainPanel);
		
		// log text
		logArea = new JTextArea();
		logArea.setEditable(false);
		mainPanel.add(new JScrollPane(logArea), BorderLayout.CENTER);
		
		// connection label
		topLabel = new JLabel("Not connected");
		topLabel.setBorder(BorderFactory.createEmptyBorder(5, 2, 5, 2));
		mainPanel.add(topLabel, BorderLayout.NORTH);
		
		// create sc manager
		try {
			// display open connection message
			aicm.getDispatcher().addOpenHandler(new OpenHandler(){
				public void handleOpen(SimConnect sender, final RecvOpen e) {
					SwingUtilities.invokeLater(new Runnable(){
						public void run() {
							setTextLabel("Connected to " + e.getApplicationName());
						}
					});
				}
			});
			// display simconnect exception
			aicm.getDispatcher().addExceptionHandler(new ExceptionHandler(){
				public void handleException(SimConnect sender, final RecvException e) {
					SwingUtilities.invokeLater(new Runnable(){
						public void run() {
							appendLogArea("Error: " +e.getException());
						}
					});
				}
			});
		} catch (Exception e) {
			appendLogArea(e.getMessage());
			setErrorLabel("Connection error");
		}
	}

	
	private void setErrorLabel(String msg) {
		topLabel.setText(msg);
		topLabel.setForeground(Color.red);
	}
	
	private void setTextLabel(String msg) {
		topLabel.setText(msg);
		topLabel.setForeground(Color.black);
	}
	
	private void appendLogArea(String msg) {
		logArea.append(msg);
		logArea.append("\n");
	}
	
	public void show() {
		mainFrame.setVisible(true);
	}
	

}
