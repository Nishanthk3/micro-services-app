package com.example.webapp.controller;

import com.example.webapp.entity.account.UserDO;
import com.example.webapp.repository.account.UserRepository;
import com.example.webapp.service.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping
    public List<User> getAll() {
        List<UserDO> userDOS = this.userRepository.findAll();
        List<User> users = new ArrayList<>();
        for (UserDO userDo : userDOS) {
            users.add(User.builder().id(userDo.getId()).firstName(userDo.getFirstName()).lastName(userDo.getLastName()).build());
        }
        return users;
    }

    @GetMapping(value = "{id}")
    public User get(@PathVariable Long id) {
        UserDO userDo = this.userRepository.findById(id).get();
        return User.builder().id(userDo.getId()).firstName(userDo.getFirstName()).lastName(userDo.getLastName()).build();
    }

    @GetMapping(value = "/name")
    public List<User> getByName(@RequestParam String name) {
        List<UserDO> userDOs = (List<UserDO>) this.userRepository.findByFirstName(name);
        List<User> users = new ArrayList<>();
        for (UserDO userDo : userDOs) {
            users.add(User.builder().id(userDo.getId()).firstName(userDo.getFirstName()).lastName(userDo.getLastName()).build());
        }
        return users;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Long insert(@RequestBody User user) {
        System.out.println(user.toString());
        UserDO userDO = this.userRepository.save(UserDO.builder().firstName(user.getFirstName()).lastName(user.getLastName()).build());
        return userDO.getId();
    }
}