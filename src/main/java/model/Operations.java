package model;

import util.Constants;

public enum Operations {
    ADD(Constants.ADD_OPERATOR),
    PLUS(Constants.ADD_OPERATOR),
    SUBTRACT(Constants.SUBTRACT_OPERATOR),
    MINUS(Constants.SUBTRACT_OPERATOR),
    LESS(Constants.SUBTRACT_OPERATOR),
    MULTIPLIED_BY(Constants.MULTIPLY_OPERATOR),
    TIMES(Constants.MULTIPLY_OPERATOR),
    DIVIDED_BY(Constants.DIVIDE_OPERATOR),
    OVER(Constants.DIVIDE_OPERATOR);

    private char operationChar;

    Operations(char c) {
        operationChar = c;
    }

    public char getOperation() {
        return operationChar;
    }
}
