package br.com.each.ppgsi.testeDeSoftware.lexerAnalyser;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class LexerAnalyserImpl implements ILexerAnalyser {

    private final StreamTokenizer input;

    private String symbol = "NONE";

    private final List<String> result = new LinkedList<>();

    public LexerAnalyserImpl(InputStream in) {
        Reader r = new BufferedReader(new InputStreamReader(in));
        input = new StreamTokenizer(r);

        input.eolIsSignificant(false);
        input.slashSlashComments(false);
        input.slashStarComments(false);

        input.resetSyntax();
        input.wordChars('a', 'z');
        input.wordChars('A', 'Z');
        input.wordChars('0', '9');
        input.whitespaceChars('\u0000', ' ');
        input.whitespaceChars('\n', '\t');
        input.ordinaryChars('0', '9');

        input.ordinaryChar('(');
        input.ordinaryChar(')');
        input.ordinaryChar('|');
        input.ordinaryChar('!');

        input.ordinaryChar('^');
        input.ordinaryChar('<');
        input.ordinaryChar('>');
        input.ordinaryChar('=');
        input.ordinaryChar('+');
        input.ordinaryChar('-');
        input.ordinaryChar('*');
        input.ordinaryChar('/');
        input.ordinaryChar('#');    

        //input.wordChars(EOL, OR);
    }

    @Override
    public String nextSymbol() {
        try {
            int token = input.nextToken();
            input.parseNumbers();
            
            switch (token) {
                case StreamTokenizer.TT_EOL:
                    symbol = EOL;
                    break;
                case StreamTokenizer.TT_EOF:
                    symbol = EOF;
                    break;
                case StreamTokenizer.TT_WORD: {
                   
                    if ( input.sval.equalsIgnoreCase( TRUE_LITERAL ) ) {
                        symbol = TRUE;
                    } else if (input.sval.equalsIgnoreCase( FALSE_LITERAL ) ) {
                        symbol = FALSE;
                    } else {
                        symbol = input.sval;
                    }
                    result.add(symbol);
                    break;
                }
                case StreamTokenizer.TT_NUMBER: {
                    symbol = Double.toString(input.nval);
                    result.add(makeIntegerFromDouble(symbol));//GAMBIS
                    break;
                }
                case '(':
                    symbol = LEFT;
                    result.add(symbol);
                    break;
                case ')':
                    symbol = RIGHT;
                    result.add(symbol);
                    break;
                case '^':
                    symbol = AND;
                    result.add(symbol);
                    break;
                case '|': {
                    
                    int t = input.nextToken();
                    
                    switch (t) {
                        case '|':
                            symbol = OR;
                            result.add(symbol);
                            break;
                        default:
                            input.pushBack();
                            symbol = OR;
                            result.add(symbol);
                            break;
                    }
                    break;
                }                    
                case '!': {
                    
                    int t = input.nextToken();
                    
                    switch (t) {
                        case '=':
                            symbol = NOT_EQUAL;
                            result.add(symbol);
                            break;
                        default:
                            input.pushBack();
                            symbol = NOT;
                            result.add(symbol);
                            break;
                    }
                    break;
                }           
                case '=': {
                    
                    int t = input.nextToken();
                    
                    switch (t) {
                        case '=':
                            symbol = EQUAL;
                            result.add(symbol);
                            break;
                        default:
                            input.pushBack();
                            symbol = EQUAL; // OTRA GAMBI LASCADA TODO:
                            result.add(symbol);
                            break;
                    }
                    break;
                }     
                case '+':
                    symbol = PLUS;
                    result.add(symbol);
                    break;
                case '-':
                    symbol = MINUS;
                    result.add(symbol);
                    break;
                case '*':
                    symbol = MULTIPLY;
                    result.add(symbol);
                    break;
                case '/':
                    symbol = DIVISION;
                    result.add(symbol);
                    break;
                case '<': {
                    
                    int t = input.nextToken();
                    
                    switch (t) {
                        case '=':
                            symbol = LESS_THAN_OR_EQUAL;
                            result.add(symbol);
                            break;
                        case '<':
                            result.add("<<");
                            break;
                        default:
                            input.pushBack();
                            symbol = LESS;
                            result.add(symbol);
                            break;
                    }
                    break;
                }
                case '>': {
                    int t = input.nextToken();
                    switch (t) {
                        case '=':
                            symbol = GREATER_THAN_OR_EQUAL;
                            result.add(symbol);
                            break;
                        default:
                            input.pushBack();
                            symbol = GREATER;
                            result.add(symbol);
                            break;
                    }
                }
                default:
                    symbol = INVALID;
            }
        } catch (IOException e) {
            symbol = EOF;
        }
        return symbol;
    }

    @Override
    public List<String> tokenizeAll(String input) {
        LexerAnalyserImpl l = new LexerAnalyserImpl(new ByteArrayInputStream(input.getBytes()));
        String s;
        while (!(s = l.nextSymbol()).equals(LexerAnalyserImpl.EOF)) {
        }
        return l.result;
    }
    
    private String makeIntegerFromDouble( String number ){
        return number.substring(0, number.indexOf("."));
    }

    @Override
    public String toString() {
        return input.toString();
    }
}
