/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Alvaro Monsalve
 */
public class stringMD5 {
    
    public static String MD5="MD5";
    
       private static String toHexadecimal(byte[] digest){
        String hash = "";
        for(byte aux : digest) {
            int b = aux & 0xff;
            if (Integer.toHexString(b).length() == 1) {
                hash += "0";
            }
            hash += Integer.toHexString(b);
        }
        return hash;
    }
       
       public static String getStringMessageDigest(String message, String algorithm) throws NoSuchAlgorithmException{
        byte[] digest = null;
        byte[] buffer = message.getBytes();
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            messageDigest.reset();
            messageDigest.update(buffer);
            digest = messageDigest.digest();
        return toHexadecimal(digest);
    }

    
}
