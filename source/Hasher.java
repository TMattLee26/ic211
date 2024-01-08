/**
 * An Interface for the Hashing Algorithms
 * 
 * @author MIDN 3/C Tristan M. Lee
 */
public interface Hasher {
    // The Initialization Vector used for all hashes
    public static final String IV = "GO_NAVY_2018^mid";

    // Supports the Hashing Algorithms
    public abstract String getAlgName();
    public abstract String computeHash(char[] paswd);

    /**
     * Used to help the Shift+Caesar and Shift+Vigenere hashing
     * algorithms.
     * 
     * @param index The Index of the Chararcter to shift
     * @param str The original String of the shifted Character
     * @return The new String with the character shifted
     */
    public static String shifter(int index, String str)
    {
        char c = str.charAt(index);
        int k = ((int)c) % 16;
        k = (k < 0) ? k + 16 : k;
        str = str.substring(k) + str.substring(0, k);
        return str;
    }
}
