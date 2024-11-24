
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {

    private String transaction_ID, item_code, item_name, party, transaction_status, transaction_type;
    private int quantity;
    private LocalDateTime transaction_date_time;
    private BigDecimal price_per_box, total_price;
    
    
    
    public Transaction(String transaction_ID, String item_code, String item_name, String party, 
            String transaction_status, int quantity, String transaction_type, LocalDateTime transaction_date_time, 
            BigDecimal price_per_box, BigDecimal total_price) {

        this.transaction_date_time = transaction_date_time;

        this.transaction_ID = transaction_ID;
        this.item_code = item_code;
        this.item_name = item_name;
        this.party = party;
        this.transaction_status = transaction_status;
        this.quantity = quantity;
        this.transaction_type = transaction_type;

        this.price_per_box = price_per_box;
        this.total_price = price_per_box.multiply(BigDecimal.valueOf(quantity));
        
    }

    public Transaction(Transaction ppeitem) {
    this.transaction_ID = ppeitem.getTransaction_ID();
    this.item_code = ppeitem.getItem_code();
    this.item_name = ppeitem.getItem_name();
    this.party = ppeitem.getParty();
    this.transaction_status = ppeitem.getTransaction_status();
    this.quantity = ppeitem.getQuantity();
    this.transaction_type = ppeitem.getTransaction_type();
    this.transaction_date_time = ppeitem.getTransaction_date_time();
    this.price_per_box = ppeitem.getPrice_per_box();
    this.total_price = ppeitem.getTotal_price();
}


    public String getTransaction_ID() {
        return transaction_ID;
    }

    public void setTransaction_ID(String transaction_ID) {
        this.transaction_ID = transaction_ID;
    }

    public String getItem_code() {
        return item_code;
    }

    public void setItem_code(String item_code) {
        this.item_code = item_code;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public String getTransaction_status() {
        return transaction_status;
    }

    public void setTransaction_status(String transaction_status) {
        this.transaction_status = transaction_status;
    }

    public String getTransaction_type() {
        return transaction_type;
    }

    public void setTransaction_type(String transaction_type) {
        this.transaction_type = transaction_type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        updateTotalPrice();
    }

    public LocalDateTime getTransaction_date_time() {
        return transaction_date_time;
    }
    
    public String getFormattedTransactionDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return transaction_date_time.format(formatter);
    }

    public void setTransaction_date_time(LocalDateTime transaction_date_time) {
        this.transaction_date_time = transaction_date_time;
    }

    public BigDecimal getPrice_per_box() {
        return price_per_box;
    }

    public void setPrice_per_box(BigDecimal price_per_box) {
        this.price_per_box = price_per_box;
        updateTotalPrice();

    }

    public BigDecimal getTotal_price() {
        return total_price;
    }
    

    private void updateTotalPrice() {
        this.total_price = price_per_box.multiply(BigDecimal.valueOf(quantity));
    }
    
//    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return String.format("Transaction ID: %s, Item Code: %s, Item Name: %s, Party: %s, " +
                             "Status: %s, Quantity: %d, Type: %s, Date: %s, Price per Box: %.2f, Total Price: %.2f",
                             transaction_ID, item_code, item_name, party, transaction_status,
                             quantity, transaction_type, transaction_date_time.format(formatter),
                             price_per_box, total_price);
    }
}
