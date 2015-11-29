package ca.ece.ubc.cpen221.mp5;

import java.net.URL;
import java.util.Collections;
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
	private double[] location = { 0, 0 }; // rep invariant check legal long/lat
											// numbers
	final private String city;
	final private String address;
	final private String state;
	final private Set<String> neighbourhood = new HashSet<String>();

	final private String businessID;
	final private String name;
	final private String type;
	final private Set<String> categories = new HashSet<String>();

	final private double stars; // rep invariant check if 10 or 5 max
	private long reviewCount;
	final private long price; // rep invariant price > 0
	final private String photo;
	final private Set<String> schools = new HashSet<String>();

	final private JSONObject restaurantJSON;

	/**
	 * This is the constructor for <b>restaurant</b>.
	 * 
	 * @param obj
	 *            the restaurant details in JSON format
	 */
	public Restaurant(JSONObject obj) {
		this.restaurantJSON = (JSONObject) obj.clone();

		this.location[LONGITUDE] = (Double) this.restaurantJSON.get(LONGITUDE_KEY);
		this.location[LATITUDE] = (Double) this.restaurantJSON.get(LATITUDE_KEY);

		this.city = (String) this.restaurantJSON.get(CITY_KEY);
		this.address = (String) this.restaurantJSON.get(ADDRESS_KEY);
		this.state = (String) this.restaurantJSON.get(STATE_KEY);

		JSONArray allNeighborhoods = (JSONArray) this.restaurantJSON.get(NEIGHBORHOOD_KEY);
		for (Object currentNeighbor : allNeighborhoods) {
			this.neighbourhood.add((String) currentNeighbor);
		}

		this.businessID = (String) this.restaurantJSON.get(BUSINESSID_KEY);
		this.name = (String) this.restaurantJSON.get(NAME_KEY);
		this.type = (String) this.restaurantJSON.get(TYPE_KEY);

		JSONArray allCategories = (JSONArray) this.restaurantJSON.get(CATEGORIES_KEY);
		for (Object currentCategory : allCategories) {
			this.categories.add((String) currentCategory);
		}

		this.stars = (Double) this.restaurantJSON.get(STARS_KEY);
		this.reviewCount = (long) this.restaurantJSON.get(REVIEWCOUNT_KEY);
		this.price = (long) this.restaurantJSON.get(PRICE_KEY);
		this.photo = (String) this.restaurantJSON.get(PHOTO_KEY);

		JSONArray allSchools = (JSONArray) this.restaurantJSON.get(SCHOOLS_KEY);
		for (Object currentSchool : allSchools) {
			this.schools.add((String) currentSchool);
		}
	}

	/**
	 * A method to return the restaurant details in JSON format.
	 * 
	 * @return the restaurant details in JSON format.
	 */
	public String getJSONDetails() {
		return this.restaurantJSON.toJSONString();
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

	/**
	 * A method to return a copy of the array containing the location (longitude
	 * and latitude) of a restaurant.
	 * 
	 * @return an array of size 2 where array[0] is the longitude and array[1]
	 *         is the latitude.
	 */
	public double[] getLocation() {
		return location.clone();
	}

	/**
	 * A method to return the city of a restaurant.
	 * 
	 * @return the city of the restaurant.
	 */
	public String getCity() {
		return city;
	}

	/**
	 * A method to return the street address of a restaurant.
	 * 
	 * @return the street address of the restaurant.
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * A method to return the state of a restaurant.
	 * 
	 * @return the state of the restaurant.
	 */
	public String getState() {
		return state;
	}

	/**
	 * A method to return the neighborhoods surrounding the restaurant.
	 * 
	 * @return the neighborhood(s) of the restaurant.
	 */
	public Set<String> getNeighbourhoods() {
		Set<String> neighbourhoodClone = new HashSet<String>();
		neighbourhoodClone.addAll(neighbourhood);
		return Collections.unmodifiableSet(neighbourhoodClone);
	}

	/**
	 * A method to return the type of the restaurant.
	 * 
	 * @return the type of the restaurant.
	 */
	public String getType() {
		return type;
	}

	/**
	 * A method to return the categories of the restaurant.
	 * 
	 * @return the categories of the restaurant.
	 */
	public Set<String> getCategories() {
		Set<String> categoriesClone = new HashSet<String>();
		categoriesClone.addAll(categories);
		return Collections.unmodifiableSet(categoriesClone);
	}

	/**
	 * A method to get the stars of the restaurant.
	 * 
	 * @return the stars of the restaurant.
	 */
	public double getStars() {
		return stars;
	}

	/**
	 * A method to get the review count of the restaurant.
	 * 
	 * @return the review count of the restaurant.
	 */
	public long getReviewCount() {
		return reviewCount;
	}

	/**
	 * A method to get the price of the restaurant.
	 * 
	 * @return the price of the restaurant.
	 */
	public long getPrice() {
		return price;
	}

	/**
	 * A method to get the url to a photo of the restaurant.
	 * 
	 * @return the url of a photo of the restaurant.
	 */
	public String getPhoto() {
		return photo;
	}

	/**
	 * A method to get the universities near the restaurant.
	 * 
	 * @return the universities near the restaurant.
	 */
	public Set<String> getSchools() {
		Set<String> schoolsClone = new HashSet<String>();
		schoolsClone.addAll(schools);
		return Collections.unmodifiableSet(schoolsClone);
	}

	/**
	 * This method returns a cloned version of the given restaurant.
	 * 
	 * @return The cloned restaurant.
	 */
	public Restaurant clone() {
		Restaurant clone = new Restaurant(this.restaurantJSON);

		return clone;
	}

	@Override
	/**
	 * The method returns the String representation of a restaurant -- its name.
	 * 
	 * @return the String representation of a restaurant.
	 */
	public String toString() {
		return name;
	}

}
