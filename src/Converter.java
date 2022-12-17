public class Converter {

    public Converter() {

    }

    public static boolean isDecimal(String dec) {
        for (int i = 0; i < dec.length(); i++) {
            if (dec.charAt(i) < '0' || dec.charAt(i) > '9') {
                return false;
            }
        }
        try {
            int num = Integer.parseInt(dec);
            return num >= 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isBinary(String bin, String type) {

        if (type.equals("3") && bin.length() > 31) {
            return false;
        }

        if (!(bin.contains("1")) && !(bin.contains("0"))) {
            return false;
        }

        for (int i = 0; i < bin.length(); i++) {
            if (bin.charAt(i) != '0' && bin.charAt(i) != '1') {
                if (bin.charAt(i) != ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isHexa(String hexa, String type) {

        if (type.equals("6") && hexa.length() > 7) {
            return false;
        }
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

    private static String formatBin(String endBin) {
        if (endBin.length() % 4 != 0) {
            int numZeros = 4 - (endBin.length() % 4);
            for (int i = 0; i < numZeros; i++) {
                endBin = "0" + endBin;
            }
        }

        for (int i = endBin.length() - 4; i > 0; i--) {
            if (i % 4 == 0) {
                endBin = endBin.substring(0, i) + " " + endBin.substring(i);
            }
        }
        return endBin;
    }

    public static String decToBin(String dec) {
        int binCt = 1;
        String endBin = "";
        int decNum = Integer.parseInt(dec);

        if (decNum == 0) {
            return "0000";
        }

        while(decNum - binCt >=0){
            binCt *= 2;
        }

        binCt /= 2;

        while(binCt > 0){
            if(decNum - binCt >= 0){
                endBin = endBin + "1";
                decNum -= binCt;
            }else{
                endBin = endBin + "0";
            }
            binCt/=2;
        }

        return formatBin(endBin);

    }

    public static String decToHexa(String dec){
        int decNum = Integer.parseInt(dec);
        String hexa = "";
        int remainder;

        if (decNum == 0) {
            return "0";
        }

        while(decNum > 0)
        {
            remainder = decNum % 16;
            decNum /= 16;
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
        return hexa;
    }

    public static int binToDec(String bin) {
        int dec = 0;
        int binCt = 1;
        while(bin.contains(" ")) {
            bin = bin.substring(0, bin.indexOf(" ")) + bin.substring(bin.indexOf(" ") + 1);
        }
        for (int i = bin.length() - 1; i>=0; i--) {
            if (bin.charAt(i) == '1') {
                dec += binCt;
            }
            binCt *= 2;
        }
        return dec;
    }

    public static String binToHexa(String bin) {
        String currentBin;
        String hexa = "";
        while(bin.contains(" ")) {
            bin = bin.substring(0, bin.indexOf(" ")) + bin.substring(bin.indexOf(" ") + 1);
        }

        if (bin.length() % 4 != 0) {
            int numZeros = 4 - (bin.length() % 4);
            for (int i = 0; i < numZeros; i++) {
                    bin = "0" + bin;}
        }

        for (int i = 0; i < bin.length(); i+=4){
            currentBin = (bin.substring(i,i+4));

            int binCt = 1;
            int dec = 0;
            for (int j = 3; j>=0; j--) {
                if (currentBin.charAt(j) == '1') {
                    dec += binCt;
                }
                binCt *= 2;
            }
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
        return hexa;
    }

    public static String hexaToBin(String hexa) {
        char currentHexa;
        int binSectDec;
        String endBin = "";

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

            int binCt = 1;
            while(binSectDec - binCt >= 0){
                binCt *= 2;
            }

            binCt /= 2;

            if (binSectDec <= 7 && binSectDec >= 4) {
                endBin += "0";
            } else if (binSectDec <= 3 && binSectDec >= 2) {
                endBin += "00";
            } else if (binSectDec == 1) {
                endBin += "000";
            } else if (binSectDec == 0) {
                endBin += "0000";
            }

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

        return formatBin(endBin);
    }

    public static int hexaToDec(String hexa) {
        char currentHexa;
        int currentDec;
        int decVal = 0;
        int multiplier = 1;

        for (int i = hexa.length() - 1; i >= 0; i--) {
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
            decVal += currentDec*multiplier;
            multiplier*=16;
        }
        return decVal;
    }

}
