/**
 * An Interface for the Encryption Algorithms
 * 
 * Sourced from: IC211 Project 2
 * @author MIDN 3/C Tristan M. Lee
 */
public interface Encryptor {
    public String getAlgName();
    public void   init(char[] key);
    public String encrypt(String plain);
    public String decrypt(String cipher);
}
