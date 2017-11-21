package com.khwopa.ebaithak.dao;

import java.util.List;

import com.khwopa.ebaithak.models.Message;

public interface MessageDao {
	
	public void createMessage(Message msg);
	
	public void deleteMessage(long groupId);
	
	public List<Message> getMessage(long groupId);

}
