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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 *
 * @author orlando
 */
public class TestInputGenerator {
    
    private final IResoureWriter resolutionWriter = new ResourceWriterImpl(Commons.RESOLUTION_DESTINATION_PATH);
    private final IResoureWriter completeResolutionWriter = new ResourceWriterImpl(Commons.COMPLETE_RESOLUTION_DESTINATION_PATH);
    private final IResoureWriter constraintWriter = new ResourceWriterImpl(Commons.CONSTRAINTS_DESTINATION_PATH);
    private final IResoureWriter boundaryWriter = new ResourceWriterImpl(Commons.BOUNDARY_ANALYSIS_DESTINATION_PATH);
    private Map<String, List<String>>boudaryAnalysisLinesByOperator = new HashMap<>(); 
    private Map<String, List<String>>boudaryAnalysisLinesByValue = new HashMap<>(); 
    
    public void executeFromInputFile(final String filename) {
        IResourceReader reader = ResourceReaderImpl.getInstance();
        List<String> lines = reader.read(filename);
        this.boudaryAnalysisLinesByOperator = this.makeBoundaryAnaysisByOperator(lines);
        this.boudaryAnalysisLinesByValue = this.makeBoundaryAnalysisByValues(lines);
        
        this.cleanResources();
        ResultSetHolder holder = this.executeInternal(lines, false);
        this.writeReports(holder);
        
        List<String> newBoundarylines = new ArrayList<>();
        newBoundarylines.add(this.boudaryAnalysisLinesByOperator.get("variables").get(0));
        
        this.boudaryAnalysisLinesByOperator.remove("variables");
        this.boudaryAnalysisLinesByValue.remove("variables");
        
        for(Collection<String> list : boudaryAnalysisLinesByOperator.values()){
            newBoundarylines.addAll(list);
        }
        
        for(Collection<String> list : boudaryAnalysisLinesByValue.values()){
            newBoundarylines.addAll(list);
        }
        
        ResultSetHolder holderBoundary = this.executeInternal(newBoundarylines, true);
        this.writeBoundaryReport(holderBoundary);

    }
    
    public ResultSetHolder executeInternal(List<String> lines, boolean isBoundary){
        InstructionParser parser = InstructionParser.getinstance();
        List<String> variables = parser.parseVariables(lines.get(0));
        StringBuilder constraintReportBuffer = new StringBuilder();
        StringBuilder completerReportBuffer = new StringBuilder();
        StringBuilder oficialResponseBuffer = new StringBuilder();
        StringBuilder boundaryAnalysisResponseBuffer = new StringBuilder();
        
        if( isBoundary  ){
            System.out.println("BOUNDARIES!!!!");
        }
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
                if( isBoundary )
                    this.writeBoundaryBuffer(boundaryAnalysisResponseBuffer, solver,lines.get(i));
                else{
                    this.buildOfficialResponse(oficialResponseBuffer, solver);
                    this.buildCompleteResponse(completerReportBuffer, solver);
                    this.buildConstraintsReport(constraintReportBuffer, solver, model);
                }
                
            }else{
                if( isBoundary )
                    this.buildNotFeasibleLineReport(boundaryAnalysisResponseBuffer,lines.get(i));
                else{
                    this.buildNotFeasibleLineReport(completerReportBuffer);
                    this.buildNotFeasibleLineReport(oficialResponseBuffer);
                    this.buildConstraintsReport(constraintReportBuffer, solver, model);
                }
            }
        }
        if( isBoundary )
            return new ResultSetHolder( oficialResponseBuffer.toString(), boundaryAnalysisResponseBuffer.toString(), constraintReportBuffer.toString());
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
    
    private void buildNotFeasibleLineReport(StringBuilder buffer){
        buffer.append("Infeasible Result\n");
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
   
    private List<String> groupByConjunction( final String line ){
        if( line.contains("^" ) )//deixa sem OR por enquanto
            return Arrays.asList(line.split("\\^"));
        if( line.contains("||" ) )//deixa sem OR por enquanto
            return Arrays.asList(line.split("||"));
        return Arrays.asList(line);
    }

    private Map<String, List<String>>  makeBoundaryAnaysisByOperator(List<String> lines) {
        Map<String, List<String>> resultSet = new HashMap<>();
        
        InstructionParser parser = InstructionParser.getinstance();
        List<String> variables = parser.parseVariables(lines.get(0));
        resultSet.put("variables", Arrays.asList(lines.get(0)));
        
        for (int i = 1; i < lines.size(); i++) {
            String subject = lines.get(i);
            if( subject.trim().startsWith("#"))
                continue; // Igonre commented lines
            ILexerAnalyser lexer = new LexerAnalyserImpl( new ByteArrayInputStream(subject.getBytes()));
            List<String> tokens = lexer.tokenizeAll(subject);
            
            List<String> boundaryAnalysisLines = new LinkedList<>();
            String newInputLine1 = "";
            String newInputLine2 = "";
            String previousOperator = "";
            for(String token : tokens ){   
                if ( Commons.isOperator(token) ){
                    String[] transformed = Commons.getTransformedOperator(token);
                    newInputLine1 = newInputLine1 + transformed[0] + " ";
                    newInputLine2 = newInputLine2 + transformed[1] + " ";
                    previousOperator = token;
                }
                else{
                    if(token.contains("(")){
                        newInputLine1 = newInputLine1.trim() + "(";
                        newInputLine2 = newInputLine2.trim() + "(";
                    }else if( token.contains(")")){
                        newInputLine1 = newInputLine1.trim() + ")";
                        newInputLine2 = newInputLine2.trim() + ")";
                    }else{
                        newInputLine1 = newInputLine1 + token + " ";
                        newInputLine2 = newInputLine2 + token + " ";
                    }
                }
                
            }
            newInputLine1 = newInputLine1.replaceAll("AND", " ^ ");     
            newInputLine2 = newInputLine2.replaceAll("AND", " ^ ");
            
            boundaryAnalysisLines.add(newInputLine1.trim());
            boundaryAnalysisLines.add(newInputLine2.trim());
            resultSet.put(subject, boundaryAnalysisLines);
        }
        return resultSet;
    }
    
    private Map<String, List<String>>  makeBoundaryAnalysisByValues(List<String> lines) {
        Map<String, List<String>> resultSet = new HashMap<>();
        
        InstructionParser parser = InstructionParser.getinstance();
        List<String> variables = parser.parseVariables(lines.get(0));
        resultSet.put("variables", Arrays.asList(lines.get(0)));
        
        for (int i = 1; i < lines.size(); i++) {
            String subject = lines.get(i);
            if( subject.trim().startsWith("#"))
                continue; // Ignore commented lines
            ILexerAnalyser lexer = new LexerAnalyserImpl( new ByteArrayInputStream(subject.getBytes()));
            List<String> tokens = lexer.tokenizeAll(subject);
            
            List<String> boundaryAnalysisLines = new LinkedList<>();
            String newInputLine1 = "";
            String previousOperator = "";
            int position = 0;
            for(String token : tokens ){
                position++;
               
                if( Commons.isOperator(token)){
                    previousOperator = token;
                }
                if(Commons.isConstantValue(token, variables)){
                    if( previousOperator.equals( ILexerAnalyser.LESS) || previousOperator.equals( ILexerAnalyser.LESS_THAN_OR_EQUAL)){
                        Integer value = Commons.getInteger(token);
                        newInputLine1 = newInputLine1 + value++ + " ";  
                    }else if( previousOperator.equals( ILexerAnalyser.GREATER )|| previousOperator.equals( ILexerAnalyser.GREATER_THAN_OR_EQUAL)){
                        Integer value = Commons.getInteger(token);
                        newInputLine1 = newInputLine1 + value-- + " ";
                    }
                }else{
                    if(token.contains("(")){
                        newInputLine1 = newInputLine1.trim() + "(";
                    }else if( token.contains(")")){
                        newInputLine1 = newInputLine1.trim() + ")";
                    }else{
                        newInputLine1 = newInputLine1 + token + " ";
                    }
                }                
            }
            newInputLine1 = newInputLine1.replaceAll("AND", " ^ ");     
            
            boundaryAnalysisLines.add(newInputLine1.trim());
           
            resultSet.put(subject, boundaryAnalysisLines);
        }
        return resultSet;
    }

    private void writeBoundaryBuffer(StringBuilder buffer, Solver solver, String line) {
        DisposableIterator<IntDomainVar> it = solver.getIntVarIterator();
             
        String originalLine = null;
        for (Entry<String, List<String>> entry : this.boudaryAnalysisLinesByOperator.entrySet()) {
            List<String> list = entry.getValue();
            if(list.contains(line)){
                originalLine = entry.getKey();
                break;
            }
        }
        if( originalLine == null || originalLine.trim().isEmpty() )
            originalLine = line;
        //if( buffer.lastIndexOf(line) < 0 )
        //buffer.append("Original: ").append(originalLine).append("\n");
        //buffer.append("Modified: ").append(line).append( " Values: " );
        buffer.append("Original Restriction: ").append(originalLine).append(" Values: ");//.append("\n");

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
    
    private void buildNotFeasibleLineReport(StringBuilder buffer, String line ){
        String originalLine = null;
        for (Entry<String, List<String>> entry : this.boudaryAnalysisLinesByOperator.entrySet()) {
            List<String> list = entry.getValue();
            if(list.contains(line)){
                originalLine = entry.getKey();
                break;
            }
        }
        buffer.append("original: ").append(originalLine).append("\n");
        buffer.append("Modified: ").append(line).append( " " );
        buffer.append("Infeasible Result\n");
    }
    

    private void writeBoundaryReport(ResultSetHolder holderBoundary) {
        this.boundaryWriter.writeFile(holderBoundary.getCompleteResult());
    }
    
   
}
