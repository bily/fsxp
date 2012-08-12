package flightsim.aicarriers;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.lc0277lib.conf.Config;

public class FormationList extends ListMenu<Formation> {
	private List<Formation> formations = new ArrayList<Formation>();
	
	public FormationList(String confDirectory, Config conf) {
		super(ID.EVENT_MENU_SELECT_FORMATION, "Select ship or ships formation:");
		
		parseConfig(conf);
		
		// parse configuration files included i	n subdirectory
		String confdDir = conf.get("base", "confdir", null);
		if (confdDir != null) {
			File confDir = new File(confDirectory, confdDir);
			if (confDir != null && confDir.exists() && 
					confDir.isDirectory()) {
				
				File[] fList = confDir.listFiles();
				if (fList != null) {
					for (File confDirFile : confDir.listFiles()) {
						Config sc = Config.buildConfig(confDirFile);
						parseConfig(sc);
					}
				}
			}
		}
		
		// fix empty list
		if (formations.size() == 0) {
			formations.add(new Formation("No formations configured"));
			formations.add(new Formation("Check aicarriers.cfg"));
		}
	}
	
	private void parseConfig(Config conf) {
		int idx = 0;
		while (true) {
			if (conf.containsSection("formation." + idx)) {
				addFormation(conf, idx);
				idx++;
			} else {
				break;
			}
		}
	}
	
	private void addFormation(Config conf, int index) {
		String section = "formation." + index;
		String title = conf.get(section, "title");
		if (title == null || "".equals(title))
			return;
		Formation f = new Formation(title);
		
		int idx = 0;
		while (true) {
			String unitStr = conf.get(section, "unit." + idx);
			if (unitStr != null) {
				String[] parts = unitStr.split(",");
				if (parts.length == 3) {
					try {
						f.addUnit(parts[0], 
								Double.parseDouble(parts[1]), 
								Double.parseDouble(parts[2]));
					} catch (NumberFormatException nfe) {}
				}
				idx++;
			} else {
				break;
			}
		}
		
		if (f.size() > 0)
			formations.add(f);
	}
	
	@Override
	public String toString() {
		return formations.toString();
	}
	
	@Override
	protected Formation get(int index) {
		return formations.get(index);
	}
	
	@Override
	protected int size() {
		return formations.size();
	}
	
	@Override
	protected String toString(Formation f) {
		return f.getTitle();
	}
	

	
}
