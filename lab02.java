import java.util.Scanner;

/**
 * Floating-Point Converter
 * Converts a floating-point number into IEEE 754 single-precision format
 * (1-bit sign, 8-bit exponent, 23-bit significand).
 */
public class lab02 {
    
    /**
     * Converts a floating-point number to IEEE 754 single-precision format.
     */
    public static void convertToIEEE754(float number) {
        int intBits = Float.floatToIntBits(number); // Get IEEE 754 raw bits

        // Extract IEEE 754 components
        int signBit = (intBits >>> 31) & 1;
        int exponent = (intBits >>> 23) & 0xFF; // 8-bit exponent
        int significand = intBits & 0x7FFFFF; // 23-bit fraction

        // Display results
        System.out.println("=== IEEE 754 Single-Precision Representation ===");
        System.out.println("Input: " + number);
        System.out.println("Sign Bit: " + signBit);
        System.out.println("Exponent (8 bits): " + String.format("%8s", Integer.toBinaryString(exponent)).replace(' ', '0'));
        System.out.println("Significand (23 bits): " + String.format("%23s", Integer.toBinaryString(significand)).replace(' ', '0'));
        System.out.println("Final Representation: " + signBit + " " +
                String.format("%8s", Integer.toBinaryString(exponent)).replace(' ', '0') + " " +
                String.format("%23s", Integer.toBinaryString(significand)).replace(' ', '0'));
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Taking user input for demonstration
        System.out.print("Enter a floating-point number: ");
        float input1 = scanner.nextFloat();
        convertToIEEE754(input1);

        System.out.print("Enter another floating-point number: ");
        float input2 = scanner.nextFloat();
        convertToIEEE754(input2);

        scanner.close();
    }
}
