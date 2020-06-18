package br.com.ppgsi.testeDeSoftware.Interpreter;

import choco.kernel.model.variables.integer.IntegerExpressionVariable;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author orlando
 */
public abstract class AritmeticExpression<IntegerExpressionVariable> extends AbstractExpression{
    
    protected @Getter @Setter IntegerExpressionVariable state;

    public AritmeticExpression(Context context, IExpression firstExpression, IExpression secondExpression) {
        super(context);
        this.context.setFirstExpression(firstExpression);
        this.context.setSecondExpression(secondExpression);
    }
    
}
