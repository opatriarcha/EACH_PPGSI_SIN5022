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
public class ShuntingYardSimpleParser {

    // trocar por enum pq isso é muito feio!!!
    private static final int LEFT_ASSOC  = 0;
    private static final int RIGHT_ASSOC = 1;
  
    private static final Map<String, int[]> OPERATORS = new HashMap<String, int[]>();
    static{
        OPERATORS.put("+", new int[] { 0, LEFT_ASSOC });
        OPERATORS.put("-", new int[] { 0, LEFT_ASSOC });
        OPERATORS.put("*", new int[] { 5, LEFT_ASSOC });
        OPERATORS.put("/", new int[] { 5, LEFT_ASSOC });
        OPERATORS.put("<", new int[] { 7, LEFT_ASSOC });
        OPERATORS.put("<=", new int[] { 7, LEFT_ASSOC });
        OPERATORS.put(">", new int[] { 7, LEFT_ASSOC });
        OPERATORS.put(">=", new int[] { 7, LEFT_ASSOC });
        OPERATORS.put("=", new int[] { 7, LEFT_ASSOC });
        OPERATORS.put("AND", new int[] { 9, LEFT_ASSOC });
        OPERATORS.put("OR", new int[] { 9, LEFT_ASSOC });
    }
    
    private ShuntingYardSimpleParser(){
    }
  
    public static ShuntingYardSimpleParser getInstance(){
        return new ShuntingYardSimpleParser();
    }
    
    private static boolean isOperator(String token){
        return OPERATORS.containsKey(token);
    }
  
    // Testa a associativiade
    private static boolean isAssociativeComponent(String token, int type) 
    {
        if (!isOperator(token))
            throw new IllegalArgumentException("Invalid token: " + token);
        
        if (OPERATORS.get(token)[1] == type)
            return true;
        return false;
    }
  
    // Compare precedence of operators.    
    private static final int assertPrecedence(String token1, String token2){
        if (!isOperator(token1) || !isOperator(token2)){
            throw new IllegalArgumentException("Invalid tokens: " + token1  + " " + token2);
        }
        return OPERATORS.get(token1)[0] - OPERATORS.get(token2)[0];
    }
  
    // Converte INFIX para RESVERSE POLISH
    public List<String> infixToReversePolishNotation(List<String> inputTokens) 
    {
        List<String> out = new ArrayList<>();
        Stack<String> stack = new Stack<>();
        List<List<String>> compositeOutput = new LinkedList<>(); 
        
        for (String token : inputTokens){
            // Se for um operador
            if (isOperator(token)){  
                //pilha não vazia e é um operator
                while (!stack.empty() && isOperator(stack.peek())){                    
                    if ((isAssociativeComponent(token, LEFT_ASSOC)         && 
                         assertPrecedence(token, stack.peek()) <= 0) || 
                        (isAssociativeComponent(token, RIGHT_ASSOC)        && 
                         assertPrecedence(token, stack.peek()) < 0)){
                        out.add(stack.pop());   
                        continue;
                    }
                    break;
                }
                // poe na pilha o novo oeprador
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
        Stack<String> stack = new Stack<String>();
         
        // For each token 
        for (String token : tokens) 
        {
            // If the token is a value push it onto the stack
            if (!isOperator(token)) 
            {
                stack.push(token);                
            }
            else
            {
                // Token is an operator: pop top two entries
                Double d2 = Double.valueOf( stack.pop() );
                Double d1 = Double.valueOf( stack.pop() );
                 
                //Get the result
                Double result = token.compareTo("+") == 0 ? d1 + d2 : 
                                token.compareTo("-") == 0 ? d1 - d2 :
                                token.compareTo("*") == 0 ? d1 * d2 :
                                                            d1 / d2;               
                                 
                // Push result onto stack
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
        ShuntingYardSimpleParser parser = ShuntingYardSimpleParser.getInstance();
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
