/** Abstract class */
public class Customer extends Person{
    
    protected Order[] orders = new Order[100];  // Its hold to orders
    protected int Operator_ID; 
    private int customer_order=0;


    public Customer(String name, String surname, String address, String phone, int ID, int Operator_ID ){   // parameter constructor 
        super(name, surname, address, phone, ID);
        this.Operator_ID = Operator_ID;
    }
    public int getID() {    // return customer ID
        return this.ID;
    }
    public int getOperator_ID() {   // Operator id of the customer's operator
        return Operator_ID;
    }
    public boolean which_customer() { // define customer retail or corporate
        return true;
    }

    /* Actual Method print customer */
    public void print_customer() {      
        System.out.println("Name & Surname: " + name + " " + surname);
        System.out.println("Address: " + address);
        System.out.println("Phone: " + phone);
        System.out.println("ID: " + ID );
        System.out.println("Operator ID: " + Operator_ID );
        
    } 
    public void print_orders () {              // this method print customer's orders
        for(int i=0;i<customer_order;i++)
        {
            System.out.print("Order #" + (i+1) + " => " );
            orders[i].print_order();
        }
    }
    public void define_orders(Order[] a) {  // this function has all order and it select only related customer's order
        for(int i=0; i<MainClass.OrderCounter;i++)   // use static MainClass
        {
            if(this.ID == a[i].getcustomer_ID())
                add_Order(a[i]);
        }
    }
    public void add_Order(Order a) {
        if(customer_order<100) {
            orders[customer_order] = a;
            customer_order++; 
        }
        else
            System.out.println("You not include new order.");
    }

    
}