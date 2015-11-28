package ca.ece.ubc.cpen221.mp5;

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

    
    public static void parseQuery(String string) {
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
    

    
    private static class QueryListener_QueryCreator extends QueryGrammarBaseListener {
        private Stack<String> stack = new Stack<String>();
        
        @Override 
        public void exitOrExpr(@NotNull QueryGrammarParser.OrExprContext ctx) {
            
        }
        
        @Override
        public void exitAtom(@NotNull QueryGrammarParser.AtomContext ctx) {
            
        }
        
        @Override
        public void exitAndExpr(@NotNull QueryGrammarParser.AndExprContext ctx) {
            
        }
        
    }
    
    public void main(String args[]){
        String query = "in(Telegraph Ave) && (category(Chinese) || cateogry(Italian)) && price(1..2)";
        
        parseQuery(query);
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
