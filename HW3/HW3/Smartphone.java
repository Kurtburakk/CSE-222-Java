/**
 * A class representing a Smartphone device.
 * Time Complexity: O(1) for all methods.
 */
public class Smartphone implements Device {

    private final String category = "Smart Phone ";
    private String name;
    private double price;
    private int quantity;

    /**
     * Constructs a new Smartphone with the given name, price, and quantity.
     * 
     * @param name     the name of the Smartphone
     * @param price    the price of the Smartphone
     * @param quantity the quantity of the Smartphone
     * Time Complexity: O(1)
     */
    public Smartphone(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
    
    @Override
    public String getCategory() {
        return category;
        // Time Complexity: O(1)
    }

    @Override
    public String getName() {
        return name;
        // Time Complexity: O(1)
    }

    @Override
    public double getPrice() {
        return price;
        // Time Complexity: O(1)
    }

    @Override
    public int getQuantity() {
        return quantity;
        // Time Complexity: O(1)
    }

    @Override
    public void setName(String name) {
        this.name = name;
        // Time Complexity: O(1)
    }

    @Override
    public void setPrice(double price) {
        this.price = price;
        // Time Complexity: O(1)
    }

    @Override
    public void setQuantity(int quantity) {
        this.quantity = quantity;
        // Time Complexity: O(1)
    }
}
