import java.util.*;

public class TextCalculator {
    public static boolean isValid(String expr) {
        int balance = 0;
        for (int i = 0; i < expr.length(); i++) {
            char c = expr.charAt(i);
            if (!Character.isDigit(c) && "+-*/() ".indexOf(c) == -1) return false;
            if (c == '(') balance++;
            if (c == ')') balance--;
            if (balance < 0) return false;
        }
        return balance == 0;
    }

    public static List<String> tokenize(String expr) {
        List<String> tokens = new ArrayList<>();
        int i = 0;
        while (i < expr.length()) {
            char c = expr.charAt(i);
            if (Character.isDigit(c)) {
                int j = i;
                while (j < expr.length() && Character.isDigit(expr.charAt(j))) j++;
                tokens.add(expr.substring(i, j));
                i = j;
            } else if ("+-*/()".indexOf(c) != -1) {
                tokens.add(String.valueOf(c));
                i++;
            } else {
                i++;
            }
        }
        return tokens;
    }

    public static int applyOp(int a, int b, String op) {
        switch (op) {
            case "+": return a + b;
            case "-": return a - b;
            case "*": return a * b;
            case "/": return a / b;
        }
        return 0;
    }

    public static int precedence(String op) {
        if (op.equals("+") || op.equals("-")) return 1;
        if (op.equals("*") || op.equals("/")) return 2;
        return 0;
    }

    public static int evaluateTokens(List<String> tokens, StringBuilder steps) {
        Stack<Integer> values = new Stack<>();
        Stack<String> ops = new Stack<>();
        for (String t : tokens) {
            if (t.matches("\\d+")) {
                values.push(Integer.parseInt(t));
            } else if (t.equals("(")) {
                ops.push(t);
            } else if (t.equals(")")) {
                while (!ops.peek().equals("(")) {
                    int b = values.pop(), a = values.pop();
                    String op = ops.pop();
                    int res = applyOp(a, b, op);
                    values.push(res);
                    steps.append(a + " " + op + " " + b + " = " + res + "\n");
                }
                ops.pop();
            } else {
                while (!ops.isEmpty() && precedence(ops.peek()) >= precedence(t)) {
                    int b = values.pop(), a = values.pop();
                    String op = ops.pop();
                    int res = applyOp(a, b, op);
                    values.push(res);
                    steps.append(a + " " + op + " " + b + " = " + res + "\n");
                }
                ops.push(t);
            }
        }
        while (!ops.isEmpty()) {
            int b = values.pop(), a = values.pop();
            String op = ops.pop();
            int res = applyOp(a, b, op);
            values.push(res);
            steps.append(a + " " + op + " " + b + " = " + res + "\n");
        }
        return values.pop();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter expression: ");
        String expr = sc.nextLine();
        if (!isValid(expr)) {
            System.out.println("Invalid Expression");
        } else {
            List<String> tokens = tokenize(expr);
            StringBuilder steps = new StringBuilder();
            int result = evaluateTokens(tokens, steps);
            System.out.println("\n--- Calculation Steps ---");
            System.out.print(steps.toString());
            System.out.println("Final Result: " + result);
        }
        sc.close();
    }
}
