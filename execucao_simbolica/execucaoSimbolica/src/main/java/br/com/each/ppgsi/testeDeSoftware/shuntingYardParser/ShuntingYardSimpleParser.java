package br.com.each.ppgsi.testeDeSoftware.shuntingYardParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Thanksto Dijksra ( he is watching! )
 * @author orlando
 */
public class ShuntingYardSimpleParser {

    private static final int LEFT_ASSOC  = 0;
    private static final int RIGHT_ASSOC = 1;
  
    private static final Map<String, int[]> OPERATORS = new HashMap<String, int[]>();
    static{
        OPERATORS.put("+", new int[] { 0, LEFT_ASSOC });
        OPERATORS.put("-", new int[] { 0, LEFT_ASSOC });
        OPERATORS.put("*", new int[] { 5, LEFT_ASSOC });
        OPERATORS.put("/", new int[] { 5, LEFT_ASSOC });
        OPERATORS.put("<", new int[] { 9, LEFT_ASSOC });
        OPERATORS.put("<=", new int[] { 9, LEFT_ASSOC });
        OPERATORS.put(">", new int[] { 9, LEFT_ASSOC });
        OPERATORS.put(">=", new int[] { 9, LEFT_ASSOC });
        OPERATORS.put("=", new int[] { 9, LEFT_ASSOC });
        OPERATORS.put("^", new int[] { 9, LEFT_ASSOC });
    }
  
    private static boolean isOperator(String token){
        return OPERATORS.containsKey(token);
    }
  
    // Testa a associativiade
    private static boolean isAssociative(String token, int type) 
    {
        if (!isOperator(token))
            throw new IllegalArgumentException("Invalid token: " + token);
        
        if (OPERATORS.get(token)[1] == type)
            return true;
        return false;
    }
  
    // Compare precedence of operators.    
    private static final int cmpPrecedence(String token1, String token2){
        if (!isOperator(token1) || !isOperator(token2)){
            throw new IllegalArgumentException("Invalid tokens: " + token1  + " " + token2);
        }
        return OPERATORS.get(token1)[0] - OPERATORS.get(token2)[0];
    }
  
    // Converte INFIX para RESVERSE POLISH
    public static String[] infixToRPN(String[] inputTokens) 
    {
        ArrayList<String> out = new ArrayList<String>();
        Stack<String> stack = new Stack<String>();
         
        
        for (String token : inputTokens){
            // Se for um operador
            if (isOperator(token)){  
                //pilha não vazia e é um operator
                while (!stack.empty() && isOperator(stack.peek())){                    
                    if ((isAssociative(token, LEFT_ASSOC)         && 
                         cmpPrecedence(token, stack.peek()) <= 0) || 
                        (isAssociative(token, RIGHT_ASSOC)        && 
                         cmpPrecedence(token, stack.peek()) < 0)){
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
        String[] output = new String[out.size()];
        return out.toArray(output);
    }
     
    public static double RPNtoDouble(String[] tokens)
    {        
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
  
    public static void main(String[] args) {
        //String[] input = "( 1 + 2 ) * ( 3 / 4 ) - ( 5 + 6 )".split(" ");
        String[] input = "( ( X > 0 ) ^ ( Y <= 5 ) ) ^ ( X < Y + 5 )".split(" ");
        String[] output = infixToRPN(input);
         
        // constroi a string tirando os parentesis
         for (String token : output) {
            System.out.print(token + " ");
        }
         
        // Resultado
        Double result = RPNtoDouble( output );  
        System.out.println("RESULTDOUBLE: " + result);
    }
}
