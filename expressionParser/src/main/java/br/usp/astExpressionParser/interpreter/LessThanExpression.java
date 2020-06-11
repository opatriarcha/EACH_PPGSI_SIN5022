/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.usp.astExpressionParser.interpreter;

/**
 *
 * @author orlando
 */
public class LessThanExpression extends AbstractExpression {
    
    private IExpression firstExpression,secondExpression;
    
    public LessThanExpression(IExpression firstExpression, IExpression secondExpression){
        this.firstExpression=firstExpression;
        this.secondExpression=secondExpression;
    }
    @Override
    public int interpret(){
        return this.firstExpression.interpret()+this.secondExpression.interpret();
    }
    @Override
    public String toString(){
        return "+";
    }
}
