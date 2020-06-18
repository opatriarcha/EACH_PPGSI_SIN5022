package br.com.each.ppgsi.testeDeSoftware;

import choco.Choco;
import choco.cp.model.CPModel;
import choco.cp.solver.CPSolver;
import choco.kernel.common.util.iterators.DisposableIterator;
import choco.kernel.model.Model;
import choco.kernel.model.constraints.Constraint;
import choco.kernel.model.variables.integer.IntegerExpressionVariable;
import choco.kernel.model.variables.integer.IntegerVariable;
import choco.kernel.solver.Solver;
import choco.kernel.solver.constraints.SConstraint;
import choco.kernel.solver.variables.integer.IntDomainVar;

public class TrianguloSolution {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		IntegerVariable ladoA = Choco.makeIntVar("ladoA", 1, 1000);
		IntegerVariable ladoB = Choco.makeIntVar("ladoB", 1, 1000);
		IntegerVariable ladoC = Choco.makeIntVar("ladoC", 1, 1000);
		
		IntegerExpressionVariable somaBC = Choco.plus(ladoB,ladoC); //LB +LC
        IntegerExpressionVariable somaAC = Choco.plus(ladoA,ladoC); //LA+LC
        IntegerExpressionVariable somaAB = Choco.plus(ladoA,ladoB); //LA+LB               
		
		Model m = new CPModel();
		Constraint C1 = Choco.lt(ladoA, somaBC);		//LA > LA+LB	
		Constraint C2 = Choco.lt(ladoB, somaAC);          //LB > LA+LC
		Constraint C3 = Choco.lt(ladoC, somaAB);          //LC > LA+LB
		Constraint C4 = Choco.neq(ladoA, ladoB);        //LA <> LB
		Constraint C5 = Choco.neq(ladoB, ladoC);	//LB <> LC			
		Constraint C6 = Choco.neq(ladoA, ladoC);        //LA <> LC
		
		m.addConstraint(C1) ;
		m.addConstraint(C2) ;
		m.addConstraint(C3) ;
		m.addConstraint(C4) ;
		m.addConstraint(C5) ;
		m.addConstraint(C6) ;	
                m.addVariable(ladoA);
		m.addVariable(ladoB);
                m.addVariable(ladoC);
		Solver s = new CPSolver();
		
		// Read the model
		s.read(m);		
		// Solve the problem
		s.solve() ;
		// Print the solution
                
                DisposableIterator<IntDomainVar> it = s.getIntVarIterator();
                DisposableIterator<SConstraint> it2 = s.getConstraintIterator();
                StringBuffer buffer = new StringBuffer();
                buffer.append("(");
                
                while( it2.hasNext() ){
                    SConstraint constraint = it2.next();
                    System.out.println("CONSTRAINT: " + constraint.pretty());
                   
                }
                while( it.hasNext() ){
                    IntDomainVar item = it.next();
                    System.out.println("DOMAIN: " + item.getDomain());
                    System.out.println("NAME: " + item.getName());
                    System.out.println("VALUE: " + item.getVal());
                    System.out.println("DOMAIN SIZE" + item.getDomainSize());
                    System.out.println("--------------------------------------");
                    buffer.append(item.getVal()).append(",");
                }
                buffer.deleteCharAt(buffer.length()-1);
                buffer.append(")");
                
                System.out.println("BUFFER: " + buffer.toString());
		int LA = s.getVar(ladoA) .getVal();
		int LB = s.getVar(ladoB) .getVal();
		int LC = s.getVar(ladoC) .getVal();
		System.out.println("("+LA+","+LB+","+LC+")");
                
                System.out.println("OUTRO RESULTADO");
                TrianguloSolution.buildCompleteResponse(s);
                
	}
        
        public static void buildCompleteResponse( Solver solver ){
        DisposableIterator<IntDomainVar> it = solver.getIntVarIterator();
            StringBuilder buffer = new StringBuilder();
            buffer.append("(");
            while( it.hasNext() ){
                IntDomainVar var = it.next();
                buffer.append(var.getName()).append(" = ");
                buffer.append(var.getVal());
                buffer.append(" , ");
            }
            buffer.delete(buffer.length()-3, buffer.length());
            buffer.append(")");
            buffer.append("\n");
            System.out.println(buffer.toString());
    }

}
