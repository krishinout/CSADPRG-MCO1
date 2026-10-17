/*************************** 
Last names: Danieles, De Guzman, Nono, Suazon
Language: C
Paradigm(s): Procedural, Imperative, Structured
***************************/

#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#define MAX_NAME 100
#define DEFAULT_BALANCE 1000.00
#define ANNUAL_RATE 0.05
#define MAX_DAYS 3650


/*
* Enum representing the available currencies.
* The integer values match the menu choices (1-6) minus 1.
*/
typedef enum{
    PHP, USD, JPY, GBP, EUR, CNY, CURRENCY_COUNT
} Currency;


/*
* Array containing the full names of each currency.
* Index corresponds to the Currency enum.
*/
const char* CURRENCY_NAMES[6] = {
    "Philippine Peso", "United States Dollar", "Japanese Yen",
    "British Pound Sterling", "Euro", "Chinese Yuan Renminni"
};

/*
* Array containing the 3-letter codes of each currency.
* Index corresponds to the Currency enum.
*/
const char* CURRENCY_CODES[6] = {
    "PHP", "USD", "JPY", "GBP", "EUR", "CNY"
};


/*
* Structure representing a user's bank account.
* Holds the account name, current balance, and base currency.
*/
typedef struct{
    char accountName[MAX_NAME];
    double balance;
    Currency currency;
} Account;

//Function Prototypes
void showMenu();
int  getChoice();
void clearInput();
int  readDouble(double* value);
int  readInt(int* value);
void registerAccountName(Account* acct);
void depositAmount(Account* acct);
void withdrawAmount(Account* acct);
void recordExchangeRate(double rates[]);
void currencyExchange(double rates[]);
void showInterestAmount(Account* acct);
char askBackToMenu();
char askConvertAnother();

/*
* Main entry point of the program.
* Initializes the account and exchange rate array, then runs the main menu loop.
* @param (void)
* @return (int) Returns 0 upon successful termination.
*/
int main(){
    Account acct;
    acct.balance = DEFAULT_BALANCE;
    acct.currency = PHP;
    strcpy(acct.accountName, "");

    double rates[6] = {1.0, 0, 0, 0, 0, 0};

    //main loop
    int running = 1;
    while(running){
        showMenu();
        int choice = getChoice();

        printf("\n***\n");
        printf("Choice = %d\n\n", choice);

        switch(choice){
            case 1:
                registerAccountName(&acct);
                break;
            case 2:
                depositAmount(&acct);
                break;
            case 3:
                withdrawAmount(&acct);
                break;
            case 4:
                currencyExchange(rates);
                break;
            case 5:
                recordExchangeRate(rates);
                break;
            case 6:
                showInterestAmount(&acct);
                break;
            default:
                printf("Invalid choice. Please try again.\n\n");
                continue;
        }
        char ans = askBackToMenu();
        if(ans == 'N' || ans == 'n'){
            running = 0;
        }
    }
    printf("Thank you!\n");
    return 0;
}

/*
* This function clears the input buffer up to the newline character.
* It is used to prevent infinite loops and skipped prompts caused by leftover characters.
* @param (void)
* @return (void)
*/
void clearInput(){
    int c;
    while((c = getchar()) != '\n' && c != EOF);
}

/*
* This function safely reads a double from the console.
* It validates the input type and clears the buffer.
* @param (double*) value is a pointer to the double variable where the input will be stored.
* @return (int) Returns 1 if the read was successful, 0 if invalid input was detected.
*/
int readDouble(double* value){
    if(scanf("%lf", value) != 1){
        clearInput();
        return 0;
    }
    clearInput();
    return 1;
}

/*
* This function safely reads an integer from the console.
* It validates the input type and clears the buffer.
* @param (int*) value is a pointer to the integer variable where the input will be stored.
* @return (int) Returns 1 if the read was successful, 0 if invalid input was detected.
*/
int readInt(int* value){
    if(scanf("%d", value) != 1){
        clearInput();
        return 0;
    }
    clearInput();
    return 1;
}

/*
* This function prints the main menu options to the console.
* @param (void)
* @return (void)
*/
void showMenu(){
    printf("Select Transaction:\n");
    printf("[1] Register Account Name\n");
    printf("[2] Deposit Amount\n");
    printf("[3] Withdraw Amount\n");
    printf("[4] Currency Exchange\n");
    printf("[5] Record Exchange Rates\n");
    printf("[6] Show Interest Amount\n\n");
}

/*
* This function prompts the user for their menu choice.
* If the user types a non-integer, it defaults to -1 to trigger an invalid choice.
* @param (void)
* @return (int) The integer choice entered by the user, or -1 if invalid.
*/
int getChoice(){
    int choice;
    printf("Choice: ");
    if(scanf("%d", &choice) != 1){
        choice = -1;
    }
    clearInput();
    return choice;
}

/*
* This function asks the user if they want to return to the main menu.
* @param (void)
* @return (char) The validated character entered by the user.
*/
char askBackToMenu(){
    char ans;
    while(1){
        printf("Back to the Main Menu (Y/N): ");
        scanf(" %c", &ans);
        clearInput();
        printf("\n");
        if(ans == 'Y' || ans == 'y' || ans == 'N' || ans == 'n'){
            return ans;
        }
        printf("Please enter Y or N.\n\n");
    }
}

/*
* This function asks the user if they want to convert another currency.
* @param (void)
* @return (char) The validated character entered by the user.
*/
char askConvertAnother(){
    char ans;
    while(1){
        printf("Convert another currency (Y/N)? ");
        scanf(" %c", &ans);
        clearInput();
        printf("\n");
        if(ans == 'Y' || ans == 'y' || ans == 'N' || ans == 'n'){
            return ans;
        }
        printf("Please enter Y or N.\n\n");
    }
}

/*
* This function prompts the user to input their account name.
* It stores the input in the Account struct.
* @param (Account*) acct is a pointer to the user's Account struct
* @return (void)
*/
void registerAccountName(Account* acct){
    printf("Register Account Name\n");
    printf("Account Name: ");
    fgets(acct->accountName, MAX_NAME, stdin);
    acct->accountName[strcspn(acct->accountName, "\n")] = '\0';
    printf("\n");
}

/*
* This function handles the deposit process.
* It displays the current balance, takes a deposit amount, validates it, and updates the account balance.
* @param (Account*) acct is a pointer to the user's Account struct
* @return (void)
*/
void depositAmount(Account* acct){
    double deposit;

    printf("Deposit Amount\n");
    printf("Account Name: %s\n", acct->accountName);
    printf("Current Balance: %.2f\n", acct->balance);
    printf("Currency: %s\n\n", CURRENCY_CODES[acct->currency]);

    printf("Deposit Amount: ");
    if(!readDouble(&deposit)){
        printf("Invalid input.\n\n");
        return;
    }

    if(deposit <= 0){
        printf("Invalid deposit amount.\n\n");
        return;
    }

    acct->balance += deposit;
    printf("Updated Balance: %.2f\n\n", acct->balance);
}

/*
* This function handles the withdrawal process.
* It displays the current balance, takes a withdrawal amount, validates it, checks for sufficient funds, 
* and updates the account balance.
* @param (Account*) acct is a pointer to the user's Account struct
* @return (void)
*/
void withdrawAmount(Account* acct){
    double withdraw;

    printf("Withdraw Amount\n");
    printf("Account Name: %s\n", acct->accountName);
    printf("Current Balance: %.2f\n", acct->balance);
    printf("Currency: %s\n\n", CURRENCY_CODES[acct->currency]);

    printf("Withdraw Amount: ");
    if(!readDouble(&withdraw)){
        printf("Invalid input.\n\n");
        return;
    }

    if(withdraw <= 0){
        printf("Invalid withdraw amount.\n\n");
    } else if(withdraw > acct->balance){
        printf("Insufficient funds.\n\n");
    } else {
        acct->balance -= withdraw;
        printf("Updated Balance: %.2f\n\n", acct->balance);
    }
}

/*
* This function allows the user to record an exchange rate for a foreign currency.
* @param (double[]) rates is an array of size 6 storing the exchange rates relative to PHP.
* @return (void)
*/
void recordExchangeRate(double rates[]){
    int choice;
    double rate;

    printf("Record Exchange Rate\n\n");
    printf("[1] Philippine Peso (PHP)\n");
    printf("[2] United States Dollar (USD)\n");
    printf("[3] Japanese Yen (JPY)\n");
    printf("[4] British Pound Sterling (GBP)\n");
    printf("[5] Euro (EUR)\n");
    printf("[6] Chinese Yuan Renminni (CNY)\n\n");

    printf("Select Foreign Currency: ");
    if(!readInt(&choice)){
        printf("Invalid input.\n\n");
        return;
    }
    if(choice < 1 || choice > 6){
        printf("Invalid currency choice.\n\n");
        return;
    }
    if(choice == 1){
        printf("PHP is the base currency. Cannot set its rate.\n\n");
        return;
    }

    printf("Exchange Rate: ");
    if(!readDouble(&rate)){
        printf("Invalid input.\n\n");
        return;
    }
    if(rate <= 0){
        printf("Invalid exchange rate.\n\n");
        return;
    }

    rates[choice - 1] = rate;
    printf("\n");
}

/*
* This function performs currency exchange calculations.
* @param (double[]) rates is an array of size 6 storing the exchange rates relative to PHP.
* @return (void)
*/
void currencyExchange(double rates[]){
    char again = 'Y';

    while(again == 'Y' || again == 'y'){
        int source, target;
        double amount, result;

        printf("Foreign Currency Exchange\n");
        printf("Source Currency Option:\n");
        printf("[1] Philippine Peso (PHP)\n");
        printf("[2] United States Dollar (USD)\n");
        printf("[3] Japanese Yen (JPY)\n");
        printf("[4] British Pound Sterling (GBP)\n");
        printf("[5] Euro (EUR)\n");
        printf("[6] Chinese Yuan Renminni (CNY)\n\n");

        printf("Source Currency: ");
        if(!readInt(&source)){
            printf("Invalid input.\n\n");
            again = askConvertAnother();
            continue;
        }

        if(source < 1 || source > 6){
            printf("Invalid source currency.\n\n");
            again = askConvertAnother();
            continue;
        }

        printf("Source Amount: ");
        if(!readDouble(&amount)){
            printf("Invalid input.\n\n");
            again = askConvertAnother();
            continue;
        }

        if(amount <= 0){
            printf("Invalid amount.\n\n");
            again = askConvertAnother();
            continue;
        }
        printf("\n");

        printf("Exchanged Currency Options:\n");
        printf("[1] Philippine Peso (PHP)\n");
        printf("[2] United States Dollar (USD)\n");
        printf("[3] Japanese Yen (JPY)\n");
        printf("[4] British Pound Sterling (GBP)\n");
        printf("[5] Euro (EUR)\n");
        printf("[6] Chinese Yuan Renminni (CNY)\n\n");

        printf("Exchange Currency: ");
        if(!readInt(&target)){
            printf("Invalid input.\n\n");
            again = askConvertAnother();
            continue;
        }

        if(target < 1 || target > 6){
            printf("Invalid target currency.\n\n");
            again = askConvertAnother();
            continue;
        }

        // to check if rates are set
        if(source != 1 && rates[source - 1] == 0){
            printf("Rate for %s not yet recorded.\n\n", CURRENCY_CODES[source - 1]);
            again = askConvertAnother();
            continue;
        }
        if(target != 1 && rates[target - 1] == 0){
            printf("Rate for %s not yet recorded.\n\n", CURRENCY_CODES[target - 1]);
            again = askConvertAnother();
            continue;
        }

        //conversion of the amount
        double inPhp;
        if(source == 1){
            inPhp = amount;
        } else {
            inPhp = amount * rates[source - 1];
        }

        if(target == 1){
            result = inPhp;
        } else {
            result = inPhp / rates[target - 1];
        }

        printf("Exchange Amount: %.2f\n\n", result);

        again = askConvertAnother();
    }
}

/*
* This function calculates and displays the expected daily interest over N days.
* It displays the current account details, takes the number of days, and prints
* a formatted table showing the day, interest earned, and running balance.
* @param (Account*) acct is a pointer to the user's Account struct
* @return (void)
*/
void showInterestAmount(Account* acct){
    int days;

    printf("Show Interest Amount\n");
    printf("Account Name: %s\n", acct->accountName);
    printf("Current Balance: %.2f\n", acct->balance);
    printf("Currency: %s\n", CURRENCY_CODES[acct->currency]);
    printf("Interest Rate: 5%%\n\n");

    printf("Total Number of Days: ");
    if(!readInt(&days)){
        printf("Invalid input.\n\n");
        return;
    }

    if(days <= 0){
        printf("Invalid number of days.\n\n");
        return;
    }

    if(days > MAX_DAYS){
        printf("Number of days too large (max %d).\n\n", MAX_DAYS);
        return;
    }

    printf("\n");

    printf("%-4s| %-9s| %-8s|\n", "Day", "Interest", "Balance");

    double current = acct->balance;
    for(int i = 1; i <= days; i++){
        double interest = current * (ANNUAL_RATE / 365.0);
        current += interest;
        printf("%-4d| %-9.2f| %-8.2f|\n", i, interest, current);
    }
    printf("\n");
}
