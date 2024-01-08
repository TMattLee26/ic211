/**
 * A class that supports Shift+Vigenere Hashes.
 * 
 * @author MIDN 3/C Tristan M. Lee
 */
public class VigenereHash extends Vigenere implements Hasher
{
    /**
     * Used to the retrieve the Algorithm's name. 
     * Mostly used for comparisons.
     * 
     * @return A String with the Algorithms name
     */
    public String getAlgName()
    { 
        return "shift+vigenere"; 
    }
    
    /**
     * Computes a shift+vigenere Hash
     * 
     * @param key A char[] used in the Vigenere Cipher as the
     * key in the algorithm.
     * @return A String that is the computed hash
     */
    public String computeHash(char[] key)
    {
        String str = new String(key);
        String iv = Hasher.IV;
        String hash = iv;

        // Shift+ Implementatin utilized for Strings over 16 characters
        while(str.length() > 16)
        {
            // Use the first 16 characters as the key
            init(str.substring(0,16).toCharArray());

            // Perform the shift+vigenere hash
            for(int i = 0; i < 16; i++)
                hash = encrypt(Hasher.shifter(i, hash));
            
            // Set our Initalization vector to the calculated hash 
            // and use that new iv to calculate the next 16 characters in the String
            iv = hash;
            str = str.substring(16);
        }

        // For any keys less than 16 characters including the last block of initial
        // greater than 16 characters calculate a normal shift+vigenere hash
        init(str.toCharArray());

        for(int i = 0; i < 16; i++)
            hash = encrypt(Hasher.shifter(i, hash));

        return hash;
    }
}