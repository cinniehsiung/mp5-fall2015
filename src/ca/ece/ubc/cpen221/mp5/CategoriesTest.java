package ca.ece.ubc.cpen221.mp5;

import static org.junit.Assert.*;

import org.junit.Test;

import ca.ece.ubc.cpen221.mp5.server.RestaurantDB;

public class CategoriesTest {

	@Test
	public void test() {
		RestaurantDB db = new RestaurantDB("data/restaurants.json", "data/reviews.json", "data/users.json");
		Categories cat = new Categories(db);
		System.out.println(cat.getCategories().toString());
	}

}
