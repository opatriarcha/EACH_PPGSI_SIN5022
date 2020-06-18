
package br.com.ppgsi.testeDeSoftware.Interpreter;

import br.com.each.ppgsi.testeDeSoftware.commons.CasterHelper;
import choco.kernel.model.variables.integer.IntegerConstantVariable;
import choco.kernel.model.variables.integer.IntegerExpressionVariable;
import lombok.Setter;

/**
 *
 * @author orlando
 */
public abstract class AbstractExpression implements IExpression{
   
    protected @Setter Context context;
    
    public AbstractExpression(Context context){
        this.context = context;
    }
    
    protected <E> E convert (Class<E> clazz, Object content){
        return (E) new CasterHelper(clazz).cast(content.toString());
    }
    
    protected <E> E convertFromContext (){
        return (E) this.convert(this.context.getType(), this.context.getValue().toString());
    }
    
    protected boolean isLeftVariableAndRightVariable(Object left, Object right){
        return left instanceof IntegerExpressionVariable && right instanceof IntegerExpressionVariable;
    }
    
    protected boolean isLeftVariableAndRightConstant(Object left, Object right){
        return left instanceof IntegerExpressionVariable && right instanceof IntegerConstantVariable;
    }
    
    protected boolean isLeftConstantAndRightConstant(Object left, Object right){
        return left instanceof IntegerConstantVariable && right instanceof IntegerConstantVariable;
    }
    
    protected boolean isLeftConstantAndRightVariable(Object left, Object right){
        return left instanceof IntegerConstantVariable && right instanceof IntegerExpressionVariable;
    }
    
    //        if( isLeftConstantAndRightConstant(stateLeft, stateRight))
//            this.setState(Choco.sum((IntegerConstantVariable )stateLeft, (IntegerConstantVariable)stateRight));
//        if( isLeftConstantAndRightVariable(stateLeft, stateRight))
//            this.setState(Choco.sum((IntegerConstantVariable )stateLeft, (IntegerExpressionVariable)stateRight));
//        if( isLeftVariableAndRightConstant(stateLeft, stateRight))
//            this.setState(Choco.sum((IntegerExpressionVariable )stateLeft, (IntegerConstantVariable)stateRight));
        //if( isLeftVariableAndRightVariable(stateLeft, stateRight))
}
