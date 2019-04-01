import org.junit.Test;
import util.ExpressionEvaluator;
import util.ExpressionGenerator;

import static org.junit.Assert.*;

public class RunCalculatorTest {

    @Test
    public void happyCase() {
        String inputString = "nine over eight plus four times two divided_by three";
        String expression = ExpressionGenerator.getMathEquivalent(inputString);
        assertEquals("9 / 8 + 4 * 2 / 3", expression);
        double result = ExpressionEvaluator.evaluate(expression);
        assertTrue(new Double("3.79").equals(result));

        inputString = "one plus two times three";
        expression = ExpressionGenerator.getMathEquivalent(inputString);
        assertEquals("1 + 2 * 3", expression);
        result = ExpressionEvaluator.evaluate(expression);
        assertTrue(new Double("7").equals(result));

        inputString = "four minus eight plus six times nine";
        expression = ExpressionGenerator.getMathEquivalent(inputString);
        assertEquals("4 - 8 + 6 * 9", expression);
        result = ExpressionEvaluator.evaluate(expression);
        assertTrue(new Double("50").equals(result));

        inputString = "seven over nine plus two";
        expression = ExpressionGenerator.getMathEquivalent(inputString);
        assertEquals("7 / 9 + 2", expression);
        result = ExpressionEvaluator.evaluate(expression);
        assertTrue(new Double("2.78").equals(result));
    }

    @Test
    public void testMixedCaseInput(){
        final String inputString = "One plus two TIMES thrEE";
        final String expression = ExpressionGenerator.getMathEquivalent(inputString);
        assertEquals("1 + 2 * 3", expression);
        double result = ExpressionEvaluator.evaluate(expression);
        assertTrue(new Double("7").equals(result));
    }

    @Test(expected = ArithmeticException.class)
    public void testDivideByZero() {
        final String inputString = "two divided_by zero";
        final String expression = ExpressionGenerator.getMathEquivalent(inputString);
        assertEquals("2 / 0", expression);
        double result = ExpressionEvaluator.evaluate(expression);
    }

    @Test
    public void testCornerCase() {
        final String inputString = "ten plus two";
        final String expression = ExpressionGenerator.getMathEquivalent(inputString);
        assertEquals("10 + 2", expression);
        final double result = ExpressionEvaluator.evaluate(expression);
        assertTrue(new Double("12").equals(result));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidTokenCount() {
        final String inputString = "nine over eight three";
        final String expression = ExpressionGenerator.getMathEquivalent(inputString);
        assertEquals("9 / 8 3", expression);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidToken() {
        final String inputString = "twenty divided_by two";
        final String expression = ExpressionGenerator.getMathEquivalent(inputString);
        assertNull(expression);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyInput(){
        final String inputString = " ";
        final String expression = ExpressionGenerator.getMathEquivalent(inputString);
        assertNull(expression);
    }
}
