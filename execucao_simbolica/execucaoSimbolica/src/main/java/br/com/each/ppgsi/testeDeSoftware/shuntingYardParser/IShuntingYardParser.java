package br.com.each.ppgsi.testeDeSoftware.shuntingYardParser;

import java.util.List;

/**
 *
 * @author orlando
 */
public interface IShuntingYardParser {

    // Converte INFIX para RESVERSE POLISH
    List<String> infixToReversePolishNotation(List<String> inputTokens);

    String toPrettyFormat(List<String> valueList);
    
}
