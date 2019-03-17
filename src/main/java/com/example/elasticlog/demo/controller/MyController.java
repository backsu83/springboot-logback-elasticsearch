package com.example.elasticlog.demo.controller;

import com.example.elasticlog.demo.model.User;
import com.example.elasticlog.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class MyController {

    @Autowired
    UserService userService;

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        log.info("=== controller input value === " + id);
        User user = userService.getUserInfo(id);
        return new ResponseEntity<>(user , HttpStatus.OK);
    }
}
