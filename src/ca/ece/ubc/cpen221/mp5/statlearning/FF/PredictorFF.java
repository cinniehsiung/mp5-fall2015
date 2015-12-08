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
    
    /**
     * Constructor for the Predictor Feature Function class.
     * @param a
     *          a value in the regression formula
     * @param b     
     *          b value in the regression formula
     * @param rSquared
     *          r_squared value of the given regression formula
     * @param featureFunction
     *          feature function the regression formula is designed for
     * @param allPoints
     *          list of all points corresponding to this regression formula
     */
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
        
        //System.out.println("x = " + x);   debug purposes
        
        predictedRating = a + b*x;        
        
        if(predictedRating > 5.0){
            predictedRating = 5.0;
        }
        else if(predictedRating < 0.0){
            predictedRating = 0.0;
        }
        
        return predictedRating;
    }

    /**
     * Method to get the a value of the predictor's regression formula, where y = a + bx
     * 
     * @return a double representing the a value of the regression formula
     */
    public double getA(){
        return a;
    }
    
    /**
     * Method to get the b value of the predictor's regression formula, where y = a + bx
     * 
     * @return a double representing the b value of the regression formula
     */
    public double getB(){
        return b;
    }
    
    /**
     * Method to get the r_squared value of the predictor's regression formula for the given set of Points
     * 
     * @return a double representing the r_square value of the regression formula
     */
    public double getRSquared(){
        return rSquared;
    }

    /**
     * Method to get the feature function that the regression formula predictor was created based on.
     * @return a MP5Function for the regression formula (eg. a priceScaleFF if the predictor is predicting rating based on price)
     */
    public MP5Function getFF(){
        return featureFunction;
    }
}
