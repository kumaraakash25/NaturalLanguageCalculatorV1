package util;

import java.util.Stack;

import static util.Constants.*;

/**
 * Evaluates the mathematical expression and returns the result with two decimal places
 */
public class ExpressionEvaluator {

    public static double evaluate(final String expression) {
        if(expression.contains("null")){
            throw new IllegalArgumentException("No matching number or operator found for input");
        }
        final char[] tokens = expression.toCharArray();
        final Stack<Float> NUMERIC_STACK = new Stack<>();
        final Stack<Character> OPERATION_STACK = new Stack<>();

        for (int count = 0; count < tokens.length; count++) {
            if (tokens[count] == ' ') {
                continue;
            }
            if (tokens[count] >= ZERO && tokens[count] <= NINE) {
                final StringBuffer buffer = new StringBuffer();
                // To handle numbers greater than 1 digit (10 in our case)
                while (count < tokens.length && tokens[count] >= ZERO && tokens[count] <= NINE) {
                    buffer.append(tokens[count++]);
                }
                NUMERIC_STACK.push(Float.parseFloat(buffer.toString()));
                // If operation token
            } else if (tokens[count] == ADD_OPERATOR || tokens[count] == SUBTRACT_OPERATOR || tokens[count] == MULTIPLY_OPERATOR
                    || tokens[count] == DIVIDE_OPERATOR) {
                computingNumericStackAsPerOperatorPrecedence(tokens[count], NUMERIC_STACK, OPERATION_STACK);
            }
        }
        while (!OPERATION_STACK.empty()) {
            NUMERIC_STACK.push(performOperation(OPERATION_STACK.pop(), NUMERIC_STACK.pop(), NUMERIC_STACK.pop()));
        }
        final float expressionResult = NUMERIC_STACK.pop();
        return Math.round(expressionResult * 100.0) / 100.0;
    }

    /**
     * While adding a new operator in the OPERATION_STACK the method checks if the current operator has higher
     * precedence than the last added operators in the OPERATION_STACK
     *
     * @param token
     * @param NUMERIC_STACK
     * @param OPERATION_STACK
     */
    private static void computingNumericStackAsPerOperatorPrecedence(char token, final Stack<Float> NUMERIC_STACK,
                                                                     final Stack<Character> OPERATION_STACK) {
        while (!OPERATION_STACK.empty() && hasPrecedence(token, OPERATION_STACK.peek())) {
            NUMERIC_STACK.push(performOperation(OPERATION_STACK.pop(), NUMERIC_STACK.pop(), NUMERIC_STACK.pop()));
        }
        OPERATION_STACK.push(token);
    }

    /**
     * Performs teh mathematics operation on the input values
     *
     * @param operation
     * @param num1
     * @param num2
     * @return
     */
    private static float performOperation(final char operation, final float num1, final float num2) {
        switch (operation) {
            case ADD_OPERATOR:
                return num2 + num1;
            case SUBTRACT_OPERATOR:
                return num2 - num1;
            case MULTIPLY_OPERATOR:
                return num2 * num1;
            case DIVIDE_OPERATOR:
                if (num1 == 0)
                    throw new ArithmeticException("Cannot divide by 0");
                return num2 / num1;
        }
        return 0;
    }

    /**
     * Checks the precendence for the operator.
     *
     * @param operation1
     * @param operation2
     * @return
     */
    private static boolean hasPrecedence(final char operation1, final char operation2) {
        if ((operation1 == MULTIPLY_OPERATOR || operation1 == DIVIDE_OPERATOR) && (operation2 == ADD_OPERATOR ||
                operation2 == SUBTRACT_OPERATOR))
            return false;
        else
            return true;
    }
}
