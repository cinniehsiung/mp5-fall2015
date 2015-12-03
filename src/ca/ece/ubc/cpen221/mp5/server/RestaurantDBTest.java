package ca.ece.ubc.cpen221.mp5.server;

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

	//{"open": true, "url": "yoloswag", "longitude": -2.290, "neighborhoods": ["UBC Campus Area"], "business_id": "7787077207", "name": "Calvin's BBT Place", "categories": ["BBT", "Restaurants"], "state": "BC", "type": "business", "stars": 2.5, "city": "Vancouver", "full_address": "2920 Wesbrook Mall", "review_count": 8, "photo_url": "calvinbbtplace.com", "schools": ["University of British Columbia"], "latitude": 2.209, "price": 2}
	//{"type": "review", "business_id": "7787077207", "votes": {"cool": 0, "useful": 0, "funny": 0}, "review_id": "reviewmoop", "text": "calvin's bbt place SUCKS", "stars": 2, "user_id": "90wm_01FAIqhcgV_mPON9Q", "date": "2006-07-26"}
	//{"url": "cinnie's user page", "votes": {"funny": 35, "useful": 21, "cool": 14}, "review_count": 29, "type": "user", "user_id": "4530", "name": "Cinnie H.", "average_stars": 3.89655172413793}

	
}
