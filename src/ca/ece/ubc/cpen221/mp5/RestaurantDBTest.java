package ca.ece.ubc.cpen221.mp5;

import static org.junit.Assert.*;

import org.junit.Test;

public class RestaurantDBTest {

	@Test
	public void testRandomReview() {
		RestaurantDB testDB = new RestaurantDB("data/restaurants.json", "data/reviews.json",
				"data/users.json");

		String testReview = testDB.randomReview("La Val's Pizza");
		System.out.println(testReview);
	}
	
	@Test
	public void testGetRestaurant() {
		RestaurantDB testDB = new RestaurantDB("data/restaurants.json", "data/reviews.json",
				"data/users.json");

		String testRestaurant = testDB.getRestaurant("1CBs84C-a-cuA3vncXVSAw");
		System.out.println(testRestaurant);
	}

	@Test
	public void testAddRestaurant() {
		RestaurantDB testDB = new RestaurantDB("data/restaurants.json", "data/reviews.json",
				"data/users.json");

		String newRestaurant = "{\"neighborhoods\":[\"UBC Campus\"],\"city\":\"Vancouver\",\"latitude\":37.8755322,\"review_count\":218,\"stars\":3.5,\"full_address\":\"1834 Euclid Ave\nUC Campus Area\nBerkeley, CA 94709\",\"type\":\"business\",\"url\":\"UBC Website\",\"schools\":[\"University of British Columbia\"],\"price\":1,\"name\":\"Tim Horton\",\"categories\":[\"Coffee\",\"Pastries\"],\"state\":\"BC\",\"photo_url\":\"photostuffthati'mtoolazytoput\",\"business_id\":\"7787077207\",\"open\":true,\"longitude\":-122.2603641}";

		testDB.addRestaurant(newRestaurant);

		String testRestaurant = testDB.getRestaurant("7787077207");
		System.out.println(testRestaurant);
	}

}
