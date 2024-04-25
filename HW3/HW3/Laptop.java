/**
 * A class representing a Laptop device.
 * Implements the Device interface.
 */
public class Laptop implements Device {

    private final String category = "Laptop";
    private String name;
    private double price;
    private int quantity;

    /**
     * Constructs a Laptop object with the specified name, price, and quantity.
     * 
     * @param name the name of the Laptop
     * @param price the price of the Laptop
     * @param quantity the quantity of the Laptop
     * Time complexity: O(1).
     */
    public Laptop(String name, double price, int quantity) {
        this.name = name;       // O(1).
        this.price = price;     // O(1).
        this.quantity = quantity;   // O(1).
    }
    
    /**
     * Returns the category of the Laptop.
     * 
     * @return String the category of the Laptop
     * Time complexity: O(1).
     */
    @Override
    public String getCategory() {
        return category;
    }
    
    /**
     * Returns the name of the Laptop.
     * 
     * @return String the name of the Laptop
     * Time complexity: O(1).
     */
    @Override
    public String getName() {
        return name;
    }
    
    /**
     * Returns the price of the Laptop.
     * 
     * @return double the price of the Laptop
     * Time complexity: O(1).
     */
    @Override
    public double getPrice() {
        return price;
    }
    
    /**
     * Returns the quantity of the Laptop.
     * 
     * @return int the quantity of the Laptop
     * Time complexity: O(1).
     */
    @Override
    public int getQuantity() {
        return quantity;
    }
    
    /**
     * Sets the name of the Laptop.
     * 
     * @param name the new name of the Laptop
     * Time complexity: O(1).
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Sets the price of the Laptop.
     * 
     * @param price the new price of the Laptop
     * Time complexity: O(1).
     */
    @Override
    public void setPrice(double price) {
        this.price = price;
    }
    
    /**
     * Sets the quantity of the Laptop.
     * 
     * @param quantity the new quantity of the Laptop
     * Time complexity: O(1).
     */
    @Override
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
