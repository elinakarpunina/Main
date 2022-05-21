import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.IOException;
// Данный калькулятор, к сожалению, не выбрасывает обработанное исключение при вводе операции, состоящей одновременно из цифр римской и арабской системы исчисления
// Также имеет некоторые недоработки при работе с римскими цифрами
public class MainCalculator {
    static Scanner scan = new Scanner(System.in);
    static int var1, var2;
    static char operation;
    static int result;

    public static void main(String[] args) {
        System.out.println("Что нужно посчитать?: ");
        String userInput = scan.next(); // Считываем строку которую ввёл пользователь
        char[] operation_char = new char[10]; // Создаём пустой символьный массив

        for (int i = 0; i < userInput.length(); i++) { // Заполняем массив символами строки, которую ввел пользователь и по ходу ловим знак операции
            operation_char[i] = userInput.charAt(i);
            if (operation_char[i] == '+') {
                operation = '+';
            }
            if (operation_char[i] == '-') {
                operation = '-';
            }
            if (operation_char[i] == '*') {
                operation = '*';
            }
            if (operation_char[i] == '/') {
                operation = '/';
            }
            if (operation_char[i] == '.') {
                try {
                    throw new IOException();
                } catch (IOException e) {
                    System.out.println("Калькулятор принимает на вход тольцо целые числа");
                    System.exit(0);
                }
                break;
            }
        }

        String oper_charString = String.valueOf(operation_char);
        String[] signs = oper_charString.split("[+-/*]", 2);
        if ((operation != '+') && (operation != '-') && (operation != '/') && (operation != '*')) {
            try {
                throw new IOException();
            } catch (IOException e) {
                System.out.println("Введён не верный оператор");
                System.exit(0);
            }
        } else {
            String stb0 = signs[0];
            String stb1 = signs[1];
            String strings = stb1.trim();

            var1 = romanToNumber(stb0);
            var2 = romanToNumber(strings);

            if (var1 < 0 && var2 < 0) {
                result = 0;
            } else {
                result = calc(var1, var2, operation);

                    if (((var1 <= 10) || (var2 <= 10)) && (result < 1)) {
                        try {
                            throw new ArrayIndexOutOfBoundsException();
                        } catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println("В римской системе исчисления существуют только положительные числа");
                            System.exit(0);
                        }
                    } else {
                        if (((var1 > 10) || (var2 > 10))){
                            try {
                                throw new ArrayIndexOutOfBoundsException();
                            } catch (ArrayIndexOutOfBoundsException e) {
                                System.out.println("Калькулятор принимает на вход только цифры от 1 до 10");
                            }
                        } else{
                        System.out.println("Результат:"); // Для римской системы исчисления
                        String resultRoman = convertNumToRoman(result);

                        System.out.println(stb0 + " " + operation + " " + strings + " = " + resultRoman);
                        System.exit(0);
                    }
                }
            }

            var1 = Integer.parseInt(stb0);
            try {
                var2 = Integer.parseInt(strings);
            } catch (NumberFormatException e) {
                System.out.println("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
                System.exit(0);
            }
            if (((var1 < 1) || (var1 > 10)) || ((var2 < 1) || (var2 > 10))) {
                try {
                    throw new IllegalArgumentException();
                } catch (IllegalArgumentException exception) {
                    System.out.println("Калькулятор принимает на вход только цифры от 1 до 10");
                }
            } else {
                result = calc(var1, var2, operation);
                System.out.println("Результат:"); // Для арабской системы исчисления
                System.out.println(var1 + " " + operation + " " + var2 + " = " + result);
            }
        }
    }






    private static String convertNumToRoman (int numArabian) {
        String[] roman = {"0", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
                "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
                "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
                "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
                "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
                "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
                "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"
        };
        final String s = roman[numArabian];

        return s;
    }


    private static int romanToNumber (String roman) {
        try {
            switch (roman) {
                case "I":
                    return 1;
                case "II":
                    return 2;
                case "III":
                    return 3;
                case "IV":
                    return 4;
                case "V":
                    return 5;
                case "VI":
                    return 6;
                case "VII":
                    return 7;
                case "VIII":
                    return 8;
                case "IX":
                    return 9;
                case "X":
                    return 10;
            }
        } catch (InputMismatchException e) {
            throw new InputMismatchException("Неверный формат данных");
        }
        return -1;
    }

    public static int calc ( int num1, int num2, char op){
        int result = switch (op) {
            case '+' -> num1 + num2;
            case '-' -> num1 - num2;
            case '*' -> num1 * num2;
            case '/' -> num1 / num2;
            default -> 0;
        };
        return result;
    }
}
