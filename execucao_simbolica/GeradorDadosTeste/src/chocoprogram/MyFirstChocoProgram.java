package chocoprogram;

import choco.Choco;
import choco.cp.model.CPModel;
import choco.cp.solver.CPSolver;
import choco.kernel.model.Model;
import choco.kernel.model.constraints.Constraint;
import choco.kernel.model.variables.integer.IntegerConstantVariable;
import choco.kernel.model.variables.integer.IntegerExpressionVariable;
import choco.kernel.model.variables.integer.IntegerVariable;
import choco.kernel.solver.Solver;

public class MyFirstChocoProgram {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		// Build a model
		Model m = new CPModel() ;		
		// Build enumerated domain variables
		IntegerVariable x1 = Choco.makeIntVar("N", -100, 110);
		IntegerVariable x2 = Choco.makeIntVar("var2", 0, 5);
		IntegerVariable x3 = Choco.makeIntVar("var3", 0, 5);
		IntegerConstantVariable icv100 = new IntegerConstantVariable(100);
		IntegerConstantVariable icv4 = new IntegerConstantVariable(4);
		IntegerConstantVariable icv12 = new IntegerConstantVariable(12);
		
		// Build the constraints
		Constraint C1 = Choco.geq(x1,icv100) ;		
		//Constraint C2 = Choco.leq(icv, icv) ;
		Constraint C3 = Choco.geq(x1,101) ;
		//Constraint C4 = Choco.gt(101, icv) ;
		Constraint C5 = Choco.leq(x1,102) ;
		
		IntegerExpressionVariable iev = Choco.sum(icv4,icv12);
		Constraint cx = Choco.lt(icv100, iev);
		
		
		
		//Constraint C4 = Choco.neq(x1, x1);
		// Add the constraints to the Choco model
		//m.addConstraint(C1) ;
		//m.addConstraint(C2) ;
		//m.addConstraint(C3) ;
		//m.addConstraint(C4);
		//m.addConstraint(C5);
		// Build a solver
		
		m.addConstraint(cx);
		Solver s = new CPSolver();
		
		// Read the model 
		s.read(m);
		// Solve the problem
		boolean temSolucao = s.solve() ;
		// Print the variable domains
		if (temSolucao){
			System.out.println("solução");
			//System.out.println("var1 =" + s.getVar(x1) .getVal()) ;
			//System.out.println("var2 =" + s.getVar(x2) .getVal()) ;
			//System.out.println("var3 =" + s.getVar(x3) .getVal()) ;
		}
		else
			System.out.println("Nao tem solução");
		
		
		

	}

}
