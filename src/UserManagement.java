
import java.util.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class UserManagement {

    private List<User> users;

    public UserManagement() {
        users = new ArrayList<>();
    }

    public List<User> getUsers() {
        return users;
    }

    //get user data from file and convert to array
    public void loadUsersfromfile() {
        String filePath = "src/users.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split("\t");
                String user_id = data[0].trim();
                String name = data[1].trim();
                String password = data[2].trim();
                String user_type = data[3].trim();
                String email = data[4].trim();
                String phone_no = data[5].trim();

                // Based on the user_type, instantiate the appropriate subclass
                if (user_type.equals("admin")) {
                    users.add(new AdminUser(user_id, name, password, email, phone_no));
                } else if (user_type.equals("staff")) {
                    users.add(new StaffUser(user_id, name, password, email, phone_no));
                }

            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void updateUsersToFile() {
        String filePath = "src/users.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (User user : users) {

                String userType;
                if (user instanceof AdminUser) {
                    userType = "admin";
                } else if (user instanceof StaffUser) {
                    userType = "staff";
                } else {
                    continue; // Skip if the user type is not recognized
                }

                // Write each user to the file in the same format it was read (e.g., tab-separated)
                writer.write(user.getUser_ID() + "\t"
                        + user.getName() + "\t"
                        + user.getPassword() + "\t"
                        + userType + "\t"
                        + user.getEmail() + "\t"
                        + user.getPhone_no());
                writer.newLine(); // Move to the next line for the next user
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public User login(String user_id, String password) {
        for (User user : users) {
            if (user_id.equals(user.getUser_ID()) && password.equals(user.getPassword())) {
                return user;  // Return the user object if credentials match
            }
        }
        return null; //return null if not
    }


    public boolean addUser(String user_ID, String name, String password, String user_type, String email, String phone_no) {
        // Check if user_ID already exists
        for (User user : users) {
            if (user.getUser_ID().equals(user_ID)) {
                // User ID already exists
                return false;
            }
        }

        // User ID is unique, proceed to add the new user
        User newUser;

        // Instantiate the appropriate user type
        if (user_type.equalsIgnoreCase("admin")) {
            newUser = new AdminUser(user_ID, name, password, email, phone_no);
        } else {
            newUser = new StaffUser(user_ID, name, password, email, phone_no);
        }

        boolean isAdded = users.add(newUser);

        if (isAdded) {
            // Call the function to update the text file after adding the new user
            updateUsersToFile();
        }
        return isAdded;
    }

    public List searchUser(String searchterm) {
        List<User> filteredUsers = new ArrayList<>();

        String searchTerm = searchterm.toLowerCase().trim();

        for (User user : users) {
            String userID = (user.getUser_ID() != null) ? user.getUser_ID().toLowerCase() : "";
            String name = (user.getName() != null) ? user.getName().toLowerCase() : "";

            if (userID.contains(searchTerm) || name.contains(searchTerm)) {
                filteredUsers.add(user);
            }
        }
        return filteredUsers;
    }

    public User findUserByID(String user_ID) {
        for (User user : users) {
            if (user.getUser_ID().equals(user_ID)) {
                return user; // Return the user object if found
            }
        }
        return null; // Return null if user not found
    }

    public boolean modifyUser(String user_ID, String newName, String newUserType, String newEmail, String newPhoneNo) {
        for (User user : users) {
            if (user.getUser_ID().equals(user_ID)) {
                // Update user details
                // Create a new user object based on the new user type
                User updatedUser;
                if (newUserType.equalsIgnoreCase("admin")) {
                    updatedUser = new AdminUser(user.getUser_ID(), newName, user.getPassword(), newEmail, newPhoneNo);
                } else {
                    updatedUser = new StaffUser(user.getUser_ID(), newName, user.getPassword(), newEmail, newPhoneNo);
                }

                // Remove the old user and add the updated one
                users.remove(user);
                users.add(updatedUser);

                // After modifying the user, save the updated list to the file
                updateUsersToFile();

                return true;
            }
        }
        return false; // Return false if user not found
    }

    public boolean deleteUser(String user_id) {
        for (User user : users) {
            if (user.getUser_ID().equals(user_id)) {
                users.remove(user);
                updateUsersToFile();
                return true;
            }
        }
        return false;
    }

    //write user data to user.txt
    public static void writeUserFile() {
        String[] userdata = {
            "kkdetective\tKyoko Kirigiri\tkk123\tadmin\tkyoko123@gmail.com\t011-3456789",
            "jacky123\tJacky\tjacky123\tstaff\tjacky@gmail.com\t014-3456789",
            "elaineA\tElaine Auclair\tvan\tadmin\telaine@gmail.com\t017-3456789"

        };
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/users.txt"))) {
            for (String user : userdata) {
                writer.write(user + "\n");
            }
            writer.close();
            System.out.println("Data written successfully");

        } catch (IOException except) {
            System.out.println("Failed");
            except.printStackTrace();
        }
    }

}
