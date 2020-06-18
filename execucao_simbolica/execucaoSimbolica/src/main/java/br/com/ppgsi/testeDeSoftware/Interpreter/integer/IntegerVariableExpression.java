
package br.com.ppgsi.testeDeSoftware.Interpreter.integer;

import br.com.ppgsi.testeDeSoftware.Interpreter.Context;
import br.com.ppgsi.testeDeSoftware.Interpreter.VariableExpression;
import choco.Choco;

/**
 *
 * @author orlando
 * @param <IntegerVariable>
 */
public class IntegerVariableExpression<IntegerVariable>  extends VariableExpression{
    
    public IntegerVariableExpression(Context context, String variable, Integer fromValue, Integer toValue) {
        super(context, variable, fromValue, toValue);
    }

    @Override
    public String toString() {
        return "INTEGER_VARIABLE";
    }
    
   
    @Override
    public Object interpret() {
        this.setState(Choco.makeIntVar(this.context.getVariableName(), this.context.getFromValue(), this.context.getToValue()));
        return this.getState();
    }

    

   
    
    
}
