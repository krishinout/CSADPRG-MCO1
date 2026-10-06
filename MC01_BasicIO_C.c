/*************************** 
Last names: Danieles, De Guzman, Nono, Suazon
Language: C
Paradigm(s): Procedural, Imperative, Structured
***************************/

#include <stdio.h>
#include <string.h>
#define MAX_NAME 100
#define DEFAULT_BALANCE 1000.00

//Functions
void showMenu();
void registerAccountName(char* accountName);
void depositAmount(char* accountName);
void withdrawAmount(char* accountName);
void recordExchangeRate();
void currencyExchange();

//Main Program
int main(){
    char accountName[MAX_NAME];

    showMenu();
    registerAccountName(accountName);
    depositAmount(accountName);
    withdrawAmount(accountName);
    recordExchangeRate();
    currencyExchange();

    return 0;
}

//Main Menu Function
void showMenu(){
    int choice;

    printf("Select Transaction:\n");
    printf("[1] Register Account Name\n");
    printf("[2] Deposit Amount\n");
    printf("[3] Withdraw Amount\n");
    printf("[4] Currency Exchange\n");
    printf("[5] Record Exchange Rates\n");
    printf("[6] Show Interest Amount\n\n");

    printf("Choice: ");
    scanf("%d", &choice);
    getchar();

    printf("\n***\n");
    printf("Choice = %d\n\n", choice);
}

//Register Account Name Function
void registerAccountName(char* accountName){
    printf("Register Account Name\n");
    printf("Account Name: ");
    fgets(accountName, MAX_NAME, stdin);
    accountName[strcspn(accountName, "\n")] = '\0';

    printf("\n***\n");
    printf("Account Name = %s\n\n", accountName);

}

//Deposit Amount Function
void depositAmount(char* accountName){
    char inputName[MAX_NAME];
    double deposit;

    printf("Deposit Amount\n");
    printf("Account Name: ");
    fgets(inputName, MAX_NAME, stdin);
    inputName[strcspn(inputName, "\n")] = '\0';

    printf("Current Balance: %.2f\n", DEFAULT_BALANCE);
    printf("Currency: PHP\n\n");

    printf("Deposit Amount: ");
    scanf("%lf", &deposit);
    getchar();

    printf("\n***\n");
    printf("Account Name = %s\n", inputName);
    printf("Deposit Amount = %.2f\n\n", deposit);
}

//Withdraw Amount Function
void withdrawAmount(char* accountName){
    char inputName[MAX_NAME];
    double withdraw;

    printf("Withdraw Amount\n");
    printf("Account Name: ");
    fgets(inputName, MAX_NAME, stdin);
    inputName[strcspn(inputName, "\n")] = '\0';

    printf("Current Balance: %.2f\n", DEFAULT_BALANCE);
    printf("Currency: PHP\n\n");

    printf("Withdraw Amount: ");
    scanf("%lf", &withdraw);
    getchar();

    printf("\n***\n");
    printf("Account Name = %s\n", inputName);
    printf("Withdraw Amount = %.2f\n\n", withdraw);  
    
}

//Record Exchange Rate Function
void recordExchangeRate(){
    int choice;
    double rate;

    printf("Record Exchange Rate\n\n");
    printf("[1] Philippine Peso (PHP)\n");
    printf("[2] United States Dollar (USD)\n");
    printf("[3] Japanese Yen (JPY)\n");
    printf("[4] British Pound Sterling (GBP)\n");
    printf("[5] Euro (EUR)\n");
    printf("[6] Chinese Yuan Renminbi (CNY)\n\n");

    printf("Select Foreign Currency: ");
    scanf("%d", &choice);
    getchar();

    printf("Exchange Rate: ");
    scanf("%lf", &rate);
    getchar();

    printf("\n***\n");
    printf("Select Foreign Currency = [%d]\n", choice);
    printf("Exchange Rate = %.2f\n\n", rate);
}

//Currency Exchange Function
void currencyExchange(){
    double amount;
    
    printf("Foreign Currency Exchange\n");
    printf("Source Amount = ");
    scanf("%lf", &amount);
    getchar();

    printf("\nExchanged Currency\n");
    printf("[1] Philippine Peso (PHP) = %.2f\n", 1.00 * amount);
    printf("[2] United States Dollar (USD) = %.2f\n", 62.00 * amount);
    printf("[3] Japanese Yen (JPY) = %.2f\n", 0.40 * amount);
    printf("[4] British Pound Sterling (GBP) = %.2f\n", 84.00 * amount);
    printf("[5] Euro (EUR) = %.2f\n", 72.00 * amount);
    printf("[6] Chinese Yuan Renminbi (CNY) = %.2f\n", 9.00 * amount);

    printf("\n***\n");
    printf("Target Currency = Philippine Peso (PHP)\n");
    printf("Source Amount (PHP) = %.2f\n\n", amount);
}
