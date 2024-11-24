/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Acer
 */
public class Hospital {
    // Attribute of Hospital
    private String hospital_ID, hospital_Name, hospital_Address, hospital_Type, hospital_Email;
    
    // Constructor
    public Hospital(String hospital_ID, String hospital_Name, String hospital_Address, String hospital_Type, String hospital_Email){
        this.hospital_ID = hospital_ID;
        this.hospital_Name = hospital_Name;
        this.hospital_Address = hospital_Address;
        this.hospital_Type = hospital_Type;
        this.hospital_Email = hospital_Email;
    }

    public String getHospital_ID() {
        return hospital_ID;
    }

    public void setHospital_ID(String hospital_ID) {
        this.hospital_ID = hospital_ID;
    }

    public String getHospital_Name() {
        return hospital_Name;
    }

    public void setHospital_Name(String hospital_Name) {
        this.hospital_Name = hospital_Name;
    }

    public String getHospital_Address() {
        return hospital_Address;
    }

    public void setHospital_Address(String hospital_Address) {
        this.hospital_Address = hospital_Address;
    }

    public String getHospital_Type() {
        return hospital_Type;
    }

    public void setHospital_Type(String hospital_Type) {
        this.hospital_Type = hospital_Type;
    }

    public String getHospital_Email() {
        return hospital_Email;
    }

    public void setHospital_Email(String hospital_Email) {
        this.hospital_Email = hospital_Email;
    }
}
