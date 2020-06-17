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
import java.util.Arrays;
import java.util.Collections;
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
        this.cleanResources();
        
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
        StringBuilder constraintReportBuffer = new StringBuilder();
        StringBuilder completerReportBuffer = new StringBuilder();
        StringBuilder oficialResponseBuffer = new StringBuilder();
        
        this.cleanResources();
        
        List<String> lines = reader.read(filename);

//        List<String> predicates = new ArrayList<>();
        InstructionParser parser = InstructionParser.getinstance();
        List<String> variables = parser.parseVariables(lines.get(0));
        
        //não é a melhor solução para tratar as constraints do tipo and do choco, mas... é o q tem pra hj
        for (int i = 1; i < lines.size(); i++) {
            String subject = lines.get(i);
            if( subject.trim().startsWith("#"))
                continue; // Igonre commented lines
            Lexer lexer = new Lexer( new ByteArrayInputStream(subject.getBytes()));
            List<Constraint> constraints = new LinkedList<>();
            List<String> lista = this.groupByConjunction(lines.get(i));
            for(String andExpression : lista ){
                
                List<String> tokens = lexer.tokenizeAll(andExpression);
                printList("TOKENIZER: ", tokens);
                //"X < 0 AND Y > 0" = (X 0 <) (Y 0 >) AND  
                ShuntingYardSimpleParser reversePolishParser = ShuntingYardSimpleParser.getInstance();
                List<String> reversePolishTokens = reversePolishParser.infixToReversePolishNotation(tokens);
                printList("Reverse Polish Notation: ", reversePolishTokens);
                String reversePolishTokenString = reversePolishParser.toPrettyFormat(reversePolishTokens);

                System.out.println("REVERSE POLISH NOTATION PRETTY FORMAT: " + reversePolishTokenString);
                Constraint constraint = ExpressionParser.getInstance(reversePolishTokenString, variables).parse();
                System.out.println("CONSTRAINT: " + constraint.pretty());
                constraints.add(constraint);
            }
            //Constraint finalConstraint = ExpressionParser.concatenateConstraints(constraints);// ^É um concatenador ou um AND???
            
            Model model = new CPModel() ;
            
            for( Constraint c : constraints ){
                model.addConstraint(c);
            }
            //model.addConstraints((Constraint[]) constraints.toArray());
            
            Solver solver = new CPSolver();
            solver.read(model);
            boolean hasSolution = solver.solve();
            
            if( hasSolution ){
                this.buildOfficialResponse(oficialResponseBuffer, solver);
                this.buildCompleteResponse(completerReportBuffer, solver);
                this.buildConstraintsReport(constraintReportBuffer, solver);
            }else{
                this.buildNotFeasibleLineReport(completerReportBuffer);
                this.buildNotFeasibleLineReport(oficialResponseBuffer);
                this.buildConstraintsReport(constraintReportBuffer, solver);
            }
        }
        this.completeResolutionWriter.writeFile(completerReportBuffer.toString());
        this.resolutionWriter.writeFile(oficialResponseBuffer.toString());
        this.constraintWriter.writeFile(constraintReportBuffer.toString());

    }

    private void cleanResources() {
        this.resolutionWriter.deleteFile();
        this.completeResolutionWriter.deleteFile();
        this.constraintWriter.deleteFile();
    }
    
    private void buildOfficialResponse(StringBuilder buffer, Solver solver){
            DisposableIterator<IntDomainVar> it = solver.getIntVarIterator();
            buffer.append("(");
            while( it.hasNext() ){
                IntDomainVar var = it.next();
                buffer.append(var.getVal());
                buffer.append(",");
            }
            buffer.deleteCharAt(buffer.length() -1);
            buffer.append(")\n");
    }
    
    private void buildCompleteResponse(StringBuilder buffer, Solver solver ){
        DisposableIterator<IntDomainVar> it = solver.getIntVarIterator();
            
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
    }
    
    private void buildConstraintsReport(StringBuilder buffer, Solver solver){
        DisposableIterator<SConstraint> it = solver.getConstraintIterator();
            buffer.append("#");
            while( it.hasNext() ){
                SConstraint var = it.next();
                buffer.append(var.pretty());
            }
            buffer.append("#");
            buffer.append("\n");
    }
    private void printList( String message, List<String> lista){
        StringBuilder builder = new StringBuilder();
        builder.append(message);
        for( String str : lista ){
            builder.append( str ).append(" ");
        }
        builder.deleteCharAt(builder.length() -1);
        System.out.println(builder.toString());
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
    
    private void buildNotFeasibleLineReport(StringBuilder builder ){
        builder.append("Infeasible Result");
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
            buffer.append(")\n");
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
    
    private List<String> groupByConjunction( final String line ){
        if( line.contains("^" ) )//deixa sem OR por enquanto
            return Arrays.asList(line.split("\\^"));
        if( line.contains("||" ) )//deixa sem OR por enquanto
            return Arrays.asList(line.split("||"));
        return Arrays.asList(line);
    }
    
    private boolean hasANDConjunction( final String line){
        return line.contains("^");
    }
    
    private boolean hasOrConjunction( final String line){
        return line.contains("||");
    }

    public static void main(String[] args) {

        TestInputGenerator generator = new TestInputGenerator();
        //generator.executeWithResolver("/home/orlando/software_development/workspace/each_usp/EACH_PPGSI_SIN5022/execucao_simbolica/execucaoSimbolica/src/main/resources/exemplorestricoes.txt");
        generator.executeWithResolver("exemplorestricoes.txt");
    }
}
