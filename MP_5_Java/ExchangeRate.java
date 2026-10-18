import java.util.*;

/**
 * Stores the exchange rate for each foreign currency (is relative to PHP)
 * and performs conversions between the currencies.
 * Uses an EnumMap which allows each Currency to map to its rate.
 */

public class ExchangeRate {
    
    /**
     * Maps each currency to its rate relative to PHP.
     * PHP not stored as fixed at 1.00.
     */
    private final Map<Currency, Double> rates = new EnumMap<>(Currency.class);

    /**
     * Sets the exchange rate for the given currency.
     * @param currency is the Currency instance to set the rate for.
     * @param rate represents the rate relative to PHP
     */
    public void setRate(Currency currency, Double rate){
        // places in enum
        rates.put(currency, rate);
    }

    /**
     * Checks if a rate is set/available for the given currency.
     * @param currency is the Currency instance to check.
     * @return true if a rate exists, false otherwise.
     */
    public boolean ifRateExists(Currency currency){
        return currency == Currency.PHP || rates.containsKey(currency);
    }

    /**
     * Converts an amount from one currency to another with PHP as base.
     * @param amount is the amount to convert.
     * @param from represents the source Currency instance.
     * @param to represents the target Currency instance.
     * @return the converted amount as a double.
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

        // converts php to target currency
        if(to == Currency.PHP){
            convertedAmt = amountInPhp;
        } else {
            convertedAmt = amountInPhp / rates.get(to);
        }

        return convertedAmt;
    }
}