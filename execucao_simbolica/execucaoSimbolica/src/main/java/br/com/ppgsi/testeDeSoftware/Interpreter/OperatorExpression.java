
package br.com.ppgsi.testeDeSoftware.Interpreter;

import choco.kernel.model.constraints.Constraint;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author orlando
 */
public abstract class OperatorExpression<Constraint> extends AbstractExpression{

    protected @Getter @Setter Constraint state;
            
    public OperatorExpression(Context context, IExpression firstExpression, IExpression secondExpression) {
        super(context);
        this.context.setFirstExpression(firstExpression);
        this.context.setSecondExpression(secondExpression);
    }
    
}
