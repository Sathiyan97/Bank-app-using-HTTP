package main.java;

public class Customer {
    private Account accountNo;
    private String name;
    private String nic;
    private String address;


    Customer(String name, String nic, String address, Account accountNo) {
        this.name = name;
        this.address = address;
        this.nic = nic;
        this.accountNo = accountNo;

    }

    @Override
    public String toString() {
        return "Name: " + name +" \n "+ " Account No :" + accountNo;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress(String address) {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    Account getAccountNo() {
        return accountNo;
    }
}
