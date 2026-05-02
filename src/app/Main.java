package app;

import java.util.Scanner;

public class Main {

    static void main(String[] args){
        double balance = getBalance();
        validateAmount(balance, getAmount(balance));
    }

    private static double getBalance(){
        return 1000.00;
    }

    private static double getAmount(double balance){
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.printf("Balance is USD %.2f%nEnter purchase amount, USD: ", balance);
            try {
                if(!scanner.hasNextDouble()){
                    throw new InputException("Invalid input. Try again!\n");
                }

                double purchaseAmount = scanner.nextDouble();

                if(purchaseAmount <= 0){
                    throw new InputException("The purchase amount cannot be less than zero or equal to zero.\nTry again!\n");
                }

                return purchaseAmount;
            } catch (InputException ex) {
                System.out.println(ex.getMessage());
                scanner.nextLine();
            }
        }
    }

    private static void validateAmount(double balance, double withdrawal) {
        if(withdrawal > balance){
            try {
                throw new FundsException("Insufficient funds!");
            } catch (FundsException ex) {
                System.out.println(ex.getMessage());
            }
        } else {
            balance = calculateBalance(balance, withdrawal);
            System.out.printf("Funds are OK. Purchase paid.%nBalance is USD %.2f", balance);
        }
    }

    private static double calculateBalance(double balance, double withdrawal){
        return balance - withdrawal;
    }
}
