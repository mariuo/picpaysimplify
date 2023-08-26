package com.picpaysimplify.picpaysimplify.services;

import com.picpaysimplify.picpaysimplify.domain.transaction.Transaction;
import com.picpaysimplify.picpaysimplify.domain.user.User;
import com.picpaysimplify.picpaysimplify.dtos.TransactionDTO;
import com.picpaysimplify.picpaysimplify.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@Service
public class TransactionService {
    @Autowired
    private UserService userService;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private NotificationService notificationService;

    @Value("${mockAuth}")
    private String mockAuth;

    public Transaction createTransaction(TransactionDTO dto) throws Exception {
        User sender = this.userService.findUserById(dto.senderId());
        User receiver = this.userService.findUserById(dto.receiverId());

        userService.validateTransaction(sender, dto.value());

        boolean isAuthorized = this.authorizedTransaction(sender, dto.value());

        if(!isAuthorized){
            throw new Exception("Transaction denied!");
        }
        Transaction transaction = new Transaction();
        transaction.setAmount(dto.value());
        transaction.setSender(sender);
        transaction.setReceiver(receiver);
        transaction.setTimestamp(LocalDateTime.now());

        sender.setBalance(sender.getBalance().subtract(dto.value()));
        receiver.setBalance(receiver.getBalance().add(dto.value()));

        this.transactionRepository.save(transaction);
        this.userService.saveUser(sender);
        this.userService.saveUser(receiver);

        this.notificationService.sendNotification(sender, "Sended success Transaction");
        this.notificationService.sendNotification(receiver, "Received success Transaction");

        return transaction;
    }

    public boolean authorizedTransaction(User sender, BigDecimal value){
        ResponseEntity<Map> authResponse = restTemplate.getForEntity(mockAuth, Map.class);
        if(authResponse.getStatusCode() == HttpStatus.OK){
            String message = (String)authResponse.getBody().get("message");
            return "Autorizado".equalsIgnoreCase(message);
        }else return false;

    }
}
