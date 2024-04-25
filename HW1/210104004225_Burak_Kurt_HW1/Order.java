public class Order {

    private String[] status_table = {"Initialized", "Processing", "Completed", "Cancelled"};    // this table define status 
    private String product_name;
    private int count;
    private int total_price;
    private int status;
    private int customer_ID;

    public Order(String product_name, int count, int total_price, int status, int customer_ID) {    // parameter constructor 
        this.product_name=product_name;
        this.count=count;
        this.total_price=total_price;
        this.status=status;
        this.customer_ID=customer_ID;
    }
    public int getcustomer_ID() {   // return ID of order's customer
        return customer_ID;
    }
    
    public void print_order(){     // print order information
        System.out.print("Product name: " + product_name + " - ");
        System.out.print("Count: " + count + " - ");
        System.out.print("Total price: " + total_price + " - ");
        System.out.println("Status: " + status_table[status] + ".");
    }  
}