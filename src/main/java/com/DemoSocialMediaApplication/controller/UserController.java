package com.DemoSocialMediaApplication.controller;

import com.DemoSocialMediaApplication.model.UserDetail;
import com.DemoSocialMediaApplication.service.UserDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "api/users")
public class UserController {

    @Autowired
    private UserDaoService userDaoService;

    @GetMapping
    public ResponseEntity<List<UserDetail>> getUsers() {
        List<UserDetail> users =  userDaoService.findAllUser();
        return ResponseEntity.ok(users);
    }

    @PostMapping
    public ResponseEntity<UserDetail> saveUser(@Valid @RequestBody UserDetail user) {
        UUID id =  userDaoService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED)
                .header("location", "api/users/"+id)
                .build();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<UserDetail> getUserById(@PathVariable UUID id) {
        UserDetail user =  userDaoService.findByUserId(id);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @GetMapping(path = "/email/{email}")
    public ResponseEntity<UserDetail> getUserByEmail(@PathVariable String email) {
        UserDetail user =  userDaoService.findByUserEmail(email);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }
}
