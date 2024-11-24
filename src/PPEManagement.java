
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author User
 */
public class PPEManagement {

    private List<PPE> ppe;
    private List<Transaction> transactions;

    public PPEManagement() {
        ppe = new ArrayList<>();
        transactions = new ArrayList<>();
    }

    public List<PPE> getPpe() {
        return ppe;
    }

    public List<Transaction> getTransaction() {
        return transactions;
    }

    //check whether ppe.txt exists or not
    public boolean checkInventory() {
        // Specify the file location
        File inventoryfile = new File("src/ppe.txt");
        return inventoryfile.exists();

    }

    //add ppe item into array
    public boolean addItem(String item_code, String item_name, String supplier_code, int quantity, BigDecimal price_per_box, BigDecimal total_price) {
        PPE ppeitem = new PPE(item_code, item_name, supplier_code, quantity, price_per_box, total_price);
        return ppe.add(ppeitem);
    }

    //initial inventory creation, write that data to ppe file
    public boolean uploadDatatoPPEFile(PPEFrame frame, List<PPE> ppeList) {

        String filePath = "src/ppe.txt";
        // Write to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // Write PPE details from the array (list)
            for (PPE ppe : ppeList) {
                writer.write(ppe.getItem_code() + "\t"
                        + ppe.getItem_name() + "\t"
                        + ppe.getSupplier_code() + "\t"
                        + ppe.getQuantity() + "\t"
                        + ppe.getPrice_per_box() + "\t"
                        + ppe.getTotal_price());
                writer.newLine();
            }
            loadPPEDatafromfiletoArray();
            frame.loadPPEDataToTable();
            return true;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    //write ppe array to ppe.txt file
    public void updatePPEToFile() {
        String filePath = "src/ppe.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (PPE ppe_item : ppe) { // Assuming you have a list named ppeList
                // Write each PPE to the file in a tab-separated format
                writer.write(ppe_item.getItem_code() + "\t"
                        + ppe_item.getItem_name() + "\t"
                        + ppe_item.getSupplier_code() + "\t"
                        + ppe_item.getQuantity() + "\t"
                        + ppe_item.getPrice_per_box() + "\t"
                        + ppe_item.getTotal_price()); // Assuming total_price is computed from quantity and price_per_box
                writer.newLine(); // Move to the next line for the next PPE
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //load ppe data from ppe.txt to array
    public void loadPPEDatafromfiletoArray() {
        String filePath = "src/ppe.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split("\t");
                String item_code = data[0].trim();
                String item_name = data[1].trim();
                String supplier_code = data[2].trim();

                // Converting quantity from String to int
                int quantity = Integer.parseInt(data[3].trim());

                // Converting price_per_box and total_price from String to BigDecimal
                BigDecimal price_per_box = new BigDecimal(data[4].trim());
                BigDecimal total_price = new BigDecimal(data[5].trim());

                ppe.add(new PPE(item_code, item_name, supplier_code, quantity, price_per_box, total_price));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printTransactions() {
        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
    }

    public boolean addTransaction(String transaction_ID, String item_code, String item_name,
            String party, String transaction_status, int quantity, String transaction_type,
            LocalDateTime transaction_date_time, BigDecimal price_per_box, BigDecimal total_price) throws IOException {

        for (Transaction transaction : transactions) {
            if (transaction.getTransaction_ID().equals(transaction_ID)) {
                return false;
            }
        }

        Transaction newTransaction = new Transaction(transaction_ID, item_code, item_name, party,
                transaction_status, quantity, transaction_type,
                transaction_date_time, price_per_box, total_price);

        boolean isAdded = transactions.add(newTransaction);
        if (isAdded) {
            // Call the function to update the text file after adding the transaction
            updateTransactionsToFile();
        }
        return isAdded;
    }

    //load transaction data from transaction.txt to array
    public void loadTransactionDatafromfiletoArray() {
        String filePath = "src/transaction.txt";

        transactions.clear();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split("\t");

                String transaction_ID = data[0].trim();
                String item_code = data[1].trim();
                String item_name = data[2].trim();
                String party = data[3].trim();
                String transaction_status = data[4].trim();

                // Converting quantity from String to int
                int quantity = Integer.parseInt(data[5].trim());

                String transaction_type = data[6].trim();

                // Parsing the transaction date-time
                String transaction_date_time_str = data[7].trim();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime transaction_date_time = LocalDateTime.parse(transaction_date_time_str, formatter);

                // Converting price_per_box and total_price from String to BigDecimal
                BigDecimal price_per_box = new BigDecimal(data[8].trim());
                BigDecimal total_price = new BigDecimal(data[9].trim());

                // Creating the transaction object and adding it to the list
                transactions.add(new Transaction(transaction_ID, item_code, item_name, party, transaction_status,
                        quantity, transaction_type, transaction_date_time, price_per_box, total_price));
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    //search items in ppe.txt 
    public List searchPPE(String searchterm) {
        List<PPE> filteredPPE = new ArrayList<>();

        String searchTerm = searchterm.toLowerCase().trim();

        for (PPE ppeitem : ppe) {
            String item_code = (ppeitem.getItem_code() != null) ? ppeitem.getItem_code().toLowerCase() : "";

            if (item_code.contains(searchTerm)) {
                filteredPPE.add(ppeitem);
            }

        }
        return filteredPPE;
    }

    public List<Transaction> searchByItemCode(String itemCodeSearchTerm) {
        List<Transaction> filteredList = new ArrayList<>();
        String lowerCaseSearchTerm = itemCodeSearchTerm.toLowerCase().trim();

        for (Transaction transaction : transactions) {
            String itemCode = transaction.getItem_code() != null ? transaction.getItem_code().toLowerCase() : "";

            if (itemCode.contains(lowerCaseSearchTerm)) {
                filteredList.add(transaction);
            }
        }
        return filteredList;
    }

    public List<Transaction> filterTransactions(String searchTerm, String transactionType, String selectedParty, LocalDateTime startDate, LocalDateTime endDate) {
        List<Transaction> filteredTransactions = new ArrayList<>();

        // Convert searchTerm to lowercase and trim it
        String lowerCaseSearchTerm = searchTerm.toLowerCase().trim();

        for (Transaction transaction : transactions) {
            // Check if the transaction matches the search term and filter criteria
            boolean matchesSearchTerm = transaction.getItem_code().toLowerCase().contains(lowerCaseSearchTerm);
            boolean matchesTransactionType = transactionType.equalsIgnoreCase("All") || transactionType.equalsIgnoreCase(transaction.getTransaction_type());
            boolean matchesParty = selectedParty.equals("All") || selectedParty.equalsIgnoreCase(transaction.getParty());
            boolean withinDateRange = (startDate == null || endDate == null) || (transaction.getTransaction_date_time().isAfter(startDate) && transaction.getTransaction_date_time().isBefore(endDate));

            // If all criteria match, add the transaction to the filtered list
            if (matchesSearchTerm && matchesTransactionType && matchesParty && withinDateRange) {
                filteredTransactions.add(transaction);
            }
        }

        return filteredTransactions;
    }

    public int sumQuantities(List<Transaction> filteredTransactions) {
        int totalQuantity = 0;

        for (Transaction transaction : filteredTransactions) {
            totalQuantity += transaction.getQuantity();
        }
        return totalQuantity;
    }

    public boolean receiveStockFromSupplier(String item_code, int quantityReceived) {
        // Update item quantity when receiving stock from supplier
        for (PPE ppeItem : ppe) {
            if (ppeItem.getItem_code().equalsIgnoreCase(item_code)) {
                ppeItem.setQuantity(ppeItem.getQuantity() + quantityReceived);
                updatePPEToFile();
                return true;
            }
        }
        return false; //Return false if the item code does nor exist
    }

    public boolean distributeStockToHospital(String item_code, int quantityDistributed) {
        for (PPE ppeItem : ppe) {
            if (ppeItem.getItem_code().equalsIgnoreCase(item_code)) {
                if (ppeItem.getQuantity() >= quantityDistributed) {
                    ppeItem.setQuantity(ppeItem.getQuantity() - quantityDistributed);
                    updatePPEToFile();
                    return true;
                } else {
                    return false; // Not enough stock to distribute
                }
            }
        }
        return false; // Return false if the item code does not exist
    }

    public PPE searchItemByCode(String item_code) {
        String searchTerm = item_code.toUpperCase().trim();

        for (PPE ppe_item : ppe) {
            String itemCode = ppe_item.getItem_code() != null ? ppe_item.getItem_code().toUpperCase() : "";
            // If the item code matches the search term, return the PPE item
            if (itemCode.contains(searchTerm)) {
                return ppe_item;
            }
        }
        //return null if no match
        return null;
    }

    public boolean deleteTransaction(String transaction_id) {
        for (Transaction transaction : transactions) {
            if (transaction.getTransaction_ID().equals(transaction_id)) {
                transactions.remove(transaction);
                updateTransactionsToFile();
                return true;
            }
        }
        return false;
    }

    public void updateTransactionsToFile() {
        String filePath = "src/transaction.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Transaction transaction : transactions) {
                // Write each transaction to the file in the same format it was read 
                writer.write(transaction.getTransaction_ID() + "\t"
                        + transaction.getItem_code() + "\t"
                        + transaction.getItem_name() + "\t"
                        + transaction.getParty() + "\t"
                        + transaction.getTransaction_status() + "\t"
                        + transaction.getQuantity() + "\t"
                        + transaction.getTransaction_type() + "\t"
                        + transaction.getFormattedTransactionDateTime() + "\t"
                        + transaction.getPrice_per_box() + "\t"
                        + transaction.getTotal_price());
                writer.newLine(); // Move to the next line for the next transaction
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Transaction findTransactionByID(String transaction_ID) {
        for (Transaction transaction : transactions) {
            if (transaction.getTransaction_ID().equals(transaction_ID)) {
                return transaction; // Return the user object if found
            }
        }
        return null; // Return null if user not found
    }

    public List<PPE> getTotalAvailableQuantitySorted() {
        List<PPE> sortedPPE = new ArrayList<>(ppe);
        sortedPPE.sort(Comparator.comparing(PPE::getItem_code));
        return sortedPPE;
    }

    public List<PPE> getLowStockItems() {
        return ppe.stream()
                .filter(ppe -> ppe.getQuantity() < 25)
                .collect(Collectors.toList());
    }

    public List<PPE> trackPPEReceivedInDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        Map<String, PPE> ppeReceivedMap = new HashMap<>();

        for (Transaction transaction : transactions) {
            // Check if the transaction is of type "Received" and within the date range
            if (transaction.getTransaction_type().equalsIgnoreCase("Received")
                    && (transaction.getTransaction_date_time().isAfter(startDate) || transaction.getTransaction_date_time().isEqual(startDate))
                    && (transaction.getTransaction_date_time().isBefore(endDate) || transaction.getTransaction_date_time().isEqual(endDate))) {

                String item_code = transaction.getItem_code();
                int quantityReceived = transaction.getQuantity();
                BigDecimal pricePerBox = transaction.getPrice_per_box();
                BigDecimal totalPrice = pricePerBox.multiply(BigDecimal.valueOf(quantityReceived));

                // If PPE item is already in the map, add the quantity and total price
                if (ppeReceivedMap.containsKey(item_code)) {
                    PPE existingPPE = ppeReceivedMap.get(item_code);
                    existingPPE.setQuantity(existingPPE.getQuantity() + quantityReceived);
                    existingPPE.updateTotalPrice();
                } else {
                    // Find the corresponding PPE item by item code
                    PPE ppeItem = searchItemByCode(item_code);
                    if (ppeItem != null) {
                        // Set the initial received quantity and total price for this PPE item
                        PPE newPPE = new PPE(ppeItem.getItem_code(), ppeItem.getItem_name(), ppeItem.getSupplier_code(),
                                quantityReceived, pricePerBox, totalPrice);
                        ppeReceivedMap.put(item_code, newPPE);
                    }
                }
            }
        }

        // Convert the map to a list of PPE items to display in the table
        return new ArrayList<>(ppeReceivedMap.values());
    }

}
