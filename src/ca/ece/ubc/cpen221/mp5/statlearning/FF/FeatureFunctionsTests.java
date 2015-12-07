package ca.ece.ubc.cpen221.mp5.statlearning.FF;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import ca.ece.ubc.cpen221.mp5.Restaurant;
import ca.ece.ubc.cpen221.mp5.server.RestaurantDB;

public class FeatureFunctionsTests {
    //tests for ensuring that each feature function returns the correct features
    
    
    RestaurantDB db = new RestaurantDB("data/restaurants.json", "data/reviews.json",
            "data/users.json");
    
    Restaurant firstTest = db.findRestaurantIterator("plRUSuB_uY_dEgcMqeZMYA"); 
    //{"open": true, "url": "http://www.yelp.com/biz/durant-square-asian-ghetto-berkeley", "longitude": -122.2579783, 
    //"neighborhoods": ["Telegraph Ave", "UC Campus Area"], "business_id": "plRUSuB_uY_dEgcMqeZMYA", 
    //"name": "Durant Square - Asian Ghetto", "categories": ["Asian Fusion", "Restaurants"], "state": "CA", "type": "business", 
    //"stars": 4.0, "city": "Berkeley", "full_address": "2519-2521 Durant Ave\nTelegraph Ave\nBerkeley, CA 94704", 
    //"review_count": 62, "photo_url": "http://s3-media4.ak.yelpcdn.com/bphoto/9r3CH_iE1erlsqJg35bMHA/ms.jpg", 
    //"schools": ["University of California at Berkeley"], "latitude": 37.8680781, "price": 1}
    
    Restaurant secondTest = db.findRestaurantIterator("ea769Nc6dltC9QYTJXIzuw");
    //{"open": true, "url": "http://www.yelp.com/biz/celias-berkeley", "longitude": -122.259984, 
    //"neighborhoods": ["UC Campus Area"], "business_id": "ea769Nc6dltC9QYTJXIzuw", "name": "Celia's", 
    //"categories": ["Mexican", "Restaurants"], "state": "CA", "type": "business", "stars": 3.5, "city": "Berkeley", 
    //"full_address": "1841 Euclid Ave\nUC Campus Area\nBerkeley, CA 94709", "review_count": 110, 
    //"photo_url": "http://s3-media4.ak.yelpcdn.com/bphoto/ZdHUTChVX6bsilWdVpBHCA/ms.jpg", 
    //"schools": ["University of California at Berkeley"], "latitude": 37.8755809, "price": 4}

    Restaurant thirdTest = db.findRestaurantIterator("WqAtgHTxgS-B8J6iHc4eeA");
    //{"open": true, "url": "http://www.yelp.com/biz/blondies-pizza-berkeley", "longitude": -122.2591966, 
    //"neighborhoods": ["Telegraph Ave", "UC Campus Area"], "business_id": "WqAtgHTxgS-B8J6iHc4eeA", 
    //"name": "Blondie's Pizza", "categories": ["Pizza", "Restaurants"], "state": "CA", "type": "business", 
    //"stars": 3.5, "city": "Berkeley", "full_address": "2340 Telegraph Ave\nTelegraph Ave\nBerkeley, CA 94704", 
    //"review_count": 358, "photo_url": "http://s3-media2.ak.yelpcdn.com/bphoto/iWd8lAs3zRv3KsaOukJT6w/ms.jpg", 
    //"schools": ["University of California at Berkeley"], "latitude": 37.8680339, "price": 1}

    
    @Test
    public void CategoryFFTest() {
        CategoryFF testFF = new CategoryFF();

        
        double result1 = testFF.f(firstTest, db);
        assertEquals(result1, 3.0, 0);
        
        double result2 = testFF.f(secondTest, db);
        assertEquals(result2, 42.0, 0);
        
        double result3 = testFF.f(thirdTest, db);
        assertEquals(result3, 48.0, 0);
    }
    
    @Test
    public void LatitudeFFTest() {
        LatitudeFF testFF = new LatitudeFF();
        
        double result1 = testFF.f(firstTest, db);
        assertEquals(result1, 37.8680781, 0);
        
        double result2 = testFF.f(secondTest, db);
        assertEquals(result2, 37.8755809, 0);
        
        double result3 = testFF.f(thirdTest, db);
        assertEquals(result3, 37.8680339, 0);
    }
    
    @Test
    public void LongitudeFFTest() {
        LongitudeFF testFF = new LongitudeFF();
        
        double result1 = testFF.f(firstTest, db);
        assertEquals(result1, -122.2579783, 0);
        
        double result2 = testFF.f(secondTest, db);
        assertEquals(result2, -122.259984, 0);
        
        double result3 = testFF.f(thirdTest, db);
        assertEquals(result3, -122.2591966, 0);
    }
    
    @Test
    public void MeanRatingFFTest() {
        MeanRatingFF testFF = new MeanRatingFF();
        
        double result1 = testFF.f(firstTest, db);
        assertEquals(result1, 4.0, 0);
        
        double result2 = testFF.f(secondTest, db);
        assertEquals(result2, 3.5, 0);
        
        double result3 = testFF.f(thirdTest, db);
        assertEquals(result3, 3.5, 0);
    }
    
    @Test
    public void PriceScaleFFTest() {
        PriceScaleFF testFF = new PriceScaleFF();
        
        double result1 = testFF.f(firstTest, db);
        assertEquals(result1, 1, 0);
        
        double result2 = testFF.f(secondTest, db);
        assertEquals(result2, 4, 0);
        
        double result3 = testFF.f(thirdTest, db);
        assertEquals(result3, 1, 0);
    }

}
