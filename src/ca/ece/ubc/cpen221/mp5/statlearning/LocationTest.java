package ca.ece.ubc.cpen221.mp5.statlearning;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class LocationTest {

	@Test
	public void testGetRandomLocations() {
		List<Location> test = Location.getRandomLocations(5);
		System.out.println(test.toString());
	}

}
