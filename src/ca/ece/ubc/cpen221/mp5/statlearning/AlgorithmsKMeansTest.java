package ca.ece.ubc.cpen221.mp5.statlearning;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Set;

import org.junit.Test;

import ca.ece.ubc.cpen221.mp5.Restaurant;
import ca.ece.ubc.cpen221.mp5.server.RestaurantDB;

public class AlgorithmsKMeansTest {

	@Test
	public void testKMeansClustering() {
		List<Set<Restaurant>> ans;
		RestaurantDB db = new RestaurantDB("data/restaurants.json", "data/reviews.json", "data/users.json");

		ans = Algorithms.kMeansClustering(7, db);
		for (int i = 0; i < ans.size(); i++) {
			System.out.println(ans.get(i).toString());
			System.out.println("Number of Restaurants in Cluster:" + ans.get(i).size());
		}
	}

	@Test
	public void testConvertClustersToJSON() {
		RestaurantDB db = new RestaurantDB("data/restaurants.json", "data/reviews.json", "data/users.json");

		System.out.println(Algorithms.convertClustersToJSON(Algorithms.kMeansClustering(5, db)));
	}

}
