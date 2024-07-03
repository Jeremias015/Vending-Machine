package com.techelevator;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class SalesReport {
// will generate data based on the Vending Machine Sales Data, prints out to a txt file
    public static void  generateReport (VendingMachine vendingMachine) {
        String filename = "SalesReport " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")) + ".txt";
        try (FileWriter fw = new FileWriter(filename)) {
            Map<String, Product> inventory = vendingMachine.getInventory();
            for (String slot : inventory.keySet()) {
                Product product = inventory.get(slot);
                fw.write(product.getName() + "," + (5 - product.getQuantity() + "\n")); //  \n is a break in the line
            }
            fw.write(("\nTotal Sales $" + String.format("%.2f", vendingMachine.getTotalSales()) + "\n"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
