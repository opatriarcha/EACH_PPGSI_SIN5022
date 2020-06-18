package br.com.ppgsi.testeDeSoftware.Interpreter.real;

import br.com.ppgsi.testeDeSoftware.Interpreter.Context;
import br.com.ppgsi.testeDeSoftware.Interpreter.VariableExpression;
import choco.Choco;

/**
 *
 * @author orlando
 */
public class DoubleExpression<RealVariable> extends VariableExpression {
    
    private Double expression;

    public DoubleExpression( Context context, String variable, Integer fromValue, Integer toValue) {
        super(context, variable, fromValue, toValue);
    }
    
    @Override
    public String toString(){
        return "+";
    }

    @Override
    public RealVariable interpret() {
        return (RealVariable) Choco.makeRealVar(this.context.getVariableName(), this.context.getFromValue(), this.context.getToValue(), "");
    }
}
