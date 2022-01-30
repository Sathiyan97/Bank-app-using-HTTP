package Http;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
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
    public static <JSONObject> void main(String[] args) {
        try {

            ServerSocket ss = new ServerSocket(8000);
            // Now enter an infinite loop, waiting for & handling connections.
            while (true) {

                Socket client = ss.accept();

                // Get input and output streams to talk to the client
                BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                PrintWriter out = new PrintWriter(client.getOutputStream());
                StringBuilder buffer = new StringBuilder();


                List<String> list = new ArrayList<>();

                String line = in.readLine();
                while (line.length() > 0) {
                    System.out.println(line);
                    list.add(line);
                    line = in.readLine();
                }
//                in.readLine();
//                String bodyLine = in.readLine();
//                while(bodyLine != null && bodyLine.length() > 0){
//                    System.out.println(bodyLine);
//                    out.println(bodyLine);
//                    bodyLine = in.readLine();
//                }


                String requestLineStr = list.get(0);
                String[] requestLineArr = requestLineStr.split(" ");
                String method = requestLineArr[0];
                String URI = requestLineArr[1];
                String status = requestLineArr[2];


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
                    out.println("<form action=\"\" method=\"post\">\n" +
                            "    <fieldset>\n" +
                            "        <legend>Deposit Amount</legend>\n" +
                            "        <label for=\"accNo\">Enter your account number : </label> <br>\n" +
                            "        <input type=\"text\" id=\"accNo\" name=\"accNo\"><br>\n" +
                            "        <label for=\"amt\">   Enter deposit amount : </label><br>\n" +
                            "        <input type=\"text\" id=\"amt\" name=\"amount\"><br>\n" +
                            "        <button id=\"depositBtn\">Deposit</button><br>\n" +
                            "    </fieldset>\n" +
                            "</form> ");
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


                }


                out.close();
                in.close();
                client.close();
            }
        }
        // If anything goes wrong, print an error message
        catch (Exception e) {
            System.err.println();
            System.err.println("Usage: java Http.HttpMirror <port>");
        }
    }
}
