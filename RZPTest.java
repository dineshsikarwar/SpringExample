package com.Test;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.temporal.Temporal;
import java.util.Date;

import javax.net.ssl.HttpsURLConnection;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RZPTest {
	
	public static void main(String[] args) {
		

		
	}


}

class Order{
	String amount="1000";
	String currency="INR";
	String receipt="test_1";
	boolean payment_capture=true;
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getReceipt() {
		return receipt;
	}
	public void setReceipt(String receipt) {
		this.receipt = receipt;
	}
	
	public boolean isPayment_capture() {
		return payment_capture;
	}
	public void setPayment_capture(boolean payment_capture) {
		this.payment_capture = payment_capture;
	}
	@Override
	public String toString() {
		return "Order [amount=" + amount + ", currency=" + currency + ", receipt=" + receipt + ", payment_capture="
				+ payment_capture + "]";
	}

	
}
