import java.util.*;

/**
 * This class is for the storage of rates and conversion of rates
 * Makes use of an enum map, like a dictionary for easy storage and access of currency details
 */

public class ExchangeRate {
    /**
     * NOTE:
     * Currency is like the type of enum class
     * double is the data type associated with each type
     */
    private final Map<Currency, Double> rates = new EnumMap<>(Currency.class);

    /**
     * Sets the rate for each currency, and only returns true when successful
     * Done for validation
     */
    public void setRate(Currency currency, Double rate){
        // places in enum
        rates.put(currency, rate);
    }

    /**
     * Checks if a rating is set, calling before conversion
     */
    public boolean ifRateExists(Currency currency){
        return currency == Currency.PHP || rates.containsKey(currency);
    }

    /**
     * Conversion from one currency to another
     */
    public double convert(double amount, Currency from, Currency to){
        double amountInPhp, convertedAmt;

        if(from == to){ // if base currency(PHP) return as is
            return amount;
        }
 
        // converts amount to php
        if(from == Currency.PHP){
            amountInPhp = amount;
        } else {
            amountInPhp = amount * rates.get(from);
        }

        // converts php to other currency
        if(to == Currency.PHP){
            convertedAmt = amountInPhp;
        } else {
            convertedAmt = amountInPhp / rates.get(to);
        }

        return convertedAmt;
    }
}