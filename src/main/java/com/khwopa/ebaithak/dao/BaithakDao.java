package com.khwopa.ebaithak.dao;

import java.util.List;

import com.khwopa.ebaithak.models.Baithak;
import com.khwopa.ebaithak.models.Leave;

public interface BaithakDao {

	public void createBaithak(Baithak b);
	
	public boolean deletebaithak(long id);
	
	public List<Baithak> getAllBaithak(long createrId);
	
	public Baithak getBaithak(Long id);

	public long getBaithakId(String b);
	
	public List<Leave> getAllLeaves(Long bId);
	
	public void addLeave(Leave leave);
	
}
