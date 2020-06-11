package br.usp.astExpressionParser.parser;

import br.usp.astExpressionParser.ast.BooleanExpression;
import br.usp.astExpressionParser.ast.nonterminal.And;
import br.usp.astExpressionParser.ast.nonterminal.Not;
import br.usp.astExpressionParser.ast.nonterminal.Or;
import br.usp.astExpressionParser.ast.terminal.False;
import br.usp.astExpressionParser.ast.terminal.True;
import br.usp.astExpressionParser.lexer.Lexer;
import java.io.ByteArrayInputStream;


public class RecursiveDescentParser {
	private Lexer lexer;
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
        
        public static void main(String[] args) {
        Lexer lexer = new Lexer(new ByteArrayInputStream("(X > 0) ^ (Y <= 5) ^ (X >= Y)".getBytes()));
		RecursiveDescentParser parser = new RecursiveDescentParser(lexer);
	    BooleanExpression ast = parser.build();
		System.out.println(String.format("AST: %s", ast));
		System.out.println(String.format("RES: %s", ast.interpret()));
    }
}
