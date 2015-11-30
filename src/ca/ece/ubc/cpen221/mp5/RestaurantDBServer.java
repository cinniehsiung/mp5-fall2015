package ca.ece.ubc.cpen221.mp5;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
// TODO: Implement a server that will instantiate a database, 
// process queries concurrently, etc.

public class RestaurantDBServer {

	// class fields
	private final RestaurantDB database;
	private final int port;
	private ServerSocket serverSocket;
	private boolean serverOn = true;

	/**
	 * The constructor for RestaurantDBServer. Initializes the restaurant
	 * database and listens for connections on port.
	 * 
	 * @param port
	 *            a port number, 0 <= port M = 65535
	 * @param restaurantDetails
	 *            the name of a file that contains the restaurant details in
	 *            JSON format
	 * @param userReviews
	 *            the name of a file that contains user reviews in JSON format
	 * @param userDetails
	 *            the name of a file with user details in JSON format
	 */
	public RestaurantDBServer(int port, String restaurantDetails, String userReviews, String userDetails) {
		this.port = port;

		System.out.println("Making Database.");
		this.database = new RestaurantDB(restaurantDetails, userReviews, userDetails);
		System.out.println("Finished Making Database.");

		openServerSocket();
	}

	/**
	 * Run the server, listening for connections and handling them.
	 */
	public void serve() {

		while (serverOn) {
			Socket clientSocket = null;

			try {
				// listen until a client connection is made
				clientSocket = this.serverSocket.accept();
			} catch (IOException e) {

				// if can't make the connection then print error messages
				if (!serverOn) {
					System.out.println("Server Offline.");
				} else {
					e.printStackTrace();
					throw new IllegalArgumentException("Error making socket.");
				}
			}

			// once a client connection has been made
			// make a new thread to process their queries
			new Thread(new RestaurantDBWorker(clientSocket, this.database)).start();
		}
	}

	/**
	 * Helper method to attempt to open and bind to the server socket.
	 */
	private void openServerSocket() {
		System.out.println("Booting up server...");
		try {
			this.serverSocket = new ServerSocket(this.port);
			serverOn = true;
			System.out.println("Server Online.");
		} catch (IOException e) {
			serverOn = false;
			System.err.println("Cannot open port" + port + ".");
		}
	}

	/**
	 * Helper method to attempt to close the server socket.
	 */
	private void closeServerSocket() {
		System.out.println("Shutting down server...");
		try {
			serverSocket.close();
			serverOn = false;
			System.out.println("Server Offline.");
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Error shutting down server.");
		}
	}

	public static void main(String[] args) {
		RestaurantDBServer server = new RestaurantDBServer(Integer.parseInt(args[0]), args[1], args[2], args[3]);
		server.serve();

		server.closeServerSocket();

		return;
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
		String randomReview = database.randomReview(restaurantName);
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
		String restaurantDetails = database.getRestaurant(businessID);
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
		database.addRestaurant(restaurantDetails);
	}

	/**
	 * Creates a new user and adds it into the database given the <b>user
	 * details</b> provided that the user does not already exist.
	 * 
	 * @param userDetails
	 *            the user details in JSON format
	 */
	public void addUser(String userDetails) {
		database.addUser(userDetails);
	}

	/**
	 * Creates a new restaurant review and adds it into the database given the
	 * <b>review details</b> provided that the review does not already exist.
	 * 
	 * @param userReview
	 *            the review details in JSON format
	 */
	public void addReview(String userReview) {
		database.addReview(userReview);
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
		return Collections.unmodifiableSet(database.respondRequest(request, search));
	}

}
