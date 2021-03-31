package api.services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import api.entities.Notification;
import api.repositories.NotificationRepository;

@Service
public class NotificationService {
	@Autowired
	NotificationRepository notificationRepository;
	public Collection<Notification> getAll(){
		return notificationRepository.findAll();
	}
	public void add(Notification notification) {
		notificationRepository.save(notification);
		
	}
	
	

}
