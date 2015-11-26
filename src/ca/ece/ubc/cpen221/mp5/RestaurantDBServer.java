package ca.ece.ubc.cpen221.mp5;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;

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
		String randomReview = "";

		//TODO: YUQING IS MEAN AND DOES MECH
		
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
		String restaurant = "";

		// TODO: CODE MAN

		return restaurant;
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
