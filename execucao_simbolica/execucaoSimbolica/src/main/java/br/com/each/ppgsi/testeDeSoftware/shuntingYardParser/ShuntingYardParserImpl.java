package br.com.each.ppgsi.testeDeSoftware.shuntingYardParser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * Thanks to Dijkstra ( he is watching! )
 * @author orlando
 */
public class ShuntingYardParserImpl {

    // trocar por enum pq isso é muito feio!!!
    private static final int LEFT_ASSOC  = 0;
    private static final int RIGHT_ASSOC = 1;
  
    private static final Map<String, int[]> OPERATORS = new HashMap<String, int[]>();
    static{
        OPERATORS.put("+", new int[] { 3, LEFT_ASSOC });
        OPERATORS.put("-", new int[] { 3, LEFT_ASSOC });
        OPERATORS.put("*", new int[] { 4, LEFT_ASSOC });
        OPERATORS.put("/", new int[] { 4, LEFT_ASSOC });
        OPERATORS.put("<", new int[] { 5, LEFT_ASSOC });
        OPERATORS.put("<=", new int[] { 5, LEFT_ASSOC });
        OPERATORS.put(">", new int[] { 5, LEFT_ASSOC });
        OPERATORS.put(">=", new int[] { 5, LEFT_ASSOC });
        OPERATORS.put("<>", new int[] { 5, LEFT_ASSOC });
        OPERATORS.put("==", new int[] { 5, LEFT_ASSOC });
        OPERATORS.put("AND", new int[] { 9, LEFT_ASSOC });
        OPERATORS.put("OR", new int[] { 9, LEFT_ASSOC });
        OPERATORS.put("^", new int[] { 9, LEFT_ASSOC });
        OPERATORS.put("||", new int[] { 9, LEFT_ASSOC });
        OPERATORS.put("!=", new int[] { 5, LEFT_ASSOC });
    }
    
    private ShuntingYardParserImpl(){
    }
  
    public static ShuntingYardParserImpl getInstance(){
        return new ShuntingYardParserImpl();
    }
    
    private static boolean isOperator(String token){
        return OPERATORS.containsKey(token);
    }
  
    // Testa a associativiade nao precisa depois da nova gambiarra
    private static boolean isAssociativeComponent(String token, int type) 
    {
        if (!isOperator(token))
            throw new IllegalArgumentException("Invalid token: " + token);
        
        if (OPERATORS.get(token)[1] == type)
            return true;
        return false;
    }
     
    private static final int assertPrecedence(String token1, String token2){
        if (!isOperator(token1) || !isOperator(token2)){
            throw new IllegalArgumentException("Invalid tokens: " + token1  + " " + token2);
        }
        Integer result = OPERATORS.get(token1)[0] - OPERATORS.get(token2)[0];
        System.out.println("PRECEDENCE: " + token1 + " " + token2 + " precendence: " + result);
        return result;
    }
  
    // Converte INFIX para RESVERSE POLISH
    public List<String> infixToReversePolishNotation(List<String> inputTokens) 
    {
        List<String> out = new ArrayList<>();
        Stack<String> stack = new Stack<>();
        Stack<String> visitedTokens = new Stack<>();
        for (String token : inputTokens){
            if (isOperator(token)){  
                visitedTokens.push(token);
                while (!stack.empty() && isOperator(stack.peek())){                    
                    if( visitedTokens.size() > 1){
                        String previousToken = visitedTokens.peek();
                        if( OPERATORS.get(token)[0] < OPERATORS.get(previousToken)[0]){
                            out.add(stack.pop());
                            continue;
                        }
                    }
                    break;
                }
                stack.push(token);
            }             
            else if (token.equals("(")){
                stack.push(token);   
            } 
            else if (token.equals(")")){                
                while (!stack.empty() && !stack.peek().equals("(")){
                    out.add(stack.pop()); 
                }
                stack.pop(); 
            } 
            // se o tokem é um numero
            else{
                out.add(token); 
            }
        }
        while (!stack.empty()){
            out.add(stack.pop()); 
        }
       
        return out;
    }
     
    public static double RPNtoDouble(List<String> tokens){        
        Stack<String> stack = new Stack<>();
        for (String token : tokens) 
        {
            if (!isOperator(token))
                stack.push(token);                
            else{
                Double second = Double.valueOf( stack.pop() );
                Double first = Double.valueOf( stack.pop() );
                 
                //Get the result
                Double result = token.compareTo("+") == 0 ? first + second : 
                                token.compareTo("-") == 0 ? first - second :
                                token.compareTo("*") == 0 ? first * second :
                                                            first / second;               
                                 
                stack.push( String.valueOf( result ));                                                
            }                        
        }        
        return Double.valueOf(stack.pop());
    }
    
    public String toPrettyFormat(List<String> valueList){
        StringBuilder builder = new StringBuilder();
        for( String value: valueList){
            builder.append(value).append(" ");
        }
        String result = builder.toString();
        return result.trim();
    }
  
    public static void main(String[] args) {
        ShuntingYardParserImpl parser = ShuntingYardParserImpl.getInstance();
        String[] input = "( 1 + 2 ) * ( 3 / 4 ) - ( 5 + 6 )".split(" ");
        //String[] input = "( ( X > 0 ) ^ ( Y <= 5 ) ) ^ ( X < Y + 5 )".split(" ");
        List<String> output = parser.infixToReversePolishNotation(Arrays.asList(input));
         
        // constroi a string tirando os parentesis
         for (String token : output) {
            System.out.print(token + " ");
        }
         
        // Resultado
        Double result = RPNtoDouble( output );  
        System.out.println("RESULTDOUBLE: " + result);
    }
}
