package com.techelevator;

public class Product {

    private String name;
    private double price;
    private String type;
    private int quantity;

    public Product(String name, double price, int quantity, String type) {
        this.name = name;
        this.price = price;
        this.type = type;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }


    public String getType() {
        return type;
    }

    public int getQuantity(){
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


}