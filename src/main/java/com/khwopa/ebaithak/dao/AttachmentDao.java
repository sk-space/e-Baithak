package com.khwopa.ebaithak.dao;

import java.util.List;

import com.khwopa.ebaithak.models.Attachment;

public interface AttachmentDao {
	
	public void createFile(Attachment a);
	
	public List<Attachment> getAllFiles();

}
