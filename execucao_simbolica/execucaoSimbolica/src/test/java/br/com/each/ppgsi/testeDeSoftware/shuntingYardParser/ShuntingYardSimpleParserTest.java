/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.each.ppgsi.testeDeSoftware.shuntingYardParser;

import static br.com.each.ppgsi.testeDeSoftware.shuntingYardParser.ShuntingYardSimpleParser.RPNtoDouble;
import static br.com.each.ppgsi.testeDeSoftware.shuntingYardParser.ShuntingYardSimpleParser.infixToRPN;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author orlando
 */
public class ShuntingYardSimpleParserTest {
    
    public ShuntingYardSimpleParserTest() {
    }

    /**
     * Test of infixToRPN method, of class ShuntingYardSimpleParser.
     */
    @Test
    public void testInfixToRPN() {
        String[] input = "( 1 + 2 ) * ( 3 / 4 ) - ( 5 + 6 )".split(" ");       
        String[] output = infixToRPN(input);
         
        System.out.println("expression:  ( 1 + 2 ) * ( 3 / 4 ) - ( 5 + 6 )");
        // Build output RPN string minus the commas
        System.out.println("reverse: " );
         for (String token : output) {
            System.out.print(token + " ");
        }
         
        // Feed the RPN string to RPNtoDouble to give result
        Double result = RPNtoDouble( output );
        System.out.println("RESULT: " + result);
    }

    /**
     * Test of RPNtoDouble method, of class ShuntingYardSimpleParser.
     */
    @Test
    public void testRPNtoDouble() {
    }

    /**
     * Test of main method, of class ShuntingYardSimpleParser.
     */
    @Test
    public void testMain() {
    }
    
}
