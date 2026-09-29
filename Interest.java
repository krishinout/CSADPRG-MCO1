import java.util.*;

/**
 * This class contains the computation of daily interest, annual interest, and expected daily interest
 * Each daily entry is represented by a record which contains the day, interest, and updated balance
 * List of entries are created for easy access, especially when called during the menu class
 */

public class Interest {

    private static final double ANNUAL_RATE = 0.05;
    public record DailyEntry(int day, double interest, double balance){} // records parang tuples

    public double dailyInterest(double balance){
        return balance * (ANNUAL_RATE / 365);
    }

    public double annualInterest(double balance){
        return balance * ANNUAL_RATE;
    }

    public List<DailyEntry> ExpectedDailyInterest(int days, double startBalance){
        ArrayList<DailyEntry> entries = new ArrayList<>(); // creates the arrayList that stores each day entry
        double newBalance = startBalance;

        for(int i=0; i<n; i++){
            double interest = dailyInterest(newBalance); // computes for interest and adds to new balance
            newBalance+=interest;

            entries.add(new DailyEntry(i+1, interest, newBalance));
        }

        return entries;
    }
}