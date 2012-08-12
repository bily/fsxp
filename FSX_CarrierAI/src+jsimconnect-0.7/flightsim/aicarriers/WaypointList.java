package flightsim.aicarriers;

import java.text.*;
import java.util.*;

import org.lc0277lib.geography.GeoCalc;

import flightsim.simconnect.*;
import flightsim.simconnect.recv.*;

class WaypointList extends ListMenu<FacilityWaypoint> implements FacilitiesListHandler  {
	
	private List<FacilityWaypoint> wpList = new ArrayList<FacilityWaypoint>();;
	private WaypointComparator comparator = new WaypointComparator();
	
	private NumberFormat df;
	private UserTracker userTracker;
	
	WaypointList(UserTracker ut, DispatcherTask dt) {
		super(ID.EVENT_MENU_SELECT_WAYPOINT, "Select waypoint");
		this.userTracker = ut;
		
		df = DecimalFormat.getNumberInstance();
		df.setMinimumFractionDigits(2);
		dt.addFacilitiesListHandler(this);
	}

	public void handleAirportList(SimConnect sender, RecvAirportList list) {
	}
	
	public void handleNDBList(SimConnect sender, RecvNDBList list) {
	}
	
	public void handleVORList(SimConnect sender, RecvVORList list) {
	}
	
	public void handleWaypointList(SimConnect sender, RecvWaypointList list) {
		if (list.getEntryNumber() == 0) {
			wpList.clear();
			// reset start index
			setStartIndex(0);
		}
		
		for (FacilityWaypoint wp : list.getFacilities()) {
			wpList.add(wp);
		}
		
		// last packet
		if ((list.getEntryNumber()+1) >= list.getOutOf()) {
			// fix empty list by adding a dumb waypoint
			// at current user position
			if (wpList.size() == 0) {
				wpList.add(new FacilityWaypoint("No waypoints available, display FS map to load them",
						Math.toDegrees(userTracker.getUserLat()),
						Math.toDegrees(userTracker.getUserLon()),
						0, 0));
			}
			
			// sort by distance
			Collections.sort(wpList, comparator);
			showMenu(sender);
		}
	}
	
	class WaypointComparator implements Comparator<FacilityWaypoint> {
		public int compare(FacilityWaypoint o1, FacilityWaypoint o2) {
			double d1 = distance(o1);
			double d2 = distance(o2);
			return (int) Math.signum(d1 - d2); 
		}
	}
	
	private double distance(FacilityWaypoint wp) {
		return GeoCalc.distanceRadians(userTracker.getUserLat(), 
				userTracker.getUserLon(), 
				Math.toRadians(wp.getLatitude()), 
				Math.toRadians(wp.getLongitude()));
	}

	private double heading(FacilityWaypoint wp) {
		return GeoCalc.headingRadians(userTracker.getUserLat(), 
				userTracker.getUserLon(), 
				Math.toRadians(wp.getLatitude()), 
				Math.toRadians(wp.getLongitude()));
	}

	@Override
	protected FacilityWaypoint get(int index) {
		return wpList.get(index);
	}
	
	@Override
	protected int size() {
		return wpList.size();
	}
	
	@Override
	protected String toString(FacilityWaypoint wp) {
		double d = distance(wp) / 1000;
		double head = heading(wp);
		return wp.getIcao() + " (" + df.format(d) + " km, " +
			df.format(Math.toDegrees(head)) + "°)";

	}
	
	
	
}
