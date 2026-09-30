#
# MCO Milestone 1: Basic I/O Operations in R
# 
# Last names: 
# Language: R
# Paradigm(s):
#

#####################################
# MAIN PROGRAM
main <- function(){
    accountName <- ""
    balance <- 0.0

    whie(True)
    {
        showMenu()
        choice <- readline()
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
    cat("\n===================================")
    cat("Select Transaction:\n")
    cat("[1] Register Account Name\n")
    cat("[2] Deposit Amount\n")
    cat("[3] Withdraw Amount\n")
    cat("[4] Currency Exchange\n")
    cat("[5] Record Exchange Rates\n")
    cat("[6] Show Interest Amount\n")

    cat("\nChoice: ")
}

registerAccount <- function(){
    cat("[REGISTER ACCOUNT NAME]\n")
    print("Account Name: ")
    accountName <- readline()

    cat("\n***\n")
    cat("Registered Account Name = ", accountName, "\n") #pede den gamitin paste
    cat("***\n")

    return(accountName)

}