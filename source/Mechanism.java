/**
 * A class used to support the Caesar and Vigenere 
 * Encryption Algorithms taht both rely on the same individual
 * character encryption method.
 * 
 * @author MIDN 3/C Tristan M. Lee
 */
public abstract class Mechanism implements Encryptor {
    // Declared in the Caesar and Vigenere ciphers
    protected char sc;

    /**
     * Used to encrypt individual characters in the Caesar
     * and Vigenere ciphers.
     * 
     * @param pc A plaintext char
     * @return An ciphertext char
     */
    protected char encrypt(char pc)
    {
        int k = sc - 42;
        int p = pc - 42;
        int c = (p + k) % 81;
        int cc = 42 + c;
        return (char)cc;
    }

    /**
     * Used to decrypt individual characters in the Caesar
     * and Vigenere ciphers.
     * 
     * @param pc A ciphertext char
     * @return An plaintext char
     */
    protected char decrypt(char cc)
    {
        int k = sc - 42;
        int c = cc - 42;
        int p = (c + (81 - k)) % 81;
        int pc = 42 + p;
        return (char)pc;
    }
}
