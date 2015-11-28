package ca.ece.ubc.cpen221.mp5;

import static org.junit.Assert.*;

import org.junit.Test;

public class TreeTestTest {

    @Test
    public void test() {
       
            String query = "in(Telegraph Ave) && (category(Chinese) || category(Italian)) && price(1..2)";
            
            TreeTest.parseQuery(query);
    }

}
