/**
 * A class that supports Shift+Caesar Hashes.
 * 
 * @author MIDN 3/C Tristan M. Lee
 */
public class CaesarHash extends Caesar implements Hasher
{
    /**
     * Used to the retrieve the Algorithm's name. 
     * Mostly used for comparisons.
     * 
     * @return A String with the Algorithms name
     */
    public String getAlgName()
    { 
        return "shift+caesar"; 
    }
    
    /**
     * Computes a shift+caesar Hash
     * 
     * @param key A char[] used in the Caesar Cipher as the
     * key in the algorithm.
     * @return A String that is the computed hash
     */
    public String computeHash(char[] key)
    {
        // Initalize Key for Caesar Cipher
        init(key);

        // Create String to hold Hashes
        String hash = Hasher.IV;

        for(int i = 0; i < 16; i++)
            hash = encrypt(Hasher.shifter(i, hash));

        return hash;
    }
}