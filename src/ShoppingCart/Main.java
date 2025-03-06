package ShoppingCart;

import java.util.*;

class Item {
	private int itemId;
    private String name;
    private String description;
    private double price; 
    
    public Item(int itemId, String name, String description, double price) {
        this.itemId = itemId;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    // By using getter and setter we can access our private fields of the item
    public int getItemId() {
        return itemId;
    }

    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public String toString() {
        return name + " ( ₹" + String.format("%.2f",price) + " )";
    }
}

class ShoppingCart {
	//We are Using HashMap to store the item and the quantity of the item 
		//Here Item is the key We are using an object as a key 
	    private Map<Item, Integer> cart; 

	    //Initializes the cart field with new Hashmap 
	    //When ever the object of this Shopping cart will be created

	    public ShoppingCart() {
	        this.cart = new HashMap<>();
	    }

	    public void addToCart(Item item, int quantity) {
	    	//Adding item to cart or increase the Quantity if already exists
	        cart.put(item, cart.getOrDefault(item, 0) + quantity); //Either creates a new Entry in the hashmap or uodate the existing entry
	        System.out.println(quantity + " " + item.getName() + "(s) added to your cart.");
	    }

	    public Integer displayQty(Item item) {
	    	//Returns the quantity of the given item in cart
	        return cart.getOrDefault(item, 0); 
	    }

	    public void updateQty(Item item, int quantity) {
	    	//Updates the Quantity of an item in the cart
	        if (cart.containsKey(item)) { 
	            cart.put(item, quantity); 
	            System.out.println("Updated quantity of " + item.getName() + " to " + quantity);
	        } else {
	            System.out.println("Item not found in cart!");
	        }
	    }

	    public void deleteItem(Item item) {
	    	//Removes an item from the cart
	        if (cart.remove(item) != null) { 
	            System.out.println(item.getName() + " removed from cart.");
	        } else {
	            System.out.println("Item not found in cart!");
	        }
	    }

	    public Double displayBill() {
	    	//Calculates and display the total bill
	        double total = 0; 
	        for (Map.Entry<Item, Integer> entry : cart.entrySet()) { 

	            //Where Map.Entry is representing the Key Value Pairs
	            total += entry.getKey().getPrice() * entry.getValue(); 
	            //entry.getKey()Gets the item object from the current entry
	            //entry.getValue()Gets the quantity
	        }

	        System.out.println("Total Bill: " +total); 
	        return total;
	    }

	    public void showCart() {
	    	//Display the Cart
	        if (cart.isEmpty()) { 
	            System.out.println("Your cart is empty.");
	            return;
	        }

	        System.out.println("Your Shopping Cart:");
	        double subtotal = 0; 

	        for (Map.Entry<Item, Integer> entry : cart.entrySet()) { 
	            Item item = entry.getKey();
	            int quantity = entry.getValue(); 
	            double itemTotal = item.getPrice() * quantity; 
	            subtotal += itemTotal; 

	            System.out.println(item + " - Quantity: " + quantity+" Total :-"+item.getPrice()*quantity); // Displays the item, quantity, and item total.
	        }

	        System.out.println("-----------------------------");
	        System.out.println("Subtotal: " +subtotal); 
	    }
	    }

public class Main {
	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); 

        ShoppingCart cart = new ShoppingCart();

        // Sample items
        Map<Integer, Item> items = new HashMap<>();
        Item prod_1=new Item(1, "Laptop", "High-performance laptop", 90000.0);
        Item prod_2=new Item(2, "Smartphone", "Latest model smartphone", 70000.0);
        Item prod_3=new Item(3, "Headphones", "Noise-canceling headphones", 12000.0);
        Item prod_4=new Item(4, "Tablet", "Portable tablet device", 30000.0);
        Item prod_5=new Item(5, "Smartwatch", "Fitness tracking smartwatch", 20000.0);
        Item prod_6=new Item(6, "Keyboard", "Mechanical keyboard", 5000.0);
        Item prod_7=new Item(7, "Mouse", "Wireless mouse", 2000.0);
        Item prod_8=new Item(8, "Monitor", "High-resolution monitor", 40000.0);
        Item prod_9=new Item(9, "Printer", "Laser printer", 25000.0);
        items.put(1, prod_1 );
        items.put(2, prod_2);
        items.put(3, prod_3);
        items.put(4, prod_4);
        items.put(5, prod_5);
        items.put(6, prod_6);
        items.put(7, prod_7);
        items.put(8, prod_8);
        items.put(9, prod_9);

        while (true) { 
            System.out.println("\nWelcome to the Shopping Cart!");
            System.out.println("1. View Available Items");
            System.out.println("2. Add to Cart");
            System.out.println("3. Show Cart");
            System.out.println("4. Update Quantity");
            System.out.println("5. Remove Item");
            System.out.println("6. Display Bill");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) { 
                case 1:
                    System.out.println("\nAvailable Items:");
                    for (Map.Entry<Integer, Item> entry : items.entrySet()) { 
                        System.out.println(entry.getKey() + ". " + entry.getValue()+"- Description: "+entry.getValue().getDescription()); // Displays the item ID and the item details.
                    }
                    break;
                case 2:
                    System.out.print("Enter Item ID to add to cart: ");
                    int itemId = scanner.nextInt(); 
                    System.out.print("Enter quantity: ");
                    int quantity = scanner.nextInt(); 
                    scanner.nextLine(); 

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
                    System.out.print("Enter Item ID to update: ");
                    int updateId = scanner.nextInt();
                    System.out.print("Enter new quantity: ");
                    int newQty = scanner.nextInt(); 
                    scanner.nextLine();

                    if (items.containsKey(updateId)) { 
                        cart.updateQty(items.get(updateId), newQty);
                    } else {
                        System.out.println("Invalid Item ID!");
                    }
                    break;
                case 5:
                    System.out.print("Enter Item ID to remove: ");
                    int removeId = scanner.nextInt();
                    scanner.nextLine();

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
