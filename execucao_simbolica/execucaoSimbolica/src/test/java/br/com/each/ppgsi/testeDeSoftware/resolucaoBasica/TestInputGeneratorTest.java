package br.com.each.ppgsi.testeDeSoftware.resolucaoBasica;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author orlando
 */
public class TestInputGeneratorTest {
    
    public TestInputGeneratorTest() {
    }

    /**
     * Test of execute method, of class TestInputGenerator.
     */
    @Test
    public void testExecute() {
        TestInputGenerator generator = new TestInputGenerator();
        generator.execute("exemplorestricoes.txt");
    }

    /**
     * Test of main method, of class TestInputGenerator.
     */
    @Test
    public void testMain() {
    }
    
}
