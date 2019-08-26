package com.Test;

import java.security.KeyStore;
import java.security.MessageDigest;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.security.spec.AlgorithmParameterSpec;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.net.SocketFactory;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;

import com.sun.xml.internal.messaging.saaj.packaging.mime.util.*;

import ch.qos.logback.core.util.Duration;

import java.awt.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.nio.client.HttpAsyncClient;
import org.apache.http.util.EntityUtils;
import org.hibernate.mapping.Set;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.net.ssl.SSLContext;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.cert.CertificateException;

public class Test {
	// https://sakshamuat.axisbank.co.in/gateway/api/v2/bbp/getbillercategory

	//final static String KEY_STORE_PATH = "/home/sen-sei/Documents/certificate/p12/cert2.p12";

	public static void main(String[] args)  throws URISyntaxException{

		String BBPS_BANCS_CHNL_PWD = "axisbankaxisbank";
		String str = "643"+"axisbankaxisbank";
		String str1 = "axisbankaxisbank";
		System.out.println("CHECKSUM=======-> " + sha256(str));
		System.out.println("ENCrypted tokenvalue======-> " + encrypt(str1,"643"));
		
		
		

		KeyStore ks;
		
		
		/*try {

			ks = KeyStore.getInstance("JKS");
			FileInputStream fis = new FileInputStream("/home/sen-sei/Documents/certificate/SelfSigned Certificate/JKS/uat-dmz-api-gateway-cert2.pfx");
			ks.load(fis, "password".toCharArray());
			KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
			kmf.init(ks, "password".toCharArray());

			SSLContext sc = SSLContext.getInstance("TLS");
			sc.init(kmf.getKeyManagers(), null, null);
			SSLSocketFactory sslSocketFactory = sc.getSocketFactory();
			URL url = new URL("https://sakshamuat.axisbank.co.in/gateway/api/v2/bbp/getbillercategory");
			
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			conn.setSSLSocketFactory(sslSocketFactory);

			CloseableHttpClient httpClient = HttpClients.createDefault();
			HttpGet getRequest = new HttpGet("https://sakshamuat.axisbank.co.in/gateway/api/v2/bbp/getbillercategory");
			getRequest.addHeader("X-IBM-Client-Id", "5ba836c5-85e6-47ce-9926-72f6177153e0");
			getRequest.addHeader("X-IBM-Client-Secret", "E4yV2cO3mJ0yO1pL8eO2kL3wU3fY8aD1vL0kK2mG0yI7dE3fK7");
			getRequest.addHeader("Content-type", "application/json");

			ArrayList<NameValuePair> nameValuePairs = new ArrayList<>();
			nameValuePairs.add(new BasicNameValuePair("requestUUID", "1"));
			nameValuePairs.add(new BasicNameValuePair("serviceRequestId", "2"));
			nameValuePairs.add(new BasicNameValuePair("serviceRequestVersion", "3"));
			nameValuePairs.add(new BasicNameValuePair("channelId", "IFMR"));
			URI uri = new URIBuilder(getRequest.getURI()).addParameters(nameValuePairs).build();
			getRequest.setURI(uri);
			
			
			HttpResponse response = httpClient.execute(getRequest);
			  
			  if (response.getStatusLine().getStatusCode() != 200) { throw new
			  RuntimeException("Failed : HTTP error code : " +
			  response.getStatusLine().getStatusCode()); }
			  
			  BufferedReader br = new BufferedReader(new
			  InputStreamReader((response.getEntity().getContent())));
			  
			  String output; System.out.println("Output from Server .... \n"); while
			  ((output = br.readLine()) != null) { System.out.println(output); }
			  
			  

		} catch (IOException |CertificateException |KeyStoreException |NoSuchAlgorithmException |UnrecoverableKeyException |KeyManagementException e) {
			e.printStackTrace();
		}*/

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
			return new String(BASE64EncoderStream.encode(encrypted));
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
			byte[] orignal = cipher.doFinal(BASE64DecoderStream.decode(encrypted.getBytes()));
			return new String(orignal);
		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return null;
	}
}
class Emp{
	int id=101;
	String name="dinesh";
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Emp [id=" + id + ", name=" + name + "]";
	}
	
}
