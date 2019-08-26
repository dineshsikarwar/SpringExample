package com.Test;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.security.KeyStore;

import javax.net.ssl.HttpsURLConnection;

public class TwoWaySSLTest  {

	// final static String KEY_STORE_PATH =
	// "/home/sen-sei/Documents/certificate/p12/cert2.p12";
	//"/home/sen-sei/Documents/certificate/new certificate/sakshamuat.jks"
	//"/home/sen-sei/Documents/certificate/new certificate/clientsertificate.jks"

	public TwoWaySSLTest() throws IOException {
		String BBPS_BANCS_CHNL_PWD = "axisbankaxisbank";
		String str = "IFMR" + "axisbankaxisbank";
		String str1 = "axisbankaxisbank";
		/*
		 * System.out.println("CHECKSUM=======-> " + sha256(str));
		 * System.out.println("ENCrypted value======-> " + encrypt(str1, "IFMR"));
		 */
		//KeyStore ks;

		System.setProperty("javax.net.debug", "all");
		System.setProperty("jdk.tls.client.protocols", "TLSv1.2");
		System.setProperty("https.protocols", "TLSv1.2");
		//System.setProperty("javax.net.ssl.trustStore","/home/sen-sei/Documents/certificate/SelfSigned Certificate/uat-dmz-api-gateway.key");
		System.setProperty("javax.net.ssl.trustStore","/home/sen-sei/Documents/certificate/new certificate/sakshamuat.jks");
		System.setProperty("javax.net.ssl.trustStorePassword", "password");
		System.setProperty("javax.net.ssl.keyStore", "/home/sen-sei/Documents/certificate/SelfSigned Certificate/JKS/uat-dmz-api-gateway-cert2.pfx");
		//System.setProperty("javax.net.ssl.keyStore", "/home/sen-sei/Documents/certificate/SelfSigned Certificate/JKS/uat-dmz-api-gateway-cert (2).crt");
		//System.setProperty("javax.net.ssl.keyStore", "/home/sen-sei/Documents/certificate/new certificate/clientsertificate.jks");
		System.setProperty("javax.net.ssl.keyStorePassword", "password");

		URL url = new URL("https://sakshamuat.axisbank.co.in/gateway/api/v2/bbp/getbillercategory");
		
		HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setRequestProperty("X-IBM-Client-Id", "5ba836c5-85e6-47ce-9926-72f6177153e0");
		conn.setRequestProperty("X-IBM-Client-Secret", "E4yV2cO3mJ0yO1pL8eO2kL3wU3fY8aD1vL0kK2mG0yI7dE3fK7");
		conn.setRequestProperty("Content-type", "application/json");
		conn.setDoOutput(true);
		conn.setDoInput(true);

		String json = "{\r\n" + "    \"BBPGetBillerCategoryRequest\": {\r\n" + "        \"SubHeader\": {\r\n"
				+ "            \"requestUUID\": \"1\",\r\n" + "            \"serviceRequestId\": \"2\",\r\n"
				+ "            \"serviceRequestVersion\": \"3\",\r\n" + "            \"channelId\": \"IFMR\"\r\n"
				+ "        },\r\n"
				+ "        \"BBPGetBillerCategoryRequestBodyEncrypted\": \"jhI5nAdyb1qOEjmcB3JvWhVQ6SXYbqByJq8eeopx43hUupv85qgg0HMAbAi8CKyvFRLDMidpLaYm6LkfwE4o+t/8Bc162czJtYf+Z2rntG8OeC21cRg+RtURol3iSPwTAoNnz7/Exb9wlqGvzGbUP+AFjLvzCBHtW56vQFIHAcrFTS0CD0mTsf7NARbEd1FJBNG6/nEOroNbc0CMkiaL4thd0HNSo/8Bv034FDDfuyA=\"\r\n"
				+ "    }\r\n" + "}";

		System.err.println(conn.getURL());
		OutputStream os = conn.getOutputStream(); os.write(json.getBytes("UTF-8"));
		os.close();
		 

		// read the response
		InputStream in = new BufferedInputStream(conn.getInputStream());
		System.out.println(getStringFromInputStream(in));

		/*
		 * SSLSocketFactory sslSocketFactory = (SSLSocketFactory)
		 * SSLSocketFactory.getDefault(); SSLSocket sslSocket = null; try { sslSocket =
		 * (SSLSocket) sslSocketFactory.createSocket("sakshamuat.axisbank.co.in", 443);
		 * sslSocket.startHandshake();
		 * 
		 * InputStream inputStream = sslSocket.getInputStream(); OutputStream
		 * outputStream = sslSocket.getOutputStream();
		 * 
		 * System.out.println(inputStream);
		 * 
		 * while (true) { System.out.print("Command: "); String message = "Hello";
		 * send(message, outputStream);
		 * 
		 * message = receive(inputStream); System.out.println("Server response: " +
		 * message); }
		 * 
		 * } catch (IOException e) { System.err.println("Connection to server lost."+
		 * e); } finally { if (sslSocket != null) { try { sslSocket.close(); } catch
		 * (IOException e) { e.printStackTrace(); } } }
		 */

		/*
		 * try {
		 * 
		 * ks = KeyStore.getInstance("PKCS12"); FileInputStream fis = new
		 * FileInputStream("/home/sen-sei/Documents/certificate/p12/cert2.p12");
		 * ks.load(fis, "root".toCharArray()); KeyManagerFactory kmf =
		 * KeyManagerFactory.getInstance("SunX509"); kmf.init(ks, "root".toCharArray());
		 * 
		 * SSLContext sc = SSLContext.getInstance("TLS"); sc.init(kmf.getKeyManagers(),
		 * null, null); SSLSocketFactory sslSocketFactory = sc.getSocketFactory(); URL
		 * url = new
		 * URL("https://sakshamuat.axisbank.co.in/gateway/api/v2/bbp/getbillercategory")
		 * ;
		 * 
		 * HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
		 * conn.setSSLSocketFactory(sslSocketFactory);
		 * 
		 * CloseableHttpClient httpClient = HttpClients.createDefault(); HttpGet
		 * getRequest = new HttpGet(
		 * "https://sakshamuat.axisbank.co.in/gateway/api/v2/bbp/getbillercategory");
		 * getRequest.addHeader("X-IBM-Client-Id",
		 * "5ba836c5-85e6-47ce-9926-72f6177153e0");
		 * getRequest.addHeader("X-IBM-Client-Secret",
		 * "E4yV2cO3mJ0yO1pL8eO2kL3wU3fY8aD1vL0kK2mG0yI7dE3fK7");
		 * getRequest.addHeader("Content-type", "application/json");
		 * 
		 * ArrayList<NameValuePair> nameValuePairs = new ArrayList<>();
		 * nameValuePairs.add(new BasicNameValuePair("requestUUID", "1"));
		 * nameValuePairs.add(new BasicNameValuePair("serviceRequestId", "2"));
		 * nameValuePairs.add(new BasicNameValuePair("serviceRequestVersion", "3"));
		 * nameValuePairs.add(new BasicNameValuePair("channelId", "IFMR")); URI uri =
		 * new URIBuilder(getRequest.getURI()).addParameters(nameValuePairs).build();
		 * getRequest.setURI(uri);
		 * 
		 * HttpResponse response = httpClient.execute(getRequest);
		 * 
		 * if (response.getStatusLine().getStatusCode() != 200) { throw new
		 * RuntimeException("Failed : HTTP error code : " +
		 * response.getStatusLine().getStatusCode()); }
		 * 
		 * BufferedReader br = new BufferedReader(new
		 * InputStreamReader((response.getEntity().getContent())));
		 * 
		 * String output; System.out.println("Output from Server .... \n"); while
		 * ((output = br.readLine()) != null) { System.out.println(output); }
		 * 
		 * 
		 * 
		 * } catch (IOException | URISyntaxException e) { e.printStackTrace(); }
		 */
	}

	public static void main(String[] args) throws IOException {

		new TwoWaySSLTest();

	}

	// convert InputStream to String
	private static String getStringFromInputStream(InputStream is) {

		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();

		String line;
		try {

			br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return sb.toString();

	}

}
