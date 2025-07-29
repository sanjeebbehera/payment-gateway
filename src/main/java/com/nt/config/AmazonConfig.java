package com.nt.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

@Configuration
public class AmazonConfig {
	
	@Value("${razorpay.key}")
	private String key;
	
	@Value("${razorpay.secret.key}")
	private String secretKey;
	
	@Bean
	RazorpayClient getClient() throws RazorpayException {
		return new RazorpayClient(key, secretKey);
	}

}
