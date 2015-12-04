package ca.ece.ubc.cpen221.mp5.statlearning;

import ca.ece.ubc.cpen221.mp5.Restaurant;
import ca.ece.ubc.cpen221.mp5.server.RestaurantDB;

public class LongitudeFF implements MP5Function {

    /**
     * This method returns the longitude coordinate of the given restaurant.
     * 
     * @param yelpRestaurant
     *          The restaurant that the method will give the longitude coordinate for
     *          
     * @param db 
     *          The database the yelpRestaurant is in
     *          
     * @return a double representing the longitude coordinate of the given restaurant.
     */
    @Override
    public double f(Restaurant yelpRestaurant, RestaurantDB db) {
        double[] location = yelpRestaurant.getLocation();
        
        return location[0];
    }

}
