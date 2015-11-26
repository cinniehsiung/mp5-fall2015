package ca.ece.ubc.cpen221.mp5;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class RestaurantDBServerTest {

	@Test
	public void testRandomReview() {
		try {
			RestaurantDBServer testServer = new RestaurantDBServer(2223, "data/restaurants.json", "data/reviews.json",
					"data/users.json");

			String testReview = testServer.randomReview("La Val's Pizza");
			System.out.println(testReview);

		} catch (IOException e) {
			e.printStackTrace();
			fail("CAN'T READ FILE. DAMMIT.");
		}
	}

	@Test
	public void testGetRestaurant() {
		try {
			RestaurantDBServer testServer = new RestaurantDBServer(22160, "data/restaurants.json", "data/reviews.json",
					"data/users.json");

			String testRestaurant = testServer.getRestaurant("1CBs84C-a-cuA3vncXVSAw");
			System.out.println(testRestaurant);

		} catch (IOException e) {
			e.printStackTrace();
			fail("CAN'T READ FILE. DAMMIT.");
		}
	}

	@Test
	public void testAddRestaurant() {
		try {
			RestaurantDBServer testServer = new RestaurantDBServer(22360, "data/restaurants.json", "data/reviews.json",
					"data/users.json");

			String newRestaurant = "{\"neighborhoods\":[\"UBC Campus\"],\"city\":\"Vancouver\",\"latitude\":37.8755322,\"review_count\":218,\"stars\":3.5,\"full_address\":\"1834 Euclid Ave\nUC Campus Area\nBerkeley, CA 94709\",\"type\":\"business\",\"url\":\"UBC Website\",\"schools\":[\"University of British Columbia\"],\"price\":1,\"name\":\"Tim Horton\",\"categories\":[\"Coffee\",\"Pastries\"],\"state\":\"BC\",\"photo_url\":\"photostuffthati'mtoolazytoput\",\"business_id\":\"7787077207\",\"open\":true,\"longitude\":-122.2603641}";

			testServer.addRestaurant(newRestaurant);

			String testRestaurant = testServer.getRestaurant("7787077207");
			System.out.println(testRestaurant);

		} catch (IOException e) {
			e.printStackTrace();
			fail("CAN'T READ FILE. DAMMIT.");
		}
	}

	@Test
	public void testAddUser() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddReview() {
		fail("Not yet implemented");
	}

}
