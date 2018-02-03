
package rsakeys;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;

public class HashGenerator {
	
	public static void main(String[] args) throws Exception {
		
		// Get public key
		PublicKey KeyPublic = Encryption.getPublicKey("C:/users/Chas/Encryption/publicKey");
		
		// Document location 
		String document = "C:/users/Chas/Encryption/document.txt";
		String hashedDocument = "C:/users/Chas/Encryption/hash.txt";
		String saveLocation = "C:/users/Chas/Encryption/eHash";
		
		// Hash a copy of the document & save to file
		HashGenerator.hasher(document);

		// Encrypt hashed message & save to file
		Encryption.encrypt(hashedDocument, saveLocation, KeyPublic);			
		
}
	
	public static void hasher(String filePath) throws NoSuchAlgorithmException, IOException {

		
		// Read file and convert to byte[]
		MessageDigest md = MessageDigest.getInstance("MD5");
        FileInputStream fis = new FileInputStream(filePath);

        byte[] dataBytes = new byte[1024];

        int nread = 0;
        while ((nread = fis.read(dataBytes)) != -1) {
          md.update(dataBytes, 0, nread);
        };
        fis.close();
        byte[] mdbytes = md.digest();

        //convert the byte to hex format method 1
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < mdbytes.length; i++) {
          sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        

		// Save hashed document to file
		FileWriter file = new FileWriter("C:/users/Chas/Encryption/hash.txt");
		file.write(sb.toString());
		file.close();
		
		System.out.println("Success! Document was Hashed.");
		

	}
    
}



