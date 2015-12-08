package ca.ece.ubc.cpen221.mp5;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

// TODO: Use this class to represent a restaurant.
// State the rep invariant and abs

public class Restaurant {
	/**
	 * Abstraction Function: TODO
	 */

	// Rep Invariant:

	// No fields can be null. city, address, state, neighborhoods and schools
	// must
	// represent real cities, address, states, neighborhoods and universities.
	// -180 <= Location[0] <= 180
	// -90 <= Location[1] <= 90 (max and min values for longitude and latitude)

	// BusinessIDs for each restaurant must be unique such that this restaurant
	// is equal to that restaurant if and only if they are the same restaurant.

	// 0 <= stars <= 5. (max and min ratings)
	// 0 <= review count (can not have negative reviews)
	// 0 <= price <= 5. (max and min price ratings)

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
	
	final static String OPEN_KEY = "open";

	// fields for restaurant
	final private double[] location = new double[2];
	
	final private boolean open;
	
	// numbers
	final private String city;
	final private String address;
	final private String state;
	final private Set<String> neighbourhood = new HashSet<String>();

	final private String businessID;
	final private String name;
	final private String type;
	final private Set<String> categories = new HashSet<String>();

	final private double stars; // rep invariant check if 5 max
	private long reviewCount; // RI: is equal to the number of ratings (stars)
	final private long price; // rep invariant price > 0
	final private String photo;
	final private Set<String> schools = new HashSet<String>();

	/**
	 * This is the constructor for an error restaurant. Used to signal errors in
	 * the queries clients enter into the server.
	 */
	public Restaurant() {
		this.location[LONGITUDE] = 0;
		this.location[LATITUDE] = 0;

		this.city = "Error";
		this.address = "Error";
		this.state = "Error";

		this.businessID = "Error";
		this.name = "Error Message";
		this.type = "Error";
		this.stars = -1.0;
		this.reviewCount = -1;
		this.price = -1;
		this.photo = "Error";
		
		this.open = false;

	}

	/**
	 * This is the constructor for <b>restaurant</b>.
	 * 
	 * @param obj
	 *            the restaurant details in JSON format
	 */
	public Restaurant(JSONObject obj) {
		JSONObject restaurantJSON = (JSONObject) obj.clone();

		this.location[LONGITUDE] = (Double) restaurantJSON.get(LONGITUDE_KEY);
		this.location[LATITUDE] = (Double) restaurantJSON.get(LATITUDE_KEY);

		this.city = (String) restaurantJSON.get(CITY_KEY);
		this.address = (String) restaurantJSON.get(ADDRESS_KEY);
		this.state = (String) restaurantJSON.get(STATE_KEY);

		JSONArray allNeighborhoods = (JSONArray) restaurantJSON.get(NEIGHBORHOOD_KEY);
		for (Object currentNeighbor : allNeighborhoods) {
			this.neighbourhood.add((String) currentNeighbor);
		}

		this.businessID = (String) restaurantJSON.get(BUSINESSID_KEY);
		this.name = (String) restaurantJSON.get(NAME_KEY);
		this.type = (String) restaurantJSON.get(TYPE_KEY);

		JSONArray allCategories = (JSONArray) restaurantJSON.get(CATEGORIES_KEY);
		for (Object currentCategory : allCategories) {
			this.categories.add((String) currentCategory);
		}

		this.stars = (Double) restaurantJSON.get(STARS_KEY);
		this.reviewCount = (long) restaurantJSON.get(REVIEWCOUNT_KEY);
		this.price = (long) restaurantJSON.get(PRICE_KEY);
		this.photo = (String) restaurantJSON.get(PHOTO_KEY);

		JSONArray allSchools = (JSONArray) restaurantJSON.get(SCHOOLS_KEY);
		for (Object currentSchool : allSchools) {
			this.schools.add((String) currentSchool);
		}
		
		this.open = (boolean) restaurantJSON.get(OPEN_KEY);
	}

	/**
	 * A method to return the restaurant details in JSON format.
	 * 
	 * @return the restaurant details in JSON format.
	 */
	public String getJSONDetails() {
		return this.getRestaurantJSON().toJSONString();
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
	 * A method to return a JSONObject with the restaurant details.
	 */
	public JSONObject getRestaurantJSON() {
		JSONObject restaurantJSON = new JSONObject();
		
		restaurantJSON.put(OPEN_KEY, this.open);

		restaurantJSON.put(LONGITUDE_KEY, this.location[LONGITUDE]);
		restaurantJSON.put(LATITUDE_KEY, this.location[LATITUDE]);

		restaurantJSON.put(CITY_KEY, this.city);
		restaurantJSON.put(ADDRESS_KEY, this.address);
		restaurantJSON.put(STATE_KEY, this.state);

		JSONArray allNeighborhoods = new JSONArray();
		for (String currentNeighbor : this.neighbourhood) {
			allNeighborhoods.add(currentNeighbor);
		}
		restaurantJSON.put(NEIGHBORHOOD_KEY, allNeighborhoods);

		restaurantJSON.put(BUSINESSID_KEY, this.businessID);
		restaurantJSON.put(NAME_KEY, this.name);
		restaurantJSON.put(TYPE_KEY, this.type);

		JSONArray allCategories = new JSONArray();
		for (String currentCategory : this.categories) {
			allCategories.add(currentCategory);
		}
		restaurantJSON.put(CATEGORIES_KEY, allCategories);

		restaurantJSON.put(STARS_KEY, this.stars);
		restaurantJSON.put(REVIEWCOUNT_KEY, this.reviewCount);
		restaurantJSON.put(PRICE_KEY, this.price);
		restaurantJSON.put(PHOTO_KEY, this.photo);

		JSONArray allSchools = new JSONArray();
		for (String currentSchool : this.schools) {
			allSchools.add(currentSchool);
		}
		restaurantJSON.put(SCHOOLS_KEY, allSchools);

		return restaurantJSON;
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
		return new Restaurant(this.getRestaurantJSON());
	}

	/**
	 * Helper method to increment the review count for the restaurant.
	 */
	public void incrementReview() {
		this.reviewCount = reviewCount++;
	}

	@Override
	/**
	 * The method returns the String representation of a restaurant -- its
	 * details in JSON format.
	 * 
	 * @return the String representation of a restaurant.
	 */
	public String toString() {
		return this.getJSONDetails();
		// return this.name;
	}

	@Override
	/**
	 * Compares this restaurant to that restaurant. Returns true if the business
	 * IDs are the same.
	 * 
	 * @return true if the restaurants are equal
	 */
	public boolean equals(Object obj) {
		Restaurant that = (Restaurant) obj;
		return this.businessID.equals(that.getBusinessID());
	}

	@Override
	/**
	 * Returns a hash code value for the object. This method is supported for
	 * the benefit of hash tables such as those provided by java.util.HashMap.
	 * 
	 * If two objects are equal than they will have the same hashcode.
	 * 
	 * @return the hashcode
	 */
	public int hashCode() {
		return this.businessID.hashCode();
	}

}
