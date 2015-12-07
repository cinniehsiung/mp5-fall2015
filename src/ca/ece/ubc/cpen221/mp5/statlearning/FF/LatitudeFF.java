package ca.ece.ubc.cpen221.mp5.statlearning.FF;

import ca.ece.ubc.cpen221.mp5.Restaurant;
import ca.ece.ubc.cpen221.mp5.server.RestaurantDB;

public class LatitudeFF implements MP5Function {

    /**
     * This method returns the latitude coordinate of the given yelpRestaurant.
     * 
     * @param yelpRestaurant
     *          The given yelpRestaurant we want the latitude coordinate of.
     *          
     * @param db
     *          The database that the yelpRestaurant will be found in.
     *          
     * @return a double representing the latitude cooridnate of a given restaurant.
     */
    @Override
    public double f(Restaurant yelpRestaurant, RestaurantDB db) {
        double[] location = yelpRestaurant.getLocation();
        return location[1];
    }

}
