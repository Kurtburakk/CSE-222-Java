import java.util.Scanner;

/**
 * Main class for the Electronics Inventory Management System.
 * This system includes basic operations with the inventory.
 * 
 * @Author: Burak Kurt
 * @Date: March 23, 2024 
 */
public class Main {
    
    /**
     * Main method to run the program.
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Inventory instance
        Inventory inventory = new Inventory();

        // Valid categories for input validation
        String[] validCategories = {"Laptop", "Smart Phone", "TV", "Smart Watch", "Headphones"};                        

        try (Scanner scanner = new Scanner(System.in)) {
            String category;
            String name;
            double price;
            int quantity;

            while (true) {
                System.out.println("Welcome to the Electronics Inventory Management System!");
                System.out.println("Please select an option:");
                System.out.println("1. Add a new device");
                System.out.println("2. Remove a device");
                System.out.println("3. Update device details");
                System.out.println("4. List all devices");
                System.out.println("5. Find the cheapest device");
                System.out.println("6. Sort devices by price");
                System.out.println("7. Calculate total inventory value");
                System.out.println("8. Restock a device");
                System.out.println("9. Export inventory report");
                System.out.println("0. Exit");
                System.out.print("Your choice: ");
                int choice = 0;

                try {
                    choice = scanner.nextInt();
                    scanner.nextLine();
                } catch (Exception e) {
                    System.out.println("Invalid input. Please enter a valid number.");
                }

                switch (choice) {    
                    case 1:
                        try {
                            System.out.print("Enter category name: ");
                            category = scanner.nextLine();
                            boolean checkCategory = false;
                            // Time Complexity: O(1) (constant operation)
                            for (String validCategory : validCategories) {
                                if (category.equals(validCategory)) {
                                    checkCategory = true;
                                    break;
                                }
                            }
                            if (!checkCategory) {
                                throw new IllegalArgumentException("Invalid category.");
                            }
                            System.out.print("Enter device name: ");
                            name = scanner.nextLine();
                            System.out.print("Enter price: ");
                            String priceInput = scanner.nextLine();
                            priceInput = priceInput.replace("$", "");
                            price = Double.parseDouble(priceInput);
                            System.out.print("Enter quantity: ");
                            String quantityInput = scanner.nextLine().trim(); // Trim the input string
                            quantity = Integer.parseInt(quantityInput);
                            inventory.addDevice(category, name, price, quantity);
                            // Time Complexity: O(n) (because this method uses findCategory method and O(n) is bigger than O(m) where this operation checks one category device)
                        } catch (IllegalArgumentException e) {
                            System.out.println("Invalid input");
                            scanner.nextLine(); 
                        } 
                        break;
                    case 2:
                        try {
                            System.out.print("Enter device name: ");
                            name = scanner.nextLine();
                            inventory.removeDevice(name);
                            // Time Complexity: O(n*m) (worst-case because use findDeviceIndex method; other operations are constant O(1))
                        } catch (Exception e) {
                            System.out.println("Invalid input. Please enter valid data.");
                        }
                        break;
                    case 3:
                        try {
                            System.out.print("Enter the name of the device to update: ");
                            name = scanner.nextLine();
                            System.out.print("Enter new price (leave blank to keep current price): ");
                            String priceInput = scanner.nextLine().trim();
                            if(priceInput.isEmpty())
                                price = 0.0;
                            else{
                                priceInput = priceInput.replace("$", "");
                                price = Double.parseDouble(priceInput);
                            }
                            System.out.print("Enter new quantity (leave blank to keep current quantity): ");
                            String quantityInput = scanner.nextLine().trim(); // Trim the input string
                            quantity = quantityInput.isEmpty() ? 0 : Integer.parseInt(quantityInput);

                            inventory.updateDevice(name, price, quantity);
                            // Time Complexity: O(n*m) (worst case because use findDeviceIndex method)
                        } catch (Exception e) {
                            System.out.println("Invalid input. Please enter valid data.");
                        }
                        break;
                    case 4:
                        System.out.println("\nDevice List:");
                        inventory.displayAllDevices();
                        // Time Complexity: O(n*m) (worst-case assume deviceList.size() n and deviceList.get(i).size() m; other operations are constant)
                        break;
                    case 5:
                        inventory.findCheapestDevice();
                        // Time Complexity: O(n*m) (worst-case assume deviceList.size() n and list.size() m; other operations are constant)
                        break;
                    case 6:
                        inventory.sortDevices();
                        // Time Complexity: O(n*log(n)) (n is the total length of allDevices, and this method belongs to Java)
                        break;
                    case 7:
                        System.out.println("\nTotal inventory value: $" + inventory.calculateTotalValue() + "\n" );
                        // Time Complexity: O(n*m) (worst-case assume deviceList.size() n and list.size() m; other operations are constant)
                        break;
                    case 8:
                        try {
                            System.out.print("Enter the name of the device to restock: ");
                            name = scanner.nextLine();
                            System.out.print("Do you want to add or remove stock? (Add/Remove): ");
                            String action = scanner.nextLine();
                            System.out.print("Enter the quantity to " + action + ": ");
                            String quantityInput = scanner.nextLine().trim(); // Trim the input string
                            quantity = Integer.parseInt(quantityInput);
                            inventory.restockDevice(name, action, quantity);
                            System.out.println();
                            // Time Complexity: O(n*m) (because use findDeviceIndex method)
                        } catch (Exception e) {
                            System.out.println("Invalid input. Please enter valid data.");
                        }
                        break;
                    case 9:
                        inventory.reportFile();
                        // Time Complexity: O(n*m) (worst-case assume deviceList.size() n and list.size() m; other operations are constant)
                        break;
                    case 0:
                        System.out.println("Exiting...");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        }
    }
}

