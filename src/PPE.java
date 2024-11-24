import java.math.BigDecimal;

public class PPE {
    private String item_code,item_name,supplier_code;
    private int quantity;
    private BigDecimal price_per_box,total_price;

    public PPE(String item_code, String item_name, String supplier_code, int quantity, BigDecimal price_per_box, BigDecimal total_price) {
        this.item_code = item_code;
        this.item_name = item_name;
        this.supplier_code = supplier_code;
        this.quantity = quantity;
        this.price_per_box = price_per_box;
        this.total_price = total_price;
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

    public String getSupplier_code() {
        return supplier_code;
    }

    public void setSupplier_code(String supplier_code) {
        this.supplier_code = supplier_code;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        updateTotalPrice();
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
    
    public void updateTotalPrice() {
        this.total_price = price_per_box.multiply(BigDecimal.valueOf(quantity));
    }
}
