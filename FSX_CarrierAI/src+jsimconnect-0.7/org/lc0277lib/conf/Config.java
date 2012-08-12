package org.lc0277lib.conf;

import java.awt.Color;
import java.io.*;
import java.util.*;

/**
 * 
 * Adapted from INIFile by Prasad P. Khandekar
 */
public class Config
{
	private final LinkedHashMap<String, ConfigSection> sections = 
		new LinkedHashMap<String, ConfigSection>();
	private final Properties envProps;
	
	private File readFile;
	private boolean readFromFile;
	
	
	/* *******************************************************************
	 * 
	 * initialisation
	 * 
	 * *******************************************************************
	 */
	

	/**
	 * Construct a blank config file
	 *
	 */
	private Config() {
		envProps = fillEnvironnment();
	}
	
	
	/**
	 * Build a new config, reading from file. Returns empty
	 * config on read error
	 * @param fileName
	 * @return
	 */
	public static Config buildConfig(String fileName) {
		Config f = new Config();
		try {
			f.loadFromFile(new File(fileName));
		} catch (IOException e) {
			f.sections.clear();
		}
		f.readFile = new File(fileName);
		return f;
	}
	

	/**
	 * Build a new config, reading from file. Returns empty
	 * config on read error
	 * @param fileName
	 * @return
	 */
	public static Config buildConfig(File file) {
		Config f = new Config();
		try {
			f.loadFromFile(file);
		} catch (IOException e) {
			f.sections.clear();
		}
		return f;
	}
	
	private Properties fillEnvironnment() {
	    Process p = null;
		Properties envVars = new Properties();

		try {
			Runtime r = Runtime.getRuntime();
			String OS = System.getProperty("os.name").toLowerCase(); //$NON-NLS-1$

			if (OS.indexOf("windows 9") > -1) { //$NON-NLS-1$
				p = r.exec("command.com /c set"); //$NON-NLS-1$
			} else if ((OS.indexOf("nt") > -1) //$NON-NLS-1$
					|| (OS.indexOf("windows 2000") > -1) //$NON-NLS-1$
					|| (OS.indexOf("windows xp") > -1)) { //$NON-NLS-1$
				p = r.exec("cmd.exe /c set"); //$NON-NLS-1$
			} else {
				// assume unix
				p = r.exec("env"); //$NON-NLS-1$
			}
			BufferedReader br = new BufferedReader(new InputStreamReader(p
					.getInputStream()));
			String line;
			while ((line = br.readLine()) != null) {
				int idx = line.indexOf('=');
				String key = line.substring(0, idx);
				String value = line.substring(idx + 1);
				envVars.setProperty(key, value);
			}
		} catch (Exception ign) {
		}
		return envVars;
	}
	
	
	/* **************************************************************************
	 * 
	 * File read/Write
	 * 
	 * *******************************************************************
	 */
	
	private void load(InputStream is)  throws IOException {
        
		ConfigSection currentSection = null;
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String remarks = null;
		int ip;
		
		String line;
		while ((line = br.readLine()) != null) {
			line = line.trim();
			if (line.length() == 0) continue;
		
			// parse comments
			if (line.charAt(0) == ';') {
				if (remarks == null || remarks.length() == 0) {
					remarks = line.substring(1);
				} else {
					remarks = remarks + "\r\n" + line.substring(1); //$NON-NLS-1$
				}
			} else if (line.startsWith("[") && line.endsWith("]")) { //$NON-NLS-1$ //$NON-NLS-2$
				if (currentSection != null) {
					//save previous section
					sections.put(currentSection.name, currentSection);
				}
				String secName = line.substring(1,line.length()-1);
				currentSection = new ConfigSection(secName, remarks);
				remarks = null;
			} else if (currentSection != null && (ip = line.indexOf('=')) != -1) {
				// property
				currentSection.set(line.substring(0, ip).trim(),
						line.substring(ip+1).trim(), remarks);
				remarks = null;
			}
		}
		
		// memorize last section
		if (currentSection != null) {
			sections.put(currentSection.name, currentSection);
		}
		
		// close file and memorize status
		br.close();
	}

	private void loadFromFile(File source) throws FileNotFoundException, IOException	{
		load(new FileInputStream(source));
		this.readFile = source;
		this.readFromFile = true;
	}
	
	public void save(Writer w) throws IOException {
		if (sections.size() == 0) return;
		
		for (Map.Entry<String, ConfigSection> secEnt : sections.entrySet()) {
			w.write(secEnt.getValue().toString());
			w.write("\r\n"); //$NON-NLS-1$
		}
		w.flush();
	}
	
	public boolean save() {
		if (readFile == null) return false;
		try {
			FileWriter fw = new FileWriter(readFile);
			save(fw);
			fw.close();
			return true;
			
		}  catch (IOException ioe) {}
		return false;
	}
	
	public void save(File f) throws IOException {
		FileWriter fw = new FileWriter(f);
		save(fw);
		fw.flush();
		fw.close();
	}
	

	/* *******************************************************************
	 * 
	 * Status getters
	 * 
	 * *******************************************************************
	 */
	
	public boolean isReadFromFile() {
		return readFromFile;
	}
	
	public File getFile() {
		return readFile;
	}

	public void setFile(File readFile) {
		this.readFile = readFile;
	}
	
	
	/* *******************************************************************
	 * 
	 * Global config (singleton)
	 * 
	 * *******************************************************************
	 */
	private static Config globalConfig;
	
	/**
	 * Returns the singleton global configuration object (if set)
	 * @return global configuration
	 */
	public static Config getGlobalConfig() {
		return globalConfig;
	}
	
	/**
	 * Sets the global configuration object
	 * @param config config
	 */
	public static void setGlobalConfig(Config config) {
		globalConfig = config;
	}
	

	
	/* *******************************************************************
	 * 
	 * Bean support
	 * 
	 * *******************************************************************
	 */

	private List<ConfigChangeListener> listeners = 
		new ArrayList<ConfigChangeListener>();
	
	public void addConfigChangeListener(ConfigChangeListener cel) {
		listeners.add(cel);
	}
	
	public void addConfigChangeListener(
			final String section,
			final String key,
			final ConfigChangeListener cel) {
		listeners.add(new ConfigChangeListener(){
			public void configChange(ConfigChangeEvent e) {
				if ((e.getSection().equals(section) || section == null) &&
						(e.getKey().equals(key) || key == null)) {
					cel.configChange(e);
				}
			}
		});
	}
	
	public void removeConfigChangeListener(ConfigChangeListener cel) {
		listeners.remove(cel);
	}
	
	private void fireChangeEvent(String section, String key,
			String oldValue, String newValue) {
		if (listeners.size() == 0) return;

		if (oldValue != null &&
				newValue != null &&
				oldValue.equals(newValue)) {
			return;
		}
		
		ConfigChangeEvent event = new ConfigChangeEvent(this, 
				section, key, oldValue, newValue);
		
		ConfigChangeListener[] listenersArr = new
			ConfigChangeListener[listeners.size()];
		listenersArr = listeners.toArray(listenersArr);
		for (ConfigChangeListener cel : listenersArr) {
			if (cel != null) {
				cel.configChange(event);
			}
		}
	}
	

	/* *******************************************************************
	 * 
	 * generic getters/setters
	 * 
	 * *******************************************************************
	 */
	
	public String get(String sectionName, String key, String def) {
		ConfigSection sec = sections.get(sectionName);
		if (sec != null) {
			return sec.get(key, def);
		}
		return def;
	}
	
	public String getOrSet(String sectionName, String key, String def) {
		ConfigSection sec = sections.get(sectionName);
		if (sec != null) {
			return sec.getOrSet(key, def);
		} else {
			set(sectionName, key, def);
		}
		return def;
	}
	
	public String get(String sectionName, String key) {
		return get(sectionName, key, null);
	}
	
	public boolean containsSection(String sectionName) {
		return sections.containsKey(sectionName);
	}
	
	public boolean containsKey(String sectionName, String key) {
		ConfigSection sec = sections.get(sectionName);
		if (sec != null) {
			return sec.containsKey(key);
		}
		return false;
	}
	
	public void set(String sectionName, String key, String value) {
		ConfigSection sec = sections.get(sectionName);
		if (sec == null) {
			sec = new ConfigSection(sectionName, null);
			sections.put(sectionName, sec);
		}
		
	
		String oldVal = sec.get(key, null);
		sec.set(key, value, null);
		fireChangeEvent(sectionName, key, oldVal, value);
	}


	/* *******************************************************************
	 * 
	 * Specialized getters / setters
	 * 
	 * *******************************************************************
	 */
	
	public boolean getBoolean(String section, String key, boolean def) {
		String val = get(section, key);
		if (val == null)
			return def;
		
		val = val.toUpperCase();
		if ("YES".equals(val) ||  //$NON-NLS-1$
				"TRUE".equals(val) || //$NON-NLS-1$
				"1".equals(val)) //$NON-NLS-1$
			return true;
		
		if ("NO".equals(val) ||  //$NON-NLS-1$
				"FALSE".equals(val) || //$NON-NLS-1$
				"0".equals(val)) //$NON-NLS-1$
			return false;
		
		return def;
	}
	
	public boolean getBooleanOrSet(String section, String key, boolean def) {
		String val = get(section, key);
		if (val == null)  {
			setBoolean(section, key, def);
			return def;
		}
		
		val = val.toUpperCase();
		if ("YES".equals(val) ||  //$NON-NLS-1$
				"TRUE".equals(val) || //$NON-NLS-1$
				"1".equals(val)) //$NON-NLS-1$
			return true;
		
		if ("NO".equals(val) ||  //$NON-NLS-1$
				"FALSE".equals(val) || //$NON-NLS-1$
				"0".equals(val)) //$NON-NLS-1$
			return false;
		
		setBoolean(section, key, def);
		return def;
	}

	
	public void setBoolean(String section, String key, boolean val) {
		set(section, key, Boolean.toString(val));
	}
	
	public long getLong(String section, String key, long def, int base) {
		String val = get(section, key);
		if (val == null)
			return def;
		

		try {
			return Long.parseLong(val, base);
		} catch (Exception e) {}
		return def;
	}
	
	public long getLongOrSet(String section, String key, long def, int base) {
		String val = get(section, key);
		if (val == null) {
			setLong(section, key, def);
			return def;
		}
		

		try {
			return Long.parseLong(val, base);
		} catch (Exception e) {
			setLong(section, key, def);
		}
		return def;
	}



	public long getLong(String section, String key, long def) {
		return getLong(section, key, def, 10);
	}
	
	public void setLong(String section, String key, long val) {
		set(section, key, Long.toString(val));
	}
	
	public float getFloat(String section, String key, float def) {
		String val = get(section, key);
		if (val == null)
			return def;
		

		try {
			return Float.parseFloat(val);
		} catch (Exception e) {}
		return def;
	}

	public float getFloatOrSet(String section, String key, float def) {
		String val = get(section, key);
		if (val == null) {
			setFloat(section, key, def);
			return def;
		}
		

		try {
			return Float.parseFloat(val);
		} catch (Exception e) {
			setFloat(section, key, def);
		}
		return def;
	}

	public void setFloat(String section, String key, float val) {
		set(section, key, Float.toString(val));
	}
	
	public double getDouble(String section, String key, double def) {
		String val = get(section, key);
		if (val == null)
			return def;
		

		try {
			return Double.parseDouble(val);
		} catch (Exception e) {}
		return def;
	}
	
	public double getDoubleOrSet(String section, String key, double def) {
		String val = get(section, key);
		if (val == null) {
			setDouble(section, key, def);
			return def;
		}
		

		try {
			return Double.parseDouble(val);
		} catch (Exception e) {
			setDouble(section, key, def);
		}
		return def;
	}
	
	public void setDouble(String section, String key, double val) {
		set(section, key, Double.toString(val));
	}
	
	public int getInteger(String section, String key, int def, int base) {
		String val = get(section, key);
		if (val == null)
			return def;
		

		try {
			return Integer.parseInt(val, base);
		} catch (Exception e) {}
		return def;
	}

	public int getIntegerOrSet(String section, String key, int def, int base) {
		String val = get(section, key);
		if (val == null) {
			setInteger(section, key, def);
			return def;
		}
		

		try {
			return Integer.parseInt(val, base);
		} catch (Exception e) {
			setInteger(section, key, def);
		}
		return def;
	}

	public int getInteger(String section, String key, int def) {
		return getInteger(section, key, def, 10);
	}
	
	public void setInteger(String section, String key, int val) {
		set(section, key, Integer.toString(val));
	}
	
	public Color getColor(String section, String key, Color def) {
		String val = get(section, key);
		if (val == null)
			return def;
		
		try {
			int v = Long.decode(val).intValue();
			if ((v & 0xff000000) == 0) {
				// don't allow purely transparent
				v |= 0xff000000;
			}
			return new Color(v, true);
		} catch (Exception ex) {
		}
		
		return def;
	}
	
	public Color getColorOrSet(String section, String key, Color def) {
		String val = get(section, key);
		if (val == null) {
			setColor(section, key, def);
			return def;
		}
		
		try {
			int v = Long.decode(val).intValue();
			if ((v & 0xff000000) == 0) {
				// don't allow purely transparent
				v |= 0xff000000;
			}
			return new Color(v, true);
		} catch (Exception ex) {
			setColor(section, key, def);
		}
		return def;
	}
	
	public void setColor(String section, String key, Color val) {
		setColor(section, key, val.getRGB());
	}

	public void setColor(String section, String key, int rgbval) {
		if ((rgbval & 0xff000000) == 0) {
			// don't allow totally transparent
			rgbval |= 0xff000000;
		}
		
		set(section, key, "0x" +  Integer.toHexString(rgbval)); //$NON-NLS-1$
	}
	
	/**
	 * Get an enumerated value from config. Warning: if you pass <code>null</code>
	 * as {@code def} then this function will always return null. The default
	 * value class is used for constructing types from string.
	 * 
	 * @param <E> base enum type
	 * @param section config section
	 * @param key config key
	 * @param def default value.
	 * @return value, or def if key not found
	 */
	public <E extends Enum<E>> E getEnum(String section, String key, E def) {
		if (def == null)
			return null;
		
		Class<E> cle = def.getDeclaringClass();
		String s = get(section, key);
		if (s == null || "".equals(s))
			return def;
		try {
			return Enum.valueOf(cle, s);
		} catch (IllegalArgumentException iae) {
		}
		return def;
	}

	/**
	 * Write an enumerated value to configuration 
	 * @param <E> base enum type
	 * @param section config section
	 * @param key config key
	 * @param value value
	 */
	public <E extends Enum<E>> void setEnum(String section, String key, E value) {
		set(section, key, value.name());
	}
	
	/**
	 * Read an enumerated value from config, and write it if it's not present in config.
	 * If <code>def</code> is null, then no value is wrote
	 * @param <E> base enumeration type
	 * @param section config section
	 * @param key config key
	 * @param def default value to write if not present
	 * @return value
	 */
	public <E extends Enum<E>> E getEnumOrSet(String section, String key, E def) {
		if (def == null)
			return null;
		
		Class<E> cle = def.getDeclaringClass();
		String s = get(section, key);
		if (s == null || "".equals(s)) {
			setEnum(section, key, def);
			return def;
		}
		try {
			return Enum.valueOf(cle, s);
		} catch (IllegalArgumentException iae) {
		}
		setEnum(section, key, def);
		return def;
	}
	
	
	/* *******************************************************************
	 * 
	 * Section mgmt
	 * 
	 * *******************************************************************
	 */
	
	public void addSection(String secName, String comments) {
		if (sections.containsKey(secName))
			return;
		sections.put(secName, new ConfigSection(secName, comments));
	}

	public void removeSection(String sectionName) {
		sections.remove(sectionName);
	}
	
	public void unsetProperty(String sectionName, String key) {
		ConfigSection sec = sections.get(sectionName);
		if (sec != null)
			sec.unsetProperty(key);
	}
	
	public String[] sectionNames() {
		String[] pn = new String[sections.size()];
		int i = 0;
		for (String s : sections.keySet()) {
			pn[i++] = s;
		}
		return pn;
	}
	
	public Collection<String> sections() {
		return sections.keySet();
	}
	
	public String[] propertyNames(String sectionName) {
		ConfigSection sec = sections.get(sectionName);
		if (sec == null) return null;
		return sec.propertyNames();
	}

	public Collection<String> properties(String sectionName) {
		ConfigSection sec = sections.get(sectionName);
		if (sec == null) return null;
		return sec.properties();
	}

	/* *******************************************************************
	 * 
	 * Format helpers
	 * 
	 * *******************************************************************
	 */
	
	private String filterComments(String o) {
		if (o == null) return null;
		return o.replaceAll(";", ""); //$NON-NLS-1$ //$NON-NLS-2$
	}
	
	private String unfilterComments(String o) {
		if (o == null) return null;
		o = o.replace("\r\n", "\r\n;"); //$NON-NLS-1$ //$NON-NLS-2$
		return ";" + o; //$NON-NLS-1$
	}

	
	/* *******************************************************************
	 * 
	 * Section block
	 * 
	 * *******************************************************************
	 */
	
	private class ConfigSection {

		private final String name;
		private final String comments;
		private final LinkedHashMap<String, ConfigProperty> properties =
			new LinkedHashMap<String, ConfigProperty>();
		
		ConfigSection(String name, String comments) {
			this.name = name;
			this.comments = filterComments(comments);
		}
		
		public void unsetProperty(String key) {
			properties.remove(key);
		}

		public boolean containsKey(String key) {
			return properties.containsKey(key);
		}

		public String get(String key, String def) {
			if (properties.containsKey(key)) 
				return properties.get(key).getValue();
			
			return def;	// fallback
		}
		
		public String getOrSet(String key, String def) {
			if (properties.containsKey(key)) 
				return properties.get(key).getValue();
			else
				set(key, def, null);
				
			return def;	// fallback
		}
		
		void set(String key, String value, String comments) {
			ConfigProperty prop = new ConfigProperty(key, value, comments);
			properties.put(key, prop);
		}
		
		
		public String getName() {
			return name;
		}
		
		public String getComments() {
			return comments;
		}

		public Map getProperties() {
			return Collections.unmodifiableMap(properties);
		}
		
		public String toString() {
			StringBuffer sgb = new StringBuffer(""); //$NON-NLS-1$
			if (comments != null) {
				sgb.append(unfilterComments(comments));
				sgb.append("\r\n"); //$NON-NLS-1$
			}
			sgb.append("[" + name + "]\r\n"); //$NON-NLS-1$ //$NON-NLS-2$
			for (Map.Entry<String, ConfigProperty> ent : properties.entrySet()) {
				sgb.append(ent.getValue().toString());
				sgb.append("\r\n"); //$NON-NLS-1$
			}
			sgb.append("\r\n"); //$NON-NLS-1$
			
			return sgb.toString();
		}
		
		public String[] propertyNames() {
			String[] pn = new String[properties.size()];
			int i = 0;
			for (String s : properties.keySet()) {
				pn[i++] = s;
			}
			return pn;
		}
		
		public Collection<String> properties() {
			return properties.keySet();
		}
		
	}
	
	/* *******************************************************************
	 * 
	 * Property
	 * 
	 * *******************************************************************
	 */
	
	private class ConfigProperty {
		
		private final String key;
		private final String value;
		private final String comments;
		
		ConfigProperty(final String key, final String value, 
				final String comments) {
			this.key = key;
			this.value = value;
			this.comments = filterComments(comments);
		}
		
		ConfigProperty(String key, String value) {
			this(key, value, null);
		}
		
		String getValue() {
			String retVal = value;
			int start, end;
			start = retVal.indexOf('%');
			
			if (start != -1) {
				end = retVal.indexOf('%', start+1);
				String var = retVal.substring(start+1, end);
				String varSub =  envProps.getProperty(var);
				if (varSub != null) {
					retVal = value.substring(0, start) +
						varSub + value.substring(end+1);
				}
			}
			return retVal;
		}
		
		public String getKey() {
			return key;
		}
		
		public String getComments() {
			return comments;
		}
		
		@Override
		public String toString() {
			StringBuffer sgb = new StringBuffer(""); //$NON-NLS-1$
			if (comments != null) {
				sgb.append(unfilterComments(comments));
				sgb.append("\r\n"); //$NON-NLS-1$
			}
			sgb.append(key);
			sgb.append("="); //$NON-NLS-1$
			sgb.append(value);
			
			return sgb.toString();
		}
		
		@Override
		public int hashCode() {
			return key.hashCode();
		}
		
		@Override
		public boolean equals(Object obj) {
			if (obj instanceof ConfigProperty) {
				ConfigProperty cp = (ConfigProperty) obj;
				return cp.key.equals(key) &&
					cp.value.equals(value);
			}
			return false;
		}
	
	}
	
	
}
