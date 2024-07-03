package com.techelevator;

import java.io.IOException;
import java.util.Scanner;

public class Application {

	public static void main(String[] args) {

		Inventory inventory = new Inventory();
		Vendo800 vendingMachine = null;

		try {
			vendingMachine = new Vendo800(inventory.loadInventory("vendingmachine.csv"), new Transactions());
		} catch (IOException e) {
			System.err.println("Inventory load fail: " + e.getMessage());
			System.exit(1); //(Status 1) is not a normal exit of the program
		}  // will give an error if inventory fails to load

		Scanner scanner = new Scanner(System.in);
		new Sound("Start Up.wav");
		while(true) {
			System.out.println("**********Welcome to the Vendo-Matic 800**********");
			System.out.println("---------------------------------------------------");
			System.out.println("**************Courtesy of CuteCo, Inc**************");
			System.out.println("----The #1 Supplier of all Things Cute and Fuzzy----");
			System.out.println();
			System.out.println("***********Main Menu**************");
			System.out.println("(1) Display Vending Mahchine Items");
			System.out.println("(2) Purchase");
			System.out.println("(3) Exit");
			System.out.println("(4) Generate Sales Report"); //clickable but customers cannot see the report Printed out

			String menuPick = scanner.nextLine();

			switch (menuPick) { //if and if-else statements
				case "1":		//will run the appropriate method once a valid number is picked
					vendingMachine.displayItems();
					break;
				case "2":
					handlePurchase(scanner, vendingMachine);
					break;
				case "3":
				new Sound("Shut Down.wav");
					try{
						Thread.sleep(2000);
					}catch (InterruptedException e){
						System.out.println(e.getMessage());
					}
					System.exit(0);
					break;
				case "4":
					SalesReport.generateReport(vendingMachine);
					break;
				default:
					System.out.println("Not a valid choice");
			}
		}
	}

	public static void handlePurchase(Scanner scanner, Vendo800 vendingMachine)	{
		while (true) {
			System.out.println("****Purchase Menu****");
			System.out.println("Current Money Provided: $" + String.format("%.2f", vendingMachine.currentBalance));
			System.out.println("(1) Feed Me Money");
			System.out.println("(2) Select Product");
			System.out.println("(3) Finish Transaction");
			System.out.println("***Please Make Sure to Have a Balance Before Making a Purchase**");

			String choice = scanner.nextLine();  // will scan in the user choice

			switch (choice) {   // if choice equals appropriate number will run the method chosen
				case "1":
					System.out.println("Enter whole dollar amount to feed:");
					double amount = Double.parseDouble(scanner.nextLine());
					vendingMachine.feedMoney(amount);
					break;
				case "2":
					System.out.println("Enter Slot #:");
					vendingMachine.displayItems();
					String slot = scanner.nextLine();
					vendingMachine.selectProduct(slot);
					break;
				case "3":
					vendingMachine.finishTransaction();
					return;
				default:
					System.out.println("Not a valid choice");
			}
		}
	}
}
