package ca.ece.ubc.cpen221.mp5.server;

import static org.junit.Assert.*;

import org.junit.Test;

public class RestaurantDBWorkerTest {

	@Test
	public void testIsSpecialRequest1() {
		boolean test = RestaurantDBWorker.isSpecialRequest("randomReview(wow)");
		assertTrue(test);
	}
	
	@Test
	public void testIsSpecialRequest2() {
		boolean test = RestaurantDBWorker.isSpecialRequest("randomReview(\"wow\")");
		assertTrue(test);
	}
	
	@Test
	public void testIsSpecialRequest3() {
		boolean test = RestaurantDBWorker.isSpecialRequest("getRestaurant(\"wow\")");
		assertTrue(test);
	}
	
	@Test
	public void testIsSpecialRequest4() {
		boolean test = RestaurantDBWorker.isSpecialRequest("addRestaurant(\"wow\")");
		assertTrue(test);
	}
	
	@Test
	public void testIsSpecialRequest5() {
		boolean test = RestaurantDBWorker.isSpecialRequest("addUser(\"wow\")");
		assertTrue(test);
	}
	
	@Test
	public void testIsSpecialRequest6() {
		boolean test = RestaurantDBWorker.isSpecialRequest("addReview(\"wow\")");
		assertTrue(test);
	}
	
	@Test
	public void testIsSpecialRequest7() {
		boolean test = RestaurantDBWorker.isSpecialRequest("yoloswag");
		assertFalse(test);
	}
}
