package ca.ece.ubc.cpen221.mp5;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class RestaurantDBServerTest {

	@Test
	public void testRandomReview() {
		try {
			RestaurantDBServer testServer = new RestaurantDBServer(2990, "data/restaurants.json", "data/reviews.json",
					"users.json");

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
			RestaurantDBServer testServer = new RestaurantDBServer(2860, "data/restaurants.json", "data/reviews.json",
					"users.json");

			String testRestaurant = testServer.getRestaurant("1CBs84C-a-cuA3vncXVSAw");
			System.out.println(testRestaurant);

		} catch (IOException e) {
			e.printStackTrace();
			fail("CAN'T READ FILE. DAMMIT.");
		}
	}

	@Test
	public void testAddRestaurant() {
		fail("Not yet implemented");
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
