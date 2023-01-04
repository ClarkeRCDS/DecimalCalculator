public class Converter {

    //empty constructor because no Convertor objects are made, just use of static methods
    public Converter() {

    }

    //tests the decimal input for non-decimal characters and returns a boolean to validate it
    //also checks if the decimal is too long with a try, catch
    public static boolean isDecimal(String dec) {
        //checks for non-decimal characters in the string
        for (int i = 0; i < dec.length(); i++) {
            if (dec.charAt(i) < '0' || dec.charAt(i) > '9') {
                return false;
            }
        }
        //requires decimal to be lower than max value
        try {
            int num = Integer.parseInt(dec);
            return num >= 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    //tests the binary input for non-binary characters and returns a boolean to validate it
    public static boolean isBinary(String bin, String type) {
        String testBin = bin;
        //removes spaces before checking
        while(testBin.contains(" ")) {
            testBin = testBin.substring(0, testBin.indexOf(" ")) + testBin.substring(testBin.indexOf(" ") + 1);
        }
        //limits the length to 30 if conversion type 3
        if (type.equals("3") && testBin.length() > 31) {
            return false;
        }
        //makes sure bin is not just spaces
        if (!(bin.contains("1")) && !(bin.contains("0"))) {
            return false;
        }
        //checks for non-binary characters in the string
        for (int i = 0; i < bin.length(); i++) {
            if (bin.charAt(i) != '0' && bin.charAt(i) != '1') {
                if (bin.charAt(i) != ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    //tests the hexadecimal input for non-hexadecimal characters and returns a boolean to validate it
    public static boolean isHexa(String hexa, String type) {
        //limits the length to 7 character if conversion type 6
        if (type.equals("6") && hexa.length() > 7) {
            return false;
        }
        //checks for non-hexadecimal characters in the string
        for (int i = 0; i < hexa.length(); i++) {
            if (hexa.charAt(i) < '0' || hexa.charAt(i) > '9') {
                if (hexa.charAt(i) < 'A' || hexa.charAt(i) > 'F') {
                    if (hexa.charAt(i) < 'a' || hexa.charAt(i) > 'f') {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    //formats the binary number into units of four
    private static String formatBin(String endBin) {
        //makes the length divisible by 4 by adding zeros to the beginning.
        //this creates even units of four
        if (endBin.length() % 4 != 0) {
            int numZeros = 4 - (endBin.length() % 4);
            for (int i = 0; i < numZeros; i++) {
                endBin = "0" + endBin;
            }
        }
        //adds a space every four characters to divide these units and make the number easier to read
        for (int i = endBin.length() - 4; i > 0; i--) {
            if (i % 4 == 0) {
                endBin = endBin.substring(0, i) + " " + endBin.substring(i);
            }
        }
        //returns the formatted binary number
        return endBin;
    }
    //converts a decimal value to a binary value
    public static String decToBin(String dec) {
        //stores the value of each digit in the binary number
        int binCt = 1;
        //holds each binary digit as it is added
        String endBin = "";
        //holds the decimal number in integer form
        int decNum = Integer.parseInt(dec);

        //returns 0000 in the case of a zero input
        if (decNum == 0) {
            return "0000";
        }
        //this while loop and the line below it find the max digit that the binary number reaches
        while(decNum - binCt >=0){
            binCt *= 2;
        }
        binCt /= 2;

        //this loop adds the ones and zeros to the binary number until it reaches zero
        //a one is added if the result of binCt subtraction is positive and a zero is added if the result is negative
        while(binCt > 0){
            if(decNum - binCt >= 0){
                endBin = endBin + "1";
                decNum -= binCt;
            }else{
                endBin = endBin + "0";
            }
            //divides binCt so the number gets halved after each subtraction
            binCt/=2;
        }
        //formats the binary and returns it
        return formatBin(endBin);

    }

    //converts a decimal value to a hexadecimal value
    public static String decToHexa(String dec){
        //holds the integer form of the decimal number
        int decNum = Integer.parseInt(dec);
        //holds each hexadecimal digit as it is added
        String hexa = "";
        //holds the value of the remainder after division
        int remainder;

        //returns zero if the input was zero
        if (decNum == 0) {
            return "0";
        }

        //divides the decimal number by 16 until it reaches zero
        //as it does this, a hexadecimal number is being taken from each remainder from modulo 16
        while(decNum > 0)
        {
            //gets the remainder
            remainder = decNum % 16;
            //divides the decimal and truncates because the numbers after the decimal point are redundant
            decNum /= 16;

            //converts the remainder to a hexadecimal value and adds it the the hexadecimal number
            if (remainder <= 9){
                hexa = remainder + hexa;
            } else if(remainder == 10) {
                hexa = "A" + hexa;
            } else if(remainder == 11) {
                hexa = "B" + hexa;
            } else if(remainder == 12) {
                hexa = "C" + hexa;
            } else if(remainder == 13) {
                hexa = "D" + hexa;
            }else if(remainder == 14) {
                hexa = "E" + hexa;
            }else {
                hexa = "F" + hexa;
            }
        }
        //returns the hexadecimal number
        return hexa;
    }

    //converts a binary value to a decimal value
    public static int binToDec(String bin) {
        //holds the decimal value as the binary digits are added
        int dec = 0;
        //increases the binary digit value as they are added
        int binCt = 1;

        //removes all spaces from the binary number
        while(bin.contains(" ")) {
            bin = bin.substring(0, bin.indexOf(" ")) + bin.substring(bin.indexOf(" ") + 1);
        }
        //for every one in the binary number the value of that digit is added to the decimal value
        for (int i = bin.length() - 1; i>=0; i--) {
            if (bin.charAt(i) == '1') {
                dec += binCt;
            }
            //increase binary digit value
            binCt *= 2;
        }
        //returns the decimal value
        return dec;
    }

    //converts a binary value to a hexadecimal value
    public static String binToHexa(String bin) {
        //holds the a unit of four binary digits
        String currentBin;
        //holds the hexadecimal value as the binary digits are added
        String hexa = "";

        //removes all spaces from the binary number
        while(bin.contains(" ")) {
            bin = bin.substring(0, bin.indexOf(" ")) + bin.substring(bin.indexOf(" ") + 1);
        }

        //adds a zero to the beginning of the binary number if the length is not divisible by four
        //this creates even units of four
        if (bin.length() % 4 != 0) {
            int numZeros = 4 - (bin.length() % 4);
            for (int i = 0; i < numZeros; i++) {
                    bin = "0" + bin;}
        }

        //for every unit of four binary digits, the hexadecimal value is added to the hexadecimal number
        for (int i = 0; i < bin.length(); i+=4){
            //gets the unit of four binary digits
            currentBin = (bin.substring(i,i+4));

            //holds the decimal value of each binary digit in each unit
            int binCt = 1;
            //holds the decimal value of the unit of four binary digits
            int dec = 0;
            //for every one in the binary number the value of that digit is added to the decimal value
            for (int j = 3; j>=0; j--) {
                if (currentBin.charAt(j) == '1') {
                    dec += binCt;
                }
                binCt *= 2;
            }

            //converts the decimal value to a hexadecimal value and adds it to the hexadecimal number
            if (dec <= 9){
                hexa += dec;
            } else if(dec == 10) {
                hexa += "A";
            } else if(dec == 11) {
                hexa += "B";
            } else if(dec == 12) {
                hexa += "C";
            } else if(dec == 13) {
                hexa += "D";
            }else if(dec == 14) {
                hexa += "E";
            }else if(dec == 15) {
                hexa += "F";
            }
        }
        //returns the hexadecimal number
        return hexa;
    }

    //converts a hexadecimal value to a binary value
    public static String hexaToBin(String hexa) {
        //holds the current hexadecimal digit
        char currentHexa;
        //holds the decimal value of the current hexadecimal digit
        int binSectDec;
        //holds the binary value as the hexadecimal digits are added
        String endBin = "";

        //converts each hexadecimal digit to a decimal value
        for (int i = 0; i < hexa.length(); i++) {
            currentHexa = hexa.charAt(i);
            if (currentHexa == 'F') {
                binSectDec = 15;
            } else if (currentHexa == 'E') {
                binSectDec = 14;
            } else if (currentHexa == 'D') {
                binSectDec = 13;
            } else if (currentHexa == 'C') {
                binSectDec = 12;
            } else if (currentHexa == 'B') {
                binSectDec = 11;
            } else if (currentHexa == 'A') {
                binSectDec = 10;
            } else {
                binSectDec = Character.getNumericValue(currentHexa);
            }

            //holds the decimal value of each binary digit
            int binCt = 1;
            //this while loop and the line below it find the max digit that the binary number reaches
            while(binSectDec - binCt >= 0){
                binCt *= 2;
            }
            binCt /= 2;

            //adds zero(s) to each binary unit depending on the number to make it four digits long
            if (binSectDec <= 7 && binSectDec >= 4) {
                endBin += "0";
            } else if (binSectDec <= 3 && binSectDec >= 2) {
                endBin += "00";
            } else if (binSectDec == 1) {
                endBin += "000";
            } else if (binSectDec == 0) {
                endBin += "0000";
            }

            //adds the binary digits to the binary number
            while(binCt > 0){
                if(binSectDec - binCt >= 0){
                    endBin += "1";
                    binSectDec -= binCt;
                }else{
                    endBin += "0";
                }
                binCt/=2;
            }
        }
        //returns the formatted binary number
        return formatBin(endBin);
    }

    //converts a hexadecimal value to a hexadecimal value
    public static int hexaToDec(String hexa) {
        //holds the current hexadecimal digit
        char currentHexa;
        //holds the decimal value of the current hexadecimal digit
        int currentDec;
        //holds the decimal value as the hexadecimal digits are added
        int decVal = 0;
        //increases amount as each hexadecimal digit is added
        int multiplier = 1;
        //sets the decimal value of each hexadecimal digit
        for (int i = hexa.length() - 1; i >= 0; i--
        ) {
            currentHexa = hexa.charAt(i);
            if (currentHexa == 'F') {
                currentDec = 15;
            } else if (currentHexa == 'E') {
                currentDec = 14;
            } else if (currentHexa == 'D') {
                currentDec = 13;
            } else if (currentHexa == 'C') {
                currentDec = 12;
            } else if (currentHexa == 'B') {
                currentDec = 11;
            } else if (currentHexa == 'A') {
                currentDec = 10;
            } else {
                currentDec = Character.getNumericValue(currentHexa);
            }
            //adds the decimal value of the hexadecimal digit to the decimal value multiplied by the value of the digit
            decVal += currentDec*multiplier;
            //increases the multiplier by a factor of 16
            multiplier*=16;
        }
        return decVal;
    }

}
