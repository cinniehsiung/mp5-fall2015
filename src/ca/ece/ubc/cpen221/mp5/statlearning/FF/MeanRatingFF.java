package ca.ece.ubc.cpen221.mp5.statlearning.FF;

import ca.ece.ubc.cpen221.mp5.Restaurant;
import ca.ece.ubc.cpen221.mp5.server.RestaurantDB;

public class MeanRatingFF implements MP5Function {

    /**
     * This method returns the mean rating for the restaurant (the number of stars)
     * 
     * @param Restaurant
     *          yelpRestaurant, the restaurant we want the mean rating for
     *          
     * @param RestaurantDB 
     *          db, the database that the restaurant is in
     *          
     *          
     * @return a double representing the mean rating of a given restaurant.
     */
    @Override
    public double f(Restaurant yelpRestaurant, RestaurantDB db) {

        return yelpRestaurant.getStars();
    }

}
