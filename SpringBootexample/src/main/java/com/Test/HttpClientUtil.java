package com.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;



public class HttpClientUtil {

	
	private static final String algorithm = "AES";
	private static final String cipherPadding = "AES/CBC/PKCS5PADDING";
	private static final int keySize = 128;

	private static final String keyAsHexString = "2CB719C5DF693CA2C626EB1A02E9F2BD";

	
	//private static String plainText = "{\"billCategory\":\""+billCategoryValue+"\",\"channelId\":\"643\",\"checksum\":\""+checksumValue+"\",\"encToken\":\""+encryptValue+"\"}";
	//private static String requestPre = "{\"BBPGetBillerCategoryRequest\":{\"SubHeader\":{\"requestUUID\":\"1\",\"serviceRequestId\":\"2\",\"serviceRequestVersion\":\"3\",\"channelId\":\"IFMR\"},\"BBPGetBillerCategoryRequestBodyEncrypted\":\"";
	//private static String requestPre = "{\"BBPGetBillerCategoryRequest\":{\"SubHeader\":{\"requestUUID\":\""+reqUUID+"\",\"serviceRequestId\":\""+serviceReqId+"\",\"serviceRequestVersion\":\""+serviceReqVersion+"\",\"channelId\":\"IFMR\"},\"BBPGetBillerCategoryRequestBodyEncrypted\":\"";
	//private static String requestPost = "\"}}";
	//private static String requestToSend = "";


	static String encryptedResult = "";

	// encryptedResult
	public static String encryptedResult(String plainText) throws Exception {
		byte[] iv = getRandomIV();
		String ivTxt = getByteToString(iv);
		encryptedResult = CommonEncryption(plainText, keyAsHexString, iv);
		return encryptedResult;

	}

	// finalRequest
	/*public static String finalRequest() {
		requestToSend = requestPre + encryptedResult + requestPost;
		return requestToSend;
	}*/

	// decryptedResult
	public static String decryptedResult(String encryptedResult) throws Exception {
		String decryptedResult = CommonDecryption(encryptedResult, keyAsHexString);
		return decryptedResult;
	}

	public static String encrypt(String key, String enc) {
		// TODO Autt-geiercted methtd stub
		try {
			SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
			byte[] iv1 = new byte[] { (byte) 0x8E, 0x12, 0x39, (byte) 0x9C, 0x07, 0x72, 0x6F, 0x5A, (byte) 0x8E, 0x12,
					0x39, (byte) 0x9C, 0x07, 0x72, 0x6F, 0x5A };
			AlgorithmParameterSpec paramSpec = new IvParameterSpec(iv1);
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(1, skeySpec, paramSpec);
			byte[] encrypted = cipher.doFinal(enc.getBytes());
			return new String(Base64.getEncoder().encode(encrypted));
		} catch (Exception ex) {
		}
		return null;
	}

	public static String sha256(String sha) {
		// TODO Autt-geiercted methtd stub
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(sha.getBytes("UTF-8"));
			StringBuffer hexString = new StringBuffer();
			for (int i = 0; i < hash.length; i++) {
				String hex = Integer.toHexString(0xFF & hash[i]);
				if (hex.length() == 1)
					hexString.append('0');
				hexString.append(hex);
			}
			return hexString.toString();
		} catch (Exception ex) {
		}
		throw new RuntimeException("");
	}

	public static String decrypt(String key, String encrypted) {
		try {
			SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
			byte[] iv1 = new byte[] { (byte) 0x8E, 0x12, 0x39, (byte) 0x9C, 0x07, 0x72, 0x6F, 0x5A, (byte) 0x8E, 0x12,
					0x39, (byte) 0x9C, 0x07, 0x72, 0x6F, 0x5A };
			AlgorithmParameterSpec paramSpec = new IvParameterSpec(iv1);
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(2, skeySpec, paramSpec);
			byte[] orignal = cipher.doFinal(Base64.getDecoder().decode(encrypted.getBytes()));
			return new String(orignal);
		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return null;
	}

	public static String CommonDecryption(String ciphertext, String keyAsHexString) throws Exception {
		SecretKeySpec skeySpec = getSecretKeySpecFromHexString(algorithm, keyAsHexString);
		byte[] encryptedIVandTextAsBytes = Base64.getDecoder().decode(ciphertext);
		/** First 16 bytes are always the IV */
		byte[] iv = Arrays.copyOf(encryptedIVandTextAsBytes, 16);

		byte[] ciphertextByte = Arrays.copyOfRange(encryptedIVandTextAsBytes, 16, encryptedIVandTextAsBytes.length);
		// Decrypt the message
		Cipher cipher = Cipher.getInstance(cipherPadding);
		cipher.init(Cipher.DECRYPT_MODE, skeySpec, new IvParameterSpec(iv));
		byte[] decryptedTextBytes = cipher.doFinal(ciphertextByte);
		String decryptedResult = getByteToString(decryptedTextBytes);
		return decryptedResult;
	}

	private static SecretKeySpec getSecretKeySpecFromHexString(String algoCommonName, String hexString)
			throws Exception {
		byte[] encodedBytes = hexStrToByteArray(hexString);
		return new SecretKeySpec(encodedBytes, algoCommonName);
	}

	private static byte[] hexStrToByteArray(String hex) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream(hex.length() / 2);

		for (int i = 0; i < hex.length(); i += 2) {
			String output = hex.substring(i, i + 2);
			int decimal = Integer.parseInt(output, 16);
			baos.write(decimal);
		}
		return baos.toByteArray();
	}

	public static String CommonEncryption(String plainText, String keyAsHexString, byte[] iv) throws Exception,
			NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException,
			IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
		AlgorithmParameterSpec paramSpec = new IvParameterSpec(iv);
		/** Generate a secret key from the hex string as key */
		SecretKeySpec skeySpec = getSecretKeySpecFromHexString(algorithm, keyAsHexString);
		/** Creating a cipher instance with the algorithm and padding */
		Cipher cipher = Cipher.getInstance(cipherPadding);
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec, paramSpec);
		/** generating the encrypted result */
		byte[] encrypted = cipher.doFinal(plainText.getBytes("UTF-8"));
		// To add iv in encrypted string.
		byte[] encryptedWithIV = copyIVAndCipher(encrypted, iv);
		String encryptedResult = new String(Base64.getEncoder().encode(encryptedWithIV));
		return encryptedResult;
	}

	public static byte[] getRandomIV() throws Exception {

		/** Generating a random IV param spec for encrypting */
		byte[] iv = new byte[] { (byte) 0x8E, 0x12, 0x39, (byte) 0x9C, 0x07, 0x72, 0x6F, 0x5A, (byte) 0x8E, 0x12, 0x39,
				(byte) 0x9C, 0x07, 0x72, 0x6F, 0x5A };
		return iv;

	}

	public static byte[] encryptWithSpecificIV(String ivAsHex) throws Exception {
		/** Generating a random IV param spec for encrypting */
		byte[] iv = ivAsHex.getBytes();
		return iv;
	}

	public static String getByteToString(byte[] byteData) throws Exception {
		String byteToString = new String(byteData, "UTF-8");
		return byteToString;
	}

	public static byte[] getStringToByte(String stringData) throws Exception {
		byte[] stringToByte = stringData.getBytes("UTF-8");
		return stringToByte;
	}

	public static byte[] copyIVAndCipher(byte[] encryptedText, byte[] iv) throws Exception {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		os.write(iv);
		os.write(encryptedText);
		return os.toByteArray();
	}

}
