### Developers: Dharma Vyas, Kamron Mason, Drew Belloff

# Virtual Vending Machine

This is a Java project that simulates a vending machine.

Users can view items, enter a slot code, pay for an item, and receive it if the item is available and enough money is entered.

The program also checks for common vending machine errors like sold out items, expired products, and insufficient payment.

## Features

- View vending machine inventory
- Buy items using slot codes
- Handles sold out items
- Prevents expired item purchases
- Checks for enough payment
- Admin mode
- Restock inventory
- Inventory loaded from a file

## Files

### Main.java
Starts the program and handles user input.

### VendingMachine.java
Contains the main vending machine logic.

### Item.java
Parent class for all item types.

### Snack.java
Represents snack items.

### Beverage.java
Represents drink items.

### PerishableItem.java
Represents items with expiration dates.

### InventorySlot.java
Stores item quantity and stock information.

### PaymentProcessor.java
Defines payment rules.

### Exception files
Handle vending machine errors.

### Inventory
Stores all item data.

## Concepts Used

- Abstraction
- Encapsulation
- Inheritance
- Method Overriding
- Interfaces
- Generics
- File Handling
- Collections
- Exception Handling

## How to Run

1. Open the project in IntelliJ
2. Make sure the inventory file is in the src folder
3. Run Main.java

## Admin Mode

Type:

ADMIN

Options:
- View inventory
- Restock items
- Exit admin mode

## Example Inventory Format

A1,Snack,Potato Chips,1.50,5,true

B2,Beverage,Hot Coffee,2.50,10,false

C1,Perishable,Turkey Sandwich,4.50,3,false,2023-01-01
