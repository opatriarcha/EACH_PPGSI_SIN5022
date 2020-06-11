package br.usp.astExpressionParser.ast;

/**
 * Nao é a mesma coisa mas é um começo....
 * <expression>::=<term>{<or><term>}
 * <term>::=<factor>{<and><factor>}
 * <factor>::=<constant>|<not><factor>|(<expression>)
 * <constant>::= false|true
 * <or>::='|'
 * <and>::='&'
 * <not>::='!'
 */
public interface BooleanExpression {
	public boolean interpret();
}
