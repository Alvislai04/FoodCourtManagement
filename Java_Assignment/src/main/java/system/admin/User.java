package system.admin;

public class User {
    private String id;
    private String name;
    private String address;
    private String phonenumber;
    private String username;
    private String password;
    private String roles;
    
    public User(String id, String name, String address, String phoneno, String username, String password, String roles) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phonenumber = phoneno;
        this.username = username;
        this.password = password;
        this.roles = roles;
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
}
