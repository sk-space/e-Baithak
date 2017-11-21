package com.khwopa.ebaithak.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.khwopa.ebaithak.models.Friends;
import com.khwopa.ebaithak.models.Notification;
import com.khwopa.ebaithak.models.User;

@Repository
public class FriendsDaoImpl implements FriendsDao {

	//hibernate object
	@Resource
	private SessionFactory sessionFactory;
	
	//jdbc object
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	public UserDao uDao;
	
	
	@Override
	public boolean isFriend(long friendId) {

		JdbcTemplate template = new JdbcTemplate(dataSource);
		
		try{
			String sql = "Select userId from friends where friendId='"+friendId+"'";
			
			long userId = template.queryForObject(sql, Long.class);
			
			if(userId > 0){
				return true;
			}
			
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return false;
	}

	@Override
	public List<User> searchFriend(String name) {
			
			JdbcTemplate template = new JdbcTemplate(dataSource);
			
//			Long id = uDao.getUserId(name);
			
			String sql = "Select * from user where username like '%"+name+"%' ";
//			String sql = "Select * from user where username='"+name+"'";
			List<User> userList = new ArrayList<User>();

			List<Map<String, Object>> rows = template.queryForList(sql);
			for (Map<?, ?> row : rows) {
				
				if( isFriend((Long) row.get("id"))){
					
				}else{
					
					User user = new User();
					user.setUsername((String) row.get("username"));
					user.setPhoto((String) row.get("photo"));
					user.setName((String) row.get("name"));
					user.setId((Long) row.get("id"));
					userList.add(user);
					
				}
			}
			
			return userList;
		
	}

	@Override
	public boolean addFriend(Long friendId, Long userId) {

		try{
			
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			
			//adding friend to the USER
			Friends friend = new Friends();
			friend.setFriendId(friendId);
			friend.setUserId(userId);
			friend.setStatus(0);
			session.save(friend);
			
			session.getTransaction().commit();
			
			return true;
			
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public List<User> getFriends(String uName) {
		
		Long id = uDao.getUserId(uName);
		
		JdbcTemplate template = new JdbcTemplate(dataSource);
		
		List<User> friendsList = new ArrayList<User>();
		
		String sql = "Select * from friends where userId = "+id+ " and status = 1";
		//System.out.println(sql);
		List<Map<String, Object>> rows = template.queryForList(sql);
		for (Map<?, ?> row : rows) {
			
			User user = new User();
			
			//String sql1 = "Select username from user where id = '"+row.get("friendId")+"'";
			//String username = template.queryForObject(sql1, String.class);

			String sql2 = "Select * from user where id = '"+row.get("friendId")+"'";
			
			//String sql2 = "Select * from user where id in (Select friendId from friends where userId = '"+id+"') order by status desc";
			
			List<Map<String, Object>> rows1 = template.queryForList(sql2);
			for (Map<?, ?> row1 : rows1) {
				System.out.println((String) row1.get("name"));
				user.setId((Long)row1.get("id"));
				user.setName((String) row1.get("name"));
				user.setUsername((String) row1.get("username"));
				user.setPhoto((String) row1.get("photo"));
				user.setStatus((Integer) row1.get("status"));
				//friendsList.add(user);
			}
			//user.setUsername(username);
			//System.out.println(user.getUsername());
			friendsList.add(user);
		}
		
		return friendsList;
	}
	
	public List<Friends> friendRequest(long userId) {
		
		JdbcTemplate template = new JdbcTemplate(dataSource);
		List<Friends> friendRequestList = new ArrayList<Friends>();
		String sql = "SELECT * FROM friends WHERE friendId = " + userId + " AND status = 0";
		List<Map<String, Object>> rows1 = template.queryForList(sql);
		for (Map<?, ?> row2 : rows1) {
			Friends frnd = new Friends();
			frnd.setId((Long) row2.get("id"));
			frnd.setUserId((Long) row2.get("userId"));
			frnd.setFriendId((Long) row2.get("friendId"));
			
			User user = uDao.getUser((Long) row2.get("userId"));
			frnd.setSenderName(user.getUsername());
			
			friendRequestList.add(frnd);
		}
		return friendRequestList;
	}
	
	@Override
	public void confirmFriend(long friendsTableId) {
		
		JdbcTemplate template = new JdbcTemplate(dataSource);
		String sql = " UPDATE `friends` SET `status`= 1 WHERE `id` = "+friendsTableId+" ";
		template.execute(sql);

		//adding user as a friend to the Friend
		String sql2 = " SELECT * FROM `friends` WHERE id = "+friendsTableId+" ";
		List<Map<String, Object>> rows1 = template.queryForList(sql2);
		for (Map<?, ?> row2 : rows1) {
			
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			
			Friends friend1 = new Friends();
			friend1.setFriendId((Long) row2.get("userId"));
			friend1.setUserId((Long) row2.get("friendId"));
			friend1.setStatus(1);
			session.save(friend1);
			
			Notification notif = new Notification();
			notif.setUserId((Long) row2.get("friendId"));
			User u = uDao.getUser((Long) row2.get("userId"));
			String message = "<b>"+u.getName()+" ("+u.getUsername()+")</b> added you as a friend";
			notif.setMessage(message);
			// Mon Jul 17 16:45:16 
			notif.setCreated_at(new Date().toString().substring(0, 20));
			session.save(notif);
			
			Notification notif1 = new Notification();
			notif1.setUserId((Long) row2.get("userId"));
			User u1 = uDao.getUser((Long) row2.get("friendId"));
			String message1 = "<b>"+u1.getName()+" ("+u1.getUsername()+")</b> added as a friend";
			notif1.setMessage(message1);
			// Mon Jul 17 16:45:16 
			notif1.setCreated_at(new Date().toString().substring(0, 20));
			
			session.save(notif1);
			session.getTransaction().commit();
			
		}
		
	}
	
	@Override
	public void cancelFriend(long friendsTableId) {
		
		JdbcTemplate template = new JdbcTemplate(dataSource);
		String sql = " DELETE FROM `friends` WHERE `id` = "+friendsTableId+" ";
		template.execute(sql);
		
	}

}
