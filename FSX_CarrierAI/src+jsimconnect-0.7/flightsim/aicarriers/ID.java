package flightsim.aicarriers;

import flightsim.simconnect.recv.RecvAssignedObjectID;
import flightsim.simconnect.recv.RecvEvent;
import flightsim.simconnect.recv.RecvSimObjectData;

public enum ID {
	
	DATA_DEF_POSITION,
	DATA_DEF_DESIRED_HEADING,
	
	REQUEST_USER_POSITION,
	REQUEST_WAYPOINT_LIST,
	REQUEST_AI_POSITION,
	REQUEST_AI_POSITION_FOR_LOCATE,
	REQUEST_ERROR_TEXT,
	
	REQUEST_CREATE_AI,
	REQUEST_DELETE_AI,
	
	EVENT_SIMSTART,
	EVENT_OBJECT_REMOVED,
	EVENT_TITLE_MENU,
	EVENT_TITLE_MENU_SHOW,
	EVENT_TITLE_MENU_AI_POSITION,
	
	EVENT_CALL_MENU,
	
	EVENT_MENU_SELECT_FORMATION,
	EVENT_MENU_SELECT_WAYPOINT,
	EVENT_MENU_SELECT_POSITION_TYPE,
	EVENT_MENU_SELECT_MOVE,
	
	EVENT_TEXT_FLEET_POSITION,
	
	EVENT_AI_THROTTLE,
	
	GROUP_KEYS,
	INPUT_KEYS,
	
	
	LAST;		// placeholder
	
	public boolean isEvent(RecvEvent e) {
		return e.getEventID() == ordinal();
	}
	
	public boolean isRequest(RecvSimObjectData e) {
		return e.getRequestID() == ordinal();
	}
	
	public boolean isRequest(RecvAssignedObjectID e) {
		return e.getRequestID() == ordinal();
	}

}
