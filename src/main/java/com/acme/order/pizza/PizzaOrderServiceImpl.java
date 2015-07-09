package com.acme.order.pizza;

import java.util.Date;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acme.order.OrderFactory;
import com.acme.order.OrderStatus;
import com.acme.order.customer.Customer;
import com.acme.order.delivery.DeliveryTimeService;
import com.acme.order.notification.DeliveryTemplate;
import com.acme.order.notification.MailSender;
import com.acme.order.notification.MessageTemplateService;
import com.acme.order.notification.OrderCancelledTemplate;
import com.google.common.collect.Lists;

@Slf4j
@Service
public class PizzaOrderServiceImpl implements PizzaOrderService {

	private final MailSender mailSender;

	private final OrderRepository orderRepository;

	private final OrderFactory orderFactory;

	private final DeliveryTimeService deliveryTimeService;

	private final MessageTemplateService messageTemplate;

	@Autowired
	public PizzaOrderServiceImpl(MailSender mailSender, OrderRepository orderRepository, OrderFactory orderFactory,
			DeliveryTimeService deliveryTimeService, MessageTemplateService messageTemplate) {
		this.orderFactory = orderFactory;
		this.orderRepository = orderRepository;
		this.deliveryTimeService = deliveryTimeService;
		this.messageTemplate = messageTemplate;
		this.mailSender = mailSender;
	}

	@Override
	public Long createOrder(Customer customer, PizzaType type) {
		PizzaOrder order = null;
		try {
			log.info("##############################");
			log.info("Ordering pizza type: {} for customer: {}", type, customer);
			order = orderFactory.create(customer, type);
			Date date = deliveryTimeService.getTime(customer, type);
			order.withEstimatedTime(date);
			order = orderRepository.save(order);
			mailSender.send(createMessage(order), customer.getEmail());
			return order.getId();
		} catch (Exception e) {
			log.error("Error while creating order", e);
			return null;
		} finally {
			log.info("##############################\n");
		}
	}

	@Override
	public void cancelOrder(Long pizzaOrderId) {
		log.info("Cancelling order with id: {}", pizzaOrderId);
		PizzaOrder order = orderRepository.findOne(pizzaOrderId);
		order.cancel();
		orderRepository.save(order);
		OrderCancelledTemplate template = messageTemplate.getCancelTemplate();
		mailSender.send(template, order.getEmail());
	}

	@Override
	public void deliverOrder(Long pizzaOrderId) {
		log.info("Delivering order with id: {}", pizzaOrderId);
		PizzaOrder order = orderRepository.findOne(pizzaOrderId);
		order.deliver();
	}

	private DeliveryTemplate createMessage(PizzaOrder order) {
		DeliveryTemplate template = messageTemplate.getDeliveryTemplate();
		template.putTime(order.getEstimatedTime());
		template.putAddress(order.getAddress());
		return template;
	}

	@Override
	public List<PizzaOrder> fetchOrders() {
		return Lists.newArrayList(orderRepository.findAll());
	}

	@Override
	public List<PizzaOrder> fetchDelivered() {
		return orderRepository.findByStatus(OrderStatus.DELIVERED);
	}

	@Override
	public List<PizzaOrder> fetchUnprocessed() {
		return orderRepository.findByStatus(OrderStatus.CREATED);
	}

	@Override
	public List<PizzaOrder> fetchCancelled() {
		return orderRepository.findByStatus(OrderStatus.CANCELLED);
	}
}
