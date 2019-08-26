package com.Test;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import com.sun.org.apache.xml.internal.security.utils.Base64;

public class Test2 {
	//private static final String algorithm = "AES";
	//private static final String cipherPadding = "AES/CBC/PKCS5PADDING";
	//private static final int keySize = 128;

	private static final String keyAsHexString = "2CB719C5DF693CA2C626EB1A02E9F2BD";

	private static String plainText = "{\"billCategory\":\"17\",\"channelId\":\"643\",\"checksum\":\"887411b79b862c72159fb9897e481f0abdcd16afdf56a42d9475d945ff46469c\",\"encToken\":\"M0HeXjNos55n8AdbXnyl/Q==\"}";
	private static String requestPre = "{\"BBPGetBillerCategoryRequest\":{\"SubHeader\":{\"requestUUID\":\"1\",\"serviceRequestId\":\"2\",\"serviceRequestVersion\":\"3\",\"channelId\":\"IFMR\"},\"BBPGetBillerCategoryRequestBodyEncrypted\":\"";
	private static String requestPost = "\"}}";
	private static String requestToSend = "";

	// Encrypted string generated from C# to check whether cross platform things
	// working or not
	private static final String encryptedText = "I0iLQOjwtFwNYZ3kTykeQ9p1J8/0DZFuq9AAAsie93exZGdAJABaMZDFG97lnXo/tA5vMf6Dwxay"
			+ "MI4GB7rP0K//WWAr+uUA1kizhIk+SOEM70GPpO8SkV/n0MW2yUp1sqUDUQMe7luenYCLBvf3vxoK"
			+ "h4cv3g9ydAaKxtZA1xy1pWAzSIgeFvc32k8NkTR8b9kwrWxVJWm5qbiAgaYwsLBvs7bbYj3Lc8WY"
			+ "oQYCPjWrOtk5xff7QQT2wpm/tJU/ErtLVNDNJg3fVkN7l/tb6ucHEvZNkwEPOwc/RzZutZmVJUs8"
			+ "la2YtUhneyrxvOg28T6RhTnl7E6sK8KWBphGQ0WumMHdQaOaTmOvdjgABsJ3GO1Kf9UJJmbBqCQM"
			+ "RbOQ4U90J/wcXvrieRFAt+aO5o9OLc82aRxZRKf7vjn9Qfy6GeOGAYyZJXkDspaoIFITbW+sHEfK"
			+ "kAd2TY4pmZNekAPl1b5Z5SKfwmwigjVP2pKCCsuloFMcnEepAJjr6z/Rdyt4Cp+nIXGbYrT9DJvA"
			+ "T9HeGwHlvZUR1eYpxBBW6JrFBOt6aywNHhb4wHG4RxreTOTx8oxfecBbSfHYpM5040fculYC6B4S"
			+ "OfQUxSWexgMc8h+qjLz+7QvHo3PtjZ3NBP+gRMNCfBIvjhSmn36SxaLDsGgb6KUIXBBg1engh1aU"
			+ "niYUBwXdrwFRnOEXSa4n0eD0mRQnuGFTm/NK9gMmyfNttrmelhpCvUqJsAMi5GuOiGpgjxB9peUs"
			+ "9tAKQPO4PtO38qJoAZoBmYPf1aWkOiJBkue6zrnywNH2ywN9Dez/Fbp5OOKdWlguool5s7qgsgVI"
			+ "R1kh5olZYsJ0zNbphkhbevfpDcuGqxgO/uWbD7e0c+Lgsrl3sbvy5PRmKImR";

	public static void main(String[] args) throws Exception {

		byte[] iv = getRandomIV();
		String ivTxt = getByteToString(iv);
		System.out.println("IV WHILE ENCRYPTION :: " + ivTxt);
		System.out.println("Length :: " + iv.length);

		String encryptedResult = CommonEncryption(plainText, keyAsHexString, iv);

		System.out.println("Encrypted: " + encryptedResult);

		requestToSend = requestPre + encryptedResult + requestPost;

		System.out.println("Final Reqeuest : " + requestToSend);

		String decryptedResult = CommonDecryption(encryptedResult, keyAsHexString);

		System.out.println("Decrypted: " + decryptedResult);
	}

	public static String CommonDecryption(String ciphertext, String keyAsHexString) throws Exception {
		SecretKeySpec skeySpec = getSecretKeySpecFromHexString(algorithm, keyAsHexString);
		byte[] encryptedIVandTextAsBytes = Base64.decode(ciphertext);
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
		String encryptedResult = Base64.encode(encryptedWithIV);
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
