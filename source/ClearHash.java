/**
 * A class that supports the Clear Hash.
 * 
 * @author MIDN 3/C Tristan M. Lee
 */
public class ClearHash implements Hasher
{
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
     * Computes the clear hash which is the first 16
     * characters of the String. If the string is less than
     * 16 characters, it is made 16 characters by appending x's
     * on the end
     * 
     * @param A A char[] array of text to be hashed.
     * @return A String that is the computed hash
     */
    public String computeHash(char[] A)
    {
        String hash = "";
        for(int i = 0; i < 16; i++)
        {
            if(i < A.length)
                hash += A[i];
            else
                hash += 'x';
        }
        return hash;
    }
}