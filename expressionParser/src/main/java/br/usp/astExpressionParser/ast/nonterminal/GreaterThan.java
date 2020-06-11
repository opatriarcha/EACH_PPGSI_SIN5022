package br.usp.astExpressionParser.ast.nonterminal;

import br.usp.astExpressionParser.ast.NonTerminal;

/**
 *
 * @author orlando
 */
public class GreaterThan extends NonTerminal {
	public boolean interpret() {
		return left.interpret() && right.interpret();
	}

	public String toString() {
		return String.format("(%s & %s)", left, right);
	}
}
