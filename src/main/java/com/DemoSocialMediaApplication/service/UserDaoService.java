package com.DemoSocialMediaApplication.service;

import com.DemoSocialMediaApplication.exception.ResourceNotFoundException;
import com.DemoSocialMediaApplication.model.UserDetail;
import com.DemoSocialMediaApplication.respository.UserRepository;
import org.hibernate.exception.SQLGrammarException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserDaoService  {

    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserRepository userRepository;
    public List<UserDetail> findAllUser() {
        return userRepository.findAll();
    }

    public UserDetail findByUserId(UUID id) {
        logger.info(id.toString());
        Optional<UserDetail> user = userRepository.findById(id);
        if(user.isEmpty()) {
            throw new ResourceNotFoundException("User is not present in repository id" + id);
        }
        return user.get();
    }

    public UserDetail findByUserEmail(String email) {
        Optional<UserDetail> user = userRepository.findByUserEmail(email);
        if(user.isEmpty()) {
            throw new ResourceNotFoundException("User is not present in repository email" + email);
        }
        return user.get();
    }

    public UUID saveUser(UserDetail user) {
        user.setId(UUID.randomUUID());
        UserDetail addedUser = userRepository.save(user);
        return addedUser.getId();
    }
}
