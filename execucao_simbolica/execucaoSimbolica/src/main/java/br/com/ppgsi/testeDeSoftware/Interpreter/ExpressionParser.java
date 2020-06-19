
package br.com.ppgsi.testeDeSoftware.Interpreter;

import br.com.each.ppgsi.testeDeSoftware.commons.Commons;
import br.com.ppgsi.testeDeSoftware.Interpreter.commons.ParserCommons;
import br.com.ppgsi.testeDeSoftware.Interpreter.integer.IntegerConstantExpression;
import br.com.ppgsi.testeDeSoftware.Interpreter.integer.IntegerVariableExpression;
import choco.Choco;
import choco.kernel.model.constraints.Constraint;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;


/**
 *
 * @author orlando
 */

public class ExpressionParser {
    
    private final String context;
    private final List<String> variableNames;
    private final Stack<IExpression> stack = new Stack<>();
    private HashMap<String, IntegerVariableExpression> variables = new HashMap<>();
    
  
            
    
    private ExpressionParser(final String toParse, final List<String> variableNames, HashMap<String, IntegerVariableExpression> variables ){
        this.context = toParse;
        this.variableNames = variableNames;
        this.variables = variables;
    }
    
    public static final ExpressionParser getInstance(final String toParse, final List<String> variableNames, HashMap<String, IntegerVariableExpression> variables ){
        return new ExpressionParser(toParse, variableNames, variables);
    }
       
    public Constraint parse(){
        String[] tokenList = this.context.split(" ");
        Context context = new Context();
        //boolean hasMoreThanOneConjunctionOperator = this.hasMoreThanOneConjunction(tokenList);
        Stack<String> previousTokens = new Stack<>();
        ExpressionHolder holder = new ExpressionHolder();
        
        for (String symbol : tokenList) {
            
            if (!ParserCommons.isOperator(symbol) && !ParserCommons.isVariable(symbol, variableNames)) {
                
                IExpression numberExpression = new IntegerConstantExpression(context, symbol, Integer.class);
                numberExpression.interpret();
                if(!holder.isEmpty())
                    holder.add(numberExpression);
                //else
                    stack.push(numberExpression);
                previousTokens.push(symbol);
            } else if (ParserCommons.isOperator(symbol)) {                
                IExpression secondExpression = (IExpression) stack.pop(); //inverter por ser pilha... mas tem q ser como escala isso
                IExpression firstExpression = (IExpression) stack.pop();
                
                
                IExpression operator = ParserCommons.getExpressionObject(context, firstExpression, secondExpression, symbol);
                
                //constraintType or IntegerExpressionVariable type
                Object result =  operator.interpret();
                
                if(!holder.isEmpty())
                    holder.add(result);
                //else
                    stack.push(operator);
                //se nao interpretar fica legal, mas arriscado
                previousTokens.push(symbol);
            }else if(ParserCommons.isVariable(symbol, variableNames)){
                IExpression variableExpression = null;
                if(variables.containsKey(symbol))
                    variableExpression = variables.get(symbol);
                else{
                    variableExpression = new IntegerVariableExpression(context,  symbol, Commons.FROM_NUMBER, Commons.TO_NUMBER);
                    variableExpression.interpret();
                    variables.put(symbol, (IntegerVariableExpression) variableExpression);
                }
                
                if( this.previousTokenIsVariable(previousTokens, variableNames) )
                    holder.add(variableExpression);
                //else
                    stack.push(variableExpression);
                
                
                previousTokens.push(symbol);
                
            }
            if( holder.isReadyForFinish() ){
                
            }
        }
       IExpression result = stack.pop();
       Constraint constraint = (Constraint) result.interpret();
       
       return constraint;
    }
    
    private boolean previousTokenIsOperator( Stack<String> stack ){
        if( stack.isEmpty() )
            return false;
        return ParserCommons.isOperator(stack.peek());
    }
    
    private boolean previousTokenIsVariable( Stack<String> stack, List<String> variableNames ){
        if( stack.isEmpty() )
            return false;
        return ParserCommons.isVariable(stack.peek(), variableNames);
    }
    
    public static Constraint concatenateConstraints(List<Constraint> constraints ){
        Constraint finalConstraint = null;
        for( Constraint constraint : constraints){
            finalConstraint = Choco.and(constraint);
        }
        
        return finalConstraint;
    }
    
    private boolean hasMoreThanOneConjunction(String[] tokenList ){
        int counter = 0;
        for( String str : tokenList ){
            if( str != null && ( str.equals( "AND" ) || str.equals("OR") ) )
                counter++;
        }
        return counter > 0;
    }
    
    
}
