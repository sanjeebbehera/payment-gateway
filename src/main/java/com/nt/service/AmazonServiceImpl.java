package com.nt.service;

import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.model.AmazonOrder;
import com.nt.repository.IAmazonRepository;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;

@Service
public class AmazonServiceImpl implements IAmazonService{
	
	@Autowired
	private IAmazonRepository repository;
	
	@Autowired
	private RazorpayClient client;

	@Override
	public AmazonOrder saveOrder(AmazonOrder order) throws Exception {
		System.out.println("RazorpayClient class take only json file ....");
		JSONObject json=new JSONObject();
		json.put("amount", order.getAmount()*100);//convert into paisa (1 rupee = 100 paisa)
		json.put("currency", "INR"); //Indian Rupees
		json.put("receipt", order.getEmail());
		
		Order razorpayOrder = client.orders.create(json);
		
		order.setRozorpayOrderId(razorpayOrder.get("id"));
		order.setOrderStatus(razorpayOrder.get("status"));
			
		return repository.save(order);
	}

	@Override
	public AmazonOrder updateOrder(Map<String, String> respPayload) throws Exception {
		String rpayOderId = respPayload.get("razor_order_id");
		AmazonOrder order = repository.findByRozorpayOrderId(rpayOderId);
		 
		order.setOrderStatus("PAYMENT_COMPLETED");
		
		return repository.save(order);
	}

}
