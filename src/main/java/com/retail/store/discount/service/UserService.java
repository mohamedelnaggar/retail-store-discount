package com.retail.store.discount.service;

import com.retail.store.discount.entity.User;
import com.retail.store.discount.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Collection<User> getAllUsers(){
        return this.userRepository.findAll();
    }

    public User getUserById(Long userId){
        User user = this.userRepository.findOneById(userId);
        if(user == null){
            // TODO : throw exception
        }
        return user;
    }

}
