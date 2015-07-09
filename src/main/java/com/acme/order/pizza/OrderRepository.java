package com.acme.order.pizza;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.acme.order.OrderStatus;

public interface OrderRepository extends CrudRepository<PizzaOrder, Long> {
	List<PizzaOrder> findByStatus(OrderStatus delivered);
}
