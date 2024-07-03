package com.techelevator;

import java.util.HashMap;
import java.util.Map;

public class Vendo800 extends VendingMachine {

    private final Map<String, String> dispenseMessages;

    public Vendo800(Map<String, Product> inventory, Log logger) {
        super(inventory, logger);  // call the loaded Inventory and also make sure to log transactions
        dispenseMessages = new HashMap<>();
        dispenseMessages.put("Duck", "Quack, Quack, Splash!");
        dispenseMessages.put("Penguin", "Squawk, Squawk, Whee!");
        dispenseMessages.put("Cat", "Purr, Purr, Meow!");
        dispenseMessages.put("Pony", "Neigh, Neigh, Yay!");
    }

    @Override  // in the Application Case 1 "Display Machine Items"
    public void displayItems() {
        for (String slot : inventory.keySet()) {
            Product product = inventory.get(slot);
            String status = product.getQuantity() > 0 ? product.getQuantity() + " remaining" : "Sold Out!";
            System.out.println("Slot: " + slot + " | Name: " + product.getName() +
                            " | Status: " + status + " | Price: $" + String.format("%.2f", product.getPrice()));
        }
    }

    @Override // in Application "Purchase Menu" Case 1 "Feed Money"
    public void feedMoney (double amount) {
        currentBalance += amount;
        logger.logs("Insert Money", amount, currentBalance);
        new Sound("Money sound.wav");
    }

    @Override // in Application "Purchase Menu" Case 2: "Select Product" slot
    public void selectProduct(String slot) {
        if (!inventory.containsKey(slot)) {
            System.out.println("Invalid Slot");
            return;
        }

        Product product = inventory.get(slot); // prints out if item is out of stock
        if(product.getQuantity() == 0) {
            System.out.println("Sold Out!");
            return;
        }

        if(currentBalance < product.getPrice()) { // if the customer does not have enough cash
            System.out.println("**Not enough CASH! Stranger**");
            return;
        }

        //runs when they the product is chosen and they have enough money: transaction is logged at the end
        // tied to getDispenseMessage at the bottom of this class
        product.setQuantity(product.getQuantity() - 1);
        currentBalance -= product.getPrice();
        totalSales += product.getPrice();
        String message = getDispenseMessage(product.getType());
        System.out.println(product.getName() + " dispensed. Cost: $" + String.format("%.2f", product.getPrice()) +
                ", Remaining balance: $" + String.format("%.2f", currentBalance));
        System.out.println(message);
        logger.logs(product.getName() + " " + slot, product.getPrice(), currentBalance);
    }

    @Override // in Application: Case 3 "Finish".  Once the customer completes they are given their change
    public void finishTransaction() {
        int quarters = (int) (currentBalance / 0.25);
        currentBalance %= 0.25;
        int dimes = (int) (currentBalance / 0.10);
        currentBalance %= 0.10;
        int nickels = (int) (currentBalance / 0.05);
        currentBalance = 0;
        System.out.println("Change: " + quarters + " quarters, " + dimes + " dimes " + nickels + " nickels ");
        logger.logs("Give Change", currentBalance, 0.0);
        new Sound("Money sound.wav");
    }

    @Override //tied into the selectProduct method in this class
    protected String getDispenseMessage(String type) {
        new Sound("Money sound.wav");
        new Sound(type + " Sound.wav");
        return dispenseMessages.getOrDefault(type, "");
    }










}
