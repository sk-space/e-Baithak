package com.khwopa.ebaithak.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.khwopa.ebaithak.models.Emoji;

@Repository
public class EmojiDaoImpl implements EmojiDao{
		
	//jdbc object
	@Autowired
	private DataSource dataSource;
		
	@Override
	public List<Emoji> getAllEmoji() {		
		
		JdbcTemplate template = new JdbcTemplate(dataSource);
		String sql = "SELECT * FROM emoji";
		List<Emoji> emojiList = new ArrayList<Emoji>();
		List<Map<String, Object>> rows = template.queryForList(sql);
		for (Map<?, ?> row : rows) {
			Emoji emoji = new Emoji();
			emoji.setId((Long) row.get("id"));
			emoji.setName((String) row.get("name"));
			emoji.setImage((String) row.get("image"));
			emojiList.add(emoji);
		}
	
		return emojiList;
		
	}

}
