package br.com.each.ppgsi.testeDeSoftware.resolucaoBasica;

import br.com.each.ppgsi.testeDeSoftware.resolucaoCompleta.InstructionParser;
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
    
}
