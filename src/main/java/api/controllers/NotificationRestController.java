package api.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import api.entities.Notification;
import api.notificators.DiscordWebhook;
import api.services.NotificationService;

@RestController
@RequestMapping("notification")
public class NotificationRestController {
	
	@Autowired
	NotificationService notificationService;
	
	@GetMapping("")
	public Collection<Notification> getAllNotifications(){
		return notificationService.getAll();
	}
	
	@PostMapping("")
	public ResponseEntity<HttpStatus> addNotification(@RequestBody Notification notification){
		notificationService.add(notification);
		DiscordWebhook webhook = new DiscordWebhook();
		webhook.send(notification.getMessage());
		//webhook.send("hey");
		return new ResponseEntity<HttpStatus>(HttpStatus.CREATED);	
	}
	
	@GetMapping("page={pageNumber}")
	public Collection<Notification> getAllNotificationPages(@PathVariable Integer number){
		return null;
	}

}
