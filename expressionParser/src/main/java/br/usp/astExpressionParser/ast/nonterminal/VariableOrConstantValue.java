/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.usp.astExpressionParser.ast.nonterminal;

import br.usp.astExpressionParser.ast.NonTerminal;

/**
 *
 * @author orlando
 */
public class VariableOrConstantValue extends NonTerminal {
	public boolean interpret() {
		return left.interpret() && right.interpret();
	}

	public String toString() {
		return String.format("(%s & %s)", left, right);
	}
}
	
