package ca.ece.ubc.cpen221.mp5;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

// TODO: This class represents the Restaurant Database.
// Define the internal representation and 
// state the rep invariant and the abstraction function.

public class RestaurantDB {
	private JSONArray restaurantArray = new JSONArray();
	private JSONArray reviewArray = new JSONArray();
	private JSONArray userArray = new JSONArray();

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
		try {
			JSONParser parser = new JSONParser();

			BufferedReader restaurantReader = new BufferedReader(new FileReader(restaurantJSONfilename));
			String currentRestaurantLine;
			while ((currentRestaurantLine = restaurantReader.readLine()) != null) {

				JSONObject currentJSONRestaurant = (JSONObject) parser.parse(currentRestaurantLine);
				this.restaurantArray.add(currentJSONRestaurant);
			}

			BufferedReader reviewReader = new BufferedReader(new FileReader(reviewsJSONfilename));
			String currentReviewLine;
			while ((currentReviewLine = reviewReader.readLine()) != null) {

				JSONObject currentJSONReview = (JSONObject) parser.parse(currentReviewLine);
				this.reviewArray.add(currentJSONReview);
			}

			BufferedReader userReader = new BufferedReader(new FileReader(usersJSONfilename));
			String currentUserLine;
			while ((currentUserLine = userReader.readLine()) != null) {

				JSONObject currentJSONUser = (JSONObject) parser.parse(currentUserLine);
				this.userArray.add(currentJSONUser);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new IllegalArgumentException();
		} catch (IOException e) {
			e.printStackTrace();
			throw new IllegalArgumentException();
		} catch (ParseException e) {
			e.printStackTrace();
			throw new IllegalArgumentException();
		}
	}

	public JSONArray getRestaurants() {
		return this.restaurantArray;
	}

	public JSONArray getReviews() {
		return this.reviewArray;
	}

	public JSONArray getUsers() {
		return this.userArray;
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
