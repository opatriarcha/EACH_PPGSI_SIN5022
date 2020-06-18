package br.com.ppgsi.testeDeSoftware.Interpreter;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author orlando
 */
public abstract class VariableExpression<IntegerVariable> extends AbstractExpression{
    
    protected @Setter @Getter IntegerVariable state;
    
    public VariableExpression(Context context, String variable, Integer fromValue, Integer toValue){
        super(context);
        this.context.setVariableName(variable);
        this.context.setFromValue(fromValue);
        this.context.setToValue(toValue);
    }
    
    @Override
    public String toString(){
        return "VARIABLE";
    }
}
