public class Calculator {

    private final int MONTHS;
    private final double PERCENT_PER_MONTH;
    private final double PAYMENT;

    private double startBalance;
    private double endBalance;
    private double monthlyPrincipal;
    private double monthlyInterest;

    public Calculator(double loanAmount, int years, double interestRate, double extraPayment) {
        this.MONTHS = years * 12;
        this.PERCENT_PER_MONTH = interestRate / 1200;
        this.startBalance = loanAmount;
        this.PAYMENT = calcPayment() + extraPayment;
    }

    public void calcMortgage() {
        int count = 0;
        double interestAmount = 0;
        System.out.printf("%-12s %-18s %-15s %-15s %-18s %-24s %-15s%n", "МЕСЯЦ", "ЗАДОЛЖЕННОСТЬ", "ПЛАТЁЖ", "ПРОЦЕНТ БАНКА",
                "ИТОГОВЫЙ ПЛАТЁЖ", "ОСТАТОК ЗАДОЛЖЕННОСТИ", "ОБЩАЯ СУММА С ПРОЦЕНТОВ");
        while (count++ < MONTHS) {
            endBalance = monthEndBalance();
            if (startBalance > monthlyPrincipal) {
                interestAmount += monthlyInterest;
                System.out.printf("%-12d %-18.2f %-15.2f %-15.2f %-18.2f %-24.2f %-13.2f%n", count, startBalance,
                        PAYMENT, monthlyInterest, monthlyPrincipal, endBalance, interestAmount);
            } else {
                double lastPayment = startBalance;
                endBalance = startBalance - lastPayment;
                System.out.printf("%-12d %-18.2f %-15.2f %-15.2f %-18.2f %-24.2f %-13.2f%n", count, startBalance,
                        lastPayment, endBalance, lastPayment, endBalance, interestAmount);
                return;
            }
        }
    }

    private double monthStartBalance() {
        return (endBalance == 0) ? startBalance : endBalance;
    }

    private double calcPayment() {
        return (startBalance * PERCENT_PER_MONTH) / (1 - Math.pow(1 + PERCENT_PER_MONTH, -MONTHS));
    }

    private double monthEndBalance() {
        startBalance = monthStartBalance();
        return startBalance - monthlyPrincipal();
    }

    private double monthlyPrincipal() {
        monthlyPrincipal = PAYMENT - monthlyInterest();
        return monthlyPrincipal;
    }

    private double monthlyInterest() {
        monthlyInterest = startBalance * PERCENT_PER_MONTH;
        return monthlyInterest;
    }

    public double getPAYMENT() {
        return PAYMENT;
    }
}
