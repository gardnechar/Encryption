package rsakeys;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class Encryption {
	
	public static void main(String[] args) throws Exception {
			
			// Get public key
			PublicKey KeyPublic = Encryption.getPublicKey("C:/users/Chas/Encryption/publicKey");
			
			// Document location
			String document = "C:/users/Chas/Encryption/document.txt";
			String saveLocation = "C:/users/Chas/Encryption/eMessage";
	
			// Encrypt message & save to file
			Encryption.encrypt(document, saveLocation, KeyPublic);			
			
	}
	
	
	// Method to get my saved public Key and be able to use it
	public static PublicKey getPublicKey(String file) throws Exception {
		
		byte[] keybytes = Files.readAllBytes(Paths.get(file));
		X509EncodedKeySpec spec = new X509EncodedKeySpec(keybytes);
		KeyFactory kf = KeyFactory.getInstance("RSA");
		
		return kf.generatePublic(spec);
		
	}
	
	// Method to encrypt my document
	public static void encrypt(String document, String saveLocation, PublicKey keyPublic) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, IOException {
				
		Cipher c = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		c.init(Cipher.ENCRYPT_MODE, keyPublic);
		
		// Open file and convert to bytes
		FileInputStream inputStream = new FileInputStream(document);
        byte[] inputBytes = new byte[(int) document.length()];
        inputStream.read(inputBytes);
        inputStream.close();
        
		// Encrypt bytes
        byte[] encrypted = c.doFinal(inputBytes);
		
		
		// Save encryption to file
		FileOutputStream file = new FileOutputStream(saveLocation);
		file.write(encrypted);
		file.close();
		
		System.out.println("Success! Document was Encrypted.");
		
	}
	


}
