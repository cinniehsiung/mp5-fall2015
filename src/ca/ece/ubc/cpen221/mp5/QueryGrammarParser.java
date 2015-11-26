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
		RULE_orExpr = 0, RULE_andExpr = 1, RULE_atom = 2;
	public static final String[] ruleNames = {
		"orExpr", "andExpr", "atom"
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
	public static class OrExprContext extends ParserRuleContext {
		public List<AndExprContext> andExpr() {
			return getRuleContexts(AndExprContext.class);
		}
		public AndExprContext andExpr(int i) {
			return getRuleContext(AndExprContext.class,i);
		}
		public List<TerminalNode> OR() { return getTokens(QueryGrammarParser.OR); }
		public TerminalNode OR(int i) {
			return getToken(QueryGrammarParser.OR, i);
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
		enterRule(_localctx, 0, RULE_orExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(6); andExpr();
			setState(11);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OR) {
				{
				{
				setState(7); match(OR);
				setState(8); andExpr();
				}
				}
				setState(13);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
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
		public List<TerminalNode> AND() { return getTokens(QueryGrammarParser.AND); }
		public List<AtomContext> atom() {
			return getRuleContexts(AtomContext.class);
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
		enterRule(_localctx, 2, RULE_andExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(14); atom();
			setState(19);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AND) {
				{
				{
				setState(15); match(AND);
				setState(16); atom();
				}
				}
				setState(21);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
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
		public TerminalNode LPAREN() { return getToken(QueryGrammarParser.LPAREN, 0); }
		public TerminalNode RATING() { return getToken(QueryGrammarParser.RATING, 0); }
		public TerminalNode RPAREN() { return getToken(QueryGrammarParser.RPAREN, 0); }
		public TerminalNode OR() { return getToken(QueryGrammarParser.OR, 0); }
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
		enterRule(_localctx, 4, RULE_atom);
		try {
			setState(30);
			switch (_input.LA(1)) {
			case IN:
				enterOuterAlt(_localctx, 1);
				{
				setState(22); match(IN);
				}
				break;
			case CATEGORY:
				enterOuterAlt(_localctx, 2);
				{
				setState(23); match(CATEGORY);
				}
				break;
			case RATING:
				enterOuterAlt(_localctx, 3);
				{
				setState(24); match(RATING);
				}
				break;
			case PRICE:
				enterOuterAlt(_localctx, 4);
				{
				setState(25); match(PRICE);
				}
				break;
			case NAME:
				enterOuterAlt(_localctx, 5);
				{
				setState(26); match(NAME);
				}
				break;
			case LPAREN:
				enterOuterAlt(_localctx, 6);
				{
				setState(27); match(LPAREN);
				setState(28); match(OR);
				setState(29); match(RPAREN);
				}
				break;
			default:
				throw new NoViableAltException(this);
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\16#\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\3\2\3\2\3\2\7\2\f\n\2\f\2\16\2\17\13\2\3\3\3\3\3\3\7\3\24\n"+
		"\3\f\3\16\3\27\13\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4!\n\4\3\4\2\2\5"+
		"\2\4\6\2\2&\2\b\3\2\2\2\4\20\3\2\2\2\6 \3\2\2\2\b\r\5\4\3\2\t\n\7\4\2"+
		"\2\n\f\5\4\3\2\13\t\3\2\2\2\f\17\3\2\2\2\r\13\3\2\2\2\r\16\3\2\2\2\16"+
		"\3\3\2\2\2\17\r\3\2\2\2\20\25\5\6\4\2\21\22\7\3\2\2\22\24\5\6\4\2\23\21"+
		"\3\2\2\2\24\27\3\2\2\2\25\23\3\2\2\2\25\26\3\2\2\2\26\5\3\2\2\2\27\25"+
		"\3\2\2\2\30!\7\5\2\2\31!\7\6\2\2\32!\7\7\2\2\33!\7\b\2\2\34!\7\t\2\2\35"+
		"\36\7\n\2\2\36\37\7\4\2\2\37!\7\13\2\2 \30\3\2\2\2 \31\3\2\2\2 \32\3\2"+
		"\2\2 \33\3\2\2\2 \34\3\2\2\2 \35\3\2\2\2!\7\3\2\2\2\5\r\25 ";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}