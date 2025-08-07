package com.nt.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nt.model.AmazonOrder;
import com.nt.service.IAmazonService;

@Controller
public class AmazonController {
	
	@Autowired
	private IAmazonService service;
	
	@GetMapping("/")
	public String homePage() {
		return "index";
	}
	
	@PostMapping(value="/save",produces = "application/JSON")
	@ResponseBody
	public ResponseEntity<AmazonOrder> saveOrder(@RequestBody AmazonOrder order) throws Exception{
		return new ResponseEntity<AmazonOrder>(service.saveOrder(order),HttpStatus.CREATED);
	}
	
	@PostMapping("/payment-callback")
	public String handlePaymentCallback(@RequestParam Map<String,String> respPayload) throws Exception {
		service.updateOrder(respPayload);
		return "success";
	}

}
