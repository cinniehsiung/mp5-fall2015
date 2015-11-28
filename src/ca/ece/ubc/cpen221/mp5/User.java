package ca.ece.ubc.cpen221.mp5;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;

// TODO: Use this class to represent a Yelp user.

public class User {
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
	final private Long reviewCount; // rep invariant greater than 0
	final private String type; // rep invariant should always be "user"
	final private String userID;
	final private String name;
	final private Double avgStars; // rep invariant greater than 0

	final private JSONObject userJSON;

	/**
	 * This is the constructor for <b>review</b>.
	 * 
	 * @param obj
	 *            the review details in JSON format
	 */
	public User(JSONObject obj) {
		this.userJSON = (JSONObject) obj.clone();

		this.url = (String) this.userJSON.get(URL_KEY);
		this.reviewCount = (Long) this.userJSON.get(REVIEWCOUNT_KEY);
		this.type = (String) this.userJSON.get(TYPE_KEY);
		this.userID = (String) this.userJSON.get(USERID_KEY);
		this.name = (String) this.userJSON.get(NAME_KEY);
		this.avgStars = (Double) this.userJSON.get(AVGSTARS_KEY);

		JSONObject allVotes = (JSONObject) this.userJSON.get(VOTES_KEY);
		this.votes.put(COOL_KEY, (Long) allVotes.get(COOL_KEY));
		this.votes.put(USEFUL_KEY, (Long) allVotes.get(USEFUL_KEY));
		this.votes.put(FUNNY_KEY, (Long) allVotes.get(FUNNY_KEY));
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
}
