package ca.ece.ubc.cpen221.mp5.queryParsing;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import ca.ece.ubc.cpen221.mp5.Restaurant;
import ca.ece.ubc.cpen221.mp5.server.RestaurantDB;

public class QueryParserTest {

	RestaurantDB testDB = new RestaurantDB("data/restaurants.json", "data/reviews.json", "data/users.json");

	@Test
	public void simpleTest1() {
		QueryParser parser = new QueryParser();

        String query = "in(\"Telegraph Ave\") && (category(\"Chinese\") || category(\"Italian\")) && price(1..2)";

		Set<Restaurant> result = parser.parseQuery(query, testDB);

		for (Restaurant current : result) {
			System.out.println(current.getName());
		}
		if (result.isEmpty()) {
			System.out.println("no results");
		}
		
		System.out.println();
		//should print out: 
		
		//Happy Valley
		//Peking Express
		//Sun Hong Kong Restaurant
		//Chinese Express
		//Lotus House
		//Mandarin House
		//Chang Luong Restaurant
		//Pasta-In-A-Box
		//Gypsy's Trattoria Italiano 		
	}

	
	@Test
	public void andTest1() {
		QueryParser parser = new QueryParser();

		String query = "category(\"Bars\") || (price(1..3) && rating(4..5))";

		Set<Restaurant> result = parser.parseQuery(query, testDB);

		for (Restaurant current : result) {
		    System.out.println(current.getName());
		}
		if (result.isEmpty()) {
			System.out.println("no results");
		}
		
		System.out.println();
		//should print out:
		
		//for category("Bars") only
		//Henry's
		//The Bear's Lair Brew Pub
		//Pappy's Grill & Sports Bar
		//Freehouse 
		
		//for (price(1..3) && rating(4..5)) only
		//Gypsy's Trattoria Italiano
		//Desi Dog by Brazil Cafe
		//Cafe Espresso Experience
		//Tivoli Caffe
		//The Stuffed Inn
		//Satay House
		//La Fiesta Mexican Restaurant
		//Cheese 'N' Stuff
		//Momo Masala
		//Northside Cafe
		//Cinnaholic
		//Celia's Mexican Catering
		//Stella Nonna Milwaukee Fish Fry
		//Urbann Turbann
		//Sam's Market
		//Musical Offering
		//Planet Kebob & Cafe
		//Dojo Dog
		//Bongo Burger
		//Top Dog
		//Happy Valley
		//Cupkates
		//Turkish Kitchen Express
		//Durant Square - Asian Ghetto
		//Crepes A-Go Go
		//Hummingbird Cafe
		//Pasta Bene
		//Nefeli Caffe
		//Top Dog
		//Berkeley Floor Cafe
		//Foley's Deli
		//Top Dog 2
		//The Coffee Lab
		//I.B.'s Hoagies
		//Taco Truck
		//Wing Fiesta

		//for query, it should print out both sets

	}

	@Test
	public void orTest1() {
		String query1 = "category(\"Chinese\") && price(1..2)";
		String query2 = "category(\"Chinese\") && price(4..5)";

		String query3 = "(category(\"Chinese\") && price(1..2)) || (category(\"Chinese\") && price(4..5))";
		
	    QueryParser parser = new QueryParser();
		Set<Restaurant> result = parser.parseQuery(query3, testDB);

        for (Restaurant current : result) {
            System.out.println(current.getName());
        }
        if (result.isEmpty()) {
            System.out.println("no results");
        }
        
        //should print out:
        
        //Chinese Express
        //Happy Valley
        //Sun Hong Kong Restaurant
        //T C Garden Restaurant
        //Chang Luong Restaurant
        //Mandarin House
        //Peking Express
        //Lotus House

        System.out.println();
	}

	@Test
	public void orTest2() {
		String query1 = "(category(\"Mexican\") && price(1..2)) || (category(\"Hot Dogs\") && in(\"UC Campus Area\") && price(1..3))";

		 QueryParser parser = new QueryParser();
	        Set<Restaurant> result = parser.parseQuery(query1, testDB);

	        for (Restaurant current : result) {
	            System.out.println(current.getName());
	        }
	        if (result.isEmpty()) {
	            System.out.println("no results");
	        }
	        
		// should be : [Cafe Durant, Gordo Taqueria, Chipotle Mexican Grill, La
		// Cascada Taqueria, Remy's Mexican Restaurant, La Fiesta Mexican
		// Restaurant, Taqueria Reyes, Taqueria El Tacontento, La Burrita,
		// Pancho's, Taco Truck, La Burrita]
		//
		// and
		// [Top Dog, Dojo Dog, Top Dog 2, Top Dog, Desi Dog by Brazil Cafe]

	}
	
	@Test
	public void moreTest(){
	    //testing symbols in names
	    String query1 = "name(\"Ramona's Caf\u00e9\")";

        QueryParser parser = new QueryParser();
           Set<Restaurant> result = parser.parseQuery(query1, testDB);

           for (Restaurant current : result) {
               System.out.println(current.getName());
           }
           if (result.isEmpty()) {
               System.out.println("no results");
           }
           
        //should print out:
                      
        //Ramona's Café  

           
	}
	
	@Test
	public void errorTest(){
	    //no quotations around strings, should cause parsing error
	    String query1 = "(category(Mexican) && price(1..2)) || (category(Hot Dogs) && in(UC Campus Area) && price(1..3))";

        QueryParser parser = new QueryParser();
           Set<Restaurant> result = parser.parseQuery(query1, testDB);

           for (Restaurant current : result) {
               System.out.println(current.getName());
           }
           if (result.isEmpty()) {
               System.out.println("no results");
           }
           
           //should print out: Error Message
	}

}
