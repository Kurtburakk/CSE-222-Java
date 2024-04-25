/**
 * A class representing a Headphones device.
 * Implements the Device interface.
 */
public class Headphones implements Device {

    private final String category = "Headphones";
    private String name;
    private double price;
    private int quantity;

    /**
     * Constructs a Headphones object with the specified name, price, and quantity.
     * 
     * @param name the name of the Headphones
     * @param price the price of the Headphones
     * @param quantity the quantity of the Headphones
     * Time complexity: O(1).
     */
    public Headphones(String name, double price, int quantity) {
        this.name = name;       // O(1).
        this.price = price;     // O(1).
        this.quantity = quantity;   // O(1).
    }
    
    /**
     * Returns the category of the Headphones.
     * 
     * @return String the category of the Headphones
     * Time complexity: O(1).
     */
    @Override
    public String getCategory() {
        return category;
    }
    
    /**
     * Returns the name of the Headphones.
     * 
     * @return String the name of the Headphones
     * Time complexity: O(1).
     */
    @Override
    public String getName() {
        return name;
    }
    
    /**
     * Returns the price of the Headphones.
     * 
     * @return double the price of the Headphones
     * Time complexity: O(1).
     */
    @Override
    public double getPrice() {
        return price;
    }
    
    /**
     * Returns the quantity of the Headphones.
     * 
     * @return int the quantity of the Headphones
     * Time complexity: O(1).
     */
    @Override
    public int getQuantity() {
        return quantity;
    }
    
    /**
     * Sets the name of the Headphones.
     * 
     * @param name the new name of the Headphones
     * Time complexity: O(1).
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Sets the price of the Headphones.
     * 
     * @param price the new price of the Headphones
     * Time complexity: O(1).
     */
    @Override
    public void setPrice(double price) {
        this.price = price;
    }
    
    /**
     * Sets the quantity of the Headphones.
     * 
     * @param quantity the new quantity of the Headphones
     * Time complexity: O(1).
     */
    @Override
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
