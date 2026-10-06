/*
 * MCO Milestone 1: Basic I/O Operations in Kotlin
 *
 * Last names: Danieles, De Guzman, Nono, Suazon
 * Language: Kotlin
 * Paradigm(s):
 */

// ---------------------------------------
// MAIN PROGRAM
// Screens show one after the other lang based sa anns ni maam
fun main()
{
    showMenu()

    val accountName = registerAccount()
    var balance = 1000.0

    depositAmount(accountName, balance)
    withdrawAmount(accountName, balance)
    recordExchange()
    currencyExchange()
}

// ---------------------------------------
// SCREENS/FUNCTIONS
fun showMenu()
{
    println("\n===================================\n")
    println("Select Transaction:")
    println("[1] Register Account Name")
    println("[2] Deposit Amount")
    println("[3] Withdraw Amount")
    println("[4] Currency Exchange")
    println("[5] Record Exchange Rates")
    println("[6] Show Interest Amount")
    println("\n===================================\n")
}

fun registerAccount(): String
{
    println("[REGISTER ACCOUNT NAME]")
    print("Account Name: ")
    val accountName = readln()

    println("\n***")
    println("Registered Account Name = ${accountName}")
    println("***\n")

    return accountName
}

fun depositAmount(accountName: String, balance: Double): Double
{
    println("[DEPOSIT AMOUNT]")
    print("Account Name: ")
    val depositAccountName = readln()

    println("Current Balance: ${balance}")
    println("Currency: PHP")

    print("\nDeposit Amount: ")
    val depositAmount = readln().toDouble()
    val newBalance = balance 
    // val newBalance = balance + depositAmount

    println("\n***")
    println("Registered Account Name = ${depositAccountName}")
    println("Deposited Amount = ${depositAmount}")
    println("Updated Balance = ${newBalance}")
    println("***\n")

    return newBalance
}

fun withdrawAmount(accountName: String, balance: Double): Double
{
    println("[WITHDRAW AMOUNT]")
    print("Account Name: ")
    val withdrawAccountName = readln()

    println("Current Balance: ${balance}")
    println("Currency: PHP")

    print("\nWithdraw Amount: ")
    val withdrawAmount = readln().toDouble()
    val newBalance = balance
    // val newBalance = balance - withdrawAmount

    println("\n***")
    println("Registered Account Name = ${withdrawAccountName}")
    println("Withdrawn Amount = ${withdrawAmount}")
    println("Updated Balance = ${newBalance}")
    println("***\n")

    return newBalance
}

fun currencyExchange()
{
    println("[FOREIGN CURRENCY EXCHANGE]")
    print("Source Amount (PHP): ")
    val amount = readln().toDouble()

    println("\nExchanged Currency")
    println("[1] Philippine Peso (PHP) = ${String.format("%.2f", 1 * amount)}")
    println("[2] United States Dollar (USD) = ${String.format("%.2f", 62 * amount)}")
    println("[3] Japanese Yen (JPY) = ${String.format("%.2f", 0.4 * amount)}")
    println("[4] British Pound Sterling (GBP) = ${String.format("%.2f", 84 * amount)}")
    println("[5] Euro (EUR) = ${String.format("%.2f", 72 * amount)}")
    println("[6] Chinese Yuan Renminbi (CNY) = ${String.format("%.2f", 9 * amount)}")

    println("\n***")
    println("Source Currency = Philippine Peso (PHP)")
    println("Source Amount (PHP) = ${amount}")
    println("***\n")
}

fun recordExchange()
{
    println("[RECORD EXCHANGE RATE]\n")

    println("[1] Philippine Peso (PHP)")
    println("[2] United States Dollar (USD)")
    println("[3] Japanese Yen (JPY)")
    println("[4] British Pound Sterling (GBP)")
    println("[5] Euro (EUR)")
    println("[6] Chinese Yuan Renminbi (CNY)\n")

    print("Select Foreign Currency: ")
    val choice = readln().toInt()

    print("Exchange Rate: ")
    val rate = readln().toDouble()

    println("\n***")
    println("Selected Foreign Currency = [${choice}]")
    println("Exchange Rate: ${String.format("%.2f", rate)}")
    println("***\n")
}