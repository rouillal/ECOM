package fr.ecombio.model;
import java.security.Key;
import java.util.Base64;
import java.util.Properties;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;


public class MyCryptoConverter {

	private static String ALGORITHM = null;
	private static byte[] KEY = null;

	public static final String algorithm_property_key = "encryption.algorithm";
	public static final String secret_property_key = "encryption.key";

	static final Properties properties = new Properties();
	static {
		try {
			properties.load(MyCryptoConverter.class.getClassLoader()
					.getResourceAsStream("persistence.properties"));
		} catch (Exception e) {
			properties.put(algorithm_property_key, "AES/ECB/PKCS5Padding");
			properties.put(secret_property_key, "MySuperSecretKey");
		}
		ALGORITHM = (String) properties.get(algorithm_property_key);
		KEY = ((String) properties.get(secret_property_key)).getBytes();
	}

	public static String encrypt(String sensitive) {
		Key key = new SecretKeySpec(KEY, "AES");
		try {
			final Cipher c = Cipher.getInstance(ALGORITHM);
			c.init(Cipher.ENCRYPT_MODE, key);
			final String encrypted = new String(Base64.getEncoder().encode(c
					.doFinal(sensitive.getBytes())), "UTF-8");
			return encrypted;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static String decrypt(String sensitive) {
		Key key = new SecretKeySpec(KEY, "AES");
		try {
			final Cipher c = Cipher.getInstance(ALGORITHM);
			c.init(Cipher.DECRYPT_MODE, key);
			final String decrypted = new String(c.doFinal(Base64.getDecoder()
					.decode(sensitive.getBytes("UTF-8"))));
			return decrypted;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
