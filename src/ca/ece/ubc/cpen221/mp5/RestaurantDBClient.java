package ca.ece.ubc.cpen221.mp5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class RestaurantDBClient {

	public RestaurantDBClient(String hostname, int port) {
		try (

		Socket echoSocket = new Socket(hostname, port);
				PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));

		) {

			BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

			String fromServer;
			String fromUser = null;

			while (true) {
				fromServer = in.readLine();
				if (fromServer != null) {
					System.out.println("Server: " + fromServer);
				}

				fromUser = stdIn.readLine();
				if (fromUser != null) {
					System.out.println("Client: " + fromUser);
					if (fromUser.equals("Bye.")) {
						break;
					}
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
