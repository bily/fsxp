package flightsim.simconnect.recv;

import java.nio.ByteBuffer;

/**
 * The <code>FacilityWaypoint</code> structure used to return information
 * on a single waypoint in the facilities cache.
 * @author lc0277
 *
 */
public class FacilityWaypoint extends FacilityAirport {
	

	private float magVar;
	
	FacilityWaypoint(ByteBuffer bf) {
		super(bf);
		magVar = bf.getFloat();
	}

	/**
	 * Returns The magnetic variation of the waypoint in degrees.
	 * @return magnetic variation
 	 */
	public float getMagVar() {
		return magVar;
	}
	
	@Override
	public String toString() {
		return super.toString() + " magvar=" + magVar;
	}
}
