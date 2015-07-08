package com.acme.order.pizza;

import java.util.List;

import com.acme.order.customer.Customer;

public interface PizzaOrderService {

	Long createOrder(Customer customer, PizzaType type);

	void cancelOrder(Long pizzaOrderId);

	void deliverOrder(Long pizzaOrderId);

	List<PizzaOrder> fetchOrders();

	List<PizzaOrder> fetchDelivered();

	List<PizzaOrder> fetchUnprocessed();

	List<PizzaOrder> fetchCancelled();

}
