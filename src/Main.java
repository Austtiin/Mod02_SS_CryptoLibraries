//main.java
//This will be the main entry of the prgoram

//Austin Stephens
//Professor Zayas
//CEN4071C
//Module 02 Assignment - Crypto Libraries

//Instructions
//Develop a Java program that:
//imports one of the crypto libraries
//takes in keyboard text input
//generates and prints the MD5 hash of the keyboard text input

//we will be using the java.security.MessageDigest class to generate the MD5 hash of the user input



//we will have a seperate class for the main entry of the program
//this will allow us to keep the main entry of the program seperate from the actual logic of the program
//we will also use helper classes to keep the code clean and organized
// our helper class will be called CryptoHelper
//this class will contain the logic for generating the MD5 hash of the user input


public class Main {

    public static void main(String[] args) {
        //new instances of our classes / run the program

        try {
            CryptoHelper cryptoHelper = new CryptoHelper();
            cryptoHelper.processFile("data.txt");
        } catch (Exception e) {
            System.out.println("Error  " + e.getMessage());
        }
    }
}