package br.com.each.ppgsi.testeDeSoftware.lexerAnalyser;

import java.util.List;

/**
 *
 * @author orlando
 */
public interface ILexerAnalyser {

    String AND = "AND";
    String DIVISION = "/";
    String EOF = "-2";
    String EOL = "-3";
    String EQUAL = "==";
    String FALSE = "FALSE";
    String FALSE_LITERAL = "false";
    String GREATER = ">";
    String GREATER_THAN_OR_EQUAL = ">=";
    String INVALID = "-1";
    String LEFT = "(";
    String LESS = "<";
    String LESS_THAN_OR_EQUAL = "<=";
    String MINUS = "-";
    String MULTIPLY = "*";
    String NONE = "NONE";
    String NOT = "NOT";
    String NOT_EQUAL = "!=";
    String OR = "OR";
    String PLUS = "+";
    String RIGHT = ")";
    String TRUE = "TRUE";
    String TRUE_LITERAL = "true";

    String nextSymbol();

    String toString();

    List<String> tokenizeAll(String input);
    
}
