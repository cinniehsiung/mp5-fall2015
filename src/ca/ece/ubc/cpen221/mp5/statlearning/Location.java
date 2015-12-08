package ca.ece.ubc.cpen221.mp5.statlearning;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import ca.ece.ubc.cpen221.mp5.Restaurant;
import ca.ece.ubc.cpen221.mp5.server.RestaurantDB;

public class Location {
	private double longitude;
	private double latitude;

	final static double MIN_LONGITUDE = -122.251;
	final static double MAX_LONGITUDE = -122.266;
	final static double MIN_LATITUDE = 37.86;
	final static double MAX_LATITUDE = 37.88;

	/**
	 * Represents a location with longitude and latitude coordinates in degrees
	 * in the Yelp region our dataset is contained.
	 * 
	 * @param longitude
	 *            must be between -122.251 and -122.266 degrees
	 * @param latitude
	 *            must be between 37.86 and 37.88 degrees
	 */
	public Location(double longitude, double latitude) {
		this.longitude = longitude;
		this.latitude = latitude;
	}
	
	/**
	 * Represents a location with longitude and latitude coordinates in degrees
	 * in the Yelp region our dataset is contained.
	 * 
	 * @param longitude
	 *            must be between -122.251 and -122.266 degrees
	 * @param latitude
	 *            must be between 37.86 and 37.88 degrees
	 */
	public Location(Restaurant res) {
		this.longitude = res.getLocation()[0];
		this.latitude = res.getLocation()[1];
	}

	public double getLongitude() {
		return this.longitude;
	}

	public double getLatitude() {
		return this.latitude;
	}

	@Override
	public boolean equals(Object obj) {
		boolean isEqual = false;

		Location loc = (Location) obj;

		double long1 = this.longitude;
		double lat1 = this.latitude;

		double long2 = loc.longitude;
		double lat2 = loc.latitude;

		if (Math.abs(long1 - long2) <= 0.00000000005 && Math.abs(lat1 - lat2) <= 0.000000000000005) {
			isEqual = true;
		}

		return isEqual;

	}

	@Override
	public int hashCode() {
		String lat = Double.toString(this.latitude);
		String longi = Double.toString(this.longitude);

		return lat.hashCode() + longi.hashCode();
	}

	@Override
	public String toString() {
		return "(" + longitude + ", " + latitude + ")";
	}

	/**
	 * Helper method to calculate the absolute (always postive) distance between
	 * a Location and a Restaurant. Uses the Haversine Formula.
	 * 
	 * @param loc1
	 * @param loc2
	 * @return the absolute distance in kilometers between two Locations
	 */
	public double getAbsoluteDistance(Restaurant restaurant) {

		double long1 = restaurant.getLocation()[0];
		double long2 = this.getLongitude();
		double lat1 = restaurant.getLocation()[1];
		double lat2 = this.getLatitude();

		double latDistance = lat1 - lat2;
		double lngDistance = long1 - long2;

		double c = Math.sqrt(latDistance * latDistance + lngDistance * lngDistance);

		return c;
	}
	
	/**
	 * Helper method to calculate the absolute (always postive) distance between
	 * a Location and a Location. Uses the Haversine Formula.
	 * 
	 * @param loc1
	 * @param loc2
	 * @return the absolute distance in kilometers between two Locations
	 */
	public double getAbsoluteDistance(Location loc) {

		double long1 = loc.getLongitude();
		double long2 = this.getLongitude();
		double lat1 = loc.getLatitude();
		double lat2 = this.getLatitude();

		double latDistance = lat1 - lat2;
		double lngDistance = long1 - long2;

		double c = Math.sqrt(latDistance * latDistance + lngDistance * lngDistance);

		return c;
	}
}
