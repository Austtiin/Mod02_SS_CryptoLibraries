//CryptoHelper.java
//This class will contain the logic for generating the MD5 hash of the user input

import javax.crypto.Cipher;
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
    private static final byte[] KEY = "PROFESSORZayass!".getBytes();


    //processFile method - bring in the file name
    public void processFile(String fileName) {
        File file = new File(fileName);
        String fileContent = "";

        //If the file exists, read the file
        if (file.exists()) {
            try (Scanner fileScanner = new Scanner(file)) {
                //if the file has a next line, read the file
                //we are setting the file content and showing the encrypted file data
                if (fileScanner.hasNextLine()) {
                    fileContent = fileScanner.nextLine();
                    System.out.println("Encrypted File Data " + fileContent);

                    //decrypt the file content and show the decrypted content
                    String decryptedContent = decrypt(fileContent);
                    System.out.println("Decrypted content: " + decryptedContent);
                }
            } catch (IOException e) {
                System.out.println("Cannot read file / error: " + e.getMessage());
            }
        }

        //get new input / 'data' from the user
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a string to encrypt: ");
        String input = scanner.nextLine();
        scanner.close();

        //Encrypt the input
        String encryptedContent = encrypt(input);
        //call encrypt method and show the encrypted content / new MD5 hash
        System.out.println("Encrypted content: " + encryptedContent);



        //new we need to update the file with the new encrypted content
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(encryptedContent);

        } catch (IOException e) {
            System.out.println("File Write Error" + e.getMessage());
        }
    }

    //Encrypt method - bring in the input
    private String encrypt(String input) {
        try {
            //set the key spec and cipher
            //side note: key spec is the key for the encryption
            SecretKeySpec keySpec = new SecretKeySpec(KEY, ALGORITHM);

            //cipher is the encryption
            //Cipher is apart of the javax.crypto package
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec);

            //encrypt the input and return the encrypted bytes, bytes are the smallest unit of data
            byte[] encryptedBytes = cipher.doFinal(input.getBytes());

            //then we return the encrypted bytes as a string because we are using Base64 encoding
            return Base64.getEncoder().encodeToString(encryptedBytes);


        } catch (Exception e) {
            System.out.println("Error during encryption: " + e.getMessage());
            return null;
        }
    }


//decrypt method - bring in the encrypted content
    private String decrypt(String encryptedContent) {
        try {
            //grabbing our secret key and setting the cipher
            SecretKeySpec keySpec = new SecretKeySpec(KEY, ALGORITHM);
            //setting the cipher to decrypt
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            //initiating the cipher to decrypt
            cipher.init(Cipher.DECRYPT_MODE, keySpec);


            //decoding the encrypted content and decrypting the bytes
            byte[] decodedBytes = Base64.getDecoder().decode(encryptedContent);

            //return the decrypted bytes as a string
            byte[] decryptedBytes = cipher.doFinal(decodedBytes);
            return new String(decryptedBytes);


        } catch (Exception e) {
            System.out.println("Error decrypting " + e.getMessage());
            return null;
        }
    }
}