package br.com.each.ppgsi.testeDeSoftware.shuntingYardParser;

import java.util.Arrays;
import java.util.List;
import org.junit.Test;

/**
 *
 * @author orlando
 */
public class ShuntingYardSimpleParserTest {
    
    public ShuntingYardSimpleParserTest() {
    }

    /**
     * Test of infixToRPN method, of class ShuntingYardParserImpl.
     */
    @Test
    public void testInfixToRPN() {
        ShuntingYardParserImpl instance = ShuntingYardParserImpl.getInstance();
        String[] input = "( 1 + 2 ) * ( 3 / 4 ) - ( 5 + 6 )".split(" ");       
        List<String> output = instance.infixToReversePolishNotation(Arrays.asList(input));
         
        System.out.println("expression:  ( 1 + 2 ) * ( 3 / 4 ) - ( 5 + 6 )");
        // Build output RPN string minus the commas
        System.out.println("reverse: " );
         for (String token : output) {
            System.out.print(token + " ");
        }
         
//        // Feed the RPN string to RPNtoDouble to give result
//        Double result = RPNtoDouble( output );
//        System.out.println("RESULT: " + result);
    }

    /**
     * Test of RPNtoDouble method, of class ShuntingYardParserImpl.
     */
    @Test
    public void testRPNtoDouble() {
    }

    /**
     * Test of main method, of class ShuntingYardParserImpl.
     */
    @Test
    public void testMain() {
    }
    
}
