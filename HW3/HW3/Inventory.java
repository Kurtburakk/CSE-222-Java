import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Represents an inventory management system for electronic devices.
 */
public class Inventory {
    private LinkedList<ArrayList<Device>> deviceList;  // linked list include array list and it hold to all devices
    
    /**
     * Constructs an inventory.
     * Time complexity O(1).  
     */
    public Inventory() {
        deviceList = new LinkedList<>();

        deviceList.add(new ArrayList<Device>());
        deviceList.add(new ArrayList<Device>());
        deviceList.add(new ArrayList<Device>());
        deviceList.add(new ArrayList<Device>());
        deviceList.add(new ArrayList<Device>());

        // Add elements for examples
        deviceList.get(0).add(new TV("Vestel 1", 67, 23));
        deviceList.get(0).add(new TV("Samsung A24", 2300.75, 90));

        deviceList.get(1).add(new Laptop("Lenovo A1", 8000, 75));

        deviceList.get(2).add(new Smartphone("Iphone 13", 5500.30, 150));
        deviceList.get(2).add(new Smartphone("Samsung S21 Black", 2500, 400));

        deviceList.get(3).add(new Headphones("Airpods Pro", 2050.75, 550));
        deviceList.get(3).add(new Headphones("Beats 3", 100, 20));

        deviceList.get(4).add(new SmartWatch("Huawei Watch Fit", 1000.80, 300));
        deviceList.get(4).add(new SmartWatch("Apple Watch Series 6", 1112.23, 65));

    }
    /**
     * helper function find category within linked list (deviceList).
     * @param category linked list node
     * @return i
     * Time complexity : O(n). worst-case deviceList.size assume n
     */
    private int findCategory(String category) {
        for (int i = 0; i < deviceList.size(); i++) {
            ArrayList<Device> list = deviceList.get(i);
            if (!list.isEmpty() && list.get(0).getCategory().equals(category)) {
                return i; // Found the category
            }
        }
        return -1; // Category not found
    }
    
    /**
     * helper function find index within deviceList.
     * @param category linked list node
     * @return positionDevice
     * Time complexity : O(n*m). worst-case deviceList.size assume n and list.size() assume m
     */
    private int[] findDeviceIndex(String name) {
        int[] positionDevice = new int[2]; 
        for (int i = 0; i < deviceList.size(); i++) {
            ArrayList<Device> list = deviceList.get(i);
            for(int j =0; j< list.size(); j++)
            {
                if (list.get(j).getName().equals(name)) {
                    positionDevice[0]=i;
                    positionDevice[1]=j;
                    return positionDevice;
                }
            }
        }
        positionDevice[0] = -1;
        return positionDevice;
    }
    /**
     * print devices according to index 
     * @param linkedIndex
     * @param listIndex
     * Time complexity : O(1). constant operation.
     */
    private void printDevice(int linkedIndex, int listIndex){
        System.out.println(
                "Category: " + deviceList.get(linkedIndex).get(listIndex).getCategory() +
                ", Name: " + deviceList.get(linkedIndex).get(listIndex).getName() +
                ", Price: " + deviceList.get(linkedIndex).get(listIndex).getPrice() +
                ", Quantity: " + deviceList.get(linkedIndex).get(listIndex).getQuantity() + "\n" );

    }
    /**
     * Adds a new device.
     * @param category linked list node
     * @param name the name of the device
     * @param price the price of the device
     * @param quantity the quantity of the device
     * Time Complexity O(n). because this method use findCategory method and O(n) bigger O(m)(this operation check one category device)
     */
    public void addDevice(String category, String name , double price, int quantity) {
        int index = findCategory(category);
        if (index == -1) {
            
            ArrayList<Device> newList = new ArrayList<>();  //if no have category, it create new list

            if(category.equals("TV")){
            newList.add(new TV(name,price,quantity));                
            }
            else if (category.equals("Smart Phone")){
            newList.add(new Smartphone(name,price,quantity));  
            }
            else if (category.equals("Laptop")){
            newList.add(new Laptop(name,price,quantity));  
            }
            else if (category.equals("Headphones")){
            newList.add(new Headphones(name,price,quantity));  
            }
            else if (category.equals("Smart Watch")){
            newList.add(new SmartWatch(name,price,quantity));
            }
            deviceList.add(newList);
            System.out.println("\n" + category + ", " + name + ", " + price + ", " + quantity
                                + ", " + " amount added..." + "\n");
        }
        else {
            ArrayList<Device> existingList = deviceList.get(index);
            boolean deviceExists = false;
            // Time complexity O(m).
            for (Device existingDevice : existingList) {
                if (existingDevice.getName().equals(name)) {
                    deviceExists = true;
                    break;
                }
            }
            if (!deviceExists) {
                if(category.equals("TV")){
                    existingList.add(new TV(name,price,quantity));                
                    }
                    else if (category.equals("Smart Phone")){
                    existingList.add(new Smartphone(name,price,quantity));  
                    }
                    else if (category.equals("Laptop")){
                    existingList.add(new Laptop(name,price,quantity));  
                    }
                    else if (category.equals("Headphones")){
                    existingList.add(new Headphones(name,price,quantity));  
                    }
                    else if (category.equals("SmartWatch")){
                    existingList.add(new SmartWatch(name,price,quantity));
                    }
                    System.out.println("\n" + category + ", " + name + ", " + price + ", " + quantity
                                + ", " + " amount added..." + "\n");
            } else {
                System.out.println("\nSame name already exists in the category.\n");
            }
        }
    }
    /**
     * Removes a device.
     * @param name the name of the device to remove
     * Time Complexity O(n*m). worst-case because use findDeviceIndex method another operation are constant O(1). 
     */
    public void removeDevice(String name) {
        int[] positionDevice = findDeviceIndex(name);
        if (positionDevice[0] != -1) { 
            deviceList.get(positionDevice[0]).remove(positionDevice[1]);
            System.out.println("\nDevice removed.\n");
        }
        else
            System.out.println("\nDevice not found.\n");
    }
    /**
     * Updates device details.
     * @param name the name of the device to update
     * @param price the new price of the device
     * @param quantity the new quantity of the device
     * Time Complexity: O(n*m). worst case because use findDeviceIndex method.
     */
    public void updateDevice(String name, double price, int quantity) {
        int[] positionDevice = findDeviceIndex(name);

        if (positionDevice[0] != -1) { 
            deviceList.get(positionDevice[0]).get(positionDevice[1]).setPrice(price);
            deviceList.get(positionDevice[0]).get(positionDevice[1]).setQuantity(quantity);
            System.out.println("\n" + name + " details updated: Price - " + price + ", Quantity - " + quantity + "\n");
        }
        else
        System.out.println("\nDevice not found!\n");
    }
    /**
     * Displays all devices.
     * Time Complexity: O(n*m). worst-case assume deviceList.size() n and deviceList.get(i).size() m. another operation are constant
     */
    public void displayAllDevices() {
        int deviceNumber = 1;
        for (int i = 0; i < deviceList.size(); i++) {
            for (int j = 0; j < deviceList.get(i).size(); j++) {
                System.out.print(deviceNumber + ".");   // device order
                printDevice(i,j);           // print device method call
                deviceNumber++;
            }
        }
    }
    /**
     * Finds  cheapest device.
     * Time Complexity: O(n*m). worst-case assume deviceList.size() n and list.size() m. another operation are constant
     */

    public void findCheapestDevice() {
        int linkedIndex=0;
        int listIndex=0;
        double cheapest = Double.MAX_VALUE; // Initialize to maximum possible value
        if(!deviceList.isEmpty() && !deviceList.get(0).isEmpty()){
    
        for (int i = 0; i < deviceList.size(); i++) {
            ArrayList<Device> list = deviceList.get(i);
            for (int j = 0; j < list.size(); j++) {
                if (list.get(j).getPrice() < cheapest) {    // this algorithm find cheapest device
                    cheapest = list.get(j).getPrice();
                    linkedIndex = i;
                    listIndex = j;
                }
            }
        }   
        System.out.print("\nCheapest Device: ");
        printDevice(linkedIndex, listIndex);        // print device method call
        }
    }
    /**
     * Sorts devices according to prices 
     * Time Complexity: O(n*log(n)) n total length of allDevices and this method belonging to Java
     */
    public void sortDevices() {
         List<Device> allDevices = new ArrayList<>();

        for (ArrayList<Device> list : deviceList) {     // all of list save allDiveces list
            allDevices.addAll(list);
        }
        Collections.sort(allDevices, new DevicePriceComparator());
        int deviceNumber = 1;
        System.out.println("\n");
        for (Device device : allDevices) {
            System.out.println(deviceNumber + ". Category: " + device.getCategory() +
                        ", Name: " + device.getName() +
                        ", Price: " + device.getPrice() +
                        ", Quantity: " + device.getQuantity());
                deviceNumber++;
        }
        System.out.println("\n");

    }
    /**
     * Calculates the total value of all devices.
     * @return the total value
     * Time Complexity O(n*m). worst-case assume deviceList.size() n and list.size() m. another operation are constant
     */
    public double calculateTotalValue() {
        double result = 0.0; 
    
        for (int i = 0; i < deviceList.size(); i++) {
            ArrayList<Device> list = deviceList.get(i);
            for (int j = 0; j < list.size(); j++) 
                result += list.get(j).getPrice() * list.get(j).getQuantity() ;
                
            }
        return result;
    }
    /**
     * Restocks a device
     * @param name the name of the device to restock
     * @param operation the operation 
     * @param quantity the quantity
     * Time Complexity O(n*m). because use findDeviceIndex method
     */
    public void restockDevice(String name, String operation, int quantity) {
        int[] positionDevice = findDeviceIndex(name);

        if (positionDevice[0] != -1) { 
            Device device = deviceList.get(positionDevice[0]).get(positionDevice[1]);
            
            if (operation.equals("Add")) {
                device.setQuantity(device.getQuantity() + quantity);
                System.out.println(name + " restocked. New quantity: " + device.getQuantity());
            } else if (operation.equals("Remove")) {
                if (quantity <= device.getQuantity()) {
                    device.setQuantity(device.getQuantity() - quantity);
                    System.out.println(name + " stock reduced. New quantity: " + device.getQuantity());
                } else {
                    System.out.println("Entered quantity is greater than current quantity");
                }
            } else {
                System.out.println("Please enter 'Add' or 'Remove'.");
            }
        } else {
            System.out.println("Device not found.");
        }
    } 
    /**
     * Report file all devices.
     * Time Complexity O(n*m). worst-case assume deviceList.size() n and list.size() m. another operation are constant
     */
    public void reportFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("report.txt"))) {
            writer.write("Electronics Shop Inventory Report");
            writer.newLine();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy");
            LocalDate currentDate = LocalDate.now();
            String formattedDate = currentDate.format(formatter);
            writer.write("Generated on: " + formattedDate);
            writer.newLine();
            writer.newLine();
            writer.write("---------------------------------------");
            writer.newLine();
            writer.write("| No. | Category      | Name             | Price   | Quantity |");
            writer.newLine();
            writer.write("---------------------------------------");
            writer.newLine();

            int deviceNumber = 1;
            for (ArrayList<Device> list : deviceList) {
                for (Device device : list) {
                    writer.write(String.format("| %-4d| %-14s| %-17s| $%-7.2f| %-9d|",
                            deviceNumber, device.getCategory(), device.getName(),
                            device.getPrice(), device.getQuantity()));
                    writer.newLine();
                    deviceNumber++;
                }
            }
            writer.write("---------------------------------------");
            writer.newLine();
            writer.newLine();
            writer.write("Summary:");
            writer.newLine();
            writer.write("- Total Number of Devices: " + (deviceNumber-1));
            writer.newLine();
            writer.write(String.format("- Total Inventory Value: $%.2f", calculateTotalValue()));
            writer.newLine();
            writer.newLine();
            writer.write("End of Report");
            writer.newLine();

        } catch (IOException e) {
            System.err.println("An error occurred while writing the inventory report: " + e.getMessage());
        }
    }

}


