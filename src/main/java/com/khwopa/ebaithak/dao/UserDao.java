package com.khwopa.ebaithak.dao;

import java.util.List;

import com.khwopa.ebaithak.models.User;

public interface UserDao {
	
	public void addUser(User user);
	
	public User getUser(long id);
	
	public Long getUserId(String username);
	
	public boolean isExist(User user);
	
	public boolean isUsernameExist(String user);
	
	public boolean isAdmin(User user);
	
	public void changeStatus(int status, long userId);
	
	public int isOnline(long userId);
	
	public List<User> getDetail(long userId);
	
	public List<User> getAllUser();
	
	

}
