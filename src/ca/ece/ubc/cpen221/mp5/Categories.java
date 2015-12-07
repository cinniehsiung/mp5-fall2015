package ca.ece.ubc.cpen221.mp5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import ca.ece.ubc.cpen221.mp5.server.RestaurantDB;

public class Categories {
	private Map<String, Long> categoriesDB = new ConcurrentHashMap<String, Long>();

	public Categories(RestaurantDB db) {

		List<String> catList = new ArrayList<String>();
		List<Restaurant> resList = db.getAllRestaurantDetails();

		for (Restaurant curRes : resList) {
			for (String curCat : curRes.getCategories()) {
				if (!catList.contains(curCat)) {
					catList.add(curCat);
				}
			}
		}

		Collections.sort(catList);

		long count = 0;
		for (String curCat : catList) {
			categoriesDB.put(curCat, count);
			count++;
		}
	}

	public Map<String, Long> getCategories() {
		return Collections.unmodifiableMap(categoriesDB);
	}
}
