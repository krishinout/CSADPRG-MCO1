/**
 * MCO Milestone 1: Basic I/O Operations in Kotlin
 * 
 * Last names: Danieles, De Guzman, Nono, Suazon
 * Language: Kotlin
 * Paradigm(s): Procedural, Imperative
 */

// ---------------------------------------
// MAIN PROGRAM

// set up an array to store the currency exchange rates
val currencyRates = arrayOfNulls<Double>(6)

/**
 * This is the main function that runs the program.
 * @return N/A
 */
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
            6 -> interestAmount(accountName, balance)
            else -> println("Error: Invalid choice")
        }

        if (!backToMainMenu())
        {
            println("Thank you for using the program. Goodbye!")
            return // main ends
        }

        clearTerminal()
    }
}

// ---------------------------------------
// FUNCTIONS FOR CHOICES AND MAIN MENU

/**
 * Displays the main menu.
 * @return N/A
 */
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

/**
 * Asks the user if they want to go back to the main menu.
 * @return (Boolean) true if the user wants to go back, false otherwise
 */
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

/**
 * Registers a new account.
 * @return (String) The name of the registered account.
 */
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

/**
 * Deposits an amount into the account.
 * @param accountName (String) The name of the account.
 * @param balance (Double) The current balance of the account.
 * @return (Double) The updated balance after the deposit.
 */
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

        val depositAmount: Double
        while (true)
        {
            print("\nDeposit Amount: ")
            val input = readln().trim()
            val amount = input.toDoubleOrNull()

            if (amount == null)
            {
                println("Error: Deposit amount must be numerical.")
                continue
            }

            if (amount <= 0)
            {
                println("Error: Deposit amount must be greater than zero.")
                continue
            }

            depositAmount = amount
            break
        }

        val newBalance = depositAmount + balance

        println("Updated Balance: ${newBalance}")
        return newBalance // para maupdate balance sa main
}

/**
 * Withdraws an amount from the account.
 * @param accountName (String) The name of the account.
 * @param balance (Double) The current balance of the account.
 * @return (Double) The updated balance after the withdrawal.
 */
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

        val withdrawAmount: Double
        while (true)
        {
            print("\nWithdraw Amount: ")
            val input = readln().trim()
            val amount = input.toDoubleOrNull()

            if (amount == null)
            {
                println("Error: Withdrawal amount must be numerical.")
                continue
            }

            if (amount <= 0)
            {
                println("Error: Withdrawal amount must be greater than zero.")
                continue
            }

            if (amount > balance)
            {
                println("Error: Insufficient balance.")
                continue
            }

            withdrawAmount = amount
            break
        }

        val newBalance = balance - withdrawAmount

        println("Updated Balance: ${newBalance}")
        return newBalance // para maupdate balance sa main
}

/**
 * Performs a currency exchange.
 * @return N/A
 */
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

    while (true) // para magrestart loop kung may error sa input
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

        if (sourceRate == null)
            println("Error: No rate set for ${currencyNames[sourceCurrency - 1]}")

        if (exchangeRate == null)
            println("Error: No rate set for ${currencyNames[exchangeCurrency - 1]}")
        

        if (sourceRate == null || exchangeRate == null)
        {
            print("\nConvert another currency (Y/N)? ")
            
            if (readln().trim().lowercase() != "y")
                return // stop function

            else
                continue // start again from the top of the loop
        }

        val exchangeAmount = sourceAmount * sourceRate / exchangeRate // main output
        println("Exchange Amount: ${String.format("%.2f", exchangeAmount)}")

        print("\nConvert another currency (Y/N)? ")
        if (readln().trim().lowercase() != "y")
        {
            return //stop function
        }
    }
}

/**
 * Records a new exchange rate.
 * @return N/A
 */
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

/**
 * Displays the interest amount for a given account and balance.
 * @param accountName (String) The name of the account.
 * @param balance (Double) The current balance of the account.
 * @return N/A
 */
fun interestAmount(accountName: String, balance: Double)
{
    println("[SHOW INTEREST AMOUNT]\n")

    if (accountName.isEmpty())
    {
        println("Error: Please register an account name first.")
        return
    }

    println("Account Name: ${accountName}")
    println("Current Balance: ${balance}")
    println("Currency: PHP")
    println("Interest Rate: 5%")

    print("Total Number of Days: ")
    val days = readln().toInt()

    var newBalance = balance
    println("\n| Day | Interest | Balance |")
    for (i in 1..days)
    {
        val interest = balance * 0.05 / 365
        newBalance += interest

        println("| ${String.format("%3d", i)} | ${String.format("%8.2f", interest)} | ${String.format("%7.2f", newBalance)} |")
    }
}

/**
 * Clears the terminal screen.
 * @return N/A
 */
fun clearTerminal()
{
    val process = ProcessBuilder("cmd", "/c", "cls")
        .inheritIO()
        .start()

    process.waitFor()
}