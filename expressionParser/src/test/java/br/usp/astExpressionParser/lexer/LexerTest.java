package br.usp.astExpressionParser.lexer;

import br.usp.astExpressionParser.ast.nonterminal.GenericExpression;
import static br.usp.astExpressionParser.lexer.Lexer.EOL;
import java.io.ByteArrayInputStream;
import java.util.Collections;
import java.util.List;
import org.junit.Test;

/**
 *
 * @author orlando
 */
public class LexerTest {

    private static final String SIMPLE_BINARY_EXPRESSION = "(X < 1)";
    private static final String SIMPLE_CONJUNCTION_EXPRESSION = "(X > 0) ^ (Y > 5)";
    private static final String SIMPLE_CONJUNCTIONWITH_EQUAL_EXPRESSION = "(X > 0) ^ (Y <= 5) ^ (X < Y)";
    private static final String COMPLEX_BINARY_EXPRESSION = "(X > 0) ^ (Y <= 5) ^ (X >= Y + 5)";
    private static final String COMPLEX_EXPRESSION_VARIABLE_LENGH_DIFFER = "(XYZ > 0) ^ (YABC <= 5) ^ (SAS < Y)";
    private static final String SIMPLE_CONJUNCTION_GROUPING = "((X > 0) ^ (Y <= 5)) ^ (X < Y)";
    public LexerTest() {
    }

    /**
     * Test of nextSymbol method, of class Lexer.
     */
    //@Test
    public void testNextSymbol() {
        String expression = "true & ((true | false) & !(true & false))";
        Lexer l = new Lexer(new ByteArrayInputStream(expression.getBytes()));
        String s;
        while ((s = l.nextSymbol()) != Lexer.EOF) {
            if (s != EOL) {
                System.out.printf("%s -> %s\n", l, s);
            }
        }
    }

    //@Test
    public void testNextSymbolWithSIMPLE_BINARY_EXPRESSION() {
        System.out.println("SIMPLE_BINARY_EXPRESSION: ");
        Lexer l = new Lexer(new ByteArrayInputStream(SIMPLE_BINARY_EXPRESSION.getBytes()));
        String s;
        while ((s = l.nextSymbol()) != Lexer.EOF) {
            if (s != EOL) {
                System.out.printf("%s -> %s\n", l, s);
                System.out.println("\n");
            }
        }
    }
    
    //@Test
    public void testNextSymbolWithSIMPLE_CONJUNCTION_EXPRESSION() {
        System.out.println("SIMPLE_CONJUNCTION_EXPRESSION: ");
        Lexer l = new Lexer(new ByteArrayInputStream(SIMPLE_CONJUNCTION_EXPRESSION.getBytes()));
        String s;
        while ((s = l.nextSymbol()) != Lexer.EOF) {
            if (s != EOL) {
                System.out.printf("%s -> %s\n", l, s);
            }
        }
    }
    
    //@Test
    public void testNextSymbolWithSIMPLE_CONJUNCTIONWITH_EQUAL_EXPRESSION() {
        System.out.println("SIMPLE_CONJUNCTIONWITH_EQUAL_EXPRESSION: ");
        Lexer l = new Lexer(new ByteArrayInputStream(SIMPLE_CONJUNCTIONWITH_EQUAL_EXPRESSION.getBytes()));
        String s;
        while ((s = l.nextSymbol()) != Lexer.EOF) {
            if (s != EOL) {
                System.out.printf("%s -> %s\n", l, s);
            }
        }
    }
    
    //@Test
    public void testNextSymbolWithCOMPLEX_BINARY_EXPRESSION() {
        System.out.println("COMPLEX_BINARY_EXPRESSION: ");
        Lexer l = new Lexer(new ByteArrayInputStream(COMPLEX_BINARY_EXPRESSION.getBytes()));
        String s;
        while ((s = l.nextSymbol()) != Lexer.EOF) {
            if (s != EOL) {
                System.out.printf("%s -> %s\n", l, s);
            }
        }
    }

    @Test
    public void testTokenizeAll() {
        System.out.println("TokenizeAll Method: ");
        Lexer l = new Lexer(new ByteArrayInputStream(COMPLEX_BINARY_EXPRESSION.getBytes()));
        List<String> result = l.tokenizeAll(COMPLEX_BINARY_EXPRESSION);
        String linha = "";
        System.out.println("Resultset size: " +result.size());
        for( String item : result ){
            linha = linha + item + " ";
        }
        linha = linha.trim();
        System.out.println(COMPLEX_BINARY_EXPRESSION);
        System.out.println(linha);
    }
    
    //@Test
    public void testPrototype() {
        System.out.println("Protyping Method: ");
        Lexer lexer = new Lexer(new ByteArrayInputStream(SIMPLE_CONJUNCTION_GROUPING.getBytes()));
        List<String> result = lexer.tokenizeAll(SIMPLE_CONJUNCTION_GROUPING);
        String linha = "";
        System.out.println("Resultset size: " +result.size());
        for( String item : result ){
            linha = linha + item + " ";
        }
        linha = linha.trim();
        System.out.println(SIMPLE_CONJUNCTION_GROUPING);
        System.out.println(linha);
                
        GenericExpression seed = new GenericExpression(Collections.EMPTY_LIST);
        GenericExpression grouped = lexer.group(result, seed);
        StringBuilder sb = new StringBuilder();
        for( String s : grouped.getTokenizedContent() ){
            sb.append(s);                    
        }
        System.out.println("BUILDER RESULT: " + sb.toString());
        
    }

}
