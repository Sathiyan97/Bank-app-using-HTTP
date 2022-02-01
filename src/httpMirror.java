import java.io.*;
import java.math.BigDecimal;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This program is a very simple Web server. When it receives a HTTP request it
 * sends the request back as the reply. This can be of interest when you want to
 * see just what a Web client is requesting, or what data is being sent when a
 * form is submitted, for example.
 */
class HttpMirror {

    Bank banks = new Bank();

    public static void main(String[] args) {
        HttpMirror obj = new HttpMirror();
        Account acc = new Account();
        try {

            ServerSocket ss = new ServerSocket(8000);
            // Now enter an infinite loop, waiting for & handling connections.
            while (true) {

                Socket client = ss.accept();

                // Get input and output streams to talk to the client
                BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                PrintWriter out = new PrintWriter(client.getOutputStream());
                OutputStream output = client.getOutputStream();

                List<String> list = new ArrayList<>();

                String line = in.readLine();
                while (line.length() > 0) {
                    System.out.println(line);
                    list.add(line);
                    line = in.readLine();
                }


                String requestLineStr = list.get(0);
                String[] requestLineArr = requestLineStr.split(" ");
                String method = requestLineArr[0];
                String URI = requestLineArr[1];
                String status = requestLineArr[2];

//                System.out.println("URI = " + URI);

                List<String> headerList = list.subList(1, list.size() - 1);

                Map<String, String> headersMap = new HashMap<>();

                for (int i = 0; i < headerList.size(); i++) {
                    String line1 = headerList.get(i);
                    String[] arr = line1.split(" ");
                    headersMap.put(arr[0], arr[1]);
                }

//                System.out.println(Collections.singletonList(headersMap)); // method 1
//                System.out.println(Collections.singletonList(headersMap));


                if (method.equals("GET")) {
//                    System.out.println("Hello GET");
                    out.println("HTTP/1.0 200 OK");
                    out.println("Content-Type: text/html");
                    out.println("");
//
                    StringBuilder webHtml = new StringBuilder();
                    FileReader fileReader = new FileReader("/Users/sathiyan/Documents/Bank app using HTTP/src/web/web.html");
                    BufferedReader bufferReader = new BufferedReader(fileReader);
                    String val;
                    while ((val = bufferReader.readLine()) != null) {
                        webHtml.append(val);
                    }
                    String result = webHtml.toString();

                    out.write(result);
                    out.write("\r\n\r\n");

                } else if (method.equals("POST")) {
//                    System.out.println("Hello POST");
                    out.println("HTTP/1.0 200 OK");
                    out.println("Content-Type: text/html");
                    out.println("");

                    String contentLength = headersMap.get("Content-Length:");

                    char[] data = new char[Integer.parseInt(contentLength)];
                    in.read(data);
                    String content = new String(data);
                    System.out.println("content = " + content);


                    String[] contentData = content.split("&");
                    String[] data1 = contentData[0].split("=");
                    String[] data2 = contentData[1].split("=");
                    String[] data3 = contentData[2].split("=");
                    String alloutput;
//                    System.out.println("URI = " + URI.trim());
                    switch (URI) {
                        case "/create":
                            alloutput = obj.accountCreate(data1[1], data2[1], data3[1]);

                            out.write(alloutput);
                            break;
                        case "/deposit":
                            int accNum = Integer.parseInt(data2[1]);
                            double amount = Double.parseDouble(data3[1]);
                            alloutput = obj.makeDeposit(accNum, amount);
                            out.write(alloutput);
                            break;

                        case "/withdrawal":
                            int accNum1 = Integer.parseInt(data2[1]);
                            double amount1 = Double.parseDouble(data3[1]);
                            alloutput = obj.makeWithdrawal(accNum1, amount1);
                            out.write(alloutput);
                            break;
                        case "/transfer":
                            int fromAccNum = Integer.parseInt(data1[1]);
                            int toAccNum = Integer.parseInt(data2[1]);
                            double amount2 = Double.parseDouble(data3[1]);
                            alloutput = obj.makeTransfer(fromAccNum, toAccNum, amount2);
                            out.write(alloutput);
                            break;


                    }


                }

//                out.flush();
                out.close();
                in.close();
                client.close();
            }
        }
        // If anything goes wrong, print an error message
        catch (Exception e) {
            System.err.println();
            System.err.println("Usage: java HttpMirror <port>");
        }
    }


    public String accountCreate(String name, String nic, String address) {
        Account accountNo = new Account();
        Customer customs = new Customer(name, nic, address, accountNo);

        banks.addCustomer(customs);
        return customs.toString();
    }

    public String makeDeposit(int accountNo, double amount) {
        if (banks.getCustomerIndex(accountNo) < 0) {
            return "Invalid account number";
        } else {
            boolean b = banks.getCustomer(accountNo).getAccountNo().depositt(amount);
            if (b) {
                Transaction t = new Transaction();
                LocalDateTime myDateObj = LocalDateTime.now();
                Transaction t1 = new Transaction(t.getTransactionid(), accountNo, "Deposit", amount, myDateObj);
                banks.addTransaction(t1);
                return "Account No : " + banks.getCustomer(accountNo).getAccountNo().toString();
            }
            return "You can not deposit negative values";

        }


    }

    public String makeWithdrawal(int accountNo, double amount) {
        if (banks.getCustomerIndex(accountNo) < 0) {
            return "Invalid account number";
        } else {
            boolean b = banks.getCustomer(accountNo).getAccountNo().withdraw(amount);
            if (b) {
                Transaction t = new Transaction();
                LocalDateTime myDateObj = LocalDateTime.now();
                Transaction t1 = new Transaction(t.getTransactionid(), accountNo, "Deposit", amount, myDateObj);
                banks.addTransaction(t1);
                return "Account No : " + banks.getCustomer(accountNo).getAccountNo().toString();
            }
            return "Insufficient Balance";

        }
    }

    public String makeTransfer(int fromAcc, int toAcc, double amount) {
        if (banks.getCustomerIndex(fromAcc) < 0 || banks.getCustomerIndex(toAcc) < 0) {
            return "Invalid account Number";
        } else {
            BigDecimal d1 = banks.getCustomer(fromAcc).getAccountNo().getBalance();
            BigDecimal d2 = BigDecimal.valueOf(amount);
            //double d = bank.getCustomer(fromAccountNo).getAccountNo().getBalance();
            BigDecimal d3 = d1.max(d2);
            if (d3.equals(d2)) {
                return "insufficient balance";
            } else {
                Transaction t = new Transaction();
                LocalDateTime myDateObj = LocalDateTime.now();
                Transaction t1 = new Transaction(t.getTransactionid(), fromAcc, "Transfer-Debit", amount, myDateObj);
                banks.addTransaction(t1);
                banks.getCustomer(fromAcc).getAccountNo().withdraw(amount);
                Transaction t2 = new Transaction(t.getTransactionid(), toAcc, "Transfer-Credit", amount, myDateObj);
                banks.addTransaction(t2);
                banks.getCustomer(toAcc).getAccountNo().depositt(amount);

            }
            return "From Account No : " + banks.getCustomer(fromAcc).getAccountNo().toString() + "\nTo Account No : " + banks.getCustomer(toAcc).getAccountNo().toString();
        }
    }

}