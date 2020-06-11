package br.usp.astExpressionParser.ast.nonterminal;

import br.usp.astExpressionParser.ast.NonTerminal;
import java.util.List;

/**
 *
 * @author orlando
 */
public class GenericExpression extends NonTerminal {
        private List<String> tokenizedContent; 
        
        public GenericExpression( List<String>content ){
            this.tokenizedContent = content;
        }
                
	public boolean interpret() {
		return left.interpret() && right.interpret();
	}

	public String toString() {
		return String.format("(%s & %s)", left, right);
	}

    public List<String> getTokenizedContent() {
        return this.tokenizedContent;
    }
        
        
}
