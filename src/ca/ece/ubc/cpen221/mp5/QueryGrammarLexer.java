// Generated from QueryGrammar.g4 by ANTLR 4.4

package ca.ece.ubc.cpen221.mp5;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class QueryGrammarLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.4", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		AND=1, OR=2, IN=3, CATEGORY=4, RATING=5, PRICE=6, NAME=7, LPAREN=8, RPAREN=9, 
		STRING=10, RANGE=11, WHITESPACE=12;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"'\\u0000'", "'\\u0001'", "'\\u0002'", "'\\u0003'", "'\\u0004'", "'\\u0005'", 
		"'\\u0006'", "'\\u0007'", "'\b'", "'\t'", "'\n'", "'\\u000B'", "'\f'"
	};
	public static final String[] ruleNames = {
		"AND", "OR", "IN", "CATEGORY", "RATING", "PRICE", "NAME", "LPAREN", "RPAREN", 
		"STRING", "CHAR", "RANGE", "WHITESPACE"
	};


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


	public QueryGrammarLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "QueryGrammar.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\16p\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\3\2\3\2\3\2\3\3\3\3\3\3\3\4\3\4\3\4\3\4"+
		"\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7"+
		"\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13"+
		"\6\13\\\n\13\r\13\16\13]\3\13\3\13\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\16"+
		"\6\16k\n\16\r\16\16\16l\3\16\3\16\2\2\17\3\3\5\4\7\5\t\6\13\7\r\b\17\t"+
		"\21\n\23\13\25\f\27\2\31\r\33\16\3\2\5\5\2C\\c|~~\3\2\63\67\5\2\13\f\17"+
		"\17\"\"p\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2"+
		"\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\31"+
		"\3\2\2\2\2\33\3\2\2\2\3\35\3\2\2\2\5 \3\2\2\2\7#\3\2\2\2\t*\3\2\2\2\13"+
		"\67\3\2\2\2\rB\3\2\2\2\17L\3\2\2\2\21U\3\2\2\2\23W\3\2\2\2\25Y\3\2\2\2"+
		"\27a\3\2\2\2\31c\3\2\2\2\33j\3\2\2\2\35\36\7(\2\2\36\37\7(\2\2\37\4\3"+
		"\2\2\2 !\7~\2\2!\"\7~\2\2\"\6\3\2\2\2#$\7k\2\2$%\7p\2\2%&\3\2\2\2&\'\5"+
		"\21\t\2\'(\5\25\13\2()\5\23\n\2)\b\3\2\2\2*+\7e\2\2+,\7c\2\2,-\7v\2\2"+
		"-.\7g\2\2./\7i\2\2/\60\7q\2\2\60\61\7t\2\2\61\62\7{\2\2\62\63\3\2\2\2"+
		"\63\64\5\21\t\2\64\65\5\25\13\2\65\66\5\23\n\2\66\n\3\2\2\2\678\7t\2\2"+
		"89\7c\2\29:\7v\2\2:;\7k\2\2;<\7p\2\2<=\7i\2\2=>\3\2\2\2>?\5\21\t\2?@\5"+
		"\31\r\2@A\5\23\n\2A\f\3\2\2\2BC\7r\2\2CD\7t\2\2DE\7k\2\2EF\7e\2\2FG\7"+
		"g\2\2GH\3\2\2\2HI\5\21\t\2IJ\5\31\r\2JK\5\23\n\2K\16\3\2\2\2LM\7p\2\2"+
		"MN\7c\2\2NO\7o\2\2OP\7g\2\2PQ\3\2\2\2QR\5\21\t\2RS\5\25\13\2ST\5\23\n"+
		"\2T\20\3\2\2\2UV\7*\2\2V\22\3\2\2\2WX\7+\2\2X\24\3\2\2\2Y[\7$\2\2Z\\\5"+
		"\27\f\2[Z\3\2\2\2\\]\3\2\2\2][\3\2\2\2]^\3\2\2\2^_\3\2\2\2_`\7$\2\2`\26"+
		"\3\2\2\2ab\t\2\2\2b\30\3\2\2\2cd\t\3\2\2de\7\60\2\2ef\7\60\2\2fg\3\2\2"+
		"\2gh\t\3\2\2h\32\3\2\2\2ik\t\4\2\2ji\3\2\2\2kl\3\2\2\2lj\3\2\2\2lm\3\2"+
		"\2\2mn\3\2\2\2no\b\16\2\2o\34\3\2\2\2\5\2]l\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}