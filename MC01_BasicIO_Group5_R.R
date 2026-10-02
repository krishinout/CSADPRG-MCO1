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
    accountName <- ""
    balance <- 0.0

    while(TRUE)
    {
        showMenu()
        choice <- readline("choice: ")
        choice <- as.integer(choice)
        cat("===================================\n")

        # WALA ATANG SWITCH CASE SA R?? check q mmya..
        if(choice == 1){
            accountName <- registerAccount()
        } 
        else if(choice == 2){
            balance <- depositAmount(accountName, balance)
        }
        else if(choice == 3){
            balance <- withdrawAmount(accountName, balance)
        }
        else{
            cat("Error: Invalid choice")
        }
    }
}

#####################################
# FUNCTIONS FOR CHOICES AND MAIN MENU
showMenu <- function(){
    cat("\n===================================\n")
    cat("Select Transaction:\n")
    cat("[1] Register Account Name\n")
    cat("[2] Deposit Amount\n")
    cat("[3] Withdraw Amount\n")
    cat("[4] Currency Exchange\n")
    cat("[5] Record Exchange Rates\n")
    cat("[6] Show Interest Amount\n")
}

registerAccount <- function(){
    cat("[REGISTER ACCOUNT NAME]\n")
    accountName <- readline("Account Name: ")

    cat("\n***\n")
    cat("Registered Account Name = ", accountName, "\n")
    cat("***\n")

    return(accountName)

}

depositAmount <- function(accountName, balance){
    cat("[DEPOSIT AMOUNT]\n")
    depositAccountName <- readline("Account Name: ")

    if (depositAccountName == accountName)
    {
        cat("Current Balance: ", balance, "\n")
        cat("Currency: PHP\n")
        depositAmount <- readline("\nDeposit Amount: ")
        depositAmount <- as.numeric(depositAmount)
        newBalance <- depositAmount + balance
        
        cat("\n***\n")
        cat("Registered Account Name = ", accountName, "\n")
        cat("Deposited Amount = ", depositAmount, "\n")
        cat("Updated Balance = ", newBalance, "\n")
        cat("***\n")
        return(newBalance) #para maupdate balance sa main
    }

    # to be added deposit validation (e.g., negative amount)

    else
    {
        cat("Error: Account name does not match records.\n")
        return(balance) #no change sa balance
    }
}

withdrawAmount <- function(accountName, balance) {
    cat("[WITHDRAW AMOUNT]\n")
    withdrawAccountName <- readline("Account Name: ")

    if (withdrawAccountName == accountName)
    {
        cat("Current Balance:", balance, "\n")
        cat("Currency: PHP\n")

        withdrawAmount <- readline("\nWithdraw Amount: ")
        withdrawAmount <- as.numeric(withdrawAmount)
        newBalance <- balance - withdrawAmount
        
        cat("\n***\n")
        cat("Registered Account Name = ", accountName, "\n")
        cat("Withdrawn Amount = ", withdrawAmount, "\n")
        cat("Updated Balance = ", newBalance, "\n")
        cat("***\n")
        return(newBalance) # para maupdate balance sa main
    }

    # to be added withdrawal validation (e.g., insufficient funds)

    else
    {
        cat("Error: Account name does not match records.\n")
        return(balance) # no change sa balance
    }
}

main()