//CryptoHelper.java
//This class will contain the logic for generating the MD5 hash of the user input

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Base64;
import java.util.Scanner;

class CryptoHelper {

    //setting variables for the algorithm and key
    private static final String ALGORITHM = "AES";
    //key for the encryption
    private static final byte[] KEY = "PROFESSORZayas".getBytes();


    //processFile method - bring in the file name
    public void processFile(String fileName) {
        File file = new File(fileName);
        String fileContent = "";

        //If the file exists, read the file
        if (file.exists()) {
            try (Scanner fileScanner = new Scanner(file)) {
                //if the file has a next line, read the file
                if (fileScanner.hasNextLine()) {
                    fileContent = fileScanner.nextLine();
                    System.out.println("Encrypted File Data " + fileContent);
                    String decryptedContent = decrypt(fileContent);
                    System.out.println("Decrypted content: " + decryptedContent);
                }
            } catch (IOException e) {
                System.out.println("Error reading file: " + e.getMessage());
            }
        }

        // Prompt the user for new input
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a string to encrypt: ");
        String input = scanner.nextLine();
        scanner.close();

    }

    private String encrypt(String input) {

    }

    private String decrypt(String encryptedContent) {

    }
}