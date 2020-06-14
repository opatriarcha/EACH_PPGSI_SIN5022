package br.com.each.ppgsi.testeDeSoftware.resolucaoBasica;

import br.com.each.ppgsi.testeDeSoftware.shuntingYardParser.ShuntingYardSimpleParser;
import br.usp.astExpressionParser.Commons;
import br.usp.astExpressionParser.interpreter.ExpressionParser;
import br.usp.astExpressionParser.lexer.Lexer;
import choco.cp.model.CPModel;
import choco.cp.solver.CPSolver;
import choco.kernel.common.util.iterators.DisposableIterator;
import choco.kernel.model.Model;
import choco.kernel.model.constraints.Constraint;
import choco.kernel.model.variables.ComponentVariable;
import choco.kernel.solver.Solver;
import choco.kernel.solver.constraints.SConstraint;
import choco.kernel.solver.variables.integer.IntDomainVar;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author orlando
 */
public class TestInputGenerator {
    
    private final ResourceWriter resolutionWriter = new ResourceWriter(Commons.RESOLUTION_DESTINATION_PATH);
    private final ResourceWriter completeResolutionWriter = new ResourceWriter(Commons.COMPLETE_RESOLUTION_DESTINATION_PATH);
    private final ResourceWriter constraintWriter = new ResourceWriter(Commons.CONSTRAINTS_DESTINATION_PATH);

     
//    public TestInputGenerator(){
//        
//        mapping.put("=", "eq");
//        mapping.put("<", "lt");
//        mapping.put("<=", "leq");
//        mapping.put(">", "gt");
//        mapping.put("=>", "geq");
//        mapping.put("+", "plus");
//        mapping.put("-", "minus");
//        mapping.put("/", "div");
//        mapping.put("*", "mult");
//    }
    
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
                //ExpressionVariable = null; parei aqui
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
    
    public void executeWithResolver(final String filename) {
        ResourceReader reader = ResourceReader.getInstance();
        
        
        List<String> lines = reader.read(filename);

        List<String> predicates = new ArrayList<>();
        InstructionParser parser = InstructionParser.getinstance();
        List<String> variables = parser.parseVariables(lines.get(0));
        
        
                
        for (int i = 1; i <= lines.size(); i++) {
            Lexer lexer = new Lexer( new ByteArrayInputStream(lines.get(i).getBytes()));
            
            List<String> tokens = lexer.tokenizeAll(lines.get(i));
            ShuntingYardSimpleParser reversePolishParser = ShuntingYardSimpleParser.getInstance();
            List<String> reversePolishTokens = reversePolishParser.infixToReversePolishNotation(tokens);
            String reversePolishTokenString = reversePolishParser.toPrettyFormat(reversePolishTokens);
            
            Constraint constraint = ExpressionParser.getInstance(reversePolishTokenString, variables).parse();
            
            Model model = new CPModel() ;
            model.addConstraint(constraint);
            
            Solver solver = new CPSolver();
            solver.read(model);
            boolean hasSolution = solver.solve();
            
            if( hasSolution ){
                this.extractAndWriteOficialResponse(solver);
                this.extractAndWriteCompleteResponse(solver);
                this.extractAndWriteConstraints(solver);
            }else{
                this.writeNotFeasibleResult();
            }
        }

    }
    //TODO
    private List<String> generateLimitValueBasedONResult(final String resultLine){
        List<String> resultSet = new LinkedList<>();
        String temp = resultLine.substring(0 - resultLine.length() -1); //remove the parentesis
        String[] entries = temp.split(",");
        for( String entry : entries ){
            
        }
        return resultSet;
    }
    
    //TODO
    private List<String> generateLimitValueBasedONEntries(final String resultLine){
        List<String> resultSet = new LinkedList<>();
        String temp = resultLine.substring(0 - resultLine.length() -1); //remove the parentesis
        String[] entries = temp.split(",");
        for( String entry : entries ){
            
        }
        return resultSet;
    }
    
    private void writeNotFeasibleResult(){
        this.resolutionWriter.writeFile("Infeasible Result");
    }
    private void extractAndWriteOficialResponse(Solver solver){
        DisposableIterator<IntDomainVar> it = solver.getIntVarIterator();
            StringBuilder buffer = new StringBuilder();
            buffer.append("(");
            while( it.hasNext() ){
                IntDomainVar var = it.next();
                buffer.append(var.getVal());
                buffer.append(",");
            }
            buffer.deleteCharAt(buffer.length() -1);
            buffer.append("\n");
            this.resolutionWriter.writeFile(buffer.toString());
    }
    
    private void extractAndWriteCompleteResponse(Solver solver){
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
            this.completeResolutionWriter.writeFile(buffer.toString());
    }
    
    
    private void extractAndWriteConstraints(Solver solver){
        DisposableIterator<SConstraint> it = solver.getConstraintIterator();
            StringBuilder buffer = new StringBuilder();
            buffer.append("#");
            while( it.hasNext() ){
                SConstraint var = it.next();
                buffer.append(var.pretty());
            }
            buffer.append("#");
            buffer.append("\n");
            this.constraintWriter.writeFile(buffer.toString());
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
        TestInputGenerator generator = new TestInputGenerator();
        //generator.executeWithResolver("/home/orlando/software_development/workspace/each_usp/EACH_PPGSI_SIN5022/execucao_simbolica/execucaoSimbolica/src/main/resources/exemplorestricoes.txt");
        generator.executeWithResolver("/exemplorestricoes.txt");
    }
}
