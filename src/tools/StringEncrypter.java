/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 *
 * @author Alvaro Monsalve
 */
public class StringEncrypter {
    
    Cipher ecipher;
    Cipher dcipher;
    
    public StringEncrypter() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException{

//         SecretKey DESKey  = KeyGenerator.getInstance("DES").generateKey();
//         //generador de DES.key
//         saveKey(DESKey);

        SecretKey DESKey=readKey();
//        String algorithm=DESKey.getAlgorithm();
        ecipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        dcipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        ecipher.init(  Cipher.ENCRYPT_MODE, DESKey);
        dcipher.init(Cipher.DECRYPT_MODE, DESKey);
//        System.out.println("Encriptado: 7");
    }
    
    public String encrypt(String str) throws UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException{
        byte[] utf8 = str.getBytes("UTF8");
        byte[] enc = ecipher.doFinal(utf8);
        return new sun.misc.BASE64Encoder().encode(enc);
    }
    
    public String decrypt(String str) throws IOException, IllegalBlockSizeException, BadPaddingException{
        byte[] dec = new sun.misc.BASE64Decoder().decodeBuffer(str);
       
        byte[] utf8 = dcipher.doFinal(dec);
         
        return new String(utf8,"UTF8");
    }
    
//    private void saveKey(SecretKey key){
//        FileOutputStream fos = null;
//        try {
//            String fl=(System.getProperty("user.dir")+"\\DES.key");
//            fos = new FileOutputStream(fl);
//            byte[] kb = key.getEncoded();
//            fos.write(kb);
//        } catch (FileNotFoundException ex) {
//            System.out.println("Encriptado: 1");
//        } catch (IOException ex) {
//            System.out.println("Encriptado: 2");
//        } finally {
//            try {
//                fos.close();
//            } catch (IOException ex) {
//                
//            }
//        }
//    }
    
    private SecretKey readKey(){
        FileInputStream fis = null;
        String fl=(System.getProperty("user.dir")+"\\DES.key");
        KeySpec ks = null;
        SecretKey ky = null;
        SecretKeyFactory kf = null;
        try {
            fis = new FileInputStream(fl);
            int kl = fis.available();
            byte[] kb = new byte[kl];
            fis.read(kb);
            ks = new DESKeySpec(kb);

            kf = SecretKeyFactory.getInstance("DES");

            ky = kf.generateSecret(ks);
        } catch (InvalidKeySpecException ex) {

            //ky = kf.generateSecret(ks);
        } catch (NoSuchAlgorithmException ex) {

            //kf = SecretKeyFactory.getInstance("DES");
        } catch (InvalidKeyException ex) {

            //ks = new DESKeySpec(kb);
        } catch (FileNotFoundException ex) {

            //fis.read(kb);
        } catch (IOException ex) {

            
        //fis = new FileInputStream(fl);
            
        } finally {
            try {
                fis.close();
            } catch (IOException ex) {
                
            }
            return ky;
        }
    }
}
