package br.usp.astExpressionParser.lexer;

import br.usp.astExpressionParser.ast.nonterminal.GenericExpression;
import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class Lexer {

    private StreamTokenizer input;

    private String symbol = "NONE";
    public static final String EOL = "-3";
    public static final String EOF = "-2";
    public static final String INVALID = "-1";

    public static final String NONE = "NONE";

    public static final String OR = "OR";
    public static final String AND = "AND";
    public static final String NOT = "NOT";

    public static final String TRUE = "TRUE";
    public static final String FALSE = "FALSE";

    public static final String LEFT = "(";
    public static final String RIGHT = ")";

    public static final String LESS = "<";
    public static final String LESS_THAN_OR_EQUAL = "<=";
    public static final String GREATER = ">";
    public static final String GREATER_THAN_OR_EQUAL = ">=";
    public static final String EQUAL = "=";
    public static final String PLUS = "+";
    public static final String MINUS = "-";
    public static final String MULTIPLY = "*";
    public static final String DIVISION = "/";

    public static final String TRUE_LITERAL = "true";
    public static final String FALSE_LITERAL = "false";

    private List<String> result = new LinkedList<>();

    public Lexer(InputStream in) {
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


        //input.wordChars(EOL, OR);
    }

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
                   
                    if (input.sval.equalsIgnoreCase(TRUE_LITERAL)) {
                        symbol = TRUE;
                    } else if (input.sval.equalsIgnoreCase(FALSE_LITERAL)) {
                        symbol = FALSE;
                    } else {
                        symbol = input.sval;
                    }
                    result.add(symbol);
                    break;
                }
                case StreamTokenizer.TT_NUMBER: {
                    symbol = Double.toString(input.nval);
                    result.add(symbol);
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
                case '|':
                    symbol = OR;
                    result.add(symbol);
                    break;
                case '!':
                    symbol = NOT;
                    result.add(symbol);
                    break;
                case '=':
                    symbol = EQUAL;
                    result.add(symbol);
                    break;
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
//                    symbol = GREATER;
//                    result.add(symbol);
//                    break;
//                }
            ///          }
        } catch (IOException e) {
            symbol = EOF;
        }
        return symbol;
    }

    public List<String> tokenizeAll(String input) {
        Lexer l = new Lexer(new ByteArrayInputStream(input.getBytes()));
        String s;
        while ((s = l.nextSymbol()) != Lexer.EOF) {
            s = "nada"; /// meu deus que gambiarra da mulestia!
        }
        return l.result;
    }

    @Override
    public String toString() {
        return input.toString();
    }
    
    @Deprecated// em virtude do algoritmo do Dijkstra mas deixa aqui por enquanto
    public GenericExpression group( List<String> input, GenericExpression exp ){
        int parentesisCounter = 0;
        List<String> group = new LinkedList<>();
        GenericExpression expression = null;
        int count = 0;
        boolean counterMoved = false;
        for( String str : input ){
            count++;            
            if(str.equals("(")){
                parentesisCounter++;
                counterMoved = true;
            }
            if( str.equals(")")){
                parentesisCounter--;
                counterMoved = true;
            }
            
            group.add(str);
            if( parentesisCounter == 0 && counterMoved ){
                // fecha grupo                            
                List<String> innerGroup = new LinkedList<>(group);
                group = group.subList(1, group.size()-1);
                expression = new GenericExpression(innerGroup);
                List<String> tail = new LinkedList<>(input.subList(count, input.size()));
                this.group(tail, expression);
            }
        }
        return expression;
    }
}
