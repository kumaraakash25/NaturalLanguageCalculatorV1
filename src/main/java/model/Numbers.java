package model;

// As per the requirement, more acceptable input can be added
public enum Numbers {
    ZERO(0),
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10);

    private int wholeNumberValue;

    Numbers(int integer) {
        wholeNumberValue = integer;
    }

    public int getNumericEquivalent() {
        return wholeNumberValue;
    }
}
