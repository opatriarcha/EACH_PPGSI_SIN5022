
package br.usp.astExpressionParser.interpreter;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author orlando
 */
public abstract class AbstractExpression implements IExpression{
    private @Getter @Setter Object content;
    private @Getter @Setter Class type;
    
}
