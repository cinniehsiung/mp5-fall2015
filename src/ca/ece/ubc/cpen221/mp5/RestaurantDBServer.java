package ca.ece.ubc.cpen221.mp5;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

// TODO: Implement a server that will instantiate a database, 
// process queries concurrently, etc.

public class RestaurantDBServer {

	// some clarifying and helpful constants
	final static String REVIEWTEXT_KEY = "text";

	// class fields
	private ServerSocket serverSocket;

	private JSONArray restaurantArray = new JSONArray();
	private JSONArray reviewArray = new JSONArray();
	private JSONArray userArray = new JSONArray();

	/**
	 * The constructor for RestaurantDBServer.
	 * 
	 * @param port
	 *            a port number
	 * @param restaurantDetails
	 *            the name of a file that contains the restaurant details in
	 *            JSON format
	 * @param userReviews
	 *            the name of a file that contains user reviews in JSON format
	 * @param userDetails
	 *            the name of a file with user details in JSON format
	 */
	@SuppressWarnings({ "unchecked", "resource" })
	public RestaurantDBServer(int port, String restaurantDetails, String userReviews, String userDetails)
			throws IOException {

		serverSocket = new ServerSocket(port);
		JSONParser parser = new JSONParser();
		try {
			BufferedReader restaurantReader = new BufferedReader(new FileReader(restaurantDetails));
			String currentRestaurantLine;
			while ((currentRestaurantLine = restaurantReader.readLine()) != null) {

				JSONObject currentJSONRestaurant = (JSONObject) parser.parse(currentRestaurantLine);
				this.restaurantArray.add(currentJSONRestaurant);
			}

			BufferedReader reviewReader = new BufferedReader(new FileReader(userReviews));
			String currentReviewLine;
			while ((currentReviewLine = reviewReader.readLine()) != null) {

				JSONObject currentJSONReview = (JSONObject) parser.parse(currentReviewLine);
				this.reviewArray.add(currentJSONReview);
			}

			BufferedReader userReader = new BufferedReader(new FileReader(userDetails));
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

	/**
	 * Returns a <b>random review</b> in JSON format for the restaurant that
	 * matches the provided name. If more than one restaurant matches the
	 * provided name, then any restaurant that satisfies the match can be
	 * returned.
	 * 
	 * @param restaurantName
	 *            the restaurant in which to search for a random review
	 * @return the name of the random review in JSON format, if the request is
	 *         invalid, returns a POISON PILL
	 */
	@SuppressWarnings("unchecked")
	public String randomReview(String restaurantName) {
		String randomReview = "No Review Found. Sorry :(";
		List<String> allReviews = new ArrayList<String>();

		Iterator<JSONObject> restaurantItr = this.restaurantArray.iterator();
		while (restaurantItr.hasNext()) {

			Restaurant currentRestaurant = new Restaurant((restaurantItr.next()));
			if (currentRestaurant.getName().equals(restaurantName)) {

				String businessID = currentRestaurant.getBusinessID();

				Iterator<JSONObject> reviewItr = this.reviewArray.iterator();
				while (reviewItr.hasNext()) {
					JSONObject currentJSONReview = reviewItr.next();
					if (currentJSONReview.get(Restaurant.BUSINESSID_KEY).equals(businessID)) {
						String currentReview = (String) currentJSONReview.get(REVIEWTEXT_KEY);
						allReviews.add(currentReview);
					}
				}

				Random rand = new Random();
				int randomIndex = rand.nextInt(allReviews.size());
				randomReview = allReviews.get(randomIndex);

				break;
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
	 *         returns a POISON PILL
	 */
	@SuppressWarnings("unchecked")
	public String getRestaurant(String businessID) {
		String restaurantDetails = "Sorry. The restaurant was not found.";

		Iterator<JSONObject> restaurantItr = this.restaurantArray.iterator();
		while (restaurantItr.hasNext()) {
			JSONObject currentJSONRestaurant = restaurantItr.next();
			Restaurant currentRestaurant = new Restaurant(currentJSONRestaurant);
			if (currentRestaurant.getBusinessID().equals(businessID)) {
				restaurantDetails = currentJSONRestaurant.toJSONString();
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
	@SuppressWarnings("unchecked")
	public void addRestaurant(String restaurantDetails) {
		JSONParser parser = new JSONParser();
		boolean existingRestaurant = false;

		try {
			JSONObject newRestaurant = (JSONObject) parser.parse((restaurantDetails));

			Iterator<JSONObject> restaurantItr = this.restaurantArray.iterator();
			while (restaurantItr.hasNext()) {
				if (restaurantItr.next().equals(newRestaurant)) {
					existingRestaurant = true;
					break;
				}
			}

			if (!existingRestaurant) {
				this.restaurantArray.add(newRestaurant);
			}

		} catch (ParseException e) {
			e.printStackTrace();
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Creates a new user and adds it into the database given the <b>user
	 * details</b> provided that the user does not already exist.
	 * 
	 * @param userDetails
	 *            the user details in JSON format
	 */
	@SuppressWarnings("unchecked")
	public void addUser(String userDetails) {

		JSONParser parser = new JSONParser();
		boolean existingUser = false;

		try {
			JSONObject newUser = (JSONObject) parser.parse((userDetails));

			Iterator<JSONObject> userItr = this.userArray.iterator();
			while (userItr.hasNext()) {
				if (userItr.next().equals(newUser)) {
					existingUser = true;
					break;
				}
			}

			if (!existingUser) {
				this.userArray.add(newUser);
			}

		} catch (ParseException e) {
			e.printStackTrace();
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Creates a new restaurant review and adds it into the database given the
	 * <b>review details</b> provided that the review does not already exist.
	 * 
	 * @param userReview
	 *            the review details in JSON format
	 */
	@SuppressWarnings("unchecked")
	public void addReview(String userReview) {

		JSONParser parser = new JSONParser();
		boolean existingReview = false;

		try {
			JSONObject newReview = (JSONObject) parser.parse((userReview));

			Iterator<JSONObject> reviewItr = this.reviewArray.iterator();
			while (reviewItr.hasNext()) {
				if (reviewItr.next().equals(newReview)) {
					existingReview = true;
					break;
				}
			}

			if (!existingReview) {
				this.reviewArray.add(newReview);
			}

		} catch (ParseException e) {
			e.printStackTrace();
			throw new IllegalArgumentException();
		}
	}

}
