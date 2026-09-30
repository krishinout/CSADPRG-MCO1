/**
 * MCO Milestone 1: Basic I/O Operations in Kotlin
 * 
 * Last names: Danieles, De Guzman, Nono, Suazon
 * Language: Kotlin
 * Paradigm(s):
 */

// ---------------------------------------
// MAIN PROGRAM
fun main()
{
    var accountName = ""
    var balance = 0.0

    while (true)
    {
        showMenu()
        val choice = readln().toInt()
        println("===================================\n")

        when (choice)
        {
            1 -> accountName = registerAccount()
            2 -> balance = depositAmount(accountName, balance)
            else -> println("Error: Invalid choice")
        }
    }
}

// ---------------------------------------
// FUNCTIONS FOR CHOICES AND MAIN MENU
fun showMenu()
{
    println("\n===================================")
    println("Select Transaction:")
    println("[1] Register Account Name")
    println("[2] Deposit Amount")
    println("[3] Withdraw Amount")
    println("[4] Currency Exchange")
    println("[5] Record Exchange Rates")
    println("[6] Show Interest Amount")

    print("\nChoice: ")
}

fun registerAccount(): String
{
    println("[REGISTER ACCOUNT NAME]")
    print("Account Name: ")
    val accountName = readln()

    println("\n***")
    println("Registered Account Name = ${accountName}")
    println("***")

    return accountName
}

fun depositAmount(accountName: String, balance: Double): Double
{    
    println("[DEPOSIT AMOUNT]")
    print("Account Name: ")
    val depositAccountName = readln()

    if (depositAccountName == accountName)
    {
        println("Current Balance: ${balance}")
        println("Currency: PHP")

        print("\nDeposit Amount: ")
        val depositAmount = readln().toDouble()
        val newBalance = depositAmount + balance
        
        println("\n***")
        println("Registered Account Name = ${accountName}")
        println("Deposited Amount = ${depositAmount}")
        println("Updated Balance = ${newBalance}")
        println("***")
        return newBalance // para maupdate balance sa main
    }

    else
    {
        println("Error: Account name does not match records.")
        return balance // no change sa balance
    }
}