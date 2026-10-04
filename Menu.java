import java.util.*;


public class Menu{
    private static final double DEFAULT_BAL = 1000.00;
    private static final String DEFAULT_CURRENCY = "PHP";
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        mainMenu(sc);
        registerAccount(sc);
        depositAmount(sc);
        withdrawAmount(sc);
        recordExchange(sc);
        currencyExchange(sc);
        
    }

    // Main Menu
    public static void mainMenu(Scanner sc){

        System.out.println("Select Transaction:");
        System.out.println("[1] Register Account Name");
        System.out.println("[2] Deposit Amount");
        System.out.println("[3] Withdraw Amount");
        System.out.println("[4] Currency Exchange");
        System.out.println("[5] Record Exchange Rates");
        System.out.println("[6] Show Interest Amount");
        System.out.print("Choice: ");
        int choice = sc.nextInt();
        sc.nextLine();
        System.out.println();

        System.out.println("***");
        System.out.println("Choice = " + choice);
        System.out.println();
    }

    // Register Account
    public static String registerAccount(Scanner sc){

        System.out.println("Register Account Name");
        System.out.print("Account Name: ");
        String name = sc.nextLine();
        System.out.println();
        
        System.out.println("***");
        System.out.println("Account Name = " + name);
        System.out.println();

        return name;
    }


    // Deposit
    public static void depositAmount(Scanner sc){
        System.out.println("Deposit Amount");
        System.out.print("Account Name: ");
        String name = sc.nextLine();
        System.out.printf("Current Balance: %.2f\n", DEFAULT_BAL);
        System.out.println("Currency: " + DEFAULT_CURRENCY);
        System.out.println();
        System.out.print("Deposit Amount: ");
        double depAmount = sc.nextDouble();
        sc.nextLine();
        System.out.println();
        
        System.out.println("***");
        System.out.println("Account Name = " + name);
        System.out.printf("Deposit Amount = %.2f\n", depAmount);
        System.out.println();
    }

    // Withdraw
    public static void withdrawAmount(Scanner sc){

        System.out.println("Withdraw Amount");
        System.out.print("Account Name: ");
        String name = sc.nextLine();
        System.out.printf("Current Balance: %.2f\n", DEFAULT_BAL);
        System.out.println("Currency: " + DEFAULT_CURRENCY);
        System.out.println();
        System.out.print("Withdraw Amount: ");
        double witAmount = sc.nextDouble();
        sc.nextLine();
        System.out.println();

        System.out.println("***");
        System.out.println("Account Name = " + name);
        System.out.printf("Withdraw Amount = %.2f\n", witAmount);
        System.out.println();
    }

    public static void recordExchange(Scanner sc){
        System.out.println("Record Exchange Rate\n");
        System.out.println("[1] Philippine Peso (PHP)");
        System.out.println("[2] United States Dollar (USD)");
        System.out.println("[3] Japanese Yen (JPY)");
        System.out.println("[4] British Pound Sterling (GBP)");
        System.out.println("[5] Euro (EUR)");
        System.out.println("[6] Chinese Yuan Renminni (CNY)");
        System.out.println();

        System.out.print("Select Foreign Currency: ");
        String choice = sc.nextLine();
        System.out.print("Exchange Rate: ");
        double rate = sc.nextDouble();
        sc.nextLine();
        System.out.println();

        System.out.println("***");
        System.out.println("Select Foreign Currency = " + choice);
        System.out.printf("Exchange Rate = %.2f\n", rate);
        System.out.println();
    }

    public static void currencyExchange(Scanner sc){
        System.out.println("Foreign Currency Exchange");
        System.out.print("Source Amount (PHP): ");
        double amount = sc.nextDouble();
        sc.nextLine();
        System.out.println();

        System.out.println("Exchanged Currency");
        System.out.printf("[1] Philippine Peso (PHP) = %.2f\n", amount);
        System.out.printf("[2] United States Dollar (USD) = %.2f\n", amount * 62.00);
        System.out.printf("[3] Japanese Yen (JPY) = %.2f\n", amount * 0.40);
        System.out.printf("[4] British Pound Sterling (GBP) = %.2f\n", amount * 84.00);
        System.out.printf("[5] Euro (EUR) = %.2f\n", amount * 72.00);
        System.out.printf("[6] Chinese Yuan Renminni (CNY) = %.2f\n", amount * 9.00);
        System.out.println();

        System.out.println("***");
        System.out.println("Source Currency = Philippine Peso (PHP)");
        System.out.printf("Source Amount (PHP) = %.2f\n", amount);
    }

}