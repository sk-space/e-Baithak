package com.khwopa.ebaithak.dao;

import java.util.List;

import com.khwopa.ebaithak.models.Baithak;
import com.khwopa.ebaithak.models.BaithakMembers;
import com.khwopa.ebaithak.models.User;

public interface BaithakMembersDao {

	public List<User> getFriends(String uName,long groupId);

	public void addMembers(BaithakMembers bmember);

	public void deleteMembers(long userId, long groupId);
	
	public void deleteMembersByGroup(long groupId);
	
	public List<User> getMembers(long groupId);
	
	public List<User> getActiveMembers(long groupId);
	
	public List<Baithak> getAllGroup(long userId);
	
	public int isExist(long gId, long uId);
	
	public List<BaithakMembers>confirmMember(long uId);
	
	public void confirmAddMember(long bmId);
	
	public void removeConfirmMember(long bmId);
}
