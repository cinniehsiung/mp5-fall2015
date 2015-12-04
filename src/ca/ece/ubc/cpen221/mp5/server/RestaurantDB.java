package ca.ece.ubc.cpen221.mp5.server;

import java.io.BufferedReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import ca.ece.ubc.cpen221.mp5.*;
import ca.ece.ubc.cpen221.mp5.queryParsing.QueryParser;

// TODO: This class represents the Restaurant Database.
// Define the internal representation and 
// state the rep invariant and the abstraction function.

public class RestaurantDB {
	private List<Restaurant> restaurantDB = new CopyOnWriteArrayList<Restaurant>();
	private List<Review> reviewDB = new CopyOnWriteArrayList<Review>();
	private List<User> userDB = new CopyOnWriteArrayList<User>();

	// clarifying constants
	public static String KEY_RESTAURANT = "This restaurant was";
	public static String KEY_REVIEW = "This review was";
	public static String KEY_USER = "This user was";
	public static String ADDED = "added sucessfully.";
	public static String NOTADDED = "not added because it already exists.";
	public static String NOTFOUND = "not found.";
	public static String FORMATERROR = "not added because it was formatted incorrectly.";

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

			// read from the json files to create the list of restaurants for
			// the database
			BufferedReader restaurantReader = new BufferedReader(new FileReader(restaurantJSONfilename));
			String currentRestaurantLine;
			while ((currentRestaurantLine = restaurantReader.readLine()) != null) {

				JSONObject currentJSONRestaurant = (JSONObject) parser.parse(currentRestaurantLine);
				this.restaurantDB.add(new Restaurant(currentJSONRestaurant));
			}

			// read from the json files to create the list of reviews for the
			// database
			BufferedReader reviewReader = new BufferedReader(new FileReader(reviewsJSONfilename));
			String currentReviewLine;
			while ((currentReviewLine = reviewReader.readLine()) != null) {

				JSONObject currentJSONReview = (JSONObject) parser.parse(currentReviewLine);
				this.reviewDB.add(new Review(currentJSONReview));
			}

			// read from the json files to create the list of users for the
			// database
			BufferedReader userReader = new BufferedReader(new FileReader(usersJSONfilename));
			String currentUserLine;
			while ((currentUserLine = userReader.readLine()) != null) {

				JSONObject currentJSONUser = (JSONObject) parser.parse(currentUserLine);
				this.userDB.add(new User(currentJSONUser));
			}

			// throw exceptions if anything goes wrong
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

	/**
	 * Returns a <b>random review</b> in JSON format for the restaurant that
	 * matches the provided name. If more than one restaurant matches the
	 * provided name, then any restaurant that satisfies the match can be
	 * returned.
	 * 
	 * @param restaurantName
	 *            the restaurant in which to search for a random review
	 * @return the name of the random review in JSON format, if the request is
	 *         invalid, returns "No Review Found. Sorry :(".
	 */
	public String randomReview(String restaurantName) {
		// create a suitable json string if a review could not be found
		JSONObject JSONStringObj = new JSONObject();
		JSONStringObj.put(KEY_REVIEW, NOTFOUND);
		String randomReview = JSONStringObj.toJSONString();

		// iterate through the restaurant database
		Iterator<Restaurant> restaurantItr = this.restaurantDB.iterator();
		while (restaurantItr.hasNext()) {

			// if the restaurant has the same name as the query restaurant
			Restaurant currentRestaurant = restaurantItr.next();
			if (currentRestaurant.getName().equals(restaurantName)) {

				// get the business ID of the restaurant
				String businessID = currentRestaurant.getBusinessID();
				List<Review> allReviews = new ArrayList<Review>();

				// so we can iterate through the reviews and get all the reviews
				// with the same business ID
				Iterator<Review> reviewItr = this.reviewDB.iterator();
				while (reviewItr.hasNext()) {
					Review currentJSONReview = reviewItr.next();
					if (currentJSONReview.getBusinessID().equals(businessID)) {
						allReviews.add(currentJSONReview.clone());
					}
				}

				// if any reviews were found
				if (allReviews.size() > 0) {
					// then get a random one
					Random rand = new Random();
					int randomIndex = rand.nextInt(allReviews.size());
					// replace the json not-found string with the json review
					randomReview = allReviews.get(randomIndex).getJSONDetails();

					break;
				}
			}

		}

		return randomReview;
	}

	/**
	 * Returns the <b>restaurant details</b> in JSON format for the restaurant
	 * that matches the provided <b>business identifier</b>.
	 * 
	 * @param businessID
	 *            the business identifier of which to find the restaurant
	 *            details
	 * @return the restaurant details in JSON format, if the request is invalid,
	 *         returns "Sorry. The restaurant was not found.".
	 */
	public String getRestaurant(String businessID) {
		// create a suitable json string if the restaurant could not be found
		JSONObject JSONStringObj = new JSONObject();
		JSONStringObj.put(KEY_RESTAURANT, NOTFOUND);
		String restaurantDetails = JSONStringObj.toJSONString();

		// iterate through the restaurant database
		Iterator<Restaurant> restaurantItr = this.restaurantDB.iterator();
		while (restaurantItr.hasNext()) {
			// as soon as we find a restaurant with the desired business ID
			Restaurant currentRestaurant = restaurantItr.next();
			if (currentRestaurant.getBusinessID().equals(businessID)) {
				// then get the restaurant details and return it
				restaurantDetails = currentRestaurant.getJSONDetails();
				break;
			}
		}

		return restaurantDetails;

	}

	/**
	 * Creates a new restaurant and adds it into the database given the
	 * <b>restaurant details</b> provided that the restaurant does not already
	 * exist.
	 * 
	 * @param restaurantDetails
	 *            the restaurant details in JSON format
	 */
	public String addRestaurant(String restaurantDetails) {
		boolean existingRestaurant = false;

		// create a suitable json string if the restaurant was not added
		JSONObject JSONStringObj = new JSONObject();
		JSONStringObj.put(KEY_RESTAURANT, NOTADDED);

		try {
			// create a restaurant from the given json details
			JSONParser parser = new JSONParser();
			Restaurant newRestaurant = new Restaurant((JSONObject) parser.parse((restaurantDetails)));

			// iterate through the restaurant database
			Iterator<Restaurant> restaurantItr = this.restaurantDB.iterator();
			while (restaurantItr.hasNext()) {
				// if the restaurant we want to add exists
				if (restaurantItr.next().equals(newRestaurant)) {
					// we take note of that fact
					existingRestaurant = true;
					break;
				}
			}

			// so if it doesn't already exist
			if (!existingRestaurant) {
				// then add it to the database
				this.restaurantDB.add(newRestaurant);
				JSONStringObj.clear();
				JSONStringObj.put(KEY_RESTAURANT, ADDED);
			}

			// if we can't make a restaurant with the given restaurant details
		} catch (ParseException e) {
			// return a suitable json formattted string with the error message
			JSONStringObj.clear();
			JSONStringObj.put(KEY_RESTAURANT, FORMATERROR);
		}

		return JSONStringObj.toJSONString();
	}

	/**
	 * Creates a new user and adds it into the database given the <b>user
	 * details</b> provided that the user does not already exist.
	 * 
	 * @param userDetails
	 *            the user details in JSON format
	 */
	public String addUser(String userDetails) {
		boolean existingUser = false;
		JSONObject JSONStringObj = new JSONObject();
		JSONStringObj.put(KEY_USER, NOTADDED);

		try {
			JSONParser parser = new JSONParser();
			User newUser = new User((JSONObject) parser.parse((userDetails)));

			Iterator<User> userItr = this.userDB.iterator();
			while (userItr.hasNext()) {
				if (userItr.next().equals(newUser)) {
					existingUser = true;
					break;
				}
			}

			if (!existingUser) {
				this.userDB.add(newUser);
				JSONStringObj.clear();
				JSONStringObj.put(KEY_USER, ADDED);
			}

		} catch (ParseException e) {
			// e.printStackTrace();
			JSONStringObj.clear();
			JSONStringObj.put(KEY_USER, FORMATERROR);
		}

		return JSONStringObj.toJSONString();
	}

	/**
	 * Creates a new restaurant review and adds it into the database given the
	 * <b>review details</b> provided that the review does not already exist.
	 * 
	 * @param userReview
	 *            the review details in JSON format
	 */
	public String addReview(String reviewDetails) {
		boolean existingReview = false;
		JSONObject JSONStringObj = new JSONObject();
		JSONStringObj.put(KEY_REVIEW, NOTADDED);

		try {
			JSONParser parser = new JSONParser();
			Review newReview = new Review((JSONObject) parser.parse((reviewDetails)));

			Iterator<Review> reviewItr = this.reviewDB.iterator();
			while (reviewItr.hasNext()) {
				if (reviewItr.next().equals(newReview)) {
					existingReview = true;
					break;
				}
			}

			if (!existingReview) {
				this.reviewDB.add(newReview);

				JSONStringObj.clear();
				JSONStringObj.put(KEY_REVIEW, ADDED);
			}

		} catch (ParseException e) {
			JSONStringObj.clear();
			JSONStringObj.put(KEY_REVIEW, FORMATERROR);
		}

		return JSONStringObj.toJSONString();
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
		QueryParser queryParser = new QueryParser();
		return queryParser.parseQuery(queryString, this);
	}

	/**
	 * This method responds to the queries given by a client by determining
	 * which request type it is responding to, and searches for the appropriate
	 * set.
	 * 
	 * @param request
	 * @param search
	 * @return
	 */
	public Set<Restaurant> respondRequest(String request, String search) {
		Set<Restaurant> results = Collections.synchronizedSet(new HashSet<Restaurant>());

		if ("in".equals(request)) {
			Iterator<Restaurant> restaurantItr = this.restaurantDB.iterator();
			while (restaurantItr.hasNext()) {
				Restaurant currentRestaurant = restaurantItr.next();

				Set<String> neighbourhoods = currentRestaurant.getNeighbourhoods();

				if (neighbourhoods.contains(search)) {
					results.add(currentRestaurant.clone());
				}
			}
		}

		else if ("category".equals(request)) {
			Iterator<Restaurant> restaurantItr = this.restaurantDB.iterator();
			while (restaurantItr.hasNext()) {
				Restaurant currentRestaurant = restaurantItr.next();
				if (currentRestaurant.getCategories().contains(search)) {
					results.add(currentRestaurant.clone());
				}
			}
		}

		else if ("rating".equals(request)) {
			Iterator<Restaurant> restaurantItr = this.restaurantDB.iterator();
			while (restaurantItr.hasNext()) {
				Restaurant currentRestaurant = restaurantItr.next();

				double lowerbound = Character.getNumericValue(search.charAt(0));
				double upperbound = Character.getNumericValue(search.charAt(3));

				double rating = currentRestaurant.getStars();

				if (rating >= lowerbound && rating <= upperbound) {

					results.add(currentRestaurant.clone());
				}
			}
		}

		else if ("price".equals(request)) {
			Iterator<Restaurant> restaurantItr = this.restaurantDB.iterator();
			while (restaurantItr.hasNext()) {
				Restaurant currentRestaurant = restaurantItr.next();

				int lowerbound = Character.getNumericValue(search.charAt(0));
				int upperbound = Character.getNumericValue(search.charAt(3));

				int price = (int) currentRestaurant.getPrice();

				if (price >= lowerbound && price <= upperbound) {
					results.add(currentRestaurant.clone());
				}
			}
		}

		else if ("name".equals(request)) {
			Iterator<Restaurant> restaurantItr = this.restaurantDB.iterator();
			while (restaurantItr.hasNext()) {
				Restaurant currentRestaurant = restaurantItr.next();
				if (currentRestaurant.getName().equals(search)) {
					results.add(currentRestaurant.clone());
				}
			}
		}

		return results;
	}

	/**
	 * Helper method for the Algorithms class to get all the restaurant details.
	 * 
	 * @return a list of restaurant objects
	 */
	public List<Restaurant> getAllRestaurantDetails() {
		List<Restaurant> copy = new CopyOnWriteArrayList();
		copy.addAll(this.restaurantDB);
		return Collections.unmodifiableList(copy);
	}

}
