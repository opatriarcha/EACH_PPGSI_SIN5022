package br.com.ppgsi.testeDeSoftware.Interpreter;

/**
 *
 * @author orlando
 * @param <T>
 */
public interface IExpression<T> {
    <T>T interpret();
    <T>T getState();
}
