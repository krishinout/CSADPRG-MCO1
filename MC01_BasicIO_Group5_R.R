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

