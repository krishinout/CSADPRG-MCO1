import java.util.*;

/**
 * Serves as the GUI, handling all user interactions, displaying the main menu
 * and transaction screens, and input validation.
 * Delegates logic to the Account class, interest logic to the Interest class,
 * and currency conversion to the ExchangeRate class.
 */
public class MainMenu{
    private Scanner sc = new Scanner(System.in);
    private Account account; // null until registered
    private ExchangeRate exchangeRate = new ExchangeRate();

    /**
     * Runs the main menu loop
     */
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

    /**
     * Displays the main menu options
     */
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

    /**
     * Reads the input and continues to prompt until it is within the given range (hi, lo).
     * @param hi represents the highest acceptable value.
     * @param lo represents the lowest acceptable value.
     * @param prompt represents the prompt shown to the user.
     * @return a value integer/choice within the given range.
     */
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

    /**
     * Clears the console using the ANSI escape codes
     */
    public static void clearScreen(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * Simulates account registration with validation.
     */
    public void registerAccount(){

        String name;
        // Input validation of names.
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

    /**
     * Asks whether to return to the main menu and loops until valid input (Y/N).
     */
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

    /** 
     * Simulates the deposit transaction.
     * Reads amount and updates balance accordingly.
     */
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

    /**
     * Validating for amount entered by user, continues to prompt until a numerical
     * and positive value is inputted.
     */
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

    /**
     * Similar to readAmount, validating for integer entered by user. Continues
     * to prompt until a positive integer is inputted.
     * @param prompt represents the prompt shown to user.
     * @return the valid positive integer.
     */
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

    /** 
     * Simulates the withdraw transaction.
     * Reads amount and updates balance accordingly.
     */
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

    /**
     * Sets a rate for a foreign currency.
     */
    public void recordExchange(){
        System.out.println("Record Exchange Rate");
        System.out.println();
        showCurrencies();

        int choice = ifChoiceValid(6, 1, "Select Foreign Currency: ");

        // Cannot update PHP as it is the base currency
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

    /**
     * Converts a menu number to its Currency enum Value.
     * @param choice represents the choice number (1-6).
     * @return the corresponding Currency.
     */
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

    /**
     * Displays the currency selection list.
     */
    public void showCurrencies(){
        System.out.println("[1] Philippine Peso (PHP)");
        System.out.println("[2] United States Dollar (USD)");
        System.out.println("[3] Japanese Yen (JPY)");
        System.out.println("[4] British Pound Sterling (GBP)");
        System.out.println("[5] Euro (EUR)");
        System.out.println("[6] Chinese Yuan Renminbi (CNY)");
        System.out.println();
    }

    /**
     * Simulates conversion from source currency to target currency.
     */
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

             // Implementation of a checker in case no value is set to the chosen currency.
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

            if(willConvert){
                clearScreen();
            }
        }   

        exit();
    }

    /**
     * Computes and displays the daily interest table.
     */
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

