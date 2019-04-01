package util;

import java.util.Stack;

import static util.Constants.*;

/**
 * Evaluates the mathematical expression and returns the result with two decimal places
 */
public class ExpressionEvaluator {

    /**
     * Computes the mathematical result given an input of mathematical expression.
     */
    public static double evaluate(final String expression) {
        final Stack<Float> numericStack = new Stack<>();
        final Stack<Character> operatorStack = new Stack<>();

        final char[] tokens = expression.toCharArray();
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
                numericStack.push(Float.parseFloat(buffer.toString()));
                // If operation token
            } else if (tokens[count] == ADD_OPERATOR || tokens[count] == SUBTRACT_OPERATOR || tokens[count] == MULTIPLY_OPERATOR
                    || tokens[count] == DIVIDE_OPERATOR) {
                computeStack(tokens[count], numericStack, operatorStack);
            }
        }
        while (!operatorStack.empty()) {
            numericStack.push(performOperation(operatorStack.pop(), numericStack.pop(), numericStack.pop()));
        }
        final double expressionResult = roundOff(numericStack.pop());
        return expressionResult;
    }

    /**
     * While adding a new operator in the stack checks if the current operator has higher
     * precedence than the last added operators
     */
    private static void computeStack(char token, final Stack<Float> numericStack,
                                     final Stack<Character> operatorStack) {
        while (!operatorStack.empty() && hasPrecedence(token, operatorStack.peek())) {
            numericStack.push(performOperation(operatorStack.pop(), numericStack.pop(), numericStack.pop()));
        }
        operatorStack.push(token);
    }

    /**
     * Performs mathematics operation on the input values.
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
     * Checks the precedence of the operator.
     */
    private static boolean hasPrecedence(final char operation1, final char operation2) {
        if ((operation1 == MULTIPLY_OPERATOR || operation1 == DIVIDE_OPERATOR) && (operation2 == ADD_OPERATOR ||
                operation2 == SUBTRACT_OPERATOR))
            return false;
        else
            return true;
    }

    /**
     * Round off the final result nearest to two decimal places.
     */
    private static double roundOff(final Float plainResult) {
        return Math.round(plainResult * 100.0) / 100.0;
    }
}
