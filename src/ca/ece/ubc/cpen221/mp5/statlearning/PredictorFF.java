package ca.ece.ubc.cpen221.mp5.statlearning;

import java.util.List;

import ca.ece.ubc.cpen221.mp5.Restaurant;
import ca.ece.ubc.cpen221.mp5.server.RestaurantDB;

public class PredictorFF implements MP5Function{
    double a;
    double b;
    double rSquared;
    MP5Function featureFunction;
    
    //representation of the line y = ax + b
    //where y is the rating, and x is the feature (eg. price)
    
    public PredictorFF(double a, double b, double rSquared, MP5Function featureFunction){
        this.a = a;
        this.b = b;
        this.rSquared = rSquared;
        this.featureFunction = featureFunction;
    }
    
    /**
     * This feature function returns the predicted rating a user would give,
     * given a yelpRestaurant and the linear regression of the particular feature 
     * and user.
     * 
     * @param yelpRestaurant
     * @param db
     */
    @Override
    public double f(Restaurant yelpRestaurant, RestaurantDB db) {       
        double predictedRating;
        double x = featureFunction.f(yelpRestaurant, db);      
        
        predictedRating = a*x + b;        
        
        return predictedRating;
    }

    public double getA(){
        return a;
    }
    
    public double getB(){
        return b;
    }
    
    public double getRSquared(){
        return rSquared;
    }
    //make clone method
    public MP5Function getFF(){
        return featureFunction;
    }
}
