package demo;

import cn.my.commons.CommonUtils;
import dao.CustomerDao;
import domain.Customer;

public class Demo1 {

	public static void main(String[] args) {
		CustomerDao dao=new CustomerDao();
		
		for(int i=0;i<100;i++) {
			Customer customer=new Customer();
			customer.setId(CommonUtils.uuid());
			customer.setName("customer"+i);
			customer.setGender(i%2==0?"male":"female");
			customer.setPhone("12345"+i);
			customer.setEmail("customer"+i+"@qq.com");
			customer.setDescription("Hello world");
			dao.add(customer);
		}

	}

}
