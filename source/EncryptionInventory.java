import java.util.*;

/**
 * A class used to store all the Encryption Algorithms
 * 
 * @author MIDN 3/C Tristan M. Lee
 */
public abstract class EncryptionInventory
{
    // A globally accessible HashMap<String,Encryptor>
    public static HashMap<String, Encryptor> E = initEncryptInventory();

    /**
     * A Helper method to add algorithms to the HashMap of Encryption Algorithmns
     * @return A HashMap<String, Encryptor> of Encryption Algorithms and their names
     */
    private static HashMap<String, Encryptor> initEncryptInventory()
    {
        HashMap<String, Encryptor> map = new HashMap<>();
        map.put("clear", new Clear());
        map.put("caesar", new Caesar());
        map.put("vigenere", new Vigenere());
        return map;
    }
}