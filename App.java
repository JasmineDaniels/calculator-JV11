import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        while (true) {
            final Scanner inputScanner = new Scanner(System.in);
            Calculator calculator = new Calculator();

            Map<String, String> choices = menu(inputScanner);
            String operation = "";
            double num1 = getDouble(choices.get("num1"));
            double num2 = getDouble(choices.get("num2"));

            if (choices.get("operation").equals("+")) {
                operation = "add";
            } else if (choices.get("operation").equals("-")) {
                operation = "subtract";
            } else if (choices.get("operation").equals("x")) {
                operation = "multiply";
            } else if (choices.get("operation").equals("/")) {
                operation = "divide";
            } else {
                operation = choices.get("operation");
            }

            System.out.println(String.format("You want to %s %.0f and %.0f? Y/N", operation, num1, num2));
            String consent = inputScanner.nextLine().toLowerCase();

            if (consent.equals("n")) {
                System.out.println("GoodBye");
            } else if (consent.equals("y")) {
                evaluate(calculator, operation, num1, num2);
            } else {
                System.out.println("Invalid Input");
            }

            System.out.println("Would you like to continue? Y/N");
            String close = inputScanner.nextLine().toLowerCase();

            if (close.equals("n")) {
                System.out.println("GoodBye");
                inputScanner.close();
                return;
            }
        }
    }

    private static Map<String, String> menu(Scanner scannerObj) throws NumberFormatException {
        Map<String, String> map = new HashMap<>();
        System.out.println("Welcome To Calculator App");
        System.out.println("Please Enter a number i.e. 6");
        String num1 = scannerObj.nextLine();
        System.out.println("Enter another number i.e. 10");
        String num2 = scannerObj.nextLine();
        System.out.println("Enter an operation (i.e. +, -, x, /)");
        String operation = scannerObj.nextLine().toLowerCase();

        try {
            Double.parseDouble(num1);
            Double.parseDouble(num2);
        } catch (NumberFormatException e) {
            scannerObj.close();
            throw new NumberFormatException("Invalid Input - NaN");
        }

        map.put("num1", num1);
        map.put("num2", num2);
        map.put("operation", operation);
        return map;
    }

    private static void evaluate(Calculator calculator, String operation, double a, double b) {
        switch (operation) {
            case "multiply":
                System.out.println(String.format("The result of multiplying %.0f and %.0f is: %.0f", a, b,
                        calculator.multiplyNums(a, b)));
                break;
            case "add":
                System.out.println(String.format("The result of adding %.0f and %.0f is: %.0f", a, b,
                        calculator.addNums(a, b)));
                break;
            case "subtract":
                System.out.println(String.format("The result of subtracting %.0f and %.0f is: %.0f", a, b,
                        calculator.subtractNums(a, b)));
                break;
            case "divide":
                if (b == 0) {
                    System.out.println("Cannot divide by zero");
                    break;
                }
                System.out.println(String.format("The result of dividing %.0f and %.0f is: %.0f", a, b,
                        calculator.divideNums(a, b)));
                break;
            default:
                System.out.println(operation + " is an Invalid Operation");
                break;
        }
    }

    private static double getDouble(String prompt) {
        return Double.valueOf(prompt);
    }
}