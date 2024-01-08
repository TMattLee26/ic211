import java.util.*;

/**
 * A class used to store all the Hashing Algorithms
 * 
 * @author MIDN 3/C Tristan M. Lee
 */
public abstract class HashInventory
{
    // A globally accessible HashMap<String,Hasher>
    public static HashMap<String, Hasher> H = initHashInventory();

    /**
     * A Helper method to add algorithms to the HashMap of Hashing Algorithmns
     * @return A HashMap<String, Hasher> of Hashing Algorithms and their names
     */
    private static HashMap<String, Hasher> initHashInventory()
    {
        HashMap<String, Hasher> map = new HashMap<>();
        map.put("clear", new ClearHash());
        map.put("shift+caesar", new CaesarHash());
        map.put("shift+vigenere", new VigenereHash());
        return map;
    }
}