package ca.ece.ubc.cpen221.mp5.statlearning;

import ca.ece.ubc.cpen221.mp5.Restaurant;
import ca.ece.ubc.cpen221.mp5.server.RestaurantDB;

public class PriceScaleFF implements MP5Function {

    /**
     * This method returns the price scale for a restaurant.
     * 
     * @param Restaurant
     *          yelpRestaurant, the restaurant we want the price scale for
     *          
     * @param RestaurantDB 
     *          db, the database that the restaurant is in
     *          
     * @return a double representing the price scale of a given restaurant.
     */
    @Override
    public double f(Restaurant yelpRestaurant, RestaurantDB db) {
        return (double) yelpRestaurant.getPrice();
    }

}
