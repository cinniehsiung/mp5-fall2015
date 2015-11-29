package ca.ece.ubc.cpen221.mp5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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
	public RestaurantDBServer(int port, String restaurantDetails, String userReviews, String userDetails) {
		System.out.println("Making Database.");
		this.database = new RestaurantDB(restaurantDetails, userReviews, userDetails);
		System.out.print("Finished Making Database.");

		try (
				// listen to the specified port and bind to it
				ServerSocket serverSocket = new ServerSocket(port);
				// if the server successfully binds to the port, then accept a
				// connection from a client
				Socket clientSocket = serverSocket.accept();

				// get streams to communicate to and from the client from the
				// socket
				PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

		) {
			// if initializing is successful then:
			String inputLine;
			String outputLine;

			// first prompt for a query
			out.println("Hello. Please enter your query:");

			// now continue reading from the socket until the client says
			// "Bye."
			while (true) {
				inputLine = in.readLine();

				if (inputLine != null) {
					if (inputLine.equals("Bye.")) {
						break;
					}

					// when the client enters a query, process it
					outputLine = database.query(inputLine).toString();
					if ("[]".equals(outputLine)) {
						outputLine = "No Results Found. Sorry :(";
					}
					// then print the answer to the socket
					out.println(outputLine);
				}
			}

			// when there is nothing more to say close everything
			out.close();
			in.close();
			clientSocket.close();
			serverSocket.close();

		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Can't listen on the port or can't listen for a connection.");
		}
	}

	public static void main(String[] args) {
		RestaurantDBServer server = new RestaurantDBServer(Integer.parseInt(args[0]), args[1], args[2], args[3]);
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
