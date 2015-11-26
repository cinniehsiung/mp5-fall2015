package ca.ece.ubc.cpen221.mp5;

import java.net.URL;
import java.util.HashSet;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

// TODO: Use this class to represent a restaurant.
// State the rep invariant and abs

public class Restaurant {
	// constants for ease of reading and clarity purposes
	final static int LONGITUDE = 0;
	final static int LATITUDE = 1;
	final static String LONGITUDE_KEY = "longitude";
	final static String LATITUDE_KEY = "latitude";
	final static String CITY_KEY = "city";
	final static String ADDRESS_KEY = "full_address";
	final static String STATE_KEY = "state";
	final static String NEIGHBORHOOD_KEY = "neighborhoods";

	final static String BUSINESSID_KEY = "business_id";
	final static String NAME_KEY = "name";
	final static String TYPE_KEY = "type";
	final static String CATEGORIES_KEY = "categories";

	final static String STARS_KEY = "stars";
	final static String REVIEWCOUNT_KEY = "review_count";
	final static String PRICE_KEY = "price";
	final static String PHOTO_KEY = "photo_url";
	final static String SCHOOLS_KEY = "schools";

	// fields for restaurant
	private double[] location = { 0, 0 };
	private String city;
	private String address;
	private String state;
	private Set<String> neighbourhood = new HashSet<String>();

	private String businessID = "";
	private String name = "";
	private String type;
	private Set<String> categories = new HashSet<String>();

	private double stars;
	private long reviewCount = 0;
	private long price;
	private String photo;
	private Set<String> schools = new HashSet<String>();

	private JSONObject restaurantObj;

	/**
	 * This is the constructor for <b>restaurant</b>.
	 * 
	 * @param obj
	 *            the restaurant details in JSON format
	 */
	public Restaurant(JSONObject obj) {
		this.restaurantObj = obj;

		this.location[LONGITUDE] = (Double) this.restaurantObj.get(LONGITUDE_KEY);
		this.location[LATITUDE] = (Double) this.restaurantObj.get(LATITUDE_KEY);

		this.city = (String) this.restaurantObj.get(CITY_KEY);
		this.address = (String) this.restaurantObj.get(ADDRESS_KEY);
		this.state = (String) this.restaurantObj.get(STATE_KEY);

		JSONArray allNeighborhoods = (JSONArray) this.restaurantObj.get(NEIGHBORHOOD_KEY);
		for (Object currentNeighbor : allNeighborhoods) {
			this.neighbourhood.add((String) currentNeighbor);
		}

		this.businessID = (String) this.restaurantObj.get(BUSINESSID_KEY);
		this.name = (String) this.restaurantObj.get(NAME_KEY);
		this.type = (String) this.restaurantObj.get(TYPE_KEY);

		JSONArray allCategories = (JSONArray) this.restaurantObj.get(CATEGORIES_KEY);
		for (Object currentCategory : allCategories) {
			this.categories.add((String) currentCategory);
		}

		this.stars = (Double) this.restaurantObj.get(STARS_KEY);
		this.reviewCount = (long) this.restaurantObj.get(REVIEWCOUNT_KEY);
		this.price = (long) this.restaurantObj.get(PRICE_KEY);
		this.photo = (String) this.restaurantObj.get(PHOTO_KEY);

		JSONArray allSchools = (JSONArray) this.restaurantObj.get(SCHOOLS_KEY);
		for (Object currentSchool : allSchools) {
			this.schools.add((String) currentSchool);
		}

	}

	/**
	 * A method to get the business ID of a restaurant.
	 * 
	 * @return the business ID
	 */
	public String getBusinessID() {
		return businessID;
	}

	/**
	 * A method to get the name of the restaurant.
	 * 
	 * @return the name of the restaurant
	 */
	public String getName() {
		return name;
	}
}
