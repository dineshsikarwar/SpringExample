package com.Test;

import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RZPTest {
	
	public static void main(String[] args) throws Exception {
		Order o=new Order();
	    String name="din"; 
		System.out.println(o);
		String json=new ObjectMapper().writeValueAsString(o);
		System.out.println(json);
		JSONObject j=new JSONObject(json);
		System.out.println(j.get("amount"));

		
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
