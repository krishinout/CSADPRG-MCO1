#
# MCO Milestone 1: Basic I/O Operations in R
# 
# Last names: Danieles, De Guzman, Nono, Suazon
# Language: R
# Paradigm(s):
#

#####################################
# MAIN PROGRAM
main <- function(){

    showMenu()
    choice <- readline("Choice: ")
    choice <- as.integer(choice)
    cat("\n***\n")
    cat("Choice =", choice, "\n\n")    

    accountName <- registerAccount()
    balance <- 1000.0

    balance <- depositAmount(accountName, balance)
    balance <- withdrawAmount(accountName, balance)
    recordExchange()
    currencyExchange()
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
    cat("[6] Show Interest Amount\n\n")

}

registerAccount <- function(){
    cat("Register Account Name\n")
    accountName <- readline("Account Name: ")

    cat("\n***\n")
    cat("Account Name =", accountName, "\n\n")

    return(accountName)

}

depositAmount <- function(accountName, balance) {
    cat("Deposit Amount\n")
    depositAccountName <- readline("Account Name: ")

    cat("Current Balance:", sprintf("%.2f", balance), "\n")
    cat("Currency: PHP\n")

    depositAmount <- readline("\nDeposit Amount: ")
    depositAmount <- as.numeric(depositAmount)
    # newBalance <- balance + depositAmount (FOR FINAL VER)
    newBalance <- balance 

    cat("\n***\n")
    cat("Account Name =", depositAccountName, "\n")
    cat("Deposit Amount =", sprintf("%.2f", depositAmount), "\n\n")
    return(newBalance)
}

withdrawAmount <- function(accountName, balance) {
    cat("Withdraw Amount\n")
    withdrawAccountName <- readline("Account Name: ")

    cat("Current Balance:", sprintf("%.2f", balance), "\n")
    cat("Currency: PHP\n")

    withdrawAmount <- readline("\nWithdraw Amount: ")
    withdrawAmount <- as.numeric(withdrawAmount)
    # newBalance <- balance - withdrawAmount
    newBalance <- balance

    cat("\n***\n")
    cat("Account Name =", withdrawAccountName, "\n")
    cat("Withdraw Amount =", sprintf("%.2f", withdrawAmount), "\n\n")

    return(newBalance)
}

currencyExchange <- function()
{
    cat("Foreign Currency Exchange\n")
    amount <- readline("Source Amount (PHP): ")
    amount <- as.numeric(amount)
    
    #sprintf is parang printf sa C
    cat("\nExchanged Currency\n")
    cat("[1] Philippine Peso (PHP) =", sprintf("%.2f", 1 * amount), "\n")
    cat("[2] United States Dollar (USD) =", sprintf("%.2f", 62 * amount), "\n")
    cat("[3] Japanese Yen (JPY) =", sprintf("%.2f", 0.4 * amount), "\n")
    cat("[4] British Pound Sterling (GBP) =", sprintf("%.2f", 84 * amount), "\n")
    cat("[5] Euro (EUR) =", sprintf("%.2f", 72 * amount), "\n")
    cat("[6] Chinese Yuan Renminni (CNY) =", sprintf("%.2f", 9 * amount), "\n")
    
    cat("\n***\n")
    cat("Source Currency = Philippine Peso (PHP)\n")
    cat("Source Amount (PHP) =", sprintf("%.2f", amount), "\n\n")
}

recordExchange <- function()
{
    cat("Record Exchange Rate\n\n")
    
    cat("[1] Philippine Peso (PHP)\n")
    cat("[2] United States Dollar (USD)\n")
    cat("[3] Japanese Yen (JPY)\n")
    cat("[4] British Pound Sterling (GBP)\n")
    cat("[5] Euro (EUR)\n")
    cat("[6] Chinese Yuan Renminni (CNY)\n\n")

    choice <- readline("Select Foreign Currency: ")
    choice <- as.integer(choice)

    rate <- readline("Exchange Rate: ")
    rate <- as.numeric(rate)

    cat("\n***\n")
    cat("Selected Foreign Currency = [", choice, "]\n", sep = "")
    cat("Exchange Rate:", sprintf("%.2f", rate), "\n\n")
}

main()