package com.Test;
//GetBillerRegDetailsResponse
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URI;
import java.net.URL;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.json.JSONObject;


//String request = "{\"BBPGetBillerCategoryRequest\":{\"SubHeader\":{\"requestUUID\":\"" + requestUUID+ "\",\"serviceRequestId\":\"" + serviceRequestId + "\",\"serviceRequestVersion\":\""+ serviceRequestVersion + "\",\"channelId\":\"IFMR\"},\"BBPGetBillerCategoryRequestBodyEncrypted\":\""+ encryptedRequestBody + "\"}}";
//"{\"GetBBPBCH\":{\"msgType\":\"" + msgType + "\",\"channelId\":\"643\",\"msgRef\":\""+msgRef+"\",\"numRem\":\""+numRem+"\",\"numPymnt\":\""+numPymnt+"\",\"totAmt\":\""+totAmt+"\",\"msgVs\":\""+msgVs+"\",\"txnMode\":\""+txnMode+"\",\"checksum\":\""+ checksumValue + "\",\"encToken\":\"" + encToken + "\"},\"GetBBPRemMst\":{\"msgRef\":\""+msgRef+"\",\"drcation\":\""+drcation+"\"},\"GetBBPRemDtl\":{\"txnRefNum\":\""+txnRefNum+"\",\"mandateRef\":\""+mandateRef+"\"},\"GetBBPRemChqDtl\":{\"ouid\":\""+ouid+"\"},\"GetBBPPtsBbpsBillAddtnlInfo\":{\"billPeriod\":\""+billPeriod+"\",\"invoiceNo\":\""+invoiceNo+"\",\"billerCode\":\""+billerCode+"\"}"+",\"ptsBbpsBillAddtnlInfoVer\":\""+1+"\"}";
public class HttpClient {
	
	static String ibmClientId="5ba836c5-85e6-47ce-9926-72f6177153e0";
	static String ibmClientSecret="E4yV2cO3mJ0yO1pL8eO2kL3wU3fY8aD1vL0kK2mG0yI7dE3fK7";
	static String AES128EncryptionKey="2CB719C5DF693CA2C626EB1A02E9F2BD";
	static String channelId="IFMR";
	static String body_channelId="643";
	static String bbps_bancs_chnl_pwd="axisbankaxisbank";
	static String CHKSUM_KEY="axisbankaxisbank";
	static String ENC_KEY="axisbankaxisbank";
	static String checksumKey =body_channelId+CHKSUM_KEY;
	static String encTokenKey=body_channelId+ENC_KEY;


	static String resBody="";
	static JSONObject jsonObj=null;
	/*static {
		checksumValue = HttpClientUtil.sha256("643" + "axisbankaxisbank");
		encToken = HttpClientUtil.encrypt("axisbankaxisbank","643"+"axisbankaxisbank");

	}*/


	public static void main(String[] args) throws Exception {
		
		String corpCode="ATPOST000NAT01";
		String rsrvdStr1 = "";
		String ouid ="Airtel Postpaid";
		String instructedChnl = "643";
		String pymntMode ="Internet Banking";
		String clrngChnlID ="747";
		String atm ="300";
		String pymntChnlValue ="INT";
		String name ="BASE_BILL_AMOUNT";
		String value ="3000";
		Amount amt=new Amount();
		amt.setName(name);
		amt.setValue(value);
		
		

		//System.out.println(j);
		String checksumValue=HttpClientUtil.sha256(instructedChnl + rsrvdStr1 + clrngChnlID + corpCode + atm +"axisbankaxisbank");		
	    String encToken=HttpClientUtil.encrypt("axisbankaxisbank",instructedChnl + rsrvdStr1 + clrngChnlID + corpCode + atm +"axisbankaxisbank");

	    
	
	    String requestBody="{\"GetAPIBPMoInpCttxn\":{\"ouId\":\"" + ouid + "\",\"instructedChnl\":\"" + instructedChnl
				+ "\",\"pymntMode\":\"" + pymntMode + "\",\"clrngChnlID\":\"" + clrngChnlID
				+ "\"},\"GetAPIBBMoBillPresentResp\":{\"corpCode\":\"" + corpCode + "\",\"atm\":\"" + atm
				+ "\",\"Amount\":{\"tag\":{\"" + amt + "\"}} ,\"pymntChnlvalue\":\"" + pymntChnlValue + "\",\"checksomeToken\":\"" + checksumValue
				+ "\",\"encryptToken\":\"" + encToken + "\"}}";
	    //String requestBody="{\"billCategory\":\"" + ephRefNum + "\",\"channelId\":\""+channelId+"\",\"checksum\":\""
				//+ checksumValue + "\",\"encToken\":\"" + encToken + "\"}";
	    String encryptedResult=HttpClientUtil.encryptedResult(requestBody);
		String s1[]=encryptedResult.split("\n");
		String encryptedResult1="";
		for (int i = 0; i < s1.length; i++) {
			encryptedResult1=encryptedResult1+s1[i];
		}
		HttpClient.client(HttpClient.addSSLCertificate(),encryptedResult1);
		String responseArray=HttpClientUtil.decryptedResult(resBody);
		System.out.println(HttpClientUtil.decryptedResult(resBody));
		JSONObject jsonObj=new JSONObject(responseArray);
		/*JSONArray jsonArray = jsonObj.getJSONObject("GetBillerRegDetailsResponseArray")
				.getJSONArray("GetBillerRegDetailsResponse");
		
		for (int i = 0; i < jsonArray.length(); i++) {
			System.out.println("corpCode = "+jsonArray.getJSONObject(i).get("corpCode"));
			System.out.println("fieldDesc = "+jsonArray.getJSONObject(i).get("fieldDesc"));
			System.out.println("fieldIdentify = "+jsonArray.getJSONObject(i).get("fieldIdentify"));
			System.out.println("fieldFormat = "+jsonArray.getJSONObject(i).get("fieldFormat"));
			System.out.println("isMand = "+jsonArray.getJSONObject(i).get("isMand"));
			System.out.println("isConstant = "+jsonArray.getJSONObject(i).get("isConstant"));
			if(i==0) {
			System.out.println("varType = "+jsonArray.getJSONObject(i).get("varType"));
			}
			System.out.println("length = "+jsonArray.getJSONObject(i).get("length"));
			System.out.println();
		}*/

	}
	//}


	private static void client(SSLContext sc,String encryptedResult) throws Exception{
		CloseableHttpClient client = HttpClients.custom().setSslcontext(sc).build();

		HttpPost post = new HttpPost("https://sakshamuat.axisbank.co.in/gateway/api/v2/bbp/chargecalc");
		post.addHeader("X-IBM-Client-Id", ibmClientId);
		post.addHeader("X-IBM-Client-Secret", ibmClientSecret);
		post.addHeader("Content-type", "application/json");

		//String request="BBPGetBillerLocationRequest";
		//String json1 = "{\"BBPBillDueEnquiryRequest\":{\"SubHeader\":{\"requestUUID\":\"1\",\"serviceRequestId\":\"2\",\"serviceRequestVersion\":\"3\",\"channelId\":\"IFMR\"},\"BBPBillDueEnquiryRequestBodyEncrypted\":\""+encryptedResult+"\"}}";
		//String json1 = "{\"BBPBillerRequest\":{\"SubHeader\":{\"requestUUID\":\"123\",\"serviceRequestId\":\"AE.ATM.IRIS.SDBSP.001\",\"serviceRequestVersion\":\"1.0\",\"channelId\":\"IFMR\"},\"BBPBillerRequestBodyEncrypted\":\""+encryptedResult+"\"}}";
        //String json1="{\"BBPChargeCalcRequest\":{\"SubHeader\":{\"requestUUID\":\"1\",\"serviceRequestId\":\"2\",\"serviceRequestVersion\":\"3\",\"channelId\":\"IFMR\"},\"BBPChargeCalcRequest"
		String json1="{\"BBPChargeCalcRequest\":{\"SubHeader\":{\"requestUUID\":\"1\",\"serviceRequestId\":\"2\",\"serviceRequestVersion\":\"3\",\"channelId\":\"IFMR\"},\"BBPChargeCalcRequestBodyEncrypted\":\""
				+ encryptedResult + "\"}}";
		post.setEntity(new StringEntity(json1));

		HttpResponse response = client.execute(post);
		InputStreamReader in=new InputStreamReader(response.getEntity().getContent());
				
		if (response.getStatusLine().getStatusCode() != 200) {
			throw new RuntimeException(
					"Failed : HTTP error code : " + response.getStatusLine().getStatusCode() + "");
		}
		System.out.println(getResponseResult(in));
		
	}
	
	
	private static SSLContext addSSLCertificate() throws Exception{
		KeyStore ts = KeyStore.getInstance("JKS");
		FileInputStream fis1 = new FileInputStream(
				"/home/sen-sei/Documents/certificate/new certificate/sakshamuat.jks");
		ts.load(fis1, "password".toCharArray());
		KeyStore ks = KeyStore.getInstance("PKCS12");
		FileInputStream fis2 = new FileInputStream(
				"/home/sen-sei/Documents/certificate/SelfSigned Certificate/JKS/uat-dmz-api-gateway-cert2.pfx");
		ks.load(fis2, "password".toCharArray());

		KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
		kmf.init(ks, "password".toCharArray());

		SSLContext sc = SSLContext.getInstance("TLS");
		sc.init(kmf.getKeyManagers(), null, null);
		SSLSocketFactory sslSocketFactory = sc.getSocketFactory();
		return sc;
	}

	private static String getResponseResult(InputStreamReader in) throws IOException{
		BufferedReader br = new BufferedReader(in);
		StringBuilder sb = new StringBuilder();
		String output;
		System.out.println("Output from Server .... \n");
		System.out.println();
		
		while ((output = br.readLine()) != null) {
			//System.out.println(output);
			sb.append(output);
			jsonObj = new JSONObject(output);
						
		}
		System.out.println(jsonObj);
		Iterator<String> it=jsonObj.keys();	
		String s1=it.next();
		if(s1.equalsIgnoreCase("BBPGetBillerCategoryResponse"))
			resBody = (String) jsonObj.getJSONObject("BBPGetBillerCategoryResponse")
			.get("BBPGetBillerCategoryResponseBodyEncrypted");
		if(s1.equalsIgnoreCase("BBPGetBillerNmResponse"))
		resBody= (String) jsonObj.getJSONObject("BBPGetBillerNmResponse").get("BBPGetBillerNmResponseBodyEncrypted");
		if(s1.equalsIgnoreCase("BBPGetBillerLocationResponse"))
			resBody= (String) jsonObj.getJSONObject("BBPGetBillerLocationResponse").get("BBPGetBillerLocationResponseBodyEncrypted");
		if (s1.equalsIgnoreCase("BBPGetRegistrationFormResponse"))
			resBody=(String) jsonObj.getJSONObject("BBPGetRegistrationFormResponse")
					.get("BBPGetRegistrationFormResponseBodyEncrypted");
		if(s1.equalsIgnoreCase("BBPBillerResponse"))
			resBody= (String) jsonObj.getJSONObject("BBPBillerResponse").get("BBPBillerResponseBodyEncrypted");
		if(s1.equalsIgnoreCase("BBPBillDueEnquiryResponse"))
			resBody= (String) jsonObj.getJSONObject("BBPBillDueEnquiryResponse").get("BBPBillDueEnquiryResponseBodyEncrypted");
		if(s1.equalsIgnoreCase("BBPChargeCalcResponse"))
			resBody= (String) jsonObj.getJSONObject("BBPChargeCalcResponse").get("BBPChargeCalcResponseBodyEncrypted");
		if(s1.equalsIgnoreCase("BBPBillProcessCIResponse"))
			resBody= (String) jsonObj.getJSONObject("BBPBillProcessCIResponse").get("BBPBillProcessCIResponseBodyEncrypted");
		if(s1.equalsIgnoreCase("BBPBillPaymentEnquiryResponse"))
			resBody= (String) jsonObj.getJSONObject("BBPBillPaymentEnquiryResponse").get("BBPBillPaymentEnquiryResponseBodyEncrypted");
		if(s1.equalsIgnoreCase("BBPCTSReqResponse"))
			resBody= (String) jsonObj.getJSONObject("BBPCTSReqResponse").get("BBPCTSReqResponseBodyEncrypted");
		if(s1.equalsIgnoreCase("BBPCTSEnquiryResponse"))
			resBody= (String) jsonObj.getJSONObject("BBPCTSEnquiryResponse").get("BBPCTSEnquiryResponseBodyEncrypted");
		return sb.toString();
	}

}
class AdditionalInfo{
	String name="a";
	String value="10";	
	@Override
	public String toString() {
		return "{\"_name\":\"" + name +"\",\"_value\":\""+ value +"\" }";
	}
}
class Amount{
	private String name;
	private String value;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return "@name\":\"" + name + "\",\"@value\":\"" + value + "";
	}
		
}
/*
"{\"GetAPIBPMoInpCttxn\":{\"ouId\":\"" + ouid + "\",\"instructedChnl\":\"" + instructedChnl
				+ "\",\"pymntMode\":\"" + pymntMode + "\",\"clrngChnlID\":\"" + clrngChnlID
				+ "\"},\"GetAPIBBMoBillPresentResp\":{\"corpCode\":\"" + corpCode + "\",\"atm\":\"" + atm
				+ "\",\"amount\":{\"tag\":{\"@name\":\"" + name + "\",\"@value\":\"" + value
				+ "\"}} ,\"pymntChnlvalue\":\"" + pymntChnlValue + "\",\"checksomeToken\":\"" + checksumValue
				+ "\",\"encryptToken\":\"" + encToken + "\"}}";
 */
