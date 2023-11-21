import java.util.Scanner;

public class SubnetCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the IP address: ");
        String ip = sc.nextLine();  // nextLine - it takes whole line as input

        // Validate IP address format
        String[] splitIp = ip.split("\\."); // if ip was "192.168.1.1", then after the split, splitIp would be an array with the elements "192", "168", "1", and "1".
        if (splitIp.length != 4) // 4 element should be their 
        {
            System.out.println("Invalid IP address format. Please enter a valid IP address.");
            return;
        }

        // Validate each part of the IP address
        for (String part : splitIp)  // just like python for part in splitIp
        {
            if (part.isEmpty()) {
                System.out.println("Invalid IP address format. Please enter a valid IP address.");
                return;
            }
        }

        StringBuilder binaryIp = new StringBuilder(); 
        for (String part : splitIp) 
        {
            // here first part is converted into int -> Integer.parseInt(part)
            // then this is send to Integer.toBinaryString( above expression ) this will convert that integer into binary string
            // string.format ("%8s",above expression) it will format the binary string into 8 and where ever their is not 8 it will add " " in front
            // replace( " ","0") it will replace space with 0
            // at end this will be append to binaryIp obj
            binaryIp.append(String.format("%8s", Integer.toBinaryString(Integer.parseInt(part))).replace(' ', '0'));
        }

        System.out.println("IP in binary is " + binaryIp);

        System.out.print("Enter the number of addresses: ");
        int n = sc.nextInt();

        //Math.log(n) / Math.log(2): Divides the natural logarithm of n by the natural logarithm of 2.
        //This is a common technique for computing the binary logarithm.
        // Math.ceil(...): Rounds up the result of the division to the nearest integer
        // (int) ...: Casts the result to an integer.
        int bits = (int) Math.ceil(Math.log(n) / Math.log(2));
        System.out.println("Number of bits required for address = " + bits);

        int mask = 32 - bits; // direct showing in integer 
        System.out.println("The subnet mask is = " + mask);

        int[] fbip = new int[32];
        for (int i = 0; i < 32; i++) {
            fbip[i] = (int) binaryIp.charAt(i) - '0';
        }

        for (int i = 31; i > 31 - bits; i--) {
            fbip[i] &= 0;
        }

        StringBuilder fip = new StringBuilder();
        for (int i = 0; i < 32; i += 8) {
            int decimalPart = Integer.parseInt(binaryIp.substring(i, i + 8), 2);
            fip.append(decimalPart);
            if (i < 24) {
                fip.append(".");
            }
        }

        System.out.println("First address is = " + fip);

        int[] lbip = new int[32];
        for (int i = 0; i < 32; i++) {
            lbip[i] = (int) binaryIp.charAt(i) - '0';
        }

        for (int i = 31; i > 31 - bits; i--) {
            lbip[i] |= 1;
        }

        StringBuilder lip = new StringBuilder();
        for (int i = 0; i < 32; i += 8) {
            int decimalPart = Integer.parseInt(binaryIp.substring(i, i + 8), 2);
            lip.append(decimalPart);
            if (i < 24) {
                lip.append(".");
            }
        }

        System.out.println("Last address is = " + lip);
    }
}
