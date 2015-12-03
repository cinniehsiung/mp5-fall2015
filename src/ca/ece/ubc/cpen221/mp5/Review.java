package ca.ece.ubc.cpen221.mp5;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;

// TODO: Use this class to represent a Yelp review.
public class Review {

	// constants for ease of reading and clarity purposes
	final static String TYPE_KEY = "type";
	final static String BUSINESSID_KEY = "business_id";

	final static String VOTES_KEY = "votes";
	final static String COOL_KEY = "cool";
	final static String USEFUL_KEY = "useful";
	final static String FUNNY_KEY = "funny";

	final static String REVIEWID_KEY = "review_id";
	final static String TEXT_KEY = "text";

	final static String STARS_KEY = "stars";
	final static String USERID_KEY = "user_id";
	final static String DATE_KEY = "date";

	// fields for review
	final private String type; // rep invariant should always be "review"
	final private String businessID;
	final private Map<String, Long> votes = new HashMap<String, Long>();
	final private String reviewID;
	final private String text;
	final private long stars; // rep invariant greater than 0
	final private String userID;
	final private String date; // rep invariant year month day valid

	final private JSONObject reviewJSON;

	/**
	 * This is the constructor for <b>review</b>.
	 * 
	 * @param obj
	 *            the review details in JSON format
	 */
	public Review(JSONObject obj) {
		this.reviewJSON = (JSONObject) obj.clone();

		this.type = (String) this.reviewJSON.get(TYPE_KEY);
		this.businessID = (String) this.reviewJSON.get(BUSINESSID_KEY);

		JSONObject allVotes = (JSONObject) this.reviewJSON.get(VOTES_KEY);
		this.votes.put(COOL_KEY, (Long) allVotes.get(COOL_KEY));
		this.votes.put(USEFUL_KEY, (Long) allVotes.get(USEFUL_KEY));
		this.votes.put(FUNNY_KEY, (Long) allVotes.get(FUNNY_KEY));

		this.reviewID = (String) this.reviewJSON.get(REVIEWID_KEY);
		this.text = (String) this.reviewJSON.get(TEXT_KEY);
		this.stars = (Long) this.reviewJSON.get(STARS_KEY);
		this.userID = (String) this.reviewJSON.get(USERID_KEY);
		this.date = (String) this.reviewJSON.get(DATE_KEY);
	}

	public Review clone() {
		return new Review(this.reviewJSON);
	}

	public JSONObject getReviewJSON() {
		return (JSONObject) this.reviewJSON.clone();
	}

	/**
	 * A method to return the type of the review;
	 * 
	 * @return the type of the review (should be review).
	 */
	public String getType() {
		return type;
	}

	/**
	 * A method to return the business ID of the review;
	 * 
	 * @return the business ID of the review.
	 */
	public String getBusinessID() {
		return businessID;
	}

	/**
	 * A method to return the votes of the review;
	 * 
	 * @return the votes of the review.
	 */
	public Map<String, Long> getVotes() {
		Map<String, Long> votesClone = new HashMap<String, Long>();
		votesClone.putAll(votes);
		return Collections.unmodifiableMap(votesClone);
	}

	/**
	 * A method to return the review ID of the review.
	 * 
	 * @return the review ID of the review.
	 */
	public String getReviewID() {
		return reviewID;
	}

	/**
	 * A method to return the text of the review.
	 * 
	 * @return the text of the review.
	 */
	public String getText() {
		return text;
	}

	/**
	 * A method to return the stars of the review.
	 * 
	 * @return the stars of the review.
	 */
	public Long getStars() {
		return stars;
	}

	/**
	 * A method to return the user ID of the review.
	 * 
	 * @return the user ID of the review.
	 */
	public String getUserID() {
		return userID;
	}

	/**
	 * A method to return the date of the review.
	 * 
	 * @return the date of the review.
	 */
	public String getDate() {
		return date;
	}

	@Override
	/**
	 * Compares this review to that review. Returns true if the business IDs are
	 * the same.
	 * 
	 * @return true if the reviews are equal, false otherwise
	 */
	public boolean equals(Object obj) {
		Review that = (Review) obj;
		return this.reviewID.equals(that.getReviewID());
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
		return this.reviewID.hashCode();
	}
}
