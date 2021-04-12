import java.util.InputMismatchException;
import java.util.Scanner;

class Lab3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String userInput = "";
        System.out.println("Hello, Calculator is ok");
        while (!"q".equals(userInput)) {
            try {
                System.out.println("Input Number1 Number2 and operations");
                int firstNumber = scanner.nextInt();
                int secondNumber = scanner.nextInt();
                userInput = scanner.nextLine();
                double result = 0;
                switch (userInput) {
                    case " +":
                        result = plus(firstNumber, secondNumber);
                        System.out.println("Plus is ok");
                        break;
                    case " -":
                        result = minus(firstNumber, secondNumber);
                        System.out.println("Minus is ok");
                        break;
                    case " *":
                        result = ymn(firstNumber, secondNumber);
                        System.out.println("multiplication is ok");
                        break;
                    case " /":
                        if (secondNumber == 0) {
                            continue;
                        }
                        result = del(firstNumber, secondNumber);
                        System.out.println("division is ok");
                        break;
                    case " !":
                        result = fact(firstNumber);
                        System.out.println("Factorial is ok");
                        break;
                    default:
                        break;
                }
                System.out.println(result);
            } catch (InputMismatchException ex) {
                System.out.println("Wrong input data!");
                break;
            }
        }
        System.out.println("The calculator is finished");
    }

    private static int plus(int numberOne, int numberTwo) {
        return numberOne + numberTwo;
    }

    private static int minus(int numberOne, int numberTwo) {
        return numberOne - numberTwo;
    }

    private static int ymn(int numberOne, int numberTwo) {
        return numberOne * numberTwo;
    }

    private static int del(int numberOne, int numberTwo) {
        return numberOne / numberTwo;
    }

    private static int fact(int numberOne) {
        int res = 1;
        for (int i = 1; i <= numberOne; i++) {
            res *= i;
        }

        return res;
    }

}
