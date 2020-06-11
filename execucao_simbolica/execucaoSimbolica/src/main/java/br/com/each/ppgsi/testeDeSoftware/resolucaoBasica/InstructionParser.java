package br.com.each.ppgsi.testeDeSoftware.resolucaoBasica;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author orlando
 */
public class InstructionParser {
    
    
    private InstructionParser(){
        mapping.put("=", "eq");
        mapping.put("<", "lt");
        mapping.put("<=", "lte");
        mapping.put(">", "gt");
        mapping.put("=>", "gte");
        mapping.put("+", "plus");
        mapping.put("-", "minus");
        mapping.put("/", "div");
        mapping.put("*", "mult");
    }
    
    private static final Map<String, String> mapping = new HashMap<>();
    
    public static InstructionParser getinstance(){
        return new InstructionParser();
    }
    
    
    
    public void parseInstructions(final String variableLine, final String line){
        List<String> predicates = this.parsePredicates(line);
        List<String> variables = this.parseVariables(variableLine);
        for( String predicate : predicates ){
            
        }
        
    }
    
    public List<String> parsePredicateBody( final String predicate ){
        String predicateBody = predicate.trim();
        predicateBody = this.removeOuterParentesis(predicate);
        return parseInnerPredicate(predicateBody);
    }

    public List<String> parseInnerPredicate(String predicateBody) {
        List<String> components = Arrays.asList(predicateBody.split(" "));
        if( components != null && !components.isEmpty())
            return components;
        return Collections.EMPTY_LIST;
    }
    
    public String removeOuterParentesis(String content ){
        //int last = content.lastIndexOf(")");
        //int first = content.indexOf("(");
        //return content.substring(first+1, last);
        content = content.replace("(", "");
        content = content.replace(")", "");
        return content;
    }
    
    public List<String> parsePredicates( final String predicates ){
        return Arrays.asList(predicates.split("^"));
    }
    
    public List<String> parseVariables( final String variables ){
        if(variables == null || variables.isBlank() || variables.isEmpty() || !variables.contains(","))
            return Collections.EMPTY_LIST;
        return Arrays.asList(variables.split(","));
    }
}
