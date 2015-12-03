package ca.ece.ubc.cpen221.mp5.queryParsing;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Test;

import ca.ece.ubc.cpen221.mp5.Restaurant;
import ca.ece.ubc.cpen221.mp5.server.RestaurantDB;

public class QueryParserTest {

	RestaurantDB testDB = new RestaurantDB("data/restaurants.json", "data/reviews.json", "data/users.json");

	@Test
	public void simpleTest1() {
		QueryParser parser = new QueryParser();

        String query = "(category(\"Mexican\") && price(1..2)) || (category(\"Hot Dogs\") && in(\"UC Campus Area\") && price(1..3))";

		Set<Restaurant> result = parser.parseQuery(query, testDB);

		for (Restaurant current : result) {
			System.out.println(current.getName());
		}
		if (result.isEmpty()) {
			System.out.println("no results");
		}
	}

	@Test
	public void andTest1() {
		QueryParser parser = new QueryParser();

		String query = "(category(\"Bars\")) || (price(1..3) && rating(4..5))";

		Set<Restaurant> result = parser.parseQuery(query, testDB);

		for (Restaurant current : result) {
			System.out.println(current.getName());
		}
		if (result.isEmpty()) {
			System.out.println("no results");
		}
	}

	@Test
	public void orTest1() {
		String query1 = "category(Chinese) && price(1..2)";
		String query2 = "category(Chinese) && rating(4..5)";

		// query3 only gets query2 stuff, not both of them
		String query3 = "(category(Chinese) && price(1..2)) || (category(Chinese) && rating(4..5))";
	}

	@Test
	public void orTest2() {
		// came up with no restaurants
		String query1 = "(category(Mexican) && price(1..2)) || (category(Hot Dogs) && in(UC Campus Area) && price(1..3))";

		// should be : [Cafe Durant, Gordo Taqueria, Chipotle Mexican Grill, La
		// Cascada Taqueria, Remy's Mexican Restaurant, La Fiesta Mexican
		// Restaurant, Taqueria Reyes, Taqueria El Tacontento, La Burrita,
		// Pancho's, Taco Truck, La Burrita]
		//
		// and
		// [Top Dog, Dojo Dog, Top Dog 2, Top Dog, Desi Dog by Brazil Cafe]

	}

}
