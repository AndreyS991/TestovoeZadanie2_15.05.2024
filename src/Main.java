import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        System.out.println("Введите выражение:");
        String exp = scn.nextLine();
        char action;
        String[] data;

        if (!exp.matches("\"[^\"]{1,10}\" [\\+\\-\\*/] \"[^\"]{1,10}\"") && !exp.matches("\"[^\"]{1,10}\" \\* ([1-9]|10)") && !exp.matches("\"[^\"]{1,10}\" / \\d+")) {
            throw new IllegalArgumentException("Некорректный формат ввода или значения вне допустимого диапазона");
        }

        if (exp.contains(" + ")) {
            data = exp.split(" \\+ ");
            action = '+';
        } else if (exp.contains(" - ")) {
            data = exp.split(" - ");
            action = '-';
        } else if (exp.contains(" * ")) {
            data = exp.split(" \\* ");
            action = '*';
        } else if (exp.contains(" / ")) {
            data = exp.split(" / ");
            action = '/';
        } else {
            throw new IllegalArgumentException("Некорректный знак действия");
        }

        data[0] = data[0].replace("\"", "");
        if (action != '*') {
            data[1] = data[1].replace("\"", "");
        }

        String result = performOperation(data, action);
        printInQuotes(result);
    }

    static String performOperation(String[] data, char action) {
        switch (action) {
            case '+':
                return data[0] + data[1];
            case '-':
                if (data[0].contains(data[1])) {
                    return data[0].replace(data[1], "");
                }
                return data[0];
            case '*':
                int multiplier = Integer.parseInt(data[1]);
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < multiplier; i++) {
                    sb.append(data[0]);
                }
                return sb.toString();
            case '/':
                int divisor = Integer.parseInt(data[1]);
                if (divisor == 0) {
                    throw new ArithmeticException("Деление на ноль");
                }
                return data[0].substring(0, data[0].length() / divisor);
            default:
                throw new IllegalArgumentException("Некорректный знак действия");
        }
    }

    static void printInQuotes(String text) {
        if (text.length() > 40) {
            text = text.substring(0, 40) + "...";
        }
        System.out.println("\"" + text + "\"");
    }
}