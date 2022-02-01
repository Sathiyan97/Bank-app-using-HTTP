import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Bank {
    ArrayList<Customer> customers = new ArrayList<Customer>();
    ArrayList<Transaction> transactions = new ArrayList<Transaction>();

    public void addCustomer(Customer customer) {
        customers.add(customer);

    }

    int getCustomerIndex(int account) {
        int index = -1;
        for (int i = 0; i < customers.size(); i++) {
            int acco = customers.get(i).getAccountNo().getAccountNumber();
            if (acco == account) {
                index = i;

            }


        }
        return index;
    }

    Customer getCustomer(int account) {


        int index = -1;
        for (int i = 0; i < customers.size(); i++) {
            int acco = customers.get(i).getAccountNo().getAccountNumber();
            if (acco == account) {
                index = i;

            }


        }

        return customers.get(index);
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    void getTransaction(int account) {
        int index;
        int x = 1;
        for (int i = 0; i < transactions.size(); i++) {

            if (transactions.get(i).getSourceAccount() == account) {
                System.out.println("Transaction " + x + "\nTransaction ID : " + transactions.get(i).getTransactionid());
                System.out.println("Description : " + transactions.get(i).getDescription() + "\nAmount : " + transactions.get(i).getAmount());
                DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                LocalDateTime myDateObj = transactions.get(i).getMyDate();
                String formattedDate = myDateObj.format(myFormatObj);
                System.out.println("Transaction time : " + formattedDate);
                x++;
            }
        }
    }


}
