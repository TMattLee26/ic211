import java.util.*;
import java.io.*; 

/**
 * The primary program of this project containing a Main function and private helper methods 
 * used to read in a Vault file, and authenticate a user from the Vault file using console input.
 * 
 * Features: Ability to query Vault data, and add new data to a vault file. Ability to add new Users
 * outside of a userSession using the -au options.
 * 
 * Usage: java Vault [-au] <filename>
 * 
 * @author MIDN 3/C Tristan M. Lee
 */
public class Vault {

    // Global Private Static Variables used to store values called within private helper methods
    // to support functionality. Used often in place of requiring Method parameters.
    private static HashMap<String, User> accounts = new HashMap<>();
    private static ArrayList<Data> allData = new ArrayList<>();
    private static String fname = null;
    private static String userSession = null;
    private static char[] userKey = null;
    private static ArrayList<Data> userData = new ArrayList<>();
    public static void main(String [] args)
    {

        BufferedReader br = null;
        FileReader fr = null;

        // Checks if the program was properly called with a Vault file and whether the user
        // wants to access the Vault database or add a user.
        try
        {
            if(args[0].equals("-au"))
            {
                fname = args[1];
                initDatabase(br, fr);
                addUser();
            }
            else
            {
                fname = args[0];
                initDatabase(br, fr);
                userAuthentication();
            }
            
        } catch(IndexOutOfBoundsException e) {
            System.out.printf("usage: java Vault [-au] <filename>\n");
            System.exit(0);
        }
    }

    /**
     * A private static helper method used to initalize the accounts HashMap and the data ArrayList
     * which represent the contents of the Vault file that will be used to support userAuthentication and
     * used in checks before adding a new user.
     * 
     * @param br A Buffered Reader
     * @param fr A FileReader
     */
    private static void initDatabase(BufferedReader br, FileReader fr)
    {
        try 
        {
            fr = new FileReader(fname);
            br = new BufferedReader(fr);

            String line = null;

            // Reads through each line of the Vault file
            while((line = br.readLine()) != null)
            {
                String[] parts = line.split(" ");

                // Checks if this line in the Vault file follows the appropriate format.
                BadVaultFormatException.checkLine(parts, fname);

                // Checks if the line is a User line or a Data line and adds the entry to its 
                // respective storage container.
                if(parts[0].equals("user"))
                    accounts.put(parts[1].trim(), new User(parts[1].trim(), 
                        parts[2].trim(), parts[3].trim()));
                if(parts[0].equals("data"))
                    allData.add(new Data(parts[1].trim(), parts[2].trim(), parts[3].trim()));
            }
        } catch(BadVaultFormatException e) {
            System.out.printf(e.getMessage() + "\n");
            System.exit(0);
        } catch(FileNotFoundException e) {
            System.out.printf("Error! File '%s' could not be opened.\n", fname);
            System.exit(0);
        } catch(IOException e) {
            System.exit(0);
        } finally {
            // Close the BufferedReader and FileReader
            if (br != null) { 
                try { 
                    br.close();
                    fr.close();
                } 
                catch (Exception e) { 
                    System.exit(0);
                }; 
            } 
        }
        return;
    }

    /**
     * A private helper method used to read console input for logging in a User.
     * Sets the global userSession and userKey variables and populates a userShell().
     * 
     */
    private static void userAuthentication()
    {
        System.out.printf("username: ");
        String user = System.console().readLine();

        System.out.printf("password: ");
        char[] passwd = System.console().readPassword();

        userSession = User.userLoginVerification(user, passwd, accounts);
        userKey = passwd;

        userShell();
    }

    /**
     * A private static helper method used to control a userShell which handles console input from a user
     * after they have logged in and authenticated with a user account contained in the Vault file.
     */
    private static void userShell()
    {
        Scanner in = new Scanner(System.in);
        String cmd = null;

        // Once a user has authenticated, we want to load their data so it can be queried in their session
        loadUserData();

        do 
        {
            System.out.printf("> ");
            cmd = in.next();
            
            // View the labels of data for the current userSession
            if(cmd.equals("labels"))
                listAllLabels();
            // View the content of a label of data for the current userSession
            else if(cmd.equals("get"))
            {
                cmd = in.next();
                listSingleLabel(cmd);
            }
            // Used to add new data to a Vault file associated with this user.
            else if(cmd.equals("add"))
            {
                // Stores the Encryption Algorithm, Data Label and Data Content
                String[] options = new String[3];
                
                for(int i = 0; i < 3; i++)
                    options[i] = in.next();
                // Perform checks on data to be entered and add the data if all checks are passed.
                try {
                    EncryptionException.encryptSupport(options[0]);
                    InvalidCharException.checkASCIILabel(options[1].toCharArray());
                    InvalidCharException.checkASCII(options[2].toCharArray(), "text");
                    
                    addData(options[0], options[1], options[2]);

                } catch(Exception e){
                    System.out.printf(e.getMessage() + "\n");
                }
            }
            else if(cmd.equals("quit"))
                continue;
            else
                System.out.printf("Unknown command '%s'.\n", cmd);
        } while(!(cmd.equals("quit")));

        // Close the Scanner Object
        in.close();

        System.exit(0);
    }

    /**
     * A private static helper method used to loadUserData which takes the allData Arraylist read in from the vault
     * file, determines if the data is associated with the user, and decrypts the data using the user's passkey before
     * adding this new data to a new ArrayList representing this user's data entries.
     */
    private static void loadUserData()
    {
        if(userSession != null)
        {
            for(Data d : allData)
            {
                if(d.uName.equals(userSession))
                {
                    try{
                        
                        // Check the Encryption algorithm of the data
                        EncryptionException.encryptSupport(d.encalg);

                        // Initalize the key used in the respective Encryption algorithm
                        // using the current user's passkey.
                        EncryptionInventory.E.get(d.encalg).init(userKey);

                        // Verify the encrypted content follows the proper format
                        EncryptionException.inVerify(d.encContent);

                        // Decrypt the encrypted content.
                        d.deContent = EncryptionInventory.E.get(d.encalg).decrypt(d.encContent);

                        // Verify the decrypted content follows the proper format
                        EncryptionException.outVerify(d.deContent);
                        
                        // Separate the label from the actual content of the data.
                        int index = d.deContent.indexOf('_');
                        d.label = d.deContent.substring(0, index);
                        d.content = d.deContent.substring(index+1);

                        // Add the data to a new ArrayList representing the current User's decrypted data.
                        userData.add(d);  
                    } catch(Exception e) {
                        System.out.printf("Corrupted entry '%s' in vault file.\n", d.encContent);
                    }
                }
            }
        }
    }

    /**
     * A private static helper method used to list all the labels of the User's data
     */
    private static void listAllLabels()
    {
        for(Data d : userData)
            System.out.printf(d.label + "\n");
    }

    // A private static helper method used to query a label for the User's data
    private static void listSingleLabel(String tag)
    {
        for(Data d : userData)
            if(d.label.equals(tag))
                System.out.printf(d.content + "\n");
    }

    /**
     * A private static helper method to append a user to a Vault file
     */
    private static void addUser()
    {
        System.out.printf("username: ");
        String user = System.console().readLine();

        System.out.printf("password: ");
        char[] passwd = System.console().readPassword();

        System.out.printf("Hash algorithm: ");
        String encalg = System.console().readLine();

        User.addUserVerification(user, passwd, encalg, accounts);
        
        // Open a PrintWriter object with the current Vault file to be appeneded to
        PrintWriter pw = null;
        
        try 
        {
            pw = new PrintWriter(new BufferedWriter(new FileWriter(fname, true)));
        } catch (Exception e) {
            System.out.printf("Error! File '%s' could not be opened.\n", fname);
            System.exit(0);
        }
        
        // Append the new user account to Vault file if all conditions are passed.
        // Follows the format user <username> <hash algorithm> <hash>
        String hash = null;
        hash = HashInventory.H.get(encalg).computeHash(passwd);

        pw.printf("\nuser %s %s %s", user, encalg, hash);

        if (pw != null)
            pw.close();
    }

    /**
     * A private static helper method used to add data to a Vault file.
     * 
     * IMPORTANT: Only one data entry can be added to a Vault file per userSession. The database would
     * need to be reloaded to add more than one data entry per session but such functionality was not required
     * and only storing the most recent data entry in a userSession is a feature.
     * 
     * @param encalg A String representing the encryption algorithm of the data to add
     * @param tag A String representing the label for the data
     * @param text A String representing the content to be associated with the provided label.
     */
    private static void addData(String encalg, String tag, String text)
    {
        // Overwrite the Existing Vault File to prevent duplicate data entries for a particular user.
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new File(fname));
        } catch (Exception e) {
            System.out.printf("Error! File '%s' could not be opened.\n", fname);
            System.exit(0);
        }

        // Add user entries to the Vault File
        for(User u : accounts.values())
            pw.printf("user %s %s %s\n", u.uName, u.hashalg, u.hash);

        if(allData != null)
        {
            for(Data x : allData)
            {
                // Add all data for users that are not our current User's session
                if(!(x.uName.equals(userSession)))
                    pw.printf("data %s %s %s\n", x.uName, x.encalg, x.encContent);
                else
                {
                    boolean exists = false;
                    // Check the User's data in the Vault to ensure none have the same label. Requires
                    // the data to be decrypted hence why we are only handling our userSession's data.
                    for(Data myData : userData)
                    {
                        if(myData.label.equals(tag) && x.encContent.equals(myData.encContent))
                            exists = true;
                    }
                    // If the data does not currently exist in the vault. Add the data.
                    if(!exists)
                    {
                        pw.printf("data %s %s %s\n", x.uName, x.encalg, x.encContent);
                    }
                }
            }
        }

        // Encrypt our data to add and add it to the Vault if the encryption algorithm is supported.
        try{
            EncryptionException.encryptSupport(encalg);
            EncryptionInventory.E.get(encalg).init(userKey);
        }catch (EncryptionException e){
            System.out.printf(e.getMessage() + "\n");
            System.exit(0);
        }

        String textToEncrypt = tag + "_" + text;

        String encrypted = EncryptionInventory.E.get(encalg).encrypt(textToEncrypt);
        
        // Add the data entry to the file.
        pw.printf("data %s %s %s\n", userSession, encalg, encrypted);

        if (pw != null)
            pw.close();
    }
}