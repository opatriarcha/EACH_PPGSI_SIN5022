package br.com.each.ppgsi.testeDeSoftware.resolucaoBasica;

import java.util.List;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author orlando
 */
public class InstructionParserTest {
    
    private static final String SIMPLE_CASE_VARIABLES = "X,Y";
    private static final String SIMPLE_CASE_INSTRUCTION_1 = "(X < 0)";
    private static final String DOUBLE_INSTRUCTION_1 = "(X <= 0)";
    private static final String SIMPLE_CASE_INSTRUCTION_2 = "(X > 0) ^ (Y > 5)";
    private static final String SIMPLE_CASE_INSTRUCTION_3 = "(X > 0) ^ (Y <= 5) ^ (X < Y)";
    private static final String SIMPLE_CASE_INSTRUCTION_4 = "(X > 0) ^ (Y <= 5) ^ (X >= Y)";
    /**
     * Test of parse method, of class InstructionParser.
     */
    @Test
    public void testParse() {
        InstructionParser parser = InstructionParser.getinstance();
         
    }
    
    @Test
    public void testParseVariables() {
        InstructionParser parser = InstructionParser.getinstance();
        List<String> result = parser.parseVariables(SIMPLE_CASE_VARIABLES);
        Assert.assertEquals("X", result.get(0));
        Assert.assertEquals("Y", result.get(1));
    }
    
    @Test
    public void removeOuterParentesis(){
        String input = "stuff1 (foo1(bar1)foo2) stuff2 (bar2) stuff3";
        InstructionParser parser = InstructionParser.getinstance();
        String result = parser.removeOuterParentesis(input); 
        System.out.println(result);
        //Assert.assertEquals("foo1bar1foo2 stuff2 bar2", result);
        Assert.assertEquals("stuff1 foo1bar1foo2 stuff2 bar2 stuff3", result);
    }
    
    @Test
    public void removeOuterParentesisReal(){
        InstructionParser parser = InstructionParser.getinstance();
        String result = parser.removeOuterParentesis(SIMPLE_CASE_INSTRUCTION_2); 
        System.out.println(result);
        Assert.assertEquals("X > 0 ^ Y > 5", result);
    }
    
    @Test
    public void testParseInnerPredicate(){
        InstructionParser parser = InstructionParser.getinstance();
        String input = parser.removeOuterParentesis(SIMPLE_CASE_INSTRUCTION_1);
        List<String> result = parser.parseInnerPredicate(input);
        Assert.assertEquals("X", result.get(0));
        Assert.assertEquals("<", result.get(1));
        Assert.assertEquals("0", result.get(2));
    }
    
    @Test
    public void testParseInnerPredicateDoubleOperator(){
        InstructionParser parser = InstructionParser.getinstance();
        String input = parser.removeOuterParentesis(DOUBLE_INSTRUCTION_1);
        List<String> result = parser.parseInnerPredicate(input);
        Assert.assertEquals("X", result.get(0));
        Assert.assertEquals("<=", result.get(1));
        Assert.assertEquals("0", result.get(2));
    }
    
}
