import java.io.*;
import java.net.*;

public class TCPClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 8081);

            // Send data to the server
            //  same as server
            
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println("Hello from TCP client!");

            // Receive response from the server
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String serverResponse = in.readLine();
            System.out.println("Received from server: " + serverResponse);

            // Close socket
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
