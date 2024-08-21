//CryptoHelper.java
//This class will contain the logic for generating the MD5 hash of the user input

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Base64;
import java.util.Scanner;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


class CryptoHelper {

    //This class will contain the logic for generating the MD5 hash of the user input
    private static final String ALGORITHM = "AES";
    private static final byte[] KEY = "ProfessorZaya!z!".getBytes(); // 16-byte key for AES-128


    //process file method
    //bring in the file name
    public void processFile(String fileName) {

        //create a new file object and set it to the file name
        File file = new File(fileName);
        String fileContent = "";


        //if the file exists then read file
        if (file.exists()) {

            //try to read the file and if there is a next line then read the file
            try (Scanner fileScanner = new Scanner(file)) {
                if (fileScanner.hasNextLine()) {
                    fileContent = fileScanner.nextLine();
                    System.out.println("Encrypted File Data: " + fileContent);
                    String decryptedContent = decrypt(fileContent);
                    System.out.println("Decrypted Data: " + decryptedContent);
                }
            } catch (IOException e) {
                System.out.println("Read file / error: " + e.getMessage());
            }
        }



        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a string to encrypt: ");
        String input = scanner.nextLine();
        scanner.close();

        //generate the MD5 hash of the input per requirements
        String md5Hash = generateMD5Hash(input);
        System.out.println("MD5 Hash: " + md5Hash);

        String encryptedContent = encrypt(input);
        System.out.println("Encrypted content: " + encryptedContent);

        try (FileWriter writer = new FileWriter(file)) {
            writer.write(encryptedContent);
            writer.write("\n");
            writer.write(md5Hash);
        } catch (IOException e) {
            System.out.println("File Write Error: " + e.getMessage());
        }
    }



    //encrypt method
    //bring in the input
    private String encrypt(String input) {

        //try to encrypt the input
        try {

            //new instance of the secret key spec ad cipher
            SecretKeySpec keySpec = new SecretKeySpec(KEY, ALGORITHM);
            Cipher cipher = Cipher.getInstance(ALGORITHM);

            //initialize the cipher
            cipher.init(Cipher.ENCRYPT_MODE, keySpec);

            //get the bytes of the input and encrypt the bytes
            byte[] encryptedBytes = cipher.doFinal(input.getBytes());

            //return the encrypted bytes
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            System.out.println("Error during encryption: " + e.getMessage());
            return null;
        }
    }


    //decrypt method
    //bring in the encrypted content
    private String decrypt(String encryptedContent) {

        //try to decrypt the encrypted content
        try {
            //initalize / new instance of the secret key spec and cipher
            SecretKeySpec keySpec = new SecretKeySpec(KEY, ALGORITHM);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, keySpec);

            //decode the encrypted content and decrypt the bytes
            byte[] decodedBytes = Base64.getDecoder().decode(encryptedContent);
            byte[] decryptedBytes = cipher.doFinal(decodedBytes);

            //return the decrypted bytes
            return new String(decryptedBytes);
        } catch (Exception e) {
            System.out.println("Error during decryption: " + e.getMessage());
            return null;
        }
    }


    //generate MD5 hash method
    private String generateMD5Hash(String input) {

        //try to generate the MD5 hash of the input
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");

            //update the message digest with the input bytes
            md.update(input.getBytes());

            //digest the message
            byte[] digest = md.digest();

            //create a new string builder
            StringBuilder sb = new StringBuilder();

            //for each byte in the digest append the string
            for (byte b : digest) {
                sb.append(String.format("%02x", b & 0xff));
            }

            //then return the string
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
}