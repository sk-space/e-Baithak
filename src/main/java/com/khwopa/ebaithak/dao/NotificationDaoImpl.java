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

import com.khwopa.ebaithak.models.Notification;

@Repository
public class NotificationDaoImpl implements NotificationDao {

	//hibernate object
		@Resource
		private SessionFactory sessionFactory;
		
		//jdbc object
		@Autowired
		private DataSource dataSource;
		
	@Override
	public List<Notification> getNotifications(long userId) {
		
		JdbcTemplate template = new JdbcTemplate(dataSource);
		
		String sql = "Select * from notification where userId='"+userId+"' order by created_at desc LIMIT 3";
		List<Notification> nList = new ArrayList<Notification>();

		List<Map<String, Object>> rows = template.queryForList(sql);
		
		for (Map<?, ?> row : rows) {
			Notification notif = new Notification();
			
			notif.setUserId((Long) row.get("userId"));
			notif.setMessage((String) row.get("message"));
			notif.setCreated_at((String) row.get("created_at"));
			
			nList.add(notif);
		}
		
		return nList;
	}

	@Override
	public void setNotification(Notification notif) {

		Session session = sessionFactory.getCurrentSession();
		session.save(notif);
		}
		
	

}
