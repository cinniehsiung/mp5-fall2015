package ca.ece.ubc.cpen221.mp5;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;

public class RestaurantTest {

	@Test
	public void test1() {
		JSONParser parser = new JSONParser();
		try {
			JSONObject o = (JSONObject) parser
					.parse(new BufferedReader(new FileReader("data/restaurants.json")).readLine());
			// for (Object o : a) {
			Restaurant testRestaurant = new Restaurant((JSONObject) o);
			assertTrue(true);
			// }

		} catch (FileNotFoundException e) {
			fail("FILE NOT FOUND SHOULDN'T DO THIS YUI HELP ");
			e.printStackTrace();
		} catch (IOException e) {
			fail("CAN'T READ THE FILEEEEE SHOULDN'T DO THIS YUI HELP ");
			e.printStackTrace();
		} catch (ParseException e) {
			fail("STUPID PARSER SHOULDN'T DO THIS YUI HELP ");
			e.printStackTrace();
		}
	}

	@Test
	public void test2() {
		JSONParser parser = new JSONParser();

		try {
			BufferedReader reader = new BufferedReader(new FileReader("data/restaurants.json"));

			String currentLine;
			while ((currentLine = reader.readLine()) != null) {
				JSONObject o = (JSONObject) parser.parse(currentLine);

				Restaurant testRestaurant = new Restaurant((JSONObject) o);
				assertTrue(true);
			}
		} catch (FileNotFoundException e) {
			fail("FILE NOT FOUND SHOULDN'T DO THIS YUI HELP ");
			e.printStackTrace();
		} catch (IOException e) {
			fail("CAN'T READ THE FILEEEEE SHOULDN'T DO THIS YUI HELP ");
			e.printStackTrace();
		} catch (ParseException e) {
			fail("STUPID PARSER SHOULDN'T DO THIS YUI HELP ");
			e.printStackTrace();
		}
	}

}
