package br.com.ppgsi.testeDeSoftware.Interpreter.integer;


import br.com.each.ppgsi.testeDeSoftware.lexerAnalyser.LexerAnalyserImpl;
import br.com.ppgsi.testeDeSoftware.Interpreter.AritmeticExpression;
import br.com.ppgsi.testeDeSoftware.Interpreter.Context;
import br.com.ppgsi.testeDeSoftware.Interpreter.IExpression;
import choco.Choco;
import choco.kernel.model.variables.integer.IntegerExpressionVariable;

/**
 *
 * @author orlando//return this.firstExpression.interpret()+this.secondExpression.interpret();
 */
public class AdditionExpression extends AritmeticExpression<IntegerExpressionVariable> {

    public AdditionExpression(Context context, IExpression firstExpression, IExpression secondExpression) {
        super(context, firstExpression, secondExpression);
    }
       
    @Override
    public String toString(){
        return LexerAnalyserImpl.PLUS;
    }

    @Override
    public IntegerExpressionVariable interpret() {
        Object stateLeft = this.context.getFirstExpression().getState();
        Object stateRight = this.context.getSecondExpression().getState();
        this.setState(Choco.sum((IntegerExpressionVariable )stateLeft, (IntegerExpressionVariable)stateRight));
        return (IntegerExpressionVariable) this.getState();
    }
    
    
}
