package ShoppingCart;

import java.util.*;

class Item {
    private  int itemId;
    private String name;
    private double price;
    private String Description;

    public Item(int itemId, String name,String Description, double price) {
        this.itemId = itemId;
        this.name = name;
        this.Description=Description;
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
    public String getDesc() {
    	return Description;
    }
    public String toString() {
        return "ID: " + itemId + " | " + name +" |  "+Description+ " | Price: " + price;
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
        System.out.println("Total Bill: Rs" + total);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ShoppingCart cart = new ShoppingCart();

        
        Map<Integer, Item> items = new HashMap<>();
        items.put(1, new Item(1, "Laptop","Windows 11 full time MS office", 111900.0));
        items.put(2, new Item(2, "Smartphone","Latest Model with latest processor", 17000.0));
        items.put(3, new Item(3, "Headphones","Noise Cancelling", 11120.0));
        items.put(4, new Item(4, "Mouse","Super Fast", 1030.0));
        items.put(5, new Item(5, "Keyboard","Compact", 1180.0));
        items.put(6, new Item(6, "Monitor","Large and HD", 1300.0));
        items.put(7, new Item(7, "Printer","Color Printer", 2200.0));
        items.put(8, new Item(8, "External SSD","512 GB", 11150.0));
        items.put(9, new Item(9, "Gaming Chair", "Comfortable",22250.0));

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
