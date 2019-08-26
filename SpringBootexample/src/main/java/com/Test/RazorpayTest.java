package com.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.razorpay.Order;
import com.razorpay.Payment;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

public class RazorpayTest {

	public static void main(String[] args) throws ParseException {
		try {
			RazorpayClient razorpayClient = new RazorpayClient("rzp_test_8EvBKvAMFVDjWU", "wZbVWt3V7aUB1C9kG0VUIiCM");
			Map<String, String> headers = new HashMap<String, String>();
			headers.put("Content-type", "application/json");
			razorpayClient.addHeaders(headers);
			razorpayClient.addHeaders(headers);
			JSONObject orderRequest = new JSONObject();

		/*	orderRequest.put("amount", 30000);
			orderRequest.put("currency", "INR");
			orderRequest.put("receipt", "test_1");
			orderRequest.put("payment_capture", true);

			Order order = razorpayClient.Orders.create(orderRequest);
			System.out.println(order);
			String id=order.get("id");
			//System.out.println(id);*/
			//Date value = order.get("created_at");
			//System.out.println(value);
			//JSONArray notes=order.get("notes");
			//ArrayList arr=(ArrayList) notes.toList();
			//System.out.println(arr);

			//orderRequest.put("count", 10);
			//orderRequest.put("skip", 1);

		//	List<Order> orders = razorpayClient.Orders.fetchAll();
		//	System.out.println(orders);
			
			
			/*Order order1 = razorpayClient.Orders.fetch("order_D9HbGoZpErP6wg");
			System.out.println(order1);*/
			 
			//List<Payment> payments = razorpayClient.Orders.fetchPayments("order_D9HbGoZpErP6wg");
		    //System.out.println(payments);
			/*JSONObject captureRequest = new JSONObject();
			captureRequest.put("amount", 200000); // Amount should be in paise // 
			Payment payment =razorpayClient.Payments.capture("pay_D9KkPC0CEjXCen",captureRequest);
			System.out.println(payment);*/
			
			//List<Payment> payments = razorpayClient.Orders.fetchPayments("order_D3mk92pVovyIyX");
			//System.out.println(payments);
			Payment payment = razorpayClient.Payments.fetch("pay_D9KkPC0CEjXCen");
			System.out.println(payment);
			//List<Payment> payments1 = razorpayClient.Payments.fetchAll();
		  // System.out.println(payments1);

		} catch (RazorpayException e) {
			System.out.println(e.getMessage());
		}
	}

}
