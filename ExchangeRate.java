import java.util.*;
import java.math.*;

public class ExchangeRate {
    // conversion and assignment of new rates
    // implementation of an enum map, like an array of objects
    // stores rates and does conversions

    /**
     * Currency is like the type of enum class
     * double is the data type associated with each type
     */
    private Map<Currency, Double> rates = new EnumMap<>(Currency.class);

    // for recording exchange rate
    // sets rate only when valid enum and valid rate
    public boolean setRate(Currency currency, Double rate){
        if (currency == null || currency == Currency.PHP || rate <= 0){
            return false;
        }

        // places in enum
        rates.put(currency, rate);
        return true;
    }

    // returns true if rate is in the map, false otherwise
    public boolean ifRateExists(Currency currency){
        return currency == Currency.PHP || rates.containsKey(currency);
    }

    // conversion
    public double convert(double amount, Currency from, Currency to){
        double amountInPhp, convertedAmt;

        if(from == to){
            return round(amount);
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

        return round(convertedAmt);
    }

    private double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}