package br.com.ppgsi.testeDeSoftware.Interpreter;

import choco.kernel.model.constraints.Constraint;
import java.util.List;
import lombok.Data;

/**
 *
 * @author orlando
 */
@Data
public class Context {
    private String variableName;
    private Object value;
    private Class type;
    private List<Constraint> constraints;
    private IExpression firstExpression;
    private IExpression secondExpression;
    private Integer fromValue;
    private Integer toValue;
    
    public void makeConstraint(Constraint constraint){
        this.constraints.add(constraint);
    }
    
}
