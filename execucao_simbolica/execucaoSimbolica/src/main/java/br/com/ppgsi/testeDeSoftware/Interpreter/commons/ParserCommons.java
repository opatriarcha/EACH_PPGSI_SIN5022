package br.com.ppgsi.testeDeSoftware.Interpreter.commons;


import br.com.each.ppgsi.testeDeSoftware.lexerAnalyser.LexerAnalyserImpl;
import br.com.ppgsi.testeDeSoftware.Interpreter.Context;
import br.com.ppgsi.testeDeSoftware.Interpreter.IExpression;
import br.com.ppgsi.testeDeSoftware.Interpreter.integer.AdditionExpression;
import br.com.ppgsi.testeDeSoftware.Interpreter.integer.AndExpression;
import br.com.ppgsi.testeDeSoftware.Interpreter.integer.DivisionExpression;
import br.com.ppgsi.testeDeSoftware.Interpreter.integer.EqualsExpression;
import br.com.ppgsi.testeDeSoftware.Interpreter.integer.GreaterThanExpression;
import br.com.ppgsi.testeDeSoftware.Interpreter.integer.GreaterThanOrEqualExpression;
import br.com.ppgsi.testeDeSoftware.Interpreter.integer.LessThanExpression;
import br.com.ppgsi.testeDeSoftware.Interpreter.integer.LessThanOrEqualExpression;
import br.com.ppgsi.testeDeSoftware.Interpreter.integer.MultiplicationExpression;
import br.com.ppgsi.testeDeSoftware.Interpreter.integer.NotEqualsExpression;
import br.com.ppgsi.testeDeSoftware.Interpreter.integer.NotExpression;
import br.com.ppgsi.testeDeSoftware.Interpreter.integer.OrExpression;
import br.com.ppgsi.testeDeSoftware.Interpreter.integer.SubtractionExpression;
import java.util.List;

/**
 *
 * @author orlando
 */
public class ParserCommons {
    
    public static boolean isOperator(String symbol) {
        return (symbol.equals(LexerAnalyserImpl.EQUAL) || 
                symbol.equals(LexerAnalyserImpl.GREATER) ||
                symbol.equals(LexerAnalyserImpl.GREATER_THAN_OR_EQUAL) ||
                symbol.equals(LexerAnalyserImpl.LESS) ||
                symbol.equals(LexerAnalyserImpl.LESS_THAN_OR_EQUAL) ||
                symbol.equals(LexerAnalyserImpl.DIVISION) ||
                symbol.equals(LexerAnalyserImpl.MULTIPLY) ||
                symbol.equals(LexerAnalyserImpl.MINUS) ||
                symbol.equals(LexerAnalyserImpl.PLUS) ||
                symbol.equals(LexerAnalyserImpl.AND) ||
                symbol.equals(LexerAnalyserImpl.NOT_EQUAL) ||
                symbol.equals(LexerAnalyserImpl.NOT) || 
                symbol.equals(LexerAnalyserImpl.OR)
                );
    }
    public static IExpression getExpressionObject(Context context, IExpression firstExpression,IExpression secondExpression,String symbol){
        switch (symbol) {
            case LexerAnalyserImpl.PLUS:
                return new AdditionExpression(context, firstExpression,secondExpression);
            case LexerAnalyserImpl.MINUS:
                return new SubtractionExpression(context, firstExpression,secondExpression);
            case LexerAnalyserImpl.MULTIPLY:
                return new MultiplicationExpression(context, firstExpression,secondExpression);
            case LexerAnalyserImpl.DIVISION:
                return new DivisionExpression(context, firstExpression,secondExpression);
            case LexerAnalyserImpl.LESS:
                return new LessThanExpression(context, firstExpression,secondExpression);
            case LexerAnalyserImpl.LESS_THAN_OR_EQUAL:
                return new LessThanOrEqualExpression(context, firstExpression,secondExpression);
            case LexerAnalyserImpl.GREATER:
                return new GreaterThanExpression(context, firstExpression,secondExpression);
            case LexerAnalyserImpl.GREATER_THAN_OR_EQUAL:
                return new GreaterThanOrEqualExpression(context, firstExpression,secondExpression);
            case LexerAnalyserImpl.EQUAL:
                return new EqualsExpression(context, firstExpression,secondExpression);
            case LexerAnalyserImpl.NOT_EQUAL:
                return new NotEqualsExpression(context, firstExpression,secondExpression);
            case LexerAnalyserImpl.AND:
                return new AndExpression(context, firstExpression,secondExpression);
            case LexerAnalyserImpl.OR:
                return new OrExpression(context, firstExpression,secondExpression);
            case LexerAnalyserImpl.NOT:
                return new NotExpression(context, firstExpression,secondExpression);
            default:
                throw new RuntimeException("Expression Type not found!!! OMG!!!" );
        }
    }
    
    public static boolean isVariable(String symbol, List<String> variables) {
        return variables.contains(symbol);
    }
    
}
