public enum Currency {

    // enum class where it has the full name, code, and indicator if base case
    PHP("Philippine Peso", "PHP", true),
    USD("United States Dollar", "USD", false),
    JPY("Japanese Yen", "JYP", false),
    GBP("British Pound Sterling", "GBP", false),
    EUR("EURO", "EUR", false),
    CNY("Chinese Yuan Renminni", "CNY", false);

    // attributes
    private final String fullName;
    private final String code;
    private final boolean isBase;

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
