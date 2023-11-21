import java.net.InetAddress;
import java.net.UnknownHostException;

public class DNSLookup {
    public static void main(String[] args) {
        // Example usage
        dnsLookup("127.0.0.1");
        dnsLookup("www.google.com");
        dnsLookup("142.250.199.132"); // Example with duplicate entry
    }

    private static void dnsLookup(String query) {
        try {
            InetAddress ipAddress = InetAddress.getByName(query);
            InetAddress localhost = InetAddress.getLocalHost();
            String canonicalHostName = InetAddress.getByName(ipAddress.getHostAddress()).getCanonicalHostName();

            System.out.println("----------------------------------------------------");
            System.out.println("Which Host: " + query);
            System.out.println(" ip address name : " + ipAddress.getHostName());

            System.out.println("Canonical Host Name: " + canonicalHostName);
            System.out.println("Host Name: " + localhost.getHostName());
            System.out.println("Host Address: " + ipAddress.getHostAddress());
        } catch (UnknownHostException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
