package ca.ece.ubc.cpen221.mp5;

import java.util.Set;

// TODO: This class represents the Restaurant Database.
// Define the internal representation and 
// state the rep invariant and the abstraction function.

public class RestaurantDB {
	private String restaurantDetails;
	private String userReviews;
	private String userDetails;

	/**
	 * Create a database from the Yelp dataset given the names of three files:
	 * <ul>
	 * <li>One that contains data about the restaurants;</li>
	 * <li>One that contains reviews of the restaurants;</li>
	 * <li>One that contains information about the users that submitted reviews.
	 * </li>
	 * </ul>
	 * The files contain data in JSON format.
	 * 
	 * @param restaurantJSONfilename
	 *            the filename for the restaurant data
	 * @param reviewsJSONfilename
	 *            the filename for the reviews
	 * @param usersJSONfilename
	 *            the filename for the users
	 */
	public RestaurantDB(String restaurantJSONfilename, String reviewsJSONfilename, String usersJSONfilename) {
		this.restaurantDetails = restaurantJSONfilename;
		this.userReviews = reviewsJSONfilename;
		this.userDetails = usersJSONfilename;
	}

	/**
	 * Answers the queries using the Yelp dataset database given a string of the
	 * queries.
	 * 
	 * @param queryString
	 *            the queries in JSON format
	 * @return a set of restaurants that answer the queries
	 */
	public Set<Restaurant> query(String queryString) {
		// TODO: Implement this method
		// Write specs, etc.
		return null;
	}

}
