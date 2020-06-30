package br.com.each.ppgsi.testeDeSoftware.bouerAndSamelsonAlgorithm;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author orlando
 */
@Deprecated()
public class InfixToPolishAlgorithm {
    protected static Stack<String> convertToPolish(String in) {
    Stack<String> inputString = new Stack<>();
    Stack<String> outputString = new Stack<>();
    Stack<String> store = new Stack<>();

    inputString.addAll(reverse(splitForExpressonsArray(in)));

    while (!inputString.empty()) {
        if (isNumber(inputString.peek())) {
            outputString.push(inputString.pop());
        }  else if (isBinaryOperator(inputString.peek())) {
            if (store.isEmpty()) {
                store.push(inputString.pop());
            } else if (getComparPriority(inputString.peek()) > getStorePriority(store.peek())) {
                store.push(inputString.pop());
            } else {
                outputString.push(store.pop());
            }
        } else if (isBracket(inputString.peek())) {
            if (inputString.peek().equals("(")) {
                store.push(inputString.pop());
            } else {
                while (!store.peek().equals("(")) {
                    outputString.push(store.pop());
                }
                store.pop();
                inputString.pop();
            }
        }
    }
    while (!store.isEmpty()) {
        outputString.push(store.pop());
    }

    return outputString;
}

static Stack<String> splitForExpressonsArray(String input) {
    Stack<String> result = new Stack<String>();
    Matcher m = Pattern.compile("\\d+(?:\\.\\d+)?|[a-zA-Z]+|[^\\s\\w\\.]").matcher(input);
    while (m.find()) {
        result.add(m.group());
    }
    return result;
}

static int getComparPriority(String s) {
    switch (s) {
    case "(":
        return 100;
    case ")":
        return 0;
    case "+":
        return 2;
    case "-":
        return 2;
    case "*":
        return 3;
    case "/":
        return 3;
    default:
        return 0;
    }
}

static int getStorePriority(String s) {
    switch (s) {
    case "(":
        return 0;
    case "+":
        return 2;
    case "-":
        return 2;
    case "*":
        return 3;
    case "/":
        return 3;
    default:
        return 0;
    }
}

static boolean isBinaryOperator(String s) {
    List<String> operators = Arrays.asList("+", "-", "*", "/");
    return operators.contains(s);
}

static boolean isBracket(String s) {
    List<String> brackets = Arrays.asList("(", ")");
    return brackets.contains(s);
}

static boolean isUnarOperator(String s) {
    List<String> operators = Arrays.asList("sin", "cos", "tan", "ctg");
    return operators.contains(s);
}
static boolean isNumber(String peek) {
    Pattern p = Pattern.compile("[0-9]*\\.?[0-9]");
    Matcher m = p.matcher(peek);
    return m.matches();
}
static Stack<String> reverse(Stack<String> split) {
    Stack<String> result = new Stack<String>();
    while (!split.isEmpty()) {
        result.push(split.pop());
    }
    return result;
}
    public static void main(String[] args) {
        Stack<String> result = InfixToPolishAlgorithm.convertToPolish("(2*2) + (4-3)");
        System.out.println(result.toString());
    }
    
}
