/**
 * A class that supports the Vigenere Cipher
 * by performing encryption and decryption using
 * the Vigenere algorithm.
 * 
 * @author MIDN 3/C Tristan M. Lee
 */
public class Vigenere extends Mechanism{
    // The Encryption Key used throughout the Vigenere Object
    private char[] encryp_key;

    /**
     * Used to the retrieve the Algorithm's name. 
     * Mostly used for comparisons.
     * 
     * @return A String with the Algorithms name
     */
    public String getAlgName()
    { 
        return "vigenere";
    }

    /**
     * Initalizes the key value used in the Vigenere
     * algorithm
     * 
     * @param key A char[] key used as the encryption key
     */
    public void init(char[] key)
    {
        encryp_key = key;
    }

    /**
     * Encrypts a plaintext using the Vigenere Cipher
     * 
     * @param plain A plaintext String to encrypt
     * @return A String representing the CipherText encrypted 
     */
    public String encrypt(String plain)
    {
        return algorithm(plain, 'e');
    }

    /**
     * Decrypts a ciphertext using the Vigenere Cipher
     * 
     * @param cipher A ciphertext String to decrypt
     * @return A String representing the decrypted plaintext
     */
    public String decrypt(String cipher)
    {
        return algorithm(cipher, 'd');
    }

    /**
     * A helper method that outlines the algorithmic steps
     * to perform the Vigenere Cipher for decryption and encryption
     * 
     * @param str A String to be encrypted or decrypted
     * @param c A char -> 'e' for encryption, 'd' for decryption
     * @return A String of either plaintext or ciphertext
     * decrypted using Vigenere
     */
    private String algorithm(String str, char c)
    {
        // Used as a switch for encryption or decryption
        // since the two processes share similar steps
        Boolean gate = (c == 'e');
        char [] itxt = str.toCharArray();
        char [] otxt = new char[itxt.length];

        // Encrypting or Decrypting the Vigenere Cipher
        for(int i = 0; i < itxt.length; i++)
        {
            char cc = itxt[i];
            sc = encryp_key[i % encryp_key.length];
            if(gate)
                otxt[i] = super.encrypt(cc);
            else
                otxt[i] = super.decrypt(cc);
        }
        return new String(otxt);
    }
}
