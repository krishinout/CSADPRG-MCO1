import java.util.*;

public class MainMenu{
    private Scanner sc = new Scanner(System.in);
    private Account account;
    private ExchangeRate exchangeRate = new ExchangeRate();

    // the main menu screen with validation checker
    public void mainMenu(){
        clearScreen();
        while(true){
            showMenu();
            int choice = ifChoiceValid(6, 0, "Choice: ");

            if(choice == 0)
                return;

            clearScreen();

            switch (choice) {
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

            clearScreen();
        }
    }

    public void showMenu(){
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

    public static void clearScreen(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void registerAccount(){

        String name;
        // to avoid invalid inputs of names
        do { 
            System.out.println("Register Account Name");
            System.out.print("Account Name: ");
            name = sc.nextLine().trim();

            if (name.isEmpty()){
                System.out.println("Error: Invalid Name Registered");
            }
            
        } while (name.isEmpty());
        
        account = new Account(name);
        System.out.println();
        
        exit();

    }

    public void exit(){
        while (true){
            System.out.print("Back to the Main Menu (Y/N): " );
            String choice = sc.nextLine().trim().toUpperCase();

            if(choice.equals("N") || choice.equals("n")){
                continue;
            }
            if(choice.equals("Y") || choice.equals("y")){
                break;
            }

            System.out.println("Error: Invalid choice");
        }
    }


    public void depositAmount(){

        if (account == null){
            System.out.println("Error: No account registered");
            exit();
            return;
        } 

        System.out.println("Deposit Amount");
        System.out.println("Account Name: " + account.getAccountName());
        System.out.printf("Current Balance: %.2f\n", account.getBalance());
        System.out.println("Currency: PHP");
        System.out.println();

        double depAmount = readAmount("Deposit Amount: ");
        account.deposit(depAmount);
        System.out.printf("Updated Balance: %.2f\n", account.getBalance());
        System.out.println();

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

    public int readInt(String prompt){
        while(true){
            try{
                System.out.print(prompt);
                int value = sc.nextInt();
                sc.nextLine();

                if(value > 0){
                    return value;
                }

                System.out.println("Error: Input must be positive");
            } catch (InputMismatchException e){
                System.out.println("Error: Invalid Input");
                sc.nextLine();
            }
        }
    }


    public void withdrawAmount(){

         if (account == null){
            System.out.println("Error: No account registered");
            exit();
            return;
        }

        System.out.println("Withdraw Amount");
        System.out.println("Account Name: " + account.getAccountName());
        System.out.printf("Current Balance: %.2f\n", account.getBalance());
        System.out.println("Currency: PHP");
        System.out.println();
        
        while (true){
            double witAmount = readAmount("Withdraw Amount: ");

            if (witAmount > account.getBalance()){
            System.out.println("Error: Insufficient Funds");
            continue;
            } 

            account.withdraw(witAmount);
            System.out.printf("Updated Balance: %.2f\n", account.getBalance());
            break;
        }
        System.out.println();

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
        System.out.println();

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
        System.out.println("[6] Chinese Yuan Renminbi (CNY)");
        System.out.println();
    }

    public void exchangeCurrency(){
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
            System.out.println();

            while(!convertAgain){
                System.out.print("Convert another currency (Y/N)? . . . ");
                String choice = sc.nextLine().trim().toUpperCase();

                if(choice.equals("Y")){
                    willConvert = true;
                    convertAgain = true;

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

         if (account == null){
            System.out.println("Error: No account registered");
            exit();
            return;
        }

        System.out.println("Show Interest Amount");
        System.out.println("Account Name: " + account.getAccountName());
        System.out.printf("Current Balance %.2f\n", account.getBalance());
        System.out.println("Currency: PHP");
        System.out.println();

        int days = readInt("Total Number of Days: ");

        System.out.println("Day | Interest | Balance |");

        List<Interest.DailyEntry> entries = account.dailyInterest(days);
        
        for(Interest.DailyEntry e: entries){
            System.out.printf("%d  |  %.2f  |  %.2f%n", e.day(), e.interest(), e.balance());
        }

        exit();

    }
}

