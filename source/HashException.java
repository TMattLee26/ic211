/**
 * HashException is a subclass of the Exception class
 * with the task of handling errors relating to
 * the hashing algorithms.
 *
 * @author MIDN 3/C Tristan M. Lee
 */
public class HashException extends Exception
{
    /**
     * Creates a HashException with a message
     * @param msg The msg to be thrown with the exception
     */
    public HashException(String msg) 
    {
        super(msg);
    }

    /**
     * Verifies if the hashing algorithm is supported 
     * 
     * @param hashalg A String detailing the potential hashing algorithm to use/used
     * @throws HashException
     */
    public static void hashSupport(String hashalg) throws HashException{
        if(HashInventory.H.get(hashalg) == null)
            throw new HashException("Error! Hash algorithm '" + hashalg + "' not supported.");
    }
}