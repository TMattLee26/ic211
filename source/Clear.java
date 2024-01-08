/**
 * A class that supports the Clear Algorithm which
 * is essentially no encryption or decryption. The
 * text stays plaintext.
 * 
 * @author MIDN 3/C Tristan M. Lee
 */
public class Clear implements Encryptor {
    /**
     * Used to the retrieve the Algorithm's name. 
     * Mostly used for comparisons.
     * 
     * @return A String with the Algorithms name
     */
    public String getAlgName()
    { 
        return "clear"; 
    }
    /**
     * Does nothing. Declared to support the interface
     * @param key A Char[] Key
     */
    public void init(char[] key)
    {
        return;
    }
    /**
     * Encrypts using nothing so it just returns the 
     * plaintext.
     * 
     * @param plain A string of plaintext
     * @return A String representing the encrypted text
     */
    public String encrypt(String plain)
    { 
        return plain;
    }
    /**
     * Decrypts using nothing so it just returns the cipher
     * 
     * @param cipher A String of ciphertext
     * @return A string represnting the plaintext
     */
    public String decrypt(String cipher)
    { 
        return cipher; 
    }
}
