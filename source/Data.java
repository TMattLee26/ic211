/**
 * A class used to define the Data object that stores
 * data entries read in from a Vault file.
 * 
 * @author MIDN 3/C Tristan M. Lee
 */
public class Data {
    String uName;
    String encalg;
    String encContent;
    String deContent;
    String label;
    String content;

    /**
     * A constructor for the Data object implemented in such a way
     * to represent the raw form of the data from the Vault file.
     * 
     * @param uName A String representing the username associated with the data
     * @param encalg A String with the encryption name
     * @param encContent A String of the ciphertext encrypted by 'encalg.'
     * Contains the label and content.
     */
    public Data(String uName, String encalg, String encContent)
    {
        this.uName = uName;
        this.encalg = encalg;
        this.encContent = encContent;
    }

    /**
     * Returns the label found in the decrypted data
     * 
     * @return A String that is the label
     */
    public String getLabel() { return label; }
    /**
     * Returns the content found in the decrypted data
     * 
     * @return A String that is the decrypted data
     */
    public String getContent() { return content; } 
}