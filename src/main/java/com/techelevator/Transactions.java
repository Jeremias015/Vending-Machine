package com.techelevator;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transactions implements Log{
    private static final String LOG_FILE = "Log.txt";

    // timStamp String = LocalDateTime.now will grab the time now
    // will log our transactions and format the time with the DateTimeFormatter.ofPattern which will print out the format given below
    // %.2f is shorthand to modify the number "up" to two decimal places
    //  \n is a line break
    @Override
    public void logs (String action, double amount, double balance) {
        try (FileWriter fw = new FileWriter(LOG_FILE, true)) {
            String timeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss a"));
            fw.write(timeStamp + " " + action + "; $" + String.format("%.2f", amount) + " $" + String.format("%.2f", balance) + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
