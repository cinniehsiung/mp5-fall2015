package ca.ece.ubc.cpen221.mp5;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

// TODO: Implement a server that will instantiate a database, 
// process queries concurrently, etc.

public class RestaurantDBServer {
	private ServerSocket serverSocket;
	private String restaurantDetails;
	private String userReviews;
	private String userDetails;

	final static String REVIEWTEXT_KEY = "text";

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
	public RestaurantDBServer(int port, String restaurantDetails, String userReviews, String userDetails)
			throws IOException {

		serverSocket = new ServerSocket(port);
		this.restaurantDetails = restaurantDetails;
		this.userDetails = userDetails;
		this.userReviews = userReviews;
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
	public String randomReview(String restaurantName) {
		String randomReview = "No Review Found. Sorry :(";
		List<String> allReviews = new ArrayList<String>();
		JSONParser parser = new JSONParser();

		try {
			BufferedReader restaurantReader = new BufferedReader(new FileReader(this.restaurantDetails));
			String businessID = "";
			String currentRestaurantLine;
			outer: while ((currentRestaurantLine = restaurantReader.readLine()) != null) {

				JSONObject currentJSONRestaurant = (JSONObject) parser.parse(currentRestaurantLine);
				Restaurant currentRestaurant = new Restaurant((JSONObject) currentJSONRestaurant);
				if (currentRestaurant.getName().equals(restaurantName)) {
					businessID = currentRestaurant.getBusinessID();
					break;
				}
			}

			BufferedReader reviewsReader = new BufferedReader(new FileReader(this.userReviews));

			String currentReviewsLine;
			String currentReview;
			while ((currentReviewsLine = reviewsReader.readLine()) != null) {

				JSONObject currentJSONReview = (JSONObject) parser.parse(currentReviewsLine);
				if (currentJSONReview.get(Restaurant.BUSINESSID_KEY).equals(businessID)) {
					currentReview = (String) currentJSONReview.get(REVIEWTEXT_KEY);
					allReviews.add(currentReview);
				}
			}

			Random rand = new Random();
			int randomIndex = rand.nextInt(allReviews.size());
			randomReview = allReviews.get(randomIndex);

		} catch (FileNotFoundException e) {
			fail("FILE NOT FOUND SHOULDN'T DO THIS YUI HELP ");
			e.printStackTrace();
		} catch (IOException e) {
			fail("CAN'T READ THE FILEEEEE SHOULDN'T DO THIS YUI HELP ");
			e.printStackTrace();
		} catch (ParseException e) {
			fail("STUPID PARSER SHOULDN'T DO THIS YUI HELP ");
			e.printStackTrace();
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
	public String getRestaurant(String businessID) {
		String restaurantDetails = "Sorry. The restaurant was not found.";

		JSONParser parser = new JSONParser();
		try {
			BufferedReader restaurantReader = new BufferedReader(new FileReader(this.restaurantDetails));

			String currentRestaurantLine;
			outer: while ((currentRestaurantLine = restaurantReader.readLine()) != null) {

				JSONObject currentJSONRestaurant = (JSONObject) parser.parse(currentRestaurantLine);
				Restaurant currentRestaurant = new Restaurant((JSONObject) currentJSONRestaurant);
				if (currentRestaurant.getBusinessID().equals(businessID)) {
					restaurantDetails = currentJSONRestaurant.toJSONString();
					break outer;
				}
			}

		} catch (FileNotFoundException e) {
			fail("FILE NOT FOUND SHOULDN'T DO THIS YUI HELP ");
			e.printStackTrace();
		} catch (IOException e) {
			fail("CAN'T READ THE FILEEEEE SHOULDN'T DO THIS YUI HELP ");
			e.printStackTrace();
		} catch (ParseException e) {
			fail("STUPID PARSER SHOULDN'T DO THIS YUI HELP ");
			e.printStackTrace();
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
	public void addRestaurant(String restaurantDetails) {

		// TODO: CODE MAN. ONLY ADD IF ITS NOT THERE ALREADY
	}

	/**
	 * Creates a new user and adds it into the database given the <b>user
	 * details</b> provided that the user does not already exist.
	 * 
	 * @param userDetails
	 *            the user details in JSON format
	 */
	public void addUser(String userDetails) {

		// TODO: CODE MAN. ONLY ADD IF ITS NOT THERE ALREADY
	}

	/**
	 * Creates a new restaurant review and adds it into the database given the
	 * <b>review details</b> provided that the review does not already exist.
	 * 
	 * @param userReview
	 *            the review details in JSON format
	 */
	public void addReview(String userReview) {

		// TODO: CODE MAN. ONLY ADD IF ITS NOT THERE ALREADY
	}

}
