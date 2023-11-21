import java.io.FileOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class p9server {
    public static void main(String[] args) {
        int port = 9876; // Change to desired port

        try (DatagramSocket socket = new DatagramSocket(port)) {
            byte[] receiveData = new byte[1024];

            while (true) {
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                socket.receive(receivePacket);

                String fileName = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Receiving file: " + fileName);

                receivePacket = new DatagramPacket(receiveData, receiveData.length);
                socket.receive(receivePacket);

                int fileSize = Integer.parseInt(new String(receivePacket.getData(), 0, receivePacket.getLength()));
                System.out.println("File size: " + fileSize + " bytes");

                try (FileOutputStream fileOutputStream = new FileOutputStream(fileName)) {
                    int totalReceived = 0;
                    while (totalReceived < fileSize) {
                        receivePacket = new DatagramPacket(receiveData, receiveData.length);
                        socket.receive(receivePacket);

                        int receivedLength = receivePacket.getLength();
                        fileOutputStream.write(receiveData, 0, receivedLength);
                        totalReceived += receivedLength;
                    }
                }

                System.out.println("File received: " + fileName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
