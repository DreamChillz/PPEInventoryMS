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
public class HospitalManagement {

    private List<Hospital> hospitals;
    
    public HospitalManagement() {
        hospitals = new ArrayList<>();
    }
    
    public List<Hospital> getHospitals() {
        return hospitals;
    }
    
    public void loadHospitalsfromfile() {
        String filePath = "src/hospitals.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split("\t");
                String hospital_ID = data[0].trim();
                String hospital_Name = data[1].trim();
                String hospital_Address = data[2].trim();
                String hospital_Type = data[3].trim();
                String hospital_Email = data[4].trim();
                
                hospitals.add(new Hospital(hospital_ID, hospital_Name, hospital_Address, hospital_Type, hospital_Email));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void writeHospitalFile() throws IOException {
        String[] hospitaldata = {
            "H001\tGeneral Hospital\t123 Main St\tPublic\tgeneral@hospital.com",
            "H002\tCity Clinic\t456 City Rd\tPrivate\tcity@clinic.com",
            "H003\tSpecialist Center\t70 Specialist Ave\tSpecialized\tspecialist@center.com"
        };
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/hospitals.txt"))) {
            for (String hospital: hospitaldata) {
                writer.write(hospital + "\n");
            }
            writer.close();
            System.out.println("Data written successfully");
        } catch (IOException except) {
            System.out.println("Failed");
            except.printStackTrace();
        }
    }
    
    public boolean addHospital(String hospital_ID, String hospital_Name, String hospital_Address, String hospital_Type, String hospital_Email) {
        // Check if hospital_ID already exists
        for (Hospital hospital : hospitals) {
            if (hospital.getHospital_ID().equals(hospital_ID)) {
                // Hospital ID already exists
                return false;
            }
        }

        // Hospital ID is unique, proceed to add the new hospital
        Hospital newhospital = new Hospital(hospital_ID, hospital_Name, hospital_Address, hospital_Type, hospital_Email);
        boolean isAdded = hospitals.add(newhospital);
        
        if (isAdded) {
            // Call the function to update the text file after adding the new hospital
            updateHospitalsToFile();
        }
        return isAdded;
    }

    public void updateHospitalsToFile() {
        String filePath = "src/hospitals.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Hospital hospital : hospitals) {
                // Write each hospital to the file in the same format it was read 
                writer.write(hospital.getHospital_ID() + "\t"
                        + hospital.getHospital_Name() + "\t"
                        + hospital.getHospital_Address() + "\t"
                        + hospital.getHospital_Type() + "\t"
                        + hospital.getHospital_Email());
                writer.newLine(); // Move to the next line for the next hopsital
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public boolean modifyHospital(String hospital_ID, String newHospitalName, String newHospitalAddress, String newHospitalType, String newHospitalEmail) {
        for (Hospital hospital : hospitals) {
            if (hospital.getHospital_ID().equals(hospital_ID)) {
                // Update hospital details
                hospital.setHospital_Name(newHospitalName);
                hospital.setHospital_Address(newHospitalAddress);
                hospital.setHospital_Type(newHospitalType);
                hospital.setHospital_Email(newHospitalEmail);

                // After modifying the hospital, save the updated list to the file
                updateHospitalsToFile();

                return true;
            }
        }
        return false; // Return false if hospital not found
    }
    
    public boolean deleteHospital(String hospital_id) {
        for (Hospital hospital : hospitals) {
            if (hospital.getHospital_ID().equals(hospital_id)) {
                hospitals.remove(hospital);
                updateHospitalsToFile();
                return true; 
            }
        }
        return false;
    }
    
    public Hospital findHospitalByID(String hospital_ID) {
        for (Hospital hospital : hospitals) {
            if (hospital.getHospital_ID().equals(hospital_ID)) {
                return hospital; // Return the hospital object if found
            }
        }
        return null; // Return null if hospital not found
    }
    
    public List searchHospital(String searchterm) {
        List<Hospital> filteredHospitals = new ArrayList<>();

        String searchTerm = searchterm.toLowerCase().trim();

        for (Hospital hospital : hospitals) {
            String hospitalid = (hospital.getHospital_ID() != null) ? hospital.getHospital_ID().toLowerCase() : "";
            String hospital_Name = (hospital.getHospital_Name() != null) ? hospital.getHospital_Name().toLowerCase() : "";

            if (hospitalid.contains(searchTerm) || hospital_Name.contains(searchTerm)) {
                filteredHospitals.add(hospital);
            }
        }
        return filteredHospitals;
    }
}

    