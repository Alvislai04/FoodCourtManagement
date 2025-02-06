package system.admin;

public class User {
    protected String id;
    protected String name;
    protected String address;
    protected String phonenumber;
    protected String username;
    protected String password;
    protected String roles;
    protected double balance;
    
    protected User(String id, String name, String address, String phoneno, String username, String password, String roles, double balance) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phonenumber = phoneno;
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.balance = balance;
    }
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getphoneNo() {
        return phonenumber;
    }
    
    public void setPhoneNumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }
    
    public String getUsername(){
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getRoles() {
        return roles;
    }
    
    public void setRoles(String roles) {
        this.roles = roles;
    }
    
    public double getBalance() {
        return balance;
    }
    
    public void setBalance(double balance) {
        this.balance = balance;
    }
}
