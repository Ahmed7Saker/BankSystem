

public class Customer {
    private String name;
    private String address;
    private String phone;
    private String id;
    private int  age;
    private String email;
    private int DateofJoining;   

    // Default Constructor
    public Customer() {
        
    }

    // Parameterized Constructor
    public Customer(String name, String address, String phone, String id, int age, String email, int DateofJoining) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.id = id;
        this.age = age;
        this.email = email;
        this.DateofJoining = DateofJoining;
    }
    // Getters and Setters
    public String getName(){
        return name;
    }  
    public String getId() {
        return id;
    }
    public String getEmail(){
        return email;
    }
    public int getAge() {
        return age;
    }
    public int getDateofJoining() {
        return DateofJoining;
    }
    public String getAddress() {
        return address;
    }
        public String getPhone() {
        return phone;
    }
    @Override
    public String toString() {
        return "Customer [name=" + name + ", address=" + address + ", phone=" + phone + ", id=" + id + ", age=" + age + ", email=" + email + ", DateofJoining=" + DateofJoining + "]";
    }
}