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
public class LessThanExpression extends OperatorExpression<Constraint>{

    public LessThanExpression(Context context, IExpression firstExpression, IExpression secondExpression) {
        super(context, firstExpression, secondExpression);
    }
    
    @Override
    public String toString(){
        return LexerAnalyserImpl.LESS;
    }

    @Override
    public Constraint interpret() {
        
        Object stateLeft = this.context.getFirstExpression().getState();
        Object stateRight = this.context.getSecondExpression().getState();
        if( stateRight instanceof IntegerExpressionVariable )
            this.setState(Choco.lt((IntegerExpressionVariable)stateLeft, (IntegerExpressionVariable)stateRight));
        else if( stateRight instanceof Integer )
             this.setState(Choco.lt((IntegerExpressionVariable)stateLeft, (Integer)stateRight));
        return (Constraint) this.getState();
    }
    
   
}
