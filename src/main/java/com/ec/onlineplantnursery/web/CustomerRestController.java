package com.ec.onlineplantnursery.web;

import java.time.LocalDateTime;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ec.onlineplantnursery.customer.entity.Customer;
import com.ec.onlineplantnursery.customer.service.ICustomerServiceImpl;
import com.ec.onlineplantnursery.exceptions.ResourceNotFoundException;
import com.ec.onlineplantnursery.order.entity.Order;
import com.ec.onlineplantnursery.order.repository.IOrderRepository;
import com.ec.onlineplantnursery.order.service.IOrderServiceImpl;
import com.ec.onlineplantnursery.plant.entity.Plant;
import com.ec.onlineplantnursery.plant.repository.IPlantRepository;
import com.ec.onlineplantnursery.plant.service.IPlantServiceImpl;
import com.ec.onlineplantnursery.planter.entity.Planter;
import com.ec.onlineplantnursery.planter.service.IPlanterServiceImpl;
import com.ec.onlineplantnursery.seed.entity.Seed;
import com.ec.onlineplantnursery.seed.service.ISeedServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.slf4j.Logger;
@Validated
@RestController
@RequestMapping("/api/customer")
@Api(value = "Online Nursery Application")
public class CustomerRestController {
	Logger log = org.slf4j.LoggerFactory.getLogger(CustomerRestController.class);
	
	@Autowired
	private IOrderServiceImpl ordService;
	
	@Autowired
	private ICustomerServiceImpl custService;
	
	@Autowired
	private ISeedServiceImpl seedService;
	
	@Autowired
	private IPlantServiceImpl plantService;
	
	@Autowired
	private IPlanterServiceImpl planterService;
	

	/*Method Name:viewCustomer
	 *Parameters:cid
	 *ReturnType:Customer
	 *Author Name:Sri Vidya Vaddi
	 *Created Date: 26/05/2021 */
	
	@GetMapping("/customer/{cid}")
	@ApiOperation(value = "Customer Get mapping to fetch customer by id" , response = Customer.class)
	public Customer viewCustomer(@PathVariable int cid) throws ResourceNotFoundException {
		log.info("Get customer with Id");
	return custService.viewCustomer(cid);	
	}
	
	/*Method Name:addCustomer
	 *Parameters:Customer
	 *ReturnType:Customer
	 *Author Name:Sri Vidya Vaddi
	 *Created Date: 26/05/2021 */
	@PostMapping("/addcustomer")
	@ApiOperation(value = "customer post mapping" , response = Customer.class)
	public Customer addCustomer(@RequestBody @Valid Customer customer) {
		System.out.println(" --------------customer "+customer);
		log.info("postmapping insert customer");
		custService.addCustomer(customer);
		return customer;
	}
	
	/*Method Name:viewAllCustomers
	 *Parameters:No Parameters 
	 *ReturnType:List<Customer>
	 *Author Name:Sri Vidya Vaddi
	 *Created Date: 26/05/2021 */
	@GetMapping("/customer")
	@ApiOperation(value = "Customer Get mapping to fetch all customer" , response = Customer.class)
	public List<Customer> viewAllCustomers() {
		log.info("Get all Customers");
		return custService.viewAllCustomers();
	}
	
	/*Method Name:updateCustomer
	 *Parameters:Customer
	 *ReturnType:Customer
	 *Author Name:Sri Vidya Vaddi
	 *Created Date: 26/05/2021 */
	@PutMapping("/customer")
	@ApiOperation(value = "Customer Put mapping to update Customer" , response = Customer.class)
	public Customer updateCustomer(@RequestBody @Valid Customer c) throws ResourceNotFoundException
	{
		log.info("put mapping update customer");
		return custService.updateCustomer(c);
	}

	/*Method Name:deleteCustomer
	 *Parameters:Customer
	 *ReturnType:Customer
	 *Author Name:Sri Vidya Vaddi
	 *Created Date: 26/05/2021 */
	@PostMapping("/customer/delete")
	@ApiOperation(value = "Customer Post mapping to delete Customer" , response = Customer.class)
	public Customer deleteCustomer(@RequestBody @Valid Customer customer) throws ResourceNotFoundException {
		log.info("post mapping delete customer");
		return custService.deleteCustomer(customer);
	}
	
	/*Method Name:validateCustomers
	 *Parameters:username,password
	 *ReturnType:boolean
	 *Author Name:Sri Vidya Vaddi
	 *Created Date: 26/05/2021 */
	@GetMapping("/customer/validate/{uname}/{pass}")
	@ApiOperation(value = "Customer Get mapping to customer username and password" , response = Customer.class)
	public boolean validateCustomer(@PathVariable String uname, @PathVariable String pass) {
		log.info("get validation for username and password");
		return custService.validateCustomer(uname, pass);
	}
}
