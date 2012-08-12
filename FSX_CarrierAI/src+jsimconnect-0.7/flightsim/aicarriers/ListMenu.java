package flightsim.aicarriers;

import java.io.IOException;
import java.util.Vector;

import flightsim.simconnect.SimConnect;
import flightsim.simconnect.TextResult;

/**
 * Base class for displaying multiple-page list menus. Subclass must provide
 * collection-like accessor methods.
 * @author lc
 *
 * @param <T>
 */
public abstract class ListMenu<T> {

	
	protected abstract int size();
	protected abstract T get(int index);
	protected abstract String toString(T object);
	
	
	private int startIndex = 0;
	
	private ID eventId;
	private String prompt;
	
	
	public ListMenu(ID eventId, String prompt) {
		this.eventId = eventId;
		this.prompt = prompt;
		
		if (prompt == null)
			prompt = "Select";
		
		if (eventId == null)
			eventId = ID.LAST;		// oops
	}
	
	protected void setStartIndex(int index) {
		this.startIndex = index;
	}

	T processEvent(SimConnect sc, TextResult tr) {
		if (!tr.isSelection())
			return null;
		
		int index = tr.value();
		
		if (startIndex != 0 && index == 0) {
			// previous page
			startIndex -= 8;
			showMenu(sc);
			return null;
		}
		
		int n = size();
		if (index == 9 && 
				(n > (startIndex+index))) {
			// is next page
			startIndex += 8;
			showMenu(sc);
			return null;
		}
		
		if (startIndex != 0)
			index--;	// first entry is previous

		return get(index+startIndex);
	}
	
	private Vector<String> sv = new Vector<String>(10);
	
	void showMenu(SimConnect sc) {
		// clear menu items
		sv.clear();

		int sz = size();
		int n = 10;
		if (startIndex != 0) n --;	// need "previous page" item
		n = Math.min(n, sz - startIndex);		// too much items
		if (sz > (n+startIndex)) n--;	// need "Next page" item
		
		if (0 != startIndex) sv.add("Previous page");
		for (int i = 0; i < n; i++) {
			T obj = get(startIndex+i);
			sv.add(toString(obj));
		}
		if (sz > (n+startIndex)) sv.add("Next page");
		
		String[] items = new String[sv.size()];
		items = sv.toArray(items);

		try {
			sc.menu(15.0f, eventId, 
					AICarriersManager.MENU_TITLE,
					prompt,
					items);
		} catch (IOException e) {
		}
	}

}
