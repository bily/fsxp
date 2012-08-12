package flightsim.aicarriers;

import java.io.IOException;

import flightsim.simconnect.SimConnect;
import flightsim.simconnect.SimConnectConstants;
import flightsim.simconnect.SimConnectDataType;
import flightsim.simconnect.SimConnectPeriod;
import flightsim.simconnect.recv.*;
import flightsim.simconnect.wrappers.*;

class UserTracker implements SimObjectDataHandler, OpenHandler {

	// note that annotations are not used at all
	
	@FlightSimData(variable="PLANE LATITUDE", units="radians")
	private double userLat;
	@FlightSimData(variable="PLANE LONGITUDE", units="radians")
	private double userLon;
	@FlightSimData(variable="PLANE HEADING DEGREES TRUE", units="radians")
	private double userHdg;
	@FlightSimData(variable="MAGVAR", units="radians")
	private double magVar;
	
	UserTracker(DispatcherTask dt) {
		dt.addSimObjectDataHandler(this);
		dt.addOpenHandler(this);
	}
	
	public void handleSimObject(SimConnect sender, RecvSimObjectData e) {
		if (ID.REQUEST_USER_POSITION.isRequest(e)) {
			e.reset();
			userLat = e.getDataFloat64();
			userLon = e.getDataFloat64();
			userHdg = e.getDataFloat64();
			magVar = e.getDataFloat64();
		}
	}
	
	public void handleOpen(SimConnect sc, RecvOpen e) {
		// user position
		try {
			sc.addToDataDefinition(ID.DATA_DEF_POSITION, 
					"PLANE LATITUDE", "radians", 
					SimConnectDataType.FLOAT64);
			sc.addToDataDefinition(ID.DATA_DEF_POSITION, 
					"PLANE LONGITUDE", "radians", 
					SimConnectDataType.FLOAT64);
			sc.addToDataDefinition(ID.DATA_DEF_POSITION, 
					"PLANE HEADING DEGREES TRUE", "radians", 
					SimConnectDataType.FLOAT64);
			sc.addToDataDefinition(ID.DATA_DEF_POSITION, 
					"MAGVAR", "radians", 
					SimConnectDataType.FLOAT64);
			sc.requestDataOnSimObject(ID.REQUEST_USER_POSITION, 
					ID.DATA_DEF_POSITION, 
					SimConnectConstants.OBJECT_ID_USER, 
					SimConnectPeriod.SIM_FRAME);
		} catch (IOException e1) {}
	}

	public double getUserHdg() {
		return userHdg;
	}

	public double getUserLat() {
		return userLat;
	}

	public double getUserLon() {
		return userLon;
	}
	
	public double getMagVar() {
		return magVar;
	}
	
	
	
	

}
