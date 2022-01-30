import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    Scanner sc = new Scanner(System.in);
    int choice;

    public static void main(String[] args) throws Exception {
        Socket s = new Socket("localhost", 8080);
        DataInputStream in = new DataInputStream(s.getInputStream());
        DataOutputStream out = new DataOutputStream(s.getOutputStream());
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String clientInput = "", clientOutput = "";
        while (!clientInput.equals("0")) {
            Client cc = new Client();

            int addChoice = cc.bankMenu();
            clientInput = br.readLine();
            String addtoinput = addChoice + "|";
            String string3 = addtoinput + clientInput;
            out.writeUTF(string3);
            out.flush();
            clientOutput = in.readUTF();
            System.out.println("Your Account details: \n" + clientOutput);
        }


        out.close();
        s.close();
    }

    public int bankMenu() {
        System.out.println("\nEnter your choice\n ");
        System.out.println("1.Create new account" +
                "\n2.Deposit \n3.Withdraw \n4.Transfer\n0.Exit");
        choice = sc.nextInt();
        switch (choice) {
            case 0:
                System.exit(0);
                break;
            case 1:
                System.out.println("Enter name | NIC No | Address");
                break;
            case 2:
                System.out.println("Enter name | Account No | amount ");
                break;
            case 3:
                System.out.println("Enter name | Account No | amount ");
                break;
            case 4:
                System.out.println("From Account No |To Account No | amount ");
                break;
            default:
                System.out.println("Invalid input");
        }
        return choice;
    }


}
