package com.techelevator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class Inventory {

    private static final String FILENAME = "vendingmachine.csv";
        // setting our FILENAME to final because we do not want it to be changed
    public Map<String, Product> loadInventory(String s) throws IOException {
        Map<String, Product> inventory = new HashMap<>();
        List<String> lines = Files.readAllLines(Paths.get(FILENAME));

        for (String line: lines) { // for each will go through each line
            String[] parts = line.split(",");  // will split all items on the comma
            String slot = parts[0];
            String name = parts[1];
            double price = Double.parseDouble(parts[2]);
            String type = parts[3];
            int quantity = Integer.parseInt(parts[4]);
            inventory.put(slot, new Product(name, price, quantity, type));
        }
        return inventory;
    }

}
