package ca.ece.ubc.cpen221.mp5.statlearning;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import ca.ece.ubc.cpen221.mp5.Restaurant;

public class LocationTest {

	@Test
	public void testGetRandomLocations() {
		List<Location> test = Location.getRandomLocations(5);
		System.out.println(test.toString());
	}

	@Test
	public void testGetDistance() {
		
		Location loc = new Location(-122.253, 38.87);
		Restaurant res = new Restaurant();
		double distance = loc.getAbsoluteDistance(res);
		assertEquals(distance, 0.002236, 0.0000005);
	}

}
