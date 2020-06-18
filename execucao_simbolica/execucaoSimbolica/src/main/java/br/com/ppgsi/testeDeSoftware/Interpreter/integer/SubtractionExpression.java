package br.com.ppgsi.testeDeSoftware.Interpreter.integer;

import br.com.each.ppgsi.testeDeSoftware.lexerAnalyser.LexerAnalyserImpl;
import br.com.ppgsi.testeDeSoftware.Interpreter.AritmeticExpression;
import br.com.ppgsi.testeDeSoftware.Interpreter.Context;
import br.com.ppgsi.testeDeSoftware.Interpreter.IExpression;
import choco.Choco;
import choco.kernel.model.variables.integer.IntegerExpressionVariable;

/**
 *
 * @author orlando
 * @param <IntegerExpressionVariable>
 */
public class SubtractionExpression extends AritmeticExpression<IntegerExpressionVariable>{

    public SubtractionExpression(Context context, IExpression firstExpression, IExpression secondExpression) {
        super(context, firstExpression, secondExpression);
    }
    
    @Override
    public String toString(){
        return LexerAnalyserImpl.MINUS;
    }

    @Override
    public IntegerExpressionVariable interpret() {
        Object stateLeft = this.context.getFirstExpression().getState();
        Object stateRight = this.context.getSecondExpression().getState();
        this.setState(Choco.minus((IntegerExpressionVariable )stateLeft, (IntegerExpressionVariable)stateRight));
        return (IntegerExpressionVariable) this.getState();
    }
} 