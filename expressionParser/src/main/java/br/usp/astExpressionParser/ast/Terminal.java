package br.usp.astExpressionParser.ast;

public abstract class Terminal implements BooleanExpression{
	protected Object value;

	public Terminal(Object value) {
		this.value = value;
	}

	public String toString() {
		return String.format("%s", value);
	}
}
