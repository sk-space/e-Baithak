package com.khwopa.ebaithak.dao;

import java.util.List;

import com.khwopa.ebaithak.models.Notification;

public interface NotificationDao {

	public List<Notification> getNotifications(long userId);
	
	public void setNotification(Notification notif);
	
}
