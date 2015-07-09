package com.acme;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.acme.order.customer.Customer;
import com.acme.order.customer.CustomerRepository;
import com.acme.order.customer.CustomerType;
import com.acme.order.pizza.PizzaOrder;
import com.acme.order.pizza.PizzaOrderService;
import com.acme.order.pizza.PizzaOrderServiceImpl;
import com.acme.order.pizza.PizzaType;
import com.google.common.collect.Lists;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = JpaTutorialApplication.class)
public class JpaTutorialApplicationTests {

	private Customer customer1;
	private Customer customer2;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private PizzaOrderService pizzaOrderService;
	
	@Test
	public void contextLoads() {
		
//		customer1 = new Customer(null, "John Smith", "john@smith.com", "Lodz, Jaracza 74");
//		customer2 = new Customer(null, "Jan Kowalski", "jan@kowalski.pl", "Lodz, Piotrkowska 100");
//		
//		customerRepository.save(Lists.newArrayList(customer1, customer2));
//		
//		pizzaOrderService.createOrder(customer1, PizzaType.BIG);
//		pizzaOrderService.createOrder(customer2, PizzaType.BIG);
		
		
	}
	
	@Test
	public void findIndividualsTest() {
		givenIndividualCustomer();
		
	}
	
	private void givenIndividualCustomer() {
		CustomerType cType = new CustomerType();
		customer1 = new Customer(null, "John", "@wp.pl", "lodz", cType);
	}
	
//	@Test
//	public void dataLoad() {
//		customer1 = new Customer(null, "John Smith", "john@smith.com", "Lodz, Jaracza 74");
//		customer2 = new Customer(null, "Jan Kowalski", "jan@kowalski.pl", "Lodz, Piotrkowska 100");
//		
//		customerRepository.save(Lists.newArrayList(customer1, customer2));
//	}

}
