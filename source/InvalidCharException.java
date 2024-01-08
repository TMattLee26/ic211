/**
 * InvalidCharException is a subclass of the Exception class
 * with the task of handling errors relating to invalid
 * ASCII Characters outside of the range 42-122.
 *
 * @author MIDN 3/C Tristan M. Lee
 */
public class InvalidCharException extends Exception
{

    /**
     * Creates an InvalidCharException with a message
     * @param msg The msg to be thrown with the exception
     */
    public InvalidCharException(String msg) 
    {
        super(msg);
    }

    /**
     * A static method called to check ASCII characters in a char[] and reply with a context msg.
     * 
     * @param A Char[] of text to check
     * @param context A String representing the context used in the throw message
     * @throws InvalidCharException
     */
    public static void checkASCII(char [] A, String context) throws InvalidCharException{
        for(char c : A)
            if(c < 42 || c > 122)
                throw new InvalidCharException("Error! Invalid symbol '" + c + "' in " + context + ".");
        return;
    }

    /**
     * A static method called to check ASCII characters in a char[] and reply with true/false. Used in User.java.
     * 
     * @param A Char[] of text to check
     * @return False if there is an invalid character, true if all characters fall in the ASCII range 42-122.
     */
    public static boolean checkASCII(char [] A){
        for(char c : A)
            if(c < 42 || c > 122)
                return false;
        return true;
    }

    /**
     * A more specific implementation of the checkASCII method for Labels due to the unique error 
     * message thrown by an improper label in the Vaults data.
     * 
     * @param A Char[] of text to check
     * @throws InvalidCharException
     */
    public static void checkASCIILabel(char [] A) throws InvalidCharException{
        for(char c : A)
            if(c < 42 || c > 122 || c == '_')
                throw new InvalidCharException("Error! Label '" + new String(A) + "' invalid.");
        return;
    }
}