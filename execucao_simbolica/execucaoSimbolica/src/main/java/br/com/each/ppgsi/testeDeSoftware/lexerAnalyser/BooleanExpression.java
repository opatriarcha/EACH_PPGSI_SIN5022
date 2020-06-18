package br.com.each.ppgsi.testeDeSoftware.lexerAnalyser;

/**
 * Nao é a mesma coisa mas ta ajudando
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
