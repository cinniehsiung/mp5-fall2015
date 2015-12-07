package ca.ece.ubc.cpen221.mp5.statlearning.FF;

import java.util.List;

import ca.ece.ubc.cpen221.mp5.Restaurant;
import ca.ece.ubc.cpen221.mp5.server.RestaurantDB;
import ca.ece.ubc.cpen221.mp5.statlearning.Point;

public class PredictorFF implements MP5Function{
    double a;
    double b;
    double rSquared;
    MP5Function featureFunction;
    List<Point> allPoints;
    
    
    //representation of the line y = ax + b
    //where y is the rating, and x is the feature (eg. price)
    
    public PredictorFF(double a, double b, double rSquared, MP5Function featureFunction, List<Point> allPoints){
        this.a = a;
        this.b = b;
        this.rSquared = rSquared;
        this.featureFunction = featureFunction;
        this.allPoints = allPoints;
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
        if(allPoints.size() == 1){
            return allPoints.get(0).getRating();
        }
        if(allPoints.isEmpty()){
            return -1.0;
        }
        
        double predictedRating;
        double x = featureFunction.f(yelpRestaurant, db);      
        
        System.out.println("x = " + x);
        
        predictedRating = a + b*x;        
        
        if(predictedRating > 5.0){
            predictedRating = 5.0;
        }
        else if(predictedRating < 0.0){
            predictedRating = 0.0;
        }
        
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
