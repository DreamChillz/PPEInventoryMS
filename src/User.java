public class User {
    private String user_ID,name,password,email,phone_no;

    public User(String user_ID, String name, String password, String email, String phone_no) {
        this.user_ID = user_ID;
        this.name = name;
        this.password = password;
        this.email = email;
        this.phone_no = phone_no;
    }

    public String getUser_ID() {
        return user_ID;
    }

    public void setUser_ID(String user_ID) {
        this.user_ID = user_ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }
    
    public String getWelcomeMessage() {
        return "Welcome!";
    }
}
