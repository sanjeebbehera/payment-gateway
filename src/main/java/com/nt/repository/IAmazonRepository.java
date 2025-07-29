package com.nt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.model.AmazonOrder;

public interface IAmazonRepository extends JpaRepository<AmazonOrder, Integer> {

	AmazonOrder findByRozorpayOrderId(String orderId);
}
