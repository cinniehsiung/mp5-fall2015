package ca.ece.ubc.cpen221.mp5;

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

public class TreeTest {
	private RestaurantDB database;

	public Set<Restaurant> parseQuery(String string, RestaurantDB database) {
		this.database = database;

		// Create a stream of tokens using the lexer.
		CharStream stream = new ANTLRInputStream(string);
		QueryGrammarLexer lexer = new QueryGrammarLexer(stream);
		lexer.reportErrorsAsExceptions();
		TokenStream tokens = new CommonTokenStream(lexer);

		// Feed the tokens into the parser.
		QueryGrammarParser parser = new QueryGrammarParser(tokens);
		parser.reportErrorsAsExceptions();

		// Generate the parse tree using the starter rule.
		ParseTree tree = parser.orExpr(); // starter rule is an orExpression

		// debugging option #1: print the tree to the console
		System.err.println(tree.toStringTree(parser));

		// debugging option #2: show the tree in a window
		((RuleContext) tree).inspect(parser);

		// debugging option #3: walk the tree with a listener
		 new ParseTreeWalker().walk(new QueryGrammarListener_PrintEverything(),
		 tree);

		ParseTreeWalker walker = new ParseTreeWalker();
		QueryListener_QueryCreator listener = new QueryListener_QueryCreator();
		walker.walk(listener, tree);

		// return the Document value that the listener created
		return listener.answerQuery();
	}

	private class QueryListener_QueryCreator extends QueryGrammarBaseListener {
		private Stack<Set<Restaurant>> stack = new Stack<Set<Restaurant>>();;
		private Set<Restaurant> restaurantSet = Collections.synchronizedSet(new HashSet<Restaurant>());

		private final int IN_START_INDEX = 3;
		private final int CATEGORY_START_INDEX = 9;
		private final int RATING_START_INDEX = 7;
		private final int PRICE_START_INDEX = 6;
		private final int NAME_START_INDEX = 5;

		public Set<Restaurant> answerQuery() {
			assert stack.size() == 1;
			restaurantSet.addAll(stack.get(0));
			Set<Restaurant> clone = Collections.synchronizedSet(Collections.unmodifiableSet(restaurantSet));

			return clone;
		}

		@Override
		public void exitOrExpr(@NotNull QueryGrammarParser.OrExprContext ctx) {
			if (ctx.OR() != null && ctx.getChildCount() > 1) {
			    int count = 1;
				while (count < ctx.getChildCount()-1 && stack.size() > 1) {
					Set<Restaurant> result1 = stack.pop();
					Set<Restaurant> result2 = stack.pop();

					result1.addAll(result2);

					stack.push(result1);
					count++;
				}
			} else {
				// do nothing, because we just matched a literal and its
				// BooleanLiteral is already on the stack
			}

		}

		@Override
		public void exitAndExpr(@NotNull QueryGrammarParser.AndExprContext ctx) {

			if (ctx.AND() != null && ctx.getChildCount() > 1) {
			    int count = 1;
				while (count < ctx.getChildCount()-1 && stack.size() > 1) {
					Set<Restaurant> result1 = stack.pop();
					Set<Restaurant> result2 = stack.pop();

					result1.retainAll(result2);

					stack.push(result1);
					count++;
				}
			} else {
				// do nothing, because we just matched a literal and its
				// BooleanLiteral is already on the stack
			}

		}

		@Override
		public void exitAtom(@NotNull QueryGrammarParser.AtomContext ctx) {
			String text = ctx.start.getText();
			System.out.println(text); // debug purposes

			if (ctx.start.getType() == QueryGrammarParser.IN) {
				String search = text.substring(IN_START_INDEX, text.length() - 1);
				System.out.println(search); // debug purposes
				String request = text.substring(0, IN_START_INDEX - 1);
				System.out.println(request); // debug purposes

				Set<Restaurant> atomResults = Collections.synchronizedSet(database.respondRequest(request, search));
				stack.push(atomResults);
			} else if (ctx.start.getType() == QueryGrammarParser.CATEGORY) {
				String search = text.substring(CATEGORY_START_INDEX, text.length() - 1);
				System.out.println(search); // debug purposes
				String request = text.substring(0, CATEGORY_START_INDEX - 1);
				System.out.println(request); // debug purposes

				Set<Restaurant> atomResults = Collections.synchronizedSet(database.respondRequest(request, search));
				stack.push(atomResults);
			} else if (ctx.start.getType() == QueryGrammarParser.RATING) {
				String search = text.substring(RATING_START_INDEX, text.length() - 1);
				System.out.println(search); // debug purposes
				String request = text.substring(0, RATING_START_INDEX - 1);
				System.out.println(request); // debug purposes

				Set<Restaurant> atomResults = Collections.synchronizedSet(database.respondRequest(request, search));
				stack.push(atomResults);
			} else if (ctx.start.getType() == QueryGrammarParser.PRICE) {
				String search = text.substring(PRICE_START_INDEX, text.length() - 1);
				System.out.println(search); // debug purposes
				String request = text.substring(0, PRICE_START_INDEX - 1);
				System.out.println(request); // debug purposes

				Set<Restaurant> atomResults = Collections.synchronizedSet(database.respondRequest(request, search));
				stack.push(atomResults);
			} else if (ctx.start.getType() == QueryGrammarParser.NAME) {
				String search = text.substring(NAME_START_INDEX, text.length() - 1);
				System.out.println(search); // debug purposes
				String request = text.substring(0, NAME_START_INDEX - 1);
				System.out.println(request); // debug purposes

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
	      System.err.println("exiting or expression " + ctx.getText()); }
	  
	  @Override
	  public void enterAndExpr(QueryGrammarParser.AndExprContext ctx) {
	      System.err.println("entering and expression " + ctx.getText());
	  }
	  
	  @Override
	  public void exitAndExpr(QueryGrammarParser.AndExprContext ctx) {
	  System.err.println("exiting and expression " + ctx.getText()); }
	  
	  @Override
	  public void enterAtom(QueryGrammarParser.AtomContext ctx) {
	  System.err.println("entering atom " + ctx.getText()); } 
	  
	  public void
	  exitAtom(QueryGrammarParser.AtomContext ctx) { System.err.println(
	  "exiting atom " + ctx.getText()); } 
	  
	  }
	 

}
