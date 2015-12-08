package ca.ece.ubc.cpen221.mp5.statlearning;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import ca.ece.ubc.cpen221.mp5.Restaurant;
import ca.ece.ubc.cpen221.mp5.User;
import ca.ece.ubc.cpen221.mp5.server.RestaurantDB;
import ca.ece.ubc.cpen221.mp5.statlearning.FF.CategoryFF;
import ca.ece.ubc.cpen221.mp5.statlearning.FF.LatitudeFF;
import ca.ece.ubc.cpen221.mp5.statlearning.FF.LongitudeFF;
import ca.ece.ubc.cpen221.mp5.statlearning.FF.MP5Function;
import ca.ece.ubc.cpen221.mp5.statlearning.FF.MeanRatingFF;
import ca.ece.ubc.cpen221.mp5.statlearning.FF.PriceScaleFF;

public class AlgorithmsLeastSquaresTest {

    RestaurantDB db = new RestaurantDB("data/restaurants.json", "data/reviews.json", "data/users.json");

    Restaurant toPredict = db.findRestaurantIterator("gclB3ED6uk6viWlolSb_uA");
    //{"open": true, "url": "http://www.yelp.com/biz/cafe-3-berkeley",
    //"longitude": -122.260408, "neighborhoods": ["Telegraph Ave", "UC Campus Area"], 
    //"business_id": "gclB3ED6uk6viWlolSb_uA", "name": "Cafe 3",
    //"categories": ["Cafes", "Restaurants"], "state": "CA", "type": "business", "stars": 2.0, 
    //"city": "Berkeley", "full_address": "2400 Durant Ave\nTelegraph Ave\nBerkeley, CA 94701", 
    //"review_count": 9, "photo_url": "http://s3-media1.ak.yelpcdn.com/bphoto/AaHq1UzXiT6zDBUYrJ2NKA/ms.jpg",
    //"schools": ["University of California at Berkeley"], "latitude": 37.867417, "price": 1}

    List<User> allUsers = db.getAllUserDetails();

    // User 1:
    User firstUser = allUsers.get(0);
    String UserID1 = firstUser.getUserID();
    // {"url": "http://www.yelp.com/user_details?userid=_NH7Cpq3qZkByP5xR4gXog",
    // "votes": {"funny": 35, "useful": 21, "cool": 14}, "review_count": 29,
    // "type": "user",
    // "user_id": "_NH7Cpq3qZkByP5xR4gXog", "name": "Chris M.", "average_stars":
    // 3.89655172413793}

    // User 2 - has no reviews:
    String complete = db.addUser("{\"url\": \"cinnie's user page\", \"votes\": {\"funny\": 35, \"useful\": 21, \"cool\": 14}, \"review_count\": 29, \"type\": \"user\", \"user_id\": \"4530\", \"name\": \"Cinnie H.\", \"average_stars\": 3.89655172413793}");
    User noReviewsUser = db.getAllUserDetails().get(allUsers.size());
    //{"url": "http://www.yelp.com/user_details?userid=9fMogxnnd0m9_FKSi-4AoQ", 
    //"votes": {"funny": 0, "useful": 0, "cool": 0}, "review_count": 1,
    //"type": "user", "user_id": "9fMogxnnd0m9_FKSi-4AoQ", "name": 
    //"Ronyde R.", "average_stars": 5.0}


    // User 3:
    User thirdUser = allUsers.get(11);
    String UserID3 = thirdUser.getUserID();
    //{"url": "http://www.yelp.com/user_details?userid=fL8ujZ89qTyhbjr1Qz5aSg",
    //"votes": {"funny": 69, "useful": 185, "cool": 103}, "review_count": 172, 
    //"type": "user", "user_id": "fL8ujZ89qTyhbjr1Qz5aSg", "name": "Hayne P.",
    //"average_stars": 3.86627906976744}


    //feature functions to test with
    LatitudeFF latitudeFFTest = new LatitudeFF();
    LongitudeFF longitudeFFTest = new LongitudeFF();
    MeanRatingFF meanRatingFFTest = new MeanRatingFF();
    PriceScaleFF priceScaleFFTest = new PriceScaleFF();
    CategoryFF categoryFFTest = new CategoryFF();

    
    @Test
    public void testGetPredictor() {
        //latitude prediction
        MP5Function latitudePredictorTest1 = Algorithms.getPredictor(firstUser, db, latitudeFFTest);
        System.out.println("\n");
        // MP5Function latitudePredictorTest2 = AlgorithmsLeastSquares.getPredictor(secondUser, db, latitudeFFTest);
        MP5Function latitudePredictorTest3 = Algorithms.getPredictor(thirdUser, db, latitudeFFTest);
        
        double predictedLa1 = latitudePredictorTest1.f(toPredict, db);
        // double predictedL2a = latitudePredictorTest2.f(toPredict, db);
        double predictedLa3 = latitudePredictorTest3.f(toPredict, db);

        assertEquals(predictedLa1, 4.0, 0);
        assertEquals(predictedLa3, 5.0, 0);
        
        
        //longitude prediction
        MP5Function longitudePredictorTest1 = Algorithms.getPredictor(firstUser, db, longitudeFFTest);
        System.out.println("\n");
        //MP5Function longitudePredictorTest2 = AlgorithmsLeastSquares.getPredictor(secondUser, db, longitudeFFTest);
        MP5Function longitudePredictorTest3 = Algorithms.getPredictor(thirdUser, db, longitudeFFTest);
          
        double predictedLo1 = longitudePredictorTest1.f(toPredict, db);
        // double predictedLo2 = longitudePredictorTest2.f(toPredict, db);
        double predictedLo3 = longitudePredictorTest3.f(toPredict, db);
        
        assertEquals(predictedLo1, 4.0, 0);
        assertEquals(predictedLo3, 0.0, 0);
        
        System.out.println("\n");

        
        //mean rating prediction
        MP5Function mRPredictorTest1 = Algorithms.getPredictor(firstUser, db, meanRatingFFTest);
        System.out.println("\n");
        //MP5Function mRPredictorTest2 = AlgorithmsLeastSquares.getPredictor(secondUser, db, meanRatingFFTest);
        MP5Function mRPredictorTest3 = Algorithms.getPredictor(thirdUser, db, meanRatingFFTest);
           
        double predictedMR1 = mRPredictorTest1.f(toPredict, db);
        // double predictedMR2 = mRPredictorTest2.f(toPredict, db);
        double predictedMR3 = mRPredictorTest3.f(toPredict, db);
        
        assertEquals(predictedMR1, 4.0, 0);
        assertEquals(predictedMR3, 0.0, 0);
        
        System.out.println("\n");

        
        //price scale prediction
        MP5Function pSPredictorTest1 = Algorithms.getPredictor(firstUser, db, priceScaleFFTest);
        System.out.println("\n");
        //MP5Function pSPredictorTest2 = AlgorithmsLeastSquares.getPredictor(secondUser, db, priceScaleFFTest);
        MP5Function pSPredictorTest3 = Algorithms.getPredictor(thirdUser, db, priceScaleFFTest);
        
        double predictedPS1 = pSPredictorTest1.f(toPredict, db);
        // double predictedPS2 = pSPredictorTest2.f(toPredict, db);
        double predictedPS3 = pSPredictorTest3.f(toPredict, db);
        
        assertEquals(predictedPS1, 4.0, 0);
        assertEquals(predictedPS3, 5.0, 0);
        
        System.out.println("\n");

        
        //category prediction
        MP5Function categoryPredictorTest1 = Algorithms.getPredictor(firstUser, db, categoryFFTest);
        System.out.println("\n");
        //MP5Function categoryPredictorTest2 = AlgorithmsLeastSquares.getPredictor(secondUser, db, categoryFFTest);
        MP5Function categoryPredictorTest3 = Algorithms.getPredictor(thirdUser, db, categoryFFTest);
        
        double predictedCa1 = categoryPredictorTest1.f(toPredict, db);
        // double predictedCa2 = categoryPredictorTest2.f(toPredict, db);
        double predictedCa3 = categoryPredictorTest3.f(toPredict, db);
        
        assertEquals(predictedCa1, 4.0, 0);
        assertEquals(predictedCa3, 5.0, 0);        
        
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void noReviewsUserGetPredictor(){
        MP5Function categoryPredictorTest1 = Algorithms.getPredictor(noReviewsUser, db, categoryFFTest);
        
    }

    @Test
    public void testGetBestPredictor() {      
        List<MP5Function> featureFunctionList = new ArrayList<MP5Function>();
        featureFunctionList.add(categoryFFTest);
        featureFunctionList.add(latitudeFFTest);
        featureFunctionList.add(longitudeFFTest);
        featureFunctionList.add(meanRatingFFTest);
        featureFunctionList.add(priceScaleFFTest);
        
        MP5Function bestPredict1 = Algorithms.getBestPredictor(firstUser, db, featureFunctionList);
        MP5Function bestPredict3 = Algorithms.getBestPredictor(thirdUser, db, featureFunctionList);
        
        assertEquals(bestPredict1, priceScaleFFTest);
        assertEquals(bestPredict3, priceScaleFFTest);
    }

    @Test
    public void testCalculateMeans() {
        Point p1 = new Point(1.0, 2.0);
        Point p2 = new Point(5.0, 4.0);
        Point p3 = new Point(4.5, -5.0);
        Point p4 = new Point(0.0, 2.2);
        Point p5 = new Point(-1.0, 7.8);
        Point p6 = new Point(0.0, 0.0);

        List<Point> testList1 = new ArrayList<Point>();
        testList1.add(p1);
        testList1.add(p2);

        List<Point> testList2 = new ArrayList<Point>();
        testList2.add(p3);
        testList2.add(p4);
        testList2.add(p5);
        testList2.add(p2);
        testList2.add(p1);
        testList2.add(p6);

        Point testMean1 = Algorithms.calculateMeans(testList1);
        Point testMean2 = Algorithms.calculateMeans(testList2);

        assertEquals(testMean1, new Point(3.0, 3.0));
        assertEquals(testMean2.getFeature(), 1.583333, 0.000001);
        assertEquals(testMean2.getRating(), 1.833333, 0.000001);

    }

    @Test
    public void testCalculateSumOfSquares() {
        Point p1 = new Point(1.0, 2.0);
        Point p2 = new Point(5.0, 4.0);
        Point p3 = new Point(4.5, -5.0);
        Point p4 = new Point(0.0, 2.2);
        Point p5 = new Point(-1.0, 7.8);
        Point p6 = new Point(0.0, 0.0);

        List<Point> testList1 = new ArrayList<Point>();
        testList1.add(p1);
        testList1.add(p6);

        List<Point> testList2 = new ArrayList<Point>();
        testList2.add(p3);
        testList2.add(p4);
        testList2.add(p5);
        testList2.add(p2);
        testList2.add(p1);
        testList2.add(p6);

        Point testMean1 = Algorithms.calculateMeans(testList1);
        Point testMean2 = Algorithms.calculateMeans(testList2);

        List<Double> test1 = Algorithms.calculateSumOfSquares(testMean1, testList1);
        List<Double> test2 = Algorithms.calculateSumOfSquares(testMean2, testList2);

        double Sxx1 = test1.get(0);
        double Syy1 = test1.get(1);
        double Sxy1 = test1.get(2);

        double Sxx2 = test2.get(0);
        double Syy2 = test2.get(1);
        double Sxy2 = test2.get(2);

        assertEquals(Sxx1, 0.5, 0);
        assertEquals(Syy1, 2.0, 0);
        assertEquals(Sxy1, 1.0, 0);

        assertEquals(Sxx2, 32.208333, 0.000001);
        assertEquals(Syy2, 90.513333, 0.000001);
        assertEquals(Sxy2, -25.716667, 0.000001);

    }

}
