//package tema2;

import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

/**
 * Clasa Feed este clasa in care se mentin datele si care este subiectul tuturor
 * observatorilor.
 * 
 * @author Mocanu Alexandru
 *
 */
public class Feed {

	private static Feed feed = null;
	private Map<Integer, Observer> observers = new TreeMap<Integer, Observer>();
	Map<String, Float> dataTable = new TreeMap<String, Float>();

	public void updateFeed(String name, float value) {
		dataTable.put(name, value);
		notifyObservers(name, value);
	}

	public void attach(Observer obs) {
		observers.put(obs.id, obs);
	}

	public void notifyObservers(String name, float value) {
		for (Entry<Integer, Observer> entry : observers.entrySet()) {
			entry.getValue().update(name, value);
			
		}
	}

	public static Feed getFeed() {
		if (feed == null) {
			feed = new Feed();
		}
		return feed;
	}

	public void deleteObs(Observer obs) {

		observers.remove(obs.id);
	}
}
