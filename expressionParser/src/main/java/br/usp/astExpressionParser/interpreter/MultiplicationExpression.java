package br.usp.astExpressionParser.interpreter;

/**
 *
 * @author orlando
 */
public class MultiplicationExpression extends AbstractExpression{
    private IExpression firstExpression,secondExpression;
    public MultiplicationExpression(IExpression firstExpression, IExpression secondExpression){
        this.firstExpression=firstExpression;
        this.secondExpression=secondExpression;
    }
    @Override
    public int interpret(){
        return this.firstExpression.interpret()*this.secondExpression.interpret();
    }
    @Override
    public String toString(){
        return "*";
    }
}
