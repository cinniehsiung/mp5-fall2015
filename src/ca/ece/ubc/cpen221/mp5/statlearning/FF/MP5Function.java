package ca.ece.ubc.cpen221.mp5.statlearning.FF;

import ca.ece.ubc.cpen221.mp5.Restaurant;
import ca.ece.ubc.cpen221.mp5.server.RestaurantDB;

public interface MP5Function {

	/**
	 * Compute a feature function given a restaurant
	 * 
	 * @param yelpRestaurant
	 * @return the value of the feature function
	 */
	public double f(Restaurant yelpRestaurant, RestaurantDB db);
	
}
