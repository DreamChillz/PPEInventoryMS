public class Supplier {
    private String supplier_ID, supplier_Name, supplier_Address, supplier_Email, supplier_Contact_Number;
    
    public Supplier(String supplier_ID, String supplier_Name, String supplier_Address, String supplier_Email, String supplier_Contact_Number) {
        this.supplier_ID = supplier_ID;
        this.supplier_Name = supplier_Name;
        this.supplier_Address = supplier_Address;
        this.supplier_Email = supplier_Email;
        this.supplier_Contact_Number = supplier_Contact_Number;
    }

    public String getSupplier_ID() {
        return supplier_ID;
    }

    public void setSupplier_ID(String supplier_ID) {
        this.supplier_ID = supplier_ID;
    }

    public String getSupplier_Name() {
        return supplier_Name;
    }

    public void setSupplier_Name(String supplier_Name) {
        this.supplier_Name = supplier_Name;
    }

    public String getSupplier_Address() {
        return supplier_Address;
    }

    public void setSupplier_Address(String supplier_Address) {
        this.supplier_Address = supplier_Address;
    }

    public String getSupplier_Email() {
        return supplier_Email;
    }

    public void setSupplier_Email(String supplier_Email) {
        this.supplier_Email = supplier_Email;
    }

    public String getSupplier_Contact_Number() {
        return supplier_Contact_Number;
    }

    public void setSupplier_Contact_Number(String supplier_Contact_Number) {
        this.supplier_Contact_Number = supplier_Contact_Number;
    }
}
