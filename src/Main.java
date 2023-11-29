import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Welcome to the Restricted Calculator!");
            System.out.println("Enter your arithmetic expression or type 'exit' to exit:");

            while (true) {
                System.out.print("Expression: ");
                String userInput = scanner.nextLine();

                if (userInput.equalsIgnoreCase("exit")) {
                    System.out.println("Exiting Calculator. Goodbye!");
                    break;
                }

                int result = calculateExpression(userInput);
                System.out.println("Result: " + result);
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    private static int calculateExpression(String expression) {
        String[] parts = expression.split("\\s+");

        if (parts.length == 3) {
            int a = validateAndParse(parts[0]);
            int b = validateAndParse(parts[2]);

            switch (parts[1]) {
                case "+":
                    return a + b;
                case "-":
                    return a - b;
                case "*":
                    return a * b;
                case "/":
                    return a / b;
                default:
                    throw new IllegalArgumentException("Invalid operator: " + parts[1]);
            }
        } else if (parts.length == 5) {
            int a = validateAndParse(parts[0]);
            int b = validateAndParse(parts[2]);
            int c = validateAndParse(parts[4]);

            switch (parts[1] + parts[3]) {
                case "+-":
                    return a + b - c;
                case "-+":
                    return a - b + c;
                case "*-":
                    return a * b - c;
                case "/-":
                    return a / b * c;
                default:
                    throw new IllegalArgumentException("Invalid operator: " + parts[1] + parts[3]);
            }
        } else {
            throw new IllegalArgumentException("Invalid expression format");
        }
    }

    private static int validateAndParse(String input) {
        int value;
        try {
            value = Integer.parseInt(input);
            if (value < 1 || value > 10) {
                throw new IllegalArgumentException("Numbers must be between 1 and 10 inclusive.");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid number format: " + input);
        }
        return value;
    }
}
