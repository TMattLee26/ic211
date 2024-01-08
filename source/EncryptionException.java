/**
 * EncryptionException is a subclass of the Exception class
 * with the task of handling errors relating to
 * the encryption algorithms.
 *
 * @author MIDN 3/C Tristan M. Lee
 */
public class EncryptionException extends Exception
{
    /**
     * Creates an EncryptionException with a message
     * @param msg The msg to be thrown with the exception
     */
    public EncryptionException(String msg) 
    {
        super(msg);
    }

    /**
     * Verifies if the Encryption algorithm is supported 
     * 
     * @param encalg A String detailing the potential encryption algorithm to use/used
     * @throws EncryptionException
     */
    public static void encryptSupport(String encalg) throws EncryptionException{
        if(EncryptionInventory.E.get(encalg) == null)
            throw new EncryptionException("Error! Encryption algorithm '" + encalg + "' not supported.");
    }

    /**
     * Verifies the incoming Ciphertext falls in the ASCII range 42-122
     * @param encContent A String of Ciphertext
     * @throws EncryptionException
     */
    public static void inVerify(String encContent) throws EncryptionException {
        try{
            InvalidCharException.checkASCII(encContent.toCharArray(), "Input Ciphertext");
        } catch(Exception e) {
            throw new EncryptionException("Corrupted Input CipherText!");
        }
    }
    
    /**
     * Verifies the outgoing Ciphertext falls in the ASCII range 42-122 and doesn't contain _'s
     * @param deContent A String of Plaintext
     * @throws EncryptionException
     */
    public static void outVerify(String deContent) throws EncryptionException {
        try{
            InvalidCharException.checkASCII(deContent.toCharArray(), "Output Ciphertext");
        } catch(Exception e) {
            throw new EncryptionException("Corrupted Output CipherText!");
        }
        if(!(deContent.contains("_")))
            throw new EncryptionException("Corrupted Output CipherText!");
    }
}