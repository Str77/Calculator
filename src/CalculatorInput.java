import java.util.InputMismatchException;
import java.util.Scanner;

public class CalculatorInput {

    private static int family;
    private static double summary;

    public static void verificationInBank(Scanner scanner) {
        Calculator calculator = enteredData(scanner);
        if (calculator == null)
            return;
        double percent;
        switch (family) {
            case 1:
                percent = 0.5;
                break;
            case 2:
                percent = 0.45;
                break;
            case 3:
                percent = 0.35;
                break;
            case 4:
                percent = 0.30;
                break;
            default:
                percent = 0.25;
        }
        if (!(calculator.getPAYMENT() > summary * percent)) {
            System.out.println("\nПоздравляем! Ваш кредит рассчитан по месяцам!\n" +
                    "=======================================================================================\n\n");
            scanner.close();
            calculator.calcMortgage();
        } else
            System.out.println("Извините, банк не может выдать кредит на этих условия");
    }

    private static Calculator enteredData(Scanner scanner) {
        System.out.println("КАЛЬКУЛЯТОР КРЕДИТА\n");
        while (true) {
            System.out.println("Для выхода используйте любое отрицательное значение");
            try {
                System.out.println("Введите количество членов вашей семьи:");
                family = (int) checkValue(scanner.nextInt());
                System.out.println("Введите ваш общий семейный доход:");
                summary = checkValue(scanner.nextDouble());
                System.out.println("Введите объём кредита:");
                double loanAmount = checkValue(scanner.nextDouble());
                System.out.println("Введите количество лет на которое оформляйте кредит:");
                int years = (int) checkValue(scanner.nextInt());
                System.out.println("Введите ежегодный процент под который банк выдает кредит:");
                double interestRate = checkValue(scanner.nextDouble());
                System.out.println("Введите дополнительный ежемесячный платеж (опционально)");
                double extraPayment = checkValue(scanner.nextDouble());
                return new Calculator(loanAmount, years, interestRate, extraPayment);
            } catch (InputMismatchException e) {
                System.out.println("Ошибка ввода: " +
                        (e.getMessage() == null ? "недопустимое значение" : e.getMessage()) +
                        "\n\n=========================================================\n");
                scanner.nextLine();
            } catch (IllegalArgumentException e) {
                System.out.println("Выход из программы\n");
                break;
            }
        }
        return null;
    }

    private static double checkValue(Number value) throws InputMismatchException {
        if (value.intValue() >= 0) {
            if (value instanceof Integer && value.intValue() == 0)
                throw new InputMismatchException("данное значение не может быть меньше 1");
            else
                return value.doubleValue();
        } else
            throw new IllegalArgumentException();
    }
}
