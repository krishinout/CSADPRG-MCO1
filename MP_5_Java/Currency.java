/**
 * Enum representing the given currencies.
 * Each currency stores its full name, code, and indicator if it is a base currency.
 */

public enum Currency {

    // enum constants in the format: full name, code, isBase
    PHP("Philippine Peso", "PHP", true),
    USD("United States Dollar", "USD", false),
    JPY("Japanese Yen", "JPY", false),
    GBP("British Pound Sterling", "GBP", false),
    EUR("Euro", "EUR", false),
    CNY("Chinese Yuan Renminbi", "CNY", false);

    // attributes
    private final String fullName;
    private final String code;
    private final boolean isBase;

    /**
     * Constructor that stores the display name, code, and base-currency indicator
     * @param fullName represents full name of the currency.
     * @param code represents its ISO code.
     * @param isBase represents a base-currency indicator, true if base-currency and false otherwise.
     */
    Currency(String fullName, String code, boolean isBase){
        this.fullName = fullName;
        this.code = code;
        this.isBase = isBase;
    }

    // getters and setters
    public String getFullName(){
        return fullName;
    }

    public String getCode(){
        return code;
    }

    public boolean isBase(){
        return isBase;
    }
}
 