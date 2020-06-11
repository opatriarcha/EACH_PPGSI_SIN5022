package br.usp.astExpressionParser.ast.terminal;

import br.usp.astExpressionParser.ast.Terminal;



public class True extends Terminal {
	
        public True() {
		super(true);
	}

	public boolean interpret() {
		return (boolean) value;
	}
}
