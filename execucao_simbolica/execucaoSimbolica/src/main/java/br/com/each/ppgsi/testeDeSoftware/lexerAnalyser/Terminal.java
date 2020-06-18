package br.com.each.ppgsi.testeDeSoftware.lexerAnalyser;

public abstract class Terminal implements BooleanExpression{
	protected Object value;

	public Terminal(Object value) {
		this.value = value;
	}

        @Override
	public String toString() {
		return String.format("%s", value);
	}
}
