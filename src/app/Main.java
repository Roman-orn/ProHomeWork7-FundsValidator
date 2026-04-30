package app;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    static double balance;

    static void main(String[] args){
        balance = getBalance();
        validateAmount(balance, getAmount());

    }

    private static double getBalance(){
        return 1000.00;
    }

    private static double getAmount(){
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.printf("Balance is USD %.2f%nEnter purchase amount, USD: ", balance);
            try {
                return scanner.nextDouble();
            } catch (InputMismatchException ex) {
                System.out.println("Invalid input. Try again!\n");
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
