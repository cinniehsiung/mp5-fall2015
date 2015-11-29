package ca.ece.ubc.cpen221.mp5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class RestaurantDBClient {

	/**
	 * The constructor for restaurantDBClient that speaks to RestaurantDBServer.
	 * 
	 * @param hostname
	 *            the hostname of the computer running RestaurantDBServer
	 * @param port
	 *            the remote port to listen to, the same as RestaurantDBServer
	 */
	public RestaurantDBClient(String hostname, int port) {
		try (
				// try connecting the client's socket to the server's socket
				Socket echoSocket = new Socket(hostname, port);

				// create streams to write to and from the socket/server
				PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));

		) {

			// initialize a reader that reads from the console input
			BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

			String fromServer;
			String fromUser = null;

			// continue reading until the user inputs "Bye."
			while (true) {
				// if the server sends something, then print to the console
				fromServer = in.readLine();
				if (fromServer != null) {
					System.out.println("Server: " + fromServer);
				}

				// if the user writes something
				fromUser = stdIn.readLine();
				if (fromUser != null) {
					// print to the console for verification purposes
					System.out.println("Client: " + fromUser);
					if (fromUser.equals("Bye.")) {
						break;
					}
					// as well as print to the socket and thus to the server
					out.println(fromUser);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("IO connection failed");
		}
	}

	public static void main(String[] args) {
		RestaurantDBClient client = new RestaurantDBClient(args[0], Integer.parseInt(args[1]));
	}
}
