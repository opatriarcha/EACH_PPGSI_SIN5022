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
 */
public class DivisionExpression extends AritmeticExpression<IntegerExpressionVariable>{

    public DivisionExpression(Context context, IExpression firstExpression, IExpression secondExpression) {
        super(context, firstExpression, secondExpression);
    }
    
    
    @Override
    public String toString(){
        return LexerAnalyserImpl.DIVISION;
    }

    @Override
    public IntegerExpressionVariable interpret() {
        Object stateLeft = this.context.getFirstExpression().getState();
        Object stateRight = this.context.getSecondExpression().getState();
        this.setState(Choco.div((IntegerExpressionVariable )stateLeft, (IntegerExpressionVariable)stateRight));
        return (IntegerExpressionVariable) this.getState();
    }
}
