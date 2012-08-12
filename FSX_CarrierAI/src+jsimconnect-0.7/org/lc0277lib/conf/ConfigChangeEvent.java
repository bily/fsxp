package org.lc0277lib.conf;

public class ConfigChangeEvent {

	private final Config source;
	private final String section;
	private final String key;
	private final String oldValue;
	private final String newValue;
	
	ConfigChangeEvent(final Config source, final String section, final String key, final String oldValue, final String newValue) {
		this.source = source;
		this.section = section;
		this.key = key;
		this.oldValue = oldValue;
		this.newValue = newValue;
	}

	/**
	 * The key whose value was changed.
	 * @return
	 */
	public String getKey() {
		return key;
	}

	public String getNewValue() {
		return newValue;
	}

	public String getOldValue() {
		return oldValue;
	}

	public String getSection() {
		return section;
	}

	public Config getSource() {
		return source;
	}
	
	
	
}
