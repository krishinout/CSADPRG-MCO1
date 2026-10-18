import java.util.*;

public class MainMenu{
    Scanner sc = new Scanner(System.in);
    private Account account;
    private ExchangeRate exchangeRate = new ExchangeRate();
    private List<Interest.DailyEntry> dailyInterest = new ArrayList<>();

    // the main menu screen with validation checker
    public void mainMenu(){
        while(true){
            showMenu();
            int choice = ifChoiceValid(6, 0, "Choice: ");

            switch (choice) {
                case 0: return;
                case 1: registerAccount();
                    break;
                case 2: depositAmount();
                    break;
                case 3: withdrawAmount();
                    break;
                case 4: exchangeCurrency();
                    break;
                case 5: recordExchange();
                    break;
                case 6: showInterest(); 
                    break;
            }
        }
    }

    public void showMenu(){
        System.out.println("\n===================================");
        System.out.println("Select Transaction:");
        System.out.println("[1] Register Account Name");
        System.out.println("[2] Deposit Amount");
        System.out.println("[3] Withdraw Amount");
        System.out.println("[4] Currency Exchange");
        System.out.println("[5] Record Exchange Rates");
        System.out.println("[6] Show Interest Amount");
        System.out.println("[0] Exit");
    }

    public int ifChoiceValid(int hi, int lo, String prompt){

        while(true){
            try {
            System.out.print(prompt);
            int choice = sc.nextInt();
            sc.nextLine();

            if(choice >= lo && choice <= hi){
                return choice;
            }
            
            System.out.println("Error: Invalid choice");

            } catch (InputMismatchException e) {
                System.out.println("Error: Invalid choice");
                sc.nextLine();
            }
        }
    }

    public void registerAccount(){

        String name;

        // to avoid invalid inputs of names
        do { 
            System.out.println("Register Account Name");
            System.out.print("Account Name: ");
            name = sc.nextLine();

            if (name.equals("")){
                System.out.print("Error: Invalid Name Registered");
            }
            
        } while (name.equals(""));
        
        account = new Account(name);
        System.out.println();
        
        exit();

    }

    public void exit(){
        while (true){
            System.out.print("Back to the Main Menu (Y/N): " );
            String choice = sc.nextLine().trim().toUpperCase();

            if(choice.equals("N")){
                continue;
            }
            if(choice.equals("Y")){
                break;
            }

            System.out.println("Error: Invalid choice");
        }
    }


    public void depositAmount(){
        System.out.println("Deposit Amount");
        System.out.print("Account Name: ");
        String name = sc.nextLine();

        if (!name.equals(account.getAccountName())){
            System.out.println("Error: Account name does not match records.");
        } else{
            System.out.printf("Current Balance: %.2f\n", account.getBalance());
            System.out.println("Currency: PHP");

            double depAmount = readAmount("Deposit Amount: ");

            account.deposit(depAmount);
            System.out.printf("Updated Balance: %.2f\n", account.getBalance());
        }

       exit();
    }


    public double readAmount(String prompt){
        while(true){
            try {
                System.out.print(prompt);
                double amount = sc.nextDouble();
                sc.nextLine();

                if(amount > 0){
                    return amount;
                }

                System.out.println("Error: Amount must be positive");

            } catch (InputMismatchException e) {
                System.out.println("Error: Invalid Amount");
                sc.nextLine();
            }
        }
    }


    public void withdrawAmount(){
        System.out.println("Withdraw Amount");
        System.out.print("Account Name: ");
        String name = sc.nextLine();
        
        if (!name.equals(account.getAccountName())){
            System.out.println("Error: Account name does not match records.");
        } else{
            System.out.printf("Current Balance: %.2f\n", account.getBalance());
            System.out.println("Currency: PHP");

            double witAmount = readAmount("Withdraw Amount: ");

            if (witAmount > account.getBalance()){
                System.out.println("Error: Insufficient Funds");
            } else{
                account.withdraw(witAmount);
                System.out.printf("Updated Balance: %.2f\n", account.getBalance());
            }
        }

       exit();
    }

    public void recordExchange(){
        System.out.println("Record Exchange Rate");
        System.out.println();
        showCurrencies();

        int choice = ifChoiceValid(6, 1, "Select Foreign Currency: ");

        if(choice == 1){
            System.out.println("PHP is base currency. Its rate is fixed at 1.00");
        } else{
            double rate = readAmount("Exchange Rate: ");
            Currency currency = choiceToCurrency(choice);
            exchangeRate.setRate(currency, rate);
        }

        exit();
    }

    public Currency choiceToCurrency(int choice){
        switch(choice){
            case 1: return Currency.PHP;
            case 2: return Currency.USD;
            case 3: return Currency.JPY;
            case 4: return Currency.GBP;
            case 5: return Currency.EUR;
            case 6: return Currency.CNY;
            default: return Currency.PHP;
        }
    }

    public void showCurrencies(){
        System.out.println("[1] Philippine Peso (PHP)");
        System.out.println("[2] United States Dollar (USD)");
        System.out.println("[3] Japanese Yen (JPY)");
        System.out.println("[4] British Pound Sterling (GBP)");
        System.out.println("[5] Euro (EUR)");
        System.out.println("[6] Chinese Yuan Renminni (CNY)");
        System.out.println();
    }

    public void currencyExchange(){
        boolean willConvert = true;

        while(willConvert){
            System.out.println("Foreign Currency Exchange");
            System.out.println("Source Currency Option:");
            showCurrencies();

            int c1 = ifChoiceValid(6, 1, "Source Currency: ");
            Currency sourceCurr = choiceToCurrency(c1);
            double sourceAmt = readAmount("Source Amount: ");
            System.out.println();

            System.out.println("Exchanged Currency Options:");
            showCurrencies();
            int c2 = ifChoiceValid(6, 1, "Exchange Currency: ");
            Currency exchangeCurr = choiceToCurrency(c2);

            if(!exchangeRate.ifRateExists(sourceCurr)){
                System.out.println("Error: No rate set for " + sourceCurr.getCode());
            } else if(!exchangeRate.ifRateExists(exchangeCurr)){
                System.out.println("Error: No rate set for " + exchangeCurr.getCode());
            } else{
                System.out.printf("Exchange Amount: %.2f\n", exchangeRate.convert(sourceAmt, sourceCurr, exchangeCurr));
            }

            boolean convertAgain = false;

            while(!convertAgain){
                System.out.print("Convert another currency (Y/N)? . . . ");
                String choice = sc.nextLine().trim().toUpperCase();

                if(choice.equals("Y")){
                    willConvert = true;
                } else if(choice.equals("N")){
                    willConvert = false;
                    convertAgain = true;
                } else{
                    System.out.println("Error: Invalid Choice");
                }
            }
        }   

    }

    public void showInterest(){
        System.out.println("Show Interest Amount");
        System.out.println("Account Name: " + account.getAccountName());
        System.out.printf("Current Balance %.2f\n", account.getBalance());
        System.out.println("Currency: PHP");
        System.out.println();

        System.out.print("Total Number of Days: ");
        int days = sc.nextInt();
        sc.nextLine();

        System.out.println("Day | Interest | Balance |");

        Interest interest = new Interest();
        List<Interest.DailyEntry> entries = interest.ExpectedDailyInterest(days, account.getBalance());
        
        for(Interest.DailyEntry e: entries){
            System.out.printf("\n", e.day(), e.interest(), e.balance());
        }

        exit();

    }
}

