import java.util.Scanner;

public class MainCalc {
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        CalculatorInput.verificationInBank(scanner);
        scanner.close();
    }
}
