package chocoprogram;

import choco.Choco;
import choco.cp.model.CPModel;
import choco.cp.solver.CPSolver;
import choco.kernel.model.Model;
import choco.kernel.model.constraints.Constraint;
import choco.kernel.model.variables.integer.IntegerExpressionVariable;
import choco.kernel.model.variables.integer.IntegerVariable;
import choco.kernel.solver.Solver;

public class MyInfeasibleChocoProgram {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		IntegerVariable ladoA = Choco.makeIntVar("ladoA", 1, 30000);					              
		
		Model m = new CPModel();
		Constraint C1 = Choco.lt(ladoA, 10);			
		Constraint C2 = Choco.gt(ladoA, 5);
				
		m.addConstraint(C1);
		m.addConstraint(C2);
		
		
		Solver s = new CPSolver();
		
		// Read the model
		s.read(m);		
		// Solve the problem
		boolean ok = s.solve();
		System.out.println(ok);
		// Print the variable domains
		System.out.println("ladoA = " + s.getVar(ladoA) .getVal()) ;
	}

}
