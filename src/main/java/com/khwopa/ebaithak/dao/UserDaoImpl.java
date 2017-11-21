package com.khwopa.ebaithak.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.khwopa.ebaithak.models.User;

@Repository
public class UserDaoImpl implements UserDao {

	//hibernate object
	@Resource
	private SessionFactory sessionFctory;
	
	//jdbc object
	@Autowired
	private DataSource dataSource;
	
	
	@Override
	/*
	 * @Transctional auto commits the query i.e main consistency
	 * @see com.khwopa.ebaithak.dao.UserDao#addUser(com.khwopa.ebaithak.models.User)
	 */
	@Transactional
	public void addUser(User user) {

		Session session = sessionFctory.getCurrentSession();
		session.save(user);
				
	}
	
	/*
	 * using hibernate
	 * @see com.khwopa.ebaithak.dao.UserDao#getUser(long)
	 */
	@Override
	public User getUser(long id) {
		
		Session session = sessionFctory.openSession();
		User user= (User) session.get(User.class, id);
		session.close();
		
		return user;
	}
	
	/*
	 * Using JDBC
	 * @see com.khwopa.ebaithak.dao.UserDao#isExist(com.khwopa.ebaithak.models.User)
	 */
	@SuppressWarnings("null")
	@Override
	public boolean isExist(User user) {
		
		JdbcTemplate template = new JdbcTemplate(dataSource);
		
		try{
			String sql = "Select username from user where username='"+user.getUsername()+"' and password ='"+user.getPassword()+"'";
			
			String uname = template.queryForObject(sql, String.class);
			
			if(uname != null || !uname.equals("")){
				return true;
			}
			
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return false;
		
		//return false;
	}

	@Override
	public boolean isAdmin(User user) {
		JdbcTemplate template = new JdbcTemplate(dataSource);
		
		try{
			String sql = "Select role from user where username='"+user.getUsername()+"'";
			
			String role = template.queryForObject(sql, String.class);
			
			if(role.equals("admin")){
				return true;
			}
			
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return false;
	}

	@SuppressWarnings("null")
	@Override
	public boolean isUsernameExist(String user) {
		
		JdbcTemplate template = new JdbcTemplate(dataSource);
		
		try{
			String sql = "Select username from user where username='"+user+"'";
			
			String uname = template.queryForObject(sql, String.class);
			
			if(uname != null || !uname.equals("")){
				return true;
			}
			
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return false;
	}

	@Override
	public Long getUserId(String username) {

		JdbcTemplate template = new JdbcTemplate(dataSource);
		
		try{
			String sql = "Select id from user where username='"+username+"'";
			
			Long uid = template.queryForObject(sql, Long.class);
			
				return uid;
			
			
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
		//return null;
	}

	@Override
	public void changeStatus(int status, long userId) {
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update(
                "update user set status = ? where id = ?",status, userId);
	}

	@Override
	public int isOnline(long userId) {

		JdbcTemplate template = new JdbcTemplate(dataSource);
		
		try{
			String sql = "Select status from user where id='"+userId+"'";
			
			int status = template.queryForObject(sql, int.class);
			
				return status;
			
			
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public List<User> getDetail(long userId) {
		
		JdbcTemplate template = new JdbcTemplate(dataSource);
		
		String sql = "Select * from user where id='"+userId+"'";
		List<User> userDetail = new ArrayList<User>();

		List<Map<String, Object>> rows = template.queryForList(sql);
		for (Map<?, ?> row : rows) {
			User user = new User();
			user.setUsername((String) row.get("username"));
			user.setName((String) row.get("name"));
			user.setPhoto((String) row.get("photo"));
			user.setId((Long) row.get("id"));
			userDetail.add(user);
		}
		
		return userDetail;
	}

	@Override
	public List<User> getAllUser() {
		JdbcTemplate template = new JdbcTemplate(dataSource);
		
		String sql = "Select * from user";
		List<User> userDetail = new ArrayList<User>();

		List<Map<String, Object>> rows = template.queryForList(sql);
		for (Map<?, ?> row : rows) {
			User user = new User();
			user.setUsername((String) row.get("username"));
			user.setName((String) row.get("name"));
			user.setPhoto((String) row.get("photo"));
			user.setId((Long) row.get("id"));
			userDetail.add(user);
		}
		
		return userDetail;
	}

	

}
