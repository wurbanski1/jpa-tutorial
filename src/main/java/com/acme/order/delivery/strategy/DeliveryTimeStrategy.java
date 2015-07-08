package com.acme.order.delivery.strategy;

import com.acme.order.customer.Customer;
import com.acme.order.pizza.PizzaType;

public interface DeliveryTimeStrategy {

	int provideDeliveryMinutesOffset(Customer customer, PizzaType pizzaType);

}
