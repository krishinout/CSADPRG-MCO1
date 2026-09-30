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

    while (true)
    {
        showMenu()
        val choice = readln().toInt()
        println("===================================\n")

        when (choice)
        {
            1 -> accountName = registerAccount()
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