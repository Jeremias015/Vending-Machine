package com.techelevator;


import java.util.Map;


public abstract class VendingMachine {
    protected Map<String, Product> inventory;
    protected double currentBalance;
    protected double totalSales;
    protected Log logger;
    // used so it can be used outside this class is the descending classes

    // constructor
    public VendingMachine(Map<String, Product> inventory, Log logger) {
        this.inventory = inventory;
        this.currentBalance = 0.0;
        this.totalSales = 0.0;
        this.logger = logger;
    }

    public abstract void displayItems();
    public abstract void feedMoney(double amount);
    public abstract void selectProduct(String slot);
    public abstract void finishTransaction();
    protected abstract String getDispenseMessage(String type);

    public double getTotalSales() {
        return totalSales;
    }

    public Map<String, Product> getInventory() {
        return inventory;
    }


}


