public class Operator extends Person{

    private int wage;
    private Customer[] customers = new Customer[100];   // create customers array
    private int CustomersNumberOperator=0;              // Keeps track of how many customers an operator has
    
    public Operator(String name, String surname, String address, String phone, int ID, int wage){   // parameter constructor
        super(name, surname, address, phone, ID);   // call to person class constructor
        this.wage=wage;
    }
    public int getID() {
        return this.ID;     // return Operator ID value
    }
    public void print_operator() {                                        // print operator information 
        System.out.println("Name & Surname: " + name + " " + surname);
        System.out.println("Address: " + address);
        System.out.println("Phone: " + phone);
        System.out.println("ID: " + ID );
        System.out.println("Wage: " + wage );
        System.out.println("----------------------------");
        print_customers();  

    }

    public void print_customers(){      // print customer and  their order    

        if(CustomersNumberOperator == 0)    // if no have orders
        {
            System.out.println("This operator doesn't have any customer.");
            System.out.println("----------------------------");
        }
        else
        {
            for(int i=0; i<CustomersNumberOperator; i++)
            {
                if(customers[i].which_customer()){      // define customer type
                    System.out.println("Customer #" + (i+1) + " (a corporate customer)");
                }
                else {
                    System.out.println("Customer #" + (i+1) + " (a retail customer)");
                }
                customers[i].print_customer();  // call to customer class method
                System.out.println("----------------------------");
            }
        }
    }
    public void define_customers(Customer[] a){            
        for(int i=0; i<MainClass.CustomerCounter;i++)     // use MainClass static value
        {
            if(this.ID == a[i].getOperator_ID())          // check customerID and operators define customer
                add_Customer(a[i]);
        }
    }
    public void add_Customer(Customer a) {

        if(CustomersNumberOperator<100){        // no invalid more 100
            customers[CustomersNumberOperator] = a; // operators add customer within own customers array
            CustomersNumberOperator++;              // customers counter increase
        }
        else
            System.out.println("You not include operator because that capacity is full.");
    }
}