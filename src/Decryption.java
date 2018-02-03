package rsakeys;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class Decryption {

	public static void main(String[] args) throws Exception {
		
		// Get private key
		Key privateKey = Decryption.getPrivateKey("C:/users/Chas/Encryption/privateKey");
		
		// Select message to decrypt
		String eMessage = "C:/users/Chas/Encryption/eMessage";
		String eHash = "C:/users/Chas/Encryption/eHash";
		
		// Decrypt message & hash
		Decryption.decrypt(eMessage, privateKey);
		Decryption.decrypt(eHash, privateKey);
		
		
	}
	
	
	// Method to get my saved private Key and be able to use it
	public static PrivateKey getPrivateKey(String filename) throws Exception {
		
		byte[] keybytes = Files.readAllBytes(Paths.get(filename));
		PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keybytes);
		KeyFactory kf = KeyFactory.getInstance("RSA");
		
		return kf.generatePrivate(spec);
		
	}
	

	public static void decrypt(String encryptedMessage, Key privateKey) throws IllegalBlockSizeException, BadPaddingException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IOException {
		
		// Open file and convert to bytes
		FileInputStream file = new FileInputStream(encryptedMessage);
		byte[] bytesToRead = new byte[file.available()];
		file.read(bytesToRead);
		file.close();
		
		// Decrypte bytes
		Cipher c = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		c.init(Cipher.DECRYPT_MODE, privateKey);
		byte[] decrypted = c.doFinal(bytesToRead);
		
		// Convert bytes to String
		String message = new String(decrypted);
		
		// Print out decrypted message & hash value
		System.out.println(message);		

		
	}

}
