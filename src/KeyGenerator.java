package rsakeys;

import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

public class KeyGenerator {
	
	public static void main(String[] args) throws NoSuchAlgorithmException, IOException{ 
		
		// initialize key generator
		KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
		keyGen.initialize(1024);
		
		// generate a key pair
		KeyPair pair = keyGen.generateKeyPair();
		PublicKey publ = pair.getPublic();
		PrivateKey priv = pair.getPrivate();
		
		//Save to file
		byte[] keyPriv = priv.getEncoded();
		FileOutputStream keyPrivate = new FileOutputStream("C:/users/Chas/Encryption/privateKey");
		keyPrivate.write(keyPriv);
		keyPrivate.close();
		
		byte[] keyPubl = publ.getEncoded();
		FileOutputStream keyPublic = new FileOutputStream("C:/users/Chas/Encryption/publicKey");
		keyPublic.write(keyPubl);
		keyPublic.close();	
		
		System.out.println("Success! Public & Private Keys Generated.");
	}

}
