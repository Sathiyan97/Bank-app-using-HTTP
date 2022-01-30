import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Date;

class SimpleHTTPServer {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(8080);
        System.out.println("Listening for connection on port 8000 ....");





        while (true) {
            try (Socket socket = server.accept()) {
                DataInputStream in = new DataInputStream(socket.getInputStream());
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
//                System.out.println("Hello");
//

//                Date today = new Date();
//                String httpResponse = "HTTP/1.1 200 OK\r\n\r\n" + today;
//                socket.getOutputStream().write(httpResponse.getBytes(StandardCharsets.UTF_8));
            }
        }
    }
}

//     void sendPage(Socket client) throws Exception {
//        System.out.println("Page writter called");
//
//        PrintWriter printWriter = new PrintWriter(client.getOutputStream());//Make a writer for the output stream to the client
//        BufferedReader reader = new BufferedReader(new FileReader("path/to/index.html"));//grab a file and put it into the buffer
//        String line = reader.readLine();//line to go line by line from file
//        while (line != null)//repeat till the file is empty
//        {
//            printWriter.println(line);//print current line
//            printWriter.flush();// I have also tried putting this outside the while loop right before
//            printWriter.close();
//            line = reader.readLine();//read next line
//        }
//
//
//    }


