package ca.ece.ubc.cpen221.mp5.statlearning;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import ca.ece.ubc.cpen221.mp5.*;
import ca.ece.ubc.cpen221.mp5.server.RestaurantDB;
import ca.ece.ubc.cpen221.mp5.statlearning.FF.MP5Function;
import ca.ece.ubc.cpen221.mp5.statlearning.FF.PredictorFF;

public class Algorithms {
	private final static int SXX_INDEX = 0;
	private final static int SYY_INDEX = 1;
	private final static int SXY_INDEX = 2;

	/**
	 * Use k-means clustering to compute k clusters for the restaurants in the
	 * database.
	 * 
	 * @param db
	 *            the restaurant database with the restaurants
	 * @param k
	 *            the number of clusters to compute, must be greater than 0
	 * @return the clusters computed through k-means
	 */
	public static List<Set<Restaurant>> kMeansClustering(int k, RestaurantDB db) {
		// create a list to return
		List<Set<Restaurant>> kMeansClusters = Collections.synchronizedList(new ArrayList<Set<Restaurant>>());
		Map<Location, Set<Restaurant>> clusterMap = new ConcurrentHashMap<Location, Set<Restaurant>>();
		List<Restaurant> allRestaurants = db.getAllRestaurantDetails();

		// border case of having no clusters
		if (k <= 0) {
			return kMeansClusters;
		}

		// find initial clusters on restaurant locations
		int index = 0;
		int numCentroids = 0;
		while (numCentroids < k && index < allRestaurants.size()) {
			Location l = new Location(allRestaurants.get(index));

			// only if location is not already there
			if (!clusterMap.keySet().contains(l)) {
				clusterMap.put(l, new HashSet<Restaurant>());
				numCentroids++;
			}
			index++;
		}

		boolean steadyState = false;
		while (!steadyState) {

			// group into clusters around centroids
			for (Location loc : clusterMap.keySet()) {
				clusterMap.get(loc).clear();
			}

			for (Restaurant currentRes : allRestaurants) {
				double minDistance = Double.MAX_VALUE;
				Location closestCentroid = null;

				for (Location curCentroid : clusterMap.keySet()) {
					double newDistance = curCentroid.getAbsoluteDistance(currentRes);
					if (newDistance < minDistance) {
						minDistance = newDistance;
						closestCentroid = curCentroid;
					}
				}

				clusterMap.get(closestCentroid).add(currentRes);
			}

			// find new centroids
			Map<Location, Set<Restaurant>> newClusterMap = new HashMap<Location, Set<Restaurant>>();
			for (Location centroid : clusterMap.keySet()) {
				Set<Restaurant> currentSet = clusterMap.get(centroid);

				// ignore if the set is empty
				if (currentSet.size() > 0) {
					Location newCentroid = findCentroid(currentSet);
					newClusterMap.put(newCentroid, currentSet);
				}
			}

			// if there was no change it means kMeans is finished
			if (newClusterMap.equals(clusterMap)) {
				steadyState = true;
			}

			clusterMap = newClusterMap;

		}

		// put clusters into the list to return
		kMeansClusters.addAll(clusterMap.values());

		// put empty sets if they wanted more clusters
		while (kMeansClusters.size() < k) {
			kMeansClusters.add(new HashSet<Restaurant>());
		}

		// return it
		return Collections.synchronizedList(kMeansClusters);
	}

	/**
	 * A method to convert the clusters calculated from the K-Means method into
	 * a JSON formatted string.
	 * 
	 * @param clusters
	 *            the clusters to convert
	 * @return a JSON formatted string
	 */
	public static String convertClustersToJSON(List<Set<Restaurant>> clusters) {
		JSONArray clustersJSON = new JSONArray();

		int clusterNum = 0;
		for (Set<Restaurant> cluster : clusters) {
			for (Restaurant res : cluster) {
				JSONObject currentObj = new JSONObject();
				currentObj.put("x", res.getLocation()[1]);
				currentObj.put("y", res.getLocation()[0]);
				currentObj.put("name", res.getName());
				currentObj.put("cluster", clusterNum);
				currentObj.put("weight", 4);
				clustersJSON.add(currentObj);
			}
			clusterNum++;
		}

		return clustersJSON.toJSONString();
	}

	/**
	 * This method implements a least-squares linear regression to approximate
	 * the relationship between the input feature function and the ratings given
	 * by the given User.
	 * 
	 * @param u
	 *            User for whom we want to predict ratings.
	 * @param db
	 *            Database containing all the restaurants, users, and reviews.
	 * @param featureFunction
	 *            MP5Function for finding a given feature (eg. price)
	 * @return a feature function that predicts the users ratings as well as a
	 *         regression quality estimate (r_squared).
	 * 
	 */
	public static MP5Function getPredictor(User u, RestaurantDB db, MP5Function featureFunction) {
		String userID = u.getUserID();
		List<Review> allReviews = db.getAllReviewDetails();

		List<Point> allPoints = Collections.synchronizedList(new LinkedList<Point>());

		// iterate through all reviews to find reviews written by the given user
		for (Review currentReview : allReviews) {
			if (currentReview.getUserID().equals(userID)) {
				// once we found a review, find the business id of the
				// restaurant the user reviewed and find the rating
				// the user gave this restaurant
				String currentBusinessID = currentReview.getBusinessID();
				Restaurant currentRestaurant = db.findRestaurantIterator(currentBusinessID);

				// feature of the given restaurant we want to analyze (eg.
				// price)
				double feature = featureFunction.f(currentRestaurant, db);

				System.out.println("Feature: " + feature);
				// rating given by the given user (in stars)
				double ratingGivenByUser = currentReview.getStars();

				System.out.println("Rating: " + ratingGivenByUser);

				Point newPoint = new Point(feature, ratingGivenByUser);
				allPoints.add(newPoint);

			}

		}

		double sumOfSquaresXX;
		double sumOfSquaresYY;
		double sumOfSquaresXY;

		double b = 0.0;
		double a = 0.0;
		double rSquared = 0.0;

		PredictorFF predictorFunction;

		// If allPoints is empty, or if there is only one point, what do we do?
		if (allPoints.size() > 1) {
			Point meanPoint = calculateMeans(allPoints);

			List<Double> sumOfSquares = calculateSumOfSquares(meanPoint, allPoints);
			sumOfSquaresXX = sumOfSquares.get(SXX_INDEX);
			sumOfSquaresYY = sumOfSquares.get(SYY_INDEX);
			sumOfSquaresXY = sumOfSquares.get(SXY_INDEX);

			// System.out.println("Sxx = " + sumOfSquaresXX); debug purposes
			// System.out.println("Syy = " + sumOfSquaresYY); debug purposes
			// System.out.println("Sxy = " + sumOfSquaresXY); debug purposes

			b = sumOfSquaresXY / sumOfSquaresXX;
			a = meanPoint.getRating() - b * meanPoint.getFeature();

			// System.out.println("b = " + b); debug purposes
			// System.out.println("a = " + a); debug purposes

			rSquared = Math.pow(sumOfSquaresXY, 2) / (sumOfSquaresXX * sumOfSquaresYY);
		}

		List<Point> cloneOfAllPoints = Collections.synchronizedList(new LinkedList<Point>());
		cloneOfAllPoints.addAll(allPoints);

		if (allPoints.isEmpty()) {
			throw new IllegalArgumentException(
					"This user has completed 0 reviews - a prediction function cannot be generated. Sorry!");
		}
		predictorFunction = new PredictorFF(a, b, rSquared, featureFunction, cloneOfAllPoints);

		return predictorFunction;
	}

	/**
	 * This method takes a user and a list of feature function, and returns the
	 * feature function with the highest r_squared value.
	 * 
	 * @param u
	 *            User that we want to find the best predictor for.
	 * @param db
	 *            Database that the User and Restaurants are in.
	 * @param featureFunctionList
	 *            A List of MP5Functions from which we want to calculate the
	 *            best predictor.
	 * 
	 * @return a MP5Function that is the best predictor for the give User
	 *         (lowest r_squared value). If there are multiple featureFunctions
	 *         that produce the same r_squared, it will return the latest
	 *         featureFunction in the given featureFunctionList.
	 */
	public static MP5Function getBestPredictor(User u, RestaurantDB db, List<MP5Function> featureFunctionList) {
		MP5Function bestPredictor = null;

		double rSquared = 0.0;

		for (MP5Function currentFunction : featureFunctionList) {
			PredictorFF predictorFF = (PredictorFF) getPredictor(u, db, currentFunction);
			if (predictorFF.getRSquared() >= rSquared) {
				rSquared = predictorFF.getRSquared();
				bestPredictor = currentFunction;
			}
		}

		return bestPredictor;
	}

	/**
	 * This method calculates the mean x (feature) and mean y (rating) value
	 * given a List of Points.
	 * 
	 * @param points
	 *            A List of Points we want to find the means of, cannot be an
	 *            empty List.
	 * @return a Point representing the mean values of x and y of the given List
	 *         of Points.
	 */
	public static Point calculateMeans(List<Point> points) {
		double sumOfX = 0.0;
		double sumOfY = 0.0;
		double numOfPoints = points.size();

		for (Point currentPoint : points) {
			sumOfX += currentPoint.getFeature();
			sumOfY += currentPoint.getRating();
		}

		return new Point(sumOfX / numOfPoints, sumOfY / numOfPoints);
	}

	/**
	 * This method calculates the sum of squares of the given set of points
	 * relative to the given mean point.
	 * 
	 * @param meanPoint
	 *            a Point representing the average of all the points in the
	 *            points List
	 * @param points
	 *            all the points we want to find the sum of squares of
	 * @return a List of Doubles representing the Sxx, Syy, Sxy
	 */
	public static List<Double> calculateSumOfSquares(Point meanPoint, List<Point> points) {
		List<Double> sumOfSquares = new ArrayList<Double>();

		double Sxx = 0.0;
		double Syy = 0.0;
		double Sxy = 0.0;

		double yi = 0.0;
		double xi = 0.0;

		double xMean = meanPoint.getFeature();
		double yMean = meanPoint.getRating();

		for (Point currentPoint : points) {
			xi = currentPoint.getFeature();
			yi = currentPoint.getRating();

			Sxx += Math.pow((xi - xMean), 2);
			Syy += Math.pow((yi - yMean), 2);
			Sxy += (xi - xMean) * (yi - yMean);

		}

		sumOfSquares.add(SXX_INDEX, Sxx);
		sumOfSquares.add(SYY_INDEX, Syy);
		sumOfSquares.add(SXY_INDEX, Sxy);

		return Collections.unmodifiableList(sumOfSquares);

	}

	/**
	 * Helper method to find the centroid given a set of restaurants.
	 * 
	 * @param cluster
	 *            the set of restaurants which cannot be empty.
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
	 * Helper method to find the centroid given a list of restaurants.
	 * 
	 * @param cluster
	 *            the list of restaurants of which cannot be empty.
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