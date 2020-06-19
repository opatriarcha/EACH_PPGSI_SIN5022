package br.com.each.ppgsi.testeDeSoftware.commons;

import br.com.each.ppgsi.testeDeSoftware.lexerAnalyser.ILexerAnalyser;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jdk.nashorn.internal.parser.Lexer;

/**
 *
 * @author orlando
 */
public class Commons {
    public static final Integer FROM_NUMBER = -10;
    public static final Integer TO_NUMBER = 50;
    public static final String RESOLUTION_DESTINATION_PATH = "/home/orlando/software_development/workspace/each_usp/EACH_PPGSI_SIN5022/execucao_simbolica/execucaoSimbolica/src/main/resources/resolution.txt";
    public static final String COMPLETE_RESOLUTION_DESTINATION_PATH = "/home/orlando/software_development/workspace/each_usp/EACH_PPGSI_SIN5022/execucao_simbolica/execucaoSimbolica/src/main/resources/complete_resolution.txt";
    public static final String CONSTRAINTS_DESTINATION_PATH = "/home/orlando/software_development/workspace/each_usp/EACH_PPGSI_SIN5022/execucao_simbolica/execucaoSimbolica/src/main/resources/constraints.txt";
    public static final String BOUNDARY_ANALYSIS_DESTINATION_PATH = "/home/orlando/software_development/workspace/each_usp/EACH_PPGSI_SIN5022/execucao_simbolica/execucaoSimbolica/src/main/resources/boundary_analysis.txt";
    public static final Map<String, String[]> map = new HashMap<>();
    
    static{
        map.put(ILexerAnalyser.LESS, new String[]{ILexerAnalyser.LESS_THAN_OR_EQUAL, ILexerAnalyser.EQUAL});
        map.put(ILexerAnalyser.LESS_THAN_OR_EQUAL, new String[]{ILexerAnalyser.LESS, ILexerAnalyser.GREATER_THAN_OR_EQUAL});
        map.put(ILexerAnalyser.GREATER, new String[]{ILexerAnalyser.GREATER_THAN_OR_EQUAL, ILexerAnalyser.EQUAL});
        map.put(ILexerAnalyser.GREATER_THAN_OR_EQUAL, new String[]{ILexerAnalyser.GREATER, ILexerAnalyser.LESS_THAN_OR_EQUAL}); 
        map.put(ILexerAnalyser.EQUAL, new String[]{ILexerAnalyser.GREATER, ILexerAnalyser.LESS});       
        map.put(ILexerAnalyser.NOT_EQUAL, new String[]{ILexerAnalyser.EQUAL, ILexerAnalyser.LESS});   
    }
    
    public static String[] getTransformedOperator( final String operator ){
        return map.get(operator);
    }
    
    public static boolean isOperator( final String token ){
        return map.containsKey(token);
    }
    
    public static boolean isVariable( final String token, List<String> variables ){
        return variables.contains(token);
    }
    
    public static boolean isConstantValue(final String token, final List<String> variables){
        if(isOperator(token) || token.equals("AND") || token.equals("OR") || token.equals("!="))
            return false;
        if(isVariable(token, variables))
            return false;
        if( token.trim().equals("") || token.contains("(") || token.contains(")"))
            return false;
        if(token.contains(".")){
            Double d = Double.parseDouble(token);
            return true;
        }else{
            Integer i = Integer.parseInt(token);
            return true;        
        }
        
            
                    
    }
}
