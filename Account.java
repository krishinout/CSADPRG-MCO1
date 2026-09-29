
/**
 * Class represents the account of the user
 * contains name, balance, and current currency (automatically php)
 */

public class Account{
    // Attributes
    private String accountName;
    private double balance = 0.0;
    private Currency currency = Currency.PHP;

    public Account(String accountName, double balance){
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

    public Currency getCurrency(){
        return currency;
    }

    // deposit function
    public boolean deposit(double depositAmt){

        if(depositAmt <= 0){
            return false;
        }

        this.balance += depositAmt;
        return true;
    }

    // withdraw function
    public boolean transact(double drawAmt){

        if(drawAmt > this.balance){
            return false;
        }

        this.balance -= drawAmt;
        return true;
    }



}