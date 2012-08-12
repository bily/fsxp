package flightsim.simconnect.config;

import java.util.Hashtable;

/**
 * A configuration block. Extends <code>HashTable</code> to easily store values
 * @author lc0277
 * @since 0.2
 *
 */
public class Configuration extends Hashtable<String, String> {
	private static final long serialVersionUID = 4120183786070819349L;
	
	/**
	 * Get a parameter from the configuration
	 * @param key parameter name (case insensitive)
	 * @param def default value if parameter is not found
	 * @return parameter value
	 */
	public String get(String key, String def) {
		String s = get(key.toLowerCase());
		if (s == null) return def;
		else return s;
	}
	
	/**
	 * Get an integer parameter from the configuration
	 * @param key parameter name (case insensitive)
	 * @param def default value if parameter is not found
	 * @return parameter value
	 */
	public int getInt(String key, int def) {
		String s = get(key.toLowerCase());
		if (s == null) return def;
		try {
			return Integer.parseInt(s);
		} catch (NumberFormatException nfe) {
			return def;
		}
	}

	/**
	 * Get a boolean parameter from the configuration. Booleans are
	 * represented by integers 0/1
	 * @param key parameter name (case insensitive)
	 * @param def default value if not found or invalid format
	 * @return parameter value
	 */
	public boolean getBoolean(String key, boolean def) {
		int val = getInt(key, def ? 1 : 0);
		if (val == 1) return true;
		else return false;
	}
	
	/**
	 * Add a key=value pair to the configuration
	 */
	@Override
	public synchronized String put(String key, String value) {
		return super.put(key.toLowerCase(), value);
	}

	
}
