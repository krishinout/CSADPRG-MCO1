# 
# Last names: Danieles, De Guzman, Nono, Suazon
# Language: R
# Paradigm(s): Procedural
#
#####################################

# walang constants sa R so wag nlng baguhin pag naka caps
MAX_NAME <- 100
DEFAULT_BALANCE <- 1000.00
ANNUAL_RATE <- 0.05
MAX_DAYS <- 3650

CURRENCY_NAMES <- c(
  "Philippine Peso", "United States Dollar", "Japanese Yen",
  "British Pound Sterling", "Euro", "Chinese Yuan Renminni"
)
CURRENCY_CODES <- c("PHP", "USD", "JPY", "GBP", "EUR", "CNY")

# MAIN PROGRAM
main <- function(){
    accountName <- ""
    balance <- 0.0

    while(TRUE)
    {
        showMenu()
        choice <- readline("choice: ")
        choice <- as.integer(choice)

        # add this para di mag error if nag enter ng string 
        if (is.na(choice)) {
            cat("Error: Please enter a valid choice.\n\n")
            next
        }
        
        # need muna mag register account bago magpick ibang choice
        if(accountName == "" && choice != 1){
            cat("Error: Please register an account name first.\n\n")
            next
        }

        # WALA ATANG SWITCH CASE SA R?? check q mmya..
        if(choice == 1){
            accountName <- registerAccount()
        } else if(choice == 2 ){
            balance <- depositAmount(accountName, balance)
        } else if(choice == 3){
            balance <- withdrawAmount(accountName, balance)
        }else if(choice == 4){
            currencyExchange()
        } else if(choice == 5){
            recordExchange()
        } else{
            cat("Error: Invalid choice")
        }
        askBackToMenu()
    }
}

#####################################
# FUNCTIONS FOR CHOICES AND MAIN MENU
showMenu <- function(){
    cat("Select Transaction:\n")
    cat("[1] Register Account Name\n")
    cat("[2] Deposit Amount\n")
    cat("[3] Withdraw Amount\n")
    cat("[4] Currency Exchange\n")
    cat("[5] Record Exchange Rates\n")
    cat("[6] Show Interest Amount\n")
}

#This function asks the user if they want to return to the main menu.
askBackToMenu <- function() {
  while(TRUE) {
    ans <- readline("Back to the Main Menu (Y/N): ")
    if(is.na(ans)){
        next
    } else if (ans == "Y" || ans == "y") {
      break
    } else if (ans == "N" || ans == "n") {
      cat("Thank you!\n")
      quit(save = "no")
    } else {
      cat("Error: Please enter Y or N.\n\n")
    }
  }
}

# This function asks the user if they want to convert another currency.
askConvertAnother <- function() {
  while(TRUE) {
    ans <- readline("Convert another currency (Y/N)? ")
    if(is.na(ans)){
        next
    } else if (ans == "Y" || ans == "y" || ans == "N" || ans == "n") {
        return(ans)
    } else {
      cat("Error: Please enter Y or N.\n\n")
    }
}
}

registerAccount <- function(){
    cat("\nRegister Account Name\n")
    while(TRUE){
        accountName <- readline("Account Name: ")
        if(trimws(accountName) == ""){
            cat("Error: Name cannot be empty")
        }
        else{
            break
        }
    }

    cat("\n===\n")
    cat("Registered Account Name =", accountName, "\n")
    cat("===\n\n")

    return(accountName)
}

depositAmount <- function(accountName, balance){
    cat("\nDeposit Amount\n")
    cat("Account Name:", accountName, "\n")
    cat("Current Balance:", sprintf("%.2f", balance), "\n")
    cat("Currency: PHP\n")

    #input validation for negative values 
    while(TRUE){
        depositAmount <- readline("\nDeposit Amount: ")
        depositAmount <- as.numeric(depositAmount)
        if(is.na(depositAmount)){
            cat("Error: Please enter a valid number.\n")
        }
        else if(depositAmount <= 0){
            cat("Error: Cannot deposit negative number")
        }
        else{
            break
        }
    }
    
    newBalance <- depositAmount + balance
    cat("\n===\n")
    cat("Deposit Amount =", sprintf("%.2f", depositAmount), "\n")
    cat("Updated Balance =", sprintf("%.2f", newBalance), "\n")
    cat("===\n\n")
    return(newBalance) #para maupdate balance sa main

}

withdrawAmount <- function(accountName, balance) {
    cat("\nWithdraw Amount\n")
    cat("Account Name:", accountName, "\n")
    cat("Current Balance:", sprintf("%.2f", balance), "\n")
    cat("Currency: PHP\n")

    #input validation for negative values 
    while(TRUE){
        withdrawAmount <- readline("\nWithdraw Amount: ")
        withdrawAmount <- as.numeric(withdrawAmount)
        if(is.na(withdrawAmount)){
            cat("Error: Please enter a valid number.\n")
        }
        else if(withdrawAmount < 0){
            cat("Error: Cannot withdraw negative number")            
        }
        else if(withdrawAmount > balance){
            cat("Error: insufficient funds")
        }
        else{
            break
        }
    }

    newBalance <- balance - withdrawAmount
    
    cat("\n===\n")
    cat("Withdraw Amount =", sprintf("%.2f", withdrawAmount), "\n")
    cat("Updated Balance =", sprintf("%.2f", newBalance), "\n")
    cat("===\n\n")
    return(newBalance) # para maupdate balance sa main

}

currencyExchange <- function(rates)
{
    again <- 'Y'
    
    while(again == 'Y' || again == 'y'){
        cat("\nForeign Currency Exchange\n")
        cat("Source Currency Option:\n")
        cat("[1] Philippine Peso (PHP)\n")
        cat("[2] United States Dollar (USD)\n")
        cat("[3] Japanese Yen (JPY)\n")
        cat("[4] British Pound Sterling (GBP)\n")
        cat("[5] Euro (EUR)\n")
        cat("[6] Chinese Yuan Renminni (CNY)\n\n")
        
        while(TRUE){
            source <- readline("Source Currency: ")
            source <- as.numeric(source)

            if (is.na(source)) {
                cat("Invalid input. Please enter a number.\n\n")
                next
            }
            if (source < 1 || source > 6) {
                cat("Invalid source currency. Must be 1-6.\n\n")
                next
            }
            break
        }

        while(TRUE){
            amount <- readline("Source Amount: ")
            amount <- as.numeric(amount)

            if (is.na(amount)) {
                cat("Invalid input. Please enter a number.\n\n")
                next
            }
            if (amount <= 0) {
                cat("Invalid source amount. Must be a positive number.\n\n")
                next
            }
            break
        }
        cat("\n")

        cat("Exchanged Currency Options\n\n")
        cat("[1] Philippine Peso (PHP)\n")
        cat("[2] United States Dollar (USD)\n")
        cat("[3] Japanese Yen (JPY)\n")
        cat("[4] British Pound Sterling (GBP)\n")
        cat("[5] Euro (EUR)\n")
        cat("[6] Chinese Yuan Renminni (CNY)\n\n")

        while(TRUE){
            target <- readline("Exchange Currency: ")
            target <- as.numeric(target)

            if (is.na(target)) {
                cat("Invalid input. Please enter a number.\n\n")
                next
            }
            if (target < 1 || target > 6) {
                cat("Invalid exchange currency. Must be 1-6.\n\n")
                next
            }
            break
        }

    if (source != 1 && rates[source] == 0) {
        cat(sprintf("Rate for %s not yet recorded. Please record it first.\n\n", CURRENCY_CODES[source]))
        again <- askConvertAnother()
        next
    }
    if (target != 1 && rates[target] == 0) {
      cat(sprintf("Rate for %s not yet recorded. Please record it first.\n\n", CURRENCY_CODES[target]))
      again <- askConvertAnother()
      next
    }
    
    if(source == 1){
        inPhp <- amount
    }else{
        inPhp <- amount * rates[source]
    }

    if(target == 1){
        result <- inPhp
    } else{
        result <- inPhp / rates[target]
    }

    again <- askConvertAnother()
    }
}

recordExchange <- function(rates)
{
    cat("Record Exchange rate\n\n")
    cat("[1] Philippine Peso (PHP)\n")
    cat("[2] United States Dollar (USD)\n")
    cat("[3] Japanese Yen (JPY)\n")
    cat("[4] British Pound Sterling (GBP)\n")
    cat("[5] Euro (EUR)\n")
    cat("[6] Chinese Yuan Renminni (CNY)\n\n")

    choice <- readline("Select Foreign Currency: ")
    choice <- as.integer(choice)

    while(TRUE){
        choice <- readline("Select Foreign Currency: ")
        choice <- as.numeric(choice)
        if (is.na(choice)) {
        cat("Invalid input. Please enter a number.\n\n")
        next
        }
        if (choice < 1 || choice > 6) {
        cat("Invalid currency choice. Must be 1-6.\n\n")
        next
        }
        if (choice == 1) {
        cat("PHP is the base currency. Cannot set its rate.\n\n")
        next
        }
        break
    }

        while(TRUE){
            rate <- readline("Exchange rate ")
            rate <- as.numeric(rate)
            if (is.na(rate)) {
                cat("Invalid input. Please enter a number.\n\n")
                next
            }
            if (rate <= 0) {
                cat("ERROR: Invalid exchange rate. Must be greater than 0.\n\n")
                next
            }
            break
    }

  rates[choice] <- rate
  cat("\n")
  return(rates)
}

showInterestAmount <- function(accountName, balance, accountCurrency) {
  cat("Show Interest Amount\n")
  cat("Account Name:", accountName, "\n")
  cat(sprintf("Current Balance: %.2f\n", balance))
  cat("Currency:", CURRENCY_CODES[accountCurrency], "\n")
  cat("Interest Rate: 5%\n\n")

  while(TRUE) {
    days <- readline("Total Number of Days: ")
    days <- as.numeric(days)
    if (is.na(days)) {
      cat("Invalid input. Please enter a number.\n\n")
      next
    }
    if (days <= 0) {
      cat("Invalid number of days. Must be greater than 0.\n\n")
      next
    }
    if (days > MAX_DAYS) {
      cat(sprintf("Number of days too large (max %d).\n\n", MAX_DAYS))
      next
    }
    break
  }

  cat("\n")
  cat(sprintf("%-4s| %-9s| %-8s|\n", "Day", "Interest", "Balance"))
  current <- balance
  for (i in 1:days) {
    interest <- current * (ANNUAL_RATE / 365.0)
    current <- current + interest
    cat(sprintf("%-4d| %-9.2f| %-8.2f|\n", i, interest, current))
  }
  cat("\n")
}

main()