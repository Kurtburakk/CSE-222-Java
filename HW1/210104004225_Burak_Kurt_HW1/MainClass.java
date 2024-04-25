import java.io.File;
import java.util.Scanner;

public class MainClass {

    public static int OrderCounter=0;       // it hold to all orders number
    public static int CustomerCounter=0;    // it hold to all customer number
    public static int OperatorCounter=0;    // it hold to all operator number

    public static boolean checkIntegerInput(int a) {    // this method check integer value
            if (a <= Integer.MAX_VALUE && a > 0)
                return true;
            else
                return false;
        }
        public static boolean checkStringInput(String a) { // this method check String 
            if(!a.isEmpty())
                return true;
            else 
                return false;
        }
        public static boolean checkID(int a) {              // this method operator and customer ID , check same ID
            boolean controlID = true;
            for(int i = 0; i < CustomerCounter; i++ ) {
                if(a == main_Customer[i].getID())
                    controlID=false;
            }
            for(int i = 0; i < OperatorCounter; i++ ) {
                if(a == main_Operator[i].getID())
                    controlID=false;
            }
            return controlID;
        }
        public static int convertInt(String a){         // this method convert integer to string and control string have within number
            try {
                int number = Integer.parseInt(a);
                return number;
            } catch (NumberFormatException e) {
                return -1;
            }
        }

        static Order[] main_Orders = new Order[100];            // this array hold to all orders
        static Customer[] main_Customer = new Customer[100];    // this array hold to all customers
        static Operator[] main_Operator = new Operator[100];    // this array hold to all operators

    public static void main(String args[]) {
        String name = "", surname = "", address = "", phone = "", company_name = "";
        int ID = 0, wage = 0, Operator_ID = 0, count = 0, total_price = 0, status = 0, customer_ID = 0;
        boolean flag = true;
        
        try {
            File file = new File("content.txt");    // open file
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {         // read every each line
                String line = scanner.nextLine();    
                String[] parts = line.split(";");   // its hold to String array to parse line

                if(checkStringInput(parts[0])) {    // first input check is string

                    if(parts[0].equals("order")) {         
                        count = convertInt(parts[2]);
                        flag = checkIntegerInput(count);
                        if(parts.length != 6)   // it check number of entry
                        flag=false;
                        if(flag){
                            total_price = convertInt(parts[3]);
                            flag = checkIntegerInput(total_price);
                            if(flag){       
                                status = convertInt(parts[4]);
                                flag = checkIntegerInput(status);
                                if(status == 0)
                                    flag = true;
                                if(flag) {
                                    customer_ID = convertInt(parts[5]);
                                    flag = checkIntegerInput(customer_ID);
                                }
                            }
                        }
                        if(flag) {      // if each value true, new order add orders array
                            main_Orders[OrderCounter] = new Order(parts[1],count,total_price,status,customer_ID);
                            OrderCounter++;
                        }
                    }
                    else if(parts[0].equals("retail_customer")) {
                        name = parts[1];
                        flag = checkStringInput(name);
                        if(parts.length != 7)       // it check number of entry
                        flag=false;
                        if(flag){
                            surname = parts[2];
                            flag = checkStringInput(surname);
                            if(flag){
                                address = parts[3];
                                flag = checkStringInput(address);
                                if(flag){
                                    phone = parts[4];
                                    flag = checkStringInput(phone);
                                    if(flag){
                                        ID = convertInt(parts[5]);
                                        flag = checkIntegerInput(ID);
                                        if(flag){
                                            flag = checkID(ID);
                                            if(flag){
                                                Operator_ID = convertInt(parts[6]);
                                                flag = checkIntegerInput(Operator_ID);
                                            }
                                        }
                                    }
                                }                        
                            }
                        }
                        if(flag) {      // if each value true, new customer add customers array (with upcasting method )
                        main_Customer[CustomerCounter] = new Retail_customer(parts[1], parts[2], parts[3], parts[4], ID, Operator_ID);
                        CustomerCounter++; 
                        }
                    }
                    else if(parts[0].equals("corporate_customer")) {
                        name = parts[1];
                        flag = checkStringInput(name);
                        if(parts.length != 8)       // it check number of entry
                        flag=false;
                        if(flag){
                            surname = parts[2];
                            flag = checkStringInput(surname);
                            if(flag){
                                address = parts[3];
                                flag = checkStringInput(address);
                                if(flag){
                                    phone = parts[4];
                                    flag = checkStringInput(phone);
                                    if(flag){
                                        ID = convertInt(parts[5]);
                                        flag = checkIntegerInput(ID);
                                        if(flag){
                                            flag = checkID(ID);
                                            if(flag){
                                                Operator_ID = convertInt(parts[6]);
                                                flag = checkIntegerInput(Operator_ID);
                                                if(flag){
                                                company_name = parts[7];
                                                flag = checkStringInput(company_name);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        if(flag) {          // if each value true, new customer add customers array (with upcasting method )
                            main_Customer[CustomerCounter] = new Corporate_customer(parts[1], parts[2], parts[3], parts[4], ID, Operator_ID, company_name);
                            CustomerCounter++;      
                        }
                    }
                    else if(parts[0].equals("operator")) {
                        name = parts[1];
                        flag = checkStringInput(name);
                        if(parts.length != 7)       // it check number of entry
                        flag=false;
                        if(flag){
                            surname = parts[2];
                            flag = checkStringInput(surname);
                            if(flag){
                                address = parts[3];
                                flag = checkStringInput(address);
                                if(flag){
                                    phone = parts[4];
                                    flag = checkStringInput(phone);
                                    if(flag){
                                        ID = convertInt(parts[5]);
                                        flag = checkIntegerInput(ID);
                                        if(flag){
                                            flag = checkID(ID);
                                            if(flag){
                                                wage = convertInt(parts[6]);
                                                flag = checkIntegerInput(wage);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        if(flag) {      // if each value true, new operator add customers array (with upcasting method )
                            main_Operator[OperatorCounter] = new Operator(parts[1], parts[2], parts[3], parts[4], ID, wage);
                            OperatorCounter++; 
                        }
                    }
                }
                // parts String clean 
                for (int i = 0; i < parts.length; i++)
                    parts[i] = null;
                
                flag = true; // reset flag
                
            }
            scanner.close();    // close file
        } catch (Exception e) {
            System.out.println("Not find file: " + e.getMessage());
        }

        // must part
        for(int i=0;i<CustomerCounter;i++)  
            main_Customer[i].define_orders(main_Orders);    //check all orders and they add within customer's order array

        for(int i=0;i<OperatorCounter;i++)
            main_Operator[i].define_customers(main_Customer); //check all customers and they add within operator's customer array
        

        Scanner scanner = new Scanner(System.in);

        int IdInput = -1;   // first wrong input

        while (IdInput <= 0) {
            try {
                System.out.println("Please enter your ID... ");     
                IdInput = scanner.nextInt();    // take id

            } catch (Exception e) {
                System.out.println(" Mistake input.");
                scanner.nextLine(); // clean buffer
            }
        }
        boolean findFlag = false;   // define flag 

        // choice part 
        for(int i=0;i<CustomerCounter;i++){
            if(IdInput == main_Customer[i].getID()){    // it check all customer 
                System.out.println("*** Customer Screen ***"); 
                main_Customer[i].print_customer();
                findFlag = true;
            }
        }
        if(!findFlag){
            for(int i=0;i<OperatorCounter;i++){
                if(IdInput == main_Operator[i].getID()){    // it check all operator
                    System.out.println("*** Operator Screen ***");
                    System.out.println("----------------------------");
                    main_Operator[i].print_operator();
                    findFlag = true;
                }
            }
        }
        if(!findFlag)   // if no find any operator or customer
            System.out.println("No operator/customer was found with ID " + IdInput + ". Please try again.");
    }
}
