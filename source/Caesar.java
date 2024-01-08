/**
 * A class that supports the Vigenere Cipher
 * by performing encryption and decryption using
 * the Vigenere algorithm.
 * 
 * @author MIDN 3/C Tristan M. Lee
 */
public class Caesar extends Mechanism{
    /**
     * Used to the retrieve the Algorithm's name. 
     * Mostly used for comparisons.
     * 
     * @return A String with the Algorithms name
     */
    public String getAlgName()
    {
        return "caesar"; 
    }
    /**
     * Initializes the Shift Value for the Caesar Cipher
     * 
     * @param key The char[] used to derive the shiftValue to
     * use in the Caesar Cipher algorithm.
     */
    public void init(char[] key)
    {
        sc = shiftValue(key);
    }

    /**
     * Encrypts a plaintext using the Caesar Cipher
     * 
     * @param plain A Plaintext String to encrypt
     * @return A String representing the CipherText encrypted 
     */
    public String encrypt(String plain)
    {
        return algorithm(plain, 'e');
    }
    /**
     * Decrypts a ciphertext using the Vigenere Cipher
     * 
     * @param cipher A Ciphertext String to Decrypt
     * @return A String representing the plaintext decrypted
     */
    public String decrypt(String cipher)
    { 
        return algorithm(cipher, 'd');
    }

    /**
     * A helper method that calculates the Caesar Cipher's shiftvalue
     * 
     * @param key The char[] to derive the shift value
     * @return A char representing the shiftValue of the Caesar cipher
     */
    private char shiftValue(char[] key)
    {
        int sub = 18;
        for(char c : key)
            sub += (c - 42);
        char sc = (char)(42 + (sub % 81));
        return sc;
    }

    /**
     * A helper method that outlines the algorithmic steps
     * to perform the Caesar Cipher for decryption and encryption
     * 
     * @param str A String to be encrypted or decrypted
     * @param c A char -> 'e' for encryption, 'd' for decryption
     * @return A String of either plaintext or ciphertext
     * decrypted using Caesar
     */
    private String algorithm(String str, char c)
    {
        Boolean gate = (c == 'e');
        char [] itxt = str.toCharArray();
        String otxt = "";
        for(char a : itxt)
            if(gate) 
                otxt += super.encrypt(a);
            else
                otxt += super.decrypt(a);
        return otxt;
    }
}
