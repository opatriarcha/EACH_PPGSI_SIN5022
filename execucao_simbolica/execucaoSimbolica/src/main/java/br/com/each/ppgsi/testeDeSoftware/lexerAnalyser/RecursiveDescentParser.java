package br.com.each.ppgsi.testeDeSoftware.lexerAnalyser;



//Teste falido por enquanto
public class RecursiveDescentParser {
	/*private Lexer lexer;
	private String symbol;
	private BooleanExpression root;

	private final True t = new True();
	private final False f = new False();

	public RecursiveDescentParser(Lexer lexer) {
		this.lexer = lexer;
	}

	public BooleanExpression build() {
		expression();
		return root;
	}

	private void expression() {
		this.term();
		while (symbol.equals(Lexer.OR)) {
			Or or = new Or();
			or.setLeft(root);
			term();
			or.setRight(root);
			root = or;
		}
	}

	private void term() {
		this.factor();
		while (symbol.equals(Lexer.AND)) {
			And and = new And();
			and.setLeft(root);
			factor();
			and.setRight(root);
			root = and;
		}
	}

	private void factor() {
		symbol = lexer.nextSymbol();
		if (symbol.equals(Lexer.TRUE)) {
			root = t;
			symbol = lexer.nextSymbol();
		} else if (symbol.equals(Lexer.FALSE)) {
			root = f;
			symbol = lexer.nextSymbol();
		} else if (symbol.equals(Lexer.NOT)) {
			Not not = new Not();
			factor();
			not.setChild(root);
			root = not;
		} else if (symbol.equals(Lexer.LEFT)) {
			expression();
			symbol = lexer.nextSymbol(); // we don't care about ')'
		} else {
			throw new RuntimeException("Malformed fucking Expression");
		}
	}
        */
        
}
