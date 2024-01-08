/**
 * A class used to test different Encryption types and their implementations.
 * Currently tests the Clear, Caesar and Vigenere algorithms.
 * 
 * Parts Sourced from: IC211 Project 2 TestEncryptors.java
 * @author MIDN 3/C Tristan M. Lee
 */
import java.util.*;

public class TestEncryptors {
    public static void main(String[] args) throws Throwable {
        // Create ArrayList of all supported encryptors
        ArrayList<Encryptor> E = new ArrayList<Encryptor>();

        // <Add New Hashes Here as Additional Encryptors are configured>
        E.add(new Clear());
        E.add(new Caesar());
        E.add(new Vigenere());

        // Get alg,psw,msg from user
        System.out.print("algorithm: ");
        String encalg = System.console().readLine();
        System.out.print("password : ");
        char[] password = System.console().readPassword();
        System.out.print("message  : ");
        String plaintext = System.console().readLine();

        // Find index of encryptor (throw exception if not found)
        int i = -1;
        try {
            while( !E.get(++i).getAlgName().equals(encalg) ) ;
        } catch(IndexOutOfBoundsException e) {
            throw new NoSuchElementException("Unknown algorithm '" + encalg +"'.");
        }

        // Check the ASCII of the Key and the Plaintext to be encrypted
        try {
            InvalidCharException.checkASCII(password, "key");
            InvalidCharException.checkASCII(plaintext.toCharArray(), "plaintext");
        } catch(InvalidCharException e){
            throw e;
        }

        // Encrypt, decrypt print summary of results
        E.get(i).init(password);
        String ciphertext = E.get(i).encrypt(plaintext);
        String hopefully = E.get(i).decrypt(ciphertext);
        System.out.println("plain : " + plaintext);
        System.out.println("cipher: " + ciphertext);
        System.out.println("decryp: " + hopefully);
    }
}
