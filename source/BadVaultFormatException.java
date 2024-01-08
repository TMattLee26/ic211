/**
 * BadVaultFormatException is a subclass of the Exception class
 * with the task of handling errors relating to
 * improper formatting of the supplied vault file.
 *
 * @author MIDN 3/C Tristan M. Lee
 */
public class BadVaultFormatException extends Exception
{
     /**
     * Creates a BadVaultFormatException with a message
     * @param msg The msg to be thrown with the exception
     */
    public BadVaultFormatException(String msg) 
    {
        super(msg);
    }

    /**
     * Checks a line in a Vault file to ensure it is properly formatted
     * @param lineSplit A String array containing elements of a line in the Vault file
     * @param context The File that is improperly formatted
     * @throws BadVaultFormatException
     */
    public static void checkLine(String[] lineSplit, String context) throws BadVaultFormatException{
        // Verifies the lines have 4 elements and start with either user or data
        if((lineSplit.length != 4) || !(lineSplit[0].equals("user") || lineSplit[0].equals("data")))
            throw new BadVaultFormatException("Error! File '" + context + "' improperly formatted.");
    }
}