package ca.ece.ubc.cpen221.mp5.statlearning;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Set;

import org.junit.Test;

import ca.ece.ubc.cpen221.mp5.Restaurant;
import ca.ece.ubc.cpen221.mp5.server.RestaurantDB;

public class AlgorithmsTest {

	@Test
	public void testKMeansClustering() {
		List<Set<Restaurant>> ans;

		RestaurantDB db = new RestaurantDB("data/restaurants.json", "data/reviews.json", "data/users.json");

		ans = Algorithms.kMeansClustering(5, db);
		System.out.println(ans.toString());
	}
	
	@Test
	public void testConvertClustersToJSON() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPredictor() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetBestPredictor() {
		fail("Not yet implemented");
	}

}
