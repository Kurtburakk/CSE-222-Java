import java.util.Comparator;

/**
 * A comparator for comparing Device objects based on their prices.
 * Time Complexity: O(1) for all methods.
 */
public class DevicePriceComparator implements Comparator<Device> {

    /**
     * Compares two Device objects based on their prices.
     * 
     * @param d1 the first Device object
     * @param d2 the second Device object
     * @return int a negative integer, zero, or a positive integer as the first device's price is less than, equal to, or greater than the second device's price.
     * Time Complexity: O(1). This method performs basic comparison operations.
     */
    @Override
    public int compare(Device d1, Device d2) {
        return Double.compare(d1.getPrice(), d2.getPrice());
    }
}
