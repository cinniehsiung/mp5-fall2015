// Generated from QueryGrammar.g4 by ANTLR 4.4

package ca.ece.ubc.cpen221.mp5;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class QueryGrammarParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.4", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		AND=1, OR=2, IN=3, CATEGORY=4, RATING=5, PRICE=6, NAME=7, LPAREN=8, RPAREN=9, 
		STRING=10, RANGE=11, WHITESPACE=12;
	public static final String[] tokenNames = {
		"<INVALID>", "'&&'", "'||'", "IN", "CATEGORY", "RATING", "PRICE", "NAME", 
		"'('", "')'", "STRING", "RANGE", "WHITESPACE"
	};
	public static final int
		RULE_query = 0, RULE_orExpr = 1, RULE_andExpr = 2, RULE_atom = 3;
	public static final String[] ruleNames = {
		"query", "orExpr", "andExpr", "atom"
	};

	@Override
	public String getGrammarFileName() { return "QueryGrammar.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }


	    // This method makes the lexer or parser stop running if it encounters
	    // invalid input and throw a RuntimeException.
	    public void reportErrorsAsExceptions() {
	        //removeErrorListeners();
	        
	        addErrorListener(new ExceptionThrowingErrorListener());
	    }
	    
	    private static class ExceptionThrowingErrorListener extends BaseErrorListener {
	        @Override
	        public void syntaxError(Recognizer<?, ?> recognizer,
	                Object offendingSymbol, int line, int charPositionInLine,
	                String msg, RecognitionException e) {
	            throw new RuntimeException(msg);
	        }
	    }

	public QueryGrammarParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class QueryContext extends ParserRuleContext {
		public AtomContext atom(int i) {
			return getRuleContext(AtomContext.class,i);
		}
		public List<OrExprContext> orExpr() {
			return getRuleContexts(OrExprContext.class);
		}
		public List<AndExprContext> andExpr() {
			return getRuleContexts(AndExprContext.class);
		}
		public TerminalNode RPAREN(int i) {
			return getToken(QueryGrammarParser.RPAREN, i);
		}
		public TerminalNode EOF() { return getToken(QueryGrammarParser.EOF, 0); }
		public List<TerminalNode> LPAREN() { return getTokens(QueryGrammarParser.LPAREN); }
		public AndExprContext andExpr(int i) {
			return getRuleContext(AndExprContext.class,i);
		}
		public List<TerminalNode> RPAREN() { return getTokens(QueryGrammarParser.RPAREN); }
		public List<AtomContext> atom() {
			return getRuleContexts(AtomContext.class);
		}
		public TerminalNode LPAREN(int i) {
			return getToken(QueryGrammarParser.LPAREN, i);
		}
		public OrExprContext orExpr(int i) {
			return getRuleContext(OrExprContext.class,i);
		}
		public QueryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_query; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QueryGrammarListener ) ((QueryGrammarListener)listener).enterQuery(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QueryGrammarListener ) ((QueryGrammarListener)listener).exitQuery(this);
		}
	}

	public final QueryContext query() throws RecognitionException {
		QueryContext _localctx = new QueryContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_query);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(23); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(23);
				switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
				case 1:
					{
					{
					setState(9);
					_la = _input.LA(1);
					if (_la==LPAREN) {
						{
						setState(8); match(LPAREN);
						}
					}

					setState(11); orExpr();
					setState(13);
					_la = _input.LA(1);
					if (_la==RPAREN) {
						{
						setState(12); match(RPAREN);
						}
					}

					}
					}
					break;
				case 2:
					{
					{
					setState(16);
					_la = _input.LA(1);
					if (_la==LPAREN) {
						{
						setState(15); match(LPAREN);
						}
					}

					setState(18); andExpr();
					setState(20);
					_la = _input.LA(1);
					if (_la==RPAREN) {
						{
						setState(19); match(RPAREN);
						}
					}

					}
					}
					break;
				case 3:
					{
					{
					setState(22); atom();
					}
					}
					break;
				}
				}
				setState(25); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << AND) | (1L << OR) | (1L << IN) | (1L << CATEGORY) | (1L << RATING) | (1L << PRICE) | (1L << NAME) | (1L << LPAREN))) != 0) );
			setState(27); match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OrExprContext extends ParserRuleContext {
		public AtomContext atom(int i) {
			return getRuleContext(AtomContext.class,i);
		}
		public List<QueryContext> query() {
			return getRuleContexts(QueryContext.class);
		}
		public List<TerminalNode> OR() { return getTokens(QueryGrammarParser.OR); }
		public List<AtomContext> atom() {
			return getRuleContexts(AtomContext.class);
		}
		public TerminalNode OR(int i) {
			return getToken(QueryGrammarParser.OR, i);
		}
		public QueryContext query(int i) {
			return getRuleContext(QueryContext.class,i);
		}
		public OrExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_orExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QueryGrammarListener ) ((QueryGrammarListener)listener).enterOrExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QueryGrammarListener ) ((QueryGrammarListener)listener).exitOrExpr(this);
		}
	}

	public final OrExprContext orExpr() throws RecognitionException {
		OrExprContext _localctx = new OrExprContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_orExpr);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(30);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << IN) | (1L << CATEGORY) | (1L << RATING) | (1L << PRICE) | (1L << NAME))) != 0)) {
				{
				setState(29); atom();
				}
			}

			setState(37); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(32); match(OR);
					setState(35);
					switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
					case 1:
						{
						setState(33); atom();
						}
						break;
					case 2:
						{
						setState(34); query();
						}
						break;
					}
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(39); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AndExprContext extends ParserRuleContext {
		public AtomContext atom(int i) {
			return getRuleContext(AtomContext.class,i);
		}
		public TerminalNode AND(int i) {
			return getToken(QueryGrammarParser.AND, i);
		}
		public List<QueryContext> query() {
			return getRuleContexts(QueryContext.class);
		}
		public List<TerminalNode> AND() { return getTokens(QueryGrammarParser.AND); }
		public List<AtomContext> atom() {
			return getRuleContexts(AtomContext.class);
		}
		public QueryContext query(int i) {
			return getRuleContext(QueryContext.class,i);
		}
		public AndExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_andExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QueryGrammarListener ) ((QueryGrammarListener)listener).enterAndExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QueryGrammarListener ) ((QueryGrammarListener)listener).exitAndExpr(this);
		}
	}

	public final AndExprContext andExpr() throws RecognitionException {
		AndExprContext _localctx = new AndExprContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_andExpr);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(42);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << IN) | (1L << CATEGORY) | (1L << RATING) | (1L << PRICE) | (1L << NAME))) != 0)) {
				{
				setState(41); atom();
				}
			}

			setState(49); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(44); match(AND);
					setState(47);
					switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
					case 1:
						{
						setState(45); atom();
						}
						break;
					case 2:
						{
						setState(46); query();
						}
						break;
					}
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(51); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AtomContext extends ParserRuleContext {
		public TerminalNode PRICE() { return getToken(QueryGrammarParser.PRICE, 0); }
		public TerminalNode CATEGORY() { return getToken(QueryGrammarParser.CATEGORY, 0); }
		public TerminalNode NAME() { return getToken(QueryGrammarParser.NAME, 0); }
		public TerminalNode RATING() { return getToken(QueryGrammarParser.RATING, 0); }
		public TerminalNode IN() { return getToken(QueryGrammarParser.IN, 0); }
		public AtomContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atom; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QueryGrammarListener ) ((QueryGrammarListener)listener).enterAtom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QueryGrammarListener ) ((QueryGrammarListener)listener).exitAtom(this);
		}
	}

	public final AtomContext atom() throws RecognitionException {
		AtomContext _localctx = new AtomContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_atom);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(53);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << IN) | (1L << CATEGORY) | (1L << RATING) | (1L << PRICE) | (1L << NAME))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\16:\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\3\2\5\2\f\n\2\3\2\3\2\5\2\20\n\2\3\2\5\2\23\n\2\3\2"+
		"\3\2\5\2\27\n\2\3\2\6\2\32\n\2\r\2\16\2\33\3\2\3\2\3\3\5\3!\n\3\3\3\3"+
		"\3\3\3\5\3&\n\3\6\3(\n\3\r\3\16\3)\3\4\5\4-\n\4\3\4\3\4\3\4\5\4\62\n\4"+
		"\6\4\64\n\4\r\4\16\4\65\3\5\3\5\3\5\2\2\6\2\4\6\b\2\3\3\2\5\tB\2\31\3"+
		"\2\2\2\4 \3\2\2\2\6,\3\2\2\2\b\67\3\2\2\2\n\f\7\n\2\2\13\n\3\2\2\2\13"+
		"\f\3\2\2\2\f\r\3\2\2\2\r\17\5\4\3\2\16\20\7\13\2\2\17\16\3\2\2\2\17\20"+
		"\3\2\2\2\20\32\3\2\2\2\21\23\7\n\2\2\22\21\3\2\2\2\22\23\3\2\2\2\23\24"+
		"\3\2\2\2\24\26\5\6\4\2\25\27\7\13\2\2\26\25\3\2\2\2\26\27\3\2\2\2\27\32"+
		"\3\2\2\2\30\32\5\b\5\2\31\13\3\2\2\2\31\22\3\2\2\2\31\30\3\2\2\2\32\33"+
		"\3\2\2\2\33\31\3\2\2\2\33\34\3\2\2\2\34\35\3\2\2\2\35\36\7\2\2\3\36\3"+
		"\3\2\2\2\37!\5\b\5\2 \37\3\2\2\2 !\3\2\2\2!\'\3\2\2\2\"%\7\4\2\2#&\5\b"+
		"\5\2$&\5\2\2\2%#\3\2\2\2%$\3\2\2\2&(\3\2\2\2\'\"\3\2\2\2()\3\2\2\2)\'"+
		"\3\2\2\2)*\3\2\2\2*\5\3\2\2\2+-\5\b\5\2,+\3\2\2\2,-\3\2\2\2-\63\3\2\2"+
		"\2.\61\7\3\2\2/\62\5\b\5\2\60\62\5\2\2\2\61/\3\2\2\2\61\60\3\2\2\2\62"+
		"\64\3\2\2\2\63.\3\2\2\2\64\65\3\2\2\2\65\63\3\2\2\2\65\66\3\2\2\2\66\7"+
		"\3\2\2\2\678\t\2\2\28\t\3\2\2\2\16\13\17\22\26\31\33 %),\61\65";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}