import io.bretty.console.table.Alignment;
import io.bretty.console.table.Table;
import util.ExpressionEvaluator;
import util.ExpressionGenerator;

import java.util.Scanner;

import static util.Constants.*;

public class RunCalculator {

    public static void main(String[] args) {
        final String inputString = getInputString();
        final String mathExpression = ExpressionGenerator.getMathEquivalent(inputString);
        final double result = ExpressionEvaluator.evaluate(mathExpression);
        final Table table = formatResult(inputString, mathExpression, result);
        System.out.println(table);
    }

    private static String getInputString() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("######## Enter the expression ########");
        final String inputString = scanner.nextLine();
        return inputString;
    }

    private static Table formatResult(final String inputString, final String mathExpression, final double result) {
        final Object[] rowHeader = {ROW_HEADING1, ROW_HEADING2, ROW_HEADING3};
        final Object[] rowValues = {inputString, mathExpression, result};
        final Object[][] data = {rowHeader, rowValues};
        return Table.of(data, Alignment.LEFT, inputString.length());
    }
}
