package br.com.ppgsi.testeDeSoftware.Interpreter.integer;


import br.com.each.ppgsi.testeDeSoftware.lexerAnalyser.LexerAnalyserImpl;
import br.com.ppgsi.testeDeSoftware.Interpreter.Context;
import br.com.ppgsi.testeDeSoftware.Interpreter.IExpression;
import br.com.ppgsi.testeDeSoftware.Interpreter.OperatorExpression;
import choco.Choco;
import choco.kernel.model.constraints.Constraint;
import choco.kernel.model.variables.integer.IntegerVariable;

/**
 *
 * @author orlando
 */
public class AndExpression extends OperatorExpression<Constraint> {

    public AndExpression(Context context, IExpression firstExpression, IExpression secondExpression) {
        super(context, firstExpression, secondExpression);
    }
   
    @Override
    public String toString(){
        return LexerAnalyserImpl.AND;
    }

    @Override
    public Constraint interpret() {
        Object stateLeft = this.context.getFirstExpression().getState();
        Object stateRight = this.context.getSecondExpression().getState();
        if( stateRight instanceof IntegerVariable )
            this.setState(Choco.and((Constraint )stateLeft, (Constraint)stateRight));
        else if( stateRight instanceof Integer || state instanceof IntegerVariable  )
            this.setState(Choco.and((IntegerVariable)stateLeft, (IntegerVariable)stateRight));
        return (Constraint) this.getState(); 
    }
}
