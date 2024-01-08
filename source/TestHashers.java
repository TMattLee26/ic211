/**
 * A class used to test different Hash types and their implementations.
 * Currently tests the ClearHash, CaesarHash and VigenereHash.
 * 
 * @author MIDN 3/C Tristan M. Lee
 */
import java.util.*;

public class TestHashers{
    public static void main(String[] args) throws Throwable {
        // Create ArrayList of all supported hashers
        ArrayList<Hasher> H = new ArrayList<Hasher>();

        // <Add New Hashes Here as Additional Hashes are configured>
        H.add(new ClearHash());
        H.add(new CaesarHash());
        H.add(new VigenereHash());

        // Get alg,psw from user
        System.out.print("algorithm: ");
        String encalg = System.console().readLine();
        System.out.print("password : ");
        char[] password = System.console().readPassword();

        // Find index of encryptor (throw exception if not found)
        int i = -1;
        try {
            while( !H.get(++i).getAlgName().equals(encalg) ) ;
        } catch(IndexOutOfBoundsException e) {
            throw new NoSuchElementException("Unknown algorithm '" + encalg +"'.");
        }

        // Check the ASCII of the Hash key
        try {
            InvalidCharException.checkASCII(password, "key");
        } catch(InvalidCharException e){
            throw e;
        }

        // Hash Computation
        System.out.println("password read : " + new String(password));
        System.out.println("hash computed : " + H.get(i).computeHash(password));
    }
}
