package ca.ece.ubc.cpen221.mp5.statlearning.FF;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import ca.ece.ubc.cpen221.mp5.Restaurant;
import ca.ece.ubc.cpen221.mp5.server.RestaurantDB;

public class CategoryFF implements MP5Function {
    //map to represent the mapping from category -> integer
    private Map<String, Integer> categoriesDB = new ConcurrentHashMap<String, Integer>();


    /**
     * This method returns the category of the given yelpRestaurant.
     * 
     * @param yelpRestaurant
     *          The given yelpRestaurant we want the category of.
     *          
     * @param db
     *          The database that the yelpRestaurant will be found in.
     *          
     * @return a double representing the category of a given restaurant. If a restaurant has multiple categories, it will
     *          return the first category of the restaurant.
     */
    
    @Override
    public double f(Restaurant yelpRestaurant, RestaurantDB db) {
        List<String> catList = new ArrayList<String>();
        List<Restaurant> resList = db.getAllRestaurantDetails();

        for (Restaurant curRes : resList) {
            for (String curCat : curRes.getCategories()) {
                if (!catList.contains(curCat)) {
                    catList.add(curCat);
                }
            }
        }

        Collections.sort(catList);

        int count = 0;
        for (String curCat : catList) {
            categoriesDB.put(curCat, count);
            count++;
        }
        
        
        Set<String> allCategories = yelpRestaurant.getCategories();  
        return (double) categoriesDB.get(allCategories.iterator().next());

    
    }

}
