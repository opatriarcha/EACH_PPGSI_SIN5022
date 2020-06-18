package br.com.ppgsi.testeDeSoftware.Interpreter.integer;
import br.com.ppgsi.testeDeSoftware.Interpreter.ConstantExpression;
import br.com.ppgsi.testeDeSoftware.Interpreter.Context;
import choco.kernel.model.variables.integer.IntegerConstantVariable;


/**
 *
 * @author orlando
 * @param <IntegerExpressionVariable>
 */
public class IntegerConstantExpression<IntegerExpressionVariable> extends ConstantExpression{

    public IntegerConstantExpression(Context context, Object content, Class clazz) {
        super(context, content, clazz);
        this.context.setValue(content);
        this.context.setType(clazz);
    }

    @Override
    public String toString() {
        return "INTEGER_CONSTANT";
    }
 
    
    @Override
    public Object interpret() {
        this.setState(new IntegerConstantVariable((int) this.convertFromContext())); 
        return this.getState();
    }

    
 

    

                
    
   
}
