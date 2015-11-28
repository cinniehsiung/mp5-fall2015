// Generated from QueryGrammar.g4 by ANTLR 4.4

package ca.ece.ubc.cpen221.mp5;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link QueryGrammarParser}.
 */
public interface QueryGrammarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link QueryGrammarParser#query}.
	 * @param ctx the parse tree
	 */
	void enterQuery(@NotNull QueryGrammarParser.QueryContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryGrammarParser#query}.
	 * @param ctx the parse tree
	 */
	void exitQuery(@NotNull QueryGrammarParser.QueryContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryGrammarParser#orExpr}.
	 * @param ctx the parse tree
	 */
	void enterOrExpr(@NotNull QueryGrammarParser.OrExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryGrammarParser#orExpr}.
	 * @param ctx the parse tree
	 */
	void exitOrExpr(@NotNull QueryGrammarParser.OrExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryGrammarParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterAtom(@NotNull QueryGrammarParser.AtomContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryGrammarParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitAtom(@NotNull QueryGrammarParser.AtomContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryGrammarParser#andExpr}.
	 * @param ctx the parse tree
	 */
	void enterAndExpr(@NotNull QueryGrammarParser.AndExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryGrammarParser#andExpr}.
	 * @param ctx the parse tree
	 */
	void exitAndExpr(@NotNull QueryGrammarParser.AndExprContext ctx);
}