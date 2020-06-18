package br.com.ppgsi.testeDeSoftware.Interpreter.integer;


import br.com.ppgsi.testeDeSoftware.Interpreter.Context;
import br.com.ppgsi.testeDeSoftware.Interpreter.IExpression;
import br.com.ppgsi.testeDeSoftware.Interpreter.OperatorExpression;
import choco.kernel.model.constraints.Constraint;

/**
 *
 * @author orlando
 */
public class LogicalGroupingExpression extends OperatorExpression<Constraint> {

    public LogicalGroupingExpression(Context context, IExpression firstExpression, IExpression secondExpression) {
        super(context, firstExpression, secondExpression);
    }
    
     @Override
    public String toString(){
        return "GROUP";
    }


    @Override
    public Constraint interpret() {
        
        return null;
    }
}