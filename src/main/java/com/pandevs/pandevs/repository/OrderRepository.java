package com.pandevs.pandevs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pandevs.pandevs.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{
	// JPQL
}
