package com.picpaysimplify.picpaysimplify.services;

import com.picpaysimplify.picpaysimplify.domain.user.User;
import com.picpaysimplify.picpaysimplify.domain.user.UserType;
import com.picpaysimplify.picpaysimplify.dtos.UserDTO;
import com.picpaysimplify.picpaysimplify.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void validateTransaction(User sender, BigDecimal amount) throws Exception {
        if(sender.getUserType() == UserType.MERCHANT){
            throw new Exception("User not authorized.");
        }
        if(sender.getBalance().compareTo(amount) < 0){
            throw new Exception("Amount insuficient");
        }
    }
    public User findUserById(Long id) throws Exception {
        return this.userRepository.findUserById(id).orElseThrow(()-> new Exception("User not found!"));
    }
    public User saveUser(User user){
        return this.userRepository.save(user);
    }

    public User createUser(UserDTO dto){
        User entity = new User(dto);
        return this.saveUser(entity);
    }
    public List<User> getAllUsers(){
        return this.userRepository.findAll();
    }
}
