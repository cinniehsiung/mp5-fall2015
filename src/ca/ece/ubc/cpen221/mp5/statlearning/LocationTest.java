package ca.ece.ubc.cpen221.mp5.statlearning;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import ca.ece.ubc.cpen221.mp5.Restaurant;
import ca.ece.ubc.cpen221.mp5.server.RestaurantDB;

public class LocationTest {
	
	@Test
	public void testGetDistance() {

		Location loc = new Location(-122.253, 38.87);
		Restaurant res = new Restaurant();
		double distance = loc.getAbsoluteDistance(res);
		assertEquals(distance, 0.002236, 0.0000005);
	}

}
