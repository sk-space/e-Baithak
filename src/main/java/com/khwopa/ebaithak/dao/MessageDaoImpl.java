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

import com.khwopa.ebaithak.models.Message;

@Repository
public class MessageDaoImpl implements MessageDao{
	
	//hibernate object
		@Resource
		private SessionFactory sessionFctory;
		
		//jdbc object
		@Autowired
		private DataSource dataSource;

	@Override
	@Transactional
	public void createMessage(Message msg) {

		Session session = sessionFctory.getCurrentSession();
		session.save(msg);
		
	}

	@Override
	public void deleteMessage(long gId) {
		
		JdbcTemplate template = new JdbcTemplate(dataSource);
		String sql = "DELETE FROM `messgae` WHERE groupId = '"+gId+"' ";
		System.out.println(sql);
		template.execute(sql);

	}

	@Override
	public List<Message> getMessage(long groupId) {
		
		JdbcTemplate template = new JdbcTemplate(dataSource);
		String sql = "SELECT * FROM `messgae` WHERE `groupId`='"+groupId+"' ";
		List<Message> messageList = new ArrayList<Message>();

		List<Map<String, Object>> rows = template.queryForList(sql);
		for (Map<?, ?> row : rows) {
			Message msg = new Message();
			msg.setSenderId((Long) row.get("senderId"));
			msg.setMessage((String) row.get("message"));
			msg.setCreatedAt((String) row.get("createdAt"));
			messageList.add(msg);
		}
		
		return messageList;
	}

}
