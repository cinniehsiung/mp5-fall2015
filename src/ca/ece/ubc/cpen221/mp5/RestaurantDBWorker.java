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
					
					outputLine = database.query(inputLine).toString();
					if ("[]".equals(outputLine)) {
						outputLine = "No Results Found. Sorry :(";
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

}
