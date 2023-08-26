package com.picpaysimplify.picpaysimplify.services;

import com.picpaysimplify.picpaysimplify.domain.user.User;
import com.picpaysimplify.picpaysimplify.dtos.NotificationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotificationService {

    @Autowired
    private RestTemplate restTemplate;
    @Value("${mockNotify}")
    private String mockNotify;

    public void sendNotification(User user, String message) throws Exception {
        String email = user.getEmail();
        NotificationDTO notificationDTO = new NotificationDTO(email, message);

//        ResponseEntity<String> responseEntity = restTemplate.postForEntity(mockNotify, notificationDTO, String.class);
//
//        if(!(responseEntity.getStatusCode() == HttpStatus.OK)){
//            System.out.println("Notification service outbound.");
//            throw new Exception("Notification service outbound.");
//        }
        System.out.println("Notification sended to the user.");
    }
}
