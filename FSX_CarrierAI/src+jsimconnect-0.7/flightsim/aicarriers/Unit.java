package flightsim.aicarriers;

import flightsim.aicarriers.Formation.UnitPosition;

class Unit {

	private final UnitPosition position;
	private int containerId = -1;
	
	Unit(UnitPosition up) {
		this.position = up;
	}
	
	public int getContainerId() {
		return containerId;
	}

	public void setContainerId(int containerId) {
		this.containerId = containerId;
	}
	
	public UnitPosition getPosition() {
		return position;
	}
	
}
