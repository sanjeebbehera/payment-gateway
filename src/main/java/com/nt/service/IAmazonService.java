package com.nt.service;

import java.util.Map;

import com.nt.model.AmazonOrder;

public interface IAmazonService {
	AmazonOrder saveOrder(AmazonOrder order)throws Exception;
	public AmazonOrder updateOrder(Map<String,String> respPayload)throws Exception;
}
