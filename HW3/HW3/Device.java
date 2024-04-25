/**
 * The interface for a Device.
 */
public interface Device {

    /**
     * Gets the category of the device.
     * 
     * @return the category of the device
     */
    public String getCategory();

    /**
     * Gets the name of the device.
     * 
     * @return the name of the device
     */
    public String getName();
    
    /**
     * Gets the price of the device.
     * 
     * @return the price of the device
     */
    double getPrice();
    
    /**
     * Gets the quantity of the device.
     * 
     * @return the quantity of the device
     */
    int getQuantity();
    
    /**
     * Sets the name of the device.
     * 
     * @param name the name to set
     */
    void setName(String name);
    
    /**
     * Sets the price of the device.
     * 
     * @param price the price to set
     */
    void setPrice(double price);
    
    /**
     * Sets the quantity of the device.
     * 
     * @param quantity the quantity to set
     */
    void setQuantity(int quantity);
}
