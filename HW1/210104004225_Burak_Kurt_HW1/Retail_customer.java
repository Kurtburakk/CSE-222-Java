public class Retail_customer  extends Customer{

    public Retail_customer(String name, String surname, String address, String phone, int ID, int Operator_ID){
        super(name, surname, address, phone, ID, Operator_ID);
    }
    @Override
    public boolean which_customer() {
        return false;
    }

    @Override
    public void print_customer() {
        super.print_customer();     // this method call Customer class actual method
        print_orders();             
    }
    
}