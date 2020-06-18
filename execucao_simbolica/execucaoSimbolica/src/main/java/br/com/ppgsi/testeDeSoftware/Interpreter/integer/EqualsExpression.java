package br.com.ppgsi.testeDeSoftware.Interpreter.integer;

import br.com.each.ppgsi.testeDeSoftware.lexerAnalyser.LexerAnalyserImpl;
import br.com.ppgsi.testeDeSoftware.Interpreter.Context;
import br.com.ppgsi.testeDeSoftware.Interpreter.IExpression;
import br.com.ppgsi.testeDeSoftware.Interpreter.OperatorExpression;
import choco.Choco;
import choco.kernel.model.constraints.Constraint;
import choco.kernel.model.variables.integer.IntegerExpressionVariable;

/**
 *
 * @author orlando
 */
public class EqualsExpression extends OperatorExpression<Constraint> {

    public EqualsExpression(Context context, IExpression firstExpression, IExpression secondExpression) {
        super(context, firstExpression, secondExpression);
    }
    
     @Override
    public String toString(){
        return LexerAnalyserImpl.EQUAL;
    }


    @Override
    public Constraint interpret() {
        Object stateLeft = this.context.getFirstExpression().getState();
        Object stateRight = this.context.getSecondExpression().getState();
        this.setState(Choco.eq((IntegerExpressionVariable )stateLeft, (IntegerExpressionVariable)stateRight));
        return (Constraint) this.getState();
    }
    
   
}
