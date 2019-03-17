package util;

import model.Dictionary;

import java.util.Map;

import static util.Constants.SEPARATOR;

/**
 * Generates a mathematical expression from natural language.
 */
public class MathExpressionGenerator {

    private static Map<String, String> DICTIONARY = Dictionary.getDictionary();

    /**
     * Generate and return a mathematical expression from the inputString
     * eg. inputString : two plus four minus one divide by 9
     * returns 2 + 4 -1 /9
     *
     * @param inputString
     * @return
     */
    public static String getMathematicalExpression(final String inputString) {
        final String[] inputTokens = inputString.split(SEPARATOR);
        if (inputTokens.length % 2 == 0) {
            // Odd number of tokens is interpreted as illegal input
            throw new IllegalArgumentException("Missing operator or number");
        }
        final StringBuffer strBuffer = new StringBuffer();
        for (int count = 0; count < inputTokens.length; count++) {
            strBuffer.append(DICTIONARY.get(inputTokens[count].toUpperCase())).append(SEPARATOR);
        }
        return strBuffer.toString().trim();
    }
}

