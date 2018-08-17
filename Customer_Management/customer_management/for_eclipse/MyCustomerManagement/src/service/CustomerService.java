package service;

import dao.CustomerDao;
import domain.Customer;
import domain.PageBean;

public class CustomerService {
	CustomerDao customerDao=new CustomerDao();
	
	public void add(Customer c) {
		customerDao.add(c);
	}
	
	public PageBean<Customer> findAll(int pc,int pr){
		return customerDao.findAll(pc, pr);
	}
	
	public Customer find(String id) {
		return customerDao.find(id);
	}
	
	public void edit(Customer c) {
		customerDao.edit(c);
	}
	
	public void delete(String id) {
		customerDao.delete(id);
	}
	
	public PageBean<Customer> query(Customer customer,int pc,int pr){
		return customerDao.query(customer, pc, pr);
	}
}
