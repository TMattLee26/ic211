import java.util.*;

/**
 * A class used to define the User object that stores
 * user entries read in from a Vault file.
 * 
 * @author MIDN 3/C Tristan M. Lee
 */
public class User{
    String uName;
    String hashalg;
    String hash;

    /**
     * A constructor for the User object implemented in such a way
     * to represent the raw form of the data from the Vault file.
     * 
     * @param uName A String representing the username
     * @param hashalg A String with the hash algorithm
     * @param hash A String of a message digest computed by 'hashalg.' Contains the user's password.
     */
    public User(String uName, String hashalg, String hash)
    {
        this.uName = uName;
        this.hashalg = hashalg;
        this.hash = hash;
    }

    /**
     * Returns username of the User
     * 
     * @return String uName
     */
    public String getUser() { return uName; }
    /**
     * Returns the Hash Algorithm of the User
     * 
     * @return String hashalg
     */
    public String getHashAlg() { return hashalg; }
    /**
     * Returns the hash of the User's password
     * @return String hash
     */
    public String getHash() { return hash; }

    /**
     * A Static Class Method defined to look through a HashMap<String, User> and determine whether a specific
     * is in the HashMap database and verify a supplied password with that user's password in the HashMap.
     * 
     * Note: The HashMap<String,User> represents a mapping of data entries read in from a Vault file.
     * 
     * @param user A String of the user to query for
     * @param passwd A char[] representing the password for the user
     * @param accounts A HashMap<String, User> containing User objects to query.
     * @return If user successfully passes all tests and logs in their username is returned to be stored 
     * in User session in the Vault.java program.
     */
    public static String userLoginVerification(String user, char[] passwd, HashMap<String, User> accounts)
    {
        try
        {
            String hash = null;

            // Checks if the HashMap contains the requested user account and that the supplied password
            // does not contain any invalid characters.
            if(accounts.containsKey(user) && InvalidCharException.checkASCII(passwd))
            {
                User u = accounts.get(user);
                String hashAlg = u.getHashAlg();
                
                // Check if the user's hash algorithm is supported
                HashException.hashSupport(hashAlg);

                // Compute a hash using the user's hashing algorithm and the supplied password
                hash = HashInventory.H.get(hashAlg).computeHash(passwd);

                if(hash.equals(u.getHash()))
                    System.out.printf("Access granted!\n");
                else
                {
                    System.out.printf("Access denied!\n");
                    System.exit(0);
                }
            }
            else
            {
                System.out.printf("Access denied!\n");
                System.exit(0);
            }
        } catch (HashException e) {
            System.out.printf(e.getMessage() + "\n");
            System.exit(0);
        }

        return user;
    }

    /**
     * A Static class method defined to perform various checks required before adding a new user to a vault file.
     * These checks include checking the ASCII characters in the password, ensuring the desired hash algorithm is supported,
     * and that the user account is not already in use.
     * 
     * Note: The Implementation of adding the user to the vault file is performed in Vault.java since file operations are needed
     * to be used, but the checks are done here to follow my intended design of checks on users being handled in User.java.
     * 
     * @param user A String of the user to add
     * @param passwd A char[] of a passwd supplied to be added with the userAccount
     * @param encalg The User's hash algorithm
     * @param accounts A HashMap<String, User> representing all users in the Vault database.
     */
    public static void addUserVerification(String user, char[] passwd, String hashalg, HashMap<String, User> accounts)
    {
        try
        {
            InvalidCharException.checkASCII(passwd, "password");
            HashException.hashSupport(hashalg);

            if(accounts.containsKey(user))
            {
                System.out.printf("Error! Username '%s' already in use.\n", user);
                System.exit(0);
            }

        } catch(InvalidCharException e){
            System.out.printf(e.getMessage() + "\n");
            System.exit(0);
        } catch (HashException e) {
            System.out.printf(e.getMessage() + "\n");
            System.exit(0);
        }
    }
}