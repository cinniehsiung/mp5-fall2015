package ca.ece.ubc.cpen221.mp5;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Test;

public class TreeTestTest {

    @Test
    public void test() {
            RestaurantDB testDB = new RestaurantDB("data/restaurants.json", "data/reviews.json",
                "data/users.json");
            
            TreeTest parser = new TreeTest();
            
            String query = "in(Telegraph Ave) && (category(Chinese) || category(Italian)) && price(1..2)";
            
            Set<Restaurant> result = parser.parseQuery(query, testDB);
            
            for(Restaurant current : result){
                System.out.println(current.getName());
            }
            if(result.isEmpty()){
                System.out.println("no results");
            }
    }
    
   

}
