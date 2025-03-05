package ShoppingCart;

import java.util.*;

class Item {
    private  int itemId;
    private String name;
    private double price;

    public Item(int itemId, String name, double price) {
        this.itemId = itemId;
        this.name = name;
        this.price = price;
    }

    public int getItemId() {
        return itemId;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
    public String toString() {
        return "ID: " + itemId + " | " + name + " | Price: $" + price;
    }
}

class ShoppingCart {
    private  Map<Item, Integer> cart = new HashMap<>();

    public void addToCart(Item item, int quantity) {
        cart.put(item, cart.getOrDefault(item, 0) + quantity);
        System.out.println(quantity + " x " + item.getName() + " added to cart.");
    }

    public void showCart() {
        if (cart.isEmpty()) {
            System.out.println("Cart is empty.");
        } else {
            System.out.println("\nYour Cart:");
            for (Map.Entry<Item, Integer> entry : cart.entrySet()) {
                System.out.println(entry.getKey() + " | Quantity: " + entry.getValue());
            }
        }
    }

    public void updateQty(Item item, int quantity) {
        if (cart.containsKey(item)) {
            cart.put(item, quantity);
            System.out.println("Updated " + item.getName() + " quantity to " + quantity);
        } else {
            System.out.println("Item not found in cart!");
        }
    }

    public void deleteItem(Item item) {
        if (cart.containsKey(item)) {
            cart.remove(item);
            System.out.println(item.getName() + " removed from cart.");
        } else {
            System.out.println("Item not found in cart!");
        }
    }

    public void displayBill() {
        double total = 0;
        for (Map.Entry<Item, Integer> entry : cart.entrySet()) {
            total += entry.getKey().getPrice() * entry.getValue();
        }
        System.out.println("Total Bill: $" + total);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ShoppingCart cart = new ShoppingCart();

        
        Map<Integer, Item> items = new HashMap<>();
        items.put(1, new Item(1, "Laptop", 900.0));
        items.put(2, new Item(2, "Smartphone", 700.0));
        items.put(3, new Item(3, "Headphones", 120.0));
        items.put(4, new Item(4, "Mouse", 30.0));
        items.put(5, new Item(5, "Keyboard", 80.0));
        items.put(6, new Item(6, "Monitor", 300.0));
        items.put(7, new Item(7, "Printer", 200.0));
        items.put(8, new Item(8, "External SSD", 150.0));
        items.put(9, new Item(9, "Gaming Chair", 250.0));
        items.put(10, new Item(10, "Smart Watch", 180.0));

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. View Available Items");
            System.out.println("2. Add Item to Cart");
            System.out.println("3. Show Cart");
            System.out.println("4. Update Quantity");
            System.out.println("5. Remove Item");
            System.out.println("6. View Total Bill");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("\nAvailable Items:");
                    for (Item item : items.values()) {
                        System.out.println(item);
                    }
                    break;

                case 2:
                    System.out.print("\nEnter Item ID to add: ");
                    int itemId = scanner.nextInt();
                    System.out.print("Enter quantity: ");
                    int quantity = scanner.nextInt();

                    if (items.containsKey(itemId)) {
                        cart.addToCart(items.get(itemId), quantity);
                    } else {
                        System.out.println("Invalid Item ID!");
                    }
                    break;

                case 3:
                    cart.showCart();
                    break;

                case 4:
                    System.out.print("\nEnter Item ID to update: ");
                    int updateId = scanner.nextInt();
                    System.out.print("Enter new quantity: ");
                    int newQty = scanner.nextInt();

                    if (items.containsKey(updateId)) {
                        cart.updateQty(items.get(updateId), newQty);
                    } else {
                        System.out.println("Invalid Item ID!");
                    }
                    break;

                case 5:
                    System.out.print("\nEnter Item ID to remove: ");
                    int removeId = scanner.nextInt();

                    if (items.containsKey(removeId)) {
                        cart.deleteItem(items.get(removeId));
                    } else {
                        System.out.println("Invalid Item ID!");
                    }
                    break;

                case 6:
                    cart.displayBill();
                    break;

                case 7:
                    System.out.println("Exiting program...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}

