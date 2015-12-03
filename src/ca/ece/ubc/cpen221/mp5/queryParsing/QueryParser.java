package ca.ece.ubc.cpen221.mp5.queryParsing;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import ca.ece.ubc.cpen221.mp5.Restaurant;
import ca.ece.ubc.cpen221.mp5.server.RestaurantDB;

public class QueryParser {
    private RestaurantDB database;

    /**
     * This method returns a set of restaurants responding to the given query
     * String, or an appropriate error message if the query was not parsed
     * correctly due to syntax errors.
     * 
     * @param query
     *            String containing the query from the client.
     * @param database
     *            Database of Restaurants, Users, and Reviews with which to
     *            respond to query String.
     * @return A set of restaurants that follow the given query, or an Error
     *         Restaurant if the given query was formatted incorrectly and
     *         caused a parsing error.
     */
    public Set<Restaurant> parseQuery(String query, RestaurantDB database) {
        this.database = database;

        String invalidQuery = "This query was invalid, and caused a parsing error.";
        JSONObject errorMessage = new JSONObject();
        errorMessage.put("Error Message", invalidQuery);

        Restaurant error = new Restaurant();

        try {
            // Create a stream of tokens using the lexer.
            CharStream stream = new ANTLRInputStream(query);
            QueryGrammarLexer lexer = new QueryGrammarLexer(stream);
            lexer.reportErrorsAsExceptions();
            TokenStream tokens = new CommonTokenStream(lexer);

            // Feed the tokens into the parser.
            QueryGrammarParser parser = new QueryGrammarParser(tokens);
            parser.reportErrorsAsExceptions();

            // Generate the parse tree using the starter rule.
            ParseTree tree = parser.orExpr(); // starter rule is an orExpression

            // debugging option #1: print the tree to the console
            // System.err.println(tree.toStringTree(parser));

            // debugging option #2: show the tree in a window
            // ((RuleContext) tree).inspect(parser);

            // debugging option #3: walk the tree with a listener
            // new ParseTreeWalker().walk(new
            // QueryGrammarListener_PrintEverything(), tree);

            // walk through the tree using the QueryListener and creator
            ParseTreeWalker walker = new ParseTreeWalker();
            QueryListener_QueryCreator listener = new QueryListener_QueryCreator();
            walker.walk(listener, tree);

            // returns a set of restaurants corresponding to the given query
            return listener.answerQuery();

        } catch (RuntimeException e) {

        }
        // if a RuntimeException was caught from the lexer or parser, create a
        // special set only containing the
        // Error Restaurant
        Restaurant errorRestaurant = new Restaurant();
        Set<Restaurant> errorSet = new HashSet<Restaurant>();
        errorSet.add(errorRestaurant);

        // return this Error Restaurant
        return errorSet;

    }

    private class QueryListener_QueryCreator extends QueryGrammarBaseListener {

        // Stack to store sets of Restaurants from each atomic expression
        private final Stack<Set<Restaurant>> stack = new Stack<Set<Restaurant>>();;

        // final set of restaurants corresponding to query
        private final Set<Restaurant> restaurantSet = Collections.synchronizedSet(new HashSet<Restaurant>());

        // flags for types of query
        boolean isQuery = false;

        // Constants for parsing
        private final int IN_START_INDEX = 4;
        private final int CATEGORY_START_INDEX = 10;
        private final int RATING_START_INDEX = 7;
        private final int PRICE_START_INDEX = 6;
        private final int NAME_START_INDEX = 6;

        private final String RESTAURANT_SET_RESULT = "Set of restaurants responding to query";

        public Set<Restaurant> answerQuery() {

            // there should only be the final set (of Restaurants answering the
            // query) left on the stack
            assert stack.size() == 1;
            restaurantSet.addAll(stack.get(0));

            // clone the set from the stack and add it to the restaurantSet
            Set<Restaurant> clone = Collections.synchronizedSet(Collections.unmodifiableSet(restaurantSet));

            return clone;
        }

        @Override
        public void exitOrExpr(@NotNull QueryGrammarParser.OrExprContext ctx) {
            if (ctx.OR() != null && ctx.getChildCount() > 1) {
                int count = 0;

                // loop through and add sets of Restaurants to each other for
                // each atomic child of the
                // orExpr.
                while (count < (ctx.getChildCount() - 1) / 2 && stack.size() > 1) {
                    Set<Restaurant> result1 = stack.pop();
                    Set<Restaurant> result2 = stack.pop();

                    result1.addAll(result2);

                    stack.push(result1);
                    count++;
                }
            } else {
                // do nothing, because this OrExpr only has one child, so we do
                // not need
                // to process it further
            }

        }

        @Override
        public void exitAndExpr(@NotNull QueryGrammarParser.AndExprContext ctx) {

            if (ctx.AND() != null && ctx.getChildCount() > 1) {
                int count = 0;

                // loop through and unionize (find common Restaurants between
                // two sets)
                // sets of Restaurants to each other for each atomic child of
                // the
                // andExpr.

                while (count < (ctx.getChildCount() - 1) / 2 && stack.size() > 1) {
                    Set<Restaurant> result1 = stack.pop();
                    Set<Restaurant> result2 = stack.pop();

                    result1.retainAll(result2);

                    stack.push(result1);
                    count++;
                }
            } else {
                // do nothing, because this andExpr only has one child, and does
                // not need
                // to be processed further
            }

        }

        @Override
        public void exitAtom(@NotNull QueryGrammarParser.AtomContext ctx) {
            String text = ctx.start.getText();
            isQuery = true;

            // if the atomic expression is for 'in', get the substring of the
            // neighbourhood and respond to the request
            if (ctx.start.getType() == QueryGrammarParser.IN) {
                // where search is the neighbourhood to search for
                String search = text.substring(IN_START_INDEX, text.length() - 2);
                // request is the type of request (in this case, in)
                String request = text.substring(0, IN_START_INDEX - 2);

                System.out.println(search);
                System.out.println(request);

                Set<Restaurant> atomResults = Collections.synchronizedSet(database.respondRequest(request, search));
                stack.push(atomResults);

                // if the atomic expression is for 'category', get the substring
                // of the category and respond to the request
            } else if (ctx.start.getType() == QueryGrammarParser.CATEGORY) {
                // where search is the category to search for
                String search = text.substring(CATEGORY_START_INDEX, text.length() - 2);
                // request is the type of request (in this case, category)
                String request = text.substring(0, CATEGORY_START_INDEX - 2);

                Set<Restaurant> atomResults = Collections.synchronizedSet(database.respondRequest(request, search));
                stack.push(atomResults);

                // if the atomic expression is for 'rating', get the substring
                // of the rating range and respond to the request
            } else if (ctx.start.getType() == QueryGrammarParser.RATING) {
                // where search is the rating range to search for
                String search = text.substring(RATING_START_INDEX, text.length() - 1);
                // request is the type of request (in this case, rating)
                String request = text.substring(0, RATING_START_INDEX - 1);

                Set<Restaurant> atomResults = Collections.synchronizedSet(database.respondRequest(request, search));
                stack.push(atomResults);

                // if the atomic expression is for 'price', get the substring of
                // the price range and respond to the request
            } else if (ctx.start.getType() == QueryGrammarParser.PRICE) {
                // where search is the price range to search for
                String search = text.substring(PRICE_START_INDEX, text.length() - 1);
                // request is the type of request (in this case, price)
                String request = text.substring(0, PRICE_START_INDEX - 1);

                Set<Restaurant> atomResults = Collections.synchronizedSet(database.respondRequest(request, search));
                stack.push(atomResults);

                // if the atomic expression is for 'name', get the substring of
                // the restaurant name and respond to the request
            } else if (ctx.start.getType() == QueryGrammarParser.NAME) {
                // where search is the name of the restaurant to search for
                String search = text.substring(NAME_START_INDEX, text.length() - 2);
                // request is the type of request (in this case, name)
                String request = text.substring(0, NAME_START_INDEX - 2);

                Set<Restaurant> atomResults = Collections.synchronizedSet(database.respondRequest(request, search));
                stack.push(atomResults);
            }
        }
    }

    private static class QueryGrammarListener_PrintEverything extends QueryGrammarBaseListener {

        @Override
        public void enterOrExpr(QueryGrammarParser.OrExprContext ctx) {
            System.err.println("entering or expression " + ctx.getText());
        }

        @Override
        public void exitOrExpr(QueryGrammarParser.OrExprContext ctx) {
            System.err.println("exiting or expression " + ctx.getText());
        }

        @Override
        public void enterAndExpr(QueryGrammarParser.AndExprContext ctx) {
            System.err.println("entering and expression " + ctx.getText());
        }

        @Override
        public void exitAndExpr(QueryGrammarParser.AndExprContext ctx) {
            System.err.println("exiting and expression " + ctx.getText());
        }

        @Override
        public void enterAtom(QueryGrammarParser.AtomContext ctx) {
            System.err.println("entering atom " + ctx.getText());
        }

        @Override
        public void exitAtom(QueryGrammarParser.AtomContext ctx) {
            System.err.println("exiting atom " + ctx.getText());
        }

    }

}
