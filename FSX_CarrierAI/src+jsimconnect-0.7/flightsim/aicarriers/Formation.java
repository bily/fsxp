package flightsim.aicarriers;
import java.util.*;

public class Formation implements Iterable<Formation.UnitPosition> {

	private List<UnitPosition> units = new ArrayList<UnitPosition>();
	private String title;
	
	
	public Formation(String title) {
		this.title = title;
	}
	
	public Iterator<UnitPosition> iterator() {
		return units.iterator();
	}
	
	Formation addUnit(UnitPosition up) {
		units.add(up);
		return this;
	}
	
	Formation addUnit(String container, double x, double y) {
		return addUnit(new UnitPosition(container, x, y));
	}
	
	public String getTitle() {
		return title;
	}
	
	public int size() {
		return units.size();
	}

	public UnitPosition getUnitPosition(int i) {
		return units.get(i);
	}

	@Override
	public String toString() {
		return title + ": " + units.toString();
	}
	

	class UnitPosition {

		private String containerTitle;
		private double xOffset;
		private double yOffset;
		
		UnitPosition(String containerTitle, double offset, double offset2) {
			super();
			this.containerTitle = containerTitle;
			xOffset = offset;
			yOffset = offset2;
		}
		
		public String getContainerTitle() {
			return containerTitle;
		}
		public double getXOffset() {
			return xOffset;
		}
		public double getYOffset() {
			return yOffset;
		}
		
		@Override
		public String toString() {
			
			return containerTitle + ", " + xOffset + ", " + yOffset;
		}
		
	}

}


