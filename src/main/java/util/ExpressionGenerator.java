package util;

import model.Dictionary;
import org.apache.commons.lang.StringUtils;

import java.util.Arrays;
import java.util.Map;

import static util.Constants.SEPARATOR;

/**
 * Generates a mathematical expression from natural language.
 */
public class ExpressionGenerator {

    private static Map<String, String> DICTIONARY = Dictionary.getDictionary();

    /**
     * Generate and return a mathematical expression from the inputString
     * eg. inputString : two plus four minus one divide by 9
     * returns 2 + 4 -1 /9
     */
    public static String getMathEquivalent(final String inputString) {
        validateNonEmptyInput(inputString);
        final StringBuffer expression = new StringBuffer();
        final String[] inputTokens = inputString.split(SEPARATOR);
        validateTokenCount(inputTokens);
        Arrays.asList(inputTokens).stream().forEach(element -> {
            final String token = DICTIONARY.get(element.toUpperCase());
            validateToken(token);
            expression.append(DICTIONARY.get(element.toUpperCase())).append(SEPARATOR);
        });
        return expression.toString().trim();
    }

    /**
     * Validates if the token is equivalent to any mathematical value, otherwise throws IllegalArgumentException
     */
    private static void validateToken(final String token) {
        if (StringUtils.isEmpty(token)) {
            throw new IllegalArgumentException("Input Token is invalid");
        }
    }

    /**
     * Validates that the token count is odd. num1 op1 num2 op2 ........ num n
     */
    private static void validateTokenCount(final String[] inputTokens) {
        if (inputTokens.length % 2 == 0) {
            // Even number of tokens is interpreted as illegal input
            throw new IllegalArgumentException("Missing operator or number");
        }
    }

    /**
     * Validates the input string is not empty.
     */
    private static void validateNonEmptyInput(final String inputString){
        if(StringUtils.isEmpty(inputString)){
            throw new IllegalArgumentException("Input String is empty");
        }
    }
}

