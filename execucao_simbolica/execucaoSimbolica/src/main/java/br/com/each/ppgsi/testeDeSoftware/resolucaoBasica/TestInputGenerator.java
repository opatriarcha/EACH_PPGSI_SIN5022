/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.each.ppgsi.testeDeSoftware.resolucaoBasica;

import choco.Choco;
import choco.cp.model.CPModel;
import choco.cp.solver.CPSolver;
import choco.kernel.model.Model;
import choco.kernel.model.variables.ComponentVariable;
import choco.kernel.model.variables.integer.IntegerConstantVariable;
import choco.kernel.model.variables.integer.IntegerVariable;
import choco.kernel.model.variables.real.RealConstantVariable;
import choco.kernel.model.variables.real.RealVariable;
import choco.kernel.solver.Solver;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author orlando
 */
public class TestInputGenerator {
     
    public TestInputGenerator(){
        
        mapping.put("=", "eq");
        mapping.put("<", "lt");
        mapping.put("<=", "leq");
        mapping.put(">", "gt");
        mapping.put("=>", "geq");
        mapping.put("+", "plus");
        mapping.put("-", "minus");
        mapping.put("/", "div");
        mapping.put("*", "mult");
    }
    
    private static final Map<String, String> mapping = new HashMap<>();

    public void execute(final String filename) {
        ResourceReader reader = ResourceReader.getInstance();
        InstructionParser parser = InstructionParser.getinstance();
        List<String> lines = reader.read(filename);

        List<String> predicates = new ArrayList<>();
        List<String> variables = parser.parseVariables(lines.get(0));

        for (int i = 1; i <= lines.size(); i++) {
            List<String> blocks = parser.parsePredicates(lines.get(i));
            for( String item : blocks ){
                List<String> instructions = parser.parsePredicateBody(item);
                List<ComponentVariable> components = new ArrayList<>();
                ComponentVariable variable = null;
                Double doubleVariable = null;
                Integer integerVariavle = null;
                ExpressionVariable = null;
                for (String instruction : instructions) {
                    System.out.println(instruction);
                    if(isVariable(variables, instruction)){
                        // fazer algo
                    }
                    if(isOperator(instruction)){
                        
                    }
                    if(isNumberValue(instruction)){
                        
                    }
                    
                }
            }
            
            
        }

    }
    
    // verifica se um componente é uma variavel pelo nome. Mas isso da problema com testes em programas que recebem strings.
    // Nesse caso a String de parametro nao deve ser exatamente iqual ao nome da variável. Gambiarra a principio.
    private boolean isVariable( List<String> variableNames, String componentInstruction){
        return variableNames.contains(componentInstruction);                    
    }
    
    private boolean isOperator( String componentInstruction ){
        return mapping.containsKey(componentInstruction);
    }
    
    
    //Outra gambiarrisima. Rever isso depois.
    private boolean isNumberValue( String componentInstruction ){
        
        try{
            if(componentInstruction.contains(".") || componentInstruction.contains(",")){
                Double doubleNumber = Double.parseDouble(componentInstruction);
               return true;
            }
            Integer integerNumber = Integer.parseInt(componentInstruction);

            return integerNumber.toString().equals(componentInstruction);    
        }catch( Exception ex ){
            ex.printStackTrace();
            return false;
        }
    }
    
    private Double retrieveDoubleValue( String componentInstruction ){
        if( componentInstruction.contains(",") )
            componentInstruction = componentInstruction.replace(",", ".");
        return Double.parseDouble(componentInstruction);
    }
    
    private Integer retrieveInteger( String componentInstruction ){
        return Integer.parseInt(componentInstruction);
    }

    public static void main(String[] args) {
        IntegerVariable x3 = Choco.makeIntVar("var3", 0, 5);
        IntegerConstantVariable icv100 = new IntegerConstantVariable(100);
        RealVariable x1 = Choco.makeRealVar("varDouble", 0, 1.1);
        RealConstantVariable realConstant = new RealConstantVariable(100);
        ComponentVariable componentVariable = Choco.makeRealVar("varDouble", 0, 1.1);

        Model model = new CPModel();
        
        model.addVariable(componentVariable);
        Solver s = new CPSolver();

        // Read the model 
        s.read(model);
        // Solve the problem
        boolean temSolucao = s.solve();
    }
}
