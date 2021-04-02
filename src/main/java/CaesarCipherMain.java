import java.util.Scanner;

public class CaesarCipherMain {
    public static final String alph = "abcdefghijklmnopqrstuvwxyz";
    // static because all other functions are static, and we can't reference from a
    // non-static

    public static String encoding(String plainT, int shift) {
        plainT = plainT.toLowerCase();
        // converting the text to lowercase
        String cipherT = "";
        // initializing empty string to add alphabets iteratively
        for (int i = 0; i < plainT.length(); i++) {
            int mappingV = alph.indexOf(plainT.charAt(i));
            // value of each alphabet in integers like for A=0, B=1 ...
            int enVal = (shift + mappingV) % 26;
            char Val = alph.charAt(enVal); // the character to be replaced
            cipherT = cipherT + Val; // adding to ciphertext
        }
        return cipherT;
    }

    // following same algorithm but in reverse way, plaintext becomes
    // ciphertext and vice versa
    public static String decoding(String cipherT, int shift) {
        cipherT = cipherT.toLowerCase();
        // converting the text to lowercase
        String plainT = "";
        // initializing empty string to add alphabets iteratively
        for (int i = 0; i < cipherT.length(); i++) {
            int mappingV = alph.indexOf(cipherT.charAt(i));
            int deVal = (mappingV - shift) % 26;
            if (deVal < 0) // to handle the negative values
            {
                deVal = alph.length() + deVal;
            }
            char Val = alph.charAt(deVal); // the character to be replaced
            plainT = plainT + Val; // adding to plaintext
        }
        return plainT;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the text message to be encrypted ");
        String msg = new String();
        msg = scan.next();
        System.out.println(" Encrypted Text : " + encoding(msg, 4));
        System.out.print(" Decryptd Text : ");
        System.out.print(decoding(encoding(msg, 4), 4));
        scan.close();
    }
}