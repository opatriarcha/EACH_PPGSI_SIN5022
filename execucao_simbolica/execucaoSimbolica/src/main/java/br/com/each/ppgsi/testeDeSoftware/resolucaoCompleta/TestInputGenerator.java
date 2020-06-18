package br.com.each.ppgsi.testeDeSoftware.resolucaoCompleta;

import br.com.each.ppgsi.testeDeSoftware.infrastructure.ResourceWriterImpl;
import br.com.each.ppgsi.testeDeSoftware.infrastructure.ResourceReaderImpl;
import br.com.each.ppgsi.testeDeSoftware.infrastructure.IResourceReader;
import br.com.each.ppgsi.testeDeSoftware.infrastructure.IResoureWriter;
import br.com.each.ppgsi.testeDeSoftware.commons.Commons;
import br.com.each.ppgsi.testeDeSoftware.lexerAnalyser.ILexerAnalyser;
import br.com.each.ppgsi.testeDeSoftware.lexerAnalyser.LexerAnalyserImpl;
import br.com.each.ppgsi.testeDeSoftware.shuntingYardParser.IShuntingYardParser;
import br.com.each.ppgsi.testeDeSoftware.shuntingYardParser.ShuntingYardParserImpl;
import br.com.ppgsi.testeDeSoftware.Interpreter.ExpressionParser;
import br.com.ppgsi.testeDeSoftware.Interpreter.integer.IntegerVariableExpression;
import choco.cp.model.CPModel;
import choco.cp.solver.CPSolver;
import choco.kernel.common.util.iterators.DisposableIterator;
import choco.kernel.model.Model;
import choco.kernel.model.constraints.Constraint;
import choco.kernel.solver.Solver;
import choco.kernel.solver.constraints.SConstraint;
import choco.kernel.solver.variables.integer.IntDomainVar;
import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author orlando
 */
public class TestInputGenerator {
    
    private final IResoureWriter resolutionWriter = new ResourceWriterImpl(Commons.RESOLUTION_DESTINATION_PATH);
    private final IResoureWriter completeResolutionWriter = new ResourceWriterImpl(Commons.COMPLETE_RESOLUTION_DESTINATION_PATH);
    private final IResoureWriter constraintWriter = new ResourceWriterImpl(Commons.CONSTRAINTS_DESTINATION_PATH);
    
    public void executeWithResolver(final String filename) {
        IResourceReader reader = ResourceReaderImpl.getInstance();
        List<String> lines = reader.read(filename);
        this.cleanResources();
        ResultSetHolder holder = this.executeInternal(lines);
        this.writeReports(holder);

    }
    
    public ResultSetHolder executeInternal(List<String> lines){
        InstructionParser parser = InstructionParser.getinstance();
        List<String> variables = parser.parseVariables(lines.get(0));
        StringBuilder constraintReportBuffer = new StringBuilder();
        StringBuilder completerReportBuffer = new StringBuilder();
        StringBuilder oficialResponseBuffer = new StringBuilder();
        
        //não é a melhor solução para tratar as constraints do tipo and do choco, mas... é o q tem pra hj
        for (int i = 1; i < lines.size(); i++) {
            String subject = lines.get(i);
            if( subject.trim().startsWith("#"))
                continue; // Igonre commented lines
            ILexerAnalyser lexer = new LexerAnalyserImpl( new ByteArrayInputStream(subject.getBytes()));
            List<Constraint> constraints = new LinkedList<>();
            List<String> lista = this.groupByConjunction(lines.get(i));
            HashMap<String, IntegerVariableExpression> variablesHolder = new HashMap<>();
            for(String andExpression : lista ){
                
                List<String> tokens = lexer.tokenizeAll(andExpression);
                printList("TOKENIZER: ", tokens);
                //"X < 0 AND Y > 0" = (X 0 <) (Y 0 >) AND  
                IShuntingYardParser reversePolishParser = ShuntingYardParserImpl.getInstance();
                List<String> reversePolishTokens = reversePolishParser.infixToReversePolishNotation(tokens);
                printList("Reverse Polish Notation: ", reversePolishTokens);
                String reversePolishTokenString = reversePolishParser.toPrettyFormat(reversePolishTokens);

                System.out.println("REVERSE POLISH NOTATION PRETTY FORMAT: " + reversePolishTokenString);
                Constraint constraint = ExpressionParser.getInstance(reversePolishTokenString, variables, variablesHolder).parse();
                
                constraints.add(constraint);
            }
            
            Model model = new CPModel() ;
            
            for( Constraint c : constraints ){
                model.addConstraint(c);
            }
            
            Solver solver = new CPSolver();
            solver.read(model);
            boolean hasSolution = solver.solve();
            
            if( hasSolution ){
                this.buildOfficialResponse(oficialResponseBuffer, solver);
                this.buildCompleteResponse(completerReportBuffer, solver);
                this.buildConstraintsReport(constraintReportBuffer, solver, model);
            }else{
                this.buildNotFeasibleLineReport(completerReportBuffer);
                this.buildNotFeasibleLineReport(oficialResponseBuffer);
                this.buildConstraintsReport(constraintReportBuffer, solver, model);
            }
        }
        
        return new ResultSetHolder( oficialResponseBuffer.toString(), completerReportBuffer.toString(), constraintReportBuffer.toString());
    }
    
    private void writeReports( ResultSetHolder holder ){
        this.completeResolutionWriter.writeFile(holder.getCompleteResult());
        this.resolutionWriter.writeFile(holder.getOfficialResult());
        this.constraintWriter.writeFile(holder.getConstraintsResult());
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
                if(var.getName().startsWith("TMP"))
                    continue;
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
                if(var.getName().startsWith("TMP"))
                    continue;
                buffer.append(var.getName()).append(" = ");
                buffer.append(var.getVal());
                buffer.append(" , ");
            }
            buffer.delete(buffer.length()-3, buffer.length());
            buffer.append(")");
            buffer.append("\n");
    }
    
    private void buildConstraintsReport(StringBuilder buffer, Solver solver, Model model){
        DisposableIterator<SConstraint> it2 = solver.getConstraintIterator();
            //buffer.append("#");
            Iterator<Constraint> it = model.getConstraintIterator();
            while( it.hasNext() ){
                Constraint c = it.next();
                buffer.append(c.pretty());
                //buffer.append("\n");
            }
            //buffer.delete(buffer.length()-1, buffer.length());
           
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
        builder.append("Infeasible Result\n");
    }
    
    private List<String> groupByConjunction( final String line ){
        if( line.contains("^" ) )//deixa sem OR por enquanto
            return Arrays.asList(line.split("\\^"));
        if( line.contains("||" ) )//deixa sem OR por enquanto
            return Arrays.asList(line.split("||"));
        return Arrays.asList(line);
    }
   
}
