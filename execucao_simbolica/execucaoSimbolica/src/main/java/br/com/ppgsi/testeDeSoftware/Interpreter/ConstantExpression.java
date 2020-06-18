package br.com.ppgsi.testeDeSoftware.Interpreter;

import choco.kernel.model.variables.integer.IntegerExpressionVariable;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author orlando
 */
public abstract class ConstantExpression<IntegerExpressionVariable> extends AbstractExpression{
    
    protected @Getter @Setter IntegerExpressionVariable state;
    
    public ConstantExpression( Context context, Object content, Class clazz ){
       super(context);
        this.context.setValue(content);
        this.context.setType(clazz);    
    }
}
