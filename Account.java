
/**
 * Class represents the account of the user
 * contains name, balance, and current currency (automatically php)
 */

public class Account{
    // Attributes
    private String accountName;
    private double balance;
    private Currency currency = Currency.PHP;
    private Interest interest = new Interest();

    public Account(String accountName){
        this.accountName = accountName;
        this.balance = 0.0;
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
    public boolean withdraw(double drawAmt){

        if(drawAmt > this.balance){
            return false;
        }

        this.balance -= drawAmt;
        return true;
    }

    // interest computations
    public List<Interest.DailyEntry> dailyInterest(int days){

        return interest.ExpectedDailyInterest(days, balance);

    }

    public double computeAnnualInterest(){
        return interest.annualInterest(balance);
    }



} 