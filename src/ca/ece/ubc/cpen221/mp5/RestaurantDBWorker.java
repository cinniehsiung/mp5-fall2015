package ca.ece.ubc.cpen221.mp5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class RestaurantDBWorker implements Runnable {

	private Socket clientSocket;
	private RestaurantDB database;

	/**
	 * Helper class to handle one client connection. Returns when client
	 * disconnects.
	 * 
	 * @param clientSocket
	 *            socket where client is connected
	 * @param database
	 *            the database to search through
	 */
	public RestaurantDBWorker(Socket clientSocket, RestaurantDB database) {
		this.clientSocket = clientSocket;
		this.database = database;
		System.out.println("Client connected.");
	}

	@Override
	public void run() {
		try (
				// get streams to communicate with the client from the socket
				PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

		) {
			// if initializing is successful then:
			String inputLine;
			String outputLine;

			// first prompt for a query
			out.println("Hello. Please enter your query:");

			// continue reading from the socket until the client says "Bye."
			while (true) {
				inputLine = in.readLine();

				if (inputLine != null) {
					// exit key
					if (inputLine.equals("Bye.")) {
						break;
					}
					
					// when the client enters a special request process it
					else if (isSpecialRequest(inputLine)) {
						outputLine = processSpecialRequest(inputLine);
					}

					else {
						// when the client enters a query, process it
						outputLine = database.query(inputLine).toString();
						if ("[]".equals(outputLine)) {
							outputLine = "No Results Found. Sorry :(";
						}
					}
					// then print the answer to the socket, self-flushing
					out.println(outputLine);
				}
			}

			// when there is nothing more to say close everything
			out.close();
			in.close();
			clientSocket.close();

		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Can't listen on the port or can't listen for a connection.");
		}

		System.out.println("Client disconnected.");
	}

	/**
	 * Helper method to check whether the request is a query or otherwise. If it
	 * is not a query but instead is one of "randomReview", "getRestaurant",
	 * "addRestaurant", "addUser" or "addReview" then the method returns true.
	 * 
	 * @param request
	 *            the request to check
	 * @return true if the request is one of the special requests enumerated
	 *         above, false otherwise.
	 */
	public static boolean isSpecialRequest(String request) {
		boolean isSpecial = false;

		int indexOfBracket = request.indexOf("(");

		if (indexOfBracket != -1) {

			String requestType = request.substring(0, indexOfBracket);
			if ("randomReview".equals(requestType) || "getRestaurant".equals(requestType)
					|| "addRestaurant".equals(requestType) || "addUser".equals(requestType)
					|| "addReview".equals(requestType)) {
				isSpecial = true;
			}
		}
		return isSpecial;
	}

	final private static int START_INDEX_RANDOMREVIEW = 14;
	final private static int START_INDEX_GETRESTAURANT = 15;
	final private static int START_INDEX_ADDRESTAURANT = 15;
	final private static int START_INDEX_ADDUSER = 9;
	final private static int START_INDEX_ADDREVIEW = 11;

	private String processSpecialRequest(String request) {
		String requestAns = "";
		String requestType = request.substring(0, request.indexOf("("));
		int END_INDEX = request.length() - 2;

		if ("randomReview".equals(requestType)) {
			String restaurantName = request.substring(START_INDEX_RANDOMREVIEW, END_INDEX);
			requestAns = database.randomReview(restaurantName);
		}

		else if ("getRestaurant".equals(requestType)) {
			String businessID = request.substring(START_INDEX_GETRESTAURANT, END_INDEX);
			requestAns = database.getRestaurant(businessID);
		}

		else if ("addRestaurant".equals(requestType)) {
			String restaurantDetails = request.substring(START_INDEX_ADDRESTAURANT, END_INDEX);
			requestAns = database.addRestaurant(restaurantDetails);
		}

		else if ("addUser".equals(requestType)) {
			String userDetails = request.substring(START_INDEX_ADDUSER, END_INDEX);
			requestAns = database.addUser(userDetails);

		}

		else if ("addReview".equals(requestType)) {
			String reviewDetails = request.substring(START_INDEX_ADDREVIEW, END_INDEX);
			requestAns = database.addReview(reviewDetails);
		}

		return requestAns;
	}

}
