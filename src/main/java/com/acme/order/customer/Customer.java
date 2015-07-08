package com.acme.order.customer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
public class Customer {
	@Id
	private Long id;
	@Column
	private String name;
	@Column
	private String email;
	@Column
	private String address;

}
