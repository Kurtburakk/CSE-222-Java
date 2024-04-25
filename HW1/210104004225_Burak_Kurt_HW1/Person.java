/** Abstract class */
public abstract class Person {
    
    protected String name = ""; // The name of the person
    protected String surname = ""; // The surname of the person
    protected String address = ""; // The address of the person
    protected String phone = ""; // The phone of the person
    protected int ID ; // The ID of the person

    public Person(String name, String surname, String address, String phone, int ID)  // Parameter Constructor
    {
        this.name=name;
        this.surname=surname;
        this.address=address;
        this.phone=phone;
        this.ID=ID;
    }
    
}