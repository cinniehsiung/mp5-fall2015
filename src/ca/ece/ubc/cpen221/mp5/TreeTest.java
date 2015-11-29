package ca.ece.ubc.cpen221.mp5;

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
    

    
    public void parseQuery(String string, RestaurantDB database) {
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
        ParseTree tree = parser.query(); // starter rule is an orExpression
        
        // debugging option #1: print the tree to the console
        System.err.println(tree.toStringTree(parser));

        // debugging option #2: show the tree in a window
        ((RuleContext)tree).inspect(parser);

        // debugging option #3: walk the tree with a listener
        //new ParseTreeWalker().walk(new FormulaListener_PrintEverything(), tree);
        
        ParseTreeWalker walker = new ParseTreeWalker();
        QueryListener_QueryCreator listener = new QueryListener_QueryCreator();
        walker.walk(listener, tree);
        
        // return the Document value that the listener created
        return;
    }
    

    
    private class QueryListener_QueryCreator extends QueryGrammarBaseListener {
        private Stack<String> stack = new Stack<String>();
        private Set<Restaurant> restaurantSet = new HashSet<Restaurant>();
        
        private final int IN_START_INDEX = 3;
        private final int CATEGORY_START_INDEX = 9;
        private final int RATING_START_INDEX = 7;
        private final int PRICE_START_INDEX = 6;
        private final int NAME_START_INDEX = 5;
                
        
        @Override 
        public void exitOrExpr(@NotNull QueryGrammarParser.OrExprContext ctx) {
            
        }        
        
        @Override
        public void exitAndExpr(@NotNull QueryGrammarParser.AndExprContext ctx) {
            
        }
        
        @Override
        public void exitAtom(@NotNull QueryGrammarParser.AtomContext ctx) {
            String text = ctx.start.getText();
            System.out.println(text); //debug purposes
            
            if(ctx.start.getType() == QueryGrammarParser.IN){
                String search = text.substring(IN_START_INDEX, text.length() - 1);
                System.out.println(search); //debug purposes
                String request = text.substring(0, IN_START_INDEX-1);
                System.out.println(request); //debug purposes

                restaurantSet.addAll(database.respondRequest(request, search));
            }
            if(ctx.start.getType() == QueryGrammarParser.CATEGORY){
                String search = text.substring(CATEGORY_START_INDEX, text.length() - 1);
                System.out.println(search); //debug purposes
                String request = text.substring(0, CATEGORY_START_INDEX-1);
                System.out.println(request); //debug purposes

                restaurantSet.addAll(database.respondRequest(request, search));

            }
            if(ctx.start.getType() == QueryGrammarParser.RATING){
                String search = text.substring(RATING_START_INDEX, text.length() - 1);
                System.out.println(search); //debug purposes
                String request = text.substring(0, RATING_START_INDEX-1);
                System.out.println(request); //debug purposes

                restaurantSet.addAll(database.respondRequest(request, search));

            }
            if(ctx.start.getType() == QueryGrammarParser.PRICE){
                String search = text.substring(PRICE_START_INDEX, text.length() - 1);
                System.out.println(search); //debug purposes
                String request = text.substring(0, PRICE_START_INDEX-1);
                System.out.println(request); //debug purposes

                restaurantSet.addAll(database.respondRequest(request, search));

            }
            if(ctx.start.getType() == QueryGrammarParser.NAME){
                String search = text.substring(NAME_START_INDEX, text.length() - 1);
                System.out.println(search); //debug purposes
                String request = text.substring(0, NAME_START_INDEX-1);
                System.out.println(request); //debug purposes

                restaurantSet.addAll(database.respondRequest(request, search));

            }
        }
    }
  /*  

    private static class FormulaListener_PrintEverything extends FormulaBaseListener {
        public void enterFormula(FormulaParser.FormulaContext ctx) { System.err.println("entering formula " + ctx.getText()); }
        public void exitFormula(FormulaParser.FormulaContext ctx) { System.err.println("exiting formula " + ctx.getText()); }

        public void enterConjunction(FormulaParser.ConjunctionContext ctx) { System.err.println("entering conjunction " + ctx.getText()); }
        public void exitConjunction(FormulaParser.ConjunctionContext ctx) { System.err.println("exiting conjunction " + ctx.getText()); }

        public void enterLiteral(FormulaParser.LiteralContext ctx) { System.err.println("entering literal " + ctx.getText()); }
        public void exitLiteral(FormulaParser.LiteralContext ctx) { System.err.println("exiting literal " + ctx.getText()); }
    }*/
    
}
