/**
 * MCO Milestone 1: Basic I/O Operations in Kotlin
 * 
 * Last names: Danieles, De Guzman, Nono, Suazon
 * Language: Kotlin
 * Paradigm(s):
 */

// ---------------------------------------
// MAIN PROGRAM
val currencyRates = doubleArrayOf(1.00, 62.00, 0.40, 84.00, 72.00, 9.00) // set default rates

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
            3 -> balance = withdrawAmount(accountName, balance)
            4 -> currencyExchange()
            5 -> recordExchange()
            else -> println("Error: Invalid choice")
        }

        if (!backToMainMenu())
        {
            return // main ends
        }
    }
}

// ---------------------------------------
// FUNCTIONS FOR CHOICES AND MAIN MENU
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

    print("\nChoice: ")
}

fun backToMainMenu(): Boolean
{
    while (true)
    {
        print("\nBack to the Main Menu (Y/N): ")
        when (readln().trim().lowercase())
        {
            "y" -> return true
            "n" -> return false
            else -> println("Error: Please enter Y or N.")
        }
    }
}

fun registerAccount(): String
{
    println("[REGISTER ACCOUNT NAME]")

    while (true)
    {
        print("Account Name: ")
        val accountName = readln().trim()

        if (accountName.isEmpty())
        {
            println("Error: Account name cannot be empty.")
            continue // skips the rest tapos goes back sa start ng loop
        }

        return accountName
    }
}

fun depositAmount(accountName: String, balance: Double): Double
{    
    println("[DEPOSIT AMOUNT]")

    if (accountName.isEmpty())
    {
        println("Error: Please register an account name first.")
        return balance
    }

        println("Account Name: ${accountName}")
        println("Current Balance: ${balance}")
        println("Currency: PHP")

        print("\nDeposit Amount: ")
        val depositAmount = readln().toDouble()
        val newBalance = depositAmount + balance

        println("Updated Balance: ${newBalance}")
        return newBalance // para maupdate balance sa main
}

fun withdrawAmount(accountName: String, balance: Double): Double
{    
    println("[WITHDRAW AMOUNT]")

    if (accountName.isEmpty())
    {
        println("Error: Please register an account name first.")
        return balance
    }

        println("Account Name: ${accountName}")
        println("Current Balance: ${balance}")
        println("Currency: PHP")

        print("\nWithdraw Amount: ")
        val withdrawAmount = readln().toDouble()
        val newBalance = balance - withdrawAmount

        println("Updated Balance: ${newBalance}")
        return newBalance // para maupdate balance sa main
}

fun currencyExchange()
{
    val currencyNames = arrayOf(
        "Philippine Peso (PHP)",
        "United States Dollar (USD)",
        "Japanese Yen (JPY)",
        "British Pound Sterling (GBP)",
        "Euro (EUR)",
        "Chinese Yuan Renminbi (CNY)"
    )

    while (true)
    {
        println("[FOREIGN CURRENCY EXCHANGE]")
        println("Source Currency Option:")
        println("[1] Philippine Peso (PHP)")
        println("[2] United States Dollar (USD)")
        println("[3] Japanese Yen (JPY)")
        println("[4] British Pound Sterling (GBP)")
        println("[5] Euro (EUR)")
        println("[6] Chinese Yuan Renminbi (CNY)")

        print("\nSource Currency: ")
        val sourceCurrency = readln().toInt()
        if (sourceCurrency !in 1..6)
        {
            println("Error: Invalid source currency.")
            continue
        }

        print("Source Amount: ")
        val sourceAmount = readln().toDouble()

        println("\nExchanged Currency Options:")
        println("[1] Philippine Peso (PHP)")
        println("[2] United States Dollar (USD)")
        println("[3] Japanese Yen (JPY)")
        println("[4] British Pound Sterling (GBP)")
        println("[5] Euro (EUR)")
        println("[6] Chinese Yuan Renminbi (CNY)")

        print("\nExchange Currency: ")
        val exchangeCurrency = readln().toInt()
        if (exchangeCurrency !in 1..6)
        {
            println("Error: Invalid exchange currency.")
            continue
        }

        // formulas and values para sa exchange
        val sourceName = currencyNames[sourceCurrency - 1]
        val exchangeName = currencyNames[exchangeCurrency - 1]
        val sourceRate = currencyRates[sourceCurrency - 1]
        val exchangeRate = currencyRates[exchangeCurrency - 1]
        val exchangeAmount = sourceAmount * sourceRate / exchangeRate // main output

        println("Exchange Amount: ${String.format("%.2f", exchangeAmount)}")

        print("\nConvert another currency (Y/N)? ")
        if (readln().trim().lowercase() != "y")
        {
            return
        }
    }
}

fun recordExchange()
{
    println("[RECORD EXCHANGE RATE]\n")

    println("[1] Philippine Peso (PHP)")
    println("[2] United States Dollar (USD)")
    println("[3] Japanese Yen (JPY)")
    println("[4] British Pound Sterling (GBP)")
    println("[5] Euro (EUR)")
    println("[6] Chinese Yuan Renminni (CNY)\n")

    print("Select Foreign Currency: ")
    val choice = readln().toInt()

    if (choice !in 1..6)
    {
        println("Error: Invalid choice.")
        return
    }

    print("Exchange Rate: ")
    val rate = readln().toDouble()
    currencyRates[choice - 1] = rate // set the new exchange rate
}
