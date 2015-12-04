package ca.ece.ubc.cpen221.mp5.statlearning;

import java.util.List;

import ca.ece.ubc.cpen221.mp5.User;
import ca.ece.ubc.cpen221.mp5.server.RestaurantDB;

public class AlgorithmsLeastSquares {
    /**
     * This method implements a least-squares linear regression to approximate
     * the relationship between the input feature function and the ratings
     * given by the given User.
     * 
     * @param u
     *          User for whom we want to predict ratings.
     * @param db
     *          Database containing all the restaurants, users, and reviews.
     * @param featureFunction
     *          MP5Function for finding a given feature (eg. price)
     * @return a feature function that predicts the users ratings as well as a regression
     *          quality estimate (r_squared).
     * 
     */
    public static MP5Function getPredictor(User u, RestaurantDB db, MP5Function featureFunction) {
        String userID = u.getUserID();
        
        
        
        return null;
    }

    public static MP5Function getBestPredictor(User u, RestaurantDB db, List<MP5Function> featureFunctionList) {
        // TODO: Implement this method
        return null;
    }
}
