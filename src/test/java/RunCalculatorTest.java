import org.junit.Test;
import util.ExpressionEvaluator;
import util.MathExpressionGenerator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RunCalculatorTest {

    @Test
    public void happyCase() {
        String inputString = "nine over eight plus four times two divided_by three";
        String mathematicalExpression = MathExpressionGenerator.getMathematicalExpression(inputString);
        assertEquals("9 / 8 + 4 * 2 / 3", mathematicalExpression);
        double result = ExpressionEvaluator.evaluate(mathematicalExpression);
        assertTrue(new Double("3.79").equals(result));

        inputString = "one plus two times three";
        mathematicalExpression = MathExpressionGenerator.getMathematicalExpression(inputString);
        assertEquals("1 + 2 * 3", mathematicalExpression);
        result = ExpressionEvaluator.evaluate(mathematicalExpression);
        assertTrue(new Double("7").equals(result));

        inputString = "four minus eight plus six times nine";
        mathematicalExpression = MathExpressionGenerator.getMathematicalExpression(inputString);
        assertEquals("4 - 8 + 6 * 9", mathematicalExpression);
        result = ExpressionEvaluator.evaluate(mathematicalExpression);
        assertTrue(new Double("50").equals(result));

        inputString = "seven over nine plus two";
        mathematicalExpression = MathExpressionGenerator.getMathematicalExpression(inputString);
        assertEquals("7 / 9 + 2", mathematicalExpression);
        result = ExpressionEvaluator.evaluate(mathematicalExpression);
        assertTrue(new Double("2.78").equals(result));
    }

    @Test
    public void checkMixedCaseInput(){
        String inputString = "One plus two TIMES thrEE";
        String mathematicalExpression = MathExpressionGenerator.getMathematicalExpression(inputString);
        assertEquals("1 + 2 * 3", mathematicalExpression);
        double result = ExpressionEvaluator.evaluate(mathematicalExpression);
        assertTrue(new Double("7").equals(result));
    }

    @Test(expected = ArithmeticException.class)
    public void divideByZero() {
        String inputString = "two divided_by zero";
        String mathematicalExpression = MathExpressionGenerator.getMathematicalExpression(inputString);
        assertEquals("2 / 0", mathematicalExpression);
        double result = ExpressionEvaluator.evaluate(mathematicalExpression);
        assertTrue(new Double("3.79").equals(result));
    }

    @Test
    public void cornerCase() {
        String inputString = "ten plus two";
        String mathematicalExpression = MathExpressionGenerator.getMathematicalExpression(inputString);
        assertEquals("10 + 2", mathematicalExpression);
        double result = ExpressionEvaluator.evaluate(mathematicalExpression);
        assertTrue(new Double("12").equals(result));
    }

    @Test(expected = IllegalArgumentException.class)
    public void invalidInputTokens() {
        String inputString = "nine over eight three";
        String mathematicalExpression = MathExpressionGenerator.getMathematicalExpression(inputString);
        assertEquals("9 / 8 3", mathematicalExpression);
        double result = ExpressionEvaluator.evaluate(mathematicalExpression);
        assertTrue(new Double("3.79").equals(result));
    }

    @Test(expected = IllegalArgumentException.class)
    public void illegalInput() {
        String inputString = "twenty divided_by two";
        String mathematicalExpression = MathExpressionGenerator.getMathematicalExpression(inputString);
        assertEquals("null / 2", mathematicalExpression);
        double result = ExpressionEvaluator.evaluate(mathematicalExpression);
        assertTrue(new Double("10").equals(result));
    }
}
