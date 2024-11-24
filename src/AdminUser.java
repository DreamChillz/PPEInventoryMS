/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author User
 */
public class AdminUser extends User{

    public AdminUser(String user_ID, String name, String password, String email, String phone_no) {
        super(user_ID, name, password, email, phone_no);
    }
    
    @Override
    public String getWelcomeMessage() {
        return "Welcome, Admin!";
    }

}
