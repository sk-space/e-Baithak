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
import org.springframework.transaction.annotation.Transactional;

import com.khwopa.ebaithak.models.Baithak;
import com.khwopa.ebaithak.models.BaithakMembers;
import com.khwopa.ebaithak.models.Notification;
import com.khwopa.ebaithak.models.User;

@Repository
public class BaithakMembersDaoImpl implements BaithakMembersDao {
	
	//hibernate object
	@Resource
	private SessionFactory sessionFactory;
	
	//jdbc object
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	public UserDao uDao;
	
	@Autowired
	public BaithakDao bDao;
	
	@Override
	@Transactional
	public void addMembers(BaithakMembers bmember) {
		
		Session session = sessionFactory.getCurrentSession();
		session.save(bmember);
		
		Baithak b = bDao.getBaithak(bmember.getGroupId());
		
		User u = uDao.getUser(bmember.getAddedBy());
		Notification notif = new Notification();
		notif.setUserId(bmember.getUserId());
		String message = "<b>"+u.getName()+" ("+u.getUsername()+")</b> added you to the group <b>"+b.getName()+"</b>.";
		notif.setMessage(message);
		// Mon Jul 17 16:45:16 
		notif.setCreated_at(new Date().toString().substring(0, 20));
		session.save(notif);
		
		Notification notif1 = new Notification();
		notif1.setUserId(bmember.getAddedBy());
		User u1 = uDao.getUser(bmember.getUserId());
		String message1 = "<b>"+u1.getName()+" ("+u1.getUsername()+")</b> has been added to the group <b>"+b.getName()+"</b>.";
		notif1.setMessage(message1);
		// Mon Jul 17 16:45:16 
		notif1.setCreated_at(new Date().toString().substring(0, 20));
		session.save(notif1);
		
	}
	

	@Override
	public void deleteMembers(long userId, long groupId) {
		
		JdbcTemplate template = new JdbcTemplate(dataSource);
		String sql = "DELETE FROM `baithakmembers` WHERE groupId = '"+groupId+"' AND userId = '"+userId+"' ";
		System.out.println(sql);
		template.execute(sql);
		
	}
	
	@Override
	public void deleteMembersByGroup(long groupId) {
		
		JdbcTemplate template = new JdbcTemplate(dataSource);
		String sql = "DELETE FROM `baithakmembers` WHERE groupId = '"+groupId+"' ";
		System.out.println(sql);
		template.execute(sql);
		
	}
	
	
	@Override
	public List<BaithakMembers> confirmMember(long userId) {
	
		JdbcTemplate template = new JdbcTemplate(dataSource);
		List<BaithakMembers> memberList = new ArrayList<BaithakMembers>();
		String sql = "SELECT * FROM baithakmembers WHERE userId = '"+userId+"' AND status = 0";
		List<Map<String, Object>> rows = template.queryForList(sql);
		for (Map<?, ?> row : rows) {
			BaithakMembers bmember = new BaithakMembers();
			bmember.setId((Long) row.get("id"));
			bmember.setAddedBy((Long) row.get("addedBy"));
			bmember.setGroupId((Long) row.get("groupId"));
			bmember.setUserId((Long) row.get("userId"));
			
			User u = uDao.getUser((Long) row.get("addedBy"));
			bmember.setSenderName(u.getName());
			
			Baithak b = bDao.getBaithak((Long) row.get("groupId"));
			bmember.setGroupName(b.getName());
			
			memberList.add(bmember);
		}
		return memberList;
		
	}
	
	
	@Override
	public void confirmAddMember(long groupMemberId) {
		
		JdbcTemplate template = new JdbcTemplate(dataSource);
		String sql = " UPDATE `baithakmembers` SET `status`= 1 WHERE `id` = "+groupMemberId+" ";
		template.execute(sql);
		
	}
	

	@Override
	public void removeConfirmMember(long groupMemberId) {
		
		JdbcTemplate template = new JdbcTemplate(dataSource);
		String sql = " DELETE FROM `baithakmembers` WHERE `id` = "+groupMemberId+" ";
		template.execute(sql);
		
	}
	
	
	@Override
	public List<User> getMembers(long groupId) {
	
		JdbcTemplate template = new JdbcTemplate(dataSource);
		List<User> memberList = new ArrayList<User>();
		String sql = "SELECT * FROM baithakmembers WHERE groupId = '"+groupId+"' AND status = 1";
		List<Map<String, Object>> rows = template.queryForList(sql);
		for (Map<?, ?> row : rows) {
			User user = new User();
			String sql2 = "SELECT * FROM user WHERE id = '"+row.get("userId")+"'";
			List<Map<String, Object>> rows1 = template.queryForList(sql2);
			for (Map<?, ?> row1 : rows1) {
				user.setId((Long)row1.get("id"));
				user.setName((String) row1.get("name"));
				user.setUsername((String) row1.get("username"));
				user.setPhoto((String) row1.get("photo"));
				user.setStatus((Integer) row1.get("status"));
				memberList.add(user);
			}
		}
		return memberList;
		
	}

	@Override
	public List<User> getActiveMembers(long groupId) {
		
		JdbcTemplate template = new JdbcTemplate(dataSource);
		List<User> activeMemberList = new ArrayList<User>();
		String sql = "SELECT * FROM baithakmembers WHERE groupId = '"+groupId+"' AND status = 1";
		List<Map<String, Object>> rows = template.queryForList(sql);
		for (Map<?, ?> row : rows) {
			User user = new User();
			String sql2 = "SELECT * FROM user WHERE id = '"+row.get("userId")+"' AND status = 1";
			List<Map<String, Object>> rows1 = template.queryForList(sql2);
			for (Map<?, ?> row1 : rows1) {
				user.setId((Long)row1.get("id"));
				user.setName((String) row1.get("name"));
				user.setUsername((String) row1.get("username"));
				user.setPhoto((String) row1.get("photo"));
				user.setStatus((Integer) row1.get("status"));
				activeMemberList.add(user);
			}
		}
		return activeMemberList;
		
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<Baithak> getAllGroup(long userId) {
		
		JdbcTemplate template = new JdbcTemplate(dataSource);
		List<Baithak> groupList = new ArrayList<Baithak>();
		String sql = "SELECT * FROM baithakmembers WHERE userId = '"+userId+"' AND addedBy != '"+userId+"' AND status = 1";
		List<Map<String, Object>> rows = template.queryForList(sql);
		for (Map row : rows) {
			String sql2 = "SELECT * FROM baithak WHERE id = '"+row.get("groupId")+"' ";
			List<Map<String, Object>> rows1 = template.queryForList(sql2);
			for (Map row1 : rows1) {
				Baithak b = new Baithak();
				b.setId((Long) row1.get("id"));
				b.setName((String) row1.get("name"));
				b.setDiscription((String) row1.get("discription"));
				b.setImage((String) row1.get("image"));
				groupList.add(b);
			}
		}
		return groupList;
	}
	
	@Override
	public List<User> getFriends(String uName, long groupId) {
		
		Long id = uDao.getUserId(uName);
		
		JdbcTemplate template = new JdbcTemplate(dataSource);
		
		List<User> friendsList = new ArrayList<User>();
		
		String sql = "Select * from friends where userId = '"+id+"'";
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
				
//				String sql3 = "Select userId from baithakmembers where userId = '"+row.get("friendId")+"' and groupId = '"+groupId+"'";
//				Long count = template.queryForObject(sql, Long.class);
//				
//				if( count != 0){
//
//					System.out.println((String) row1.get("name"));
//					user.setId((Long)row1.get("id"));
//					user.setName((String) row1.get("name"));
//					user.setUsername((String) row1.get("username"));
//					user.setPhoto((String) row1.get("photo"));
//					user.setStatus((Integer) row1.get("status"));
//					
//				}
//				
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

	@SuppressWarnings("deprecation")
	@Override
	public int isExist(long gId, long uId) {
		
		JdbcTemplate template = new JdbcTemplate(dataSource);
		String sql = "SELECT COUNT(*) FROM baithakmembers WHERE groupId = '"+gId+"' AND userId = '"+uId+"' ";
		int i = template.queryForInt(sql);
		if(i > 0)
			return 1;
		else
			return 0;
	}

}
