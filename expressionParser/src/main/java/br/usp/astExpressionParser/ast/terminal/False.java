package br.usp.astExpressionParser.ast.terminal;

import br.usp.astExpressionParser.ast.Terminal;


public class False extends Terminal {
	public False() {
		super(false);
	}

	public boolean interpret() {
		return (boolean) value;
	}
}
