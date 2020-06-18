package br.com.ppgsi.testeDeSoftware.Interpreter.integer;


import br.com.ppgsi.testeDeSoftware.Interpreter.Context;
import br.com.ppgsi.testeDeSoftware.Interpreter.IExpression;
import br.com.ppgsi.testeDeSoftware.Interpreter.OperatorExpression;
import choco.Choco;
import choco.kernel.model.variables.integer.IntegerExpressionVariable;

/**
 *
 * @author orlando
 */
public class NotEqualsExpression<Constraint> extends OperatorExpression{

    public NotEqualsExpression(Context context, IExpression firstExpression, IExpression secondExpression) {
        super(context, firstExpression, secondExpression);
    }

    @Override
    public Constraint interpret() {
        Object stateLeft = this.context.getFirstExpression().getState();
        Object stateRight = this.context.getSecondExpression().getState();
        this.setState(Choco.neq((IntegerExpressionVariable )stateLeft, (IntegerExpressionVariable)stateRight));
        return (Constraint) this.getState();
    }
    
}
