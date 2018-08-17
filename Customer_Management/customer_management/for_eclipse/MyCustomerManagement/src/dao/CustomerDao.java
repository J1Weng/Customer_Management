package dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;


import cn.my.jdbc.MyQueryRunner;
import domain.Customer;
import domain.PageBean;

public class CustomerDao {
	private  MyQueryRunner qr=new MyQueryRunner();
	
//	添加客户信息
	public void add(Customer c) {
		try {
			String sql="insert into customer values(?,?,?,?,?,?)";
			Object[] params= {c.getId(),c.getName(),c.getGender(),c.getPhone(),c.getEmail(),c.getDescription()};
			qr.update(sql,params);
		}catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
//	查找某页全部记录
	public PageBean<Customer> findAll(int pc,int pr){
		try {
			PageBean<Customer> pb=new PageBean<>();
			pb.setPc(pc);
			pb.setPr(pr);
			
			String sql="select count(*) from customer";
			Number number=(Number)qr.query(sql, new ScalarHandler<>());
			int tr=number.intValue();
			pb.setTr(tr);
			
			sql="select * from customer order by name limit ?,?";
			Object[] params= {(pc-1)*pr,pr};
			List<Customer> beanList=qr.query(sql, new BeanListHandler<>(Customer.class),params);
			
			pb.setBeanList(beanList);
			
			return pb;
		}catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	//按id查找客户信息
	public Customer find(String id) {
		try {
			String sql="select * from customer where id=?";
			return qr.query(sql, new BeanHandler<Customer>(Customer.class),id);
		}catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
//	更改客户信息
	public void edit(Customer customer) {
		try {
			String sql="update customer set name=?,gender=?,phone=?,email=?,description=? where id=?";
			Object[] params= {customer.getName(),customer.getGender(),customer.getPhone(),customer.getEmail(),customer.getDescription(),customer.getId()};
			qr.update(sql,params);
			
		}catch (Exception e) {
			throw new RuntimeException(e);	
		}
	}
//	根据id删除客户信息
	public void delete(String id) {
		try {
			String sql="delete from customer where id=?";
			qr.update(sql,id);
		}catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
//	根据条件筛选客户信息，以页面多条记录形式返回
	public PageBean<Customer> query(Customer customer ,int pc,int pr){
		
		try {
			PageBean<Customer> pb=new PageBean<>();
			pb.setPc(pc);
			pb.setPr(pr);
			
			StringBuilder countSql=new StringBuilder("select count(*) from customer");
			StringBuilder whereSql=new StringBuilder(" where 1=1 ");
			List<Object> params =new ArrayList<>();
			
			
			String name =customer.getName();
			if( name!=null && !name.trim().isEmpty()) {
				whereSql.append(" and name like ?");
				params.add("%"+name+"%");
			}
			
									
			String gender=customer.getGender();
			if(gender!=null && !gender.trim().isEmpty()) {
				whereSql.append(" and gender=?");
				params.add(gender);
			}
						
			String phone =customer.getPhone();
			if(phone!=null && !phone.trim().isEmpty()) {
				whereSql.append(" and phone like ?");
				params.add("%"+phone+"%");
			}
			

			String email=customer.getEmail();
			if(email!=null && !email.trim().isEmpty()) {
				whereSql.append(" and email like ?");
				params.add("%"+email+"%");
			}
			Number num=(Number)qr.query(countSql.append(whereSql).toString(), new ScalarHandler<>(),params.toArray());
			int tr=num.intValue();
			pb.setTr(tr);
			
						
			StringBuilder sql= new StringBuilder("select * from customer ");
			StringBuilder limitSql=new StringBuilder(" limit ?,?");

//			
			params.add((pc-1)*pr);
			params.add(pr);
			
			List<Customer> beanList=qr.query(sql.append(whereSql).append(limitSql).toString(), new BeanListHandler<Customer>(Customer.class),params.toArray());
			pb.setBeanList(beanList);
			
			
			return pb;
		}catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
}
