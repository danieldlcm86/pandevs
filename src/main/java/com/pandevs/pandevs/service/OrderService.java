package com.pandevs.pandevs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pandevs.pandevs.exceptions.EmailNotFoundException;
import com.pandevs.pandevs.model.Order;
import com.pandevs.pandevs.model.User;
import com.pandevs.pandevs.repository.OrderRepository;
import com.pandevs.pandevs.repository.UserRepository;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class OrderService {
	
	private OrderRepository orderRepository;
	private UserRepository userRepository;

	@Autowired
	public OrderService(OrderRepository orderRepository, UserRepository userRepository) {
		this.orderRepository = orderRepository;
		this.userRepository = userRepository;
	}
	
	// GetAll
	public List<Order> getAll() {
		return orderRepository.findAll();
	}
	
	// Post 
	public Order newOrder(Order order, String email) {
		User existingUser = userRepository.findByEmail(email);
		if (existingUser == null) {
			throw new EmailNotFoundException(email);
		}
		
		// asociar la orden con el usuario existente
		order.setUser(existingUser);

		return orderRepository.save(order);
	}
	
	
	
	
	
	
	
}
