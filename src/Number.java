import java.lang.Math;
import java.util.ArrayList;
import java.util.logging.Logger;

public class Number {
    int number;
    public Number(int n_){
        number=n_;
    }
    public int getDenary(){
        return number;
    }
    public void setDenary(int n_){
        number = n_;
    }

    public String getSignedBinary(){
        String result="0";
        int n = number;

        if (n > 0) {
            int maxPower = 0;
            int digitFromRight = 0;
            while (n > (Math.pow(2, maxPower+1)-1)) {
                maxPower++;
            }

            while (digitFromRight <= maxPower){
                if (n >= Math.pow(2, (maxPower-digitFromRight))){
                    n -= Math.pow(2, (maxPower-digitFromRight));
                    result += "1";
                }
                else
                    result += "0";
                digitFromRight++;
            }
        }
        else if (n < 0) {
            number = -number;
            String posBinary = getSignedBinary();
            char[] posBinaryChars = new char[posBinary.length()];
            posBinary.getChars(0,posBinary.length(), posBinaryChars, 0);
            System.out.println("BEFORE FLIP: "+String.valueOf(posBinaryChars));

            for (int i=0; i < posBinaryChars.length; i++){
                if (posBinaryChars[i] =='0'){
                    posBinaryChars[i] = '1';
                } else{
                    posBinaryChars[i] = '0';
                }
            }
            System.out.println("AFTER FLIP: "+String.valueOf(posBinaryChars));
            boolean increaseDigit = true;
            for (int i = posBinaryChars.length - 1; i >= 0; i--){
                if (posBinaryChars[i] == '0'){
                    posBinaryChars[i] = '1';
                    increaseDigit = false;
                    break;
                }
                else {
                    posBinaryChars[i] = '0';
                }
            }

            if (increaseDigit){
                result = "1" + String.valueOf(posBinaryChars);
            } else {
                result = String.valueOf(posBinaryChars);
            }
            number = -number;
        }

        else {
            return "0";
        }

        return result;
    }
    public String getHexadecimal(){
        String binary = getSignedBinary();
        ArrayList<String> halfBytes = new ArrayList<String>();
        while (!binary.isEmpty()){
            if (binary.length() >=4) {
                String halfByte = binary.substring(binary.length() - 4);
                binary = binary.substring(0, binary.length() - 4);
                halfBytes.add(halfByte);
            } else {
                halfBytes.add(binary);
                binary = "";
            }
        }
        String result = "";
        for (int i = halfBytes.size()-1; i >= 0; i--){
            String b = halfBytes.get(i);
            int bNumber = 0;
            for (int j = 0; j < b.length(); j++){
               bNumber += Integer.parseInt( "" + b.charAt( b.length() - 1 - j ) ) * (int)Math.pow(2, j);
            }
            if (bNumber < 10){
                result += bNumber;
            }
            else if (bNumber == 10) result += "A";
            else if (bNumber == 11) result += "B";
            else if (bNumber == 12) result += "C";
            else if (bNumber == 13) result += "D";
            else if (bNumber == 14) result += "E";
            else if (bNumber == 15) result += "F";

        }
        while (result.charAt(0) == '0')
            result = result.substring(1);
        return result;
    }

    public void negate(){
        number = -number;
    }

    public String toString(){
        return "Number{denary=" + getDenary() + " binary=" + getSignedBinary() + " hexadecimal=" +getHexadecimal() + "}";
    }


    public static void main(String[] args) {
        Number n = new Number(148);
        System.out.println("OUTPUT: "+n);
    }
}

