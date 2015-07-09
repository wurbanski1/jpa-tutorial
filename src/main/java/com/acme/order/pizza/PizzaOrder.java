package com.acme.order.pizza;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

import com.acme.order.OrderStatus;
import com.acme.order.customer.Customer;

@Data
@NoArgsConstructor
@Entity
@Table(name = "order_t")
public class PizzaOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;
	@Column
	@Enumerated(EnumType.STRING)
	private PizzaType type;
	@Column
	@Enumerated(EnumType.STRING)
	private OrderStatus status;
	@Column(name = "estimatedDeliveryTime")
	private Date estimatedDeliveryTime;
	@Column(name = "finishTime")
	private Date finishTime;

	public PizzaOrder(Customer customer, PizzaType type) {
		this.status = OrderStatus.CREATED;
		this.customer = customer;
		this.type = type;
	}

	public void withEstimatedTime(Date date) {
		this.estimatedDeliveryTime = date;
	}

	public Date getEstimatedTime() {
		return estimatedDeliveryTime;
	}

	public void cancel() {
		this.status = OrderStatus.CANCELLED;
	}

	public boolean isCreated() {
		return this.status == OrderStatus.CREATED;
	}

	public boolean isCancelled() {
		return this.status == OrderStatus.CANCELLED;
	}

	public boolean isDelivered() {
		return this.status == OrderStatus.DELIVERED;
	}

	public String getEmail() {
		return customer.getEmail();
	}

	public String getAddress() {
		return customer.getAddress();
	}

	public void deliver() {
		this.status = OrderStatus.DELIVERED;
		finishTime = new Date();
	}

	public boolean wasDeliveredOnTime() {
		if (estimatedDeliveryTime == null) {
			return true;
		}
		if (status == OrderStatus.DELIVERED) {
			return estimatedDeliveryTime.after(finishTime);
		}
		throw new IllegalStateException("The Pizza is not delivered yet!");
	}

	public PizzaType getType() {
		return type;
	}

}
