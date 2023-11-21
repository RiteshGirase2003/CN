import java.net.*;

public class UDPClient {
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket();

            // Send data to the server
            String message = "Hello from UDP client!";
            byte[] sendData = message.getBytes();

            // InetAddress is a Java class that represents an IP address
            // eg. InetAddress address = InetAddress.getByName("www.example.com");
            InetAddress serverAddress = InetAddress.getByName("127.0.0.1");
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, 8080);
            socket.send(sendPacket);

            // Receive response from the server
            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            socket.receive(receivePacket);
            String serverResponse = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("Received from server: " + serverResponse);

            // Close socket
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
