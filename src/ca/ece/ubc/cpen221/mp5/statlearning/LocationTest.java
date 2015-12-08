package ca.ece.ubc.cpen221.mp5.statlearning;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import ca.ece.ubc.cpen221.mp5.Restaurant;
import ca.ece.ubc.cpen221.mp5.server.RestaurantDB;

public class LocationTest {

	@Test
	public void testGetRandomLocations1() {
		List<Location> test = Location.getRandomLocations(5,
				new RestaurantDB("data/restaurants.json", "data/reviews.json", "data/users.json"));
		System.out.println(test.toString());
		assertEquals(test.size(), 5);
	}

	@Test
	public void testGetRandomLocations2() {
		List<Location> test = Location.getRandomLocations(135,
				new RestaurantDB("data/restaurants.json", "data/reviews.json", "data/users.json"));
		System.out.println(test.toString());
		assertEquals(test.size(), 135);
	}
	
	@Test
	public void testGetRandomLocations3() {
		List<Location> test = Location.getRandomLocations(119,
				new RestaurantDB("data/restaurants.json", "data/reviews.json", "data/users.json"));
		System.out.println(test.toString());
		assertEquals(test.size(), 119);
	}
	
	@Test
	public void testGetRandomLocations4() {
		List<Location> test = Location.getRandomLocations(120,
				new RestaurantDB("data/restaurants.json", "data/reviews.json", "data/users.json"));
		System.out.println(test.toString());
		assertEquals(test.size(), 120);
	}
	
	@Test
	public void testGetRandomLocations5() {
		List<Location> test = Location.getRandomLocations(121,
				new RestaurantDB("data/restaurants.json", "data/reviews.json", "data/users.json"));
		System.out.println(test.toString());
		assertEquals(test.size(), 121);
	}
	
	@Test
	public void testGetDistance() {

		Location loc = new Location(-122.253, 38.87);
		Restaurant res = new Restaurant();
		double distance = loc.getAbsoluteDistance(res);
		assertEquals(distance, 0.002236, 0.0000005);
	}

}
