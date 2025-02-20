import java.util.Scanner;
/**
* FloatingPointConverter.java
 * 
 * This program converts a floating-point number into a simplified floating-point representation
 * with 1-bit sign, 5-bit exponent (excess-15 representation), and 8-bit significand.
 * The program takes a floating-point number as input and prints its simplified binary form.
 *
 * Author: khushi Verma
 * 
 */
public class lab02 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Get user input
        System.out.print("Enter a floating-point number: ");
        float number = scanner.nextFloat();
        
        // Convert and display result
        String result = convertToSimplifiedFloatingPoint(number);
        System.out.println("Simplified floating-point representation: " + result);
        
        // Test with two examples
        System.out.println("Test Cases:");
        System.out.println("Positive Example (5.75): " + convertToSimplifiedFloatingPoint(5.75f));
        System.out.println("Negative Example (-3.25): " + convertToSimplifiedFloatingPoint(-3.25f));
        
        scanner.close();
    }

    public static String convertToSimplifiedFloatingPoint(float num) {
        // Get the sign bit (1 if negative, 0 if positive)
        int sign = (num < 0) ? 1 : 0;
        
        // Convert to absolute value
        num = Math.abs(num);
        
        // Get the exponent and significand using IEEE 754-like approach
        int exponent = 0;
        float significand = num;
        
        // Normalize the significand to the form 0.1xxxxxxxx
        while (significand >= 2.0) {
            significand /= 2.0;
            exponent++;
        }
        while (significand < 1.0 && significand != 0.0) {
            significand *= 2.0;
            exponent--;
        }
        
        // Adjust exponent with excess-15 bias
        int biasedExponent = exponent + 15;
        
        // Extract the 8 bits after the leading 1 in the significand
        int significandBits = 0;
        for (int i = 0; i < 8; i++) {
            significand *= 2;
            if (significand >= 1.0) {
                significandBits = (significandBits << 1) | 1;
                significand -= 1.0;
            } else {
                significandBits = (significandBits << 1);
            }
        }
        
        // Format as binary string
        String signBit = Integer.toBinaryString(sign);
        String exponentBits = String.format("%5s", Integer.toBinaryString(biasedExponent)).replace(' ', '0');
        String significandBinary = String.format("%8s", Integer.toBinaryString(significandBits)).replace(' ', '0');
        
        return signBit + " " + exponentBits + " " + significandBinary;
    }
}
