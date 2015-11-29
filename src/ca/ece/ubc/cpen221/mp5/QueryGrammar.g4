grammar QueryGrammar;

@header {
package ca.ece.ubc.cpen221.mp5;
}

// This adds code to the generated lexer and parser. Do not change these lines.
@members {
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
}

/*
 * These are the lexical rules. They define the tokens used by the lexer.
 *   *** Antlr requires tokens to be CAPITALIZED, like START_ITALIC, END_ITALIC, and TEXT.
 */
AND : '&&';
OR : '||';
IN : 'in' LPAREN STRING RPAREN;
CATEGORY : 'category' LPAREN STRING RPAREN;
RATING : 'rating' LPAREN RANGE RPAREN;
PRICE : 'price' LPAREN RANGE RPAREN;
NAME : 'name' LPAREN STRING RPAREN;
LPAREN : '(' ;
RPAREN : ')' ;
STRING : (([a-z]|[A-Z])+ WHITESPACE*)+;
RANGE : [1-5]'..'[1-5];
WHITESPACE : [ \t\r\n]+ -> skip ;

/*
 * These are the parser rules. They define the structures used by the parser.
 *    *** Antlr requires grammar nonterminals to be lowercase, like html, normal, and italic.
 */


query : (LPAREN? orExpr RPAREN? | LPAREN? andExpr RPAREN?)+ EOF; 
orExpr : atom? (OR (atom|query))+ ;
andExpr : atom? (AND (atom|query))+ ;
atom : IN | CATEGORY | RATING | PRICE | NAME | (LPAREN orExpr RPAREN);
 