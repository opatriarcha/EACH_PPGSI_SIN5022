package br.com.ppgsi.testeDeSoftware.Interpreter.integer;


import br.com.each.ppgsi.testeDeSoftware.lexerAnalyser.LexerAnalyserImpl;
import br.com.ppgsi.testeDeSoftware.Interpreter.Context;
import br.com.ppgsi.testeDeSoftware.Interpreter.IExpression;
import br.com.ppgsi.testeDeSoftware.Interpreter.OperatorExpression;
import choco.Choco;
import choco.kernel.model.constraints.Constraint;

/**
 *
 * @author orlando
 */
public class NotExpression extends OperatorExpression<Constraint> {

    public NotExpression(Context context, IExpression firstExpression, IExpression secondExpression) {
        super(context, firstExpression, secondExpression);
    }
     
    @Override
    public String toString(){
        return LexerAnalyserImpl.NOT;
    }

    @Override
    public Constraint interpret() {
        this.setState((Constraint) Choco.not((Constraint) this.context.getFirstExpression().getState()));
        return this.getState();
    }
}
