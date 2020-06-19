package br.com.each.ppgsi.testeDeSoftware.resolucaoCompleta;

import br.com.each.ppgsi.testeDeSoftware.resolucaoCompleta.TestInputGenerator;
import br.com.each.ppgsi.testeDeSoftware.resolucaoCompleta.ResultSetHolder;
import choco.Choco;
import choco.kernel.model.constraints.Constraint;
import choco.kernel.model.variables.integer.IntegerConstantVariable;
import choco.kernel.model.variables.integer.IntegerExpressionVariable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
    public void testExecuteInternalPlus() {
        TestInputGenerator generator = new TestInputGenerator();
        ResultSetHolder holder = generator.executeInternal(Arrays.asList("X,Y", "(Y >= X + 3)"), false);
        
        IntegerExpressionVariable x = Choco.makeIntVar("X", 0, 10);
        IntegerExpressionVariable y = Choco.makeIntVar("Y", 0, 10);
        IntegerConstantVariable c3 = new IntegerConstantVariable(3);
        IntegerExpressionVariable sum = Choco.sum(x, c3);
        Constraint c = Choco.geq(y, sum);
        
        this.printResult(c, holder);
        
        assertEquals(c.pretty(), holder.getConstraintsResult());
        
    }
    
    @Test
    public void testExecuteNOT_EQUALS() {
        TestInputGenerator generator = new TestInputGenerator();
        ResultSetHolder holder = generator.executeInternal(Arrays.asList("X,Y", "(X != 3) ^ (Y != 3) ^ (X != Y)"), false);
        
        IntegerExpressionVariable x = Choco.makeIntVar("X", 0, 10);
        IntegerExpressionVariable y = Choco.makeIntVar("Y", 0, 10);
        IntegerConstantVariable c3 = new IntegerConstantVariable(3);
        
        Constraint constraint1 = Choco.neq(x, c3);
        Constraint constraint2 = Choco.neq(y, c3);
        Constraint constraint3 = Choco.neq(x, y);
        
        String result = constraint1.pretty() + constraint2.pretty() + constraint3.pretty();
        this.printResult(result, holder);
        
        assertEquals(result, holder.getConstraintsResult());
        
    }
    
    @Test
    public void testExecuteEQUALS() {
        TestInputGenerator generator = new TestInputGenerator();
        ResultSetHolder holder = generator.executeInternal(Arrays.asList("X,Y", "(X == 3) ^ (Y == 3) ^ (X == Y)"), false);
        
        IntegerExpressionVariable x = Choco.makeIntVar("X", 0, 10);
        IntegerExpressionVariable y = Choco.makeIntVar("Y", 0, 10);
        IntegerConstantVariable c3 = new IntegerConstantVariable(3);
        
        Constraint constraint1 = Choco.eq(x, c3);
        Constraint constraint2 = Choco.eq(y, c3);
        Constraint constraint3 = Choco.eq(x, y);
        
        String result = constraint1.pretty() + constraint2.pretty() + constraint3.pretty();
        this.printResult(result, holder);
        
        assertEquals(result, holder.getConstraintsResult());
        
    }

    private void printResult(Constraint c, ResultSetHolder holder) {
        System.out.println("EXPECTED: "+ c.pretty());
        System.out.println("RESULT:   "+ holder.getConstraintsResult());
    }
    
    private void printResult(String c, ResultSetHolder holder) {
        System.out.println("EXPECTED: "+ c);
        System.out.println("RESULT:   "+ holder.getConstraintsResult());
    }
    
    @Test
    public void testExecuteInternalMinus() {
        TestInputGenerator generator = new TestInputGenerator();
        ResultSetHolder holder = generator.executeInternal(Arrays.asList("X,Y", "(Y >= X - 3)"), false);
        
        IntegerExpressionVariable x = Choco.makeIntVar("X", 0, 10);
        IntegerExpressionVariable y = Choco.makeIntVar("Y", 0, 10);
        IntegerConstantVariable c3 = new IntegerConstantVariable(3);
        IntegerExpressionVariable minus = Choco.minus(x, c3);
        Constraint c = Choco.geq(y, minus);
        this.printResult(c, holder);
        
        assertEquals(c.pretty(), holder.getConstraintsResult());
        
    }
    
    @Test
    public void testExecuteInternalMult() {
        TestInputGenerator generator = new TestInputGenerator();
        ResultSetHolder holder = generator.executeInternal(Arrays.asList("X,Y", "(Y >= X * 3)"), false);
        
        IntegerExpressionVariable x = Choco.makeIntVar("X", 0, 10);
        IntegerExpressionVariable y = Choco.makeIntVar("Y", 0, 10);
        IntegerConstantVariable c3 = new IntegerConstantVariable(3);
        IntegerExpressionVariable mult = Choco.mult(x, c3);
        Constraint c = Choco.geq(y, mult);
        this.printResult(c, holder);
        
        assertEquals(c.pretty(), holder.getConstraintsResult());
        
    }
    
    @Test
    public void testExecuteInternalDiv() {
        TestInputGenerator generator = new TestInputGenerator();
        ResultSetHolder holder = generator.executeInternal(Arrays.asList("X,Y", "(Y >= X / 2)"), false);
        
        IntegerExpressionVariable x = Choco.makeIntVar("X", 0, 10);
        IntegerExpressionVariable y = Choco.makeIntVar("Y", 0, 10);
        IntegerConstantVariable c3 = new IntegerConstantVariable(2);
        IntegerExpressionVariable mult = Choco.div(x, c3);
        Constraint c = Choco.geq(y, mult);
        this.printResult(c, holder);
        
        assertEquals(c.pretty(), holder.getConstraintsResult()); 
    }
    
    @Test
    public void testExecuteInternalX_LT_0() {
        TestInputGenerator generator = new TestInputGenerator();
        ResultSetHolder holder = generator.executeInternal(Arrays.asList("X,Y", "(X <= 0)"), false);
        
        IntegerExpressionVariable x = Choco.makeIntVar("X", 0, 10);
        IntegerConstantVariable constant = new IntegerConstantVariable(0);
        
        Constraint c = Choco.leq(x, constant);
        this.printResult(c, holder);
        
        assertEquals(c.pretty(), holder.getConstraintsResult()); 
    }
    
    //(X > 0) ^ (Y > 5)
    @Test
    public void testX_GTE_0_AND_Y_GT_5() {
        TestInputGenerator generator = new TestInputGenerator();
        ResultSetHolder holder = generator.executeInternal(Arrays.asList("X,Y", "(X > 0) ^ (Y > 5)"), false);
        
        IntegerExpressionVariable x = Choco.makeIntVar("X", 0, 10);
        IntegerExpressionVariable y = Choco.makeIntVar("Y", 0, 10);
        IntegerConstantVariable c0 = new IntegerConstantVariable(0);
        IntegerConstantVariable c5 = new IntegerConstantVariable(5);
        
        
        Constraint c1 = Choco.gt(x, c0);
        Constraint c2 = Choco.gt(y, c5);
        
        String result = c1.pretty() + c2.pretty();
        this.printResult(result, holder);
        
        assertEquals(result, holder.getConstraintsResult()); 
    }
    
    @Test//(X > 0) ^ (Y <= 5) ^ (X < Y)
    public void testX_GT_0_AND_Y_LTE_5_AND_X_LT_Y() {
        TestInputGenerator generator = new TestInputGenerator();
        ResultSetHolder holder = generator.executeInternal(Arrays.asList("X,Y", "(X > 0) ^ (Y <= 5) ^ (X < Y)"), false);
        
        IntegerExpressionVariable x = Choco.makeIntVar("X", 0, 10);
        IntegerExpressionVariable y = Choco.makeIntVar("Y", 0, 10);
        
        IntegerConstantVariable c0 = new IntegerConstantVariable(0);
        IntegerConstantVariable c5 = new IntegerConstantVariable(5);
        
        Constraint c1 = Choco.gt(x, c0);
        Constraint c2 = Choco.leq(y, c5);
        Constraint c3 = Choco.lt(x, y);
        
        String result = c1.pretty() + c2.pretty() + c3.pretty();
        this.printResult(result, holder);
        
        assertEquals(result, holder.getConstraintsResult()); 
    }
    
    @Test//(X > 0) ^ (Y <= 5) ^ (X >= Y)
    public void testX_GT_0_AND_Y_LTE_5_AND_X_GTE_Y() {
        TestInputGenerator generator = new TestInputGenerator();
        ResultSetHolder holder = generator.executeInternal(Arrays.asList("X,Y", "(X > 0) ^ (Y <= 5) ^ (X >= Y)"), false);
        
        IntegerExpressionVariable x = Choco.makeIntVar("X", 0, 10);
        IntegerExpressionVariable y = Choco.makeIntVar("Y", 0, 10);
        
        IntegerConstantVariable c0 = new IntegerConstantVariable(0);
        IntegerConstantVariable c5 = new IntegerConstantVariable(5);
        
        Constraint c1 = Choco.gt(x, c0);
        Constraint c2 = Choco.leq(y, c5);
        Constraint c3 = Choco.geq(x, y);
        
        String result = c1.pretty() + c2.pretty() + c3.pretty();
        this.printResult(result, holder);
        
        assertEquals(result, holder.getConstraintsResult()); 
    }
    
    @Test//(X > 0) ^ (Y >= X + 3) ^ (Y <= X + X)
    public void testX_GT_0_AND_Y_GTE_X_PLUS_3_AND_Y_LTE_X_PLUS_X() {
        TestInputGenerator generator = new TestInputGenerator();
        ResultSetHolder holder = generator.executeInternal(Arrays.asList("X,Y", "(X > 0) ^ (Y >= X + 3) ^ (Y <= X + X)"), false);
        
        IntegerExpressionVariable x = Choco.makeIntVar("X", 0, 10);
        IntegerExpressionVariable y = Choco.makeIntVar("Y", 0, 10);
        
        IntegerConstantVariable c0 = new IntegerConstantVariable(0);
        IntegerConstantVariable c3 = new IntegerConstantVariable(3);
        
        Constraint ct1 = Choco.gt(x, c0);
        Constraint ct2 = Choco.geq(y, Choco.sum(x, c3));
        Constraint ct3 = Choco.leq(y, Choco.sum(x, x));
        
        String result = ct1.pretty() + ct2.pretty() + ct3.pretty();
        this.printResult(result, holder);
        
        assertEquals(result, holder.getConstraintsResult()); 
    }
    
    @Test//(LA > 0) ^ (LB > 0) ^ (LC > 0) ^ (LA < LB + LC) ^ (LB < LA + LC) ^ (LC < LA + LB) ^ (LA != LB) ^ ( LA != LB ) ^ (LB != LC) ^ (LA != LC)
    public void testTrianguloEscaleno() {
        TestInputGenerator generator = new TestInputGenerator();
        ResultSetHolder holder = generator.executeInternal(Arrays.asList("LA,LB,LC", "(LA > 0) ^ (LB > 0) ^ (LC > 0) ^ (LA < LB + LC) ^ (LB < LA + LC) ^ (LC < LA + LB) ^ (LA != LB) ^ ( LA != LB ) ^ (LB != LC) ^ (LA != LC)"), false);
        
        IntegerExpressionVariable LA = Choco.makeIntVar("LA", 0, 10);
        IntegerExpressionVariable LB = Choco.makeIntVar("LB", 0, 10);
        IntegerExpressionVariable LC = Choco.makeIntVar("LC", 0, 10);
        
        IntegerConstantVariable c0 = new IntegerConstantVariable(0);
        
        List<Constraint> constraints = new ArrayList<>();
        constraints.add(Choco.gt(LA, c0));
        constraints.add(Choco.gt(LB, c0));
        constraints.add(Choco.gt(LC, c0));
        constraints.add(Choco.lt(LA, Choco.sum(LB, LC)));
        constraints.add(Choco.lt(LB, Choco.sum(LA, LC)));
        constraints.add(Choco.lt(LC, Choco.sum(LA, LB)));
        constraints.add(Choco.neq(LA, LB));
        constraints.add(Choco.neq(LA, LB));
        constraints.add(Choco.neq(LB, LC));
        constraints.add(Choco.neq(LA, LC));
        
        String result = "";
        for(Constraint c : constraints){
            result = result + c.pretty();
        }
        this.printResult(result, holder);
        
        assertEquals(result, holder.getConstraintsResult()); 
    }
    
    @Test
    public void testManyMultiplications() {
        TestInputGenerator generator = new TestInputGenerator();
        ResultSetHolder holder = generator.executeInternal(Arrays.asList("X,Y", "(Y >= X * 1 * X)"), false);
        
        IntegerExpressionVariable x = Choco.makeIntVar("X", 0, 10);
        IntegerExpressionVariable y = Choco.makeIntVar("Y", 0, 10);
        IntegerConstantVariable c1 = new IntegerConstantVariable(1);
        
        Constraint c = Choco.geq(y, Choco.mult(c1, Choco.mult(x, x)));
        this.printResult(c, holder);
        
        assertEquals(c.pretty(), holder.getConstraintsResult());
        
    }
    
    @Test
    public void testExecuteFull() {
        TestInputGenerator generator = new TestInputGenerator();
        generator.executeFromInputFile("restricoes_teste.txt");
        
        assertTrue(true);
        
    }
}
