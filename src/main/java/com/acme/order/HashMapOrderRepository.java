package com.acme.order;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Repository;

import com.acme.order.pizza.PizzaOrder;

@Slf4j
@Repository
public class HashMapOrderRepository implements OrderRepository {

	private static Long sequence = 1l;
	private final Map<Long, PizzaOrder> database = new HashMap<>();

	public Long save(PizzaOrder order) {
		if (order.getId() == null) {
			Long uniqueId = sequence++;
			order.setId(uniqueId);
		}
		database.put(order.getId(), order);
		return order.getId();

	}

	public void rollback() {
		log.info("Fake db rollback...");
	}

	public PizzaOrder get(Long pizzaOrderId) {
		return database.get(pizzaOrderId);
	}

	@Override
	public List<PizzaOrder> findAll() {
		return database.values()
						.stream()
						.collect(Collectors.toList());
	}

	public List<PizzaOrder> findByOrderStatus(OrderStatus orderStatus) {
		return database.values()
						.stream()
						.filter(o -> o.getState() == orderStatus)
						.collect(Collectors.toList());
	}

}
