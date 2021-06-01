package com.ec.onlineplantnursery.customer.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ec.onlineplantnursery.customer.entity.Customer;
import com.ec.onlineplantnursery.customer.repository.ICustomerRepository;
import com.ec.onlineplantnursery.exceptions.ResourceNotFoundException;

@Service
public class ICustomerServiceImpl implements ICustomerService{

	
	@Autowired
	ICustomerRepository repo;
	

	public ICustomerServiceImpl(ICustomerRepository repo) {
		super();
		this.repo = repo;
	}
	
	/*Method Name:addCustomer
	 *Parameters:Customer
	 *ReturnType:Customer
	 *Author Name:Sri Vidya Vaddi
	 *Created Date: 25/05/2021 */
	@Transactional
	@Override
	public Customer addCustomer(Customer customer) {
		// TODO Auto-generated method stub
		repo.save(customer);
		return customer;
	}


	/*Method Name:updateCustomer
	 *Parameters:Customer
	 *ReturnType:Customer
	 *Author Name:Sri Vidya Vaddi
	 *Created Date: 25/05/2021 */
	@Override
	@Transactional
	public Customer updateCustomer(Customer tenant) throws ResourceNotFoundException {
		int custId = tenant.getCustomerId();
		Optional<Customer> s = repo.findById(custId);
		Customer s1 = null;
		if(s.isEmpty()) {
			throw new ResourceNotFoundException();
		}
		else {
	
			s1 = s.get();
			s1.setCustomerId(tenant.getCustomerId());
			s1.setCustomerEmail(tenant.getCustomerEmail());
			s1.setCustomerName(tenant.getCustomerName());
			s1.setUsername(tenant.getUsername());
			s1.setPassword(tenant.getPassword());
		
			repo.save(s1);
		}
		return s1;
	}
	
	/*Method Name:deleteCustomer
	 *Parameters:Customer
	 *ReturnType:Customer
	 *Author Name:Sri Vidya Vaddi
	 *Created Date: 25/05/2021 */
	@Override
	@Transactional
	public Customer deleteCustomer(Customer tenant) throws ResourceNotFoundException{
		// TODO Auto-generated method stub
		boolean isDeleted = false;
		int custId = tenant.getCustomerId();
		Optional<Customer> s = repo.findById(custId);
		if(s.isEmpty()) {
			throw new ResourceNotFoundException();
		}
		else {
			repo.delete(tenant);
		}
		return tenant;
		
	}
	


	/*Method Name:viewCustomer
	 *Parameters:custometId
	 *ReturnType:Customer
	 *Author Name:Sri Vidya Vaddi
	 *Created Date: 25/05/2021 */

	@Override
	public Customer viewCustomer(int customerId) throws ResourceNotFoundException {
      Optional<Customer> s = repo.findById(customerId);
      if(s.isEmpty()) {
    	  throw new ResourceNotFoundException();
      }
      else {
		return repo.findById(customerId).get();
	}
	
	}

	/*Method Name:viewAllCustomers
	 *Parameters:No Parameters
	 *ReturnType:List<Customer>
	 *Author Name:Sri Vidya Vaddi
	 *Created Date: 25/05/2021 */
	@Override
	public List<Customer> viewAllCustomers() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}
	
	/*Method Name:validateCustomer
	 *Parameters:username,password
	 *ReturnType:boolean
	 *Author Name:Sri Vidya Vaddi
	 *Created Date: 25/05/2021 */

	@Override
	public boolean validateCustomer(String userName, String password) {
		Boolean validBoolean = false;
		List<Customer> custList = repo.findAll();
		for(Customer c:custList) {
			if((c.getUsername().equalsIgnoreCase(userName))){
				if((c.getPassword()).equals(password)) {
					validBoolean= true;
				}
				
			}
		}
		return validBoolean;
	}

}



