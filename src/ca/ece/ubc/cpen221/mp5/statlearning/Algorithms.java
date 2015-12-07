package ca.ece.ubc.cpen221.mp5.statlearning;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import ca.ece.ubc.cpen221.mp5.*;
import ca.ece.ubc.cpen221.mp5.server.RestaurantDB;

public class Algorithms {

	/**
	 * Use k-means clustering to compute k clusters for the restaurants in the
	 * database.
	 * 
	 * @param db
	 * @return
	 */
	public static List<Set<Restaurant>> kMeansClustering(int k, RestaurantDB db) {
		List<Location> allCentroids = new ArrayList<Location>();
		RestaurantDB database = db;

		// get initial random centroids
		allCentroids = Location.getRandomLocations(k, db);

		List<Restaurant> allRestaurants = db.getAllRestaurantDetails();

		// get initial restaurant clusters
		Map<Location, Set<Restaurant>> clusters = getRestaurantClusters(allRestaurants, allCentroids);

		// do until reaches steady state
		List<Location> newCentroids = allCentroids;
		List<Location> oldCentroids;
		int count = 0;
		do {
			// save the old centroids for comparison
			oldCentroids = newCentroids;
			// find the new centroids with the new clusters
			newCentroids = findCentroids(clusters);

			// find the new clusters
			System.out.println("Finding new clusters. Loop: " + count);
			clusters = getRestaurantClusters(allRestaurants, newCentroids);
			count++;

		} while (!newCentroids.equals(oldCentroids));

		// create a list to return
		List<Set<Restaurant>> kMeansClusters = Collections.synchronizedList(new ArrayList<Set<Restaurant>>());

		// put all the clusters into the list
		for (Location currentLoc : clusters.keySet()) {
			kMeansClusters.add(clusters.get(currentLoc));
		}

		// return it
		return kMeansClusters;
	}

	public static String convertClustersToJSON(List<Set<Restaurant>> clusters) {
		// TODO: Implement this method
		return null;
	}

	public static MP5Function getPredictor(User u, RestaurantDB db, MP5Function featureFunction) {
		// TODO: Implement this method
		return null;
	}

	public static MP5Function getBestPredictor(User u, RestaurantDB db, List<MP5Function> featureFunctionList) {
		// TODO: Implement this method
		return null;
	}

	/**
	 * Helper method to group restaurants with their clusters in a map. Each
	 * restaurant is a key that maps to their respective centroids.
	 * 
	 * @param database
	 *            the database of restaurants
	 * @param allCentroids
	 *            a list of centroids
	 * @return a map with restaurant-centroid pairs
	 */
	private static Map<Location, Set<Restaurant>> getRestaurantClusters(List<Restaurant> database,
			List<Location> allCentroids) {

		// Need to TEST
		Map<Location, Set<Restaurant>> clusters = new ConcurrentHashMap<Location, Set<Restaurant>>();
		// initialize empty sets for each centroid
		for (Location loc : allCentroids) {
			Set<Restaurant> clusterSet = new HashSet<Restaurant>();
			clusters.put(loc, clusterSet);
		}

		// for every restaurant
		Iterator<Restaurant> ResItr = database.iterator();
		while (ResItr.hasNext()) {
			double distance = Double.MAX_VALUE;
			Restaurant currentRestaurant = ResItr.next();

			// compare the distance between the restaurant and all centroids
			Location closestCentroid = null;
			Iterator<Location> locItr = allCentroids.iterator();
			while (locItr.hasNext()) {
				Location currentCentroid = locItr.next();
				double newDistance = currentCentroid.getAbsoluteDistance(currentRestaurant);

				// if the centroid is closer, save the centroid
				if (newDistance < distance) {
					closestCentroid = currentCentroid;
					// as well as the distance
					distance = newDistance;
				}
			}

			// add the restaurant to the set of the closest centroid
			clusters.get(closestCentroid).add(currentRestaurant);

		}
		return Collections.unmodifiableMap(clusters);
	}

	/**
	 * Helper method to return a list of the new centroids given initial
	 * clusters and initial centroids.
	 * 
	 * @param originalClusters
	 * @return
	 */
	private static List<Location> findCentroids(Map<Location, Set<Restaurant>> originalClusters) {

		List<Location> newCentroids = new ArrayList<Location>();

		for (Location initialCentroid : originalClusters.keySet()) {
			newCentroids.add(findCentroid(originalClusters.get(initialCentroid)));
		}

		return Collections.unmodifiableList(newCentroids);

	}

	/**
	 * Helper method to find the centroid given a set of restaurants.
	 * 
	 * @param cluster
	 *            the set of restaurants.
	 * @return the centroid of the set of restaurants.
	 */
	private static Location findCentroid(Set<Restaurant> cluster) {
		double sumOfLongitudes = 0;
		double sumOfLatitudes = 0;

		for (Restaurant currentRestaurant : cluster) {
			sumOfLongitudes = sumOfLongitudes + currentRestaurant.getLocation()[0];
			sumOfLatitudes = sumOfLatitudes + currentRestaurant.getLocation()[1];
		}

		double newLong = sumOfLongitudes / cluster.size();
		double newLat = sumOfLatitudes / cluster.size();

		return new Location(newLong, newLat);
	}
	
	/**
	 * Helper method to find the centroid given a set of restaurants.
	 * 
	 * @param cluster
	 *            the list of restaurants.
	 * @return the centroid of the set of restaurants.
	 */
	static Location findCentroid(List<Restaurant> cluster) {
		double sumOfLongitudes = 0;
		double sumOfLatitudes = 0;

		for (Restaurant currentRestaurant : cluster) {
			sumOfLongitudes = sumOfLongitudes + currentRestaurant.getLocation()[0];
			sumOfLatitudes = sumOfLatitudes + currentRestaurant.getLocation()[1];
		}

		double newLong = sumOfLongitudes / cluster.size();
		double newLat = sumOfLatitudes / cluster.size();

		return new Location(newLong, newLat);
	}
}