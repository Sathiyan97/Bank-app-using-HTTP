import java.time.LocalDateTime;

public class Transaction {
    private static int numberOfTransaction = 50000;
    LocalDateTime myDate;
    private int transactionid;
    private int sourceAccount;
    private String description;
    private double amount;
    //LocalDateTime.now();

    Transaction() {
        transactionid = numberOfTransaction++;
    }

    Transaction(int transactionid, int sourceAccount, String description, double amount, LocalDateTime myDate) {
        this.transactionid = transactionid;
        this.sourceAccount = sourceAccount;
        this.description = description;
        this.amount = amount;
        this.myDate = myDate;
    }

    public int getSourceAccount() {
        return sourceAccount;
    }

    public void setSourceAccount(int sourceAccount) {
        this.sourceAccount = sourceAccount;
    }

    public int getTransactionid() {
        return transactionid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDateTime getMyDate() {
        return myDate;
    }
}
