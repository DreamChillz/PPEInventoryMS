
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Acer
 */
public class SupplierManagement {
    private List<Supplier> suppliers;
    
    public SupplierManagement() {
        suppliers = new ArrayList<>();
    }
    
    public List<Supplier> getSuppliers() {
        return suppliers;
    }
    
        //get supplier data from file and convert to array
    public void loadSuppliersfromfile() {
        String filePath = "src/suppliers.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split("\t");
                String supplier_ID = data[0].trim();
                String supplier_Name = data[1].trim();
                String supplier_Address = data[2].trim();
                String supplier_Email = data[3].trim();
                String supplier_Contact_Number = data[4].trim();
                
                suppliers.add(new Supplier(supplier_ID, supplier_Name, supplier_Address, supplier_Email, supplier_Contact_Number));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // Method to display all suppliers in the array
    public void displaySuppliers() {
        if (suppliers.isEmpty()) {
            System.out.println("No suppliers loaded.");
            return;
        }
        
        for (Supplier supplier : suppliers) {
            System.out.println("Supplier ID: " + supplier.getSupplier_ID());
            System.out.println("Name: " + supplier.getSupplier_Name());
            System.out.println("Address: " + supplier.getSupplier_Address());
            System.out.println("Email: " + supplier.getSupplier_Email());
            System.out.println("Contact Number: " + supplier.getSupplier_Contact_Number());
            System.out.println("-------------------------------------");
        }
    }
    
    
    //search supplier by id or name
    public List<Supplier> searchSupplier(String searchterm) {
    List<Supplier> filteredSuppliers = new ArrayList<>();

    String searchTerm = searchterm.toLowerCase().trim();

    for (Supplier supplier : suppliers) {
        String supplierID = (supplier.getSupplier_ID() != null) ? supplier.getSupplier_ID().toLowerCase() : "";
        String supplierName = (supplier.getSupplier_Name() != null) ? supplier.getSupplier_Name().toLowerCase() : "";

        if (supplierID.contains(searchTerm) || supplierName.contains(searchTerm)) {
            filteredSuppliers.add(supplier);
        }
    }
    return filteredSuppliers;
}

    
    
    public boolean addSupplier(String supplier_ID, String supplier_Name, String supplier_Address, String supplier_Email, String supplier_Contact_Number) {
    // Check if supplier_ID already exists
    for (Supplier supplier : suppliers) {
        if (supplier.getSupplier_ID().equals(supplier_ID)) {
            // Supplier ID already exists
            return false;
        }
    }

    // Supplier ID is unique, proceed to add the new supplier
    Supplier newsupplier = new Supplier(supplier_ID, supplier_Name, supplier_Address, supplier_Email, supplier_Contact_Number);
    boolean isAdded = suppliers.add(newsupplier);

    if (isAdded) {
        // Call the function to update the text file after adding the new supplier
        updateSuppliersToFile();
    }

    return isAdded;
    }
    
    public void updateSuppliersToFile() {
        String filePath = "src/suppliers.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Supplier supplier : suppliers) {
                // Write each supplier to the file in the same format it was read 
                writer.write(supplier.getSupplier_ID() + "\t"
                        + supplier.getSupplier_Name() + "\t"
                        + supplier.getSupplier_Address() + "\t"
                        + supplier.getSupplier_Email() + "\t"
                        + supplier.getSupplier_Contact_Number());
                writer.newLine(); // Move to the next line for the next supplier
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public Supplier findSupplierByID(String supplier_ID) {
        for (Supplier supplier : suppliers) {
            if (supplier.getSupplier_ID().equals(supplier_ID)) {
                return supplier; // Return the supplier object if found
            }
        }
        return null; // Return null if supplier not found
    }
    
    public boolean modifySupplier(String supplier_ID, String newSupplierName, String newSupplierAddress, String newSupplierEmail, String newSupplierContactNumber) {
        for (Supplier supplier : suppliers) {
            if (supplier.getSupplier_ID().equals(supplier_ID)) {
                // Update supplier details
                supplier.setSupplier_Name(newSupplierName);
                supplier.setSupplier_Address(newSupplierAddress);
                supplier.setSupplier_Email(newSupplierEmail);
                supplier.setSupplier_Contact_Number(newSupplierContactNumber);

                // After modifying the supplier, save the updated list to the file
                updateSuppliersToFile();

                return true;
            }
        }

        return false; // Return false if supplier not found
    }
    
    public boolean deleteSupplier(String supplier_id) {
        for (Supplier supplier : suppliers) {
            if (supplier.getSupplier_ID().equals(supplier_id)) {
                suppliers.remove(supplier);
                updateSuppliersToFile();
                return true; 
            }

        }

        return false;
    }
    
    public static void writeSupplierFile() throws IOException {
        String[] supplierdata = {
            "SUP001\tGlobal Med Supplies\tNo.15, Jalan SS 3/50, 47300 Petaling Jaya, Selangor\tcontact@globalmed.com.my\t+60 3-7877 1234",
            "SUP002\tMedico Healthcare\t145, Jalan Tun Dr.Awam, 11902 Bayan Lepas, Penang\tinfo@medicohealthcare.com\t+60 4-641 2211",
            "SUP003\tHealthcare Solutions Malaysia\tNo.90, Jalan Sultan Ahmad Shah, 10055 Geogetown, Penang\tsales@healthcaremy.com\t+60 4-119 8888",
            "SUP004\tMedTech Supplies Sdn Bdh\tNo.35, Jalan TPP 1/5, Taman Perindustrian Puchong, 47000 Puchong, Selangor\tsupport@medtech,com,my\t+60 3-8060 4567"
        };
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/suppliers.txt"))) {
            for (String supplier: supplierdata) {
                writer.write(supplier + "\n");
            }
            writer.close();
            System.out.println("Data written successfully");
        } catch (IOException except) {
            System.out.println("Failed");
            except.printStackTrace();
        }
    }
    
    
    
}
