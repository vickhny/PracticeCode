import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class MathematicalExpressionParse {

    public static void main(String[] args) {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Enter a string that you want to calculate the value of: \n");
            String infixExp = bufferedReader.readLine();

            while (!infixExp.equals("q")) {
                final InfixToPostfix infixToPostfix = new InfixToPostfix();
                final String postfixExpWithUnderscore = infixToPostfix.convertToPostfix(infixExp);
                System.out.println(postfixExpWithUnderscore);
                System.out.println("Result: " + calculate(postfixExpWithUnderscore));

                infixExp = bufferedReader.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static double calculate(String postfixExpWithUnderscore) {
        Stack<Double> operandStack = new Stack<>();

        boolean isUnderscore = false;
        StringBuilder numberString = new StringBuilder();

        for (int i = 0; i < postfixExpWithUnderscore.length(); i++) {

            if (postfixExpWithUnderscore.charAt(i) == '_') {
                isUnderscore = true;
                if (i > 0 && numberString.length() > 0)
                    operandStack.push(Double.parseDouble(numberString.toString()));
                numberString.delete(0, numberString.length());
                continue;
            }
            final boolean isOperator = InfixToPostfix.isOperator(postfixExpWithUnderscore.charAt(i));
            if (isUnderscore) {
                if (!isOperator) {
                    numberString.append(postfixExpWithUnderscore.charAt(i));
                    continue;
                } else {
                    operandStack.push(Double.parseDouble(numberString.toString()));
                    numberString.delete(0, numberString.length());
                    isUnderscore = false;
                }
            }
            if (isOperator) {
                final double res = evaluate(operandStack.pop(), operandStack.pop(), postfixExpWithUnderscore.charAt(i));
                operandStack.push(res);
            }
        }

        return operandStack.pop();
    }

    private static double evaluate(Double second, Double first, char operator) {
        switch (operator) {
            case '+':
                return first + second;
            case '-':
                return first - second;
            case '*':
                return first * second;
            case '/':
                return first / second;
            case '^':
                return Math.pow(first, second);
            default:
                throw new IllegalArgumentException("Operator not defined for " + operator);

        }
    }

}

class InfixToPostfix {

    private final Stack<Character> operatorStack = new Stack<>();

    /**
     * checks if the first character has higher precedence compared to the second
     *
     * @return true if the fist character has higher precedence than the second, otherwise false
     */
    public static boolean hasHigherPrecedence(Character first, Character second) {

        if (first == null || second == null) {
            throw new UnsupportedOperationException("The characters cannot be null!");
        }

        return priorityLevelOfOperator(first) - priorityLevelOfOperator(second) >= 0;
    }

    private static byte priorityLevelOfOperator(Character c) {
        switch (c) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
        }
        return -1;
    }

    public static boolean isOperator(char c) {
        return c == '-' || c == '+' || c == '*' || c == '/' || c == '^';
    }

    /**
     * puts an underscore '_' at the beginning of each word that is not a operator, so as to make it easy to identify a number
     * e.g 25*34-(23+1) becomes _25*_34-(_23+_1)
     */
    public static String enhance(String input) {
        return input.replaceAll("(\\w+)", "_$1");
    }

    private boolean isLeftParenthesis(Character c) {
        return c == '(' ||
                c == '{' ||
                c == '[';
    }

    private boolean isRightParenthesis(Character c) {
        return c == ')' ||
                c == '}' ||
                c == ']';
    }

    public String convertToPostfix(String infixExpression) {

        final String enhanced = enhance(infixExpression);

        final StringBuilder resultStringBuilder = new StringBuilder();
        for (int i = 0; i < enhanced.length(); i++) {
            final char c = enhanced.charAt(i);
            if (c == '_')
                resultStringBuilder.append(c);
            if (Character.isLetterOrDigit(c))   // if the character is an operand add it to the output
                resultStringBuilder.append(c);
//            if the token is an operator or a
            else if (isLeftParenthesis(c)) {
                if (i > 0 && (!isOperator(enhanced.charAt(i - 1)) && !isLeftParenthesis(enhanced.charAt(i - 1))))
                    throw new IllegalArgumentException("There should be an operator before a left parentheses, at position: " + i + " of string: " + enhanced);
                operatorStack.push(c);
            } else if (isOperator(c)) {
                if (i == 0 || i == enhanced.length() - 1)
                    throw new IllegalArgumentException("The operator should not be at the start or end of the expression, at position: " + i);
                if (isOperator(enhanced.charAt(i + 1)))
                    throw new IllegalArgumentException("Two operators should not occur consecutively, at position: " + i);
                while (!operatorStack.isEmpty() && !isLeftParenthesis(operatorStack.peek()) && hasHigherPrecedence(operatorStack.peek(), c))
                    resultStringBuilder.append(operatorStack.pop());
                operatorStack.push(c);
            } else if (isRightParenthesis(c)) {
                if (i > 0 && isOperator(enhanced.charAt(i - 1)))
                    throw new IllegalArgumentException("There should not be an operator, at position: " + (i - 1));
                while (!operatorStack.isEmpty() && !isLeftParenthesis(operatorStack.peek()))
                    resultStringBuilder.append(operatorStack.pop());
                final Character poppedChar = operatorStack.pop();
                if (!isLeftParenthesis(poppedChar)) {
                    throw new IllegalArgumentException("The expression might be malformed!");
                }
            }
        }

        while (!operatorStack.isEmpty()) {
            final Character pop = operatorStack.pop();
            if (isLeftParenthesis(pop))
                throw new IllegalArgumentException("The expression might contain extra left parentheses");
            resultStringBuilder.append(pop);
        }
        return resultStringBuilder.toString();

    }

}