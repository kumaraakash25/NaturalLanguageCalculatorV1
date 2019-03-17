import util.ExpressionEvaluator;
import util.MathExpressionGenerator;

import java.util.Scanner;

public class RunCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("######## Enter the expression ########");
        final String inputString = scanner.nextLine();
        System.out.println("######## You have entered : " + inputString);
        final String mathematicalExpression = MathExpressionGenerator.getMathematicalExpression(inputString);
        System.out.println("######## Mathematical expression for the input : " + mathematicalExpression);
        final double result = ExpressionEvaluator.evaluate(mathematicalExpression);
        System.out.println("######## Result from the expression evaluation  : " + result);
        System.out.println("######## ######## ######## ########");
    }
}
