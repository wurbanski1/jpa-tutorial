package com.acme.order.pizza;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

import com.acme.order.customer.Customer;
import com.acme.order.OrderStatus;

@Data
@Entity
public class PizzaOrder {

	@Id
	private Long id;
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;
	@Column
	private PizzaType pizzaType;
	@Column
	private OrderStatus state;
	@Column
	private Date estimatedDeliveryTime;
	@Column
	private Date finishTime;

	public PizzaOrder(Customer customer, PizzaType pizzaType) {
		this.state = OrderStatus.CREATED;
		this.customer = customer;
		this.pizzaType = pizzaType;
	}

	public void withEstimatedTime(Date date) {
		this.estimatedDeliveryTime = date;
	}

	public Date getEstimatedTime() {
		return estimatedDeliveryTime;
	}

	public void cancel() {
		this.state = OrderStatus.CANCELLED;
	}

	public boolean isCreated() {
		return this.state == OrderStatus.CREATED;
	}

	public boolean isCancelled() {
		return this.state == OrderStatus.CANCELLED;
	}

	public boolean isDelivered() {
		return this.state == OrderStatus.DELIVERED;
	}

	public String getEmail() {
		return customer.getEmail();
	}

	public String getAddress() {
		return customer.getAddress();
	}

	public void deliver() {
		this.state = OrderStatus.DELIVERED;
		finishTime = new Date();
	}

	public boolean wasDeliveredOnTime() {
		if (estimatedDeliveryTime == null) {
			return true;
		}
		if (state == OrderStatus.DELIVERED) {
			return estimatedDeliveryTime.after(finishTime);
		}
		throw new IllegalStateException("The Pizza is not delivered yet!");
	}

	public PizzaType getPizzaType() {
		return pizzaType;
	}

}
