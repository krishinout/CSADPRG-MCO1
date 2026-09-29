import java.util.*;

public class Account{
    // Attributes
    private String accountName;
    private double balance = 0;
    private Currency currency = Currency.PHP;

    private Account(String accountName, double balance){
        this.accountName = accountName;
        this.balance = balance;
    }

    // Setters and getters
    public void setAccountName(String accountName){
        this.accountName = accountName;
    }

    public String getAccountName(){
        return accountName;
    }

    public double getBalance(){
        return balance;
    }

    // deposit function
    public void deposit(){

        Scanner sc = new Scanner(System.in);

        System.out.println("Account Name: " + accountName);
        System.out.printf("Current Balance: %.2f", balance);
        System.out.println("Currency: " + currency);

        try {
            System.out.println("Deposite Amount: ");
            double amount = sc.nextDouble();
        } catch (InputMismatchException e) {
            amount = 0;
            System.out.println("Transaction Cancelled. Unable to Process Request.");

        } finally {
            System.out.printf("Updated Balance: %.2f\n", balance+amount);
        }

        sc.close();
    }

    // withdraw function


}