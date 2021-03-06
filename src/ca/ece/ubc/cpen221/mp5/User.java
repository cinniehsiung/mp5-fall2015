package ca.ece.ubc.cpen221.mp5;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;

public class User {
	/**
	 * Abstraction Function: Represents a User who contributes to the Yelp
	 * database. Review count represents the number of reviews the User has
	 * written. Name represents first name and the initial of the last name of
	 * the user. AvgStars represents the average rating given by the user.
	 */

	// Rep Invariant
	// type should always be "user"
	// 0 <= reviewcount
	// 0 <= avgStars
	// 0 <= votes <= 5

	// userID should be unique to each User such that two Users will have
	// identical userIDs if and only if they are the same User

	// constants for ease of reading and clarity purposes
	final static String URL_KEY = "url";
	final static String REVIEWCOUNT_KEY = "review_count";
	final static String TYPE_KEY = "type";
	final static String USERID_KEY = "user_id";
	final static String NAME_KEY = "name";
	final static String AVGSTARS_KEY = "average_stars";

	final static String VOTES_KEY = "votes";
	final static String COOL_KEY = "cool";
	final static String USEFUL_KEY = "useful";
	final static String FUNNY_KEY = "funny";

	// fields for review
	final private String url;
	final private Map<String, Long> votes = new HashMap<String, Long>();
	private Long reviewCount; // rep invariant greater than 0, reviewCount is
								// equal to the number of ratings done by user

	final private String type; // rep invariant should always be "user"
	final private String userID;
	final private String name;
	final private Double avgStars; // rep invariant greater than 0

	/**
	 * This is the constructor for <b>user</b>.
	 * 
	 * @param obj
	 *            the review details in JSON format
	 */
	public User(JSONObject obj) {
		JSONObject userJSON = obj;
		this.url = (String) userJSON.get(URL_KEY);
		this.reviewCount = (Long) userJSON.get(REVIEWCOUNT_KEY);
		this.type = (String) userJSON.get(TYPE_KEY);
		this.userID = (String) userJSON.get(USERID_KEY);
		this.name = (String) userJSON.get(NAME_KEY);
		this.avgStars = (Double) userJSON.get(AVGSTARS_KEY);

		JSONObject allVotes = (JSONObject) userJSON.get(VOTES_KEY);
		this.votes.put(COOL_KEY, (Long) allVotes.get(COOL_KEY));
		this.votes.put(USEFUL_KEY, (Long) allVotes.get(USEFUL_KEY));
		this.votes.put(FUNNY_KEY, (Long) allVotes.get(FUNNY_KEY));
	}

	/**
	 * This is a error constructor for <b>user</b>.
	 */
	public User() {
		this.url = "Error";
		this.reviewCount = 0L;
		this.type = "Error";
		this.userID = "Error";
		this.name = "Error";
		this.avgStars = 0.0;

		this.votes.put(COOL_KEY, 0L);
		this.votes.put(USEFUL_KEY, 0L);
		this.votes.put(FUNNY_KEY, 0L);
	}

	/**
	 * A method to return the url of the user.
	 * 
	 * @return the url of the user.
	 */
	public String getURL() {
		return url;
	}

	/**
	 * A method to return the votes of the user.
	 * 
	 * @return the votes of the user.
	 */
	public Map<String, Long> getVotes() {
		Map<String, Long> votesClone = new HashMap<String, Long>();
		votesClone.putAll(votes);
		return Collections.unmodifiableMap(votesClone);
	}

	/**
	 * A method to return the review count of the user.
	 * 
	 * @return the review count of the user.
	 */
	public Long getReviewCount() {
		return reviewCount;
	}

	/**
	 * A method to return the type of the user. It should always be "user".
	 * 
	 * @return the type of the user.
	 */
	public String getType() {
		return type;
	}

	/**
	 * A method to return the user ID of the user.
	 * 
	 * @return the user ID of the user.
	 */
	public String getUserID() {
		return userID;
	}

	/**
	 * A method to return the name of the user.
	 * 
	 * @return the name of the user.
	 */
	public String getName() {
		return name;
	}

	/**
	 * A method to return the average stars of the user.
	 * 
	 * @return the average stars of the user.
	 */
	public Double getAvgStars() {
		return avgStars;
	}

	/**
	 * Helper method to increment the user review count for the database.
	 */
	public void incrementReview() {
		this.reviewCount = reviewCount++;
	}

	@Override
	/**
	 * Compares this user to that user. Returns true if the business IDs are the
	 * same.
	 * 
	 * @return true if the users are equal, false otherwise
	 */
	public boolean equals(Object obj) {
		User that = (User) obj;
		return this.userID.equals(that.getUserID());
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
		return this.userID.hashCode();
	}
}
