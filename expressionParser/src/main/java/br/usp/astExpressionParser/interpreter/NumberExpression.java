package br.usp.astExpressionParser.interpreter;

/**
 *
 * @author orlando
 */
public class NumberExpression extends AbstractExpression{
    
    private int number;
    
    public NumberExpression(int number){
        this.number=number;
    }
    public NumberExpression(String number){
        this.number=Integer.parseInt(number);
    }
    @Override
    public int interpret(){
        return this.number;
    }
}
