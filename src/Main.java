//imports
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        //new scanner
        Scanner inp = new Scanner(System.in);
        //initial print statement
        System.out.println("This is a decimal converter. It converts positive integer decimal, hexadecimal, and binary numbers to each other.");
        System.out.println(" ");
        System.out.println("Conversion types:");
        System.out.println("1. Decimal to Binary");
        System.out.println("2. Decimal to Hexadecimal");
        System.out.println("3. Binary to Decimal");
        System.out.println("4. Binary to Hexadecimal");
        System.out.println("5. Hexadecimal to Decimal");
        System.out.println("6. Hexadecimal to Binary");
        System.out.print("Enter the number of the conversion you want to use: ");
        //initial input for conversion type
        String type = inp.nextLine();
        //tests input and continues to ask for a new one if it is invalid
        while (!(type.equals("1")) && !(type.equals("2")) && !(type.equals("3")) && !(type.equals("4")) && !(type.equals("5")) && !(type.equals("6"))) {
            System.out.println(" ");
            System.out.println("Invalid input. Please enter a number between 1 and 6.");
            System.out.println("Enter the number of the conversion you want to use: ");
            type = inp.nextLine();
        }
        System.out.println(" ");

        //prints for decimal conversions
        if (type.equals("1") || type.equals("2")) {
            System.out.print("Enter the decimal number you want to convert (must be a positive integer and cannot exceed the max integer value): ");
            //decimal input
            String dec = inp.nextLine();
            //asks for new input until valid
            while (!Converter.isDecimal(dec)) {
                System.out.println(" ");
                System.out.println("Invalid input. Please enter a positive integer below the max integer value.");
                System.out.print("Enter the decimal number you want to convert: ");
                dec = inp.nextLine();
            }
            //prints conversion result for the selected conversion
            if (type.equals("1")) {
                System.out.println(" ");
                System.out.println("The binary value of " + dec + " is " + Converter.decToBin(dec) + ".");
            } else {
                System.out.println(" ");
                System.out.println("The hexadecimal value of " + dec + " is " + Converter.decToHexa(dec) + ".");
            }

            //prints for binary conversions
        } else if (type.equals("3") || type.equals("4")) {
            //prints separate requirements for each conversion
            if (type.equals("3")) {
                System.out.print("Enter the binary number(should not exceed 31 digits) you want to convert(Ex: formatted: \"0100 1011\" or unformatted: \"1001011\"): ");
            } else {
                System.out.print("Enter the binary number you want to convert(Ex: formatted: \"0100 1011\" or unformatted: \"1001011\"): ");
            }
            //binary input
            String bin = inp.nextLine();
            //asks for new input until valid
            while (!Converter.isBinary(bin, type)) {
                System.out.println(" ");
                //separate print statements required for each conversion type
                if (type.equals("3")) {
                    System.out.println("Invalid input. Please enter a binary number with less than 31 digits.");
                } else {
                    System.out.println("Invalid input. Please enter a binary number.");
                }
                System.out.print("Enter the binary number you want to convert: ");
                bin = inp.nextLine();
            }
            //prints conversion result for the selected conversion
            if (type.equals("3")) {
                    System.out.println(" ");
                    System.out.println("The decimal value of " + bin + " is " + Converter.binToDec(bin) + ".");
            } else {
                    System.out.println(" ");
                    System.out.println("The hexadecimal value of " + bin + " is " + Converter.binToHexa(bin) + ".");
            }

            //prints for hexadecimal conversions
        } else {
            //separate print statements depending on conversion type
            if (type.equals("5")) {
                System.out.print("Enter the hexadecimal number you want to convert (ex: AB3F96 or ab3f96): ");
            } else {
                System.out.print("Enter the hexadecimal number (less than 8 digits) you want to convert (ex: AB3F96 or ab3f96): ");
            }
            System.out.print("Enter the hexadecimal number you want to convert (ex: AB3F96 or ab3f96): ");
            //hexadecimal input
            String hexa = inp.nextLine();
            //asks for new input until valid
            while (!Converter.isHexa(hexa, type)) {
                System.out.println(" ");
                //separate print statements depending on conversion type
                if (type.equals("5")) {
                    System.out.println("Invalid input. Please enter a hexadecimal number.");
                } else {
                    System.out.println("Invalid input. Please enter a hexadecimal number with less than 8 digits.");
                }
                System.out.print("Enter the hexadecimal number you want to convert: ");
                hexa = inp.nextLine();

            }
            //prints conversion result for the selected conversion
            if (type.equals("5")) {
                System.out.println(" ");
                System.out.println("The binary value of " + hexa + " is " + Converter.hexaToBin(hexa) + ".");
            } else {
                System.out.println(" ");
                System.out.println("The decimal value of " + hexa + " is " + Converter.hexaToDec(hexa) + ".");
            }

        }
        //closes scanner
        inp.close();



    }
}