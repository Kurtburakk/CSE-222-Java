public class Corporate_customer  extends Customer{

    private String company_name = "";

    public Corporate_customer(String name, String surname, String address, String phone, int ID, int Operator_ID, String company_name){
        super(name, surname, address, phone, ID, Operator_ID);
        this.company_name=company_name;
    }
    
    @Override
    public void print_customer() {
        super.print_customer();     // this method call Customer class actual method
        System.out.println("Company name: " + company_name );
        print_orders(); 
    }

    @Override
    public boolean which_customer() {
        return true;
    }
    
}