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

import com.khwopa.ebaithak.models.Attachment;

@Repository
public class AttachmentDaoImpl implements AttachmentDao{

	//hibernate object
	@Resource
	private SessionFactory sessionFctory;
			
	//jdbc object
	@Autowired
	private DataSource dataSource;
				
	@Override
	@Transactional
	public void createFile(Attachment a) {
		
		Session session = sessionFctory.getCurrentSession();
		session.save(a);
		
	}

	@Override
	public List<Attachment> getAllFiles() {
		
		JdbcTemplate template = new JdbcTemplate(dataSource);
		String sql = "SELECT * FROM `attachment`";
		List<Attachment> fileList = new ArrayList<Attachment>();
		List<Map<String, Object>> rows = template.queryForList(sql);
		for (Map<?, ?> row : rows) {
			//System.out.println(row.get("file"));
			Attachment a = new Attachment();
			a.setFile((String) row.get("file"));
			fileList.add(a);
		}
	
		return fileList;
	}

}
