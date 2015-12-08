package ca.ece.ubc.cpen221.mp5.statlearning;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Set;

import org.junit.Test;

import ca.ece.ubc.cpen221.mp5.Restaurant;
import ca.ece.ubc.cpen221.mp5.server.RestaurantDB;

public class AlgorithmsKMeansTest {

	@Test
	public void testKMeansClustering1() {
		List<Set<Restaurant>> ans;
		RestaurantDB db = new RestaurantDB("data/restaurants.json", "data/reviews.json", "data/users.json");

		ans = Algorithms.kMeansClustering(5, db);
		for (int i = 0; i < ans.size(); i++) {
			System.out.println(ans.get(i).toString());
			System.out.println("Number of Restaurants in Cluster:" + ans.get(i).size());
		}

		assertEquals(ans.size(), 5);
		System.out.println(Algorithms.convertClustersToJSON(ans)); // visualize
	}

	@Test
	public void testKMeansClustering2() {
		List<Set<Restaurant>> ans;
		RestaurantDB db = new RestaurantDB("data/restaurants.json", "data/reviews.json", "data/users.json");

		ans = Algorithms.kMeansClustering(1, db);
		for (int i = 0; i < ans.size(); i++) {
			System.out.println(ans.get(i).toString());
			System.out.println("Number of Restaurants in Cluster:" + ans.get(i).size());
		}
		
		assertEquals(ans.size(), 1);
		System.out.println(Algorithms.convertClustersToJSON(ans)); // visualize
	}

	@Test
	public void testKMeansClustering3() {
		List<Set<Restaurant>> ans;
		RestaurantDB db = new RestaurantDB("data/restaurants.json", "data/reviews.json", "data/users.json");

		ans = Algorithms.kMeansClustering(0, db);
		for (int i = 0; i < ans.size(); i++) {
			System.out.println(ans.get(i).toString());
			System.out.println("Number of Restaurants in Cluster:" + ans.get(i).size());
		}

		assertEquals(ans.size(), 0);
		System.out.println(Algorithms.convertClustersToJSON(ans)); // visualize
	}

	@Test
	public void testKMeansClustering4() {
		List<Set<Restaurant>> ans;
		RestaurantDB db = new RestaurantDB("data/restaurants.json", "data/reviews.json", "data/users.json");

		ans = Algorithms.kMeansClustering(136, db);
		for (int i = 0; i < ans.size(); i++) {
			System.out.println(ans.get(i).toString());
			System.out.println("Number of Restaurants in Cluster:" + ans.get(i).size());
		}

		assertEquals(ans.size(), 136);
		System.out.println(Algorithms.convertClustersToJSON(ans)); // visualize
	}
	
	@Test
	public void testKMeansClustering5() {
		List<Set<Restaurant>> ans;
		RestaurantDB db = new RestaurantDB("data/restaurants.json", "data/reviews.json", "data/users.json");

		ans = Algorithms.kMeansClustering(120, db);
		for (int i = 0; i < ans.size(); i++) {
			System.out.println(ans.get(i).toString());
			System.out.println("Number of Restaurants in Cluster:" + ans.get(i).size());
		}

		assertEquals(ans.size(), 120);
		System.out.println(Algorithms.convertClustersToJSON(ans)); // visualize
	}
	
	@Test
	public void testKMeansClustering6() {
		List<Set<Restaurant>> ans;
		RestaurantDB db = new RestaurantDB("data/restaurants.json", "data/reviews.json", "data/users.json");

		ans = Algorithms.kMeansClustering(135, db);
		for (int i = 0; i < ans.size(); i++) {
			System.out.println(ans.get(i).toString());
			System.out.println("Number of Restaurants in Cluster:" + ans.get(i).size());
		}

		assertEquals(ans.size(), 135);
		System.out.println(Algorithms.convertClustersToJSON(ans)); // visualize
	}

}
